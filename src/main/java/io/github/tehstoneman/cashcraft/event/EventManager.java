package io.github.tehstoneman.cashcraft.event;

import java.util.Random;

import io.github.tehstoneman.cashcraft.common.item.CashCraftItems;
import io.github.tehstoneman.cashcraft.common.item.ItemCash;
import io.github.tehstoneman.cashcraft.util.ModSettings;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventManager
{
	private static Minecraft	mc	= Minecraft.getMinecraft();
	public Random				r	= new Random();

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

		if( entity instanceof EntityGhast )
		{
			final EntityItem itemDrop = new EntityItem( world, pos.getX(), pos.getY(), pos.getZ(),
					new ItemStack( CashCraftItems.itemCoin, 1, ItemCash.EnumCoinValue.COIN_FIVE.getMetadata() + value ) );
			event.getDrops().add( itemDrop );
		}

		if( entity instanceof EntityDragon || entity instanceof EntityWither )
		{
			int maxDrops = 5 + r.nextInt( 5 + 5 * event.getLootingLevel() );
			for( int i = 0 ; i < maxDrops ; i++ )
			{
				final EntityItem itemDrop = new EntityItem( world, pos.getX(), pos.getY(), pos.getZ(),
						new ItemStack( CashCraftItems.itemCoin, 1, ItemCash.EnumCoinValue.NOTE_ONE.getMetadata() + r.nextInt( 1 + event.getLootingLevel() ) ) );
				event.getDrops().add( itemDrop );
			}
		}
	}
}
