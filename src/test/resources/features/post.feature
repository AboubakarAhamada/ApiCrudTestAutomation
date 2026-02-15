Feature: Créer des TODOs via POST

  Background:
    Given un endpoint "/todos"

  @post @datatable
  Scenario: Créer un TODO avec un payload défini
    Given un payload JSON
      | userId    | 1                        |
      | title     | Nouveau TODO via POST    |
      | completed | false                    |
    When j'envoie une requête POST vers "/todos"
    Then le status code doit être l'un de
      | 201 |
      | 200 |
    And le Content-Type doit être JSON
    And la réponse contient les clés
      | userId    |
      | id        |
      | title     |
      | completed |
    And la réponse reflète le payload envoyé

  @post @outline
  Scenario Outline: Créer plusieurs TODOs avec des valeurs variées
    Given un payload JSON
      | userId    | <userId>     |
      | title     | <title>      |
      | completed | <completed>  |
    When j'envoie une requête POST vers "/todos"
    Then le status code doit être l'un de
      | 201 |
      | 200 |
    And le Content-Type doit être JSON
    And la réponse contient les clés
      | userId    |
      | id        |
      | title     |
      | completed |
    And la réponse reflète le payload envoyé

    Examples:
      | userId | title                      | completed |
      | 1      | Création via Outline #1    | true      |
      | 2      | Création via Outline #2    | false     |
      | 3      | Données paramétrées        | true      |