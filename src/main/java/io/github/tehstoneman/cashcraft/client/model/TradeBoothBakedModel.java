package io.github.tehstoneman.cashcraft.client.model;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import io.github.tehstoneman.cashcraft.ModInfo;
import io.github.tehstoneman.cashcraft.common.block.BlockVender;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ItemOverrideList;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.EnumFacing;
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

	@Override
	public List< BakedQuad > getQuads( IBlockState state, EnumFacing side, long rand )
	{
		if( state instanceof IExtendedBlockState )
		{
			Logger.getLogger( ModInfo.MODID ).info( "getQuads" );
			final IExtendedBlockState iExtendedBlockState = (IExtendedBlockState)state;
			/*if( iExtendedBlockState.getValue( BlockVender.BUILT ) )
			{
				if( iExtendedBlockState.getValue( BlockVender.MASTER ) )
					return builtModel.getQuads( state, side, rand );
				return new LinkedList<BakedQuad>();
			}
			else
				return unbuiltModel.getQuads( state, side, rand );*/
		}
		return unbuiltModel.getQuads( state, side, rand );
	}

	@Override
	public ItemOverrideList getOverrides()
	{
		// TODO Auto-generated method stub
		return null;
	}

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
}
