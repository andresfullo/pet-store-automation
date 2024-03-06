Feature: User Functionality

    @Regression
    Scenario Outline: Create User
        When the URL is "<userURL>"
        Given a user is created with "<userName>", "<firstName>", "<lastName>", "<email>", "<password>", "<phone>", <userStatus>
        Then a response code of <statusCode> is expected
        And the response user is valid
        Examples:
            |userURL| statusCode | userName | firstName | lastName | email | password | phone | userStatus |
            |/user| 200        | andrew1 | Andrew        | Anderson | andrew1234@gmail.com | 123qweasd | +57 12345 | 1 |
    
    @Regression
    Scenario Outline: User logs in
        When the URL is "<userURL>"
        Given the user data "<username>" "<password>"
        Then a response code of <statusCode> is expected
        Examples:
            | userURL | username | password | statusCode |
            | /user/login | andrew | 123qweasd | 200     |
            | /user/login | asdfgh | asdadkjl  | 401 |

    @Regression
    Scenario: Get user information
        When the URL is "/user"
        Given the username "andrew"
        And user is searched
        Then a response code of 200 is expected
        And the user info should be valid

    @Regression
    Scenario: Update user
        When the URL is "/user"
        Given the username "andrew"
        And update the user data "andrewAnderson" "Andrew" "Anderson" "andrewAnderson@gmail.com" "qwe123asd" "+57 300 1234567" 1
        Then a response code of 200 is expected
        And the response user is valid

    @Regression
    Scenario: Delete User
        When the URL is "/user"
        Given the username "andrew"
        And user is deleted
        Then a response code of 200 is expected
        When the URL is "/user"
        Given the username "andrew"
        And user is searched
        Then a response code of 404 is expected




