package Commands.Implements;

import Commands.Command;
import Commands.Receiver;
import DB.Implements.MethodsDB;
import DB.InterfaceDB;
import Models.Massage;
import Models.Network;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class SendPrivateMassage implements Command {
    private final Receiver receiver;

    @Autowired
    public SendPrivateMassage(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        Network network = receiver.getNetwork();
        Scanner scanner = new Scanner(System.in);
        String text, recUser;
        System.out.print("Enter receiver name: ");
        recUser = scanner.nextLine();
        if(!network.validationLogin(recUser))
        {
            System.out.println("Enter private massage for " + recUser + ": ");
            text = scanner.nextLine();
            Massage mas = new Massage(network.getCurrentUser(),network.getUser(recUser) , text);
            network.getCurrentUser().setPrivateMassage(mas);
            network.getUser(recUser).setPrivateMassage(mas);
            InterfaceDB masDB = new MethodsDB(receiver);
            masDB.setPrivateMassageDB(mas);
        }
        else
            System.out.println("User doesn't exist");
    }
}
