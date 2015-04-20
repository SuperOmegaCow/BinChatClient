package binchat.network.protocol;

import binchat.network.logic.ServerConnection;
import binchat.network.logic.ServerManager;
import binchat.network.protocol.packet.Kick;
import binchat.network.protocol.packet.chat.Chat;
import binchat.network.protocol.packet.chat.Command;
import binchat.network.protocol.packet.chat.ConnectedClient;
import binchat.network.protocol.packet.handshake.Handshake;
import binchat.network.protocol.packet.login.LoginStart;
import binchat.network.protocol.packet.login.LoginSuccess;
import binchat.network.protocol.packet.login.PasswordRequest;
import binchat.network.protocol.packet.login.PasswordResponse;

public class AbstractPacketHandler {

    private ServerManager serverManager;
    private ServerConnection serverConnection;

    public AbstractPacketHandler(ServerManager serverManager, ServerConnection serverConnection) {
        this.serverManager = serverManager;
        this.serverConnection = serverConnection;
    }

    public void handle(Handshake handshake) throws Exception {

    }

    public void handle(LoginStart loginStart) throws Exception {

    }

    public void handle(PasswordRequest passwordRequest) throws Exception {

    }

    public void handle(PasswordResponse passwordResponse) throws Exception {

    }

    public void handle(LoginSuccess loginSuccess) throws Exception {

    }

    public void handle(ConnectedClient connectedClient) throws Exception {

    }

    public void handle(Chat chat) throws Exception {

    }

    public void handle(Command command) throws Exception {

    }

    public void handle(Kick kick) throws Exception {

    }

    public ServerManager getServerManager() {
        return serverManager;
    }

    public ServerConnection getServerConnection() {
        return serverConnection;
    }
}
