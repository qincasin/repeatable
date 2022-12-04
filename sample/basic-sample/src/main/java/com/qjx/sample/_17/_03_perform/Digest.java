package com.qjx.sample._17._03_perform;

import java.security.NoSuchAlgorithmException;
import java.util.function.Consumer;

/**
 * @author: qinjiaxing
 * @Date: 2022/12/3 11:57
 * @Description:
 */
public sealed abstract class Digest {

    //version 1
    abstract byte[] digest(byte[] message);

    //version 2
    public static void ofFlow(String alg, Consumer<Digest> onsuccess,Consumer<Integer> onFailure){
    }

    //version 2
    abstract void digestFlow(byte[] message,Consumer<byte[]> onsuccess,Consumer<Integer> onFailure);


    private static final class Sha256 extends Digest {
        @Override
        byte[] digest(byte[] message) {
            return new byte[0];
        }

        @Override
        void digestFlow(byte[] message, Consumer<byte[]> onsuccess, Consumer<Integer> onFailure) {

        }
    }

    private static final class Sha512 extends Digest {
        @Override
        byte[] digest(byte[] message) {
            return new byte[0];
        }

        @Override
        void digestFlow(byte[] message, Consumer<byte[]> onsuccess, Consumer<Integer> onFailure) {

        }
    }

    public static Digest of(String alg) throws NoSuchAlgorithmException {
        return switch (alg) {
            case "SHA-256" -> new Sha256();
            case "SHA-512" -> new Sha512();
            default -> throw new NoSuchAlgorithmException();
            //            default -> new Coded<>(null,-1);
        };
    }

    public static Coded<Digest> of2(String alg) {
        return switch (alg) {
            case "SHA-256" -> new Coded<>(new Sha256(), 0);
            case "SHA-512" -> new Coded<>(new Sha512(), 0);
            default -> new Coded<>(null, -1);
        };
    }

    public static Returned<Digest> of3(String alg) {
        return switch (alg) {
            case "SHA-256" -> new Returned.ReturnValue(new Sha256());
            case "SHA-512" -> new Returned.ReturnValue(new Sha512());
            case null, default -> new Returned.ErrorCode<>(-1);
        };
    }


}

record Coded<T>(T returned, int errorCode) {
    //blank
};

sealed interface Returned<T> {
    record ReturnValue<T>(T returnValue) implements Returned {

    }

    record ErrorCode<T>(Integer errorCode) implements Returned {

    }
}
