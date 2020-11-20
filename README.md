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
    - Animations
    - Colliders
    
### Building Locally
This project was built with Java version 15.

**To compile the game-engine package, run the following command in the game-engine root directory**

```
    javac -d <destination> *.java  
```
This command will generate a folder of classes named 'game_engine' in the directory specified by destination. 

### Including game-engine in your project

In order to include game-engine in your project, either move the `game_engine` folder to your project's root directory, or to the directory specified by environment variable `CLASSPATH`.

