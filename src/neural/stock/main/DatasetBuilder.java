/***
 * StockFileReader is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * StockFileReader is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Neuroph. If not, see <http://www.gnu.org/licenses/>.
 */

package neural.stock.main;

import java.util.ArrayList;
import java.util.Collection;

import org.neuroph.core.learning.SupervisedTrainingElement;
import org.neuroph.core.learning.TrainingElement;
import org.neuroph.core.learning.TrainingSet;

/**
 *
 * @author Dr.V.Steinhauer
 */
public class DatasetBuilder {

	double daxmax;
	
	public TrainingSet getTrainingSet(double daxmax) {
		//TrainingSet trainingSet = setTrainingSet(daxmax);
		//TrainingSet trainingSet = setTrainingSet2(daxmax);
		TrainingSet trainingSet = setTrainingSet3(daxmax);
		return trainingSet;
	}

	/*
	 * 
	 */
	
	public TrainingSet getPredictingSet(double daxmax) {
		//TrainingSet preditingSet = setPreditingSet(daxmax);
		TrainingSet preditingSet = setPreditingSet3(daxmax);
		return preditingSet;
	}
	
	// ============= Set 3 ===================== //
	
	
	private TrainingSet setTrainingSet3(double daxmax) {

		TrainingSet trainingSet = new TrainingSet();   
		
		//String filenameStr = "E:/cs/Neural/StockMarket/data/goog.csv";
		String filenameStr = "X:/Cs/homeschool/sciencefair_2015-2016/StockMarket/data/goog.csv";
		StockFileReader stockFileReader = new StockFileReader();
		ArrayList<String[]> inputList = stockFileReader.read(filenameStr);
		
		ArrayList<String[]> inputRvsList = new ArrayList<String[]>();
		
		for(int ii = 0; ii < inputList.size() - 2; ii++) {
			int jj = inputList.size() - (ii+1);
			inputRvsList.add(inputList.get(jj));
			//System.out.println(inputList.get(jj)[4]);
		}
		
		ArrayList<String[]> traningList = new ArrayList<String[]>();
		
		for(int ii = 0; ii < inputRvsList.size()-6; ii=ii+5) {
			String[] s0 = inputRvsList.get(ii);
			String[] s1 = inputRvsList.get(ii+1);
			String[] s2 = inputRvsList.get(ii+2);
			String[] s3 = inputRvsList.get(ii+3);
			String[] s4 = inputRvsList.get(ii+4);
			
			String[] ss = new String[5];
			ss[0] = s0[4];
			ss[1] = s1[4];
			ss[2] = s2[4];
			ss[3] = s3[4];
			ss[4] = s4[4];
			
			traningList.add(ss);
		}
		
		printTraingSet3(traningList);

        double[] learningData = new double[4];
        double[] learningCheck = new double[1];
        
        for(int ii = 0; ii < traningList.size(); ii++){
        	String[] s1 = traningList.get(ii);
        	
            for(int i = 0; i < learningData.length; i++){
            	learningData[i] = Double.valueOf(s1[i])/ daxmax;
            }
            learningCheck[0]=Double.valueOf(s1[4])/ daxmax;

	        trainingSet.addElement(new SupervisedTrainingElement(learningData, learningCheck));
		}
		
		return trainingSet;
	}
	
	/*
	 * 
	 */
	
	private void printTraingSet3(ArrayList<String[]> traningList) {
		daxmax = 1.0D;
		
        for(int ii = 0; ii < traningList.size(); ii++){
        //for(int ii = traningList.size() - 1; ii > 5; ii--){
        	String[] s1 = traningList.get(ii);
        	
            double[] learningData = new double[4];
            double[] learningCheck = new double[1];
            
            for(int i = 0; i < learningData.length; i++){
            	
            }
            
            for(int i = 0; i < learningData.length; i++){
            	learningData[i] = Double.valueOf(s1[i])/ daxmax;
            	System.out.print(learningData[i] + ", ");
            }
            learningCheck[0]=Double.valueOf(s1[4])/ daxmax;
            
            System.out.print(learningCheck[0] + ", ");
            
            System.out.println();
		}
	}
	
	/*
	 * 
	 */
	
	
	private TrainingSet setPreditingSet3(double daxmax) {
		TrainingSet testSet = new TrainingSet();  

		//String filenameStr = "E:/cs/Neural/StockMarket/data/dataset_01.txt";
		String filenameStr = "X:/Cs/homeschool/sciencefair_2015-2016/StockMarket/data/goog_Prediction.txt";
		StockFileReader stockFileReader = new StockFileReader();
		ArrayList<String[]> traningList = stockFileReader.read(filenameStr);
		
        double[] learningData = new double[4];
        double[] learningCheck = new double[1];

        for(int ii = 0; ii < traningList.size(); ii++){
        	String[] s1 = traningList.get(ii);

            for(int i = 0; i < learningData.length; i++){
            	learningData[i] = Double.valueOf(s1[i])/ daxmax;
            }
            
            learningCheck[0]=Double.valueOf(s1[4])/ daxmax;

            testSet.addElement(new SupervisedTrainingElement(learningData, learningCheck));
		}
		
		return testSet;
	}
	
	
	/*
	 * 
	 */
		
		private TrainingSet setPreditingSet32(double daxmax) {
	        double[] learningData = new double[4];
	        double[] learningCheck = new double[1];
	        
			TrainingSet testSet = new TrainingSet();
			
			// -------- 1
			
			learningData[0] = 541.83;
			learningData[1] = 540.37;
	        learningData[2] = 541.08;
	        learningData[3] = 539.27;
	        learningCheck[0] = 537.50;
	        
	        for(int ii=0; ii<4; ii++) {
	        	learningData[ii] = learningData[ii]/daxmax;
	        }
	        
			// -------- 2
	        
	        testSet.addElement(new TrainingElement(learningData));

			learningData[0] = 541.83;
			learningData[1] = 540.37;
	        learningData[2] = 541.08;
	        learningData[3] = 539.27;
	        learningCheck[0] = 537.50;
	        
	        for(int ii=0; ii<4; ii++) {
	        	learningData[ii] = learningData[ii]/daxmax;
	        }
	        
	        testSet.addElement(new TrainingElement(learningData));
	        
			// -------- 3
	        
			learningData[0] = 541.83;
			learningData[1] = 540.37;
	        learningData[2] = 541.08;
	        learningData[3] = 539.27;
	        learningCheck[0] = 537.50;
	        
	        for(int ii=0; ii<4; ii++) {
	        	learningData[ii] = learningData[ii]/daxmax;
	        }
	        
	        testSet.addElement(new TrainingElement(learningData));
	        
	        
	        return testSet;
		}
		
	// ================================ DataSet 2 ========================== //
	
	private TrainingSet setTrainingSet2(double[] daxnorm) {

	    //TrainingSet<SupervisedTrainingElement> trainingSet = new TrainingSet<SupervisedTrainingElement>(4, 1);
	    TrainingSet trainingSet = new TrainingSet();
	 
	    double[] in = new double[4];
	    double[] out = new double[1];
	 
	    for (int i = 0; i < daxnorm.length - 5; i++) {
	        for (int j = i; j < i + 4; j++) { 
	    	    in[j - i] = daxnorm[j]; 
	        }
	    
	        out[0] = daxnorm[i + 4];
	        trainingSet.addElement(new SupervisedTrainingElement( in, out ));
	     }
		
	 return trainingSet;
	}
	
	private TrainingSet setTrainingSet2(double daxmax) {
		TrainingSet trainingSet = new TrainingSet();   
		
		
		//String filenameStr = "E:/cs/Neural/StockMarket/data/dataset_01.txt";
		String filenameStr = "X:/Cs/homeschool/sciencefair_2015-2016/StockMarket/data/dataset_01.txt";
		StockFileReader stockFileReader = new StockFileReader();
		ArrayList<String[]> traningList = stockFileReader.read(filenameStr);
		
        double[] learningData = new double[4];
        double[] learningCheck = new double[1];
        
        for(int ii = 0; ii < traningList.size(); ii++){
        	String[] s1 = traningList.get(ii);
        	
            for(int i = 0; i < learningData.length; i++){
            	learningData[i] = Double.valueOf(s1[i])/ daxmax;
            }
            learningCheck[0]=Double.valueOf(s1[4])/ daxmax;

	        trainingSet.addElement(new SupervisedTrainingElement(learningData, learningCheck));
		}
		
		return trainingSet;
	}
	
	// ============================= Set 1 ============================== //
    /*
	 * 
	 */
    
	
	private TrainingSet setTrainingSet(double daxmax) {
		TrainingSet trainingSet = new TrainingSet();

        trainingSet.addElement(new SupervisedTrainingElement(new double[]{3710.0D / daxmax, 3690.0D / daxmax, 3890.0D / daxmax, 3695.0D / daxmax}, new double[]{3666.0D / daxmax}));
        trainingSet.addElement(new SupervisedTrainingElement(new double[]{3690.0D / daxmax, 3890.0D / daxmax, 3695.0D / daxmax, 3666.0D / daxmax}, new double[]{3692.0D / daxmax}));
        trainingSet.addElement(new SupervisedTrainingElement(new double[]{3890.0D / daxmax, 3695.0D / daxmax, 3666.0D / daxmax, 3692.0D / daxmax}, new double[]{3886.0D / daxmax}));
        trainingSet.addElement(new SupervisedTrainingElement(new double[]{3695.0D / daxmax, 3666.0D / daxmax, 3692.0D / daxmax, 3886.0D / daxmax}, new double[]{3914.0D / daxmax}));
        trainingSet.addElement(new SupervisedTrainingElement(new double[]{3666.0D / daxmax, 3692.0D / daxmax, 3886.0D / daxmax, 3914.0D / daxmax}, new double[]{3956.0D / daxmax}));
        trainingSet.addElement(new SupervisedTrainingElement(new double[]{3692.0D / daxmax, 3886.0D / daxmax, 3914.0D / daxmax, 3956.0D / daxmax}, new double[]{3953.0D / daxmax}));
        trainingSet.addElement(new SupervisedTrainingElement(new double[]{3886.0D / daxmax, 3914.0D / daxmax, 3956.0D / daxmax, 3953.0D / daxmax}, new double[]{4044.0D / daxmax}));
        trainingSet.addElement(new SupervisedTrainingElement(new double[]{3914.0D / daxmax, 3956.0D / daxmax, 3953.0D / daxmax, 4044.0D / daxmax}, new double[]{3987.0D / daxmax}));
        trainingSet.addElement(new SupervisedTrainingElement(new double[]{3956.0D / daxmax, 3953.0D / daxmax, 4044.0D / daxmax, 3987.0D / daxmax}, new double[]{3996.0D / daxmax}));
        trainingSet.addElement(new SupervisedTrainingElement(new double[]{3953.0D / daxmax, 4044.0D / daxmax, 3987.0D / daxmax, 3996.0D / daxmax}, new double[]{4043.0D / daxmax}));
        trainingSet.addElement(new SupervisedTrainingElement(new double[]{4044.0D / daxmax, 3987.0D / daxmax, 3996.0D / daxmax, 4043.0D / daxmax}, new double[]{4068.0D / daxmax}));
        trainingSet.addElement(new SupervisedTrainingElement(new double[]{3987.0D / daxmax, 3996.0D / daxmax, 4043.0D / daxmax, 4068.0D / daxmax}, new double[]{4176.0D / daxmax}));
        trainingSet.addElement(new SupervisedTrainingElement(new double[]{3996.0D / daxmax, 4043.0D / daxmax, 4068.0D / daxmax, 4176.0D / daxmax}, new double[]{4187.0D / daxmax}));
        trainingSet.addElement(new SupervisedTrainingElement(new double[]{4043.0D / daxmax, 4068.0D / daxmax, 4176.0D / daxmax, 4187.0D / daxmax}, new double[]{4223.0D / daxmax}));
        trainingSet.addElement(new SupervisedTrainingElement(new double[]{4068.0D / daxmax, 4176.0D / daxmax, 4187.0D / daxmax, 4223.0D / daxmax}, new double[]{4259.0D / daxmax}));
        trainingSet.addElement(new SupervisedTrainingElement(new double[]{4176.0D / daxmax, 4187.0D / daxmax, 4223.0D / daxmax, 4259.0D / daxmax}, new double[]{4203.0D / daxmax}));
        trainingSet.addElement(new SupervisedTrainingElement(new double[]{4187.0D / daxmax, 4223.0D / daxmax, 4259.0D / daxmax, 4203.0D / daxmax}, new double[]{3989.0D / daxmax}));
	

	
        //Experiments:
        //                   calculated
        //31;3;2009;4084,76 -> 4121 Error=0.01 Rate=0.7 Iterat=100
        //31;3;2009;4084,76 -> 4096 Error=0.01 Rate=0.7 Iterat=1000
        //31;3;2009;4084,76 -> 4093 Error=0.01 Rate=0.7 Iterat=10000
        //31;3;2009;4084,76 -> 4108 Error=0.01 Rate=0.7 Iterat=100000
        //31;3;2009;4084,76 -> 4084 Error=0.001 Rate=0.7 Iterat=10000
        
        return trainingSet;
	}

	
	/*
	 * 
	 */
		
		private TrainingSet setPreditingSet(double daxmax) {
	        double[] learningData = new double[4];
	        double[] learningCheck = new double[1];
	        
			TrainingSet testSet = new TrainingSet();
			
			testSet.addElement(new TrainingElement(
	        		   new double[]{4223.0D / daxmax, 4259.0D / daxmax, 4203.0D / daxmax, 3989.0D / daxmax})
	        );
	        
	        testSet.addElement(new TrainingElement(
	     		   new double[]{5223.0D / daxmax, 3259.0D / daxmax, 6203.0D / daxmax, 7989.0D / daxmax})
	        );
	        
	        testSet.addElement(new TrainingElement(
		     		   new double[]{4176.37D / daxmax, 4187.36D / daxmax, 4223.29D / daxmax, 4259.37D / daxmax})
		        );
	        
	        //----------------------------------
	        learningData[0] = 4176.37D / daxmax;
	        learningData[1] = 4187.36D/ daxmax;
	        learningData[2] = 4223.29D / daxmax;
	        learningData[3] = 4259.37D / daxmax;
	        //learningCheck[0]=Double.valueOf(s1[4])/ daxmax;
	        
	        testSet.addElement(new TrainingElement(learningData));
	        
	        return testSet;
		}
		
}
