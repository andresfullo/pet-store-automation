Feature: Pets Functionality

  @Regression
  Scenario Outline: Add new pet to the store
    When the URL is "/pet"
    Given the pet data "<name>" "<category>" "<photoUrls>" "<tags>" "<status>"
    Then a response code of <statusCode> is expected
    And response pet is consistent with data
    Examples:
      | name | category | photoUrls | tags | status | statusCode |
      | dog | 1,Dogs | https://i.natgeofe.com/n/4f5aaece-3300-41a4-b2a8-ed2708a0a27c/domestic-dog_thumb_square.jpg,https://i.pinimg.com/564x/9c/a7/ef/9ca7ef81f546b7133267b94e48bf8ab2.jpg | 0,string;1,test | available | 200 |
      | dog | 1,Dogs | string | 0,string | available | 200 |

  @Regression
  Scenario: Find pet
    When the URL is "/pet"
    Given the pet id 0
    Then a response code of 200 is expected
    And response pet is valid
    
  @Test
  Scenario Outline: Upload Image
    When the URL is "/pet"
    Given the pet id <petId> and image "<imageUrl>"
    Then a response code of 200 is expected
    Examples:
      | petId | imageUrl |
      | 1     | /Users/andresulloa/Documents/Golden-Retriever-Puppy.jpg |

