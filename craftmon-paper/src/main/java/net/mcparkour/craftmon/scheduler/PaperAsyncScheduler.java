/*
 * MIT License
 *
 * Copyright (c) 2019 MCParkour
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package net.mcparkour.craftmon.scheduler;

import java.time.Duration;
import org.bukkit.Server;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;

public class PaperAsyncScheduler implements Scheduler {

	private BukkitScheduler scheduler;
	private Plugin plugin;

	public PaperAsyncScheduler(Plugin plugin) {
		this(plugin.getServer(), plugin);
	}

	public PaperAsyncScheduler(Server server, Plugin plugin) {
		this(server.getScheduler(), plugin);
	}

	public PaperAsyncScheduler(BukkitScheduler scheduler, Plugin plugin) {
		this.scheduler = scheduler;
		this.plugin = plugin;
	}

	@Override
	public void run(Runnable task) {
		this.scheduler.runTaskAsynchronously(this.plugin, task);
	}

	@Override
	public void delay(Duration delay, Runnable task) {
		long delayTicks = delay.toMillis() / 50;
		delay(delayTicks, task);
	}

	@Override
	public void delay(long delayTicks, Runnable task) {
		this.scheduler.runTaskLaterAsynchronously(this.plugin, task, delayTicks);
	}

	@Override
	public void timer(Duration period, Runnable task) {
		long periodTicks = period.toMillis() / 50;
		timer(periodTicks, task);
	}

	@Override
	public void timer(long periodTicks, Runnable task) {
		timer(0, periodTicks, task);
	}

	@Override
	public void timer(Duration delay, Duration period, Runnable task) {
		long delayTicks = delay.toMillis() / 50;
		long periodTicks = period.toMillis() / 50;
		timer(delayTicks, periodTicks, task);
	}

	@Override
	public void timer(long delayTicks, long periodTicks, Runnable task) {
		this.scheduler.runTaskTimerAsynchronously(this.plugin, task, delayTicks, periodTicks);
	}
}
