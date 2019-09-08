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
import com.velocitypowered.api.proxy.ProxyServer;

public class VelocityScheduler implements Scheduler {

	private com.velocitypowered.api.scheduler.Scheduler scheduler;
	private Object plugin;

	public VelocityScheduler(ProxyServer server, Object plugin) {
		this(server.getScheduler(), plugin);
	}

	public VelocityScheduler(com.velocitypowered.api.scheduler.Scheduler scheduler, Object plugin) {
		this.scheduler = scheduler;
		this.plugin = plugin;
	}

	@Override
	public void run(Runnable task) {
		this.scheduler.buildTask(this.plugin, task).schedule();
	}

	@Override
	public void delay(long delayTicks, Runnable task) {
		Duration delay = Duration.ofMillis(delayTicks * 50);
		delay(delay, task);
	}

	@Override
	public void delay(Duration delay, Runnable task) {
		this.scheduler.buildTask(this.plugin, task)
			.delay(delay.toNanos(), TimeUnit.NANOSECONDS)
			.schedule();
	}

	@Override
	public void timer(long periodTicks, Runnable task) {
		Duration period = Duration.ofMillis(periodTicks * 50);
		timer(period, task);
	}

	@Override
	public void timer(Duration period, Runnable task) {
		this.scheduler.buildTask(this.plugin, task)
			.repeat(period.toNanos(), TimeUnit.NANOSECONDS)
			.schedule();
	}

	@Override
	public void timer(long delayTicks, long periodTicks, Runnable task) {
		Duration delay = Duration.ofMillis(delayTicks * 50);
		Duration period = Duration.ofMillis(periodTicks * 50);
		timer(delay, period, task);
	}

	@Override
	public void timer(Duration delay, Duration period, Runnable task) {
		this.scheduler.buildTask(this.plugin, task)
			.delay(delay.toNanos(), TimeUnit.NANOSECONDS)
			.repeat(period.toNanos(), TimeUnit.NANOSECONDS)
			.schedule();
	}

	public com.velocitypowered.api.scheduler.Scheduler getScheduler() {
		return this.scheduler;
	}
}
