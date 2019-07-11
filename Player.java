package Codific;

import java.util.ArrayList;
import java.util.Scanner;

public class Player {
    public String name;
    public Integer health;
    public Integer[] position;
    public Integer damage;
    public Integer armor;
    public ArrayList<Item> backpack;
    public Integer maxBackpackCapacity;

    public Player(String name, Integer health, Integer[] position, Integer damage, Integer armor) {
        this.name = name;
        this.health = health;
        this.position = position;
        this.damage = damage;
        this.armor = armor;
        this.backpack = new ArrayList<>();
        this.maxBackpackCapacity = 2;
    }

    public void movePlayer(Dungeon dungeon) {
        Scanner scanner = new Scanner(System.in);
        Room room = dungeon.rooms[this.position[0]][this.position[1]];
        String input = "";

        if (room.doors[0] == 1) {
            System.out.println("You can move top. Type \"W\"");
        }

        if (room.doors[1] == 1) {
            System.out.println("You can move right. Type \"D\"");
        }

        if (room.doors[2] == 1) {
            System.out.println("You can move bottom. Type \"S\"");
        }

        if (room.doors[3] == 1) {
            System.out.println("You can move left. Type \"A\"");
        }

        do {
            input = scanner.nextLine().trim().toUpperCase();
            if ("W".equals(input) && room.doors[0] == 1) {
                this.position[0] -= 1;

            } else if ("D".equals(input) && room.doors[1] == 1) {
                this.position[1] += 1;

            } else if ("S".equals(input) && room.doors[2] == 1) {
                this.position[0] += 1;

            } else if ("A".equals(input) && room.doors[3] == 1) {
                this.position[0] -= 1;

            } else {
                System.out.println("Invalid move. Please type a valid move.");

            }

        } while (!input.equals("W") && !input.equals("D") && !input.equals("S") && !input.equals("A"));

    }

    public boolean fightMonster(Dungeon dungeon) {
        Room currentRoom = dungeon.rooms[this.position[0]][this.position[1]];
        Monster monster = currentRoom.monster;

        if (monster == null) {
            return true;
        }

        while (this.health > 0) {
            // Player attacks the monster
            if (monster.armor - this.damage <= 0) {
                monster.health -= this.damage - monster.armor;
                System.out.printf("You have dealt %d damage to %s.", this.damage - monster.armor, this.name);
                if (monster.health < 0) {
                    System.out.printf("You have slain the %s!", monster.type);
                    return true;
                }
            }

            // Monster attacks the Player;
            if (this.armor - monster.damage <= 0) {
                this.health -= monster.damage - this.armor;
                System.out.printf("The %s has dealt %d damage to you.", monster.type, monster.damage - this.armor);
                if (this.health < 0) {
                    System.out.println("GAME OVER!/nYou have been defeated..");
                    return false;
                }
            }
        }
        return true;
    }

    public Boolean hasFoundExit(Dungeon dungeon) {
        Room room = dungeon.rooms[this.position[0]][this.position[1]];

        if (room.hasExit) {
            System.out.printf("Congratulations %s!%nYou have found the exit.%nYOU WON!!!", this.name);
            return true;
        }
        return false;
    }

    public void pickItem(Dungeon dungeon) {
        Room room = dungeon.rooms[this.position[0]][this.position[1]];
        Item item = room.item;

        if (item == null) {
            return;
        }

        if (this.backpack.size() >= this.maxBackpackCapacity) {
            System.out.println("Your backpack is full!");
            return;
        }
        this.backpack.add(item);
        switch (item.type) {
            case "Sword":
                this.damage += item.damage;
                System.out.printf("%s has picked the mighty sword.", this.name);
                break;
            case "Shield":
                this.armor += item.armor;
                System.out.printf("%s has picked the strongest shield.", this.name);
                break;
        }

    }
}
