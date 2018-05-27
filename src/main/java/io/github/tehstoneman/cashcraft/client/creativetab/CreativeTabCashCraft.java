package io.github.tehstoneman.cashcraft.client.creativetab;

import io.github.tehstoneman.cashcraft.ModInfo;
import io.github.tehstoneman.cashcraft.common.item.ItemCashCraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class CreativeTabCashCraft extends CreativeTabs
{

	public CreativeTabCashCraft()
	{
		super( ModInfo.MODID );
	}

	@Override
	public ItemStack getTabIconItem()
	{
		return new ItemStack( ItemCashCraft.COIN );
	}
}
