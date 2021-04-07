package com.my.lfy.api.test;

import lombok.Data;
import lombok.ToString;

/**
 * TestInnerAndOuterClass
 *
 * @author lfy
 * @date 2021/4/7
 **/
public class TestInnerAndOuterClass {

    public static void main(String[] args) {

        Outer outer = new Outer();
        System.out.println(outer.getOuter());
        System.out.println("============================================");

        Outer.StaticInner staticInner = new Outer.StaticInner();
        staticInner.setStaticInner("staticInner");
        System.out.println(staticInner.getStaticInner());
        staticInner.staticInner();
        System.out.println("============================================");

        Outer.Inner inner = outer.new Inner();
        inner.setInner("inner");
        System.out.println(inner.getInner());
        System.out.println("============================================");

        outer.setStaticInner(staticInner);
        outer.setInner(inner);
        inner.inner();
        System.out.println(outer);
    }
}


@Data
@ToString
class Outer {

    private static String staticOuter = "staticOuter";

    private String outer = "outer";

    private StaticInner staticInner;

    private Inner inner;

    public void out() {
        System.out.println("This is Outer Method");
    }

    @Data
    @ToString
    static class StaticInner {
        private String staticInner;

        public void staticInner() {
            System.out.println("This is Static Inner Method,StaticInner : " + staticInner);
        }
    }

    @Data
    @ToString
    class Inner {
        private String inner;

        public void inner() {
            System.out.println("This is Inner Method,Inner : " + outer + "||" + staticOuter);
        }
    }
}