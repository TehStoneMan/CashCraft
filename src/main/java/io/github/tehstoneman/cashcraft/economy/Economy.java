package io.github.tehstoneman.cashcraft.economy;

import io.github.tehstoneman.cashcraft.api.IEcomomy;
import io.github.tehstoneman.cashcraft.item.CashItems;
import io.github.tehstoneman.cashcraft.item.ItemCash;
import io.github.tehstoneman.cashcraft.item.ItemCash.EnumCoinValue;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class Economy implements IEcomomy
{
	@Override
	public PlayerWallet getWallet( EntityPlayer player )
	{
		if( player != null )
			return new PlayerWallet( player );
		return null;
	}

	@Override
	public String currency( long amount )
	{
		if( amount == 1 )
			return "cash";
		else
			return "cash";
	}

	@Override
	public ItemStack getCash( long amount )
	{
		// Find largest cash value that is less than amount
		int i = EnumCoinValue.values().length - 1;
		while( EnumCoinValue.byMetadata( i ).getValue() > amount )
			i--;

		// Calculate how many coins/notes of value fit in amount
		final int count = (int)( amount / EnumCoinValue.byMetadata( i ).getValue() );

		return new ItemStack( CashItems.itemCoin, count, i );
	}

	@Override
	public long getValue( ItemStack itemStack )
	{
		if( itemStack == null || itemStack.getItem() != CashItems.itemCoin )
			return 0;

		final int count = itemStack.stackSize;
		final int value = EnumCoinValue.byMetadata( itemStack.getItemDamage() ).getValue();

		return count * value;
	}

	@Override
	public String toString( long amount )
	{
		return Long.toString( amount ) + " " + currency( amount );
	}
}
