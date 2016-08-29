package io.github.tehstoneman.cashcraft.util;

import io.github.tehstoneman.cashcraft.ModInfo;
import io.github.tehstoneman.cashcraft.client.model.TradeBoothModel;

import java.io.IOException;

import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ICustomModelLoader;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.ModelLoaderRegistry;

public class CashCraftModelLoader implements ICustomModelLoader
{
	public String	MODEL_LOCATION	= "models/block/smartmodel/";

	@Override
	public void onResourceManagerReload( IResourceManager resourceManager )
	{
		// TODO Auto-generated method stub
	}

	@Override
	public boolean accepts( ResourceLocation modelLocation )
	{
		return modelLocation.getResourceDomain().equals( ModInfo.MODID.toLowerCase() )
				&& modelLocation.getResourcePath().startsWith( MODEL_LOCATION );
	}

	@Override
	public IModel loadModel( ResourceLocation modelLocation ) throws IOException
	{
		final String resourcePath = modelLocation.getResourcePath();
		if( !resourcePath.startsWith( MODEL_LOCATION ) )
			assert false : "loadModel expected " + MODEL_LOCATION + " but found " + resourcePath;
		final String modelName = resourcePath.substring( MODEL_LOCATION.length() );
		
		if( modelName.equals( "vender" ) )
			return new TradeBoothModel();
		else
			return ModelLoaderRegistry.getMissingModel();
	}
}
