package col106.assignment5;

public class Node<T> {

  Node<T> next;
  T data;
  Integer qnum;
  DateNode date1;
  DateNode date2;

  public Node(T data) {
    next = null;
    this.data = data;
    qnum = 0;
    date1=null;
    date2=null;
  }

  @SuppressWarnings("unchecked")
  public T getData() {
    return data;
  }

  @SuppressWarnings("unchecked")
  public void setData(T dataValue) {
    data = dataValue;
  }

  @SuppressWarnings("unchecked")
  public Node<T> getNext() {
    return next;
  }

  @SuppressWarnings("unchecked")
  public void setNext(Node<T> nextValue) {
    next = nextValue;
  }

  @SuppressWarnings("unchecked")
  public Node<T> duplicate() {
    return null;
  }

  @SuppressWarnings("unchecked")
  public String toString() {
    String nodeItem = data.toString();
    return nodeItem;
  }

  @SuppressWarnings("unchecked")
  public int getq(){
    return qnum;
  }

  @SuppressWarnings("unchecked")
  public void setq(int inp){
    qnum = inp;
  }

  @SuppressWarnings("unchecked")
  public DateNode getdate1(){
    return date1;
  }

  @SuppressWarnings("unchecked")
  public DateNode getdate2(){
    return date2;
  }  

  @SuppressWarnings("unchecked")
  public void setdate1(DateNode date){
    date1 = date;
  }

  @SuppressWarnings("unchecked")
  public void setdate2(DateNode date){
    date2 = date;
  }      
}
