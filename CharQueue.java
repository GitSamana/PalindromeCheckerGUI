/**
 * CharQueue.java 
 *
 * This class implements the Queue Abstract Data Type 
 * by modifying the implementation of a SinglyLinkedList 
 * to adhere to the Queue interface.  Nodes containing 
 * Characters are added at rear of the list and removed at 
 * front of the list.
 */

class CharQueue {
    
    // instance variables are pointers to front and rear
    // of queue which are of inner class type Node.
    private Node front,rear;
    
    // Create a new, empty queue 
    public CharQueue(){
        front = rear = null;
    }
    
     /**
     * Instance method enqueue
     * Put Character object at rear of queue
     */
    public void enqueue(Character obj) {
        // creates a new Node to put on front of queue
        Node node = new Node(obj,null);
        if(this.empty()){ // queue empty 
            front = node;
        }
        else { // reset rear pointer if queue is not empty
            rear.next = node;
        }
        rear = node;
    }
    
     /**
     * Instance method dequeue
     * return value of front and remove it from queue.
     * 
     * Throws an object of type Exception if queue
     * is empty.
     */
    public Character dequeue() throws RuntimeException {
        Character holder;
        if (this.empty()) {
            throw new RuntimeException("Queue is empty");
        }      
        holder = front.element;
        front = front.next;
        return holder;
    }
    
    /**
     * Instance method empty
     * Uses status of front pointer to determine when stack is
     * empty
     */
    public boolean empty() {
        if (front == null)
            return true;
        return false;
    }
    
    // inner class - all private members directly accessible 
    // to CharQueue class 
    private class Node {
        private Node next;
        private Character element;
        
        // no-parameter constructor calls 2-parameter constructor
        Node() {
            this(null,null);
        }
        // 2-parameter constructor
        Node(Character obj, Node link) {
            element = obj;
            next = link;
        }
    }  
}  // end of class CharQueue
