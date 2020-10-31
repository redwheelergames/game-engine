class Asteroid extends GameObject {

    public Asteroid (Scene scene, double x, double y, int rotation, int speed) {
        super(x, y, rotation);
        Sprite sprite = new Sprite(scene, this, "asteroid_sprite_1.png");
        AsteroidController controller = new AsteroidController(scene, this, 3, speed);
        Collider collider = new Collider(scene, this, sprite.sprite.getWidth());
        this.addComponent(sprite);
        this.addComponent(controller);
        this.addComponent(collider);
    }
}