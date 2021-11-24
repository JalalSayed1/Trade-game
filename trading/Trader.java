package trading;

import trading.Goods;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

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
        trade = new ArrayList<>();
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
        return (trade.stream().collect(Collectors.toList()));
    }

    /**
	 * Adds a new randomly-generated trade to the list.
	 */
    public void addRandomTrade(){
        // get random values for gems, amount and type of goods:
        int gems = rand.nextInt(5) + 1; // random num between 1 and 5 (inclusive)
        int amount = rand.nextInt(5) + 1; // random num between 1 and 5 (inclusive)
        Goods goods = Goods.values()[rand.nextInt(Goods.values().length)];

        this.trade.add(new Trade(gems, amount, goods));
    }
    
}
