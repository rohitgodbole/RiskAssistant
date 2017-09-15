package risk;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class RiskDiceTest {

    /**
     * Test that attackers lose a soldier when roll is tied.    <br />
     * 
     * Roll:            <br />
     *  Attack:  6 5 1  <br />
     *  Defense: 6 6    <br />
     * 
     * Desired return value is -2.
     */
    @Test
    public void equalDiceTest() {
        int[] attackDice = {6, 5, 1};
        int[] defenseDice = {6, 6};
        RiskDice dice = new RiskDice(attackDice, defenseDice);
        assertTrue(dice.roll(3, 2, true) == -2);
    }

    /**
     * Test with one attacker against two defenders.
     * Should lose only one defender.
     */
    @Test
    public void fewerAttackersTest() {
        int[] attackDice = {5, 5, 5};
        int[] defenseDice = {4, 2};
        RiskDice dice = new RiskDice(attackDice, defenseDice);
        assertTrue(dice.roll(1, 2, true) == 1);
    }

    /**
     * Test with three attackers against one defender.
     * Should only lose one defender. 
     */
    @Test
    public void fewerDefendersTest() {
        int[] attackDice = {5, 5, 5};
        int[] defenseDice = {4, 2};
        RiskDice dice = new RiskDice(attackDice, defenseDice);
        assertTrue(dice.roll(3, 1, true) == 1);
    }
}
