/* ******************************************************
 * Simulator alpha - Composants logiciels 2015.
 * Copyright (C) 2015 <Binh-Minh.Bui-Xuan@ens-lyon.org>.
 * GPL version>=3 <http://www.gnu.org/licenses/>.
 * $Id: userInterface/Viewer.java 2015-03-09 buixuan.
 * ******************************************************/
package userInterface;

import data.ImObject;
import javafx.scene.Group;
import javafx.scene.effect.Lighting;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import specifications.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

public class Viewer implements ViewerService, RequireReadService, RequireStartEngineService{
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
  }

  @Override
  public void startViewer(){
    startEngine.start();
  }

  @Override
  public ArrayList<Line> getMoveObjectsLines(){
    ArrayList<Line> res = new ArrayList<Line>();
    Point NO = data.getMove().getNO();
    Point SE = data.getMove().getSE();
    Point SO = new Point(NO.x, SE.y);
    Point NE = new Point(SE.x, NO.y);

    Line SESO = new Line(SE.x,SE.y, SO.x,SO.y);
    SESO.setStroke(Color.RED);
    Line SONO = new Line(SO.x,SO.y, NO.x,NO.y);
    SONO.setStroke(Color.RED);
    Line NONE = new Line(NO.x,NO.y, NE.x,NE.y);
    NONE.setStroke(Color.RED);
    Line NESE = new Line(NE.x,NE.y, SE.x,SE.y);
    NESE.setStroke(Color.RED);

    res.add(SESO); // SESO
    res.add(SONO); // SONO
    res.add(NONE); // NONE
    res.add(NESE); // NESE

    return res;
  }

  @Override
  public Group getPanel(){
    Circle heroesAvatar = new Circle(2,  Color.rgb(156,216,255));
    heroesAvatar.setEffect(new Lighting());
    heroesAvatar.setTranslateX(data.getHeroesPosition().x);
    heroesAvatar.setTranslateY(data.getHeroesPosition().y);

    Line limitLineLeft = new Line(data.getLimitLeft(), data.getLimitUp(), data.getLimitLeft(), data.getLimitDown());
    Line limitLineRight = new Line(data.getLimitRight(), data.getLimitUp(), data.getLimitRight(), data.getLimitDown());
    Line limitLineUp = new Line(data.getLimitLeft(), data.getLimitUp(), data.getLimitRight(), data.getLimitUp());
    Line limitLineDown = new Line(data.getLimitLeft(), data.getLimitDown(), data.getLimitRight(), data.getLimitDown());

    Hashtable<ImObject, ArrayList<Line>> lOfL = data.getListOfLines();

    Group panel = new Group();
    panel.getChildren().add(heroesAvatar);

    panel.getChildren().add(limitLineLeft);
    panel.getChildren().add(limitLineRight);
    panel.getChildren().add(limitLineUp);
    panel.getChildren().add(limitLineDown);

    for(Enumeration enm = lOfL.elements(); enm.hasMoreElements();){
      for(Line L : (ArrayList<Line>)enm.nextElement()){
          L.setStroke(Color.BLUE);
        panel.getChildren().add(L);
      }
    }

    for(Line L : getMoveObjectsLines()){
      panel.getChildren().add(L);
    }


    return panel;
  }

}
