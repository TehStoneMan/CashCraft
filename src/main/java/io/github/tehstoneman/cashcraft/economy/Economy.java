package io.github.tehstoneman.cashcraft.economy;

import io.github.tehstoneman.cashcraft.api.IEcomomy;
import io.github.tehstoneman.cashcraft.common.item.ItemCash;
import io.github.tehstoneman.cashcraft.config.CashCraftConfig;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class Economy implements IEcomomy
{
	@Override
	public Boolean isEnabled()
	{
		return CashCraftConfig.useEconomy;
	}

	@Override
	public PlayerWallet getWallet( PlayerEntity player )
	{
		if( player != null )
			return new PlayerWallet( player );
		return null;
	}

	@Override
	public String getCurrencyName( boolean plural, boolean longFormat )
	{
		if( plural )
			if( !CashCraftConfig.useCustomName )
				if( longFormat )
					if( CashCraftConfig.showAsCoins )
						return "economy.cashcraft.coin_plural.long";
					else
						return "economy.cashcraft.cash_plural.long";
				else if( CashCraftConfig.showAsCoins )
					return "economy.cashcraft.coin_plural.short";
				else
					return "economy.cashcraft.cash_plural.short";
			else
				return CashCraftConfig.cashPlural;
		else if( !CashCraftConfig.useCustomName )
			if( longFormat )
				if( CashCraftConfig.showAsCoins )
					return "economy.cashcraft.coin_singular.long";
				else
					return "economy.cashcraft.cash_singular.long";
			else if( CashCraftConfig.showAsCoins )
				return "economy.cashcraft.coin_singular.short";
			else
				return "economy.cashcraft.cash_singular.short";
		else
			return CashCraftConfig.cashSingular;
	}

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
	public long getValue( ItemStack itemStack )
	{
		final Item item = itemStack.getItem();
		if( item instanceof ItemCash )
		{
			final int count = itemStack.getCount();
			final int value = ( (ItemCash)item ).getValue();

			return count * value;
		}
		return 0;
	}

	@Override
	public String toString( long amount )
	{
		return toString( amount, false );
	}

	@Override
	public String toString( long amount, boolean longFormat )
	{
		if( CashCraftConfig.showAsCoins )
			return I18n.format( getCurrencyName( amount != 1, longFormat ), String.format( "%d", amount ) );
		else
		{
			final long numerator = amount / 100;
			final long demoninator = amount % 100;
			return I18n.format( getCurrencyName( numerator != 1, longFormat ), String.format( "%d.%02d", numerator, demoninator ) );
		}
	}
}
