package netty.c1;

import java.nio.ByteBuffer;

import static netty.c1.ByteBufferUtil.debugAll;

public class TestByteBufferReadWrite {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put((byte) 97);
        debugAll(buffer);
        buffer.put(new byte[]{0x62,0x63,0x64} );
        debugAll(buffer);
        sout(123)
    }
}
