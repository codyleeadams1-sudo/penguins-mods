package com.penguinmod.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.penguinmod.PenguinMod;
import com.penguinmod.entity.PenguinEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

/**
 * Model generated from the Blockbench penguin (blockbench/penguin.bbmodel).
 * Texture: assets/penguinmod/textures/entity/penguin.png (128x64, standard box UV).
 */
public class PenguinModel extends EntityModel<PenguinEntity> {
    public static final ModelLayerLocation LAYER = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(PenguinMod.MODID, "penguin"), "main");

    private static final float FLIPPER_BASE = 0.1396F;   // ~8 degrees, flares outward
    private static final float TAIL_BASE = -0.3142F;     // ~-18 degrees

    private final ModelPart root;
    private final ModelPart body;
    private final ModelPart head;
    private final ModelPart flipperPos;
    private final ModelPart flipperNeg;
    private final ModelPart tail;
    private final ModelPart footPos;
    private final ModelPart footNeg;

    public PenguinModel(ModelPart root) {
        this.root = root;
        this.body = root.getChild("body");
        this.head = this.body.getChild("head");
        this.flipperPos = this.body.getChild("flipper_pos");
        this.flipperNeg = this.body.getChild("flipper_neg");
        this.tail = this.body.getChild("tail");
        this.footPos = root.getChild("foot_pos");
        this.footNeg = root.getChild("foot_neg");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        PartDefinition bodyDef = root.addOrReplaceChild("body",
            CubeListBuilder.create()
                .texOffs(50, 18).addBox(-6.0F, -5.0F, -5.0F, 12F, 5F, 10F) /* body-01 */
                .texOffs(0, 18).addBox(-7.0F, -8.36F, -5.5F, 14F, 5F, 11F) /* body-02 */
                .texOffs(0, 0).addBox(-7.0F, -14.36F, -5.5F, 14F, 7F, 11F) /* body-03 */
                .texOffs(0, 34).addBox(-6.0F, -18.12F, -5.0F, 12F, 5F, 10F) /* body-04 */,
            PartPose.offset(0.0F, 22.4F, 0.0F));

        PartDefinition headDef = bodyDef.addOrReplaceChild("head",
            CubeListBuilder.create()
                .texOffs(50, 0).addBox(-5.0F, -7.2F, -5.06F, 10F, 8F, 9F) /* head-01 */
                .texOffs(44, 34).addBox(-5.5F, -8.6F, -5.52F, 11F, 3F, 10F) /* head-crown */
                .texOffs(52, 49).addBox(-2.0F, -3.84F, -8.6F, 4F, 2F, 4F) /* beak-upper */
                .texOffs(82, 49).addBox(-1.5F, -2.56F, -7.7F, 3F, 2F, 3F) /* beak-lower */
                .texOffs(94, 49).addBox(-1.0F, -4.0F, -9.58F, 2F, 2F, 1F) /* beak-tip */,
            PartPose.offset(0.0F, -17.6F, 0.0F));

        PartDefinition flipper_posDef = bodyDef.addOrReplaceChild("flipper_pos",
            CubeListBuilder.create()
                .texOffs(88, 0).addBox(-1.6F, -1.08F, -3.0F, 2F, 11F, 6F) /* flipper-east-01 */
                .texOffs(24, 49).addBox(-1.44F, 9.44F, -2.5F, 2F, 2F, 5F) /* flipper-east-02 */,
            PartPose.offsetAndRotation(-6.4F, -15.04F, 0.0F, 0.00000F, 0.00000F, 0.13963F));

        PartDefinition flipper_negDef = bodyDef.addOrReplaceChild("flipper_neg",
            CubeListBuilder.create()
                .texOffs(104, 0).addBox(-0.4F, -1.08F, -3.0F, 2F, 11F, 6F) /* flipper-west-01 */
                .texOffs(38, 49).addBox(-0.56F, 9.44F, -2.5F, 2F, 2F, 5F) /* flipper-west-02 */,
            PartPose.offsetAndRotation(6.4F, -15.04F, 0.0F, 0.00000F, 0.00000F, -0.13963F));

        PartDefinition tailDef = bodyDef.addOrReplaceChild("tail",
            CubeListBuilder.create()
                .texOffs(68, 49).addBox(-2.0F, -1.8F, -0.14F, 4F, 3F, 3F) /* tail-01 */,
            PartPose.offsetAndRotation(0.0F, -1.52F, 4.48F, -0.31416F, 0.00000F, 0.00000F));

        PartDefinition foot_posDef = root.addOrReplaceChild("foot_pos",
            CubeListBuilder.create()
                .texOffs(86, 34).addBox(-2.5F, -0.08F, -3.5F, 5F, 2F, 7F) /* foot-east-01 */,
            PartPose.offset(-3.32F, 22.08F, -4.72F));

        PartDefinition foot_negDef = root.addOrReplaceChild("foot_neg",
            CubeListBuilder.create()
                .texOffs(0, 49).addBox(-2.5F, -0.08F, -3.5F, 5F, 2F, 7F) /* foot-west-01 */,
            PartPose.offset(3.32F, 22.08F, -4.72F));

        return LayerDefinition.create(mesh, 128, 64);
    }

    @Override
    public void setupAnim(PenguinEntity entity, float limbSwing, float limbSwingAmount,
                          float ageInTicks, float netHeadYaw, float headPitch) {
        float walk = limbSwing * 0.6662F;

        // head follows where the penguin looks
        this.head.yRot = netHeadYaw * Mth.DEG_TO_RAD;
        this.head.xRot = headPitch * Mth.DEG_TO_RAD;

        // waddle: whole upper body rocks side to side while walking
        this.body.zRot = Mth.cos(walk) * 0.12F * limbSwingAmount;

        // flippers: gentle idle sway + flap while walking
        float flap = Mth.cos(ageInTicks * 0.09F) * 0.04F + Math.abs(Mth.cos(walk)) * 0.35F * limbSwingAmount;
        this.flipperPos.zRot = FLIPPER_BASE + flap;
        this.flipperNeg.zRot = -(FLIPPER_BASE + flap);

        // feet step alternately
        this.footPos.xRot = Mth.cos(walk) * 0.9F * limbSwingAmount;
        this.footNeg.xRot = Mth.cos(walk + Mth.PI) * 0.9F * limbSwingAmount;

        // tail wiggle
        this.tail.xRot = TAIL_BASE;
        this.tail.yRot = Mth.cos(walk) * 0.2F * limbSwingAmount;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer,
                               int packedLight, int packedOverlay, int color) {
        this.root.render(poseStack, buffer, packedLight, packedOverlay, color);
    }
}
