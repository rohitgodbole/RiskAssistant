package risk;

import java.awt.Color;

public class RiskMain {

    public final static String[] TERRITORY_LIST = {"Alaska", "Alberta", "Central America",
            "Eastern United States", "Greenland", "Northwest Territory", "Ontario", "Quebec",
            "Western United States", "Argentina", "Brazil", "Peru", "Venezuela", "Great Britain",
            "Iceland", "Northern Europe", "Scandinavia", "Southern Europe", "Ukraine",
            "Western Europe", "Central Africa", "East Africa", "Egypt", "Madagascar",
            "North Africa", "South Africa", "Afghanistan", "China", "India", "Irkutsk", "Japan",
            "Kamchatka", "Middle East", "Mongolia", "Siam", "Siberia", "Ural", "Yakutsk",
            "Eastern Australia", "Indonesia", "New Guinea", "Western Australia"};
    public final static Color[] SOLDIER_COLORS = {
            new Color(0, 0, 255),   // blue
            new Color(255, 0, 0),   // red
            new Color(0, 0, 0),     // black
            new Color(0, 150, 0),   // green
            new Color(200, 0, 200), // purple
            new Color(255, 140, 0)  // orange
    };
    public final static String[] COLOR_STR_LIST = {"blue", "red", "black", "green", "purple", "orange"};

    public static void main(String[] args) {
        new RiskGui();
    }
}
