package col106.assignment5;

public class ItemNode implements ItemInterface{

	int itemId;
	String itemName;
	int stock;
	LinkedList<PurchaseNode> purchaseTransactions;
	

	public ItemNode(int itemId, String itemName, int stock){
		this.itemId = itemId;
		this.itemName = itemName;
		this.stock = stock;
		this.purchaseTransactions = new LinkedList<PurchaseNode>();
	}

	@SuppressWarnings("unchecked")
	public int getItemId(){
		//Enter your code here
		return this.itemId;
	}

	@SuppressWarnings("unchecked")
	public  String getItemName(){
		//Enter your code here
		return this.itemName;
	}

	@SuppressWarnings("unchecked")
	public int getStockLeft(){
		//Enter your code here
		return this.stock;
	}

	@SuppressWarnings("unchecked")
	public void addTransaction(PurchaseNode purchaseT){
		//Enter your code here
		this.stock-=purchaseT.numItemsPurchased();
		this.purchaseTransactions.add(purchaseT);
		this.purchaseTransactions.getTail().setq(5);
		LLMergeSort objz = new LLMergeSort();
		LinkedList<PurchaseNode> lst = this.purchaseTransactions;
		this.purchaseTransactions = objz.MergeSort(lst);
	}

	@SuppressWarnings("unchecked")
	public Node<PurchaseNode> getPurchaseHead(){
		//Enter your code here
		return this.purchaseTransactions.getHead();
	}

	@SuppressWarnings("unchecked")
	public LinkedList<PurchaseNode> getPurchaseList(){
		//Enter your code here
		return this.purchaseTransactions;
	}	

}
