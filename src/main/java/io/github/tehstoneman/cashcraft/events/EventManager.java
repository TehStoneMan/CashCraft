package io.github.tehstoneman.cashcraft.events;

import java.util.Random;

import io.github.tehstoneman.cashcraft.common.item.CashCraftItems;
import io.github.tehstoneman.cashcraft.config.CashCraftConfig;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.BlazeEntity;
import net.minecraft.entity.monster.CaveSpiderEntity;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.monster.EndermanEntity;
import net.minecraft.entity.monster.GhastEntity;
import net.minecraft.entity.monster.SkeletonEntity;
import net.minecraft.entity.monster.SpiderEntity;
import net.minecraft.entity.monster.WitchEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber( bus = Mod.EventBusSubscriber.Bus.MOD )
public class EventManager
{
	// private static Minecraft mc = Minecraft.getMinecraft();
	private final Random r = new Random();

	@SubscribeEvent
	public void onLivingDropsEvent( LivingDropsEvent event )
	{
		if( !CashCraftConfig.COMMON.doMobDrops.get() )
			return;

		// Calculate drop chance
		if( r.nextInt( 4 + event.getLootingLevel() ) < 3 )
			return;

		// Calculate loot value
		final int value = r.nextInt( 1 + event.getLootingLevel() );

		final Entity entity = event.getEntity();
		final World world = entity.getEntityWorld();
		final BlockPos pos = entity.getPosition();

		if( entity instanceof CaveSpiderEntity || entity instanceof SkeletonEntity || entity instanceof SpiderEntity
				|| entity instanceof ZombieEntity )
		{
			final ItemEntity itemDrop = new ItemEntity( world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack( CashCraftItems.COIN_ONE, 1 ) );
			event.getDrops().add( itemDrop );
		}

		if( entity instanceof BlazeEntity || entity instanceof CreeperEntity || entity instanceof EndermanEntity || entity instanceof WitchEntity )
		{
			final ItemEntity itemDrop = new ItemEntity( world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack( CashCraftItems.COIN_TWO, 1 ) );
			event.getDrops().add( itemDrop );
		}

		if( entity instanceof GhastEntity )
		{
			final ItemEntity itemDrop = new ItemEntity( world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack( CashCraftItems.COIN_FIVE, 1 ) );
			event.getDrops().add( itemDrop );
		}

		if( entity instanceof EnderDragonEntity || entity instanceof WitherEntity )
		{
			final int maxDrops = 5 + r.nextInt( 5 + 5 * event.getLootingLevel() );
			for( int i = 0; i < maxDrops; i++ )
			{
				final ItemEntity itemDrop = new ItemEntity( world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack( CashCraftItems.NOTE_ONE, 1 ) );
				event.getDrops().add( itemDrop );
			}
		}
	}

	/*
	 * @SubscribeEvent
	 * public void onPlayerJoinEvent( PlayerLoggedInEvent event )
	 * {
	 * if( !event.player.world.isRemote )
	 * {
	 * final SyncConfigMessage message = new SyncConfigMessage( CashCraftConfig.showAsCoins, CashCraftConfig.useCustomName,
	 * CashCraftConfig.cashSingular, CashCraftConfig.cashPlural );
	 * CashCraft.simpleNetworkWrapper.sendTo( message, (EntityPlayerMP)event.player );
	 * }
	 * }
	 */

	/*
	 * @SubscribeEvent
	 * public void onItemPickupEvent( ItemPickupEvent event )
	 * {
	 * if( !event.player.worldObj.isRemote )
	 * {
	 * ItemStack itemStack = event.pickedUp.getItemEntity();
	 * if( itemStack.getItem() instanceof ItemCash )
	 * {
	 * EntityPlayer player = event.player;
	 * if( player.inventory.hasItemStack( new ItemStack( CashCraftItems.itemMoneyPouch ) ) )
	 * {
	 * ItemStack lookFor = new ItemStack( CashCraftItems.itemMoneyPouch );
	 * int index = 0;
	 * for ( int i = 0 ; i < player.inventory.getSizeInventory() ; i++ )
	 * {
	 * ItemStack itemStack1 = player.inventory.getStackInSlot( i );
	 * if (itemStack1 != null && itemStack1.isItemEqual(lookFor))
	 * {
	 * index = i;
	 * }
	 * }
	 * Logger.getLogger( ModInfo.MODID ).info( "Money pouch found in slot " + index );
	 * }
	 * }
	 * }
	 * }
	 */
}
