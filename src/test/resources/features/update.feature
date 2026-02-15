Feature: Mettre à jour partiellement un TODO via PATCH

  Background:
    Given un endpoint "/todos"

  Scenario Outline: Modifier partiellement un TODO
    Given un payload JSON
      | title     | <title>     |
      | completed | <completed> |
    When j'envoie une requête PATCH pour l'id <id>
    Then le status code doit être 200
    And le Content-Type doit être JSON
    And la réponse contient les clés
      | userId |
      | id     |
      | title  |
      | completed |
    And la réponse reflète le payload envoyé

    Examples:
      | id | title                    | completed |
      | 1  | MAJ partielle via patch  | true      |
      | 2  | Update courte            | false     |