package model;

public class Toy implements Comparable<Toy> {
    private final int id;
    private final String description;
    private int chance;

    public Toy(int id, String description, int chance) {
        this.id = id;
        this.description = description;
        this.chance = chance;
    }

    public int getChance() {
        return chance;
    }

    public int getId() {
        return id;
    }

    public void setChance(int chance) {
        this.chance = chance;
    }

    @Override
    public String toString() {
        return "Toy{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", chance=" + chance +
                '}';
    }

    @Override
    public int compareTo(Toy o) {
        if (this.getId() > o.getId()) {
            return 1;
        } else if (this.getId() < o.getId()) {
            return -1;
        }
        return 0;
    }
}
