package io.github.tehstoneman.cashcraft.common.item;

import io.github.tehstoneman.cashcraft.CashCraft;
import io.github.tehstoneman.cashcraft.ModInfo;
import net.minecraft.item.Item;

public class ItemCashCraft extends Item
{
	public static ItemCash			COIN		= new ItemCash();
	public static ItemMoneyPouch	MONEY_POUCH	= new ItemMoneyPouch();

	private final String			name;

	ItemCashCraft( String name )
	{
		this.name = name;
		setCreativeTab( CashCraft.creativeTab );
		setUnlocalizedName( ModInfo.MODID + "." + name );
	}
	
	public String getName()
	{
		return name;
	}
}
