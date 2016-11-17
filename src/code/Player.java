package code;

import java.awt.Color;


public class Player extends Character {

	// コンストラクタ
    public Player(int x, Color c, String p, String dp, int d) {
        super(x, c, p, dp, d);
    }
    
    /*
     * 必殺技1 ボールの不可視化
     */
    public void invisible(Puck p1, Puck p2) {
    	if (! isGaugeMax()) return;
    	if (p1.getColor() == this.color) p1.invisible(this.color);
    	if (p2.getColor() == this.color) p2.invisible(this.color);
    	gaugeReset();
    }

    /*
     * 必殺技2 ブロック破壊時に反射しない
     */
    public void powerPuck(Puck p1, Puck p2) {
    	if (! isGaugeMax()) return;
    	p1.powerUp();
    	p2.powerUp();
    	gaugeReset();
    }
    
    /*
     * 必殺技3 ラケットの巨大化
     */
    public void bigRacket(Racket r) {
    	if (! isGaugeMax()) return;
    	r.bigRacket();
    	gaugeReset();
    }
}
