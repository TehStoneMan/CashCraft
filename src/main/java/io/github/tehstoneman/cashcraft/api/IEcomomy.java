package io.github.tehstoneman.cashcraft.api;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

/**
 * Class that contains the basic Cash API
 * Modeled after Forge Essentials Economy API
 *
 * @author TehStoneMan
 *
 */
public interface IEcomomy
{
	/**
	 * Check if the economy module is enabled
	 * WARNING:
	 * API calls are not guaranteed to be safe if this is ignored.
	 *
	 * @return
	 */
	public Boolean isEnabled();

	/**
	 * Get the wallet for a player
	 *
	 * @param player
	 * @return
	 */
	public IPlayerWallet getWallet( EntityPlayer player );

	/**
	 * Get the singular or plural term of the currency used
	 *
	 * @param amount
	 * @param longFormat
	 * @return
	 */
	public String getCurrencyName( long amount, boolean longFormat );

	/**
	 * Returns the highest stack of cash for the given amount
	 *
	 * @param amount
	 * @return
	 */
	public ItemStack getCash( long amount );

	/**
	 * Returns the value of the given stack
	 *
	 * @param itemStack
	 * @return
	 */
	public long getValue( ItemStack itemStack );

	/**
	 * Display amount as a string with the long form currency attached
	 *
	 * @param amount
	 * @return
	 */
	public String toString( long amount );

	/**
	 * Display amount as a string with choice of long or short form currency attached
	 * 
	 * @param amount
	 * @param longFormat
	 * @return
	 */
	public String toString( long amount, boolean longFormat );
}
