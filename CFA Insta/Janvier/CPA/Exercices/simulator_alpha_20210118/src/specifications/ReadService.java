/* ******************************************************
 * Simulator alpha - Composants logiciels 2015.
 * Copyright (C) 2015 <Binh-Minh.Bui-Xuan@ens-lyon.org>.
 * GPL version>=3 <http://www.gnu.org/licenses/>.
 * $Id: specifications/ReadService.java 2015-03-09 buixuan.
 * ******************************************************/
package specifications;

import data.ImObject;
import data.Position;
import data.moveObject;
import javafx.scene.shape.Line;

import java.util.ArrayList;
import java.util.Hashtable;

public interface ReadService {
  public Position getHeroesPosition();
  public int getStepNumber();
  public int getLimitLeft();
  public int getLimitRight();
  public int getLimitUp();
  public int getLimitDown();
  public moveObject getMove();
  public void setOtherPosition(Position p);
  public Position getOtherPosition();
  public ArrayList<ImObject> getListObjIm();
  public Hashtable<ImObject, ArrayList<Line>> getListOfLines();

}
