package org.openstack.client.cli.autocomplete;

import java.util.List;

import org.openstack.client.cli.OpenstackCliContext;
import org.openstack.client.storage.OpenstackStorageClient;
import org.openstack.model.storage.SwiftContainer;

import com.fathomdb.cli.CliContext;
import com.fathomdb.cli.autocomplete.SimpleArgumentAutoCompleter;
import com.google.common.collect.Lists;

public class ContainerNameAutoCompleter extends SimpleArgumentAutoCompleter {

	@Override
	public List<String> doComplete(CliContext context, String prefix) throws Exception {
		List<String> strings = Lists.newArrayList();

		OpenstackCliContext osContext = (OpenstackCliContext) context;
		OpenstackStorageClient client = osContext.getStorageClient();
		Iterable<SwiftContainer> items = client.root().containers().list();
		for (SwiftContainer item : items) {
			strings.add(item.getName());
		}
		addSuffix(strings, " ");

		return strings;
	}

}
