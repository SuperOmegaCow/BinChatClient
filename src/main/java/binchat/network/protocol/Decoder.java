/*
package binchat.network.protocol;

import binchat.network.exception.BadPacketException;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class Decoder extends ByteToMessageDecoder {

    private Packets.ProtocolData protocolData;

    public Decoder(Packets.ProtocolData protocolData) {
        this.protocolData = protocolData;
    }

    public Decoder(Packets packets) {
        this.protocolData = packets.INBOUND;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        ByteBuf copy = in.copy();
        int packetId = DefinedPacket.readVarInt(in);
        System.out.println("In " + packetId);
        DefinedPacket packet = null;
        if (protocolData.hasPacket(packetId)) {
            packet = protocolData.createPacket(packetId);
            packet.read(in);
            if (in.readableBytes() != 0) {
                throw new BadPacketException("Did not read all bytes from packet " + packet.getClass() + " " + packetId + " Protocol " + protocolData + " Direction INBOUND");
            }
        } else {
            in.skipBytes(in.readableBytes());
        }
        out.add(new PacketWrapper(packet, copy));
    }

    public void setProtocolData(Packets.ProtocolData protocolData) {
        this.protocolData = protocolData;
    }

    public void setProtocolData(Packets protocolData) {
        this.protocolData = protocolData.INBOUND;
    }

}
*/
