package io.github.tehstoneman.cashcraft.event;

import io.github.tehstoneman.cashcraft.CashCraft;
import io.github.tehstoneman.cashcraft.item.CashItems;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.AchievementList;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemPickupEvent;

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
