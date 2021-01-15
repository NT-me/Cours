/* ******************************************************
 * Simulator alpha - Composants logiciels 2015.
 * Copyright (C) 2015 <Binh-Minh.Bui-Xuan@ens-lyon.org>.
 * GPL version>=3 <http://www.gnu.org/licenses/>.
 * $Id: engine/Engine.java 2015-03-09 buixuan.
 * ******************************************************/
package engine;

import specifications.EngineService;
import specifications.DataService;
import specifications.RequireDataService;
import specifications.AlgorithmService;
import specifications.RequireAlgorithmService;

import data.Position;

import java.util.Timer;
import java.util.TimerTask;

public class Engine implements EngineService, RequireDataService, RequireAlgorithmService{
  private Timer engineClock;
  private DataService data;
  private AlgorithmService algorithm;

  public Engine(){}

  @Override
  public void bindDataService(DataService service){
    data=service;
  }
  
  @Override
  public void bindAlgorithmService(AlgorithmService service){
    algorithm=service;
  }
  
  @Override
  public void init(){
    engineClock = new Timer();
  }

  @Override
  public void start(){
    algorithm.activation();
    engineClock.schedule(new TimerTask(){
      public void run() {
        System.out.println("Game step #"+data.getStepNumber()+": checked.");
        algorithm.stepAction();
        data.setStepNumber(data.getStepNumber()+1);
      }
    },0,100);
  }

  @Override
  public void moveLeft(){
    data.setHeroesPosition(new Position(data.getHeroesPosition().x-1,data.getHeroesPosition().y));
  }
  
  @Override
  public void moveRight(){
    data.setHeroesPosition(new Position(data.getHeroesPosition().x+1,data.getHeroesPosition().y));
  }
  
  @Override
  public void moveUp(){
    data.setHeroesPosition(new Position(data.getHeroesPosition().x,data.getHeroesPosition().y-1));
  }
  
  @Override
  public void moveDown(){
    data.setHeroesPosition(new Position(data.getHeroesPosition().x,data.getHeroesPosition().y+1));
  }
}
