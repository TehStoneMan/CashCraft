package io.github.tehstoneman.cashcraft.economy;

import io.github.tehstoneman.cashcraft.api.CashCraftAPI;
import io.github.tehstoneman.cashcraft.api.IPlayerWallet;
import io.github.tehstoneman.cashcraft.common.item.ItemCash.EnumCoinValue;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;

class PlayerWallet implements IPlayerWallet
{
	private long				amount;
	private final PlayerEntity	player;

	PlayerWallet( PlayerEntity player )
	{
		amount = 0;

		// Scan through player's inventory and count cash values
		final PlayerInventory inventory = player.inventory;

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
			// final EntityItem entityitem = player.dropPlayerItemWithRandomChoice( stackCash, false );
			// entityitem.delayBeforeCanPickup = 0;
			value -= CashCraftAPI.economy.getValue( stackCash );
		}
	}

	@Override
	public boolean withdraw( long value )
	{
		if( amount < value )
			return false;

		final int cashValue = EnumCoinValue.values().length;
		/*
		 * while( cashValue > 0 )
		 * {
		 * for( int i = 0; i < player.inventory.mainInventory.size(); i++ )
		 * {
		 * final ItemStack itemStack = player.inventory.mainInventory.get( i );
		 * if( itemStack != null && itemStack.getItem() == CashCraftItems. && itemStack.getItemDamage() == cashValue - 1 )
		 * {
		 * int count = (int)( value / EnumCoinValue.byMetadata( cashValue - 1 ).getValue() );
		 * if( itemStack.getCount() <= count )
		 * {
		 * count = itemStack.getCount();
		 * player.inventory.mainInventory.set( i, ItemStack.EMPTY );
		 * }
		 * else
		 * player.inventory.decrStackSize( i, count );
		 * value -= EnumCoinValue.byMetadata( cashValue - 1 ).getValue() * count;
		 * }
		 * }
		 * cashValue--;
		 * }
		 */
		return false;
	}

	@Override
	public void empty()
	{
		// Loop through player's inventory and remove cash items
		final PlayerInventory inventory = player.inventory;

		/*
		 * for( int i = 0; i < inventory.mainInventory.size(); i++ )
		 * {
		 * final ItemStack itemStack = inventory.mainInventory.get( i );
		 * if( itemStack != null && itemStack.getItem() == ItemCashCraft.COIN )
		 * inventory.mainInventory.set( i, ItemStack.EMPTY );
		 * }
		 */

		amount = 0;
	}

	@Override
	public String toString()
	{
		return CashCraftAPI.economy.toString( amount );
	}
}
