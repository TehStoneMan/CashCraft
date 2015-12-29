package io.github.tehstoneman.cashcraft;

import io.github.tehstoneman.cashcraft.api.CashCraftAPI;
import io.github.tehstoneman.cashcraft.economy.Economy;
import io.github.tehstoneman.cashcraft.proxies.CommonProxy;
import io.github.tehstoneman.cashcraft.util.ModSettings;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

//@formatter:off
@Mod(	modid						= ModInfo.MODID,
		name						= ModInfo.NAME,
		version						= ModInfo.VERSION,
		dependencies				= ModInfo.DEPENDENCIES,
		acceptedMinecraftVersions	= ModInfo.MINECRAFT )
//@formatter:on
public class CashCraft
{
	public static ModMetadata	modMetadata;

	@Instance( value = ModInfo.MODID )
	public static CashCraft		instance;

	// Define proxies
	@SidedProxy( clientSide = ModInfo.PROXY_LOCATION + "ClientProxy", serverSide = ModInfo.PROXY_LOCATION + "CommonProxy" )
	public static CommonProxy	proxy;

	public ModSettings			modConfig;

	// public static SimpleNetworkWrapper network;

	@EventHandler
	public void serverLoad( FMLServerStartingEvent event )
	{
		// Economy commands - for testing purposes only
		// event.registerServerCommand( new CommandWallet() );
		// event.registerServerCommand( new CommandPay() );
		// event.registerServerCommand( new CommandBankrupt() );
		// event.registerServerCommand( new CommandReconcile() );
		// event.registerServerCommand( new CommandBill() );
	}

	@EventHandler
	public void preInit( FMLPreInitializationEvent event )
	{
		proxy.preInit();

		// Load configuration
		ModSettings.init( event.getSuggestedConfigurationFile() );

		// Initialize API
		CashCraftAPI.economy = new Economy();
	}

	@EventHandler
	public void init( FMLInitializationEvent event )
	{
		proxy.Init();
	}

	@EventHandler
	public void postInit( FMLPostInitializationEvent event )
	{
		proxy.postInit();

		// Register event handler
		// MinecraftForge.EVENT_BUS.register( new EventManager() );
	}

	/**
	 * Prepend the name with the mod ID, suitable for ResourceLocations such as
	 * textures.
	 * Adapted from MinecraftByExample
	 *
	 * @param name
	 * @return "cashcraft:name"
	 */
	public static String modAsset( String name )
	{
		return ModInfo.MODID + ":" + name;
	}
}