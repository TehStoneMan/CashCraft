package io.github.tehstoneman.cashcraft.economy;

import io.github.tehstoneman.cashcraft.api.IEcomomy;
import io.github.tehstoneman.cashcraft.common.item.CashCraftItems;
import io.github.tehstoneman.cashcraft.common.item.ItemCash.EnumCoinValue;
import io.github.tehstoneman.cashcraft.util.ModSettings;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

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
	public String getCurrencyName( boolean plural, boolean longFormat )
	{
		if( plural )
			if( !ModSettings.useCustomName )
				if( longFormat )
					if( ModSettings.showAsCoins )
						return "economy.cashcraft.coinplural.long";
					else
						return "economy.cashcraft.cashplural.long";
				else
					if( ModSettings.showAsCoins )
						return "economy.cashcraft.coinplural.short";
					else
						return "economy.cashcraft.cashplural.short";
			else
				return ModSettings.cashPlural;
		else
			if( !ModSettings.useCustomName )
				if( longFormat )
					if( ModSettings.showAsCoins )
						return "economy.cashcraft.coinsingular.long";
					else
						return "economy.cashcraft.cashsingular.long";
				else
					if( ModSettings.showAsCoins )
						return "economy.cashcraft.coinsingular.short";
					else
						return "economy.cashcraft.cashsingular.short";
			else
				return ModSettings.cashSingular;
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
			return I18n.format(  getCurrencyName( amount != 1, longFormat ), Long.toString( amount ) );
		else
		{
			long numerator = amount / 100;
			long demoninator = amount % 100;
			return I18n.format( getCurrencyName( numerator != 1, longFormat ), Long.toString( numerator ) + "." + Long.toString( demoninator ) );
		}
	}
}
