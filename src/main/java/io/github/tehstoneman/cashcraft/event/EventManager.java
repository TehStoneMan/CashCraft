package io.github.tehstoneman.cashcraft.event;

import net.minecraft.client.Minecraft;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.ItemPickupEvent;

public class EventManager
{
	private static Minecraft	mc	= Minecraft.getMinecraft();

	@SubscribeEvent
	public void onEntityItemPickupEvent( EntityItemPickupEvent event )
	{
	}

	@SubscribeEvent
	public void onPlayerItemPickupEvent( ItemPickupEvent event )
	{
	}

	@SubscribeEvent
	public void onLivingDeathEvent( LivingDeathEvent event )
	{
		if( event.entityLiving.worldObj.isRemote )
			return;
	}
}
