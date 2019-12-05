package io.github.tehstoneman.cashcraft.common.item;

public class ItemCash extends ItemCashCraft
{
	private final int value;

	public ItemCash( int value )
	{
		this.value = value;
	}

	public int getValue()
	{
		return value;
	}
}
