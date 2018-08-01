package nettystudy;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.logging.Logger;

public class TimeClient {

    private static int[] getNext(byte[] pattern){
        int[] next = new int[pattern.length];
        next[0] = -1;
        int k = 0;
        for (int j = 2; j < pattern.length; j++) {
            while (k>0&&pattern[j-1]!=pattern[k]){
                k = next[k];
            }
            if (pattern[j-1]==pattern[k])
                k++;
            next[j] = k;
        }
        return next;
    }

    public static int KMP(byte[] source,byte[] pattern){
        int[] next = getNext(pattern);
        int i = 0, j = 0;
        while (i < source.length && j < pattern.length)
        {
            if (j==-1||source[i] == pattern[j])
            {
                ++i;
                ++j;
            }
            else
            {
                j = next[j];
            }
        }
        if (j==pattern.length)
            return i - j;
        else
            return -1;
    }

    public void connect(int port, String host) throws Exception{
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new TimeClientHandler());
                        }
                    });

            ChannelFuture f = b.connect(host, port).sync();
            f.channel().closeFuture().sync();
        }finally {
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception{
        int port = 8080;
        new TimeClient().connect(port, "localhost");
    }

    static class TimeClientHandler extends ChannelInboundHandlerAdapter{

        private static final Logger logger = Logger.getLogger(TimeClientHandler.class.getName());

        private final ByteBuf firstMessage;

        public TimeClientHandler(){
            byte[] req = "QUERY TIME ORDER".getBytes();
            firstMessage = Unpooled.buffer(req.length);
            firstMessage.writeBytes(req);
        }

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            ctx.writeAndFlush(firstMessage);
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            ByteBuf buf = (ByteBuf)msg;
            byte[] req = new byte[buf.readableBytes()];
            buf.readBytes(req);
            String body = new String(req, "UTF-8");
            System.out.println("Now is : "+body);
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            logger.warning("Unexpected exception from downstream : "+cause.getMessage());
            ctx.close();
        }
    }

}
