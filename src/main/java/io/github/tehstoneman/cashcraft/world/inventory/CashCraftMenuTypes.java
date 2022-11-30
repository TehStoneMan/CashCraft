package io.github.tehstoneman.cashcraft.world.inventory;

import io.github.tehstoneman.cashcraft.ModInfo;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class CashCraftMenuTypes
{
	public static final DeferredRegister< MenuType< ? > > REGISTERY = DeferredRegister.create( ForgeRegistries.MENU_TYPES, ModInfo.MOD_ID );

	public static final RegistryObject< MenuType< MoneyPouchContainerMenu > > MONEY_POUCH = REGISTERY.register( "money_pouch",
		() -> IForgeMenuType.create( ( windowID, inv, data ) ->
		{
			final ItemStack moneyPouch	= data.readItem();
			final int	protectedIndex	= data.readInt();
			return new MoneyPouchContainerMenu( windowID, inv, moneyPouch, protectedIndex );
		} ) );
}
