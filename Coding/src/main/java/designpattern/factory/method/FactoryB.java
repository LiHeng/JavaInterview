package designpattern.factory.method;

/**
 * 生产B类产品
 */
public class FactoryB extends Factory{
    @Override
    Product getProduct() {
        return new ProductB();
    }
}
