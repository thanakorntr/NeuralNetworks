package MNIST.utils;

import MNIST.Image;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thanakorn on 7/3/16.
 */
public class MnistUtils {

    public static List<Image> loadImages(String path) {
        List<Image> images = new ArrayList<>();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (!line.isEmpty()) {
                    String[] vals = line.split("\\,");
                    int goldDigit = Integer.parseInt(vals[0]);
                    int numFeats = vals.length - 1;
                    float[] input = new float[numFeats];
                    for (int i = 0; i < numFeats; i++) {
                        input[i] = Float.parseFloat(vals[i + 1]);
                    }
                    float[] goldOutput = new float[10];
                    goldOutput[goldDigit] = 1;
                    Image image = new Image();
                    image.goldDigit = goldDigit;
                    image.input = input;
                    image.goldOutput = goldOutput;
                    images.add(image);
                }
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return images;
    }

}
