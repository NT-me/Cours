# ROPL

## Chapitre I : Programmation linéaire en 2D

### Problème A 

> Bob fabrique des yaourt de deux type : Allégés et sucrés, avec 3 ingrédients. Les proportions sont les suivantes :
>
> | Ingrédients/Type | Allégés | Sucrés |
> | ---------------- | ------- | ------ |
> | Fraises          | 2       | 1      |
> | 1                | 1       | 2      |
> | Sucre            | 0       | 1      |
>
> Prix de vente :
>
> - Allégés : 4€/kg
> - Sucrés 5€/kg
>
> Les stockes disponibles sont :
>
> - 800kg de fraises
> - 700L de lait
> - 300kg de sucre
>
> **<u>Question :</u>** Comment maximiser le revenu de Bob ?

### Modélisation problème A 

Soit X<sub>a</sub> la quantité produite de yaourt allégé et X<sub>S</sub> la quantité produite de sucré

**Fonction objectif :**
$$
max(4x_a + 5 x_s)
$$
Ce qui va correspondre au revenu de Bob.

**Contraintes :** 
$$
2x_a + x_s ≤ 800 \text{ (800kg de farine)}\\
x_a + 2x_s ≤ 700 \text{ (800kg de lait)}\\
x_s ≤ 300 \text{ (300kg de sucre)}\\
x_a ≥ 0 \\
x_s ≥ 0
$$

### Résolution graphique 

*Voir cours du prof pour la courbe.*

- *Droite 2x<sub>a</sub>+x<sub>s</sub> = 800* : deux points (0,800) et (400,0)
  - **Remarque :** 2*0 + 0 = 0 < 800, donc 0 est du côté <= 800

- *Droite x<sub>a</sub>+2x<sub>s</sub>=700* : deux points (700,0) et (0,350)

**Domaine réalisable : **Ensemble des solutions réalisables

**Remarque : **le maximum s'il existe, est atteint en un sommet du domaine réalisable

**Conséquence :** Pour trouver le maximum s'il existe, il suffit de calculer la valeur de la fonction objectif pour chaque sommet. 

| Sommet                                  | O: (0,0) | A:(0,300) | B(100,300) | C : (300, 200) | D : (400, 0) |
| --------------------------------------- | -------- | --------- | ---------- | -------------- | ------------ |
| Valeurs (4x<sub>a</sub>+5x<sub>s</sub>) | 0        | 1500      | 1900       | 2200           | 1600         |

Donc Bob gagnera au maximum 2200 € en faisant 300 allégés et 200 sucrés.

Il s'agit ici d'un problème de **production**.

### Etapes pour résoudre un problème d'optimisation

1. Modélisation
   - Quelles sont les variables a introduire ?
   - Quelle est la fonction objectif ?
   - Quelles sont les contraintes ?
2. Résolution des programmes linéaires (PL) obtenu
   - En 2D : Résolution graphique
   - En général : Algo du simplexe

### Un peu de vocabulaire

Un programme linéaire est généralement représenté sous forme matricielle :
$$
max(c.x)\\

\left \{
\begin{array}{c @{=} c}
    A_x \le b \\
    x \ge 0 \\
\end{array}
\right.
$$
Dans le problème de Bob :
$$
x=
\begin{pmatrix}
x_a \\
x_s
\end{pmatrix}
$$
c = [4 5], donc c.x = 4x<sub>a</sub> + 5x<sub>s</sub> et
$$
a = 
\begin{pmatrix}
2 ~ 1 \\
1 ~ 2 \\
0 ~ 1
\end{pmatrix} et ~ 
b=
\begin{pmatrix}
800 \\
700 \\
300
\end{pmatrix}
$$

$$
A_x = \begin{pmatrix}
2 ~ 1 \\
1 ~ 2 \\
0 ~ 1
\end{pmatrix}
\begin{pmatrix}
x_a \\
x_s
\end{pmatrix}
=
\begin{pmatrix}
2x_a + x_s \\
x_a+2x_s \\
x_s
\end{pmatrix}
\le
\begin{pmatrix}
800 \\
700 \\
300
\end{pmatrix}
$$

**Définition :** 
$$
\{x:A_x \le b, x\ge 0\}
$$
est l'ensemble des solutions réalisables et appelé **POLYEDRE**.

C'est aussi l'intersection d'un nombre <u>fini</u> de demi-espaces. Un polyèdre borné est un **POLYTOPE**. En 2D, ces demi-espaces sont des demi-plans et les polytopes sont polygones.

Une **FACE** d'un polèdre est l'ensemble des points du polyèdre qui vérifie une des inégalités à égalité.

Si lorsqu'on enlève l'inégalité de la description (A<sub>x</sub> <= b), on obtient le même polyèdre, cette inégalité est dite **REDONDANTE**.

**Remarque :** Les polyèdres sont convexes. P convexe lorsque pour tout x, y dans P, le segemnt [x, y] est contenu dans P.

### Complexité de l'algo

Quelle est la complexité de l'algorithme "résolution graphique" (algorithme utilisé un peu plus haut) ? Appliqué a un polygone définit par un m inégalités, en supposant qu'aucune inégalité n'est redondante.

**Mini-question :** Combien P a-t-il de sommets ? ~> m

**Algo :**

> Pour toute paire de droite provenant de la description du polygone, on calcule le point d'intersection.
>
> Si ce point est dans ce polygone, c'est un sommet

O(m²)

> Ensuite, il suffit de trouver un sommet de plus grande valeur

 O(m) : Il y a m sommet, et on doit calculer la valeur de la fonction objectif pour chacun d'entre eux.

**Total :** O(m^3^) il est donc polynomial.

<u>Question :</u> Qu'est ce que ça donne en dimension d ?

Si P est un polytope avec m inégalités : m dimension d, un sommet est l'intersection de d de ces m inégalités. 
$$
\begin{pmatrix}
m \\ d
\end{pmatrix}
=
(m^d)
$$
Et m^d^ possibilités ça explose.

### Redondance

Sur l'exercice 1 du TD_2D on peut observer que la droite $ 20x_{T1} + 30x_{T2} \le 480 $ est redondante. Pourquoi ?

On a déjà :
$$
(1) 40x_{T1}+30x_{T2} \le 360 \\
(2) 20x_{T1}+30x_{T2} \le 480 \\
(3) -x_{T1} \le 0\\
(4) -x_{T2} \le 0\\
$$
 Sur le dessin on observe que (2) est redondante :
$$
(1) - (3) : \left \{
\begin{array}{c @{=} c}
    40x_{T1}+30x_{T2} \le 360 \\
    -x_{T1} \le 0 \\
\end{array}
\right. \\
39x_{T1}+30x_{T2} \le 360 \\ \text{ Cette inégalité est valide pour l'ensemble des points satifaisant (1) et (3)}
$$
$(1)-20*(3)$ donne donc $ 20x_{T1}+30x_{T2} \le 360 (*) < 480 $.

Y'a t-il une relation entre (*) et (2) ?

Tous les points satisfaisant (*) vérifiant (2).

<u>Idée :</u> Une inégalité est redondante si on peut écrire une inégalité au moins aussi forte en combinant les autres.

## Chapitre II : Algorithme du simplexe

...

### Forme standard

$$
max ~ cx \\

\left \{
\begin{array}{c @{=} c}
    A_x = b \\
    x \ge 0 \\
\end{array}
\right.
$$



**Conséquence :** Sous forme standard on peut supposer $ rang(A) = m $, où m est le nombre de lignes de A.

*Ex :*

> $$
> \begin{pmatrix}
> 1 ~ 0 \\ 0 ~ 1 \\ 1 ~ 1
> \end{pmatrix}
> \text{N'a pas de rang(A) = m, car} ~ L_3=L_1+L_2
> $$

Dorénavant on supposera que dans la forme standard le rang de la matrice est égal à son nombre de ligne.  $ rang(A) =m $.

### Définitions 

Soit : 
$$
max ~ cx \\

\left \{
\begin{array}{c @{=} c}
    A_x = b \\
    x \ge 0 \\
\end{array}
\right.
$$
un programme linéaire sous forme standard.

- Un ensemble $ B \subseteq \{1, -d\}$ (avec d correspondant au nombre de colonne de la matrice) tel que les colonnes de A indicées par B forment une matrice $ A_b$ inversible est appelé **une base** 
  - $ x_B = (x_j:j \in B)$ sont les **variables de base**
  - $x_H= (x_j:j \notin B) $ sont les **variables hors base**

*Ex :*

> $$
> A = 
> \begin{pmatrix}
> 1 ~ 2 ~ 4 ~ 7 \\ -1 ~ 1 ~ 1 ~ 8 
> \end{pmatrix}
> $$
>
> <u>Q:</u> $ \{1, 2\} $ forme une base ?
>
> <u>R:</u> Oui car elle est inversible 

- Etant donné une base B : poser $ x_H = 0$ (c'est à dire mettre toutes les variables hors-base à zéro) définit une solution unique au système $A_x = b$ 

  ![img](https://i.gyazo.com/a7c7a5e58d1bb58ac55e75f5663b9a3e.png)

On obtient $A_xx_B =b $ qui a pour unique solution $x_B = A_B ^{-1}b $.

Cette solution $(x_B,x_H)$ est appelée **solution de base associée à la base.** (Variable hors base a 0 et système linéaire restant résolu)

Si $x_B,x_H\ge0$ : c'est une **solution de base réalisable**. (Toutes les variables sont positives ou nulles)

Et le simplexe va chercher a améliorer les valeurs de la fonction objectif. 

- Coûts réduits : On écrit la fonction objectif en fonction des variables hors base et une fois ceci fait les coefficients obtenus sont les coûts réduits des variables.

  - <u>Idée :</u> Le coût réduit d'une variable indique de combien augmenterais la fonction objectif si en faisant entrer la variable dans la base.

  - <u>Conséquence :</u> Si tout les coûts réduits sont négatifs ou nul alors la solution courante est "optimale" <u>ssi</u> elle est **réalisable**. Si elle n'est pas réalisable cela signifie que la solution courante est du côté de l'optimale mais est en dehors du domaine réalisable.

    ![img](https://i.gyazo.com/8b72a8c3be9199ab31b9992857610dd8.png)

    Et le point rose est **réalisable** et **optimale**.

###  Déterminer si une base est réalisable

  Etant donné un PL, pour vérifier si la base est réalisable :

  1. Déterminer la matrice carrée obtenue
  2. Si c'est < 0 ça forme une base
  3. Déterminer les variables en base (celle dans la matrice carrée) et hors base (les autres)
  4. Poser les variables hors bases a 0
  5. Résoudre le système linéaire sachant ça
  6. Les valeurs trouvées pour les variables en base détermine la solution de base
  7. Si elle est supérieur ou égal a 0 alors c'est réalisable

  ### Trouver les coûts réduits

Ici sur la base $ I = \{4,5\} $ issue de l'exercice 2 du TD "Solution de base"

![image-20210105112109959](C:\Users\Theo\AppData\Roaming\Typora\typora-user-images\image-20210105112109959.png)

**Remarque :** On peut lire immédiatement dans le tableau :

- Les coûts réduits
- Les coordonnées de la solution de base associée

Le tableau associé à une base B donnée est la réécriture sous forme de tableau de $ 
    A_x = b \\
    F \\
$ avec  $\begin{pmatrix}
1 ~ 0 \\ 0 ~ 1 \\ 0 ~ 0  
\end{pmatrix} $dans les colonnes correspondant à B.

### Algorithme du simplexe (Phase 2)

#### Entrée/Sortie

**Entrée :** Un programme linéaire sous forme standard et une solution de base réalisable (base B) et son tableau

**Sortie :** La valeur du programme linéaire, et une solution optimale si cette valeur est finie.


#### Corps

Tant qu'il existe une variable hors-base de coût réduit strictement positif :

  - Variable entrante : variable k hors-base de coût réduit maximum
  - Variable sortante : variable l minimisant (avec le plus petit) $ \frac{b_i}{a_{i,k}}  avec ~ a_{i,k}>0$ 
  - Nouvelle base : $B:=B \cup \{k\} \text{\ } \{l\} $ 
  - Ecrire le tableau associé à la nouvelle base

#### Terminaison (Conditions de sortie)

- Tous les coûts réduits sont négatifs ou nuls : l'algorithme termine et la solution de base courante est optimale
- S'il existe un coût réduit strictement positif (donc il y a une variable entrante) mais que tous les $a_{i,k}$ sont positif ou nul (il n' a pas de variable sortante), alors la valeur du PL est $+ \infty $

**Remarque :** Pour revenir a une solution optimale du PL de départ, il suffit de ne plus tenir compte des variables d'écarts (remarque issue de l'exemple déroulé du simplexe)

##### **Exemple du tableau type**

|                               | Nom de toutes les variables          | Résultat des équations (b)      |
| ----------------------------- | ------------------------------------ | ------------------------------- |
| **Nom des variables de base** | Coefficients de toutes les variables | Résultat des équations          |
| **Coût (c)**                  | Coefficient de la fonction objectif  | Inverse de la solution courante |

**Exemple tiré de l'exercice 2 du TD :**

![image-20210106112316472](C:\Users\Theo\AppData\Roaming\Typora\typora-user-images\image-20210106112316472.png)

Le premier tableau sera donc :

$x_B = \{e_1, e_2, e_3 \}$

$x_H = \{x_1, x_2\}$

|         | $ x_1 $ | $ x_2 $ | $ e_1 $ | $ e_2 $ | $ e_3 $ | b             |
| ------- | ------- | ------- | ------- | ------- | ------- | ------------- |
| $ e_1 $ | 2       | 3       | 1       | 0       | 0       | 1             |
| $e_2$   | 1       | 0       | 0       | 1       | 0       | $\frac{1}{3}$ |
| $e_3$   | 0       | 1       | 0       | 0       | 1       | $\frac{1}{4}$ |
| **C**   | 1       | 1       | 0       | 0       | 0       | 0             |

