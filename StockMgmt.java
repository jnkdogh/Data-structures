package col106.assignment5;

public class StockMgmt implements StockMgmtInterface {
	//!!!!!!!*********DON'T CREATE YOUR OWN OBJECTS OF LLMergeSort*********!!!!!!!
	//use these only this object to sort
	LLMergeSort mergeSortobj;

	LinkedList<CategoryNode> categories;

	//DO NOT MNODIFY THIS CONSTRUCTOR
	public StockMgmt() {

		mergeSortobj = new LLMergeSort();
		categories = new LinkedList<CategoryNode>();

		categories.add(new CategoryNode(1, "mobile"));
		categories.add(new CategoryNode(2, "utensils"));
		categories.add(new CategoryNode(3, "sanitary"));
		categories.add(new CategoryNode(4, "medicalEquipment"));
		categories.add(new CategoryNode(5, "clothes"));

	}

	@SuppressWarnings("unchecked")
	public void addItem(int categoryId, int itemId, String itemName, int stock) {
		//Your code goes here
		Node<CategoryNode> categoryLN = categories.getHead();
		while(categoryLN.getData().getCategoryId() != categoryId){
			categoryLN=categoryLN.getNext();
		} 
		categoryLN.getData().getLinkedListOfCategory().add(new ItemNode(itemId, itemName, stock));
	}

	@SuppressWarnings("unchecked")
	public void addItemTransaction(int categoryId, int itemId, String itemName, int numItemPurchased, int day, int month, int year) {
		//Your code goes here
		Node<CategoryNode> categoryLN = categories.getHead();
		while(categoryLN.getData().getCategoryId() != categoryId){
			categoryLN=categoryLN.getNext();
		} 
		Node<ItemNode> itemLN = categoryLN.getData().getLinkedListOfCategory().getHead();
		while(itemLN.getData().getItemId() != itemId){
			itemLN=itemLN.getNext();
		} 		
		itemLN.getData().addTransaction(new PurchaseNode(numItemPurchased, day, month, year));
	}

	//Query 1
	@SuppressWarnings("unchecked")
	public LinkedList<ItemNode> sortByLastDate() {
		//Your code goes here
		LinkedList<ItemNode> ans = new LinkedList<ItemNode>();
		Node<CategoryNode> cat = categories.getHead();
		for (int i=0; i<5; i++) {
			LinkedList<ItemNode> items = cat.getData().getLinkedListOfCategory();
			Node<ItemNode> item = items.getHead();
			while(item!=null){
				ans.add(item.getData());
				ans.getTail().setq(1);
				item=item.getNext();
			}
			cat=cat.getNext();
		}
		LinkedList<ItemNode> ans1 = mergeSortobj.MergeSort(ans); 
		return ans1;
	}

	//Query 2
	@SuppressWarnings("unchecked")
	public LinkedList<ItemNode> sortByPurchasePeriod(int day1, int month1, int year1, int day2, int month2, int year2) {
		//Your code goes here
		LinkedList<ItemNode> ans = new LinkedList<ItemNode>();
		Node<CategoryNode> cat = categories.getHead();
		for (int i=0; i<5; i++) {
			LinkedList<ItemNode> items = cat.getData().getLinkedListOfCategory();
			Node<ItemNode> item = items.getHead();
			while(item!=null){
				ans.add(item.getData());
				ans.getTail().setq(2);
				item=item.getNext();
			}
			cat=cat.getNext();
		}		
		Node<ItemNode> itemN = ans.getHead();
		LinkedList<ItemNode> ans1 = new LinkedList<ItemNode>();
		while(itemN!=null){
			DateNode d1 = new DateNode(day1, month1, year1);
			DateNode d2 = new DateNode(day2, month2, year2);	
			DateNode id1;		
			DateNode id2;		
			if(itemN.getData().getPurchaseHead()==null){
				DateNode d = new DateNode(1, 8, 1970);
				id1 = d;
				id2 = d;				
			}
			else{
				id1 = itemN.getData().getPurchaseHead().getData().getDate();	
				id2 = itemN.getData().getPurchaseList().getTail().getData().getDate();	
			}		
			DateNode dz = new DateNode(0, 0, 0);
			if(this.comp(id1, d2)<=0 && this.comp(d1, id1)<=0  && this.comp(d2, id2)<=0){
				ans1.add(itemN.getData());
				ans1.getTail().setq(2);
				ans1.getTail().setdate1(id1);
				ans1.getTail().setdate2(d2);
				itemN=itemN.getNext();
			}
			else if(this.comp(id1, d1)>=0 && this.comp(d2, id2)>=0){				
				ans1.add(itemN.getData());
				ans1.getTail().setq(2);
				ans1.getTail().setdate1(id1);
				ans1.getTail().setdate2(id2);
				itemN=itemN.getNext();				
			}
			else if(this.comp(id1, d1)<=0 && this.comp(d1, id2)<=0 && this.comp(id1, d2)<=0 && this.comp(d2, id2)<=0){				
				ans1.add(itemN.getData());
				ans1.getTail().setq(2);
				ans1.getTail().setdate1(d1);
				ans1.getTail().setdate2(d2);
				itemN=itemN.getNext();				
			}
			else if(this.comp(id1, d1)<=0 && this.comp(d1, id2)<=0 && this.comp(id2, d2)<=0){				
				ans1.add(itemN.getData());
				ans1.getTail().setq(2);
				ans1.getTail().setdate1(d1);
				ans1.getTail().setdate2(id2);
				itemN=itemN.getNext();				
			}
			else{		
				ans1.add(itemN.getData());
				ans1.getTail().setq(2);
				ans1.getTail().setdate1(dz);
				ans1.getTail().setdate2(dz);				
				itemN=itemN.getNext();				
			}		
		}
		LinkedList<ItemNode> ans2 = mergeSortobj.MergeSort(ans1);
		return ans2;
	}

	//Query 3
	@SuppressWarnings("unchecked")
	public LinkedList<ItemNode> sortByStockForCategory(int category) {
		//Your code goes here
		LinkedList<ItemNode> ans = new LinkedList<ItemNode>();
		Node<CategoryNode> cat = categories.getHead();
		for (int i=0; i<5; i++) {
			if(cat.getData().getCategoryId()==category){
				LinkedList<ItemNode> items = cat.getData().getLinkedListOfCategory();
				Node<ItemNode> item = items.getHead();
				while(item!=null){
					ans.add(item.getData());
					ans.getTail().setq(3);
					item=item.getNext();
				}

			}
			cat=cat.getNext();
		}		
		LinkedList<ItemNode> ans1 = mergeSortobj.MergeSort(ans);

		return ans1;
	}

	//Query 4
	@SuppressWarnings("unchecked")
	public LinkedList<ItemNode> filterByCategorySortByDate(int category) {
		//Your code goes here
		LinkedList<ItemNode> ans = new LinkedList<ItemNode>();
		Node<CategoryNode> cat = categories.getHead();
		for (int i=0; i<5; i++) {
			if(cat.getData().getCategoryId()==category){
				LinkedList<ItemNode> items = cat.getData().getLinkedListOfCategory();
				Node<ItemNode> item = items.getHead();
				while(item!=null){
					ans.add(item.getData());
					ans.getTail().setq(4);
					item=item.getNext();
				}

			}
			cat=cat.getNext();
		}
		LinkedList<ItemNode> ans1 = mergeSortobj.MergeSort(ans);
		return ans1;
	}

	//!!!!!*****DO NOT MODIFY THIS METHOD*****!!!!!//
	@SuppressWarnings("unchecked")
	public LinkedList<ItemNode> checkMergeSort() {
		LinkedList<ItemNode> retLst = mergeSortobj.getGlobalList();
		mergeSortobj.clearGlobalList();
		return retLst;
	}

	@SuppressWarnings("unchecked")
	public int comp(DateNode date1, DateNode date2){
		// System.out.println("Comparable dates");
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
}
