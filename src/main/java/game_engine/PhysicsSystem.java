package game_engine;

import java.util.ArrayList;

public class PhysicsSystem {

    private Game game;

    public PhysicsSystem(Game game) {
        this.game = game;
    }

    public void stepPhysics(){
        var colliders = this.game.sceneManager.getComponents(Collider.class);

        for (Collider colliderA: colliders) {

            ArrayList<Collider> relevantColliders;
            // If no groups are specified, check all colliders in the scene
            if (colliderA.groups.size() == 0) {
                relevantColliders = colliders;
            }
            // If groups are specified, check only colliders on GameObjects of matching group
            else {
                relevantColliders = new ArrayList<Collider> ();
                for (String groupName : colliderA.groups) {
                    for (GameObject gameObject : this.game.sceneManager.getGroup(groupName)) {
                        relevantColliders.addAll(gameObject.getComponents(Collider.class));
                    }
                }
            }

            // Check for collisions with all relevant colliders
            for (Collider colliderB : relevantColliders) {
                if (!colliderA.equals(colliderB) && colliderA.hasCollided(colliderB)) {
                    colliderA.onCollide(colliderB);
                }
            }
        }
    }

}
