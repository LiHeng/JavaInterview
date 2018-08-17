package designpattern.stratege;

/**
 * 避免 if-else-if
 */
public class Context {

    IStrategy strategy;

    public void setStrategy(IStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * 由具体的算法去执行操作
     */
    public void dosomething(){
        strategy.operation();
    }



}
