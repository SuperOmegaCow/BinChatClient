package binchat.protocol.packet.handler;

import binchat.logic.ServerConnection;
import binchat.logic.ServerManager;
import binchat.protocol.AbstractPacketHandler;
import binchat.protocol.packet.chat.Chat;
import binchat.protocol.packet.chat.Command;
import binchat.protocol.packet.chat.ConnectedClient;

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
