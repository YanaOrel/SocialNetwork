package Models;

import java.util.ArrayList;
import java.util.List;

public class User {
    private int id;
    private String login;
    private String password;
    private List<User> friendList = new ArrayList<User>();
    private List<Massage> privateMassage = new ArrayList<Massage>();

    public void setPrivateMassage(Massage mas){
        this.privateMassage.add(mas);
    }
    public List<Massage> getPrivateMassage(){
        return this.privateMassage;
    }
    public User(int id, String login, String password){
        this.id = id;
        this.login = login;
        this.password = password;
    }
    public int getID()
    {
        return this.id;
    }
    public String getLogin(){
        return this.login;
    }
    public String getPassword(){
        return this.password;
    }


    public List<User> getFriendList() {
        return friendList;
    }

    public void addFriend(User friend){
        this.friendList.add(friend);
    }
    public boolean validateFriend(String name)
    {
        if(this.friendList.size()!=0){
            for(int i = 0; i< friendList.size(); i++){
                if(friendList.get(i).getLogin().equals(name)){
                    return false;
                }
            }
        }
        return false;
    }
}
