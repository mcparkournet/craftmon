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
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public final class Permission implements Iterable<String> {

	private static final Permission EMPTY_PERMISSION = new Permission();

	private Deque<String> nodes;

	public static PermissionBuilder builder() {
		return new PermissionBuilder();
	}

	public static PermissionBuilder builder(Permission permission) {
		return new PermissionBuilder(permission);
	}

	public static Permission empty() {
		return EMPTY_PERMISSION;
	}

	public static Permission of(String node) {
		Deque<String> deque = new LinkedList<>();
		deque.add(node);
		return new Permission(deque);
	}

	public static Permission of(String... nodes) {
		List<String> nodesList = List.of(nodes);
		return of(nodesList);
	}

	public static Permission of(Collection<String> nodes) {
		Deque<String> deque = new LinkedList<>(nodes);
		return new Permission(deque);
	}

	private Permission() {
		this(new LinkedList<>());
	}

	Permission(Deque<String> nodes) {
		this.nodes = nodes;
	}

	@Override
	public Iterator<String> iterator() {
		return this.nodes.iterator();
	}

	public Permission with(String node) {
		return new PermissionBuilder(this)
			.node(node)
			.build();
	}

	public Permission with(String... nodes) {
		return new PermissionBuilder(this)
			.nodes(nodes)
			.build();
	}

	public Permission with(Collection<String> nodes) {
		return new PermissionBuilder(this)
			.nodes(nodes)
			.build();
	}

	public Permission with(Permission permission) {
		return new PermissionBuilder(this)
			.with(permission)
			.build();
	}

	public Permission withoutFirst() {
		Deque<String> nodes = getNodes();
		nodes.removeFirst();
		return new Permission(nodes);
	}

	public Permission withoutLast() {
		Deque<String> nodes = getNodes();
		nodes.removeLast();
		return new Permission(nodes);
	}

	public String getName() {
		return String.join(".", this.nodes);
	}

	public String getFirstNode() {
		return this.nodes.getFirst();
	}

	public String getLastNode() {
		return this.nodes.getLast();
	}

	public int getNodesCount() {
		return this.nodes.size();
	}

	public boolean isEmpty() {
		return this.nodes.isEmpty();
	}

	public List<String> getNodesList() {
		return List.copyOf(this.nodes);
	}

	public Deque<String> getNodes() {
		return new LinkedList<>(this.nodes);
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (!(object instanceof Permission)) {
			return false;
		}
		Permission that = (Permission) object;
		return Objects.equals(this.nodes, that.nodes);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.nodes);
	}

	@Override
	public String toString() {
		return getName();
	}
}
