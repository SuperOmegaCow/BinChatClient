/*
package binchat.network.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class Encoder extends MessageToByteEncoder<DefinedPacket> {

    private Packets.ProtocolData protocolData;

    public Encoder(Packets.ProtocolData protocolData) {
        this.protocolData = protocolData;
    }

    public Encoder(Packets packets) {
        this.protocolData = packets.OUTBOUND;
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, DefinedPacket msg, ByteBuf out) throws Exception {
        System.out.println("Out: " + protocolData.getId(msg.getClass()));
        DefinedPacket.writeVarInt(protocolData.getId(msg.getClass()), out);
        msg.write(out);
        System.out.println(out.readableBytes());
        System.out.println(out.isReadable());
    }

    public void setProtocolData(Packets.ProtocolData protocolData) {
        this.protocolData = protocolData;
    }

    public void setProtocolData(Packets protocolData1) {
        this.protocolData = protocolData1.OUTBOUND;
    }

}
*/
