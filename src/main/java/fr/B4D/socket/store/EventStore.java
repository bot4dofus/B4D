package fr.B4D.socket.store;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

import fr.B4D.bot.B4D;
import fr.B4D.program.CancelProgramException;
import fr.B4D.program.StopProgramException;

/**
 * The {@code SocketStore} class is an abstract class storing all the socket results.
 * @author Lucas
 *
 * @param <T> Type of the socket result.
 */
public class EventStore<T> {
	
	private ArrayBlockingQueue<T> socketResults;
	private List<EventHandler<T>> eventHandlers;

	/**
	 * Constructor of a socket store.
	 * @param capacity Number of results, the store is able to save.
	 */
	public EventStore(int capacity) {
		socketResults = new ArrayBlockingQueue<T>(capacity);
		eventHandlers = new ArrayList<EventHandler<T>>();
	}
	
	/** 
	 * Add socket result to the parsed list. Remove the first if no space left.
	 * @param socketResult - Socket result to add to the queue.
	 */
	public void addSocketResult(T socketResult) {
		synchronized(socketResults){
			if(!socketResults.offer(socketResult)) {
				socketResults.poll();
				socketResults.offer(socketResult);
			}
			socketResults.notifyAll();
			
			for(EventHandler<T> eventHandler:eventHandlers)
				eventHandler.onEventReceived(socketResult);
		}
	}

	/**
	 * Attend un message pour une durée infinie. Cela est identique à {@code waitForMessage(0)}.
	 * @return Plus vieux message correspondant au filtre.
	 * @throws StopProgramException Si le programme est stoppé.
	 * @throws CancelProgramException Si le bot programme est annulé.
	 */
	public T waitForResult() throws StopProgramException, CancelProgramException {
		return waitForResult(0);
	}
	
	/**
	 * Attend un message pour une durée finie.
	 * @param timeout - Durée d'attente maximale en millisecondes.
	 * @return Plus vieux message correspondant au filtre et {@code null} si timeout.
	 * @throws StopProgramException Si le programme est stoppé.
	 * @throws CancelProgramException Si le bot programme est annulé.
	 */
	public T waitForResult(long timeout) throws StopProgramException, CancelProgramException {		
		T socketResult = null;

		socketResult = socketResults.poll();
		if(socketResult == null) {
			synchronized(socketResults){
				B4D.wait.waitOnObject(socketResults, timeout);
				socketResult = socketResults.poll();
			}
		}

		return socketResult;
	}

	/** 
	 * Efface les messages contenues dans la queue.
	 */
	public void clear() {
		synchronized(socketResults){
			socketResults.clear();
		}
	}
	
	/**
	 * Add an event handler to the list. The handler will be called every time the event occurs.
	 * @param eventHandler - Event handler to add.
	 */
	public synchronized void addEventHandeler(EventHandler<T> eventHandler) {
		eventHandlers.add(eventHandler);
	}
	
	/**
	 * Removes the event handler from the list. The handler will not be called anymore.
	 * @param eventHandler - Event handler to remove.
	 * @return {@code true} if it has been removed, {@code false} otherwise.
	 */
	public synchronized boolean removeEventHandler(EventHandler<T> eventHandler) {
		return eventHandlers.remove(eventHandler);
	}
}
