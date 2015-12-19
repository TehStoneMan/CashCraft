package io.github.tehstoneman.cashcraft.item;

import io.github.tehstoneman.cashcraft.item.ItemCash.EnumCoinValue;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemNote extends Item
{
	public ItemNote()
	{
		setHasSubtypes( true );
		setMaxDamage( 0 );
		setCreativeTab( CreativeTabs.tabMisc );
	}

	@Override
	public int getMetadata( int damage )
	{
		return damage;
	}

	@Override
	@SideOnly( Side.CLIENT )
	public void getSubItems( Item item, CreativeTabs tab, List subItems )
	{
		for( final EnumNoteValue values : EnumNoteValue.values() )
		{
			final int metadata = values.getMetadata();
			final ItemStack subItemStack = new ItemStack( item, 1, metadata );
			subItems.add( subItemStack );
		}
	}

	@Override
	public String getUnlocalizedName( ItemStack itemStack )
	{
		final int metadata = itemStack.getItemDamage();
		final EnumNoteValue values = EnumNoteValue.byMetadata( metadata );
		return super.getUnlocalizedName() + "." + values.getName();
	}

	public static enum EnumNoteValue
	{
		//@formatter:off
		ONE(           0,   128, "one"           ),
		TWO(           1,   256, "two"           ),
		FOUR(          2,   512, "four"          ),
		EIGHT(         3,  1024, "eight"         ),
		SIXTEEN(       4,  2048, "sixteen"       ),
		THIRTY_TWO(    5,  4096, "thirty_two"    ),
		SIXTY_FOUR(    6,  8192, "sixty_four"    ),
		ONE_TWO_EIGHT( 7, 16384, "one_two_eight" );
		//@formatter:on

		private int								meta;
		private int								value;
		private String							name;
		private static final EnumNoteValue[]	META_LOOKUP	= new EnumNoteValue[values().length];

		static
		{
			for( EnumNoteValue value : values() )
				META_LOOKUP[value.getMetadata()] = value;
		}
		
		private EnumNoteValue( int meta, int value, String name )
		{
			this.meta = meta;
			this.value = value;
			this.name = name;
		}

		public static EnumNoteValue byMetadata( int metadata )
		{
			if( metadata < 0 || metadata >= META_LOOKUP.length )
				metadata = 0;

			return META_LOOKUP[metadata];
		}

		public int getMetadata()
		{
			return meta;
		}

		public int getValue()
		{
			return value;
		}

		public String getName()
		{
			return name;
		}
	}
}
