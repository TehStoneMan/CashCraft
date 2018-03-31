package io.github.tehstoneman.cashcraft.command;

import java.util.List;

import io.github.tehstoneman.cashcraft.api.CashCraftAPI;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;

public class CommandBill implements ICommand
{
	@Override
	public String getName()
	{
		return "bill";
	}

	@Override
	public String getUsage( ICommandSender p_71518_1_ )
	{
		return "bill";
	}

	@Override
	public List getAliases()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void execute( MinecraftServer server, ICommandSender sender, String[] args ) throws CommandException
	{
		final EntityPlayer player = (EntityPlayer)sender;
		final int amount = CommandBase.parseInt( args[0] );
		CashCraftAPI.economy.getWallet( player ).withdraw( amount );
	}

	/*
	@Override
	public boolean canCommandSenderUseCommand( ICommandSender p_71519_1_ )
	{
		// TODO Auto-generated method stub
		return true;
	}
	*/

	/*
	@Override
	public List< String > addTabCompletionOptions( ICommandSender sender, String[] args, BlockPos pos )
	{
		// TODO Auto-generated method stub
		return null;
	}
	*/
	
	@Override
	public boolean checkPermission( MinecraftServer server, ICommandSender sender )
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List< String > getTabCompletions( MinecraftServer server, ICommandSender sender, String[] args, BlockPos pos )
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

	@Override
	public int compareTo( ICommand o )
	{
		// TODO Auto-generated method stub
		return 0;
	}

}
