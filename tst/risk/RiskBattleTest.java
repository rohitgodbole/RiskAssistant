package risk;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class RiskBattleTest {

    /**
     * Verify that RiskBattle properly checks to see if a battle is finished.
     */
    @Test
    public void isBattleFinishedTest() {
        RiskBattle battle = new RiskBattle(-3, 0);
        assertTrue(battle.isBattleFinished());
        battle = new RiskBattle(0, 5);
        assertTrue(battle.isBattleFinished());
        battle = new RiskBattle(3, 3);
        assertTrue(!battle.isBattleFinished());
    }

    /**
     * Test ability of finishBattle to keep rolling until battle is complete.
     */
    @Test
    public void finishBattleTest() {
        RiskBattle battle = new RiskBattle(1000000, 1000000);
        System.out.println("Initial Battle State: " + battle);
        battle.finishBattle();
        System.out.println("End Battle State: " + battle);
        assertTrue(battle.isBattleFinished());
    }
}
