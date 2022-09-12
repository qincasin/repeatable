package com.qjx.pattern.proxy;

/**
 * @author: qinjiaxing
 * @Date: 2022/9/12 15:10
 * @Description:
 */


interface ProxyBase {
    void f();

    void g();

    void h();
}

class Proxy implements ProxyBase {
    private ProxyBase implementation = new Implementation();

    private ProxyBase implementation2 = new Implementation2();

    @Override
    public void f() {
        implementation.f();
        implementation2.f();
    }

    @Override
    public void g() {
        implementation.g();
        implementation2.g();
    }

    @Override
    public void h() {
        implementation.h();
        implementation2.h();
    }
}

class Implementation implements ProxyBase {
    @Override
    public void f() {
        System.out.println("f()");
    }

    @Override
    public void g() {
        System.out.println("g()");
    }

    @Override
    public void h() {
        System.out.println("h()");
    }
}

class Implementation2 implements ProxyBase {
    @Override
    public void f() {
        System.out.println("f2()");
    }

    @Override
    public void g() {
        System.out.println("g2()");
    }

    @Override
    public void h() {
        System.out.println("h2()");
    }
}

public class ProxyDemo {
    public static void main(String[] args) {
        Proxy proxy = new Proxy();
        proxy.f();
        proxy.g();
        proxy.h();
    }
}

