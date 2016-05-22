package io.github.tehstoneman.cashcraft.block;

import io.github.tehstoneman.cashcraft.CashCraft;

import java.io.IOException;
import java.util.Collection;

import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IFlexibleBakedModel;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.IModelState;
import net.minecraftforge.client.model.ModelLoaderRegistry;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;

public class TradeBoothModel implements IModel
{
	public static final ResourceLocation		TEXTURE_SHEET	= new ResourceLocation( CashCraft.modAsset( "blocks/vender" ) );

	public static final ModelResourceLocation	MODEL_UNBUILT	= new ModelResourceLocation( CashCraft.modAsset( "parts/vender_unbuilt" ) );
	public static final ModelResourceLocation	MODEL_BUILT		= new ModelResourceLocation( CashCraft.modAsset( "parts/vender_built" ) );
	//public static final ModelResourceLocation	MODEL_ALT		= new ModelResourceLocation( CashCraft.modAsset( "parts/vender_alt" ) );
	//public static final ModelResourceLocation	MODEL_GLASS		= new ModelResourceLocation( CashCraft.modAsset( "parts/vender_glass" ) );

	@Override
	public Collection< ResourceLocation > getDependencies()
	{
		return ImmutableList.copyOf( new ResourceLocation[] { MODEL_UNBUILT, MODEL_BUILT } );
	}

	@Override
	public Collection< ResourceLocation > getTextures()
	{
		return ImmutableList.copyOf( new ResourceLocation[] { TEXTURE_SHEET } );
	}

	@Override
	public IFlexibleBakedModel bake( IModelState state, VertexFormat format, Function< ResourceLocation, TextureAtlasSprite > bakedTextureGetter )
	{
		try
		{
			IModel model = ModelLoaderRegistry.getModel( MODEL_UNBUILT );
			final IBakedModel unbuiltModel = model.bake( state, format, bakedTextureGetter );

			model = ModelLoaderRegistry.getModel( MODEL_BUILT );
			final IBakedModel builtModel = model.bake( state, format, bakedTextureGetter );

			return new TradeBoothBakedModel( unbuiltModel, builtModel );
		}
		catch( final IOException e )
		{
			System.err.println( "TradeBoothModel.bake() failed due to exception:" + e );
			return ModelLoaderRegistry.getMissingModel().bake( state, format, bakedTextureGetter );
		}
	}

	@Override
	public IModelState getDefaultState()
	{
		return null;
	}

}
