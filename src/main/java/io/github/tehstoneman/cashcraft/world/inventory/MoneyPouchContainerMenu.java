package io.github.tehstoneman.cashcraft.world.inventory;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.ItemStackHandler;

public class MoneyPouchContainerMenu extends AbstractContainerMenu
{
	// private final PlayerInventory inventoryPlayer;
	private final ItemStackHandler	inventoryMoneyPouch;
	private final int				protectedIndex;

	private final int indexHotbar;

	public MoneyPouchContainerMenu( int windowID, Inventory inventoryPlayer, ItemStack moneyPouch, int protectedIndex )
	{
		super( CashCraftMenuTypes.MONEY_POUCH.get(), windowID );
		inventoryMoneyPouch	= (ItemStackHandler)moneyPouch.getCapability( ForgeCapabilities.ITEM_HANDLER, null ).orElse( null );
		this.protectedIndex	= protectedIndex;

		indexHotbar = inventoryMoneyPouch.getSlots();
		for( int i = 0; i < indexHotbar; i++ )
		{
			final int	x	= i % 5 * 18 + 44;
			final int	y	= i / 5 * 18 + 20;
			addSlot( new CoinSlotHandler( inventoryMoneyPouch, i, x, y ) );
		}

		for( int i = 0; i < 27; i++ )
		{
			final int	x	= i % 9 * 18 + 8;
			final int	y	= 87 + i / 9 * 18;
			addSlot( new Slot( inventoryPlayer, i + 9, x, y ) );
		}

		for( int i = 0; i < 9; i++ )
		{
			final int	x	= i % 9 * 18 + 8;
			final int	y	= 145;
			addSlot( new Slot( inventoryPlayer, i, x, y ) );
		}
	}

	@Override
	public boolean isValidSlotIndex( int slotId )
	{
		if( slotId == protectedIndex )
			return false;
		return super.isValidSlotIndex( slotId );
	}

	@Override
	public ItemStack quickMoveStack( Player playerIn, int index )
	{
		final Slot	slot		= slots.get( index );
		ItemStack	returnStack	= ItemStack.EMPTY;

		if( slot != null && slot.hasItem() )
		{
			final ItemStack itemStack = slot.getItem();
			returnStack = itemStack.copy();

			if( index < indexHotbar )
			{
				// Try to transfer from container to player
				if( !moveItemStackTo( itemStack, indexHotbar, slots.size(), true ) )
					return ItemStack.EMPTY;
			} else if( !moveItemStackTo( itemStack, 0, indexHotbar, false ) )
				return ItemStack.EMPTY;

			if( itemStack.isEmpty() )
				slot.set( ItemStack.EMPTY );
			else
				slot.setChanged();
		}

		return returnStack;
	}

	@Override
	public boolean stillValid( Player playerIn )
	{
		return true;
	}
}
