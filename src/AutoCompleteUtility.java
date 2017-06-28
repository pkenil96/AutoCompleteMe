import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class AutoCompleteUtility {

	/**
	 * @param args
	 */
	public static final int ALPHABET_SIZE = 26;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final Trie t = new Trie();
		t.root.insertIntoTrie("kenil");
		t.root.insertIntoTrie("kepler");
		t.root.insertIntoTrie("ketchup");
		t.root.insertIntoTrie("killer");
		t.root.insertIntoTrie("mason");
		t.root.insertIntoTrie("ralph");
		t.root.insertIntoTrie("kiss");
		t.root.insertIntoTrie("kutte");
		t.root.insertIntoTrie("kettle");
		
		
		JFrame frame = new JFrame("AutoCompleteUtility");
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		
		JLabel label = new JLabel("Enter search term:");
		final JTextField textField = new JTextField();
		textField.setColumns(10);
		JButton button = new JButton();
		final JTextArea display = new JTextArea();
		display.setColumns(10);
		display.setRows(10);
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
					System.out.println(result);
					for(int i=0;i<result.size();i++)
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
					System.out.println(result);
					for(int i=0;i<result.size();i++)
						display.append(result.get(i)+"\n");
				}
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				//JOptionPane.showMessageDialog(null,e);
				display.setText("");
				String prefix = textField.getText();
				ArrayList <String> result = t.root.displayAllKeysWithPrefix(prefix);
				if(result==null){
					display.setText("No suggestions");
				} else {
					System.out.println(result);
					for(int i=0;i<result.size();i++)
						display.append(result.get(i)+"\n");
				}
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				//JOptionPane.showMessageDialog(null,e);
				display.setText("");
				String prefix = textField.getText();
				ArrayList <String> result = t.root.displayAllKeysWithPrefix(prefix);
				
				if(result==null){
					display.setText("No suggestions");
				} else {
					System.out.println(result);
					for(int i=0;i<result.size();i++)
						display.append(result.get(i)+"\n");
				}
			
			}
		});
		
		
		System.out.println("Running....");
		
	}

}
