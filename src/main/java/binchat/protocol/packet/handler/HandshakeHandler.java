package binchat.protocol.packet.handler;

import binchat.logic.ServerConnection;
import binchat.logic.ServerManager;
import binchat.protocol.AbstractPacketHandler;

public class HandshakeHandler extends AbstractPacketHandler {

    public HandshakeHandler(ServerManager serverManager, ServerConnection serverConnection) {
        super(serverManager, serverConnection);
    }

}
