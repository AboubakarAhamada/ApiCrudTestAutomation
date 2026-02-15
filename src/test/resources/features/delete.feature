Feature: Supprimer un TODO

  Background:
    Given un endpoint "/todos"

  Scenario Outline: Supprimer un TODO
    When j'envoie une requête DELETE pour l'id <id>
    Then le status code doit être 200
    And la réponse est vide

    Examples:
      | id |
      | 1  |
      | 2  |