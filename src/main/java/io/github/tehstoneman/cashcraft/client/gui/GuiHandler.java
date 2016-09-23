package io.github.tehstoneman.cashcraft.client.gui;

import java.util.logging.Logger;

import io.github.tehstoneman.cashcraft.ModInfo;
import io.github.tehstoneman.cashcraft.api.ITrade.EnumTradeType;
import io.github.tehstoneman.cashcraft.common.inventory.ContainerVender;
import io.github.tehstoneman.cashcraft.common.tileentity.TileEntityVender;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler
{
	@Override
	public Object getServerGuiElement( int ID, EntityPlayer player, World world, int x, int y, int z )
	{
		final BlockPos blockPos = new BlockPos( x, y, z );
		final TileEntity tileEntity = world.getTileEntity( blockPos );
		if( ID == EnumTradeType.OWNER.getGuiID() && tileEntity instanceof TileEntityVender )
		{
			final TileEntityVender vender = (TileEntityVender)tileEntity;
			return new ContainerVender( player.inventory, vender, Minecraft.getMinecraft().thePlayer );
		}
		return null;
	}

	@Override
	public Object getClientGuiElement( int ID, EntityPlayer player, World world, int x, int y, int z )
	{
		final BlockPos blockPos = new BlockPos( x, y, z );
		final TileEntity tileEntity = world.getTileEntity( blockPos );
		if( ID == EnumTradeType.OWNER.getGuiID() && tileEntity instanceof TileEntityVender )
		{
			final TileEntityVender vender = (TileEntityVender)tileEntity;
			return new GuiVender( player.inventory, vender );
		}
		return null;
	}
}
