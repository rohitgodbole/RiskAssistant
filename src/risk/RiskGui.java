package risk;

import static risk.RiskMain.TERRITORY_LIST;
import static risk.RiskMain.SOLDIER_COLORS;
import static risk.RiskMain.COLOR_STR_LIST;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class RiskGui extends JFrame {

    /**
     * Just added this default value to avoid this error message:
     * "The serializable class RiskGui does not declare a static final
     * serialVersionUID field of type long"
     */
    private static final long serialVersionUID = 1L;

    /**
     * Initializes a new instance of the Risk GUI.
     * Consists of the intro screen which contains the battle simulator,
     * and a popup map setup screen.
     */
    public RiskGui() {
        this.setLayout(new GridBagLayout());
        this.setBounds(0, 0, 350, 250);

        RiskBattle battle = new RiskBattle(0, 0); // "back end"

        Container pane = this.getContentPane();
        pane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;

        // GUI elements
        JTextPane titleText = new JTextPane();
        JTextField numPlayersText = new JTextField("numPlayers");
        JButton setupMapButton = new JButton("Automatic Map Setup");
        JTextField numAttackersText = new JTextField("numAttackers");
        JTextField numDefendersText = new JTextField("numDefenders");
        JButton rollOnceButton = new JButton("Roll Once");
        JButton totalWarButton = new JButton("Total War");

        titleText.setText("RISK ASSISTANT");
        titleText.setEditable(false);
        SimpleAttributeSet attribs = new SimpleAttributeSet();
        StyleConstants.setAlignment(attribs, StyleConstants.ALIGN_CENTER);
        StyleConstants.setFontFamily(attribs, "Consolas");
        StyleConstants.setFontSize(attribs, 36);
        StyleConstants.setForeground(attribs, Color.RED);
        titleText.setParagraphAttributes(attribs, true);
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.NORTH;
        pane.add(titleText, c);

        numPlayersText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                int numPlayers = Integer.parseInt(numPlayersText.getText());
                if (numPlayers > 1 && numPlayers <= SOLDIER_COLORS.length) {
                    showMapSetup(numPlayers);
                } else {
                    numPlayersText.setText("must be 2-" + SOLDIER_COLORS.length + " players");
                }
            }
        });
        c.gridx = 0;
        c.gridy = 1;
        pane.add(numPlayersText, c);

        setupMapButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                int numPlayers = Integer.parseInt(numPlayersText.getText());
                if (numPlayers > 1 && numPlayers <= SOLDIER_COLORS.length) {
                    showMapSetup(numPlayers);
                } else {
                    numPlayersText.setText("must be 2-" + SOLDIER_COLORS.length + " players");
                }
            }
        });
        c.gridx = 0;
        c.gridy = 2;
        pane.add(setupMapButton, c);

        numAttackersText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                int numAttackers = Integer.parseInt(numAttackersText.getText());
                int numDefenders = Integer.parseInt(numDefendersText.getText());
                battle.setCombatants(numAttackers, numDefenders);
                battle.rollOnce();
                numAttackers = battle.getAtkArmySize();
                numDefenders = battle.getDefArmySize();
                numAttackersText.setText(Integer.toString(numAttackers));
                numDefendersText.setText(Integer.toString(numDefenders));
            }
        });
        c.gridx = 0;
        c.gridy = 3;
        pane.add(numAttackersText, c);

        numDefendersText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                int numAttackers = Integer.parseInt(numAttackersText.getText());
                int numDefenders = Integer.parseInt(numDefendersText.getText());
                battle.setCombatants(numAttackers, numDefenders);
                battle.rollOnce();
                numAttackers = battle.getAtkArmySize();
                numDefenders = battle.getDefArmySize();
                numAttackersText.setText(Integer.toString(numAttackers));
                numDefendersText.setText(Integer.toString(numDefenders));
            }
        });
        c.gridx = 0;
        c.gridy = 4;
        pane.add(numDefendersText, c);

        rollOnceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                int numAttackers = Integer.parseInt(numAttackersText.getText());
                int numDefenders = Integer.parseInt(numDefendersText.getText());
                battle.setCombatants(numAttackers, numDefenders);
                battle.rollOnce();
                numAttackers = battle.getAtkArmySize();
                numDefenders = battle.getDefArmySize();
                numAttackersText.setText(Integer.toString(numAttackers));
                numDefendersText.setText(Integer.toString(numDefenders));
            }
        });
        c.gridx = 0;
        c.gridy = 5;
        pane.add(rollOnceButton, c);

        totalWarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                int numAttackers = Integer.parseInt(numAttackersText.getText());
                int numDefenders = Integer.parseInt(numDefendersText.getText());
                battle.setCombatants(numAttackers, numDefenders);
                battle.finishBattle();
                numAttackers = battle.getAtkArmySize();
                numDefenders = battle.getDefArmySize();
                numAttackersText.setText(Integer.toString(numAttackers));
                numDefendersText.setText(Integer.toString(numDefenders));
            }
        });
        c.gridx = 0;
        c.gridy = 6;
        pane.add(totalWarButton, c);

        this.setVisible(true);  // makes window visible & redraws
    }

    /**
     * Given the number of players, open a new window that shows how
     * the territories will be divided up.
     * @param numPlayers
     */
    private void showMapSetup(int numPlayers) {

        JFrame mapFrame = new JFrame();
        mapFrame.setVisible(true);
        mapFrame.setSize(500, 500);

        Container pane = mapFrame.getContentPane();
        pane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;

        JTextPane colorDescriptionText = new JTextPane();
        colorDescriptionText.setText(String.join(", ",
                Arrays.copyOfRange(COLOR_STR_LIST, 0, numPlayers)));
        colorDescriptionText.setEditable(false);
        SimpleAttributeSet attribs = new SimpleAttributeSet();
        StyleConstants.setAlignment(attribs, StyleConstants.ALIGN_CENTER);
        StyleConstants.setFontFamily(attribs, "Consolas");
        StyleConstants.setFontSize(attribs, 14);
        StyleConstants.setForeground(attribs, Color.BLACK);
        colorDescriptionText.setParagraphAttributes(attribs, false);
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 3;
        pane.add(colorDescriptionText, c);
        c.gridwidth = 1;

        Color[] toPaint = new Color[TERRITORY_LIST.length];
        Color[] playerColors = new Color[numPlayers];
        int[] playerNumTerritories = new int[numPlayers];
        for (int i = 0; i < playerColors.length; i++) {
            playerColors[i] = SOLDIER_COLORS[i];
            playerNumTerritories[i] = 0;
        }
        int numTerritoriesCap = 1 + TERRITORY_LIST.length / numPlayers;
        for (int i = 0; i < toPaint.length; i++) {
            int luckyPlayer = (int) (Math.random() * numPlayers);
            while (playerNumTerritories[luckyPlayer] >= numTerritoriesCap) {
                luckyPlayer = (int) (Math.random() * numPlayers);
            }
            playerNumTerritories[luckyPlayer]++;
            toPaint[i] = playerColors[luckyPlayer];
        }

        int xpos = 0;
        int ypos = 1;
        int numColumns = 3;
        for (int i = 0; i < TERRITORY_LIST.length; i++) {
            JTextPane mapText = new JTextPane();
            mapText.setText(TERRITORY_LIST[i]);
            mapText.setEditable(false);
            StyleConstants.setAlignment(attribs, StyleConstants.ALIGN_CENTER);
            StyleConstants.setFontFamily(attribs, "Consolas");
            StyleConstants.setFontSize(attribs, 14);
            StyleConstants.setForeground(attribs, toPaint[i]);
            mapText.setParagraphAttributes(attribs, false);
            c.gridx = xpos;
            c.gridy = ypos++;
            if (ypos > TERRITORY_LIST.length / numColumns) {
                ypos = 1;
                xpos++;
            }
            pane.add(mapText, c);
        }
    }
}
