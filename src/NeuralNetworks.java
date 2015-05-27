import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thanakorn on 5/25/15.
 */

/*
Fully connected NN
Simple implementation
*/

public class NeuralNetworks {

    public enum LEARNING_MODE {ONLINE, BATCH};
    public enum STOPPING_CRITERIA {FIXED_ITERATION, MEAN_SQUARE_ERROR} ;

    public List<NetworkLayer> networkLayerList = new ArrayList<>();
    public int numLayers;

    public NeuralNetworks(int numLayers) {
        this.numLayers = numLayers;

        for (int i = 0; i < numLayers; i++) {

        }
    }
}
