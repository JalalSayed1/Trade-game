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

import trading.Citizen;
import trading.Goods;
import trading.Trade;

public class TestCitizen {
	
	@Test
	public void citizenIsPublicAndNotAbstract() {
		Assertions.assertTrue(Modifier.isPublic(Citizen.class.getModifiers()), "Citizen is not public");
		Assertions.assertFalse(Modifier.isAbstract(Citizen.class.getModifiers()), "Citizen is abstract");
	}
	
	@Test
	public void citizenFieldsAllPrivate() {
		for (Field f : Citizen.class.getDeclaredFields()) {
			Assertions.assertTrue(Modifier.isPrivate(f.getModifiers()), "Citizen field " + f + " not private");
		}
	}
	
	@SuppressWarnings("rawtypes")
	@Test
	public void citizenConstructorCorrect() {
		Constructor[] constructors = Citizen.class.getDeclaredConstructors();
		Assertions.assertEquals(1, constructors.length, "Citizen has wrong number of constructors");
		
		Constructor c = constructors[0];
		Assertions.assertTrue(Modifier.isPublic(c.getModifiers()), "Citizen constructor is not public");
		Assertions.assertArrayEquals(new Class[] { int.class }, c.getParameterTypes(), "Citizen constructor has wrong signature");
	}
	
	@Test
	public void citizenHasExpectedPublicMethods() {
		// Core public methods that need to be there
		List<String> expected = Arrays.asList("getGems", "getAmount", "executeTrade");
		List<String> actual = new ArrayList<>();
		for (Method m : Citizen.class.getDeclaredMethods()) {
			if (m.getName().equals("main") || Modifier.isPrivate(m.getModifiers())) {
				continue;
			}
			actual.add(m.getName());
		}
		Collections.sort(expected);
		Collections.sort(actual);
		Assertions.assertEquals(expected, actual, "Citizen does not have expected public methods");
	}
	
	@Test
	public void citizenConstructorSetsGemsField() {
		Assertions.assertEquals(100, new Citizen(100).getGems(), "Citizen.getGems() does not return correct result on newly created Citizen");
	}

	@Test
	public void citizenGetAmountWorksOnNewCitizen() {
		Citizen c = new Citizen(10);
		for (Goods g : Goods.values()) {
			Assertions.assertEquals(0, c.getAmount(g), "Citizen.getAmount() does not return zero on newly created Citizen");
		}
	}
		
	@Test
	public void citizenExecuteTradeSuccessfulTrades() {
		int expectedGems = 100;
		Citizen c = new Citizen(expectedGems);
		for (Goods g : Goods.values()) {
			Trade t = Utils.createTrade(1, 2, g);
			Assertions.assertTrue(c.executeTrade(t), "Citizen.executeTrade() does not return true on successful trade");
			expectedGems--;
			Assertions.assertEquals(expectedGems, c.getGems(), "Gems not correct after successful trade");
			int expectedGoods = 2;
			Assertions.assertEquals(expectedGoods, c.getAmount(g), "Amount of goods not correct after successful trade");

			// Do it all again
			t = Utils.createTrade(3, 3, g);
			Assertions.assertTrue(c.executeTrade(t), "Citizen.executeTrade() does not return true on successful trade");
			expectedGems -= 3;
			Assertions.assertEquals(expectedGems, c.getGems(), "Gems not correct after successful trade");
			expectedGoods += 3;
			Assertions.assertEquals(expectedGoods, c.getAmount(g), "Amount of goods not correct after successful trade");
		}
	}
	
	@Test
	public void citizenExecuteTradeUnsuccessfulTrades() {
		int expectedGems = 1;
		Citizen c = new Citizen(expectedGems);
		for (Goods g : Goods.values()) {
			Trade t = Utils.createTrade(2, 2, g);
			Assertions.assertFalse(c.executeTrade(t), "Citizen.executeTrade() does not return false on successful trade");
			Assertions.assertEquals(expectedGems, c.getGems(), "Gems not correct after unsuccessful trade");
			int expectedGoods = 0;
			Assertions.assertEquals(expectedGoods, c.getAmount(g), "Amount of goods not correct after unsuccessful trade");
		}
	}
	
}
