package io.github.tehstoneman.cashcraft.block;

import java.util.LinkedList;
import java.util.List;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ItemOverrideList;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.model.Attributes;
import net.minecraftforge.common.property.IExtendedBlockState;

public class TradeBoothBakedModel implements IBakedModel
{
	private final IBakedModel	unbuiltModel;
	private final IBakedModel	builtModel;

	public TradeBoothBakedModel( IBakedModel unbuiltModel, IBakedModel builtModel )
	{
		this.unbuiltModel = unbuiltModel;
		this.builtModel = builtModel;
	}

	/*
	@Override
	public List< BakedQuad > getFaceQuads( EnumFacing side )
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public List< BakedQuad > getGeneralQuads()
	{
		throw new UnsupportedOperationException();
	}
	*/

	@Override
	public boolean isAmbientOcclusion()
	{
		return unbuiltModel.isAmbientOcclusion();
	}

	@Override
	public boolean isGui3d()
	{
		return unbuiltModel.isGui3d();
	}

	@Override
	public boolean isBuiltInRenderer()
	{
		return false;
	}

	@Override
	public TextureAtlasSprite getParticleTexture()
	{
		return unbuiltModel.getParticleTexture();
	}

	@Override
	public ItemCameraTransforms getItemCameraTransforms()
	{
		return unbuiltModel.getItemCameraTransforms();
	}

	/*
	@Override
	public VertexFormat getFormat()
	{
		return Attributes.DEFAULT_BAKED_FORMAT;
	}
	*/

	/*
	@Override
	public IBakedModel handleBlockState( IBlockState state )
	{
		if( state instanceof IExtendedBlockState )
		{
			final IExtendedBlockState iExtendedBlockState = (IExtendedBlockState)state;
			return new AssembledBakedModel( iExtendedBlockState );
		}
		return new AssembledBakedModel();
	}
	*/

	public class AssembledBakedModel implements IBakedModel
	{
		private boolean	built	= false;
		private boolean	master	= false;
		private boolean	center	= false;
		private boolean	top		= false;
		private boolean	bottom	= false;

		public AssembledBakedModel()
		{}

		public AssembledBakedModel( IExtendedBlockState iExtendedBlockState )
		{
			built  = iExtendedBlockState.getValue( BlockVender.BUILT );
			master = iExtendedBlockState.getValue( BlockVender.MASTER );
			center = iExtendedBlockState.getValue( BlockVender.CENTER );
			top    = iExtendedBlockState.getValue( BlockVender.TOP );
			bottom = iExtendedBlockState.getValue( BlockVender.BOTTOM );
		}

		/*
		@Override
		public List< BakedQuad > getFaceQuads( EnumFacing side )
		{
			final List< BakedQuad > allFaceQuads = new LinkedList< BakedQuad >();
			if( built )
			{
				if( master )
					allFaceQuads.addAll( builtModel.getFaceQuads( side ) );
				/*
				 * if( center )
				 * allFaceQuads.addAll( centerModel.getFaceQuads( side ) );
				 * else
				 * if( top )
				 * allFaceQuads.addAll( canopySideModel.getFaceQuads( side ) );
				 * else
				 * allFaceQuads.addAll( baseModel.getFaceQuads( side ) );
				 */
		/*
			}
			else
				allFaceQuads.addAll( unbuiltModel.getFaceQuads( side ) );
			return allFaceQuads;
		}
		*/

		/*
		@Override
		public List< BakedQuad > getGeneralQuads()
		{
			final List< BakedQuad > allGeneralQuads = new LinkedList< BakedQuad >();
			if( built )
			{
				if( master )
				{
					final EnumWorldBlockLayer layer = MinecraftForgeClient.getRenderLayer();
					allGeneralQuads.addAll( builtModel.getGeneralQuads() );
				}
				/*
				 * if( center )
				 * allGeneralQuads.addAll( centerModel.getGeneralQuads() );
				 * else
				 * if( top )
				 * allGeneralQuads.addAll( canopySideModel.getGeneralQuads() );
				 * else
				 * allGeneralQuads.addAll( baseModel.getGeneralQuads() );
				 */
		/*
			}
			else
				allGeneralQuads.addAll( unbuiltModel.getGeneralQuads() );
			return allGeneralQuads;
		}
		*/

		@Override
		public boolean isAmbientOcclusion()
		{
			return unbuiltModel.isAmbientOcclusion();
		}

		@Override
		public boolean isGui3d()
		{
			return unbuiltModel.isGui3d();
		}

		@Override
		public boolean isBuiltInRenderer()
		{
			return false;
		}

		@Override
		public TextureAtlasSprite getParticleTexture()
		{
			return unbuiltModel.getParticleTexture();
		}

		@Override
		public ItemCameraTransforms getItemCameraTransforms()
		{
			return unbuiltModel.getItemCameraTransforms();
		}

		@Override
		public List< BakedQuad > getQuads( IBlockState state, EnumFacing side, long rand )
		{
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public ItemOverrideList getOverrides()
		{
			// TODO Auto-generated method stub
			return null;
		}
	}

	@Override
	public List< BakedQuad > getQuads( IBlockState state, EnumFacing side, long rand )
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemOverrideList getOverrides()
	{
		// TODO Auto-generated method stub
		return null;
	}
}
