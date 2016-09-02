package io.github.tehstoneman.cashcraft.proxies;

import io.github.tehstoneman.cashcraft.CashCraft;
import io.github.tehstoneman.cashcraft.ModInfo;
import io.github.tehstoneman.cashcraft.common.block.CashCraftBlocks;
import io.github.tehstoneman.cashcraft.common.item.CashCraftItems;
import io.github.tehstoneman.cashcraft.common.item.ItemCash.EnumCoinValue;
import io.github.tehstoneman.cashcraft.network.UpdateMessage;
import io.github.tehstoneman.cashcraft.util.CashCraftModelLoader;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.fml.relauncher.Side;

public class ClientProxy extends CommonProxy
{
	@Override
	public void preInit()
	{
		super.preInit();

		// Load custom models
		OBJLoader.INSTANCE.addDomain( ModInfo.MODID.toLowerCase() );

		/*final StateMapperBase ignoreState = new StateMapperBase()
		{
			@Override
			protected ModelResourceLocation getModelResourceLocation( IBlockState iBlockState )
			{
				return new ModelResourceLocation( CashCraft.modAsset( "vender" ) );
			}
		};
		ModelLoader.setCustomStateMapper( CashCraftBlocks.blockVender, ignoreState );*/

		//ModelLoaderRegistry.registerLoader( new CashCraftModelLoader() );

		// Register coin / note variants
		ModelBakery.registerItemVariants( CashCraftItems.itemCoin,
				new ResourceLocation( CashCraft.modAsset( EnumCoinValue.byMetadata( 0 ).getTextureName() ) ),
				new ResourceLocation( CashCraft.modAsset( EnumCoinValue.byMetadata( 1 ).getTextureName() ) ),
				new ResourceLocation( CashCraft.modAsset( EnumCoinValue.byMetadata( 2 ).getTextureName() ) ),
				new ResourceLocation( CashCraft.modAsset( EnumCoinValue.byMetadata( 3 ).getTextureName() ) ),
				new ResourceLocation( CashCraft.modAsset( EnumCoinValue.byMetadata( 4 ).getTextureName() ) ),
				new ResourceLocation( CashCraft.modAsset( EnumCoinValue.byMetadata( 5 ).getTextureName() ) ),
				new ResourceLocation( CashCraft.modAsset( EnumCoinValue.byMetadata( 6 ).getTextureName() ) ),
				new ResourceLocation( CashCraft.modAsset( EnumCoinValue.byMetadata( 7 ).getTextureName() ) ),
				new ResourceLocation( CashCraft.modAsset( EnumCoinValue.byMetadata( 8 ).getTextureName() ) ),
				new ResourceLocation( CashCraft.modAsset( EnumCoinValue.byMetadata( 9 ).getTextureName() ) ),
				new ResourceLocation( CashCraft.modAsset( EnumCoinValue.byMetadata( 10 ).getTextureName() ) ),
				new ResourceLocation( CashCraft.modAsset( EnumCoinValue.byMetadata( 11 ).getTextureName() ) ),
				new ResourceLocation( CashCraft.modAsset( EnumCoinValue.byMetadata( 12 ).getTextureName() ) ) );

		final ModelResourceLocation itemModelResourceLocation = new ModelResourceLocation( CashCraft.modAsset( "vender" ), "inventory" );
		final int DEFAULT_ITEM_SUBTYPE = 0;
		ModelLoader.setCustomModelResourceLocation( CashCraftBlocks.itemBlockVender, DEFAULT_ITEM_SUBTYPE, itemModelResourceLocation );
		
		CommonProxy.simpleNetworkWrapper.registerMessage( UpdateMessage.Handler.class, UpdateMessage.class, CommonProxy.MESSAGE_ID_UPDATE, Side.CLIENT );
	}

	@Override
	public void Init()
	{
		super.Init();

		for( final EnumCoinValue value : EnumCoinValue.values() )
		{
			final String itemModelName = value.getTextureName();
			final ModelResourceLocation itemModelResourceLocation = new ModelResourceLocation( CashCraft.modAsset( itemModelName ), "inventory" );
			Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
				.register( CashCraftItems.itemCoin, value.getMetadata(), itemModelResourceLocation );
		}
	}

	@Override
	public void postInit()
	{
		super.postInit();
		// TODO Auto-generated method stub
	}

}
