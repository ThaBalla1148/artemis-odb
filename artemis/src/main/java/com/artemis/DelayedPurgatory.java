package com.artemis;

import com.artemis.utils.Bag;

import java.util.BitSet;

class DelayedPurgatory<A extends Component> extends Purgatory<A> {
	final BitSet idBits = new BitSet();
	final BatchChangeProcessor batchProcessor;

	DelayedPurgatory(Bag<A> components, ComponentPool pool, BatchChangeProcessor batchProcessor) {
		super(components, pool);
		this.batchProcessor = batchProcessor;
	}

	@Override
	void mark(int entityId) {
		if (idBits.isEmpty()) // see cm#clean
			batchProcessor.purgatories.add(this);

		idBits.set(entityId);
	}

	@Override
	void purge() {
		if (pool != null)
			purgeWithPool();
		else
			purgeNoPool();

		idBits.clear();
	}

	@Override
	boolean has(int entityId) {
		return idBits.get(entityId);
	}

	private void purgeWithPool() {
		for (int id = idBits.nextSetBit(0); id >= 0; id = idBits.nextSetBit(id + 1)) {
			A c = components.get(id);
				pool.free((PooledComponent) c);

			components.fastSet(id, null);
		}
	}

	private void purgeNoPool() {
		for (int id = idBits.nextSetBit(0); id >= 0; id = idBits.nextSetBit(id + 1)) {
			components.fastSet(id, null);
		}
	}
}
