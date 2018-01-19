package Commands.Implements;

import Commands.Command;
import Commands.Receiver;
import Models.Network;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShowUserList implements Command {
    private final Receiver receiver;

    @Autowired
    public ShowUserList(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        Network network = receiver.getNetwork();
        for(int i = 0; i < network.getUserList().size(); i++)
            System.out.println("ID: " + network.getUserList().get(i).getID() +
                ", login: " + network.getUserList().get(i).getLogin());
    }
}
