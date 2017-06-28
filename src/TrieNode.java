//private static final int ALPHABETS = 26;//this throws an error as it is not a part of any class...lets face it..its "java"!! :P

import java.util.*;

public class TrieNode {
	TrieNode [] children;
	boolean isLeaf;
	
	TrieNode(){
		int n = AutoCompleteUtility.ALPHABET_SIZE;
		children = new TrieNode[n];
		isLeaf=false;//marks the end of the string
		
		if(children==null){
			System.out.println("ERROR: Not enough memory!");
			System.exit(-1);
		}
		
		for(int i=0;i<n;i++){
			children[i] = null;
		}	
	}
	
	public int getCharIndex(char ch){
		return (int)ch-(int)'a';
	}
	
	public void insertIntoTrie(String key){
		int l=key.length(),index;
		TrieNode current = this;
		
		for(int i=0;i<l;i++){
			char ch = key.charAt(i);
			index=getCharIndex(ch);
			if(current.children[index]==null){
				current.children[index]=new TrieNode();
			}
			current=current.children[index];
		}
		current.isLeaf=true;
		return;
	}
	
	
	public int checkIfPresent(String key){
		int l=key.length(),index;
		TrieNode current=this;
		
		for(int i=0;i<l;i++){
			char ch = key.charAt(i);
			index=getCharIndex(ch);
			if(current.children[index]==null){
				return 0;//key not found ..so return 0
			}//hence there are no words with that prefix
			current=current.children[index];
		}
		if(current.isLeaf==true)
			return 1;//exact key found in the dictionary
		return 0;
		
	}
	
	
	public void printAllWords(TrieNode root,String prefix){
		if(root.isLeaf==true)
			System.out.println(prefix);
		int n = AutoCompleteUtility.ALPHABET_SIZE;
		for(int i=0;i<n;i++){
			if(root.children[i]!=null){
				StringBuilder temp = new StringBuilder(prefix);
				StringBuilder new_prefix = temp.append((char)('a'+i));
				printAllWords(root.children[i],new_prefix.toString());
			}
		}
	}
	
	
	//for actual use
	public ArrayList<String> printAllWords(TrieNode root,String prefix,ArrayList<String> result){
		if(root.isLeaf==true)
			result.add(prefix);
		
		int n = AutoCompleteUtility.ALPHABET_SIZE;
		for(int i=0;i<n;i++){
			if(root.children[i]!=null){
				StringBuilder temp = new StringBuilder(prefix);
				StringBuilder new_prefix = temp.append((char)('a'+i));
				printAllWords(root.children[i],new_prefix.toString(),result);
			}
		}
		return result;
	}
	
	public ArrayList<String> displayAllKeysWithPrefix(String prefix){
		
		TrieNode current = this;
		int l=prefix.length();
		ArrayList <String> result = new ArrayList<>();
		for(int i=0;i<l;i++){
			char ch = prefix.charAt(i);
			int index = getCharIndex(ch);
			if(current.children[index]==null)
				return null;
			current=current.children[index];
		}
		printAllWords(current,prefix,result);
		return result;
	}


}
