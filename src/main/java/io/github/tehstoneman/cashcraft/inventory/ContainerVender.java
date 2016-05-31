package io.github.tehstoneman.cashcraft.inventory;

import io.github.tehstoneman.cashcraft.tileentity.TileEntityVender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;

public class ContainerVender extends Container
{
	private IInventory venderInventory;
	private int numRows;
	
	public ContainerVender( IInventory vender, EntityPlayer player )
	{
		venderInventory = vender;
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean canInteractWith( EntityPlayer playerIn )
	{
		return venderInventory.isUseableByPlayer( playerIn );
	}

}
