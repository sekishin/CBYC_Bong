package src.code;

import java.awt.Color;

public class Goal extends Wall {

	public Goal(int x, int y, int w, int h, Color c) {
		super(x, y, w, h, c);
	}
	
	@Override
	public Type getType() {
		return Type.Goal;
	}

}
