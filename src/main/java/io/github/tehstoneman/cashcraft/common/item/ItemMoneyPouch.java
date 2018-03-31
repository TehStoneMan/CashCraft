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
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class ItemMoneyPouch extends Item
{
	public ItemMoneyPouch()
	{
		super();
		setMaxStackSize( 1 );
	}

	@Override
	public ICapabilityProvider initCapabilities( ItemStack stack, NBTTagCompound nbt )
	{
		return new MoneyPouchProvider();
	}

	@Override
	public ActionResult< ItemStack > onItemRightClick( World worldIn, EntityPlayer playerIn, EnumHand hand )
	{
		if( !worldIn.isRemote && hand == EnumHand.MAIN_HAND )
			playerIn.openGui( CashCraft.instance, CashCraft.GUI_MONEY_POUCH, worldIn, 0, 0, 0 );
		return new ActionResult( EnumActionResult.SUCCESS, playerIn.getHeldItem( hand ) );
	}

	// adds 'tooltip' text
	@SideOnly( Side.CLIENT )
	@Override
	public void addInformation( ItemStack stack, EntityPlayer playerIn, List tooltip, boolean advanced )
	{
		final NBTTagCompound nbtTagCompound = stack.getTagCompound();
		if( nbtTagCompound != null && nbtTagCompound.hasKey( "Value" ) )
		{
			final String v = CashCraftAPI.economy.toString( nbtTagCompound.getInteger( "Value" ), false );
			final String s = I18n.format( "tooltip.cashcraft.contains", v );
			tooltip.add( s );
		}
		else
			tooltip.add( I18n.format( "tooltip.cashcraft.empty" ) );
		tooltip.add( I18n.format( "tooltip.cashcraft.moneypouch" ) );
	}

	public static class MoneyPouchProvider implements ICapabilitySerializable< NBTTagCompound >
	{
		public ItemStackHandler inventory;

		public MoneyPouchProvider()
		{
			inventory = new ItemStackHandler( 15 );
			/*
			 * {
			 * 
			 * @Override
			 * protected void onContentsChanged( int slot )
			 * {
			 * TileEntityContainer.this.markDirty();
			 * }
			 * };
			 */
		}

		@Override
		public boolean hasCapability( Capability< ? > capability, EnumFacing facing )
		{
			return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY;
		}

		@Override
		public <T> T getCapability( Capability< T > capability, EnumFacing facing )
		{
			if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
				return (T)inventory;
			return null;
		}

		@Override
		public NBTTagCompound serializeNBT()
		{
			if( inventory != null )
			{
				final NBTTagCompound compound = new NBTTagCompound();
				compound.setTag( "Inventory", inventory.serializeNBT() );
				return compound;
			}
			return null;
		}

		@Override
		public void deserializeNBT( NBTTagCompound compound )
		{
			if( compound.hasKey( "Inventory" ) )
				inventory.deserializeNBT( compound.getCompoundTag( "Inventory" ) );
		}
	}
}
