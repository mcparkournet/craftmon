package net.mcparkour.craftmon.permission;

import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

class EmptyPermission implements Permission {

	private static final Deque<String> EMPTY_DEQUE = new LinkedList<>();

	@Override
	public Permission withFirst(String node) {
		return this;
	}

	@Override
	public Permission withFirst(String... nodes) {
		return this;
	}

	@Override
	public Permission withFirst(Permission permission) {
		return this;
	}

	@Override
	public Permission withFirst(Collection<String> nodes) {
		return this;
	}

	@Override
	public Permission withLast(String node) {
		return this;
	}

	@Override
	public Permission withLast(String... nodes) {
		return this;
	}

	@Override
	public Permission withLast(Permission permission) {
		return this;
	}

	@Override
	public Permission withLast(Collection<String> nodes) {
		return this;
	}

	@Override
	public String getName() {
		return "";
	}

	@Override
	public String getFirstNode() {
		throw new NoSuchElementException("Permission is empty");
	}

	@Override
	public String getLastNode() {
		throw new NoSuchElementException("Permission is empty");
	}

	@Override
	public int getNodesCount() {
		return 0;
	}

	@Override
	public boolean isEmpty() {
		return true;
	}

	@Override
	public List<String> getNodesList() {
		return Collections.emptyList();
	}

	@Override
	public Deque<String> getNodes() {
		return EMPTY_DEQUE;
	}

	@Override
	public Iterator<String> iterator() {
		return Collections.emptyIterator();
	}

	@Override
	public String toString() {
		return getName();
	}
}
