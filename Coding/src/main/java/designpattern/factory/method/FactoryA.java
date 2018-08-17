package designpattern.factory.method;

/**
 * 生产A类产品
 */
public class FactoryA extends Factory{

    @Override
    Product getProduct() {
        return new ProductA();
    }
}
