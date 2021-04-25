package fr.B4D.building.hdv;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import fr.B4D.bot.B4D;
import fr.B4D.bot.B4DException;
import fr.B4D.dofus.items.Item;
import fr.B4D.program.CancelProgramException;
import fr.B4D.program.StopProgramException;
import fr.B4D.socket.event.HDVFilterResultEvent;
import fr.B4D.socket.store.EventStore;
import fr.B4D.utils.PointF;

/**
 * The {@code HDVEquipments} represents an equipment HDV. This class extends {@code HDV}.
 * 
 * @author Lucas
 *
 */
public class HDVEquipments extends HDV{

	/**
	 * Equipment HDV of Bonta.
	 */
	public static HDVEquipments BONTA = new HDVEquipments(new Point(-27, -51), null, null, new PointF(0.1816, 0.3892));
	
	/**
	 * List of active Filters.
	 */
	private List<HDVEquipmentCategoryFilter> activeCategoryFilters;
	
	/**
	 * Constructs an {@code HDVEquipments}.
	 * @param position - Location of the HDV on the map.
	 * @param inPoints - List of points to get in the HDV.
	 * @param outPoints - List of points to get out the HDV.
	 * @param tablePosition - Position of the table in relative coordinates.
	 */
	public HDVEquipments(Point position, List<PointF> inPoints, List<PointF> outPoints, PointF tablePosition) {
		super(position, inPoints, outPoints, tablePosition);
		activeCategoryFilters = new ArrayList<HDVEquipmentCategoryFilter>();
	}
	
	/**
	 * Enables a category filter.
	 * @param categoryFilter - Category filter to enable.
	 * @return List of items matching the filter.
	 * @throws StopProgramException If the program has been stopped.
	 * @throws CancelProgramException If the program has been canceled.
	 * @throws B4DException if an unexpected error occurred.
	 */
	public List<Item> enableCategoryFilter(HDVEquipmentCategoryFilter categoryFilter) throws StopProgramException, CancelProgramException, B4DException{
		List<Item> items = new ArrayList<Item>();
		
		if(!activeCategoryFilters.contains(categoryFilter)) {
			activeCategoryFilters.add(categoryFilter);
			B4D.mouse.leftClick(categoryFilter.getFilterPosition(), false);

			HDVFilterResultEvent result = EventStore.getInstance().waitForEvent(HDVFilterResultEvent.class, 5000);
			
			if(result != null) {
				items.addAll(result.getItems());
				
				do {
					result = EventStore.getInstance().waitForEvent(HDVFilterResultEvent.class, 500);
					if(result != null)
						items.addAll(result.getItems());
				}while(result != null);
			}
		}
		
		return items;
	}
	
	/**
	 * Disables a category filter.
	 * @param categoryFilter - Category filter to disable.
	 * @throws StopProgramException If the program has been stopped.
	 * @throws CancelProgramException If the program has been canceled.
	 * @throws B4DException if an unexpected error occurred.
	 */
	public void disableCategoryFilter(HDVEquipmentCategoryFilter categoryFilter) throws StopProgramException, CancelProgramException, B4DException{
		if(activeCategoryFilters.contains(categoryFilter)) {
			B4D.mouse.leftClick(categoryFilter.getFilterPosition(), false);
		}
	}
}
