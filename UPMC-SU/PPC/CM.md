# PPC

## Glossaire

[**Beta reduction** :](https://wiki.haskell.org/Beta_reduction) A *beta reduction* (also written *β reduction*) is the process of calculating a result from the application of a function to an expression.

## Introduction

Démonstration par le "ou" concurrent qu'il est nécessaire de créer des programmes parallèles et donc d'abandonner le lambda calcul car il ne peut pas marcher. On va utiliser le **$$\pi$$ calcul**.

La différence entre concurrence et parallélisme c'est :

- La concurrence va organiser de façon séquentiel le travail pour alterner rapidement entre les différentes tâches et simuler le fait que plusieurs applications s'exécute en même temps.
- La parallélisme c'est quand on organise ce "en même temps" pour de vrai.

 ## Réseau de Petri

![](https://upload.wikimedia.org/wikipedia/commons/d/d7/Animated_Petri_net_commons.gif)

Proposé par M.Petri en 1962. 

Le réseau de Petri est composé de trois éléments :

- Les places 
- Les transitions
- Les jetons
- Les arcs