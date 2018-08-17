package java8;

public interface Java8Interface {

    void show();

    // 可以重载Object里面的方法
    default String toString(int type){
        return null;
    }

    // 不可以重写Object里面的方法
//    default String toString(){
//        return null;
//    }

}
