package col106.assignment3.Heap;

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

	/*@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		Heap obj = new Heap();
		obj.insert(1, 100);
		obj.insert(2, 10);
		obj.insert(3, 30);
		obj.insert(4, 50);
		obj.insert(5, 150);
		obj.insert(6, 1);
		obj.insert(7, 3);
		obj.printHeap();
		obj.delete(1);
		System.out.println("delete 1");
		obj.printHeap();
		
	}*/


    ArrayList<E> valuesh = new ArrayList<>();;
    int sizeh;
    ArrayList<T> keysh = new ArrayList<>();;

	public Heap()
	{
        sizeh = 0;
        keysh.add(null);
        valuesh.add(null);
	}		


	@SuppressWarnings("unchecked")
	public void insert(T key, E value) {
		//write your code here
        Heap h = this;
        sizeh=sizeh+1;
        valuesh.add(value);
        keysh.add(key);
        int a = sizeh;
        while (a > 1 && h.getv(a).compareTo(h.getv(a/2)) > 0)
        {
        	T temp = (T) h.getk(a);
        	keysh.set(a, (T) h.getk(a/2));
        	keysh.set(a/2, temp);
        	E temp1 = (E) h.getv(a);
        	valuesh.set(a, (E) h.getv(a/2));
        	valuesh.set(a/2, temp1);         
            a=a/2;
        }
	}

    private E getv(int t){
        return valuesh.get(t);
    }

    private T getk(int t){
        return keysh.get(t);
    }    


	@SuppressWarnings("unchecked")
	public E extractMax() {
		//write your code here
        Heap h = this;
        if (sizeh < 1)
        {
            return null;
        }

        E temp = (E) h.getv(1);
        keysh.set(1, (T) h.getk(sizeh));
        valuesh.set(1, (E) h.getv(sizeh));
        int a = 1;

        while ((2*a < sizeh) && (h.getk(a).compareTo(h.getk(2*a)) <= 0 || h.getv(a).compareTo(h.getv((2*a)+1)) <= 0))
        {
            if (h.getv(2*a).compareTo(h.getv((2*a)+1)) >= 0)
            {
        		T temp2 = (T) h.getk(a);
        		keysh.set(a, (T) h.getk(2*a));
        		keysh.set(2*a, temp2);
        		E temp1 = (E) h.getv(a);
        		valuesh.set(a, (E) h.getv(2*a));
        		valuesh.set(2*a, temp1);
                a = 2*a;
            }
            else
            {
        		T temp2 = (T) h.getk(a);
        		keysh.set(a, (T) h.getk((2*a)+1));
        		keysh.set((2*a)+1, temp2);
        		E temp1 = (E) h.getv(a);
        		valuesh.set(a, (E) h.getv((2*a)+1));
        		valuesh.set((2*a)+1, temp1);                
                a = (2*a)+1;
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
        Heap h = this;
        int a = keysh.indexOf(key);
        if (a == -1)
        {
            return;
        }

        keysh.set(a, (T) h.getk(sizeh));
        valuesh.set(a, (E) h.getv(sizeh));
        while ((2*a < sizeh) && (h.getv(a).compareTo(h.getv(2*a)) <= 0 || h.getv(a).compareTo(h.getv((2*a)+1)) <= 0))
        {
            if (h.getv(2*a).compareTo(h.getv((2*a)+1)) >= 0)
            {
        		T temp = (T) h.getk(a);
        		keysh.set(a, (T) h.getk(2*a));
        		keysh.set(2*a, temp);
        		E temp1 = (E) h.getv(a);
        		valuesh.set(a, (E) h.getv(2*a));
        		valuesh.set(2*a, temp1);                
                a = 2*a;
            }
            else
            {
        		T temp = (T) h.getk(a);
        		keysh.set(a, (T) h.getk((2*a)+1));
        		keysh.set((2*a)+1, temp);
        		E temp1 = (E) h.getv(a);
        		valuesh.set(a, (E) h.getv((2*a)+1));
        		valuesh.set((2*a)+1, temp1);                
                a = (2*a)+1;                
            }
        }
        keysh.remove(sizeh);
        valuesh.remove(sizeh);
        sizeh=sizeh-1;		
		
	}


	@SuppressWarnings("unchecked")
	public void increaseKey(T key, E value) {
		//write your code here
        Heap h = this;
        int a = keysh.indexOf(key);
        if (a == -1)
        {
            return;
        }
        int b = a;
        valuesh.set(b, value);
        if (b/2 > 0)
        {
            if (h.getv(b).compareTo(h.getv(b/2)) > 0)
            {
                while (b > 1 && h.getv(b).compareTo(h.getv(b/2)) > 0)
                {
        			T temp = (T) h.getk(b);
        			keysh.set(b, (T) h.getk(b/2));
        			keysh.set(b/2, temp);
        			E temp1 = (E) h.getv(b);
        			valuesh.set(b, (E) h.getv(b/2));
        			valuesh.set(b/2, temp1);                
                	b = b/2;                    
                }
            }
            else
            {
                b = a;
                while((2*b < sizeh) && (h.getv(b).compareTo(h.getv(2*b)) <= 0 || h.getv(b).compareTo(h.getv((2*b)+1)) <= 0))
                {
                    if (h.getv(2*b).compareTo(h.getv((2*b)+1)) >= 0)
                    {
        				T temp = (T) h.getk(b);
        				keysh.set(b, (T) h.getk(2*b));
        				keysh.set(2*b, temp);
        				E temp1 = (E) h.getv(b);
        				valuesh.set(b, (E) h.getv(2*b));
        				valuesh.set(2*b, temp1);                
                		b = 2*b;                        
                    }
                    else
                    {
        				T temp = (T) h.getk(b);
        				keysh.set(b, (T) h.getk((2*b)+1));
        				keysh.set((2*b)+1, temp);
        				E temp1 = (E) h.getv(b);
        				valuesh.set(b, (E) h.getv((2*b)+1));
        				valuesh.set((2*b)+1, temp1);                
                		b = (2*b)+1;                         
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
            
            if((2*i)+1 <= sizeh)
            {
                que.offer((2*i)+1);
            }

            System.out.println(keysh.get(i) + ", " + valuesh.get(i));
        }		
	}
}
