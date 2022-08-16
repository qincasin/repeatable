package com.qjx.smell.bad;

import lombok.Getter;
import lombok.Setter;

public class SwitchBadSmellTest {
    /**
     * 获取book price
     * @param user
     * @param book
     * @return
     */
    public double getBookPrice(final User user,final Book book){
        UserLevel level = user.getUserLevel();
        return level.getPrice(book);
    }

    /**
     * 获取ebook price
     * @param user
     * @param eBook
     * @return
     */
    public double getEbookPrice(final User user,final EBook eBook){
        UserLevel level = user.getUserLevel();
        return level.getPrice(eBook);
    }

}

enum UserLevel {
    silver() {
        @Override
        public double getPrice(Book book) {
            return book.getPrice();
        }

        @Override
        public double getPrice(EBook eBook) {
            return eBook.getPrice();
        }
    }, gold() {
        @Override
        public double getPrice(Book book) {
            return book.getPrice() * 0.8;
        }

        @Override
        public double getPrice(EBook eBook) {
            return eBook.getPrice() * 0.85;
        }
    }, platinum() {
        @Override
        public double getPrice(Book book) {
            return book.getPrice() * 0.7;
        }

        @Override
        public double getPrice(EBook eBook) {
            return eBook.getPrice() * 0.75;
        }
    };

    public abstract double getPrice(Book book);

    public abstract double getPrice(EBook eBook);
}

@Getter
@Setter
class Book {
    private Integer id;
    private String name;
    private double price;
}

@Getter
@Setter
class EBook {
    private Integer id;
    private String name;
    private double price;
}

@Setter
@Getter
class User{
    private Integer id;
    private String name;
    private UserLevel userLevel;
}