package net.mcparkour.craftmon.permission;

import java.util.Collection;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

final class PermissionFactory {

	private static final Permission EMPTY_PERMISSION = new EmptyPermission();

	private PermissionFactory() {
		throw new UnsupportedOperationException("Cannot create an instance of this class");
	}

	static Permission empty() {
		return EMPTY_PERMISSION;
	}

	static Permission fromName(String name) {
		String[] split = name.split("\\.");
		return of(split);
	}

	static Permission of(String node) {
		if (node.isEmpty()) {
			return EMPTY_PERMISSION;
		}
		Deque<String> nodesDeque = new LinkedList<>();
		nodesDeque.add(node);
		return new NotEmptyPermission(nodesDeque);
	}

	static Permission of(String... nodes) {
		List<String> nodesList = List.of(nodes);
		return of(nodesList);
	}

	static Permission of(Collection<String> nodes) {
		Deque<String> nodesDeque = nodes.stream()
			.filter(node -> !node.isEmpty())
			.collect(Collectors.toCollection(LinkedList::new));
		if (nodesDeque.isEmpty()) {
			return EMPTY_PERMISSION;
		}
		return new NotEmptyPermission(nodesDeque);
	}
}
