package java8;

public class Java8InterfaceImpl implements Java8Interface{
    @Override
    public void show() {

    }

    //静态方法不能被重写
    public static void showStatic(){
        System.out.println("show static");
    }

    public static void main(String[] args) {
        Father mByFather = new Father();
        Father mBySon = new Son();
        Son son = new Son();

        mByFather.staticMethod();
        mBySon.staticMethod();  // 这里返回的是Father而不是Son， static方法上重写不会报错，但是从逻辑运行效果上来看达不到多态的目的
        son.staticMethod();

    }
}

class Father {
    public static void staticMethod() {
        System.out.println("Father");
    }
}

class Son extends Father {
    //	@Override  因为从逻辑上达不到重写的目的，所以这里添加 @Override 会编译报错
    public static void staticMethod() {
        System.out.println("Son");
    }
}

