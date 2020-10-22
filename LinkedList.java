package col106.assignment5;

public class LinkedList<T> {

  private Node<T> head;
  private Node<T> tail;

  private int size;

  public LinkedList() {
    head = null;
    tail = null;
    size = 0;
  }

  @SuppressWarnings("unchecked")
  public Node<T> getHead(){
  	return this.head;
  }

  @SuppressWarnings("unchecked")
  public Node<T> getTail(){
    return this.tail;
  }

  @SuppressWarnings("unchecked")
  public void add(T data) {
    Node<T> node = new Node(data);
    node.setNext(null);

    if(head==null){
		  head = node;
		  head.setNext(null);
      tail = head;
  	}
  	else{
      tail.setNext(node);
      tail = node;
  	}

    size++;
  }

  @SuppressWarnings("unchecked")
  public int getSize() {
    return size;
  }

  @SuppressWarnings("unchecked")
  public String toString() {
    Node<T> current = head;
    String elements = "";
    while (current != null) {
      elements += "[" + current.getData().toString() + "]";
      current = current.getNext();
    }
    return elements;
  }

}
