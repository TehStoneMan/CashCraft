package io.github.tehstoneman.cashcraft.common.inventory;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

public class PriceSlot extends Slot
{
	public PriceSlot( IInventory inventoryIn, int index, int xPosition, int yPosition )
	{
		super( inventoryIn, index, xPosition, yPosition );
	}

	@Override
    public int getSlotStackLimit()
    {
        return 1;
    }

	/*@Override
    public void onSlotChanged()
    {
		if( inventory instanceof ContainerVender )
			((ContainerVender)inventory).updatePriceList();
        super.onSlotChanged();
    }*/
}
