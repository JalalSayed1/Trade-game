package test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import trading.Goods;
import trading.Trade;

public class Utils {
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Trade createTrade(int gems, int amount, Goods goods) {
		// If all this fails, just put the correct constructor call here and comment out the rest ...
		try {
			// return new Trade(gems, amount, goods);
		} catch (Exception ex) {
		}

		// First, just try the easy version ...
		Constructor<Trade> constructor = (Constructor<Trade>) Trade.class.getConstructors()[0];
		try {
			return constructor.newInstance(gems, amount, goods);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e1) {
			e1.printStackTrace();
		}

		// Try harder to find the constructor
		constructor = null;
		for (Constructor c : Trade.class.getConstructors()) {
			if (c.getParameterCount() == 3) {
				// Check arguments to see if it can work
				boolean int1 = false, int2 = false, g = false;
				for (Class cls : c.getParameterTypes()) {
					if (cls.equals(int.class) || cls.equals(Integer.class)) {
						if (int1) {
							int2 = true;
						} else {
							int1 = true;
						}
					} else if (cls.equals(Goods.class)) {
						g = true;
					}
				}
				if (int1 && int2 && g) {
					constructor = (Constructor<Trade>)c;
					break;
				}
			}
		}
		if (constructor == null) {
			System.err.println("Unable to find appropriate constructor for Trade; most tests will probably fail");
			return null;
		}
		
		Object[] params = new Object[constructor.getParameterCount()];
		boolean int1Used = false;
		for (int i = 0; i < constructor.getParameterCount(); i++) {
			Class targetClass = constructor.getParameterTypes()[i];
			if (targetClass.equals(int.class) || targetClass.equals(Integer.class)) {
				if (int1Used) {
					params[i] = amount;
				} else {
					params[i] = gems;
					int1Used = true;
				}
			} else if (targetClass.equals(Goods.class)) {
				params[i] = goods;
			}
		}
		
		try {
			return constructor.newInstance(params);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			System.err.println("Couldn't create new Trade object (see below); most tests will probably fail");
			e.printStackTrace();
			return null;
		}
	}
	
	public static Goods getGoods (Trade trade) {
		for (Method m : Trade.class.getMethods()) {
			if (m.getReturnType().equals(Goods.class)) {
				try {
					return (Goods)m.invoke(trade);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					System.err.println("Couldn't get Goods from Trade");
					e.printStackTrace();
					return null;
				}
			}
		}
		System.err.println("No method returning Goods in Trade");
		return null;
	}

}
