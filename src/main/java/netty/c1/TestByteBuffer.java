package netty.c1;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author 1
 */
@Slf4j
public class TestByteBuffer {
    public static void main(String[] args) {

        try (FileChannel channel = new FileInputStream("data.txt").getChannel()) {
            ByteBuffer buffer=ByteBuffer.allocate(10);
            while (true){
                int read = channel.read(buffer);

                log.debug("读取到的字节数{}",read);
                if (read==-1){
                    break;
                }
                buffer.flip();//切换读写
                while(buffer.hasRemaining()){//判断buffer里面是否有剩余
                    byte b = buffer.get();
                    log.debug("实际字节{}",(char) b);
                }
                buffer.clear();
            }
        } catch (IOException e) {
        }
    }
}
