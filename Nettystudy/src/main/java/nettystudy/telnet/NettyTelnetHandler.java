package nettystudy.telnet;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.net.InetAddress;
import java.util.Date;

public class NettyTelnetHandler extends SimpleChannelInboundHandler<String> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // Send greeting for a new connection.
        ctx.write("Welcome to " + InetAddress.getLocalHost().getHostName() + "!\n");
        ctx.write("It is " + new Date() + " now.\n");
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }


    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        String response;
        boolean close = false;
        if (msg.isEmpty()) {
            response = "Please type something.\n";
        } else if ("bye".equals(msg.toLowerCase())) {
            response = "Have a good day!\n";
            close = true;
        } else {
            response = "Did you say '" + msg + "'?\n";
        }

        ChannelFuture future = ctx.write(response);
        ctx.flush();
        if (close) {
            future.addListener(ChannelFutureListener.CLOSE);
        }
    }
}
