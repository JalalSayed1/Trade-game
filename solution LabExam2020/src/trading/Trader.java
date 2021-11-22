package trading;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Represents a trader in a trading game, who trades goods for gems. A trader
 * starts with a single, randomly-generated trade and can add more trades to their
 * list on request.
 * 
 * Model solution to JP2 lab exam 2020.
 * 
 * @author Mary Ellen Foster <MaryEllen.Foster@glasgow.ac.uk>
 */
public class Trader {
	/** The list of trades */
	private List<Trade> trades;
	/** Used to generate new random trades to add */
	private Random rand;
	
	/**
	 * Creates a new trader, with a single randomly-generated trade.
	 */
	public Trader() {
		trades = new ArrayList<>();
		rand = new Random();
		addRandomTrade();
	}
	
	/**
	 * Adds a new randomly-generated trade to the list.
	 */
	public void addRandomTrade() {
		int gems = rand.nextInt(5) + 1;
		int amount = rand.nextInt(5) + 1;
		Goods goods = Goods.values()[rand.nextInt(Goods.values().length)];
		trades.add(new Trade(gems, goods, amount));
	}
	
	/**
	 * Returns all of the trades in the list.
	 * 
	 * @return The list of trades
	 */
	public List<Trade> getTrades() {
		// Immutable -- not required by spec
		return trades.stream().collect(Collectors.toList());
	}

}
