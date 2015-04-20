package binchat.network.protocol.packet.handler;

import binchat.network.logic.ServerConnection;
import binchat.network.logic.ServerManager;
import binchat.network.protocol.AbstractPacketHandler;
import binchat.network.protocol.packet.chat.Chat;
import binchat.network.protocol.packet.chat.Command;
import binchat.network.protocol.packet.chat.ConnectedClient;

public class ChatHandler extends AbstractPacketHandler {

    public ChatHandler(ServerManager serverManager, ServerConnection serverConnection) {
        super(serverManager, serverConnection);
    }

    @Override
    public void handle(Chat chat) throws Exception {

    }

    @Override
    public void handle(Command command) throws Exception {

    }

    @Override
    public void handle(ConnectedClient connectedClient) throws Exception {

    }

}
