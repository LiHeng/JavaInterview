package designpattern.factory.method;

/**
 * 添加新产品时，除了增加新产品类外，还要提供与之对应的具体工厂类，系统类的个数将成对增加，
 * 在一定程度上增加了系统的复杂度；同时，有更多的类需要编译和运行，会给系统带来一些额外的开销
 * 工厂方法模式
 */
public abstract class Factory {

    abstract Product getProduct();

}
