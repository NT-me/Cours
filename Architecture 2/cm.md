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

Temps CPU = Temps d'exec d'un programme (en cycle) * durée d'un cycle
$$
Temps CPU = \frac{Nombre \ de \ cycles \ pour \ un \ programme}{fréquence \ d'horloge}
$$

**Performance CPU :** 2 métriques (MIPS, MFLOPS)

Le temps CPU en général est exprimé en cycle. La durée du cycle dépend de la fréquence de l'horloge de la machine.

**Le temps du cycle = 1/freq**
$$
TpsCPU_{Programme P} = Nombre \ d'instructions \ * \ Tps de cycle \ * \ durée \ d'executions \ d'une \ instruction \ = \ \frac{Temps_{moy}*N}{freq}
$$

$$
TpsCPU = 15 \ Megacycles 
$$

$$
\frac{1}{freq}= durée \ du \ cycle = 5 ms = 5*10^{-9}s
$$

$$
freq = \frac{1}{Durée \ cycle} = \frac{1}{5*10^{-9}s} = \frac{10^6}{5*10^{-3}}MHz = \frac{1000}{ 5}MHz 
$$

Les instructions sont différentes => ont des temps d'exécution différents.

C'est très rare (voire impossible) de connaître avec exactitude la composition des programmes et le temps d'exécution de chacune des instructions.

On utilise alors le CPI : Nombre moyen de cycles pour exécuter une instruction quelconque d'un programme.
$$
Temps \ CPU = \frac{Nombre \ d'instructions \ * \ CPI}{fréquence \ d'horloge}
$$

$$
TpsCPU = N \ Tps \ moyen \ d'exec \ d'une \ instruction 
$$

$$
TpsCPU(cy) = N*CPI
$$

$$
TpsCPU(s)=N*CPI*durée \ du \ cycle(s)= \frac{N*CPI}{freq}
$$

**Méthode 1:**

Le nombre de Cycle Par Instruction peut être calculée par :
$$
CPI=\frac{Nombre \ de \ cycles \ pour \ un \ programme \ P}{Nombre \ d'instructions \ dans \ le \ programme \ P}
$$


Dans l'exemple précédent P s'exécutait en 15 Mcycles et contenait 6 Minstructions.

**Conclusion :** La performance du CPU dépend de 3 paramètres :

- La fréquence de l'horloge qui dépend de la technologie du matériel.
- Le nombre moyen de cycles par instruction qui dépend du jeu d'instructions
- Le nombre d'instructions d'un programme qui dépend du jeu d'instructions et du compilateur

**MFLOPS** Millions d'instructions flottantes par secondes. Remarque :

-  Dépend d la machine et du programme
- Restreint aux machines exécutant des codes avec instructions en opérations flottantes
- Nombre d'opérations flottantes d'un même programme dépend de la machine (si l'opération flottante est câblée ou pas)
- Les différentes opérations n'ont pas le même coût 

### Test/Evaluation de performances

Choix du programme de test :

- Programmes réels : tester directement les applications que l'on va exécuter ou bien un ensemble d'applications représentatif du domaine d'applications (SPEC)
- Noyaux de programmes
- Benchmarks
- Benchmarks Synthtétiques.

## Jeu d'instructions

**Définition :** Une instruction = un ordre d'effectuer une tâche précise portant sur des données précises et de fournir un résultat.

Une instruction est un enregistrement de plusieurs champs :

- Un code opération : type d'instruction (ordre)

### Modes d'adressage

La technique de localisation des opérandes est appelée **mode d'adressage** et il en existe plusieurs, dont :

- **Immédiat :** La valeur de l'opérande est disponible *immédiatement*. Mode ne nécessitant aucun accès mémoire.

- **Direct : **l'adresse de l'opérande est disponible. On l'utilise pour accéder *directement* à sa valeur en mémoire. Mode nécessitant un seul accès mémoire. L'instruction contient l'adresse de la donnée.

  - Utilise pour les variables (en mémoire)
  - Un accès mémoire est nécessaire.

- **Indirect :** L'instruction contient l'adresse de l'adresse de la donnée

  - Utilisé pour les pointeurs
  - Deux accès à la mémoire sont nécessaires

- **Indexé :** Le mot instruction contient 2 informations :

  Un adresse de base et un déplacement par rapport à cette adresse.

Le déplacement est souvent exprimé en octet mais peut l'être en mots mémoire.

Un mot mémoire est une unité pour ranger ou lire les données. Il est exprimé en octet et fixé par le constructeur.

Une machine 32 bits => 1 mot = 32bits = 4 octets.

**Pour le mode indexé :**

Il faut une opération d'addition (adresse base + déplacement). Pour calculer l'adresse absolue et u accès mémoire.

### CISC vs RISC

Un jeu d'instructions est l'ensemble d'instructions désignant toutes les opérations que peut exécuter une machine (processeur).

Les processeurs sont classés par rapport à leurs jeux d'instructions, qui peuvent être :

- **CISC** (Complex Instruction Set Computer), exemple : PentiumPRO, I7, phenom...

  - Jeu d'instruction riches (beaucoup de variantes dans les instructions) mais complexes 

- **RISC** (Reduced Instruction Set computer) exemple : ultraSPARC, ARM

  Une particularité MISC (Minimal Instruction Set Computer : RISC poussé à l'extrême)

  - Jeu d'instructions réduits et simple (Peu de variantes) mais simples -> rapide à l'exec.

### Propriétés



## Processeur RISC

## Pipeline

## Machines superscalaires

## Hiérarchie mémoire

