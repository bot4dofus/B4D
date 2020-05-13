package fr.B4D.building.hdv;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import fr.B4D.bot.B4D;
import fr.B4D.bot.B4DException;
import fr.B4D.dofus.Dofus;
import fr.B4D.dofus.items.Equipment;
import fr.B4D.dofus.items.Item;
import fr.B4D.program.CancelProgramException;
import fr.B4D.program.StopProgramException;
import fr.B4D.socket.result.HDVResearchSocketResult;
import fr.B4D.socket.store.HDVResearchSocketStore;
import fr.B4D.utils.PointF;

public class HDVEquipments extends HDV{

	public static HDVEquipments BONTA = new HDVEquipments(new Point(-27, -51), new ArrayList<PointF>(), new ArrayList<PointF>(), new PointF(0.1816, 0.3892));
	
	private List<HDVEquipmentCategoryFilter> activeCategoryFilters;
	
	public HDVEquipments(Point position, List<PointF> inPoints, List<PointF> outPoints, PointF tablePosition) {
		super(position, inPoints, outPoints, tablePosition);
		activeCategoryFilters = new ArrayList<HDVEquipmentCategoryFilter>();
	}
	
	public List<Item> enableCategoryFilter(HDVEquipmentCategoryFilter categoryFilter) throws StopProgramException, CancelProgramException, B4DException{
		List<Item> items = new ArrayList<Item>();
		
		if(!activeCategoryFilters.contains(categoryFilter)) {
			activeCategoryFilters.add(categoryFilter);
			B4D.mouse.leftClick(categoryFilter.getFilterPosition(), false);

			HDVResearchSocketResult result = HDVResearchSocketStore.getInstance().waitForResult(5000);
			
			if(result != null) {
				items.addAll(result.getItems());
				
				do {
					result = HDVResearchSocketStore.getInstance().waitForResult(500);
					if(result != null)
						items.addAll(result.getItems());
				}while(result != null);
			}
		}
		
		return items;
	}
	
	public void disableCategoryFilter(HDVEquipmentCategoryFilter categoryFilter) throws StopProgramException, CancelProgramException {
		if(activeCategoryFilters.contains(categoryFilter)) {
			B4D.mouse.leftClick(categoryFilter.getFilterPosition(), false);
		}
	}
}
