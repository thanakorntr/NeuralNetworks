package utils.wordembedding;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Thanakorn on 8/7/16.
 */
public class SennaReader implements WordEmbeddingReader {

    @Override
    public Map<String, float[]> readWordEmbedding(String path) {
        Map<String, float[]> wordembeddingMap = new HashMap<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (!line.isEmpty()) {
                    String[] vals = line.split(" ");
                    String word = vals[0];
                    float[] embedding = new float[vals.length - 1];
                    for (int i = 0; i < embedding.length; i++) {
                        embedding[i] = Float.parseFloat(vals[i + 1]);
                    }
                    wordembeddingMap.put(word, embedding);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wordembeddingMap;
    }

}
