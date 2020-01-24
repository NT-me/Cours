# Cours architecture 2

## Notions de performance

Les principaux facteurs de performance sont :

- **Technologique** : Plus au niveau électronique, mais c'est globalement le hardware, la partie matériel.
  - Temps de cycle réduit
  - Degrés d'intégration élevé
    - Plusieurs composants de calculs -> exécution parallèle
- **Architectural** : Gérer la hiérarchie pour que les données soient au plus proche du CPU quand celui-ci pourra les calculer ou même la proximité des cellules de calculs.
  - CPU : Ordonnancement correct des instrs pour une exécution efficaces (utilisation max du CPU)
  - Hiérarchie : Dimensionnement adéquat (caches, mémoires, disques) + les stratégies de placement et migration de données
  - Assemblage : Réseau rapide/msg échangés rapidement
- **Logiciel **
  - Adapter l'application à la machine qui l'exécute.

### Quantifications

- Temps de réponses
- Latene
- Débit ou bande passante
- Accélération ou speed up  : Il est nécessaire d'avoir une machine de référence, ou un mode d'exécution différent. Il s'agit d'une comparaison de paradigme
- Efficacité ou rendement : On compare le coût de l'investissement à l’accélération produite par ce dernier. 

### Calcul de performance : Accélération 

#### Loi d'Amdhal

T : Temp d'exécution du programme P.

N : Le nombre d'instructions dans le programme P.

Vmoy ; La vitesse moyenne d'exécution d'une instruction
$$
T = N * V_{moy}
$$
Exemple dans diapo, loi d'Amdahl.

### Calcul de performance : Efficacité

Acc : L'accélération apportée par l'amélioration

C : Le coût de l'amélioration **par rapport à l'état initial**. Exprimé en coût financier ou en nombre de ressources... etc.

a :  Les instructions.

Fa : est la fraction des instructions qui ont permis l’accélération. 
$$
Eff = \frac{Acc}{C}
$$

$$
Acc = \frac{A}{(1-F_a)+\frac{F_a}{a}}
$$

Si `Eff`:

- `=1` : Aussi efficace que la réf
- `<1` : Pas efficace
-  `>1` : Efficace.

### Performance CPU : MIPS, MFLOPS

Les métriques spécifiques pour quantifier les performances du CPU : MIPS, MFLOPS
$$
MIPS = \frac
$$


## Jeu d'instructions

## Processeur RISC

## Pipeline

## Machines superscalaires

## Hiérarchie mémoire

