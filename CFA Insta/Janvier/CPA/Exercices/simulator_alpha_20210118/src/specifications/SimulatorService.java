/* ******************************************************
 * Simulator alpha - Composants logiciels 2015.
 * Copyright (C) 2015 <Binh-Minh.Bui-Xuan@ens-lyon.org>.
 * GPL version>=3 <http://www.gnu.org/licenses/>.
 * $Id: specifications/SimulatorService.java 2015-03-09 buixuan.
 * ******************************************************/
package specifications;

public interface SimulatorService{
  public void moveLeft();
  public void moveRight();
  public void moveUp();
  public void moveDown();

  public void moveOtherLeft();
  public void moveOtherRight();
  public void moveOtherUp();
  public void moveOtherDown();
}
