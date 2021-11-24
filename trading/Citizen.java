package trading;

import java.util.HashMap;
import java.util.Map;

import trading.Goods;

/**
 * Represents a citizen in the trading game, who can trade with a trader. A citizen starts
 * with an initial amount of gems and an empty goods inventory. Each time they do a trade, 
 * the corresponding amount of gems is removed, and the corresponding amount of goods
 * is added to the inventory.
 * 
 * @author Jalal Sayed - 22/11/2021
 */
public class Citizen {

    // How many gems this citizen has:
    private int gems;
    // How much goods this citizen has:
    private Map<Goods, Integer> inventory;

    /**
     * Creates a new Citizen with the given amount of gems and an empty inventory.
     * 
     * @param gems The initial amount of gems
     */
    public Citizen(int gems) {
        this.gems = gems;
    }
    
    public int getGems(){
        return this.gems;
    }

    public int getAmount(Goods goods){
        return inventory.getOrDefault(goods, 0);
    }

    /**
     * Tries to execute the trade, as long as this citizen has enough gems to do it. If we have enough gems,
	 * the corresponding amount of gems are removed and the goods added to the inventory. 
     * If we do not, nothing is changed.
     * 
     * @param trade The trade to execute
     * @return Whether the trade was executed successfully or not
     */
    public boolean executeTrade(Trade trade){
        if(trade.getGems() > this.gems){
            return false;
        }else{
            // update the amount of gems
            this.gems -= trade.getGems();
            // if this goods is not present in the map, add a new entry: goods => 0:
            if(!inventory.containsKey(trade.getGoods())){
                inventory.put(trade.getGoods(), 0);

            }
            // otherwise, add the new amount to the value of that key:
            inventory.put(trade.getGoods(), inventory.get(trade.getGoods())+trade.getAmount());
            
            return true;
        }
    }

}
