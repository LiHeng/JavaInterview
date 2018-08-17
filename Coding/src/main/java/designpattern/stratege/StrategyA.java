package designpattern.stratege;

public class StrategyA implements IStrategy{
    @Override
    public void operation() {
        System.out.println("执行A算法");
    }
}
