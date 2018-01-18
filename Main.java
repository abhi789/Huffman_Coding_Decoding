
import java.io.FileWriter;
import java.io.IOException;



public class Main {
	public static void main(String[] args) throws IOException {
		
		
		long BT_Final_Time = 0;
		//for(int i =0; i<10; i++)
		{
			long startTimeBT = System.nanoTime();
			binary_heap bh = new binary_heap();
			bh.buildHeap("./sample_input_large.txt");
			node root = bh.createHufftree();
			FileWriter w_bh = new FileWriter("./Binary_Heap_Codes.txt");
			bh.createHuffcodes(root, "", w_bh);
			w_bh.close();
			long endTimeBT = System.nanoTime();
			
			long duration_BT = (endTimeBT - bh.startTime)/1000000;
			//System.out.println("Run "+ (i+1) + ": " + duration_BT + " ms");
			BT_Final_Time = BT_Final_Time + duration_BT;
		}
		System.out.println("Time required by Binary Heap = " + BT_Final_Time + " ms\n");

		
		
		
		long FH_Final_Time = 0;
		//for(int i=0; i<10; i++)
		{
			long startTimeFourWay = System.nanoTime();
			fourway_heap fh = new fourway_heap("./sample_input_large.txt");
			fh.build();
			//long endTimeFourway = System.nanoTime();
			//fh.printHeap();
			node r = fh.createhufftree();
			FileWriter w_fh = new FileWriter("./Fourway_Heap_codes.txt");
			fh.createhuffcodes(r, "", w_fh);
			w_fh.close();
			long endTimeFourway = System.nanoTime();
			long duration_FH = (endTimeFourway - fh.starttime)/1000000;
			//System.out.println("Run "+ (i+1) + ": " + duration_FH + " ms");
			FH_Final_Time = FH_Final_Time + duration_FH;
		}
		System.out.println("Time required by four-way cache optimised heap = " + FH_Final_Time + " ms\n");
		
		
		
	
		long PH_Final_Time = 0;
		//for(int i = 0; i<10; i++)
		{
			long startTimePH = System.nanoTime();
			pairing_heap ph = new pairing_heap("./sample_input_large.txt");
			ph.build();
			node m = ph.createhufftree();
			FileWriter w_ph = new FileWriter("./Pairing_Heap_Codes.txt");
			ph.createhuffcodes(m, "", w_ph);	
			w_ph.close();
			long endTimePH = System.nanoTime();
			long duration_PH = (endTimePH - ph.start)/1000000;
			//System.out.println("Run " + (i+1) + ": " + duration_PH + " ms");
			PH_Final_Time = PH_Final_Time + duration_PH;
		}
		System.out.println("Time required by Pairing Heap = " + PH_Final_Time + " ms");
		


		
		
	}

}
