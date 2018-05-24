package io.github.tehstoneman.cashcraft.client.creativetab;

import java.util.List;

import io.github.tehstoneman.cashcraft.ModInfo;
import io.github.tehstoneman.cashcraft.common.item.CashCraftItems;
import io.github.tehstoneman.cashcraft.common.item.ItemCash.EnumCoinValue;
import io.github.tehstoneman.cashcraft.common.item.ItemCashCraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

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
