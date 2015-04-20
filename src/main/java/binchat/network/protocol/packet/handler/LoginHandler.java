package binchat.network.protocol.packet.handler;

import binchat.network.logic.ServerConnection;
import binchat.network.logic.ServerManager;
import binchat.network.protocol.AbstractPacketHandler;
import binchat.network.protocol.packet.login.LoginSuccess;
import binchat.network.protocol.packet.login.PasswordRequest;

public class LoginHandler extends AbstractPacketHandler {

    public LoginHandler(ServerManager serverManager, ServerConnection serverConnection) {
        super(serverManager, serverConnection);
    }

    @Override
    public void handle(PasswordRequest passwordRequest) throws Exception {

    }

    @Override
    public void handle(LoginSuccess loginSuccess) throws Exception {

    }

}
