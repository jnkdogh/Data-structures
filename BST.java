package col106.assignment3.BST;

public class BST<T extends Comparable, E extends Comparable> implements BSTInterface<T, E> {
	/* 
	 * Do not touch the code inside the upcoming block 
	 * If anything tempered your marks will be directly cut to zero
	*/
	public static void main() {
		BSTDriverCode BDC = new BSTDriverCode();
		System.setOut(BDC.fileout());
	}
	/*
	 * end code
	 * start writing your code from here
	 */
	
	//write your code here
	/*@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		BST obj = new BST();
		obj.insert(1, 50);
		obj.insert(2, -1);
		obj.insert(3, 60);
		obj.insert(4, 0);
		obj.insert(5, 55);
		obj.insert(6, -3);
		obj.insert(10, 51);
		obj.insert(7, 56);
		obj.insert(8, 53);
		obj.insert(9, -4);
		obj.delete(1);
		obj.update(9, 52);
		obj.insert(10, 61);
		obj.delete(2);

		// obj.insert(12, 16);
		// obj.insert(13, 19);
		// obj.insert(14, 21);
		// obj.insert(15, 26);
		// obj.insert(16, 24);
		System.out.println(obj.search(10).left.valuebst);
		// obj.printBST();
	}*/

	public T keybst;
	public E valuebst;
	public BST left;
	public BST right;

	public BST()
	{
		this.keybst = null;
		this.valuebst = null;
		this.left = null;
		this.right = null;
	}


	@SuppressWarnings("unchecked")
	public BST search(T key)
	{
		BST temp = this;
		if(temp.left==null && temp.right==null && temp.keybst!=key)
		{
			return null;
		}
		else if (temp.left==null && temp.right!=null && temp.keybst!=key) 
		{
			return temp.right.search(key);
		}
		else if(temp.keybst==key)
		{
			return temp;
		}
		else
		{
			BST a = temp.left.search(key);
			if(a==null)
			{
				if(temp.right!=null)
				{
					return temp.right.search(key);
				}
				else
				{
					return null;
				}
			}
			else
			{
				return a;
			}
		}
	}

	@SuppressWarnings("unchecked")
    public void insert(T key, E value) {
    	// System.out.println("insert");
    	// System.out.println((String) key);
    	// System.out.println((String) value);
		BST temp = this;
		if(temp.valuebst!=null)
		{
			while(temp!= null)
			{
				if(value.compareTo(temp.valuebst)<0)
				{
					if(temp.left!=null)
					{
						temp = temp.left;
					}

					else
					{
						BST obj = new BST();
						obj.valuebst=value;
						obj.keybst=key;
						temp.left = obj;
						temp = temp.left;
						break;
					}
				}

				if(value.compareTo(temp.valuebst)>0)
				{
					if(temp.right!=null)
					{
						temp = temp.right;
					}

					else//else mistake to be noted
					{
						BST obj = new BST();
						obj.valuebst=value;
						obj.keybst=key;
						temp.right = obj;
						temp = temp.right;
						break;
					}
				}		
			}	
		}

		if(temp.valuebst==null)
		{
			temp.keybst=key;
			temp.valuebst=value;			
		}		
    }

    @SuppressWarnings("unchecked")
    public void update(T key, E value) {
		//write your code here
    	// System.out.println("update");
    	// String a = (String) key;
    	// String b = (String) value;
    	// System.out.println(a);
    	// System.out.println(b);		
		BST temp = this;
		temp.delete(key);
		temp.insert(key, value);
		
    }

    @SuppressWarnings("unchecked")
    public void delete(T key) {
		//write your code here
    	// System.out.println("delete");
    	// String aa = (String) key;
    	// String b = (String) value;
    	// System.out.println(aa);
    	// System.out.println(b);		
		BST temp = this;
		BST ump = this;
		temp = temp.search(key);
		BST pv = ump.prev(temp.keybst);	
		int a = 0;
		int b = 0;
		if(a==0)
		{	
		if(temp.right!=null)
		{
			// System.out.println("temp.right not null");
			a+=1;
			BST temp2 = temp.right;
			BST temp4 = temp.right;
			BST temp3 = temp.left;
			BST prev = null;
			
			if(temp2.left!=null)
			{
				// System.out.println("if temp2.left is not null");
				b+=1;
				while(temp2.left!=null)
				{
					// System.out.println("while temp2.left is not null");
					prev = temp2;
					temp2 = temp2.left;
				}
				if(temp2.right!=null)
				{
					// System.out.println(temp2.valuebst);
					// System.out.println("if temp2.right is not null");
					prev.left=temp2.right;
					temp.keybst=temp2.keybst;
					temp.valuebst=temp2.valuebst;
					temp.right=temp4;
					temp.left=temp3;
				}
				else
				{
					prev.left=null;
					temp.valuebst=temp2.valuebst;
					temp.keybst=temp2.keybst;
					temp.right=temp4;
					temp.left=temp3;					
				}			
			}
			else
			{
				b+=1;
				// System.out.println("if temp2.left is null");
				// System.out.println(temp2.valuebst);
				// System.out.println(temp.valuebst);
				// pv.left=temp2;
				// temp2.left=temp.left;
				// System.out.println(pv.left.valuebst);
				temp.keybst=temp2.keybst;
				temp.valuebst=temp2.valuebst;
				temp.right=temp2.right;
				temp.left=temp3;
				// System.out.println(temp2.valuebst);
				// System.out.println(pv.left.valuebst);
				// System.out.println(temp.valuebst);
			}

		}

		if(b==0)
		{
		if(temp.right==null && temp.left!=null)
		{
			a+=1;
			BST temp2 = temp.left;
			BST temp4 = temp.left;
			BST temp3 = temp.right;
			BST prev = null;
			if(temp2.right!=null)
			{
				while(temp2.right!=null)
				{
					prev = temp2;
					temp2 = temp2.right;
				}
				if(temp2.left!=null)
				{
					prev.right=temp2.left;
					temp.valuebst=temp2.valuebst;
					temp.keybst=temp2.keybst;
					temp.left=temp4;
					temp.right=temp3;
				}
				else
				{
					prev.right=null;
					temp.keybst=temp2.keybst;
					temp.valuebst=temp2.valuebst;
					temp.left=temp4;
					temp.right=temp3;					
				}			
			}
			else
			{
				temp.valuebst=temp2.valuebst;
				temp.keybst=temp2.keybst;
				temp.left=temp2.left;
				temp.right=temp3;
			}		
		}
		}
		}

		if(a==0)
		{
		if(temp.right==null && temp.left==null)
		{
			if(pv==null)
			{
				temp=null;
				temp.valuebst=null;
				temp.keybst=null;				
			}
			else
			{
				if(pv.right==temp)
				{
					pv.right=null;
				}
				else
				{
					pv.left=null;
				}
			}
			temp.valuebst=null;
			temp.keybst=null;
		}
		}
    }

    @SuppressWarnings("unchecked")
    public int height(){
    	BST temp = this;
    	if(temp.right == null && temp.left == null)
    	{
    		return 1;
    	}
    	else if (temp.right!=null && temp.left==null) 
    	{
    		int right_height = temp.right.height();
    		return right_height+1;
    	}
    	else if (temp.right==null && temp.left!=null) 
    	{
    		int left_height = temp.left.height();
	   		return left_height+1;
    	}
    	else
    	{
    		int left_height = temp.left.height();
    		int right_height = temp.right.height();
    		if(right_height > left_height)
    		{
    			return right_height+1;
    		}
    		else if (right_height == left_height) 
    		{
    			return right_height+1;
    		}
    		else
    		{
    			return left_height+1;
    		}
    	}
    }

    @SuppressWarnings("unchecked")
    public void print_level(int a){
    	BST temp = this;
    	if(a==1)
    	{
    		System.out.println(temp.keybst + ", " + temp.valuebst);
    	}
    	else if (a>1) 
    	{
    		if(temp.right!=null && temp.left!=null)
    		{
    			int b = a-1;
    			temp.left.print_level(b);
    			temp.right.print_level(b);
    		}
    		else if (temp.right!=null && temp.left==null) 
    		{
    			int b = a-1;
    			temp.right.print_level(b);
    		}
     		else if (temp.right==null && temp.left!=null) 
    		{
    			int b = a-1;
    			temp.left.print_level(b);
    		}   		
    	}
    }


    @SuppressWarnings("unchecked")
    public void printBST () {
    	BST temp = this;
    	int h = temp.height();
    	for (int i=1; i<=h ;i++ ) 
    	{
    		temp.print_level(i);	
    	}
		//write your code here
    }

	@SuppressWarnings("unchecked")
	public BST prev(T key)
	{
		BST temp = this;
		BST prev = null;
		if(temp.search(key)!=null)
		{
			while(temp.keybst!=key)
			{
				if(temp!=null)
				{
					if(temp.right!=null)
					{
					if(temp.right.search(key)!=null)
					{
						prev=temp;
						temp=temp.right;
					}
					else
					{
						prev=temp;
						temp=temp.left;
					}
					}
					else
					{
						prev=temp;
						temp=temp.left;						
					}
				}
				else
				{
					return prev;
				}

			}
		}
		else
		{
			return null;
		}
		return prev;
	}   

}