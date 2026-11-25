package com.penguinmod.client;

import com.penguinmod.PenguinMod;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class PenguinClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityModelLayerRegistry.registerModelLayer(PenguinModel.LAYER, PenguinModel::createBodyLayer);
        EntityRendererRegistry.register(PenguinMod.PENGUIN, PenguinRenderer::new);
    }
}
