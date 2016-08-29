package io.github.tehstoneman.cashcraft.client.model;

import java.io.IOException;
import java.util.Collection;
import java.util.logging.Logger;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;

import io.github.tehstoneman.cashcraft.CashCraft;
import io.github.tehstoneman.cashcraft.ModInfo;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.common.model.IModelState;

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
	public IBakedModel bake( IModelState state, VertexFormat format, Function< ResourceLocation, TextureAtlasSprite > bakedTextureGetter )
	{
		try
		{
			IModel model = ModelLoaderRegistry.getModel( MODEL_UNBUILT );
			final IBakedModel unbuiltModel = model.bake( state, format, bakedTextureGetter );

			model = ModelLoaderRegistry.getModel( MODEL_BUILT );
			final IBakedModel builtModel = model.bake( state, format, bakedTextureGetter );

			return new TradeBoothBakedModel( unbuiltModel, builtModel );
		}
		catch( final Exception e )
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
