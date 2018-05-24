package io.github.tehstoneman.cashcraft.proxies;

import io.github.tehstoneman.cashcraft.CashCraft;
import io.github.tehstoneman.cashcraft.common.item.ItemCash.EnumCoinValue;
import io.github.tehstoneman.cashcraft.common.item.ItemCashCraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.client.model.ModelLoader;

public class ClientProxy extends CommonProxy
{
	@Override
	public void preInit()
	{
		super.preInit();

		ModelResourceLocation itemModelResourceLocation;

		// Register coin / note variants
		for( final EnumCoinValue value : EnumCoinValue.values() )
		{
			itemModelResourceLocation = new ModelResourceLocation( CashCraft.modAsset( value.getTextureName() ), "inventory" );
			ModelLoader.setCustomModelResourceLocation( ItemCashCraft.COIN, value.getMetadata(), itemModelResourceLocation );
		}
		//itemModelResourceLocation = new ModelResourceLocation( CashCraft.modAsset( ItemCashCraft.MONEY_POUCH.getName() ), "inventory" );
		//ModelLoader.setCustomModelResourceLocation( ItemCashCraft.MONEY_POUCH, 0, itemModelResourceLocation );
	}

	@Override
	public void Init()
	{
		super.Init();
	}

	@Override
	public void postInit()
	{
		super.postInit();
		// TODO Auto-generated method stub
	}

	@Override
	public String localize( String unlocalized, Object... args )
	{
		return I18n.format( unlocalized, args );
	}
}
