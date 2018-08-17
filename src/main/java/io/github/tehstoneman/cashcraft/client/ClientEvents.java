package io.github.tehstoneman.cashcraft.client;

import io.github.tehstoneman.cashcraft.CashCraft;
import io.github.tehstoneman.cashcraft.common.item.ItemCash.EnumCoinValue;
import io.github.tehstoneman.cashcraft.common.item.ItemCashCraft;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ClientEvents
{
	@SubscribeEvent
	public void onRegisterModels( ModelRegistryEvent event )
	{
		// Register coin / note variants
		for( final EnumCoinValue value : EnumCoinValue.values() )
			registerItemModel( ItemCashCraft.COIN, value.getMetadata(), CashCraft.modAsset( value.getTextureName() ) );
		registerItemModel( ItemCashCraft.MONEY_POUCH );
	}

	/*
	 * =================
	 * Support functions
	 *
	 * Adapted from Choonster's TestMod3
	 * =================
	 */

	/**
	 * A {@link StateMapperBase} used to create property strings.
	 */
	private final StateMapperBase propertyStringMapper = new StateMapperBase()
	{
		@Override
		protected ModelResourceLocation getModelResourceLocation( IBlockState state )
		{
			return new ModelResourceLocation( "minecraft:air" );
		}
	};

	/**
	 * Register a model for a metadata value of the {@link Block}'s {@link Item}.
	 * <p>
	 * Uses the registry name as the domain/path and the {@link IBlockState} as the variant.
	 *
	 * @param state
	 *            The state to use as the variant
	 * @param metadata
	 *            The item metadata to register the model for
	 */
	private void registerBlockItemModelForMeta( IBlockState state, int metadata )
	{
		final Item item = Item.getItemFromBlock( state.getBlock() );

		if( item != Items.AIR )
			registerItemModel( item, metadata, propertyStringMapper.getPropertyString( state.getProperties() ) );
	}

	private void registerItemModel( Block block )
	{
		final Item item = Item.getItemFromBlock( block );

		if( item != Items.AIR )
			registerItemModel( item );
	}

	private void registerItemModel( Block block, int metadata, String modelLocation )
	{
		final Item item = Item.getItemFromBlock( block );

		if( item != Items.AIR )
			registerItemModel( item, metadata, modelLocation );
	}

	/**
	 * Register a single model for an {@link Item}.
	 * <p>
	 * Uses the registry name as the domain/path and {@code "inventory"} as the variant.
	 *
	 * @param item
	 *            The Item
	 */
	private void registerItemModel( Item item )
	{
		final ResourceLocation registryName = item.getRegistryName();
		registerItemModel( item, registryName.toString() );
	}

	/**
	 * Register a single model for an {@link Item}.
	 * <p>
	 * Uses {@code modelLocation} as the domain/path and {@link "inventory"} as the variant.
	 *
	 * @param item
	 *            The Item
	 * @param modelLocation
	 *            The model location
	 */
	private void registerItemModel( Item item, String modelLocation )
	{
		final ModelResourceLocation fullModelLocation = new ModelResourceLocation( modelLocation, "inventory" );
		registerItemModel( item, fullModelLocation );
	}

	private void registerItemModel( Item item, int meta, String modelLocation )
	{
		final ModelResourceLocation fullModelLocation = new ModelResourceLocation( modelLocation, "inventory" );
		registerItemModel( item, meta, fullModelLocation );
	}

	/**
	 * Register a single model for an {@link Item}.
	 * <p>
	 * Uses {@code fullModelLocation} as the domain, path and variant.
	 *
	 * @param item
	 *            The Item
	 * @param fullModelLocation
	 *            The full model location
	 */
	private void registerItemModel( Item item, ModelResourceLocation fullModelLocation )
	{
		ModelBakery.registerItemVariants( item, fullModelLocation );
		registerItemModel( item, stack -> fullModelLocation );
	}

	/**
	 * Register an {@link ItemMeshDifinition} for an {@link Item}.
	 *
	 * @param item
	 *            The Item
	 * @param meshDefinition
	 *            The ItemModelDefinition
	 */
	private void registerItemModel( Item item, ItemMeshDefinition meshDefinition )
	{
		ModelLoader.setCustomMeshDefinition( item, meshDefinition );
	}

	/**
	 * Register a model for a metadata value of an {@link Item}.
	 * <p>
	 * Uses {@code modelResourceLocation} as the domain, path and variant.
	 *
	 * @param item
	 *            The Item
	 * @param metadata
	 *            The metadata
	 * @param modelResourceLocation
	 *            The full model location
	 */
	private void registerItemModel( Item item, int metadata, ModelResourceLocation modelResourceLocation )
	{
		ModelLoader.setCustomModelResourceLocation( item, metadata, modelResourceLocation );
	}
}
