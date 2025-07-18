# Circus Plate Stacker Game

## Overview
Circus Plate Stacker is a single-player game where the player controls a clown who catches falling plates and stacks them. The goal is to collect three consecutive plates of the same color to make them vanish and increase the score. The game features different difficulty levels and implements several design patterns for efficient and maintainable code.
![backgr](https://github.com/user-attachments/assets/89160ecc-d205-416c-b46d-e7fb4fcf8488)

## Technical Specifications
- **Game Engine**: Built using `engine_v3.jar` library   
- **Architecture**: MVC (Model-View-Controller)  

## Features
- **Multiple Shapes**: Supports plates and bombs as falling objects  
- **Difficulty Levels**: Three levels (Easy, Hard, Expert) with varying speeds and plate counts  
- **Score System**: Earn points by collecting three consecutive same-colored plates  
- **Dynamic Object Loading**: Shapes are loaded dynamically at runtime  

## Design Patterns Implemented
1. **Singleton**: Used in the `Factory` class to ensure only one instance exists  
2. **Factory**: `Factory` class creates different game objects (plates, bombs, clown)  
3. **Iterator**: `ClassIterator` provides iteration over game objects  
4. **State**: `State`, `BarState`, and `MovingState` manage object movement states  
5. **Strategy**: `Strategy` interface with implementations for different difficulty levels  
6. **Observer**: `Observer` and `ScoreObserver` handle score updates  

## Game Components
### Model
- `BarObject`, `BombObject`, `PlateObject`, `Shape`: Game objects  
- `ClownObject`: Player-controlled character  
- `EasyStrategy`, `HardStrategy`, `ExpertStrategy`: Difficulty implementations  
- `Factory`: Object creation factory  
- `GameController`: Manages game flow and UI  

### Control
- `CircusGame`: Main game logic and world implementation  
- `State` pattern classes: Manage object movement states  
- `Observer` pattern: Handles score updates  

### View
- `NewJFrame`: Main menu for difficulty selection  

## How to Play
1. Run the game from `MyFirstGame` or `NewJFrame`  
2. Select difficulty level:  
   - **Easy**: Slow speed, 10 plates to win  
   - **Hard**: Medium speed, 8 plates to win  
   - **Expert**: Fast speed, 6 plates to win  
3. Control the clown to catch falling plates  
4. Stack three consecutive plates of the same color to earn points  
5. Avoid bombs which decrease your score  

## Game Rules
- Collect three same-colored plates in a row to score  
- Bombs decrease your score when caught  
- Game ends when:  
  - You collect the required number of plates for your difficulty level  
  - Time runs out (varies by difficulty)  
  - Score drops below zero  

