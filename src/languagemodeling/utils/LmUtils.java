package languagemodeling.utils;

import utils.wordembedding.SennaReader;
import utils.wordembedding.WordEmbeddingReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;

/**
 * Created by Thanakorn on 8/7/16.
 */
public class LmUtils {

    public static List<Character> readCharLevel(String path) {
        List<Character> characterList = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (!line.isEmpty()) {
                    for (char c : line.toCharArray()) {
                        characterList.add(c);
                    }
                    characterList.add('\n');
                } else {
                    characterList.add('\n');
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return characterList;
    }

    public static Map<String, float[]> readWordEmbedding(String path) {
        WordEmbeddingReader wordEmbeddingReader = new SennaReader();
        return wordEmbeddingReader.readWordEmbedding(path);
    }

}
