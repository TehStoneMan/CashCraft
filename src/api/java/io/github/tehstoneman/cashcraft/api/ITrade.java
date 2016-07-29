package io.github.tehstoneman.cashcraft.api;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * The interface to the CashCraft trading module
 *
 * @author TehStoneMan
 *
 */
public interface ITrade
{
	/**
	 * Check if the trading module is enabled
	 * Trade API calls will be ignored if this is false.
	 *
	 * @return
	 */
	public Boolean isEnabled();

	public void openTradeGui( EntityPlayer playerIn, EnumTradeType tradeType, World worldIn, BlockPos pos );
	
	public static enum EnumTradeType
	{
		OWNER( 0 ), BUYER( 1 ), SERVER( 2 );
		private final int guiID;

		private EnumTradeType( int guiID )
		{
			this.guiID = guiID;
		}

		public int getGuiID()
		{
			return guiID;
		}
	}
}
