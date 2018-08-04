package MathGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Block {
	private int unit;
	private Color color;
	private String digit;
	private int x;
	private int y;
	public String getDigit() {
		return digit;
	}
	public void setDigit(String digit) {
		this.digit = digit;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public Block(int x,int y,int unit,Color color,String digit) {
		this.x=x;
		this.y=y;
		this.color=color;
		this.unit=unit;
		this.digit=digit;
		
		// TODO Auto-generated constructor stub
	}
	public void paintSelf(Graphics g){
//		if(this==null)return;
		g.setColor(color);
		g.fillRect(x, y, unit, unit);
		g.setColor(Color.black);
		g.drawRect(x, y, unit, unit);
		g.setFont(new Font("test",Font.ITALIC, 25));
		g.drawString(digit, (int)(x+(0.4-0.1*this.digit.length())*unit), (int)(y+0.7*unit));
		
	}
}
