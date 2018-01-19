package DB.Implements;

import Commands.Receiver;
import DB.InitDB;
import DB.InterfaceDB;
import Models.Massage;
import Models.Network;
import Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.List;

@Service
public class MethodsDB extends InitDB implements InterfaceDB {
    private final Receiver receiver;
    @Autowired
    public MethodsDB(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void setUserDB(User user) {
        String INSERT = "INSERT INTO user(name, password) VALUES (?, ?)";
        Connection connect = getConnectDB();
        try {
            PreparedStatement statement = connect.prepareStatement((INSERT));
            statement.setString(1,user.getLogin());
            statement.setString(2, user.getPassword());
            statement.execute();
            closeConnectDB(connect);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getUserDB() {
        Network network = receiver.getNetwork();
        String SELECT = "SELECT * FROM user";
        ResultSet res;
        Connection connect = getConnectDB();
        try {
            PreparedStatement statement = connect.prepareStatement(SELECT);
            res = statement.executeQuery();
            while(res.next()){
                network.addUserList(new User(
                                                res.getInt(1),
                                                res.getString(2),
                                                res.getString(3)
                ));
            }
            closeConnectDB(connect);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void setFriendDB(String name, String friend) {
        String INSERT_FRIEND = "INSERT INTO friend(name, friend) VALUES (?, ?)";
        Connection connect = getConnectDB();
        try {
            PreparedStatement statement = connect.prepareStatement(INSERT_FRIEND);
            statement.setString(1,name);
            statement.setString(2,friend);
            PreparedStatement statement1 = connect.prepareStatement(INSERT_FRIEND);
            statement1.setString(1,friend);
            statement1.setString(2,name);
            statement.execute();
            statement1.execute();
            closeConnectDB(connect);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getFriendBD() {
        Network network = receiver.getNetwork();
        String SELECT_FRIEND = "SELECT * FROM friend WHERE name = (?)";
        Connection connect = getConnectDB();
        try {
            PreparedStatement statement = connect.prepareStatement(SELECT_FRIEND);
            statement.setString(1,network.getCurrentUser().getLogin());
            ResultSet res = statement.executeQuery();
            while(res.next()){
                network.getCurrentUser().addFriend(network.getUser(res.getString(2)));
            }
            closeConnectDB(connect);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setPublicMassageDB(Massage massage) {
        Network network = receiver.getNetwork();
        String INSERT_PUBLIC_MASSAGE = "INSERT INTO public_massage(curent_user, text) VALUES (?,?)";
        Connection connect = getConnectDB();
        try {
            PreparedStatement statement = connect.prepareStatement(INSERT_PUBLIC_MASSAGE);
            statement.setString(1, massage.getSenderUserMassage().getLogin());
            statement.setString(2, massage.getText());
            statement.execute();
            closeConnectDB(connect);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getPublicMassageDB() {
        Network network = receiver.getNetwork();
        String SELECT_PUBLIC_MASSAGE = "SELECT * FROM public_massage";
        Connection connect = getConnectDB();
        try {
            PreparedStatement statement = connect.prepareStatement(SELECT_PUBLIC_MASSAGE);
            ResultSet res = statement.executeQuery();
            while(res.next()){
                network.setPublicMassage(
                        new Massage(
                            network.getUser(res.getString(1)),
                            res.getString(2)
                        )
                );
            }
            closeConnectDB(connect);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setPrivateMassageDB(Massage massage) {
        Network network = receiver.getNetwork();
        String INSERT_PRIVATE_MASSAGE = "INSERT INTO private_massage VALUES (?,?,?)";
        Connection connect = getConnectDB();
        try {
            System.out.println(connect.isClosed());
            PreparedStatement statement = connect.prepareStatement(INSERT_PRIVATE_MASSAGE);
            statement.setString(1, massage.getSenderUserMassage().getLogin());
            statement.setString(2, massage.getReceiverUserMassage().getLogin());
            statement.setString(3, massage.getText());
            statement.execute();
            closeConnectDB(connect);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void getPrivateMassageDB() {
        Network network = receiver.getNetwork();
        String SELECT_PRIVATE_MASSAGE = "SELECT * FROM private_massage";
        Connection connect = getConnectDB();
        try {
            PreparedStatement statement = connect.prepareStatement(SELECT_PRIVATE_MASSAGE);
            //statement.setString(1, network.getCurrentUser().getLogin());
            ResultSet res = statement.executeQuery();
            while(res.next()){
                if(res.getString(1).equals(network.getCurrentUser().getLogin())) {
                    network.getCurrentUser().setPrivateMassage(new Massage(
                            network.getUser(res.getString(1)),
                            network.getUser(res.getString(2)),
                            res.getString(3))
                    );
                }else
                    if(res.getString(2).equals(network.getCurrentUser().getLogin())){
                    network.getUser(res.getString(2)).setPrivateMassage(new Massage(
                            network.getUser(res.getString(1)),
                            network.getUser(res.getString(2)),
                            res.getString(3))
                    );
                }
            }
            closeConnectDB(connect);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
