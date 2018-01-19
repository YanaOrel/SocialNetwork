package Commands.Implements;

import Commands.Command;
import Commands.Receiver;
import DB.Implements.MethodsDB;
import DB.InterfaceDB;
import Models.Network;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShowPublicMassage implements Command {
    private final Receiver receiver;

    @Autowired
    public ShowPublicMassage(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        Network network = receiver.getNetwork();
        InterfaceDB show = new MethodsDB(receiver);
        show.getPublicMassageDB();
        if(network.getPublicMassage().size()!=0)
        {
            for(int i = 0; i < network.getPublicMassage().size(); i++){
                System.out.println("User " + network.getPublicMassage().get(i).getSenderUserMassage().getLogin() +
                    " send massage: " + network.getPublicMassage().get(i).getText());
            }
        }
    }
}
