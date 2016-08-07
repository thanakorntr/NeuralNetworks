package MNIST;

/**
 * Created by Thanakorn on 7/3/16.
 */
public class Image {

    public float[] input;
    public Integer goldDigit;
    public Integer actualDigit;

    public float[] goldOutput;

    public float[] hidden1OutputAct;  // hidden1 output after activation
    public float[] hidden1ReluGrad;
    public float[] hidden2OutputAct;  // hidden2 output after activation
    public float[] hidden2ReluGrad;
    public float[] actualOutput;      // final layer output after activation
}
