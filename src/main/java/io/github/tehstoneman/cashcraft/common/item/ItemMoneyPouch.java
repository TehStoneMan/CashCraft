package io.github.tehstoneman.cashcraft.common.item;

import java.util.List;

import javax.annotation.Nullable;

import io.github.tehstoneman.cashcraft.api.CashCraftAPI;
import io.github.tehstoneman.cashcraft.common.inventory.ContainerMoneyPouch;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class ItemMoneyPouch extends ItemCashCraft implements INamedContainerProvider
{
	public ItemMoneyPouch()
	{}

	@Override
	public Container createMenu( int windowID, PlayerInventory playerInventory, PlayerEntity player )
	{
		final ItemStack itemStack = player.getHeldItemMainhand();
		final int index = player.inventory.currentItem;
		return new ContainerMoneyPouch( windowID, playerInventory, itemStack, index );
	}

	@Override
	public ITextComponent getDisplayName()
	{
		return new TranslationTextComponent( "container.cashcraft.money_pouch" );
	}

	@Override
	public ICapabilityProvider initCapabilities( ItemStack stack, @Nullable CompoundNBT nbt )
	{
		return new MoneyPouchProvider( stack );
	}

	@Override
	public ActionResult< ItemStack > onItemRightClick( World worldIn, PlayerEntity playerIn, Hand hand )
	{
		if( !worldIn.isRemote && hand == playerIn.getActiveHand() )
			NetworkHooks.openGui( (ServerPlayerEntity)playerIn, CashCraftItems.MONEY_POUCH,
					buf -> buf.writeItemStack( playerIn.getHeldItem( hand ) ).writeInt( playerIn.inventory.currentItem ) );

		return new ActionResult<>( ActionResultType.SUCCESS, playerIn.getHeldItem( hand ) );
	}

	// adds 'tooltip' text
	@Override
	public void addInformation( ItemStack stack, @Nullable World worldIn, List< ITextComponent > tooltip, ITooltipFlag flagIn )
	{
		super.addInformation( stack, worldIn, tooltip, flagIn );
		final CompoundNBT CompoundNBT = stack.getTag();
		if( CompoundNBT != null && CompoundNBT.contains( "Value" ) )
		{
			final String v = CashCraftAPI.economy.toString( CompoundNBT.getInt( "Value" ), false );
			tooltip.add( new TranslationTextComponent( "tooltip.cashcraft.contains", v ) );
		}
		else
			tooltip.add( new TranslationTextComponent( "tooltip.cashcraft.empty" ) );
		tooltip.add( new TranslationTextComponent( "tooltip.cashcraft.money_pouch" ) );
	}

	private static class MoneyPouchProvider implements ICapabilitySerializable< CompoundNBT >
	{
		private final ItemStack						invItem;
		private ItemStackHandler					inventory;
		private final LazyOptional< IItemHandler >	inventoryHandler	= LazyOptional.of( () -> inventory );

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
		public <T> LazyOptional< T > getCapability( Capability< T > capability, Direction facing )
		{
			if( capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY )
				return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.orEmpty( capability, inventoryHandler );
			return LazyOptional.empty();
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
			// invItem.setValue( (int)getValue() );
		}

		@Override
		public CompoundNBT serializeNBT()
		{
			CompoundNBT compound = new CompoundNBT();
			if( invItem.hasTag() )
				compound = invItem.getTag();
			compound.putLong( "Value", getValue() );
			invItem.setTag( compound );
			return inventory.serializeNBT();
		}

		@Override
		public void deserializeNBT( CompoundNBT compound )
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
	}
}
