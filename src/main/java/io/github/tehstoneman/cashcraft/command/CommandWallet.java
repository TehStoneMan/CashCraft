package io.github.tehstoneman.cashcraft.command;

import java.util.List;

import io.github.tehstoneman.cashcraft.api.CashCraftAPI;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

public class CommandWallet implements ICommand
{
	@Override
	public String getName()
	{
		return "wallet";
	}

	@Override
	public String getUsage( ICommandSender p_71518_1_ )
	{
		return "/wallet: Count the amount of cash you are carrying.";
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
		// player.addChatMessage( new ChatComponentText( StatCollector.translateToLocal("container.crafting")+ " " + CashCraftAPI.economy.getWallet( player
		// ).toString() + "." ) );
		player.sendMessage( new TextComponentString( "You have " + CashCraftAPI.economy.getWallet( player ).toString() + "." ) );
	}

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
