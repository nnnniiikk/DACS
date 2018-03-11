package neuronet.copy;

public final class Backnetwork extends Network {
	 
    public Backnetwork(Layer[] layers) {
        
        super(layers);
        
        randomize(0,0.3f);
    }

   
    public void randomize(float min,float max) {
        
        final int size = getSize();
        for (int i = 0; i < size; i++) {
            Layer layer = getLayer(i);
            if (layer instanceof Bacground) ((Bacground)layer).randomize(min,max);
        }
    }

  
    public float learnPattern(float[] input,float[] goal,float rate,float momentum) {
        
        if (input == null || input.length != getInputSize() || 
                goal == null || goal.length != getOutputSize()) throw new IllegalArgumentException();

        
        final int size = getSize();
        float[][] outputs = new float[size][];

        outputs[0] = getLayer(0).computeOutput(input);
        for (int i = 1; i < size; i++)
            outputs[i] = getLayer(i).computeOutput(outputs[i - 1]);

        
        Layer layer = getLayer(size - 1);
        final int layerSize = layer.getSize();
        float[] error = new float[layerSize];
        float totalError = 0;

        for (int i = 0; i < layerSize; i++) {
            error[i] = goal[i] - outputs[size - 1][i];
            totalError += Math.abs(error[i]);
        }

        
        if (layer instanceof Bacground)
            ((Bacground)layer).adjust(size == 1 ? input : outputs[size - 2],error,rate,momentum);

        
        float[] prevError = error;
        Layer prevLayer = layer;

        for (int i = size - 2; i >= 0; i--,prevError = error,prevLayer = layer) {
            
            layer = getLayer(i);
            
            if (prevLayer instanceof Bacground)
                error = ((Bacground)prevLayer).computeBackwardError(outputs[i],prevError);
            else
                error = prevError;
            
            if (layer instanceof Bacground)
                ((Bacground)layer).adjust(i == 0 ? input : outputs[i - 1],error,rate,momentum);
        }

        
        return totalError;
    }

}
