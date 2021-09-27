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

### Suite aléatoire : Qu'est ce que c'est ?



## Chapitre II : Génération de structures combinatoires de base (Boite à outils)

## Chapitre III : Génération arborescente ou de graphes
