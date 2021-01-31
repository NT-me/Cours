/* ******************************************************
 * Project alpha - Composants logiciels 2015.
 * Copyright (C) 2015 <Binh-Minh.Bui-Xuan@ens-lyon.org>.
 * GPL version>=3 <http://www.gnu.org/licenses/>.
 * $Id: data/Data.java 2015-03-11 buixuan.
 * ******************************************************/
package data;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import tools.HardCodedParameters;
import tools.Position;
import tools.Sound;

import specifications.DataService;
import specifications.PhantomService;

import data.ia.MoveLeftPhantom;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;

import static java.lang.Math.abs;

public class Data implements DataService{
  //private Heroes hercules;
  private Position heroesPosition;
  private int stepNumber, score;
  private ArrayList<PhantomService> phantoms;
  private double heroesWidth,heroesHeight,phantomWidth,phantomHeight;
  private Sound.SOUND sound;
  private static final double defaultMainWidth=HardCodedParameters.defaultWidth,
          defaultMainHeight=HardCodedParameters.defaultHeight;
  private double xShrink,yShrink,shrink,xModifier,yModifier,heroesScale;
  private double limitRight, limitLeft, limitUp, limitDown;
  ArrayList<ImObject> listObjIm = new ArrayList<ImObject>();

  public Data(){}

  @Override
  public void init(){
    //hercules = new Heroes;
    heroesPosition = new Position(HardCodedParameters.heroesStartX,HardCodedParameters.heroesStartY);
    phantoms = new ArrayList<PhantomService>();
    stepNumber = 0;
    score = 0;
    heroesWidth = HardCodedParameters.heroesWidth;
    heroesHeight = HardCodedParameters.heroesHeight;
    phantomWidth = HardCodedParameters.phantomWidth;
    phantomHeight = HardCodedParameters.phantomHeight;
    sound = Sound.SOUND.None;

    limitRight = HardCodedParameters.defaultWidth;
    limitLeft = 0;
    limitDown = HardCodedParameters.defaultHeight-HardCodedParameters.defaultHeight/6;
    limitUp = 0;

    listObjIm.add(new ImObject(new Position(400, 100), new Position(450,150)));

  }

  @Override
  public Position getHeroesPosition(){ return heroesPosition; }
  
  @Override
  public double getHeroesWidth(){ return heroesWidth; }
  
  @Override
  public double getHeroesHeight(){ return heroesHeight; }
  
  @Override
  public double getPhantomWidth(){ return phantomWidth; }
  
  @Override
  public double getPhantomHeight(){ return phantomHeight; }

  @Override
  public int getStepNumber(){ return stepNumber; }

  public ArrayList<ImObject> getListObjIm() {
    return listObjIm;
  }

  @Override
  public int getScore(){ return score; }

  @Override
  public ArrayList<PhantomService> getPhantoms(){ return phantoms; }
  
  @Override
  public Sound.SOUND getSoundEffect() { return sound; }

  @Override
  public void setHeroesPosition(Position p) {
    if (p.x + HardCodedParameters.heroesWidth/2 > limitRight){
      p.x = limitRight - HardCodedParameters.heroesWidth/2;
    }
    else if (p.x - HardCodedParameters.heroesWidth/2  < limitLeft){
      p.x = limitLeft + HardCodedParameters.heroesWidth/2;
    }

    if (p.y - HardCodedParameters.heroesHeight/2 < limitUp){
      p.y = limitUp + HardCodedParameters.heroesHeight/2;
    }
    else if (p.y + HardCodedParameters.heroesHeight/2 > limitDown){
      p.y = limitDown - HardCodedParameters.heroesHeight/2;
    }

    if (listObjIm.get(0).collision(p)){
      System.out.println("hey");
      p = getHeroesPosition();
    }
    heroesPosition=p;
  }
  
  @Override
  public void setStepNumber(int n){ stepNumber=n; }
  
  @Override
  public void addScore(int score){ this.score+=score; }

  @Override
  public void addPhantom(Position p) { phantoms.add(new MoveLeftPhantom(p)); }
  
  @Override
  public void setPhantoms(ArrayList<PhantomService> phantoms) { this.phantoms=phantoms; }
  
  @Override
  public void setSoundEffect(Sound.SOUND s) { sound=s; }

  @Override
  public Hashtable<ImObject, ArrayList<Line>> getListOfLines(){
    Hashtable<ImObject, ArrayList<Line>> res = new Hashtable<>();
    for (ImObject imo : this.listObjIm){
      res.put(imo, imo.getContourLines());
    }
    return res;
  }
}
