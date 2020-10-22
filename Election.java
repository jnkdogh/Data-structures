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
	/*public static void main(String[] args) {
		Election obj = new Election();
		obj.insert("cand1", "1", "S1", "D11", "C11", "P1", "10");
		obj.insert("cand1", "2", "S1", "D11", "C11", "P2", "50");
		obj.insert("cand1", "3", "S1", "D11", "C11", "P3", "30");
		obj.insert("cand1", "4", "S1", "D12", "C12", "P1", "5");
		obj.insert("cand1", "5", "S1", "D12", "C12", "P2", "11");
		obj.insert("cand1", "6", "S1", "D12", "C12", "P3", "15");
		obj.insert("cand1", "7", "S1", "D13", "C13", "P1", "12");
		obj.insert("cand1", "8", "S1", "D13", "C13", "P2", "13");
		obj.insert("cand1", "9", "S1", "D13", "C13", "P3", "14");
		obj.insert("cand1", "10", "S2", "D21", "C21", "P1", "0");
		obj.insert("cand1", "11", "S2", "D21", "C21", "P2", "32");
		obj.insert("cand1", "12", "S2", "D21", "C21", "P3", "6");
		obj.insert("cand1", "13", "S3", "D31", "C31", "P1", "18");
		obj.insert("cand1", "14", "S3", "D31", "C31", "P2", "19");
		obj.insert("cand1", "15", "S3", "D31", "C31", "P3", "55");
		obj.insert("cand1", "16", "S3", "D32", "C32", "P1", "255");
		obj.insert("cand1", "17", "S3", "D32", "C32", "P2", "75");
		obj.insert("cand1", "18", "S3", "D33", "C32", "P3", "180");
		obj.printElectionLevelOrder();
		obj.updateVote("cand3", "3", "100");
		// System.out.println(obj.arr[18].candID);
		// System.out.println("updatevote");
		// obj.printElectionLevelOrder();
		// obj.leadingPartyOverall();
		// obj.leadingPartyInState("S1");
		obj.topkInConstituency("C11", "4");
	}*/


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
		// System.out.println("updatevote");
		int a = Integer.parseInt(candID);
		int b = Integer.parseInt(votes);
		// String a1 = tree.search(Integer.parseInt(candID)).cand.name;
		// String a2 = tree.search(Integer.parseInt(candID)).cand.candID;
		// String a3 = tree.search(Integer.parseInt(candID)).cand.state;
		// String a4 = tree.search(Integer.parseInt(candID)).cand.district;
		// String a5 = tree.search(Integer.parseInt(candID)).cand.constituency;
		// String a6 = tree.search(Integer.parseInt(candID)).cand.party;
		// String a7 = votes;
		Candidate c = tree.search(Integer.parseInt(candID)).cand;
		c.votes=votes;
		// System.out.println("delete1");
		tree.delete(a);
		tree.insert(a, b, c);
		// System.out.println("delete2");
		// Election r = this;
		// System.out.println("insert1");
		// System.out.println(a7);
		// tree.insert(a1, a2, a3, a4, a5, a6, a7);
		// System.out.println(arr[18].candID);
		// System.out.println("insert2");

		for (int i=0;i<10000 ;i++) 
		{
			if(arr[i].candID.equals(candID))
			{
				// System.out.println(arr[i].candID);
				arr[i].votes=votes;
				// System.out.println(i);
				break;
			}
		}		

		// System.out.println(arr[18].candID);
	}
	public void topkInConstituency(String constituency, String k){
		//write your code here
		Candidate[] arr1 = new Candidate[10000];
		for(int i=0;i<10000 ;i++) 
		{
			if(arr[i]!=null)
			{
				// System.out.println("for arr");
			if(arr[i].constituency.equals(constituency))
			{
				// System.out.println("ID");
				// System.out.println(arr[i].candID);
				// System.out.println(i);
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

		// System.out.println(arr1[5].party);			

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

		// System.out.println(arr1[500].candID);

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

		// System.out.println(arr2[4]);

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

		// System.out.println(total[2]);

		int max = total[0];
		for (int i=0; i<10000; i++) 
		{
			if(total[i]>max)
			{
				max=total[i];
			}
		}
		// System.out.println(max);

		String[] p = new String[10000];
		for (int i=0; i<10000; i++) 
		{
			if(total!=null)
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
			else
			{
				break;
			}
		}		

		// System.out.println(p[1]);

		int len = 0;
		for (int i=0; i<10000; i++) 
		{
			if(p[i]!=null)
			{
				len+=1;
			}
		}
		// System.out.println(len);

		String[] p1 = new String[len];
		for (int i=0; i<len; i++)
		{
			p1[i]=p[i];
		} 		

		// System.out.println(p1[0]);


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

        for (int i = len-1; i > -1; i--)
        {
        	// System.out.println(i);
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

        for (int i = len-1; i > -1; i--)
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
		int b1 = b*100;
		int d = b1/c;
		System.out.println(d);
	}
	
	public void printElectionLevelOrder() {
		//write your code here
		BST temp = tree;
    	temp.printBST();	
	}
}