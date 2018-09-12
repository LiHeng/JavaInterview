package designpattern.chain;

public class Main {
    public static void main(String[] args) {
        String msg = "大家好:),<script>,敏感,被就业";
        Request request = new Request();
        request.setRequestStr(msg);
        Response response = new Response();
        response.setResponseStr("response");

        FilterChain filterChain = new FilterChain();
        filterChain.addFilter(new HtmlFilter()).addFilter(new SensitiveFilter());
        filterChain.doFilter(request,response);
        System.out.println(request.getRequestStr());
        System.out.println(response.getResponseStr());
    }
}
