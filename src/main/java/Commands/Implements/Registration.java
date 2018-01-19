package Commands.Implements;

import Commands.Command;
import Commands.Receiver;
import DB.Implements.MethodsDB;
import DB.InterfaceDB;
import Models.Network;
import Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class Registration implements Command {
    private final Receiver receiver;

    @Autowired
    public Registration(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        Network network = receiver.getNetwork();
        InterfaceDB addUserDB = new MethodsDB(receiver);
        if(network.getUserList().size()==0)
            addUserDB.getUserDB();
        Scanner scanner = new Scanner(System.in);
        String login, password;
        System.out.print("Enter login: ");
        login = scanner.nextLine();
        if(network.validationLogin(login)) {
            System.out.print("Enter password: ");
            password = scanner.nextLine();
            network.addUserList(new User(network.getUserList().size() + 1, login, password));
            System.out.println("Registration success. Your ID: " + network.getUserList().size() +
                    ", Login: " + login + ", password: " + password);
            addUserDB.setUserDB(network.getUser(login));
        } else
            System.out.println("Login isn't unique");
    }
}
