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

package net.mcparkour.craftmon.material.set;

import java.util.Collection;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.Set;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.data.BlockData;

public class MaterialSet implements Iterable<Material> {

	private Set<Material> materials;

	public static MaterialSetBuilder builder() {
		return new MaterialSetBuilder();
	}

	public MaterialSet(Collection<Material> materials) {
		this.materials = EnumSet.copyOf(materials);
	}

	public boolean contains(Block block) {
		Material type = block.getType();
		return contains(type);
	}

	public boolean contains(BlockData blockData) {
		Material material = blockData.getMaterial();
		return contains(material);
	}

	public boolean contains(BlockState blockState) {
		Material type = blockState.getType();
		return contains(type);
	}

	public boolean contains(Material material) {
		return this.materials.contains(material);
	}

	@Override
	public Iterator<Material> iterator() {
		return this.materials.iterator();
	}

	public Set<Material> getMaterials() {
		return Set.copyOf(this.materials);
	}
}
