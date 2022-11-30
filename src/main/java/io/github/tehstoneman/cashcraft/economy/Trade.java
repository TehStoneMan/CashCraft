package io.github.tehstoneman.cashcraft.economy;

import io.github.tehstoneman.cashcraft.api.ITrade;
import io.github.tehstoneman.cashcraft.config.CashCraftConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class Trade implements ITrade
{
	@Override
	public Boolean isEnabled()
	{
		return CashCraftConfig.useEconomy.get() && CashCraftConfig.useTrade.get();
	}

	@Override
	public void openTradeGui( Player playerIn, EnumTradeType tradeType, Level worldIn, BlockPos pos )
	{
		// playerIn.openGui( CashCraft.instance, tradeType.getGuiID(), worldIn, pos.getX(), pos.getY(), pos.getZ() );
	}
}
