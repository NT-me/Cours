public Circle calculCercleMin(ArrayList<Point> points){
	Circle resultat;

	for (Point p: points) {
		for (Point q: points) {
			Point centre;
			double centre.x = (p.x+q.x)/(double)2;
			double centre.y = (p.y+q.y)/(double)2;
			double rayon = p.distance(q);
			if (contains(centre,rayon,points)) {
				return new Circle(centre,rayon);
			}
		}
	}

	// QUE FAIRE ICI?

	return resultat;
}
