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
import java.util.LinkedList;
import java.util.List;

public final class PermissionBuilder {

	private Deque<String> nodes;

	PermissionBuilder() {
		this(new LinkedList<>());
	}

	PermissionBuilder(Permission permission) {
		this(permission.getNodes());
	}

	private PermissionBuilder(Deque<String> nodes) {
		this.nodes = nodes;
	}

	public PermissionBuilder with(Permission permission) {
		List<String> nodes = permission.getNodesList();
		return nodes(nodes);
	}

	public PermissionBuilder nodes(String... nodes) {
		List<String> nodesList = List.of(nodes);
		return nodes(nodesList);
	}

	public PermissionBuilder nodes(Collection<String> nodes) {
		this.nodes.addAll(nodes);
		return this;
	}

	public PermissionBuilder node(String node) {
		this.nodes.add(node);
		return this;
	}

	public Permission build() {
		return new Permission(this.nodes);
	}
}
