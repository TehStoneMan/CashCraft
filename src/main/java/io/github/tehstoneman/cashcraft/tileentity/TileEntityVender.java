package io.github.tehstoneman.cashcraft.tileentity;

import java.util.UUID;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ITickable;

public class TileEntityVender extends TileEntity implements ITickable, IInventory
{
	private boolean		isBuilt, isMaster;
	private BlockPos	masterPos	= new BlockPos( 0, 0, 0 );
	private UUID		ownerUUID;

	public void setBuilt( boolean isBuilt )
	{
		this.isBuilt = isBuilt;
	}

	public void setMaster( boolean isMaster )
	{
		this.isMaster = isMaster;
	}

	public void setMasterPos( BlockPos masterPos )
	{
		this.masterPos = masterPos;
	}

	public boolean isBuilt()
	{
		return isBuilt;
	}

	public boolean isMaster()
	{
		return isMaster;
	}

	public boolean isCenter()
	{
		return isMaster || masterPos.getX() == pos.getX() && masterPos.getZ() == pos.getZ();
	}

	public boolean isTop()
	{
		return masterPos.getY() < pos.getY();
	}

	public boolean isBottom()
	{
		return masterPos.getY() > pos.getY();
	}

	public BlockPos getMasterPos()
	{
		return masterPos;
	}

	public boolean checkMultiBlockForm()
	{
		final BlockPos thisPos = getPos();
		// Compare potential structure with blueprint
		for( int y = 0; y < 3; y++ )
			for( int z = 0; z < 2; z++ )
				for( int x = 0; x < 2; x++ )
				{
					final BlockPos checkPos = getPos().add( x, y - 1, z );

					final TileEntity tileEntity = worldObj.getTileEntity( checkPos );
					if( tileEntity == null || !( tileEntity instanceof TileEntityVender ) )
						return false;
					final TileEntityVender tileEntityTrade = (TileEntityVender)tileEntity;
					if( this.ownerUUID != null && tileEntityTrade.ownerUUID != null && !tileEntityTrade.ownerUUID.equals( this.ownerUUID ) ) return false;
					if( tileEntityTrade.isBuilt() )
						if( isBuilt )
						{
							if( tileEntityTrade.getMasterPos() != masterPos )
								return false;
						}
						else
							// Block is already part of another structure
							return false;
				}
		// Structure is valid
		return true;
	}

	public void setupStructure()
	{
		// This should only be performed from the master block
		if( !isMaster )
			return;

		// Compare potential structure with blueprint
		for( int y = 0; y < 3; y++ )
			for( int z = 0; z < 2; z++ )
				for( int x = 0; x < 2; x++ )
				{
					final BlockPos checkPos = getPos().add( x, y - 1, z );
					final TileEntity tileEntity = worldObj.getTileEntity( checkPos );

					if( tileEntity != null && tileEntity instanceof TileEntityVender )
					{
						( (TileEntityVender)tileEntity ).setMasterPos( getPos() );
						( (TileEntityVender)tileEntity ).setBuilt( true );
						worldObj.markBlockForUpdate( checkPos );
					}
				}
		markDirty();
	}

	public void resetStructure()
	{
		// This should only be performed from the master block
		if( !isMaster )
			return;

		// Loop through all structure blocks and reset them
		for( int y = 0; y < 3; y++ )
			for( int z = 0; z < 2; z++ )
				for( int x = 0; x < 2; x++ )
				{
					final BlockPos checkPos = getPos().add( x, y - 1, z );
					final TileEntity tileEntity = worldObj.getTileEntity( checkPos );
					if( tileEntity != null && tileEntity instanceof TileEntityVender )
						( (TileEntityVender)tileEntity ).reset();
					worldObj.markBlockForUpdate( checkPos );
				}
		markDirty();
	}

	public void reset()
	{
		masterPos = new BlockPos( 0, 0, 0 );
		isBuilt = false;
		isMaster = false;
	}

	public boolean checkForMaster()
	{
		final TileEntity tileEntity = worldObj.getTileEntity( masterPos );
		return tileEntity != null && tileEntity instanceof TileEntityVender;
	}

	@Override
	public void update()
	{
		if( !worldObj.isRemote )
			if( isBuilt() )
			{
				if( isMaster() )
				{
					// TODO: Master code here
				}
			}
			else
				// Check if structure is formed
				if( checkMultiBlockForm() )
				{
					// If we have reached this point, we should be able to safely assume that this is the block that will become the master block
					setMaster( true );
					// Create structure
					setupStructure();
				}
	}

	@Override
	public void writeToNBT( NBTTagCompound compound )
	{
		super.writeToNBT( compound );

		// Store status of master
		compound.setBoolean( "isBuilt", isBuilt );
		compound.setBoolean( "isMster", isMaster );

		// Store location of master
		compound.setInteger( "masterX", masterPos.getX() );
		compound.setInteger( "masterY", masterPos.getY() );
		compound.setInteger( "masterZ", masterPos.getZ() );

		if( ownerUUID != null )
		{
			compound.setLong( "owner_M", ownerUUID.getMostSignificantBits() );
			compound.setLong( "owner_L", ownerUUID.getLeastSignificantBits() );
		}

		// Store Master specific data
		if( isBuilt() && isMaster() )
		{
			// TODO: Store Master-only data here
		}
	}

	@Override
	public void readFromNBT( NBTTagCompound compound )
	{
		super.readFromNBT( compound );

		// Read status of master
		isBuilt = compound.getBoolean( "isBuilt" );
		isMaster = compound.getBoolean( "isMster" );

		// Read location of master
		final int x = compound.getInteger( "masterX" );
		final int y = compound.getInteger( "masterY" );
		final int z = compound.getInteger( "masterZ" );
		masterPos = new BlockPos( x, y, z );

		if( compound.hasKey( "owner_M" ) )
			ownerUUID = new UUID( compound.getLong( "owner_M" ), compound.getLong( "owner_L" ) );

		// Read Master specific data
		if( isBuilt() && isMaster() )
		{
			// TODO: Read Master-only data here
		}
	}

	@Override
	public Packet getDescriptionPacket()
	{
		final NBTTagCompound nbtTagCompound = new NBTTagCompound();
		writeToNBT( nbtTagCompound );
		final int metadata = getBlockMetadata();
		return new S35PacketUpdateTileEntity( pos, metadata, nbtTagCompound );
	}

	@Override
	public void onDataPacket( NetworkManager net, S35PacketUpdateTileEntity pkt )
	{
		readFromNBT( pkt.getNbtCompound() );
		Minecraft.getMinecraft().renderGlobal.markBlockForUpdate( pos );
	}

	public void setOwner( EntityPlayer player )
	{
		ownerUUID = player.getUniqueID();
	}

	public UUID getOwnderID()
	{
		return ownerUUID;
	}

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
	public IChatComponent getDisplayName()
	{
		// TODO Auto-generated method stub
		return null;
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
}
