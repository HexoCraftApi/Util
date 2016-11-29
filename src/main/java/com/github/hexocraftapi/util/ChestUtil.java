/*
 * Copyright 2016 hexosse
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.github.hexocraftapi.util;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Chest;
import org.bukkit.block.DoubleChest;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

/**
 * @author <b>Hexosse</b> (<a href="https://github.com/hexosse">on GitHub</a>))
 */
public class ChestUtil
{
	/**
	 * Checks if the block is a chest
	 *
	 * @param material Material to check
	 * @return Is this material a chest?
	 */
	public static boolean isChest(Material material)
	{
		if (material == null) throw new NullPointerException("material cannot be null");

		if (material == Material.CHEST) return true;

		return false;
	}

	/**
	 * Checks if the block is a chest
	 *
	 * @param block Block to check
	 * @return Is this block a chest?
	 */
	public static boolean isChest(Block block)
	{
		if (block == null) throw new NullPointerException("block cannot be null");

		return isChest(block.getType());
	}

	/**
	 * Checks if the block is a chest
	 *
	 * @param location location to check
	 * @return Is this location a chest?
	 */
	public static boolean isChest(Location location)
	{
		if (location == null) throw new NullPointerException("location cannot be null");

		return isChest(location.getBlock().getType());
	}

	/**
	 * Checks if the ItemStack is a chest
	 *
	 * @param itemStack ItemStack to check
	 * @return Is this itemStack a chest?
	 */
	public static boolean isChest(ItemStack itemStack)
	{
		return isChest(itemStack.getType());
	}

	/**
	 * Checks if the InventoryHolder is a chest
	 *
	 * @param holder Inventory holder to check
	 * @return Is this holder a chest?
	 */
	public static boolean isChest(InventoryHolder holder)
	{
		if (holder == null)
			return false;

		return holder instanceof Chest;
	}


	
	/**
	 * Checks if the InventoryHolder is a double chest
	 *
	 * @param holder Inventory holder to check
	 * @return Is this holder a double chest?
	 */
	public static boolean isDoubleChest(InventoryHolder holder)
	{
		if (holder == null)
			return false;

		return holder instanceof DoubleChest;
	}



	/**
	 * Checks if the block is a chest
	 *
	 * @param material Material to check
	 * @return Is this material a chest?
	 */
	public static boolean isTrappedChest(Material material)
	{
		if (material == null) throw new NullPointerException("material cannot be null");

		if (material == Material.TRAPPED_CHEST) return true;

		return false;
	}

	/**
	 * Checks if the block is a chest
	 *
	 * @param block Block to check
	 * @return Is this block a chest?
	 */
	public static boolean isTrappedChest(Block block)
	{
		if (block == null) throw new NullPointerException("block cannot be null");

		return isTrappedChest(block.getType());
	}

	/**
	 * Checks if the block is a trapped chest
	 *
	 * @param location location to check
	 * @return Is this location a chest?
	 */
	public static boolean isTrappedChest(Location location)
	{
		if (location == null) throw new NullPointerException("location cannot be null");

		return isTrappedChest(location.getBlock().getType());
	}

	/**
	 * Checks if the ItemStack is a trapped chest
	 *
	 * @param itemStack ItemStack to check
	 * @return Is this itemStack a chest?
	 */
	public static boolean isTrappedChest(ItemStack itemStack)
	{
		return isTrappedChest(itemStack.getType());
	}

	/**
	 * Checks if the InventoryHolder is a chest
	 *
	 * @param holder Inventory holder to check
	 * @return Is this holder a chest?
	 */
	public static boolean isTrappedChest(InventoryHolder holder)
	{
		if (holder == null)
			return false;

		Chest chest = getChest(holder);
		if(chest != null)
			return isTrappedChest(chest.getBlock());

		return false;
	}

	

	/**
	 * Return the chest if the block is a chest
	 *
	 * @param block Block to check
	 * @return Chest or null
	 */
	public static Chest getChest(Block block)
	{
		return isChest(block) ? (Chest)block.getState() : null;
	}

	/**
	 * Return the chest if the block is a chest
	 *
	 * @param holder Inventory holder to check
	 * @return Chest or null
	 */
	public static Chest getChest(InventoryHolder holder)
	{
		return isChest(holder) ? (Chest)holder : null;
	}

	/**
	 * Return the chest if the block is a chest
	 *
	 * @param block Block to check
	 * @return Chest or null
	 */
	public static Chest getTrappedChest(Block block)
	{
		return isTrappedChest(block) ? (Chest)block.getState() : null;
	}

	/**
	 * Return the chest if the block is a chest
	 *
	 * @param holder Inventory holder to check
	 * @return Chest or null
	 */
	public static Chest getTrappedChest(InventoryHolder holder)
	{
		return isTrappedChest(holder) ? (Chest)holder : null;
	}

	/**
	 * Return the chest if the block is a double chest
	 *
	 * @param holder Inventory holder to check
	 * @return DoubleChest or null
	 */
	public static DoubleChest getDoubleChest(InventoryHolder holder)
	{
		return isDoubleChest(holder) ? (DoubleChest)holder : null;
	}



	/**
	 * Check if a chest exist arround the location
	 * @param block the block to check
	 * @return the nearby chest if exist
	 */
	public synchronized static Chest getChestNearby(Block block)
	{
		if (block == null) throw new NullPointerException("block cannot be null");

		if(isChest(block.getRelative(BlockFace.NORTH)))
			return getChest(block.getRelative(BlockFace.NORTH));

		else if(isChest(block.getRelative(BlockFace.EAST)))
			return getChest(block.getRelative(BlockFace.EAST));

		else if(isChest(block.getRelative(BlockFace.SOUTH)))
			return getChest(block.getRelative(BlockFace.SOUTH));

		else if(isChest(block.getRelative(BlockFace.WEST)))
			return getChest(block.getRelative(BlockFace.WEST));

		return null;
	}

	/**
	 * Check if a chest exist arround the location
	 * @param location the location to check
	 * @return the nearby chest if exist
	 */
	public synchronized static Chest getChestNearby(Location location)
	{
		if (location == null) throw new NullPointerException("location cannot be null");

		return getChestNearby(location.getBlock());
	}

	public static Location GetChestHolderLocation(InventoryHolder holder)
	{
		if (holder == null)
			return null;

		if (holder instanceof DoubleChest) {
			return ((DoubleChest)holder).getLocation();
		} else if(holder instanceof Chest) {
			return ((Chest) holder).getLocation();
		}

		return null;
	}
}
