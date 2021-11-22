package test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import trading.Trade;
import trading.Trader;

public class TestTrader {
	
	@Test
	public void traderIsPublicAndNotAbstract() {
		Assertions.assertTrue(Modifier.isPublic(Trader.class.getModifiers()), "Trader is not public");
		Assertions.assertFalse(Modifier.isAbstract(Trader.class.getModifiers()), "Trader is abstract");
	}
	
	@Test
	public void traderFieldsAllPrivate() {
		for (Field f : Trader.class.getDeclaredFields()) {
			if (!Modifier.isStatic(f.getModifiers())) {
				Assertions.assertTrue(Modifier.isPrivate(f.getModifiers()), "Trader field " + f + " not private");
			}
		}
	}
	
	@SuppressWarnings("rawtypes")
	@Test
	public void traderConstructorCorrect() {
		Constructor[] constructors = Trader.class.getDeclaredConstructors();
		Assertions.assertEquals(1, constructors.length, "Trader has wrong number of constructors");
		
		Constructor c = constructors[0];
		Assertions.assertTrue(Modifier.isPublic(c.getModifiers()), "Trader constructor is not public");
		Assertions.assertArrayEquals(new Class[0], c.getParameterTypes(), "Trader constructor has wrong signature");
	}
	
	@Test
	public void traderHasExpectedPublicMethods() {
		// Core public methods that need to be there
		List<String> expected = Arrays.asList("addRandomTrade", "getTrades");
		List<String> actual = new ArrayList<>();
		for (Method m : Trader.class.getDeclaredMethods()) {
			if (m.getName().equals("main") || Modifier.isPrivate(m.getModifiers())) {
				continue;
			}
			actual.add(m.getName());
		}
		Collections.sort(expected);
		Collections.sort(actual);
		Assertions.assertEquals(expected, actual, "Trader does not have expected public methods");
	}
	
	@Test
	public void newlyCreatedTraderHasOneTrade() {
		Trader trader = new Trader();
		Assertions.assertEquals(1, trader.getTrades().size(), "Newly created trader should have exactly one trade");
	}
	
	@Test
	public void addRandomTradeAddsCorrectly() {
		Trader trader = new Trader();
		for (int i = 0; i < 10; i++) {
			trader.addRandomTrade();
			Assertions.assertEquals(i + 2, trader.getTrades().size(), "addRandomTrade does not add trade correctly");
		}
		for (Trade trade : trader.getTrades()) {
			Assertions.assertAll("Random trade value out of range",
					() -> Assertions.assertTrue(trade.getGems() > 0 && trade.getGems() <= 5, "Gems out of range: " + trade.getGems()),
					() -> Assertions.assertTrue(trade.getAmount() > 0 && trade.getAmount() <= 5, "Amount out of range: " + trade.getGems()),
					() -> Assertions.assertNotNull(Utils.getGoods(trade), "Goods is null")
			);
		}
	}

}
