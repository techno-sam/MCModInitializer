/*package com.slimeist.base_mod.client.render.tileentity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.slimeist.base_mod.BaseMod;
import com.slimeist.base_mod.client.render.RenderTypes;
import com.slimeist.base_mod.client.util.ClientUtils;
import com.slimeist.base_mod.common.tiles.ForceTubeTileEntity;
import com.slimeist.base_mod.core.init.TileEntityTypeInit;
import com.slimeist.base_mod.core.util.ColorUtil;
import com.slimeist.base_mod.core.util.MiscUtil;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.RenderMaterial;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Matrix3f;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

public class AlternateForceTubeTileEntityRenderer extends TileEntityRenderer<ForceTubeTileEntity> {

    public static final ResourceLocation SHIMMER_LOCATION = BaseMod.getId("entity/force_field_shimmer");
    public static final RenderMaterial SHIMMER_MATERIAL = new RenderMaterial(AtlasTexture.LOCATION_BLOCKS, SHIMMER_LOCATION);

    public static final ResourceLocation OUTLINE_LOCATION = BaseMod.getId("entity/force_field_outline");
    public static final RenderMaterial OUTLINE_MATERIAL = new RenderMaterial(AtlasTexture.LOCATION_BLOCKS, OUTLINE_LOCATION);

    public AlternateForceTubeTileEntityRenderer(TileEntityRendererDispatcher dispatch) {
        super(dispatch);
    }

    public void render(ForceTubeTileEntity te, float partialTicks, MatrixStack matrix, IRenderTypeBuffer rtbuffer, int combinedLight, int combinedOverlay) {
        int looplength = 100;

        long gt = te.getLevel().getGameTime();

        int seed = te.getBlockPos().hashCode();
        int offset = new Random(seed).nextInt(looplength);

        double loop = ClientUtils.modDouble(gt+offset + partialTicks, looplength)/looplength;
        double sinloop = (Math.sin(loop*2*Math.PI)+1)/2;

        double minSize = 0.55;
        double maxSize = 0.95;

        double scale = sinloop*(maxSize-minSize) + minSize;

        int[] intcolor = ColorUtil.unpackRGBA(te.getColor());

        float red = intcolor[0]/255.0f;
        float green = intcolor[1]/255.0f;
        float blue = intcolor[2]/255.0f;
        float alpha = intcolor[3]/255.0f;

        float x1 = (float) (-0.5f * scale);
        float y1 = (float) (-0.5f * scale);
        float z1 = (float) (-0.5f * scale);

        float x2 = (float) (0.5f * scale);
        float y2 = (float) (0.5f * scale);
        float z2 = (float) (0.5f * scale);

        float u1 = SHIMMER_MATERIAL.sprite().getU0();
        float v1 = SHIMMER_MATERIAL.sprite().getV0();

        float u2 = SHIMMER_MATERIAL.sprite().getU1();
        float v2 = SHIMMER_MATERIAL.sprite().getV1();

        float ou1 = OUTLINE_MATERIAL.sprite().getU0();
        float ov1 = OUTLINE_MATERIAL.sprite().getV0();

        float ou2 = OUTLINE_MATERIAL.sprite().getU1();
        float ov2 = OUTLINE_MATERIAL.sprite().getV1();

        if (te.getDistance()>0) {
            matrix.pushPose();
            matrix.translate(0.5, 0.5, 0.5);
            renderCube(te, matrix, SHIMMER_MATERIAL.buffer(rtbuffer, RenderTypes::simpleForceField), red, green, blue, alpha, x1, y1, z1, x2, y2, z2, u1, v1, u2, v2);
            if (ClientUtils.mc().player!=null && MiscUtil.isPlayerWearingShimmeringHelmet(ClientUtils.mc().player) && ClientUtils.mc().player.isShiftKeyDown()) {
                renderCube(te, matrix, OUTLINE_MATERIAL.buffer(rtbuffer, RenderTypes::simpleForceField), red, green, blue, Math.max(alpha, 0.2f), -0.5f, -0.5f, -0.5f, 0.5f, 0.5f, 0.5f, ou1, ov1, ou2, ov2);
            }
            matrix.popPose();
        }
    }

    private void renderCube(ForceTubeTileEntity te, MatrixStack matrix, IVertexBuilder builder, float red, float green, float blue, float alpha, float x1, float y1, float z1, float x2, float y2, float z2, float u1, float v1, float u2, float v2) {
        MatrixStack.Entry matrixstack$entry = matrix.last();
        Matrix4f poseMatrix = matrixstack$entry.pose();
        Matrix3f normalMatrix = matrixstack$entry.normal();

        this.renderFace(te, poseMatrix, normalMatrix, builder, x1, x2, y1, y2, z2, z2, z2, z2, red, green, blue, alpha, u1, v1, u2, v2, Direction.SOUTH);
        this.renderFace(te, poseMatrix, normalMatrix, builder, x1, x2, y2, y1, z1, z1, z1, z1, red, green, blue, alpha, u1, v1, u2, v2, Direction.NORTH);
        this.renderFace(te, poseMatrix, normalMatrix, builder, x2, x2, y2, y1, z1, z2, z2, z1, red, green, blue, alpha, u1, v1, u2, v2, Direction.EAST);
        this.renderFace(te, poseMatrix, normalMatrix, builder, x1, x1, y1, y2, z1, z2, z2, z1, red, green, blue, alpha, u1, v1, u2, v2, Direction.WEST);
        this.renderFace(te, poseMatrix, normalMatrix, builder, x1, x2, y1, y1, z1, z1, z2, z2, red, green, blue, alpha, u1, v1, u2, v2, Direction.DOWN);
        this.renderFace(te, poseMatrix, normalMatrix, builder, x1, x2, y2, y2, z2, z2, z1, z1, red, green, blue, alpha, u1, v1, u2, v2, Direction.UP);
    }

    private void renderFace(ForceTubeTileEntity te, Matrix4f poseMatrix, Matrix3f normalMatrix, IVertexBuilder builder, float x1, float x2, float y1, float y2, float z1, float z2, float z3, float z4, float red, float green, float blue, float alpha, float u1, float v1, float u2, float v2, Direction dir) {
        /*builder.vertex(poseMatrix, x1, y1, z1).color(red, green, blue, alpha).endVertex();
        builder.vertex(poseMatrix, x2, y1, z2).color(red, green, blue, alpha).endVertex();
        builder.vertex(poseMatrix, x2, y2, z3).color(red, green, blue, alpha).endVertex();
        builder.vertex(poseMatrix, x1, y2, z4).color(red, green, blue, alpha).endVertex();*/
        /*if (te.isConnected(dir.getOpposite())) {
            return;
        }*/
/*
        vertex(poseMatrix, normalMatrix, builder, red, green, blue, alpha, x1, y1, z1, u2, v2);
        vertex(poseMatrix, normalMatrix, builder, red, green, blue, alpha, x2, y1, z2, u1, v2);
        vertex(poseMatrix, normalMatrix, builder, red, green, blue, alpha, x2, y2, z3, u1, v1);
        vertex(poseMatrix, normalMatrix, builder, red, green, blue, alpha, x1, y2, z4, u2, v1);
    }

    private static void vertex(Matrix4f poseMatrix, Matrix3f normalMatrix, IVertexBuilder builder, float red, float green, float blue, float alpha, float x, float y, float z, float u, float v) {
        builder.vertex(poseMatrix, x, y, z).color(red, green, blue, alpha).uv(u, v).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(15728880).normal(normalMatrix, 0.0F, 1.0F, 0.0F).endVertex();
    }

    @Override
    public boolean shouldRenderOffScreen(ForceTubeTileEntity tile)
    {
        return true;
    }

    public static void register() {
        ClientRegistry.bindTileEntityRenderer(TileEntityTypeInit.FORCE_TUBE_TYPE, AlternateForceTubeTileEntityRenderer::new);
    }


    private static final Logger LOGGER = LogManager.getLogger();
}
*/