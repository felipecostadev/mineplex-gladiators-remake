package com.mineplex.player;

import com.mineplex.core.util.ItemBuilder;
import com.mineplex.player.user.User;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public final class PlayerManager {

    private final ItemStack THREE_ARROWS = ItemStack.of(Material.ARROW, 3);

    private final ItemStack FIVE_ARROWS = ItemStack.of(Material.ARROW, 5);

    private final ItemStack SEVEN_ARROWS = ItemStack.of(Material.ARROW, 7);

    private final ItemStack TEN_ARROWS = ItemStack.of(Material.ARROW, 10);

    private final ItemStack SOUP = ItemStack.of(Material.MUSHROOM_STEW);

    private final ItemStack BOW = new ItemBuilder(Material.BOW)
            .setUnbreakable(true)
            .build();

    private final ItemStack WOODEN_SWORD = new ItemBuilder(Material.WOODEN_SWORD)
            .setUnbreakable(true)
            .setLegacySword()
            .build();

    private final ItemStack STONE_SWORD = new ItemBuilder(Material.STONE_SWORD)
            .setUnbreakable(true)
            .setLegacySword()
            .build();

    private final ItemStack IRON_SWORD = new ItemBuilder(Material.IRON_SWORD)
            .setUnbreakable(true)
            .setLegacySword()
            .build();

    private final ItemStack DIAMOND_SWORD = new ItemBuilder(Material.DIAMOND_SWORD)
            .setUnbreakable(true)
            .setLegacySword()
            .build();

    private final ItemStack LEATHER_HELMET = new ItemBuilder(Material.LEATHER_HELMET)
            .setUnbreakable(true)
            .build();

    private final ItemStack GOLDEN_HELMET = new ItemBuilder(Material.GOLDEN_HELMET)
            .setUnbreakable(true)
            .build();

    private final ItemStack CHAINMAIL_HELMET = new ItemBuilder(Material.CHAINMAIL_HELMET)
            .setUnbreakable(true)
            .build();

    private final ItemStack IRON_HELMET = new ItemBuilder(Material.IRON_HELMET)
            .setUnbreakable(true)
            .build();

    private final ItemStack LEATHER_CHESTPLATE = new ItemBuilder(Material.LEATHER_CHESTPLATE)
            .setUnbreakable(true)
            .build();

    private final ItemStack GOLDEN_CHESTPLATE = new ItemBuilder(Material.GOLDEN_CHESTPLATE)
            .setUnbreakable(true)
            .build();

    private final ItemStack CHAINMAIL_CHESTPLATE = new ItemBuilder(Material.CHAINMAIL_CHESTPLATE)
            .setUnbreakable(true)
            .build();

    private final ItemStack IRON_CHESTPLATE = new ItemBuilder(Material.IRON_CHESTPLATE)
            .setUnbreakable(true)
            .build();

    private final ItemStack LEATHER_LEGGINGS = new ItemBuilder(Material.LEATHER_LEGGINGS)
            .setUnbreakable(true)
            .build();

    private final ItemStack GOLDEN_LEGGINGS = new ItemBuilder(Material.GOLDEN_LEGGINGS)
            .setUnbreakable(true)
            .build();

    private final ItemStack CHAINMAIL_LEGGINGS = new ItemBuilder(Material.CHAINMAIL_LEGGINGS)
            .setUnbreakable(true)
            .build();

    private final ItemStack IRON_LEGGINGS = new ItemBuilder(Material.IRON_LEGGINGS)
            .setUnbreakable(true)
            .build();

    private final ItemStack LEATHER_BOOTS = new ItemBuilder(Material.LEATHER_BOOTS)
            .setUnbreakable(true)
            .build();

    private final ItemStack GOLDEN_BOOTS = new ItemBuilder(Material.GOLDEN_BOOTS)
            .setUnbreakable(true)
            .build();

    private final ItemStack CHAINMAIL_BOOTS = new ItemBuilder(Material.CHAINMAIL_BOOTS)
            .setUnbreakable(true)
            .build();

    private final ItemStack IRON_BOOTS = new ItemBuilder(Material.IRON_BOOTS)
            .setUnbreakable(true)
            .build();

    public void grantTemplatePerState(Player player, User user) {

        final var inventory = player.getInventory();
        inventory.setHeldItemSlot(0);
        inventory.clear();

        switch (user.getState()) {

            case ROUND_OF_PLAYERS -> {

                inventory.setItem(0, WOODEN_SWORD);
                inventory.setItem(8, THREE_ARROWS);
                inventory.setHelmet(LEATHER_HELMET);
                inventory.setChestplate(LEATHER_CHESTPLATE);
                inventory.setLeggings(LEATHER_LEGGINGS);
                inventory.setBoots(LEATHER_BOOTS);

            }

            case QUARTER_FINALS -> {

                inventory.setItem(0, STONE_SWORD);
                inventory.setItem(8, FIVE_ARROWS);
                inventory.setHelmet(GOLDEN_HELMET);
                inventory.setChestplate(GOLDEN_CHESTPLATE);
                inventory.setLeggings(GOLDEN_LEGGINGS);
                inventory.setBoots(GOLDEN_BOOTS);

            }

            case SEMI_FINALS -> {

                inventory.setItem(0, IRON_SWORD);
                inventory.setItem(8, SEVEN_ARROWS);
                inventory.setHelmet(CHAINMAIL_HELMET);
                inventory.setChestplate(CHAINMAIL_CHESTPLATE);
                inventory.setLeggings(CHAINMAIL_LEGGINGS);
                inventory.setBoots(CHAINMAIL_BOOTS);

            }

            case FINALS -> {

                inventory.setItem(0, DIAMOND_SWORD);
                inventory.setItem(8, TEN_ARROWS);
                inventory.setHelmet(IRON_HELMET);
                inventory.setChestplate(IRON_CHESTPLATE);
                inventory.setLeggings(IRON_LEGGINGS);
                inventory.setBoots(IRON_BOOTS);

            }

        }

        final var ability = user.getAbility();

        if (ability != null)
            inventory.setItem(1, ability.getItemStack());

        inventory.setItem(2, BOW);

        for (var index = 3; index < 8; index++)
            inventory.setItem(index, SOUP);

    }

}