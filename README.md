# POKER-HANDS challenge

## Table of Contents

1. Introduction
2. Assumptions
3. Compilation and run

## 1. Introduction

This is the challenge of the poker hands, please take as it is

## 2. Assumptions

- **Number of players**: We assume we can have between 2 and 9 players
- **Number of cards**: We assume every player has exactly 5 cards
- **Cards format**: The cards format is as follows: suits [Clubs(C), Diamonds(D), Hearts(H), Spades(S)] and card raks are [Two(2), Three(3), Four(4), Five(5), Six(6), Seven(7), Eight(8), Nine(9), Ten(T), Jack(J), Queen(Q), King(K), Ace(A)]
- **Cards order**: We assume cards can be passed as unordered

## 3. Compilation and run

- This project has been compiled and packaged by Apache Maven 3.6.3 on "11.0.12" 2021-07-20 LTS over a Win 10 PC, just run 
```
mvn clean package
```

Then simply run the game as follows:

```
java -jar target/poker-hands-0.0.0-SNAPSHOT.jar "<player1 cards>" "<player2 cards>" "<player3 cards>" ... "<player-n cards>"
```

Here is an example

```
java -jar target/poker-hands-0.0.0-SNAPSHOT.jar "2D 9C AS AH AC" "3D 6D 7D TD QD" "2C 5C 7C 8S QH"
```

Have fun!





