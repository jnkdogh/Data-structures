
public class MyStack<T extends Object>
{
	private T[] ar;

	@SuppressWarnings("unchecked")
	public MyStack()
	{
		ar= (T[]) new Object[1];
	}

	@SuppressWarnings("unchecked")
	public void push(T value)
	{
		T[] ar1 = (T[]) new Object[ar.length+1];

		if(ar[0]==null)
		{
			ar[0]=value;
		}

		else
		{
			for(int i=0; i<ar.length; i++)
			{
				ar1[i]=ar[i];
			}
			ar1[ar.length]=value;
			ar=ar1;
		}
	}

	@SuppressWarnings("unchecked")
	public T pop() throws EmptyStackException
	{
		if(ar[0]==null)
		{
			throw new EmptyStackException();
		}

		else if(ar.length==1 && ar[0]!=null)
		{
			T z1 = ar[0];
			ar[0] = null;
			return z1;
		}
		else
		{
			T[] ar2 = (T[]) new Object[ar.length-1];
			for(int i=0; i<ar.length-1; i++)
			{
				ar2[i]=ar[i];
			}
			T z = ar[ar.length-1];
			ar=ar2;
			return z;
		}
		
	}

	public int length()
	{
		if(ar[0]==null)
		{
			return 0;
		}

		else
		{
			int z = ar.length;
			return z;
		}
	}

	public T top() throws EmptyStackException
	{
		if(ar[0]==null)
		{
			throw new EmptyStackException();
		}
		T z1 = ar[ar.length-1];
		return z1;
	}

	public boolean isEmpty()
	{
		if(ar[0]==null)
		{
			return true;
		}

		else
		{
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public MyStack copy()
	{
		T[] tu = (T[]) new Object[ar.length];
		for(int i=0; i<ar.length; i++)
		{
			tu[i]=ar[i]; 
		}

		MyStack<T> sy = new MyStack<T>();
		sy.ar=tu; 
		System.out.println("Stack");
		return sy;
	}


/*@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
	
	MyStack<Integer> fst = new MyStack<Integer>();
	MyStack<Integer> fst1 = new MyStack<Integer>();
	int a = 5;
	fst.push(a);
	/*fst.push(7);
	fst.push(8);
	fst.push(9);
	fst.push(10);*/
	//System.out.println(fst.pop());
	/*fst.top();
	fst.isEmpty();
	fst1 = fst.copy();*/
	//System.out.println(fst.top());
	//System.out.println(fst1.pop());
	//System.out.println(fst1.top());
	//System.out.println(fst.pop());
	//System.out.println(fst.isEmpty());*/
//}
}



