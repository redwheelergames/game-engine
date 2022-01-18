package com.github.redwheelergames.gameengine;

public class Script extends Component {

    public Script (GameObject gameObject) {
        super(gameObject);
    }

    // Called once per frame
    public void update () {}

    // Called once per physics update
    public void fixedUpdate () {}

    // Called immediately after the parent game object is added to the scene
    public void start () {}

    // Called immediately after a new scene finishes loading
    public void onSceneLoad() {}

}
