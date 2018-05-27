package io.github.tehstoneman.cashcraft.common.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemCash extends ItemCashCraft
{
	public ItemCash()
	{
		super( "cash" );
		setHasSubtypes( true );
		setMaxDamage( 0 );
	}

	@Override
	public int getMetadata( int damage )
	{
		return damage;
	}

	@Override
	public String getUnlocalizedName( ItemStack itemStack )
	{
		final int metadata = itemStack.getItemDamage();
		final EnumCoinValue values = EnumCoinValue.byMetadata( metadata );
		return super.getUnlocalizedName() + "." + values.getName();
	}

	@Override
	@SideOnly( Side.CLIENT )
	public void getSubItems( CreativeTabs tab, NonNullList< ItemStack > subItems )
	{
		if( isInCreativeTab( tab ) )
			for( final EnumCoinValue values : EnumCoinValue.values() )
			{
				final int metadata = values.getMetadata();
				final ItemStack subItemStack = new ItemStack( this, 1, metadata );
				subItems.add( subItemStack );
			}
	}

	public int getValue()
	{
		return this.getValue( new ItemStack( this ) );
	}

	public int getValue( ItemStack itemStack )
	{
		final int metadata = itemStack.getItemDamage();
		final EnumCoinValue values = EnumCoinValue.byMetadata( metadata );
		return values.getValue();
	}

	public static enum EnumCoinValue
	{
		//@formatter:off
		COIN_ONE(		 0,     1, "coin.one",		"coin_001" ), //   0.01
		COIN_TWO(		 1,     2, "coin.two",		"coin_002" ), //   0.02
		COIN_FIVE(		 2,     5, "coin.five",		"coin_005" ), //   0.05
		COIN_TEN(		 3,    10, "coin.ten",		"coin_010" ), //   0.10
		COIN_TWENTY(	 4,    20, "coin.twenty",	"coin_020" ), //   0.20
		COIN_FIFTY(		 5,    50, "coin.fifty",	"coin_050" ), //   0.50
		NOTE_ONE(		 6,   100, "note.one",		"note_001" ), //   1.00
		NOTE_TWO(		 7,   200, "note.two",		"note_002" ), //   2.00
		NOTE_FIVE(		 8,   500, "note.five",		"note_005" ), //   5.00
		NOTE_TEN(		 9,  1000, "note.ten",		"note_010" ), //  10.00
		NOTE_TWENTY(	10,  2000, "note.twenty",	"note_020" ), //  20.00
		NOTE_FIFTY(		11,  5000, "note.fifty",	"note_050" ), //  50.00
		NOTE_HUNDRED(	12,	10000, "note.hundred",	"note_100" ); // 100.00
		// @formatter:on

		private int								meta;
		private int								value;
		private String							name;
		private String							icon;
		private static final EnumCoinValue[]	META_LOOKUP	= new EnumCoinValue[values().length];

		static
		{
			for( final EnumCoinValue value : values() )
				META_LOOKUP[value.getMetadata()] = value;
		}

		private EnumCoinValue( int meta, int value, String name, String icon )
		{
			this.meta = meta;
			this.value = value;
			this.name = name;
			this.icon = icon;
		}

		public static EnumCoinValue byMetadata( int metadata )
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

		public String getTextureName()
		{
			return "cash_" + icon;
		}
	}
}
