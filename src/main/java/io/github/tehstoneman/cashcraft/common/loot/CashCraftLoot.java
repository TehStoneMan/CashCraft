package io.github.tehstoneman.cashcraft.common.loot;

import com.mojang.serialization.Codec;

import io.github.tehstoneman.cashcraft.ModInfo;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class CashCraftLoot
{
	public static final DeferredRegister< Codec< ? extends IGlobalLootModifier > > REGISTERY = DeferredRegister
		.create( ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, ModInfo.MOD_ID );

	public static final RegistryObject< Codec< CoinDropFromMobLootModifier > > COINDROPFROMMOB = REGISTERY.register( "coin_drop_from_mob",
		CoinDropFromMobLootModifier.CODEC );
}
