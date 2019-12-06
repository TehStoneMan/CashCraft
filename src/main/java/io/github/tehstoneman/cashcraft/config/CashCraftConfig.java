package io.github.tehstoneman.cashcraft.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;

public class CashCraftConfig
{
	private static final ForgeConfigSpec.Builder	COMMON_BUILDER	= new ForgeConfigSpec.Builder();

	public static Common							COMMON			= new Common( COMMON_BUILDER );
	// public static Client CLIENT = new Common( CLIENT_BUILDER );
	// public static Server SERVER = new Common( SERVER_BUILDER );

	private static ForgeConfigSpec					COMMON_SPEC		= COMMON_BUILDER.build();

	public static class Common
	{
		public ForgeConfigSpec.BooleanValue				makeChange;
		public ForgeConfigSpec.BooleanValue				useEconomy;
		public ForgeConfigSpec.BooleanValue				doMobDrops;

		public ForgeConfigSpec.BooleanValue				showAsCoins;
		public ForgeConfigSpec.BooleanValue				useCustomName;
		public ForgeConfigSpec.ConfigValue< String >	cashSingular;
		public ForgeConfigSpec.ConfigValue< String >	cashPlural;

		public ForgeConfigSpec.BooleanValue				useTrade;

		public Common( ForgeConfigSpec.Builder builder )
		{
			builder.comment( "General settings." ).push( "General" );

			//@formatter:off
			makeChange = builder
					.translation( "config.cashcraft.make_change" )
					.define( "makeChange", true );
			useEconomy = builder
					.translation( "config.cashcraft.use_economy" )
					.define( "useEconomy", true );
			doMobDrops = builder
					.translation( "config.cashcraft.mob_drops" )
					.define( "doMobDrops", true );
			//@formatter:on

			builder.pop();

			builder.comment( "Display settings." ).push( "Display" );

			//@formatter:off
			showAsCoins = builder
					.translation( "config.cashcraft.show_as_coins" )
					.define( "showAsCoins", true );
			useCustomName = builder
					.translation( "config.cashcraft.use_custom_name" )
					.define( "useCustomName", true );
			cashSingular = builder
					.translation( "config.cashcraft.cash_singular" )
					.define("cashSingular","%s cash");
			cashPlural = builder
					.translation( "config.cashcraft.cash_plural" )
					.define("cashPlural","%s cash");
			//@formatter:on

			builder.pop();

			builder.comment( "Economy settings." ).push( "Economy" );

			//@formatter:off
			useTrade = builder
					.translation( "config.cashcraft.show_as_coins" )
					.define( "use_trade", true );
			//@formatter:on

			builder.pop();
		}
	}

	public static void register( ModLoadingContext context )
	{
		context.registerConfig( ModConfig.Type.COMMON, COMMON_SPEC );
	}
}
