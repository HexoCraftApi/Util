package com.github.hexocraftapi.util;

import com.google.common.collect.Lists;
import org.bukkit.Chunk;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * @author <b>Hexosse</b> (<a href="https://github.com/hexosse">on GitHub</a>))
 */
public class ChunkUtil
{
	public static Collection<? extends Player> getClosePlayers(Chunk chunk, final Collection<? extends Player> players, int nbChunk)
	{
		List<Player> closePlayers = Lists.newArrayList(players);

		for (Iterator<Player> iter = closePlayers.listIterator(); iter.hasNext(); )
		{
			Player player = iter.next();
			if(!isClosePlayer(chunk,player,nbChunk))
				iter.remove();
		}
		return closePlayers;
	}

	public static boolean isClosePlayer(Chunk chunk, Player player, int nbChunk)
	{
		// Get player chunk
		Chunk playerChunk = player.getWorld().getChunkAt(player.getLocation());

		// Check that player is in the same world
		if (!chunk.getWorld().getName().equals(playerChunk.getWorld().getName()))
			return false;

		// Calculate the square distance
		double vX= playerChunk.getX() - chunk.getX();
		double vZ = playerChunk.getZ() - chunk.getZ();
		return ((int) (vX * vX + vZ * vZ)) < (nbChunk * nbChunk);
	}
}
