package binchat.network.protocol.packet.handler;

import binchat.network.logic.ServerConnection;
import binchat.network.logic.ServerManager;
import binchat.network.logic.State;
import binchat.network.protocol.AbstractPacketHandler;
import binchat.network.protocol.packet.Kick;
import binchat.network.protocol.packet.login.LoginSuccess;
import binchat.network.protocol.packet.login.PasswordRequest;
import binchat.network.protocol.packet.login.PasswordResponse;

public class LoginHandler extends AbstractPacketHandler {

    public LoginHandler(ServerManager serverManager, ServerConnection serverConnection) {
        super(serverManager, serverConnection);
    }

    @Override
    public void handle(PasswordRequest passwordRequest) throws Exception {
        PasswordResponse passwordResponse = new PasswordResponse(this.getServerManager().getPassword());
        this.getServerConnection().sendPacket(passwordResponse);
    }

    @Override
    public void handle(LoginSuccess loginSuccess) throws Exception {
        this.getServerConnection().setState(State.CHAT);
        this.getServerManager().getGuiManager().login();
    }

    @Override
    public void handle(Kick kick) throws Exception {

    }

}
