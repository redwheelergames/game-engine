package game_engine;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PhysicsSystem {

    private Game game;

    public PhysicsSystem(Game game) {
        this.game = game;
    }

    public void stepPhysics(){
        var gameObjects = this.game.sceneManager.getComponents(Collider.class);

        // If no groups are specified, check all gameObjects in the scene
        for(GameObject gameobjectA: gameObjects) {
            for(Collider colliderA: gameobjectA.getComponents(Collider.class)){
                if (colliderA.groups.size() == 0) {
                    for (GameObject gameObjectB : gameObjects) {
                        if(gameobjectA.equals(gameObjectB)){
                            continue;
                        }

                        for (Collider colliderB : gameObjectB.getComponents(Collider.class)) {
                            if (colliderA.hasCollided(colliderB)) {
                                colliderA.onCollide(colliderB);
                            }
                        }
                    }
                }

                // If groups are specified
                else {
                    // Iterate through all specified groups
                    for (String groupName : colliderA.groups) {
                        for (GameObject gameObjectB : this.game.sceneManager.getGroup(groupName)) {
                            if(gameobjectA.equals(gameObjectB)){
                                continue;
                            }

                            // Get all colliders on gameObject
                            for (Collider colliderB : gameObjectB.getComponents(Collider.class)) {
                                if (colliderA.hasCollided(colliderB)) {
                                    colliderA.onCollide(colliderB, groupName);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
