/* ******************************************************
 * Project alpha - Composants logiciels 2015.
 * Copyright (C) 2015 <Binh-Minh.Bui-Xuan@ens-lyon.org>.
 * GPL version>=3 <http://www.gnu.org/licenses/>.
 * $Id: specifications/ReadService.java 2015-03-11 buixuan.
 * ******************************************************/
package specifications;

import data.ImObject;
import javafx.scene.shape.Line;
import tools.Position;
import tools.Sound;

import java.util.ArrayList;
import java.util.Hashtable;

public interface ReadService {
  public Position getHeroesPosition();
  public double getHeroesWidth();
  public double getHeroesHeight();
  public double getPhantomWidth();
  public double getPhantomHeight();
  public int getStepNumber();
  public int getScore();
  public ArrayList<PhantomService> getPhantoms();
  public Sound.SOUND getSoundEffect();
  public ArrayList<ImObject> getListObjIm();
  Hashtable<ImObject, ArrayList<Line>> getListOfLines();
}
