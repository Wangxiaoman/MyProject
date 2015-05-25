package com.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class DiscardServerHandler extends ChannelHandlerAdapter { // (1)

    public void channelRead(ChannelHandlerContext ctx, Object msg) { // (2)
    	System.out.println(msg);
        // Discard the received data silently.
        ((ByteBuf) msg).release(); // (3)
    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }
}
