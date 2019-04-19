##

GUIDE DE STYLE ET DE NOMENCLATURE
(Mars 2019)


***NORMES DE CODIFICATION***

Le code suivra le format par défaut du *beautifier* inclus dans l'IDE utilisé par l'équipe: IntelliJ.

De plus, la casse des identifiant devra suivre les conventions JAVA:
- Aucune espace dans les noms.
- Le nom ne devrait jamais commencer par un caractère spécial ou des chiffres, mais seulement par des lettres.
- Les noms de classes commencent par une lettre majuscule et les mots suivants par une majuscule (PascalCase).
- Les noms d'interfaces commencent par une lettre majuscule.
- Les noms de méthodes commencent par une lettre minuscule et les mots suivants par une majuscule (CamelCase).
- Les noms de variables commencent par une lettre minuscule et les mots suivants par une majuscule (CamelCase).
- Un nom de variable doit être plus long qu'un seul caractère, habituellement au moins un mot.
- Les noms de constantes sont entièrement en majuscules et les mots suivants sont séparés par des "_".
- Les noms de packages commencent toujours par une lettre minuscule et les mots suivants sont séparés par des ".".


***NOMENCLATURE***

**Langue**

Les noms de classes, d'interfaces, de méthodes, de variables, et de packages seront en anglais.

Par contre, l'entièreté des commentaires ainsi que la JavaDoc et toute autre documentation ne sera écrite qu'en français.

----------------------

**Commentaires**

Les commentaires placés directement dans le code devraient agrémenter la JavaDoc lorsque celle-ci n'est pas suffisante pour bien expliquer le fonctionnement du code en question.
Ils peuvent aussi indiquer des fonctionnalités manquantes ou des aspects du code à améliorer.
Ils sont toujours sur une ligne séparée du code.
Ils doivent être courts et précis.

----------------------

**Choix des noms de classes**

Un nom de classe doit exprimer au mieux le concept que celle-ci représente.
Celui-ci devrait habituellement être un nom et non un verbe.
Il est largement laissé à la discrétion du développeur.

--------Exemple-------

public class Psychologist {

//Cette classe représente l'ordre professionel des psychologues.

}

----------------------


**Choix des noms d'interfaces**

Un nom d'interface consistera en un nom qui décrit l'utilité de l'interface.

--------Exemple-------

public interface Validation {

//Cette interface contient des méthodes qui doivent être implémentées pour effectuer la validation d'un objet .

}

----------------------


**Choix des noms de méthodes**

Un nom de méthode devra contenir un verbe comme préfixe indiquant l'action effectuée par celle-ci.
Un nom de méthode devra également avoir l'objet de cette action comme suffixe.
Le suffixe peut également être un terme qui représente l'objet de cette action.

--------Exemple-------

public int getTransferredHours() {

 return this.TrasferredHours; //Retourne les heures transférées d'un cycle précédent.

}

----------------------


**Choix des noms de variables**

Un nom de variable devrait représenter au mieux sont contenu.
Pour ce faire, elle devrait utiliser le moins d'acronymes possible.
Un tableau devrait avoir son type (ex: List, Array, File,...) comme préfixe ainsi que son contenu comme suffixe.


--------Exemple-------

ArrayList<String> lastNamesList = new ArrayList<String>(); //ArrayList contenant des noms de famille.

Architect architect20162018 = new architect("2016-2018") //Objet Architect pour le cycle 2016-2018.

----------------------

**Variables locales**

La déclaration des variables locales se fait le plus proche possible de leur utilisation.

----------------------

**Ordre d'apparition des éléments**

- Une classe débute toujours par la déclaration des variables de classe qui sont suivies, le cas échéant, par le constructeur.
- Les méthodes déclarées "public" sont, de préférence, les plus haut dans la classe.
- Une certaine flexibilité est permise.
Les méthodes privées appelées par une méthode publique peuvent suivre directement une méthode publique afin d'assurer une meilleure lisibilité, et ce, même si des méthodes publiques ayant d'autres usages suivent ces méthodes privées.

----------------------

**Espacement et accolades**

- Lorsqu'un élément est passé en paramètre, il n'y a pas d'espace entre les parenthèses et l'élément qu'elles contiennent.
- Dans la signature d'une méthode, il n'y a pas d'espace entre le nom de la méthode et ses paramètres entre parenthèses.
Il y a une espace avant l'accolade ouvrante qui est toujours sur la même ligne que la signature de la méthode ou le nom d'une classe.
- L'accolade fermante est le seul élément de la dernière ligne d'une méthode ou d'une classe.
- Ces normes sur la position des accolades s'appliquent aussi à tout bloc inclus dans une méthode.
- Le signe "=" lors de déclaration de variables est précédé d'une espace et suivie d'une espace.
- L'identation respecte les normes de Java et fait 4 espaces.
- Une ligne vide sépare une méthode de la méthode suivante (ou de la JavaDoc de la méthode suivante).
- Une ligne de code ne devrait pas dépasser la largeur de l'écran lorsque l'éditeur est la seule fenêtre ouverte (police de référence: Monospaced, taille: 12)