package src.code;

import java.awt.Color;


public class Player extends Character {
    
	// コンストラクタ
    public Player(int x, Color c, String p) {
        super(x, c, p);
    }
    
    public void invisible(Puck p1, Puck p2) {
    	if (! isGaugeMax()) return;
    	if (p1.getColor() == this.color) p1.invisible(this.color); gaugeReset();
    	if (p2.getColor() == this.color) p2.invisible(this.color); gaugeReset();
    }
    
    public void powerPuck(Puck p1, Puck p2) {
    	if (! isGaugeMax()) return;
    	if (p1.getColor() == this.color) p1.powerUp(); gaugeReset();
    	if (p2.getColor() == this.color) p2.powerUp(); gaugeReset();
    }
}
