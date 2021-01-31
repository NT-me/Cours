# Présentation du projet CPA

Dans l'architecture MVC le modèle est chargé de gérer les données en elles même. C'est à dire que c'est ce composant de l'architecture qui va sauvegarder l'état des objets et décrire l'intégralités des objets composant l'univers du logiciel, mais il est aussi chargé d'effectuer les vérifications sur les données et gère la logique qu'on peut y appliquer. Par exemple les limites de déplacement de certain éléments. 

Le modèle est le seul composant parfaitement indépendant du reste de l'architecture. Dans la mesure où un modèle est indépendant de la vue comme du contrôleur, un même modèle peut servir a plusieurs vues.

C'est donc le modèle qui va recevoir des messages des deux autres composants et se charger de leur communiquer l'état des objets, pas exemple leurs positions ou leur taille et c'est aussi lui qui va donc recevoir les modifications a appliquer aux objets d'ores et déjà instanciés.

## Notre modèle

Dans notre modèle nous décrivons l'ensemble des objets composants le jeu :

- Les palettes rouges et bleues

- Toutes les balles (de création, de match, de destruction)

- Les briques

- Murs

- Buts

- Certains autres éléments pas forcément visible comme la matrice de placement des briques qui permet d'éviter leur chevauchement.

Pour assurer l'interaction des autres composants avec les objets et les instances présentes dans data un certain nombre de méthodes sont mises a disposition. Les getter et les setters classique mais aussi certaines autres permettant l'accès ou la modification simplifiés a des données en particulier. 

## Un cas précis

Pour illustrer mon propos je vais m'appuyer sur un cas spécifique : la palette.

Les palettes sont donc les deux éléments dirigés par le joueurs a chaque extrémités du terrain, elles vont avoir un certain nombre d'attributs spécifique :

- Longueur

- Largeur

- Joueur

- PV

- Cooldown de respawn

Le fichier source décrivant cette classe fait partie intégrante du modèle, et il sert a instancié deux palettes, celle de chacun des joueurs. Chacune d'entre elle va alors bénéficier de son lot de setters et getters spécifique pour les récupérer intégralement comme n'importe quel attributs de classe. Mais aussi de setters permettant un accès simplifié a meurs positions. Cela permet d'éviter les copies et réaffectations inutiles dans les autres composant comme par exemple le contrôleur.

# Démo

- Echange de balle entre les palettes
  - Donne de l'impulsion
- Rebond sur les murs
- But
  - Balle qui repart du milieu
- Balle créatrice qui créer une brique en partant de la palette rouge

# Conclusion

- Nous avons mené a bien le projet et répondu au cahier des charges du client
- Nous avons pu sur un temps particulièrement restreint fournir un logiciel dans un état de développement avancé et utilisable grâce a une implication totale de l'équipe durant ces trois jours. Et une répartition efficace des tâches.
- Les efforts vont se perpétuer sur ce week end pour fournir un code terminé à la date de livraison (Balle destructrice)

