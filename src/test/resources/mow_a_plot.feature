Feature: Mow a plot
    As a automatic mower
    I want to browse the entire surface of the plot
    so that I can mow the entire plot

Background:
    Given A plot with a dimension of 5 5
    And An initial position to 3 3 "E"

Scenario: Browse a surface
    Given I get the following instructions:
        | A |
        | A |
        | D |
        | A |
        | A |
        | D |
        | A |
        | D |
        | D |
        | A |
    When I browse the entire surface of the plot
    Then The final position should be 5 1 "E"




