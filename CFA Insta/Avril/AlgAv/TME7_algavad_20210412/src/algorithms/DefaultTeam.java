package algorithms;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;

class Edge {
  protected Point p,q;
  protected Double distance;
  protected Edge(Point p,Point q)
  {
    this.p=p;
    this.q=q;
  }

  protected double distance() {
    return this.p.distance(q);
  }

  public Tree2D convertToTree(){
    Point root = this.p;
    ArrayList<Tree2D> subtree = new ArrayList<Tree2D>();
    subtree.add(new Tree2D(this.q, new ArrayList<Tree2D>()));
    return new Tree2D(root, subtree);
  }

}

public class DefaultTeam {

  private boolean contains(ArrayList<Edge> edges,Point p,Point q){
    for (Edge e:edges){
      if (e.p.equals(p) && e.q.equals(q) ||
              e.p.equals(q) && e.q.equals(p) ) return true;
    }
    return false;
  }

  public int[][] calculShortestPaths(ArrayList<Point> points, int edgeThreshold) {
    return floydWarshall(points, edgeThreshold);
    //return binhFloydWarshall(points, edgeThreshold);
  }

  public int[][] floydWarshall(ArrayList<Point> points, int edgeThreshold) {
    int[][] paths=new int[points.size()][points.size()];
    for (int i=0;i<paths.length;i++)
      for (int j=0;j<paths.length;j++)
        paths[i][j]= i;

    double[][] dist=new double[points.size()][points.size()];
    for (int i=0;i<paths.length;i++){
      for (int j=0;j<paths.length;j++){
        if(i==j){
          dist[i][i] = 0;
        }
        else if (points.get(i).distance(points.get(j))<=edgeThreshold){
          dist[i][j] = points.get(i).distance(points.get(j));
        }
        else{
          dist[i][j] = Double.POSITIVE_INFINITY;
        }
        paths[i][j]=j;
      }
    }


    for (int k=0;k<paths.length;k++){
      for (int i=0;i<paths.length;i++){
        for (int j=0;j<paths.length;j++){
          if (dist[i][k] + dist[k][j] < dist[i][j]){
            dist[i][j]=dist[i][k] + dist[k][j];
            paths[i][j]=paths[i][k];
          }
        }
      }
    }

    return paths;
  }

  private double foldedPathDistanceValue(int i, int j, int[][] paths, ArrayList<Point> points) {
    if (i==j) return 0;
    int etape = paths[i][j] ;
    return points.get(i).distance( points.get(etape) ) + foldedPathDistanceValue(etape, j, paths, points) ;
  }

  /*
  1. Modifier l'algo de Kruskal pour pouvoir le faire tourner sur une liste d'arêtes et prendre en compte les nvx poids
  2. Faire tourner kruskal sur la liste d'arête ci-dessous
  3.
   */
  public Tree2D calculSteiner(ArrayList<Point> points, int edgeThreshold, ArrayList<Point> hitPoints) {
    ArrayList<Point> rest = (ArrayList<Point>)points.clone();
    ArrayList<Edge> candidats = new ArrayList<Edge>();
    int[][] paths = floydWarshall(points, edgeThreshold);
    double[][] dist=new double[points.size()][points.size()];
    Arrays.fill(dist, 0);

    // On créer la liste de toutes les arêtes dites candidates
    for (Point p: points) {
      if (hitPoints.contains(p)){
        for (Point q: points) {
          if (!p.equals(q) &&
                  !contains(candidats, p, q) &&
                  hitPoints.contains(q) &&
                  p.distance(q) <= edgeThreshold
          ){
            candidats.add(new Edge(p,q));
            paths[points.indexOf(p)]
          }
        }
      }
    }

    return steinerTree;
  }


}
