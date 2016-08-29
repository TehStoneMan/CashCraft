package io.github.tehstoneman.cashcraft.common.block;

import io.github.tehstoneman.cashcraft.common.multiblock.VenderMultiblockController;
import io.github.tehstoneman.cashcraft.common.tileentity.TileEntityVender;
import it.zerono.mods.zerocore.api.multiblock.IMultiblockPart;
import it.zerono.mods.zerocore.api.multiblock.MultiblockControllerBase;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockVender extends Block
{
	public static PropertyEnum PROPERTYSTATE = PropertyEnum.create( "state", EnumState.class );

	public BlockVender( Material materialIn )
	{
		super( materialIn );
		setHardness( 2.0f );
		setResistance( 600.0f );
		setLightOpacity( 1 );
	}

	// Tile Entity

	@Override
	public boolean hasTileEntity( IBlockState state )
	{
		return true;
	}

	@Override
	public TileEntity createTileEntity( World world, IBlockState state )
	{
		return new TileEntityVender();
	}

	// Rendering

	@Override
	public boolean isOpaqueCube( IBlockState iBlockState )
	{
		return iBlockState.getValue( PROPERTYSTATE ) != EnumState.BUILT;
	}

	@Override
	public boolean isFullCube( IBlockState iBlockState )
	{
		return iBlockState.getValue( PROPERTYSTATE ) != EnumState.BUILT;
	}

	@Override
	@SideOnly( Side.CLIENT )
	public BlockRenderLayer getBlockLayer()
	{
		return BlockRenderLayer.SOLID;
	}

	@Override
	public EnumBlockRenderType getRenderType( IBlockState state )
	{
		if( state.getValue( PROPERTYSTATE ) == EnumState.BUILT )
			return EnumBlockRenderType.INVISIBLE;
		return EnumBlockRenderType.MODEL;
	}

	// Player Interaction

	/*
	 * @Override
	 * public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem,
	 * EnumFacing side, float hitX, float hitY, float hitZ)
	 * {
	 * if( !CashCraftAPI.economy.isEnabled() || !( (IExtendedBlockState)getExtendedState( state, worldIn, pos ) ).getValue( BUILT ) )
	 * return false;
	 *
	 * if( worldIn.isRemote )
	 * return true;
	 * else
	 * {
	 * final TileEntity tileentity = worldIn.getTileEntity( pos );
	 *
	 * if( tileentity instanceof TileEntityVender )
	 * CashCraftAPI.trade.openTradeGui( playerIn, EnumTradeType.OWNER, worldIn, pos );
	 *
	 * return true;
	 * }
	 * }
	 */

	@Override
	public void onBlockPlacedBy( World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack )
	{
		final TileEntity tileEntity = worldIn.getTileEntity( pos );
		if( tileEntity != null && tileEntity instanceof TileEntityVender )
		{
			final TileEntityVender vender = (TileEntityVender)tileEntity;
			vender.setOwner( (EntityPlayer)placer );

			/*
			 * if( vender.checkMultiBlockForm())
			 * {
			 * vender.setMaster( true );
			 * vender.setupStructure();
			 * }
			 */
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

	@Override
	public void breakBlock( World worldIn, BlockPos pos, IBlockState state )
	{
		final TileEntity tileEntity = worldIn.getTileEntity( pos );

		if( tileEntity instanceof TileEntityVender )
		{
			// TileEntityVender vender = (TileEntityVender)tileEntity;

			// InventoryHelper.dropInventoryItems(worldIn, pos, (TileEntityFurnace)tileentity);
			// worldIn.updateComparatorOutputLevel(pos, this);
		}
		// makeInvalid( worldIn, pos, state );

		super.breakBlock( worldIn, pos, state );
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
	public void onNeighborChange( IBlockAccess world, BlockPos pos, BlockPos neighbor )
	{
		// checkInvalid( world, pos, neighbor );

		super.onNeighborChange( world, pos, neighbor );
	}

	protected IMultiblockPart getMultiblockPartAt( IBlockAccess world, BlockPos position )
	{
		final TileEntity te = world.getTileEntity( position );
		return te instanceof IMultiblockPart ? (IMultiblockPart)te : null;
	}

	protected MultiblockControllerBase getMultiblockController( IBlockAccess world, BlockPos position )
	{
		final IMultiblockPart part = getMultiblockPartAt( world, position );
		return null != part ? part.getMultiblockController() : null;
	}

	protected VenderMultiblockController getVenderController( IBlockAccess world, BlockPos position )
	{
		final MultiblockControllerBase controller = getMultiblockController( world, position );
		return controller instanceof VenderMultiblockController ? (VenderMultiblockController)controller : null;
	}

	// Block State

	@Override
	public IBlockState getActualState( IBlockState state, IBlockAccess world, BlockPos position )
	{
		final IMultiblockPart part = getMultiblockPartAt( world, position );

		if( part instanceof TileEntityVender )
		{
			final TileEntityVender vender = (TileEntityVender)part;
			final boolean assembled = vender.isConnected() && vender.getMultiblockController().isAssembled();

			// state = state.withProperty(ASSEMBLED, assembled);
		}

		return state;
	}

	@Override
	protected BlockStateContainer createBlockState()
	{
		final IProperty[] listedProperties = new IProperty[] { PROPERTYSTATE };
		return new BlockStateContainer( this, listedProperties );
	}

	public static void setState( EnumState state, World worldIn, BlockPos pos )
	{
		final IBlockState iBlockState = worldIn.getBlockState( pos );
		if( !( iBlockState.getBlock() instanceof BlockVender ) )
			return;

		if( iBlockState.getValue( PROPERTYSTATE ) == state )
			return;

		worldIn.setBlockState( pos, CashCraftBlocks.blockVender.getDefaultState().withProperty( PROPERTYSTATE, state ) );
	}

	@Override
	public IBlockState getStateFromMeta( int meta )
	{
		final EnumState state = EnumState.byMetadata( meta );
		return getDefaultState().withProperty( PROPERTYSTATE, state );
	}

	@Override
	public int getMetaFromState( IBlockState blockState )
	{
		return ( (EnumState)blockState.getValue( PROPERTYSTATE ) ).getMetadata();
	}

	public static enum EnumState implements IStringSerializable
	{
		UNBUILT( 0, "unbuilt" ), BUILT( 1, "built" ), MASTER( 2, "master" );

		private int					meta;
		private String				name;
		private static EnumState[]	META_LOOKUP	= new EnumState[values().length];

		private EnumState( int meta, String name )
		{
			this.meta = meta;
			this.name = name;
		}

		public int getMetadata()
		{
			return meta;
		}

		@Override
		public String getName()
		{
			return name;
		}

		@Override
		public String toString()
		{
			return name;
		}

		public static EnumState byMetadata( int meta )
		{
			if( meta < 0 || meta >= META_LOOKUP.length )
				meta = 0;

			return META_LOOKUP[meta];
		}

		static
		{
			for( final EnumState state : values() )
				META_LOOKUP[state.getMetadata()] = state;
		}
	}
}