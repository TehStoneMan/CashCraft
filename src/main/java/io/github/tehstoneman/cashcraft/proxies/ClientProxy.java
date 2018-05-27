package io.github.tehstoneman.cashcraft.proxies;

import net.minecraft.client.resources.I18n;

public class ClientProxy extends CommonProxy
{
	@Override
	public void preInit()
	{
		super.preInit();
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
	}

	@Override
	public String localize( String unlocalized, Object... args )
	{
		return I18n.format( unlocalized, args );
	}
}
