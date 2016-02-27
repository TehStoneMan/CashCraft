package io.github.tehstoneman.cashcraft.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

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

	@Override
	public void processCommand( ICommandSender sender, String[] args )
	{
		// TODO Auto-generated method stub
	}
}
