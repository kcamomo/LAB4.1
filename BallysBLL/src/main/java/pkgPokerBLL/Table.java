package pkgPokerBLL;

import java.io.Serializable;
import java.util.UUID;
import java.util.HashMap;

public class Table implements Serializable {

	private UUID TableID;
	private HashMap<UUID, Player> TablePlayers = new HashMap<UUID, Player>();

	public Table() {
		super();
		TableID = UUID.randomUUID();
	}

	public UUID getTableID() {
		return TableID;
	}

	public void setTableID(UUID tableID) {
		TableID = tableID;
	}

	public Table AddPlayerToTable(UUID pid, Player p) {
		TablePlayers.put(pid, p);
		return this;
	}

	public Table RemovePlayerFromTable(UUID pid) {
		TablePlayers.remove(pid);
		return this;
	}
}
