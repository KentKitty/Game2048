package MathGame;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Observed{
	private int width;
	private int height;
	private int unit;
	private Color color;
	private int x;
	private int y;
	
	private Block[][] block;
	
	private Thread timer =new Thread(new Runnable() {
		
		@Override
		public void run() {
			try {
				while(true){
					Thread.sleep(100);
					repaint();
				}

			} catch (Exception e) {
				// TODO: handle exception
			}			
			
			// TODO Auto-generated method stub
			
		}
	});
	public GamePanel() {
		
		this.unit=Game_2048.GlobalUnit;
		this.width=Game_2048.rows*this.unit;
		this.height=Game_2048.cols*this.unit;
		this.setSize(width, height);
		this.setBackground(color);
		this.x=(int)((Game_2048.firstCoor+Game_2048.xCoorOffset)*unit);
		this.y=(int)((Game_2048.firstCoor+Game_2048.yCoorOffset)*unit);
		this.block=new Block[Game_2048.rows][Game_2048.cols];
//		for(int i=0;i<Game_2048.rows;i++)
//			for(int j=0;j<Game_2048.cols;j++)
//		this.block[0][0]
//				=new Block(
//						(int)((0+Game_2048.xCoorOffset)*unit), 
//						(int)((0+Game_2048.yCoorOffset)*unit), 
//						unit, 
//						Color.LIGHT_GRAY, 
//						"2"
//						);
		this.block[1][0]
				=new Block(
						(int)((0+Game_2048.xCoorOffset)*unit), 
						(int)((1+Game_2048.yCoorOffset)*unit), 
						unit, 
						Color.white, 
						"2"
						);

//		this.block[2][0]
//				=new Block(
//						(int)((0+Game_2048.xCoorOffset)*unit), 
//						(int)((2+Game_2048.yCoorOffset)*unit), 
//						unit, 
//						Color.white, 
//						"3"
//						);
		this.block[3][0]
				=new Block(
						(int)((0+Game_2048.xCoorOffset)*unit), 
						(int)((3+Game_2048.yCoorOffset)*unit), 
						unit, 
						Color.white, 
						"2"
						);
	
		this.timer.start();
		//		System.out.println(block[3][1]);
		// TODO Auto-generated constructor stub
	}
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		
		for(int i=0;i<this.block.length;i++){
			for(int j=0;j<this.block[i].length;j++){
				if(this.block[i][j]!=null)
				this.block[i][j].paintSelf(g);
			}
		}
		g.drawRect(x, y, width,height);
	}
	@Override
	public Block[][] getBlock(){
		return this.block;
	}
	
//	public static void main(String[] args) {
//		GameModel gameModel=new GameModel();
//	}
}
