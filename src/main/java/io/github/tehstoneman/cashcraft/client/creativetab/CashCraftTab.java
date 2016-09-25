package io.github.tehstoneman.cashcraft.client.creativetab;

import java.util.List;

import io.github.tehstoneman.cashcraft.ModInfo;
import io.github.tehstoneman.cashcraft.common.item.CashCraftItems;
import io.github.tehstoneman.cashcraft.common.item.ItemCash.EnumCoinValue;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CashCraftTab extends CreativeTabs
{

	public CashCraftTab( String lable )
	{
		super( lable );
	}

	@Override
	public Item getTabIconItem()
	{
		return CashCraftItems.itemCoin;
	}

	@Override
	public void displayAllRelevantItems( List itemsToShowOnTab )
	{
		for( final Object itemObject : Item.REGISTRY )
		{
			final Item item = (Item)itemObject;
			if( item != null )
				if( item.getUnlocalizedName().contains( ModInfo.MODID ) )
					item.getSubItems( item, this, itemsToShowOnTab );
		}
	}
}
