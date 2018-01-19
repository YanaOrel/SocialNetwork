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
public class Authorization implements Command {
    private final Receiver receiver;

    @Autowired
    public Authorization(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        Network network = receiver.getNetwork();
        Scanner scanner = new Scanner(System.in);
        String login, password;
        if(network.getUserList().size()==0) {
            InterfaceDB addUserDB = new MethodsDB(receiver);
            addUserDB.getUserDB();
        }
        if(network.validationTheEntrance() && network.getUserList()!=null){
            System.out.print("Enter login: ");
            login = scanner.nextLine();
            System.out.print("Enter password: ");
            password = scanner.nextLine();
            if(network.connectToNetWork(login, password)) {
                network.addCurrentUser(network.getUser(login));
                System.out.println("Welcome " + network.getCurrentUser().getLogin()+ " to the Social network");
            }
            else
                System.out.println("Login or password incorrect");
        }else
            System.out.println("User doesn't exist");
    }
}
