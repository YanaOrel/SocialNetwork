package Models;

import org.springframework.beans.factory.annotation.Autowired;

public class Massage {
    private User senderUser;
    private User receiverUser;
    private String text;
    public Massage(User senderUser, String publicMassage){
        this.senderUser = senderUser;
        this.text = publicMassage;
    }
    public Massage(User senderUser, User receiverUser, String privateMassage){
        this.senderUser = senderUser;
        this.receiverUser = receiverUser;
        this.text = privateMassage;
    }

    public User getSenderUserMassage(){
        return this.senderUser;
    }
    public User getReceiverUserMassage(){
        return this.receiverUser;
    }
    public String getText(){
        return this.text;
    }
}
