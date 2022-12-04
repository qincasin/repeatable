package com.qjx.sample._17._03_perform;

import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;

/**
 * @author: qinjiaxing
 * @Date: 2022/12/3 12:00
 * @Description:
 */
class DigestTest {
    @Test
    public void test() {
        String alg = "SHA-256";
        Digest d = null;
        try {
            d = Digest.of(alg);
            d.digest("hello world".getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void test02() {
        Coded<Digest> coded = Digest.of2("SHA-256");
        if (coded.errorCode() != 0) {
            //snipped
            return;
        }
        coded.returned().digest("Hello world".getBytes());

    }

    @Test
    public void test03() {
        Returned<Digest> returned = Digest.of3("SHA-256");
        switch (returned) {
            case Returned.ReturnValue rv -> {
                Digest d = (Digest) rv.returnValue();
                d.digest("hello world".getBytes());
            }
            case Returned.ErrorCode ec -> {
                System.out.println("Failed to get instance of SHA-256" + ec);
            }
        }
    }

    @Test
    public void testFlow() {
        Digest.ofFlow("SHA-256", md -> {
            System.out.println("SHA-256 is not support");
            md.digestFlow("hello world".getBytes(), values -> {
                System.out.println("SHA-256 is available");
            }, errorCode -> {
                System.out.println("SHA-256 is not avaliable");
            });
        }, errorCode -> {
            System.out.println("Unsupported alg : SHA-256");
        });
    }

}