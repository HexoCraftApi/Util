package com.github.hexocraftapi.util;

/*
 * Copyright 2016 hexosse
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.github.hexocraftapi.reflection.minecraft.Minecraft;
import com.github.hexocraftapi.reflection.util.MethodUtil;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;

/**
 * @author <b>Hexosse</b> (<a href="https://github.com/hexosse">on GitHub</a>))
 */

/**
 * PlayerUtil is a compilation of useful method to insure compatibility with different version of Minecraft .
 *
 * Here is the list of those methods :
 * 	- getOnlinePlayers()
 * 	- getPlayer(String name)
 * 	- getItemInHand()
 * 	- getItemInMainHand()
 * 	- getItemInOffHand()
 *
 */

@SuppressWarnings("unused")
public class PlayerUtil
{
	/**
	 * Gets a view of all currently logged in players.
	 *
	 * With this implementation of getOnlinePlayers(), it preserve from crashing your server as org.bukkit.Server.getOnlinePlayers() recently changed.
	 *
	 * @return a view of currently online players.
	 */
	public static Collection<? extends Player> getOnlinePlayers()
	{
		try
		{
			Method onlinePlayerMethod = MethodUtil.getMethod(Server.class, "getOnlinePlayers");
			if(onlinePlayerMethod.getReturnType().equals(Collection.class))
				return (Collection<? extends Player>) onlinePlayerMethod.invoke(Bukkit.getServer());
			else
				return Arrays.asList(((Player[]) onlinePlayerMethod.invoke(Bukkit.getServer())));
		}
		catch(Exception ignored) { return null; }
	}

	/**
	 * Gets a player from name.
	 *
	 * @param name the player name
	 * @return the player corresponding to the name.
	 */
	public static Player getPlayer(String name)
	{
		for(Player player : getOnlinePlayers())
		{
			if(player.getName().equals(name))
				return player;
		}
		return null;
	}

	/**
	 * getItemInHand has been deprecated in Minecraft 1.9.
	 *
	 * With this implementation of getItemInHand(), it preserve from using deprecated method.
	 *
	 * @param player the player
	 * @return the ItemStack in hand.
	 */
	public static ItemStack getItemInHand(Player player)
	{
		if(Minecraft.Version.getVersion().olderThan(Minecraft.Version.v1_9_R1))
			return player.getInventory().getItemInHand();
		else
			return player.getInventory().getItemInMainHand();
	}

	/**
	 * getItemInMainHand only appeared in Minecraft 1.9.
	 *
	 * With this implementation of getItemInMainHand(), it insure compatibility with previous version.
	 *
	 * @param player the player
	 * @return the ItemStack in main hand.
	 */
	public static ItemStack getItemInMainHand(Player player)
	{
		if(Minecraft.Version.getVersion().olderThan(Minecraft.Version.v1_9_R1))
			return player.getInventory().getItemInHand();
		else
			return player.getInventory().getItemInMainHand();
	}

	/**
	 * getItemInOffHand only appeared in Minecraft 1.9.
	 *
	 * With this implementation of getItemInOffHand(), it insure compatibility with previous version.
	 *
	 * @param player the player
	 * @return the ItemStack in off hand for Minecraft 1.9 and higher or null for Minecraft prior 1.9.
	 */
	public static ItemStack getItemInOffHand(Player player)
	{
		if(Minecraft.Version.getVersion().olderThan(Minecraft.Version.v1_9_R1))
			return null;
		else
			return player.getInventory().getItemInOffHand();
	}

}
