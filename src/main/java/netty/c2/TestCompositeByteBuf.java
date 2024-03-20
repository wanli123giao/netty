package netty.c2;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.CompositeByteBuf;
import lombok.extern.slf4j.Slf4j;

import static netty.c2.testByteBuf.log;

@Slf4j
public class TestCompositeByteBuf {
    public static void main(String[] args) {
        ByteBuf buf1= ByteBufAllocator.DEFAULT.buffer();
        buf1.writeBytes(new byte[]{'1','2','3'});

        ByteBuf buf2= ByteBufAllocator.DEFAULT.buffer();
        buf1.writeBytes(new byte[]{'4','5','6'});

        ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer();
        buffer.writeBytes(buf1).writeBytes(buf2);

        log(buffer);

        CompositeByteBuf byteBufs = ByteBufAllocator.DEFAULT.compositeBuffer();
        byteBufs.addComponents(true,buf1,buf2);
        log(byteBufs);
    }
}
