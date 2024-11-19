# 🌳 Citronix - Gestion de Ferme de Citrons 🍋

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.6.1-brightgreen)
![Java](https://img.shields.io/badge/Java-11-blue)
![Lombok](https://img.shields.io/badge/Lombok-1.18.22-green)
![JUnit](https://img.shields.io/badge/JUnit-5.7.0-yellow)
![Mockito](https://img.shields.io/badge/Mockito-3.11.2-orange)

## 🚀 Contexte du Projet

Le projet **Citronix** est une application de gestion pour une ferme de citrons. Elle permet aux fermiers de suivre la **production**, la **récolte**, et la **vente** des citrons. L'application vise à faciliter la gestion des fermes, des champs, des arbres, des récoltes et des ventes tout en optimisant le suivi de la productivité des arbres en fonction de leur âge.

## 🎯 Fonctionnalités Principales

### 🏡 Gestion des Fermes :
- Créer, modifier et consulter les informations d'une ferme (nom, localisation, superficie, date de création).
- Recherche multicritère (Criteria Builder).

### 🌾 Gestion des Champs :
- Associer des champs à une ferme avec une superficie définie.
- Assurer la cohérence des superficies : la somme des superficies des champs d'une ferme doit être strictement inférieure à celle de la ferme.

### 🌳 Gestion des Arbres :
- Suivre les arbres avec leur date de plantation, âge et champ d'appartenance.
- Calculer l'âge des arbres.
- Déterminer la productivité annuelle en fonction de l'âge de l'arbre :
  - 🌱 **Jeune** (< 3 ans) : 2,5 kg / saison.
  - 🌳 **Mature** (3-10 ans) : 12 kg / saison.
  - 🌳 **Vieux** (> 10 ans) : 20 kg / saison.

### 🍇 Gestion des Récoltes :
- Suivre les récoltes par saison (hiver, printemps, été, automne).
- Une seule récolte par saison (tous les 3 mois).
- Enregistrer la date de récolte et la quantité totale récoltée.

### 📊 Détail des Récoltes :
- Suivre la quantité récoltée par arbre pour une récolte donnée.
- Associer chaque détail de récolte à un arbre spécifique.

### 💰 Gestion des Ventes :
- Enregistrer les ventes avec la date, le prix unitaire, le client, et la récolte associée.
- Calcul du revenu : `Revenu = quantité * prixUnitaire`.

## 🛠️ Technologies Utilisées

- ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.6.1-brightgreen) : Création de l'API REST
- ![Java 17](https://img.shields.io/badge/Java-17-blue) : Langage principal
- ![Lombok](https://img.shields.io/badge/Lombok-1.18.22-green) : Simplification de la gestion des entités
- ![JUnit](https://img.shields.io/badge/JUnit-5.7.0-yellow) et ![Mockito](https://img.shields.io/badge/Mockito-3.11.2-orange) : Tests unitaires
- ![MapStruct](https://img.shields.io/badge/MapStruct-1.4.2.Final-lightblue) : Conversion entre entités, DTO et View Models
- ![Spring Data JPA](https://img.shields.io/badge/Spring%20Data%20JPA-2.5.1-lightblue) : Gestion de la persistance des données
- ![Spring Validation](https://img.shields.io/badge/Spring%20Validation-5.3.0-lightgreen) : Validation des données
- ![Swagger](https://img.shields.io/badge/Swagger-3.0-blue) : Documentation de l'API REST
- ![PostgreSQL](https://img.shields.io/badge/PostgreSQL-13-blueviolet) : Base de données relationnelle


## 📂 Architecture

L'application utilise une **architecture en couches** pour séparer les différentes responsabilités :
- **Controller** : Gère les requêtes HTTP.
- **Service** : Contient la logique métier.
- **Repository** : Interagit avec la base de données.
- **Entity** : Modélisation des données (avec **Lombok** et **Builder Pattern** pour simplifier la gestion des entités).

## 🔧 Fonctionnalités Clés

- **Validation des Données** : Utilisation des annotations Spring pour assurer la validation des données au niveau des entités.
- **Gestion Centralisée des Exceptions** : Tous les erreurs sont traitées de manière centralisée pour une gestion propre des erreurs.
- **Tests Unitaires** : Utilisation de **JUnit** et **Mockito** pour garantir la qualité du code.
- **MapStruct** : Conversion entre entités, DTO et View Models pour une gestion fluide des données.

## 💻 Installation

Clonez le dépôt et exécutez la commande suivante pour démarrer le projet :

```bash
git clone https://github.com/erradaoumaimaa/Citronix.git
cd citronix
./mvnw spring-boot:run
