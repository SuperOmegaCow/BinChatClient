/*
package binchat.network.protocol.packet.login;

import binchat.network.protocol.AbstractPacketHandler;
import binchat.network.protocol.DefinedPacket;
import io.netty.buffer.ByteBuf;

public class PasswordResponse extends DefinedPacket {

    private String password;

    public PasswordResponse() {

    }

    public PasswordResponse(String password) {
        this.password = password;
    }

    @Override
    public void read(ByteBuf buf) {
        this.password = readString(buf);
    }

    @Override
    public void write(ByteBuf buf) {
        writeString(this.password, buf);
    }

    @Override
    public void handle(AbstractPacketHandler handler) throws Exception {
        handler.handle(this);
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
*/
