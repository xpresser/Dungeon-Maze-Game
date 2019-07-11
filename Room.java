package Codific;

public class Room {
    public Integer[] position;
    public int[] doors;
    public Boolean hasExit;
    public Monster monster;
    public Item item;

    public Room(Integer[] position, int[] doors, Boolean hasExit) {
        this.position = position;
        this.doors = doors;
        this.hasExit = hasExit;
    }


}
