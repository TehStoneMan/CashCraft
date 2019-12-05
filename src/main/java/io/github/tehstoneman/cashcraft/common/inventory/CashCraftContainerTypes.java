package io.github.tehstoneman.cashcraft.common.inventory;

import io.github.tehstoneman.cashcraft.ModInfo;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder( ModInfo.MOD_ID )
public class CashCraftContainerTypes
{
	//@formatter:off
	@ObjectHolder( "money_pouch" )	public static ContainerType< ContainerMoneyPouch>	MONEY_POUCH;
	//@formatter:on

	@Mod.EventBusSubscriber( bus = Mod.EventBusSubscriber.Bus.MOD )
	private static class Register
	{
		@SubscribeEvent
		public static void onItemsRegistry( final RegistryEvent.Register< ContainerType< ? > > event )
		{
			final IForgeRegistry< ContainerType< ? > > registry = event.getRegistry();

			registry.register( IForgeContainerType.create( ( windowID, inv, data ) ->
			{
				final ItemStack moneyPouch = data.readItemStack();
				final int protectedIndex = data.readInt();
				return new ContainerMoneyPouch( windowID, inv, moneyPouch, protectedIndex );
			} ).setRegistryName( "money_pouch" ) );
		}
	}
}
