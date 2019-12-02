package io.github.tehstoneman.cashcraft.common.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerMoneyPouch extends Container
{
	private final InventoryPlayer	inventoryPlayer;
	// private final ItemStackHandler inventoryMoneyPouch;
	private final int				protectedIndex;

	private final int				indexStart, indexPlayer;
	private int						indexHotbar;

	public ContainerMoneyPouch( EntityPlayer player, ItemStack moneyPouch, int protectedIndex )
	{
		inventoryPlayer = player.inventory;
		// inventoryMoneyPouch = (ItemStackHandler)moneyPouch.getCapability( CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null );
		this.protectedIndex = protectedIndex;

		indexStart = 0;
		// indexHotbar = inventoryMoneyPouch.getSlots();
		indexPlayer = indexHotbar + 9;

		for( int i = 0; i < indexHotbar; i++ )
		{
			final int x = i % 5 * 18 + 44;
			final int y = i / 5 * 18 + 20;
			// addSlotToContainer( new CoinSlotHandler( inventoryMoneyPouch, i, x, y ) );
		}

		for( int i = 0; i < 27; i++ )
		{
			final int x = i % 9 * 18 + 8;
			final int y = 87 + i / 9 * 18;
			// addSlotToContainer( new Slot( inventoryPlayer, i + 9, x, y ) );
		}

		for( int i = 0; i < 9; i++ )
		{
			final int x = i % 9 * 18 + 8;
			final int y = 145;
			// addSlotToContainer( new Slot( inventoryPlayer, i, x, y ) );
		}
	}

	@Override
	public ItemStack slotClick( int slotId, int dragType, ClickType clickTypeIn, EntityPlayer player )
	{
		if( slotId >= 0 && getSlot( slotId ) != null && getSlot( slotId ).getStack() == player.getHeldItemMainhand() )
			return ItemStack.EMPTY;
		return super.slotClick( slotId, dragType, clickTypeIn, player );
	}

	@Override
	public boolean canInteractWith( EntityPlayer playerIn )
	{
		return true;
	}

	@Override
	public ItemStack transferStackInSlot( EntityPlayer playerIn, int index )
	{
		final Slot slot = inventorySlots.get( index );
		ItemStack returnStack = ItemStack.EMPTY;

		if( slot != null && slot.getHasStack() )
		{
			final ItemStack itemStack = slot.getStack();
			returnStack = itemStack.copy();

			if( index < indexHotbar )
			{
				// Try to transfer from container to player
				if( !mergeItemStack( itemStack, indexHotbar, inventorySlots.size(), true ) )
					return ItemStack.EMPTY;
			}
			else
				if( !mergeItemStack( itemStack, 0, indexHotbar, false ) )
					return ItemStack.EMPTY;

			if( itemStack.isEmpty() )
				slot.putStack( ItemStack.EMPTY );
			else
				slot.onSlotChanged();
		}

		return returnStack;
	}
}
