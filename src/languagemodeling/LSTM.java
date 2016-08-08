package languagemodeling;

import languagemodeling.utils.LmUtils;
import utils.MathUtils;

import java.util.*;

/**
 * Character-level LSTM.
 *
 * More dataset: http://cs.stanford.edu/people/karpathy/char-rnn/
 *
 * Created by Thanakorn on 8/7/16.
 */
public class LSTM {

    private static final String trainingFilePath = "data/dataset/languagemodeling/tinyshakespeare.txt";

    private static float learningRate = 0.001f;
    private static int numEpochs = 15;
    private static int batchSize = 100;

    private static int hiddenSize = 100;
    private static int sequenceLength = 50;  // number of truncated BPTT
    private static int numUniqueChars;
    private static int inputSize = 50;

    private static Map<Character, Integer> charMap;
    private static Map<Integer, Character> indexMap;

    // weight parameters
    private static float[][] Wxf;
    private static float[][] Whf;
    private static float[] Bf;
    private static float[][] Wxi;
    private static float[][] Whi;
    private static float[] Bi;
    private static float[][] Wxo;
    private static float[][] Who;
    private static float[] Bo;
    private static float[][] Wxu;
    private static float[][] Whu;
    private static float[] Bu;
    private static float[][] Why;
    private static float[] By;

    private static List<Character> data;

    public static void main(String[] args) {
        loadData();
        train();
        test();
    }

    private static void train() {
        initialize();

        int pointer = 0;
        for (int epoch = 0; epoch < numEpochs; epoch++) {

            for (int i = pointer; i < data.size() && i < pointer + sequenceLength; i++) {
                
            }

        }
    }

    private static void test() {

    }

    private static void loadData() {
        data = LmUtils.readCharLevel(trainingFilePath);
    }

    private static void initialize() {
        Set<Character> visited = new HashSet<>();
        for (char c : data) {
            visited.add(c);
        }
        numUniqueChars = visited.size();
        List<Character> uniqueCharList = new ArrayList<>();
        uniqueCharList.addAll(visited);
        uniqueCharList.sort(Character::compare);

        charMap = new HashMap<>();
        indexMap = new HashMap<>();
        int index = 0;
        for (char c : uniqueCharList) {
            charMap.put(c, index);
            indexMap.put(index, c);
            index++;
        }

        Wxf = new float[numUniqueChars + 1][hiddenSize];
        Whf = new float[hiddenSize][hiddenSize];
        Bf = new float[hiddenSize];
        Wxi = new float[numUniqueChars + 1][hiddenSize];
        Whi = new float[hiddenSize][hiddenSize];
        Bi = new float[hiddenSize];
        Wxo = new float[numUniqueChars + 1][hiddenSize];
        Who = new float[hiddenSize][hiddenSize];
        Bo = new float[hiddenSize];
        Wxu = new float[numUniqueChars + 1][hiddenSize];
        Whu = new float[hiddenSize][hiddenSize];
        Bu = new float[hiddenSize];
        Why = new float[hiddenSize][numUniqueChars];
        By = new float[numUniqueChars];

        MathUtils.random(Wxf);
        MathUtils.random(Whf);
        MathUtils.random(Bf);
        MathUtils.random(Wxi);
        MathUtils.random(Whi);
        MathUtils.random(Bi);
        MathUtils.random(Wxo);
        MathUtils.random(Who);
        MathUtils.random(Bo);
        MathUtils.random(Wxu);
        MathUtils.random(Whu);
        MathUtils.random(Bu);
        MathUtils.random(Why);
        MathUtils.random(By);
    }

}
