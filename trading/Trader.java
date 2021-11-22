

import trading.Goods;
import java.util.Random;

/**
 * Represents a trader in a trading game, who trades goods for gems. A trader
 * starts with a single, randomly-generated trade and can add more trades to their
 * list on request.
 * 
 * @author Jalal Sayed - 22/11/2021
 */
public class Trader {

    // The list of trades:
    List<Trade> trade;
    // Used to generate new random trades to add:
    Random rand = new Random();

    /**
     * Creates a new trader, with a single randomly-generated trade.
     */
    public Trader() {
        // new trader gets a random trade in their list:
        addRandomTrade();
    }

    /**
	 * Returns all of the trades in the list.
	 * 
	 * @return The list of trades
	 */
    public List<Trade> getTrades(){
        // return immutable list:
        return (trades.stream().collect(Collectors.toList()));
    }

    /**
	 * Adds a new randomly-generated trade to the list.
	 */
    public addRandomTrade(){
        // get random values for gems, amount and type of goods:
        int gems = rand.nextInt(6); // random num between 0 and 5 (inclusive)
        int amount = rand.nextInt(6); // random num between 0 and 5 (inclusive)
        Goods goods = Goods.values()[rand.nextInt(Goods.values().length)];

        this.trade.add(new Trade(gems, amount, goods));
    }
    
}
