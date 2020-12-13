Le projet est a ouvrir avec un IDE Java (eclipse par de préférence)

Le projet est configuré pour utiliser le fichier "sdkp2_4.txt" se trouvent dans Instance_DKP pour changer le fichier utilisé, dans Main modifier la variable fichierName avec le nom de l'instance du fichier souhaité.

/!\ les fichier idkp2_1 à idkp2_10 ne fonctionne pas, ils sont mal formé (2 espace au lieu de 1 et retour a la ligne).

Le projet est configuré pour faire 10 000 itérations, pour changer cela modifier la variable nbIteration dans Main.

exemple de temps d'éxécution en fonction du nombre d'itération:

10 000 : 3s
100 000 : 30s
1 000 000 : 5m
2 000 000 : 10m

Pour lancer le programme :

- Eclipse :

Exécuter la class "Main"
(clique droit sur "Main", "Run As", "Java application"

- .jar

1) ouvrir un inviter de commande et se mettre dans le dossier \Metaheurestique
2) taper : java -jar algo_gen.jar


Créer un .jar exécutable avec eclipse :

1) Clique droit sur le Dossier de projet (Metaheurestique) dans eclipse
2) Export
3) Runnable JAR file (dans java la catégorie java)
4) Launch configuration : Main - Metaheurestique
5) Export destination : Metaheurestique\algo_gen.jar
6) Library handing : Extract required libraries into generated JAR
7) Finish