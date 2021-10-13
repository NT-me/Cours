# Changements impliqués par Cindy

## EPIC

Les EPIC changent un peu :

- E6 passent de 2 jours a 3 car ce n'est plus une spécialiste qui s'en charge
- E7 passe de 2 jours à 3 jours pour qu'Alice est le temps de relire la documentation écrite par Eddy

```mermaid
gantt
    dateFormat  DD-MM-YYYY
    title       Diagramme de Gantt des EPIC
    excludes    weekends


    section Sprint 1
    E1 : E1, 29-09-2026, 2d
    E2 : E2, after E1, 12h

    section Sprint 2 
    E3 : E3, after E2, 4d
    E4 : E4, after E2, 6d
    E5 : E5, after E3, 6d
    E6 : E6, after E5, 3d
    
    section Sprint 3
    E7 : E7, after E5, 3d
    E8 : E8, after E6, 4d


```

## RH

Pour rappel les correspondances entre les indicatifs des tâches et leur noms réels est trouvable dans la backlog accompagnant mon dernier mail.


```mermaid
gantt
    dateFormat  DD-MM-YYYY
    title       Diagramme de Gantt RH
    excludes    weekends
    
    section Alice
    T1E1 : T1E1, 29-09-2026, 12h
    T2E1 : T2E1, after T1E1, 12h
    T3E1 : T3E1, after T2E1, 1d
    T4E2 : T4E2, after T3E1, 12h
    T5E3 : T5E3, after T4E2, 1d
    T7E4 : T7E4_A, after T5E3, 1d
    T10E3 : T10E3, after T7E4_A, 12h
    T11E3 : T11E3, after T10E3, 1d
    T8E4 : T8E4, after T11E3, 12h
    T13E5 : T13E5_A, after T13E5_B, 4d
    T14E5 : T14E5, after T13E5_A, 1d
    T15E6 : T15E6_A, after T14E5, 2d
    T17E7 : T17E7_A, after T15E6_A, 1d
    T18E8 : T18E8_A, after T17E7_A, 3d
    T19E8 : T19E8_A, after T18E8_B, 1d
    
    section Bob
    T1E1 : T1E1, 29-09-2026, 12h
    T3E1 : T3E1, after T2E1, 1d
    T7E4 : T7E4, after T4E2, 3d
    T13E5 : T13E5_B, after T12E3, 1d
    T18E8 : T18E8_B, after T17E7_A, 3d
    T15E6 : T15E6, after T14E5, 1d
    
    
    section Cindy (abs)
    T1E1 : done, T1E1, 29-09-2026, 12h
    T2E1 : done, T2E1, after T1E1, 12h
    T3E1 : done, T3E1, after T2E1, 1d
    T10E3 : done, T10E3, after T7E4_A, 12h
    T15E6 : crit, T15E6, after T14E5, 1d
    T16E6 : crit, T16E6, after T15E6, 1d
    
    section David
    T1E1 : T1E1, 29-09-2026, 12h
    T3E1 : T3E1, after T2E1, 1d
    T6E3 : T6E3, after T5E3, 12h
    T9E4 : T9E4, after T8E4, 1d
    T12E3 : T12E3, after T11E3, 1d
    T14E5 : T14E5, after T13E5_A, 1d
    T16E6 : T16E6, after T15E6_A, 1d
    T19E8 : T19E8_D, after T18E8_B, 1d
    
    section Eddy
    T1E1 : T1E1, 29-09-2026, 12h
    T3E1 : T3E1, after T2E1, 1d
    T4E2 : T4E2, after T3E1, 12h
    T5E3 : T5E3, after T4E2, 1d
    T6E3 : T6E3, after T5E3, 12h
    T8E4 : T8E4, after T7E4, 2d
    T9E4 : T9E4, after T8E4, 1d
    T10E3 : T10E3, after T7E4_A, 12h
    T13E5 : T13E5_E, after T12E3, 5d
	T14E5 : T14E5, after T13E5_A, 1d
	T17E7 : T17E7, after T14E5, 2d
	T18E8 : T18E8_E, after T17E7_A, 3d
	T19E8 : T19E8_E, after T18E8_E, 1d

```

Visisble en gris les tâches où la présence de Cindy n'était pas critique, c'est à dire toute les participations a des prises de décisions. En rouge les tâches où sa présence était critique. 

Elle sera donc efficacement remplacée par Bob et moi. Bob n'aura qu'un jour de présence en plus de façon a avoir un kickoff énergique sur le frontend. Je réduis ma présence sur l'écriture de la documentation en déléguant cette charge a Eddy, je ferais une relecture de tout cela durant une journée tout de même.

Les tests de front end ne deviendront plus que la seule charge de David qui saura se débrouiller parfaitement.

Cette organisation permet de limiter le retard en passant d'une deadline annoncée le 24/10 au 27/10.
