package neuronet.copy;

public final class WTALayer implements Bacground {

    public WTALayer(int size,float minLevel) {
        
        if (size < 1) throw new IllegalArgumentException();
       
        this.size = size;
        this.minLevel = minLevel;
    }

    public int getInputSize() {
        return size;
    }

    public int getSize() {
        return size;
    }

    public float[] computeOutput(float[] input) {
       
        if (input == null || input.length != size) throw new IllegalArgumentException();

       
        int winner = 0;
        for (int i = 1; i < size; i++)
            if (input[i] > input[winner]) winner = i;

       
        float[] output = new float[size];

       
        if (minLevel > 0) {
            float level = Float.MAX_VALUE;
            for (int i = 0; i < size; i++)
                if (i != winner && Math.abs(input[i] - input[winner]) < level)
                    level = Math.abs(input[i] - input[winner]);
            if (level < minLevel) return output;
        }

       
        output[winner] = 1;

       
        return output;
    }

    public void randomize(float min,float max) {
    }

    public float[] computeBackwardError(float[] input,float []error) {
       
        if (input == null || input.length != size || error == null ||
                error.length != size) throw new IllegalArgumentException();

       
        float[] backwardError = new float[size];
        float[] output = computeOutput(input);

        for (int i = 0; i < size; i++)
            backwardError[i] = error[i] + output[i] - input[i];

       
        return backwardError;
    }

    public void adjust(float[] input,float[] error,float rate,float momentum) {
    }

   
    public float getMinLevel() {
        return minLevel;
    }

   
    public void setMinLevel(float minLevel) {
        this.minLevel = minLevel;
    }

   
    private final int size;

   
    private float minLevel;

}
