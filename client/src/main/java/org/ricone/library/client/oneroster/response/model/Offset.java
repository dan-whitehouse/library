package org.ricone.library.client.oneroster.response.model;

import org.ricone.library.client.core.Model;

import java.util.Iterator;

/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 2020.1
 * @since 2020-02-05
 */

public class Offset extends Model implements Iterator<Integer>  {
	private int current = 0;
	private int[] offsets;

	public Offset() {
		this.offsets = new int[]{0};
	}

	public Offset(int[] offsets) {
		this.offsets = offsets;
	}

	public int[] getOffsets() {
		return offsets;
	}

	public void setOffsets(int[] offsets) {
		this.offsets = offsets;
	}

	@Override
	public boolean hasNext() {
		return (current < offsets.length);
	}

	@Override
	public Integer next() {
		return offsets[current++];
	}

	@Override
	public boolean isEmpty() {
		return offsets == null || offsets.length == 0;
	}
}
