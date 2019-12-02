package io.github.tehstoneman.cashcraft.common.item;

import java.util.List;

import javax.annotation.Nullable;

import io.github.tehstoneman.cashcraft.CashCraft;
import io.github.tehstoneman.cashcraft.api.CashCraftAPI;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
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
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class ItemMoneyPouch extends ItemCashCraft
{
	public ItemMoneyPouch()
	{
	}

	/*@Override
	public ICapabilityProvider initCapabilities( ItemStack stack, @Nullable NBTTagCompound nbt )
	{
		return new MoneyPouchProvider( stack );
	}*/

	/*@Override
	public ActionResult< ItemStack > onItemRightClick( World worldIn, EntityPlayer playerIn, EnumHand hand )
	{
		if( !worldIn.isRemote && hand == EnumHand.MAIN_HAND )
			playerIn.openGui( CashCraft.instance, CashCraft.GUI_MONEY_POUCH, worldIn, 0, 0, 0 );
		return new ActionResult<>( EnumActionResult.SUCCESS, playerIn.getHeldItem( hand ) );
	}*/

	// adds 'tooltip' text

	/*@SideOnly( Side.CLIENT )
	@Override
	public void addInformation( ItemStack stack, @Nullable World worldIn, List< String > tooltip, ITooltipFlag flagIn )
	{
		final NBTTagCompound nbtTagCompound = stack.getTagCompound();
		if( nbtTagCompound != null && nbtTagCompound.hasKey( "Value" ) )
		{
			final String v = CashCraftAPI.economy.toString( nbtTagCompound.getInteger( "Value" ), false );
			// final String v = CashCraftAPI.economy.toString( stack.getItemDamage(), false );
			final String s = CashCraft.proxy.localize( "tooltip.cashcraft.contains", v );
			tooltip.add( s );
		}
		else
			tooltip.add( CashCraft.proxy.localize( "tooltip.cashcraft.empty" ) );
		tooltip.add( CashCraft.proxy.localize( "tooltip.cashcraft.moneypouch" ) );
	}*/

	/*private static class MoneyPouchProvider implements ICapabilitySerializable< NBTTagCompound >
	{
		private final ItemStack		invItem;
		private ItemStackHandler	inventory;

		private MoneyPouchProvider( ItemStack stack )
		{
			invItem = stack;
			final int size = getSizeContents();
			if( size > 0 )
				inventory = new ItemStackHandler( size )
				{
					@Override
					protected void onContentsChanged( int slot )
					{
						// invItem.setItemDamage( (int)getValue() );
						// MoneyPouchProvider.this.markDirty();
					}
				};
			else
				inventory = null;
		}

		@Override
		public boolean hasCapability( Capability< ? > capability, EnumFacing facing )
		{
			return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY;
		}

		@Override
		public <T> T getCapability( Capability< T > capability, EnumFacing facing )
		{
			return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ? CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast( inventory )
					: null;
		}

		private int getSizeContents()
		{
			return 15;
		}

		private void markDirty()
		{
			int count = 0;
			for( int i = 0; i < inventory.getSlots(); i++ )
				if( !inventory.getStackInSlot( i ).isEmpty() )
					count++;
			invItem.setItemDamage( (int)getValue() );
		}

		@Override
		public NBTTagCompound serializeNBT()
		{
			NBTTagCompound compound = new NBTTagCompound();
			if( invItem.hasTagCompound() )
				compound = invItem.getTagCompound();
			compound.setLong( "Value", getValue() );
			invItem.setTagCompound( compound );
			return inventory.serializeNBT();
		}

		@Override
		public void deserializeNBT( NBTTagCompound compound )
		{
			inventory.deserializeNBT( compound );
		}

		public long getValue()
		{
			long value = 0;
			for( int i = 0; i < inventory.getSlots(); ++i )
				if( !inventory.getStackInSlot( i ).isEmpty() )
					value += CashCraftAPI.economy.getValue( inventory.getStackInSlot( i ) );
			return value;
		}
	}*/
}
