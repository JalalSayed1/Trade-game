package trading; //! keep getting (The declared package "trading" does not match the expected package "") error

/**
 * Represents a single trade in the trading game: an amount of gems, and the amount of
 * goods that will be given in response.
 * 
 * @author Jalal Sayed - 22/11/2021
 */
public class Trade {
    // The amount of gems that the goods cost:
    private int gems;
    // How much goods you get in return:
    private int amount;
    // The type of goods that is given:
    private Goods goods;

    /**
     * Creates a new Trade representing the given exchange.
	 * 
	 * @param gems The cost in gems
	 * @param goods The type of goods
	 * @param amount The amount of goods
     */
    public Trade(int gems, int amount, Goods goods) {
        this.gems = gems;
        this.amount = amount;
        this.goods = goods;
    }

    /**
     * Tries to execute the given trade between the given trader and the given citizen. 
     * The trade is only executed if:
     * (a) the trade is supported by the trader.
     * (b) the citizen has enough gems to pay for it.
     * If the trade is not supported, an exception is thrown; if the citizen
	 * does not have enough money, no change is made.
	 * 
	 * If the trade is successful, a new, randomly-generated trade will be added to the trader.
	 * 
	 * @param trader The trader
	 * @param citizen The citizen
	 * @throws If the trade is not supported by the trader
     */
    public void execute(Trader trader, Citizen citizen){
        // Check if it's valid first:
        if (!trader.getTrades().contains(this)) {
            //! what is the difference between throw exception and throw new exception?
			throw new IllegalArgumentException("This trade does not belong to the trader");
        }

        if(citizen.executeTrade(this)){
            trader.addRandomTrade();
        }
    }

    public int getGems() {
        return this.gems;
    }

    public int getAmount() {
        return this.amount;
    }

    public Goods getGoods() {
        return this.goods;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Trade)) {
            return false;
        }
        Trade other = (Trade) o;
        return gems == other.gems && amount == other.amount && Objects.equals(goods, other.goods);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gems, amount, goods);
    }


    @Override
    public String toString() {
        return (amount + " " + goods + " for " + gems + " gems");
    }

}
