package com.penguinmod;

import com.penguinmod.entity.PenguinEntity;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.core.Registry;
import net.minecraft.tags.TagKey;

public class PenguinMod implements ModInitializer {
    public static final String MODID = "penguinmod";
    public static final Identifier PENGUIN_ID = Identifier.fromNamespaceAndPath(MODID, "penguin");
    public static final ResourceKey<EntityType<?>> PENGUIN_KEY = ResourceKey.create(Registries.ENTITY_TYPE, PENGUIN_ID);
    public static final EntityType<PenguinEntity> PENGUIN = Registry.register(
            BuiltInRegistries.ENTITY_TYPE, PENGUIN_KEY,
            EntityType.Builder.of(PenguinEntity::new, MobCategory.CREATURE)
                    .sized(0.6F, 1.0F)
                    .eyeHeight(0.85F)
                    .clientTrackingRange(8)
                    .build(PENGUIN_KEY));
    public static final Item PENGUIN_SPAWN_EGG = Registry.register(
            BuiltInRegistries.ITEM,
            Identifier.fromNamespaceAndPath(MODID, "penguin_spawn_egg"),
            new SpawnEggItem(new Item.Properties().spawnEgg(PENGUIN)));

    private static final TagKey<Biome> PENGUIN_BIOMES =
            TagKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath(MODID, "spawns_penguins"));

    @Override
    public void onInitialize() {
        FabricDefaultAttributeRegistry.register(PENGUIN, PenguinEntity.createAttributes());
        SpawnPlacements.register(PENGUIN, SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, PenguinEntity::checkPenguinSpawnRules);
        BiomeModifications.addSpawn(BiomeSelectors.tag(PENGUIN_BIOMES), MobCategory.CREATURE, PENGUIN, 10, 2, 5);
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.SPAWN_EGGS)
                .register(entries -> entries.accept(PENGUIN_SPAWN_EGG));
    }
}
