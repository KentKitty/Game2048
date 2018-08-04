package MathGame;

import javax.swing.JFrame;

public class GameView extends JFrame{
	private String title;
	private int width;
	private int height;
	private GamePanel gamePanel;
	private GameController controller;
	public GameView() {
		super();
		this.width=Game_2048.width;
		this.height=Game_2048.height;
		this.title=Game_2048.title;
		this.setTitle(title);
		this.setSize(width, height);
		this.setResizable(false);
		this.gamePanel=new GamePanel();
		this.gamePanel.setFocusable(true);
		controller=new GameController(gamePanel);
		this.gamePanel.addKeyListener(controller);
		this.setContentPane(gamePanel);
		
		// TODO Auto-generated constructor stub
	}
	public void launch(){
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
	GameView gameView=new GameView();
	gameView.launch();
	}
}
