package Commands.Implements;

import Commands.Command;
import Commands.Receiver;
import Models.Network;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Logout implements Command {
    private final Receiver receiver;

    @Autowired
    public Logout(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        Network network = receiver.getNetwork();
        network.addCurrentUser(null);
    }
}
