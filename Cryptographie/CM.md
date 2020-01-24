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