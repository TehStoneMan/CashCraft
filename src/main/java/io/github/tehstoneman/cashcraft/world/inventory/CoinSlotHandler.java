package io.github.tehstoneman.cashcraft.world.inventory;

import javax.annotation.Nonnull;

import io.github.tehstoneman.cashcraft.world.item.CashItem;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

class CoinSlotHandler extends SlotItemHandler
{

	CoinSlotHandler( IItemHandler itemHandler, int index, int xPosition, int yPosition )
	{
		super( itemHandler, index, xPosition, yPosition );
	}

	@Override
	public boolean mayPlace( @Nonnull ItemStack stack )
	{
		return stack.isEmpty() || stack.getItem() instanceof CashItem;
	}
}
