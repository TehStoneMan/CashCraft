package io.github.tehstoneman.cashcraft.common.inventory;

import io.github.tehstoneman.cashcraft.api.CashCraftAPI;
import io.github.tehstoneman.cashcraft.common.item.ItemCash;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.Constants;

public class InventoryMoneyPouch extends InventoryBasic
{
	public static final int	INV_SIZE	= 15;
	private final ItemStack	invItem;

	public InventoryMoneyPouch( ItemStack heldItem )
	{
		super( "container.cashcraft.money_pouch", false, INV_SIZE );
		invItem = heldItem;
		if( !invItem.hasTagCompound() )
			invItem.setTagCompound( new NBTTagCompound() );

		readFromNBT( invItem.getTagCompound() );
	}

	@Override
	public void markDirty()
	{
		super.markDirty();
		writeToNBT( invItem.getTagCompound() );
	}

	@Override
	public boolean isItemValidForSlot( int index, ItemStack stack )
	{
		return stack.getItem() instanceof ItemCash;
	}

	public void readFromNBT( NBTTagCompound compound )
	{
		final NBTTagList items = compound.getTagList( "ItemInventory", Constants.NBT.TAG_COMPOUND );

		for( int i = 0; i < items.tagCount(); ++i )
		{
			final NBTTagCompound item = items.getCompoundTagAt( i );
			final int slot = item.getInteger( "Slot" );

			if( slot >= 0 && slot < getSizeInventory() )
				setInventorySlotContents( slot, ItemStack.loadItemStackFromNBT( item ) );
		}
	}

	public void writeToNBT( NBTTagCompound compound )
	{
		final NBTTagList items = new NBTTagList();

		for( int i = 0; i < getSizeInventory(); ++i )
			if( getStackInSlot( i ) != null )
			{
				final NBTTagCompound item = new NBTTagCompound();
				item.setInteger( "Slot", i );
				getStackInSlot( i ).writeToNBT( item );
				items.appendTag( item );
			}

		compound.setTag( "ItemInventory", items );
		compound.setLong( "Value", getValue() );
	}
	
	public long getValue()
	{
		long value = 0;
		for( int i = 0; i < getSizeInventory(); ++i )
			if( getStackInSlot( i ) != null )
				value += CashCraftAPI.economy.getValue( getStackInSlot( i ) );
		return value;
	}
}
