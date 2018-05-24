package io.github.tehstoneman.cashcraft.event;

import java.util.Random;

import io.github.tehstoneman.cashcraft.CashCraft;
import io.github.tehstoneman.cashcraft.ModInfo;
import io.github.tehstoneman.cashcraft.common.item.ItemCash;
import io.github.tehstoneman.cashcraft.common.item.ItemCashCraft;
import io.github.tehstoneman.cashcraft.config.CashCraftConfig;
import io.github.tehstoneman.cashcraft.network.SyncConfigMessage;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
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
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.registries.IForgeRegistry;

public class EventManager
{
	// private static Minecraft mc = Minecraft.getMinecraft();
	private Random r = new Random();

	@SubscribeEvent
	public void onRegisterItems( Register< Item > event )
	{
		final IForgeRegistry< Item > registry = event.getRegistry();

		registry.register( ItemCashCraft.COIN.setRegistryName( ModInfo.MODID, "cash" ) );
		registry.register( ItemCashCraft.MONEY_POUCH.setRegistryName( ModInfo.MODID, "moneypouch" ) );
	}

	@SubscribeEvent
	public void onRegisterModels( ModelRegistryEvent event )
	{
		registerItemModel( ItemCashCraft.MONEY_POUCH );
	}

	private void registerItemModel( Item item )
	{
		final ResourceLocation registryName = item.getRegistryName();
		registerItemModel( item, registryName.toString() );
	}

	private void registerItemModel( Item item, String modelLocation )
	{
		final ModelResourceLocation fullModelLocation = new ModelResourceLocation( modelLocation, "inventory" );
		registerItemModel( item, fullModelLocation );
	}

	private void registerItemModel( Item item, ModelResourceLocation fullModelLocation )
	{
		ModelBakery.registerItemVariants( item, fullModelLocation );
		registerItemModel( item, stack -> fullModelLocation );
	}

	private void registerItemModel( Item item, ItemMeshDefinition meshDefinition )
	{
		ModelLoader.setCustomMeshDefinition( item, meshDefinition );
	}

	@SubscribeEvent
	public void onLivingDropsEvent( LivingDropsEvent event )
	{
		if( !CashCraftConfig.doMobDrops )
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
					new ItemStack( ItemCashCraft.COIN, 1, ItemCash.EnumCoinValue.COIN_ONE.getMetadata() + value / 2 ) );
			event.getDrops().add( itemDrop );
		}

		if( entity instanceof EntityBlaze
		 || entity instanceof EntityCreeper
		 || entity instanceof EntityEnderman
		 || entity instanceof EntityWitch )
		{
			final EntityItem itemDrop = new EntityItem( world, pos.getX(), pos.getY(), pos.getZ(),
					new ItemStack( ItemCashCraft.COIN, 1, ItemCash.EnumCoinValue.COIN_TWO.getMetadata() + value ) );
			event.getDrops().add( itemDrop );
		}
		// @formatter:on

		if( entity instanceof EntityGhast )
		{
			final EntityItem itemDrop = new EntityItem( world, pos.getX(), pos.getY(), pos.getZ(),
					new ItemStack( ItemCashCraft.COIN, 1, ItemCash.EnumCoinValue.COIN_FIVE.getMetadata() + value ) );
			event.getDrops().add( itemDrop );
		}

		if( entity instanceof EntityDragon || entity instanceof EntityWither )
		{
			final int maxDrops = 5 + r.nextInt( 5 + 5 * event.getLootingLevel() );
			for( int i = 0; i < maxDrops; i++ )
			{
				final EntityItem itemDrop = new EntityItem( world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack( ItemCashCraft.COIN, 1,
						ItemCash.EnumCoinValue.NOTE_ONE.getMetadata() + r.nextInt( 1 + event.getLootingLevel() ) ) );
				event.getDrops().add( itemDrop );
			}
		}
	}

	@SubscribeEvent
	public void onPlayerJoinEvent( PlayerLoggedInEvent event )
	{
		if( !event.player.world.isRemote )
		{
			final SyncConfigMessage message = new SyncConfigMessage( CashCraftConfig.showAsCoins, CashCraftConfig.useCustomName,
					CashCraftConfig.cashSingular, CashCraftConfig.cashPlural );
			CashCraft.simpleNetworkWrapper.sendTo( message, (EntityPlayerMP)event.player );
		}
	}

	/*
	 * @SubscribeEvent
	 * public void onItemPickupEvent( ItemPickupEvent event )
	 * {
	 * if( !event.player.worldObj.isRemote )
	 * {
	 * ItemStack itemStack = event.pickedUp.getEntityItem();
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
