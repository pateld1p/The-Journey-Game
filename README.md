# TheJourney Game - Object-Oriented Graphics Programming

## Overview

TheJourney is a semester-long project focused on building an interactive game using object-oriented programming principles. Throughout the assignments, the game is incrementally developed with new features such as class associations, interfaces, GUIs, animation, and interactive elements.

This game simulates an ant’s journey through a game world where it must avoid spiders and reach food stations. The project is implemented using the Codename One (CN1) framework in Java and follows a Model-View-Controller (MVC) architecture.

---

## Table of Contents
1. [Assignment 1: Class Associations & Interfaces](#assignment-1-class-associations--interfaces)
2. [Assignment 2: Design Patterns & GUIs](#assignment-2-design-patterns--guis)
3. [Assignment 3: Interactive Graphics & Animation](#assignment-3-interactive-graphics--animation)
4. [Installation](#installation)
5. [How to Run](#how-to-run)
6. [Deliverables](#deliverables)

---

## Assignment 1: Class Associations & Interfaces

### Objective
The goal of this assignment is to develop a class hierarchy and initial control structure for TheJourney game. The game world is text-based, allowing the player to control an ant's movement and interaction with other game objects such as spiders, flags, and food stations. The game operates entirely through keyboard commands.

### Key Features
- Implemented core game objects like `Ant`, `Spider`, `Flag`, and `FoodStation`.
- Introduced abstract classes for `GameObject`, `Fixed`, and `Movable` objects.
- Added keyboard-based control for user input.
- Used a text-based simulation of the game world.

---

## Assignment 2: Design Patterns & GUIs

### Objective
In this assignment, the focus is on incorporating design patterns and developing a graphical user interface (GUI) for the game. The game's control is now managed via buttons, key bindings, and a side menu, transitioning from text input to GUI-based interaction.

### Key Features
- **Model-View-Controller (MVC) Architecture**: Implemented the game using the MVC pattern, separating the game’s data (Model), the interface (View), and the logic (Controller).
- **Design Patterns**:
  - **Observer Pattern**: Views such as `ScoreView` and `MapView` observe changes in the game state.
  - **Command Pattern**: Player actions are encapsulated as command objects.
  - **Singleton Pattern**: Ensured that only one instance of `Ant` exists in the game.
- GUI Elements:
  - Side menu for commands like “Accelerate” and “Exit.”
  - `ScoreView` and `MapView` components displaying game state and map, respectively.

---

## Assignment 3: Interactive Graphics & Animation

### Objective
The game becomes fully interactive in this assignment. New features include graphical object representations, animation, collision detection, and the ability to pause and edit game objects.

### Key Features
- **Graphical Game Map**: The `MapView` now graphically displays the game world with ants, spiders, flags, and food stations.
- **Timer-Driven Animation**: Movable objects (like the ant and spiders) animate based on a timer.
- **Collision Detection**: Dynamic collisions are detected and handled between objects like the ant, spiders, and food stations.
- **Sound Effects**: Integrated sound using JavaFX for game events (collisions, background music, etc.).
- **Play and Pause Modes**: Added functionality to pause the game, stop animations, and allow object selection and repositioning during pause mode.

---

## Installation

### Prerequisites
- Java 17 or higher installed.
- Codename One (CN1) installed on your IDE (Eclipse recommended).
- JavaFX SDK 11.0.2 installed for sound support.

### Steps
1. Clone the repository.
2. Ensure the JavaFX SDK is installed and configured in your IDE for sound support.
3. Open the project in your IDE (Eclipse).
4. Build and run the project using the respective JAR files.

