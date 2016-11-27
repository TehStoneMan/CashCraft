package io.github.tehstoneman.cashcraft.client.gui;

import io.github.tehstoneman.cashcraft.CashCraft;
import io.github.tehstoneman.cashcraft.common.inventory.ContainerMoneyPouch;
import io.github.tehstoneman.cashcraft.common.inventory.InventoryMoneyPouch;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class CashCraftGuiHandler implements IGuiHandler
{
	@Override
	public Object getServerGuiElement( int ID, EntityPlayer player, World world, int x, int y, int z )
	{
		if( ID == CashCraft.GUI_MONEY_POUCH )
			return new ContainerMoneyPouch( player.inventory, new InventoryMoneyPouch( player.getHeldItem( EnumHand.MAIN_HAND ) ), player );
		return null;
	}

	@Override
	public Object getClientGuiElement( int ID, EntityPlayer player, World world, int x, int y, int z )
	{
		if( ID == CashCraft.GUI_MONEY_POUCH )
			return new GuiMoneyPouch( player.inventory, new InventoryMoneyPouch( player.getHeldItem( EnumHand.MAIN_HAND ) ) );
		return null;
	}
}
