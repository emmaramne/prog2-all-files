package testqueue;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import queue_singlelinkedlist.FifoQueue;

class TestAppendFifoQueue {
	private FifoQueue<Integer> q1;
	private FifoQueue<Integer> q2;

	@BeforeEach
	void setUp() throws Exception {
		q1 = new FifoQueue<Integer>();
		q2 = new FifoQueue<Integer>();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testTwoEmptyQueues() {
		q1.append(q2);
		assertEquals(0, q1.size(), "Wrong size of empty queue");
		assertEquals(0, q2.size(), "Wrong size of empty queue");
		assertNull(q1.peek(), "Peek not null");
	}
	
	@Test
	void testEmptyAppendNonEmpty() {
		for (int i = 1; i <= 5; i++) {
			q2.offer(i);
		}
		q1.append(q2);
		assertEquals(5, q1.size(), "Wrong size of q1");
		assertEquals(0, q2.size(), "Wrong size of q2");
		for (int i = 1; i <= q1.size(); i++) {
			assertEquals(i, q1.poll(), "Wrong value");
		}
	}
	
	@Test
	void testNonEmptyAppendEmpty() {
		for (int i = 1; i <= 5; i++) {
			q1.offer(i);
		}
		q1.append(q2);
		assertEquals(5, q1.size(), "Wrong size of q1");
		assertEquals(0, q2.size(), "Wrong size of q2");
		for (int i = 1; i <= q1.size(); i++) {
			assertEquals(i, q1.poll(), "Wrong value");
		}
	}
	
	@Test
	void testTwoNonEmptyQueues() {
		for (int i = 1; i <= 5; i++) {
			q1.offer(i);
		}
		for (int i = 6; i <= 10; i++) {
			q2.offer(i);
		}
		q1.append(q2);
		assertEquals(10, q1.size(), "Wrong size of q1");
		assertEquals(0, q2.size(), "Wrong size of q2");
		for (int i = 1; i <= q1.size(); i++) {
			assertEquals(i, q1.poll(), "Wrong value");
		}
	}
	
	@Test
	void testQueueAppendItself() {
		for (int i = 1; i <= 5; i++) {
			q1.offer(i);
		}
		assertThrows(IllegalArgumentException.class, () -> q1.append(q1));
	}

}
