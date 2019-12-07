package io.github.tehstoneman.cashcraft.config;

import io.github.tehstoneman.cashcraft.CashCraft;
import io.github.tehstoneman.cashcraft.ModInfo;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber( modid = ModInfo.MOD_ID )
public class LoadLootTable
{
	@SubscribeEvent
	public void onLootTableLoad( LootTableLoadEvent event )
	{
		CashCraft.LOGGER.info( "onLootTableLoad ==== {} ====", event );
	}
}
