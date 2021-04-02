# Algorithmie Avancée

## Le modèle client-serveur

![image-20210401234348914](C:\Users\Theo\AppData\Roaming\Typora\typora-user-images\image-20210401234348914.png)

Ce modèle peut être rapproché du modèle MVC avec :

- Les machines clients correspondants à la **vue** car ce sont elles qui accèdent à la donnée. Elles sont une multitude.
- Le serveur qui va jouer le rôle du **contrôleur** car c'est lui qui va gérer l'accès à la donnée. On peut supposer qu'il n'y en a qu'un très petit nombre, une ou deux machines dans la plupart des cas.
- La base de donnée en elle même qui va jouer le rôle du **modèle** car c'est elle qui va formatter la donnée. Le nombre de machine impliquées va dépendre : 
  - *On premise :* Deux machines en général
  - *On Cloud :* Une multitude de machine

On peut donc observer un **goulot d'étranglement** autour du serveur : c'est un passage obligé mais avec le moins de puissance disponible.



 