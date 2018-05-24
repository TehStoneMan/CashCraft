package io.github.tehstoneman.cashcraft.config;

import java.io.File;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class CashCraftConfig
{
	private final Configuration	config;

	public static final String	CATEGORY_DISPLAY			= "display";
	public static final String	CATEGORY_ECONOMY			= "economy";

	private static boolean		makeChange;
	public static boolean		useEconomy;
	public static boolean		doMobDrops;

	public static boolean		showAsCoins;
	public static boolean		useCustomName;
	public static String		cashSingular;
	public static String		cashPlural;

	public static boolean		useTrade;

	public CashCraftConfig( File file )
	{
		config = new Configuration( file );
	}

	public Configuration getConfig()
	{
		return config;
	}

	/**
	 * Load the configuration values from the configuration file
	 */
	public void syncFromFile()
	{
		syncConfig( true, true );
	}

	/**
	 * Save the GUI-altered values to disk
	 */
	public void syncFromGUI()
	{
		syncConfig( false, true );
	}

	/**
	 * Save the configuration variables (fields) to disk
	 */
	public void syncFromFields()
	{
		syncConfig( false, false );
	}

	/**
	 * Synchronize the three copies of the data
	 * 1) loadConfigFromFile && readFieldsFromConfig -> initialize everything from the disk file.
	 * 2) !loadConfigFromFile && readFieldsFromConfig --> copy everything from the config file (altered by GUI).
	 * 3) !loadConfigFromFile && !readFieldsFromConfig --> copy everything from the native fields.
	 *
	 * @param loadConfigFromFile
	 *            if true, load the config field from the configuration file on disk.
	 * @param readFieldsFromConfig
	 *            if true, reload the member variables from the config field.
	 */
	public void syncConfig( boolean loadConfigFromFile, boolean readFieldsFromConfig )
	{
		// Load config file
		if( loadConfigFromFile )
			config.load();

		// ----------------
		// Define config properties
		//@formatter:off
		// General
		final Property propMakeChange = config.get( Configuration.CATEGORY_GENERAL, "makeChange", true )
				.setLanguageKey( "config.cashcraft.make_change" ).setRequiresMcRestart( true );
		final Property propUseEconomy = config.get( Configuration.CATEGORY_GENERAL, "useEconomy", true )
				.setLanguageKey( "config.cashcraft.use_economy" ).setRequiresMcRestart( true );
		final Property propDoMobDrops = config.get( Configuration.CATEGORY_GENERAL, "doMobDrops", true )
				.setLanguageKey( "config.cashcraft.mob_drops" ).setRequiresMcRestart( false );

		// Display
		final Property propShowAsCoins = config.get( this.CATEGORY_DISPLAY, "showAsCoins", false )
				.setLanguageKey( "config.cashcraft.show_as_Coins" ).setRequiresMcRestart( false );
		final Property propUseCustomName = config.get( this.CATEGORY_DISPLAY, "useCustomName", false )
				.setLanguageKey( "config.cashcraft.use_custom_name" ).setRequiresMcRestart( false );
		final Property propCashSingular = config.get( this.CATEGORY_DISPLAY, "cashSingular", "%s cash" )
				.setLanguageKey( "config.cashcraft.cash_singular" ).setRequiresMcRestart( false );
		final Property propCashPlural = config.get( this.CATEGORY_DISPLAY, "cashPlural", "%s cash" )
				.setLanguageKey( "config.cashcraft.cash_plural" ).setRequiresMcRestart( false );
		
		// Economy
		final Property propUseTrade = config.get( this.CATEGORY_ECONOMY, "useTrade", true )
				.setLanguageKey( "config.cashcraft.use_trade" ).setRequiresMcRestart( false );
		//@formatter:on
		// ----------------

		// ----------------
		// Read properties
		if( readFieldsFromConfig )
		{
			makeChange = propMakeChange.getBoolean();
			useEconomy = propUseEconomy.getBoolean();
			doMobDrops = propDoMobDrops.getBoolean();
			
			showAsCoins = propShowAsCoins.getBoolean();
			useCustomName = propUseCustomName.getBoolean();
			cashSingular = propCashSingular.getString();
			cashPlural = propCashPlural.getString();
			
			useEconomy = propUseEconomy.getBoolean();
		}
		// ----------------

		// ----------------
		// Save properties to file
		propMakeChange.set( makeChange );
		propUseEconomy.set( useEconomy );
		propDoMobDrops.set( doMobDrops );
		
		propShowAsCoins.set( showAsCoins );
		propUseCustomName.set( useCustomName );
		propCashSingular.set( cashSingular );
		propCashPlural.set( cashPlural );
		
		propUseEconomy.set( useEconomy );
		// ----------------

		if( config.hasChanged() )
			config.save();
	}
}
