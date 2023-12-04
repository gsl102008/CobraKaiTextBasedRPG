import java.util.Random;
import java.util.Scanner;

//Player class
class Player {
    String name;
    int health;
    int attack;

    public Player(String name, int health, int attack) {
        this.name = name;
        this.health = health;
        this.attack = attack;
    }

    public void attack(Enemy enemy) {
        int damage = new Random().nextInt(attack) + 1;
        System.out.println(name + " attacks " + enemy.name + " for " + damage + " damage.");
        enemy.takeDamage(damage);
    }

    public void takeDamage(int damage) {
        health -= damage;
        System.out.println(name + " takes " + damage + " damage. Remaining health: " + health);
    }

    public boolean isAlive() {
        return health > 0;
    }
}

// Enemy class
class Enemy {
    String name;
    int health;
    int attack;

    public Enemy(String name, int health, int attack) {
        this.name = name;
        this.health = health;
        this.attack = attack;
    }

    public void attack(Player player) {
        int damage = new Random().nextInt(attack) + 1;
        System.out.println(name + " attacks " + player.name + " for " + damage + " damage.");
        player.takeDamage(damage);
    }

    public void takeDamage(int damage) {
        health -= damage;
        System.out.println(name + " takes " + damage + " damage. Remaining health: " + health);
    }

    public boolean isAlive() {
        return health > 0;
    }
}

// Main class
public class TextBasedRPG {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        
        System.out.println("Cobra Kai Never Dies...or does it? Let's find out if you can be the one to defeat Cobra Kai...");
        System.out.print("What is your fighters name: ");
        String playerName = scanner.nextLine();
        Player player = new Player(playerName, 100, 20);

        
        System.out.println("Hello, " + player.name + "! Get ready for your adventure.");

        // Storyline
        System.out.println("One day you are out for a walk when you run into the infamous leader of Cobra Kai...");

        // Battle loop
        while (player.isAlive()) {
            Enemy enemy = new Enemy("John Kreese", 50, 10);
            System.out.println("It's the ruthless " + enemy.name + "!");

            // Battle
            while (player.isAlive() && enemy.isAlive()) {
                System.out.print("What do you want to do? (1. Attack, 2. Run): ");
                int choice = scanner.nextInt();

                if (choice == 1) {
                    player.attack(enemy);
                    if (enemy.isAlive()) {
                        enemy.attack(player);
                    }
                } else if (choice == 2) {
                    System.out.println("You ran away from the fight.");
                    break;
                } else {
                    System.out.println("Invalid choice. Try again.");
                }
            }

            // Check outcome of the battle
            if (player.isAlive()) {
                System.out.println("You defeated " + enemy.name + "!");
            } else {
                System.out.println("Fighting is not good. But if you must fight...WIN (-Mr. Miyagi) Game over. You were defeated by " + enemy.name + ".");
                break;
            }
        }

        System.out.println("Thanks for playing!");
        scanner.close();
    }
}
