/* ******************************************************
 * Simulator alpha - Composants logiciels 2015.
 * Copyright (C) 2015 <Binh-Minh.Bui-Xuan@ens-lyon.org>.
 * GPL version>=3 <http://www.gnu.org/licenses/>.
 * $Id: data/Position.java 2015-03-09 buixuan.
 * ******************************************************/
package data;

public class Position {
  public double x,y;
  public Position(double x, double y){
    this.x=x;
    this.y=y;
  }

  public boolean compareTo(Position p){
    return (this.x == (double)p.x && this.y == (double)p.y);
  }
}
