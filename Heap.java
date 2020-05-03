package col106.assignment3.Heap.*;

import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

public class Heap<T extends Comparable, E extends Comparable> implements HeapInterface <T, E> {
	/* 
	 * Do not touch the code inside the upcoming block 
	 * If anything tempered your marks will be directly cut to zero
	*/
	public static void main() {
		HeapDriverCode HDC = new HeapDriverCode();
		System.setOut(HDC.fileout());
	}
	/*
	 * end code
	 */
	
	// write your code here	
    ArrayList<E> valuesh;
    int sizeh;
    ArrayList<T> keysh;

	public Heap()
	{
        sizeh = 0;
        keysh = new ArrayList<>();
        valuesh = new ArrayList<>();
        keysh.add(null);
        valuesh.add(null);
	}		


	@SuppressWarnings("unchecked")
	public void insert(T key, E value) {
		//write your code here
        sizeh=sizeh+1;
        valuesh.add(value);
        keysh.add(key);
        int a = sizeh;
        while (valuesh.get(a).compareTo(valuesh.get(a/2)) > 0 && a > 1)
        {
        	T temp = keysh.get(a);
        	keysh.set(a, keysh.get(a/2));
        	keysh.set(a, temp);
        	E temp1 = valuesh.get(a);
        	valuesh.set(a, valuesh.get(a/2));
        	valuesh.set(a/2, temp1);            
            a=a/2;
        }
	}


	@SuppressWarnings("unchecked")
	public E extractMax() {
		//write your code here
        if (sizeh < 1)
        {
            return null;
        }

        int a = 1;
        E temp = valuesh.get(1);
        keysh.set(1, keysh.get(sizeh));
        valuesh.set(1, valuesh.get(sizeh));

        while ((2*a < sizeh) && (valuesh.get(a).compareTo(valuesh.get(2*a)) <= 0 || valuesh.get(a).compareTo(valuesh.get(2*a+1)) <= 0))
        {
            if (valuesh.get(2*a).compareTo(valuesh.get(2*a+1)) >= 0)
            {
        		T temp2 = keysh.get(a);
        		keysh.set(a, keysh.get(2*a));
        		keysh.set(2*a, temp2);
        		E temp1 = valuesh.get(a);
        		valuesh.set(a, valuesh.get(2*a));
        		valuesh.set(2*a, temp1);                
                a = 2*a;
            }
            else
            {
        		T temp2 = keysh.get(a);
        		keysh.set(a, keysh.get(2*a+1));
        		keysh.set(2*a+1, temp2);
        		E temp1 = valuesh.get(a);
        		valuesh.set(a, valuesh.get(2*a+1));
        		valuesh.set(2*a+1, temp1);                
                a = 2*a+1;
            }
        }
        keysh.remove(sizeh);
        valuesh.remove(sizeh);
        sizeh=sizeh-1;
        return temp;
	}


	@SuppressWarnings("unchecked")
	public void delete(T key) {
		//write your code here
        int a = keysh.indexOf(key);
        if (a == -1)
        {
            return;
        }

        keysh.set(a, keysh.get(sizeh));
        valuesh.set(a, valuesh.get(sizeh));
        while ((2*a < sizeh) && (valuesh.get(a).compareTo(valuesh.get(2*a)) <= 0 || valuesh.get(a).compareTo(valuesh.get(2*a+1)) <= 0))
        {
            if (valuesh.get(2*a).compareTo(valuesh.get(2*a+1)) >= 0)
            {
        		T temp = keysh.get(a);
        		keysh.set(a, keysh.get(2*a));
        		keysh.set(2*a, temp);
        		E temp1 = valuesh.get(a);
        		valuesh.set(a, valuesh.get(2*a));
        		valuesh.set(2*a, temp1);                
                a = 2*a;
            }
            else
            {
        		T temp = keysh.get(a);
        		keysh.set(a, keysh.get(2*a+1));
        		keysh.set(2*a+1, temp);
        		E temp1 = valuesh.get(a);
        		valuesh.set(a, valuesh.get(2*a+1));
        		valuesh.set(2*a+1, temp1);                
                a = 2*a+1;                
            }
        }
        keysh.remove(sizeh);
        valuesh.remove(sizeh);
        sizeh=sizeh-1;		
		
	}


	@SuppressWarnings("unchecked")
	public void increaseKey(T key, E value) {
		//write your code here
        int a = keysh.indexOf(key);
        if (a == -1)
        {
            return;
        }
        int b = a;
        valuesh.set(b, value);
        if (b/2 > 0)
        {
            if (valuesh.get(b).compareTo(valuesh.get(b/2)) > 0)
            {
                while (b > 1 && valuesh.get(b).compareTo(valuesh.get(b/2)) > 0)
                {
        			T temp = keysh.get(b);
        			keysh.set(b, keysh.get(b/2));
        			keysh.set(b/2, temp);
        			E temp1 = valuesh.get(b);
        			valuesh.set(b, valuesh.get(b/2));
        			valuesh.set(b/2, temp1);                
                	b = b/2;                    
                }
            }
            else
            {
                b = a;
                while((2*b < sizeh) && (valuesh.get(b).compareTo(valuesh.get(2*b)) <= 0 || valuesh.get(b).compareTo(valuesh.get(2*b+1)) <= 0))
                {
                    if (valuesh.get(2*b).compareTo(valuesh.get(2*b+1)) >= 0)
                    {
        				T temp = keysh.get(b);
        				keysh.set(b, keysh.get(2*b));
        				keysh.set(2*b, temp);
        				E temp1 = valuesh.get(b);
        				valuesh.set(b, valuesh.get(2*b));
        				valuesh.set(2*b, temp1);                
                		b = 2*b;                        
                    }
                    else
                    {
        				T temp = keysh.get(b);
        				keysh.set(b, keysh.get(2*b+1));
        				keysh.set(2*b+1, temp);
        				E temp1 = valuesh.get(b);
        				valuesh.set(b, valuesh.get(2*b+1));
        				valuesh.set(2*b+1, temp1);                
                		b = 2*b+1;                         
                    }
                }
            }
        }		
		
	}


	@SuppressWarnings("unchecked")
	public void printHeap() {
		//write your code here
        if (sizeh < 1)
        {
            return;
        }

        Queue<Integer> que = new LinkedList<>();
        que.offer(1);

        while (!que.isEmpty())
        {
            int i = que.poll();
            if (2*i <= sizeh)
            {
                que.offer(2*i);
            }
            
            if(2*i+1 <= sizeh)
            {
                que.offer(2*i+1);
            }

            System.out.println(keysh.get(i) + ", " + valuesh.get(i));
        }		
	}
}
