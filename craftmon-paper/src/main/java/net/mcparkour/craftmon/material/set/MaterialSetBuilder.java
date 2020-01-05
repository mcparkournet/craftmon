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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.bukkit.Material;
import org.bukkit.Tag;

public class MaterialSetBuilder {

	private List<Material> materials = new ArrayList<>(1);

	public MaterialSetBuilder add(Material material) {
		this.materials.add(material);
		return this;
	}

	public MaterialSetBuilder with(Material... materials) {
		List<Material> list = List.of(materials);
		this.materials.addAll(list);
		return this;
	}

	public MaterialSetBuilder with(Collection<Material> materials) {
		this.materials.addAll(materials);
		return this;
	}

	public MaterialSetBuilder with(MaterialSet materialSet) {
		Set<Material> materials = materialSet.getMaterials();
		this.materials.addAll(materials);
		return this;
	}

	public MaterialSetBuilder with(Tag<Material> tag) {
		Set<Material> materials = tag.getValues();
		this.materials.addAll(materials);
		return this;
	}

	public MaterialSetBuilder filtered(Predicate<? super Material> filter) {
		List<Material> materials = Arrays.stream(Material.values())
			.filter(filter)
			.collect(Collectors.toList());
		this.materials.addAll(materials);
		return this;
	}

	public MaterialSet build() {
		return new MaterialSet(this.materials);
	}
}
