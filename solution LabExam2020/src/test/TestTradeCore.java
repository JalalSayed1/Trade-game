package test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import trading.Goods;
import trading.Trade;

public class TestTradeCore {

	@Test
	public void tradeIsPublicAndNotAbstract() {
		Assertions.assertTrue(Modifier.isPublic(Trade.class.getModifiers()), "Trade is not public");
		Assertions.assertFalse(Modifier.isAbstract(Trade.class.getModifiers()), "Trade is abstract");
	}
	
	@Test
	public void tradeFieldsAllPrivate() {
		for (Field f : Trade.class.getDeclaredFields()) {
			Assertions.assertTrue(Modifier.isPrivate(f.getModifiers()), "Trade field " + f + " not private");
		}
	}
	
	@SuppressWarnings("rawtypes")
	@Test
	public void tradeConstructorCorrect() {
		Constructor[] constructors = Trade.class.getDeclaredConstructors();
		Assertions.assertEquals(1, constructors.length, "Trade has wrong number of constructors");
		
		Constructor c = constructors[0];
		Assertions.assertTrue(Modifier.isPublic(c.getModifiers()), "Trade constructor is not public");
		Assertions.assertEquals(3, c.getParameterCount(), "Trade constructor has wrong number of arguments");
		boolean int1Found = false, int2Found = false, goodsFound = false;
		for (Class cls : c.getParameterTypes()) {
			if (cls.equals(int.class) || cls.equals(Integer.class)) {
				if (int1Found) {
					int2Found = true;
				} else {
					int1Found = true;
				}
			} else if (cls.equals(Goods.class)) {
				goodsFound = true;
			} else {
				Assertions.fail("Trade constructor has wrong parameters: " + Arrays.toString(c.getParameterTypes()));
			}
		}
		if (!(int1Found && int2Found && goodsFound)) {
				Assertions.fail("Trade constructor has wrong parameters: " + Arrays.toString(c.getParameterTypes()));
		}
	}
	
	@Test
	public void tradeHasExpectedMethods() {
		// Core public methods that need to be there
		List<String> expected = Arrays.asList("getGems", "getAmount", "getGoods", "equals", "hashCode", "toString");
		List<String> actual = new ArrayList<>();
		for (Method m : Trade.class.getDeclaredMethods()) {
			if (m.getName().equals("execute") || m.getName().equals("main") || Modifier.isPrivate(m.getModifiers())) {
				continue;
			}
			actual.add(m.getName());
		}
		Collections.sort(expected);
		Collections.sort(actual);
		Assertions.assertEquals(expected, actual, "Trade does not have expected public methods");
	}
	
	
	@Test
	public void tradeEqualsHashCodeCorrect() {
		Random rand = new Random();
		int gems1 = rand.nextInt(5) + 1;
		int gems2 = rand.nextInt(5) + 1;
		int amount1 = rand.nextInt(5) + 1;
		int amount2 = rand.nextInt(5) + 1;
		Goods g1 = Goods.values()[rand.nextInt(Goods.values().length)];
		Goods g2 = Goods.values()[rand.nextInt(Goods.values().length)];
		
		Trade t1a = Utils.createTrade(gems1, amount1, g1);
		Trade t1b = Utils.createTrade(gems1, amount1, g1);
		Trade t2 = Utils.createTrade(gems2, amount2, g2);
		
		Assertions.assertTrue(t1a.equals(t1b), "Trade.equals() does not work on equivalent trades");
		Assertions.assertFalse(t1a.equals(t2), "Trade.equals() does not work on different trades");

		Assertions.assertEquals(t1a.hashCode(), t1b.hashCode(), "Trade.hashCode() does not work on equivalent trades");
		Assertions.assertNotEquals(t1a.hashCode(), t2.hashCode(), "Trade.hashCode() does not work on different trades");
	}
	
	@Test
	public void tradeToStringCorrect() {
		int gems1 = 2;
		int amount1 = 3;
		Goods g1 = Goods.values()[0];
		Trade t1 = Utils.createTrade(gems1, amount1, g1);
		String s = t1.toString();
		
		Assertions.assertFalse(s.contains("Trade ["), "Trade.toString() appears to be auto-generated");
		Assertions.assertTrue(s.contains(g1.toString()), "Trade.toString() does not contain goods");
		Assertions.assertTrue(s.contains(String.valueOf(gems1)), "Trade.toString() does not contain gems");
		Assertions.assertTrue(s.contains(String.valueOf(amount1)), "Trade.toString() does not contain amount");
	}
}
