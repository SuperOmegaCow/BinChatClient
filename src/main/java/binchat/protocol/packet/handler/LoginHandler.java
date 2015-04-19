package binchat.protocol.packet.handler;

import binchat.logic.ServerConnection;
import binchat.logic.ServerManager;
import binchat.protocol.AbstractPacketHandler;
import binchat.protocol.packet.login.LoginSuccess;
import binchat.protocol.packet.login.PasswordRequest;

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
