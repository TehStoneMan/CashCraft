package io.github.tehstoneman.cashcraft.events;

import java.util.Random;

public class EventManager
{
	// private static Minecraft mc = Minecraft.getMinecraft();
	private final Random r = new Random();

	/*
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
	*/

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
