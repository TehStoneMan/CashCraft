package io.github.tehstoneman.cashcraft.economy;

import io.github.tehstoneman.cashcraft.api.IEcomomy;
import io.github.tehstoneman.cashcraft.item.CashCraftItems;
import io.github.tehstoneman.cashcraft.item.ItemCash.EnumCoinValue;
import io.github.tehstoneman.cashcraft.util.ModSettings;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class Economy implements IEcomomy
{
	@Override
	public Boolean isEnabled()
	{
		return ModSettings.useEconomy;
	}

	@Override
	public PlayerWallet getWallet( EntityPlayer player )
	{
		if( player != null )
			return new PlayerWallet( player );
		return null;
	}

	@Override
	public String currency( long amount, boolean longFormat )
	{
		if( amount == 1 )
			if( ModSettings.cashSingular == "" )
				if( longFormat )
					if( ModSettings.showAsCoins )
						return StatCollector.translateToLocal( "economy.cashCraft.coinSingular.long" );
					else
						return StatCollector.translateToLocal( "economy.cashCraft.cashSingular.long" );
				else
					if( ModSettings.showAsCoins )
						return StatCollector.translateToLocal( "economy.cashCraft.coinSingular.short" );
					else
						return StatCollector.translateToLocal( "economy.cashCraft.cashSingular.short" );
			else
				return ModSettings.cashSingular;
		else
			if( ModSettings.cashPlural == "" )
				if( longFormat )
					if( ModSettings.showAsCoins )
						return StatCollector.translateToLocal( "economy.cashCraft.coinPlural.long" );
					else
						return StatCollector.translateToLocal( "economy.cashCraft.cashPlural.long" );
				else
					if( ModSettings.showAsCoins )
						return StatCollector.translateToLocal( "economy.cashCraft.coinPlural.short" );
					else
						return StatCollector.translateToLocal( "economy.cashCraft.cashPlural.short" );
			else
				return ModSettings.cashPlural;
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

		return new ItemStack( CashCraftItems.itemCoin, count, i );
	}

	@Override
	public long getValue( ItemStack itemStack )
	{
		final int count = itemStack.stackSize;
		final int value = EnumCoinValue.byMetadata( itemStack.getItemDamage() ).getValue();

		return count * value;
	}

	@Override
	public String toString( long amount )
	{
		return toString( amount, false );
	}

	@Override
	public String toString( long amount, boolean longFormat )
	{
		if( ModSettings.showAsCoins )
			return Long.toString( amount ) + " " + currency( amount, longFormat );
		else
		{
			long numerator = amount / 128;
			long demoninator = amount % 128;
			return Long.toString( numerator ) + "." + Long.toString( demoninator ) + " " + currency( numerator, longFormat );
		}
	}
}
