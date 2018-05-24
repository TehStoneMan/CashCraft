package io.github.tehstoneman.cashcraft;

import java.util.Random;

import org.apache.logging.log4j.Logger;

import io.github.tehstoneman.cashcraft.api.CashCraftAPI;
import io.github.tehstoneman.cashcraft.client.creativetab.CreativeTabCashCraft;
import io.github.tehstoneman.cashcraft.config.CashCraftConfig;
import io.github.tehstoneman.cashcraft.economy.Economy;
import io.github.tehstoneman.cashcraft.economy.Trade;
import io.github.tehstoneman.cashcraft.event.EventManager;
import io.github.tehstoneman.cashcraft.network.SyncConfigMessage;
import io.github.tehstoneman.cashcraft.proxies.CommonProxy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.ModMetadata;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

//@formatter:off
@Mod(	modid						= ModInfo.MODID,
		name						= ModInfo.NAME,
		version						= ModInfo.VERSION,
		//dependencies				= ModInfo.DEPENDENCIES,
		acceptedMinecraftVersions	= ModInfo.MINECRAFT,
		guiFactory					= ModInfo.GUI_FACTORY,
		updateJSON					= ModInfo.UPDATE_JSON )
//@formatter:on
public class CashCraft
{
	public static ModMetadata			modMetadata;

	@Instance( value = ModInfo.MODID )
	public static CashCraft				instance;

	// Define proxies
	@SidedProxy( clientSide = ModInfo.PROXY_LOCATION + "ClientProxy", serverSide = ModInfo.PROXY_LOCATION + "CommonProxy" )
	public static CommonProxy			proxy;

	public static Logger				logger;
	public static CreativeTabs			creativeTab;
	public static CashCraftConfig		config;
	public static SimpleNetworkWrapper	simpleNetworkWrapper;

	public static Random				random;

	// GUI ID's
	public static final int				GUI_MONEY_POUCH		= 0;

	public static final byte			MESSAGE_ID_UPDATE	= 1;

	@EventHandler
	public void preInit( FMLPreInitializationEvent event )
	{
		logger = event.getModLog();
		creativeTab = new CreativeTabCashCraft();

		// Load configuration
		config = new CashCraftConfig( event.getSuggestedConfigurationFile() );
		config.syncFromFile();

		// Register network messages
		simpleNetworkWrapper = NetworkRegistry.INSTANCE.newSimpleChannel( ModInfo.MODID );
		simpleNetworkWrapper.registerMessage( SyncConfigMessage.Handler.class, SyncConfigMessage.class, MESSAGE_ID_UPDATE, Side.CLIENT );

		// Initialize API
		CashCraftAPI.economy = new Economy();
		CashCraftAPI.trade = new Trade();

		// Register event handler
		MinecraftForge.EVENT_BUS.register( new EventManager() );

		proxy.preInit();
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
