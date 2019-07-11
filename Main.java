package Codific;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hello, what is your name?");
        String name = scanner.nextLine();
        System.out.printf("Hi %s! WELCOME TO DUNGEON MAZE!%n", name);

        Dungeon dungeon = new Dungeon();
        Player player = new Player(name, 100, new Integer[]{0, 0}, 50, 3);

        while (true) {
            player.movePlayer(dungeon);

            if (!player.fightMonster(dungeon)) {
                break;
            }

            if (player.hasFoundExit(dungeon)) {
                break;
            }

            player.pickItem(dungeon);
        }
    }

}
