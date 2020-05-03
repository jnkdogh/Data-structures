package col106.assignment3.Election; 


public class Election implements ElectionInterface {
	/* 
	 * Do not touch the code inside the upcoming block 
	 * If anything tempered your marks will be directly cut to zero
	*/
	public static void main() {
		ElectionDriverCode EDC = new ElectionDriverCode();
		System.setOut(EDC.fileout());
	}
	/*
	 * end code
	 */
	
	//write your code here 
	public BST<Integer,Integer> tree;
	public Candidate[] arr;

	public Election(){
		tree = new BST<>();
		arr = new Candidate[10000];
	}

    public void insert(String name, String candID, String state, String district, String constituency, String party, String votes){
		//write your code here 
		int a = Integer.parseInt(candID);
		int b = Integer.parseInt(votes);
		Candidate c = new Candidate(name, candID, state, district, constituency, party, votes);
		tree.insert(a, b, c);
		for (int i=0;i<10000 ;i++) 
		{
			if(arr[i]==null)
			{
				arr[i]=c;
				break;
			}
		}
	}
	public void updateVote(String name, String candID, String votes){
		//write your code here
		int a = Integer.parseInt(candID);
		int b = Integer.parseInt(votes);
		String a1 = tree.search(Integer.parseInt(candID)).cand.name;
		String a2 = tree.search(Integer.parseInt(candID)).cand.candID;
		String a3 = tree.search(Integer.parseInt(candID)).cand.state;
		String a4 = tree.search(Integer.parseInt(candID)).cand.district;
		String a5 = tree.search(Integer.parseInt(candID)).cand.constituency;
		String a6 = tree.search(Integer.parseInt(candID)).cand.party;
		String a7 = votes;
		tree.delete(a);
		Election r = this;
		r.insert(a1, a2, a3, a4, a5, a6, a7);

		for (int i=0;i<10000 ;i++) 
		{
			if(arr[i].candID.equals(candID))
			{
				arr[i].votes=votes;
				break;
			}
		}		
	}
	public void topkInConstituency(String constituency, String k){
		//write your code here
		Candidate[] arr1 = new Candidate[10000];
		for(int i=0;i<10000 ;i++) 
		{
			if(arr[i]!=null)
			{
			if(arr[i].constituency.equals(constituency))
			{
				for (int j=0;j<10000 ;j++)
				{
					if(arr1[j]==null)
					{
						arr1[j]=arr[i];
						break;
					}	
				}
			}
			}
			else
			{
				break;
			}
		}
        int len=0;
        for (int i = 0; i < 10000; i++)
        {
        	if(arr1[i]!=null)
        	{
        		len+=1;
        	}
        	else
        	{
        		break;
        	}
        }

        Candidate[] newc = new Candidate[len];
        for (int i = 0; i < 10000; i++)
        {
        	if(arr1[i]!=null)
        	{
        		newc[i]=arr1[i];
        	}
        	else
        	{
        		break;
        	}        	
        }

        for (int i = 0; i < len-1; i++)
        {
            for (int j = 0; j < len-i-1; j++)
            {
                if (Integer.parseInt(newc[j].votes) > Integer.parseInt(newc[j+1].votes))
                {
                    Candidate temp = newc[j];
                    newc[j] = newc[j+1];
                    newc[j+1] = temp;
                }
            }		
        }	

        int k1 = Integer.parseInt(k);
        if(k1>=len)
        {
        	for (int i = len-1; i > -1; i--)
        	{
        		System.out.println(newc[i].name + ", " + newc[i].candID + ", " + newc[i].party);
        	}
        }
        else
        {
        	for (int i = len-1; i > len-k1-1; i--)
        	{
        		System.out.println(newc[i].name + ", " + newc[i].candID + ", " + newc[i].party);
        	}
        }
	}
	public void leadingPartyInState(String state){
		//write your code here
		Candidate[] arr1 = new Candidate[10000];
		for(int i=0;i<10000 ;i++) 
		{
			if(arr[i]!=null)
			{
			if(arr[i].state.equals(state))
			{
				for (int j=0;j<10000 ;j++)
				{
					if(arr1[j]!=null)
					{
						arr1[j]=arr[i];
						break;
					}	
					else
					{
						break;
					}
				}
			}
			}
			else
			{
				break;
			}
		}

		String[] arr2 = new String[10000];
		for (int i=0; i<10000; i++) 
		{
			if(arr1[i]!=null)
			{
				int a=0;
				for(int j=0; j<10000; j++)
				{
					if(arr2[j]!=null)
					{
						if(arr2[j].equals(arr1[i].party))
						{
							a+=1;
						}
					}
					else
					{
						break;
					}
				}

				if(a==0)
				{
					for (int j=0; j<10000; j++) 
					{
						if(arr2[j]==null)
						{
							arr2[j]=arr1[i].party;
							break;
						}							
					}
				}
			}
		}

		int[] total = new int[10000];
		for (int i=0; i<10000; i++) 
		{
			int b=0;
			if(arr2[i]!=null)
			{
				for (int j=0; j<10000; j++) 
				{
					if(arr1[j]!=null)
					{
						if(arr1[j].party.equals(arr2[i]))
						{
							b+=Integer.parseInt(arr1[j].votes);
						}
					}
					else
					{
						break;
					}
				}
				total[i]=b;
			}
			else
			{
				break;
			}
			
		}

		int max = total[0];
		for (int i=0; i<10000; i++) 
		{
			if(total[i]>max)
			{
				max=total[i];
			}
		}

		String[] p = new String[10000];
		for (int i=0; i<10000; i++) 
		{
			if(total[i]==max)
			{
				for (int j=0; j<10000; j++) 
				{
					if(p[j]==null)
					{
						p[j]=arr2[i];
					}
				}
			}
		}		

		int len = 0;
		for (int i=0; i<10000; i++) 
		{
			if(p[i]!=null)
			{
				len+=1;
			}
		}

		String[] p1 = new String[len];
        for (int i = 0; i < len-1; i++)
        {
            for (int j = 0; j < len-i-1; j++)
            {
                if (p1[j].compareTo(p1[j+1])<0)
                {
                    // swap temp and arr[i]
                    String temp = p1[j];
                    p1[j] = p1[j+1];
                    p1[j+1] = temp;
                }
            }		
        }

        for (int i = 0; i < len; i++)
        {
        	System.out.println(p1[i]);
        }

	}

	public void cancelVoteConstituency(String constituency){
		//write your code here
		Candidate[] arr1 = new Candidate[10000];
		for(int i=0;i<10000 ;i++) 
		{
			if(arr[i]!=null)
			{
			if(arr[i].constituency.equals(constituency))
			{
				for (int j=0;j<10000 ;j++)
				{
					if(arr1[j]==null)
					{
						arr1[j]=arr[i];
						break;
					}	
				}
			}
			}
			else
			{
				break;
			}
		}	

		String[] candID = new String[10000];
		for (int i=0; i<10000; i++) 
		{
			if(arr1[i]!=null)
			{
				for (int j=0; j<10000; j++) 
				{
					if(candID[j]==null)
					{
						candID[j]=arr1[i].candID;
						break;
					}							
				}
			}
			else
			{
				break;
			}
		}	

		for (int i=0; i<10000; i++)
		{
			if(candID[i]!=null)
			{
				tree.delete(Integer.parseInt(candID[i]));
			}
			else
			{
				break;
			}
		}

		Candidate[] newa = new Candidate[10000];
		for (int i=0; i<10000; i++)
		{
			if(arr[i]!=null)
			{
				int a = 0;
				for (int j=0; j<10000; j++)
				{
					if(candID[j]!=null)
					{
						if(arr[i].candID.equals(candID[j]))
						{
							a+=1;
						}
					}
					else
					{
						break;
					}
				}

				if(a==0)
				{
					for (int j=0; j<10000; j++)
					{
						if(newa[j]==null)
						{
							newa[j]=arr[i];
							break;
						}
					}
				}
			}
			else
			{
				break;
			}
		}

		arr=newa;


	}
	public void leadingPartyOverall(){
		//write your code here
		String[] arr2 = new String[10000];
		for (int i=0; i<10000; i++) 
		{
			if(arr[i]!=null)
			{
				int a=0;
				for(int j=0; j<10000; j++)
				{
					if(arr2[j]!=null)
					{
						if(arr2[j].equals(arr[i].party))
						{
							a+=1;
						}
					}
					else
					{
						break;
					}
				}

				if(a==0)
				{
					for (int j=0; j<10000; j++) 
					{
						if(arr2[j]==null)
						{
							arr2[j]=arr[i].party;
							break;
						}							
					}
				}
			}
			else
			{
				break;
			}
		}

		int[] total = new int[10000];
		for (int i=0; i<10000; i++) 
		{
			int b=0;
			if(arr2[i]!=null)
			{
				for (int j=0; j<10000; j++) 
				{
					if(arr[j]!=null)
					{
						if(arr[j].party.equals(arr2[i]))
						{
							b+=Integer.parseInt(arr[j].votes);
						}
					}
					else
					{
						break;
					}
				}
				total[i]=b;
			}
			else
			{
				

		int max = total[0];
		for (int i=0; i<10000; i++) 
		{
			if(total[i]>max)
			{
				max=total[i];
			}
		}

		String[] p = new String[10000];
		for (int i=0; i<10000; i++) 
		{
			if(total[i]==max)
			{
				for (int j=0; j<10000; j++) 
				{
					if(p[j]==null)
					{
						p[j]=arr2[i];
						break;
					}
				}
			}
		}		

		int len = 0;
		for (int i=0; i<10000; i++) 
		{
			if(p[i]!=null)
			{
				len+=1;
			}
		}

		String[] p1 = new String[len];
		for (int i=0; i<len; i++)
		{
			p1[i]=p[i];
		} 


        for (int i = 0; i < len-1; i++)
        {
            for (int j = 0; j < len-i-1; j++)
            {
                if (p1[j].compareTo(p1[j+1])<0)
                {
                    String temp = p1[j];
                    p1[j] = p1[j+1];
                    p1[j+1] = temp;
                }
            }		
        }

        for (int i = 0; i < len; i++)
        {
        	System.out.println(p1[i]);
        }			

	}

	public void voteShareInState(String party,String state){
		//write your code here
		Candidate[] arr1 = new Candidate[10000];
		for(int i=0;i<10000 ;i++) 
		{
			if(arr[i]!=null)
			{
			if(arr[i].state.equals(state))
			{
				for (int j=0;j<10000 ;j++)
				{
					if(arr1[j]==null)
					{
						arr1[j]=arr[i];
						break;
					}	
				}
			}
			}
			else
			{
				break;
			}
		}

		int b=0;
		for (int i=0; i<10000; i++) 
		{	
			if(arr1[i]!=null)
			{
				if(arr[i].party.equals(party))
				{
					b+=Integer.parseInt(arr1[i].votes);
				}
			}	
			else
			{
				break;
			}	
		}		

		int c=0;
		for (int i=0; i<10000; i++) 
		{	
			if(arr1[i]!=null)
			{
				c+=Integer.parseInt(arr1[i].votes);
			}	
			else
			{
				break;
			}	
		}
		float b1 = b*100;
		float c1 = c;
		float d = b1/c1;
		System.out.println(d);
	}
	
	public void printElectionLevelOrder() {
		//write your code here
		BST temp = tree;
    	temp.printBST();	
	}
}