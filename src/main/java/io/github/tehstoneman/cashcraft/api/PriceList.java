package io.github.tehstoneman.cashcraft.api;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class PriceList
{
	private HashMap< ItemStack, Integer > pricelist;
	
	/**
	 * Get the size of the list
	 * 
	 * @return
	 */
	public int getSize()
	{
		return pricelist.size();
	}

	/**
	 * Get the price of an item
	 *
	 * @param itemStack
	 * @return The price of the item, or 0 if not in list
	 */
	public int getPriceOfItem( ItemStack itemStack )
	{
		return pricelist.getOrDefault( itemStack, 0 );
	}

	/**
	 * Add or change the price of an item
	 *
	 * @param itemStack
	 * @param price
	 */
	public void setPriceOfItem( ItemStack itemStack, int price )
	{
		pricelist.put( itemStack, price );
	}

	/**
	 * Check if an item is in the price list
	 *
	 * @param itemStack
	 * @return true if item is in list
	 */
	public boolean isItemListed( ItemStack itemStack )
	{
		return pricelist.containsKey( itemStack );
	}

	/**
	 * Get a sorted view of the pricelist
	 *
	 * @return SortedMap
	 */
	public SortedMap getSortedList()
	{
		return new TreeMap< ItemStack, Integer >( new ItemByPrice( pricelist ) );
	}

	/**
	 * Remove an item from the price list
	 *
	 * @param itemStack
	 */
	public void removeItemFromList( ItemStack itemStack )
	{
		pricelist.remove( itemStack );
	}

	/**
	 * Save the price list to NBT
	 *
	 * @param compound
	 */
	public void writeToNBT( NBTTagCompound compound )
	{
		if( !pricelist.isEmpty() )
		{
			final NBTTagList nbttaglist = new NBTTagList();
			for( final Entry< ItemStack, Integer > price : pricelist.entrySet() )
			{
				final NBTTagCompound nbttagcompound = new NBTTagCompound();
				price.getKey().writeToNBT( nbttagcompound );
				nbttagcompound.setInteger( "Price", price.getValue() );
				nbttaglist.appendTag( nbttagcompound );

			}
			compound.setTag( "PriceList", nbttaglist );
		}
	}

	/**
	 * Load the price list from NBT
	 *
	 * @param compound
	 */
	public void readFromNBT( NBTTagCompound compound )
	{
		pricelist.clear();
		if( compound.hasKey( "PriceList", 10 ) )
		{
			final NBTTagList nbttaglist = compound.getTagList( "PriceList", 10 );
			for( int i = 0; i < nbttaglist.tagCount(); ++i )
			{
				final NBTTagCompound nbttagcompound = nbttaglist.getCompoundTagAt( i );
				final ItemStack itemStack = ItemStack.loadItemStackFromNBT( nbttagcompound );
				final int price = nbttagcompound.getInteger( "Price" );
				pricelist.put( itemStack, price );

			}
		}
	}

	private class ItemByPrice implements Comparator< ItemStack >
	{
		private final Map< ItemStack, Integer > map;

		public ItemByPrice( Map< ItemStack, Integer > map )
		{
			this.map = map;
		}

		@Override
		public int compare( ItemStack arg0, ItemStack arg1 )
		{
			int result = map.get( arg0 ).compareTo( map.get( arg1 ) );
			if( result == 0 )
				result = arg0.getDisplayName().compareTo( arg1.getDisplayName() );
			return result;
		}
	}
}
