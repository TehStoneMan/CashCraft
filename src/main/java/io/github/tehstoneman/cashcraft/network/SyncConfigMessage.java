package io.github.tehstoneman.cashcraft.network;

import io.github.tehstoneman.cashcraft.config.CashCraftConfig;

public class SyncConfigMessage// implements IMessage
{
	private final boolean	showAsCoins;
	private final boolean	useCustomName;
	private final String	cashSingular;
	private final String	cashPlural;

	public SyncConfigMessage()
	{
		showAsCoins = CashCraftConfig.COMMON.showAsCoins.get();
		useCustomName = CashCraftConfig.COMMON.useCustomName.get();
		cashSingular = CashCraftConfig.COMMON.cashSingular.get();
		cashPlural = CashCraftConfig.COMMON.cashPlural.get();
	}

	public SyncConfigMessage( boolean showAsCoins, boolean useCustomName, String cashSingular, String cashPlural )
	{
		this.showAsCoins = showAsCoins;
		this.useCustomName = useCustomName;
		this.cashSingular = cashSingular;
		this.cashPlural = cashPlural;
	}

	/*
	 * @Override
	 * public void fromBytes( ByteBuf buf )
	 * {
	 * showAsCoins = buf.readBoolean();
	 * useCustomName = buf.readBoolean();
	 * if( useCustomName )
	 * {
	 * cashSingular = ByteBufUtils.readUTF8String( buf );
	 * cashPlural = ByteBufUtils.readUTF8String( buf );
	 * }
	 * }
	 */

	/*
	 * @Override
	 * public void toBytes( ByteBuf buf )
	 * {
	 * buf.writeBoolean( showAsCoins );
	 * buf.writeBoolean( useCustomName );
	 * if( useCustomName )
	 * {
	 * ByteBufUtils.writeUTF8String( buf, cashSingular );
	 * ByteBufUtils.writeUTF8String( buf, cashPlural );
	 * }
	 * }
	 */

	/*
	 * public static class Handler implements IMessageHandler< SyncConfigMessage, IMessage >
	 * {
	 *
	 * @Override
	 * public IMessage onMessage( SyncConfigMessage message, MessageContext ctx )
	 * {
	 * if( ctx.side == Side.CLIENT )
	 * {
	 * final Minecraft minecraft = Minecraft.getMinecraft();
	 * final WorldClient worldClient = minecraft.world;
	 * minecraft.addScheduledTask( new Runnable()
	 * {
	 *
	 * @Override
	 * public void run()
	 * {
	 * processMessage( worldClient, message );
	 * }
	 * } );
	 * }
	 * return null;
	 * }
	 *
	 * void processMessage( WorldClient worldClient, SyncConfigMessage message )
	 * {
	 * CashCraftConfig.showAsCoins = message.showAsCoins;
	 * CashCraftConfig.useCustomName = message.useCustomName;
	 * CashCraftConfig.cashSingular = message.cashSingular;
	 * CashCraftConfig.cashPlural = message.cashPlural;
	 * }
	 * }
	 */
}
