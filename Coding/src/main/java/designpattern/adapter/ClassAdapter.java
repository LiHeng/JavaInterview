package designpattern.adapter;

/**
 * 类的适配器模式
 */
public class ClassAdapter extends Adaptee implements Target{


    @Override
    public void request() {
        super.specificRequest();
    }


}
