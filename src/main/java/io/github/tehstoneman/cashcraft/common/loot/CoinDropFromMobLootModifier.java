package io.github.tehstoneman.cashcraft.common.loot;

import org.jetbrains.annotations.NotNull;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import io.github.tehstoneman.cashcraft.config.CashCraftConfig;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;

public class CoinDropFromMobLootModifier extends LootModifier
{
	public static final Supplier< Codec< CoinDropFromMobLootModifier > > CODEC = Suppliers.memoize( () -> RecordCodecBuilder
		.create( inst -> codecStart( inst ).and( ForgeRegistries.ITEMS.getCodec().fieldOf( "coin_item" ).forGetter( m -> m.itemCoin ) ).apply( inst,
			CoinDropFromMobLootModifier::new ) ) );

	private final Item itemCoin;

	public CoinDropFromMobLootModifier( LootItemCondition[] conditionsIn, Item itemCoin )
	{
		super( conditionsIn );
		this.itemCoin = itemCoin;
	}

	@Override
	public Codec< ? extends IGlobalLootModifier > codec()
	{
		return CODEC.get();
	}

	@Override
	protected @NotNull ObjectArrayList< ItemStack > doApply( ObjectArrayList< ItemStack > generatedLoot, LootContext context )
	{
		if( CashCraftConfig.doMobDrops.get() )
			generatedLoot.add( new ItemStack( itemCoin, 1 ) );
		return generatedLoot;
	}
}
