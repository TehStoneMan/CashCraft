package io.github.tehstoneman.cashcraft.event;

import io.github.tehstoneman.cashcraft.common.tileentity.TileEntityVender;
import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventManager
{
	private static Minecraft	mc	= Minecraft.getMinecraft();

	@SubscribeEvent
	public void onBreakBlock( BreakEvent event )
	{
		World world = event.getWorld();
		TileEntity tileEntity = world.getTileEntity( event.getPos() );
		if( tileEntity != null && tileEntity instanceof TileEntityVender )
		{
			if( ((TileEntityVender)tileEntity).getOwnderID() != null && !event.getPlayer().getUniqueID().equals( ((TileEntityVender)tileEntity).getOwnderID() ) )
				event.setCanceled( true );
		}
	}
	/*
	@SubscribeEvent
	public void onModelBakeEvent( ModelBakeEvent event ) throws IOException
	{
		final Function< ResourceLocation, TextureAtlasSprite > bakedTextureGetter = new Function< ResourceLocation, TextureAtlasSprite >()
		{
			@Override
			public TextureAtlasSprite apply( ResourceLocation resourceLocation )
			{
				return Minecraft.getMinecraft().getTextureMapBlocks().registerSprite( resourceLocation );
			}
		};
				final OBJModel model = (OBJModel)OBJLoader.instance.loadModel( new ModelResourceLocation( ModInfo.MODID, "block/trade_block.obj" ) );
				final IBakedModel bakedModel = model.bake( model.getDefaultState(), Attributes.DEFAULT_BAKED_FORMAT, bakedTextureGetter );
				event.modelRegistry.putObject( new ModelResourceLocation( ModInfo.MODID, "block/trade_block.obj" ), bakedModel );
	}
	*/
	
	/*
	 * @SubscribeEvent
	 * public void onEntityItemPickupEvent( EntityItemPickupEvent event )
	 * {
	 * }
	 * 
	 * @SubscribeEvent
	 * public void onPlayerItemPickupEvent( ItemPickupEvent event )
	 * {
	 * }
	 * 
	 * @SubscribeEvent
	 * public void onLivingDeathEvent( LivingDeathEvent event )
	 * {
	 * if( event.entityLiving.worldObj.isRemote )
	 * return;
	 * }
	 */
}
