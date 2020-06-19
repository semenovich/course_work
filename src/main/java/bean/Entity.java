package bean;

public abstract class Entity {
    private int id;

    public Entity() {
    }

    public Entity(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Entity setId(int id) {
        this.id = id;
        return this;
    }
}
