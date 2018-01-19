import Commands.Implements.*;
import Commands.Invoker;
import Commands.Receiver;
import Models.Network;
import org.springframework.beans.BeansException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import services.Menu;
import java.util.Scanner;

public class Start {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext annot;
        annot = new AnnotationConfigApplicationContext("config");
        Menu menu = (Menu) annot.getBean("menu");
        menu.showMenu();
    }

}