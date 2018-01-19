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
public class SendPublicMassage implements Command {
    private final Receiver receiver;

    @Autowired
    public SendPublicMassage(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        Network network = receiver.getNetwork();
        Scanner scanner = new Scanner(System.in);
        String text;
        System.out.print("Enter public massage: ");
        text = scanner.nextLine();
        Massage mas = new Massage(network.getCurrentUser(), text);
        network.setPublicMassage(mas);
        InterfaceDB masDB = new MethodsDB(receiver);
        masDB.setPublicMassageDB(mas);
    }
}
