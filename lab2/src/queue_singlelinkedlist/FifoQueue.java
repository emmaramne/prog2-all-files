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
		QueueNode<E> newNode = new QueueNode<E>(e);
		if (last == null) {
			last = newNode;
			last.next = last;
			size++;
			return true;
		}
		newNode.next = last.next;
		last.next = newNode;
		last = newNode;
		size++;
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
		if (last == null) {
			return null;
		}
		return last.next.element;
	}

	/**	
	 * Retrieves and removes the head of this queue, 
	 * or null if this queue is empty.
	 * post:	the head of the queue is removed if it was not empty
	 * @return 	the head of this queue, or null if the queue is empty 
	 */
	public E poll() {
		if (last == null) {
			return null;
		}
		if (size == 1) {
			QueueNode temp = last;
			last = null;
			size--;
			return (E) temp.element;
		}
		QueueNode temp = last.next;
		last.next = last.next.next;
		size--;
		return (E) temp.element;
	}
	
	/**	
	 * Returns an iterator over the elements in this queue
	 * @return an iterator over the elements in this queue
	 */	
	public Iterator<E> iterator() {
		return new QueueIterator();
	}
	
	
	public void append(FifoQueue<E> q) {
		if (q == this) {
			throw new IllegalArgumentException();
		}
		if (q.size == 0) {
			
		} else if (size == 0) {
			size = q.size;
			last = q.last;
			q.size = 0;
			q.last = null;
		} else {
			QueueNode<E> temp = q.last.next;
			q.last.next = last.next;
			last.next = temp;
			last = q.last;
			q.last = null;
			size += q.size;
			q.size = 0;
		}
		
		
		
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
		private int i;
		
		private QueueIterator() {
			if (size == 0) {
				pos = null;
			} else {
				pos = last.next;
			}
			i = 0;
		}

		@Override
		public boolean hasNext() {
			if (i >= size) {
				return false;
			}
			return true;
		}

		@Override
		public E next() {
			if (! hasNext()) {
				throw new NoSuchElementException();
			} else {
				QueueNode<E> temp = pos;
				pos = pos.next;
				i++;
				return temp.element;
			}
			
		}
		
	}

}
