package com.artemis;

import com.artemis.utils.Bag;

abstract class Purgatory<A extends Component> {
	protected final ComponentPool pool;
	final Bag<A> components;

	public Purgatory(Bag<A> components, ComponentPool pool) {
		this.components = components;
		this.pool = pool;
	}

	abstract void mark(int entityId);
	abstract void purge();
	abstract boolean has(int entityId);
}
