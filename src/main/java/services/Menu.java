package services;

import Commands.Implements.*;
import Commands.Invoker;
import DB.InterfaceDB;
import Models.Network;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class Menu {
    private final Network network;
    private final Invoker invoker;
    private final Registration registration;
    private final Authorization authorization;
    private final AddFriend addFriend;
    private final Logout logout;
    private final ShowFriends showFriends;
    private final SendPrivateMassage sendPrivateMassage;
    private final SendPublicMassage sendPublicMassage;
    private final ShowPrivateMassage showPrivateMassage;
    private final ShowPublicMassage showPublicMassage;
    private final ShowUserList showUserList;
    private final InterfaceDB interfaceDB;


    @Autowired
    public Menu(Network network, Invoker invoker, Registration registration, Authorization authorization, AddFriend addFriend,
                Logout logout, ShowFriends showFriends, SendPrivateMassage sendPrivateMassage, SendPublicMassage sendPublicMassage,
                ShowPrivateMassage showPrivateMassage, ShowPublicMassage showPublicMassage, ShowUserList showUserList,
                InterfaceDB interfaceDB){
        this.network = network;
        this.invoker = invoker;
        this.registration = registration;
        this.authorization = authorization;
        this.addFriend = addFriend;
        this.logout = logout;
        this.showFriends = showFriends;
        this.sendPrivateMassage = sendPrivateMassage;
        this.sendPublicMassage = sendPublicMassage;
        this.showPrivateMassage = showPrivateMassage;
        this.showPublicMassage = showPublicMassage;
        this.showUserList = showUserList;
        this.interfaceDB = interfaceDB;
    }
    public void showMenu(){
        Scanner scanner = new Scanner(System.in);
        int menu, menuNW;
        do{
            System.out.println("Menu");
            System.out.println("0. Exit social network");
            System.out.println("1. Registration");
            System.out.println("2. Authorization");
            System.out.print("Select menu item: ");
            menu = scanner.nextInt();
            switch(menu){
                case 0: break;
                case 1:
                    invoker.setCommand(registration);
                    invoker.run();
                    break;
                case 2:
                    invoker.setCommand(authorization);
                    invoker.run();
                    if(network.getCurrentUser()!=null) {
                        do {
                            System.out.println("Menu Social Network");
                            System.out.println("0. Logout");
                            System.out.println("1. Show user list");
                            System.out.println("2. Add friend");
                            System.out.println("3. Show my friends");
                            System.out.println("4. Send public massage");
                            System.out.println("5. Show public massage");
                            System.out.println("6. Send private massage");
                            System.out.println("7. Show private massage");
                            menuNW = scanner.nextInt();
                            switch (menuNW) {
                                case 0:
                                    invoker.setCommand(logout);
                                    invoker.run();
                                    break;
                                case 1:
                                    invoker.setCommand(showUserList);
                                    invoker.run();
                                    break;
                                case 2:
                                    invoker.setCommand(addFriend);
                                    invoker.run();
                                    break;
                                case 3:
                                    invoker.setCommand(showFriends);
                                    invoker.run();
                                    break;
                                case 4:
                                    invoker.setCommand(sendPublicMassage);
                                    invoker.run();
                                    break;
                                case 5:
                                    invoker.setCommand(showPublicMassage);
                                    invoker.run();
                                    break;
                                case 6:
                                    invoker.setCommand(sendPrivateMassage);
                                    invoker.run();
                                    break;
                                case 7:
                                    invoker.setCommand(showPrivateMassage);
                                    invoker.run();
                                    break;
                                default:
                                    break;
                            }
                        } while (menuNW != 0);
                    }
                    break;
                default:
                    System.out.println("Item menu exist");
                    break;
            }
        }while(menu!=0);
    }
}
