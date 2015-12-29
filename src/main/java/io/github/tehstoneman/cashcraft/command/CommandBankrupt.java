package io.github.tehstoneman.cashcraft.command;

import io.github.tehstoneman.cashcraft.api.CashCraftAPI;

import java.util.List;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;

public class CommandBankrupt implements ICommand
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
		// TODO Auto-generated method stub
		return "bankrupt";
	}

	@Override
	public String getCommandUsage( ICommandSender p_71518_1_ )
	{
		// TODO Auto-generated method stub
		return "/bankrupt";
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
		CashCraftAPI.economy.getWallet( player ).empty();
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
