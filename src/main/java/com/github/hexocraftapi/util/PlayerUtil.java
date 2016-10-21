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

import com.github.hexocraftapi.reflection.util.MethodUtil;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.entity.Player;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;

/**
 * @author <b>Hexosse</b> (<a href="https://github.com/hexosse">on GitHub</a>))
 */
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
}
