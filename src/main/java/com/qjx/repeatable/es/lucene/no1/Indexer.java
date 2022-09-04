package com.qjx.repeatable.es.lucene.no1;

import org.apache.commons.io.FileUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StoredField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

/**
 * @author qinjiaxing
 */
public class Indexer {
    private final IndexWriter writer;

    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            throw new IllegalArgumentException("Usage: java " + Indexer.class.getName() + "<index dir> <data dir>");
        }

        //获取字段
        String indexDir = args[0];
        String dataDir = args[1];

        long start = System.currentTimeMillis();
        //创建索引

        Indexer indexer = new Indexer(indexDir);


        //执行索引
        int numIndexed;
        try {
            numIndexed = indexer.index(dataDir, new TextFilesFilter());
        } finally {
            indexer.close();
        }

        //打印执行时间
        long end = System.currentTimeMillis();
        //输出执行时间
        System.out.println("Indexing " + numIndexed + " files took " + (end - start) + " ms");


    }

    private int index(String dataDir, FileFilter filter) throws IOException {
        File[] files = new File(dataDir).listFiles();
        assert files != null;
        for (File file : files) {
            if (!file.isDirectory() && !file.isHidden() && file.exists() && file.canRead() && (filter == null
                    || filter.accept(file))) {
                indexFile(file);
            }
        }
        return writer.numRamDocs();
    }


    private void indexFile(File file) throws IOException {
        System.out.println("Indexing " + file.getCanonicalPath());
        Document document = getDocument(file);
        writer.addDocument(document);
    }

    private Document getDocument(File file) throws IOException {
        //文件名
        String fileName = file.getName();

        //文件路径
        String path = file.getPath();
        //文件的内容
        String context = FileUtils.readFileToString(file, "utf-8");

        //创建Field
        //参数1 域的名称
        //参数2 域的内容
        //参数3 是否存储 store
        Field fieldName = new TextField("name", fileName, Field.Store.YES);
        Field fieldPath = new StoredField("path", path);
        Field fieldContext = new TextField("context", context, Field.Store.YES);

        //创建文档对象
        Document document = new Document();
        //向文档对象中添加域
        document.add(fieldName);
        document.add(fieldPath);
        document.add(fieldContext);

        return document;
    }

    private void close() throws IOException {
        writer.close();
    }

    public Indexer(String indexDir) throws IOException {
        Directory dir = FSDirectory.open(new File(indexDir).toPath());
        Analyzer analyzer = new StandardAnalyzer();
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        writer = new IndexWriter(dir, config);
    }

    private static class TextFilesFilter implements FileFilter {
        @Override
        public boolean accept(File pathname) {
            return pathname.getName().toLowerCase().endsWith(".txt");
        }
    }
}
