package io.github.tehstoneman.cashcraft.world.item;

import io.github.tehstoneman.cashcraft.CashCraft;
import net.minecraft.world.item.Item;

public class CashCraftItem extends Item
{
	CashCraftItem( Properties properties )
	{
		super( properties.tab( CashCraft.CREATIVE_MODE_TAB ) );
	}
}
