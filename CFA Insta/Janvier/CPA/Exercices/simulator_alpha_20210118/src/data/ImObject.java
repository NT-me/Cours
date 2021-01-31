package data;

import javafx.scene.shape.Line;

import java.awt.*;
import java.util.ArrayList;

public class ImObject {
    ArrayList<Position> aire;
    Point NO = new Point();
    Point SE = new Point();

    public ImObject() {
        aire = new ArrayList<Position>();
    }

    public ImObject(Position NO, Position SE) {
        aire = new ArrayList<Position>();
        this.NO = new Point((int)NO.x, (int)NO.y);
        this.SE = new Point((int)SE.x, (int)SE.y);

        for(double x = SE.x; x < NO.x; ++x){
            for(double y = SE.y; y < NO.y; ++y){
                aire.add(new Position(x, y));
            }
        }
    }

    public Point getNO() {
        return NO;
    }

    public void setNO(Point NO) {
        this.NO = NO;
    }

    public Point getSE() {
        return SE;
    }

    public void setSE(Point SE) {
        this.SE = SE;
    }

    public ArrayList<Position> getAire() {
        return aire;
    }

    public void setAire(ArrayList<Position> aire) {
        this.aire = aire;
    }

    public ArrayList<Line> getContourLines(){
        ArrayList<Line> res = new ArrayList<Line>();
        Point NE = new Point(this.SE.x, this.NO.y);
        Point SO = new Point(this.NO.x, this.SE.y);

        System.out.println(NE);
        System.out.println(SO);

        Line SESO = new Line(SE.getX(),SE.getY(),SO.getX(),SO.getY());
        Line SONO = new Line(SO.getX(),SO.getY(),NO.getX(),NO.getY());
        Line NONE = new Line(NO.getX(),NO.getY(),NE.getX(),NE.getY());
        Line NESE = new Line(NE.getX(),NE.getY(),SE.getX(),SE.getY());

        res.add(SESO);
        res.add(SONO);
        res.add(NONE);
        res.add(NESE);

        return res;
    }


    public boolean collision(Position verifPoint){
        if (verifPoint.x >= SE.getX() && verifPoint.x <= NO.getX()){
            if(verifPoint.y >= SE.getY() && verifPoint.y <= NO.getY()){
                return true;
            }
        }
        return false;
    }
}
