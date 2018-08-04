package MathGame;


import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Random;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameController extends KeyAdapter{
	private Block[][] blocks;
	private boolean power;
	private boolean productPower;
	public GameController(Observed observed) {
		blocks=observed.getBlock();
		power=true;
		productPower=true;
		// TODO Auto-generated constructor stub
	}
	@Override
		public void keyPressed(KeyEvent e){
		int code =e.getKeyCode();
		this.productPower=getMovePower(code);
		
		if(this.power&&this.productPower){
		
//	System.out.println(this.productPower);
		
		
		switch (code) {
		
		case KeyEvent.VK_UP:
			for(int j=0;j<Game_2048.cols;j++){
				getUpResult(j);
			}
//			System.out.println("heup");
			break;
		case KeyEvent.VK_DOWN:
			for(int j=0;j<Game_2048.cols;j++){
				getDownResult(j);
				}	

//			System.out.println("hedown");
			
			break;
		case KeyEvent.VK_RIGHT:
			for(int i=0;i<Game_2048.rows;i++){
				getRightResult(i);
				}	

//			System.out.println("heright");
			
			break;
		case KeyEvent.VK_LEFT:
			for(int i=0;i<Game_2048.rows;i++){
				getLeftResult(i);
			}
			break;
		default:
			System.err.println();
			
			break;
		}
		this.power=false;
		}
		//winJudgement();
	}
	@Override
	public void keyReleased(KeyEvent e){
		if(this.productPower){
			productNewBlock();
			this.productPower=false;
		}
		this.power=true;
	}
	
	public boolean stopUpJudge(int j){
		for(int i=Game_2048.rows-1;i>0;i--){
			if((blocks[i][j]==null)&&(blocks[i-1][j]==null)){
				continue;
			}else if(blocks[i][j]!=null){
				if(blocks[i-1][j]==null){
					return false;
				}else if(blocks[i-1][j].getDigit().equals(blocks[i][j].getDigit())){
					return false;
				}//else continue;
			}//else continue;
		}
		
		
		return  true;
		
	}
	
	public void getUpResult(int j){
//constant plus 's algorithm
		//for(int i=Game_2048.rows-1;i>0;i--){//两种不同的遍历方式，效果会有所不同~~~
//			for(int i=0+1;i<Game_2048.rows;i++){//第二种
//			if(blocks[i][j]!=null){
//				if(blocks[i-1][j]!=null){
//					if(blocks[i][j].getDigit().equals(blocks[i-1][j].getDigit())){
//						blocks[i-1][j].setDigit(
//								String.valueOf(Integer.parseInt(blocks[i][j].getDigit())*2)
//								);
//						blocks[i][j]=null;
//					} 
//				}else {
//					blocks[i-1][j]=blocks[i][j];
//					blocks[i][j]=null;
//					blocks[i-1][j].setY(blocks[i-1][j].getY()-Game_2048.GlobalUnit);
//				}
//			}
//		}
//		if(stopUpJudge(j))return ;
//		getUpResult(j);
		int i=0;
		int k=1;
		while(i+k<Game_2048.rows){
			if(blocks[i+k][j]!=null){
				if(blocks[i][j]!=null){
					if(blocks[i][j].getDigit().equals(blocks[i+k][j].getDigit())){
						blocks[i][j].setDigit(
								String.valueOf(Integer.parseInt(blocks[i][j].getDigit())*2)
								);
						blocks[i+k][j]=null;
						i+=k+1;

					}else i+=k;
				}else i+=k;
				k=1;
			}else k++;
			
		}
		
		int index=0;
		for(int i0=0;i0<Game_2048.rows;i0++){
			if(blocks[i0][j]!=null){
				if(i0!=index){
				blocks[index][j]=blocks[i0][j];
				blocks[i0][j]=null;
				blocks[index][j].setY(
						blocks[index][j].getY()	
						-(i0-index)*Game_2048.GlobalUnit	
						);

				}
				index++;
			}
		}
		
	}

	public boolean stopDownJudge(int j){
		for(int i=0+1;i<Game_2048.rows;i++){
			if((blocks[i-1][j]==null)&&(blocks[i][j]==null)){
				continue;
			}else if(blocks[i-1][j]!=null){
				if(blocks[i][j]==null)
				return false;
				else if(blocks[i][j].getDigit().equals(blocks[i-1][j].getDigit())){
					return false;
				}//else continue;
					
					
					
					//System.out.println("catch");
			}//else continue;
		}
		return true;
	}
	
	public void getDownResult(int j){
//		//for(int i=0+1;i<Game_2048.rows;i++)
//		for(int i=Game_2048.rows-1;i>0;i--)
//		if(blocks[i-1][j]!=null){
//					if(blocks[i][j]!=null){
//						if(blocks[i][j].getDigit().equals(blocks[i-1][j].getDigit())){
//							blocks[i][j].setDigit(
//									String.valueOf(Integer.parseInt(blocks[i-1][j].getDigit())*2)
//									);
//							blocks[i-1][j]=null;
//							
//						}
//						}else {
//							blocks[i][j]=blocks[i-1][j];
//							blocks[i-1][j]=null;
//							blocks[i][j].setY(blocks[i][j].getY()+Game_2048.GlobalUnit);
//							
//						}
//					}//else continue;
//			
//		if(stopDownJudge(j)){
////			System.out.println("SetDown");
//			return;				
//		}
		//getDownResult(j);
		int k=-1;
		int i=Game_2048.rows-1;
		while(i+k>=0){
			if((blocks[i+k][j]!=null)){
				if(blocks[i][j]!=null)
				if(blocks[i][j].getDigit().equals(blocks[i+k][j].getDigit())){
					blocks[i+k][j].setDigit(
					String.valueOf(Integer.parseInt(blocks[i][j].getDigit())*2)
					);
					blocks[i][j]=null;
					i+=k-1;
					k=-1;
				}else{
					i+=k;
					k=-1;
				}else{
					i+=k;
					k=-1;
				}
			}else k--;
		}

		int index=Game_2048.rows-1;
		for(int i0=Game_2048.rows-1;i0>=0;i0--){
			if(blocks[i0][j]!=null){
				if(i0!=index){
					blocks[index][j]=blocks[i0][j];
					blocks[i0][j]=null;
					blocks[index][j].setY(
							blocks[index][j].getY()
							+(index-i0)*Game_2048.GlobalUnit
							);
				}
				index--;
			}
		}

	}
	
	public boolean stopRightJudge(int i){
		for(int j=0+1;j<Game_2048.cols;j++){
			if((blocks[i][j-1]==null)&&(blocks[i][j]==null)){
				continue;
			}else if(blocks[i][j-1]!=null){
				if(blocks[i][j]==null){
					return false;
				}else if(blocks[i][j].getDigit().equals(blocks[i][j-1].getDigit())){
					return false;
				}//else continue;
			}//else continue;
		}
		return true;
	}
	
	public void getRightResult(int i){
	
//		//for(int j=0+1;j<Game_2048.cols;j++){
//			for(int j=Game_2048.cols-1;j>0;j--){
//			if(blocks[i][j-1]!=null){
//				if(blocks[i][j]!=null){
//					if(blocks[i][j].getDigit().equals(blocks[i][j-1].getDigit())){
//						blocks[i][j].setDigit(
//								String.valueOf(Integer.parseInt(blocks[i][j-1].getDigit())*2)
//								);
//						blocks[i][j-1]=null;
//					}//else continue;
//				}else {
//					blocks[i][j]=blocks[i][j-1];
//					blocks[i][j-1]=null;
//					blocks[i][j].setX(blocks[i][j].getX()+Game_2048.GlobalUnit);
//				}
//			}
//		}
//		if(stopRightJudge(i))return ;	
//		//getRightResult(i);
		int j=Game_2048.cols-1;
		int k=-1;
		while(j+k>=0){
			if(blocks[i][j+k]!=null){
				if(blocks[i][j]!=null){
					if(blocks[i][j].getDigit().equals(blocks[i][j+k].getDigit())){
						blocks[i][j+k].setDigit(
								String.valueOf(Integer.parseInt(blocks[i][j].getDigit())*2)
								);
						blocks[i][j]=null;
						j+=k-1;
					}else j+=k;
				}else j+=k;
				k=-1;
			}else k--;
		}

		int subindex=Game_2048.cols-1;
		for(int j0=Game_2048.cols-1;j0>=0;j0--){
			if(blocks[i][j0]!=null){
				if(j0!=subindex){
					blocks[i][subindex]=blocks[i][j0];
					blocks[i][j0]=null;
					blocks[i][subindex].setX(
							blocks[i][subindex].getX()
							+(subindex-j0)*Game_2048.GlobalUnit
							);
//					System.out.println("hei "+subindex);
				}
				subindex--;
//				System.out.println("holy "+subindex);
			}//else System.out.println("hello "+subindex);
		}
		
		
		
	}
	
	public boolean stopLeftJudge(int i){
		for(int j=Game_2048.cols-1;j>0;j--){
			if((blocks[i][j-1]==null)&&(blocks[i][j]==null)){
				continue;
			}else if(blocks[i][j]!=null){
				if(blocks[i][j-1]==null){
					return false ;
				}else if(blocks[i][j].getDigit().equals(blocks[i][j-1].getDigit())){
					return false ;
				}//else continue; 
			}
		}
		return true ;
	}
	public void getLeftResult(int i){
//		//for(int j=Game_2048.cols-1;j>0;j--){
//			for(int j=0+1;j<Game_2048.cols;j++){
//			if(blocks[i][j]!=null){
//				if(blocks[i][j-1]!=null){
//					if(blocks[i][j].getDigit().equals(blocks[i][j-1].getDigit())){
//						blocks[i][j-1].setDigit(
//								String.valueOf(Integer.parseInt(blocks[i][j].getDigit())*2)
//								);
//						blocks[i][j]=null;
//						
//					}//else continue;
//				}else  {
//					blocks[i][j-1]=blocks[i][j];
//					blocks[i][j]=null;
//					blocks[i][j-1].setX(blocks[i][j-1].getX()-Game_2048.GlobalUnit);
//					
//				}
//			}
//		}
//		if(stopLeftJudge(i))return ;
//		getLeftResult(i);
		int j=0;
		int k=1;
		while(j+k<Game_2048.cols){
			if(blocks[i][j+k]!=null){
				if(blocks[i][j]!=null){
					if(blocks[i][j].getDigit().equals(blocks[i][j+k].getDigit())){
						blocks[i][j+k].setDigit(
								String.valueOf(Integer.parseInt(blocks[i][j].getDigit())*2)
								);
						blocks[i][j]=null;
						j+=k+1;
					}else j+=k;
				}else j+=k;
				k=1;

			}else k++;
		}

		int subindex=0;
		for(int j0=0;j0<Game_2048.cols;j0++){
			if(blocks[i][j0]!=null){
				if(j0!=subindex){
					blocks[i][subindex]=blocks[i][j0];
					blocks[i][j0]=null;
					blocks[i][subindex].setX(
						blocks[i][subindex].getX()-(j0-subindex)*Game_2048.GlobalUnit							
						);
					
				}
				subindex++;
			}
		}
	}
	public void productNewBlock(){
		boolean judge=true;
		int x=0;
		int y=0;
		int time=0;
		while(judge){
			x=new Random().nextInt(4);
			y=new Random().nextInt(4);
			judge=(this.blocks[x][y]!=null);
			time++;
			if(time>=100){
				System.err.println("TIME OUT");
				x=y=0;
				break;
			}
		}
		 this.blocks[x][y]=new Block(
				(int)((y+Game_2048.xCoorOffset)*Game_2048.GlobalUnit), 
				(int)((x+Game_2048.yCoorOffset)*Game_2048.GlobalUnit),
				Game_2048.GlobalUnit, Color.cyan, String.valueOf((int)Math.pow(2,new Random().nextInt(2)+1))
				);
	}
	public boolean getMovePower(int directCode){
		switch (directCode) {
		case KeyEvent.VK_UP:
			for(int j=0;j<Game_2048.cols;j++){
				if(!stopUpJudge(j))return true;	
			}
			break;
		case KeyEvent.VK_DOWN:
			for(int j=0;j<Game_2048.cols;j++){

				if(!stopDownJudge(j))return true;
			}
//			System.out.println("Down");

			break;
		case KeyEvent.VK_RIGHT:
			for(int i=0;i<Game_2048.rows;i++){
				if(!stopRightJudge(i))return true;
			}
			break;
		case KeyEvent.VK_LEFT:
			for(int i=0;i<Game_2048.rows;i++){
				if(!stopLeftJudge(i))return true;
			}
			break;
		default:
			return false;
		}
		return false;
		
	}
	public void winJudgement(){
		for(int i=0;i<Game_2048.rows;i++){
			for(int j=0;j<Game_2048.cols;j++){
				if(blocks[i][j]!=null){
//						System.out.println(blocks[i][j].getDigit());
					if(blocks[i][j].getDigit().equals("2048")){
						try {
							System.out.println("hello");
							JDialog dialog=new JDialog();
							JLabel label =new JLabel("WELL DONE",JLabel.CENTER);
							dialog.setTitle("WIN");
							dialog.add(label);
							dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
							dialog.addWindowListener(new WindowListener() {
								
								@Override
								public void windowOpened(WindowEvent e) {
									// TODO Auto-generated method stub
									
								}
								
								@Override
								public void windowIconified(WindowEvent e) {
									// TODO Auto-generated method stub
									
								}
								
								@Override
								public void windowDeiconified(WindowEvent e) {
									// TODO Auto-generated method stub
									
								}
								
								@Override
								public void windowDeactivated(WindowEvent e) {
									// TODO Auto-generated method stub
									
								}
								
								@Override
								public void windowClosing(WindowEvent e) {
									System.exit(0);
									// TODO Auto-generated method stub
									
								}
								
								@Override
								public void windowClosed(WindowEvent e) {
									// TODO Auto-generated method stub
									
								}
								
								@Override
								public void windowActivated(WindowEvent e) {
									// TODO Auto-generated method stub
									
								}
							});
							dialog.setSize(204, 94);
							dialog.setVisible(true);
//							wait();
							break;
						} catch (Exception e) {
							// TODO: handle exception
						}
					}
				}
			}
		}
	}
}
