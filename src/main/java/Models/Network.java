package Models;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Network {
    private User currentUser;
    private List<User> userList = new ArrayList<User>();
    private List<Massage> publicMassage = new ArrayList<Massage>();

    public void addUserList(User user){
        this.userList.add(user);
    }
    public List<User> getUserList(){
        return this.userList;
    }
    public User getUser(String login){
        for(int i = 0; i < this.userList.size(); i++){
            if(this.userList.get(i).getLogin().equals(login))
                return this.userList.get(i);
        }
        return null;
    }
    public void addCurrentUser(User currentUser){
        this.currentUser = currentUser;
    }
    public User getCurrentUser(){
        return this.currentUser;
    }

    public List<Massage> getPublicMassage() {
        return publicMassage;
    }

    public void setPublicMassage(Massage publicMassage){
        this.publicMassage.add(publicMassage);
    }

    public boolean validationTheEntrance(){
        if(this.currentUser == null)
            return true;
        return false;
    }
    public boolean validationLogin(String login){
        if(this.userList!=null){
            for(int i = 0; i < this.userList.size(); i++){
                if(this.userList.get(i).getLogin().equals(login))
                    return false;
            }
        }
        return true;
    }
    public boolean connectToNetWork(String login, String password){
        for(int i = 0; i < this.userList.size(); i++)
            if(this.userList.get(i).getLogin().equals(login))
                if(this.userList.get(i).getPassword().equals(password))
                    return true;
        return false;
    }

}
