package io.github.tehstoneman.cashcraft.creativetab;

import java.util.List;

import io.github.tehstoneman.cashcraft.ModInfo;
import io.github.tehstoneman.cashcraft.item.CashCraftItems;
import io.github.tehstoneman.cashcraft.item.ItemCash.EnumCoinValue;
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
		return new ItemStack( CashCraftItems.itemCoin, 1, EnumCoinValue.COIN_SIXTY_FOUR.getMetadata() ).getItem();
	}

/*	@Override
	public void displayAllReleventItems( List itemsToShowOnTab )
	{
		for( final Object itemObject : Item.itemRegistry )
		{
			final Item item = (Item)itemObject;
			if( item != null )
				if( item.getUnlocalizedName().contains( ModInfo.MODID ) )
					item.getSubItems( item, this, itemsToShowOnTab );
		}
	}*/
}
