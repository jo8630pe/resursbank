package queue_singlelinkedlist;
import java.util.*;

public class FifoQueue<E> extends AbstractQueue<E> implements Queue<E> {
	private QueueNode<E> last;
	private int size;

	public FifoQueue() {
		super();
		last = null;
		size = 0;
	}

	/**	
	 * Inserts the specified element into this queue, if possible
	 * post:	The specified element is added to the rear of this queue
	 * @param	e the element to insert
	 * @return	true if it was possible to add the element 
	 * 			to this queue, else false
	 */
	public boolean offer(E e) {
		if (size > 0) {
			QueueNode<E> temp = new QueueNode<E>(e);	//noden temp ref. till elementet e
			temp.next = last.next;						//temp ref. till första noden
			last.next = temp;							//sista noden ref. till temp
			last = temp;								//temp blir sista nod
			size++;
		}
		else {
			last = new QueueNode<E>(e);					//last blir den den enda nod i kön, denna nod ref. till elementet e
			last.next = last;							//last ref. till sig själv
			size++;
		}
		return true;
	}
	
	/**	
	 * Returns the number of elements in this queue
	 * @return the number of elements in this queue
	 */
	public int size() {		
		return size;
	}
	
	/**	
	 * Retrieves, but does not remove, the head of this queue, 
	 * returning null if this queue is empty
	 * @return 	the head element of this queue, or null 
	 * 			if this queue is empty
	 */
	public E peek() {
		if(size > 0) {
			return last.next.element;
		}
		return null;
	}

	/**	
	 * Retrieves and removes the head of this queue, 
	 * or null if this queue is empty.
	 * post:	the head of the queue is removed if it was not empty
	 * @return 	the head of this queue, or null if the queue is empty 
	 */
	public E poll() {
		if (size > 0) {
			E temp = last.next.element;
			last.next = last.next.next;		//sista noden ref. till andra noden i kön
			size--;
			return temp;
		}	
		return null;
	}
	
	/**	
	 * Returns an iterator over the elements in this queue
	 * @return an iterator over the elements in this queue
	 */	
	public Iterator<E> iterator () {
		return new QueueIterator();
	}
	
	/**
	* Appends the specified queue to this queue
	* post: all elements from the specified queue are appended
	* to this queue. The specified queue (q) is empty after the call.
	* @param q the queue to append
	* @throws IllegalArgumentException if this queue and q are identical
	*/
	public void append(FifoQueue<E> q) {
		if(this == q) {
			throw new IllegalArgumentException();
		}
		else if (this.size() == 0) {		//om denna kön är tom
			this.last = q.last;				//denna kön = q
			this.size = q.size;
			//q = null;						//tolkningsfråga?
			q.size = 0;						//tömmer q
			return;
		}
		else if (q.size() == 0) {			//om q är tom, gör inget
			return;
		}

		QueueNode<E> refToThisFirst = this.last.next;	//ref. till första noden i denna kön
		this.last.next = q.last.next;					//sista noden i denna kön ref. till första noden i q
		q.last.next = refToThisFirst;					//sista noden i q ref till första noden i denna kön
		this.last = q.last;								//denna last är nu q.last
		this.size += q.size;							//uppdaterar storleken
		//q = null;										//tolkningsfråga?
		q.size = 0;										//tömmer q
	}
	
	private static class QueueNode<E> {
		E element;
		QueueNode<E> next;

		private QueueNode(E x) {
			element = x;
			next = null;
		}
	}


	private class QueueIterator implements Iterator<E> {
		private QueueNode<E> pos;
	
		/* Konstruktor */
		private QueueIterator() {
			pos = last;					//medför att vi har en ref. till första noden
		}
		
		public boolean hasNext() {
			return (pos != null);		//om kön är tom eller om vi itererat genom kön är pos = null och det finns inga fler element
		}
	
		public E next() {
			
			if (pos == null) {
				throw new NoSuchElementException();
			}
			
			E temp = pos.next.element;	//första elementet sparas i temp
			
			pos = pos.next;				//sista noden blir första noden (vi pekar 
	        
			if(pos == last) {			//då har vi gått itererat genom kön
				pos = null;
			}
			
			return temp;
		}
	}
}


