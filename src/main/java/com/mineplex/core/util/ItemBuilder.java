package com.mineplex.core.util;

import com.destroystokyo.paper.profile.ProfileProperty;
import io.papermc.paper.datacomponent.DataComponentType.Valued;
import io.papermc.paper.datacomponent.DataComponentTypes;
import io.papermc.paper.datacomponent.item.BlocksAttacks;
import io.papermc.paper.datacomponent.item.blocksattacks.DamageReduction;
import io.papermc.paper.datacomponent.item.blocksattacks.ItemDamageFunction;
import it.unimi.dsi.fastutil.objects.ObjectList;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.*;
import org.bukkit.inventory.meta.trim.ArmorTrim;
import org.bukkit.inventory.meta.trim.TrimMaterial;
import org.bukkit.inventory.meta.trim.TrimPattern;

import java.util.UUID;
import java.util.function.Consumer;

@SuppressWarnings("UnstableApiUsage")
public final class ItemBuilder {

    private final ItemStack itemStack;

    public ItemBuilder(ItemStack itemStack) {
        this.itemStack = itemStack.clone();
    }

    public ItemBuilder(Material material, int amount) {
        this.itemStack = ItemStack.of(material, amount);
    }

    public ItemBuilder(Material material) {
        this(material, 1);
    }

    public ItemBuilder modifyItem(Consumer<ItemStack> consumer) {
        consumer.accept(this.itemStack);
        return this;
    }

    public ItemBuilder setAmount(int amount) {
        return modifyItem(item -> item.setAmount(amount));
    }

    public <T> ItemBuilder setData(Valued<T> type, T value) {
        return modifyItem(item -> item.setData(type, value));
    }

    public ItemBuilder addEnchantment(Enchantment enchantment, int level) {
        return modifyItem(item -> item.addUnsafeEnchantment(enchantment, level));
    }

    public ItemBuilder setLegacySword() {

        return modifyItem(item -> {

            final var blocksAttacks = BlocksAttacks.blocksAttacks()
                    .disableCooldownScale(0.0F)
                    .addDamageReduction(DamageReduction.damageReduction()
                            .base(-0.5F)
                            .factor(0.5F)
                            .build())
                    .itemDamage(ItemDamageFunction.itemDamageFunction()
                            .threshold(0.0F)
                            .base(0.0F)
                            .factor(0.0F)
                            .build())
                    .blockSound(Key.key(Key.MINECRAFT_NAMESPACE, "entity.player.hurt"))
                    .build();

            item.setData(DataComponentTypes.BLOCKS_ATTACKS, blocksAttacks);

        });

    }

    public ItemBuilder modifyMeta(Consumer<ItemMeta> consumer) {

        return modifyItem(item -> {

            final var meta = item.getItemMeta();

            if (meta != null) {
                consumer.accept(meta);

                item.setItemMeta(meta);
            }

        });

    }

    public ItemBuilder setHeadTexture(String value) {

        return modifyMeta(meta -> {

            if (meta instanceof SkullMeta skullMeta) {

                final var playerProfile = Bukkit.createProfile(UUID.randomUUID());
                playerProfile.setProperty(new ProfileProperty("textures", value));

                skullMeta.setPlayerProfile(playerProfile);

            }

        });

    }

    public ItemBuilder setLeatherColor(Color color) {

        return modifyMeta(meta -> {

            if (meta instanceof LeatherArmorMeta leatherArmorMeta)
                leatherArmorMeta.setColor(color);

        });

    }

    public ItemBuilder setArmorTrim(TrimMaterial material, TrimPattern pattern) {

        return modifyMeta(meta -> {

            if (meta instanceof ArmorMeta armorMeta)
                armorMeta.setTrim(new ArmorTrim(material, pattern));

        });

    }

    public ItemBuilder setDamage(int damage) {

        return modifyMeta(meta -> {

            if (meta instanceof Damageable damageable) {
                damageable.setDamage(damage);
            }

        });

    }

    public ItemBuilder setGlider(boolean glider) {

        return modifyMeta(meta -> {

            if (meta instanceof Damageable damageable)
                damageable.setGlider(glider);

        });

    }

    public ItemBuilder setGlint(boolean glint) {
        return modifyMeta(meta -> meta.setEnchantmentGlintOverride(glint));
    }

    public ItemBuilder setUnbreakable(boolean unbreakable) {
        return modifyMeta(meta -> meta.setUnbreakable(unbreakable));
    }

    public ItemBuilder addFlags(ItemFlag... itemFlag) {
        return modifyMeta(meta -> meta.addItemFlags(itemFlag));
    }

    public ItemBuilder setName(Component component) {
        return modifyMeta(meta -> meta.displayName(component));
    }

    public ItemBuilder setLore(ObjectList<Component> lore) {
        return modifyMeta(meta -> meta.lore(lore));
    }

    public ItemStack build() {
        return itemStack;
    }

}