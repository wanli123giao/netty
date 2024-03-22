package netty.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;
import netty.message.Message;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class MessageCodec extends ByteToMessageCodec<Message> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Message message, ByteBuf byteBuf) throws Exception {
        // 写入一个预定义的字节数组作为消息的开始标记或协议的一部分。
        byteBuf.writeBytes(new byte[]{1,2,3,4});

        // 写入单个字节。这里写入的是1，可能用作版本号或者其他标识。
        byteBuf.writeByte(1);

        // 写入单个字节。这里写入的是0，可能用作占位或分隔符。
        byteBuf.writeByte(0);

        // 写入消息的类型。这是一个动态值，基于传入的Message对象。
        byteBuf.writeByte(message.getMessageType());

        // 写入消息的序列号。这同样是一个动态值，表示消息的唯一标识。
        byteBuf.writeInt(message.getSequenceId());

        // 写入一个0xff字节，可能作为消息头的结束标记。
        byteBuf.writeByte(0xff);

        // 创建一个ByteArrayOutputStream，用于持有序列化后的对象。
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        // 创建一个ObjectOutputStream，它可以将对象序列化到ByteArrayOutputStream。
        ObjectOutputStream obj = new ObjectOutputStream(bos);
        // 将message对象序列化。
        obj.writeObject(message);
        // 获得序列化后的字节数据。
        byte[] bytes = bos.toByteArray();

        // 写入序列化对象的长度，这对于接收方解码是必要的。
        byteBuf.writeInt(bytes.length);

        // 写入序列化对象本身的数据。
        byteBuf.writeBytes(bytes);
    }


        @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {

    }
}
