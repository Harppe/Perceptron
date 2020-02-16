import java.util.*;
/**
 * Simple machine learning program. In response to arbitrary input arrays and expected outputs, weights will be adjusted
 * over many iterations and will be able to find proper value of an array input by trained edge weights and sigmoid function.
 * 
 * Order of trainputs corresponds to order of their respective trainoutputs
 */
public class Network
{
    int[] inNodes;
    float[] edges;
    int[][] trainInputs = {{1,1,1,0},{1,0,1,0},{0,0,0,1}, {0,1,0,1}};
    float[] trainOuts = {1,1,0,1};
    private final int NODE_NUMBER = trainInputs[0].length;
    private final int NUM_OF_TRAINING_ITERATIONS = 30000;
    /**
     * Constructor for objects of class Network
     */
    public Network()
    {
        randomizeNetwork();
    }

    public void iterate() {

        for (int i = 0; i<trainInputs.length; i++) {

            float val = findValue(trainInputs[i]);   
            adjustWeights(val,trainOuts[i]);
        }

    }

    public void findValue(String args) {
        StringTokenizer st = new StringTokenizer(args," ");
        int[] inputs = new int[NODE_NUMBER];
        try {
            for (int i = 0; i<inputs.length; i++) {
                inputs[i] = Integer.parseInt(st.nextToken());
            }
        }
        catch (Exception e) {
            System.out.println("Invalid input");
            System.exit(0);
        }
        System.out.println("Output value: " +findValue(inputs));
        for (int i = 0; i<trainInputs.length; i++) {
            if (Arrays.equals(inputs,trainInputs[i])) {
                System.out.println("Expected value: " + trainOuts[i]);
            }
        }
    }

    public float findValue(int[] inputs) {
        float f = 0;
        inNodes = inputs;
        for (int i = 0; i<inNodes.length; i++) {
            f+=inNodes[i]*edges[i];   
        }
        return sigmoid(f);
    }

    public float deriveSigmoid(float val) {
        float SIGFIG = 0.000001f;
        return (sigmoid(val+SIGFIG)-sigmoid(val))/SIGFIG;
    }

    /**
     * error * input * dydx sigmoid(output)
     * 
     * error is the output you want minus the output the program gives
     */
    public void adjustWeights(float output, float trueput) {
        float error = trueput-output;
        for (int i = 0; i<edges.length; i++) {
            float adjustment = error*inNodes[i]*deriveSigmoid(output);
            edges[i]+=adjustment;
        }
    }

    public void sequence() {

        for (int i = 0; i<NUM_OF_TRAINING_ITERATIONS; i++) {
            iterate();
        }
    }

    public float sigmoid(float x) {
        return (float)(1/(1+Math.exp(-x)));   
    }

    public void randomizeNetwork() {
        inNodes = new int[NODE_NUMBER];
        edges = new float[NODE_NUMBER];
        for (int i = 0; i<edges.length; i++) {
            edges[i] = (float)Math.random();
        }
    }
}
