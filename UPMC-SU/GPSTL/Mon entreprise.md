# Mon entreprise

**Nom : ** Peoplespheres

## Organigramme

```mermaid
graph LR;
A[Direction]
B(Sales & Marketing)
C(Customer Success)
D(Produit)
E((Technique - N+1 : CTO))
F(Opération)

B1(Marketing)
B2(Vente direct)
B3(Vente indirect)
B4(Solution consultant)

E1(Développeurs Data)
E2(Développeurs PSPS)
E3>Prestataire externe : Noveo]
E4((R&D - Moi))

F1(Consultants)
F2(Support)
F3(Qualité & Méthodes)
F4(Contenus)
F5(Partenaires)

A --> B
A --> C
A --> D
A ==> E
A --> F

B --> B1
B --> B2
B --> B3
B --> B4

E --> E1
E --> E2
E -..-> E3
E ==> E4

F --> F1
F --> F2
F --> F3
F --> F4
F --> F5

 classDef me fill:#f96,stroke:#333,stroke-width:4px;
 classDef n1 fill:#f76,stroke:#333,stroke-width:4px;
 class E4 me
 class E n1
```

##  Produit

Plateforme modulaire de SIRH permettant de centraliser l'ensemble des logiciels RH d'une entreprise.

## Client

Entreprises | B2B

## Sponsor

Le CTO