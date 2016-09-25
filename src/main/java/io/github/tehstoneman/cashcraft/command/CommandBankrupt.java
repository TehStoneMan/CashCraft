package io.github.tehstoneman.cashcraft.command;

import java.util.List;

import io.github.tehstoneman.cashcraft.api.CashCraftAPI;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;

public class CommandBankrupt implements ICommand
{
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

	/*
	@Override
	public void processCommand( ICommandSender sender, String[] args )
	{
		final EntityPlayer player = (EntityPlayer)sender;
		CashCraftAPI.economy.getWallet( player ).empty();
	}
	*/

	/*
	@Override
	public boolean canCommandSenderUseCommand( ICommandSender p_71519_1_ )
	{
		// TODO Auto-generated method stub
		return true;
	}
	*/

	@Override
	public boolean isUsernameIndex( String[] p_82358_1_, int p_82358_2_ )
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int compareTo( ICommand arg0 )
	{
		// TODO Auto-generated method stub
		return 0;
	}

	/*
	@Override
	public List< String > addTabCompletionOptions( ICommandSender sender, String[] args, BlockPos pos )
	{
		// TODO Auto-generated method stub
		return null;
	}
	*/

	@Override
	public void execute( MinecraftServer server, ICommandSender sender, String[] args ) throws CommandException
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean checkPermission( MinecraftServer server, ICommandSender sender )
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List< String > getTabCompletionOptions( MinecraftServer server, ICommandSender sender, String[] args, BlockPos pos )
	{
		// TODO Auto-generated method stub
		return null;
	}

}
