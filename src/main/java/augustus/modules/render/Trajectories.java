package augustus.modules.render;

import client.ClientGlobals;
import client.event.RenderListener;
import client.module.Category;
import client.module.Module;
import client.render.RenderPass;
import client.render.RenderUtils;
import client.render.WorldRenderer;
import client.rotation.RotationHelper;
import client.setting.BooleanSetting;
import client.setting.ColorSetting;
import client.setting.MultiSelectSetting;
import client.setting.NumberSetting;
import client.setting.Setting;
import client.setting.ShaderOptions;
import client.setting.TrajectoriesBowType;
import client.util.BooleanSupplier;
import client.util.DefaultVertexFormats;
import client.util.MovementHandler;
import client.util.MovingObjectType;
import client.util.RandomUtil;
import client.util.RayTraceUtils;
import java.awt.Color;
import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemEgg;
import net.minecraft.item.ItemEnderPearl;
import net.minecraft.item.ItemFireCharge;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemSnowball;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import org.lwjgl.opengl.GL11;

public class Trajectories extends Module implements RenderListener {
    public BooleanSetting settingCalculateColor;
    public ColorSetting settingColor;
    public NumberSetting settingLineWidth;
    public ShaderOptions settingLineWidth2;
    public MultiSelectSetting settingItems;
    public ArrayList itemList3;

    public Trajectories() {
        super("Trajectories", "Flugbahnen", new Color(29, 202, 103), Category.RENDER);
        this.settingCalculateColor = new BooleanSetting(this, "CalculateColor", false);
        Color color = new Color(21, 121, 230);
        this.settingColor = (ColorSetting)new ColorSetting(this, "Color", color).getSetting(() -> this.isColorVisible());
        this.settingLineWidth = new NumberSetting(this, "LineWidth", 5.0, 1.0, 12.0, 0);
        ShaderOptions shaderOptions = new ShaderOptions(this);
        Color black = Color.black;
        this.settingLineWidth2 = shaderOptions.getShaderOptions(black).getShaderOptions2();
        TrajectoriesBowType[] trajectoriesBowTypeArray = TrajectoriesBowType.values();
        this.settingItems = new MultiSelectSetting(this, "Items", trajectoriesBowTypeArray);
        this.itemList3 = new ArrayList();
    }

    @Override
    public void onEnable() {
        this.registerEvent(32);
    }

    @Override
    public void onDisable() {
        this.itemList3.clear();
    }

    @Override
    public void onPreDisable() {
    }

    @Override
    public void onRender3D(float partialTicks) {
        this.itemList3.clear();
        ItemStack itemStack5 = minecraft.thePlayer.getHeldItem();
        MovingObjectPosition hitResult = null;
        if (itemStack5 != null) {
            Item item = itemStack5.getItem();
            if ((!(item instanceof ItemSnowball) || !TrajectoriesBowType.SNOW_BALL.isSelected())
            && (!(item instanceof ItemEgg) || !TrajectoriesBowType.EGG.isSelected())
            && (!(item instanceof ItemBow) || !TrajectoriesBowType.BOW.isSelected())
            && (!(item instanceof ItemEnderPearl) || !TrajectoriesBowType.ENDER_PEARL.isSelected())
            && (!item.equals(Items.experienceBottle) || !TrajectoriesBowType.XP_BOTTLE.isSelected())
            && (!item.equals(Items.potion) || !ItemPotion.checkCondition(itemStack5.getIndexOffset()) || !TrajectoriesBowType.POTION.isSelected())
            && (!(item instanceof ItemFishingRod) || !TrajectoriesBowType.ROD.isSelected())) {
                if (!(item instanceof ItemFireCharge)) {
                    return;
                }

                if (!TrajectoriesBowType.FIREBALL.isSelected()) {
                    return;
                }
            }

            RotationHelper rotationHelper = new RotationHelper();
            rotationHelper.applyEntityLivingBase2(minecraft.thePlayer, 14669, itemStack5);
            if (item instanceof ItemBow) {
                this.itemList3 = rotationHelper.getArrayList4(300);
            } else if (item instanceof ItemFishingRod) {
                this.itemList3 = rotationHelper.getArrayList2(300);
            } else if (!(item instanceof ItemFireCharge)) {
                this.itemList3 = rotationHelper.getArrayList(300);
            } else {
                this.itemList3 = new ArrayList();
                double offset = minecraft.thePlayer.lastTickPosX
                + (minecraft.thePlayer.posX - minecraft.thePlayer.lastTickPosX) * (double)minecraft.getTimer().scaleFactor6;
                double offset2 = minecraft.thePlayer.lastTickPosY
                + (double)minecraft.thePlayer.getHeight2()
                + (minecraft.thePlayer.posY - minecraft.thePlayer.lastTickPosY) * (double)minecraft.getTimer().scaleFactor6;
                double offset3 = minecraft.thePlayer.lastTickPosZ
                + (minecraft.thePlayer.posZ - minecraft.thePlayer.lastTickPosZ) * (double)minecraft.getTimer().scaleFactor6;
                float value = minecraft.thePlayer.prevRotationYaw
                + (minecraft.thePlayer.rotationYaw - minecraft.thePlayer.prevRotationYaw) * minecraft.getTimer().scaleFactor6;
                if (ClientGlobals.moduleManager.bowAimbot.isToggled()
                && ClientGlobals.moduleManager.bowAimbot.getTargetEntity() != null
                && ClientGlobals.rotationManager.checkModule(ClientGlobals.moduleManager.bowAimbot)
                || ClientGlobals.moduleManager.autoRod.isToggled()
                && ClientGlobals.moduleManager.autoRod.getTargetEntity() != null
                && ClientGlobals.rotationManager.checkModule(ClientGlobals.moduleManager.autoRod)) {
                    value = ClientGlobals.rotationManager.getRotation3().yaw
                    + (ClientGlobals.rotationManager.getRotation2().yaw - ClientGlobals.rotationManager.getRotation3().yaw)
                    * minecraft.getTimer().scaleFactor6;
                }

                double offset4 = offset + (double)(MathHelper.cos(value / 180.0F * (float) Math.PI) * 0.16F * -1.0F);
                double offset5 = offset2 - 0.1F;
                double offset6 = offset3 + (double)(MathHelper.abs(value / 180.0F * (float) Math.PI) * 0.16F * -1.0F);
                this.itemList3.add(new Vec3(offset4, offset5, offset6));
                hitResult = RayTraceUtils.getMovingObjectPosition2(
                partialTicks, minecraft.thePlayer.rotationYaw, minecraft.thePlayer.rotationPitch, 200.0, 200.0, 0.0
                );
                if (hitResult != null && hitResult.typeOfHit != MovingObjectType.MISS && hitResult.hitVec != null) {
                    this.itemList3.add(hitResult.hitVec);
                } else {
                    this.itemList3.add(minecraft.thePlayer.getVec32(partialTicks).getVec36(minecraft.thePlayer.getVec36(partialTicks).getVec3(200.0)));
                }

                rotationHelper = null;
            }

            if (rotationHelper != null) {
                hitResult = rotationHelper.getMovingObjectPosition();
            }

            if (this.itemList3.size() > 1) {
                this.applyMovingObjectPosition2(hitResult);
            }
        }
    }

    private void applyMovingObjectPosition(int index3, MovingObjectPosition hitResult, RenderPass renderPass) {
        Tessellator tessellator = Tessellator.getTessellator();
        WorldRenderer worldRenderer = tessellator.getWorldRenderer();
        worldRenderer.updateAuxiliaryLoggerEntry(3, DefaultVertexFormats.auxiliaryLoggerEntry1);
        int index = RandomUtil.getIndex(renderPass, index3);
        int index2 = this.settingLineWidth2.getIndex(renderPass, index);
        RenderUtils.processAction(index2, 0.85F);

        for (Vec3 vec : (Iterable<Vec3>) this.itemList3) {
            double x = (double)((float)vec.xCoord);
            double offset = x - minecraft.getRenderManager().getRenderPosX();
            double y = (double)((float)vec.yCoord);
            double offset2 = y - minecraft.getRenderManager().getRenderPosY();
            double z = (double)((float)vec.zCoord);
            worldRenderer.getWorldRenderer(offset, offset2, z - minecraft.getRenderManager().getRenderPosZ()).updateState();
        }

        tessellator.updateState();
        if (hitResult != null) {
            if (hitResult.typeOfHit != MovingObjectType.ENTITY) {
                this.applyWorldRenderer(worldRenderer, index2, hitResult, tessellator);
            } else {
                Entity entityHit = hitResult.entityHit;
                double offset3 = entityHit.posX - entityHit.lastTickPosX;
                double offset4 = entityHit.lastTickPosX + offset3 * (double)minecraft.getTimer().scaleFactor6;
                float value = (float)(offset4 - minecraft.getRenderManager().getRenderPosX());
                double offset5 = entityHit.posY - entityHit.lastTickPosY;
                double offset6 = entityHit.lastTickPosY + offset5 * (double)minecraft.getTimer().scaleFactor6;
                float value2 = (float)(offset6 - minecraft.getRenderManager().getRenderPosY());
                double offset7 = entityHit.posZ - entityHit.lastTickPosZ;
                double offset8 = entityHit.lastTickPosZ + offset7 * (double)minecraft.getTimer().scaleFactor6;
                float value3 = (float)(offset8 - minecraft.getRenderManager().getRenderPosZ());
                RenderUtils.processAction(index2, 0.3F);
                RenderUtils.applyEntity6(entityHit, value, value2, value3, (double)(entityHit.width / 1.5F), (double)entityHit.height);
                GL11.glLineWidth(2.5F);
                RenderUtils.processAction(index2, 1.0F);
                RenderUtils.applyEntity2(entityHit, value, value2, value3, (double)(entityHit.width / 1.5F), (double)entityHit.height);
            }
        }
    }

    private void applyWorldRenderer(WorldRenderer worldRenderer, int index, MovingObjectPosition hitResult, Tessellator tessellator) {
        RenderUtils.processAction(index, 0.3F);
        Vec3 hitVec = hitResult.hitVec;
        EnumFacing sideHit = hitResult.sideHit;
        float value = (float)(hitVec.xCoord - minecraft.getRenderManager().getRenderPosX());
        float value2 = (float)(hitVec.xCoord - minecraft.getRenderManager().getRenderPosX());
        float value3 = (float)(hitVec.yCoord - minecraft.getRenderManager().getRenderPosY());
        float value4 = (float)(hitVec.yCoord - minecraft.getRenderManager().getRenderPosY());
        float value5 = (float)(hitVec.zCoord - minecraft.getRenderManager().getRenderPosZ());
        float value6 = (float)(hitVec.zCoord - minecraft.getRenderManager().getRenderPosZ());
        if (sideHit == EnumFacing.SOUTH) {
            value -= 0.4F;
            value2 += 0.4F;
            value3 -= 0.4F;
            value4 += 0.4F;
            value6 += 0.02F;
            value5 += 0.05F;
        } else {
            EnumFacing enumFacing = EnumFacing.NORTH;
            if (sideHit == enumFacing) {
                value -= 0.4F;
                value2 += 0.4F;
                value3 -= 0.4F;
                value4 += 0.4F;
                value6 -= 0.02F;
                value5 -= 0.05F;
            } else {
                EnumFacing enumFacing2 = EnumFacing.EAST;
                if (sideHit == enumFacing2) {
                    value2 += 0.02F;
                    value += 0.05F;
                    value3 -= 0.4F;
                    value4 += 0.4F;
                    value5 -= 0.4F;
                    value6 += 0.4F;
                } else {
                    EnumFacing enumFacing3 = EnumFacing.WEST;
                    if (sideHit == enumFacing3) {
                        value2 -= 0.02F;
                        value -= 0.05F;
                        value3 -= 0.4F;
                        value4 += 0.4F;
                        value5 -= 0.4F;
                        value6 += 0.4F;
                    } else {
                        EnumFacing enumFacing4 = EnumFacing.UP;
                        if (sideHit == enumFacing4) {
                            value -= 0.4F;
                            value2 += 0.4F;
                            value4 += 0.02F;
                            value3 += 0.05F;
                            value5 -= 0.4F;
                            value6 += 0.4F;
                        } else {
                            EnumFacing enumFacing5 = EnumFacing.DOWN;
                            if (sideHit == enumFacing5) {
                                value -= 0.4F;
                                value2 += 0.4F;
                                value4 -= 0.02F;
                                value3 -= 0.05F;
                                value5 -= 0.4F;
                                value6 += 0.4F;
                            }
                        }
                    }
                }
            }
        }

        worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry1);
        worldRenderer.getWorldRenderer((double)value, (double)value3, (double)value5).updateState();
        worldRenderer.getWorldRenderer((double)value, (double)value3, (double)value6).updateState();
        worldRenderer.getWorldRenderer((double)value, (double)value4, (double)value6).updateState();
        worldRenderer.getWorldRenderer((double)value, (double)value4, (double)value5).updateState();
        worldRenderer.getWorldRenderer((double)value, (double)value3, (double)value6).updateState();
        worldRenderer.getWorldRenderer((double)value2, (double)value3, (double)value6).updateState();
        worldRenderer.getWorldRenderer((double)value2, (double)value4, (double)value6).updateState();
        worldRenderer.getWorldRenderer((double)value, (double)value4, (double)value6).updateState();
        worldRenderer.getWorldRenderer((double)value2, (double)value3, (double)value6).updateState();
        worldRenderer.getWorldRenderer((double)value2, (double)value3, (double)value5).updateState();
        worldRenderer.getWorldRenderer((double)value2, (double)value4, (double)value5).updateState();
        worldRenderer.getWorldRenderer((double)value2, (double)value4, (double)value6).updateState();
        worldRenderer.getWorldRenderer((double)value2, (double)value3, (double)value5).updateState();
        worldRenderer.getWorldRenderer((double)value, (double)value3, (double)value5).updateState();
        worldRenderer.getWorldRenderer((double)value, (double)value4, (double)value5).updateState();
        worldRenderer.getWorldRenderer((double)value2, (double)value4, (double)value5).updateState();
        worldRenderer.getWorldRenderer((double)value, (double)value3, (double)value5).updateState();
        worldRenderer.getWorldRenderer((double)value, (double)value3, (double)value6).updateState();
        worldRenderer.getWorldRenderer((double)value2, (double)value3, (double)value6).updateState();
        worldRenderer.getWorldRenderer((double)value2, (double)value3, (double)value5).updateState();
        worldRenderer.getWorldRenderer((double)value, (double)value4, (double)value5).updateState();
        worldRenderer.getWorldRenderer((double)value, (double)value4, (double)value6).updateState();
        worldRenderer.getWorldRenderer((double)value2, (double)value4, (double)value6).updateState();
        worldRenderer.getWorldRenderer((double)value2, (double)value4, (double)value5).updateState();
        worldRenderer.updateState();
        tessellator.updateState();
        GL11.glLineWidth(2.0F);
        worldRenderer.updateAuxiliaryLoggerEntry(3, DefaultVertexFormats.auxiliaryLoggerEntry1);
        worldRenderer.getWorldRenderer((double)value, (double)value3, (double)value5).updateState();
        worldRenderer.getWorldRenderer((double)value, (double)value3, (double)value6).updateState();
        worldRenderer.getWorldRenderer((double)value, (double)value4, (double)value6).updateState();
        worldRenderer.getWorldRenderer((double)value, (double)value4, (double)value5).updateState();
        worldRenderer.getWorldRenderer((double)value, (double)value3, (double)value5).updateState();
        worldRenderer.getWorldRenderer((double)value2, (double)value3, (double)value5).updateState();
        worldRenderer.getWorldRenderer((double)value2, (double)value4, (double)value5).updateState();
        worldRenderer.getWorldRenderer((double)value2, (double)value4, (double)value6).updateState();
        worldRenderer.getWorldRenderer((double)value2, (double)value3, (double)value6).updateState();
        worldRenderer.getWorldRenderer((double)value2, (double)value3, (double)value5).updateState();
        worldRenderer.getWorldRenderer((double)value2, (double)value3, (double)value6).updateState();
        worldRenderer.getWorldRenderer((double)value, (double)value3, (double)value6).updateState();
        worldRenderer.getWorldRenderer((double)value, (double)value4, (double)value6).updateState();
        worldRenderer.getWorldRenderer((double)value2, (double)value4, (double)value6).updateState();
        worldRenderer.getWorldRenderer((double)value2, (double)value4, (double)value5).updateState();
        worldRenderer.getWorldRenderer((double)value, (double)value4, (double)value5).updateState();
        worldRenderer.updateState();
        tessellator.updateState();
    }

    public void applyMovingObjectPosition2(MovingObjectPosition hitResult) {
        RenderUtils.updateState();
        GL11.glLineWidth((float)this.settingLineWidth.getValue() / 2.0F);
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        int index;
        if (this.settingCalculateColor.isEnabled()) {
            Vec3 vec = (Vec3)this.itemList3.get(this.itemList3.size() - 1);
            double offset = Math.min(Math.max(minecraft.thePlayer.getDouble4(vec.xCoord, vec.yCoord, vec.zCoord), 6.0), 36.0) - 6.0;
            float[] floatArray = RandomUtil.getFloatArray(this.settingColor.getInt12(0));
            index = Color.HSBtoRGB((float)(0.011415525F * offset), floatArray[1], floatArray[2]);
        } else {
            index = this.settingColor.getInt12(0);
        }

        MovementHandler.processBoolean(this.settingLineWidth2.canExecute(), this.settingLineWidth2.canExecute2());
        int index2 = index;
        MovementHandler.applyRunnableTask(false, renderPass -> this.applyMovingObjectPosition(index2, hitResult, renderPass));
        GL11.glLineWidth(1.0F);
        GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
        RenderUtils.prepareScissor();
    }

    private boolean isColorVisible() {
        return !this.settingCalculateColor.isEnabled();
    }
}
