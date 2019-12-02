package io.github.tehstoneman.cashcraft.events;

import io.github.tehstoneman.cashcraft.common.item.CashCraftItems;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber( bus = Mod.EventBusSubscriber.Bus.MOD )
public class RegistryEventHandler
{
	@SubscribeEvent
	public static void onItemsRegistry( final RegistryEvent.Register< Item > event )
	{
		final IForgeRegistry< Item > registry = event.getRegistry();

		registry.register( CashCraftItems.COIN_ONE.setRegistryName( "coin_001" ) );
		registry.register( CashCraftItems.COIN_TWO.setRegistryName( "coin_002" ) );
		registry.register( CashCraftItems.COIN_FIVE.setRegistryName( "coin_005" ) );
		registry.register( CashCraftItems.COIN_TEN.setRegistryName( "coin_010" ) );
		registry.register( CashCraftItems.COIN_TWENTY.setRegistryName( "coin_020" ) );
		registry.register( CashCraftItems.COIN_FIFTY.setRegistryName( "coin_050" ) );
		registry.register( CashCraftItems.NOTE_ONE.setRegistryName( "note_001" ) );
		registry.register( CashCraftItems.NOTE_TWO.setRegistryName( "note_002" ) );
		registry.register( CashCraftItems.NOTE_FIVE.setRegistryName( "note_005" ) );
		registry.register( CashCraftItems.NOTE_TEN.setRegistryName( "note_010" ) );
		registry.register( CashCraftItems.NOTE_TWENTY.setRegistryName( "note_020" ) );
		registry.register( CashCraftItems.NOTE_FIFTY.setRegistryName( "note_050" ) );
		registry.register( CashCraftItems.NOTE_HUNDRED.setRegistryName( "note_100" ) );
		registry.register( CashCraftItems.MONEY_POUCH.setRegistryName( "moneypouch" ) );
	}
}
