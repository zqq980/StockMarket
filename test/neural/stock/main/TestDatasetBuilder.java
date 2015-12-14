package neural.stock.main;

import static org.junit.Assert.*;

import org.junit.Test;
import org.neuroph.core.learning.SupervisedTrainingElement;
import org.neuroph.core.learning.TrainingElement;
import org.neuroph.core.learning.TrainingSet;

public class TestDatasetBuilder {

	@Test
	public void testGetTrainingSet() {

		double daxmax = 10000.0D;
     
        DatasetBuilder datasetBuilder = new DatasetBuilder();
        TrainingSet trainingSet = datasetBuilder.getTrainingSet(daxmax);
        
        for(int ii = 0; ii < trainingSet.size(); ii++) {
        	TrainingElement element = trainingSet.elementAt(ii);

        }

	}

}
