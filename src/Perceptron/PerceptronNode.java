package Perceptron;

import java.util.List;

/**
 * Created by Thanakorn on 5/25/15.
 */
public class PerceptronNode {

    public double[] weights;
    public int numEpoch;
    public double learningRate;
    public int inputSize;

    public PerceptronNode(int numEpoch, double learningRate) {
        if (numEpoch < 1) {
            System.err.println("Number of epoch must be at least 1.");
            return;
        }

        if (learningRate <= 0 || learningRate > 1) {
            System.err.println("Learning rate must be in the interval (0,1].");
            return;
        }

        this.numEpoch = numEpoch;
        this.learningRate = learningRate;
    }

    public void train(List<Datum> trainingData) {
        if (trainingData == null) {
            return;
        }

        // check the consistency of the input size across all training data
        inputSize = trainingData.get(0).featureVector.length;
        for (int i = 1; i < trainingData.size(); i++) {
            if (trainingData.get(i).featureVector.length != inputSize) {
                System.err.println("Inconsistent input size across the training data.");
                return;
            }
        }

        weights = new double[inputSize];
        // initialize weights to small random numbers
        for (int i = 0; i < inputSize; i++) {
            weights[i] = Math.random() / 100;
        }

        for (int epoch = 1; epoch <= numEpoch; epoch++) {
            for (Datum d : trainingData) {
                double weighedSum = 0;
                for (int featureIndex = 0; featureIndex < inputSize; featureIndex++) {
                    weighedSum += weights[featureIndex] * d.featureVector[featureIndex];
                }

                double predicted = weighedSum >= 0 ? 1 : -1;
                double error = d.trueLabel - predicted;

                for (int featureIndex = 0; featureIndex < inputSize; featureIndex++) {
                    weights[featureIndex] += learningRate * error * d.featureVector[featureIndex];
                }
            }
        }

    }


    public void predict(Datum testDatum) {
        double weighedSum = 0;
        for (int featureIndex = 0; featureIndex < inputSize; featureIndex++) {
            weighedSum += weights[featureIndex] * testDatum.featureVector[featureIndex];
        }

        double predicted = weighedSum >= 0 ? 1 : -1;
        System.out.println(predicted);
    }

}
