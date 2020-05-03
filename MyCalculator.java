public class MyCalculator
{
	public MyCalculator()
	{

	}
	@SuppressWarnings("unchecked")
	public int calculate (String expression) throws EmptyStackException
	{
	String line0 = expression;
	String line01 = "";
	for(int i=0; i<line0.length(); i++)
	{
		if(line0.charAt(i)!=' ')
		{
			line01 += String.valueOf(line0.charAt(i));
		}
	}
	
	String line; 
	if(line01.charAt(0)!='(' || line01.charAt(line01.length()-1)!=')')
	{
		line= "(" + line01 + ")";
	}

	else 
	{
		line=line01;
	}

	// System.out.println(line);
	MyStack<Integer> value = new MyStack<Integer>();
	MyStack<Character> operator = new MyStack<Character>();
	for(int i=0; i<line.length(); i++)
	{
		if(line.charAt(i)=='(')
		{
			// System.out.println("goes into (");
			operator.push(line.charAt(i));
		}

		if(line.charAt(i)!='(' && line.charAt(i)!=')' && line.charAt(i)!='*' && line.charAt(i)!='+' && line.charAt(i)!='-')
		{
			// System.out.println("goes into value");
			if(line.charAt(i-1)=='(' || line.charAt(i-1)==')' || line.charAt(i-1)=='*' || line.charAt(i-1)=='+' || line.charAt(i-1)=='-')
			{
			String sg = "";
			for(int z=i; z<line.length(); z++)
			{
				if(line.charAt(z)=='(' || line.charAt(z)==')' || line.charAt(z)=='*' || line.charAt(z)=='+' || line.charAt(z)=='-' )
				{
					break;
				}			
				else
				{
					sg+=String.valueOf(line.charAt(z));
				}
			}
			int x = Integer.parseInt(sg);
			value.push(x);
			}
			}

		if(line.charAt(i)==')')
		{
			// System.out.println("goes into )");
			// System.out.println(operator.top());
			while(operator.top()!='(')
			{
				char o = operator.pop();
				int i1 = value.pop();
				int i2 = value.pop();
				// System.out.println(i1);
				// System.out.println(i2);
				int i3 = 0;
				if(o=='*')
				{
					i3 = i1*i2;
				}
				if(o=='+') 
				{
					i3 = i1+i2;
				}
				if(o=='-')
				{
					i3 = i2-i1;
				}

				value.push(i3);

			}
			char dis = operator.pop();
		}

		if(line.charAt(i)=='+' || line.charAt(i)=='*' || line.charAt(i)=='-')
		{
			// System.out.println("goes into thisop");
			int u = 0;
			if(line.charAt(i)=='-')
			{
				if(operator.top()=='*' || operator.top()=='+' || operator.top()=='-')
				{
					u+=1;
				}
			}

			if(line.charAt(i)=='+')
			{
				if(operator.top()=='*' || operator.top()=='+')
				{
					u+=1;
				}
			}

			if(line.charAt(i)=='*')
			{
				if(operator.top()=='*')
				{
					u+=1;
				}
			}
			int kl=0;
			while(!operator.isEmpty() && u==1 && kl==0)
			{
			if(line.charAt(i)=='-')
			{
					char o = operator.pop();
					//System.out.println(i);
					 // System.out.println("minus");
					 // System.out.println(o);
					if(operator.top()=='(')
					{
						kl+=1;
						operator.push('-');
					}
					int i1 = value.pop();
					int i2 = value.pop();
					// System.out.println(i1);
					// System.out.println(i2);
					int i3 = 0;
					if(o=='*')
					{
						i3 = i1*i2;
					}
					if(o=='+') 
					{
						i3 = i1+i2;
					}
					if(o=='-')
					{
						i3 = i2-i1;
					}
					// System.out.println(i3);
					value.push(i3);
			}
			if(line.charAt(i)=='+')
			{
					char o = operator.pop();
					// System.out.println(i);
					// System.out.println("plus");
					// System.out.println(o);
					if(operator.top()=='(')
					{
						kl+=1;
						operator.push('+');	
					}					
					int i1 = value.pop();
					int i2 = value.pop();
					int i3 = 0;
					if(o=='*')
					{
						i3 = i1*i2;
					}
					if(o=='+') 
					{
						i3 = i1+i2;
					}
					value.push(i3);									
			}

			if(line.charAt(i)=='*')
			{
					char o = operator.pop();
					if(operator.top()=='(')
					{
						kl+=1;
						operator.push('*');
					}					
					int i1 = value.pop();
					int i2 = value.pop();
					int i3 = 0;
					if(o=='*')
					{
						i3 = i1*i2;
					}
					value.push(i3);										
			}				
			}
			if(u==0)
			{
				operator.push(line.charAt(i));
			}


		}
	}

	while(!operator.isEmpty())
	{
		char o = operator.pop();
		int i1 = value.pop();
		int i2 = value.pop();
		int i3 = 0;
		if(o=='*')
		{
			i3 = i1*i2;
		}
		if(o=='+') 
		{
			i3 = i1+i2;
		}
		if(o=='-')
		{
			i3 = i2-i1;
		}

		value.push(i3);			
	}

	int ans = value.top();
	return ans;
	}

	public static void main(String[] args) throws EmptyStackException {
		String io = "(5+7*2-2)";
		MyCalculator c = new MyCalculator();
		System.out.println(c.calculate(io));
	}
}