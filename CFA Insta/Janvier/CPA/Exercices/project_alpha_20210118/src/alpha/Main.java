/* ******************************************************
 * Project alpha - Composants logiciels 2015.
 * Copyright (C) 2015 <Binh-Minh.Bui-Xuan@ens-lyon.org>.
 * GPL version>=3 <http://www.gnu.org/licenses/>.
 * $Id: alpha/Main.java 2015-03-11 buixuan.
 * ******************************************************/
package alpha;

import tools.HardCodedParameters;
import tools.User;
import tools.Sound;

import specifications.DataService;
import specifications.EngineService;
import specifications.ViewerService;
import specifications.AlgorithmService;

import data.Data;
import engine.Engine;
import userInterface.Viewer;
//import algorithm.RandomWalker;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.event.EventHandler;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Main extends Application{
  //---HARD-CODED-PARAMETERS---//
  private static String fileName = HardCodedParameters.defaultParamFileName;

  //---VARIABLES---//
  private static DataService data;
  private static EngineService engine;
  private static ViewerService viewer;
  private static AnimationTimer timer;

  //---EXECUTABLE---//
  public static void main(String[] args) {
    //readArguments(args);

    data = new Data();
    engine = new Engine();
    viewer = new Viewer();

    ((Engine)engine).bindDataService(data);
    ((Viewer)viewer).bindReadService(data);

    data.init();
    engine.init();
    viewer.init();
    
    launch(args);
  }

  @Override public void start(Stage stage) {
    final Scene scene = new Scene(((Viewer)viewer).getPanel());

    scene.setFill(Color.CORNFLOWERBLUE);
    scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
      @Override
        public void handle(KeyEvent event) {
          if (event.getCode()==KeyCode.LEFT) engine.setHeroesCommand(User.COMMAND.LEFT);
          if (event.getCode()==KeyCode.RIGHT) engine.setHeroesCommand(User.COMMAND.RIGHT);
          if (event.getCode()==KeyCode.UP) engine.setHeroesCommand(User.COMMAND.UP);
          if (event.getCode()==KeyCode.DOWN) engine.setHeroesCommand(User.COMMAND.DOWN);
          event.consume();
        }
    });
    scene.setOnKeyReleased(new EventHandler<KeyEvent>(){
      @Override
        public void handle(KeyEvent event) {
          if (event.getCode()==KeyCode.LEFT) engine.releaseHeroesCommand(User.COMMAND.LEFT);
          if (event.getCode()==KeyCode.RIGHT) engine.releaseHeroesCommand(User.COMMAND.RIGHT);
          if (event.getCode()==KeyCode.UP) engine.releaseHeroesCommand(User.COMMAND.UP);
          if (event.getCode()==KeyCode.DOWN) engine.releaseHeroesCommand(User.COMMAND.DOWN);
          event.consume();
        }
    });
    scene.widthProperty().addListener(new ChangeListener<Number>() {
        @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
          viewer.setMainWindowWidth(newSceneWidth.doubleValue());
        }
    });
    scene.heightProperty().addListener(new ChangeListener<Number>() {
        @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneHeight, Number newSceneHeight) {
          viewer.setMainWindowHeight(newSceneHeight.doubleValue());
        }
    });
    
    stage.setScene(scene);
    stage.setWidth(HardCodedParameters.defaultWidth);
    stage.setHeight(HardCodedParameters.defaultHeight);
    stage.setOnShown(new EventHandler<WindowEvent>() {
      @Override public void handle(WindowEvent event) {
        engine.start();
      }
    });
    stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
      @Override public void handle(WindowEvent event) {
        engine.stop();
      }
    });
    stage.show();
    
    timer = new AnimationTimer() {
      @Override public void handle(long l) {
        scene.setRoot(((Viewer)viewer).getPanel());
        switch (data.getSoundEffect()){
          case PhantomDestroyed:
            new MediaPlayer(new Media(getHostServices().getDocumentBase()+"src/sound/waterdrip.mp3")).play();
            break;
          case HeroesGotHit:
            new MediaPlayer(new Media(getHostServices().getDocumentBase()+"src/sound/waterdrip.mp3")).play();
            break;
          default:
            break;
        }
      }
    };
    timer.start();
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
