package io.github.tehstoneman.cashcraft.event;

import java.util.Random;
import java.util.logging.Logger;

import io.github.tehstoneman.cashcraft.CashCraft;
import io.github.tehstoneman.cashcraft.ModInfo;
import io.github.tehstoneman.cashcraft.common.item.CashCraftItems;
import io.github.tehstoneman.cashcraft.common.item.ItemCash;
import io.github.tehstoneman.cashcraft.network.SyncConfigMessage;
import io.github.tehstoneman.cashcraft.util.ModSettings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.ItemPickupEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;

public class EventManager
{
	// private static Minecraft mc = Minecraft.getMinecraft();
	public Random r = new Random();

	@SubscribeEvent
	public void onLivingDropsEvent( LivingDropsEvent event )
	{
		if( !ModSettings.doMobDrops )
			return;

		// Calculate drop chance
		if( r.nextInt( 4 + event.getLootingLevel() ) < 3 )
			return;

		// Calculate loot value
		final int value = r.nextInt( 1 + event.getLootingLevel() );

		final Entity entity = event.getEntity();
		final World world = entity.getEntityWorld();
		final BlockPos pos = entity.getPosition();

		// @formatter:off
		if( entity instanceof EntityCaveSpider
		 || entity instanceof EntitySkeleton
		 || entity instanceof EntitySpider
		 || entity instanceof EntityZombie )
		{
			final EntityItem itemDrop = new EntityItem( world, pos.getX(), pos.getY(), pos.getZ(),
					new ItemStack( CashCraftItems.itemCoin, 1, ItemCash.EnumCoinValue.COIN_ONE.getMetadata() + value / 2 ) );
			event.getDrops().add( itemDrop );
		}

		if( entity instanceof EntityBlaze
		 || entity instanceof EntityCreeper
		 || entity instanceof EntityEnderman
		 || entity instanceof EntityWitch )
		{
			final EntityItem itemDrop = new EntityItem( world, pos.getX(), pos.getY(), pos.getZ(),
					new ItemStack( CashCraftItems.itemCoin, 1, ItemCash.EnumCoinValue.COIN_TWO.getMetadata() + value ) );
			event.getDrops().add( itemDrop );
		}
		// @formatter:on

		if( entity instanceof EntityGhast )
		{
			final EntityItem itemDrop = new EntityItem( world, pos.getX(), pos.getY(), pos.getZ(),
					new ItemStack( CashCraftItems.itemCoin, 1, ItemCash.EnumCoinValue.COIN_FIVE.getMetadata() + value ) );
			event.getDrops().add( itemDrop );
		}

		if( entity instanceof EntityDragon || entity instanceof EntityWither )
		{
			final int maxDrops = 5 + r.nextInt( 5 + 5 * event.getLootingLevel() );
			for( int i = 0; i < maxDrops; i++ )
			{
				final EntityItem itemDrop = new EntityItem( world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack( CashCraftItems.itemCoin, 1,
						ItemCash.EnumCoinValue.NOTE_ONE.getMetadata() + r.nextInt( 1 + event.getLootingLevel() ) ) );
				event.getDrops().add( itemDrop );
			}
		}
	}

	@SubscribeEvent
	public void onPlayerJoinEvent( PlayerLoggedInEvent event )
	{
		if( !event.player.worldObj.isRemote )
		{
			final SyncConfigMessage message = new SyncConfigMessage( ModSettings.showAsCoins, ModSettings.useCustomName, ModSettings.cashSingular,
					ModSettings.cashPlural );
			CashCraft.simpleNetworkWrapper.sendTo( message, (EntityPlayerMP)event.player );
		}
	}

	/*
	@SubscribeEvent
	public void onItemPickupEvent( ItemPickupEvent event )
	{
		if( !event.player.worldObj.isRemote )
		{
			ItemStack itemStack = event.pickedUp.getEntityItem();
			if( itemStack.getItem() instanceof ItemCash )
			{
				EntityPlayer player = event.player;
				if( player.inventory.hasItemStack( new ItemStack( CashCraftItems.itemMoneyPouch ) ) )
				{
					ItemStack lookFor = new ItemStack( CashCraftItems.itemMoneyPouch );
					int index = 0;
		            for ( int i = 0 ; i < player.inventory.getSizeInventory() ; i++ )
		            {
		            	ItemStack itemStack1 = player.inventory.getStackInSlot( i );
		                if (itemStack1 != null && itemStack1.isItemEqual(lookFor))
		                {
		                    index = i;
		                }
		            }
					Logger.getLogger( ModInfo.MODID ).info( "Money pouch found in slot " + index );
				}
			}
		}
	}
	*/
}
