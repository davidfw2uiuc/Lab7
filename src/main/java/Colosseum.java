import java.util.Scanner;

/**
 * Class that implements a Pokemon colosseum.
 * <p>
 * The Pokemon battles that you might have grown up to know and love are now console based! The
 * Colosseum class is where all the battles will go down. We will build our Pokemon, let them
 * battle, and see who will be the winner! Check out the example runs folder for a few example runs
 * on how the final game should look.
 *
 * @see <a href="https://cs125.cs.illinois.edu/lab/7/">Lab 7 Description</a>
 */
public class Colosseum {
    /**
     * The maximum number of hit points we will allow a Pokemon to start with.
     */
    static final int MAX_HIT_POINTS = 50;

    /**
     * Maximum attack/defense level.
     */
    static final int SUM_ATTACK_DEFENSE = 50;

    /**
     * The maximum number of rounds we will let the Pokemon battle.
     */
    static final int MAX_NUM_ROUNDS = 10;

    /**
     * The first Pokemon we will be fighting.
     */
    static Pokemon firstPokemon;

    /**
     * The second Pokemon we will be fighting.
     */
    static Pokemon secondPokemon;

    /**
     * Input scanner. Use this to take in user's input for buildPokemon(). <br>
     * Useful functions: next(), nextInt() .
     */
    static Scanner myScan;

    /**
     * How we will build our Pokemon to battle.
     * <p>
     * Obtain user input to set Pokemon's member variables
     * <p>
     * Requirements we should check the user for: <br>
     * - Hit points are between 1 and MAX_HIT_POINTS <br>
     * - No more than 50 points are split between attack level and defense leve <br>
     * - Attack level and defense level must have at least 1 point each <br>
     * Example of how this will look to the user:
     * <p>
     * Please name your Pokemon: Dolphin <br>
     * How many hit points will it have? (1-50): 50 <br>
     * Split fifty points between attack level and defense level <br>
     * Enter your attack level (1-49): 47 <br>
     * Enter your defense level (1-3): 3 <br>
     * <br>
     * Example of checking for bad input: <br>
     * <br>
     * Please name your Pokemon: Fire <br>
     * How many hit points will it have? (1-50): 0 <br>
     * Sorry. Hit points must be between 1 and 50: 55 <br>
     * Sorry. Hit points must be between 1 and 50: 50 <br>
     * Split fifty points between attack level and defense level <br>
     * Enter your attack level (1-49): -10 <br>
     * Sorry. The attack level must be between 1 and 49: 73 <br>
     * Sorry. The attack level must be between 1 and 49: 27 <br>
     * Enter your defense level (1-23): 24 <br>
     * Sorry. The defense level must be between 1 and 23: 23
     *
     * @return tempPokemon - the Pokemon we built and are going to set our fighting Pokemon to <br>
     *         (Look, we can return objects too!)
     *         <p>
     *         Implement this function.
     */
    public static Pokemon buildPokemon() {
        Pokemon tempPokemon = new Pokemon();
        boolean hitPointsSelected = false;
        boolean attackPointsSelected = false;
        boolean defensePointsSelected = false;
        myScan = new Scanner(System.in);

        System.out.print("Please name your Pokemon: ");
        tempPokemon.name = myScan.next();

        System.out.println("How many hit points will it have? (1-50):");
        while (!hitPointsSelected) {
            tempPokemon.hitPoints = myScan.nextInt();
            if (tempPokemon.hitPoints > MAX_HIT_POINTS || tempPokemon.hitPoints < 1) {
                System.out.println("Sorry. Hit points must be between 1 and 50: ");
            } else {
                hitPointsSelected = true;
            }
        }

        System.out.println("Split fifty points between attack leven and defense level");
        System.out.println("Enter your attack level (1-49): ");
        while (!attackPointsSelected) {
            tempPokemon.attackLevel = myScan.nextInt();
            if (tempPokemon.attackLevel > SUM_ATTACK_DEFENSE - 1 || tempPokemon.attackLevel < 1) {
                System.out.println("Sorry. The attack level must be between 1 and 49: ");
            } else {
                attackPointsSelected = true;
            }
        }
        System.out.printf("Enter your defense level "
                + "(1-%d): \n", MAX_HIT_POINTS - tempPokemon.attackLevel);
        while (!defensePointsSelected) {
            tempPokemon.defenseLevel = myScan.nextInt();
            if (tempPokemon.defenseLevel > SUM_ATTACK_DEFENSE  - tempPokemon.attackLevel
                    || tempPokemon.defenseLevel < 1) {
                System.out.printf("Sorry. The attack level must be "
                        + "between 1 and %d: ", SUM_ATTACK_DEFENSE  - tempPokemon.attackLevel);
            } else {
                defensePointsSelected = true;
            }
        }
        return tempPokemon;
    }

    /**
     * Prints who is ahead.
     * <p>
     * Compares the two Pokemon to see if there's a tie, or if a pokemon is currently winning.
     * <p>
     * Example: <br>
     * Fire has 41 hit points <br>
     * Dolphin has 44 hit points <br>
     * <br>
     * Print "Dolphin is currently ahead!"
     * <p>
     * Implement this function.
     */
    public static void printWhoIsAhead() {
        int hp1 = firstPokemon.hitPoints, hp2 = secondPokemon.hitPoints;
        String name1 = firstPokemon.name, name2 = secondPokemon.name, ahead = "";
        System.out.printf("%s has %d hit points \n", name1, hp1);
        System.out.printf("%s has %d hit points \n", name2, hp2);
        if (hp1 == hp2) {
            System.out.printf("%s and %s are currently tied!", name1, name2);
        } else if (hp1 > hp2) {
            ahead = name1;
        } else {
            ahead = name2;
        }

        if (hp1 != hp2) {
            System.out.printf("%s is currently ahead!", ahead);
        }
    }

    /**
     * Prints out the overall winner of the battle.
     * <p>
     * This will only be called if there is not a tie, so you don't need to worry about this case.
     * <p>
     * Write this function.
     */
    public static void determineWinner() {
        int hp1 = firstPokemon.hitPoints, hp2 = secondPokemon.hitPoints;
        String name1 = firstPokemon.name, name2 = secondPokemon.name, winner = "";
        if (hp1 == 0 || hp2 == 0) {
            if (hp1 == 0) {
                winner = name2;
            } else {
                winner = name1;
            }
            System.out.printf("%s won the battle!", winner);
        }

    }

    /**
     * Initializes the member Pokemons.
     * <p>
     * You do not need to modify this function.
     */
    public static void initializePokemon() {
        System.out.println("Player 1, build your Pokemon!");
        System.out.println("=================");
        firstPokemon = buildPokemon();
        //firstPokemon.name = "Chuchu";

        System.out.println("");

        System.out.println("Player 2, build your Pokemon!");
        System.out.println("==================");
        secondPokemon = buildPokemon();
        //secondPokemon.name = "Xyz";
    }

    /**
     * Determines the order of which Pokemon will go first.
     * <p>
     * Uses a 2-sided die to roll for first.
     * <p>
     * You do not need to modify this function.
     */
    public static void determineOrder() {
        /*
         * Use random throw to decide ordering.
         */
        final Dice d2 = new Dice(2);
        System.out.println("\nPlayer 1 will roll a D2, to decide who goes first.");
        final int firstTurn = d2.roll();
        System.out.print("Player 1 rolls a " + firstTurn + " and will go ");
        if (firstTurn == 1) {
            System.out.print("first");
        } else {
            /*
             * Swap Pokemon for second outcome.
             */
            System.out.print("second");
            Pokemon tempPokemon = new Pokemon();
            tempPokemon = firstPokemon;
            firstPokemon = secondPokemon;
            secondPokemon = tempPokemon;
        }
    }
    /**
     * Conducts the Pokemon battle.
     * <p>
     * You do not need to modify this function.
     *
     * @param unused unused input arguments.
     */
    public static void main(final String[] unused) {
        myScan = new Scanner(System.in);
        initializePokemon();
        determineOrder();
        System.out.println("");
        boolean ifWinner = false;

        /*
         * Let the battle begin!
         */
        for (int i = 0; i < MAX_NUM_ROUNDS && !ifWinner; i++) {
            System.out.println("");
            System.out.println("Round " + (i + 1) + "!");
            System.out.println("");

            ifWinner = firstPokemon.attack(secondPokemon);
            if (!ifWinner) {
                ifWinner = secondPokemon.attack(firstPokemon);
                if (!ifWinner) {
                    printWhoIsAhead();
                }

            }
        }
        System.out.println("");

        if (!ifWinner) {
            System.out.println("It's a tie!");
        } else {
            determineWinner();
        }

        myScan.close();
    }
}
