package io.github.tehstoneman.cashcraft.proxies;

import io.github.tehstoneman.cashcraft.CashCraft;
import io.github.tehstoneman.cashcraft.item.CashCraftItems;
import io.github.tehstoneman.cashcraft.item.ItemCash;
import io.github.tehstoneman.cashcraft.item.ItemCash.EnumCoinValue;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.util.ResourceLocation;

public class ClientProxy extends CommonProxy
{
	@Override
	public void preInit()
	{
		super.preInit();
		// TODO Auto-generated method stub
	}

	@Override
	public void Init()
	{
		super.Init();

		ModelBakery.registerItemVariants( CashCraftItems.itemCoin,
				new ResourceLocation( CashCraft.modAsset( EnumCoinValue.byMetadata(  0 ).getTextureName() ) ),
				new ResourceLocation( CashCraft.modAsset( EnumCoinValue.byMetadata(  1 ).getTextureName() ) ),
				new ResourceLocation( CashCraft.modAsset( EnumCoinValue.byMetadata(  2 ).getTextureName() ) ),
				new ResourceLocation( CashCraft.modAsset( EnumCoinValue.byMetadata(  3 ).getTextureName() ) ),
				new ResourceLocation( CashCraft.modAsset( EnumCoinValue.byMetadata(  4 ).getTextureName() ) ),
				new ResourceLocation( CashCraft.modAsset( EnumCoinValue.byMetadata(  5 ).getTextureName() ) ),
				new ResourceLocation( CashCraft.modAsset( EnumCoinValue.byMetadata(  6 ).getTextureName() ) ),
				new ResourceLocation( CashCraft.modAsset( EnumCoinValue.byMetadata(  7 ).getTextureName() ) ),
				new ResourceLocation( CashCraft.modAsset( EnumCoinValue.byMetadata(  8 ).getTextureName() ) ),
				new ResourceLocation( CashCraft.modAsset( EnumCoinValue.byMetadata(  9 ).getTextureName() ) ),
				new ResourceLocation( CashCraft.modAsset( EnumCoinValue.byMetadata( 10 ).getTextureName() ) ),
				new ResourceLocation( CashCraft.modAsset( EnumCoinValue.byMetadata( 11 ).getTextureName() ) ),
				new ResourceLocation( CashCraft.modAsset( EnumCoinValue.byMetadata( 12 ).getTextureName() ) ),
				new ResourceLocation( CashCraft.modAsset( EnumCoinValue.byMetadata( 13 ).getTextureName() ) ),
				new ResourceLocation( CashCraft.modAsset( EnumCoinValue.byMetadata( 14 ).getTextureName() ) ) );
		
		for( EnumCoinValue value : EnumCoinValue.values() )
		{
			String itemModelName = value.getTextureName();
			ModelResourceLocation itemModelResourceLocation = new ModelResourceLocation( CashCraft.modAsset( itemModelName ), "inventory" );
			Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register( CashCraftItems.itemCoin, value.getMetadata(), itemModelResourceLocation );
		}
	}

	@Override
	public void postInit()
	{
		super.postInit();
		// TODO Auto-generated method stub
	}

}
