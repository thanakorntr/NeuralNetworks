package Perceptron;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thanakorn on 5/25/15.
 */
public class PerceptronEngine {

    public static void main(String[] args) {
        PerceptronNode pn = new PerceptronNode(10, 0.01);

        Datum d1 = new Datum();
        d1.featureVector = new double[] {0,0};
        d1.trueLabel = 1;
        Datum d2 = new Datum();
        d2.featureVector = new double[] {-1,0};
        d2.trueLabel = 1;
        Datum d3 = new Datum();
        d3.featureVector = new double[] {1,0};
        d3.trueLabel = 1;

        Datum d4 = new Datum();
        d4.featureVector = new double[] {0,1};
        d4.trueLabel = -1;
        Datum d5 = new Datum();
        d5.featureVector = new double[] {-1,1};
        d5.trueLabel = -1;
        Datum d6 = new Datum();
        d6.featureVector = new double[] {1,1};
        d6.trueLabel = -1;

        List<Datum> trainingData = new ArrayList<>();
        trainingData.add(d1);
        trainingData.add(d2);
        trainingData.add(d3);
        trainingData.add(d4);
        trainingData.add(d5);
        trainingData.add(d6);

        Datum testDatum = new Datum();
        testDatum.featureVector = new double[] {1,5};

        pn.train(trainingData);
        pn.predict(testDatum);
    }
}
