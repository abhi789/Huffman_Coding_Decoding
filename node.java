

public class node {
	
	private node leftChild;
	private node rightChild;
	private Integer freq;
	private Integer data;	
	
	public node()
	{
		leftChild = null;
		rightChild = null;
		freq = 0;
		
	}
	
	public Integer fetchData()
	{
		return data;
	}
	
	public void setData(Integer d)
	{
		 data = d;
	}
	
	public Integer getFreq()
	{
		return freq;
	}
	
	public void setFreq(Integer f)
	{
		 freq = f;
	}

	public node getlc()
	{
		return leftChild;
	}
	
	public void setlc(node l)
	{
		leftChild = l ;
	}
	
	public node getrc()
	{
		return rightChild;
	}
	
	
	public void setrc(node r)
	{
		 rightChild = r;
	}
	
	
	
	
	
	
}


