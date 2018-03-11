package neuronet.copy;
import java.io.Serializable;

public interface Layer extends Serializable {
	 int getInputSize();
	 int getSize();
	 float[] computeOutput(float[] input);
}
