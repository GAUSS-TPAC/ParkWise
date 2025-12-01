cat > PROJECT_CONTEXT.md <<'EOF'
# Contexte du projet

- Dépôt: git@github.com:GAUSS-TPAC/smartParking.git
- Branche courante: main
- Remote: origin
- Utilisateur: GAUSS-TPAC
- OS développeur: Linux
- IDE: IntelliJ IDEA 2025.2.4
- Stack: Java, Spring Boot, Maven

## Objectif
il s'agit d'une application de gestion de parking intelligent permettant de suivre les places disponibles, gérer les réservations et optimiser l'utilisation de l'espace.

on va d'implementer les fonctionnalités suivantes:
- transfert des image (numero d'immatriculation) vers le serveur qui se chargera du traitement
- integration des API de paiement d'Orange et MTN Money
- tableau de bord pour les administrateurs du parking
- notifications en temps réel pour les utilisateurs
- actualisatio des places de parking
- 
## Instructions recommandées
- Mettre à jour ce fichier à chaque changement de configuration ou procédure.
- Inclure commandes importantes, variables d'environnement, et étapes d'exécution.
- Indiquer la personne/responsable des décisions (si applicable).

## Exemple rapide de contenu à ajouter
- Comment lancer l'application en local
- Commandes de build et tests
- Endpoints importants pour le debugging
- Liens utiles (CI, issues, wiki)

maintenant qu etu connais ce que je veux pour cette fonctionnalité, aide moi à l'implementer (fonctionnalité de transfert de donnée (ici se sont les image des numero d'immatriculation))