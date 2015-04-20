package binchat.network.protocol.packet.handler;

import binchat.network.logic.ServerConnection;
import binchat.network.logic.ServerManager;
import binchat.network.protocol.AbstractPacketHandler;

public class HandshakeHandler extends AbstractPacketHandler {

    public HandshakeHandler(ServerManager serverManager, ServerConnection serverConnection) {
        super(serverManager, serverConnection);
    }

}
