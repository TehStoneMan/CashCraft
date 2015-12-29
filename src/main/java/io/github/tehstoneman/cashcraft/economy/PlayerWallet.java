package io.github.tehstoneman.cashcraft.economy;

import io.github.tehstoneman.cashcraft.api.CashCraftAPI;
import io.github.tehstoneman.cashcraft.api.IPlayerWallet;
import io.github.tehstoneman.cashcraft.item.CashItems;
import io.github.tehstoneman.cashcraft.item.ItemCash.EnumCoinValue;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;

public class PlayerWallet implements IPlayerWallet
{
	private long				amount;
	private final EntityPlayer	player;

	public PlayerWallet( EntityPlayer player )
	{
		amount = 0;

		// Scan through player's inventory and count cash values
		final InventoryPlayer inventory = player.inventory;

		for( final ItemStack itemStack : inventory.mainInventory )
			amount += CashCraftAPI.economy.getValue( itemStack );

		this.player = player;
	}

	@Override
	public long getValue()
	{
		return amount;
	}

	@Override
	public void setValue( long value )
	{
		empty();
		deposit( value );
	}

	@Override
	public void deposit( long value )
	{
		amount += value;
		while( value > 0 )
		{
			final ItemStack stackCash = CashCraftAPI.economy.getCash( value );
			final EntityItem entityitem = player.dropPlayerItemWithRandomChoice( stackCash, false );
			entityitem.delayBeforeCanPickup = 0;
			value -= CashCraftAPI.economy.getValue( stackCash );
		}
	}

	@Override
	public boolean withdraw( long value )
	{
		if( amount < value )
			return false;

		int cashValue = EnumCoinValue.values().length;
		while( cashValue > 0 )
		{
			for( int i = 0; i < player.inventory.mainInventory.length; i++ )
			{
				final ItemStack itemStack = player.inventory.mainInventory[i];
				if( itemStack != null && itemStack.getItem() == CashItems.itemCoin && itemStack.getItemDamage() == cashValue - 1 )
				{
					int count = (int)( value / EnumCoinValue.byMetadata( cashValue - 1 ).getValue() );
					if( itemStack.stackSize <= count )
					{
						count = itemStack.stackSize;
						player.inventory.mainInventory[i] = null;
					}
					else
						player.inventory.mainInventory[i].stackSize -= count;
					value -= EnumCoinValue.byMetadata( cashValue - 1 ).getValue() * count;
				}
			}
			cashValue--;
		}
		return false;
	}

	@Override
	public void empty()
	{
		// Loop through player's inventory and remove cash items
		final InventoryPlayer inventory = player.inventory;

		for( int i = 0; i < inventory.mainInventory.length; i++ )
		{
			final ItemStack itemStack = inventory.mainInventory[i];
			if( itemStack != null && itemStack.getItem() == CashItems.itemCoin )
				inventory.mainInventory[i] = null;
		}

		amount = 0;
	}

	@Override
	public String toString()
	{
		return CashCraftAPI.economy.toString( amount );
	}
}
