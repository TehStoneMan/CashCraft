package io.github.tehstoneman.cashcraft.common.inventory;

import javax.annotation.Nonnull;

import io.github.tehstoneman.cashcraft.common.item.ItemCash;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

class CoinSlotHandler extends SlotItemHandler
{

	CoinSlotHandler( IItemHandler itemHandler, int index, int xPosition, int yPosition )
	{
		super( itemHandler, index, xPosition, yPosition );
	}

	@Override
	public boolean isItemValid( @Nonnull ItemStack stack )
	{
		return stack.isEmpty() || stack.getItem() instanceof ItemCash;
	}
}
