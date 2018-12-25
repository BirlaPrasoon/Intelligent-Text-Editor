package spelling;

public class Queue<E> {

	private class node{
        private E data;
        private node next;
        private node prev;

        private node(E data){
            this.data = data;
            this.next = null;
            this.prev = null;
        }

    }

    private node front;
    private node rear;
    private int size;
    
    Queue(){
        front = rear = null;
    }

    public void enqueue(E data){
        node newNode = new node(data);
        size++;
        if(front == null) {
            rear = front = newNode;
            return;
        }
        else if(rear.equals(front)) {
            newNode.next = front;
            front.prev = newNode;
            rear = newNode;
            return;
        }

        newNode.next = rear;
        rear.prev = newNode;
        rear = newNode;
    }

    public int size(){
        return size;
    }

    public E dequeue(){

        if(front == null)
            return null;
        E frontData = front.data;

        if(front == rear)
            front = rear = null;
        else{
            front = front.prev;
            front.next = null;
        }

        size--;
        return frontData;
    }

    public boolean isEmpty(){
        return front == null;
    }

    private void printRearTOFront(){
        System.out.print("Rear: ");
        node current = rear;
        while(current != null){
            System.out.print("-> " + current.data);
            current = current.next;
        }
        System.out.println();
    }

    private void printFrontToRear(){
        System.out.print("Front: ");
        node current = front;
        while(current != null){
            System.out.print("-> " + current.data);
            current = current.prev;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Queue<Integer> q = new Queue<>();
        System.out.println("enqueuing: 19, 2, 10, 13");
        q.enqueue(19);
        q.enqueue(2);
        q.enqueue(10);
        q.enqueue(13);
        System.out.println("Current size: " + q.size());
        q.printFrontToRear();
        q.printRearTOFront();
        System.out.println("isEmpty: " + q.isEmpty());
        System.out.println("dequeing: " + q.dequeue() + " " + q.dequeue() + " " + q.dequeue());
        System.out.println("Current size: " + q.size());
        q.printFrontToRear();
        q.printRearTOFront();
        System.out.println("isEmpty: " + q.isEmpty());
        System.out.println("dequeing: " + q.dequeue());
        System.out.println("isEmpty: " + q.isEmpty());
        System.out.println("dequeing: " + q.dequeue());
        System.out.println("isEmpty: " + q.isEmpty());
    }
	
}
