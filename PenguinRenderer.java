package com.penguinmod.client;

import com.penguinmod.PenguinMod;
import com.penguinmod.entity.PenguinEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;

public class PenguinRenderer extends MobRenderer<PenguinEntity, LivingEntityRenderState, PenguinModel> {
    private static final Identifier TEXTURE =
            Identifier.fromNamespaceAndPath(PenguinMod.MODID, "textures/entity/penguin.png");

    public PenguinRenderer(EntityRendererProvider.Context context) {
        super(context, new PenguinModel(context.bakeLayer(PenguinModel.LAYER)), 0.4F);
    }

    @Override
    public LivingEntityRenderState createRenderState() {
        return new LivingEntityRenderState();
    }

    @Override
    public Identifier getTextureLocation(LivingEntityRenderState state) {
        return TEXTURE;
    }
}
