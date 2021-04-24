package fr.B4D.socket.parser;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;

import fr.B4D.bot.B4D;
import fr.B4D.bot.B4DException;
import fr.B4D.dofus.Dofus;
import fr.B4D.dofus.items.Item;
import fr.B4D.socket.DofusSocket;
import fr.B4D.socket.DofusSocketIterator;
import fr.B4D.socket.event.HDVFilterResultEvent;

/**
 * The {@code HDVResearchSocketParser} class is used to parse a socket relative to an HDV research.
 * 
 * @author Lucas
 *
 */
public class HDVResearchSocketParser extends SocketParser<HDVFilterResultEvent>{

	//		... | ??? | Numberer of items | Id item 1 | Id Item 2 | ... | Id Item n | 0x02 0xc0 |
	//		    |  4  |         2         |     2     |     2     | ... |     2     |     2     |

	@Override
	public HDVFilterResultEvent parse(DofusSocket dofusSocket) throws B4DException {	
		DofusSocketIterator iterator = new DofusSocketIterator(dofusSocket);

		iterator.skip(4);
		Integer numberOfItem = iterator.getNextSocketElement(2).asSmallEndian();

		List<Item> items = new ArrayList<Item>();
		List<Integer> itemIds = iterator.getNextSocketElement(dofusSocket.getPayload().length-8).asBigEndians();

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

		HDVFilterResultEvent result = new HDVFilterResultEvent(items);
		return result;
	}
}
