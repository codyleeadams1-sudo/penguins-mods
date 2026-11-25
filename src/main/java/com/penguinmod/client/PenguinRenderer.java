package com.penguinmod.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.penguinmod.PenguinMod;
import com.penguinmod.entity.PenguinEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class PenguinRenderer extends MobRenderer<PenguinEntity, PenguinModel> {
    private static final ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath(PenguinMod.MODID, "textures/entity/penguin.png");

    /** The model is authored at ~27 pixels tall; scale it to roughly one block. */
    private static final float MODEL_SCALE = 0.62F;

    public PenguinRenderer(EntityRendererProvider.Context context) {
        super(context, new PenguinModel(context.bakeLayer(PenguinModel.LAYER)), 0.4F);
    }

    @Override
    public ResourceLocation getTextureLocation(PenguinEntity entity) {
        return TEXTURE;
    }

    @Override
    protected void scale(PenguinEntity entity, PoseStack poseStack, float partialTickTime) {
        float s = MODEL_SCALE * (entity.isBaby() ? 0.5F : 1.0F);
        poseStack.scale(s, s, s);
    }
}
