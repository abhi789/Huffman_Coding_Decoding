

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;


public class decoder {

	
	
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		EncodedFile = args[0];
		codeTab = args[1];
		long startTime = System.nanoTime();
		decode();
		long endTime = System.nanoTime();
		long duration = (endTime - startTime)/1000000;
		System.out.println("Time required for Decoding: "+duration + " ms");
		
	}
	
	private static node createHuffTree(String codeTablePath) throws NumberFormatException, IOException{ //Returns the root of the Huffman Tree

		BufferedReader br = new BufferedReader(new FileReader(codeTablePath));
		String line;
		node root = new node();
		node n;
	    while ((line = br.readLine())!= null) {
	    	if(!line.isEmpty() && line !=null){
	    		Integer number = Integer.parseInt(line.split(" ")[0]);
	    		String code = line.split(" ")[1];
	    		n = root;
	    		for(int i=0; i< code.length()-1;i++){
	    			if(code.charAt(i) == '0'){
	    				if(n.getlc() == null){
	    					n.setlc(new node());	    				
	    				}
	    				n = n.getlc();	    					
	    			}
	    			else if(code.charAt(i) == '1'){
	    				if(n.getrc() == null){
	    					n.setrc(new node());	    				
	    				}
	    				n = n.getrc();	
	    			}    			
	    		}
	    		if(code.charAt(code.length()-1) == '0'){
	    			n.setlc(new node());	
	    			n = n.getlc();
	    			n.setData(number);
	    		}
	    		else{
	    			n.setrc(new node());
	    			n = n.getrc();
	    			n.setData(number);
	    		}
	    		}
	    }
	    
	    return root;
	        	
	}
	
private static void printHuffmanTree(node root) {
		
		if(root.getlc() == null && root.getrc() == null){
			System.out.println(root.fetchData());;
		}
		else
		{
			printHuffmanTree(root.getlc());			
			printHuffmanTree(root.getrc());
		}
		
	}

public static String getEncodedString(String encodedPath) throws IOException{
    byte[] byteArray;
    StringBuilder outputStr=new StringBuilder();
    byteArray=Files.readAllBytes(new File(encodedPath).toPath());
    for(byte b: byteArray){
        outputStr.append(Integer.toBinaryString(b & 255 | 256).substring(1));
    }
   
    return outputStr.toString();
}
	
	
private static void decode() throws IOException{
    //read a bit at a time and traverse the tree until you fall off.
	node root = createHuffTree(codeTab);
	String encodedFS = getEncodedString(EncodedFile);
    PrintWriter w;
    try {
        w = new PrintWriter("./decoded.txt");
        node n = root;

        
        for(int i = 0; i<encodedFS.length() ; i++ ){
  
 
          if(i == encodedFS.length()-1){
            	
            }
                if( encodedFS.charAt(i) == '0' ){
                    
                    if (n.getlc() == null){
                       
                            w.print(n.fetchData()+"\n");
                            n = root.getlc();                        
                    }
                    else{
                    	
                        n = n.getlc();

                    }
                } 
                else{
                    
                    if (n.getrc() == null){
                        
                            w.print(n.fetchData()+"\n");
                            n = root.getrc();                                     
                    }
                    else{
                    	
                        n = n.getrc();
                    }
                }
            }
        w.print(n.fetchData());
        
        w.close();
    } catch (IOException ex) {
       
    }
    
}


	private static String codeTab;
	private static String EncodedFile;

}


