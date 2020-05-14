/*
 * MIT License
 *
 * Copyright (c) 2019-2020 MCParkour
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

package net.mcparkour.craftmon.permission;

import java.util.Collection;
import java.util.Deque;
import java.util.List;

public interface Permission extends Iterable<String> {

	static Permission empty() {
		return PermissionFactory.empty();
	}

	static Permission fromName(String name) {
		return PermissionFactory.fromName(name);
	}

	static Permission of(String node) {
		return PermissionFactory.of(node);
	}

	static Permission of(String... nodes) {
		return PermissionFactory.of(nodes);
	}

	static Permission of(Collection<String> nodes) {
		return PermissionFactory.of(nodes);
	}

	Permission withFirst(String node);

	Permission withFirst(String... nodes);

	Permission withFirst(Permission permission);

	Permission withFirst(Collection<String> nodes);

	Permission withLast(String node);

	Permission withLast(String... nodes);

	Permission withLast(Permission permission);

	Permission withLast(Collection<String> nodes);

	String getName();

	String getFirstNode();

	String getLastNode();

	int getNodesCount();

	boolean isEmpty();

	List<String> getNodesList();

	Deque<String> getNodes();
}
