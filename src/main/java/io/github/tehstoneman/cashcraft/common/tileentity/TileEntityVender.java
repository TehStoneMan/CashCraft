package io.github.tehstoneman.cashcraft.common.tileentity;

import java.util.UUID;

import io.github.tehstoneman.cashcraft.common.multiblock.VenderMultiblockController;
import it.zerono.mods.zerocore.api.multiblock.MultiblockControllerBase;
import it.zerono.mods.zerocore.api.multiblock.rectangular.RectangularMultiblockTileEntityBase;
import it.zerono.mods.zerocore.api.multiblock.validation.IMultiblockValidator;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TileEntityVender extends RectangularMultiblockTileEntityBase implements IInventory
{
	private UUID ownerUUID;

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

		return compound;
	}

	@Override
	public void readFromNBT( NBTTagCompound compound )
	{
		super.readFromNBT( compound );

		if( compound.hasKey( "owner_M" ) )
			ownerUUID = new UUID( compound.getLong( "owner_M" ), compound.getLong( "owner_L" ) );

	}

	public void setOwner( EntityPlayer player )
	{
		ownerUUID = player.getUniqueID();
	}

	public UUID getOwnderID()
	{
		return ownerUUID;
	}

	/*
	 * =================
	 * --- Inventory ---
	 * =================
	 */

	@Override
	public String getName()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasCustomName()
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getSizeInventory()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ItemStack getStackInSlot( int index )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemStack decrStackSize( int index, int count )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemStack removeStackFromSlot( int index )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setInventorySlotContents( int index, ItemStack stack )
	{
		// TODO Auto-generated method stub

	}

	@Override
	public int getInventoryStackLimit()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isUseableByPlayer( EntityPlayer player )
	{
		return true;
	}

	@Override
	public void openInventory( EntityPlayer player )
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void closeInventory( EntityPlayer player )
	{
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isItemValidForSlot( int index, ItemStack stack )
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getField( int id )
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setField( int id, int value )
	{
		// TODO Auto-generated method stub

	}

	@Override
	public int getFieldCount()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void clear()
	{
		// TODO Auto-generated method stub

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
