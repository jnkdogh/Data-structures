class StackSort
{
	public StackSort()
	{

	}

	public String[] sort(int[] nums) throws EmptyStackException
	{	
		int ph = 0;
		int pp = 0;
		String[] ans = new String[2*nums.length];
		MyStack<Integer> st = new MyStack<Integer>();
		st.push(nums[0]);
		ans[0]="PUSH";
		ph+=1;
		int b = 0;
		int a = 0;
		for(int i=1; i<nums.length; i++)
		{
			if(nums[i]<=st.top())
			{
				st.push(nums[i]);
				ans[ph+pp]="PUSH";
				ph+=1;
			}
			if(nums[i]>st.top())
			{
			if(st.top()<a)
			{
				String[] nm = new String[1];
				nm[0]="NOTPOSSIBLE";
				return nm;
			}
			if(st.top()>=a)
			{
				a = st.pop();
				ans[ph+pp]="POP";
				pp+=1;
				nums[pp-1]=a;
				while(!st.isEmpty() && st.top()<nums[i])
				{
					a = st.pop();
				ans[ph+pp]="POP";
				pp+=1;
				nums[pp-1]=a;					
				}
				st.push(nums[i]);
				ans[ph+pp]="PUSH";
				ph+=1;				
			}
		}
		}
		while(!st.isEmpty())
		{
			if(st.top()>=a)
			{
			a = st.pop();
			ans[ph+pp]="POP";
			pp+=1;
			nums[pp-1]=a;
			}
			else
			{
				String[] nm = new String[1];
				nm[0]="NOTPOSSIBLE";
				return nm;				
			}		
		}
		return ans;

	}

	public String[][] kSort(int[] nums) throws EmptyStackException
	{
		/*System.out.println("ksort");
		for(int i=0;i<nums.length;i++)
		{
			System.out.println(nums[i]);
		}*/
		MyStack<String[]> st1 = new MyStack<String[]>();
		int b = 0;
		while(b==0)
		{
			b+=1;
		int ph = 0;
		int pp = 0;
		String[] ans = new String[2*nums.length];
		MyStack<Integer> st = new MyStack<Integer>();


		st.push(nums[0]);
		ans[0]="PUSH";
		ph+=1;
		
		int a = 0;
		for(int i=1; i<nums.length; i++)
		{
			if(nums[i]<=st.top())
			{
				st.push(nums[i]);
				ans[ph+pp]="PUSH";
				ph+=1;
			}
			if(nums[i]>st.top())
			{
				a = st.pop();
				ans[ph+pp]="POP";
				pp+=1;
				nums[pp-1]=a;
				while(!st.isEmpty() && st.top()<nums[i])
				{
					a = st.pop();
				ans[ph+pp]="POP";
				pp+=1;
				nums[pp-1]=a;					
				}
				st.push(nums[i]);
				ans[ph+pp]="PUSH";
				ph+=1;		
		}
		}
		while(!st.isEmpty())
		{	
			a = st.pop();
			ans[ph+pp]="POP";
			pp+=1;
			nums[pp-1]=a;
		}
		//System.out.println(b);
		for(int i=1; i<nums.length; i++)
		{
			int o = 0;
			if(nums[i-1]>nums[i])
			{
				o+=1;
			}
			if(o!=0)
			{
				b-=1;
			}
		}
		// for(int i=0; i<nums.length; i++)
		// {
			// System.out.println(nums[i]);
		// }
		// for(int i=0; i<ans.length; i++)
		// {
			// System.out.println(ans[i]);
		// }
		// System.out.println(b);
		st1.push(ans);			
		}
		int y =2*nums.length;
		int sz = st1.length();
		String[][] mat = new String[sz][y];
		// System.out.println(mat.length);
		// System.out.println(mat[0].length);
		for(int i=0;i<sz;i++)
		{
			;
			String[] im = st1.pop();
			for(int j=0;j<y;j++)
			{
				mat[sz-i-1][j]=im[j];
				// System.out.println(im[j]);
			}
		}

		return mat;

	}

	/*public static void main(String[] args) throws EmptyStackException {
		int[] cv = new int[3];
		cv[0]=1;
		cv[1]=2;
		cv[2]=3;
		// cv[3]=2;
		// cv[4]=6;
		/*cv[5]=1;
		cv[6]=4;
		cv[7]=3;
		cv[8]=4;*/
		 // StackSort s = new StackSort();
		 // String[][] ab = s.kSort(cv);

		// StackSort s = new StackSort();
		 //String[] ab = s.sort(cv);
		//System.out.println(ab.length);
		/*for(int z=0; z<cv.length; z++)
		{
			System.out.println(cv[z]);
		}*/
		/*for(int i=0; i<ab.length; i++)
		{
		System.out.println(ab[i]);
		}*/
		/*for(int i=0; i<ab.length;i++)
		{
			for(int j=0; j<ab[0].length;j++)
			{
				System.out.println(ab[i][j]);
			}
		}
	}*/
}