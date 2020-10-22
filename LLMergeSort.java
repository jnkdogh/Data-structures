package col106.assignment5;

import java.util.Comparator;

/*
Implementation of MergeSort Algorithm :
    1. you get linked list of size <=1 you just return the list as it is already sorted
    2. Find mid node using findSplit method(Don't forget to add mid element to globalList before returning it)
    3. Create two LinkedList lt (with head = head and tail = mid) and rt (with head = mid.next and tail = tail)
    4. Now recursively MergSort lt and rt Linked lists(Maintain this order)
    5. Now merge these two lists that we got from recursive calls using given crieteria for ordering
    6. Return merged Linked list
*/

public class LLMergeSort<T>{

  LinkedList<T>  globalList = new LinkedList<T>();

  //CALL THIS METHOD AFTER EVERY CALL OF findSplit and DO NOT MODIFY THIS METHOD
  @SuppressWarnings("unchecked")
  public void adjustGlobalPointer(T node){
      globalList.add(node);
  }

  // Utility function to get the middle of the linked list
  @SuppressWarnings("unchecked")
  public Node<T> findSplit(LinkedList<T>  lst) {
    //find middle node of LL :
    Node<T> middle = lst.getHead();
    //Enter your code here
    if(lst.getSize()%2==0){
      int count = 1;
      int mid = lst.getSize()/2;
      while(count!=mid){
        middle=middle.getNext();
        count++;
      }
    }
    else{
      int count = 1;
      int mid = (lst.getSize()+1)/2;
      while(count!=mid){
        middle=middle.getNext();
        count++;
      }      
    }
    //!!!!!*****DO NOT REMOVE THIS METHOD CALL (change the argument apprpriately)*****!!!!!
    adjustGlobalPointer(middle.getData()); //Add object of ItemNode after finding mid in each call
    return middle;
  }


  @SuppressWarnings("unchecked")
  public LinkedList<T>  MergeSort(LinkedList<T>  lst) {
    if(lst.getSize()==0 | lst.getSize()==1){
      return lst;
    }
    else{
      Node<T> midN = this.findSplit(lst);
      Node<T> temp = midN.getNext();
      LinkedList<T>  lt = new LinkedList<T>();
      LinkedList<T>  rt = new LinkedList<T>();
      Node<T> temp1 = lst.getHead();
      lt.add(temp1.getData());
      lt.getTail().setq(temp1.getq());
      lt.getTail().setdate1(temp1.getdate1());
      lt.getTail().setdate2(temp1.getdate2());
      while(temp1.getData()!=midN.getData()){
        temp1=temp1.getNext();
        lt.add(temp1.getData());
        lt.getTail().setq(temp1.getq());
        lt.getTail().setdate1(temp1.getdate1());
        lt.getTail().setdate2(temp1.getdate2());
      }
      temp1=temp1.getNext();
      while(temp1!=null){
        rt.add(temp1.getData());
        rt.getTail().setq(temp1.getq());
        rt.getTail().setdate1(temp1.getdate1());
        rt.getTail().setdate2(temp1.getdate2());
        temp1=temp1.getNext();
      }      
      LinkedList<T> sortlt = this.MergeSort(lt);
      LinkedList<T> sortrt = this.MergeSort(rt);
      if(midN.getq()==1){
      LinkedList<T> ans = this.Merge1(sortlt, sortrt);
      return ans;
      }
      else if(midN.getq()==2){
      LinkedList<T> ans = this.Merge2(sortlt, sortrt);
      return ans;        
      }
      else if(midN.getq()==3){
      LinkedList<T> ans = this.Merge3(sortlt, sortrt);
      return ans;        
      }
      else if(midN.getq()==4){
      LinkedList<T> ans = this.Merge4(sortlt, sortrt);
      return ans;        
      }
      else if(midN.getq()==5){
      LinkedList<T> ans = this.Merge5(sortlt, sortrt);
      return ans;        
      }                  
      else{
      LinkedList<T> ans = this.Merge(sortlt, sortrt);
      return ans;
      }
    }
  }

  @SuppressWarnings("unchecked")
  public LinkedList<T> Merge(LinkedList<T> l1, LinkedList<T> l2){
    if(l1.getSize()==1 && l2.getSize()==1){
      if(((String)l1.getHead().getData()).compareTo((String)l2.getHead().getData())<=0){
        LinkedList<T> ans = new LinkedList<T>();
        ans.add(l1.getHead().getData());
        ans.add(l2.getHead().getData());
        return ans;
      }    
      else{
        LinkedList<T> ans = new LinkedList<T>();
        ans.add(l2.getHead().getData());
        ans.add(l1.getHead().getData());
        return ans;      
      }
    }
    else{
      l1.getTail().setNext(l2.getHead());
      LinkedList<T> ans = new LinkedList<T>();
      Node<T> nd = l1.getHead();
      Node<T> nd1 = l2.getHead();
      while(nd!=null && nd1!=null){
        if(((String)nd.getData()).compareTo((String)nd1.getData())<=0){
        ans.add(nd.getData());
        nd=nd.getNext();
        }
        else{
        ans.add(nd1.getData());
        nd1=nd1.getNext();          
        }
      }

      if(nd==null && nd1!=null){
        while(nd1!=null){
          ans.add(nd1.getData());
          nd1=nd1.getNext();
        }
      }
      else if (nd!=null && nd1==null) {
          ans.add(nd.getData());
          nd=nd.getNext();        
      }
      return ans;
    }
  }

  @SuppressWarnings("unchecked")
  public LinkedList<T> Merge2(LinkedList<T> l1, LinkedList<T> l2){
    if(l1.getSize()==1 && l2.getSize()==1){
      // System.out.println("size 1");
      if(this.compare2(l1.getHead(), l2.getHead())<=0){
        LinkedList<T> ans = new LinkedList<T>();
        ans.add(l1.getHead().getData());
        ans.getTail().setq(l1.getHead().getq());
        ans.getTail().setdate1(l1.getHead().getdate1());
        ans.getTail().setdate2(l1.getHead().getdate2());
        ans.add(l2.getHead().getData());
        ans.getTail().setq(l2.getHead().getq());
        ans.getTail().setdate1(l2.getHead().getdate1());
        ans.getTail().setdate2(l2.getHead().getdate2());      
        return ans;
      }    
      else{

        LinkedList<T> ans = new LinkedList<T>();
        ans.add(l2.getHead().getData());
        ans.getTail().setq(l2.getHead().getq());
        ans.getTail().setdate1(l2.getHead().getdate1());
        ans.getTail().setdate2(l2.getHead().getdate2());
        ans.add(l1.getHead().getData());
        ans.getTail().setq(l1.getHead().getq());
        ans.getTail().setdate1(l1.getHead().getdate1());
        ans.getTail().setdate2(l1.getHead().getdate2());       
        return ans;      
      }
    }
    else{

      LinkedList<T> ans = new LinkedList<T>();
      Node<T> nd = l1.getHead();
      Node<T> nd1 = l2.getHead();
      while(nd!=null && nd1!=null){
        if(this.compare2(nd, nd1)<=0){
        ans.add(nd.getData());
        ans.getTail().setq(nd.getq());
        ans.getTail().setdate1(nd.getdate1());
        ans.getTail().setdate2(nd.getdate2());
        nd=nd.getNext();
        }
        else{
        ans.add(nd1.getData());
        ans.getTail().setq(nd1.getq());
        ans.getTail().setdate1(nd1.getdate1());
        ans.getTail().setdate2(nd1.getdate2());
        nd1=nd1.getNext();          
        }
      }

      if(nd==null && nd1!=null){
        while(nd1!=null){
          ans.add(nd1.getData());
          ans.getTail().setq(nd1.getq());
          ans.getTail().setdate1(nd1.getdate1());
          ans.getTail().setdate2(nd1.getdate2());
          nd1=nd1.getNext();
        }
      }
      else if (nd!=null && nd1==null) {
        while(nd!=null){
          ans.add(nd.getData());
          ans.getTail().setq(nd.getq());
          ans.getTail().setdate1(nd.getdate1());
          ans.getTail().setdate2(nd.getdate2());          
          nd=nd.getNext();        
        }
      }
      return ans;
    }
  }  

  @SuppressWarnings("unchecked")
  public LinkedList<T> Merge1(LinkedList<T> l1, LinkedList<T> l2){
    // System.out.println("merge1");
    if(l1.getSize()==1 && l2.getSize()==1){
      if(this.compare1(l1.getHead().getData(), l2.getHead().getData())<=0){
        LinkedList<T> ans = new LinkedList<T>();
        ans.add(l1.getHead().getData());
        ans.getTail().setq(l1.getHead().getq());
        ans.add(l2.getHead().getData());
        ans.getTail().setq(l2.getHead().getq());
        return ans;
      }    
      else{
        LinkedList<T> ans = new LinkedList<T>();
        ans.add(l2.getHead().getData());
        ans.getTail().setq(l2.getHead().getq());
        ans.add(l1.getHead().getData());
        ans.getTail().setq(l1.getHead().getq());
        return ans;      
      }
    }
    else{
      LinkedList<T> ans = new LinkedList<T>();
      Node<T> nd = l1.getHead();
      Node<T> nd1 = l2.getHead();
      while(nd!=null && nd1!=null){
        if(this.compare1(nd.getData(), nd1.getData())<=0){
        ans.add(nd.getData());
        ans.getTail().setq(nd.getq());
        nd=nd.getNext();
        }
        else{
        ans.add(nd1.getData());
        ans.getTail().setq(nd1.getq());
        nd1=nd1.getNext();          
        }
      }

      if(nd==null && nd1!=null){
        while(nd1!=null){
          ans.add(nd1.getData());
          ans.getTail().setq(nd1.getq());
          nd1=nd1.getNext();
        }
      }
      else if (nd!=null && nd1==null) {
        while(nd!=null){
          ans.add(nd.getData());
          ans.getTail().setq(nd.getq());
          nd=nd.getNext();        
        }
      }
      return ans;
    }
  }  

  @SuppressWarnings("unchecked")
  public LinkedList<T> Merge5(LinkedList<T> l1, LinkedList<T> l2){
    // System.out.println("merge1");
    if(l1.getSize()==1 && l2.getSize()==1){
      if(this.compare5(l1.getHead().getData(), l2.getHead().getData())<=0){
        LinkedList<T> ans = new LinkedList<T>();
        ans.add(l1.getHead().getData());
        ans.getTail().setq(l1.getHead().getq());
        ans.add(l2.getHead().getData());
        ans.getTail().setq(l2.getHead().getq());
        return ans;
      }    
      else{
        LinkedList<T> ans = new LinkedList<T>();
        ans.add(l2.getHead().getData());
        ans.getTail().setq(l2.getHead().getq());
        ans.add(l1.getHead().getData());
        ans.getTail().setq(l1.getHead().getq());
        return ans;      
      }
    }
    else{
      LinkedList<T> ans = new LinkedList<T>();
      Node<T> nd = l1.getHead();
      Node<T> nd1 = l2.getHead();
      while(nd!=null && nd1!=null){
        if(this.compare5(nd.getData(), nd1.getData())<=0){
        ans.add(nd.getData());
        ans.getTail().setq(nd.getq());
        nd=nd.getNext();
        }
        else{
        ans.add(nd1.getData());
        ans.getTail().setq(nd1.getq());
        nd1=nd1.getNext();          
        }
      }

      if(nd==null && nd1!=null){
        while(nd1!=null){
          ans.add(nd1.getData());
          ans.getTail().setq(nd1.getq());
          nd1=nd1.getNext();
        }
      }
      else if (nd!=null && nd1==null) {
        while(nd!=null){
          ans.add(nd.getData());
          ans.getTail().setq(nd.getq());
          nd=nd.getNext();        
        }
      }
      return ans;
    }
  }    

  @SuppressWarnings("unchecked")
  public LinkedList<T> Merge4(LinkedList<T> l1, LinkedList<T> l2){
    // System.out.println("merge1");
    if(l1.getSize()==1 && l2.getSize()==1){
      if(this.compare4(l1.getHead().getData(), l2.getHead().getData())<=0){
        LinkedList<T> ans = new LinkedList<T>();
        ans.add(l1.getHead().getData());
        ans.getTail().setq(l1.getHead().getq());
        ans.add(l2.getHead().getData());
        ans.getTail().setq(l2.getHead().getq());
        return ans;
      }    
      else{
        LinkedList<T> ans = new LinkedList<T>();
        ans.add(l2.getHead().getData());
        ans.getTail().setq(l2.getHead().getq());
        ans.add(l1.getHead().getData());
        ans.getTail().setq(l1.getHead().getq());
        return ans;      
      }
    }
    else{
      LinkedList<T> ans = new LinkedList<T>();
      Node<T> nd = l1.getHead();
      Node<T> nd1 = l2.getHead();
      while(nd!=null && nd1!=null){
        if(this.compare4(nd.getData(), nd1.getData())<=0){
        ans.add(nd.getData());
        ans.getTail().setq(nd.getq());
        nd=nd.getNext();
        }
        else{
        ans.add(nd1.getData());
        ans.getTail().setq(nd1.getq());
        nd1=nd1.getNext();          
        }
      }

      if(nd==null && nd1!=null){
        while(nd1!=null){
          ans.add(nd1.getData());
          ans.getTail().setq(nd1.getq());
          nd1=nd1.getNext();
        }
      }
      else if (nd!=null && nd1==null) {
        while(nd!=null){
          ans.add(nd.getData());
          ans.getTail().setq(nd.getq());
          nd=nd.getNext();        
        }
      }
      return ans;
    }
  }  

  @SuppressWarnings("unchecked")
  public LinkedList<T> Merge3(LinkedList<T> l1, LinkedList<T> l2){
    // System.out.println("merge1");
    if(l1.getSize()==1 && l2.getSize()==1){
      if(this.compare3(l1.getHead().getData(), l2.getHead().getData())<=0){
        LinkedList<T> ans = new LinkedList<T>();
        ans.add(l1.getHead().getData());
        ans.getTail().setq(l1.getHead().getq());
        ans.add(l2.getHead().getData());
        ans.getTail().setq(l2.getHead().getq());
        return ans;
      }    
      else{
        LinkedList<T> ans = new LinkedList<T>();
        ans.add(l2.getHead().getData());
        ans.getTail().setq(l2.getHead().getq());
        ans.add(l1.getHead().getData());
        ans.getTail().setq(l1.getHead().getq());
        return ans;      
      }
    }
    else{
      LinkedList<T> ans = new LinkedList<T>();
      Node<T> nd = l1.getHead();
      Node<T> nd1 = l2.getHead();
      while(nd!=null && nd1!=null){
        if(this.compare3(nd.getData(), nd1.getData())<=0){
        ans.add(nd.getData());
        ans.getTail().setq(nd.getq());
        nd=nd.getNext();
        }
        else{
        ans.add(nd1.getData());
        ans.getTail().setq(nd1.getq());
        nd1=nd1.getNext();          
        }
      }

      if(nd==null && nd1!=null){
        while(nd1!=null){
          ans.add(nd1.getData());
          ans.getTail().setq(nd1.getq());
          nd1=nd1.getNext();
        }
      }
      else if (nd!=null && nd1==null) {
        while(nd!=null){
          ans.add(nd.getData());
          ans.getTail().setq(nd.getq());
          nd=nd.getNext();        
        }
      }
      return ans;
    }
  }   

  @SuppressWarnings("unchecked")
  public int compare5(T in1, T in2){
    DateNode d1 = ((PurchaseNode)in1).getDate();
    DateNode d2 = ((PurchaseNode)in2).getDate();

    if(d1.getYear()<d2.getYear()){
      return -1;
    }
    else if(d1.getYear()>d2.getYear()){
      return 1;
    }
    else{
      if(d1.getMonth()<d2.getMonth()){
        return -1;
      }
      else if(d1.getMonth()>d2.getMonth()){
        return 1;
      }     
      else{
        if(d1.getDay()<d2.getDay()){
          return -1;
        }
        else if(d1.getDay()>d2.getDay()){
          return 1;
        } 
        else{
          return -1;
        }
      } 
    }    
  }  

  @SuppressWarnings("unchecked")
  public int compare4(T in1, T in2){
    Node<PurchaseNode> pn1 = ((ItemNode) in1).getPurchaseHead();
    DateNode d1;
    if(pn1==null){
      d1 = new DateNode(1, 8, 1970);
    }
    else{
      while(pn1.getNext()!=null){
        pn1=pn1.getNext();
      }      
      d1=pn1.getData().getDate();
    }

    Node<PurchaseNode> pn2 = ((ItemNode) in2).getPurchaseHead();
    DateNode d2;
    if(pn2==null){
      d2 = new DateNode(1, 8, 1970);
    }
    else{
      while(pn2.getNext()!=null){
        pn2=pn2.getNext();
      }      
      d2=pn2.getData().getDate();
    }

    if(d1.getYear()>d2.getYear()){
      return -1;
    }
    else if(d1.getYear()<d2.getYear()){
      return 1;
    }
    else{
      if(d1.getMonth()>d2.getMonth()){
        return -1;
      }
      else if(d1.getMonth()<d2.getMonth()){
        return 1;
      }     
      else{
        if(d1.getDay()>d2.getDay()){
          return -1;
        }
        else if(d1.getDay()<d2.getDay()){
          return 1;
        } 
        else{
          if(((String) ((ItemNode)in1).getItemName()).compareTo((String) ((ItemNode)in2).getItemName())<=0){
            return 1;
          }
          else{
            return -1;
          }
        }
      } 
    }    

  }  

  @SuppressWarnings("unchecked")
  public int compare3(T in1, T in2){
    int s1 = ((ItemNode) in1).getStockLeft();
    int s2 = ((ItemNode) in2).getStockLeft();
    if(s1<s2){
      return 1;
    }
    else if(s1>s2){
      return -1;
    }
    else{
      if(((String) ((ItemNode)in1).getItemName()).compareTo((String) ((ItemNode)in2).getItemName())<=0){
        return 1;
      }
      else{
        return -1;  
      }       
    }
  }

  @SuppressWarnings("unchecked")
  public int compare2(Node<T> in1, Node<T> in2){
    // System.out.println("compare2");
    LinkedList<PurchaseNode> pl1 = ((ItemNode) in1.getData()).getPurchaseList();
    LinkedList<PurchaseNode> pl2 = ((ItemNode) in2.getData()).getPurchaseList();
    DateNode ds1 = in1.getdate1();
    DateNode de1 = in1.getdate2();
    DateNode ds2 = in2.getdate1();
    DateNode de2 = in2.getdate2();
    float tot1 = 0;
    float tot2 = 0;
    Node<PurchaseNode> pn1 = ((ItemNode) in1.getData()).getPurchaseHead(); 
    Node<PurchaseNode> pn2 = ((ItemNode) in2.getData()).getPurchaseHead();      
    while(pn1!=null){
      if((ds1.getYear()+ds1.getMonth()+ds1.getDay())!=0){
          if(this.comp(pn1.getData().getDate(), ds1)>=0 && this.comp(pn1.getData().getDate(), de1)<=0){
            tot1+=pn1.getData().numItemsPurchased();
            pn1=pn1.getNext();        
          }  
          else{
            pn1=pn1.getNext();
          }
      }
      else{
        pn1=pn1.getNext();
      }
    }
    while(pn2!=null){
      if((ds2.getYear()+ds2.getMonth()+ds2.getDay())!=0){
        if(this.comp(pn2.getData().getDate(), ds2)>=0 && this.comp(pn2.getData().getDate(), de2)<=0){
          tot2+=pn2.getData().numItemsPurchased();
          pn2=pn2.getNext();           
        }
        else{
          pn2=pn2.getNext();
        }
      }      
      else{
        pn2=pn2.getNext();
      }      
    }   
    LinkedList<PurchaseNode> l1 = new LinkedList<PurchaseNode>();
    Node<PurchaseNode> pp1 = pl1.getHead();
    while(pp1!=null){
      if(this.comp(pp1.getData().getDate(), ds1)>=0 && this.comp(pp1.getData().getDate(), de1)<=0){
        l1.add(pp1.getData());
      }
      pp1=pp1.getNext();
    } 
    LinkedList<PurchaseNode> l2 = new LinkedList<PurchaseNode>();
    Node<PurchaseNode> pp2 = pl2.getHead();
    while(pp2!=null){
      if(this.comp(pp2.getData().getDate(), ds2)>=0 && this.comp(pp2.getData().getDate(), de2)<=0){
        l2.add(pp2.getData());
      }
      pp2=pp2.getNext();
    }       
    if(l1.getHead()!=null){
      tot1=tot1/(-1*(l1.getHead().getData().getDate().getYear()-l1.getTail().getData().getDate().getYear())+1); 
    }

    if(l2.getHead()!=null){
      tot2=tot2/(-1*(l2.getHead().getData().getDate().getYear()-l2.getTail().getData().getDate().getYear())+1); 
    }
    if(tot1<tot2){
      return -1;
    }
    else if(tot1>tot2){
      return 1;
    }
    else{
      if(((String) ((ItemNode)in1.getData()).getItemName()).compareTo((String) ((ItemNode)in2.getData()).getItemName())<=0){
        return 1;
      }
      else{
        return -1;  
      }    
    }
  }

  @SuppressWarnings("unchecked")
  public int compare1(T in1, T in2){
    Node<PurchaseNode> pn1 = ((ItemNode) in1).getPurchaseHead();
    DateNode d1;
    if(pn1==null){
      d1 = new DateNode(1, 8, 1970);
    }
    else{
      while(pn1.getNext()!=null){
        pn1=pn1.getNext();
      }      
      d1=pn1.getData().getDate();
    }

    Node<PurchaseNode> pn2 = ((ItemNode) in2).getPurchaseHead();
    DateNode d2;
    if(pn2==null){
      d2 = new DateNode(1, 8, 1970);
    }
    else{
      while(pn2.getNext()!=null){
        pn2=pn2.getNext();
      }      
      d2=pn2.getData().getDate();
    }    

    if(d1.getYear()<d2.getYear()){
      return -1;
    }
    else if(d1.getYear()>d2.getYear()){
      return 1;
    }
    else{
      if(d1.getMonth()<d2.getMonth()){
        return -1;
      }
      else if(d1.getMonth()>d2.getMonth()){
        return 1;
      }     
      else{
        if(d1.getDay()<d2.getDay()){
          return -1;
        }
        else if(d1.getDay()>d2.getDay()){
          return 1;
        } 
        else{
          if(((String) ((ItemNode)in1).getItemName()).compareTo((String) ((ItemNode)in2).getItemName())<=0){
            return 1;
          }
          else{
            return -1;
          }
        }
      } 
    }
  }

  @SuppressWarnings("unchecked")
  public int comp(DateNode date1, DateNode date2){
    if(date1.getYear()<date2.getYear()){
      return -1;
    }
    else if(date1.getYear()>date2.getYear()){
      return 1;
    }
    else{
      if(date1.getMonth()<date2.getMonth()){
        return -1;
      }
      else if(date1.getMonth()>date2.getMonth()){
        return 1;
      }     
      else{
        if(date1.getDay()<date2.getDay()){
          return -1;
        }
        else if(date1.getDay()>date2.getDay()){
          return 1;
        }   
        else{
          return 0;
        }   
      }
    }
  }   

  //DO NOT CALL OR MODIFY THESE METHODS IN YOUR CALL THIS IS FOR USE IN DRIVER CODE
  @SuppressWarnings("unchecked")
  public LinkedList<T> getGlobalList() {
    return this.globalList;
  }

  @SuppressWarnings("unchecked")
  public void clearGlobalList(){
    globalList  = new LinkedList<>();
  }
}
