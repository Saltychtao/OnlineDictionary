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
		
		wip.jbtSearch.addActionListener(new ActionListener (){
			public void actionPerformed(ActionEvent e){
				if (!wip.jcb1.isSelected() && !wip.jcb2.isSelected() && !wip.jcb3.isSelected())
				{
						Socket socket = new Socket();
						String meaning1 = socket.get(wip.jtfWord.getText(), 1);
						rp1.jtaResult.setText(meaning1);
						String meaning2 = socket.get(wip.jtfWord.getText(), 2);
						rp2.jtaResult.setText(meaning2);
						String meaning3 = socket.get(wip.jtfWord.getText(), 3);
						rp3.jtaResult.setText(meaning3);

				}
				else
				{
					if (wip.jcb1.isSelected())
					{
						Socket socket = new Socket();
						String meaning1 = socket.get(wip.jtfWord.getText(), 1);
						rp1.jtaResult.setText(meaning1);
					}
					if (wip.jcb2.isSelected())
					{
						Socket socket = new Socket();
						String meaning2 = socket.get(wip.jtfWord.getText(), 2);
						rp1.jtaResult.setText(meaning2);
					}
					if (wip.jcb3.isSelected())
					{
						Socket socket = new Socket();
						String meaning3 = socket.get(wip.jtfWord.getText(), 3);
						rp1.jtaResult.setText(meaning3);
					}
				}
				;
			}
		});
	}
	public class WordInputPanel extends JPanel
	{
		private JTextField jtfWord;
		private JButton jbtSearch;
		public JCheckBox jcb1;
		public JCheckBox jcb2;
		public JCheckBox jcb3;
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
			return 0;
	}

	
	public static void main(String[] args)
	{
		UI ui = new UI();
		
	}
}
