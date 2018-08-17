package designpattern.factory.abstractp;

/**
 * 用于生产一组产品,产品是相关的
 * A类螺丝只能和A类螺帽配对
 */
public abstract class Factory {

    public abstract Product ManufactureContainer();
    public abstract Product ManufactureMould();

}
