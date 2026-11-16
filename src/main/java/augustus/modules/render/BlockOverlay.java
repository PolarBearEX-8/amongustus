package augustus.modules.render;

import client.event.EventCancelAction;
import client.event.EventCancelListener;
import client.module.Category;
import client.module.Module;
import client.render.RenderPass;
import client.render.RenderUtils;
import client.setting.BooleanSetting;
import client.setting.ColorSetting;
import client.setting.NumberSetting;
import client.setting.ShaderOptions;
import client.util.BuildManager;
import client.util.MovementHandler;
import client.util.MovingObjectType;
import client.util.PreEndHandManager;
import client.util.RandomUtil;
import java.awt.Color;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class BlockOverlay extends Module implements EventCancelListener {
    public ColorSetting settingColor;
    public BooleanSetting settingBlackLine;
    public BooleanSetting settingBlockFill;
    public NumberSetting settingLineWidth;
    public BooleanSetting settingInAdventureMode;
    public BooleanSetting settingOnlyBlockFace;
    public ShaderOptions settingLineWidth2;

    public BlockOverlay() {
        super("BlockOverlay", "Blocküberlag", new Color(26, 124, 173), Category.RENDER);
        Color color = new Color(21, 121, 230, 65);
        this.settingColor = new ColorSetting(this, "Color", color);
        this.settingBlackLine = new BooleanSetting(this, "BlackLine", false);
        this.settingBlockFill = new BooleanSetting(this, "BlockFill", true);
        this.settingLineWidth = new NumberSetting(this, "LineWidth", 5.0, 0.0, 10.0, 2);
        this.settingInAdventureMode = new BooleanSetting(this, "InAdventureMode", true);
        this.settingOnlyBlockFace = new BooleanSetting(this, "OnlyBlockFace", false);
        this.settingLineWidth2 = new ShaderOptions(this).getShaderOptions2();
    }

    @Override
    public void onEnable() {
        this.registerEvent(4);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onPreDisable() {
    }

    @Override
    public void onCancelAction(EventCancelAction event) {
        if (minecraft.objectMouseOver.typeOfHit == MovingObjectType.BLOCK) {
            GlStateManager.disableBlend();
            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            GlStateManager.color4f2(0.0F, 0.0F, 0.0F, 0.4F);
            GL11.glLineWidth(2.0F);
            GlStateManager.enableTexture2D();
            if (BuildManager.canExecute()) {
                PreEndHandManager.updateState();
            }

            GlStateManager.depthMask(false);
            BlockPos blockPos = minecraft.objectMouseOver.getBlockPos();
            Block block = minecraft.theWorld.getBlockState(blockPos).getBlock();
            if (block.getMaterial() != Material.coreItemEntry && minecraft.theWorld.getInternalListEntry().checkBlockPos(blockPos)) {
                block.processAction(minecraft.theWorld, blockPos.getX(), blockPos.getY(), blockPos.getZ());
                int int12 = this.settingColor.getInt12(0);
                block.processAction(minecraft.theWorld, blockPos.getX(), blockPos.getY(), blockPos.getZ());
                double offset = minecraft.thePlayer.lastTickPosX
                + (minecraft.thePlayer.posX - minecraft.thePlayer.lastTickPosX) * (double)minecraft.getTimer().scaleFactor6;
                double offset2 = minecraft.thePlayer.lastTickPosY
                + (minecraft.thePlayer.posY - minecraft.thePlayer.lastTickPosY) * (double)minecraft.getTimer().scaleFactor6;
                double offset3 = minecraft.thePlayer.lastTickPosZ
                + (minecraft.thePlayer.posZ - minecraft.thePlayer.lastTickPosZ) * (double)minecraft.getTimer().scaleFactor6;
                AxisAlignedBB axisAlignedBB6 = block.processAction((World)minecraft.theWorld, blockPos)
                .offset(0.002F, 0.020000000949949026, 0.002F)
                .expand(-offset, -offset2, -offset3);
                if (this.settingOnlyBlockFace.isEnabled()) {
                    axisAlignedBB6 = getAxisAlignedBB(axisAlignedBB6, minecraft.objectMouseOver.sideHit);
                }

                MovementHandler.processBoolean(
                this.settingLineWidth2.canExecute(), this.settingLineWidth2.canExecute2()
                );
                AxisAlignedBB boundingBox = axisAlignedBB6;
                MovementHandler.applyRunnableTask(false, renderPass -> this.applyAxisAlignedBB(boundingBox, int12, renderPass));
                event.cancel();
            }

            GlStateManager.depthMask(true);
            GlStateManager.disableTexture2D();
            if (BuildManager.canExecute()) {
                PreEndHandManager.updateState2();
            }

            GlStateManager.enableBlend();
        }
    }

    private void applyAxisAlignedBB(AxisAlignedBB boundingBox, int index3, RenderPass renderPass) {
        int index = RandomUtil.getIndex(renderPass, index3);
        int index2 = this.settingLineWidth2.getIndex(renderPass, index);
        this.applyAxisAlignedBB2(boundingBox, index2);
    }

    public void applyAxisAlignedBB2(AxisAlignedBB boundingBox, int index) {
        if (this.settingBlockFill.isEnabled()) {
            RenderUtils.setColor(index);
            RenderUtils.drawBoundingBox(boundingBox);
        }

        if (this.settingLineWidth.getValue() > 0.0) {
            if (this.settingBlackLine.isEnabled()) {
                GlStateManager.color4f2(0.0F, 0.0F, 0.0F, 1.0F);
            } else {
                RenderUtils.processAction(index, 1.0F);
            }

            GL11.glLineWidth((float)this.settingLineWidth.getValue() / 2.0F);
            GL11.glEnable(2848);
            RenderGlobal.applyAxisAlignedBB2(boundingBox);
            GL11.glDisable(2848);
        }

        GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
    }

    public static AxisAlignedBB getAxisAlignedBB(AxisAlignedBB boundingBox, EnumFacing enumFacing) {
        if ((enumFacing) == EnumFacing.SOUTH) {
            return new AxisAlignedBB(boundingBox.minX, boundingBox.minY, boundingBox.maxZ, boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ);
        } else if ((enumFacing) == EnumFacing.NORTH) {
            return new AxisAlignedBB(boundingBox.minX, boundingBox.minY, boundingBox.minZ, boundingBox.maxX, boundingBox.maxY, boundingBox.minZ);
        } else if ((enumFacing) == EnumFacing.EAST) {
            return new AxisAlignedBB(boundingBox.maxX, boundingBox.minY, boundingBox.minZ, boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ);
        } else if ((enumFacing) == EnumFacing.WEST) {
            return new AxisAlignedBB(boundingBox.minX, boundingBox.minY, boundingBox.minZ, boundingBox.minX, boundingBox.maxY, boundingBox.maxZ);
        } else if ((enumFacing) == EnumFacing.UP) {
            return new AxisAlignedBB(boundingBox.minX, boundingBox.maxY, boundingBox.minZ, boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ);
        } else if ((enumFacing) == EnumFacing.DOWN) {
            return new AxisAlignedBB(boundingBox.minX, boundingBox.minY, boundingBox.minZ, boundingBox.maxX, boundingBox.minY, boundingBox.maxZ);
        } else {
            return null;
        }
    }
}
