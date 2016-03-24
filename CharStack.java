/**
 * CharStack.java 
 *
 * This class implements the Stack Abstract Data Type 
 * by modifying the implementation of a SinglyLinkedList 
 * to adhere to the Stack interface.  Nodes containing 
 * Characters are added and removed at the top of the list.
 * 
 * Note: This class throw an Exception if the stack is 
 * empty when a pop is attempted. Classes using this class
 * should add a throws Exception clause on the main method.
 */
class CharStack {
    
    // instance variable is a pointer to top of
    // stack which is of inner class type Node.
    private Node top;
    
    
    // 0-parameter constructor initializes top
    CharStack(){
        top = null;
    }
    
    /**
     * Instance method push
     * Put Character object at top of stack
     */
    void push(Character obj) {
        Node newNode = new Node(obj,top);
        top = newNode;
        //size++;
    }
    
    /**
     * Instance method pop
     * return value of top and remove it from stack.
     * Throws an object of type Exception if stack
     * is empty.
     */
    Character pop() throws RuntimeException{
        Character holder;
        if (top == null) {
            throw new RuntimeException("Stack is empty");
        }      
        holder = top.element;
        top = top.next;
        return holder;
    }
    
    /**
     * Instance method empty
     * Uses status of top to determine when stack is
     * empty
     */
    public boolean empty() {
        if (top == null)
            return true;
        return false;
    }
    
    // inner class - all private members directly accessible 
    // to CharStack class 
    private class Node {
        private Node next;
        private Character element;
        
        // no-parameter constructor calls 2-parameter constructor
        Node() {
            this(null,null);
        }
        
        // 2-parameter constructor initializes element and next
        Node(Character obj, Node link) {
            element = obj;
            next = link;
        }
    } // end of inner class Node
}  // end of class ListStack
