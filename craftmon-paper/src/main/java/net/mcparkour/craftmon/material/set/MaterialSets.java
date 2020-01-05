/*
 * MIT License
 *
 * Copyright (c) 2019 MCParkour
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

import com.destroystokyo.paper.MaterialTags;
import org.bukkit.Material;
import org.bukkit.Tag;

public final class MaterialSets {

	public static final MaterialSet AIRS = MaterialSet.builder()
		.add(Material.AIR)
		.add(Material.CAVE_AIR)
		.add(Material.VOID_AIR)
		.build();

	public static final MaterialSet CHESTS = MaterialSet.builder()
		.add(Material.CHEST)
		.add(Material.TRAPPED_CHEST)
		.add(Material.ENDER_CHEST)
		.build();

	public static final MaterialSet FENCE_GATES = MaterialSet.builder()
		.add(Material.ACACIA_FENCE_GATE)
		.add(Material.BIRCH_FENCE_GATE)
		.add(Material.DARK_OAK_FENCE_GATE)
		.add(Material.JUNGLE_FENCE_GATE)
		.add(Material.OAK_FENCE_GATE)
		.add(Material.SPRUCE_FENCE_GATE)
		.build();

	public static final MaterialSet SNOWS = MaterialSet.builder()
		.add(Material.SNOW)
		.add(Material.SNOW_BLOCK)
		.build();

	public static final MaterialSet WOODEN_FENCES = MaterialSet.builder()
		.add(Material.ACACIA_FENCE)
		.add(Material.BIRCH_FENCE)
		.add(Material.DARK_OAK_FENCE)
		.add(Material.JUNGLE_FENCE)
		.add(Material.OAK_FENCE)
		.add(Material.SPRUCE_FENCE)
		.build();

	public static final MaterialSet FENCES = MaterialSet.builder()
		.with(WOODEN_FENCES)
		.add(Material.NETHER_BRICK_FENCE)
		.build();

	public static final MaterialSet TORCHES = MaterialSet.builder()
		.add(Material.TORCH)
		.add(Material.WALL_TORCH)
		.build();

	public static final MaterialSet REDSTONE_TORCHES = MaterialSet.builder()
		.add(Material.REDSTONE_TORCH)
		.add(Material.REDSTONE_WALL_TORCH)
		.build();

	public static final MaterialSet FLOWERS = MaterialSet.builder()
		.add(Material.DANDELION)
		.add(Material.POPPY)
		.add(Material.BLUE_ORCHID)
		.add(Material.ALLIUM)
		.add(Material.AZURE_BLUET)
		.add(Material.RED_TULIP)
		.add(Material.ORANGE_TULIP)
		.add(Material.WHITE_TULIP)
		.add(Material.PINK_TULIP)
		.add(Material.OXEYE_DAISY)
		.build();

	public static final MaterialSet MUSHROOMS = MaterialSet.builder()
		.add(Material.BROWN_MUSHROOM)
		.add(Material.RED_MUSHROOM)
		.build();

	public static final MaterialSet STEMS = MaterialSet.builder()
		.add(Material.ATTACHED_MELON_STEM)
		.add(Material.ATTACHED_PUMPKIN_STEM)
		.add(Material.MELON_STEM)
		.add(Material.PUMPKIN_STEM)
		.build();

	public static final MaterialSet FLORA = MaterialSet.builder()
		.with(FLOWERS)
		.with(MUSHROOMS)
		.with(STEMS)
		.add(Material.GRASS)
		.add(Material.TALL_GRASS)
		.add(Material.DEAD_BUSH)
		.add(Material.WHEAT)
		.add(Material.SUGAR_CANE)
		.add(Material.VINE)
		.add(Material.LILY_PAD)
		.add(Material.CACTUS)
		.add(Material.NETHER_WART)
		.build();

	public static final MaterialSet STONES = MaterialSet.builder()
		.add(Material.STONE)
		.add(Material.GRANITE)
		.add(Material.DIORITE)
		.add(Material.ANDESITE)
		.build();

	public static final MaterialSet GRASSES = MaterialSet.builder()
		.add(Material.GRASS_BLOCK)
		.add(Material.PODZOL)
		.build();

	public static final MaterialSet DIRT = MaterialSet.builder()
		.add(Material.DIRT)
		.add(Material.COARSE_DIRT)
		.build();

	public static final MaterialSet LIQUIDS = MaterialSet.builder()
		.add(Material.WATER)
		.add(Material.LAVA)
		.build();

	public static final MaterialSet FALLING = MaterialSet.builder()
		.with(LIQUIDS)
		.with(Tag.SAND)
		.add(Material.GRAVEL)
		.build();

	public static final MaterialSet OVERRIDEABLE = MaterialSet.builder()
		.with(STONES)
		.with(GRASSES)
		.with(DIRT)
		.with(Tag.SAND)
		.with(MaterialTags.SANDSTONES)
		.with(MaterialTags.RED_SANDSTONES)
		.add(Material.GRAVEL)
		.add(Material.MOSSY_COBBLESTONE)
		.add(Material.OBSIDIAN)
		.add(Material.SNOW)
		.add(Material.CLAY)
		.build();

	public static final MaterialSet OVERRIDEABLE_WITH_ORES = MaterialSet.builder()
		.with(OVERRIDEABLE)
		.with(MaterialTags.ORES)
		.build();

	private MaterialSets() {
		throw new UnsupportedOperationException("Cannot create an instance of this class");
	}
}
