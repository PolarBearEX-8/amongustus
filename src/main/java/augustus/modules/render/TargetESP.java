package augustus.modules.render;

import client.ClientGlobals;
import client.event.AttackEntityListener;
import client.event.EventAttackEntity;
import client.event.RenderListener;
import client.module.Category;
import client.module.Module;
import client.render.HitMarkerParticle;
import client.render.RenderPass;
import client.render.RenderUtils;
import client.setting.BooleanSetting;
import client.setting.ColorSetting;
import client.setting.ModeSetting;
import client.setting.MultiSelectSetting;
import client.setting.NumberSetting;
import client.setting.Setting;
import client.setting.ShaderOptions;
import client.setting.TargetESPKillAuraType;
import client.util.BooleanSupplier;
import client.util.ChatUtil;
import client.util.MovementHandler;
import client.util.RandomUtil;
import client.util.RayTraceUtils;
import client.util.RunnableTask;
import client.util.SimulatedPlayer;
import java.awt.Color;
import java.util.concurrent.CopyOnWriteArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import org.lwjgl.opengl.GL11;

public class TargetESP extends Module implements RenderListener, AttackEntityListener {
    public MultiSelectSetting settingModules;
    public ModeSetting settingMode;
    public ColorSetting settingTargetEspColor;
    public BooleanSetting settingHitColor;
    public ColorSetting settingTargetEspHitColor;
    public NumberSetting settingLineWidth;
    public NumberSetting settingAdjustWidth;
    public NumberSetting settingHeight;
    public NumberSetting settingAdjustYPos;
    public NumberSetting settingSpeed;
    public NumberSetting settingLength;
    public ShaderOptions settingLength2;
    public BooleanSetting settingCritVisualizer;
    public ColorSetting settingCritVisualizerColor;
    public BooleanSetting settingBestAimPoint;
    public NumberSetting settingBestAimPointSize;
    public ColorSetting settingBestAimPointColor;
    public NumberSetting settingDuration;
    public CopyOnWriteArrayList copyOnWriteArrayList;

    public TargetESP() {
        super("TargetESP", "ZielAußersinnlicheWahrnehmung", new Color(212, 133, 76), Category.RENDER);
        TargetESPKillAuraType[] targetESPKillAuraTypeArray = TargetESPKillAuraType.values();
        this.settingModules = new MultiSelectSetting(this, "Modules", targetESPKillAuraTypeArray);
        String[] textArray = new String[3];
        textArray[0] = "None";
        textArray[1] = "Box";
        textArray[2] = "Sigma";
        this.settingMode = new ModeSetting(this, "Mode", "Sigma", textArray);
        Color color = new Color(41, 146, 222);
        this.settingTargetEspColor = (ColorSetting)new ColorSetting(this, "TargetEspColor", color).getSetting(() -> this.isTargetEspColorVisible2());
        this.settingHitColor = (BooleanSetting)new BooleanSetting(this, "HitColor", true).getSetting(() -> this.isTargetEspColorVisible());
        this.settingTargetEspHitColor = (ColorSetting)new ColorSetting(this, "TargetEspHitColor", Color.red).getSetting(() -> this.isTargetEspHitColorVisible());
        this.settingLineWidth = new NumberSetting(this, "LineWidth", 0.0, 0.0, 12.0, 0).getNumberSetting(() -> this.isLineWidthVisible());
        this.settingAdjustWidth = new NumberSetting(this, "AdjustWidth", -0.1, -1.0, 1.0, 2).getNumberSetting(() -> this.isAdjustWidthVisible());
        this.settingHeight = new NumberSetting(this, "Height", 0.08, 0.0, 0.5, 2).getNumberSetting(() -> this.isHeightVisible());
        this.settingAdjustYPos = new NumberSetting(this, "AdjustYPos", 0.14, -2.0, 2.0, 2).getNumberSetting(() -> this.isAdjustYPosVisible());
        this.settingSpeed = new NumberSetting(this, "Speed", 1.0, 0.0, 2.0, 2).getNumberSetting(() -> this.isSpeedVisible());
        this.settingLength = new NumberSetting(this, "Length", 1.0, 0.0, 2.0, 2).getNumberSetting(() -> this.isLengthVisible());
        ShaderOptions shaderOptions = new ShaderOptions(this);
        Color black = Color.black;
        ShaderOptions shaderOptions2 = shaderOptions.getShaderOptions(black);
        this.settingLength2 = ((ShaderOptions)shaderOptions2.getSetting(() -> this.isLengthVisible2())).getShaderOptions5();
        this.settingCritVisualizer = new BooleanSetting(this, "CritVisualizer", true);
        Color color2 = new Color(255, 164, 75);
        this.settingCritVisualizerColor = (ColorSetting)new ColorSetting(this, "CritVisualizerColor", color2)
        .getColorSetting3(true)
        .getColorSetting(50).getSetting(() -> this.settingCritVisualizer.isEnabled());
        this.settingBestAimPoint = new BooleanSetting(this, "BestAimPoint", false);
        this.settingBestAimPointSize = new NumberSetting(this, "BestAimPointSize", 0.3, 0.01, 1.0, 2).getNumberSetting(() -> this.settingBestAimPoint.isEnabled());
        Color color3 = new Color(41, 146, 222);
        this.settingBestAimPointColor = (ColorSetting)new ColorSetting(this, "bestAimPointColor", color3).getSetting(() -> this.settingBestAimPoint.isEnabled());
        this.settingDuration = new NumberSetting(this, "Duration", 1000.0, 100.0, 4000.0, 0).getNumberSetting(() -> this.settingCritVisualizer.isEnabled());
        this.copyOnWriteArrayList = new CopyOnWriteArrayList();
    }

    @Override
    public void onEnable() {
        this.registerEvent(32);
        this.registerEvent(1);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onPreDisable() {
    }

    @Override
    public void onAttackEntity(EventAttackEntity event) {
        if (this.getEntityLivingBase2() != null) {
            if (minecraft.thePlayer.fallDistance > 0.0F && !minecraft.thePlayer.onGround) {
                boolean enabled;
                if (!minecraft.thePlayer.canExecute9()
                && !minecraft.thePlayer.isInWater()
                && !minecraft.thePlayer.checkPotion(Potion.potion16)
                && minecraft.thePlayer.ridingEntity == null
                && minecraft.objectMouseOver.entityHit instanceof EntityLivingBase) {
                    enabled = true;
                } else {
                    enabled = false;
                }

                this.copyOnWriteArrayList.add(new HitMarkerParticle(enabled ? "Crit!" : "Hit!", minecraft.objectMouseOver.hitVec, System.currentTimeMillis()));
                return;
            }

            this.copyOnWriteArrayList.add(new HitMarkerParticle("Hit!", minecraft.objectMouseOver.hitVec, System.currentTimeMillis()));
        }
    }

    @Override
    public void onRender3D(float partialTicks) {
        EntityLivingBase entityLivingBase2 = this.getEntityLivingBase2();
        if (entityLivingBase2 != null) {
            RenderUtils.updateState();
            String selected = this.settingMode.getSelected();
switch (selected) {
            case "Box":
                double offset = entityLivingBase2.posX - entityLivingBase2.lastTickPosX;
                double offset2 = entityLivingBase2.lastTickPosX + offset * (double)minecraft.getTimer().scaleFactor6;
                float value = (float)(offset2 - minecraft.getRenderManager().getRenderPosX());
                double offset3 = entityLivingBase2.posY - entityLivingBase2.lastTickPosY;
                double offset4 = entityLivingBase2.lastTickPosY + offset3 * (double)minecraft.getTimer().scaleFactor6;
                float value2 = (float)(offset4 - minecraft.getRenderManager().getRenderPosY());
                double offset5 = entityLivingBase2.posZ - entityLivingBase2.lastTickPosZ;
                double offset6 = entityLivingBase2.lastTickPosZ + offset5 * (double)minecraft.getTimer().scaleFactor6;
                float value3 = (float)(offset6 - minecraft.getRenderManager().getRenderPosZ());
                int index2;
                if (this.settingHitColor.isEnabled() && entityLivingBase2.hurtTime > 0) {
                        index2 = this.settingTargetEspHitColor.getInt12(0);
                    } else {
                        index2 = this.settingTargetEspColor.getInt12(0);
                    }
                boolean enabled = this.settingLength2.canExecute();
                MovementHandler.processBoolean(enabled, this.settingLength2.canExecute2());
                int index3 = index2;
                MovementHandler.applyRunnableTask(true, renderPass -> ((TargetESP)this).applyEntityLivingBase(index3, (EntityLivingBase)entityLivingBase2, value, value2, value3, renderPass));
                break;
            case "Sigma":
                boolean enabled2 = this.settingLength2.canExecute();
                MovementHandler.processBoolean(enabled2, this.settingLength2.canExecute2());
                MovementHandler.applyRunnableTask(true, renderPass2 -> ((TargetESP)this).applyEntityLivingBase3((EntityLivingBase)entityLivingBase2, renderPass2));
        }

            RenderUtils.prepareScissor();
        }

        if ((char)(this.settingCritVisualizer.isEnabled() ? 1 : 0) != 0) {
            GL11.glEnable(32823);
            GL11.glPolygonOffset(1.0F, -1100000.0F);

            for (int i = 0; i < this.copyOnWriteArrayList.size(); i++) {
                HitMarkerParticle baseStringEntry = (HitMarkerParticle)this.copyOnWriteArrayList.get(i);
                int int12 = this.settingCritVisualizerColor.getInt12(i);
                double timestamp = (double)HitMarkerParticle.getTimestamp(baseStringEntry);
                double offset7 = timestamp + this.settingDuration.getValue();
                double currentTime = (double)System.currentTimeMillis();
                if (Double.compare(offset7, currentTime) < 0) {
                    this.copyOnWriteArrayList.remove(baseStringEntry);
                } else {
                    double xCoord = HitMarkerParticle.getVec3(baseStringEntry).xCoord;
                    double coordOffset = minecraft.getRenderManager().getRenderPosX();
                    double offset8 = xCoord - coordOffset;
                    double offset9 = HitMarkerParticle.getVec3(baseStringEntry).yCoord - minecraft.getRenderManager().getRenderPosY();
                    double offset10 = HitMarkerParticle.getVec3(baseStringEntry).zCoord - minecraft.getRenderManager().getRenderPosZ();
                    GlStateManager.pushMatrix();
                    GlStateManager.translate3d2(offset8, offset9, offset10);
                    GlStateManager.rotate(-minecraft.getRenderManager().scaleFactor, 0.0F, 1.0F, 0.0F);
                    float value4;
                    if (Minecraft.getMinecraft().gameSettings.indexOffset64 == 2) {
                        value4 = -minecraft.getRenderManager().scaleFactor7;
                    } else {
                        value4 = minecraft.getRenderManager().scaleFactor7;
                    }

                    GlStateManager.rotate(value4, 1.0F, 0.0F, 0.0F);
                    GlStateManager.translate3d(-0.02, -0.02, 0.02);
                    GlStateManager.depthMask(true);
                    GlStateManager.disableAlpha();
                    GlStateManager.disableTexture2D();
                    FontRenderer fontRendererObj = minecraft.fontRendererObj;
                    String text = HitMarkerParticle.getText(baseStringEntry);
                    fontRendererObj.getIndex2(text, (float)(-minecraft.fontRendererObj.getIndex(HitMarkerParticle.getText(baseStringEntry))) / 2.0F, -4.0F, int12);
                    GlStateManager.popMatrix();
                }
            }

            GL11.glDisable(32823);
            GL11.glPolygonOffset(1.0F, 1100000.0F);
        }

        if ((char)(this.settingBestAimPoint.isEnabled() ? 1 : 0) != 0) {
            if (entityLivingBase2 == null) {
                return;
            }

            RenderUtils.updateState();
            Vec3 vec = SimulatedPlayer.getVec3(entityLivingBase2, partialTicks, 100, 80);
            double offset11 = -minecraft.getRenderManager().getRenderPosX();
            double offset12 = -minecraft.getRenderManager().getRenderPosY();
            Vec3 vec2 = vec.getVec312(offset11, offset12, -minecraft.getRenderManager().getRenderPosZ());
            float value5 = (float)this.settingBestAimPointSize.getValue() / 10.0F;
            RenderUtils.setColor(this.settingBestAimPointColor.getInt12(0));
            RenderUtils.processAction4(
            vec2.xCoord - (double)value5,
            vec2.yCoord - (double)value5,
            vec2.zCoord - (double)value5,
            vec2.xCoord + (double)value5,
            vec2.yCoord + (double)value5,
            vec2.zCoord + (double)value5
            );
            RenderUtils.prepareScissor();
        }
    }

    private void applyEntityLivingBase(int index2, EntityLivingBase livingEntity, float value, float value2, float value3, RenderPass renderPass) {
        int index = RandomUtil.getIndex(renderPass, index2);
        RenderUtils.setColor(this.settingLength2.getIndex(renderPass, index));
        float value4 = value2 + livingEntity.height;
        float value5 = value4 + (float)this.settingAdjustYPos.getValue();
        double width = (double)livingEntity.width;
        double offset = width + this.settingAdjustWidth.getValue();
        RenderUtils.applyEntity6(livingEntity, value, value5, value3, offset, this.settingHeight.getValue());
        if (!(this.settingLineWidth.getValue() <= 0.0)) {
            GL11.glLineWidth((float)(this.settingLineWidth.getValue() / 2.0));
            RenderUtils.processAction(index2, 1.0F);
            float value6 = value2 + livingEntity.height;
            float value7 = value6 + (float)this.settingAdjustYPos.getValue();
            double width2 = (double)livingEntity.width;
            double offset2 = width2 + this.settingAdjustWidth.getValue();
            RenderUtils.applyEntity2(livingEntity, value, value7, value3, offset2, this.settingHeight.getValue());
        }
    }

    private void applyEntityLivingBase2(EntityLivingBase livingEntity, int index2, RenderPass renderPass) {
        int index3;
        if (this.settingHitColor.isEnabled()) {
            index3 = this.settingTargetEspHitColor.getInt12(index2);
        } else {
            index3 = this.settingTargetEspColor.getInt12(index2);
        }

        int index = RandomUtil.getIndex(renderPass, index3);
        RenderUtils.setColor(this.settingLength2.getIndex(renderPass, index));
    }

    private void applyEntityLivingBase3(EntityLivingBase livingEntity, RenderPass renderPass) {
        this.applyEntityLivingBase4(livingEntity, renderPass);
    }

    public void applyEntityLivingBase4(EntityLivingBase livingEntity, RenderPass renderPass) {
        double offset = livingEntity.posX - livingEntity.lastTickPosX;
        double offset2 = livingEntity.lastTickPosX + offset * (double)minecraft.getTimer().scaleFactor6;
        float value = (float)(offset2 - minecraft.getRenderManager().getRenderPosX());
        double offset3 = livingEntity.posY - livingEntity.lastTickPosY;
        double offset4 = livingEntity.lastTickPosY + offset3 * (double)minecraft.getTimer().scaleFactor6;
        float value2 = (float)(offset4 - minecraft.getRenderManager().getRenderPosY());
        double offset5 = livingEntity.posZ - livingEntity.lastTickPosZ;
        double offset6 = livingEntity.lastTickPosZ + offset5 * (double)minecraft.getTimer().scaleFactor6;
        float value3 = (float)(offset6 - minecraft.getRenderManager().getRenderPosZ());
        GlStateManager.pushMatrix();
        GlStateManager.disableBlend();
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GlStateManager.enableTexture2D();
        GlStateManager.applyState10();
        GL11.glLineWidth(3.0F);
        GL11.glShadeModel(7425);
        GL11.glDisable(2884);
        double offset7 = (double)livingEntity.width * 1.2;
        double currentTime = (double)System.currentTimeMillis();
        float value4 = (float)Math.sin(currentTime / (400.0 / this.settingSpeed.getValue()));
        double double7 = MathHelper.getDouble7(this.settingLength.getValue(), 0.0, 10.0);
        GL11.glTranslatef(0.0F, value4, 0.0F);
        GL11.glBegin(5);
        int index = 0;
        this.applyEntityLivingBase2(livingEntity, 0, renderPass);
        double offset8 = (double)value + Math.cos(Math.toRadians((double)0)) * offset7;
        double offset9 = (double)value3 - Math.sin(Math.toRadians((double)0)) * offset7;
        GL11.glVertex3d(offset8, (double)(value2 + 1.0F), offset9);
        TargetESP targetESP = this;

        while (true) {
            int index2;
            if (targetESP.settingHitColor.isEnabled() && livingEntity.hurtTime != 0) {
                index2 = this.settingTargetEspHitColor.getInt12(index);
            } else {
                index2 = this.settingTargetEspColor.getInt12(index);
            }

            RenderUtils.processAction(this.settingLength2.getIndex(renderPass, index2), 0.0F);
            GL11.glVertex3d(offset8, (double)(value2 + 1.0F) + (double)(value4 * 0.4F) * double7, offset9);
            if (++index >= 361) {
                GL11.glEnd();
                GL11.glBegin(2);
                int index3 = 0;
                this.applyEntityLivingBase2(livingEntity, 0, renderPass);
                GL11.glVertex3d(
                (double)value + Math.cos(Math.toRadians((double)0)) * offset7,
                (double)(value2 + 1.0F),
                (double)value3 - Math.sin(Math.toRadians((double)0)) * offset7
                );
                index3++;

                while (index3 < 361) {
                    this.applyEntityLivingBase2(livingEntity, index3, renderPass);
                    GL11.glVertex3d(
                    (double)value + Math.cos(Math.toRadians((double)index3)) * offset7,
                    (double)(value2 + 1.0F),
                    (double)value3 - Math.sin(Math.toRadians((double)index3)) * offset7
                    );
                    index3++;
                }

                GL11.glEnd();
                GlStateManager.applyState14();
                GL11.glShadeModel(7424);
                GL11.glDisable(2848);
                GL11.glEnable(2884);
                GlStateManager.disableBlend();
                GlStateManager.disableTexture2D();
                GlStateManager.disableDepth();
                GlStateManager.enableBlend();
                GlStateManager.applyState();
                GlStateManager.popMatrix();
                return;
            }

            this.applyEntityLivingBase2(livingEntity, index, renderPass);
            offset8 = (double)value + Math.cos(Math.toRadians((double)index)) * offset7;
            offset9 = (double)value3 - Math.sin(Math.toRadians((double)index)) * offset7;
            GL11.glVertex3d(offset8, (double)(value2 + 1.0F), offset9);
            targetESP = this;
        }
    }

    private boolean isHeightVisible() {
        return this.settingMode.getSelected().equals("Box");
    }

    private EntityLivingBase getEntityLivingBase() {
        if (!TargetESPKillAuraType.LEGIT.isSelected()) {
            return null;
        } else {
            MovingObjectPosition movingObjectPosition = RayTraceUtils.getMovingObjectPosition(1.0F, ClientGlobals.rotationManager.getRotation2(), 6.0, 0.5);
            if (movingObjectPosition == null) {
                return null;
            } else if (!(movingObjectPosition.entityHit instanceof EntityLivingBase)) {
                return null;
            } else {
                if (movingObjectPosition.entityHit instanceof EntityPlayer) {
                    EntityPlayer player = (EntityPlayer)movingObjectPosition.entityHit;
                    if (!ChatUtil.checkEntityPlayer(player)) {
                        return null;
                    }
                }

                return (EntityLivingBase)movingObjectPosition.entityHit;
            }
        }
    }

    public EntityLivingBase getEntityLivingBase2() {
        if (ClientGlobals.moduleManager.killAura.isToggled()
        && ClientGlobals.moduleManager.killAura.target != null
        && TargetESPKillAuraType.KILL_AURA.isSelected()) {
            return ClientGlobals.moduleManager.killAura.target;
        } else if (ClientGlobals.moduleManager.aimBot.isToggled()
        && ClientGlobals.moduleManager.aimBot.target != null
        && TargetESPKillAuraType.AIM_BOT.isSelected()) {
            return ClientGlobals.moduleManager.aimBot.target;
        } else if (ClientGlobals.moduleManager.aimAssist.isToggled()
        && ClientGlobals.moduleManager.aimAssist.target != null
        && TargetESPKillAuraType.AIM_ASSIST.isSelected()) {
            return ClientGlobals.moduleManager.aimAssist.target;
        } else if (!TargetESPKillAuraType.AUTO_ROD.isSelected()) {
            return this.getEntityLivingBase();
        } else if (!ClientGlobals.moduleManager.autoRod.isToggled()) {
            return this.getEntityLivingBase();
        } else {
            return ClientGlobals.moduleManager.autoRod.getTargetEntity() != null
            ? ClientGlobals.moduleManager.autoRod.getTargetEntity()
            : this.getEntityLivingBase();
        }
    }

    private boolean isLineWidthVisible() {
        return this.settingMode.getSelected().equals("Box");
    }

    private boolean isAdjustYPosVisible() {
        return this.settingMode.getSelected().equals("Box");
    }

    private boolean isLengthVisible() {
        return this.settingMode.getSelected().equals("Sigma");
    }

    private boolean isLengthVisible2() {
        return !this.settingMode.getSelected().equals("None");
    }

    private boolean isAdjustWidthVisible() {
        return this.settingMode.getSelected().equals("Box");
    }

    private boolean isTargetEspColorVisible() {
        return !this.settingMode.getSelected().equals("None");
    }

    private boolean isTargetEspHitColorVisible() {
        return !this.settingHitColor.isEnabled() ? false : this.settingHitColor.isVisible();
    }

    private boolean isSpeedVisible() {
        return this.settingMode.getSelected().equals("Sigma");
    }

    private boolean isTargetEspColorVisible2() {
        return !this.settingMode.getSelected().equals("None");
    }
}
