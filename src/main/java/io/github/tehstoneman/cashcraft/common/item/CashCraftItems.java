package io.github.tehstoneman.cashcraft.common.item;

import io.github.tehstoneman.cashcraft.ModInfo;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder( ModInfo.MOD_ID )
public class CashCraftItems
{
	//@formatter:off
	@ObjectHolder( "coin_001" )		public static ItemCash			COIN_ONE;
	@ObjectHolder( "coin_002" )		public static ItemCash			COIN_TWO;
	@ObjectHolder( "coin_005" )		public static ItemCash			COIN_FIVE;
	@ObjectHolder( "coin_010" )		public static ItemCash			COIN_TEN;
	@ObjectHolder( "coin_020" )		public static ItemCash			COIN_TWENTY;
	@ObjectHolder( "coin_050" )		public static ItemCash			COIN_FIFTY;
	@ObjectHolder( "note_001" )		public static ItemCash			NOTE_ONE;
	@ObjectHolder( "note_002" )		public static ItemCash			NOTE_TWO;
	@ObjectHolder( "note_005" )		public static ItemCash			NOTE_FIVE;
	@ObjectHolder( "note_010" )		public static ItemCash			NOTE_TEN;
	@ObjectHolder( "note_020" )		public static ItemCash			NOTE_TWENTY;
	@ObjectHolder( "note_050" )		public static ItemCash			NOTE_FIFTY;
	@ObjectHolder( "note_100" )		public static ItemCash			NOTE_HUNDRED;
	@ObjectHolder( "money_pouch" )	public static ItemMoneyPouch	MONEY_POUCH;
	//@formatter:on

	@Mod.EventBusSubscriber( bus = Mod.EventBusSubscriber.Bus.MOD )
	private static class Register
	{
		@SubscribeEvent
		public static void onItemsRegistry( final RegistryEvent.Register< Item > event )
		{
			final IForgeRegistry< Item > registry = event.getRegistry();

			registry.register( new ItemCash( 1 ).setRegistryName( ModInfo.MOD_ID, "coin_001" ) );
			registry.register( new ItemCash( 2 ).setRegistryName( ModInfo.MOD_ID, "coin_002" ) );
			registry.register( new ItemCash( 5 ).setRegistryName( ModInfo.MOD_ID, "coin_005" ) );
			registry.register( new ItemCash( 10 ).setRegistryName( ModInfo.MOD_ID, "coin_010" ) );
			registry.register( new ItemCash( 20 ).setRegistryName( ModInfo.MOD_ID, "coin_020" ) );
			registry.register( new ItemCash( 50 ).setRegistryName( ModInfo.MOD_ID, "coin_050" ) );
			registry.register( new ItemCash( 100 ).setRegistryName( ModInfo.MOD_ID, "note_001" ) );
			registry.register( new ItemCash( 200 ).setRegistryName( ModInfo.MOD_ID, "note_002" ) );
			registry.register( new ItemCash( 500 ).setRegistryName( ModInfo.MOD_ID, "note_005" ) );
			registry.register( new ItemCash( 1000 ).setRegistryName( ModInfo.MOD_ID, "note_010" ) );
			registry.register( new ItemCash( 2000 ).setRegistryName( ModInfo.MOD_ID, "note_020" ) );
			registry.register( new ItemCash( 5000 ).setRegistryName( ModInfo.MOD_ID, "note_050" ) );
			registry.register( new ItemCash( 10000 ).setRegistryName( ModInfo.MOD_ID, "note_100" ) );
			registry.register( new ItemMoneyPouch().setRegistryName( ModInfo.MOD_ID, "money_pouch" ) );
		}
	}
}
