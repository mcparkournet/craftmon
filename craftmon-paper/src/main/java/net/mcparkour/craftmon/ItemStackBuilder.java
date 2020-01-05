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

package net.mcparkour.craftmon;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import com.destroystokyo.paper.Namespaced;
import com.destroystokyo.paper.inventory.meta.ArmorStandMeta;
import com.destroystokyo.paper.profile.PlayerProfile;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.block.BlockState;
import org.bukkit.block.banner.Pattern;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;
import org.bukkit.inventory.meta.BlockStateMeta;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.FireworkEffectMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.KnowledgeBookMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.MapMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.inventory.meta.Repairable;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.inventory.meta.TropicalFishBucketMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class ItemStackBuilder {

	private static final Colorizer COLORIZER = new PaperColorizer();

	private Material type;
	private int amount = 1;
	private List<Consumer<ItemMeta>> metaModifiers = new ArrayList<>();

	public ItemStackBuilder() {
		this(Material.AIR);
	}

	public ItemStackBuilder(Material type) {
		this.type = type;
	}

	public ItemStackBuilder type(Material type) {
		this.type = type;
		return this;
	}

	public ItemStackBuilder amount(int amount) {
		this.amount = amount;
		return this;
	}

	public ItemStackBuilder colorizedName(String name) {
		String colorized = COLORIZER.colorized(name);
		return name(colorized);
	}

	public ItemStackBuilder name(String name) {
		return meta(meta -> meta.setDisplayName(name));
	}

	public ItemStackBuilder colorizedLocalizedName(String name) {
		String colorized = COLORIZER.colorized(name);
		return localizedName(colorized);
	}

	public ItemStackBuilder localizedName(String name) {
		return meta(meta -> meta.setLocalizedName(name));
	}

	public ItemStackBuilder colorizedLore(String... lines) {
		String[] colorized = COLORIZER.colorized(lines);
		return lore(colorized);
	}

	public ItemStackBuilder colorizedLore(List<String> lines) {
		List<String> colorized = COLORIZER.colorized(lines);
		return lore(colorized);
	}

	public ItemStackBuilder lore(String... lines) {
		List<String> loreList = List.of(lines);
		return lore(loreList);
	}

	public ItemStackBuilder lore(List<String> lines) {
		return meta(meta -> meta.setLore(lines));
	}

	public ItemStackBuilder enchantment(Enchantment enchantment, int level) {
		return meta(meta -> meta.addEnchant(enchantment, level, true));
	}

	public ItemStackBuilder flag(ItemFlag flag) {
		return meta(meta -> meta.addItemFlags(flag));
	}

	public ItemStackBuilder unbreakable() {
		return meta(meta -> meta.setUnbreakable(true));
	}

	public ItemStackBuilder attributeModifier(Attribute attribute, AttributeModifier modifier) {
		return meta(meta -> meta.addAttributeModifier(attribute, modifier));
	}

	public <T, Z> ItemStackBuilder customTag(NamespacedKey key, PersistentDataType<T, Z> type, Z value) {
		return meta(meta -> {
			PersistentDataContainer persistentDataContainer = meta.getPersistentDataContainer();
			persistentDataContainer.set(key, type, value);
		});
	}

	public ItemStackBuilder destroyable(Material... types) {
		List<Material> typesList = List.of(types);
		return destroyable(typesList);
	}

	public ItemStackBuilder destroyable(List<Material> types) {
		List<NamespacedKey> keys = types.stream()
			.map(Material::getKey)
			.collect(Collectors.toList());
		return destroyableKeys(keys);
	}

	public ItemStackBuilder destroyableKeys(Namespaced... keys) {
		List<Namespaced> keysList = List.of(keys);
		return destroyableKeys(keysList);
	}

	public ItemStackBuilder destroyableKeys(List<? extends Namespaced> keys) {
		return meta(meta -> {
			List<Namespaced> keysList = new ArrayList<>(keys);
			meta.setDestroyableKeys(keysList);
		});
	}

	public ItemStackBuilder placeable(Material... types) {
		List<Material> typesList = List.of(types);
		return placeable(typesList);
	}

	public ItemStackBuilder placeable(List<Material> types) {
		List<NamespacedKey> keys = types.stream()
			.map(Material::getKey)
			.collect(Collectors.toList());
		return placeableKeys(keys);
	}

	public ItemStackBuilder placeableKeys(Namespaced... keys) {
		List<Namespaced> keysList = List.of(keys);
		return placeableKeys(keysList);
	}

	public ItemStackBuilder placeableKeys(List<? extends Namespaced> keys) {
		return meta(meta -> {
			List<Namespaced> keysList = new ArrayList<>(keys);
			meta.setPlaceableKeys(keysList);
		});
	}

	public ItemStackBuilder armorStand(Consumer<ArmorStandMeta> modifier) {
		return meta(ArmorStandMeta.class, modifier);
	}

	public ItemStackBuilder bannerPattern(Pattern pattern) {
		return meta(BannerMeta.class, meta -> meta.addPattern(pattern));
	}

	public ItemStackBuilder blockState(BlockState blockState) {
		return meta(BlockStateMeta.class, meta -> meta.setBlockState(blockState));
	}

	public ItemStackBuilder book(Consumer<BookMeta> modifier) {
		return meta(BookMeta.class, modifier);
	}

	public ItemStackBuilder storedEnchantment(Enchantment enchantment, int level) {
		return meta(EnchantmentStorageMeta.class, meta -> meta.addStoredEnchant(enchantment, level, true));
	}

	public ItemStackBuilder fireworkEffect(FireworkEffect effect) {
		return meta(FireworkEffectMeta.class, meta -> meta.setEffect(effect));
	}

	public ItemStackBuilder knowledgeBookRecipe(Material recipe) {
		NamespacedKey key = recipe.getKey();
		return knowledgeBookRecipe(key);
	}

	public ItemStackBuilder knowledgeBookRecipe(NamespacedKey recipe) {
		return meta(KnowledgeBookMeta.class, meta -> meta.addRecipe(recipe));
	}

	public ItemStackBuilder leatherArmorColor(Color color) {
		return meta(LeatherArmorMeta.class, meta -> meta.setColor(color));
	}

	public ItemStackBuilder map(Consumer<MapMeta> modifier) {
		return meta(MapMeta.class, modifier);
	}

	public ItemStackBuilder potion(Consumer<PotionMeta> modifier) {
		return meta(PotionMeta.class, modifier);
	}

	public ItemStackBuilder headProfile(PlayerProfile profile) {
		return meta(SkullMeta.class, meta -> meta.setPlayerProfile(profile));
	}

	public ItemStackBuilder tropicalFishBucket(Consumer<TropicalFishBucketMeta> modifier) {
		return meta(TropicalFishBucketMeta.class, modifier);
	}

	public ItemStackBuilder damage(int damage) {
		return meta(meta -> {
			Damageable damageable = (Damageable) meta;
			damageable.setDamage(damage);
		});
	}

	public ItemStackBuilder repairCost(int repairCost) {
		return meta(meta -> {
			Repairable repairable = (Repairable) meta;
			repairable.setRepairCost(repairCost);
		});
	}

	public <T extends ItemMeta> ItemStackBuilder meta(Class<T> type, Consumer<T> modifier) {
		return meta(meta -> {
			if (type.isInstance(meta)) {
				T typedMeta = type.cast(meta);
				modifier.accept(typedMeta);
			}
		});
	}

	public ItemStackBuilder meta(Consumer<ItemMeta> modifier) {
		this.metaModifiers.add(modifier);
		return this;
	}

	public ItemStack build() {
		ItemStack itemStack = new ItemStack(this.type);
		itemStack.setAmount(this.amount);
		ItemMeta meta = itemStack.getItemMeta();
		this.metaModifiers.forEach(modifier -> modifier.accept(meta));
		itemStack.setItemMeta(meta);
		return itemStack;
	}
}
