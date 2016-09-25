package io.github.tehstoneman.cashcraft.api;


/**
 * A class that represents player's cash items held in inventory as a general purpose wallet.
 *
 * @author TehStoneMan
 *
 */
public interface IPlayerWallet
{
	/**
	 * Return the total amount of cash held by a player.
	 *
	 * @param player
	 * @return
	 */
	public long getValue();

	/**
	 * Sets the wallet to a specified amount.
	 *
	 * @param amount
	 */
	public void setValue( long value );

	/**
	 * Add some cash to the player's wallet (inventory)
	 *
	 * @param player
	 * @param amount
	 */
	public void deposit( long value );

	/**
	 * Withdraws the specified amount from the wallet
	 *
	 * @param amount
	 * @return
	 */
	public boolean withdraw( long value );

	/**
	 * Remove all cash from the player's wallet (inventory)
	 *
	 * @param player
	 */
	public void empty();

	/**
	 * Returns a string representation of this wallet's contents
	 *
	 * @return
	 */
	@Override
	public String toString();
}