package designpattern.factory.simple;

/**
 * 简单工厂
 * 违背“开放 - 关闭原则”，一旦添加新产品就不得不修改工厂类的逻辑，这样就会造成工厂逻辑过于复杂
 */
public class Factory {

    public static Product getProduct(String type){
        switch (type){
            case "A":
                return new ProductA();
            case "B":
                return new ProductB();
            default:
                return null;
        }
    }

}
