package io.github.tehstoneman.cashcraft.block;

import io.github.tehstoneman.cashcraft.ModInfo;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class CashCraftBlocks
{
	public static Block				blockVender;

	public static void RegisterBlocks()
	{
		blockVender = new BlockVender( Material.WOOD ).setUnlocalizedName( ModInfo.MODID + ".vender" );
		GameRegistry.registerBlock( blockVender, "vender" );
	}

	public static void RegisterRecipes()
	{
		//@formatter:off
		final IRecipe tradeBlockRecipe = new ShapedOreRecipe( new ItemStack( CashCraftBlocks.blockVender, 4 ),
				new Object[] { "PPP",
							   "IGI",
							   "PDP", 'P', "plankWood",
							   		  'I', "ingotIron",
							   		  'G', "blockGlass",
							   		  'D', Blocks.DISPENSER, } );
		GameRegistry.addRecipe( tradeBlockRecipe );
		//@formatter:on
	}
}
