# 🧪 API Test Automation – Cucumber + Rest Assured + JUnit 5

Ce projet est une base d’automatisation pour tester des **API REST** en utilisant :

- **Cucumber** (scénarios BDD en Gherkin)
- **Rest Assured** (tests API fluides et expressifs)
- **JUnit Platform (JUnit 5)** pour l’exécution
- **Maven** pour la gestion des dépendances et l’exécution des tests

L’objectif du projet est de fournir une structure claire, maintenable et extensible pour automatiser des tests d’API dans un style BDD.

---

## 🎯 Objectifs du projet

- Écrire des scénarios lisibles et partagés grâce au format **Gherkin**.
- Automatiser des tests API avec **Rest Assured**.
- Structurer un projet propre, organisé et facilement extensible.
- Fournir une suite Cucumber exécutable via IntelliJ ou Maven.
- Préparer les fondations pour ajouter ensuite des **Hooks**, un **ScenarioContext**, etc.

---

## 🗂️ Structure du projet
src
└── test
    ├── java
    │    ├── runners
    │    │     └── CucumberTestSuite.java        # Classe JUnit 5 pour exécuter Cucumber
    │    └── steps
    │          └── CommonSteps.java             # Step Definitions Rest Assured
    │
    └── resources
        └── features
            └── get.feature                 # Scénarios Gherkin
            └── post.feature                # Scénarios Gherkin
            └── put.feature                 # Scénarios Gherkin
            └── delete.feature              # Scénarios Gherkin
            └── update.feature              # Scénarios Gherkin

## 📦 Installation & Requirements

Java 17+
Maven 3.8+
IntelliJ (recommandé) 

Plugins :
    Cucumber et Gherkin
---

## 🚀 Exécution des tests

### 📌 Depuis IntelliJ

1. Ouvrir la classe `CucumberTestSuite`.
2. Clic droit → **Run 'CucumberTestSuite'**.
3. Les scénarios Cucumber se lancent via le moteur JUnit Platform.

---

### 📌 Depuis Maven

#### Exécuter les tests
```bash
mvn test


