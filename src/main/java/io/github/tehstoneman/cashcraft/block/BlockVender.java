package io.github.tehstoneman.cashcraft.block;

import io.github.tehstoneman.cashcraft.tileentity.TileEntityVender;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.property.ExtendedBlockState;
import net.minecraftforge.common.property.IExtendedBlockState;
import net.minecraftforge.common.property.IUnlistedProperty;
import net.minecraftforge.common.property.Properties;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockVender extends Block implements ITileEntityProvider
{
	public static final IUnlistedProperty< Boolean >	BUILT	= new Properties.PropertyAdapter< Boolean >( PropertyBool.create( "built" ) );
	public static final IUnlistedProperty< Boolean >	MASTER	= new Properties.PropertyAdapter< Boolean >( PropertyBool.create( "master" ) );
	public static final IUnlistedProperty< Boolean >	CENTER	= new Properties.PropertyAdapter< Boolean >( PropertyBool.create( "center" ) );
	public static final IUnlistedProperty< Boolean >	TOP		= new Properties.PropertyAdapter< Boolean >( PropertyBool.create( "center" ) );
	public static final IUnlistedProperty< Boolean >	BOTTOM	= new Properties.PropertyAdapter< Boolean >( PropertyBool.create( "center" ) );
	public static final IUnlistedProperty< Boolean >	NORTH	= new Properties.PropertyAdapter< Boolean >( PropertyBool.create( "center" ) );
	public static final IUnlistedProperty< Boolean >	SOUTH	= new Properties.PropertyAdapter< Boolean >( PropertyBool.create( "center" ) );
	public static final IUnlistedProperty< Boolean >	EAST	= new Properties.PropertyAdapter< Boolean >( PropertyBool.create( "center" ) );
	public static final IUnlistedProperty< Boolean >	WEST	= new Properties.PropertyAdapter< Boolean >( PropertyBool.create( "center" ) );

	public BlockVender( Material materialIn )
	{
		super( materialIn );
		this.setHardness( 2.0f );
		this.setResistance( 600.0f );
	}

	@Override
	public Item getItemDropped( IBlockState state, Random rand, int fortune )
	{
		return Item.getItemFromBlock( CashCraftBlocks.blockVender );
	}

	/*
	 * @Override
	 *
	 * @SideOnly( Side.CLIENT )
	 * public EnumWorldBlockLayer getBlockLayer()
	 * {
	 * return EnumWorldBlockLayer.CUTOUT;
	 * }
	 */

	@Override
	public boolean onBlockActivated( World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY,
			float hitZ )
	{
		if( !( (IExtendedBlockState)getExtendedState( state, worldIn, pos ) ).getValue( BUILT ) )
			return false;

		if( worldIn.isRemote )
			return true;
		else
		{
			final TileEntity tileentity = worldIn.getTileEntity( pos );

			if( tileentity instanceof TileEntityVender )
			{
				// playerIn.displayGUIChest((TileEntityFurnace)tileentity);
			}

			return true;
		}
	}

	@Override
	public TileEntity createNewTileEntity( World worldIn, int meta )
	{
		return new TileEntityVender();
	}

	@Override
	public boolean isFullCube()
	{
		return false;
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public void onBlockPlacedBy( World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack )
	{
		final TileEntity tileEntity = worldIn.getTileEntity( pos );
		if( tileEntity != null && tileEntity instanceof TileEntityVender )
		{
			final TileEntityVender vender = (TileEntityVender)tileEntity;
			vender.setOwner( (EntityPlayer)placer );
		}
		/*
		 * if (stack.hasDisplayName())
		 * {
		 *
		 * {
		 * ((TileEntityTrade)tileentity).setCustomInventoryName(stack.getDisplayName());
		 * }
		 * }
		 */
	}

	public void checkInvalid( World worldIn, BlockPos pos, IBlockState state )
	{
		// Check for invalid structure
		final IExtendedBlockState extendedState = (IExtendedBlockState)getExtendedState( state, worldIn, pos );
		if( extendedState.getValue( BUILT ) )
		{
			final TileEntityVender tileEntity = (TileEntityVender)worldIn.getTileEntity( pos );
			if( extendedState.getValue( MASTER ) )
			{
				if( !tileEntity.checkMultiBlockForm() )
					tileEntity.resetStructure();
			}
			else
				// Get master block and reset structure
				if( tileEntity.checkForMaster() )
				{
					final TileEntityVender master = (TileEntityVender)worldIn.getTileEntity( tileEntity.getMasterPos() );
					if( !master.checkMultiBlockForm() )
						master.resetStructure();
				}
				else
					tileEntity.reset();
		}
	}

	public void makeInvalid( World worldIn, BlockPos pos, IBlockState state )
	{
		// Check for invalid structure
		final IExtendedBlockState extendedState = (IExtendedBlockState)getExtendedState( state, worldIn, pos );
		if( extendedState.getValue( BUILT ) )
		{
			final TileEntityVender tileEntity = (TileEntityVender)worldIn.getTileEntity( pos );
			if( extendedState.getValue( MASTER ) )
				tileEntity.resetStructure();
			else
				// Get master block and reset structure
				if( tileEntity.checkForMaster() )
				{
					final TileEntityVender master = (TileEntityVender)worldIn.getTileEntity( tileEntity.getMasterPos() );
					master.resetStructure();
				}
				else
					tileEntity.reset();
		}
	}

	@Override
	public void onNeighborBlockChange( World worldIn, BlockPos pos, IBlockState state, Block neighborBlock )
	{
		checkInvalid( worldIn, pos, state );

		super.onNeighborBlockChange( worldIn, pos, state, neighborBlock );
	}

	@Override
	public void breakBlock( World worldIn, BlockPos pos, IBlockState state )
	{
		final TileEntity tileEntity = worldIn.getTileEntity( pos );

		if( tileEntity instanceof TileEntityVender )
		{
			// TileEntityVender vender = (TileEntityVender)tileEntity;
			/*
			 * InventoryHelper.dropInventoryItems(worldIn, pos, (TileEntityFurnace)tileentity);
			 * worldIn.updateComparatorOutputLevel(pos, this);
			 */
		}
		makeInvalid( worldIn, pos, state );

		super.breakBlock( worldIn, pos, state );
	}

	@Override
	@SideOnly( Side.CLIENT )
	public Item getItem( World worldIn, BlockPos pos )
	{
		return Item.getItemFromBlock( CashCraftBlocks.blockVender );
	}

	@Override
	protected BlockState createBlockState()
	{
		final IProperty[] listedProperties = new IProperty[0]; // no listed properties
		final IUnlistedProperty[] unlistedProperties = new IUnlistedProperty[] { BUILT, MASTER, CENTER, TOP, BOTTOM, NORTH, SOUTH, EAST, WEST };
		return new ExtendedBlockState( this, listedProperties, unlistedProperties );
	}

	@Override
	public IBlockState getExtendedState( IBlockState state, IBlockAccess world, BlockPos pos )
	{
		if( state instanceof IExtendedBlockState )
		{ // avoid crash in case of mismatch
			IExtendedBlockState retval = (IExtendedBlockState)state;
			final TileEntity tileEntity = world.getTileEntity( pos );
			if( tileEntity != null && tileEntity instanceof TileEntityVender )
			{
				final TileEntityVender tileEntityTrade = (TileEntityVender)tileEntity;

				retval = retval.withProperty( BUILT, tileEntityTrade.isBuilt() );
				retval = retval.withProperty( MASTER, tileEntityTrade.isMaster() );
				retval = retval.withProperty( CENTER, tileEntityTrade.isCenter() );
				retval = retval.withProperty( TOP, tileEntityTrade.isTop() );
				retval = retval.withProperty( BOTTOM, tileEntityTrade.isBottom() );
			}

			return retval;
		}
		return state;
	}
}
