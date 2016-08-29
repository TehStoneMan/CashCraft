package io.github.tehstoneman.cashcraft.common.multiblock;

import java.util.logging.Logger;

import io.github.tehstoneman.cashcraft.ModInfo;
import it.zerono.mods.zerocore.api.multiblock.IMultiblockPart;
import it.zerono.mods.zerocore.api.multiblock.MultiblockControllerBase;
import it.zerono.mods.zerocore.api.multiblock.rectangular.RectangularMultiblockControllerBase;
import it.zerono.mods.zerocore.api.multiblock.validation.IMultiblockValidator;
import it.zerono.mods.zerocore.lib.block.ModTileEntity.SyncReason;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class VenderMultiblockController extends RectangularMultiblockControllerBase
{
	public VenderMultiblockController( World world )
	{
		super( world );
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onAttachedPartWithMultiblockData( IMultiblockPart part, NBTTagCompound data )
	{
		// TODO Auto-generated method stub

	}

	@Override
	protected void onBlockAdded( IMultiblockPart newPart )
	{
		// TODO Auto-generated method stub

	}

	@Override
	protected void onBlockRemoved( IMultiblockPart oldPart )
	{
		// TODO Auto-generated method stub

	}

	@Override
	protected void onMachineAssembled()
	{
		if( WORLD.isRemote )
			markMultiblockForRenderUpdate();
		else
			Logger.getLogger( ModInfo.MODID ).info( "Multiblock Assembled" );
	}

	@Override
	protected void onMachineRestored()
	{
		// TODO Auto-generated method stub

	}

	@Override
	protected void onMachinePaused()
	{
		// TODO Auto-generated method stub

	}

	@Override
	protected void onMachineDisassembled()
	{
		if( WORLD.isRemote )
			markMultiblockForRenderUpdate();
		else
			Logger.getLogger( ModInfo.MODID ).info( "Multiblock Disassembled" );
	}

	@Override
	protected int getMinimumNumberOfBlocksForAssembledMachine()
	{
		// Vender is 2x2x3
		return 12;
	}

	@Override
	protected int getMaximumXSize()
	{
		return 2;
	}

	@Override
	protected int getMaximumZSize()
	{
		return 2;
	}

	@Override
	protected int getMaximumYSize()
	{
		return 3;
	}

	@Override
	protected int getMinimumXSize()
	{
		return 2;
	}

	@Override
	protected int getMinimumZSize()
	{
		return 2;
	}

	@Override
	protected int getMinimumYSize()
	{
		return 3;
	}

	@Override
	protected void onAssimilate( MultiblockControllerBase assimilated )
	{
		// TODO Auto-generated method stub

	}

	@Override
	protected void onAssimilated( MultiblockControllerBase assimilator )
	{
		// TODO Auto-generated method stub

	}

	@Override
	protected boolean updateServer()
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void updateClient()
	{
		// TODO Auto-generated method stub

	}

	@Override
	protected boolean isBlockGoodForFrame( World world, int x, int y, int z, IMultiblockValidator validatorCallback )
	{
		validatorCallback.setLastError( "zerocore:api.multiblock.validation.invalid_block", x, y, z );
		return false;
	}

	@Override
	protected boolean isBlockGoodForTop( World world, int x, int y, int z, IMultiblockValidator validatorCallback )
	{
		validatorCallback.setLastError( "zerocore:api.multiblock.validation.invalid_block", x, y, z );
		return false;
	}

	@Override
	protected boolean isBlockGoodForBottom( World world, int x, int y, int z, IMultiblockValidator validatorCallback )
	{
		validatorCallback.setLastError( "zerocore:api.multiblock.validation.invalid_block", x, y, z );
		return false;
	}

	@Override
	protected boolean isBlockGoodForSides( World world, int x, int y, int z, IMultiblockValidator validatorCallback )
	{
		validatorCallback.setLastError( "zerocore:api.multiblock.validation.invalid_block", x, y, z );
		return false;
	}

	@Override
	protected boolean isBlockGoodForInterior( World world, int x, int y, int z, IMultiblockValidator validatorCallback )
	{
		validatorCallback.setLastError( "zerocore:api.multiblock.validation.invalid_block", x, y, z );
		return false;
	}

	@Override
	protected void syncDataFrom( NBTTagCompound data, SyncReason syncReason )
	{
		// TODO Auto-generated method stub

	}

	@Override
	protected void syncDataTo( NBTTagCompound data, SyncReason syncReason )
	{
		// TODO Auto-generated method stub

	}
}
