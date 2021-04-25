package fr.B4D.socket.store;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ArrayBlockingQueue;

import fr.B4D.bot.B4D;
import fr.B4D.program.CancelProgramException;
import fr.B4D.program.StopProgramException;
import fr.B4D.socket.event.DofusEvent;

/**
 * The {@code SocketStore} class is an abstract class storing all the dofus events.
 * A store is made of queues and handlers. 
 * 
 * @author Lucas
 * 
 */
public class EventStore {
	
	/**
	 * Default size of the queues in the store.
	 */
	public static final int DEFAULT_QUEUE_SIZE = 100;

	/**
	 * Instance of the store.
	 */
	private static EventStore instance;
	
	/**
	 * Returns the instance of the store.
	 * @return Unique instance of the store.
	 */
	public static EventStore getInstance() {
		if(instance == null)
			instance = new EventStore();
		return instance;
	}
	
	/**
	 * Size of a queue in the store.
	 */
	private int queueSize;
	
	private HashMap<Class<? extends DofusEvent>, ArrayBlockingQueue<DofusEvent>> queueMapper;
	private HashMap<Class<? extends DofusEvent>, ArrayList<EventHandler<DofusEvent>>> handlerMapper;

	/**
	 * Constructor of a socket store with the default value of 100.
	 * This is the same as {@code EventStore(100)}.
	 */
	public EventStore() {
		this(DEFAULT_QUEUE_SIZE);
	}
	
	/**
	 * Constructor of a socket store.
	 * @param queueSize - Size of a queue in the store.
	 */
	public EventStore(int queueSize) {
		if(queueSize < 1)
			throw new IllegalArgumentException(String.format("The size of a queue in the store must be greater than 1. Current : {}", queueSize));
		
		this.queueSize = queueSize; 
		this.queueMapper = new HashMap<Class<? extends DofusEvent>, ArrayBlockingQueue<DofusEvent>>();
		this.handlerMapper = new HashMap<Class<? extends DofusEvent>, ArrayList<EventHandler<DofusEvent>>>();
	}
	
	/**
	 * Returns the size of a queue in the store.
	 * @return Size of a queue.
	 */
	public int getQueueSize() {
		return queueSize;
	}

	/** 
	 * Add socket event to the parsed list. Remove the first if no space left.
	 * @param socketEvent - Socket event to add to the queue.
	 */
	public void addSocketEvent(DofusEvent socketEvent) {
		ArrayBlockingQueue<DofusEvent> eventQueue;
		
		synchronized(queueMapper){
			eventQueue = queueMapper.get(socketEvent.getClass());	//Get the queue corresponding to the event type
			if(eventQueue == null){																	//If the queue doesn't exist yet
				eventQueue = new ArrayBlockingQueue<DofusEvent>(this.queueSize);						//Create it
				queueMapper.put(socketEvent.getClass(), eventQueue);									//Add the queue in the queue mapper
			}
		}
		
		synchronized(eventQueue){
			if(!eventQueue.offer(socketEvent)) { 												//If cannot add the event to the queue
				eventQueue.poll();																	//Remove the latest
				eventQueue.offer(socketEvent);														//Add the new one
			}
			eventQueue.notifyAll();																//Notify the main thread that a event has been added to the store
		}
	}

	/**
	 * Waits for an event with no timeout.
	 * This is the same as{@code waitForMessage(0)}.
	 * @param eventClass - Type of the event to wait for.
	 * @return The oldest event corresponding to the type.
	 * @throws StopProgramException if the program is stopped.
	 * @throws CancelProgramException if the program is canceled.
	 */
	public <T extends DofusEvent> T waitForEvent(Class<T> eventClass) throws StopProgramException, CancelProgramException {
		return waitForEvent(eventClass, 0);
	}
	
	/**
	 * Waits for an event with timeout.
	 * @param eventClass - Type of the event to wait for.
	 * @param timeout - Timeout in ms.
	 * @return The oldest event corresponding to the type, {@code null} if timeout.
	 * @throws StopProgramException if the program is stopped.
	 * @throws CancelProgramException if the program is canceled.
	 */
	public <T extends DofusEvent> T waitForEvent(Class<T> eventClass, long timeout) throws StopProgramException, CancelProgramException {		
		DofusEvent socketEvent = null;
		ArrayBlockingQueue<DofusEvent> eventQueue;
		
		synchronized(queueMapper){
			 eventQueue = queueMapper.get(eventClass);		//Get the queue corresponding to the event type
			if(eventQueue == null){															//If the queue doesn't exist yet
				eventQueue = new ArrayBlockingQueue<DofusEvent>(this.queueSize);				//Create it
				queueMapper.put(eventClass, eventQueue);										//Add the queue in the queue mapper
			}
		}

		synchronized(eventQueue){
			socketEvent = eventQueue.poll();											//Get the latest event on the queue
			if(socketEvent == null) {													//If no event on the queue
				B4D.wait.waitOnObject(eventQueue, timeout);									//Wait for an event on the queue
				socketEvent = eventQueue.poll();											//Get the latest event on the queue
			}
		}
		
		synchronized(queueMapper){
			if(eventQueue.isEmpty())														//If the queue is now empty
				queueMapper.remove(eventClass);													//Remove it from the store
		}

		return eventClass.cast(socketEvent);												//Return the event
	}

	/** 
	 * Clear all the events in the store.
	 */
	public void clear() {
		synchronized(queueMapper){
			queueMapper.clear();
		}
	}

	/** 
	 * Clear all the events in a queue.
	 * Don't do anything if the queue doesn't exist.
	 * @param eventClass - Type of the event to remove.
	 */
	public <T extends DofusEvent> void clear(Class<T> eventClass) {
		synchronized(queueMapper){
			ArrayBlockingQueue<DofusEvent> eventQueue = queueMapper.get(eventClass);		//Get the queue corresponding to the event class
			if(eventQueue != null){															//If the queue exists
				eventQueue.clear();																//Clear it
			}
		}
	}
	
	/**
	 * Add an event handler to the list. The handler will be called every time the event occurs.
	 * @param eventClass - Type of the event handler.
	 * @param eventHandler - Event handler to add.
	 */
	@SuppressWarnings("unchecked")
	public synchronized <T extends DofusEvent> void addEventHandler(Class<T> eventClass, EventHandler<T> eventHandler) {		
		synchronized(handlerMapper){
			ArrayList<EventHandler<DofusEvent>> eventHandlers = handlerMapper.get(eventClass);		//Get the handlers corresponding to the event type
			if(eventHandlers == null){																//If the handlers doesn't exist yet
				eventHandlers = new ArrayList<EventHandler<DofusEvent>>();								//Create it
				handlerMapper.put(eventClass, eventHandlers);											//Add the handlers in the handler mapper
			}
			
			synchronized(eventHandlers){
				//TODO find a way to remove the cast !
				//Because of it, removing handlers fail in fr.B4D.socket.store.EventStoreTest.java:L214
				eventHandlers.add((EventHandler<DofusEvent>) eventHandler); 						//Add the handler to the list
			}
		}
	}
	
	/**
	 * Removes the event handler from the list. The handler will not be called anymore.
	 * @param eventClass - Type of the event handler.
	 * @param eventHandler - Event handler to remove.
	 * @return {@code true} if it has been removed, {@code false} otherwise.
	 */
	public synchronized <T extends DofusEvent> boolean removeEventHandler(Class<T> eventClass, EventHandler<T> eventHandler) {
		boolean removed = false;		
		synchronized(handlerMapper){
			ArrayList<EventHandler<DofusEvent>> eventHandlers = handlerMapper.get(eventClass);		//Get the handlers corresponding to the event type
			if(eventHandlers != null){																//If the handlers exists
				removed = eventHandlers.remove(eventHandler);											//Remove it
			}
		}
		return removed;
	}

	/**
	 * Process an infinite number of events with no timeout.
	 * This is the same as {@code read(eventClass, -1, -1)}.
	 * @param eventClass - Type of the event to wait for.
	 * @return Number of event read.
	 * @throws StopProgramException if the program is stopped.
	 * @throws CancelProgramException if the program is canceled.
	 */
	public <T extends DofusEvent> long read(Class<T> eventClass) throws StopProgramException, CancelProgramException {
		return read(eventClass, -1, 0);
	}

	/**
	 * Process a finite number of events with no timeout.
	 * This is the same as {@code read(eventClass, countTo, 0)}.
	 * @param eventClass - Type of the event to wait for.
	 * @param countTo - Number of events to process.
	 * @return Number of event read.
	 * @throws StopProgramException if the program is stopped.
	 * @throws CancelProgramException if the program is canceled.
	 */
	public <T extends DofusEvent> long read(Class<T> eventClass, int countTo) throws StopProgramException, CancelProgramException {
		return read(eventClass, countTo, 0);
	}
	
	/**
	 * Process a finite number of events.
	 * @param eventClass - Type of the event to wait for.
	 * @param countTo - Number of events to process.
	 * @param timeout - Timeout in ms, {@code 0} for no timeout.
	 * @return Number of event read.
	 * @throws StopProgramException if the program is stopped.
	 * @throws CancelProgramException if the program is canceled.
	 */
	public <T extends DofusEvent> long read(Class<T> eventClass, int countTo, long timeout) throws StopProgramException, CancelProgramException {		
		int count = 0;
		T socketEvent ;
		while(count != countTo) {																	//While not finished
			socketEvent = waitForEvent(eventClass, timeout);											//Wait for the next event
			if(socketEvent == null)																		//If timeout
				break;																						//Exit loop
			synchronized(handlerMapper){
				ArrayList<EventHandler<DofusEvent>> eventHandlers = handlerMapper.get(eventClass);		//Get the handlers corresponding to the event type
				if(eventHandlers != null){																//If the handlers exist
					for(EventHandler<DofusEvent> eventHandler:eventHandlers) {								//For every registered handlers
						if(!eventHandler.onEventReceived(socketEvent))											//Call the handler, if must stop receiving events
							break;																					//Exit loop
					}
				}
			}
			count++;
		}
		return count;
	}
}
