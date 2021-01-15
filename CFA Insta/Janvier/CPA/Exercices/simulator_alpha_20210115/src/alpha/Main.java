/* ******************************************************
 * Simulator alpha - Composants logiciels 2015.
 * Copyright (C) 2015 <Binh-Minh.Bui-Xuan@ens-lyon.org>.
 * GPL version>=3 <http://www.gnu.org/licenses/>.
 * $Id: alpha/Main.java 2015-03-09 buixuan.
 * ******************************************************/
package alpha;

import tools.HardCodedParameters;

import specifications.DataService;
import specifications.EngineService;
import specifications.ViewerService;
import specifications.AlgorithmService;

import data.Data;
import engine.Engine;
import userInterface.Viewer;
import algorithm.RandomWalker;

public class Main {
  //---HARD-CODED-PARAMETERS---//
  private static String fileName = HardCodedParameters.defaultParamFileName;

  //---VARIABLES---//
  private static DataService data;
  private static EngineService engine;
  private static ViewerService viewer;
  private static AlgorithmService algorithm;

  //---EXECUTABLE---//
  public static void main(String[] args) {
    //readArguments(args);

    data = new Data();
    engine = new Engine();
    viewer = new Viewer();
    algorithm = new RandomWalker();

    ((Engine)engine).bindDataService(data);
    ((Engine)engine).bindAlgorithmService(algorithm);
    ((Viewer)viewer).bindReadService(data);
    ((Viewer)viewer).bindStartEngineService(engine);
    ((RandomWalker)algorithm).bindSimulatorService(engine);

    engine.init();
    viewer.init();

    viewer.start();
  }

  //---ARGUMENTS---//
  private static void readArguments(String[] args){
    if (args.length>0 && args[0].charAt(0)!='-'){
      System.err.println("Syntax error: use option -h for help.");
      return;
    }
    for (int i=0;i<args.length;i++){
      if (args[i].charAt(0)=='-'){
	if (args[i+1].charAt(0)=='-'){
	  System.err.println("Option "+args[i]+" expects an argument but received none.");
	  return;
	}
	switch (args[i]){
	  case "-inFile":
	    fileName=args[i+1];
	    break;
	  case "-h":
	    System.out.println("Options:");
	    System.out.println(" -inFile FILENAME: (UNUSED AT THE MOMENT) set file name for input parameters. Default name is"+HardCodedParameters.defaultParamFileName+".");
	    break;
	  default:
	    System.err.println("Unknown option "+args[i]+".");
	    return;
	}
	i++;
      }
    }
  }
}
