package io.github.tehstoneman.cashcraft;

import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.github.tehstoneman.cashcraft.api.CashCraftAPI;
import io.github.tehstoneman.cashcraft.common.loot.CashCraftLoot;
import io.github.tehstoneman.cashcraft.config.CashCraftConfig;
import io.github.tehstoneman.cashcraft.economy.Economy;
import io.github.tehstoneman.cashcraft.economy.Trade;
import io.github.tehstoneman.cashcraft.world.inventory.CashCraftMenuTypes;
import io.github.tehstoneman.cashcraft.world.item.CashCraftCreativeModeTab;
import io.github.tehstoneman.cashcraft.world.item.CashCraftItems;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.network.NetworkRegistry;

@Mod( ModInfo.MOD_ID )
public class CashCraft
{
	public static final Logger			LOGGER				= LogManager.getLogger( ModInfo.MOD_ID );
	public static final CreativeModeTab	CREATIVE_MODE_TAB	= new CashCraftCreativeModeTab();

	public static final Random RANDOM = new Random();

	public CashCraft()
	{
		// Register configs
		CashCraftConfig.register( ModLoadingContext.get() );

		// Register network messages
		//simpleNetworkWrapper = NetworkRegistry.newSimpleChannel( ModInfo.MOD_ID );
		//simpleNetworkWrapper.registerMessage( SyncConfigMessage.Handler.class, SyncConfigMessage.class, MESSAGE_ID_UPDATE, Side.CLIENT );

		// Initialize API
		CashCraftAPI.economy	= new Economy();
		CashCraftAPI.trade		= new Trade();

		final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

		// CashCraftBlocks.REGISTERY.register( modEventBus );
		CashCraftItems.REGISTERY.register( modEventBus );
		CashCraftMenuTypes.REGISTERY.register( modEventBus );
		CashCraftLoot.REGISTERY.register( modEventBus );
	}
}
