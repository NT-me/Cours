# Intervalles - TME

**Sujet : ** https://www-apr.lip6.fr/~buixuan/files/daar2021/vague2alt/daar9_tme.pdf

## Exercice 1

1. Liste 1 : **Oui**, Liste 2: **Non**

2. ```pseudocode
   def isPermutation(Liste de int : L) -> bool:
   	sortedL = quickSort(L)
   	longueur = len(sortedL)
   	
   	Pour chaque i de 0 à len(sortedL)-1:
   		Si sortedL[i] == sortedL[i+1]:
   			return False
   	
   	Si sortedL[longueur] == longueur:
   		return True
   	return False
   	
   # Condition pour une permutation : pas de doublon, Longueur = min - max + 1
   ```

3.  Liste 1 : **Oui**, Liste 2: **Non**

4. ```pseudocode
   def isIntervalle(Liste de int L, Liste de int P) -> bool:
   	int i = 0
   	int c = 0
   	
   	Pour j de 0 à len(L):
   		Si L[j] == P[i]:
   			i++
   			Si i == len(L):
   				return True
   			Sinon
   				i = 0
   			
   			Si L[len(P)]-j-1 == P[c]:
   				c++
   			
   			Si c == len(P):
   				return True
   			Sinon:
   				c = 0
   			Return False
   ```

   Avec **L** la grande liste et **P** la plus petite.

## Exercice 2

 1. `[2,4,1,3,7,5,9,6,8]` et `[1,2,3,4,5,6,7,8,9]`. Liste des clusters : `{1}`, `{1,2,3,4}`, `{1,2,3,..., 9}`... `{9}`, `{5,6,7,8,9}`

 2. Vue plus haut

 3. - Polynomial : Facile
    - Quadradique : Faisable
    - Subquadradique : ???

 4. > Cas simple : P permutation, Q = `[1,2,3,...,n]`
    >
    > Idée : Utiliser $\#I = max(I)-min(I)+1 \\ \forall I=\{P[i], P[i+1],..., P[j]\}$
    >
    > **Cubique :** OK
    >
    > **Quadradique :** ???
    >
    > **Cas général :** 
    >
    > - P, Q permutations
    > - P <- P.Q^-1
    > - Q <- id

## Exercice 3

1. `P =  2 4 1 3 7 5 9 6 8`
   `Q = (1 2 3 4)(5 6 7 8 9)`
2. `P = 9 8 5 6 4 7 3 2 1`
   `Q = (1 2 3)(4)(5 6)(7)(8 9)`

## Exercice 4

1. ```pseudocode
   def articles(matrice booleenne M de n*m, int p) -> vecteur entier:
	res = []
	
   Pour i de 0 à n:
    	Si M[i][p] == 1:
    		res.append(i)
    return res

2. ```pseudocode
   def estIntervalle(M: Matrice, i: entier, j: entier, k: entier, l: entier) -> bool :
       pour x allant de i à j:
           pour p allant de k à l:
               si M[x][p] == 0:
                   return Faux
      return Vrai
   ```

![img](https://cdn.discordapp.com/attachments/903211470333050911/948176657322225664/IMG_20220301_121445.jpg)![img](https://cdn.discordapp.com/attachments/903211470333050911/948176657565491250/IMG_20220301_121505.jpg)
