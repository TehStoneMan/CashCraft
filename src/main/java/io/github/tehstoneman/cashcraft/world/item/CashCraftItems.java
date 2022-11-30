package io.github.tehstoneman.cashcraft.world.item;

import io.github.tehstoneman.cashcraft.ModInfo;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class CashCraftItems
{
	public static final DeferredRegister< Item > REGISTERY = DeferredRegister.create( ForgeRegistries.ITEMS, ModInfo.MOD_ID );

	public static final RegistryObject< CashItem >	COIN_ONE	= REGISTERY.register( "coin_001", () -> new CashItem( 1 ) );
	public static final RegistryObject< CashItem >	COIN_TWO	= REGISTERY.register( "coin_002", () -> new CashItem( 2 ) );
	public static final RegistryObject< CashItem >	COIN_FIVE	= REGISTERY.register( "coin_005", () -> new CashItem( 5 ) );
	public static final RegistryObject< CashItem >	COIN_TEN	= REGISTERY.register( "coin_010", () -> new CashItem( 10 ) );
	public static final RegistryObject< CashItem >	COIN_TWENTY	= REGISTERY.register( "coin_020", () -> new CashItem( 20 ) );
	public static final RegistryObject< CashItem >	COIN_FIFTY	= REGISTERY.register( "coin_050", () -> new CashItem( 50 ) );

	public static final RegistryObject< CashItem >	NOTE_ONE		= REGISTERY.register( "note_001", () -> new CashItem( 100 ) );
	public static final RegistryObject< CashItem >	NOTE_TWO		= REGISTERY.register( "note_002", () -> new CashItem( 200 ) );
	public static final RegistryObject< CashItem >	NOTE_FIVE		= REGISTERY.register( "note_005", () -> new CashItem( 500 ) );
	public static final RegistryObject< CashItem >	NOTE_TEN		= REGISTERY.register( "note_010", () -> new CashItem( 1000 ) );
	public static final RegistryObject< CashItem >	NOTE_TWENTY		= REGISTERY.register( "note_020", () -> new CashItem( 2000 ) );
	public static final RegistryObject< CashItem >	NOTE_FIFTY		= REGISTERY.register( "note_050", () -> new CashItem( 5000 ) );
	public static final RegistryObject< CashItem >	NOTE_HUNDRED	= REGISTERY.register( "note_100", () -> new CashItem( 10000 ) );

	public static final RegistryObject< MoneyPouchItem > MONEY_POUCH = REGISTERY.register( "money_pouch", MoneyPouchItem::new );
}
