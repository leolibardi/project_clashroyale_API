public class nextChest {

    private int index;
    private String name;


    public nextChest(String name, int index) {
        this.name = name;
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "next Chest: " +
                "Jogos restantes: " + index +
                ", name: " + name + '\'';
    }
}
