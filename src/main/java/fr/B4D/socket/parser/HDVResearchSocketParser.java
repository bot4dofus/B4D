package fr.B4D.socket.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.simple.JSONObject;

import fr.B4D.bot.B4D;
import fr.B4D.bot.B4DException;
import fr.B4D.dofus.Dofus;
import fr.B4D.dofus.items.Item;
import fr.B4D.dofus.items.Resource;
import fr.B4D.socket.DofusSocket;
import fr.B4D.socket.DofusSocketIterator;
import fr.B4D.socket.PatternNotFoundException;
import fr.B4D.socket.SocketElement;
import fr.B4D.socket.SocketUtils;
import fr.B4D.socket.result.HDVItemViewSocketResult;
import fr.B4D.socket.result.HDVResearchSocketResult;
import fr.B4D.socket.store.HDVItemViewSocketStore;
import fr.B4D.socket.store.HDVResearchSocketStore;

public class HDVResearchSocketParser extends SocketParser<HDVResearchSocketResult>{

	//		... | ??? | Numberer of items | Id item 1 | Id Item 2 | ... | Id Item n | 0x02 0xc0 |
	//		    |  4  |         2         |     2     |     2     | ... |     2     |     2     |
	
	public HDVResearchSocketParser() {
		super();
	}

	public HDVResearchSocketResult parse(DofusSocket dofusSocket) {	
		DofusSocketIterator iterator = new DofusSocketIterator(dofusSocket);

		iterator.skip(4);
		Integer numberOfItem = iterator.getNextSocketElement(2).asSmallEndian();

		List<Item> items = new ArrayList<Item>();
		List<Integer> itemIds = iterator.getNextSocketElement(dofusSocket.getPayload().length-8).asBigEndians();

		try {
			JSONObject database = Dofus.getInstance().getDatabase().loadDatabase();
			for(Integer id:itemIds) {
				Item item = Dofus.getInstance().getDatabase().findItemByKey("resources", "id", id.toString(), database);
				
				if(item == null)
					item = Dofus.getInstance().getDatabase().findItemByKey("consumables", "id", id.toString(), database);

				if(item == null)
					item = Dofus.getInstance().getDatabase().findItemByKey("equipments", "id", id.toString(), database);

				if(item == null)
					item = Dofus.getInstance().getDatabase().findItemByKey("weapons", "id", id.toString(), database);

				if(item == null)
					item = Dofus.getInstance().getDatabase().findItemByKey("idols", "id", id.toString(), database);

				if(item == null)
					item = Dofus.getInstance().getDatabase().findItemByKey("ceremonial_items", "id", id.toString(), database);

				if(item == null)
					item = Dofus.getInstance().getDatabase().findItemByKey("sidekicks", "id", id.toString(), database);

				if(item == null)
					item = Dofus.getInstance().getDatabase().findItemByKey("harnesses", "id", id.toString(), database);

				if(item != null)
					items.add(item);
				else
					B4D.logger.warning("Couldn't find any item with id " + id);
			}
			
			HDVResearchSocketResult result = new HDVResearchSocketResult(items);
			HDVResearchSocketStore.getInstance().addSocketResult(result);
			return result;
		} catch (B4DException e) {
			B4D.logger.error(e);
			return null;
		}
	}
}
