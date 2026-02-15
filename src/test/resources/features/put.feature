Feature: Mettre à jour totalement un TODO via PUT

  Background:
    Given un endpoint "/todos"

  Scenario: Mettre à jour un TODO existant
    Given un payload JSON
      | id        | 1                      |
      | userId    | 1                      |
      | title     | Titre mis à jour (PUT) |
      | completed | true                   |
    When j'envoie une requête PUT pour l'id 1
    Then le status code doit être 200
    And le Content-Type doit être JSON
    And la réponse contient les clés
      | userId |
      | id     |
      | title  |
      | completed |
    And la réponse reflète le payload envoyé