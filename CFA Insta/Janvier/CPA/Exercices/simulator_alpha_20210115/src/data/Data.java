/* ******************************************************
 * Simulator alpha - Composants logiciels 2015.
 * Copyright (C) 2015 <Binh-Minh.Bui-Xuan@ens-lyon.org>.
 * GPL version>=3 <http://www.gnu.org/licenses/>.
 * $Id: data/Data.java 2015-03-09 buixuan.
 * ******************************************************/
package data;

import java.util.ArrayList;
import specifications.DataService;

public class Data implements DataService{
  //private Heroes hercules;
  Position heroesPosition;
  ArrayList<Position> objetsFixe;
  int stepNumber;

  int limiteDroite = 50;
  int limiteGauche = -50;
  int limiteHaute = 50;
  int limiteBasse = -50;

  public Data(){
    //hercules = new Heroes;
    this.heroesPosition = new Position(0,0);
    this.objetsFixe = new ArrayList<Position>();
    this.objetsFixe.add(new Position(0.0,1.0));
    this.stepNumber = 0;
  }

  @Override
  public Position getHeroesPosition(){ return heroesPosition; }

  @Override
  public int getStepNumber(){ return stepNumber; }

  @Override
  public void setHeroesPosition(Position p) {
    if (p.x > limiteDroite){
      p.x = limiteDroite;
    }
    else if (p.x < limiteGauche){
      p.x = limiteGauche;
    }

    if (p.y > limiteHaute){
      p.y = limiteHaute;
    }
    else if (p.y < limiteBasse){
      p.y = limiteBasse;
    }

    for (Position objet : this.objetsFixe){
      if(objet.compareTo(p)){
        p = getHeroesPosition();
      }
    }

    heroesPosition = p;
  }

  @Override
  public void setStepNumber(int n){ stepNumber=n; }
}
