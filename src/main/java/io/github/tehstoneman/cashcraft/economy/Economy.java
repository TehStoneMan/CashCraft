package io.github.tehstoneman.cashcraft.economy;

import io.github.tehstoneman.cashcraft.api.IEcomomy;
import io.github.tehstoneman.cashcraft.common.item.CashCraftItems;
import io.github.tehstoneman.cashcraft.common.item.ItemCash.EnumCoinValue;
import io.github.tehstoneman.cashcraft.util.ModSettings;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentTranslation;

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
	public String getCurrencyName( long amount, boolean longFormat )
	{
		if( amount == 1 )
			//if( ModSettings.cashSingular == "" )
				if( longFormat )
					if( ModSettings.showAsCoins )
						return new TextComponentTranslation( "economy.cashCraft.coinSingular.long", new Object[0] ).getUnformattedText();
					else
						return new TextComponentTranslation( "economy.cashCraft.cashSingular.long", new Object[0] ).getUnformattedText();
				else
					if( ModSettings.showAsCoins )
						return new TextComponentTranslation( "economy.cashCraft.coinSingular.short", new Object[0] ).getUnformattedText();
					else
						return new TextComponentTranslation( "economy.cashCraft.cashSingular.short", new Object[0] ).getUnformattedText();
			//else
				//return ModSettings.cashSingular;
		else
			//if( ModSettings.cashPlural == "" )
				if( longFormat )
					if( ModSettings.showAsCoins )
						return new TextComponentTranslation( "economy.cashCraft.coinPlural.long", new Object[0] ).getUnformattedText();
					else
						return new TextComponentTranslation( "economy.cashCraft.cashPlural.long", new Object[0] ).getUnformattedText();
				else
					if( ModSettings.showAsCoins )
						return new TextComponentTranslation( "economy.cashCraft.coinPlural.short", new Object[0] ).getUnformattedText();
					else
						return new TextComponentTranslation( "economy.cashCraft.cashPlural.short", new Object[0] ).getUnformattedText();
			//else
				//return ModSettings.cashPlural;
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
		return toString( amount, true );
	}

	@Override
	public String toString( long amount, boolean longFormat )
	{
		if( ModSettings.showAsCoins )
			return String.format( "%d %s", amount, getCurrencyName( amount, longFormat ) );
		else
		{
			long numerator = amount / 100;
			long demoninator = amount % 100;
			return String.format( "%d.%02d %s", numerator, demoninator, getCurrencyName( numerator, longFormat ) );
		}
	}
}
