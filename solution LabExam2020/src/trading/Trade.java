package trading;

import java.util.Objects;

/**
 * Represents a single trade in the trading game: an amount of gems, and the amount of
 * goods that will be given in response.
 *
 * Model solution to JP2 lab exam 2020.
 * 
 * @author Mary Ellen Foster <MaryEllen.Foster@glasgow.ac.uk>
 */
public class Trade {
	
	/** The amount of gems that the goods cost */
	private int gems;
	/** How much goods you get in return */
	private int amount;
	/** The type of goods that is given */
	private Goods goods;

	/**
	 * Creates a new Trade representing the given exchange.
	 * 
	 * @param gems The cost in gems
	 * @param goods The type of goods
	 * @param amount The amount of goods
	 */
	public Trade(int gems, Goods goods, int amount) {
		this.gems = gems;
		this.amount = amount;
		this.goods = goods;
	}

	/**
	 * Tries to execute the given trade between the given trader and the given citizen. The trade
	 * is only executed if (a) the trade is supported by the trader, and (b) the citizen has enough
	 * gems to pay for it. If the trade is not supported, an exception is thrown; if the citizen
	 * does not have enough money, no change is made.
	 * 
	 * If the trade is successful, a new, randomly-generated trade will be added to the trader.
	 * 
	 * @param trader The trader
	 * @param citizen The citizen
	 * @throws If the trade is not supported by the trader
	 */
	public void execute(Trader trader, Citizen citizen) {
		// Check if it's valid first
		if (!trader.getTrades().contains(this)) {
			throw new IllegalArgumentException("This trade does not belong to the trader");
		}
		if (citizen.executeTrade(this)) {
			// Okay, it worked, update trader
			trader.addRandomTrade();
		}
	}

	/**
	 * @return the gems
	 */
	public int getGems() {
		return gems;
	}

	/**
	 * @return the amount
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * @return the goods
	 */
	public Goods getGoods() {
		return goods;
	}

	@Override
	public int hashCode() {
		return Objects.hash(amount, gems, goods);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Trade)) {
			return false;
		}
		Trade other = (Trade) obj;
		return amount == other.amount && gems == other.gems && goods == other.goods;
	}

	@Override
	/**
	 * Note: note the auto-generated default!
	 */
	public String toString() {
		return amount + " " + goods + " for " + gems + " gems";
	}
	
}
