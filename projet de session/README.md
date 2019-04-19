
# Programme de vérifications des heures de formation continue par ordre professionnel
Donné un fichier d'entrée de type JSON, Ce programme effectue le calcul des heures de formation continue pour un ordre professionnel. le programme détermine si le nombre d'heure pour les activités de formation respecte les exigences de l'ordre.
Il accumule également des données statistiques sur les déclarations analysées par le programme, à toutes les exécutions. L'utilisateur peut ensuite afficher ces statistiques à l'écran ou les réinitialiser.


# Installation

La liste des technologies et versions à installer pour développer le logiciel
- Maven 3.3.9
- net.sf.json-lib 2.4
- junit5-system-exit 1.0.0
- junit jupiter 5.4.0
- org.apache.commons 1.3.2
- commons-cli 1.4

Pour utiliser le logiciel, il doit être lancé à l'aide d'une console.
Il est important d'être dans le dossier contenant le fichier FormationContinue.jar ou de spécifier le chemin lorsque la commande est lancée.

- Exemple en étant dans le dossier:
    `../equipe21$ java -jar FormationContinue.jar`

- Exemple en étant hors du dossier:
    `$ java -jar ../equipe21/FormationContinue.jar`

Pour utiliser le logiciel dans un IDE, importer le projet en tant que projet Maven, les dépendances s'occuperont de la liste des technologies nécessaire pour l'exécution du logiciel.

# Mode d'emploi
## Usage
Le logiciel est invoqué en spécifiant les arguments à la console:
- Vérification de la déclaration: `$ java -jar FormationContinue Fichier/entrée.json Fichier/sortie.json`
- Affichage des statistiques: `$ java -jar FormationContinue -S`
- Réinitialisation des statistiques: `$ java -jar FormationContinue -SR`

## Champs

Tous les champs suivants, à l'exception du champ `heures_transferees_du_cycle_precedent`, sont requis dans le fichier d'entrée. Sans quoi, les calculs seront interrompus.

N.B. Les valeurs du fichier d'entrées doivent respecter la casse indiquée ci-dessous. Toute autre valeur sera traitée comme étant invalide.

1. Prénom (`"prenom"`)
    - Le prénom du membre de l'ordre professionnel.
    - Ce champ ne peut pas être vide.
2. Nom (`"nom"`)
    - Le nom de famille du membre de l'ordre professionnel.
    - Ce champ ne peut pas être vide
3. Sexe (`"sexe"`)
    - Valeurs: Conformément à la norme ISO 5218.
        - 0: Sexe inconnu
        - 1: Homme
        - 2: Femme
4. Ordre (`"ordre"`)
    - Ordres supportés: Architectes, géologues, podiatres, psychologues.
    - Valeurs acceptées : `"architectes"`, `"géologues"`, `"podiatres"`, `"psychologues"`
        - N.B. Les valeurs entrées doivent respecter la casse ci-dessus. Toute autre valeur sera traitée comme étant invalide.
5. Cycle (`"cycle"`)
    - Période où la formation a été effectuée.
    - Diffère pour chaque ordre professionnel.
    - Valeurs acceptées: intervales entre 2 années (AAAA-AAAA)
6. Numéro de permis (`"numero_de_permis"`)
    - Contient la matricule du professionnel en formation.
    - La validation du matricule diffère pour chaque ordre.
7. Heures transférées (`"heures_transferees_du_cycle_precedent"`)
    - Les heures transférées du cycle précédent.
    - Valeurs: Seules les entiers positifs plus grands ou égaux à zéro sont acceptés.
    - Ce champ doit impérativement être dans le fichier d'entrée pour l'ordre des architectes, mais doit obligatoirement être ommis pour les autres ordres.
8. Activités (`"activite"`)
    - Liste des activités de formation.
    
## Activités
Tous les champs suivants sont requis dans le fichier d'entrée pour chacune des activités. Sans quoi, les calculs seront interrompus.

N.B. Les valeurs du fichier d'entrées doivent respecter la casse indiquée ci-dessous. Toute autre valeur sera traitée comme étant invalide.

- Description (`"description"`)
    - La description, le titre de l'activité de formation.
    - Ce champ ne peut pas être vide.
- Heures (`"heures"`)
    - la durée de l'activité en heures entières.
    - Valeurs: Seuls les entiers positifs plus grand que zéro sont acceptés
- Catégorie (`"categorie"`)
    - Valeurs Acceptées: `"cours"`, `"atelier"`, `"séminaire"`, `"colloque"`, `"conférence"`, `"lecture dirigée"`, `"présentation"`, `"groupe de discussion"`, `"projet de recherche"`, `"rédaction professionnelle"`;
        - N.B. Les valeurs entrées doivent respecter la casse ci-dessus. Toute autre valeur sera traitée comme étant invalide.
- Date (`"date"`)
    - Date de l'activité de formation.
    - La date doit suivre le format de la norme ISO 8601 (AAAA-MM-JJ)
    - Si la date se situe en dehors de l'intervalle du cycle, l'activité sera considérée comme invalide.

## Ordres


## Paramètres
- Fichier d'entrée
- Fichier sortie
- Options statistiques
`java -jar FormationContinue -S`
`java -jar FormationContinue -SR`

## Fichier d'entrée
- Extensions supportées `.json` & `.txt`
- Le fichier doit avoir une tructure JSON Valide;
- tous les champs spécifiés dans la section "Champs" ci-haut;

### Structure

### Déclarations Invalides
les déclarations invalides interrompent l'exécution du programme. 

## Statistiques
- Le nombre total de déclarations traitées.
- Le nombre total de déclarations complètes.
- Le nombre total de déclarations incomplètes ou invalides.
- Le nombre total de déclarations faites par des hommes.
- Le nombre total de déclarations faites par des femmes.
- Le nombre total de déclarations faites par des gens de sexe inconnu.
- Le nombre total d'activités dans les déclarations.
- Le nombre d'activités par catégorie.
- Le nombre total de déclarations valides et complètes par type d'ordre professionnel.
- Le nombre total de déclarations valides et incomplètes par type d'ordre professionnel.
- Le nombre de déclarations soumises avec un numéro de permis invalide.


# Annexe

## Exemple de fichier d'entrée
```
{
  "ordre": "géologues",
  "numero_de_permis": "BJ8888",
  "nom": "Bond",
  "prenom": "Jac",
  "sexe": "2",
  "cycle": "2016-2019",
  "activites": [
    {
      "description": "Séminaire sur l'architecture contemporaine",
      "heures": 40,
      "categorie": "cours",
      "date": "2016-04-07"
    },
    {
      "description": "Rédaction pour le magazine Architecture moderne",
      "categorie": "rédaction professionnelle",
      "heures": 10,
      "date": "2016-10-22"
    },
    {
      "description": "Participation à un groupe de discussion sur le partage des projets architecturaux de plus de 20 ans",
      "categorie": "groupe de discussion",
      "heures": 50,
      "date": "2016-04-01"
    },
    {
      "description": "Visite d'établissements architecturaux",
      "categorie": "voyage",
      "heures": 20,
      "date": "2016-04-02"
    }
  ]
}
```

## Exemple de fichier de sortie:
```
{
  "complet": true,
  "erreurs":   [
    "L'activité Visite d'établissements architecturaux est dans une catégorie non reconnue. Elle sera ignorée.",
  ]
}
```

