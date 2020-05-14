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
import java.util.stream.Collectors;

public class NotEmptyPermission implements Permission {

	private Deque<String> nodes;

	NotEmptyPermission(Deque<String> nodes) {
		if (nodes.isEmpty()) {
			throw new IllegalArgumentException("Nodes are empty");
		}
		this.nodes = nodes;
	}

	@Override
	public Permission withFirst(String node) {
		if (node.isEmpty()) {
			return this;
		}
		Deque<String> nodesDeque = getNodes();
		nodesDeque.addFirst(node);
		return new NotEmptyPermission(nodesDeque);
	}

	@Override
	public Permission withFirst(String... nodes) {
		List<String> nodesList = List.of(nodes);
		return withFirst(nodesList);
	}

	@Override
	public Permission withFirst(Permission permission) {
		Deque<String> thatNodes = permission.getNodes();
		return withFirst(thatNodes);
	}

	@Override
	public Permission withFirst(Collection<String> nodes) {
		Deque<String> nodesDeque = nodes.stream()
			.filter(node -> !node.isEmpty())
			.collect(Collectors.toCollection(LinkedList::new));
		if (nodesDeque.isEmpty()) {
			return this;
		}
		Deque<String> thisNodes = getNodes();
		nodesDeque.addAll(thisNodes);
		return new NotEmptyPermission(nodesDeque);
	}

	@Override
	public Permission withLast(String node) {
		if (node.isEmpty()) {
			return this;
		}
		Deque<String> nodesDeque = getNodes();
		nodesDeque.addLast(node);
		return new NotEmptyPermission(nodesDeque);
	}

	@Override
	public Permission withLast(String... nodes) {
		List<String> nodesList = List.of(nodes);
		return withLast(nodesList);
	}

	@Override
	public Permission withLast(Permission permission) {
		Deque<String> thatNodes = permission.getNodes();
		return withLast(thatNodes);
	}

	@Override
	public Permission withLast(Collection<String> nodes) {
		Deque<String> nodesDeque = nodes.stream()
			.filter(node -> !node.isEmpty())
			.collect(Collectors.toCollection(LinkedList::new));
		if (nodesDeque.isEmpty()) {
			return this;
		}
		Deque<String> thisNodes = getNodes();
		thisNodes.addAll(nodesDeque);
		return new NotEmptyPermission(thisNodes);
	}

	@Override
	public String getName() {
		return String.join(".", this.nodes);
	}

	@Override
	public String getFirstNode() {
		return this.nodes.getFirst();
	}

	@Override
	public String getLastNode() {
		return this.nodes.getLast();
	}

	@Override
	public int getNodesCount() {
		return this.nodes.size();
	}

	@Override
	public boolean isEmpty() {
		return this.nodes.isEmpty();
	}

	@Override
	public List<String> getNodesList() {
		return List.copyOf(this.nodes);
	}

	@Override
	public Deque<String> getNodes() {
		return new LinkedList<>(this.nodes);
	}

	@Override
	public Iterator<String> iterator() {
		return this.nodes.iterator();
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (!(object instanceof NotEmptyPermission)) {
			return false;
		}
		NotEmptyPermission that = (NotEmptyPermission) object;
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
