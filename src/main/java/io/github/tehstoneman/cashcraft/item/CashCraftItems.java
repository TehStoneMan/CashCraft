package io.github.tehstoneman.cashcraft.item;

import io.github.tehstoneman.cashcraft.ModInfo;
import io.github.tehstoneman.cashcraft.item.ItemCash.EnumCoinValue;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CashCraftItems
{
	public static ItemCash	itemCoin;

	public static void RegisterItems()
	{
		itemCoin = (ItemCash)new ItemCash().setUnlocalizedName( ModInfo.MODID + ".cash" );
		GameRegistry.registerItem( itemCoin, "cash" );
	}

	public static void RegisterRecipes()
	{
		final ItemStack coinOne = new ItemStack( itemCoin, 1, EnumCoinValue.COIN_ONE.getMetadata() );
		final ItemStack coinTwo = new ItemStack( itemCoin, 1, EnumCoinValue.COIN_TWO.getMetadata() );
		final ItemStack coinFour = new ItemStack( itemCoin, 1, EnumCoinValue.COIN_FOUR.getMetadata() );
		final ItemStack coinEight = new ItemStack( itemCoin, 1, EnumCoinValue.COIN_EIGHT.getMetadata() );
		final ItemStack coinSixteen = new ItemStack( itemCoin, 1, EnumCoinValue.COIN_SIXTEEN.getMetadata() );
		final ItemStack coinThirtyTwo = new ItemStack( itemCoin, 1, EnumCoinValue.COIN_THIRTY_TWO.getMetadata() );
		final ItemStack coinSixtyFour = new ItemStack( itemCoin, 1, EnumCoinValue.COIN_SIXTY_FOUR.getMetadata() );
		final ItemStack noteOne = new ItemStack( itemCoin, 1, EnumCoinValue.NOTE_ONE.getMetadata() );
		final ItemStack noteTwo = new ItemStack( itemCoin, 1, EnumCoinValue.NOTE_TWO.getMetadata() );
		final ItemStack noteFour = new ItemStack( itemCoin, 1, EnumCoinValue.NOTE_FOUR.getMetadata() );
		final ItemStack noteEight = new ItemStack( itemCoin, 1, EnumCoinValue.NOTE_EIGHT.getMetadata() );
		final ItemStack noteSixteen = new ItemStack( itemCoin, 1, EnumCoinValue.NOTE_SIXTEEN.getMetadata() );
		final ItemStack noteThirtyTwo = new ItemStack( itemCoin, 1, EnumCoinValue.NOTE_THIRTY_TWO.getMetadata() );
		final ItemStack noteSixtyFour = new ItemStack( itemCoin, 1, EnumCoinValue.NOTE_SIXTY_FOUR.getMetadata() );
		final ItemStack noteOneTwoEight = new ItemStack( itemCoin, 1, EnumCoinValue.NOTE_ONE_TWO_EIGHT.getMetadata() );

		GameRegistry.addShapelessRecipe( coinTwo, new Object[] { coinOne, coinOne } );
		GameRegistry.addShapelessRecipe( coinFour, new Object[] { coinTwo, coinTwo } );
		GameRegistry.addShapelessRecipe( coinEight, new Object[] { coinFour, coinFour } );
		GameRegistry.addShapelessRecipe( coinSixteen, new Object[] { coinEight, coinEight } );
		GameRegistry.addShapelessRecipe( coinThirtyTwo, new Object[] { coinSixteen, coinSixteen } );
		GameRegistry.addShapelessRecipe( coinSixtyFour, new Object[] { coinThirtyTwo, coinThirtyTwo } );
		GameRegistry.addShapelessRecipe( noteOne, new Object[] { coinSixtyFour, coinSixtyFour } );
		GameRegistry.addShapelessRecipe( noteTwo, new Object[] { noteOne, noteOne } );
		GameRegistry.addShapelessRecipe( noteFour, new Object[] { noteTwo, noteTwo } );
		GameRegistry.addShapelessRecipe( noteEight, new Object[] { noteFour, noteFour } );
		GameRegistry.addShapelessRecipe( noteSixteen, new Object[] { noteEight, noteEight } );
		GameRegistry.addShapelessRecipe( noteThirtyTwo, new Object[] { noteSixteen, noteSixteen } );
		GameRegistry.addShapelessRecipe( noteSixtyFour, new Object[] { noteThirtyTwo, noteThirtyTwo } );
		GameRegistry.addShapelessRecipe( noteOneTwoEight, new Object[] { noteSixtyFour, noteSixtyFour } );

		GameRegistry.addShapelessRecipe( new ItemStack( itemCoin, 2, EnumCoinValue.COIN_ONE.getMetadata() ), new Object[] { coinTwo } );
		GameRegistry.addShapelessRecipe( new ItemStack( itemCoin, 2, EnumCoinValue.COIN_TWO.getMetadata() ), new Object[] { coinFour } );
		GameRegistry.addShapelessRecipe( new ItemStack( itemCoin, 2, EnumCoinValue.COIN_FOUR.getMetadata() ), new Object[] { coinEight } );
		GameRegistry.addShapelessRecipe( new ItemStack( itemCoin, 2, EnumCoinValue.COIN_EIGHT.getMetadata() ), new Object[] { coinSixteen } );
		GameRegistry.addShapelessRecipe( new ItemStack( itemCoin, 2, EnumCoinValue.COIN_SIXTEEN.getMetadata() ), new Object[] { coinThirtyTwo } );
		GameRegistry.addShapelessRecipe( new ItemStack( itemCoin, 2, EnumCoinValue.COIN_THIRTY_TWO.getMetadata() ), new Object[] { coinSixtyFour } );
		GameRegistry.addShapelessRecipe( new ItemStack( itemCoin, 2, EnumCoinValue.COIN_SIXTY_FOUR.getMetadata() ), new Object[] { noteOne } );
		GameRegistry.addShapelessRecipe( new ItemStack( itemCoin, 2, EnumCoinValue.NOTE_ONE.getMetadata() ), new Object[] { noteTwo } );
		GameRegistry.addShapelessRecipe( new ItemStack( itemCoin, 2, EnumCoinValue.NOTE_TWO.getMetadata() ), new Object[] { noteFour } );
		GameRegistry.addShapelessRecipe( new ItemStack( itemCoin, 2, EnumCoinValue.NOTE_FOUR.getMetadata() ), new Object[] { noteEight } );
		GameRegistry.addShapelessRecipe( new ItemStack( itemCoin, 2, EnumCoinValue.NOTE_EIGHT.getMetadata() ), new Object[] { noteSixteen } );
		GameRegistry.addShapelessRecipe( new ItemStack( itemCoin, 2, EnumCoinValue.NOTE_SIXTEEN.getMetadata() ), new Object[] { noteThirtyTwo } );
		GameRegistry.addShapelessRecipe( new ItemStack( itemCoin, 2, EnumCoinValue.NOTE_THIRTY_TWO.getMetadata() ), new Object[] { noteSixtyFour } );
		GameRegistry.addShapelessRecipe( new ItemStack( itemCoin, 2, EnumCoinValue.NOTE_SIXTY_FOUR.getMetadata() ), new Object[] { noteOneTwoEight } );
	}
}
