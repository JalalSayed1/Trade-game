package trading;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a citizen in the trading game, who can trade with a trader. A citizen starts
 * with an initial amount of gems and an empty goods inventory. Each time they do a trade, 
 * the corresponding amount of gems is removed, and the corresponding amount of goods
 * is added to the inventory.
 * 
 * Model solution to JP2 lab exam 2020.
 * 
 * @author Mary Ellen Foster <MaryEllen.Foster@glasgow.ac.uk>
 */
public class Citizen {
	/** How many gems this citizen has */
	private int gems;
	/** How much goods this citizen has */
	private Map<Goods, Integer> inventory;
	
	/**
	 * Creates a new Citizen with the given amount of gems and an empty inventory.
	 * 
	 * @param gems The initial amount of gems
	 */
	public Citizen(int gems) {
		this.gems = gems;
		this.inventory = new HashMap<>();
		// Doing this makes other methods much neater
		for (Goods g : Goods.values()) {
			inventory.put(g, 0);
		}
	}
	
	/**
	 * Tries to execute the trade, as long as this citizen has enough gems to do it. If we have enough gems,
	 * the corresponding amount of gems are removed and the goods added to the inventory. If we do not,
	 * nothing is changed.
	 * 
	 * @param trade The trade to execute
	 * @return Whether the trade was executed successfully
	 */
	public boolean executeTrade (Trade trade) {
		if (gems < trade.getGems()) {
			return false;
		}
		this.gems -= trade.getGems();
		inventory.put(trade.getGoods(), inventory.get(trade.getGoods()) + trade.getAmount());
		return true;
	}

	/**
	 * Returns the current amount of gems.
	 * 
	 * @return The gems
	 */
	public int getGems() {
		return this.gems;
	}
	
	/**
	 * Returns how much we have of the given goods.
	 * 
	 * @param goods The goods to check
	 * @return How much we have -- should return 0 if we have none of it
	 */
	public int getAmount(Goods goods) {
		return inventory.get(goods);
	}
	
}