interface Collider extends Component {
    public void onCollide(GameObject gameObject);
    public Vector2D<Integer> getPosition();
    public int getRadius();
}