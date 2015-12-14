/***
 * The Example is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * The Example is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Neuroph. If not, see <http://www.gnu.org/licenses/>.
 */
package neural.stock.main;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.learning.SupervisedTrainingElement;
import org.neuroph.core.learning.TrainingElement;
import org.neuroph.core.learning.TrainingSet;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.nnet.learning.LMS;

/**
 *
 * @author Dr.V.Steinhauer
 */
public class StockMain {

	public StockMain() {
        System.out.println("Time stamp N1:" + 
	          new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss:MM").format(new Date()));

		//double daxmax = 10000.0D;
		double daxmax = 800.0D;
		
        NeuralNetwork neuralNet = setupNeuroph();
        
        DatasetBuilder datasetBuilder = new DatasetBuilder();
        TrainingSet trainingSet = datasetBuilder.getTrainingSet(daxmax);
        
        neuralNet.learnInSameThread(trainingSet);
        
        System.out.println("Time stamp N2:" + 
              new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss:MM").format(new Date()));

        TrainingSet testSet = datasetBuilder.getPredictingSet(daxmax);
        
        doPrediction(neuralNet, testSet, daxmax);

        System.out.println("Time stamp N3:" + 
              new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss:MM").format(new Date()));
        
        System.exit(0);
	}

	/*
	 * 
	 */
	private NeuralNetwork setupNeuroph() {
		int maxIterations = 10000;
		
		// ---------------------------- inputs, nodes?, outputs
        NeuralNetwork neuralNet = new MultiLayerPerceptron(4, 9, 1);
        //NeuralNetwork neuralNet = new MultiLayerPerceptron(4, 18, 18, 9, 1);
        
        ((LMS) neuralNet.getLearningRule()).setMaxError(0.001);       //0-1
        ((LMS) neuralNet.getLearningRule()).setLearningRate(0.7);     //0-1
        ((LMS) neuralNet.getLearningRule()).setMaxIterations(maxIterations);   //0-1
        
		return neuralNet;
	}

	/*
	 * 
	 */
	private void doPrediction(NeuralNetwork neuralNet, TrainingSet testSet, double daxmax) {
		int nn = 0;
        
        for (TrainingElement testElement : testSet.trainingElements()) {
            neuralNet.setInput(testElement.getInput());
            neuralNet.calculate();
            
            Vector<Double> networkInput = testElement.getInput();
            Vector<Double> networkOutput = neuralNet.getOutput();
            
            nn++;
            
            printRes(networkInput, networkOutput, nn, daxmax);

        }
	}

	private void printRes(Vector<Double> networkInput, Vector<Double> networkOutput, 
			int nn, double daxmax) {
        System.out.println("\n===== Test Set " + nn + "=====\n");
        System.out.println("   Input: " + networkInput);
        System.out.println("  Output: " + networkOutput);
        
        System.out.print("   Input: ");
        for(Double d : networkInput) {
        	System.out.print(d * daxmax + "  ");
        }
        System.out.println("\n");
        
        System.out.print("  Output: ");
        for(Double d : networkOutput) {
        	System.out.print(d * daxmax + "  ");
        }
        System.out.println();
	}
	
	/*
	 * 
	 */
	
    public static void main(String[] args) {
         new StockMain();
    }

}
