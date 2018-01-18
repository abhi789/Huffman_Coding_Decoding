

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class pairing_heap {
	
	public pairing_heap(String filepath){
		root = null;
		Frequency_Table = new HashMap<>();
        path = filepath;
        arraylist = new ArrayList<>();
	}
	
	public void build()
	{
		 try{
	            Scanner s = new Scanner(new File(path));
	 
	            while(s.hasNextInt())
	            {
	            	int num = s.nextInt();
	            	Frequency_Table.put(num, (Frequency_Table.get(num) == null ? 1 : Frequency_Table.get(num)+1));  //build frequency table using hashmap
	            }
	        
	            s.close();
		 
		 start = System.nanoTime();
		 for(Map.Entry<Integer, Integer> entry : Frequency_Table.entrySet()){
         	pairing_heap_node num = new pairing_heap_node();
         	num.setData(entry.getKey());
         	num.setFreq(entry.getValue());
         	insertintoheap(num);																					//insert node into heap
		 }
		 }
	            catch(Exception E)
	            {
	            	E.printStackTrace();
	            }
		
		
	}
	
	
	public pairing_heap_node getroot()
	{
		return root;
	}
	
	public void printheap(pairing_heap_node n)
	{
		System.out.println(n.getFrequency());									//print heap for checking
		while(n.getrightptr() != null)
		{
			n = n.getrightptr();
			System.out.println(n.getFrequency());
		}

		if(n.getchildptr() != null)
		{
			printheap(n.getchildptr());
		}
		
		
	}
	
	
	 public pairing_heap_node Meld(pairing_heap_node n)
	    {
	        pairing_heap_node next ;															//meld the pairing heap
	        while(n != null)
	        {
	            next = n.getrightptr();
	            n.setrightptr(null);
	            n.setleftptr(null);
	            arraylist.add(n);
	            n = next;
	     
	           
	        }
	        
	        while(arraylist.size() > 1)
	        {
	            pairing_heap_node node1 = arraylist.remove(0);
	            pairing_heap_node node2 = arraylist.remove(0);
	            if(node1.getFrequency() <= node2.getFrequency())
	            {
	                if(node1.getchildptr() == null)
	                {
	                    node1.setchildptr(node2);
	                    node2.setleftptr(node1);
	                    arraylist.add(node1);
	                }
	                else//n1 has child
	                {
	                    pairing_heap_node temp = node1.getchildptr();
	                    node1.setchildptr(node2);
	                    node2.setrightptr(temp);
	                    temp.setleftptr(node2);
	                    node2.setleftptr(node1);
	                    arraylist.add(node1);
	                }
	               
	            }
	            else
	            {
	                if(node2.getchildptr() == null)
	                {
	                    node2.setchildptr(node1);
	                    node1.setleftptr(node2);
	                    arraylist.add(node2);
	                }
	                else//n2 has child
	                {
	                    pairing_heap_node temp = node2.getchildptr();
	                    node2.setchildptr(node1);
	                    node1.setleftptr(node2);
	                    node1.setrightptr(temp);
	                    temp.setleftptr(node1);
	                    arraylist.add(node2);
	                }
	               
	            }
	           
	        }
	        //System.out.println("sup: " + arrlist.get(0).getChild().getRight().getRight().getFrequency());
	        return arraylist.remove(0);
	       
	    }
	
	 public pairing_heap_node removemin()
	    {
	        pairing_heap_node min = root;											//set root as min
	        
	       
	        if(root.getchildptr() != null)											//meld
	        {
	            root = Meld(root.getchildptr());
	        }
	        else
	        {
	        	root = null;
	        }
	        min.setchildptr(null);
			return min;
	    }
	        
	 public void insertintoheap(pairing_heap_node n1)
	    {
	       
	        if(root == null)														//insert new node into heap
	        {
	            root = n1;
	        }
	        else
	        {
	            if(root.getFrequency() <= n1.getFrequency())
	            {
	                if(root.getchildptr() == null)
	                {
	                    root.setchildptr(n1);
	                    n1.setleftptr(root);
	                }
	               
	               
	                else
	                {
	                    pairing_heap_node temp = root.getchildptr();
	                    root.setchildptr(n1);
	                    n1.setrightptr(temp);
	                    temp.setleftptr(n1);
	                    n1.setleftptr(root);
	                }
	            }
	            else
	            {
	                n1.setchildptr(root);
	                root.setleftptr(n1);
	                root = n1;           
	            }
	        }
	    }
	
	 
	 public node createhufftree()
	    {
	        while(root.getchildptr() != null)
	        {
	            insertintoheap(createNode(removemin(), removemin()));							//build huff tree
	           
	        }
	        return root.getnode();
	    }
	   
	   
	    public void createhuffcodes(node root, String code, FileWriter w) throws IOException
	    {
	        if(root.getlc() == null && root.getrc() == null )
	        {
	            w.write(root.fetchData()+" " +code+"\n");												//write huff ocdes to file
	           
	        }
	        else
	        {
	            String l = code+"0";
	            String r = code+"1";
	            createhuffcodes(root.getlc(), l, w);
	            createhuffcodes(root.getrc(), r, w);
	        }
	      
	       
	    }

	    public pairing_heap_node createNode(pairing_heap_node N1, pairing_heap_node N2)
	    {
	        pairing_heap_node result = new pairing_heap_node();										//create new huffman node
	        result.setlc(N1);
	        result.setrc(N2);
	        result.setFreq(N1.getFrequency() + N2.getFrequency());
	        return result;
	    }

	
	
	
	
	
	
	
	public long start;
	private HashMap<Integer, Integer>  Frequency_Table;
    private String path;
	private ArrayList<pairing_heap_node> arraylist;
	private pairing_heap_node root; 
}
