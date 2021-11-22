# About the game:

A world involving Citizens and Traders, where Citizens are able to exchange gems for Goods including bread, wool, armour, weapons, and building materials. All of those goods are provided by Traders in exchange for gems. Every Trader supports one or more Trades, where a Trade includes the following information:
- The price in gems
- The specific goods that are for sale
- How much of that goods is available for the price

An example of a Trade:
- Trade 1 gem for 3 Bread

Every Citizen has an amount of gems along with an amount of Goods of each type (which may be zero). When a Citizen successfully executes a Trade, the following happens:
- The relevant amount of gems is removed
- The relevant amount of Goods is added

For example, if a Citizen initially has 5 gems and an empty inventory, after executing the above trade, they would have 4 gems and 3 Bread in their inventory. If a Citizen does not have enough gems to complete a trade, they cannot execute it.

Trades are provided by Traders. When a new Trader is created, they only have one Trade available. Each time a Trader makes a trade with a Citizen, a new (randomly-chosen) Trade is added to their set. So if a Trader starts with the sample Trade above, after three successful trades with Citizens, the Trader might have the following Trades in their list:

Trade 1 gem for 3 Bread
- Trade 2 gems for 1 Helmet
- Trade 1 gem for 1 Bread
- Trade 2 gems for 4 Wool


# What did I learn from this:

 Further developed my OOP skills in Java by:
 - Applying Polymorphism and Encapsulation concepts.
 - Overriding equals(), HashCode() and toString() correctly.
 - Using the Random module in Java.
 - Using enum in Java.
