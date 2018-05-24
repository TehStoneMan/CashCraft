package io.github.tehstoneman.cashcraft.client.gui;

import io.github.tehstoneman.cashcraft.CashCraft;
import io.github.tehstoneman.cashcraft.common.inventory.ContainerMoneyPouch;
import io.github.tehstoneman.cashcraft.common.item.ItemCashCraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class CashCraftGuiHandler implements IGuiHandler
{
	@Override
	public Object getServerGuiElement( int ID, EntityPlayer player, World world, int x, int y, int z )
	{
		if( ID == CashCraft.GUI_MONEY_POUCH )
		{
			final ItemStack itemStack = player.getHeldItemMainhand();
			if( itemStack.getItem() != ItemCashCraft.MONEY_POUCH )
				return null;
			return new ContainerMoneyPouch( player, itemStack, player.inventory.currentItem );
		}
		return null;
	}

	@Override
	public Object getClientGuiElement( int ID, EntityPlayer player, World world, int x, int y, int z )
	{
		if( ID == CashCraft.GUI_MONEY_POUCH )
		{
			final ItemStack itemStack = player.getHeldItemMainhand();
			if( itemStack.getItem() != ItemCashCraft.MONEY_POUCH )
				return null;
			return new GuiMoneyPouch( new ContainerMoneyPouch( player, itemStack, player.inventory.currentItem ) );
		}
		return null;
	}
}
