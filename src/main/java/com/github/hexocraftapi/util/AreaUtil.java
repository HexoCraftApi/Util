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

import org.bukkit.Location;
import org.bukkit.entity.Player;

@SuppressWarnings("unused")
public class AreaUtil
{
	// Check if a location is inside an area
	public static boolean isInside(Location loc, Location corner1, Location corner2)
	{
		int x1 = Math.min(corner1.getBlockX(), corner2.getBlockX());
		int y1 = Math.min(corner1.getBlockY(), corner2.getBlockY());
		int z1 = Math.min(corner1.getBlockZ(), corner2.getBlockZ());
		int x2 = Math.max(corner1.getBlockX(), corner2.getBlockX());
		int y2 = Math.max(corner1.getBlockY(), corner2.getBlockY());
		int z2 = Math.max(corner1.getBlockZ(), corner2.getBlockZ());

		return loc.getBlockX() >= x1 && loc.getBlockX() <= x2
			   && loc.getBlockY() >= y1 && loc.getBlockY() <= y2
			   && loc.getBlockZ() >= z1 && loc.getBlockZ() <= z2;
	}

	// Check if a player is inside an area
	public static boolean isPlayerInside(Player player, Location l1, Location l2)
	{
		return isInside(player.getLocation(), l1, l2);
	}

	// Check if entering an area
	public static boolean isEntering(Location from, Location to, Location corner1, Location corner2)
	{
		return isInside(from, corner1, corner2) == false && isInside(to, corner1, corner2) == true;
	}

	// Check if entering an area
	public static boolean isLeaving(Location from, Location to, Location corner1, Location corner2)
	{
		return isInside(from, corner1, corner2) == true && isInside(to, corner1, corner2) == false;
	}

}
