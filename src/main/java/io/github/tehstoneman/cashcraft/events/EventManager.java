package io.github.tehstoneman.cashcraft.events;

import io.github.tehstoneman.cashcraft.CashCraft;
import io.github.tehstoneman.cashcraft.ModInfo;
import io.github.tehstoneman.cashcraft.world.item.CashCraftItems;
import io.github.tehstoneman.cashcraft.world.item.CashItem;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.eventbus.api.Event.Result;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.items.ItemStackHandler;

//@Mod.EventBusSubscriber( modid = ModInfo.MOD_ID, bus = Bus.FORGE )
public class EventManager
{
	/*
	 * @SubscribeEvent
	 * public static void onItemPickupEvent( EntityItemPickupEvent event )
	 * {
	 * final Player player = event.getEntity();
	 * final Level level = player.getLevel();
	 * if( !level.isClientSide )
	 * {
	 * final ItemEntity itemEntity = event.getItem();
	 * ItemStack itemStack = itemEntity.getItem();
	 * if( itemStack.getItem() instanceof CashItem )
	 * {
	 * final Inventory inventory = player.getInventory();
	 * final ItemStack lookFor = new ItemStack( CashCraftItems.MONEY_POUCH.get() );
	 * if( inventory.contains( lookFor ) )
	 * {
	 * int index = 0;
	 * for( int i = 0; i < inventory.items.size(); ++i )
	 * {
	 * final ItemStack testStack = inventory.items.get( i );
	 * if( !testStack.isEmpty() && ItemStack.isSame( lookFor, testStack ) )
	 * {
	 * index = i;
	 * break;
	 * }
	 * }
	 * CashCraft.LOGGER.info( "Money pouch found in slot " + index );
	 * final ItemStack moneyPouch = inventory.items.get( index );
	 * final ItemStackHandler inventoryMoneyPouch = (ItemStackHandler)moneyPouch.getCapability( ForgeCapabilities.ITEM_HANDLER, null )
	 * .orElse( null );
	 * CashCraft.LOGGER.info( "Money pouch contains " + inventoryMoneyPouch );
	 * 
	 * itemStack = moveItemStackTo( itemStack, 0, inventoryMoneyPouch.getSlots(), inventoryMoneyPouch );
	 * CashCraft.LOGGER.info( "Item stack " + itemStack.isEmpty() );
	 * 
	 * if( itemStack.isEmpty() )
	 * {
	 * CashCraft.LOGGER.info( "Event isCancelable " + event.isCancelable() + " : hasResult " + event.hasResult() );
	 * // event.setCanceled( true );
	 * event.setResult( Result.DENY );
	 * }
	 * }
	 * }
	 * }
	 * }
	 * 
	 * protected static ItemStack moveItemStackTo( ItemStack itemStack, int indexFrom, int indexTo, ItemStackHandler itemStackHandler )
	 * {
	 * int i = indexFrom;
	 * 
	 * if( itemStack.isStackable() )
	 * while( !itemStack.isEmpty() )
	 * {
	 * if( i >= indexTo )
	 * break;
	 * 
	 * itemStack = itemStackHandler.insertItem( i, itemStack, false );
	 * 
	 * if( itemStack.isEmpty() )
	 * break;
	 * 
	 * ++i;
	 * }
	 * 
	 * return itemStack;
	 * }
	 */

}
