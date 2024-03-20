package netty.c2;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import lombok.extern.slf4j.Slf4j;

import static netty.c2.testByteBuf.log;

@Slf4j
public class TestSlice {
    public static void main(String[] args) {
        ByteBuf buf= ByteBufAllocator.DEFAULT.buffer(10);
        buf.writeBytes(new byte[]{'a','b','c','d','e','f','g','h','i','j'});
        log(buf);

        ByteBuf s1 = buf.slice(0, 5);
        s1.retain();
        ByteBuf s2 = buf.slice(5, 5);
        s1.retain();
        log(s1);
        log(s2);

        s1.setByte(0, 'b');
        log(s1);
//释放buf
        buf.release();
        log(s1);

        s1.release();
        s2.release();

    }
}
