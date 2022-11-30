package io.github.tehstoneman.cashcraft.economy;

import io.github.tehstoneman.cashcraft.api.IEcomomy;
import io.github.tehstoneman.cashcraft.config.CashCraftConfig;
import io.github.tehstoneman.cashcraft.world.item.CashItem;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class Economy implements IEcomomy
{
	@Override
	public ItemStack getCash( long amount )
	{
		// Find largest cash value that is less than amount
		/*
		 * int i = EnumCoinValue.values().length - 1;
		 * while( EnumCoinValue.byMetadata( i ).getValue() > amount )
		 * i--;
		 */

		// Calculate how many coins/notes of value fit in amount
		// final int count = (int)( amount / EnumCoinValue.byMetadata( i ).getValue() );

		// return new ItemStack( CashCraftItems.COIN_ONE, count );
		return ItemStack.EMPTY;
	}

	@Override
	public String getCurrencyName( boolean plural, boolean longFormat )
	{
		if( plural )
		{
			if( CashCraftConfig.useCustomName.get() )
				return CashCraftConfig.cashPlural.get();
			if( longFormat )
			{
				if( CashCraftConfig.showAsCoins.get() )
					return "economy.cashcraft.coin_plural.long";
				return "economy.cashcraft.cash_plural.long";
			}
			if( CashCraftConfig.showAsCoins.get() )
				return "economy.cashcraft.coin_plural.short";
			return "economy.cashcraft.cash_plural.short";
		}
		if( CashCraftConfig.useCustomName.get() )
			return CashCraftConfig.cashSingular.get();
		if( longFormat )
		{
			if( CashCraftConfig.showAsCoins.get() )
				return "economy.cashcraft.coin_singular.long";
			return "economy.cashcraft.cash_singular.long";
		}
		if( CashCraftConfig.showAsCoins.get() )
			return "economy.cashcraft.coin_singular.short";
		return "economy.cashcraft.cash_singular.short";
	}

	@Override
	public long getValue( ItemStack itemStack )
	{
		final Item item = itemStack.getItem();
		if( item instanceof CashItem )
		{
			final int	count	= itemStack.getCount();
			final int	value	= ( (CashItem)item ).getValue();

			return count * value;
		}
		return 0;
	}

	@Override
	public PlayerWallet getWallet( Player player )
	{
		if( player != null )
			return new PlayerWallet( player );
		return null;
	}

	@Override
	public Boolean isEnabled()
	{
		return CashCraftConfig.useEconomy.get();
	}

	@Override
	public String toString( long amount )
	{
		return toString( amount, false );
	}

	@Override
	public String toString( long amount, boolean longFormat )
	{
		if( CashCraftConfig.showAsCoins.get() )
			return I18n.get( getCurrencyName( amount != 1, longFormat ), String.format( "%d", amount ) );
		final long	numerator	= amount / 100;
		final long	demoninator	= amount % 100;
		return I18n.get( getCurrencyName( numerator != 1, longFormat ), String.format( "%d.%02d", numerator, demoninator ) );
	}
}
