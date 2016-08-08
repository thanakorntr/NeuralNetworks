package utils;

import edu.princeton.cs.introcs.StdRandom;

/**
 * Created by Thanakorn on 7/3/16.
 */
public class MathUtils {

    public static float[][] multiply(float[][] m1, float[][] m2) {
        if (m1 == null || m2 == null || m1[0].length != m2.length) {
            throw new IllegalArgumentException();
        }
        float[][] result = new float[m1.length][m2[0].length];
        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m2[0].length; j++) {
                float dotProduct = 0;
                for (int k = 0; k < m1[0].length; k++) {
                    dotProduct += m1[i][k] * m2[k][j];
                }
                result[i][j] = dotProduct;
            }
        }
        return result;
    }

    public static float[] multiply(float[] v1, float[][] m1) {
        if (v1 == null || m1 == null || v1.length != m1.length) {
            throw new IllegalArgumentException();
        }
        float[] result = new float[m1[0].length];
        for (int i = 0; i < m1[0].length; i++) {
            float dotProduct = 0;
            for (int j = 0; j < v1.length; j++) {
                dotProduct += v1[j] * m1[j][i];
            }
            result[i] = dotProduct;
        }
        return result;
    }

    public static float[][] multiply(float[] v1, float[] v2) {
        if (v1 == null || v2 == null || v1.length == 0 || v2.length == 0) {
            throw new IllegalArgumentException();
        }
        float[][] result = new float[v1.length][v2.length];
        for (int i = 0; i < v1.length; i++) {
            for (int j = 0; j < v2.length; j++) {
                result[i][j] = v1[i] * v2[j];
            }
        }
        return result;
    }

    public static float[] multconst(float[] v1, float constant) {
        if (v1 == null || v1.length == 0) {
            throw new IllegalArgumentException();
        }
        float[] result = new float[v1.length];
        for (int i = 0; i < v1.length; i++) {
            result[i] = v1[i] * constant;
        }
        return result;
    }

    public static float[][] multconst(float[][] m1, float constant) {
        if (m1 == null || m1.length == 0 || m1[0].length == 0) {
            throw new IllegalArgumentException();
        }
        float[][] result = new float[m1.length][m1[0].length];
        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m1[i].length; j++) {
                result[i][j] = m1[i][j] * constant;
            }
        }
        return result;
    }

    public static float[][] add(float[][] m1, float[][] m2) {
        if (m1 == null || m2 == null || m1.length != m2.length || m1[0].length != m2[0].length) {
            throw new IllegalArgumentException();
        }
        float[][] result = new float[m1.length][m1[0].length];
        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m1[i].length; j++) {
                result[i][j] = m1[i][j] + m2[i][j];
            }
        }
        return result;
    }

    public static float[] add(float[] v1, float[] v2) {
        if (v1 == null || v2 == null || v1.length != v2.length) {
            throw new IllegalArgumentException();
        }
        float[] result = new float[v1.length];
        for (int i = 0; i < v1.length; i++) {
            result[i] = v1[i] + v2[i];
        }
        return result;
    }

    public static float[] add(float[]... vectors) {
        if (vectors == null || vectors.length == 0) {
            throw new IllegalArgumentException();
        }
        float[] result = new float[vectors[0].length];
        for (float[] vector : vectors) {
            if (vector.length != result.length) {
                throw new IllegalArgumentException();
            }
            result = MathUtils.add(result, vector);
        }
        return result;
    }

    public static float[] relu(float[] v1) {
        if (v1 == null || v1.length == 0) {
            throw new IllegalArgumentException();
        }
        float[] result = new float[v1.length];
        for (int i = 0; i < v1.length; i++) {
            result[i] = Math.max(0, v1[i]);
        }
        return result;
    }

    public static float[] softmax(float[] v1) {
        if (v1 == null || v1.length == 0) {
            throw new IllegalArgumentException();
        }
        float[] result = new float[v1.length];
        float norm = 0;
        for (float val : v1) {
            norm += (float) Math.exp(val);
        }
        for (int i = 0; i < v1.length; i++) {
            result[i] = (float)Math.exp(v1[i]) / norm;
        }
        return result;
    }

    public static float[][] transpose(float[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            throw new IllegalArgumentException();
        }
        float[][] result = new float[matrix[0].length][matrix.length];
        for (int row = 0; row < matrix.length; row++) {
            int rowT = 0;
            for (int col = 0; col < matrix[row].length; col++) {
                result[rowT][row] = matrix[row][col];
                rowT++;
            }
        }
        return result;
    }

    public static float[] elemMult(float[] vector1, float[] vector2) {
        if (vector1 == null || vector2 == null || vector1.length != vector2.length) {
            throw new IllegalArgumentException();
        }
        float[] result = new float[vector1.length];
        for (int i = 0; i < vector1.length; i++) {
            result[i] = vector1[i] * vector2[i];
        }
        return result;
    }

    public static float[] reluGrad(float[] vector1) {
        if (vector1 == null || vector1.length == 0) {
            throw new IllegalArgumentException();
        }
        float[] result = new float[vector1.length];
        for (int i = 0; i < vector1.length; i++) {
            result[i] = vector1[i] > 0 ? 1 : 0;
        }
        return result;
    }

    public static void random(float[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = getRandom();
            }
        }
    }

    public static void random(float[] vector) {
        if (vector == null || vector.length == 0) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < vector.length; i++) {
            vector[i] = getRandom();
        }
    }

    public static float getRandom() {
        return (float)StdRandom.gaussian(0, 0.01);
    }

    public static float sigmoid(float val) {
        return 1f / (1f + (float)Math.exp(-val));
    }

    public static float sigmoidDerivative(float val) {
        float sigmoid = sigmoid(val);
        return sigmoid * (1 - sigmoid);
    }

    public static float[] sigmoid(float[] vector1) {
        if (vector1 == null || vector1.length == 0) {
            throw new IllegalArgumentException();
        }
        float[] result = new float[vector1.length];
        for (int i = 0; i < vector1.length; i++) {
            result[i] = sigmoid(vector1[i]);
        }
        return result;
    }

    public static float[][] sigmoid(float[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            throw new IllegalArgumentException();
        }
        float[][] result = new float[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                result[i][j] = sigmoid(matrix[i][j]);
            }
        }
        return result;
    }

    public static float[] sigmoidDerivative(float[] vector1) {
        if (vector1 == null || vector1.length == 0) {
            throw new IllegalArgumentException();
        }
        float[] result = new float[vector1.length];
        for (int i = 0; i < vector1.length; i++) {
            result[i] = sigmoidDerivative(vector1[i]);
        }
        return result;
    }

    public static float[][] sigmoidDerivative(float[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            throw new IllegalArgumentException();
        }
        float[][] result = new float[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                result[i][j] = sigmoidDerivative(matrix[i][j]);
            }
        }
        return result;
    }

    public static float tanh(float val) {
        return (float)Math.tanh(val);
    }

    public static float tanhDerivative(float val) {
        float tanh = tanh(val);
        return 1 - tanh * tanh;
    }

    public static float[] tanh(float[] vector1) {
        if (vector1 == null || vector1.length == 0) {
            throw new IllegalArgumentException();
        }
        float[] result = new float[vector1.length];
        for (int i = 0; i < vector1.length; i++) {
            result[i] = tanh(vector1[i]);
        }
        return result;
    }

    public static float[][] tanh(float[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            throw new IllegalArgumentException();
        }
        float[][] result = new float[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                result[i][j] = tanh(matrix[i][j]);
            }
        }
        return result;
    }

    public static float[] tanhDerivative(float[] vector1) {
        if (vector1 == null || vector1.length == 0) {
            throw new IllegalArgumentException();
        }
        float[] result = new float[vector1.length];
        for (int i = 0; i < vector1.length; i++) {
            result[i] = tanhDerivative(vector1[i]);
        }
        return result;
    }

    public static float[][] tanhDerivative(float[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            throw new IllegalArgumentException();
        }
        float[][] result = new float[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                result[i][j] = tanhDerivative(matrix[i][j]);
            }
        }
        return result;
    }

}
