package edu.uw.cs.multir.main;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		System.out.print("Command line args: " );
		for(String arg : args){
			System.out.print(arg + " ");
		}
		System.out.println();
		
		if (args.length == 0)
			printUsage();
		
		String c = args[0];
		
		if (c.equals("preprocess")) {
			String trainFile = null;
			String testFile = null;
			String outDir = null;
			for (int i=1; i < args.length; i++) {
				if (args[i].equals("-trainFile") && i+1 < args.length)
					trainFile = args[++i];
				else if (args[i].equals("-testFile") && i+1 < args.length)
					testFile = args[++i];
				else if (args[i].equals("-outDir") && i+1 < args.length)
					outDir = args[++i];
				else printUsagePreprocess();
			}
			if (trainFile == null || testFile == null || outDir == null)
				printUsagePreprocess();
			Preprocess.preprocess(trainFile, testFile, outDir);
			
		} else if (c.equals("train")) {
			String dir = null;
			String type = null;
			for (int i=1; i < args.length; i++) {
				if (args[i].equals("-dir") && i+1 < args.length)
					dir = args[++i];
				else if (args[i].equals("-type") && i+1 < args.length)
					type = args[++i];
				else printUsageTrain();
			}
			if (dir == null || type == null) printUsageTrain();
			Train.train(dir, type);
			
		} else if (c.equals("test")) {
			String dir = null;
			for (int i=1; i < args.length; i++) {
				if (args[i].equals("-dir") && i+1 < args.length)
					dir = args[++i];
				else printUsageTest();
			}
			if (dir == null) printUsageTest();
			Test.test(dir);
			
		} else if (c.equals("test-ilp")) {
			String dir = null;
			String scoring = null;
			String type = null;
			for (int i=1; i < args.length; i++) {
				if (args[i].equals("-dir") && i+1 < args.length)
					dir = args[++i];
				else if (args[i].equals("-scoring") && i+1 < args.length)
					scoring = args[++i];
				else if (args[i].equals("-type") && i+1 < args.length)
					type = args[++i];
				else printUsageTestIlp();
			}
			if (dir == null) printUsageTestIlp();
			TestILP.test(dir, type, scoring);
			
		} else if (c.equals("results")) {
			String dir = null;
			for (int i=1; i < args.length; i++) {
				if (args[i].equals("-dir") && i+1 < args.length)
					dir = args[++i];
				else printUsageResults();
			}
			if (dir == null) printUsageResults();
			ResultWriter.write(dir);
		} else if (c.equals("results-ilp")) {
			String dir = null;
			String scoring = null;
			String type = null;
			for (int i=1; i < args.length; i++) {
				if (args[i].equals("-dir") && i+1 < args.length)
					dir = args[++i];
				else if (args[i].equals("-scoring") && i+1 < args.length)
					scoring = args[++i];
				else if (args[i].equals("-type") && i+1 < args.length)
					type = args[++i];
				else printUsageResultsIlp();
			}
			if (dir == null) printUsageResultsIlp();
			ResultWriterILP.write(dir,type, scoring);
		} else if (c.equals("aggPR")) {
			String dir = null;
			for (int i=1; i < args.length; i++) {
				if (args[i].equals("-dir") && i+1 < args.length)
					dir = args[++i];
				else printUsageAggPR();
			}
			if (dir == null) printUsageAggPR();
			AggregatePrecisionRecallCurve.run(dir);
		} else if (c.equals("aggPR-ilp")) {
			String dir = null;
			String scoring = null;
			String type = null;
			for (int i=1; i < args.length; i++) {
				if (args[i].equals("-dir") && i+1 < args.length)
					dir = args[++i];
				else if (args[i].equals("-scoring") && i+1 < args.length)
					scoring = args[++i];
				else if (args[i].equals("-type") && i+1 < args.length)
					type = args[++i];
				else printUsageAggPRIlp();
			}
			if (dir == null) printUsageAggPRIlp();
			AggregatePrecisionRecallCurveILP.run(dir, type, scoring);
		} else if (c.equals("senPR")) {
			String labelsFile = null;
			String resultsFile = null;
			for (int i=1; i < args.length; i++) {
				if (args[i].equals("-labelsFile") && i+1 < args.length)
					labelsFile = args[++i];
				else if (args[i].equals("-resultsFile") && i+1 < args.length)
					resultsFile = args[++i];
				else printUsageSenPR();
			}
			if (labelsFile == null || resultsFile == null) 
				printUsageSenPR();
			SententialPrecisionRecallCurve.run(labelsFile, resultsFile);
		} else if (c.equals("senRel")) {
			String labelsFile = null;
			String resultsFile = null;
			for (int i=1; i < args.length; i++) {
				if (args[i].equals("-labelsFile") && i+1 < args.length)
					labelsFile = args[++i];
				else if (args[i].equals("-resultsFile") && i+1 < args.length)
					resultsFile = args[++i];
				else printUsageSenRel();
			}
			if (labelsFile == null || resultsFile == null) 
				printUsageSenRel();
			SententialPrecisionRecallByRelation.run(labelsFile, resultsFile);
		} else {
			printUsage();
		}
	}
	
	private static void printUsage() {
		System.out.println("Usage: Main command params \n" +
		                   "       where command can take the following values \n" +
		                   "       {preprocess,train,test,results,aggPR,senPR,senRel}");
		System.exit(1);
	}
	
	private static void printUsagePreprocess() {
		System.out.println("Usage: Main preprocess -trainFile .. -testFile .. -outDir ..");
		System.exit(1);
	}

	private static void printUsageTrain() {
		System.out.println("Usage: Main train -dir .. -type <type>");
		System.exit(1);
	}
	
	private static void printUsageTest() {
		System.out.println("Usage: Main test -dir ..");
		System.exit(1);
	}

	private static void printUsageTestIlp() {
		System.out.println("Usage: Main test-ilp -dir .. -type <type> -scoring <type>");
		System.exit(1);
	}
	
	private static void printUsageResults() {
		System.out.println("Usage: Main results -dir ..");
		System.exit(1);
	}
	
	private static void printUsageResultsIlp() {
		System.out.println("Usage: Main results-ilp -dir .. -type <type> -scoring <type>");
		System.exit(1);
	}
	
	private static void printUsageAggPR() {
		System.out.println("Usage: Main aggPR -dir ..");
		System.exit(1);
	}

	private static void printUsageAggPRIlp() {
		System.out.println("Usage: Main aggPR-ilp -dir .. -type <type> -scoring <type>");
		System.exit(1);
	}
	
	private static void printUsageSenPR() {
		System.out.println("Usage: Main senPR -labelsFile .. -resultsFile ..");
		System.exit(1);
	}

	private static void printUsageSenRel() {
		System.out.println("Usage: Main senRel -labelsFile .. -resultsFile ..");
		System.exit(1);
	}

}
