# DAAR - Session 2

## Duplication

### Cas liste

*Détecter si un if est utilisé 2 fois en temp O(n + max input - min input*

1. **O(|tab|)** : `Init tab = [0,0,0... 0]`
2. **O(n)** : `map(f, id)`
3. **O(n)** : `return tel qu'il existe i tant tab[i] >= 2`

### Cas graphe

```mermaid
graph LR;
A
B
C
D
E
F
G
H

A --- B
A --- C
B --- D
C --- D
D --- E
E --- F --- G
E --- G
F --- H
G --- H

```

**Definition de jumaux et vrai-jumaux :**

Soit $G=(V,E)$ un graphe.

Soit $u \neq v$ deux sommets difféents de $G$.

- $\{u,v\}$ est une paire de **vrai-jumaux** *ssi* l'ensemble de leur voisin est exactement le même.
  - $     \left\{        \begin{array}{ll}            N(u) \text{\\} \{v\} = N(v) \text{\\} {u} \\    uv \notin E       \end{array}    \right. \equiv N(u)=N(v) $
  
  - **Exemple :** B & C sont vrai jumaux
- $\{u,v\}$ est une paire de **jumaux** *ssi* leur exemble de voisins exétrieurs est le même
  - $     \left\{        \begin{array}{ll}            N(u) \text{\\} \{v\} = N(v) \text{\\} {u}      \end{array}    \right. \equiv \forall s \notin \{u,v\}, (s,u\in E \equiv s,v \in E)$
  - **Exemple :** F & G sont des jumaux 


#### Algo en $O(n^3)$ 
```
Récupérer la liste de voisins de chaque noeuds.
Retirer u et v de ces des listes
Comparer ces listes
```
#### Algo en $O(n+m)$ avec affinage de partition

**Avec un gaphe biparti :**

``` mermaid
graph LR;
a --- v
b --- v
c --- v
b --- w
b --- x
d  --- x
d --- y
d --- z
e --- y
f --- z
g --- y
```
**:warning: Entre chaque STEP on applique le pivot a P*n*.** 

```
STEP 1 :
    P1 = abcdefg
    pivot = N(v) = {a,b,c}

STEP 2 :
	P2 = abc dcfg
	pivot = N(w) = {b}
	
STEP 3 :
	P3 = ac b defg
	pivot = N(x) = {b,d}

STEP 4 :
	P4 = ac b d efg
	pivot = N(y)= {d,e,g}

STEP 5:
	P5 = ac b d eg f
	pivot = N(z) = {d,f}
 	
 	P6 = ac b d eg f
 	
```

Les vrai-jumaux sont les ensembles restant de taille 2

**Complexité :**

| STEP | Complexité  |
| ---- | ----------- |
| 1    | 3 = deg(v)  |
| 2    | 1 = deg(w)  |
| 3    | 2 = deg(x)  |
| 4    | 3 = deg (y) |
| 5    | 2 = deg(z)  |

**Total :** $\sum_{v \in V} deg(v)=O(m)$

### Cas de graphe temporel

$G_\Tau$ :

```mermaid
flowchart TB;
A0 --- W0
A0 --- N0
H0 --- F0

W1 --- N1
H1 --- F1

A2 --- W2
W2 --- N2
W2 --- H2
N2 --- H2
H2 --- F2

A3 --- W3
A3 --- N3
W3 --- N3
W3 --- H3
N3 --- H3
H3 --- F3

W4 --- N4
H4 --- F4

W5 --- N5
H5 --- F5

W6 --- N6
H6 --- F6
	subgraph A
	A0
	A1
	A2
	A3
	A4
	A5
	A6
	end
	subgraph W
	W0
	W1
	W2
	W3
	W4
	W5
	W6
	end
	subgraph N
	N0
	N1
	N2
	N3
	N4
	N5
	N6
	end
	subgraph H
	H0
	H1
	H2
	H3
	H4
	H5
	H6
	end
	subgraph F
	F0
	F1
	F2
	F3
	F4
	F5
	F6
	end
```

**Définition jumaux éternels et $\Delta$-jumaux :**

- $\{u,v\}$ jumaux éternals *ssi* $\forall i, \{u,v\}$ jumaux dans $G_\Tau $
- Pour $\Delta \in N,\{u,v\}$ $\Delta$-jumaux *ssi* $\exist t$ tel que $\{u,v\}$ jumaux dans $G_i \forall t \le i < t + \Delta$

## Intervalles

### TME

[Intervalles.md](./Session 2 - TME1/Intervalles.md)

### Rappels intervalles dans $\mathbb{R}/\mathbb{N}$

- $\cap$ : Deux intervalles qui s'intersectent
- $\cup$ : Union de deux intervalles
- $\text{ \\ }$ : Privé de 

![Schéma des rappels d'intervalles - photos du cours](https://cdn.discordapp.com/attachments/828925501719838730/948129570400403476/IMG_20220301_090800.jpg)

### Module (intervalles de permutation, clusters de zéro...)

**Exemple des gènes :**

| Nom de l'espèce | Gènes |      |      |      |      |      |      |      |      |
| --------------- | ----- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- |
| ID              | 1     | 2    | 3    | 4    | 5    | 6    | 7    | 8    | 9    |
| P               | 9     | 8    | 5    | 6    | 4    | 7    | 3    | 2    | 1    |

**Intervalles communs :**

- `{1,2,3}`
- `{1,2}`
- `{2,3}`
- `{1, ..., 7}`
- `{1, ..., 8}`
- `{1, ..., 9}`
- `{9,8}`
- `{5,6}`
- `{4,5,6}`
- `{4, ..., 7}`
- `{4, ..., 9}`

**V = `{1,2,3,... 9}`** :

```mermaid
graph TD;
A((L))
B((S))
C((S))
D((L))
E((//))
F((//))

A --> E
A --> F --> B --> C
A --> D

D --> 1
D --> 2
D --> 3

F --> 7

E --> 8
E --> 9

C --> 5
C --> 6
```

>L : Linéaire
>
>S : ???
>
>// : Non typé

### Biclique

**Exemple :**

| *ID article \ Id propriété*   | ailes | Froid | Sucré | Goût fruité |
| ----------------------------- | ----- | ----- | ----- | ----------- |
| **Poulet**                    | 1     | 1     | 0     | 0           |
| **Canard congelé à l'orange** | 1     | 1     | 1     | 1           |
| **Glace vanille**             | 0     | 1     | 1     | 0           |
| **Bonbon**                    | 0     | 0     | 1     | 1           |
| **Confiture**                 | 0     | 0     | 1     | 1           |

Ce tableau nous donne les aliments en fonctions de prpriétés arbitaire. Cela permet de créer un graphe bipartie avec d'un côté les aliments et de l'autre les propriétés :

```mermaid
graph RL;
subgraph Articles
1[Poulet]
2[Canard]
3[Glace]
4[Bonbon]
5[Confiture]
end

subgraph Propriétés
A[Ailes]
B[Froid]
C[Sucré]
D[Goût fruité]
end

1 --- A
1 --- B

2 --- A
2 --- B
2 --- C
2 --- D

3 --- B
3 --- C

4 --- C
4 --- D

5 --- C
5 --- D

```

