package com.gizmo.twilitgourmet.client;

import com.gizmo.twilitgourmet.TwilitGourmet;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import twilightforest.client.model.armor.TFArmorModel;

public class ShellHelmetModel extends TFArmorModel {

	public static final ModelLayerLocation SHELL_HELMET_OUTER = new ModelLayerLocation(TwilitGourmet.prefix("shell_helmet"), "outer");
	public static final ModelLayerLocation SHELL_HELMET_INNER = new ModelLayerLocation(TwilitGourmet.prefix("shell_helmet"), "inner");

	private final ModelPart claw;
	private final ModelPart leg1;
	private final ModelPart leg2;
	private final ModelPart leg3;

	public ShellHelmetModel(ModelPart root) {
		super(root);
		this.claw = this.head.getChild("arm");
		this.leg1 = this.head.getChild("leg1");
		this.leg2 = this.head.getChild("leg2");
		this.leg3 = this.head.getChild("leg3");
	}

	public static LayerDefinition addPieces(CubeDeformation deformation) {
		MeshDefinition meshdefinition = HumanoidModel.createMesh(deformation, 0);
		PartDefinition partdefinition = meshdefinition.getRoot();

		var head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create()
						.texOffs(0, 0)
						.addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, deformation),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		partdefinition.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.ZERO);

		var rightHorn = head.addOrReplaceChild("right_horn_1", CubeListBuilder.create()
						.texOffs(24, 0)
						.addBox(-5.5F, -1.5F, -1.5F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.25F)),
				PartPose.offsetAndRotation(-4.0F, -6.5F, 0.0F,
						0.0F, -15.0F * Mth.DEG_TO_RAD, 10.0F * Mth.DEG_TO_RAD));

		var rightHorn2 = rightHorn.addOrReplaceChild("right_horn_2", CubeListBuilder.create()
						.texOffs(0, 0)
						.addBox(-3.5F, -1.0F, -1.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.25F)),
				PartPose.offsetAndRotation(-4.5F, 0.0F, 0.0F,
						0.0F, 0.0F, 10.0F * Mth.DEG_TO_RAD));

		var leftHorn = head.addOrReplaceChild("left_horn_1", CubeListBuilder.create()
						.texOffs(24, 0).mirror()
						.addBox(0.5F, -1.5F, -1.5F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.25F)),
				PartPose.offsetAndRotation(4.0F, -6.5F, 0.0F,
						0.0F, 15.0F * Mth.DEG_TO_RAD, -10.0F * Mth.DEG_TO_RAD));

		var leftHorn2 = leftHorn.addOrReplaceChild("left_horn_2", CubeListBuilder.create()
						.texOffs(0, 0)
						.addBox(0.5F, -1.0F, -1.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.25F)),
				PartPose.offsetAndRotation(4.5F, 0.0F, 0.0F,
						0.0F, 0.0F, -10.0F * Mth.DEG_TO_RAD));

		PartDefinition leftEye = leftHorn2.addOrReplaceChild("left_eye", CubeListBuilder.create().texOffs(41, 0).mirror().addBox(-1.5F, -2.5F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(-0.25F)).mirror(false), PartPose.offsetAndRotation(4.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

		rightHorn2.addOrReplaceChild("right_eye", CubeListBuilder.create().texOffs(41, 0).addBox(-0.5F, -2.5F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(-0.25F)), PartPose.offsetAndRotation(-4.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

		PartDefinition arm = head.addOrReplaceChild("arm", CubeListBuilder.create().texOffs(32, 8).mirror().addBox(-7.5F, -1.0F, -1.0F, 8.0F, 2.0F, 2.0F).mirror(false), PartPose.offsetAndRotation(-3.5F, -2.0F, -1.0F, 0.0F, 0.0F, 0.0F));

		PartDefinition claw = arm.addOrReplaceChild("claw", CubeListBuilder.create().texOffs(54, 0).addBox(-3.5F, -1.5F, -2.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(-7.5F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		claw.addOrReplaceChild("top", CubeListBuilder.create().texOffs(54, 5).addBox(-3.0F, -0.25F, -1.0F, 3.0F, 1.0F, 2.0F), PartPose.offsetAndRotation(-3.5F, -1.25F, -1.0F, 0.0F, 0.0F, 0.1309F));

		claw.addOrReplaceChild("bottom", CubeListBuilder.create().texOffs(54, 8).addBox(-3.0F, -0.5F, -1.0F, 3.0F, 2.0F, 2.0F), PartPose.offsetAndRotation(-3.5F, 0.0F, -1.0F, 0.0F, 0.0F, -0.2618F));

		head.addOrReplaceChild("leg1", CubeListBuilder.create().texOffs(32, 8).mirror().addBox(-7.5F, -1.0F, -1.0F, 8.0F, 2.0F, 2.0F).mirror(false), PartPose.offset(-3.5F, -3.0F, 2.0F));

		head.addOrReplaceChild("leg2", CubeListBuilder.create().texOffs(32, 8).addBox(-0.5F, -1.0F, -1.0F, 8.0F, 2.0F, 2.0F), PartPose.offset(3.5F, -2.0F, -1.0F));

		head.addOrReplaceChild("leg3", CubeListBuilder.create().texOffs(32, 8).addBox(-0.5F, -1.0F, -1.0F, 8.0F, 2.0F, 2.0F), PartPose.offset(3.5F, -3.0F, 2.0F));

		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	@Override
	public void setupAnim(LivingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		float f6 = (Mth.HALF_PI / 4F);
		this.leg1.zRot = -f6 * 0.74F;
		this.leg3.zRot = f6 * 0.74F;
		float f8 = 20.0F * Mth.DEG_TO_RAD;
		this.leg1.yRot = f8;
		this.leg3.yRot = -f8;
		float f10 = -(Mth.cos(limbSwing * 0.6662F * 2.0F + Mth.PI) * 0.4F) * limbSwingAmount* 0.5F;
		float f12 = -(Mth.cos(limbSwing * 0.6662F * 2.0F + (Mth.PI * 3.0F / 2.0F)) * 0.4F) * limbSwingAmount * 0.5F;
		float f14 = Math.abs(Mth.sin(limbSwing * 0.6662F + Mth.PI) * 0.4F) * limbSwingAmount* 0.5F;
		float f16 = Math.abs(Mth.sin(limbSwing * 0.6662F + (Mth.PI * 3.0F / 2.0F)) * 0.4F) * limbSwingAmount* 0.5F;
		this.leg1.yRot += f10;
		this.leg3.yRot -= f10;
		this.leg1.zRot += f14;
		this.leg3.zRot -= f14;

		this.claw.yRot = -45.0F * Mth.DEG_TO_RAD;
		this.claw.yRot += Mth.cos(limbSwing * 0.6662F + Mth.PI) * 2.0F * limbSwingAmount * 0.25F;
		this.leg2.zRot = f6;
		this.leg2.yRot = f8;
		this.leg2.yRot -= f12;
		this.leg2.zRot -= f16;
	}
}
