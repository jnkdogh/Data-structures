package col106.assignment4.WeakAVLMap;
import java.util.Vector;

public class WeakAVLMap<K extends Comparable,V> implements WeakAVLMapInterface<K,V>{

	public Node root;
	public Integer count;

	public WeakAVLMap(){
		// write your code here
		root = new Node();
		count = 0;		
	}
	
	@SuppressWarnings("unchecked")
	public V put(K key, V value){
		// write your code her 
		Node temp = root; 
		Node temp2 = root;
		Integer b = 0;
		V ans = null;
		if(temp.key!=null)
		{
			Node eg = search(key, temp);
			if(eg==null)
			{
				b+=0;
			}
			else
			{
				b+=1;
				ans = (V) eg.value;
				eg.value=value;
				return ans;
			}
		}
		else
		{
			b+=0;
		}

		if(temp.right==null && temp.left==null && temp.rank==-1)
		{
			// System.out.println("s");
			temp.rank=1;
			temp.value=value;
			temp.key=key;
			Node save1 = temp;
			Node save2 = temp;
			Node nleft = new Node();
			Node nright = new Node();
			nleft.rank=0;
			nright.rank=0;
			nleft.parent_of_ex=save1;
			nright.parent_of_ex=save2;
			temp.left=nleft;
			temp.right=nright;
		}
		else
		{
			// System.out.println("s1");
			// System.out.println(temp.key);
			while(temp.key!=null)
			{
				// System.out.println("while1");
				if(key.compareTo(temp.key)<0)
				{
					if(temp.left!=null)
					{
						temp = temp.left;
					}
				}

				else if(key.compareTo(temp.key)>0)
				{
					if(temp.right!=null)
					{
						temp = temp.right;
					}
				}		
			}
			// System.out.println(temp.key);
			temp.value=value;
			temp.key=key;
			temp.rank=1;
			temp.parent_of_ex=null;
			// System.out.println(temp.key);
			Node save1 = temp;
			Node save2 = temp;			
			Node nleft = new Node();
			Node nright = new Node();
			nleft.rank=0;
			nright.rank=0;
			nleft.parent_of_ex=save1;
			nright.parent_of_ex=save2;			
			temp.left=nleft;
			temp.right=nright;		
			// System.out.println("parent of ex check");
			// System.out.println(nright.parent_of_ex.key);
		}
		// System.out.println("after just insertion");
		// System.out.println(root.right.right.key);
		// System.out.println(temp.key);

		Node q = search(key, temp2);
		Node p = prev(key, temp2);
		// if(p!=null)
		// {
			// System.out.println(p.key);
			// System.out.println(sibling(q).parent_of_ex.key);
		// }

		
		while(p!=null && rankdif(q)!=1)
		{
			// System.out.println("while2");
			if(rankdif(sibling(q))==1)
			{
				// System.out.println("a");
				p.rank+=1;
				if(prev((K) p.key, temp2)==null)
				{
					break;
				}
				if(rankdif(p)==1)
				{
					break;
				}
				else if (rankdif(p)==0) 
				{
					q=p;
					p=prev((K) p.key, temp2);
				}
			}
			else if (rankdif(sibling(q))==2 && p.left.key!=null && p.left.key.compareTo(q.key)==0) 
			{
				// System.out.println("a1");
				if(rankdif(q.left)==1)
				{
					// System.out.println("b");
					Node t = q.left;
					Node pp = prev((K) p.key, temp2);
					if(pp==null)
					{
						// System.out.println("c1");
						Integer rsave = p.rank;
						root = q;
						Node save = q.right;
						if(q.right.key==null)
						{
							q.right.parent_of_ex=p;
						}
						q.right=p;
						p.left=save;
						q.rank=rsave;
						q.left.rank=q.rank-1;
						q.right.rank=q.rank-1;
						count+=1;
						break;		
					}
					if(pp.left==p)
					{
						// System.out.println("c2");
						Integer rsave = p.rank;
						pp.left = q;
						Node save = q.right;
						if(q.right.key==null)
						{
							q.right.parent_of_ex=p;
						}						
						q.right=p;
						p.left=save;
						q.rank=rsave;
						q.left.rank=q.rank-1;
						q.right.rank=q.rank-1;	
						count+=1;		
						break;
					}
					else
					{
						// System.out.println("c3");
						Integer rsave = p.rank;
						pp.right = q;
						Node save = q.right;
						if(q.right.key==null)
						{
							q.right.parent_of_ex=p;
						}						
						q.right=p;
						p.left=save;
						q.rank=rsave;
						q.left.rank=q.rank-1;
						q.right.rank=q.rank-1;	
						count+=1;					
						break;				
					}
				}
				else
				{
					// System.out.println("b1");
					Node t = q.right;
					Node pp = prev((K) p.key, temp2);
					if(pp==null)
					{
						// System.out.println("c1");
						Integer rsave = p.rank;
						root = t;
						Node save1 = t.right;
						Node save2 = t.left;
						if(t.right.key==null)
						{
							t.right.parent_of_ex=p;
						}
						if(t.left.key==null)
						{
							t.left.parent_of_ex=q;
						}
						q.right=save2;
						p.left=save1;
						t.left=q;
						t.right=p;
						t.rank+=1;
						p.rank-=1;
						q.rank-=1;
						// System.out.println(count);	
						count+=2;			
						// System.out.println(count);	
						break;						
					}
					if(pp.left==p)
					{
						// System.out.println("c2");
						Integer rsave = p.rank;
						pp.left = t;
						Node save1 = t.right;
						Node save2 = t.left;
						if(t.right.key==null)
						{
							t.right.parent_of_ex=p;
						}
						if(t.left.key==null)
						{
							t.left.parent_of_ex=q;
						}						
						q.right=save2;
						p.left=save1;
						t.left=q;
						t.right=p;
						t.rank+=1;
						p.rank-=1;
						q.rank-=1;
						count+=2;					
						break;
					}
					else
					{
						// System.out.println("c3");
						Integer rsave = p.rank;
						pp.right = t;
						Node save1 = t.right;
						Node save2 = t.left;
						if(t.right.key==null)
						{
							t.right.parent_of_ex=p;
						}
						if(t.left.key==null)
						{
							t.left.parent_of_ex=q;
						}						
						q.right=save2;
						p.left=save1;
						t.left=q;
						t.right=p;
						t.rank+=1;
						p.rank-=1;
						q.rank-=1;	
						count+=2;				
						break;					
					}
				}
			}
			//mirror image case
			else if (rankdif(sibling(q))==2 && p.right.key!=null && p.right.key.compareTo(q.key)==0) 
			{
				// System.out.println("b3");
				if(rankdif(q.right)==1)
				{
					Node t = q.right;
					Node pp = prev((K) p.key, temp2);
					if(pp==null)
					{
						Integer rsave = p.rank;
						root=q;
						Node save = q.left;
						if(q.left.key==null)
						{
							q.left.parent_of_ex=p;
						}						
						q.left=p;
						p.right=save;
						q.rank=rsave;
						q.left.rank=q.rank-1;
						q.right.rank=q.rank-1;	
						count+=1;				
						break;					
					}
					if(pp.right==p)
					{
						Integer rsave = p.rank;
						pp.right = q;
						Node save = q.left;
						if(q.left.key==null)
						{
							q.left.parent_of_ex=p;
						}						
						q.left=p;
						p.right=save;
						q.rank=rsave;
						q.left.rank=q.rank-1;
						q.right.rank=q.rank-1;	
						count+=1;					
						break;
					}
					else
					{
						Integer rsave = p.rank;
						pp.left = q;
						Node save = q.left;
						if(q.left.key==null)
						{
							q.left.parent_of_ex=p;
						}						
						q.left=p;
						p.right=save;
						q.rank=rsave;
						q.left.rank=q.rank-1;
						q.right.rank=q.rank-1;		
						count+=1;				
						break;						
					}
				}
				else
				{
					Node t = q.left;
					Node pp = prev((K) p.key, temp2);
					if(pp==null)
					{
						Integer rsave = p.rank;
						root = t;
						Node save1 = t.left;
						Node save2 = t.right;
						//seting parent of ex to new parent if ex is to be alloted
						if(t.right.key==null)
						{
							t.right.parent_of_ex=q;
						}
						if(t.left.key==null)
						{
							t.left.parent_of_ex=p;
						}						
						q.left=save2;
						p.right=save1;
						t.right=q;
						t.left=p;
						t.rank+=1;
						p.rank-=1;
						q.rank-=1;
						count+=2;					
						break;					
					}

					if(pp.right==p)
					{
						Integer rsave = p.rank;
						pp.right = t;
						Node save1 = t.left;
						Node save2 = t.right;
						if(t.right.key==null)
						{
							t.right.parent_of_ex=q;
						}
						if(t.left.key==null)
						{
							t.left.parent_of_ex=p;
						}						
						q.left=save2;
						p.right=save1;
						t.right=q;
						t.left=p;
						t.rank+=1;
						p.rank-=1;
						q.rank-=1;	
						count+=2;				
						break;
					}
					else
					{
						Integer rsave = p.rank;
						pp.left = t;
						Node save1 = t.left;
						Node save2 = t.right;
						if(t.right.key==null)
						{
							t.right.parent_of_ex=q;
						}
						if(t.left.key==null)
						{
							t.left.parent_of_ex=p;
						}						
						q.left=save2;
						p.right=save1;
						t.right=q;
						t.left=p;
						t.rank+=1;
						p.rank-=1;
						q.rank-=1;
						count+=2;					
						break;					
					}
				}				
			}
		}

		if(b==0)
		{
			return null;
		}
		else
		{
			return ans;
		}		
	}

	@SuppressWarnings("unchecked")
	public V remove(K key){
		// write your code her 
		Node temp = root;
		Node x = search(key, temp);
		if(x==null)
		{
			return null;
		}
		if(x.right.key==null && x.left.key==null && prev((K) x.key, temp)==null)
		{
			Node n = new Node();
			root=n;
			return (V) x.value;
		}
		else if(x.right.key!=null && x.left.key!=null)
		{
			// System.out.println("base case");
			// System.out.println("a0");
			Node v = x.right;
			while(v.left.key!=null)
			{
				v=v.left;
			}

			Node ult = v;
			Node u = null;
			Node q = null;
			Node p = prev((K) v.key, temp);
			Integer a = 0;
			if (v.right.key==null && v.left.key==null) 
			{
				// System.out.println("c0");
				q = new Node();
				q.rank = 0;
				q.parent_of_ex=p;
				a+=1;
			}
			else if(v.right.key==null)
			{
				// System.out.println("c1");
				u=v.right;
				q=v.left;
			}
			else
			{
				// System.out.println("c2");
				u=v.left;
				q=v.right;
			}
			if(p==null)
			{
				// System.out.println("c3");
				temp=q;
				if(a==1)
				{
					q.parent_of_ex=null;
				}
							V ult2 = (V) x.value;
							x.key=ult.key;
							x.value=ult.value;
							return ult2;
			}
			if(p.left.key!=null && p.left.key.compareTo(v.key)==0)
			{
				// System.out.println("c4");
				p.left=q;
				if(a==1)
				{
					// System.out.println("c4if");
					q.parent_of_ex=p;
				}
			}
			else
			{
				// System.out.println("c5");
				p.right=q;
				if(a==1)
				{
					q.parent_of_ex=p;
				}				
			}
			if(p.right.key==null && p.left.key==null)
			{
				// System.out.println("new");
				// System.out.println(q.rank);
				if(p.rank!=1)
				{
					// System.out.println("condition");
					p.rank=1;
					// System.out.println(p.rank);
					if(prev((K) p.key, temp)==null)
					{

					}
					else if (rankdif(p)==3) 
					{
						q=p;
						p=prev((K) q.key, temp);
					}
				}
			}
			// System.out.println("p");
			while(rankdif(q)==3)
			{
				// System.out.println("while");
				if(rankdif(sibling(q))==2)
				{
					// System.out.println("sib 2");
					p.rank-=1;
					if(prev((K) p.key, temp)==null)
					{
							V ult2 = (V) x.value;
							x.key=ult.key;
							x.value=ult.value;
							return ult2;
					}					
					if(rankdif(p)!=3)
					{
							V ult2 = (V) x.value;
							x.key=ult.key;
							x.value=ult.value;
							return ult2;
					}
					else
					{
						q=p;
						p=prev((K) q.key, temp);
					}
				}
				else if (rankdif(sibling(q))==1)
				{
					// System.out.println("sib 1");
					Node s = sibling(q);
					if(rankdif(s.left)==2 && rankdif(s.right)==2)
					{
						// System.out.println("case1");
						p.rank-=1;
						s.rank-=1;
						if(prev((K) p.key, temp)==null)
						{
							V ult2 = (V) x.value;
							x.key=ult.key;
							x.value=ult.value;
							return ult2;
						}						
						if(rankdif(p)!=3)
						{
							V ult2 = (V) x.value;
							x.key=ult.key;
							x.value=ult.value;
							return ult2;
						}
						else
						{
							q=p;
							p=prev((K) q.key, temp);
						}						

					}
					else
					{
						// System.out.println("case2");
						Node t = null;
						Integer sleft = 0;
						Integer sright = 0;
						Integer pright=0;
						Integer pleft=0;
						if(p.right==s)
						{
							pright+=1;
						}
						else
						{
							pleft+=1;
						}						
						if(rankdif(s.left)==1 && rankdif(s.right)==1)
						{
							if(pleft==1)
							{
								t=s.left;
								sleft+=1;
							}
							else
							{
								t=s.right;
								sright+=1;
							}
						}
						else if (rankdif(s.left)==1 && rankdif(s.right)!=1)
						{
							t=s.left;
							sleft+=1;
						}
						else if (rankdif(s.left)!=1 && rankdif(s.right)==1) 
						{
							t=s.right;
							sright+=1;
						}
						Node pp = prev((K) p.key, temp);
						if(pp==null)
						{
							// System.out.println("pp null");

							//Do from here combinations of p to s directions

							if(pleft==1)
							{
								// System.out.println("pleft s");
								if(s.left==t)
								{
									// System.out.println("s.left t");
									root=s;
									Node save = s.right;
									if(s.right.key==null)
									{
										s.right.parent_of_ex=p;
									}								
									s.right=p;
									p.left=save;
									s.rank+=1;
									p.rank-=1;
									count+=1;
									V ult2 = (V) x.value;
									x.key=ult.key;
									x.value=ult.value;
									return ult2;
								}
								else
								{
									// System.out.println("elseS");
									root=t;
									Node save1 = t.left;
									Node save2 = t.right;
									if(t.left.key==null)
									{
										t.left.parent_of_ex=s;
									}
									if(t.right.key==null)
									{
										t.right.parent_of_ex=p;
									}								
									t.left=s;
									t.right=p;
									p.left=save2;
									s.right=save1;
									s.rank-=1;
									p.rank-=2;
									t.rank+=2;
									count+=2;
									V ult2 = (V) x.value;
									x.key=ult.key;
									x.value=ult.value;
									return ult2;
								}	
							}
							if(pright==1)
							{
								if(s.left==t)
								{
									root=t;
									Node save1 = t.right;
									Node save2 = t.left;
									if(t.right.key==null)
									{
										t.right.parent_of_ex=s;
									}
									if(t.left.key==null)
									{
										t.left.parent_of_ex=p;
									}								
									t.right=s;
									t.left=p;
									p.right=save2;
									s.left=save1;
									s.rank-=1;
									p.rank-=2;
									t.rank+=2;
									count+=2;
									V ult2 = (V) x.value;
									x.key=ult.key;
									x.value=ult.value;
									return ult2;					
								}
								else
								{
									root=s;
									Node save = s.left;
									if(s.left.key==null)
									{
										s.left.parent_of_ex=p;
									}								
									s.left=p;
									p.right=save;
									s.rank+=1;
									p.rank-=1;
									count+=1;
									V ult2 = (V) x.value;
									x.key=ult.key;
									x.value=ult.value;
									return ult2;
								}									
							}
						
						}
						if(pp.left==p)
						{
							if(pleft==1)
							{
								if(s.left==t)
								{
									pp.left=s;
									Node save = s.right;
									if(s.right.key==null)
									{
										s.right.parent_of_ex=p;
									}								
									s.right=p;
									p.left=save;
									s.rank+=1;
									p.rank-=1;
									count+=1;
									V ult2 = (V) x.value;
									x.key=ult.key;
									x.value=ult.value;	
									return ult2;
								}
								else
								{
									pp.left=t;
									Node save1 = t.left;
									Node save2 = t.right;
									if(t.left.key==null)
									{
										t.left.parent_of_ex=s;
									}
									if(t.right.key==null)
									{
										t.right.parent_of_ex=p;
									}								
									t.left=s;
									t.right=p;
									p.left=save2;
									s.right=save1;
									s.rank-=1;
									p.rank-=2;
									t.rank+=2;
									count+=2;
									V ult2 = (V) x.value;
									x.key=ult.key;
									x.value=ult.value;
									return ult2;
								}	
							}
							if(pright==1)
							{
								if(s.left==t)
								{
									pp.left=t;
									Node save1 = t.right;
									Node save2 = t.left;
									if(t.right.key==null)
									{
										t.right.parent_of_ex=s;
									}
									if(t.left.key==null)
									{
										t.left.parent_of_ex=p;
									}								
									t.right=s;
									t.left=p;
									p.right=save2;
									s.left=save1;
									s.rank-=1;
									p.rank-=2;
									t.rank+=2;
									count+=2;
									V ult2 = (V) x.value;
									x.key=ult.key;
									x.value=ult.value;
									return ult2;						
								}
								else
								{
									pp.left=s;
									Node save = s.left;
									if(s.left.key==null)
									{
										s.left.parent_of_ex=p;
									}								
									s.left=p;
									p.right=save;
									s.rank+=1;
									p.rank-=1;
									count+=1;
									V ult2 = (V) x.value;
									x.key=ult.key;
									x.value=ult.value;
									return ult2;
								}									
							}
						}
						else if (pp.right==p) 
						{
							if(pleft==1)
							{
								if(s.left==t)
								{
									pp.right=s;
									Node save = s.right;
									if(s.right.key==null)
									{
										s.right.parent_of_ex=p;
									}								
									s.right=p;
									p.left=save;
									s.rank+=1;
									p.rank-=1;
									count+=1;
									V ult2 = (V) x.value;
									x.key=ult.key;
									x.value=ult.value;
									return ult2;
								}
								else
								{
									pp.right=t;
									Node save1 = t.left;
									Node save2 = t.right;
									if(t.left.key==null)
									{
										t.left.parent_of_ex=s;
									}
									if(t.right.key==null)
									{
										t.right.parent_of_ex=p;
									}								
									t.left=s;
									t.right=p;
									p.left=save2;
									s.right=save1;
									s.rank-=1;
									p.rank-=2;
									t.rank+=2;
									count+=2;
									V ult2 = (V) x.value;
									x.key=ult.key;
									x.value=ult.value;
									return ult2;
								}	
							}
							if(pright==1)
							{
								if(s.left==t)
								{
									pp.right=t;
									Node save1 = t.right;
									Node save2 = t.left;
									if(t.right.key==null)
									{
										t.right.parent_of_ex=s;
									}
									if(t.left.key==null)
									{
										t.left.parent_of_ex=p;
									}								
									t.right=s;
									t.left=p;
									p.right=save2;
									s.left=save1;
									s.rank-=1;
									p.rank-=2;
									t.rank+=2;
									count+=2;
									V ult2 = (V) x.value;
									x.key=ult.key;
									x.value=ult.value;
									return ult2;					
								}
								else
								{
									pp.right=s;
									Node save = s.left;
									if(s.left.key==null)
									{
										s.left.parent_of_ex=p;
									}								
									s.left=p;
									p.right=save;
									s.rank+=1;
									p.rank-=1;
									count+=1;
									V ult2 = (V) x.value;
									x.key=ult.key;
									x.value=ult.value;
									return ult2;
								}									
							}					
						}
						
					}
				}
			}


			V ult2 = (V) x.value;
			x.key=ult.key;
			x.value=ult.value;
			return ult2;
		}
		else
		{
			// System.out.println("not base case");
			// System.out.println("b");
			Node v = x;
			Node u = null;
			Node q = null;
			Node p = prev((K) v.key, temp);
			Integer a = 0;
			if (v.right.key==null && v.left.key==null) 
			{
				// System.out.println("c0");
				q = new Node();
				q.rank = 0;
				q.parent_of_ex=p;
				a+=1;
			}
			else if(v.right.key==null)
			{
				// System.out.println("c1");
				u=v.right;
				q=v.left;
			}
			else
			{
				// System.out.println("c2");
				u=v.left;
				q=v.right;
			}
			if(p==null)
			{
				// System.out.println("c3");
				temp=q;
				if(a==1)
				{
					q.parent_of_ex=null;
				}
				return (V) x.value;
			}
			if(p.left.key!=null && p.left.key.compareTo(v.key)==0)
			{
				// System.out.println("c4");
				p.left=q;
				if(a==1)
				{
					q.parent_of_ex=p;
				}
			}
			else
			{
				// System.out.println("c5");
				p.right=q;
				if(a==1)
				{
					q.parent_of_ex=p;
				}				
			}
			//special case
			if(p.right.key==null && p.left.key==null)
			{
				// System.out.println("new");
				// System.out.println(q.rank);
				if(p.rank!=1)
				{
					// System.out.println("condition");
					p.rank=1;
					// System.out.println(p.rank);
					if(prev((K) p.key, temp)==null)
					{

					}
					else if (rankdif(p)==3) 
					{
						q=p;
						p=prev((K) q.key, temp);
					}
				}
			}			
			// System.out.println("p");
			while(rankdif(q)==3)
			{
				// System.out.println("while");
				if(rankdif(sibling(q))==2)
				{
					// System.out.println("sib 2");
					p.rank-=1;
					if(prev((K) p.key, temp)==null)
					{
						return (V) x.value;
					}					
					if(rankdif(p)!=3)
					{
						return (V) x.value;
					}
					else
					{
						q=p;
						p=prev((K) q.key, temp);
					}
				}
				else if (rankdif(sibling(q))==1)
				{
					// System.out.println("sib 1");
					Node s = sibling(q);
					// System.out.println(q.key);
					if(rankdif(s.left)==2 && rankdif(s.right)==2)
					{
						// System.out.println("case1");
						p.rank-=1;
						s.rank-=1;
						// System.out.println(p.key);
						if(prev((K) p.key, temp)==null)
						{
							return (V) x.value;
						}
						if(rankdif(p)!=3)
						{
							return (V) x.value;
						}
						else
						{
							q=p;
							p=prev((K) q.key, temp);
						}						

					}
					else
					{
						// System.out.println("case2");
						Node t = null;
						Integer sleft = 0;
						Integer sright = 0;
						Integer pright=0;
						Integer pleft=0;
						if(p.right==s)
						{
							// System.out.println("d");
							pright+=1;
						}
						else
						{
							// System.out.println("d1");
							pleft+=1;
						}						
						if(rankdif(s.left)==1 && rankdif(s.right)==1)
						{
							if(pleft==1)
							{
								t=s.left;
								sleft+=1;
							}
							else
							{
								t=s.right;
								sright+=1;
							}
						}
						else if (rankdif(s.left)==1 && rankdif(s.right)!=1)
						{
							t=s.left;
							sleft+=1;
						}
						else if (rankdif(s.left)!=1 && rankdif(s.right)==1) 
						{
							t=s.right;
							sright+=1;
						}
						// System.out.println(sright);
						// System.out.println(sleft);
						Node pp = prev((K) p.key, temp);
						if(pp==null)
						{
							// System.out.println("pp null");

							//Do from here combinations of p to s directions

							if(pleft==1)
							{
								if(s.left==t)
								{
									root=s;
									Node save = s.right;
									if(s.right.key==null)
									{
										s.right.parent_of_ex=p;
									}								
									s.right=p;
									p.left=save;
									s.rank+=1;
									p.rank-=1;
									count+=1;
									return (V) x.value;
								}
								else
								{
									root=t;
									Node save1 = t.left;
									Node save2 = t.right;
									if(t.left.key==null)
									{
										t.left.parent_of_ex=s;
									}
									if(t.right.key==null)
									{
										t.right.parent_of_ex=p;
									}								
									t.left=s;
									t.right=p;
									p.left=save2;
									s.right=save1;
									s.rank-=1;
									p.rank-=2;
									t.rank+=2;
									count+=2;
									return (V) x.value;
								}	
							}
							if(pright==1)
							{
								if(s.left==t)
								{
									root=t;
									Node save1 = t.right;
									Node save2 = t.left;
									if(t.right.key==null)
									{
										t.right.parent_of_ex=s;
									}
									if(t.left.key==null)
									{
										t.left.parent_of_ex=p;
									}								
									t.right=s;
									t.left=p;
									p.right=save2;
									s.left=save1;
									s.rank-=1;
									p.rank-=2;
									t.rank+=2;
									count+=2;
									return (V) x.value;								
								}
								else
								{
									root=s;
									Node save = s.left;
									if(s.left.key==null)
									{
										s.left.parent_of_ex=p;
									}								
									s.left=p;
									p.right=save;
									s.rank+=1;
									p.rank-=1;
									count+=1;
									return (V) x.value;
								}									
							}
						
						}
						if(pp.left==p)
						{
							// System.out.println("pp left");
							if(pleft==1)
							{
								if(s.left==t)
								{
									pp.left=s;
									Node save = s.right;
									if(s.right.key==null)
									{
										s.right.parent_of_ex=p;
									}								
									s.right=p;
									p.left=save;
									s.rank+=1;
									p.rank-=1;
									count+=1;
									return (V) x.value;
								}
								else
								{
									pp.left=t;
									Node save1 = t.left;
									Node save2 = t.right;
									if(t.left.key==null)
									{
										t.left.parent_of_ex=s;
									}
									if(t.right.key==null)
									{
										t.right.parent_of_ex=p;
									}								
									t.left=s;
									t.right=p;
									p.left=save2;
									s.right=save1;
									s.rank-=1;
									p.rank-=2;
									t.rank+=2;
									count+=2;
									return (V) x.value;
								}	
							}
							if(pright==1)
							{
								// System.out.println("pright");
								if(s.left==t)
								{
									pp.left=t;
									Node save1 = t.right;
									Node save2 = t.left;
									if(t.right.key==null)
									{
										t.right.parent_of_ex=s;
									}
									if(t.left.key==null)
									{
										t.left.parent_of_ex=p;
									}								
									t.right=s;
									t.left=p;
									p.right=save2;
									s.left=save1;
									s.rank-=1;
									p.rank-=2;
									t.rank+=2;
									count+=2;
									// System.out.println(t.rank);
									return (V) x.value;								
								}
								else
								{
									pp.left=s;
									Node save = s.left;
									if(s.left.key==null)
									{
										s.left.parent_of_ex=p;
									}								
									s.left=p;
									p.right=save;
									s.rank+=1;
									p.rank-=1;
									count+=1;
									return (V) x.value;
								}									
							}
						}
						else if (pp.right==p) 
						{
							if(pleft==1)
							{
								if(s.left==t)
								{
									pp.right=s;
									Node save = s.right;
									if(s.right.key==null)
									{
										s.right.parent_of_ex=p;
									}								
									s.right=p;
									p.left=save;
									s.rank+=1;
									p.rank-=1;
									count+=1;
									return (V) x.value;
								}
								else
								{
									pp.right=t;
									Node save1 = t.left;
									Node save2 = t.right;
									if(t.left.key==null)
									{
										t.left.parent_of_ex=s;
									}
									if(t.right.key==null)
									{
										t.right.parent_of_ex=p;
									}								
									t.left=s;
									t.right=p;
									p.left=save2;
									s.right=save1;
									s.rank-=1;
									p.rank-=2;
									t.rank+=2;
									count+=2;
									return (V) x.value;
								}	
							}
							if(pright==1)
							{
								if(s.left==t)
								{
									pp.right=t;
									Node save1 = t.right;
									Node save2 = t.left;
									if(t.right.key==null)
									{
										t.right.parent_of_ex=s;
									}
									if(t.left.key==null)
									{
										t.left.parent_of_ex=p;
									}								
									t.right=s;
									t.left=p;
									p.right=save2;
									s.left=save1;
									s.rank-=1;
									p.rank-=2;
									t.rank+=2;
									count+=2;
									return (V) x.value;								
								}
								else
								{
									pp.right=s;
									Node save = s.left;
									if(s.left.key==null)
									{
										s.left.parent_of_ex=p;
									}								
									s.left=p;
									p.right=save;
									s.rank+=1;
									p.rank-=1;
									count+=1;
									return (V) x.value;
								}									
							}					
						}
						
					}
				}
			}


		}
		return (V) x.value;
	}

	@SuppressWarnings("unchecked")
	public V get(K key){
		// write your code her 
		Node temp = root;
		Node temp1 = search(key, temp);
		if(temp1==null)
		{
			return null;
		}
		else
		{
			return (V) temp1.value;
		}
	}

	@SuppressWarnings("unchecked")
	public Vector<V> searchRange(K key1, K key2){
		// write your code her 
		WeakAVLMap obj = new WeakAVLMap();
		Node temp = root;
		Vector<V> v = new Vector<V>();	
		obj.help(v, key1, key2, temp);
		return v;
	}

	@SuppressWarnings("unchecked")
	public void help(Vector<V> vector, K key1, K key2, Node input)
	{
		WeakAVLMap obj = new WeakAVLMap();
		Node temp = input;
		Vector<V> v = vector;
		if(temp==null || temp.key==null)
		{
			return;
		}
		obj.help(v, key1, key2, temp.left);
		if(temp.key.compareTo(key2)<=0 && temp.key.compareTo(key1)>=0)
		{
			v.add((V)temp.value);
		}
		obj.help(v, key1, key2, temp.right);

	}

	public int rotateCount(){
		// write your code her 
		Integer ans = count;
		return ans;
	}

	public int getHeight(){
		// write your code her 
		WeakAVLMap temp1 = this;
		Node temp = root;
		if(root.key==null)
		{
			return 0;
		}
    	if(temp.right.key == null && temp.left.key == null)
    	{
    		return 1;
    	}
    	else if (temp.right.key!=null && temp.left.key==null) 
    	{
    		WeakAVLMap n1 = new WeakAVLMap();
    		n1.root = temp.right;
    		n1.count = temp1.count;
    		int right_height = n1.getHeight();
    		return right_height+1;
    	}
    	else if (temp.right.key==null && temp.left.key!=null) 
    	{
    		WeakAVLMap n1 = new WeakAVLMap();
    		n1.root = temp.left;
    		n1.count = temp1.count;    		
    		int left_height = n1.getHeight();
	   		return left_height+1;
    	}
    	else
    	{
    		WeakAVLMap n1 = new WeakAVLMap();
    		n1.root = temp.left;
    		n1.count = temp1.count;
    		WeakAVLMap n2 = new WeakAVLMap();
    		n2.root = temp.right;
    		n2.count = temp1.count;    		    		
    		int left_height = n1.getHeight();
    		int right_height = n2.getHeight();
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
	public Vector<K> BFS(){
		// write your code her 
		Vector<K> v = new Vector<K>();
		if(root.key==null)
		{
			return v;
		}
		Node temp = root;
		WeakAVLMap obj = new WeakAVLMap();
		obj.print(temp, v);
		return v;
	}

	@SuppressWarnings("unchecked")
    public void level(int a, Node input, Vector<K> vector){
    	WeakAVLMap obj = new WeakAVLMap();
    	// obj.count = count;
    	Node temp = input;
    	Vector<K> v = vector;
    	if(a==1)
    	{
    		if(temp.value!=null)
    		{
    			v.add((K)temp.key);	
    		}
    		
    		// System.out.println(temp.keybst + ", " + temp.valuebst);
    	}
    	else if (a>1) 
    	{
    		if(temp.right!=null && temp.left!=null)
    		{
    			int b = a-1;
    			obj.level(b, temp.left, v);
    			obj.level(b, temp.right, v);
    		}
    		else if (temp.right!=null && temp.left==null) 
    		{
    			int b = a-1;
    			obj.level(b, temp.right, v);
    			// temp.right.print_level(b);
    		}
     		else if (temp.right==null && temp.left!=null) 
    		{
    			int b = a-1;
    			// temp.left.print_level(b);
    			obj.level(b, temp.left, v);
    		}   		
    	}
    }	

    @SuppressWarnings("unchecked")
    public void print(Node input, Vector<K> vector) {
    	Node temp = input;
    	WeakAVLMap obj = new WeakAVLMap();
    	// WeakAVLMap obj1 = this;
    	obj.root = temp;
    	Vector<K> v = vector;
    	int h = obj.getHeight();
    	// System.out.println(h);
    	// System.out.println(obj1.root.right.key);
    	for (int i=1; i<=h ;i++ ) 
    	{
    		obj.level(i, temp, v);	
    	}
		//write your code here
    }

    @SuppressWarnings("unchecked")
	public Node sibling(Node input){
		Node temp = input;
		Node temp2 = root;
		if(temp.key==null)
		{
			Node parent = temp.parent_of_ex;
			if(parent.right.key==null)
			{
				return parent.left;
			}
			else
			{
				return parent.right;
			}
		}
		Node parent = prev((K) temp.key, temp2);
		if(parent.right==temp)
		{
			return parent.left;
		}		
		else
		{
			return parent.right;
		}
	}    

	@SuppressWarnings("unchecked")
	public Integer rankdif(Node input){
		Node temp = input;
		Node temp2 = root;
		if(temp.key==null)
		{
			Integer b = temp.parent_of_ex.rank - temp.rank;
			return b;
		}
		Node parent = prev((K) temp.key, temp2);
		Integer a = parent.rank - temp.rank;
		return a;
	}        

	@SuppressWarnings("unchecked")
	public Node search(K key, Node input){
		Node temp = input;
		while(temp.key.compareTo(key)!=0)
		{
			if(temp.key.compareTo(key)>0)
			{
				if(temp.left==null)
				{
					return null;
				}
				else
				{
					temp = temp.left;
					if(temp.key==null)
					{
						return null;
					}	
				}
				
			}
			else
			{
				if(temp.right==null)
				{
					return null;
				}
				else
				{
					temp = temp.right;	
					if(temp.key==null)
					{
						return null;
					}					
				}
			}
		}
		return temp;
	}

	@SuppressWarnings("unchecked")
	public Node prev(K key, Node input){
		Node temp = input;
		Node temp2 = null;
		// System.out.println("a");
		if(temp.key==null)
		{
			return temp.parent_of_ex;
		}
		// System.out.println("a1");
		// System.out.println(key);
		while(temp.key.compareTo(key)!=0)
		{
			if(temp.key.compareTo(key)<0)
			{
				temp2 = temp;
				temp = temp.right;
			}
			else
			{
				temp2 = temp;
				temp = temp.left;
			}
		}
		return temp2;
	}	

}
