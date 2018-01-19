package DB;

import Models.Massage;
import Models.Network;
import Models.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface InterfaceDB {
    void setUserDB(User user);
    User getUserDB();
    void setFriendDB(String name, String friend);
    void getFriendBD();
    void setPublicMassageDB(Massage massage);
    void getPublicMassageDB();
    void setPrivateMassageDB(Massage massage);
    void getPrivateMassageDB();
}
