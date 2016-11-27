package io.github.tehstoneman.cashcraft.util;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class ModSettings
{
	public static boolean	makeChange;
	public static boolean	useEconomy;
	public static boolean	doMobDrops;

	public static boolean	showAsCoins;
	public static boolean useCustomName;
	public static String	cashSingular;
	public static String	cashPlural;

	public static boolean	useTrade;

	public static void init( File configFile )
	{
		final Configuration config = new Configuration( configFile );

		config.load();

		// General settings
		//@formatter:off
		makeChange = config.getBoolean( Configuration.CATEGORY_GENERAL, "makeChange", true,
				"Allow coins/notes to be crafted from higher or lower value coins/notes." );
		useEconomy = config.getBoolean( Configuration.CATEGORY_GENERAL, "useEconomy", true,
				"Enable internal economy module." );
		doMobDrops = config.getBoolean( Configuration.CATEGORY_GENERAL, "doMobDrops", true,
				"Allow hostile mobs to drop money" );

		// Display settings
		showAsCoins = config.get( "Display", "showAsCoins", false,
				"Display values in coins instead of cash." ).getBoolean();
		showAsCoins = config.get( "Display", "useCustomName", false,
				"Use custom currency names instead of default names." ).getBoolean();
		cashSingular = config.get( "Display", "cashSingular", "%s cash",
				"Custom singular cash unit name." ).getString();
		cashPlural = config.get( "Display", "cashPlural", "%s cash",
				"Custom plural cash unit name." ).getString();

		// Economy settings
		useTrade = config.get( "Economy", "useTrade", true,
				"Eneables the internal trading module." ).getBoolean();
		//@formatter:on

		// Economy settings
		useTrade = config.get( "Economy", "useTrade", true,
				"Eneables the internal trading module." ).getBoolean();

		config.save();
	}
}
