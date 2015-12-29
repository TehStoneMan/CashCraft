package io.github.tehstoneman.cashcraft.command;

import io.github.tehstoneman.cashcraft.api.CashCraftAPI;

import java.util.List;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;

public class CommandWallet implements ICommand
{

	@Override
	public int compareTo( Object o )
	{
		if( o instanceof ICommand )
			return compareTo( o );
		return 0;
	}

	@Override
	public String getCommandName()
	{
		return "wallet";
	}

	@Override
	public String getCommandUsage( ICommandSender p_71518_1_ )
	{
		return "/wallet: Count the amount of cash you are carrying.";
	}

	@Override
	public List getCommandAliases()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void processCommand( ICommandSender sender, String[] args )
	{
		final EntityPlayer player = (EntityPlayer)sender;
		// player.addChatMessage( new ChatComponentText( StatCollector.translateToLocal("container.crafting")+ " " + CashCraftAPI.economy.getWallet( player
		// ).toString() + "." ) );
		player.addChatMessage( new ChatComponentText( "You have " + CashCraftAPI.economy.getWallet( player ).toString() + "." ) );
	}

	@Override
	public boolean canCommandSenderUseCommand( ICommandSender p_71519_1_ )
	{
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public List addTabCompletionOptions( ICommandSender p_71516_1_, String[] p_71516_2_ )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isUsernameIndex( String[] p_82358_1_, int p_82358_2_ )
	{
		// TODO Auto-generated method stub
		return false;
	}

}
