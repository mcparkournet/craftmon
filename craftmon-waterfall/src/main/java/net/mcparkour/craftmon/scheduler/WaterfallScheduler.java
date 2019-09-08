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
import java.util.concurrent.TimeUnit;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.scheduler.TaskScheduler;

public class WaterfallScheduler implements Scheduler {

	private TaskScheduler scheduler;
	private Plugin plugin;

	public WaterfallScheduler(Plugin plugin) {
		this(plugin.getProxy(), plugin);
	}

	public WaterfallScheduler(ProxyServer server, Plugin plugin) {
		this(server.getScheduler(), plugin);
	}

	public WaterfallScheduler(TaskScheduler scheduler, Plugin plugin) {
		this.scheduler = scheduler;
		this.plugin = plugin;
	}

	@Override
	public void run(Runnable task) {
		this.scheduler.runAsync(this.plugin, task);
	}

	@Override
	public void delay(long delayTicks, Runnable task) {
		Duration delay = Duration.ofMillis(delayTicks * 50);
		delay(delay, task);
	}

	@Override
	public void delay(Duration delay, Runnable task) {
		this.scheduler.schedule(this.plugin, task, delay.toNanos(), TimeUnit.NANOSECONDS);
	}

	@Override
	public void timer(long periodTicks, Runnable task) {
		Duration period = Duration.ofMillis(periodTicks * 50);
		timer(period, task);
	}

	@Override
	public void timer(Duration period, Runnable task) {
		this.scheduler.schedule(this.plugin, task, 0, period.toNanos(), TimeUnit.NANOSECONDS);
	}

	@Override
	public void timer(long delayTicks, long periodTicks, Runnable task) {
		Duration delay = Duration.ofMillis(delayTicks * 50);
		Duration period = Duration.ofMillis(periodTicks * 50);
		timer(delay, period, task);
	}

	@Override
	public void timer(Duration delay, Duration period, Runnable task) {
		this.scheduler.schedule(this.plugin, task, delay.toNanos(), period.toNanos(), TimeUnit.NANOSECONDS);
	}

	public TaskScheduler getScheduler() {
		return this.scheduler;
	}
}
