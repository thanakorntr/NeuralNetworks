package languagemodeling.datastructure;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thanakorn on 8/7/16.
 */
public class Sequence <T> {

    public List<Token<T>> sequence;

    public Sequence() {
        sequence = new ArrayList<>();
    }

}
