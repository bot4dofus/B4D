# B4D
## Description

Bot4Dofus (ou Bot pour Dofus) est un bot à destination des joueurs de Dofus. Il a pour objectif d'automatiser les tâches répétitives tel que :

- Quêtes *(à venir)*
- Récoltes de ressources *(à venir)*
- Mise en vente/banque de ressources *(à venir)*
- Reproduction des dragodindes *(à venir)*
- Almanax *(à venir)*

Ce bot est capable de :

- Se déplacer en utilisant tous les moyens de transport
- Communiquer et échanger de l'argent avec d'autres joueurs
- Gérer des combats simples *(à venir)*
- Connecter tous vos comptes simultanéments *(à venir)*

## Fonctionnement
**Analyse de l'environnement**

Ce bot lis les trames descendantes pour analyser les messages du chat.
Mais la plus grande parti des traitements restent graphiques : traitement de pixels, images, textes, couleurs, reconnaissance de caractères...

**Interaction avec l'environnement**

Contrairement à beaucoup d'autres bots, B4D n'envoi pas de trames ethernet pour fonctionner.
Cela à l'énorme avantage d'être indétectable par les serveurs dofus. Ce bot simule des appuis clavier et des cliques de souris aux bonnes coordonnées pour intéragir avec l'environnement.

**Utilisation des librairies suivantes :**

- Opencv (Traitement d'image)
- Tesseract (Reconnaissance Optique de Caractères)
- Jgrapht (A* algorithme)
- Jpcap (Lecture des trames entrantes)
- Google Drive & Google Sheet API
- Junit (Tests)

# Utilisation
## Configuration
**Ecran**

B4D étant graphique, vous devrez configurer le bot en fonction de votre écran.
Vous pouvez positionner la fenêtre de jeu comme vous le souhaitez avant de configurer celui-ci mais vous serez obligé de la repositionner ainsi à chaque utilisation du bot.

Rendez-vous dans l'onglet `Réglages` et spécifiez toutes les coordonnées.

**Personnages**

Toujours pour des raisons graphiques, vous devez renseigner les personnages sur lequels vous voulez jouer ainsi que la position des potions de transport.

Rendez-vous dans l'onglet `Personnages`, ajoutez un personnage et spécifiez un maximum de coordonnées.

NB : Le nom de compte et mot de passe sont utilisés pour connecter automatiquement tous vos comptes.
Si vous n'avez pas confiance, vous pouvez entrer un faux nom de compte et un faux mot de passe.
Tous les programmes (sauf celui de connexion automatique) fonctionneront correctement.
**Le serveur et le pseudo du personnage sont indispensables au bon fonctionnement de B4D.**

## Tutoriels

Afin de vérifier la bonne configuration du bot, vous pouvez lancer les tutoriaux dans l'onglet `Programmes`.

## Utilisation

[Expliquer ici les paramètres de lancement d'un programme]

## Rapport d'erreurs

B4D inclu un système de gestion des erreurs permettant de signaler aux développeurs d'éventuels problèmes. Lorsqu'un message d'erreur apparait, choisissez "Envoyer le rapport d'erreur" pour signaler celle-ci.

# Contribution
## Prérequis 

- Télécharger et installer Eclipse
- Télécharger et installer Git

## Nouveaux programmes

[Expliquer comment créer un nouveau programme et les différents arguments du constructeur]

Pour prendre en mains les API de B4D, il est recommander de regarder le code des tutoriaux [lien].

## Développement

[Modalités des objectifs, tâches, discord, communication, pull request, branches...]
