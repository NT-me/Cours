package algorithms;

import supportGUI.Circle;
import supportGUI.Line;

import java.awt.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

public class DefaultTeam {


  // ############ UTILS ##############################################
  public boolean contains(Circle c, Point p){
    return c.getCenter().distance(p) <= c.getRadius();
  }

  private double prodVect (Point p, Point q, Point r) {
    return (q.y - p.y) * (r.x - q.x) - (q.x - p.x) * (r.y - q.y);
  }

  private double prodScal (Point p, Point q, Point s, Point t) {
    return ((q.x-p.x)*(t.y-s.y) - (q.y-p.y)*(t.x-s.x));
  }

  public double dotProd(Point p, Point q, Point s, Point t){
    return ((q.x-p.x)*(t.x-s.x)+(q.y-p.y)*(t.y-s.y));
  }


  public double angle(Point p, Point q, Point s, Point t){
    if (p.equals(q)||s.equals(t)) return Double.MAX_VALUE;
    double cosTheta = dotProd(p,q,s,t)/(double)(p.distance(q)*s.distance(t));
    return Math.acos(cosTheta);
  }

  private static double[] getVectorDirector(Point start, Point end) {
    return new double[] { 1.0*(end.x - start.x), 1.0*(end.y - start.y)};
  }

  /*------------------------------------------------------------------------------
    < 0 En dessous
    = 0 Confondu
    > 0 Au dessus
------------------------------------------------------------------------------*/
  int crossproduct(Point p, Point q, Point s, Point t){
    return (((q.x-p.x)*(t.y-s.y))-((q.y-p.y)*(t.x-s.x)));
  }

  //Retourne le xmax d'un tableau de points
  public int findXmax(ArrayList<Point> points){
    int xmax = Integer.MIN_VALUE;
    for (Point P: points){
      if (P.x > xmax){
        xmax = P.x;
      }
    }
    return xmax;
  }

  // calculDiametre: ArrayList<Point> --> Line
  //   renvoie une paire de points de la liste, de distance maximum.
  public Line calculDiametre(ArrayList<Point> points) {
    if (points.size()<3) {
      return null;
    }

    Point P = new Point(0,0);
    Point Q = new Point(0,0);

    return new Line(P,Q);
  }

  public Line trouverDiametreOpti(ArrayList<Point> points){
    if (points.size()<3) {
      return null;
    }


    ArrayList<Line> pa = calculPairesAntipodales(points);
    double taille_max = Double.MIN_VALUE;
    Line diametre = new Line(new Point(), new Point());

    for (Line l : pa){
      if(l.getP().distance(l.getQ()) > taille_max){
        taille_max = l.getP().distance(l.getQ());
        diametre = l;
      }
    }

    return diametre;
  }

  // calculDiametreOptimise: ArrayList<Point> --> Line
  //   renvoie une paire de points de la liste, de distance maximum.
  public Line calculDiametreOptimise(ArrayList<Point> points) {
    if (points.size()<3) {
      return null;
    }

    return trouverDiametreOpti(points);
  }

  public Line diametreCercleMin(ArrayList<Point> points) {
    if (points.size()<3) {
      return null;
    }

    Point dummy = points.get(0);
    Point P = new Point(0,0);
    Point Q = new Point(0,0);

    double tmp_dist = -1;
    for (Point p: points) {
      double dist = dummy.distance(p);
      if (dist >= tmp_dist){
        tmp_dist = dist;
        P = p;
      }
    }
    for (Point q: points) {
      double dist = P.distance(q);
      if (dist >= tmp_dist){
        tmp_dist = dist;
        Q = q;
      }
    }
    return new Line(P,Q);
  }

  public Hashtable<String, Point> trouvePointCardinaux(ArrayList<Point> points){
    // Permet de retrouver le point ouest

    Point nord = new Point(0,0), sud = new Point(0,0), est = new Point(0,0), ouest = new Point(0,0);
    Hashtable<String, Point> res = new Hashtable<String, Point>();

    // Ouest
    double min_abs = Double.MAX_VALUE;
    for(Point P: points){
      if(P.getX() < min_abs){

        min_abs = P.getX();
        ouest = P;
      }
    }
    res.put("ouest", ouest);

    // Est
    double max_abs = Double.MIN_VALUE;
    for(Point P: points){
      if(P.getX() > max_abs){

        max_abs = P.getX();
        est = P;
      }
    }
    res.put("est", est);

    // Nord
    double max_ord = Double.MIN_VALUE;
    for(Point P: points){
      if(P.getY() > max_ord){

        max_ord = P.getY();
        nord = P;
      }
    }
    res.put("nord", nord);

    // Sud
    double min_ord = Double.MAX_VALUE;
    for(Point P: points){
      if(P.getY() < min_ord){

        min_ord = P.getY();
        sud = P;
      }
    }
    res.put("sud", sud);

    return res;
  }

  public boolean inTriangle(Point p, Point A, Point B, Point C){
    double area;
    double s;
    double t;
    boolean flagS = false, flagT = false, flag1st = false;
    area = 0.5 *(-B.getY()*C.getX() + A.getY()*(-B.getX() + C.getX()) + A.getX()*(B.getY() - C.getY()) + B.getX()*C.getY());
    s = 1/(2*area)*(A.getY()*C.getX() - A.getX()*C.getY() + (C.getY() - A.getY())*p.getX() + (A.getX() - C.getX())*p.getY());
    t = 1/(2*area)*(A.getX()*B.getY() - A.getY()*B.getX() + (A.getY() - B.getY())*p.getX() + (B.getX() - A.getX())*p.getY());

    if (s > (double) 0 && s < 1){
      flagS = true;
    }

    if (t > (double) 0 && t < 1){
      flagT = true;
    }

    if (( (double)1-(s*t)) > (double) 0 && 1-(s*t) < 1){
      flag1st = true;
    }
    System.out.println("####");
    System.out.println("A :" + A + "B :" + B + "C :" + C + "p :" + p);
    System.out.println("s :" + s);
    System.out.println("t :" + t);
    System.out.println("1-s*t :" + (1-(s*t)));
    System.out.println("FlagS :" + flagS + " flagT :" + flagT + " flag1st :" + flag1st );

    return (flagS && flagT && flag1st);
  }

  public double distance(Point p, Point a, Point b){
    return Math.abs(crossproduct(a,b,a,p));
  }

  public ArrayList<Line> calculPairesAntipodales(ArrayList<Point> points){
    ArrayList<Point> p = algoJarvis(points);
    int n = p.size();
    ArrayList<Line> antipodales = new ArrayList<Line>();
    int k = 1;
    while(distance(p.get(k), p.get(n-1), p.get(0)) < distance(p.get((k+1)%n), p.get(n-1), p.get(0))) ++k;
    int i = 0;
    int j = k;
    while (i<=k && j<n){
      while (distance(p.get(j), p.get(i), p.get(i+1)) < distance(p.get((j+1)%n), p.get(i), p.get(i+1)) && j<n-1){
        antipodales.add(new Line(p.get(i), p.get(j)));
        ++j;
      }
      antipodales.add(new Line(p.get(i), p.get(j)));
      ++i;
    }
    return antipodales;
  }


  public double getCosinus(Point start1, Point end1, Point start2, Point end2) {
    double cos = dotProd(start1, end1, start2, end2) / crossproduct(start1, end1, start2, end2);
    return Math.abs(cos);
  }
  // #################################################################


  // calculCercleMin: ArrayList<Point> --> Circle
  //   renvoie un cercle couvrant tout point de la liste, de rayon minimum.
  public Circle calculCercleMin(ArrayList<Point> points) {
    ArrayList<Point> points_ = (ArrayList<Point>) points.clone();
    Line diametre = calculDiametreOptimise(points_);
    Point P = diametre.getP();
    Point Q = diametre.getQ();

    Point C = new Point();
    C.x = (int) ((P.x+Q.x)*0.5);
    C.y = (int) ((P.y+Q.y)*0.5);
    int rayon = (int)C.distance(P);

    Circle CERCLE = new Circle(C, rayon);
    points_.remove(P);
    points_.remove(Q);
    int i = 0;
    while(!points_.isEmpty()){
      Point S = points_.get(0);

      if (contains(CERCLE, S)){
        points_.remove(S);
      }

      else{
        System.out.print(i);
        System.out.println(" " + CERCLE.getRadius());
        double rayon_ = 0.5*(CERCLE.getRadius()+CERCLE.getCenter().distance(S));
        double alpha = rayon_ / CERCLE.getCenter().distance(S);
        double beta = 1 - alpha;

        Point C_ = new Point(0,0);
        C_.x = (int) ((alpha*CERCLE.getCenter().x + beta * S.x));
        C_.y = (int) ((alpha*CERCLE.getCenter().y + beta * S.y));

        CERCLE = new Circle(C_, (int)rayon_);
        i++;
        points_.remove(S);
      }
    }
    System.out.println(points.get(0));
    System.out.println(CERCLE.getCenter());
    System.out.println(CERCLE.getRadius());

    return CERCLE;
  }

  // Retourne une liste de points trié par pixel
  public ArrayList<Point> preCalculTriParPixel(ArrayList<Point> points){

    Hashtable<Integer, Point> ymin = new Hashtable<Integer, Point>();
    ArrayList<Point> res = new ArrayList<Point>();

    for(Point P : points){
      if (ymin.get(P.x) == null || ymin.get(P.x).y > P.y ){
        ymin.put(P.x, P);
      }
    }

    Hashtable<Integer, Point> ymax = new Hashtable<Integer, Point>();
    for(Point P : points){
      if (ymax.get(P.x) == null || ymax.get(P.x).y < P.y ){
        ymax.put(P.x, P);
      }
    }

    for(Enumeration enm = ymin.elements(); enm.hasMoreElements();){
      res.add((Point) enm.nextElement());
    }

    for(Enumeration enm = ymax.elements(); enm.hasMoreElements();){
      res.add((Point) enm.nextElement());
    }

    return res;
  }

  // Retourne une liste de point réduite par l'algo de Graham
  public ArrayList<Point> algoGraham(ArrayList<Point> points){
    ArrayList<Point> points_ = preCalculTriParPixel((ArrayList<Point>) points.clone());

    int imil = 0;
    int iend = 0;

    //for (int i = 0; i< points_.size(); ++i){
    int i = 0;

    while (i > points_.size()){

      if (i == points_.size()-1){
        imil = 0;
        iend = 1;
      }
      else{
        imil = i + 1;
        iend = i + 2;
      }
      Point deb = points_.get(i);
      Point mil = points_.get(imil);
      Point end = points_.get(iend);

      if (crossproduct(deb, mil, deb, end) < 0){
        points_.remove(mil);
        --i;
      }
      else{
        ++i;
      }
    }

    //System.out.println(points_);

    return points_;
  }

  public ArrayList<Point> algoAklToussaint(ArrayList<Point> points){
    ArrayList<Point> res = (ArrayList<Point>)points.clone();
    Hashtable<String, Point> tpc = trouvePointCardinaux(points);
    Point nord = tpc.get("nord");
    Point sud = tpc.get("sud");
    Point est = tpc.get("est");
    Point ouest = tpc.get("ouest");

    boolean flagA = false, flagB = false;

    for (Point P: points){
      flagA = inTriangle(P, ouest, sud, est);
      flagB = inTriangle(P, ouest, est, nord);
      if(flagA || flagB){
        res.remove(P);
      }
    }
    return res;
  }

  public ArrayList<Point> algoJarvis(ArrayList<Point> points){
    double min_abs = Double.MAX_VALUE;
    Point start = new Point(0,0);
    Point startQ = new Point(0,0);

    ArrayList<Point> res = new ArrayList<Point>();

    // Permet de retrouver le sommet avec le plus petit abscisse
    for(Point P: points){
      if(P.getX() < min_abs){

        min_abs = P.getX();
        start = P;
      }
    }

    // Permet via un algo naïf de trouver le point Q
    for (Point q : points){
      if (start.equals(q)) continue; // Permet d'éviter le cas où p == q
      Point ref = start;

      for (Point r: points) {
        if (crossproduct(start,q,start,r)!=0){
          ref = r;
          break;
        }
      }

      double signeRef = crossproduct(start,q,start,ref);
      boolean estCote = true;

      // Permet de vérifier si les points sont tous du même côté.
      for (Point r: points){
        if (signeRef*crossproduct(start,q,start,r)<0){
          estCote = false;
          break;
        }
      }

      if (estCote){
        startQ = q;
      }
    }

    Point startRef = start;
    res.add(start);
    res.add(startQ);

    // Permet de trouver le point avec l'angle le plus petit
    Point cursor = new Point(0,0);
    do{
      double agl = Double.MAX_VALUE;

      for(Point R: points){ // On cherche parmis tous les sommets
        if(R != startQ && R != start){ // Si le point courant est différent de ses deux prédecesseurs
          if(angle(start, startQ, startQ, R) < agl){ // Compare l'angle courant avec l'angle le plus petit enregistré
            agl = angle(start, startQ, startQ, R); // Attribue un nouveau plus petit angle
            cursor = R; // Attribue un nouveau point au curseur (correspondant au plus petit angle)
          }
        }
      }

      res.add(cursor); // Ajoute le curseur a la liste de fin
      start = startQ;
      startQ = cursor;

    }while (!res.get(res.size()-1).equals(res.get(0)));

    return res;
  }

  public ArrayList<Point> algoNaif(ArrayList<Point> points){
    if (points.size()<4) {
      return null;
    }

    points = preCalculTriParPixel(points);

    ArrayList<Point> enveloppe = new ArrayList<Point>();

    for (Point p : points){
      for (Point q : points){
        if (p.equals(q)) continue; // Permet d'éviter le cas où p == q
        Point ref = p;

        // Permet d'éviter des calculs
        for (Point r: points) {
          if (crossproduct(p,q,p,r)!=0){
            ref = r;
            break;
          }
        }

        //Est assez inutile
        if (ref.equals(p)){
          enveloppe.add(p);
          enveloppe.add(q);
          continue;
        }

        double signeRef = crossproduct(p,q,p,ref);
        boolean estCote = true;

        // Permet de vérifier si les points sont tous du même côté.
        for (Point r: points){
          if (signeRef*crossproduct(p,q,p,r)<0){
            estCote = false;
            break;
          }
        }

        if (estCote){
          enveloppe.add(p);
          enveloppe.add(q);
        }
      }
    }
    return enveloppe;
  }

  public ArrayList<Point> algoToussaint(ArrayList<Point> points){
    Hashtable<String, Point> pc = trouvePointCardinaux(points);
    ArrayList<Point> enveloppe = algoJarvis(points);
    ArrayList<Point> res = new ArrayList<Point>();

    // Coin du rectangle couvrant
    Point NO = new Point(pc.get("ouest").x, pc.get("nord").y);
    Point SO = new Point(pc.get("ouest").x, pc.get("sud").y);
    Point SE = new Point(pc.get("est").x, pc.get("sud").y);
    Point NE = new Point(pc.get("est").x, pc.get("nord").y);

    double aire_minimum = Double.MAX_VALUE;
    double cosmin = Double.MAX_VALUE;

    for (Point coin : enveloppe){
      // Droite (NO, SO)
      if (getCosinus(pc.get("ouest"), SO, pc.get("ouest"), enveloppe.get(pc.get("ouest").x) ) < cosmin){
        cosmin = getCosinus(pc.get("ouest"), SO, pc.get("ouest"), enveloppe.get(pc.get("ouest").x));
      }
      // Droite (SO, SE)
      // Droite (SE, NE)
      // Droite (NE, NO)
    }



    return res;
  }

  // enveloppeConvexe: ArrayList<Point> --> ArrayList<Point>
  //   renvoie l'enveloppe convexe de la liste.
  public ArrayList<Point> enveloppeConvexe(ArrayList<Point> points){
    if (points.size()<4) {
      return null;
    }
    //points = preCalculTriParPixel(points);

    //points = algoJarvis(points);

      points = algoToussaint(points);


    return points;

  }

}
