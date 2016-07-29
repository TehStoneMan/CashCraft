package io.github.tehstoneman.cashcraft.client.gui;

import io.github.tehstoneman.cashcraft.api.ITrade.EnumTradeType;
import io.github.tehstoneman.cashcraft.inventory.ContainerVender;
import io.github.tehstoneman.cashcraft.tileentity.TileEntityVender;
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
			return new ContainerVender( vender, player );
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
			final TileEntityVender tileEntityVender = (TileEntityVender)tileEntity;
			return new GuiVender( tileEntityVender );
		}
		return null;
	}
}
