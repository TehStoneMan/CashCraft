package io.github.tehstoneman.cashcraft.common.item;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class CashCraftItemGroup extends ItemGroup
{
	public CashCraftItemGroup()
	{
		super( "cash_craft" );
	}

	@Override
	public ItemStack createIcon()
	{
		return new ItemStack( CashCraftItems.COIN_ONE );
	}
}
