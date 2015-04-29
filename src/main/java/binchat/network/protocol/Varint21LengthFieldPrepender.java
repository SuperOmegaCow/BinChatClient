/*
package binchat.network.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class Varint21LengthFieldPrepender extends MessageToByteEncoder<ByteBuf> {

    @Override
    protected void encode(ChannelHandlerContext ctx, ByteBuf msg, ByteBuf out) throws Exception {
        int bodyLen = msg.readableBytes();
        int headerLen = varintSize(bodyLen);
        System.out.println("BodyLength " + bodyLen);
        System.out.println("HeaderLength " + headerLen);
        out.ensureWritable(headerLen + bodyLen);
        DefinedPacket.writeVarInt(bodyLen, out);
        out.writeBytes(msg);
    }

    private static int varintSize(int paramInt) {
        if ((paramInt & 0xFFFFFF80) == 0) {
            return 1;
        }
        if ((paramInt & 0xFFFFC000) == 0) {
            return 2;
        }
        if ((paramInt & 0xFFE00000) == 0) {
            return 3;
        }
        if ((paramInt & 0xF0000000) == 0) {
            return 4;
        }
        return 5;
    }


}*/
