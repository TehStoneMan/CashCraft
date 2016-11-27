package io.github.tehstoneman.cashcraft.common.item;

import io.github.tehstoneman.cashcraft.ModInfo;
import io.github.tehstoneman.cashcraft.common.item.ItemCash.EnumCoinValue;
import io.github.tehstoneman.cashcraft.util.ModSettings;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class CashCraftItems
{
	public static ItemCash			itemCoin;
	public static ItemMoneyPouch	itemMoneyPouch;

	public static void RegisterItems()
	{
		itemCoin = (ItemCash)new ItemCash().setUnlocalizedName( ModInfo.MODID + ".cash" );
		itemMoneyPouch = (ItemMoneyPouch)new ItemMoneyPouch().setUnlocalizedName( ModInfo.MODID + ".moneypouch" );

		GameRegistry.register( itemCoin.setRegistryName( "cash" ) );
		GameRegistry.register( itemMoneyPouch.setRegistryName( "moneypouch" ) );
	}

	public static void RegisterRecipes()
	{
		if( ModSettings.makeChange )
		{
			// @formatter:off
			final ItemStack coinOne		= new ItemStack( itemCoin, 1, EnumCoinValue.COIN_ONE.getMetadata() );
			final ItemStack coinTwo		= new ItemStack( itemCoin, 1, EnumCoinValue.COIN_TWO.getMetadata() );
			final ItemStack coinFive	= new ItemStack( itemCoin, 1, EnumCoinValue.COIN_FIVE.getMetadata() );
			final ItemStack coinTen		= new ItemStack( itemCoin, 1, EnumCoinValue.COIN_TEN.getMetadata() );
			final ItemStack coinTwenty	= new ItemStack( itemCoin, 1, EnumCoinValue.COIN_TWENTY.getMetadata() );
			final ItemStack coinFifty	= new ItemStack( itemCoin, 1, EnumCoinValue.COIN_FIFTY.getMetadata() );
			final ItemStack noteOne		= new ItemStack( itemCoin, 1, EnumCoinValue.NOTE_ONE.getMetadata() );
			final ItemStack noteTwo		= new ItemStack( itemCoin, 1, EnumCoinValue.NOTE_TWO.getMetadata() );
			final ItemStack noteFive	= new ItemStack( itemCoin, 1, EnumCoinValue.NOTE_FIVE.getMetadata() );
			final ItemStack noteTen		= new ItemStack( itemCoin, 1, EnumCoinValue.NOTE_TEN.getMetadata() );
			final ItemStack noteTwenty	= new ItemStack( itemCoin, 1, EnumCoinValue.NOTE_TWENTY.getMetadata() );
			final ItemStack noteFifty	= new ItemStack( itemCoin, 1, EnumCoinValue.NOTE_FIFTY.getMetadata() );
			final ItemStack noteHundred	= new ItemStack( itemCoin, 1, EnumCoinValue.NOTE_HUNDRED.getMetadata() );

			GameRegistry.addShapelessRecipe( coinTwo,		new Object[] { coinOne, coinOne } );
			GameRegistry.addShapelessRecipe( coinFive,		new Object[] { coinTwo, coinTwo, coinOne } );
			GameRegistry.addShapelessRecipe( coinTen,		new Object[] { coinFive, coinFive } );
			GameRegistry.addShapelessRecipe( coinTwenty,	new Object[] { coinTen, coinTen } );
			GameRegistry.addShapelessRecipe( coinFifty,		new Object[] { coinTwenty, coinTwenty, coinTen } );
			GameRegistry.addShapelessRecipe( noteOne,		new Object[] { coinFifty, coinFifty } );
			GameRegistry.addShapelessRecipe( noteTwo,		new Object[] { noteOne, noteOne } );
			GameRegistry.addShapelessRecipe( noteFive,		new Object[] { noteTwo, noteTwo, noteOne } );
			GameRegistry.addShapelessRecipe( noteTen,		new Object[] { noteFive, noteFive } );
			GameRegistry.addShapelessRecipe( noteTwenty,	new Object[] { noteTen, noteTen } );
			GameRegistry.addShapelessRecipe( noteFifty,		new Object[] { noteTwenty, noteTwenty, noteTen } );
			GameRegistry.addShapelessRecipe( noteHundred,	new Object[] { noteFifty, noteFifty } );

			GameRegistry.addShapelessRecipe( new ItemStack( itemCoin, 2, EnumCoinValue.COIN_ONE.getMetadata() ),	new Object[] { coinTwo } );
			GameRegistry.addShapelessRecipe( new ItemStack( itemCoin, 5, EnumCoinValue.COIN_ONE.getMetadata() ),	new Object[] { coinFive } );
			GameRegistry.addShapelessRecipe( new ItemStack( itemCoin, 2, EnumCoinValue.COIN_FIVE.getMetadata() ),	new Object[] { coinTen } );
			GameRegistry.addShapelessRecipe( new ItemStack( itemCoin, 2, EnumCoinValue.COIN_TEN.getMetadata() ),	new Object[] { coinTwenty } );
			GameRegistry.addShapelessRecipe( new ItemStack( itemCoin, 5, EnumCoinValue.COIN_TEN.getMetadata() ),	new Object[] { coinFifty } );
			GameRegistry.addShapelessRecipe( new ItemStack( itemCoin, 2, EnumCoinValue.COIN_FIFTY.getMetadata() ),	new Object[] { noteOne } );
			GameRegistry.addShapelessRecipe( new ItemStack( itemCoin, 2, EnumCoinValue.NOTE_ONE.getMetadata() ),	new Object[] { noteTwo } );
			GameRegistry.addShapelessRecipe( new ItemStack( itemCoin, 5, EnumCoinValue.NOTE_ONE.getMetadata() ),	new Object[] { noteFive } );
			GameRegistry.addShapelessRecipe( new ItemStack( itemCoin, 2, EnumCoinValue.NOTE_FIVE.getMetadata() ),	new Object[] { noteTen } );
			GameRegistry.addShapelessRecipe( new ItemStack( itemCoin, 2, EnumCoinValue.NOTE_TEN.getMetadata() ),	new Object[] { noteTwenty } );
			GameRegistry.addShapelessRecipe( new ItemStack( itemCoin, 5, EnumCoinValue.NOTE_TEN.getMetadata() ),	new Object[] { noteFifty } );
			GameRegistry.addShapelessRecipe( new ItemStack( itemCoin, 2, EnumCoinValue.NOTE_FIFTY.getMetadata() ),	new Object[] { noteHundred } );
			// @formatter:on
		}

		// @formatter:off
		final IRecipe recipeMoneyPouch = new ShapedOreRecipe( new ItemStack( itemMoneyPouch ),
				new Object[] { "S.S",
							   "L.L",
							   ".L.", 'S', "string",
							   		  'L', "leather" } );
		// @formatter:on
		GameRegistry.addRecipe( recipeMoneyPouch );
	}
}
