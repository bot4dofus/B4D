package fr.B4D.socket.store;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.B4D.bot.B4D;
import fr.B4D.socket.event.ChangeMapEvent;
import fr.B4D.socket.event.PlayerEnterMapEvent;

@SuppressWarnings("javadoc")
public class EventStoreTest {
	
	EventStore store;

	@BeforeClass
	public static void initClass() throws Exception {
		new B4D();
	}
	
	@Before
	public void initTest() {
		store = new EventStore();
	}
	
	@Test
	public void test1() throws Exception {
		/* Create the objects */
		PlayerEnterMapEvent event1 = new PlayerEnterMapEvent("Player 1");
		PlayerEnterMapEvent event2 = new PlayerEnterMapEvent("Player 2");
		ChangeMapEvent event3 = new ChangeMapEvent();
		
		/* Add the events to the store */
		store.addSocketEvent(event1);
		store.addSocketEvent(event2);
		store.addSocketEvent(event3);
		
		/* Check waitForEvent */
		Assert.assertEquals(event1, store.waitForEvent(PlayerEnterMapEvent.class));
		Assert.assertEquals(event3, store.waitForEvent(ChangeMapEvent.class));
		Assert.assertEquals(event2, store.waitForEvent(PlayerEnterMapEvent.class));
		Assert.assertEquals(null, store.waitForEvent(ChangeMapEvent.class, 100));
		Assert.assertEquals(null, store.waitForEvent(PlayerEnterMapEvent.class, 100));

		/* Add the events to the store and clear a singe type of event */
		store.addSocketEvent(event1);
		store.addSocketEvent(event2);
		store.addSocketEvent(event3);
		store.clear(ChangeMapEvent.class);
		
		/* Check that the store is half-cleared */
		Assert.assertEquals(event1, store.waitForEvent(PlayerEnterMapEvent.class));
		Assert.assertEquals(null, store.waitForEvent(ChangeMapEvent.class, 100));
		
		/* Clear the entire store */
		store.clear();
		
		/* Check the store is empty */
		Assert.assertEquals(null, store.waitForEvent(PlayerEnterMapEvent.class, 100));
	}
	
	private int event1counter = 0;
	private int event2counter = 0;
	private int event3counter = 0;
	
	@Test
	public void test2() throws Exception {
		/* Create the objects */
		PlayerEnterMapEvent event1 = new PlayerEnterMapEvent("Player 1");
		PlayerEnterMapEvent event2 = new PlayerEnterMapEvent("Player 2");
		ChangeMapEvent event3 = new ChangeMapEvent();
		
		EventHandler<PlayerEnterMapEvent> eventHandler1 = new EventHandler<PlayerEnterMapEvent>() {
			@Override
			public boolean onEventReceived(PlayerEnterMapEvent socketEvent) {
				event1counter++;
				return true;
			}
		};
		EventHandler<PlayerEnterMapEvent> eventHandler2 = new EventHandler<PlayerEnterMapEvent>() {
			@Override
			public boolean onEventReceived(PlayerEnterMapEvent socketEvent) {
				event2counter++;
				return true;
			}
		};
		EventHandler<ChangeMapEvent> eventHandler3 = new EventHandler<ChangeMapEvent>() {
			@Override
			public boolean onEventReceived(ChangeMapEvent socketEvent) {
				event3counter++;
				return true;
			}
		};
		
		/* Add the handlers */
		store.addEventHandler(PlayerEnterMapEvent.class, eventHandler1);
		store.addEventHandler(PlayerEnterMapEvent.class, eventHandler2);
		store.addEventHandler(ChangeMapEvent.class, eventHandler3);
		
		/* Create and start a thread that will add events */
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(100);
					store.addSocketEvent(event1);
					store.addSocketEvent(event2);
					store.addSocketEvent(event3);
				} catch (InterruptedException e) {
					//Do nothing
				}
			}
		});
		t1.start();
		store.read(PlayerEnterMapEvent.class, 2);
		store.read(ChangeMapEvent.class, 1);
		
		/* Check the number of times the handlers have been called */
		Assert.assertEquals(2, event1counter);
		Assert.assertEquals(2, event2counter);
		Assert.assertEquals(1, event3counter);
		Assert.assertEquals(0, store.read(PlayerEnterMapEvent.class, 1, 100));
		Assert.assertEquals(0, store.read(ChangeMapEvent.class, 1, 100));
		
		/* Remove the event handler 1 and 2 */
		Assert.assertEquals(true, store.removeEventHandler(PlayerEnterMapEvent.class, eventHandler1));
		Assert.assertEquals(true, store.removeEventHandler(PlayerEnterMapEvent.class, eventHandler2));

		/* Create and start a thread that will add events */
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(100);
					store.addSocketEvent(event1);
					store.addSocketEvent(event2);
					store.addSocketEvent(event3);
				} catch (InterruptedException e) {
					//Do nothing
				}
			}
		});
		t2.start();
		store.read(PlayerEnterMapEvent.class, 2);
		store.read(ChangeMapEvent.class, 1);
		
		/* Check the number of times the handlers have been called */
		Assert.assertEquals(2, event1counter);
		Assert.assertEquals(2, event2counter);
		Assert.assertEquals(2, event3counter);
		Assert.assertEquals(0, store.read(PlayerEnterMapEvent.class, 1, 100));
		Assert.assertEquals(0, store.read(ChangeMapEvent.class, 1, 100));
	}
}
