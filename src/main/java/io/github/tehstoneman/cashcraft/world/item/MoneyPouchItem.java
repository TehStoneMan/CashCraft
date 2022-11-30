package io.github.tehstoneman.cashcraft.world.item;

import java.util.List;

import javax.annotation.Nullable;

import io.github.tehstoneman.cashcraft.api.CashCraftAPI;
import io.github.tehstoneman.cashcraft.world.inventory.MoneyPouchContainerMenu;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.network.NetworkHooks;

public class MoneyPouchItem extends CashCraftItem implements MenuProvider
{
	private static class MoneyPouchProvider implements ICapabilitySerializable< CompoundTag >
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
		public void deserializeNBT( CompoundTag compound )
		{
			inventory.deserializeNBT( compound );
		}

		@Override
		public <T> LazyOptional< T > getCapability( Capability< T > capability, Direction facing )
		{
			if( capability == ForgeCapabilities.ITEM_HANDLER )
				return ForgeCapabilities.ITEM_HANDLER.orEmpty( capability, inventoryHandler );
			return LazyOptional.empty();
		}

		public long getValue()
		{
			long value = 0;
			for( int i = 0; i < inventory.getSlots(); ++i )
				if( !inventory.getStackInSlot( i ).isEmpty() )
					value += CashCraftAPI.economy.getValue( inventory.getStackInSlot( i ) );
			return value;
		}

		@Override
		public CompoundTag serializeNBT()
		{
			CompoundTag compound = new CompoundTag();
			if( invItem.hasTag() )
				compound = invItem.getTag();
			compound.putLong( "Value", getValue() );
			invItem.setTag( compound );
			return inventory.serializeNBT();
		}

		private int getSizeContents()
		{
			return 15;
		}
	}

	public MoneyPouchItem()
	{
		super( new Item.Properties() );
	}

	@Override
	public void appendHoverText( ItemStack stack, @Nullable Level worldIn, List< Component > tooltip, TooltipFlag flagIn )
	{
		super.appendHoverText( stack, worldIn, tooltip, flagIn );
		final CompoundTag CompoundTag = stack.getTag();
		if( CompoundTag != null && CompoundTag.contains( "Value" ) )
		{
			final String v = CashCraftAPI.economy.toString( CompoundTag.getInt( "Value" ), false );
			tooltip.add( Component.translatable( "tooltip.cashcraft.contains", v ) );
		} else
			tooltip.add( Component.translatable( "tooltip.cashcraft.empty" ) );
		tooltip.add( Component.translatable( "tooltip.cashcraft.money_pouch" ) );
	}

	@Override
	public AbstractContainerMenu createMenu( int windowID, Inventory playerInventory, Player player )
	{
		final ItemStack	itemStack	= player.getMainHandItem();
		final int		index		= player.getInventory().selected;
		return new MoneyPouchContainerMenu( windowID, playerInventory, itemStack, index );
	}

	@Override
	public Component getDisplayName()
	{
		return Component.translatable( "container.cashcraft.money_pouch" );
	}

	@Override
	public ICapabilityProvider initCapabilities( ItemStack stack, @Nullable CompoundTag nbt )
	{
		return new MoneyPouchProvider( stack );
	}

	@Override
	public InteractionResultHolder< ItemStack > use( Level worldIn, Player playerIn, InteractionHand hand )
	{
		if( !worldIn.isClientSide && hand == playerIn.getUsedItemHand() )
			NetworkHooks.openScreen( (ServerPlayer)playerIn, CashCraftItems.MONEY_POUCH.get(),
				buf -> buf.writeItem( playerIn.getItemInHand( hand ) ).writeInt( playerIn.getInventory().selected ) );

		return new InteractionResultHolder<>( InteractionResult.SUCCESS, playerIn.getItemInHand( hand ) );
	}
}
