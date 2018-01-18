

import java.io.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class binary_heap 
{

    public binary_heap() {
        heapArr = new ArrayList<node>();
        length = 0;
        Frequency_Table = new HashMap<>();
        
    }
    

  	public binary_heap(String ipFilePath)
  	{
  		inputFilePath =  ipFilePath;
  		heap = new ArrayList<>();
  		Frequency_Table = new HashMap<>();
  		
  	}
    

    public void insert(node x){
    	;
        heapArr.add(x);											//insert element into heap
        length++;
        shiftup(length - 1);
        
    }
    
    
    public node extractMin(){
        if(heapArr.isEmpty()){
            System.err.println("Can not remove from empty heap");
        }
        node first = heapArr.get(0);										//remove min element
        node end = heapArr.get(length - 1);
        heapArr.set(0, end);
        heapArr.remove(length-1);
        length--;
        shiftdown(0);
        return first;
    }
    
    public boolean isEmpty(){
        return length==0;
    }
    
    public int getSize(){
        return length;
    }
    
    public void buildHeap(String inputFilePath)
	{
		try 
		{
			Scanner sc = new Scanner(new File(inputFilePath));
		    while (sc.hasNextInt()) 									//generate frequency table from input file
		    {
		    	
		        int number = sc.nextInt();
		        Frequency_Table.put(number, (Frequency_Table.get(number) == null ? 1 :  Frequency_Table.get(number)+1 )); 
		    }
		   
		    startTime = System.nanoTime();
		    for( Map.Entry<Integer, Integer> entry : Frequency_Table.entrySet()) 
		    {
		    	node num = new node();
		
		    	num.setData(entry.getKey());
		    	num.setFreq(entry.getValue());
		    
		    	insert(num);
		    	
		    }
		    sc.close();
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 	
	}
    
   
 
    
    private void shiftup(int n){
        int parent_index = parent(n);
        while (n > 0 && heapArr.get(parent_index).getFreq() > heapArr.get(n).getFreq()) {
            swapnodes(parent_index, n);
            n = parent_index;
            parent_index = parent(n);
        }
    }
    
    private void shiftdown(int n){
        int minIndex = minIndex(n);
        while (minIndex != -1 && heapArr.get(minIndex).getFreq() < heapArr.get(n).getFreq()) {
            swapnodes(minIndex, n);
            n = minIndex;
            minIndex = minIndex(n);
        }
        
    }
    
    
  
    
    private int minIndex(int n) {
        if (left(n) > length - 1) return -1;    
        if (right(n) > length - 1) return left(n);  
        if(heapArr.get(left(n)).getFreq() <= heapArr.get(right(n)).getFreq()){
            return left(n);
        }
        else{
            return right(n);
        }
    }

    private void swapnodes (int index1, int index2){
        node temp = heapArr.get(index1);								//for swapping nodes
        heapArr.set(index1, heapArr.get(index2));
        heapArr.set(index2, temp);
    }
    
    private int parent(int n) { return n == 0 ? -1 : (n - 1) >>> 1; }
    private int left(int n) { return n * 2 + 1; }
    private int right(int n) { return n * 2 + 2; }
    
    public node createHufftree()					
	{	
		while(heapArr.size() != 1)
		{
			insert(createnode(extractMin(), extractMin()));							//create huffman tree
			
			
		}
		return heapArr.get(0);
	}
    
    public void createHuffcodes(node root, String s, FileWriter w) throws IOException
	{
		if(root.getlc() == null && root.getrc() == null )
		{
			w.write(root.fetchData()+" " +s+"\n");							//write huffman codes to file
			
		}
		else
		{
			String l = s+"0";
			String r = s+"1";
			createHuffcodes(root.getlc(), l, w);
			createHuffcodes(root.getrc(), r, w);
		}
	}
    
    
	
	
	
	public node createnode(node nodeOne, node nodeTwo)
	{
		node n = new node();
		n.setlc(nodeOne);
		n.setrc(nodeTwo);
		n.setFreq(nodeOne.getFreq() + nodeTwo.getFreq());
		return n;
	}
    
    private int length;
    private ArrayList<node> heapArr;
	private String inputFilePath;
	private List<node> heap;
	private HashMap<Integer, Integer> Frequency_Table;
	public long startTime;
	
	
}