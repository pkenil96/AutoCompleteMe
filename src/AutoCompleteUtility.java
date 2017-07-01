import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.sql.Connection;
import java.sql.DriverManager;

public class AutoCompleteUtility {

	/**
	 * @param args
	 */
	public static final int ALPHABET_SIZE = 26;
	
	public static int min(int a,int b){
		return (a<=b)?a:b;
	}
	
	public static void main(String[] args){
		
		final String url = "jdbc:mysql://localhost";
		final String user = "root";
	
		final Trie t = new Trie();
		
		/*
			t.root.insertIntoTrie("kenil");
			t.root.insertIntoTrie("kepler");
			t.root.insertIntoTrie("ketchup");
			t.root.insertIntoTrie("killer");
			t.root.insertIntoTrie("mason");
			t.root.insertIntoTrie("ralph");
			t.root.insertIntoTrie("kiss");
			t.root.insertIntoTrie("kutte");
			t.root.insertIntoTrie("kettle");
		*/
		
		//lets just grab a text field containing words and put them in the tree
		try{

			File file = new File("words.txt");			
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String word;
			
			while((word = br.readLine()) != null){
				//System.out.println(word);
				t.root.insertIntoTrie(word);
			}
			
			br.close();
			fr.close();
			
			JFrame frame = new JFrame("AutoCompleteUtility");
			JPanel panel = new JPanel();
			panel.setLayout(new FlowLayout());
			
			JLabel label = new JLabel("Enter search term:");
			final JTextField textField = new JTextField();
			textField.setColumns(12);
			JButton button = new JButton();
			final JTextArea display = new JTextArea();
			display.setColumns(12);
			display.setRows(5);
			button.setText("Find Suggestions");
			
			panel.add(label);
			panel.add(textField);
			panel.add(button);
			panel.add(display);
			
			frame.add(panel);
			frame.setSize(500,500);
			frame.setLocationRelativeTo(null);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);

			
			button.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					display.setText("");
					String prefix = textField.getText();
					ArrayList <String> result = t.root.displayAllKeysWithPrefix(prefix);
					if(result==null){
						display.setText("No suggestions...");
					} else {
						int n = min(5,result.size());//only display the 5 searches
						for(int i=0;i<n;i++)
							display.append(result.get(i)+"\n");
					}
					
				}
			});
			
			/*
			 * 
				The appropriate listener in Java's swing to track changes in the text content of a JTextField is a DocumentListener,
				that you have to add to the document of the JTextField:
				
				myTextField.getDocument().addDocumentListener(new DocumentListener() {
				    // implement the methods
				});
			*/		
			
			
			textField.getDocument().addDocumentListener(new DocumentListener() {
				@Override
				
				public void removeUpdate(DocumentEvent e) {
					// TODO Auto-generated method stub
					display.setText("");
					String prefix = textField.getText();
					ArrayList <String> result = t.root.displayAllKeysWithPrefix(prefix);
					if(result==null){
						display.setText("No suggestions");
					} else {
						int n = min(5,result.size());
						for(int i=0;i<n;i++)//only display the 5 searches
							display.append(result.get(i)+"\n");
					}
				}
				
				@Override
				public void insertUpdate(DocumentEvent e) {
					// TODO Auto-generated method stub
					display.setText("");
					String prefix = textField.getText();
					ArrayList <String> result = t.root.displayAllKeysWithPrefix(prefix);
					if(result==null){
						display.setText("No suggestions");
					} else {
						int n = min(5,result.size());
						for(int i=0;i<n;i++)//only display the 5 searches
							display.append(result.get(i)+"\n");
					}
				}
				
				@Override
				public void changedUpdate(DocumentEvent e) {
					// TODO Auto-generated method stub
					display.setText("");
					String prefix = textField.getText();
					ArrayList <String> result = t.root.displayAllKeysWithPrefix(prefix);
					
					if(result==null){
						display.setText("No suggestions");
					} else {
						int n = min(5,result.size());//only display the 5 searches
						for(int i=0;i<result.size();i++)
							display.append(result.get(i)+"\n");
					}
				
				}
			});
		System.out.println("Running....");
			

		} catch(FileNotFoundException e){
			System.out.println("ERROR: The specified file does not exist");
		} catch(IOException e){
			System.out.println("Something went wrong!");
		}
		
		//connecting to the database to fetch the meaning
		try{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Connecting to the database...");
			
			Connection con = (Connection)DriverManager.getConnection(url,user,"");
			if(con==null){
				System.out.println("Couldn't connect to the database!");
				System.exit(-1);
			} else {
				System.out.println("Connected...");
				
				//fetch the meaning
				
			}
					
		} catch(Exception e){
			System.out.println(e.getMessage());
		}
	}

}
