package designpattern.chain;

public class HtmlFilter implements Filter{

    @Override
    public void doFilter(Request request, Response response, FilterChain filterChain) {
        //对请求进行过滤
        request.setRequestStr(request.getRequestStr().replace('<', '[').replace('>', ']') + "---HtmlFilter");
        //交给后面的过滤器进行过滤
        filterChain.doFilter(request,response);
        //对响应进行过滤
        response.setResponseStr(response.getResponseStr()+"---HtmlFilter");
    }
}
