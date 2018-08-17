package io.github.tehstoneman.cashcraft.proxies;

import io.github.tehstoneman.cashcraft.client.ClientEvents;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends CommonProxy
{
	@Override
	public void preInit()
	{
		super.preInit();
		MinecraftForge.EVENT_BUS.register( new ClientEvents() );
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
