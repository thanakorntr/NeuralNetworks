import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thanakorn on 5/25/15.
 */
public class NetworkLayer {

    public List<Neuron> neuronList = new ArrayList<>();
    public int numNeurons;

    public NetworkLayer(int numNeurons) {
        this.numNeurons = numNeurons;
    }
}
