package data;

import java.awt.*;

public class moveObject {
    private Position position;
    private int taille; // Distance diagonale entre position et NO/SO
    private Point NO; // Coin en bas a droite
    private Point SE; // Coin en haut a gauche

    public moveObject(Position position, int taille) {
        this.position = position;
        this.taille = taille;
        this.NO = new Point(
                (int)position.x + taille,
                (int)position.y + taille
        );
        this.SE = new Point(
                (int)position.x - taille,
                (int)position.y - taille
        );
    }

    public Position getPosition() {
        return position;
    }

    public Point getPointPosition() {
        return new Point((int)position.x, (int)position.y);
    }

    public void setPosition(Position position) {
        this.position = position;
        this.NO = new Point(
                (int)position.x + taille,
                (int)position.y + taille
        );
        this.SE = new Point(
                (int)position.x - taille,
                (int)position.y - taille
        );
    }

    public int getTaille() {
        return taille;
    }

    public void setTaille(int taille) {
        this.taille = taille;
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

    public boolean collision(Position verifPoint){

        if (verifPoint.x >= SE.getX() && verifPoint.x <= NO.getX()){
            if(verifPoint.y >= SE.getY() && verifPoint.y <= NO.getY()){
                return true;
            }
        }
        return false;
    }

}
