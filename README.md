# game-engine
### Description
This project is a basic gameobject/component game engine built with [Java](https://www.oracle.com/java/technologies/javase-downloads.html). 

Built in features:
- Timing
- Double Buffered rendering
- Keyboard and mouse inputs
- Rotation and translation
- Vector system
- GameObject communication via getComponent
- Game window management
- Built in Components
    - Sprites
    - Colliders

### Demo Game
`DemoGame.java` is a small demonstration of the capabilities of the game engine.

The following files are part of the demo and not built in to the engine itself:
- `PlayerController.java` implements Component
- `PlayerCollider.java` extends Collider
- `AsteroidController.java` implements Component
- `Asteroid.java` extends GameObject
    
### Running Locally
This project was built with Java version 15.

To compile the demo game run the following command 

```
    javac *.java
```

To run run the following command 

```
    java DemoGame
```
