package com.qjx.pattern.proxy;

/**
 * @author: qinjiaxing
 * @Date: 2022/9/12 15:29
 * @Description: 状态模式，由不同类型的接口组成的状态模式
 */

class FloorCleaner {
    Attachment attachment = new Vacuum();

    public void change(Attachment attachment) {
        this.attachment = attachment;
    }

    public void clean() {
        attachment.action();
    }

}

interface Attachment {
    void action();
}

class Vacuum implements Attachment {
    @Override
    public void action() {
        System.out.println("Vacuum ...真空");
    }
}


class Mop implements Attachment {
    @Override
    public void action() {
        System.out.println("Mopping....");
    }
}

public class CleanTheFloor {
    public static void main(String[] args) {
        FloorCleaner floorCleaner = new FloorCleaner();
        //这里是擦除 Vacuum
        floorCleaner.clean();
        floorCleaner.change(new Mop());
        //这里是擦除 Mopping
        floorCleaner.clean();
    }
}
