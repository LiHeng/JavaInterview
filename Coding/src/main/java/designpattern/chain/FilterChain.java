package designpattern.chain;

import java.util.ArrayList;
import java.util.List;

// 责任链模式屏蔽了请求的处理过程，你发起一个请求到底是谁处理的，
// 这个你不用关心，只要你把请求抛给责任链的第一个处理者，
// 最终会返回一个处理结果（当然也可以不做任何处理），
// 作为请求者可以不用知道到底是需要谁来处理的，这是责任链模式的核心
public class FilterChain {

    private List<Filter> filters = new ArrayList<>();

    private int index = 0;

    public FilterChain addFilter(Filter filter){
        this.filters.add(filter);
        return this;
    }

    public void doFilter(Request request, Response response) {
        if (index==filters.size()){
            return;
        }
        Filter filter =  filters.get(index);
        index++;

        //方法会递归地调用filterChain的doFilter方法
        filter.doFilter(request,response,this);
    }
}
