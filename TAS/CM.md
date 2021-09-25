# TAS

## Introduction

Présentation du cours TAS :

- La fiabilité logicielle : garantir la sûreté d'exécution (et la sécurité)
  - Par tests (analyse dynamique) : Pose soucis car non exaustif
  - Par preuve totale
    - A la main
    - Système d'aide (Coq, Isabelle, Atelier-B, PVS...)
  - Par preuve partielle
    - Model-checking
    - Interprétation abstraite (Partie AS de TAS)

- Génie logiciel
  - Combiner les deux approches : programmation modulaire et composition avec la compilation séparée :arrow_forward: vérifiaction des types
  - Avec le maixmum de générécité :arrow_forward: classes de polymorphismes
- Fiabilité du logiciel
  - Le typage est une analyse statique
  - La plupart des analyses ont besoin de traailler sur un programme typé
- Efficacité
  - Le typage peut permettre des optimisations
  - Crtaines propriétés statiques aussi

En somme c'est un cours où l'on va parle de langage

- Objet d'étude :arrow_forward: Les programmes
- Vérifications
  - Typage des composants au sens large (Génie et Fiabilité logicielle)
    - Fonction, objets, modules
    - Système des types, inférence
    - Classes de polymorphisme
  - Analyse statique (Fiabiité des logiciels)
    - Par intérprétation abstraite
  - Effectué automatiquement par d'autres programmes
  - Et statiquement : c'est à dire avant l'exécution

On remarque une véritable volonté lié a un besoin des géants de l'industrie a créer des lanages dynamiquemet typés.

## Objectif

Présenter les technniques de base de l'interprétation abstraite ainsi que ses fondements théoriques. Avec un accent sur le mise au point pratique d'un analyseur statique.

## $$\lambda$$​ calcul et codage de donnée

Le $$\lambda$$-calcul est la base de tous les langages de programmation surtout fonctionnel.

## $$\lambda$$-calcul simplement typé

## $$\lambda$$-calcul aux langages de programmation typage d'un mini-ML





