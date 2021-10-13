# AAGA

## Chapitre I : Génération d'entiers

### Introduction

Historiquement on a toujours cherché a limiter le hasard dans les ordinateurs c'est à dire être sûr que lancer le même algo avec les même entrées donnera le même résultat.

Le besoin du hasard est présent surtout dans 3 domaines en informatique :

- La simulation
- L'algorithimique
- La cryptographie

Les 3 domaines n'ont pas besoin du même hasard.

Pour la crypto il faut absolument que ce soit non prévisible alors que pour la simu ce n'est pas forcément un soucis.

#### Deux types de générateurs d'entiers :

##### True Random Number Generator

- Utilisés en crypto
- Complexe et couteux
- En général basé sur des mesures physiques non probabiliste

##### Pseudo Random Number Generator

- Ils sont détérministes : pas réelement aléatoire
- Très rapide
- Portable
- On peut produire des séquences d'entiers aléatoires (grâce à une graine)

Problème : **Trouver un bon générateur !**

Preuve de l'existnce de $$\lambda$$ (qui est la période de répétition):

Soit E qcq mais fini.

Soit $$f:E \rightarrow E$$ qcq

$$X_0 \text{ qcq } \in E$$ 

On génère des entiers : $$X_0; X_1 = f(X_0); X_2 = f(X_1)...X_e=f(X_{e-1})$$.

E est fini, $$|E|=k$$​ : nombre de valeurs différents différents des dans E

Supposons $$l>k$$, alors il y au moins une répétition.

### Exemple de PRNG

**Récurrence**

(trouver de quoi c'est la récurrence)
$$
X_{n+k}=a^kX_n+\frac{a^k-1}{a-1}c
$$


### Suite aléatoire : Qu'est ce que c'est ?

## Chapitre II : Génération de structures combinatoires de base (Boite à outils)

### Retour sur quelque généralités

#### Terminaison de l'algo

- Elle n'est pas prouvable

**Rappels :**
$$
-\frac{x}{(a-x)^2} == \sum^{\inf}_{k=0}k*x^k
$$

#### **Valeur moyenne (espérance) du nombre de rejet:**

$$
\frac{2^l}{n}-1
$$

**Remarque sur le dé à 6 faces :**
$$
2^l = 8 \ n = 6 \\
\frac{2^l}{n}=\frac{1}{3}
$$
Cela signifie qu'on fais 1/3 de rejets avec le dé à 6 faces.

### Générer des permutations

**Rappel :**

La permutation $P=[p_1,..., p_n]$​ a une probabilité d'apparition de $\frac{1}{!n}$​​.

#### **A propos de l'algorithme `gen_permutation_1` nommé Fisher-Yates**

La consommation de bits aléatoires est au niveau des instructions :

- `rand_int(1,n)` :arrow_right: $\alpha \ log_2n$ ​
- `rand_int(1,n-1)` :arrow_right: $\alpha \ log_2n-1$
- `rand_int(1,n-2)` :arrow_right: $\alpha \ log_2n-2$​
- ...
- `rand_int(1,2)` :arrow_right: $\alpha \ log_22$​`
- `rand_int(1,1)` :arrow_right: $\alpha \ log_21$​`

Il a noter qu' $\alpha$ est le nombre de de rejet moyen et tends vers 1 et peut donc être négliger.

On pourrait penser que l'algorithme `gen_permutation_1` présent dans les diapo est rapide bien qu'il soiten réalité relativement lent a cause de la lecture de la liste.

#### A propos de l'algorithme `gen_permutation2` nommé Fisher-Yates-Knuth...



## Chapitre III : Génération arborescente ou de graphes

