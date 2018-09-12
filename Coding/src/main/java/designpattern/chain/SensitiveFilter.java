package designpattern.chain;

public class SensitiveFilter implements Filter{

    @Override
    public void doFilter(Request request, Response response, FilterChain filterChain) {
        request.setRequestStr(request.getRequestStr().replace("被就业", "就业").replace("敏感", "") + "----SensitiveFilter");
        filterChain.doFilter(request,response);
        response.setResponseStr(response.getResponseStr()+"---SensitiveFilter");
    }
}
