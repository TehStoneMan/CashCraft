package io.github.tehstoneman.cashcraft.proxies;

import io.github.tehstoneman.cashcraft.ModInfo;
import io.github.tehstoneman.cashcraft.client.creativetab.CashCraftTab;
import io.github.tehstoneman.cashcraft.common.item.CashCraftItems;
import io.github.tehstoneman.cashcraft.util.ModSettings;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;

public class CommonProxy
{
	CashCraftTab	cashCraftTab;
	
	// Networking
	public static SimpleNetworkWrapper simpleNetworkWrapper;
	
	public static final byte MESSAGE_ID_UPDATE = 1;

	public void preInit()
	{
		cashCraftTab = new CashCraftTab( ModInfo.MODID );

		CashCraftItems.RegisterItems();
	}

	public void Init()
	{
		if( ModSettings.makeChange )
			CashCraftItems.RegisterRecipes();
	}

	public void postInit()
	{
		// TODO Auto-generated method stub

	}

}
