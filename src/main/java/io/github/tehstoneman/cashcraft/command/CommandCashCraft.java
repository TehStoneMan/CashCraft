package io.github.tehstoneman.cashcraft.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;

public class CommandCashCraft extends CommandBase
{
	@Override
	public String getCommandName()
	{
		// TODO Auto-generated method stub
		return "cash";
	}

	@Override
	public String getCommandUsage( ICommandSender sender )
	{
		// TODO Auto-generated method stub
		return "/cash <cmd>";
	}

/*	@Override
	public void processCommand( ICommandSender sender, String[] args )
	{
		// TODO Auto-generated method stub
	}*/

	@Override
	public void execute( MinecraftServer server, ICommandSender sender, String[] args ) throws CommandException
	{
		// TODO Auto-generated method stub
		
	}
}
