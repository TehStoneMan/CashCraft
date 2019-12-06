package io.github.tehstoneman.cashcraft;

import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.github.tehstoneman.cashcraft.api.CashCraftAPI;
import io.github.tehstoneman.cashcraft.common.item.CashCraftItemGroup;
import io.github.tehstoneman.cashcraft.config.CashCraftConfig;
import io.github.tehstoneman.cashcraft.economy.Economy;
import io.github.tehstoneman.cashcraft.economy.Trade;
import io.github.tehstoneman.cashcraft.proxy.ClientProxy;
import io.github.tehstoneman.cashcraft.proxy.IProxy;
import io.github.tehstoneman.cashcraft.proxy.ServerProxy;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod( ModInfo.MOD_ID )
public class CashCraft
{
	public static final Logger		LOGGER				= LogManager.getLogger( ModInfo.MOD_ID );
	public static final ItemGroup	ITEM_GROUP			= new CashCraftItemGroup();
	public static final IProxy		PROXY				= DistExecutor.<IProxy> runForDist( () -> ClientProxy::new, () -> ServerProxy::new );

	public static final Random		RANDOM				= new Random();

	public CashCraft()
	{
		CashCraftConfig.register( ModLoadingContext.get() );
		// Register network messages
		// simpleNetworkWrapper = NetworkRegistry.INSTANCE.newSimpleChannel( ModInfo.MODID );
		// simpleNetworkWrapper.registerMessage( SyncConfigMessage.Handler.class, SyncConfigMessage.class, MESSAGE_ID_UPDATE, Side.CLIENT );

		// Initialize API
		CashCraftAPI.economy = new Economy();
		CashCraftAPI.trade = new Trade();

		// Register the setup method for modloading
		FMLJavaModLoadingContext.get().getModEventBus().addListener( this::setup );
	}

	public void setup( FMLCommonSetupEvent event )
	{
		PROXY.setup( event );
	}
}
