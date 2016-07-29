package io.github.tehstoneman.cashcraft.item;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemCash extends Item
{
	public ItemCash()
	{
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
	public void getSubItems( Item item, CreativeTabs tab, List subItems )
	{
		for( final EnumCoinValue values : EnumCoinValue.values() )
		{
			final int metadata = values.getMetadata();
			final ItemStack subItemStack = new ItemStack( item, 1, metadata );
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

	/*
	@Override
	@SideOnly( Side.CLIENT )
	public void registerIcons( IIconRegister register )
	{
		icons = new IIcon[EnumCoinValue.values().length];

		for( int i = 0; i < EnumCoinValue.values().length; ++i )
		{
			final EnumCoinValue values = EnumCoinValue.byMetadata( i );
			icons[i] = register.registerIcon( CashCraft.modAsset( getIconString() + "_" + values.getTextureName() ) );
		}
	}
	*/

	public static enum EnumCoinValue
	{
		//@formatter:off
		COIN_ONE(			 0,     1, "coin.one",			"coin_001" ),
		COIN_TWO(			 1,     2, "coin.two",			"coin_002" ),
		COIN_FOUR(			 2,     4, "coin.four",			"coin_004" ),
		COIN_EIGHT(			 3,     8, "coin.eight",		"coin_008" ),
		COIN_SIXTEEN(		 4,    16, "coin.sixteen",		"coin_016" ),
		COIN_THIRTY_TWO(	 5,    32, "coin.thirtyTwo",	"coin_032" ),
		COIN_SIXTY_FOUR(	 6,    64, "coin.sixtyFour",	"coin_064" ),
		NOTE_ONE(			 7,   128, "note.one",			"note_001" ),
		NOTE_TWO(			 8,   256, "note.two",			"note_002" ),
		NOTE_FOUR(			 9,   512, "note.four",			"note_004" ),
		NOTE_EIGHT(			10,  1024, "note.eight",		"note_008" ),
		NOTE_SIXTEEN(		11,  2048, "note.sixteen",		"note_016" ),
		NOTE_THIRTY_TWO(	12,  4096, "note.thirtyTwo",	"note_032" ),
		NOTE_SIXTY_FOUR(	13,  8192, "note.sixtyFour",	"note_064" ),
		NOTE_ONE_TWO_EIGHT(	14, 16384, "note.oneTwoEight",	"note_128" );		// @formatter:on

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
			/*
			 * if( ModSettings.showAsCoins )
			 * return String.valueOf( value ) + " " + CashCraftAPI.economy.currency( value, false );
			 * else
			 * return String.valueOf( value / 128 ) + " " + CashCraftAPI.economy.currency( value, false );
			 */
			return name;
		}

		public String getTextureName()
		{
			return "cash_" + icon;
		}
	}
}
