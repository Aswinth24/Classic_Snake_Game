import java.util.*;
public class Game
{
	 Scanner input=new Scanner(System.in);
	 int row;
	 String[][] board;
	int col;
	LinkedList<int[]> snake=new LinkedList<>();
	int score=0;
	public void entry()
	{
		
		clearScreen();
		int count=0;
		int appleRow=0;
		int appleCol=0;
		row=input.nextInt();
		col=input.nextInt();
		board=new String[row][col];
		for(int i=0;i<row;i++)
		   Arrays.fill(board[i]," ");
		board[0][0]=board[row-1][0]=board[row-1][col-1]=board[0][col-1]="+";
		
		for(int i=1;i<col-1;i++)
				board[0][i]=board[row-1][i]="-";
		for(int i=1;i<row-1;i++)
			board[i][0]=board[i][col-1]="|";   
	    //snakeInitialize
		//----------------------------
		for(int i=0;i<9;i++){
			if(i==0)
			{
				board[row/2][col/2]="<";
				snake.add(new int[]{row/2,col/2});
			}
			else{
		      board[row/2][col/2+i]="0";
		     snake.add(new int[]{row/2,(col/2)+i});
			}
		}
		//------------------------------
		display();
		while(true&&count<=5)
		{
			
			 boolean flag=false;
			String str=input.next();
			input.nextLine();
			for(int i=0;i<str.length();i++)
			{
				while(true&&board[appleRow][appleCol].charAt(0)!='@')
			   {
				 appleRow=(int)System.currentTimeMillis()%row;
	             appleCol=(int)System.currentTimeMillis()%col;
				if(board[appleRow][appleCol].charAt(0)==' ')
				{
					board[appleRow][appleCol]="@";
					count++;
					break;
				}
			 }
			switch(str.charAt(i))
			{
				case'a':
			        flag=left();
				    clearScreen();
				    display();
				   break;
				   case 's':
				     flag=down();
				     clearScreen();
				     display();
					 break;
				 case'd':
				     flag=right();
				     clearScreen();
				     display();
					 break;
				case 'w':
					 flag=top();
				     clearScreen();
				     display();
					 break;
				case 'e':
				  return;	  
			}
			
			if(flag==false)
			{
				clearScreen();
			   System.out.println("\t\t\tYou Lost");
			   return ;
			}
		}
		}
		
		
	}
	public boolean left()
	{
		 int index[]=snake.getFirst();
		  
		 if(index[1]-1>0&&!board[index[0]][index[1]-1].equals("0"))
		 {
			  
			 if(board[index[0]][index[1]-1].charAt(0)=='@')
			 {
				 board[index[0]][index[1]]="0";
			     board[index[0]][index[1]-1]="<";
			     snake.addFirst(new int[]{index[0],index[1]-1});
					score+=10;
			 }
			 else  
			 {
				 board[index[0]][index[1]]="0";
			     board[index[0]][index[1]-1]="<";
			     snake.addFirst(new int[]{index[0],index[1]-1});
				 int index1[]=snake.getLast();
				 board[index1[0]][index1[1]]=" ";
				 snake.removeLast();
			 }
			 return true;
		 }
		  else if(index[1]-1==0&&board[index[0]][col-2].charAt(0)!='0')
		 {
			board[index[0]][col-2]="<";
			board[index[0]][index[1]]="0";
			 snake.addFirst(new int[]{index[0],col-2});
			 int index1[]=snake.getLast();
			 board[index1[0]][index1[1]]=" ";
			 snake.removeLast();
			 return true;
		}
	return false;  
	}
	public boolean down()
	{
		 int index[]=snake.getFirst();
		 if(index[0]+1<row-1&&!board[index[0]+1][index[1]].equals("0"))
		 {
			
             if(board[index[0]+1][index[1]].charAt(0)=='@'){
				  board[index[0]+1][index[1]]="v";
			 board[index[0]][index[1]]="0";
			 snake.addFirst(new int[]{index[0]+1,index[1]});
					score+=10;
			 }
            else{
				board[index[0]+1][index[1]]="v";
				board[index[0]][index[1]]="0";
				snake.addFirst(new int[]{index[0]+1,index[1]});				
				 int index1[]=snake.getLast();
				 board[index1[0]][index1[1]]=" ";
				 snake.removeLast();
		      }
			 return true;
		 }
		 else if(index[0]+1==row-1&&board[1][index[1]].charAt(0)!='0')
		{
			 board[1][index[1]]="v";
			 board[index[0]][index[1]]="0";
			 snake.addFirst(new int[]{1,index[1]});	 
			 int index1[]=snake.getLast();
			 board[index1[0]][index1[1]]=" ";
			 snake.removeLast();
			 return true;
		}
		return false;
	}
	public boolean right()
	{
		int index[]=snake.getFirst();
		
		  if(index[1]+1<col-1&&!board[index[0]][index[1]+1].equals("0"))
		 {
			  
			 if(board[index[0]][index[1]+1].charAt(0)=='@') 
			 {
				 board[index[0]][index[1]+1]=">";
			  board[index[0]][index[1]]="0";
			 snake.addFirst(new int[]{index[0],index[1]+1});
				 score+=10;}
			 
		    else
			{
			  board[index[0]][index[1]+1]=">";
			  board[index[0]][index[1]]="0";
			 snake.addFirst(new int[]{index[0],index[1]+1});
			 int index1[]=snake.getLast();
			 board[index1[0]][index1[1]]=" ";
			 snake.removeLast();
			}
			 return true;
		}
		else if(index[1]+1==col-1&&board[index[0]][1].charAt(0)!='0')
		{
			  board[index[0]][1]=">";
			  	board[index[0]][index[1]]="0";
			 snake.addFirst(new int[]{index[0],1});
			 int index1[]=snake.getLast();
			 board[index1[0]][index1[1]]=" ";
			 snake.removeLast();
			 return true;
		}
		return false;
	}
	public boolean top()
	{	
		 int index[]=snake.getFirst();
		 if(index[0]-1>0&&!board[index[0]-1][index[1]].equals("0"))
		 {
			 
			 if(board[index[0]-1][index[1]].charAt(0)=='@') {
				  board[index[0]-1][index[1]]="^";
			 	board[index[0]][index[1]]="0";
			 snake.addFirst(new int[]{index[0]-1,index[1]});
			 score+=10;                     
			 
			 }
			 else{
				  board[index[0]-1][index[1]]="^";
			 	board[index[0]][index[1]]="0";
			 snake.addFirst(new int[]{index[0]-1,index[1]});
				 int index1[]=snake.getLast();
				 board[index1[0]][index1[1]]=" ";
				 snake.removeLast();
			 }
			 return true;
		}
		else if(index[0]-1==0&&board[row-2][index[1]].charAt(0)!='0')
		{
			 board[row-2][index[1]]="^";
			 board[index[0]][index[1]]="0";
			 snake.addFirst(new int[]{row-2,index[1]});
			 int index1[]=snake.getLast();
			 board[index1[0]][index1[1]]=" ";
			 snake.removeLast();
			 return true;
		}
		return false;
	}
	public void display()
	{
		System.out.print("\n\t\tScore : "+score+"\n");
		for(int i=0;i<row;i++){
			System.out.print("\t");
			for(int j=0;j<col;j++)
				System.out.print(board[i][j]);
		System.out.println();
		}
	}
	 public void clearScreen()
   {
	   try{
		   Thread.sleep(1000);
	      new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
	   }
	   catch(Exception e)
	   {	   
	   }
   }

}