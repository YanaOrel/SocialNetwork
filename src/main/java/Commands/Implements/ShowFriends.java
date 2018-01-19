package Commands.Implements;

import Commands.Command;
import Commands.Receiver;
import DB.Implements.MethodsDB;
import DB.InterfaceDB;
import Models.Network;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShowFriends implements Command {
    private final Receiver receiver;

    @Autowired
    public ShowFriends(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        Network network = receiver.getNetwork();
        if(network.getCurrentUser().getFriendList().size()==0) {
            InterfaceDB addFriend = new MethodsDB(receiver);
            addFriend.getFriendBD();
        }
        if(network.getCurrentUser().getFriendList().size()!=0) {
            for (int i = 0; i < network.getCurrentUser().getFriendList().size(); i++)
                System.out.println("Friend ID: " + network.getCurrentUser().getFriendList().get(i).getID() +
                        ", Login: " + network.getCurrentUser().getFriendList().get(i).getLogin());
        }
        else
            System.out.println("You don't have friends");
    }
}
