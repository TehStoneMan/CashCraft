package io.github.tehstoneman.cashcraft.network;

import java.util.logging.Logger;

import io.github.tehstoneman.cashcraft.ModInfo;
import io.github.tehstoneman.cashcraft.common.tileentity.TileEntityVender;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class UpdateMessage implements IMessage
{
	private BlockPos	targetPos;
	private boolean		isBuilt, isMaster;
	private BlockPos	masterPos;

	public UpdateMessage( BlockPos pos, Boolean isBuilt, Boolean isMaster, BlockPos masterPos )
	{
		targetPos = pos;
		this.isBuilt = isBuilt;
		this.isMaster = isMaster;
		this.masterPos = masterPos;
	}

	public UpdateMessage()
	{
		targetPos = new BlockPos( 0, 0, 0 );
		isBuilt = false;
		isMaster = false;
		masterPos = new BlockPos( 0, 0, 0 );
	}

	@Override
	public void fromBytes( ByteBuf buf )
	{
		int x = buf.readInt();
		int y = buf.readInt();
		int z = buf.readInt();
		targetPos = new BlockPos( x, y, z );

		isBuilt = buf.readBoolean();
		isMaster = buf.readBoolean();

		x = buf.readInt();
		y = buf.readInt();
		z = buf.readInt();
		masterPos = new BlockPos( x, y, z );
	}

	@Override
	public void toBytes( ByteBuf buf )
	{
		buf.writeInt( targetPos.getX() );
		buf.writeInt( targetPos.getY() );
		buf.writeInt( targetPos.getZ() );

		buf.writeBoolean( isBuilt );
		buf.writeBoolean( isMaster );

		buf.writeInt( masterPos.getX() );
		buf.writeInt( masterPos.getY() );
		buf.writeInt( masterPos.getZ() );
	}
	
	@Override
	public String toString()
	{
		return "target " + targetPos.toString() + " : built " + isBuilt + " : isMaster " + isMaster + " : masterPos " + masterPos.toString();
	}

	public static class Handler implements IMessageHandler< UpdateMessage, IMessage >
	{
		@Override
		public IMessage onMessage( final UpdateMessage message, MessageContext ctx )
		{
			if( ctx.side == Side.CLIENT )
			{
				final Minecraft minecraft = Minecraft.getMinecraft();
				final WorldClient worldClient = minecraft.theWorld;
				minecraft.addScheduledTask( new Runnable()
				{
					@Override
					public void run()
					{
						processMessage( worldClient, message );
					}
				} );
			}
			return null;
		}

		public void processMessage( WorldClient worldClient, UpdateMessage message )
		{
			Logger.getLogger( ModInfo.MODID ).info( "Message Recieved : " + message.toString() );
			final TileEntity tileEntity = worldClient.getTileEntity( message.targetPos );
			if( tileEntity != null && tileEntity instanceof TileEntityVender )
			{
				final TileEntityVender vender = (TileEntityVender)tileEntity;
				//vender.setBuilt( message.isBuilt );
				//vender.setMaster( message.isMaster );
				//vender.setMasterPos( message.masterPos );
				worldClient.markBlockRangeForRenderUpdate( message.targetPos, message.targetPos );
			}
		}
	}
}
