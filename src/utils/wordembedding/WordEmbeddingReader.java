package utils.wordembedding;

import java.util.Map;

/**
 * Created by Thanakorn on 8/7/16.
 */
public interface WordEmbeddingReader {

    Map<String, float[]> readWordEmbedding(String path);

}
