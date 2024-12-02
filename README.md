# 🌟 Projet de Gestion de Scolarité

## 📝 Description
Une application web conçue pour gérer efficacement les informations académiques telles que les **étudiants**, les **enseignants**, les **cours**, et les **évaluations**.  

Développée avec **J2EE**, **Spring Boot**, et **Hibernate**, cette application facilite la gestion de la scolarité en offrant une interface intuitive et des fonctionnalités robustes.

---

## 📋 Fonctionnalités
- 📚 **Gestion des étudiants** : ajout, modification, suppression, et recherche.  
- 👨‍🏫 **Gestion des enseignants** : suivi des informations personnelles et professionnelles.  
- 🏫 **Gestion des cours** : création et organisation des matières.  
- 📝 **Gestion des notes** : saisie, mise à jour et consultation des résultats.  
- 🔐 **Authentification** et gestion des rôles (administrateurs, enseignants, étudiants).  
- 📊 **Tableau de bord** pour des statistiques académiques clés.  

---

## 🚀 Technologies utilisées

### Backend
- **Java (J2EE)**  
- **Spring Boot**  
- **Hibernate** (ORM)  
- **MySQL** (base de données)  

### Frontend
- **JSP (Java Server Pages)**  

### Outils
- **Maven** ou **Gradle** (gestion des dépendances)  
- **Tomcat** (serveur d'application)  

---

## ⚙️ Installation

### Prérequis
- ☕ **Java 17** ou version supérieure  
- 🗄️ **MySQL** ou un autre SGBD relationnel  
- 📦 **Maven** ou **Gradle**  
- 🖥️ Un IDE comme **Eclipse** ou **IntelliJ IDEA**  

### Étapes d'installation
1. **Cloner le dépôt**  
   ```bash
   git clone https://github.com/Nourbenmiled/Projet-JEE.git
   cd Projet-JEE
   ```

2. **Configurer la base de données pour Spring Boot**  
   - Créez une base de données MySQL appelée `gestion_scolarite`.  
   - Modifiez le fichier `application.properties` :  
     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/gestion_scolarite
     spring.datasource.username=<votre-utilisateur>
     spring.datasource.password=<votre-mot-de-passe>
     ```

3. **Construire et lancer l'application pour Spring Boot**  
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

4. **Accéder à l'application**  
   Ouvrez votre navigateur et rendez-vous sur :  
   [http://localhost:8080](http://localhost:8080)

---

## 📂 Structure du projet Spring Boot
```plaintext
src/
├── main/
│   ├── java/          # Code source (contrôleurs, services, entités)
│   ├── resources/     # Fichiers de configuration (application.properties, etc.)
│   ├── webapp/        # Frontend (fichiers JSP, ressources statiques)
├── test/              # Tests unitaires
pom.xml                # Gestion des dépendances Maven
```

---

## 👥 Contributeurs
- **[Herman SESSOU](https://github.com/Herman1010)** – Développeur principal  
- **Équipe** : Nour Ben Miled, Alexis Loyau, Mattias Ribeiro, Stevie Mobue me Efangon  

---

## 📧 Contact
Pour toute question ou suggestion :  
📧 **Email** : <herman.sessou@etu.cyu.fr>  

---

## 📜 Licence
Ce projet est sous licence [MIT](https://opensource.org/licenses/MIT).  
