package code;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;


public abstract class Character implements ImageObserver {

	private static final int PLAYER_IMAGE_WIDTH = 100;
	private static final int PLAYER_IMAGE_HEIGHT = 100;
	private static final int Y = 50;
	private static final int FRAME_WIDTH = 5;


    protected int x;
    protected int d;  // 位地の差分
    protected String playerImagePath;
    protected Image player_image;
    protected Color color;
    protected DeadlyGauge dg;
    protected int score = 0;

    /*
     * コンストラクタ
     */
    public Character(int x, Color c, String p, String dp, int d) {
        this.x = x;
        this.d = d;
        this.color = c;
        this.playerImagePath = p;
        player_image = loadImage(p);
        dg = new DeadlyGauge(x, c, dp);
    }

    /*
     * 描画
     */
    public void draw(Graphics g) {
    	g.setColor(color);
        g.fill3DRect(this.x-FRAME_WIDTH, Y-FRAME_WIDTH, PLAYER_IMAGE_WIDTH+2*FRAME_WIDTH, PLAYER_IMAGE_HEIGHT+2*FRAME_WIDTH, true);
        g.drawImage(player_image, this.x, Y, PLAYER_IMAGE_WIDTH, PLAYER_IMAGE_HEIGHT, this);
        dg.draw(g);
        java.awt.Font font = new Font("ＭＳ 明朝", 100, 100);
        g.setColor(Color.BLACK);
        if (this.score >= CBYC_Bong.FINISH_SCORE - CBYC_Bong.SCORE_GOAL) {
        	g.setColor(color);
        }
        g.setFont(font);
        g.drawString(String.format("%02d", this.score), this.x + d, Y + 50);
    }

    /*
     * 画像の取得
     * @取得した画像
     */
    public Image loadImage (String path) {
        ImageIcon icon = new ImageIcon(getClass().getResource(path));
        return icon.getImage();
    }

    /*
     * ゲージがたまっているか確認
     * @ return boolean
     */
    public boolean isGaugeMax() {
    	return this.dg.isMax();
    }
    /*
     * ゲージを増加
     */
    public void gaugeUp() {
    	this.dg.gaugeUp();
    }

    /*
     * ゲージをリセット
     */
    public void gaugeReset() {
    	this.dg.gaugeReset();
    }

    /*
     * スコアを取得
     * @return int
     */
    public int getScore() {
        return this.score;
    }

    /*
     * 得点
     */
    public void upScore(int point) {
    	if (this.score >= CBYC_Bong.FINISH_SCORE - CBYC_Bong.SCORE_GOAL && point == CBYC_Bong.SCORE_BLOCK) return;
        this.score += point;
    }

    /*
     * リセット
     */
    public void resetScore() {
        this.score = 0;
    }
    @Override
    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
        return false;
    }

}
