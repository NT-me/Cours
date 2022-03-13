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

### ETERNAL TWINS

### Présentation

>**Entrées :** Graphe temporel $L : (T,V,E)$ 
>
>**Sortie :** Liste de tous les jumeaux éternels

Le but de cet algorithme est donc de retourner une liste comprenant l'ensemble des pairs de sommets jumeaux éternels entre eux. Il s'agit d'un algorithme naïf reposant sur le la technique de raffinement de partition.

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

