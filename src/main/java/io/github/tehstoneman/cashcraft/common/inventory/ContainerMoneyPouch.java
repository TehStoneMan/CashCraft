package io.github.tehstoneman.cashcraft.common.inventory;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.ClickType;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class ContainerMoneyPouch extends Container
{
	// private final PlayerInventory inventoryPlayer;
	private final ItemStackHandler	inventoryMoneyPouch;
	private final int				protectedIndex;

	private final int				indexStart, indexPlayer;
	private final int				indexHotbar;

	public ContainerMoneyPouch( int windowID, PlayerInventory inventoryPlayer, ItemStack moneyPouch, int protectedIndex )
	{
		super( CashCraftContainerTypes.MONEY_POUCH, windowID );
		inventoryMoneyPouch = (ItemStackHandler)moneyPouch.getCapability( CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null ).orElse( null );
		this.protectedIndex = protectedIndex;

		indexStart = 0;
		indexHotbar = inventoryMoneyPouch.getSlots();
		indexPlayer = indexHotbar + 9;

		for( int i = 0; i < indexHotbar; i++ )
		{
			final int x = i % 5 * 18 + 44;
			final int y = i / 5 * 18 + 20;
			addSlot( new CoinSlotHandler( inventoryMoneyPouch, i, x, y ) );
		}

		for( int i = 0; i < 27; i++ )
		{
			final int x = i % 9 * 18 + 8;
			final int y = 87 + i / 9 * 18;
			addSlot( new Slot( inventoryPlayer, i + 9, x, y ) );
		}

		for( int i = 0; i < 9; i++ )
		{
			final int x = i % 9 * 18 + 8;
			final int y = 145;
			addSlot( new Slot( inventoryPlayer, i, x, y ) );
		}
	}

	@Override
	public ItemStack slotClick( int slotId, int dragType, ClickType clickTypeIn, PlayerEntity player )
	{
		if( slotId >= 0 && getSlot( slotId ) != null && getSlot( slotId ).getStack() == player.getHeldItemMainhand() )
			return ItemStack.EMPTY;
		return super.slotClick( slotId, dragType, clickTypeIn, player );
	}

	@Override
	public boolean canInteractWith( PlayerEntity playerIn )
	{
		return true;
	}

	@Override
	public ItemStack transferStackInSlot( PlayerEntity playerIn, int index )
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
			else if( !mergeItemStack( itemStack, 0, indexHotbar, false ) )
				return ItemStack.EMPTY;

			if( itemStack.isEmpty() )
				slot.putStack( ItemStack.EMPTY );
			else
				slot.onSlotChanged();
		}

		return returnStack;
	}
}
