package code;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class ObjectManager extends Manager {


	private static Racket rr;
	private static Racket gr;
	private static Puck p1;
	private static Puck p2;
	private static Goal rg;
	private static Goal gg;
	private static Wall wt;
	private static Wall wb;
	private static Field f;
	private static List<Block> lb;

	public ObjectManager() {
		createObject();
	}

	/*
	 * オブジェクトを生成
	 */
	private void createObject() {
		rr = new Racket(RED_RACKET_STARTX, RED_RACKET_STARTY, RACKET_WIDTH, RACKET_HEIGHT, Color.RED);
		gr = new Racket(GREEN_RACKET_STARTX, GREEN_RACKET_STRATY, RACKET_WIDTH, RACKET_HEIGHT, Color.GREEN);
		p1 = new Puck(P1_STARTX, P1_STARTY, PUCK_SIZE, PUCK_SIZE, PUCK_SPEED, PUCK_SPEED);
		p2 = new Puck(P2_STARTX, P2_STARTY, PUCK_SIZE, PUCK_SIZE, PUCK_SPEED, PUCK_SPEED);
		gg = new Goal(WALL_X, WALL_Y + WALL_THICK, WALL_THICK, WALL_LENGTH_VERTICALLY, Color.GREEN);
		rg = new Goal(WALL_X + WALL_LENGTH_HORIZONTALLY - WALL_THICK, WALL_Y + WALL_THICK, WALL_THICK, WALL_LENGTH_VERTICALLY, Color.RED);
		wt = new Wall(WALL_X, WALL_Y, WALL_LENGTH_HORIZONTALLY, WALL_THICK, Color.BLACK);
		wb = new Wall(WALL_X, WALL_Y + WALL_LENGTH_VERTICALLY + WALL_THICK, WALL_LENGTH_HORIZONTALLY, WALL_THICK, Color.BLACK);
		f = new Field(FIELD_X, FIELD_Y, FIELD_WIDTH, FIELD_HEIGHT);
		lb = new ArrayList<Block>();
		for (int j = 0; j < 2; j++) {
			for (int i = 0; i < FIELD_HEIGHT/ BLOCK_HEIGHT; i++) {
				Block b = new Block(FIELD_X + BLOCK_WIDTH*j, FIELD_Y + BLOCK_HEIGHT * i, BLOCK_WIDTH, BLOCK_HEIGHT);
				lb.add(b);
				b = new Block(FIELD_X + FIELD_WIDTH - BLOCK_WIDTH*(j+1), FIELD_Y + BLOCK_HEIGHT * i, BLOCK_WIDTH, BLOCK_HEIGHT);
				lb.add(b);
			}
		}
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < FIELD_HEIGHT / BLOCK_HEIGHT; j++) {
				Block b = new Block(FIELD_X + FIELD_WIDTH / 2 + BLOCK_WIDTH * (i-1), FIELD_Y + j * BLOCK_HEIGHT, BLOCK_WIDTH, BLOCK_HEIGHT);
				lb.add(b);
			}
		}

	}

	/*
	 * 	オブジェクトの情報を更新
	 */
	public void update() {
		racketUpdate();
		puckUpdate();
		if (lb.size() <= 30) { f.showImage(); }
	}

	/*
	 * オブジェクトの描画
	 */
	public void drawObject(Graphics g) {
		f.draw(g);
	    rr.draw(g);
	    gr.draw(g);
	    gg.draw(g);
	    wt.draw(g);
	    rg.draw(g);
	    wb.draw(g);
	    p1.draw(g);
	    p2.draw(g);
	    for (int i = 0; i < lb.size(); i++) {
		    lb.get(i).draw(g);
	    }
	}

	/*
	 * オブジェクトの情報の初期化
	 */
	public void initObject() {
		createObject();
	}

	/*
	 * ラケットの情報の更新
	 */
	private void racketUpdate() {
		rr.move();
		gr.move();
	}

	/*
	 * ラケットの移動
	 * @ return 現在位置に存在できるか
	 */
	public static boolean racketExist(Racket r, Racket e) {
		if (r.isHit(gg)) return false;
		if (r.isHit(wt)) return false;
		if (r.isHit(rg)) return false;
		if (r.isHit(wb)) return false;
		if (r.isHit(e)) return false;
		if (r.isHit(p1)) return false;
		if (r.isHit(p2)) return false;
		for (int i = 0; i < lb.size(); i++) {
			if (r.isHit(lb.get(i))) return false;
		}
		return true;
	}

	/*
	 * パックの情報の更新
	 */
	private void puckUpdate() {
		p1.move();
		p2.move();
		puckReflect();
	}

	/*
	 * パックの反射
	 */
	private void puckReflect() {
		if (p1.isHit(p2)) { p1.reflect(p2); }
		if (p1.isHit(rr)) { p1.reflect(rr); p1.changeColor(rr.getColor()); CBYC_Bong.speedUp(); PlayerManager.getRedPlayer().gaugeUp(); }
		if (p1.isHit(gr)) { p1.reflect(gr); p1.changeColor(gr.getColor()); CBYC_Bong.speedUp(); PlayerManager.getGreenPlayer().gaugeUp(); }
		if (p1.isHit(gg)) {
		    if ( p1.getColor() == gg.getColor() ) {
		        p1.reflect(gg);
		        PlayerManager.getGreenPlayer().upScore(20);
		        initObject();
		        CBYC_Bong.speedReset();
		    } else {
		        p1.reflect(gg);
		        CBYC_Bong.speedUp();
		    }
		}
		if (p1.isHit(rg)) {
            if ( p1.getColor() == rg.getColor() ) {
                p1.reflect(rg);
                PlayerManager.getRedPlayer().upScore(20);
                initObject();
                CBYC_Bong.speedReset();
            } else {
                p1.reflect(rg);
                CBYC_Bong.speedUp();
            }
        }
		if (p1.isHit(wt)) { p1.reflect(wt); CBYC_Bong.speedUp(); }
		if (p1.isHit(wb)) { p1.reflect(wb); CBYC_Bong.speedUp(); }
		if (p2.isHit(rr)) { p2.reflect(rr); p2.changeColor(rr.getColor()); CBYC_Bong.speedUp(); PlayerManager.getRedPlayer().gaugeUp(); }
		if (p2.isHit(gr)) { p2.reflect(gr); p2.changeColor(gr.getColor()); CBYC_Bong.speedUp(); PlayerManager.getGreenPlayer().gaugeUp(); }
		if (p2.isHit(gg)) {
            if ( p2.getColor() == gg.getColor() ) {
                p2.reflect(gg);
                PlayerManager.getGreenPlayer().upScore(20);
                createObject();
                CBYC_Bong.speedReset();
            } else {
                p2.reflect(gg);
                CBYC_Bong.speedUp();
            }
        }
        if (p2.isHit(rg)) {
            if ( p2.getColor() == rg.getColor() ) {
                p2.reflect(rg);
                PlayerManager.getRedPlayer().upScore(20);
                createObject();
                CBYC_Bong.speedReset();
            } else {
                p2.reflect(rg);
                CBYC_Bong.speedUp();
            }
        }
		if (p2.isHit(wt)) { p2.reflect(wt); CBYC_Bong.speedUp(); }
		if (p2.isHit(wb)) { p2.reflect(wb); CBYC_Bong.speedUp(); }

		for (int i = 0; i < lb.size(); i++) {
			Block b = lb.get(i);
			boolean flag = false;
			if (p1.isHit(b)) { 
				p1.reflect(b);
				if (p1.getColor() == RED_PLAYER_COLOR) {
					PlayerManager.getRedPlayer().upScore(1); 
					PlayerManager.getRedPlayer().gaugeUp();
				}
				else if (p1.getColor() == GREEN_PLAYER_COLOR) {
					PlayerManager.getGreenPlayer().upScore(1); 
					PlayerManager.getGreenPlayer().gaugeUp();
				}
				flag = true; 
			}
			if (p2.isHit(b)) { 
				p2.reflect(b); 
				if (p2.getColor() == RED_PLAYER_COLOR) {
					PlayerManager.getRedPlayer().upScore(1); 
					PlayerManager.getRedPlayer().gaugeUp();
				}
				else if (p2.getColor() == GREEN_PLAYER_COLOR) {
					PlayerManager.getGreenPlayer().upScore(1); 
					PlayerManager.getGreenPlayer().gaugeUp();
				}
				flag = true; 
			}
			if (flag) lb.remove(i);
		}

	}

	/*
	 * 指定した色のラケットの情報の取得
	 * @ return Racket
	 */
	public Racket getRacket(Color c) {
		return (c == Color.RED) ? rr : gr;
	}

	/*
	 * 指定した色の敵のラケットの情報の取得
	 * @ return Racket
	 */
	public static Racket getEnemyRacket(Color c) {
		return (c == Color.GREEN) ? rr : gr;
	}

	/*
	 * パック1の情報の取得
	 * @ return puck1
	 */
	public Puck getPuck1() {
		return p1;
	}

	/*
	 * パック2の情報の取得
	 * @ return puck1
	 */
	public Puck getPuck2() {
		return p2;
	}

	/*
	 * フィールド情報の取得
	 */
	public static Field getField() {
		return f;
	}

}
