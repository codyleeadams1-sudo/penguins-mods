package com.penguinmod;

import com.penguinmod.entity.PenguinEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

/**
 * Penguin Mod - adds a penguin mob that spawns naturally in snowy / icy biomes.
 * Biome spawning itself is data driven, see
 * data/penguinmod/neoforge/biome_modifier/penguin_spawns.json
 */
@Mod(PenguinMod.MODID)
public class PenguinMod {
    public static final String MODID = "penguinmod";

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(Registries.ENTITY_TYPE, MODID);
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);

    public static final DeferredHolder<EntityType<?>, EntityType<PenguinEntity>> PENGUIN =
            ENTITY_TYPES.register("penguin", () ->
                    EntityType.Builder.of(PenguinEntity::new, MobCategory.CREATURE)
                            .sized(0.6F, 1.0F)
                            .eyeHeight(0.85F)
                            .clientTrackingRange(8)
                            .build("penguin"));

    // Spawn egg uses the custom texture, so both tint colours are white (= no tint).
    public static final DeferredItem<DeferredSpawnEggItem> PENGUIN_SPAWN_EGG =
            ITEMS.register("penguin_spawn_egg", () ->
                    new DeferredSpawnEggItem(PENGUIN, 0xFFFFFF, 0xFFFFFF, new Item.Properties()));

    public PenguinMod(IEventBus modEventBus) {
        ENTITY_TYPES.register(modEventBus);
        ITEMS.register(modEventBus);

        modEventBus.addListener(this::registerAttributes);
        modEventBus.addListener(this::registerSpawnPlacements);
        modEventBus.addListener(this::addToCreativeTabs);
    }

    private void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(PENGUIN.get(), PenguinEntity.createAttributes().build());
    }

    private void registerSpawnPlacements(RegisterSpawnPlacementsEvent event) {
        event.register(PENGUIN.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                PenguinEntity::checkPenguinSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.OR);
    }

    private void addToCreativeTabs(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.SPAWN_EGGS) {
            event.accept(PENGUIN_SPAWN_EGG);
        }
    }
}
