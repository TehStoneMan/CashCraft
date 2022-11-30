package io.github.tehstoneman.cashcraft.world.item;

import net.minecraft.world.item.Item;

public class CashItem extends CashCraftItem
{
	private final int value;

	public CashItem( int value )
	{
		super( new Item.Properties() );
		this.value = value;
	}

	public int getValue()
	{
		return value;
	}
}
