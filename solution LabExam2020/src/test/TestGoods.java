package test;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import trading.Goods;

public class TestGoods {
	@Test
	public void goodsIsEnum() {
		Assertions.assertTrue(Goods.class.isEnum(), "Goods is not an enum");
	}
	
	@Test
	public void goodsValuesCorrect() {
		String[] expected = { "BREAD", "COAL", "FISH", "HELMET", "IRON", "PAPER", "SHIELD", "SWORD", "WOOD", "WOOL" };
		String[] actual = Arrays.stream(Goods.values()).map(s -> s.toString()).collect(Collectors.toList()).toArray(new String[0]);
		Assertions.assertArrayEquals(expected, actual, "Goods constants not correct");
	}
	
	public void goodsPackageCorrect() {
		Assertions.assertEquals("trading", Goods.class.getPackageName(), "Goods package incorrect");
	}
}
