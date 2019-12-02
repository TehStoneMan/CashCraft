package io.github.tehstoneman.cashcraft;

import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.github.tehstoneman.cashcraft.api.CashCraftAPI;
import io.github.tehstoneman.cashcraft.common.item.CashCraftItems;
import io.github.tehstoneman.cashcraft.config.CashCraftConfig;
import io.github.tehstoneman.cashcraft.economy.Economy;
import io.github.tehstoneman.cashcraft.economy.Trade;
import io.github.tehstoneman.cashcraft.events.RegistryEventHandler;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

//@formatter:off
@Mod(	ModInfo.MODID )
//@formatter:on
public class CashCraft
{
	// Directly reference a log4j logger.
	public static final Logger		LOGGER				= LogManager.getLogger();
	public static final ItemGroup	ITEM_GROUP			= new ItemGroup( "cashCraft" )
														{
															@Override
															@OnlyIn( Dist.CLIENT )
															public ItemStack createIcon()
															{
																return new ItemStack( CashCraftItems.COIN_ONE );
															}
														};

	public static CashCraftConfig	config;

	public static Random			random;

	// GUI ID's
	public static final int			GUI_MONEY_POUCH		= 0;

	public static final byte		MESSAGE_ID_UPDATE	= 1;

	public CashCraft()
	{
		// Load configuration
		// config = new CashCraftConfig( event.getSuggestedConfigurationFile() );
		// config.syncFromFile();

		// Register ourselves for server and other game events we are interested in
		MinecraftForge.EVENT_BUS.register( new RegistryEventHandler() );
		// MinecraftForge.EVENT_BUS.register( new EventManager() );

		// Register network messages
		// simpleNetworkWrapper = NetworkRegistry.INSTANCE.newSimpleChannel( ModInfo.MODID );
		// simpleNetworkWrapper.registerMessage( SyncConfigMessage.Handler.class, SyncConfigMessage.class, MESSAGE_ID_UPDATE, Side.CLIENT );

		// Initialize API
		CashCraftAPI.economy = new Economy();
		CashCraftAPI.trade = new Trade();
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
