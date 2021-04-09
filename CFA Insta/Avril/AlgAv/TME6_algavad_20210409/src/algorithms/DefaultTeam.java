package algorithms;

import java.awt.Point;
import java.util.ArrayList;

class Edge {
  protected Point p,q;
  protected Edge(Point p,Point q)
  {
    this.p=p;
    this.q=q;
  }
  protected double distance(){
    return p.distance(q);
  }

  public int smallerThan(Edge o) {
    if (this.distance() != o.distance()){
      if (this.distance() < o.distance()){
        return 1;
      }
      else{
        return -1;
      }
    }
    else{
      return 0;
    }
  }

}

public class DefaultTeam {

  public Tree2D algoNaif(ArrayList<Point> points){
    ArrayList<Point> rest = (ArrayList<Point>)points.clone();
    Point root = rest.remove(0);
    ArrayList<Tree2D> subTrees = new ArrayList<Tree2D>();
    Tree2D resultatNaif = new Tree2D(root, subTrees);

    while(!rest.isEmpty()){

      // On cherche le sommet le plus proche de la racine
      int closer_i = 0;
      for (int i = 0; i<rest.size(); ++i){
        if (rest.get(i).distance(root) < rest.get(closer_i).distance(root)){
          closer_i = i;
        }
      }
      root = rest.remove(closer_i); // On prend le sommet le plus proche comme root du nouvel arbre
      subTrees = new ArrayList<Tree2D>(); // On vide la liste
      subTrees.add(resultatNaif); // On lui ajoute l'arbre de l'itération précédente
      resultatNaif = new Tree2D(root, subTrees);

    }

    return resultatNaif;
  }

  public Tree2D kruskal(ArrayList<Point> points){
    ArrayList<Point> rest = (ArrayList<Point>)points.clone();
    ArrayList<Edge> candidats = new ArrayList<Edge>();
    // On créer la liste de toutes les arêtes dites candidates
    for (Point p: points) {
      for (Point q: points) {
        if (!p.equals(q) && !contains(candidats, p, q)) continue;
        candidats.add(new Edge(p,q));
      }
    }
    candidats.sort((o1, o2) -> (int) (o1.smallerThan(o2)));

  }

  private boolean contains(ArrayList<Edge> edges,Point p,Point q){
    for (Edge e:edges){
      if (e.p.equals(p) && e.q.equals(q) ||
              e.p.equals(q) && e.q.equals(p) ) return true;
    }
    return false;
  }

  public Tree2D calculSteiner(ArrayList<Point> points) {
    return algoNaif(points);
  }
}
