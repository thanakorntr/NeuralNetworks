package MNIST;

import MNIST.utils.MathUtils;
import MNIST.utils.MnistUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * With this setting, Accuracy: 97.41%
 *
 * Created by Thanakorn on 7/3/16.
 */
public class MLP {

    private static List<Image> trainingData;
    private static List<Image> testData;
    private static final String trainingFilePath = "src/MNIST/data/mnist_train.csv";
    private static final String testingFilePath = "src/MNIST/data/mnist_test.csv";

    private static float[][] hidden1;  // 784 x 256
    private static float[] bias1;      // 1 x 256
    private static float[][] hidden2;  // 256 x 256
    private static float[] bias2;      // 1 x 256
    private static float[][] out;      // 256 x 10
    private static float[] biasout;    // 1 x 10

    private static int nHidden1 = 256;
    private static int nHidden2 = 256;
    private static int nInput = 784;
    private static int nClasses = 10;

    private static float learningRate = 0.001f;
    private static int numEpochs = 15;
    private static int batchSize = 100;


    public static void main(String[] args) {
        loadData();
        train();
        test();
    }

    private static void train() {
        initParameters();

        for (int epoch = 0; epoch < numEpochs; epoch++) {
            System.out.println("Training epoch: " + epoch);

            // shuffle at the beginning of each epoch
            Collections.shuffle(trainingData);

            // mini-batch training
            for (int i = 0; i < trainingData.size(); i += batchSize) {
                List<Image> miniBatch = new ArrayList<>();
                for (int j = i; j < trainingData.size() && j < i + batchSize; j++) {
                    miniBatch.add(trainingData.get(j));
                }

                // forward propagation
                for (Image image : miniBatch) {
                    forward(image);
                }

                // backpropagation
                float[][] hidden1GradAcc = new float[nInput][nHidden1];   // 784 x 256
                float[] bias1GradAcc = new float[nHidden1];               // 1 x 256
                float[][] hidden2GradAcc = new float[nHidden1][nHidden2]; // 256 x 256
                float[] bias2GradAcc = new float[nHidden2];               // 1 x 256
                float[][] outGradAcc = new float[nHidden2][nClasses];     // 256 x 10
                float[] biasoutGradAcc = new float[nClasses];             // 1 x 10

                for (Image image : miniBatch) {
                    // 1 x 10
                    float[] gradOutput = image.actualOutput;
                    // 1 x 10
                    gradOutput[image.goldDigit] = gradOutput[image.goldDigit] - 1;
                    // 256 x 10
                    outGradAcc = MathUtils.add(outGradAcc, MathUtils.multiply(image.hidden2OutputAct, gradOutput));
                    // 1 x 10
                    biasoutGradAcc = MathUtils.add(biasoutGradAcc, gradOutput);

                    // 1 x 256
                    float[] gradHidden2 = MathUtils.multiply(gradOutput, MathUtils.transpose(out));
                    gradHidden2 = MathUtils.elemMult(gradHidden2, image.hidden2ReluGrad);
                    // 256 x 256
                    hidden2GradAcc = MathUtils.add(hidden2GradAcc, MathUtils.multiply(image.hidden1OutputAct, gradHidden2));
                    // 1 x 256
                    bias2GradAcc = MathUtils.add(bias2GradAcc, gradHidden2);

                    // 1 x 256
                    float[] gradHidden1 = MathUtils.multiply(gradHidden2, MathUtils.transpose(hidden2));
                    gradHidden1 = MathUtils.elemMult(gradHidden1, image.hidden1ReluGrad);
                    // 784 x 256
                    hidden1GradAcc = MathUtils.add(hidden1GradAcc, MathUtils.multiply(image.input, gradHidden1));
                    // 1 x 256
                    bias1GradAcc = MathUtils.add(bias1GradAcc, gradHidden1);
                }

                // divide by number of training datum
                hidden1GradAcc = MathUtils.multconst(hidden1GradAcc, 1f / miniBatch.size());
                bias1GradAcc = MathUtils.multconst(bias1GradAcc, 1f / miniBatch.size());
                hidden2GradAcc = MathUtils.multconst(hidden2GradAcc, 1f / miniBatch.size());
                bias2GradAcc = MathUtils.multconst(bias2GradAcc, 1f / miniBatch.size());
                outGradAcc = MathUtils.multconst(outGradAcc, 1f / miniBatch.size());
                biasoutGradAcc = MathUtils.multconst(biasoutGradAcc, 1f / miniBatch.size());

                // update weight parameters
                hidden1 = MathUtils.add(hidden1, MathUtils.multconst(hidden1GradAcc, -learningRate));
                bias1 = MathUtils.add(bias1, MathUtils.multconst(bias1GradAcc, -learningRate));
                hidden2 = MathUtils.add(hidden2, MathUtils.multconst(hidden2GradAcc, -learningRate));
                bias2 = MathUtils.add(bias2, MathUtils.multconst(bias2GradAcc, -learningRate));
                out = MathUtils.add(out, MathUtils.multconst(outGradAcc, -learningRate));
                biasout = MathUtils.add(biasout, MathUtils.multconst(biasoutGradAcc, -learningRate));
            }
        }
    }

    private static void forward(Image image) {
        float[] hidden1Output = MathUtils.multiply(image.input, hidden1);    // (1x784) x (784x256) = (1x256)
        hidden1Output = MathUtils.add(hidden1Output, bias1);                 // (1x256) + (1x256) = (1x256)
        image.hidden1ReluGrad = MathUtils.reluGrad(hidden1Output);
        hidden1Output = MathUtils.relu(hidden1Output);
        image.hidden1OutputAct = hidden1Output;
        float[] hidden2Output = MathUtils.multiply(hidden1Output, hidden2);  // (1x256) x (256x256) = (1x256)
        hidden2Output = MathUtils.add(hidden2Output, bias2);                 // (1x256) + (1x256) = (1x256)
        image.hidden2ReluGrad = MathUtils.reluGrad(hidden2Output);
        hidden2Output = MathUtils.relu(hidden2Output);
        image.hidden2OutputAct = hidden2Output;
        float[] outOutput = MathUtils.multiply(hidden2Output, out);          // (1x256) x (256x10) = (1x10)
        outOutput = MathUtils.add(outOutput, biasout);                       // (1x10) + (1x10) = (1x10)
        outOutput = MathUtils.softmax(outOutput);
        image.actualOutput = outOutput;
        int argmaxindex = -1;
        float maxVal = Float.MIN_VALUE;
        for (int i = 0; i < outOutput.length; i++) {
            if (outOutput[i] > maxVal) {
                maxVal = outOutput[i];
                argmaxindex = i;
            }
        }
        image.actualDigit = argmaxindex;
    }

    private static void initParameters() {
        hidden1 = new float[nInput][nHidden1];
        bias1 = new float[nHidden1];
        hidden2 = new float[nHidden1][nHidden2];
        bias2 = new float[nHidden2];
        out = new float[nHidden2][nClasses];
        biasout = new float[nClasses];

        MathUtils.random(hidden1);
        MathUtils.random(bias1);
        MathUtils.random(hidden2);
        MathUtils.random(bias2);
        MathUtils.random(out);
        MathUtils.random(biasout);
    }

    private static void test() {
        float numCorrect = 0;
        for (Image image : testData) {
            forward(image);
            if (image.actualDigit.equals(image.goldDigit)) {
                numCorrect++;
            }
        }
        float accuracy = numCorrect * 100f / testData.size();
        System.out.println("Accuracy: " + accuracy + "%");
    }

    private static void loadData() {
        trainingData = MnistUtils.loadImages(trainingFilePath);
        testData = MnistUtils.loadImages(testingFilePath);
    }

}
