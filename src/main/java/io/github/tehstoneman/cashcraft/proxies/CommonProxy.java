package io.github.tehstoneman.cashcraft.proxies;

import io.github.tehstoneman.cashcraft.ModInfo;
import io.github.tehstoneman.cashcraft.creativetab.CashCraftTab;
import io.github.tehstoneman.cashcraft.item.CashCraftItems;
import io.github.tehstoneman.cashcraft.util.ModSettings;

public class CommonProxy
{
	CashCraftTab	cashCraftTab;

	public void preInit()
	{
		cashCraftTab = new CashCraftTab( ModInfo.MODID );
	}

	public void Init()
	{
		CashCraftItems.RegisterItems();
		if( ModSettings.makeChange )
			CashCraftItems.RegisterRecipes();
	}

	public void postInit()
	{
		// TODO Auto-generated method stub

	}

}
