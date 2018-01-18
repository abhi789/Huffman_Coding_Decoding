
public class pairing_heap_node {

	public pairing_heap_node() {
		// TODO Auto-generated constructor stub
		
		childptr = null;
		leftptr = null;
		rightptr = null;
		node1 = new node();
		
	}
	
	public Integer getData()
	{
		return node1.fetchData();
	}
	
	public Integer getFrequency()
	{
		return node1.getFreq();
	}
	
	public void setData(Integer d)
	{
		 node1.setData(d);
	}
	
	public void setFreq(Integer f)
	{
		 node1.setFreq(f);
	}

	public pairing_heap_node getleftptr()
	{
		return leftptr;
	}
	
	
	public void setleftptr(pairing_heap_node l)
	{
		leftptr = l ;
	}
	
	
	public pairing_heap_node getrightptr()
	{
		return rightptr;
	}
	

	public void setrightptr(pairing_heap_node r)
	{
		 rightptr = r;
	}
	
	public pairing_heap_node getchildptr()
	{
		return childptr;
	}
	
	public void setchildptr(pairing_heap_node c)
	{
		childptr = c;
	}
	
	public node getnode()
	{
		return node1;
	}
	
	public void setlc(pairing_heap_node l)
	{
		node1.setlc(l.getnode());
	}
	
	public void setrc(pairing_heap_node r)
	{
		node1.setrc(r.getnode());
	}
	
	public node getlc()
	{
		return node1.getlc();
	}
	
	public node getrc()
	{
		return node1.getrc();
	}
	
	
	private node node1;
	private pairing_heap_node childptr;
	private pairing_heap_node leftptr;
	private pairing_heap_node rightptr;

}
