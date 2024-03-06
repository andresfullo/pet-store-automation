Feature: Store Functionality

  @Regression
  Scenario: Get Store Inventory
    When the URL is "/store/inventory"
    Given get the inventory data
    Then a response code of 200 is expected
    And the inventory data is valid

  @Regression
  Scenario Outline: Place order
    When the URL is "/store/order"
    Given the order data <petId> <quantity> "<shipDate>" "<status>" "<complete>"
    Then a response code of 200 is expected
    And response order is consistent with data
    Examples:
      | petId | quantity | shipDate | status | complete |
      | 10    | 150      | 2024-03-10T01:11:20 | approved | true |

  @Regression
  Scenario: Get order
    When the URL is "/store/order"
    Given the order id 0
    Then a response code of 200 is expected
    And response order is valid

