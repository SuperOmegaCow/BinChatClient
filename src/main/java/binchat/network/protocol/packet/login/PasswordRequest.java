package binchat.network.protocol.packet.login;

import binchat.network.protocol.AbstractPacketHandler;
import binchat.network.protocol.DefinedPacket;
import io.netty.buffer.ByteBuf;

public class PasswordRequest extends DefinedPacket {

    public PasswordRequest() {

    }

    @Override
    public void read(ByteBuf buf) {

    }

    @Override
    public void write(ByteBuf buf) {

    }

    @Override
    public void handle(AbstractPacketHandler handler) throws Exception {
        handler.handle(this);
    }


}
