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
Acc = \frac{1}{(1-F_a)+\frac{F_a}{a}}
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

### Construction du d'instruction RISC

Instruction `= {opcode, opérandes}`

#### Construction de l'opcode

- 3 catégories d'instructions : ALU, Mem, Ctrl -> 2 bits pour les coder.

  Exemple : ALU = 00, MEM = 01, Ctrl = 10.

- La catégorie AlU compte le plus d'instructions (7) -> 3 bits pour coder l'instruction au de la catégorie

**Conclusion :** 5 bits pour le codage

**Exemple **de construction d'un jeu d'instructions :

Opcode :

- Instruction ALU (arithmétique ou logarithmique) -> Catégorie ALU
- Accès mémoire -> Catégorie MEM
- CTRL -> Catégorie CTRL

Il y a donc 3 catégories -> 2 bits suffisent pour les coder

Catégorie ALU -> 7 instructions -> 3 bits pour les coder

Catégorie mémoire -> 3 instructions -> 2 bits pour les coder

Catégorie CTRL -> 2 instructions -> 1 bit pour les coder 

Max 3 bits.

Le nombre d'instructions -> La taille de l'opcode (5 instructions)

On utilise le reste du RI pour coder les opérandes

##### **Les opérandes :**

###### Catégorie ALU

```asm
ADD R1 R2 R3
SLT R1 R2 R3
AND R1 R2 R1
```

Toujours le format 3 numéros de registre

11 bits pour coder 3 numéros de registre -> 3 bts pour le numéro de registre

**-> On a  au max 2³ registres à usage général **

###### Catégorie Mémoire

Format des opérandes :

2 numéros de registres et un déplacement *(sw, lw)* -> depl = 11 bits - 6 bits = 5 bits

1 numéro de registre et une valeur *(Li)*

- Valeur sur (11 bits - 3 bits) = 8 bit. La valeur appartient **[-2⁸, ..2⁸ - 1]**

"*déplacement*" Peut être :

​	Positif ou relatif, sur 5 bits, il appartient **[-2⁵, .. 2⁵ -1]**.

###### Catégorie CTRL :

*jump :* 1 numéro de registre + déplacement : déplacement (Positif ou négatif) sur *11 bits - 3 bits = 8 bits* 

*BNEZ* : 2 numéro de registre + déplacement : déplacement (positif ou négatif) sur *11 bits - 6 bits = 5 bits*

Écriture de l'opcode :

| 0, 0 | 0, 1, 1 | -> Opcode de SLT      |
| ---- | ------- | --------------------- |
| 0, 1 | 0, 0, 0 | **-> Opcode de LW**   |
| 1, 0 | 0, 0, 0 | **-> Opcode de jump** |

**ALU :**

AND -> 0 -> 0 0 0

OR -> 1 -> 0 0 1

ADD -> 2 -> 0 1 0

SLT -> 3 -> 0 1 1

NUL -> 4 -> 1 0 0

#### **Banc de registre :**

C'est un ensemble de n regsitres de même taille, regroupés et numérotés de 0 à n-1

Une instruction spécifie quel registre est à lire (resp. écrire) et le banc fournit (resp. inscrit) le mot contenu (resp. fournit) dans le registre en question.

Pour l'identification d'un registre parmi l'ensemble, plusieurs solutions sont utilisées : les multiplexeurs, les décodeurs et tristates entre autres.

###### Unité arithmétique et logique :

Composant réservé exclusivement au calcul. Il a en entrée au maximum deux opérandesvalides et des signaux de contrôle lui indiquant l'opération à effectuer.
Il fournit en sorte le résultat de l'opération. Les entrées et les sorties DATA sont de même taille qui est celle du machine, 16 bits dans notre as.

##### Mémoire :

Support de stockage pour stocker l'infirmation binaire, qu'elle soit une donnée (entier, réel, texte...) une adresse (exp. pointeur) ou une instruction.

###### Fonctionnement :

Exécution d'une instruction en 5 étapes

1. IF: Instruction Fetch
2. ID/RF: Instruction decode/ regsiters fecth
3. EX: Execution (dans le sens calcul)
4. MEM : Memory Acess (éventuellement)
5. WB : Write Back



|                   IF                    |                            ID/RF                             |                            EX                            |                             MEM                              |                             WB                             |
| :-------------------------------------: | :----------------------------------------------------------: | :------------------------------------------------------: | :----------------------------------------------------------: | :--------------------------------------------------------: |
| Phase commune à toutes les instructions | Phase commune<br />- Générer les signaux de CTRL<br />- Préparer les opérations | Calcul :<br />- Opération de calcul -> res<br />- Calcul | - Phase valide pour le LW/SW<br /> - (NOP pour les autres instructions) | - Écriture des résultats dans le regsitre (op, calcul, LW) |



## Pipeline

![Plan d'un pipeline générique à trois étapes](https://upload.wikimedia.org/wikipedia/commons/b/bb/Pipeline_cha%C3%AEne_de_traitement.png)

Le pipeline est semblable à une chaîne de montage. Dans une chaîne de montage de voitures, il y a un certain nombre d'étapes, chacune constituant une partie de la construction d'une voiture. Chaque étape opère en parallèle avec les autres étapes, mais sur une autre voiture.

Chaque étape constitue un étage du pipeline.

### Étude d'un pipeline simple

1. Principe de base : phase (logiciel) & étages (matériel)
2. Aléas de données : principe et traitement
3. Aléas de contrôle : Principe et traitement
4. Aléas matériels : Principe et traitement 

#### Notions de phases

Les phases sont une répartition commune à toutes les machines.

1. IF (instruction Fetch) : Chargement de l'instruction et MAJ du compteur de programme
2. ID (Instruction Decode) : Préparation des opérandes : Lecture des registres sources, extension des offsets
3. EX (Execution) : Exécution dans le sens calcul/production d'une valeur résultat. Phase complexe selon les instructions.
4. MEM (Memory access) : Accès mémoire pour lecture ou écriture
5. WB (Write Back) : MAJ des registres résultats

#### Exemple d'un pipeline classique RISC

En supposant que chaque étape met 1 cycle d'[horloge](https://fr.wikipedia.org/wiki/Horloge) pour s'exécuter, il faut normalement 5 cycles pour exécuter une instruction, 15 pour 3 instructions :

[![img](https://upload.wikimedia.org/wikipedia/commons/thumb/2/2c/Nopipeline.png/386px-Nopipeline.png)](https://commons.wikimedia.org/wiki/File:Nopipeline.png?uselang=fr)

> Séquençage des instructions dans un processeur sans pipeline. Il faut 15 cycles pour exécuter 3 instructions.

En utilisant la technique du pipeline, notre processeur peut alors  contenir plusieurs instructions, chacune à une étape différente.

[![img](https://upload.wikimedia.org/wikipedia/commons/thumb/2/21/Fivestagespipeline.png/386px-Fivestagespipeline.png)](https://commons.wikimedia.org/wiki/File:Fivestagespipeline.png?uselang=fr)

> Séquençage des instructions dans un processeur doté d'un pipeline à 5 étages. Il faut 9 cycles pour exécuter 5 instructions. À *t* = 5, tous les étages du pipeline sont sollicités, et les 5 opérations ont lieu en même temps.

Les 5 instructions s'exécuteront en 9 cycles, et le processeur sera  capable de terminer une instruction par cycle à partir de la cinquième,  bien que chacune d'entre elles nécessite 5 cycles pour s'exécuter  complètement. Au 5e cycle, tous les étages sont en cours d'exécution.

#### Temps de cycle du Pipeline

On s'algine sur l'étage le plus lent en considérant chaque instruction afin de ne pas perdre les données lors du passage d'un étage à un autre le pipeline est cadencé par ce **ce cycle.**

- **Start-up :** le temps nécessaire au pipeline d'avoir tous les étages occupés afin de produire le premier résultat au cycle suivant.

#### Table de réservation

**Définition :** Une table de réservation **élémentaire** est un tableau bidirectionnel associé à chaque instruction, décrivant cycle par cycle, l'utilisation des ressources matérielles. La table de réservation se construit de la manière suivant :

1. A chaque colonne est associé un numéro de cycle
2. A chaque ligne est associé une ressource matérielle
3. A l'intersection de la la ligne i et la colonne j on va avoir le nom de l'instruction occupant l'étage.

Si elle n'est plus élémentaire un appelle ça une table de réservation globale.

### Principe du pipeline

A la différence du fonctionnement clasqqieu en mode séquentiel, le pipeline n'attend pas la fin complète d'une tâche pour démarrer la suivante :

- Une nouvelle instruction est lancée à chaque cycle mais ceci n'est valable que pour un pipeline idéale.

**Exécutions :**

- Séquentiel : Lancer une instruction, une fois la précédente totalement terminée
- Pipelinée : Lancement de l’exécution d'une instruction *à chaque cycle* (On attend pas la terminaireson d'une instruction pour lancer la suivante)

**Cas idéal :** Instructions complètement indépendante : Après le startup, on a un résultat par cycle.

## Machines superscalaires

## Hiérarchie mémoire

