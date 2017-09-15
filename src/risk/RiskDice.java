package risk;

public class RiskDice {

    int[] attackDice;
    int[] defenseDice;

    /**
     * RiskDice constructor. Change dimensions of attack/defense arrays
     * to change number of dice rolled by each side.
     */
    public RiskDice() {
        int[] attackDice = new int[3];
        int[] defenseDice = new int[2];
        this.attackDice = attackDice;
        this.defenseDice = defenseDice;
    }

    /**
     * RiskDice copy constructor.
     */
    public RiskDice(int[] attackDice, int[] defenseDice) {
        this.attackDice = attackDice;
        this.defenseDice = defenseDice;
    }

    /**
     * Roll the dice one time & return result.
     * (Part 1 of the roll method. Generates a random roll and delegates to part 2.)
     * @param numAttackers must be >= 1
     * @param numDefenders must be >= 1
     * @return net change in attackers minus defenders
     */
    public int roll(int numAttackers, int numDefenders) {

        numAttackers = Math.min(numAttackers, attackDice.length);
        numDefenders = Math.min(numDefenders, defenseDice.length);

        for(int i = 0; i < numAttackers; i++) {
            int roll = 1 + (int) (Math.random() * 6);
            int j = 0;
            while (roll < attackDice[j] && j < i) {
                j++;
            }
            for(int k = i; k > j; k--) {
                attackDice[k] = attackDice[k-1];
            }
            attackDice[j] = roll;
        }

        for(int i = 0; i < numDefenders; i++) {
            int roll = 1 + (int) (Math.random() * 6);
            int j = 0;
            while (roll < defenseDice[j] && j < i) {
                j++;
            }
            for(int k = i; k > j; k--) {
                defenseDice[k] = defenseDice[k-1];
            }
            defenseDice[j] = roll;
        }

        return roll(numAttackers, numDefenders, true);
    }

    /**
     * Roll the dice one time & return result.
     * (Part 2 of the roll method. Randomly generated numbers are already in arrays.)
     * Unit tests will be written only for this part of the roll method since part 1
     * involves randomness.
     * @param numAttackers must be >= 1
     * @param numDefenders must be >= 1
     * @param dontCare don't care
     * @return net change in attackers minus defenders
     */
    public int roll(int numAttackers, int numDefenders, boolean dontCare) {

        int numDeaths = numAttackers < numDefenders ? numAttackers : numDefenders;

        int attackerAdvantage = 0;
        for (int i = 0; i < numDeaths; i++) {
            attackerAdvantage += (attackDice[i] > defenseDice[i]) ? 1 : -1;
        }

        return attackerAdvantage;
    }

    /**
     * Return a string representation of the current battle state.
     */
    public String toString() {
        String returnVal = "";
        for (int a : attackDice) {
            returnVal += (a + " ");
        }
        returnVal += " vs. ";
        for (int d : defenseDice) {
            returnVal += (d + " ");
        }
        return returnVal;
    }
}
