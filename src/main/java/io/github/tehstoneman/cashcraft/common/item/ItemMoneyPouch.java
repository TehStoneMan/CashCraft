package io.github.tehstoneman.cashcraft.common.item;

import java.util.List;

import io.github.tehstoneman.cashcraft.CashCraft;
import io.github.tehstoneman.cashcraft.api.CashCraftAPI;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemMoneyPouch extends Item
{
	public ItemMoneyPouch()
	{
		super();
		setMaxStackSize( 1 );
	}

	@Override
	public ActionResult< ItemStack > onItemRightClick( ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand )
	{
		if( !worldIn.isRemote && hand == EnumHand.MAIN_HAND )
			playerIn.openGui( CashCraft.instance, CashCraft.GUI_MONEY_POUCH, worldIn, 0, 0, 0 );
		return new ActionResult( EnumActionResult.SUCCESS, itemStackIn );
	}

	// adds 'tooltip' text
	@SideOnly( Side.CLIENT )
	@Override
	public void addInformation( ItemStack stack, EntityPlayer playerIn, List tooltip, boolean advanced )
	{
		final NBTTagCompound nbtTagCompound = stack.getTagCompound();
		if( nbtTagCompound != null && nbtTagCompound.hasKey( "Value" ) )
		{
			String v = CashCraftAPI.economy.toString( nbtTagCompound.getInteger( "Value" ), false );
			String s = I18n.format( "tooltip.cashcraft.contains", v );
			tooltip.add( s );
		}
		else
		{
			tooltip.add( I18n.format( "tooltip.cashcraft.empty" ) );
		}
		tooltip.add( I18n.format( "tooltip.cashcraft.moneypouch" ) );
	}
}
