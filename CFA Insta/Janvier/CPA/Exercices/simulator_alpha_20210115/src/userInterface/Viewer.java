/* ******************************************************
 * Simulator alpha - Composants logiciels 2015.
 * Copyright (C) 2015 <Binh-Minh.Bui-Xuan@ens-lyon.org>.
 * GPL version>=3 <http://www.gnu.org/licenses/>.
 * $Id: userInterface/Viewer.java 2015-03-09 buixuan.
 * ******************************************************/
package userInterface;

import specifications.ViewerService;
import specifications.ReadService;
import specifications.RequireReadService;
import specifications.StartEngineService;
import specifications.RequireStartEngineService;

import java.util.Timer;
import java.util.TimerTask;

public class Viewer implements ViewerService, RequireReadService, RequireStartEngineService{
  private Timer viewerClock;
  private ReadService data;
  private StartEngineService startEngine;

  public Viewer(){}

  @Override
  public void bindReadService(ReadService service){
    data=service;
  }
  
  @Override
  public void bindStartEngineService(StartEngineService service){
    startEngine=service;
  }

  @Override
  public void init(){
    viewerClock = new Timer();
  }

  @Override
  public void start(){
    viewerClock.schedule(new TimerTask(){
      public void run(){
        System.out.println("Heroes position: "+data.getHeroesPosition().x+", "+data.getHeroesPosition().y);
      }
    },0,1000);

    startEngine.start();
  }
}
