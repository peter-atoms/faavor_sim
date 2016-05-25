// From provided lecture example
// University of Minnesota
// Course: CSCI 1933
// Instructor: Chris Dovolis (dovolis@cs.umn.edu)

// Example 27 from lecture
// Queue implementation using a linked list of nodes (see N.java)

public class Q1 implements Q {

    // constructor

    public Q1() {}

    // selectors

    public void add(Object o) {

        if (size == 0) {
          front = new N(o, null);
          rear = front;
        }
        else {
          rear.setNext(new N(o, null));
          rear = rear.getNext();
        }
        size++;
    } // End add

    public Object remove() {
        Object answer;

        if (size == 0) {
            throw new EmptyQueueException("Can't Remove From an Empty Queue");
        } // end if
        
        answer = front.getData();
        front = front.getNext();
        size--;
        if (size == 0)
          rear = null;
        return answer;
    } // End remove

    public int length() {
        return size;
    } // End length

    public boolean isEmpty(){
        return size == 0;
    } // end isEmpty

    public void reverse(){
        // Client must check that queue is not empty
        int tempSize = size;
        N newQ = new N(remove(), null);
        N tempRear = newQ;

        while (!isEmpty()){
            //System.out.println(front());
            newQ = new N(remove(), newQ);
        } // end while
        front = newQ;
        rear = tempRear;
        size = tempSize;
    } // end reverse

    public void replaceFront(Object o){
        // Replace first element
        front.setData(o);
    } // end replace

    public Object front(){
        if (isEmpty()){
            System.out.println("EMPTY QUEUE");
            return null;
        } else {
            return front.getData();
        } // End if-else
    } // End front

    private int size;
    private N front;
    private N rear;

    public static void main(String[] args) {
        Q1 qtPie = new Q1();
        System.out.println("Add to queue....\n");
        qtPie.add("20");
        qtPie.add("PI");
        qtPie.add("0");
        qtPie.add("13/2");
        qtPie.add("-Inf");
        qtPie.add("e");

        System.out.println("Queue size: " + qtPie.length());
        System.out.println("Front of Queue: " + qtPie.front());

        System.out.println("\n\nReverse.");
        qtPie.reverse();
       System.out.println("Front of Queue: " + qtPie.front());
        qtPie.replaceFront("G");
        System.out.println("Replace front of queue: " + qtPie.front() + "\n\n");

        while (!qtPie.isEmpty()){
            System.out.println(qtPie.remove());
        } // end while */

    } // main

}  // Q1 class

