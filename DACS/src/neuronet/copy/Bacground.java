package neuronet.copy;

public interface Bacground extends Layer {
	void randomize(float min, float max);
	float[] computeBackwardError(float[] input,float[] error);
	void adjust(float[] input,float[] error,float rate,float momentum);
}
