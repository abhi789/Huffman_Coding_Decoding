

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class encoder {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		_inputFile = args[0];
		long startTime = System.nanoTime();
		try {
			encode();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long endTime = System.nanoTime();
		long duration = (endTime - startTime)/1000000;
		System.out.println("Time required for encoding using Binary Heap: "+duration + " ms");
		
	}
	
	public static void encode() throws NumberFormatException, IOException{
			binary_heap bh = new binary_heap();
			BufferedOutputStream encodedFile = new BufferedOutputStream(new FileOutputStream("./encoded.bin"));
			FileWriter codeFile = new FileWriter("./code_table.txt");
			HashMap<Integer, String> codeTable = new HashMap<>();
			HashMap <Integer, Integer> Frequency_Table = buildFreqTable(_inputFile);
			for( Map.Entry<Integer, Integer> entry : Frequency_Table.entrySet()) {
			    	node number = new node();
			    	number.setData(entry.getKey());
			    	number.setFreq(entry.getValue());
			    	bh.insert(number);
			}
		
			node root = createHuffTree(bh);
			createHuffCode(root,"",codeTable, codeFile);
			BufferedReader br = new BufferedReader(new FileReader(_inputFile));
			String line;
			StringBuilder encodedFS = new StringBuilder("");
		    while ((line = br.readLine())!= null) {
		    	if(!line.isEmpty() && line !=null){
		    		int number = Integer.parseInt(line);
		    		if(codeTable.containsKey(number)){
			        	String code = codeTable.get(number);
			        	encodedFS.append(code);
			        } 
		    	}
		        	
		    }
		    //System.out.println(encodedFS.length());

		    byte[] barray = new byte[encodedFS.length()/8];
            for(int i = 0; i < encodedFS.length()/8; i++){
                barray[i] = (byte) Short.parseShort(encodedFS.substring(8*i,8*(i+1)),2);
            }
          
            
            
            OutputStream output = null;
            output = encodedFile;
            for(byte b : barray){
            	output.write(b);
            }
            
            br.close();
            output.close();  
		    codeFile.close();
		    encodedFile.close();
		
			
			    			
	}
	
	
	//Method to generate the Frequency Table.
	private static HashMap <Integer, Integer> buildFreqTable(String inputFilePath) throws NumberFormatException, IOException{
		
		HashMap <Integer, Integer> FrequencyTable = new HashMap<>();
		
		BufferedReader br = new BufferedReader(new FileReader(_inputFile));
		String line;
	    while ((line = br.readLine())!= null) {
	    	if(!line.isEmpty() && line !=null){
	    		int number = Integer.parseInt(line);
	    		if(FrequencyTable.containsKey(number)){
	    			FrequencyTable.put(number, FrequencyTable.get(number)+1);
	    		}
	    		else
	    			FrequencyTable.put(number, 1);
	    	}
	        	
	    }
	    br.close();
	    return FrequencyTable;
	}
	
	private static node createHuffTree(binary_heap bh){ //Returns the root of the Huffman Tree
		while(bh.getSize()!=1){
			bh.insert(createHuffNode(bh.extractMin(), bh.extractMin()));
		}
		return bh.extractMin();
	}
	
	private static node createHuffNode(node n1, node n2){
		node res = new node();
		res.setlc(n1);
		res.setrc(n2);
		res.setFreq(n1.getFreq()+n2.getFreq());
		
		return res;
	}
	
	private static void createHuffCode(node root, String c, HashMap<Integer, String> codeTable, FileWriter codeTableFile) throws IOException{
	
		
		if(root.getlc() == null && root.getrc() == null){
			codeTable.put(root.fetchData(), c);
			codeTableFile.write(root.fetchData()+" "+c+"\n");
		}
		else
		{
			String l = c+"0";
			String r = c+"1";
			createHuffCode(root.getlc(), l, codeTable, codeTableFile );			
			createHuffCode(root.getrc(), r, codeTable, codeTableFile);
		}
		
		
	}
	
	

	private static String _inputFile;
}

