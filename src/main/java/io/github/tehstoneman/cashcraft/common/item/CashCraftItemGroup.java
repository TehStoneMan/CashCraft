package io.github.tehstoneman.cashcraft.common.item;

import io.github.tehstoneman.cashcraft.ModInfo;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class CashCraftItemGroup extends ItemGroup
{
	public CashCraftItemGroup()
	{
		super( ModInfo.MOD_ID );
	}

	@Override
	public ItemStack createIcon()
	{
		return new ItemStack( CashCraftItems.COIN_ONE );
	}
}
