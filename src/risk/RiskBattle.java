package risk;

public class RiskBattle {
    RiskDice dice;
    int atkArmySize;
    int defArmySize;

    /**
     * Initialize a battle given the number of attackers/defenders.
     * @param atkArmySize
     * @param defArmySize
     */
    public RiskBattle(int atkArmySize, int defArmySize) {
        this.dice = new RiskDice();
        this.atkArmySize = atkArmySize;
        this.defArmySize = defArmySize;
    }

    /**
     * Initialize a battle given the number of attackers/defenders
     * and a custom number of dice for the attackers/defenders.
     * @param atkArmySize
     * @param defArmySize
     * @param numAttackDice
     * @param numDefenseDice
     */
    public RiskBattle(int atkArmySize, int defArmySize,
            int numAttackDice, int numDefenseDice) {
        int[] attackDice = new int[atkArmySize];
        int[] defenseDice = new int[defArmySize];
        this.dice = new RiskDice(attackDice, defenseDice);
        this.atkArmySize = atkArmySize;
        this.defArmySize = defArmySize;
    }

    public int getAtkArmySize() {
        return this.atkArmySize;
    }

    public int getDefArmySize() {
        return this.defArmySize;
    }

    public void setCombatants(int atkArmySize, int defArmySize) {
        this.atkArmySize = atkArmySize;
        this.defArmySize = defArmySize;
    }

    public boolean isBattleFinished() {
        return (this.atkArmySize <= 0 || this.defArmySize <= 0);
    }

    /**
     * Roll the dice once and update attacker/defender count accordingly.
     */
    public void rollOnce() {
        if (!isBattleFinished()) {
            int numAttackers = Math.min(dice.attackDice.length, atkArmySize);
            int numDefenders = Math.min(dice.defenseDice.length, defArmySize);
            int numDeaths = Math.min(numAttackers, numDefenders);
            int attackerAdvantage = dice.roll(numAttackers, numDefenders);
            atkArmySize -= (numDeaths - attackerAdvantage) / 2;
            defArmySize -= (numDeaths + attackerAdvantage) / 2;
        }
    }

    /**
     * Roll dice until battle is finished.
     * Calls rollOnce repeatedly.
     */
    public void finishBattle() {
        while (!isBattleFinished()) {
            rollOnce();
        }
    }
    
    /**
     * Return a string representation of the current battle state.
     */
    public String toString() {
        return "Attack: " + atkArmySize + " Defense: " + defArmySize + "; Dice: " + dice;
    }
}
