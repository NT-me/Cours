# Algorithme 2

## Graphe complet, clique

Un graphe complet est un graphe simple dont tous les sommets sont adjacents.			

On note Kn=(S,A)
$$
(i,j) \in A \forall i, j \in S
$$


## Un stable

Un stable, aussi appelé ensemble indépendant, est un sous-graphe induit de G sans ares ( un sous ensemble de sommets deux à deux non adjacents).
$$
S'\subset S \ tq \  (i,j) \notin A \forall i,j \in S'
$$

- Le graphe complémentaire d'un graphe simple G=(S, A) est un graphe simple :
  $$
  \overset{-}{G}=(S,\overset{-}{A})
  $$
  ayant les mêmes sommets et tle que 2 sommets distincts de non G soient adjacent <=> ils ne sont pas adjacents dans G.

  **Un stable de G = une clique de non G**

  

  

$$
Ax = \begin{pmatrix} a11 & a12 & ... & a1n \\ a21 & a22 & ... & a2n \\ a1n & a2n & ... & ann \\ \end{pmatrix} \begin{pmatrix} x1 \\ x2 \\ xn \end{pmatrix}
$$

