Feature: GET un TODO

  Background:
    Given un endpoint "/todos"

  Scenario Outline: Récupérer un TODO avec son identifiant
    When j'envoie une requête GET pour l'id <id>
    Then le status code doit être 200
    And le Content-Type doit être JSON
    And la réponse contient les clés
      | userId |
      | id     |
      | title  |
      | completed |

    Examples:
      | id |
      | 1  |
      | 2  |
      | 3  |