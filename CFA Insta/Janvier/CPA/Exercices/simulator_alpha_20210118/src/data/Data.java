/* ******************************************************
 * Simulator alpha - Composants logiciels 2015.
 * Copyright (C) 2015 <Binh-Minh.Bui-Xuan@ens-lyon.org>.
 * GPL version>=3 <http://www.gnu.org/licenses/>.
 * $Id: data/Data.java 2015-03-09 buixuan.
 * ******************************************************/
package data;

import javafx.scene.shape.Line;
import specifications.DataService;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;

public class Data implements DataService{
  //private Heroes hercules;
  Position heroesPosition;
  moveObject move;
  ArrayList<ImObject> listObjIm = new ArrayList<ImObject>();
  int stepNumber;
  int limitLeft = 20;
  int limitRight = 1000;
  int limitUp = 500;
  int limitDown = 10;

  public Data(){
    //hercules = new Heroes;
    heroesPosition = new Position(21,10);
    stepNumber = 0;
    for(int i = 0; i < new Random().nextInt(5); ++i){
      listObjIm.add(new ImObject(
              new Position(new Random().nextInt(limitRight)+limitLeft,new Random().nextInt(limitUp)+limitDown),
              new Position(new Random().nextInt(limitRight)+limitLeft,new Random().nextInt(limitUp)+limitDown)
      ));
    }

    Position mopos = new Position(0,0);
    boolean moposCollision = true;
    while(moposCollision){
      mopos = new Position(new Random().nextInt(limitRight)+limitLeft,new Random().nextInt(limitUp)+limitDown);
      for(ImObject imo : this.listObjIm){
        if (!imo.collision(mopos)){
          moposCollision = false;
          break;
        }
      }
    }
    move = new moveObject(
            mopos,
            5
    );
  }

  public int getLimitLeft() {
    return limitLeft;
  }

  public void setLimitLeft(int limitLeft) {
    this.limitLeft = limitLeft;
  }

  public int getLimitRight() {
    return limitRight;
  }

  public void setLimitRight(int limitRight) {
    this.limitRight = limitRight;
  }

  public int getLimitUp() {
    return limitUp;
  }

  public void setLimitUp(int limitUp) {
    this.limitUp = limitUp;
  }

  public int getLimitDown() {
    return limitDown;
  }

  public void setLimitDown(int limitDown) {
    this.limitDown = limitDown;
  }

  public ArrayList<ImObject> getListObjIm() {
    return listObjIm;
  }

  public void setListObjIm(ArrayList<ImObject> listObjIm) {
    this.listObjIm = listObjIm;
  }

  public moveObject getMove() {
    return move;
  }

  @Override
  public Position getHeroesPosition(){ return heroesPosition; }

  @Override
  public int getStepNumber(){ return stepNumber; }

  @Override
  public Position getOtherPosition(){
    return move.getPosition();
  }

  @Override
  public void setHeroesPosition(Position p) {
    if (p.x > limitRight){
      p.x = limitRight;
    }
    else if (p.x < limitLeft){
      p.x = limitLeft;
    }

    if (p.y > limitUp){
      p.y = limitUp;
    }
    else if (p.y < limitDown){
      p.y = limitDown;
    }

    for (ImObject obj : listObjIm){
      if (obj.collision(p)){
        p = getHeroesPosition();
        break;
      }
    }

    if (this.move.collision(p)){
      p = getHeroesPosition();
    }
    heroesPosition = p;
  }

  @Override
  public void setOtherPosition(Position p){
    int taille = move.getTaille();
    if (p.x + taille > limitRight){
      p.x = limitRight - taille;
    }
    else if (p.x - taille < limitLeft){
      p.x = limitLeft + taille;
    }

    if (p.y + taille > limitUp){
      p.y = limitUp - taille;
    }
    else if (p.y - taille < limitDown){
      p.y = limitDown + taille;
    }

    for (ImObject obj : listObjIm){

      if (obj.collision(new Position(p.x + taille, p.y))){
        p = getOtherPosition();
        p = new Position(p.x - taille, p.y);
        break;
      }
      if (obj.collision(new Position(p.x - taille, p.y))){
        p = getOtherPosition();
        p = new Position(p.x + taille, p.y);
        break;
      }
      if (obj.collision(new Position(p.x, p.y + taille))){
        p = getOtherPosition();
        p = new Position(p.x, p.y - taille);
        break;
      }
      if (obj.collision(new Position(p.x, p.y - taille))){
        p = getOtherPosition();
        p = new Position(p.x, p.y + taille);
        break;
      }
    }
    this.move.setPosition(p);
  }

  public Hashtable<ImObject, ArrayList<Line>> getListOfLines(){
    Hashtable<ImObject, ArrayList<Line>> res = new Hashtable<>();
    for (ImObject imo : this.listObjIm){
      res.put(imo, imo.getContourLines());
    }
    return res;
  }


  
  @Override
  public void setStepNumber(int n){ stepNumber=n; }
}
