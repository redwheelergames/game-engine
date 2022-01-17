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
        var colliders = this.game.sceneManager.getComponents(Collider.class);
        colliders.removeIf(collider -> !collider.gameObject.active);
    
        for (Collider colliderA: colliders) {
            // If no groups are specified, check all colliders in the scene
            if (colliderA.groups.size() == 0) {
                for (Collider colliderB : colliders) {
                    if (!colliderA.equals(colliderB) && colliderA.hasCollided(colliderB)) {
                        colliderA.onCollide(colliderB);
                    }
                }
            }
            // If groups are specified, check only colliders on GameObjects of matching group
            else {
                for (String groupName : colliderA.groups) {
                    var activeGameObjects = this.game.sceneManager.getGroup(groupName);
                    activeGameObjects.removeIf(gameObject -> !gameObject.active);
                    for (GameObject gameObject : activeGameObjects) {
                        for (Collider colliderB : gameObject.getComponents(Collider.class)) {
                            if (!colliderA.equals(colliderB) && colliderA.hasCollided(colliderB)) {
                                colliderA.onCollide(colliderB, groupName);
                            }
                        }
                    }
                }
            }
        }
    }
}
