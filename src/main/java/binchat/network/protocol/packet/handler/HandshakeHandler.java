package binchat.network.protocol.packet.handler;

import binchat.network.logic.ServerConnection;
import binchat.network.logic.ServerManager;
import binchat.network.logic.State;
import binchat.network.protocol.AbstractPacketHandler;
import binchat.network.protocol.packet.handshake.Handshake;
import binchat.network.protocol.packet.login.LoginStart;

public class HandshakeHandler extends AbstractPacketHandler {

    public HandshakeHandler(ServerManager serverManager, ServerConnection serverConnection) {
        super(serverManager, serverConnection);
    }

    @Override
    public void handle(Handshake handshake) throws Exception {
        if(handshake.getNextState() == 1) {
            this.getServerConnection().setState(State.LOGIN);
            this.getServerConnection().sendPacket(new LoginStart(this.getServerManager().getName()));
        }
    }

}
