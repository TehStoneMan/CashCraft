package io.github.tehstoneman.cashcraft.proxy;

import io.github.tehstoneman.cashcraft.client.gui.GuiMoneyPouch;
import io.github.tehstoneman.cashcraft.common.inventory.CashCraftContainerTypes;
import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class ClientProxy implements IProxy
{
	@Override
	public void setup( FMLCommonSetupEvent event )
	{
		DeferredWorkQueue.runLater( () ->
		{
			ScreenManager.registerFactory( CashCraftContainerTypes.MONEY_POUCH, GuiMoneyPouch::new );
		} );
	}
}
