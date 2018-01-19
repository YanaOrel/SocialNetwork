package Commands.Implements;

import Commands.Command;
import Commands.Receiver;
import DB.Implements.MethodsDB;
import DB.InterfaceDB;
import Models.Network;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class AddFriend implements Command {
    private final Receiver receiver;

    @Autowired
    public AddFriend(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        Network network = receiver.getNetwork();
        Scanner scanner = new Scanner(System.in);
        String name;
        InterfaceDB addFriend = new MethodsDB(receiver);
        if(network.getCurrentUser().getFriendList().size()==0)
            addFriend.getFriendBD();
        System.out.println("Enter name friend: ");
        name = scanner.nextLine();
        System.out.println(network.validationLogin(name));
        if(!network.getCurrentUser().getLogin().equals(name))
            if(!network.getCurrentUser().validateFriend(name) && !network.validationLogin(name)) {
                network.getCurrentUser().addFriend(network.getUser(name));
                network.getUser(name).addFriend(network.getCurrentUser());
                addFriend.setFriendDB(network.getCurrentUser().getLogin(), name);
            }
            else
                System.out.println("User doesn't exist");
        else
            System.out.println("You can not add yourself as a friend");

    }

}
