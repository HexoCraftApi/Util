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
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

/**
 * @author <b>Hexosse</b> (<a href="https://github.com/hexosse">on GitHub</a>))
 */
public class LocationUtil
{
	/**
	 * Returns a string representing the location
	 *
	 * @param location Location represented
	 * @return Representation of the location
	 */
	public static String toReadableString(Location location)
	{
		if (location == null) throw new NullPointerException("location");

		return '[' + location.getWorld().getName() + "] " + (int)location.getBlockX() + ", " + (int)location.getBlockY() + ", " + (int)location.getBlockZ();
	}

	public static boolean equals(Location l1, Location l2)
	{
		if (l1 == null) throw new NullPointerException("l1 can not be null");
		if (l2 == null) throw new NullPointerException("l2 can not be null");

		return (l1.getWorld().getName()==l2.getWorld().getName())
			   &&  (l1.getBlockX()==l2.getBlockX())
			   &&  (l1.getBlockY()==l2.getBlockY())
			   &&  (l1.getBlockZ()==l2.getBlockZ());
	}

	public static Location top(Location location)
	{
		if (location == null) throw new NullPointerException("location can not be null");

		Location top = location.clone();
		return top.add(0, 1, 0);
	}

	public static Location bottom(Location location)
	{
		if (location == null) throw new NullPointerException("location can not be null");

		Location top = location.clone();
		return top.add(0, -1, 0);
	}

	public static void removeBlockAt(Location location)
	{
		if (location == null) throw new NullPointerException("location can not be null");

		Block b = location.getBlock();
		if(b!=null)
			b.setType(Material.AIR);
	}

	public static double distance(Location l1, Location l2)
	{
		if (l1 == null) throw new NullPointerException("l1 can not be null");
		if (l2 == null) throw new NullPointerException("l2 can not be null");

		return Math.sqrt(Math.pow(l1.getX() - l2.getX(), 2) + Math.pow(l1.getY() - l2.getY(), 2) + Math.pow(l1.getZ() - l2.getZ(), 2));
	}

	public static Location getCardinalDistance(Location loc, BlockFace direction, float distance)
	{
		double dangle = distance * Math.sin(Math.toRadians(45));
		switch(direction)
		{
			case NORTH      : loc = new Location(loc.getWorld(), loc.getX(), loc.getY(), loc.getZ() - distance, loc.getYaw(), loc.getPitch()); break;
			case NORTH_EAST : loc = new Location(loc.getWorld(), loc.getX() + dangle, loc.getY(), loc.getZ() - dangle, loc.getYaw(), loc.getPitch()); break;
			case EAST       : loc = new Location(loc.getWorld(), loc.getX() + distance, loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch()); break;
			case SOUTH_EAST : loc = new Location(loc.getWorld(), loc.getX() + dangle, loc.getY(), loc.getZ() + dangle, loc.getYaw(), loc.getPitch()); break;
			case SOUTH      : loc = new Location(loc.getWorld(), loc.getX(), loc.getY(), loc.getZ() + distance, loc.getYaw(), loc.getPitch()); break;
			case SOUTH_WEST : loc = new Location(loc.getWorld(), loc.getX() - dangle, loc.getY(), loc.getZ() + dangle, loc.getYaw(), loc.getPitch()); break;
			case WEST       : loc = new Location(loc.getWorld(), loc.getX() - distance, loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch()); break;
			case NORTH_WEST : loc = new Location(loc.getWorld(), loc.getX() - dangle, loc.getY(), loc.getZ() - dangle, loc.getYaw(), loc.getPitch()); break;
		}
		return loc;
	}
}
