package Commands;
import Models.Network;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Receiver {
    private final Network network;

    @Autowired
    public Receiver(Network network){
        this.network = network;
    }

    public Network getNetwork(){
        return network;
    }
}
