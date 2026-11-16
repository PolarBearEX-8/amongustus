package augustus.modules.render;

import client.Client;
import client.ClientGlobals;
import client.event.EventMotionUpdate;
import client.event.EventStrafe;
import client.event.MotionUpdateListener;
import client.event.RenderListener;
import client.event.StrafeListener;
import client.module.Category;
import client.module.Module;
import client.render.RenderUtils;
import client.setting.BooleanSetting;
import client.setting.ColorSetting;
import client.setting.ModeSetting;
import client.setting.NumberSetting;
import client.setting.Setting;
import client.setting.SettingGroup;
import client.util.BooleanSupplier;
import client.util.BuildManager;
import client.util.PlayerUtil;
import client.util.PreEndHandManager;
import client.util.RandomUtil;
import client.util.SimulatedPlayer;
import java.awt.Color;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import org.lwjgl.opengl.GL11;

public class ChinaHat extends Module implements RenderListener, StrafeListener, MotionUpdateListener {
    public ModeSetting settingColorMode;
    public ColorSetting settingColor1;
    public ColorSetting settingColor2;
    public NumberSetting settingRainbowAlpha;
    public NumberSetting settingAstolfoAlpha;
    public NumberSetting settingHatQualitiy;
    public NumberSetting settingRadius;
    public NumberSetting settingHeight;
    public NumberSetting settingYOffset;
    public NumberSetting settingRotationSpeed;
    public NumberSetting settingRotationOffset;
    public BooleanSetting settingInFirstPerson;
    public BooleanSetting settingRotateWithPlayer;
    public BooleanSetting settingRotateWithSpoofRotations;
    public BooleanSetting settingRotateVertical;
    public BooleanSetting settingOnlyWhileModuleRotation;
    public SettingGroup settingOutline;
    public BooleanSetting settingEnabled;
    public NumberSetting settingLineWidth;
    public NumberSetting settingAlpha;
    public NumberSetting settingRedOffset;
    public NumberSetting settingGreenOffset;
    public NumberSetting settingBlueOffset;
    public float motionMultiplier2;
    public Vec3 positionVec;
    public boolean expanded2;
    public float motionMultiplier;
    public float motionMultiplier1;

    public ChinaHat() {
        super("ChinaHat", "ChinaHut", new Color(50, 168, 139), Category.RENDER);
        String[] textArray = new String[3];
        textArray[0] = "Fade";
        textArray[1] = "Rainbow";
        textArray[2] = "Astolfo";
        this.settingColorMode = new ModeSetting(this, "ColorMode", "Fade", textArray);
        Color color = new Color(255, 0, 0);
        this.settingColor1 = (ColorSetting)new ColorSetting(this, "Color1", color).getSetting(() -> this.isColor1Visible());
        Color color2 = new Color(0, 0, 255);
        this.settingColor2 = (ColorSetting)new ColorSetting(this, "Color2", color2).getSetting(() -> this.isColor2Visible());
        this.settingRainbowAlpha = new NumberSetting(this, "Rainbow Alpha", 170.0, 0.1, 255.0, 2).getNumberSetting(() -> this.isRainbowAlphaVisible());
        this.settingAstolfoAlpha = new NumberSetting(this, "Astolfo Alpha", 170.0, 0.1, 255.0, 2).getNumberSetting(() -> this.isAstolfoAlphaVisible());
        this.settingHatQualitiy = new NumberSetting(this, "HatQualitiy", 100.0, 8.0, 300.0, 0);
        this.settingRadius = new NumberSetting(this, "Radius", 0.65, 0.1, 10.0, 3);
        this.settingHeight = new NumberSetting(this, "Height", 0.25, 0.1, 1.0, 3);
        this.settingYOffset = new NumberSetting(this, "Y Offset", 0.0, -0.1, 1.0, 3);
        this.settingRotationSpeed = new NumberSetting(this, "RotationSpeed", 1.0, 0.0, 360.0, 0);
        this.settingRotationOffset = new NumberSetting(this, "RotationOffset", 1.0, 0.0, 360.0, 0);
        this.settingInFirstPerson = new BooleanSetting(this, "InFirstPerson", true);
        this.settingRotateWithPlayer = new BooleanSetting(this, "Rotate with player", true);
        this.settingRotateWithSpoofRotations = (BooleanSetting)new BooleanSetting(this, "Rotate with spoof rotations", true).getSetting(() -> this.settingRotateWithPlayer.isEnabled());
        this.settingRotateVertical = (BooleanSetting)new BooleanSetting(this, "Rotate vertical", false).getSetting(() -> this.settingRotateWithPlayer.isEnabled());
        this.settingOnlyWhileModuleRotation = (BooleanSetting)new BooleanSetting(this, "Only while module rotation", true).getSetting(() -> this.settingRotateVertical.isEnabled());
        this.settingOutline = new SettingGroup(this, "Outline", false);
        this.settingEnabled = new BooleanSetting(this, "Enabled", true, this.settingOutline);
        this.settingLineWidth = new NumberSetting(this, "Line-width", 2.0, 0.01, 4.0, 2, this.settingOutline);
        this.settingAlpha = new NumberSetting(this, "Alpha", 255.0, 0.1, 255.0, 2, this.settingOutline);
        this.settingRedOffset = new NumberSetting(this, "Red offset", -25.0, -255.0, 255.0, 1, this.settingOutline);
        this.settingGreenOffset = new NumberSetting(this, "Green offset", -25.0, -255.0, 255.0, 1, this.settingOutline);
        this.settingBlueOffset = new NumberSetting(this, "Blue offset", -25.0, -255.0, 255.0, 1, this.settingOutline);
    }

    @Override
    public void onEnable() {
        this.registerEvent(32);
        this.registerEvent(18);
        this.registerEvent(24, 100000);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onPreDisable() {
    }

    @Override
    public void onStrafe(EventStrafe event) {
        if (minecraft.gameSettings.indexOffset64 == 0) {
            this.positionVec = event.getPositionVec();
            this.motionMultiplier2 = event.getScaleFactor();
        } else {
            this.positionVec = new Vec3(0.0, 0.0, 0.0);
            float value;
            if (ClientGlobals.moduleManager.child.isToggled() && ClientGlobals.moduleManager.child.settingEditEyeHeight.isEnabled()) {
                value = 0.85F;
            } else {
                value = minecraft.thePlayer.getHeight2();
            }

            this.motionMultiplier2 = value;
        }
    }

    @Override
    public void onMotionUpdate(EventMotionUpdate event) {
    }

    @Override
    public void onRender3D(float partialTicks) {
        if (this.settingInFirstPerson.isEnabled() || minecraft.gameSettings.indexOffset64 != 0) {
            GL11.glPushMatrix();
            GL11.glEnable(3042);
            GL11.glBlendFunc(770, 771);
            GL11.glEnable(2848);
            GL11.glDisable(3553);
            GL11.glDisable(3008);
            if (BuildManager.canExecute()) {
                PreEndHandManager.updateState();
            }

            GlStateManager.disableAlpha();
            GlStateManager.applyState3();
            Vec3 vec5 = this.positionVec
                .getVec312(0.0, minecraft.gameSettings.indexOffset64 == 0 ? 0.5 : 0.7 + this.settingYOffset.getValue(), 0.0);
            boolean enabled = this.settingRotateWithPlayer.isEnabled();
            int index3 = (int)this.settingHatQualitiy.getValue() / 2;
            double value = this.settingRadius.getValue();
            double value2 = this.settingHeight.getValue();
            this.settingColor1.getInt12(0);
            this.settingColor2.getInt12(0);
            double offset = (double)this.settingColor1.getInt7() / 255.0;
            double offset2 = (double)this.settingColor1.getInt10() / 255.0;
            double offset3 = (double)this.settingColor1.getInt13() / 255.0;
            double offset4 = (double)this.settingColor1.getInt4() / 255.0;
            double offset5 = (double)this.settingColor2.getInt7() / 255.0;
            double offset6 = (double)this.settingColor2.getInt10() / 255.0;
            double offset7 = (double)this.settingColor2.getInt13() / 255.0;
            double offset8 = (double)this.settingColor2.getInt4() / 255.0;
            float rotationYaw = minecraft.thePlayer.rotationYaw;
            float value3 = enabled ? minecraft.thePlayer.rotationPitch : 0.0F;
            float value4 = (float)(
                (double)this.getFloat(this.motionMultiplier1, this.motionMultiplier, minecraft.getTimer().scaleFactor6)
                    + this.settingRotationOffset.getValue()
            );
            if (this.expanded2 && enabled && this.settingRotateWithSpoofRotations.isEnabled()) {
                rotationYaw = PlayerUtil.getFloat2(minecraft.thePlayer.scaleFactor22, minecraft.thePlayer.scaleFactor12, minecraft.getTimer().scaleFactor6);
                value3 = PlayerUtil.getFloat2(minecraft.thePlayer.scaleFactor, minecraft.thePlayer.scaleFactor21, minecraft.getTimer().scaleFactor6);
            }

            if (enabled) {
                value4 += rotationYaw;
            }

            double offset9 = (double)rotationYaw / 180.0 * Math.PI;
            Vec3 vec3 = SimulatedPlayer.processAction(0.0F, rotationYaw).getVec3(value);
            Vec3 vec6 = vec3.getVec3(-1.0);
            Vec3 vec7 = new Vec3(0.0, 1.0, 0.0);
            Vec3 vec8 = vec6.getVec311(vec3);
            Vec3 vec9 = vec7.subtract(vec8).normalize();
            Vec3 vec10 = vec3.getVec36(vec5);
            Vec3 vec = vec10;
            double offset10 = value * value;
            GL11.glTranslated(
                0.0,
                (double)this.motionMultiplier2 - (minecraft.gameSettings.indexOffset64 == 0 ? 0.0 : 0.2) + (minecraft.thePlayer.isSprinting() ? -0.1 : 0.0),
                0.0
            );
            if (this.settingRotateVertical.isEnabled()
                && (
                    !this.settingOnlyWhileModuleRotation.isEnabled()
                        || !ClientGlobals.rotationManager.hasRotations() && this.settingRotateWithSpoofRotations.isEnabled()
                )) {
                GL11.glRotated((double)(-value3), vec9.xCoord, 0.0, vec9.zCoord);
            }

            Vec3 vec11 = SimulatedPlayer.processAction(0.0F, value4).getVec3(value);
            Vec3 vec12 = vec11.getVec3(-1.0).getVec311(vec11).subtract(vec7).normalize();
            Vec3[] vecArray = new Vec3[index3 * 2 + 2];
            float[][] factorMatrix = new float[vecArray.length][];
            boolean enabled2 = this.settingColorMode.getSelected().equalsIgnoreCase("rainbow");
            boolean enabled3 = this.settingColorMode.getSelected().equalsIgnoreCase("astolfo");
            float floatEntry = MathHelper.clamp_float((float)this.settingRainbowAlpha.getValue() / 255.0F, 0.01F, 1.0F);
            float floatEntry2 = MathHelper.clamp_float((float)this.settingAstolfoAlpha.getValue() / 255.0F, 0.01F, 1.0F);

            for (int i = 0; i <= index3; i++) {
                double offset11 = (double)i / (double)index3;
                Vec3 vec13 = new Vec3(Math.cos((offset11 + 0.5) * Math.PI + offset9), 0.0, Math.sin((offset11 + 0.5) * Math.PI + offset9)).getVec3(value);
                Vec3[] vec3Array = getVec3Array(this.getVec3(vec9, vec13), vec9, vec3, vec6, offset10, i, index3);
                Vec3 vec2 = vec3Array[0];
                Vec3 vec4 = vec3Array[1];
                Vec3 vec14 = this.getVec3(vec12, vec2);
                Vec3 vec15 = this.getVec3(vec12, vec4);
                double offset12 = vec14.getVec311(vec11).lengthVector() / value / 2.0;
                double offset13 = vec15.getVec311(vec11).lengthVector() / value / 2.0;
                double offset14 = MathHelper.getDouble4((double)value4) + 180.0;
                vecArray[i] = vec2;
                vecArray[index3 * 2 + 1 - i] = vec4;
                Vec3 vec16 = vec2.getVec36(vec5);
                Vec3 vec17 = vec4.getVec36(vec5);
                if (enabled2) {
                    double offset15 = (Math.atan2(vec16.zCoord, vec16.xCoord) / Math.PI * 180.0 + 180.0 - offset14) % 360.0 / 360.0;
                    double[] doubleArray = getDoubleArray(offset15, 1.0, 1.0);
                    GlStateManager.color4f2((float)doubleArray[0], (float)doubleArray[1], (float)doubleArray[2], floatEntry);
                } else if (enabled3 & true) {
                    int[] intArray = RandomUtil.getIntArray(
                        MathHelper.getDouble4(Math.atan2(vec16.zCoord, vec16.xCoord) / Math.PI * 180.0 + 180.0 - offset14) + 180.0
                    );
                    GlStateManager.color4f2((float)intArray[0] / 255.0F, (float)intArray[1] / 255.0F, (float)intArray[2] / 255.0F, floatEntry2);
                } else {
                    GlStateManager.color4f2(
                        PlayerUtil.getFloat(offset, offset5, offset12),
                        PlayerUtil.getFloat(offset2, offset6, offset12),
                        PlayerUtil.getFloat(offset3, offset7, offset12),
                        PlayerUtil.getFloat(offset4, offset8, offset12)
                    );
                }

                factorMatrix[i] = new float[]{
                    GlStateManager.glState16.red,
                    GlStateManager.glState16.green,
                    GlStateManager.glState16.blue,
                    GlStateManager.glState16.alpha
                };
                GL11.glBegin(5);
                GL11.glVertex3d(vec10.xCoord, vec10.yCoord - value2, vec10.zCoord);
                GL11.glVertex3d(vec5.xCoord, vec5.yCoord, vec5.zCoord);
                GL11.glVertex3d(vec16.xCoord, vec16.yCoord - value2, vec16.zCoord);
                GL11.glEnd();
                if (enabled2) {
                    double offset16 = (Math.atan2(vec17.zCoord, vec17.xCoord) / Math.PI * 180.0 + 180.0 - offset14) % 360.0 / 360.0;
                    double[] doubleArray2 = getDoubleArray(offset16, 1.0, 1.0);
                    GlStateManager.color4f2((float)doubleArray2[0], (float)doubleArray2[1], (float)doubleArray2[2], floatEntry);
                } else if (enabled3 & true) {
                    int[] intArray2 = RandomUtil.getIntArray(
                        MathHelper.getDouble4(Math.atan2(vec17.zCoord, vec17.xCoord) / Math.PI * 180.0 + 180.0 - offset14) + 180.0
                    );
                    GlStateManager.color4f2((float)intArray2[0] / 255.0F, (float)intArray2[1] / 255.0F, (float)intArray2[2] / 255.0F, floatEntry2);
                } else {
                    GlStateManager.color4f2(
                        PlayerUtil.getFloat(offset, offset5, offset13),
                        PlayerUtil.getFloat(offset2, offset6, offset13),
                        PlayerUtil.getFloat(offset3, offset7, offset13),
                        PlayerUtil.getFloat(offset4, offset8, offset13)
                    );
                }

                factorMatrix[index3 * 2 + 1 - i] = new float[]{
                    GlStateManager.glState16.red,
                    GlStateManager.glState16.green,
                    GlStateManager.glState16.blue,
                    GlStateManager.glState16.alpha
                };
                GL11.glBegin(5);
                GL11.glVertex3d(vec.xCoord, vec.yCoord - value2, vec.zCoord);
                GL11.glVertex3d(vec5.xCoord, vec5.yCoord, vec5.zCoord);
                GL11.glVertex3d(vec17.xCoord, vec17.yCoord - value2, vec17.zCoord);
                GL11.glEnd();
                vec10 = vec16;
                vec = vec17;
            }

            if (this.settingEnabled.isEnabled()) {
                GL11.glDepthMask(false);
                GL11.glEnable(2848);
                float value5 = (float)this.settingRedOffset.getValue() / 255.0F;
                float value6 = (float)this.settingGreenOffset.getValue() / 255.0F;
                float value7 = (float)this.settingBlueOffset.getValue() / 255.0F;
                float floatEntry3 = MathHelper.clamp_float((float)this.settingAlpha.getValue() / 255.0F, 0.01F, 1.0F);
                GL11.glLineWidth((float)MathHelper.getDouble7(this.settingLineWidth.getValue(), 0.01, 10.0));
                GL11.glBegin(3);

                for (int i2 = 0; i2 < vecArray.length; i2++) {
                    Vec3 vec18 = vecArray[i2].getVec3(1.0001);
                    float[] factorArray = factorMatrix[i2];
                    float floatEntry4 = MathHelper.clamp_float(factorArray[0] + value5, 0.0F, 1.0F);
                    float floatEntry5 = MathHelper.clamp_float(factorArray[1] + value6, 0.0F, 1.0F);
                    float floatEntry6 = MathHelper.clamp_float(factorArray[2] + value7, 0.0F, 1.0F);
                    GlStateManager.color4f2(floatEntry4, floatEntry5, floatEntry6, floatEntry3);
                    GL11.glVertex3d(vec18.xCoord + vec5.xCoord, vec18.yCoord + vec5.yCoord - value2 + 0.001, vec18.zCoord + vec5.zCoord);
                }

                GL11.glEnd();
                GL11.glDepthMask(true);
            }

            RenderUtils.prepareScissor();
            GL11.glEnable(3008);
            RenderUtils.setColor(1);
            GL11.glPopMatrix();
        }
    }

    @Override
    public void onPreMotionUpdate() {
        boolean enabled = !Client.getClient().getRotationManager().hasRotations();
        this.expanded2 = enabled;
        this.motionMultiplier1 = this.motionMultiplier;
        if (this.settingRotationSpeed.getValue() == 0.0) {
            this.motionMultiplier = 0.0F;
        }

        this.motionMultiplier = (float)(((double)this.motionMultiplier + this.settingRotationSpeed.getValue() / 20.0) % 360.0);
    }

    public static double[] getDoubleArray(double x, double y, double z) {
        double offset = 0.0;
        double offset2 = 0.0;
        double offset3 = 0.0;
        if (y == 0.0) {
            offset = offset2 = offset3 = (double)((int)(z * 255.0 + 0.5));
        } else {
            double offset4 = x - Math.floor(x);
            double offset5 = offset4 * 6.0;
            double offset6 = offset5 - Math.floor(offset5);
            double offset7 = z * (1.0 - y);
            double offset8 = z * (1.0 - y * offset6);
            double offset9 = z * (1.0 - y * (1.0 - offset6));
            switch ((int)offset5) {
                case 0:
                    offset = z * 255.0 + 0.5;
                    offset2 = offset9 * 255.0 + 0.5;
                    offset3 = offset7 * 255.0 + 0.5;
                    break;
                case 1:
                    offset = offset8 * 255.0 + 0.5;
                    offset2 = z * 255.0 + 0.5;
                    offset3 = offset7 * 255.0 + 0.5;
                    break;
                case 2:
                    offset = offset7 * 255.0 + 0.5;
                    offset2 = z * 255.0 + 0.5;
                    offset3 = offset9 * 255.0 + 0.5;
                    break;
                case 3:
                    offset = offset7 * 255.0 + 0.5;
                    offset2 = offset8 * 255.0 + 0.5;
                    offset3 = z * 255.0 + 0.5;
                    break;
                case 4:
                    offset = offset9 * 255.0 + 0.5;
                    offset2 = offset7 * 255.0 + 0.5;
                    offset3 = z * 255.0 + 0.5;
                    break;
                case 5:
                    offset = z * 255.0 + 0.5;
                    offset2 = offset7 * 255.0 + 0.5;
                    offset3 = offset8 * 255.0 + 0.5;
            }
        }

        return new double[]{offset / 255.0, offset2 / 255.0, offset3 / 255.0};
    }

    public static Vec3[] getVec3Array(Vec3 vec, Vec3 vec2, Vec3 vec3, Vec3 vec4, double offset, int index, int index2) {
        double xCoord = vec.xCoord;
        double zCoord = vec.zCoord;
        double xCoord2 = vec2.xCoord;
        double zCoord2 = vec2.zCoord;
        double offset2 = xCoord2 * xCoord2 + zCoord2 * zCoord2;
        double offset3 = (xCoord * xCoord2 + zCoord * zCoord2) / offset2;
        double offset4 = xCoord * xCoord + zCoord * zCoord;
        double offset5 = 0.0;
        double offset6 = (offset - offset4) / offset2 + offset3 * offset3;
        if (offset6 > 0.0) {
            offset5 = Math.sqrt(offset6);
        }

        double offset7 = offset5 - offset3;
        double offset8 = -offset5 - offset3;
        Vec3 vec5 = vec.getVec36(vec2.getVec3(offset7));
        Vec3 vec6 = vec.getVec36(vec2.getVec3(offset8));
        if (index == 0) {
            vec5 = vec3;
            vec6 = vec3;
        } else if (index == index2) {
            vec5 = vec4;
            vec6 = vec4;
        }

        return new Vec3[]{vec5, vec6};
    }

    public float getFloat(float value, float value2, float value3) {
        if (Math.abs(value2 - value) <= 180.0F) {
            return value + (value2 - value) * value3;
        } else {
            float value4 = (360.0F - value + value2) * value3;
            return value + value4;
        }
    }

    private boolean isAstolfoAlphaVisible() {
        return this.settingColorMode.getSelected().equalsIgnoreCase("Astolfo");
    }

    private boolean isColor2Visible() {
        return this.settingColorMode.getSelected().equalsIgnoreCase("fade");
    }

    public Vec3 getVec3(Vec3 vec, Vec3 vec2) {
        double coordinate3 = vec2.distanceTo(vec);
        Vec3 vec3 = vec.getVec3(coordinate3);
        Vec3 vec4 = vec2.getVec36(vec3);
        Vec3 vec5 = vec2.getVec311(vec3);
        if (Math.abs(vec5.distanceTo(vec)) < Math.abs(vec4.distanceTo(vec))) {
            vec4 = vec5;
        }

        return vec4;
    }

    private boolean isRainbowAlphaVisible() {
        return this.settingColorMode.getSelected().equalsIgnoreCase("Rainbow");
    }

    private boolean isColor1Visible() {
        return this.settingColorMode.getSelected().equalsIgnoreCase("fade");
    }
}
