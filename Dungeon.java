package Codific;

public class Dungeon  {
    public Room[][] rooms;

    public Dungeon() {
        this.rooms = new Room[4][4];

        for (int y = 0; y < rooms.length; y++) {
            for (int x = 0; x < rooms[y].length; x++) {
                Integer[] position = new Integer[]{y, x};

                // The checks are made for rooms that are peripheral so that you don't get IndexOutOFBoundException.
                int topDoor = y == 0 ? 0 : (int)Math.round(Math.random() + 0.3);
                int rightDoor = x == rooms[y].length - 1 ? 0 : (int)Math.round(Math.random() + 0.3);
                int bottomDoor = y == rooms.length - 1 ? 0 : (int)Math.round(Math.random() + 0.3);
                int leftDoor = x == 0 ? 0 : (int)Math.round(Math.random() + 0.3) ;
                int[] doors = new int[]{topDoor, rightDoor, bottomDoor, leftDoor};
                Room room = new Room(position, doors, false);
                rooms[y][x] = room;
            }
        }
        this.rooms[0][1].monster = new Monster("Skeleton", 10, 2, 50);
        this.rooms[2][0].monster = new Monster("Skeleton", 10, 2, 50);
        this.rooms[2][2].monster = new Monster("Dragon", 30, 5, 100);
        this.rooms[0][1].item = new Item("Sword", 8, null);
        this.rooms[1][2].item = new Item("Shield", null, 8);
        this.rooms[3][2].hasExit = true;
    }

    public Room[][] getRooms() {
        return rooms;
    }



}

