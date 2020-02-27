# Cryptographie

## La stéganographie

**Définition :** La stéganographie est l'art de cacher un message dans un autre message (art de la dissimulation)

## La cryptographie

"La cryptographie est la pratique et étude des techniques pour assurer des communications sûres en présence d'adversaires" -- Ron Rivest

### Confidentialité

S'assurer que le message ne peut pas être lu par quelqu'un écoutant le transfert.

### Authenticité

S'assurer de la provenance d'un message et de l'authenticité de son émetteur.

### Intégrité

S'assurer de la non modification du message accidentelle ou intentionnelle.

## Chiffrements historiques

### La scytale

Apparue à Sparte 400 av JC.

**Chiffrer :**

- Enrouler la ceinture sur la scytale
- Écrire le message en plaçant une lettre sur chaque circonvolution  

**Déchiffrer**

Avoir une scytale de même diamètre.

### Transposition rectangulaires

Message : 

"Cela semble toujours impossible, jusqu'à ce qu'on le fasse."

Mot clé : "C R Y P T O"

| C    | R    | Y    | P    | T    | O    |
| ---- | ---- | ---- | ---- | ---- | ---- |
| 1    | 4    | 6    | 3    | 5    | 2    |
| C    | E    | L    | A    | S    | E    |
| M    | B    | L    | E    | P    | O    |
| S    | S    | I    | B    | L    | E    |

 = CMS EOE AEB EBS SPL LLI

### Le chiffrement de César

On fait correspondre A à une autre lettre de l'alphabet puis on recréer un alphabet correspondant.

### Les chiffrements par substitution

**Substitution :** Remplacer chaque lettre par une autre

#### Le carré de Polybe

**Polybe**, historien grec (200 - 125 av JC)

![http://alexandre.goyon.pagesperso-orange.fr/Carre_de_Polybe_fichiers/image002.gif](http://alexandre.goyon.pagesperso-orange.fr/Carre_de_Polybe_fichiers/image002.gif)

#### Le chiffre des templiers

On substitue les lettres par des symboles issue de la croix de Malte.

## L'analyse des fréquences

- méthodes de cryptanalyse développée par les Arabes au 9e siècle

- Exposée dans le "Manuscrit sur le déchiffrement des messages cryptographiques" d'Al Kindi.

  Dans chaque langue, certaines lettres ou des combinaisons de lettres apparaissent avec une certaines fréquence

**Conclusion :** Le chiffrement par substitution mono alphabétique est très fragile

-> Comment résister à l'analyse des fréquences

Quelque alternatives peu efficaces :

- Mal orthographier le message clair 
- Remplacer chaque mot par un autre mot ou symbole

Des solutions plus sérieuses 

- Substitution homophoniques (Remplacer une lettre par un nombre de symboles proportionnel à la fréquence d'apparition de la lettre)

## Chiffre de Vigenères

On utilise plusieurs alphabet de cryptage.

## Enigma

Pendant la première guerre mondiale le manque de chiffrement efficace créer un gros problème lors des communications sensibles.

Enigma inventée en 1918 par Arthur Scherbius et dont le premier modèle fut exposé en 1923 à Berlin.

**Partie principale :**

- Clavier
- Tableau lumineux
- Rotors
- Tableau de connexion
- Réflecteur

### Simulateur :

https://observablehq.com/@tmcw/enigma-machine

Une machine à trois rotors peut chiffrer avec 26³ alphabets différents.

### La clef

La clef est définie par :

```
ordre des rotors + positions de départ des rotors
```

## Cryptographie symétrique

Deux grandes familles de chiffrement symétrique  

-  Chiffrement à flot
- Chiffrement par blocs

### Chiffrement à flot

```mermaid
graph LR;
	Télphone -.-> Antenne;
```

$$
e_K(m)= c → d_k(c) = m
$$



La voix est transmise de façon digirale et la communication est chiffrée avec chiffrement à flot

**Idée :** Les bits sont chiffrées un par un donc le nom vient du flux de bits qui arrivent et qui sont chiffrés au fur et à mesure.

**Chiffrement :** 
$$
c_i = m_i + s_i \ mod \ 2 \\ s_i \ suite \ chiffrante
$$
**Déchiffrement :**
$$
m_i = c_i \ mod \ 2
$$


Je veux chiffrer un message :
$$
m = (m_0, m_1, m_2 \}  m_i \in \{0, 1\},  i \ge 0
$$
en un message chiffré :
$$
c= (c_0, c_1, c_2, ... \} \ c_i \in \{0, 1\}
$$
à l'aide d'une suite chiffrante :
$$
s = (s_0, s_1, ...)
$$
**Exemple :** 
$$
c_0 = m_0 + s_0 \ mod \ 2 \\
m_0 = 0, \ s_O = 1 \\
c_0 = 1
$$

**Exemple chiffrer la lettre 'A' ASCII *1000001* ** :
$$
m_6 \ m_5 \ \ m_0 = 1000001 \\
s_6 \ s_5 \ \ s_0 \ \ \ \ \ = 0011001
$$

![https://fracademic.com/pictures/frwiki/83/Schema_ofb.png](https://fracademic.com/pictures/frwiki/83/Schema_ofb.png)

**Question** comment générer la suite chiffrante *s* ?

La suite s doit ressembler à une suite aléatoire

1. Vrais générateurs basés sur des phénomènes imprévisibles

   - Dés, pile ou face,loto, bruit thermique, mouvement de la souris, frappes sur le clavier. L'inconvénient c'est que c'est très lent, il n'est pas possible de reproduire exactement la même suite chiffrante.

2. Générateurs de nombre pseudo-aléatoire (PRNG)

   Suite qui ont l'air aléatoire calculé de façon déterministe qu'on peut reproduire. En général ells sont des bonnes propriétés statistiques.

   Calculés récursivement à partir d'une valeur initiale, la graine.
   $$
   S_0 = \ graine \\
   S_{i+1} = f(S_i) \ i \ge 0
   $$
   

*Exemple :* `rand()` de C
$$
S_0= 12345 \\
S_{i+1} = 1103515245 \ S_i + 12345 \ mod \ 2^{31}
$$

3. Générateurs de nombres aléatoires cryptographiquement sûrs.

   Ce sont des **PRNG** mais qui sont imprévisibles.

*Définitions :* 

> Une suite s est dite imprévisible si étant n bits générés Si, Si+1,... Si+n il est calculatoirement impossible de prédire Sn.

> Un chiffrement est dit "inconditionnellement sûr" ou "parfait ". S'il ne peut pas être cassé même si l'attaquant possède des ressources de calcul infinis.
>
> Claude Shannon - 1949 

Un tel chiffrement existe et s'appelle "chiffre de Vernam", ou "Masque jetable" ou "One-Time-Pad (OTP)"

Définition de l'**OTP** :

> L'OTP est un chiffrement à flot où :
>
> 1. La suite chiffrante s est générée avec avec un vrai générateur aléatoire
> 2. Chaque suite n'est utilisé qu'une seule fois.

$$
c = m \oplus s
$$



*Exemple :* 
$$
2 \ bits \\
c = 10
\\
S_1 = 00, \ S_2 = 01, \ S_3 = 10, \ S_4 = 11 \\
\\
c \oplus S_1 = 10 \\
c \oplus S_2 = 11 \\
c \oplus S_3 = 00 \\
c \oplus S_4 = 01 \\
$$

$$
c_1 = m_1 \oplus s \\
c_2 = m_2 \oplus s \\
c_1 \oplus c_2 = m_1 \oplus s \oplus m_2 \oplus s = m_1 \oplus m_2
$$

Inconvénients de l'OTP :

1. La suite chiffrante doit être aussi longue que le message a chiffré (problèmes de temps et de mémoire)
2. La suite ne peut être utilisée qu'une seule fois
3. La suite doit être transmise

```mermaid
graph LR;
    A --> B;
    B -- S --> C;
    C -- C --> D;
    E -- S --> D;
    F --> E;
    M --> C;
    
    A(K)
    B[PRNG]
    C((XOR))
    D((XOR))
    E[PRNG]
    F(K)
```

Alice et Bob doivent se mettre d'accord sur la clé secrète

**Toute la sécurité repose sur la qualité du PRNG**

Pratiquement tous les PRNG utilise de nos jours pour le chiffrement à flot sont basés sur des registres à décalage à rétroaction linéaire.

#### Registre à décalage à rétroaction linéaire (LFSR)

![https://upload.wikimedia.org/wikipedia/commons/9/99/Lfsr.gif](https://upload.wikimedia.org/wikipedia/commons/9/99/Lfsr.gif)

Ou sous forme de formule :
$$
S_{i+3} = S_{i+1} \oplus S_i \text{Pour tout } i \ge 0
$$


**Description générale d'un LFSR :**
$$
C_0, C_1 \ \ C_{n-1} \in \text{{0, 1}} \\
C_i = 0  \ \text{On XOR le bit }S_i \\
C_i = 1 \ \text{On ne XOR pas le bit }S_I 
$$
Valeurs initiales S0, S1, ..., Sm-1 :
$$
S_m = S_0C_0 \oplus S_1C_1 \oplus ... \oplus S_{n-1}C_{n-1} 
$$

$$
S_{m+i} = \oplus^{m-1}_{i=0} S_{i+j} \ C_j
$$

*Exemple :* m=4
$$
C_3 = C_2 = 0, C_1 = C_0 = 1 \\
\text{Période }= 2^4 - 1 = 15
$$


*Exemple :* m=4
$$
C_3 = C_2 = C_1 = C_0 = 1 \\\text{Période }= 5
$$



### Chiffrement par bloc

Dans ce système, le texte clair est découpé en blocs de taille fixe et chiffré bloc par bloc.

![https://upload.wikimedia.org/wikipedia/commons/a/a6/Schema_ecb.png](https://upload.wikimedia.org/wikipedia/commons/a/a6/Schema_ecb.png)

On traite donc chaque bloc l'un après l'autre.
$$
F:\{0,1\}^n * \{0,1\}^k -> \{0,1\}^h \\
(m,k) -> c=F(m,k)
$$


Deux paramètres importants :

- La taille de blocs **n** *64 bits ou 128 bits* 
- La taille de la clef **K** *80-256 bits*

La taille de la clef doit être telle qu'une recherche exhaustive ne soit pas disponible.

#### Comment construire un chiffrement par blocs ? (Construction de la fonction F)

Itérer une fonction de tour **F**, plusieurs fois.

La clé que Alice et Bob ont échangé est appelée la "clef maître"

```mermaid
graph LR;
	A --> B;
	B --> C;
	C --> D;
	D --> E;
	E --> F;
	F --> G;
	B -- R tours --> F;
	H --> B;
    H --> C;
    H --> D;
    H --> E;
    H --> F;
	
A((m))
B[F]
C[F]
D[F]
E[...]
F[F]
G((C))
H[Cadencement de clés]

	
	

```

Un algorithme de cadencement de clés est une fonction qui prend en entrée la clé maître K et produit en sortie une suite de sous clés K1, ..., Kr.

> **Exemple "simple" de cadencement de clé**
> $$
> K = (K_0,...,K_{127}) K_i \in \{0,1\} \\
> K_1 = (K_1, K_2, ..., K_{127}, K_0) \\
> K_2 = (K_2, ..., K_{127}, K_0, K_1)
> $$

**Avantages :**

1. Implémentation compacte.
2. Analyse de sécurité plus simple.

#### Comment construire la fonction de tour F ?

Principes de confusion et de diffusion (Claude Shannon)

"Communication Theory of Secrecy Systems" 1949

##### La confusion

Rendre les relations entre les bits de la clé, les bits du message clair et les bits du message chiffré le plus complexe possible.

> **Exemple :**
> $$
> \text{Clé secrète }K=(K_1, K_2, K_3, K_4) \ K_i \in \{0,1\} \\
> \text{Message clair }m=(m_1, m_2, m_3, m_4) \ m_i \in \{0,1\} \\
> \text{Message chiffré }c=(c_1, c_2, c_3, c_4) \ c_i \in \{0,1\}
> $$
>
> $$
> c_1 = m_1 \oplus m_2 \oplus  K_1 \oplus K_2 \\
> c_2 = m_2 \oplus m_3 \oplus  K_2 \oplus K_3 \\
> c_3 = m_3 \oplus m_4 \oplus  K_3 \oplus K_4 \\
> c_4 = m_4 \oplus m_3 \oplus  K_4 \oplus K_1 \\
> $$
>
> 

Ce système n'est pas bon pour le principe de confusion car il est linéaire, donc pas complexe.

Pour assurer la confusion il faut assurer la non linéarité.

En pratique, pour assurer la linéarité la plupart du temps on utilise des fonctions appelés "Boites-S"  .

> **Exemple de boite S :**
> $$
> S \{0,1\}^4 -> \{0,1\}^4
> $$
>
> | x        | 0    | 1    | 2    | 3    | 4    | 5    | 6    | 7    | 8    | 9    | 10   | 11   | 12   | 13   | 14   | 15   |
> | -------- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- |
> | **S(x)** | 8    | 6    | 7    | 9    | 3    | 12   | 10   | 15   | 13   | 1    | 14   | 4    | 0    | 11   | 5    | 2    |

##### La diffusion 

Chaque bit du texte clair et chaque bit de la clé  dovent avoir une influence sur une grande partie du texte chiffré.

>**Exemple :**
>$$
>C_0 = m_0 \oplus k_0 \\
>C_0 = m_1 \oplus k_1 \\
>C_0 = m_2 \oplus k_2 \\
>C_0 = m_3 \oplus k_3 \\
>$$
>

Pour assurer la diffusion on utilise des fonctions linéaires.

Chiffrement par produit (Claude Shannon)

Combiner la diffusion et la confusion pour assurer un chiffrement robuste

#### Réseau de Feistel

Introduit par Horst Feistel au début des années 1980.

Couper en deux le bloc de message clair m=(L_0, R_0)

![https://upload.wikimedia.org/wikipedia/commons/thumb/e/ef/R%C3%A9seau_de_feistel.png/300px-R%C3%A9seau_de_feistel.png](https://upload.wikimedia.org/wikipedia/commons/thumb/e/ef/R%C3%A9seau_de_feistel.png/300px-R%C3%A9seau_de_feistel.png)

Pour chaque tour i = 0, r = 1 faire :

- $$
  L_{i+1}=R_i \\
  $$

- $$
  R_{i+1}=L_i \oplus F(K_{i-1}, R_1)
  $$

Bloc chiffré :
$$
C=(C_0, R_0) = (L_{r+1}, R_{r+1})
$$

##### Propriété d'un réseau de Feistel

Déchiffrement avec :
$$
k=(k_1, k_2,..., k_r)
$$
est également au chiffrement avec
$$
k'=(k_r, ..., k_1)
$$


