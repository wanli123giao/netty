package netty.c3;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestLengthFileDecoder {
    public static void main(String[] args) {
        EmbeddedChannel channel=new EmbeddedChannel(
                new LengthFieldBasedFrameDecoder(1024, 0,4,0,4),
                new LoggingHandler(LogLevel.DEBUG)
        );

        ByteBuf byteBuf= ByteBufAllocator.DEFAULT.buffer();
        send(byteBuf,"Hello,world");
        send(byteBuf,"Hello");
        channel.writeInbound(byteBuf);
    }

    private static void send(ByteBuf byteBuf,String content) {
        byte[] bytes=content.getBytes();
        System.out.println(bytes);
        int length=bytes.length;
        byteBuf.writeInt(length);
        byteBuf.writeByte(1);
        byteBuf.writeBytes(bytes);
    }
}
