# Dissertation TME8

## Définitions

**Graphe temporel :** Un graphe temporel $G$ est une séquence de graphes indexés par un ensemble d’entiers $T$ représentant les instants temporels. Les graphes sont ici non orientés et non pondérés.

**Vrai jumeaux :**  $\{u,v\}$ est une paire de **vrai-jumeaux** si et seulement si l'ensemble de leur voisin est exactement le même.

$     \left\{        \begin{array}{ll}            N(u) \text{\\} \{v\} = N(v) \text{\\} {u} \\    uv \notin E       \end{array}    \right. \equiv N(u)=N(v) $

**Jumeaux :** $\{u,v\}$ est une paire de **jumeaux** si et seulement si leurs voisins extérieurs est le même

$     \left\{        \begin{array}{ll}            N(u) \text{\\} \{v\} = N(v) \text{\\} {u}      \end{array}    \right. \equiv \forall s \notin \{u,v\}, (s,u\in E \equiv s,v \in E)$

**Jumeaux éternels :** Pour être des jumeaux éternels $\{u,v\}$ doivent être jumeaux dans chacun des $T$ graphes du graphe temporel $G$.

**$\Delta$-Jumeaux :** Pour être des $\Delta$-jumeaux $\{u,v\}$ doivent être jumeaux dans $\Delta$ graphes consécutifs indexés dans $G$, avec $\Delta$ un entier respectant $\Delta < T$.

## Introduction

**Problématique :** *Comment énumérer l'ensemble des pairs de jumeaux éternels et de $\Delta$-Jumeaux de façon instantanés a partir de graphes temporels collectés dans le monde réel ? Peut-on le confirmer numériquement par l'implémentation de ces algorithmes ?* 

Les graphes temporels permettent de représenter des données de différents domaines tels que les horaires de transports, de navigations.. De manière générale l'ensemble des données ayant une notion de temps. 

Les notions de jumeaux éternels et $\Delta$-Jumeaux permettent de comprendre deux réalités sur un graphe temporel :

- Les jumeaux éternels sont des données redondantes et donc inutile à conserver en $T$ exemplaires. Leur suppression permet d'accélérer les traitements sur les graphes temporels.
- Les $\Delta$-Jumeaux peuvent être les preuves de schémas ou de modèles dans le flux de donnée et sont donc nettement plus intéressant a rechercher pour les étudier.

## Algorithmes existants

### NAÏF

L'algorithme sert a prouver que le problème est solvable. Ici il s'agit d'une recherche exhaustive. 

Cet algorithme existe en deux versions, l'une pour les jumeaux éternels et l'autre pour les $\Delta$-Jumeaux :

**Jumeaux éternels :**

```
Entrée : Graphe temporel G (T, V, E)
Sortie : Liste des jumeaux éternels dans G

deltaTwinsLastInstant = Jumeaux de G à t0
resultat = liste de jumeaux vide

Pour t chaque instant dans T:
	twinsInstant = jumeaux de G à l'instant t
	tmp = liste de jumeaux vide
	
	Pour chaque jumeaux g dans deltaTwinsLastInstant :
		Pour chaque jumeaux e dans twinsInstant :
			Si g == e :
				ajouter g à tmp

	deltaTwinsLastInstant = tmp

resultat = deltaTwinsLastInstant
retourner resultat	
```

La complexité est de $O(n^3*\tau)$ avec $\tau = |T|$. 

**$\Delta$-Jumeaux : **

```
Entrée : Graphe temporel G (T, V, E), entier d
Sortie : Liste des delta-jumeaux dans G avec un delta de d

deltaTwins = liste de jumeaux vide
twinsAtT = liste de jumeaux vide

Pour chaque instant t dans T:
	twinsAtT = jumeaux de G à l'instant t
	
	Pour chaque jumeaux e dans twinsAtT:
		Si temps d'existence de e >= d alors:
			Ajouter e dans deltaTwins s'il n'y est pas déjà

retourner deltaTwins			
```

La complexité est de $O(n^3*\tau*d)$ avec $\tau = |T|$. 

### Edge Iteration algorithm for eternal-twins listing

#### Structure de donnée utilisée

**Matrice d'adjacence :** Il s'agit d'une façon de représenter un graphe au travers d'une matrice d'entier. Par exemple s'il existe un lien entre $u$ et $v$ alors $(u,v) = 1$. Prenons un graphe de trois sommets : $a, b$ et $c$ avec une arrête entre $a$ et $b$ :

```
  a b c
a 0 1 0
b 1 0 0
c 0 0 0
```

Dans le cadre d'une graphe temporel il s'agit d'une liste de matrice d'adjacence indexés par $T$ entiers.

Ce type de représentation présente un problème majeur : la place prise en mémoire. En effet la taille croît de façon polynomiale $n^2$ avec $n$ le nombre de sommets. Pour un graphe temporel cette problématiques est renforcé par la multiplication des graphes. On arrive a $n^2 * \tau$ avec $\tau$ la taille de l'historique du graphe temporel.

### Présentation

>**Entrées :** Graphe temporel $L : (T,V,E)$ 
>
>**Sortie :** Liste de tous les jumeaux éternels

Le but de cet algorithme est donc de retourner la liste de l'ensemble des pairs de sommets jumeaux éternel de façon indépendante de la taille de l'historique $\tau$ du graphe temporel $L$ passé en entrée.

Pour cela l'algorithme va s'appuyer la technique dites "*triangular structure of splitters*" ou "structure triangulaire de séparation", l'objectif va être de trouver un sommet $u$ qui prouve que $w$ et $v$ ne sont pas liés. De plus cette technique a comme avantage supplémentaire de ne pas nécessiter obligatoirement des matrices d'adjacence en entrée, en effet une simple liste d'arêtes est suffisante.

L'objectif va donc 

















Le **raffinement de partition** est une technique en algorithmie permettant de représenter une partition d'ensemble comme une structure de donnée qui permet d'affiner cet ensemble en plusieurs sous ensemble.

```
Algorithme de raffinement partition
Nom : RP
Entrée : Liste de liste d'entier L a raffiner, Liste d'entier S selon laquelle on va raffiner
Sortie : Liste de liste d'entier représentant les partitions de l'ensemble

L0 = [[0], [1], [2], [3], [4], [5], [6], [7]]
S0 = [0, 3, 5]

RP(L0, S0) 
-> L1 = [[0, 3, 5], [1], [2], [4], [6], [7]] 
S1 = [2, 4, 5]

RP(L1, S1)
-> [[5], [0, 3], [2, 4], [1], [6], [7]]
```

L'algorithme ici nommé `RP` va donc partitionner selon la règle $\{L \cap S, L\text{\\}S \}$. 

Il est a noté que deux versions de cet algorithme existe : 

### Analyse

### Argumentation construite

### $\Delta$-TWINS

### Présentation

### Analyse

### Argumentation construite

## Mon implémentation

### Présentation

### Analyse

### Argumentation construite

*Ptet pas obligatoire*

## Comparaison

### Les jeux de données

#### Présentation

#### Origines

#### Utilisation

### Tests de performances

### Discussion

## Conclusion

