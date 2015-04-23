package binchat.network.protocol.packet.chat;

import binchat.network.protocol.AbstractPacketHandler;
import binchat.network.protocol.DefinedPacket;
import io.netty.buffer.ByteBuf;

public class Command extends DefinedPacket {

    private String message;

    public Command() {

    }

    public Command(String message) {
        this.message = message;
    }

    @Override
    public void read(ByteBuf buf) {
        this.message = readString(buf);
    }

    @Override
    public void write(ByteBuf buf) {
        writeString(this.message, buf);
    }

    @Override
    public void handle(AbstractPacketHandler handler) throws Exception {
        handler.handle(this);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
