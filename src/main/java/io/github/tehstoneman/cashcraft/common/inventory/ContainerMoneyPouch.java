package io.github.tehstoneman.cashcraft.common.inventory;

import io.github.tehstoneman.cashcraft.common.item.ItemCash;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;

public class ContainerMoneyPouch extends Container
{
	private static final int			INV_START		= InventoryMoneyPouch.INV_SIZE;
	private static final int			INV_END			= INV_START + 26;
	private static final int			HOTBAR_START	= INV_END + 1;
	private static final int			HOTBAR_END		= HOTBAR_START + 8;

	private final InventoryPlayer		inventoryPlayer;
	private final InventoryMoneyPouch	inventoryMoneyPouch;

	public ContainerMoneyPouch( IInventory inventoryPlayer, IInventory inventoryMoneyPouch, EntityPlayer player )
	{
		this.inventoryPlayer = (InventoryPlayer)inventoryPlayer;
		this.inventoryMoneyPouch = (InventoryMoneyPouch)inventoryMoneyPouch;

		for( int i = 0; i < INV_START; i++ )
		{
			final int x = i % 5;
			final int y = i / 5;
			addSlotToContainer( new SlotMoneyPouch( inventoryMoneyPouch, i, 44 + 18 * x, 20 + 18 * y ) );
		}

		for( int y = 0; y < 3; y++ )
			for( int x = 0; x < 9; x++ )
				addSlotToContainer( new Slot( inventoryPlayer, x + y * 9 + 9, 8 + x * 18, 87 + y * 18 ) );

		for( int i = 0; i < 9; i++ )
			addSlotToContainer( new Slot( inventoryPlayer, i, 8 + i * 18, 145 ) );
	}

	@Override
	public boolean canInteractWith( EntityPlayer playerIn )
	{
		return inventoryMoneyPouch.isUsableByPlayer( playerIn );
	}

	@Override
	public ItemStack transferStackInSlot( EntityPlayer playerIn, int index )
	{
		ItemStack itemStack = null;
		final Slot slot = inventorySlots.get( index );

		if( slot != null && slot.getHasStack() )
		{
			final ItemStack itemStack1 = slot.getStack();
			itemStack = itemStack1.copy();

			// If item is in the moneypouch
			if( index < INV_START )
			{
				// Try to place in player inventory
				if( !mergeItemStack( itemStack1, INV_START, HOTBAR_END + 1, true ) )
					return null;

				slot.onSlotChange( itemStack1, itemStack );
			}
			else
			{
				// If item is a CashCraft coin or note, try to place in money pouch
				boolean result = false;
				if( itemStack1.getItem() instanceof ItemCash )
					result = mergeItemStack( itemStack1, 0, inventoryMoneyPouch.getSizeInventory(), false );

				// Otherwise swap between player inventory and hotbar
				if( !result )
					if( index >= INV_START && index <= INV_END )
					{
						if( !mergeItemStack( itemStack1, HOTBAR_START, HOTBAR_END + 1, false ) )
							return null;
					}
					else
						if( index >= HOTBAR_START && index <= HOTBAR_END )
							if( !mergeItemStack( itemStack1, INV_START, INV_END + 1, false ) )
								return null;
			}

			if( itemStack1.getCount() == 0 )
				slot.putStack( (ItemStack)null );
			else
				slot.onSlotChanged();

			if( itemStack1.getCount() == itemStack.getCount() )
				return null;

			//slot.onPickupFromSlot( playerIn, itemStack1 );
		}
		return itemStack;
	}

	@Override
	public ItemStack slotClick( int slotId, int dragType, ClickType clickTypeIn, EntityPlayer player )
	{
		// This will prevent the player interacting with the moneypouch that opened this inventory
		if( slotId >= 0 && getSlot( slotId ) != null && getSlot( slotId ).getStack() == player.getHeldItem( EnumHand.MAIN_HAND ) )
			return null;
		return super.slotClick( slotId, dragType, clickTypeIn, player );
	}

	public class SlotMoneyPouch extends Slot
	{
		public SlotMoneyPouch( IInventory inventoryIn, int index, int xPosition, int yPosition )
		{
			super( inventoryIn, index, xPosition, yPosition );
		}

		@Override
		public boolean isItemValid( ItemStack stack )
		{
			// Can only accept CashCraft coins and notes
			return stack.getItem() instanceof ItemCash;
		}
	}
}
