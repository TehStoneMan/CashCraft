package io.github.tehstoneman.cashcraft.common.tileentity;

import java.util.UUID;

import io.github.tehstoneman.cashcraft.ModInfo;
import io.github.tehstoneman.cashcraft.api.PriceList;
import io.github.tehstoneman.cashcraft.common.block.BlockVender;
import io.github.tehstoneman.cashcraft.common.block.BlockVender.EnumState;
import io.github.tehstoneman.cashcraft.common.multiblock.VenderMultiblockController;
import it.zerono.mods.zerocore.api.multiblock.MultiblockControllerBase;
import it.zerono.mods.zerocore.api.multiblock.rectangular.RectangularMultiblockTileEntityBase;
import it.zerono.mods.zerocore.api.multiblock.validation.IMultiblockValidator;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class TileEntityVender extends RectangularMultiblockTileEntityBase implements IInventory
{
	private UUID		ownerUUID;
	private ItemStack[]	stacks	= new ItemStack[36];
	private int			balance	= 0;
	private String		customName;
	public PriceList	priceList;

	@Override
	public boolean shouldRefresh( World world, BlockPos pos, IBlockState oldState, IBlockState newSate )
	{
		if( world.getTileEntity( pos ) instanceof TileEntityVender )
			return false;
		return true;
	}

	@Override
	public NBTTagCompound writeToNBT( NBTTagCompound compound )
	{
		super.writeToNBT( compound );

		if( ownerUUID != null )
		{
			compound.setLong( "owner_M", ownerUUID.getMostSignificantBits() );
			compound.setLong( "owner_L", ownerUUID.getLeastSignificantBits() );
		}

		final NBTTagList nbttaglist = new NBTTagList();

		for( int i = 0; i < stacks.length; ++i )
			if( stacks[i] != null )
			{
				final NBTTagCompound nbttagcompound = new NBTTagCompound();
				nbttagcompound.setByte( "Slot", (byte)i );
				stacks[i].writeToNBT( nbttagcompound );
				nbttaglist.appendTag( nbttagcompound );
			}

		compound.setTag( "Items", nbttaglist );

		if( priceList != null )
			priceList.writeToNBT( compound );

		if( balance > 0 )
			compound.setInteger( "Balance", balance );

		if( hasCustomName() )
			compound.setString( "CustomName", customName );

		return compound;
	}

	@Override
	public void readFromNBT( NBTTagCompound compound )
	{
		super.readFromNBT( compound );

		if( compound.hasKey( "owner_M" ) )
			ownerUUID = new UUID( compound.getLong( "owner_M" ), compound.getLong( "owner_L" ) );

		final NBTTagList nbttaglist = compound.getTagList( "Items", 10 );
		stacks = new ItemStack[getSizeInventory()];

		for( int i = 0; i < nbttaglist.tagCount(); ++i )
		{
			final NBTTagCompound nbttagcompound = nbttaglist.getCompoundTagAt( i );
			final int j = nbttagcompound.getByte( "Slot" ) & 255;

			if( j >= 0 && j < stacks.length )
				stacks[j] = ItemStack.loadItemStackFromNBT( nbttagcompound );
		}

		priceList.readFromNBT( compound );

		if( compound.hasKey( "Balance", 3 ) )
			balance = Math.max( compound.getInteger( "Balance" ), 0 );
		else
			balance = 0;

		if( compound.hasKey( "CustomName", 8 ) )
			customName = compound.getString( "CustomName" );
	}

	public void setOwner( EntityPlayer player )
	{
		if( getMultiblockController().isAssembled() )
		{
			final BlockPos basePos = getMultiblockController().getReferenceCoord();
			final IBlockState state = worldObj.getBlockState( basePos );
			if( state.getValue( BlockVender.PROPERTYSTATE ) == EnumState.BUILT )
			{
				final TileEntity tileEntity = worldObj.getTileEntity( basePos );
				if( tileEntity instanceof TileEntityVender )
					( (TileEntityVender)tileEntity ).setOwner( player );
			}
		}
		ownerUUID = player.getUniqueID();
	}

	public UUID getOwnerID()
	{
		if( getMultiblockController().isAssembled() )
		{
			final BlockPos basePos = getMultiblockController().getReferenceCoord();
			final IBlockState state = worldObj.getBlockState( basePos );
			if( state.getValue( BlockVender.PROPERTYSTATE ) == EnumState.BUILT )
			{
				final TileEntity tileEntity = worldObj.getTileEntity( basePos );
				if( tileEntity instanceof TileEntityVender )
					return ( (TileEntityVender)tileEntity ).getOwnerID();
			}
		}
		return ownerUUID;
	}

	/*
	 * =================
	 * --- Inventory ---
	 * =================
	 */

	public void setCustomInventoryName( String customName )
	{
		this.customName = customName;
	}

	@Override
	public ITextComponent getDisplayName()
	{
		return hasCustomName() ? new TextComponentString( getName() ) : new TextComponentTranslation( getName(), new Object[0] );
	}

	@Override
	public String getName()
	{
		return hasCustomName() ? customName : "container." + ModInfo.MODID + ".vender";
	}

	@Override
	public boolean hasCustomName()
	{
		return customName != null && !customName.isEmpty();
	}

	@Override
	public int getSizeInventory()
	{
		return stacks.length;
	}

	@Override
	public ItemStack getStackInSlot( int index )
	{
		return stacks[index];
	}

	@Override
	public ItemStack decrStackSize( int index, int count )
	{
		final ItemStack itemstack = ItemStackHelper.getAndSplit( stacks, index, count );

		if( itemstack != null )
			markDirty();

		return itemstack;
	}

	@Override
	public ItemStack removeStackFromSlot( int index )
	{
		return ItemStackHelper.getAndRemove( stacks, index );
	}

	@Override
	public void setInventorySlotContents( int index, ItemStack stack )
	{
		stacks[index] = stack;

		if( stack != null && stack.stackSize > getInventoryStackLimit() )
			stack.stackSize = getInventoryStackLimit();

		markDirty();
	}

	@Override
	public int getInventoryStackLimit()
	{
		return 64;
	}

	@Override
	public boolean isUseableByPlayer( EntityPlayer player )
	{
		return true;
	}

	@Override
	public void openInventory( EntityPlayer player )
	{}

	@Override
	public void closeInventory( EntityPlayer player )
	{}

	@Override
	public boolean isItemValidForSlot( int index, ItemStack stack )
	{
		return false;
	}

	@Override
	public int getField( int id )
	{
		return 0;
	}

	@Override
	public void setField( int id, int value )
	{}

	@Override
	public int getFieldCount()
	{
		return 0;
	}

	@Override
	public void clear()
	{
		for( int i = 0; i < stacks.length; ++i )
			stacks[i] = null;
	}

	public int getBalance()
	{
		return balance;
	}

	public void setBalance( int amount )
	{
		balance = amount;
	}

	public int addToBalance( int amount )
	{
		return balance += amount;
	}

	/*
	 * ==================
	 * --- Multiblock ---
	 *
	 * Using ZeroCore Multiblock API
	 *
	 * ==================
	 */

	@Override
	public Class< ? extends MultiblockControllerBase > getMultiblockControllerType()
	{
		return VenderMultiblockController.class;
	}

	@Override
	public boolean isGoodForFrame( IMultiblockValidator validatorCallback )
	{
		return true;
	}

	@Override
	public boolean isGoodForSides( IMultiblockValidator validatorCallback )
	{
		return true;
	}

	@Override
	public boolean isGoodForTop( IMultiblockValidator validatorCallback )
	{
		return true;
	}

	@Override
	public boolean isGoodForBottom( IMultiblockValidator validatorCallback )
	{
		return true;
	}

	@Override
	public boolean isGoodForInterior( IMultiblockValidator validatorCallback )
	{
		return true;
	}

	@Override
	public void onMachineActivated()
	{}

	@Override
	public void onMachineDeactivated()
	{}

	@Override
	public MultiblockControllerBase createNewMultiblock()
	{
		return new VenderMultiblockController( worldObj );
	}
}
