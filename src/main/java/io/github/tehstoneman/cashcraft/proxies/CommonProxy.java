package io.github.tehstoneman.cashcraft.proxies;

import io.github.tehstoneman.cashcraft.CashCraft;
import io.github.tehstoneman.cashcraft.ModInfo;
import io.github.tehstoneman.cashcraft.block.CashCraftBlocks;
import io.github.tehstoneman.cashcraft.client.gui.GuiHandler;
import io.github.tehstoneman.cashcraft.creativetab.CashCraftTab;
import io.github.tehstoneman.cashcraft.item.CashCraftItems;
import io.github.tehstoneman.cashcraft.tileentity.TileEntityVender;
import io.github.tehstoneman.cashcraft.util.ModSettings;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy
{
	CashCraftTab	cashCraftTab;

	public void preInit()
	{
		cashCraftTab = new CashCraftTab( ModInfo.MODID );

		CashCraftItems.RegisterItems();
		CashCraftBlocks.RegisterBlocks();
		GameRegistry.registerTileEntity( TileEntityVender.class, "tileentity_vender" );
		
		NetworkRegistry.INSTANCE.registerGuiHandler( CashCraft.instance, new GuiHandler() );
	}

	public void Init()
	{
		if( ModSettings.makeChange )
			CashCraftItems.RegisterRecipes();

		CashCraftBlocks.RegisterRecipes();
	}

	public void postInit()
	{
		// TODO Auto-generated method stub

	}

}
