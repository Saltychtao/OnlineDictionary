package DictionaryClient;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
public class UI extends JFrame{
	
	private resultPanel rp1;
	private resultPanel rp2;
	private resultPanel rp3;
	private WordInputPanel wip;
	
	public UI()
	{
		setTitle("MainUI");
		setLocation(300, 100);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout(10,10));
		
		rp1 = new resultPanel();
		rp2 = new resultPanel();
		rp3 = new resultPanel();
/*		
		rp1.setBackground(Color.BLACK);
		rp2.setBackground(Color.BLUE);
		rp3.setBackground(Color.cyan);
	*/	
		wip = new WordInputPanel();
		
		add(wip,BorderLayout.NORTH);
		add(rp1,BorderLayout.EAST);
		add(rp2,BorderLayout.CENTER);
		add(rp3,BorderLayout.WEST );
		
		setSize(800,600);
		setVisible(true);
	}
	public class WordInputPanel extends JPanel
	{
		private JTextField jtfWord;
		private JButton jbtSearch;
		private JCheckBox jcb1;
		private JCheckBox jcb2;
		private JCheckBox jcb3;
		public WordInputPanel()
		{
			setOpaque(false);
			jtfWord = new JTextField(30);
			jbtSearch = new JButton("Search");
			jcb1 = new JCheckBox();
			jcb1.setText("Baidu");
			jcb2 = new JCheckBox();
			jcb2.setText("Bing");
			jcb3 = new JCheckBox();
			jcb3.setText("Youdao");
			add(jtfWord);
			add(jbtSearch);
			add(jcb1);
			add(jcb2);
			add(jcb3);
		}
	}
	
	public class resultPanel extends JPanel
	{
		private JTextArea jtaResult;
		public resultPanel()
		{
			jtaResult = new JTextArea(25,20);
			jtaResult.setEditable(false);

			add(jtaResult);
			
		}
		
		public void setResult(String des)
		{
			if (des == "")
			{
				this.jtaResult.setText("");
			}
			else
			this.jtaResult.setText(des);
		}
	}
	public static int Main(String[] args) {
		// TODO Auto-generated method stub
		Socket client = new Socket();
		String meaning = "no";
		Scanner keyboard=new Scanner(System.in);   
		boolean exit=false;   
		while(!exit)   
		{   
		    System.out.println("Please input a word");   
		    System.out.println("If you want to exit, input the word: exit!");   
		    String inputword=keyboard.nextLine();   
		    if(inputword.equalsIgnoreCase("exit"))   
		    {   
		        exit=true;   
		    }    
		    else   
		    {   
		        meaning=client.get(inputword, 1)  ;
		        if(meaning.equals("no"))   
		        {   
		        System.out.println("The dictionary does not have this word!");   
		        }   
		        else   
		        {   
		        System.out.println(inputword+" means "+meaning);   
		            System.out.println();   
		        }   
		     }   
		           
		}
		keyboard.close();
		return 0;
	}

	
	public static void main(String[] args)
	{
		UI ui = new UI();
		
	}
}
