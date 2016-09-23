package io.github.tehstoneman.cashcraft.common.inventory;

import java.util.List;
import java.util.Set;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;

import io.github.tehstoneman.cashcraft.api.PriceList;
import io.github.tehstoneman.cashcraft.client.gui.GuiVender;
import io.github.tehstoneman.cashcraft.common.tileentity.TileEntityVender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerVender extends Container
{
	private final IInventory	inventoryVender, inventoryPlayer;
	public List< ItemStack >	itemList	= Lists.<ItemStack> newArrayList();

	public ContainerVender( IInventory inventoryPlayer, IInventory inventoryVender, EntityPlayer player )
	{
		this.inventoryVender = inventoryVender;
		this.inventoryPlayer = inventoryPlayer;

		int x, y;

		for( y = 0; y < 4; ++y )
			addSlotToContainer( new PriceSlot( GuiVender.inventoryBasic, y, 8, 18 + y * 18 ) );

		for( int i = 0; i < 16; ++i )
		{
			x = i % 4;
			y = i / 4;
			addSlotToContainer( new Slot( inventoryVender, i, 80 + x * 18, 18 + y * 18 ) );
		}

		// Add player inventory to GUI
		for( y = 0; y < 3; ++y )
			for( x = 0; x < 9; ++x )
				addSlotToContainer( new Slot( inventoryPlayer, x + y * 9 + 9, 8 + x * 18, 140 + y * 18 ) );

		// Add player hotbar to GUI
		for( x = 0; x < 9; ++x )
			addSlotToContainer( new Slot( inventoryPlayer, x, 8 + x * 18, 198 ) );
	}

	@Override
	public boolean canInteractWith( EntityPlayer playerIn )
	{
		return inventoryVender.isUseableByPlayer( playerIn );
	}

	@Override
	public ItemStack transferStackInSlot( EntityPlayer playerIn, int index )
	{
		ItemStack itemstack = null;
		final Slot slot = inventorySlots.get( index );

		if( slot != null && slot.getHasStack() )
		{
			final ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			if( index >= GuiVender.inventoryBasic.getSizeInventory()
					&& index < inventoryVender.getSizeInventory() + GuiVender.inventoryBasic.getSizeInventory() )
			{
				if( !mergeItemStack( itemstack1, inventoryVender.getSizeInventory() + GuiVender.inventoryBasic.getSizeInventory(),
						inventorySlots.size(), true ) )
					return null;
			}
			else
				if( index < GuiVender.inventoryBasic.getSizeInventory() || !mergeItemStack( itemstack1, GuiVender.inventoryBasic.getSizeInventory(),
						inventoryVender.getSizeInventory() + GuiVender.inventoryBasic.getSizeInventory(), false ) )
					return null;

			if( itemstack1.stackSize == 0 )
				slot.putStack( (ItemStack)null );
			else
				slot.onSlotChanged();

			if( itemstack1.stackSize == itemstack.stackSize )
				return null;

			slot.onPickupFromSlot( playerIn, itemstack1 );
		}

		return itemstack;
	}
}
