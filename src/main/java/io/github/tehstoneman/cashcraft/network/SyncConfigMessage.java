package io.github.tehstoneman.cashcraft.network;

import io.github.tehstoneman.cashcraft.util.ModSettings;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class SyncConfigMessage implements IMessage
{
	private boolean	showAsCoins;
	private boolean	useCustomName;
	private String	cashSingular;
	private String	cashPlural;

	public SyncConfigMessage()
	{
		showAsCoins = ModSettings.showAsCoins;
		useCustomName = ModSettings.useCustomName;
		cashSingular = ModSettings.cashSingular;
		cashPlural = ModSettings.cashPlural;
	}

	public SyncConfigMessage( boolean showAsCoins, boolean useCustomName, String cashSingular, String cashPlural )
	{
		this.showAsCoins = showAsCoins;
		this.useCustomName = useCustomName;
		this.cashSingular = cashSingular;
		this.cashPlural = cashPlural;
	}

	@Override
	public void fromBytes( ByteBuf buf )
	{
		showAsCoins = buf.readBoolean();
		useCustomName = buf.readBoolean();
		if( useCustomName )
		{
			cashSingular = ByteBufUtils.readUTF8String( buf );
			cashPlural = ByteBufUtils.readUTF8String( buf );
		}
	}

	@Override
	public void toBytes( ByteBuf buf )
	{
		buf.writeBoolean( showAsCoins );
		buf.writeBoolean( useCustomName );
		if( useCustomName )
		{
			ByteBufUtils.writeUTF8String( buf, cashSingular );
			ByteBufUtils.writeUTF8String( buf, cashPlural );
		}
	}

	public static class Handler implements IMessageHandler< SyncConfigMessage, IMessage >
	{
		@Override
		public IMessage onMessage( SyncConfigMessage message, MessageContext ctx )
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

		void processMessage( WorldClient worldClient, SyncConfigMessage message )
		{
			ModSettings.showAsCoins = message.showAsCoins;
			ModSettings.useCustomName = message.useCustomName;
			ModSettings.cashSingular = message.cashSingular;
			ModSettings.cashPlural = message.cashPlural;
		}
	}
}
