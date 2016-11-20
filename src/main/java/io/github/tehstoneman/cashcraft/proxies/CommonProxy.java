package io.github.tehstoneman.cashcraft.proxies;

import io.github.tehstoneman.cashcraft.CashCraft;
import io.github.tehstoneman.cashcraft.ModInfo;
import io.github.tehstoneman.cashcraft.client.creativetab.CashCraftTab;
import io.github.tehstoneman.cashcraft.client.gui.CashCraftGuiHandler;
import io.github.tehstoneman.cashcraft.common.item.CashCraftItems;
import io.github.tehstoneman.cashcraft.util.ModSettings;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;

public class CommonProxy
{
	CashCraftTab	cashCraftTab;
	
	public void preInit()
	{
		cashCraftTab = new CashCraftTab( ModInfo.MODID );

		CashCraftItems.RegisterItems();
	}

	public void Init()
	{
		NetworkRegistry.INSTANCE.registerGuiHandler( CashCraft.instance, new CashCraftGuiHandler() );
		
		CashCraftItems.RegisterRecipes();
	}

	public void postInit()
	{
		// TODO Auto-generated method stub

	}

}
