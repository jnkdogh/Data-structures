package col106.assignment5;

public class CategoryNode implements CategoryInterface
{

	int categoryId;
	String categoryName;
	LinkedList<ItemNode> itemList;

	//DO NOT REMOVE THIS CONSTRUCTOR
	public CategoryNode(int categoryId, String categoryName){
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.itemList = new LinkedList<ItemNode>();
	}

	@SuppressWarnings("unchecked")
	public int getCategoryId(){
		return this.categoryId;
	}

	@SuppressWarnings("unchecked")
	public LinkedList<ItemNode> getLinkedListOfCategory(){
		return itemList;
	}

	@SuppressWarnings("unchecked")
	public String getCategoryName(){
		return this.categoryName;
	}

}
