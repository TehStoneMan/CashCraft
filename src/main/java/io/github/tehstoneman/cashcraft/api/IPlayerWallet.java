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
	 * @return The value contained by this wallet
	 */
	public long getValue();

	/**
	 * Sets the wallet to a specified amount.
	 *
	 * @param value The amount to set this wallet to
	 */
	public void setValue( long value );

	/** Add some cash to the player's wallet (inventory)
	 * 
	 * @param value The amount to add
	 */
	public void deposit( long value );

	/**
	 * Withdraws the specified amount from the wallet
	 *
	 * @param value The amount to withdraw
	 * @return The total withdrawn
	 */
	public boolean withdraw( long value );

	/**
	 * Remove all cash from the player's wallet (inventory)
	 */
	public void empty();

	/**
	 * Returns a string representation of this wallet's contents
	 *
	 * @return The value in string format
	 */
	@Override
	public String toString();
}