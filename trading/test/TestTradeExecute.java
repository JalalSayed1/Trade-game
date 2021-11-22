package test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import trading.Citizen;
import trading.Goods;
import trading.Trade;
import trading.Trader;

public class TestTradeExecute {
	
	@Test
	public void invalidTradeThrowsException() {
		Trade trade = Utils.createTrade(1, 1, Goods.values()[0]);
		Citizen citizen = new Citizen(100);
		Trader trader = new Trader();
		
		// This should throw an exception
		Assertions.assertThrows(IllegalArgumentException.class, 
				() -> { trade.execute(trader, citizen); },
				"Invalid trade should throw an exception");
	}
	
	@Test
	public void tradeWithoutEnoughMoneyDoesNothing() {
		Citizen citizen = new Citizen(1);
		// Keep going until we get a trader with an expensive initial trade
		Trader trader = new Trader();
		int count = 0;
		while (trader.getTrades().get(0).getGems() < 2) {
			if (count++ > 100) {
				Assertions.fail("Can't get a trader with a trade that costs > 1 gem, probably an issue elsewhere in the code");
			}
			trader = new Trader();
		}
		
		// Make sure they've got at least one trade
		trader.addRandomTrade();
		Trade trade = trader.getTrades().get(0);
		trade.execute(trader, citizen);
		
		Trader realTrader = trader;
		
		Assertions.assertAll("Un-executed trade should not change anything",
				() -> Assertions.assertEquals(1, citizen.getGems(), "Citizen's gems should not change"),
				() -> Assertions.assertEquals(0, citizen.getAmount(Utils.getGoods(trade)), "Citizen's inventory should not change"),
				() -> Assertions.assertEquals(2, realTrader.getTrades().size(), "Trader should not get a new trade")
		);
	}
	
	@Test
	public void successfulTradeHasSideEffects() {
		Citizen citizen = new Citizen(10);
		Trader trader = new Trader();
		// Just make sure they have at least one trade so the rest works
		trader.addRandomTrade();
		Trade trade = trader.getTrades().get(0);
		trade.execute(trader, citizen);

		Assertions.assertAll("Successful trade should change trader and citizen",
				() -> Assertions.assertEquals(10 - trade.getGems(), citizen.getGems(), "Citizen's gems should change"),
				() -> Assertions.assertEquals(trade.getAmount(), citizen.getAmount(Utils.getGoods(trade)), "Citizen's inventory should change"),
				() -> Assertions.assertEquals(3, trader.getTrades().size(), "Trader should get a new trade")
		);
	}

}
