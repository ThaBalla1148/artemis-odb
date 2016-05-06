package com.artemis;

import com.artemis.utils.Bag;

public class ImmediatePurgatory <A extends Component> extends Purgatory<A> {
	public ImmediatePurgatory(Bag<A> components, ComponentPool pool) {
		super(components, pool);
	}

	@Override
	void mark(int entityId) {
		if (pool != null) {
			PooledComponent c = (PooledComponent) components.get(entityId);
			if (c != null) pool.free(c);
		}
		components.fastSet(entityId, null);
	}

	@Override
	void purge() {}

	@Override
	boolean has(int entityId) {
		return false;
	}
}
