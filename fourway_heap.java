

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class fourway_heap {

	public fourway_heap(String filepath) {
		// TODO Auto-generated constructor stub
		Frequency_Table = new HashMap();
        path = filepath;
        fwheap = new ArrayList<>();
        fwheap.add(null);
        fwheap.add(null);
        fwheap.add(null);
        Code = "";
	}

	public Integer getc1Index(Integer n)
	{
		return 4*n + 1 - 9 < fwheap.size() - 1 ? 4*n + 1 - 9 : -1;
	}
	
	public Integer getc2Index(Integer n)
	{
		return 4*n + 2 - 9 < fwheap.size() - 1 ? 4*n + 2 - 9 : -1;
	}
	
	public Integer getc3Index(Integer n)
	{
		return 4*n + 3 - 9 < fwheap.size() - 1 ? 4*n + 3 - 9 : -1;
	}
	
	public Integer getc4Index(Integer n)
	{
		return 4*n + 4 - 9 < fwheap.size() - 1 ? 4*n + 4 - 9 : -1;
	}
	
	public Integer getParentIndex(Integer n)
	{
		if(n == 3)
			return 3;
		return n/4 + 2;
	}
	
	public void build()
	{
		 try{
	            Scanner s = new Scanner(new File(path));
	            
	            
	            while(s.hasNextInt())
	            {
	            	int num = s.nextInt();
	            	Frequency_Table.put(num, (Frequency_Table.get(num) == null ? 1 : Frequency_Table.get(num)+1));
	            	
	            }
	            
	            starttime = System.nanoTime();
	            for(Map.Entry<Integer, Integer> entry : Frequency_Table.entrySet()){
	            	fourway_heap_node num = new fourway_heap_node();
	            	num.setData(entry.getKey());
	            	num.setFreq(entry.getValue());
	                insertintoheap(num);
	            	
	            }
	        	
	            
		}		 
	            catch(Exception E)
	            {
	            	E.printStackTrace();
	            }
	    
	       
	        
		
	}
	
	public void insertintoheap(fourway_heap_node num)
	{
		fwheap.add(num);
		Integer index = fwheap.size() - 1;
		if(fwheap.size() == 4)
		{
			return;
		}
		fourway_heap_node p = fwheap.get(getParentIndex(index));
		
		
		
		while(num.getFreq() < p.getFreq() && getParentIndex(index) >= 3)
		{
			swap(getParentIndex(index), index);
			index = getParentIndex(index);
			p = fwheap.get(getParentIndex(index));
		}
		

	}
	
	  public Integer getminchildindex(Integer numindex)
	  {
		  Integer c1 = getc1Index(numindex);
	      Integer c2 = getc2Index(numindex);
	      Integer c3 = getc3Index(numindex);
	      Integer c4 = getc4Index(numindex);
	      Integer min12, min34, minchild, minindex ; 
	      
	      if(c1 != -1 && c2 != -1)
	      {
	    	  min12 = fwheap.get(c1).getFreq() < fwheap.get(c2).getFreq() ? c1 : c2 ;
	      }
	      
	      else if(c1 == -1 && c2 == -1)
	      {
	    	  min12 = -1;
	      }
	      
	      else if(c1 == -1)
	      {
	    	  min12 = c2;
	      }
	      
	      else
	      {
	    	  min12 = c1;
	      }
	      
	      
	      if(c3 != -1 && c4 != -1)
	      {
	    	  min34 = fwheap.get(c3).getFreq() < fwheap.get(c4).getFreq() ? c3 : c4 ;
	      }
	      
	      else if(c3 == -1 && c4 == -1)
	      {
	    	  min34 = -1;
	      }
	      
	      else if(c4 == -1)
	      {
	    	  min34 = c3;
	      }
	      
	      else
	      {
	    	  min34 = c4;
	      }
	      
	      
	      if(min12 != -1 && min34 != -1)
	      {
	    	  minindex = fwheap.get(min12).getFreq() < fwheap.get(min34).getFreq() ? min12 : min34 ;
	      }
	      
	      else if(min12 == -1 && min34 == -1)
	      {
	    	  minindex = -1;
	      }
	      
	      else if(min34 == -1)
	      {
	    	  minindex = min12;
	      }
	      
	      else
	      {
	    	  minindex = min34 ;
	      }
	      
		  return minindex;
	  }
	  
	  
	  
	  
	  public void minheap(Integer index){
		  
		  
		  Integer minc = getminchildindex(index); 
		  
		  if(minc == -1){
			  
		      return;  
		  }
		  swap(minc, index);
	      minheap(minc);
	  }
	        
	      
	  
	  public void swap(Integer node1, Integer node2){
	        fourway_heap_node temp = fwheap.get(node1);
	        fwheap.set(node1, fwheap.get(node2));
	        fwheap.set(node2, temp);
	    }
	   
	
	
	  public fourway_heap_node extractMin(){
		  
		  
	        if(fwheap.size() < 4)
	        {
	            return null;
	        }
	        
	        fourway_heap_node min = fwheap.get(3);
	        
	        if(fwheap.size() - 1 == 3)
	        {
	            fwheap.remove(3);
	            return min;
	        }
	        
	        fwheap.set(3, fwheap.remove(fwheap.size()-1));
	        minheap(3);
	        return min;
	       
	    }
	  
	  
	  public void printheap()
		{
			for(fourway_heap_node n: fwheap)
	        {
				if(n  != null)
	        	System.out.println(n.getFreq());
	        }
		}
		  
	  
	  public node createhufftree()
	    {
	        while(fwheap.size() != 4)
	        {
	            insertintoheap(createNode(extractMin(), extractMin()));
	         }
	        return fwheap.get(3).getnode();
	    }
	   
	   
	    public void createhuffcodes(node root, String code, FileWriter w) throws IOException
	    {
	        if(root.getlc() == null && root.getrc() == null )
	        {
	            w.write(root.fetchData()+" " +code+"\n");
	           
	        }
	        else
	        {
	            String l = code+"0";
	            String r = code+"1";
	            createhuffcodes(root.getlc(), l, w);
	            createhuffcodes(root.getrc(), r, w);
	        }
	       
	       
	    }

	    public fourway_heap_node createNode(fourway_heap_node N1, fourway_heap_node N2)
	    {
	        fourway_heap_node result = new fourway_heap_node();
	        result.setlc(N1);
	        result.setrc(N2);
	        result.setFreq(N1.getFreq() + N2.getFreq());
	        return result;
	    }

	
	
	 public long starttime;
	 private String Code;
	 private List<fourway_heap_node> fwheap;
	 private HashMap<Integer, Integer> Frequency_Table;
     private String path;

}
