package io.github.tehstoneman.cashcraft.world.item;

import io.github.tehstoneman.cashcraft.ModInfo;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class CashCraftCreativeModeTab extends CreativeModeTab
{

	public CashCraftCreativeModeTab()
	{
		super( ModInfo.MOD_ID );
	}

	@Override
	public ItemStack makeIcon()
	{
		return new ItemStack( CashCraftItems.COIN_ONE.get() );
	}

}
