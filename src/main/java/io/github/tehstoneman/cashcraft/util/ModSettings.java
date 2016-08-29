package io.github.tehstoneman.cashcraft.util;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class ModSettings
{
	public static boolean	makeChange;
	public static boolean	useEconomy;
	public static String	cashSingular;
	public static String	cashPlural;
	public static boolean	showAsCoins;
	public static boolean	useTrade;

	public static void init( File configFile )
	{
		final Configuration config = new Configuration( configFile );

		config.load();

		// General settings
		makeChange = config.get( Configuration.CATEGORY_GENERAL, "makeChange", true,
				"Allow coins/notes to be crafted from higher or lower value coins/notes." ).getBoolean();
		useEconomy = config.get( Configuration.CATEGORY_GENERAL, "useEconomy", true,
				"Enable internal economy module." ).getBoolean();

		// Display settings
		showAsCoins = config.get( "Display", "showAsCoins", false,
				"Display values in coins instead of cash." ).getBoolean();
		cashSingular = config.get( "Display", "cashSingular", "",
				"Override default singular cash unit name." ).getString();
		cashPlural = config.get( "Display", "cashPlural", "",
				"Override default plural cash unit name." ).getString();

		// Economy settings
		useTrade = config.get( "Economy", "useTrade", true,
				"Eneables the internal trading module." ).getBoolean();

		config.save();
	}
}
