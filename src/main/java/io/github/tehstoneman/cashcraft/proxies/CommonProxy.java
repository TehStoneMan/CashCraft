package io.github.tehstoneman.cashcraft.proxies;

import io.github.tehstoneman.cashcraft.CashCraft;
import io.github.tehstoneman.cashcraft.client.gui.CashCraftGuiHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class CommonProxy
{
	public void preInit()
	{}

	public void Init()
	{
		NetworkRegistry.INSTANCE.registerGuiHandler( CashCraft.instance, new CashCraftGuiHandler() );
	}

	public void postInit()
	{}

	public String localize( String unlocalized, Object... args )
	{
		return unlocalized;
	}
}
