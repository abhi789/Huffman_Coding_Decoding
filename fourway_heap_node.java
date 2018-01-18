
public class fourway_heap_node {

	public fourway_heap_node() {
		// TODO Auto-generated constructor stub
		node2 = new node();
	}

	public Integer getData()
	{
		return node2.fetchData();
	}
	
	public void setData(Integer d)
	{
		 node2.setData(d);
	}
	
	public Integer getFreq()
	{
		return node2.getFreq();
	}
	
	public void setFreq(Integer f)
	{
		 node2.setFreq(f);
	}
	
	public node getnode()
	{
		return node2;
	}
	
	public void setlc(fourway_heap_node l)
	{
		node2.setlc(l.getnode());
	}
	
	public void setrc(fourway_heap_node r)
	{
		node2.setrc(r.getnode());
	}
	
	public node getlc()
	{
		return node2.getlc();
	}
	
	public node getrc()
	{
		return node2.getrc();
	}
	

	
	
	private node node2;
	//private Integer frequency;
	//private Integer data;
}
