package io.github.tehstoneman.cashcraft.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;

public class CashCraftConfig
{
	public static class Server
	{
		public Server( ForgeConfigSpec.Builder builder )
		{
			builder.push( "General" );

			makeChange	= builder.translation( "config.cashcraft.make_change" ).define( "make_change", true );
			useEconomy	= builder.translation( "config.cashcraft.use_economy" ).define( "use_economy", true );
			doMobDrops	= builder.translation( "config.cashcraft.mob_drops" ).define( "mob_drops", true );

			builder.pop();

			builder.push( "Display" );

			showAsCoins		= builder.translation( "config.cashcraft.show_as_coins" ).define( "show_as_coins", true );
			useCustomName	= builder.translation( "config.cashcraft.use_custom_name" ).define( "use_custom_name", true );
			cashSingular	= builder.translation( "config.cashcraft.cash_singular" ).define( "cash_singular", "%s cash" );
			cashPlural		= builder.translation( "config.cashcraft.cash_plural" ).define( "cash_plural", "%s cash" );

			builder.pop();

			builder.push( "Economy" );

			useTrade = builder.translation( "config.cashcraft.show_as_coins" ).define( "use_trade", true );

			builder.pop();
		}
	}

	public static ForgeConfigSpec.BooleanValue	makeChange;
	public static ForgeConfigSpec.BooleanValue	useEconomy;
	public static ForgeConfigSpec.BooleanValue	doMobDrops;

	public static ForgeConfigSpec.BooleanValue			showAsCoins;
	public static ForgeConfigSpec.BooleanValue			useCustomName;
	public static ForgeConfigSpec.ConfigValue< String >	cashSingular;
	public static ForgeConfigSpec.ConfigValue< String >	cashPlural;

	public static ForgeConfigSpec.BooleanValue useTrade;

	private static final ForgeConfigSpec.Builder SERVER_BUILDER = new ForgeConfigSpec.Builder();

	public static Server SERVER = new Server( SERVER_BUILDER );

	private static ForgeConfigSpec SERVER_SPEC = SERVER_BUILDER.build();

	public static void register( ModLoadingContext context )
	{
		context.registerConfig( ModConfig.Type.SERVER, SERVER_SPEC );
	}
}
