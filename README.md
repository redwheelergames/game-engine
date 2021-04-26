# game-engine
### Description
game-engine is a simple game engine used to create 2D games with [Java](https://www.oracle.com/java/technologies/javase-downloads.html).
 
### Compiling and building from source
This project uses java version 16 and [Maven](https://maven.apache.org/).
In order to compile from source you must first install these dependencies. 

In order to compile or build the project you simply need to run the corresponding Maven phase in the projects root
directory.

```console
    mvn compile
```

```console
    mvn package
```

By default Maven will write these file to the project's `target` directory.

**Note running the package phase will also run the compile phase automatically and it is not necessary to run both
manually.**

### Including game-engine in your project

The latest version of the game-engine jar can be found [here](https://github.com/redwheelergames/game-engine/releases).

In order to include game-engine in your project, either download the latest released jar or build the project manually.
When compiling your game, you must add the game engine package to your classpath. You can do this either by using the `-cp` flag for `javac`
or by adding the package path to the `CLASSPATH` environment variable.

