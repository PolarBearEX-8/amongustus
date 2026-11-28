package net.augustus.modules.render;

import java.awt.Color;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.events.listeners.RenderListener;
import net.augustus.events.EventOrientCamera;
import net.augustus.utils.rotation.RotationUtil;
import net.augustus.ClientManagers;
import net.augustus.events.listeners.MotionListener;
import net.augustus.settings.ColorSetting;
import net.augustus.utils.math.MathStatisticsUtil;
import net.augustus.render.RenderUtil;
import net.augustus.utils.render.ColorUtil;
import net.augustus.events.listeners.OrientCameraListener;
import net.augustus.Augustus;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.events.EventMotion;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.StringValue;
import net.augustus.settings.SettingGroup;
import net.augustus.settings.DoubleValue;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.optifine.config.Config;
import net.optifine.shaders.Shaders;
import org.lwjgl.opengl.GL11;

public class ChinaHat extends Module implements RenderListener, OrientCameraListener, MotionListener {
   private final StringValue colorMode;
   private final ColorSetting primaryColor;
   private final ColorSetting secondaryColor;
   private final DoubleValue rainbowAlpha;
   private final DoubleValue astolfoAlpha;
   private final DoubleValue hatQualitiy;
   private final DoubleValue radius;
   private final DoubleValue height;
   private final DoubleValue yOffset;
   private final DoubleValue rotationSpeed;
   private final DoubleValue rotationOffset;
   private final BooleanValue inFirstPerson;
   private final BooleanValue rotateWithPlayer;
   private final BooleanValue rotateWithSpoofRotations;
   private final BooleanValue rotateVertical;
   private final BooleanValue onlyWhileModuleRotation;
   private final SettingGroup outline;
   private final BooleanValue enabled;
   private final DoubleValue lineWidth;
   private final DoubleValue alpha;
   private final DoubleValue redOffset;
   private final DoubleValue greenOffset;
   private final DoubleValue blueOffset;
   private float cameraEyeHeight;
   private Vec3 cameraOffset;
   private boolean usingSpoofedRotation;
   private float spinAngle;
   private float prevSpinAngle;

   public ChinaHat() {
       super("ChinaHat", "ChinaHut", new Color(50, 168, 139), Categorys.RENDER);
      this.colorMode = new StringValue(this, "ColorMode", "Fade", new String[]{"Fade", "Rainbow", "Astolfo"});
      this.primaryColor = (ColorSetting)new ColorSetting(this, "Color1", new Color(255, 0, 0)).withVisibility(() -> this.isPrimaryColorVisible());
      this.secondaryColor = (ColorSetting)new ColorSetting(this, "Color2", new Color(0, 0, 255)).withVisibility(() -> this.isSecondaryColorVisible());
      this.rainbowAlpha = new DoubleValue(this, "Rainbow Alpha", 170.0, 0.1, 255.0, 2).visibleWhen(() -> this.isRainbowAlphaVisible());
      this.astolfoAlpha = new DoubleValue(this, "Astolfo Alpha", 170.0, 0.1, 255.0, 2).visibleWhen(() -> this.isAstolfoAlphaVisible());
      this.hatQualitiy = new DoubleValue(this, "HatQualitiy", 100.0, 8.0, 300.0, 0);
      this.radius = new DoubleValue(this, "Radius", 0.65, 0.1, 10.0, 3);
      this.height = new DoubleValue(this, "Height", 0.25, 0.1, 1.0, 3);
      this.yOffset = new DoubleValue(this, "Y Offset", 0.0, -0.1, 1.0, 3);
      this.rotationSpeed = new DoubleValue(this, "RotationSpeed", 1.0, 0.0, 360.0, 0);
      this.rotationOffset = new DoubleValue(this, "RotationOffset", 1.0, 0.0, 360.0, 0);
      this.inFirstPerson = new BooleanValue(this, "InFirstPerson", true);
      this.rotateWithPlayer = new BooleanValue(this, "Rotate with player", true);
      BooleanValue booleanValue = new BooleanValue(this, "Rotate with spoof rotations", true);
      BooleanValue rotateWithPlayer2 = this.rotateWithPlayer;
      BooleanValue booleanValue2 = rotateWithPlayer2;
      this.rotateWithSpoofRotations = (BooleanValue)booleanValue.withVisibility(() -> booleanValue2.getValue());
      BooleanValue booleanValue3 = new BooleanValue(this, "Rotate vertical", false);
      rotateWithPlayer2 = this.rotateWithPlayer;
      BooleanValue booleanValue4 = rotateWithPlayer2;
      this.rotateVertical = (BooleanValue)booleanValue3.withVisibility(() -> booleanValue4.getValue());
      BooleanValue booleanValue5 = new BooleanValue(this, "Only while module rotation", true);
      rotateWithPlayer2 = this.rotateVertical;
      BooleanValue booleanValue6 = rotateWithPlayer2;
      this.onlyWhileModuleRotation = (BooleanValue)booleanValue5.withVisibility(() -> booleanValue6.getValue());
      this.outline = new SettingGroup(this, "Outline", false);
      this.enabled = new BooleanValue(this, "Enabled", true, this.outline);
      this.lineWidth = new DoubleValue(this, "Line-width", 2.0, 0.01, 4.0, 2, this.outline);
      this.alpha = new DoubleValue(this, "Alpha", 255.0, 0.1, 255.0, 2, this.outline);
      this.redOffset = new DoubleValue(this, "Red offset", -25.0, -255.0, 255.0, 1, this.outline);
      this.greenOffset = new DoubleValue(this, "Green offset", -25.0, -255.0, 255.0, 1, this.outline);
      this.blueOffset = new DoubleValue(this, "Blue offset", -25.0, -255.0, 255.0, 1, this.outline);
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(32);
      this.toggleEventSubscription(18);
      this.toggleEventSubscription(24, 100000);
   }

   @Override
   public void onPreMotion(EventMotion event) {
   }

   @Override
   public void onPostMotion() {
      this.usingSpoofedRotation = !Augustus.getInstance().getRotationController().hasNoActiveOwner();
      this.prevSpinAngle = this.spinAngle;
      if (this.rotationSpeed.getValue() == 0.0) {
         this.spinAngle = 0.0F;
      }

      this.spinAngle = (float)(((double)this.spinAngle + this.rotationSpeed.getValue() / 20.0) % 360.0);
   }

   @Override
   public void onRender3D(float partialTicks) {
      if (this.inFirstPerson.getValue() || mc.gameSettings.thirdPersonView != 0) {
         GlStateManager.pushMatrix();
         GlStateManager.enableBlend();
         GlStateManager.blendFunc(770, 771);
         GL11.glEnable(2848);
         GlStateManager.disableTexture2D();
         GlStateManager.disableAlpha();
         if (Config.isShaderPackLoaded()) {
            Shaders.disableTexture2D();
         }

         GlStateManager.disableCull();
         GlStateManager.disableLighting();
         Vec3 vec = this.cameraOffset.addVector(0.0, mc.gameSettings.thirdPersonView == 0 ? 0.5 : 0.7 + this.yOffset.getValue(), 0.0);
         boolean value = this.rotateWithPlayer.getValue();
         int n = (int)this.hatQualitiy.getValue() / 2;
         double value2 = this.radius.getValue();
         double value3 = this.height.getValue();
         this.primaryColor.getColorInt((byte)0);
         this.secondaryColor.getColorInt((byte)0);
         double d = (double)this.primaryColor.getRed() / 255.0;
         double d2 = (double)this.primaryColor.getGreen() / 255.0;
         double d3 = (double)this.primaryColor.getBlue() / 255.0;
         double d4 = (double)this.primaryColor.getAlpha() / 255.0;
         double d5 = (double)this.secondaryColor.getRed() / 255.0;
         double d6 = (double)this.secondaryColor.getGreen() / 255.0;
         double d7 = (double)this.secondaryColor.getBlue() / 255.0;
         double d8 = (double)this.secondaryColor.getAlpha() / 255.0;
         float rotationYaw = mc.thePlayer.rotationYaw;
         float f2 = value ? mc.thePlayer.rotationPitch : 0.0F;
         float f5 = (float)((double)this.interpolateAngle(this.prevSpinAngle, this.spinAngle, mc.getTimer().renderPartialTicks) + this.rotationOffset.getValue());
         if (this.usingSpoofedRotation && value && this.rotateWithSpoofRotations.getValue()) {
            rotationYaw = MathStatisticsUtil.lerp(mc.thePlayer.prevRotationYawHead, mc.thePlayer.rotationYawHead, mc.getTimer().renderPartialTicks);
            f2 = MathStatisticsUtil.lerp(mc.thePlayer.prevRotationPitchHead, mc.thePlayer.rotationPitchHead, mc.getTimer().renderPartialTicks);
         }

         if (value) {
            f5 += rotationYaw;
         }

         double d9 = (double)rotationYaw / 180.0 * Math.PI;
         Vec3 vec2 = RotationUtil.getLookVector(0.0F, rotationYaw).scale(value2);
         Vec3 vec3 = vec2.scale(-1.0);
         Vec3 vec4 = new Vec3(0.0, 1.0, 0.0);
         Vec3 vec6 = vec4.crossProduct(vec3.subtract(vec2)).normalize();
         Vec3 vec7 = vec2.add(vec);
         Vec3 vec8 = vec7;
         double d10 = value2 * value2;
         GlStateManager.translate(0.0, (double)this.cameraEyeHeight - (mc.gameSettings.thirdPersonView == 0 ? 0.0 : 0.2) + (mc.thePlayer.isSneaking() ? -0.1 : 0.0), 0.0);
         if (this.rotateVertical.getValue() && (!this.onlyWhileModuleRotation.getValue() || !ClientManagers.rotationController.hasNoActiveOwner() && this.rotateWithSpoofRotations.getValue())) {
            GlStateManager.rotate(-f2, (float)vec6.xCoord, 0.0F, (float)vec6.zCoord);
         }

         Vec3 vec9 = RotationUtil.getLookVector(0.0F, f5).scale(value2);
         Vec3 vec12 = vec9.scale(-1.0).subtract(vec9).crossProduct(vec4).normalize();
         Vec3[] vecs = new Vec3[n * 2 + 2];
         float[][] floatsArr = new float[vecs.length][];
         boolean flag = this.colorMode.getSelectedValue().equalsIgnoreCase("rainbow");
         boolean flag2 = this.colorMode.getSelectedValue().equalsIgnoreCase("astolfo");
         float f6 = MathHelper.clamp_float((float)this.rainbowAlpha.getValue() / 255.0F, 0.01F, 1.0F);
         float f7 = MathHelper.clamp_float((float)this.astolfoAlpha.getValue() / 255.0F, 0.01F, 1.0F);

         for (int i = 0; i <= n; i++) {
            double d11 = (double)i / (double)n;
            Vec3[] vecs2 = intersectCircleChord(this.projectOntoAxis(vec6, new Vec3(Math.cos((d11 + 0.5) * Math.PI + d9), 0.0, Math.sin((d11 + 0.5) * Math.PI + d9)).scale(value2)), vec6, vec2, vec3, d10, i, n);
            Vec3 vec15 = vecs2[0];
            Vec3 vec16 = vecs2[1];
            Vec3 vec17 = this.projectOntoAxis(vec12, vec15);
            Vec3 vec18 = this.projectOntoAxis(vec12, vec16);
            double d12 = vec17.subtract(vec9).length() / value2 / 2.0;
            double d13 = vec18.subtract(vec9).length() / value2 / 2.0;
            double d14 = MathHelper.wrapAngleTo180_double((double)f5) + 180.0;
            vecs[i] = vec15;
            vecs[n * 2 + 1 - i] = vec16;
            vec15 = vec15.add(vec);
            vec16 = vec16.add(vec);
            if (flag) {
               double[] doubles = hsvToRgb((Math.atan2(vec15.zCoord, vec15.xCoord) / Math.PI * 180.0 + 180.0 - d14) % 360.0 / 360.0, 1.0, 1.0);
               GlStateManager.color((float)doubles[0], (float)doubles[1], (float)doubles[2], f6);
            } else if (flag2) {
               int[] astolfoRgbArray = ColorUtil.getAstolfoRgbArray(MathHelper.wrapAngleTo180_double(Math.atan2(vec15.zCoord, vec15.xCoord) / Math.PI * 180.0 + 180.0 - d14) + 180.0);
               GlStateManager.color((float)astolfoRgbArray[0] / 255.0F, (float)astolfoRgbArray[1] / 255.0F, (float)astolfoRgbArray[2] / 255.0F, f7);
            } else {
               GlStateManager.color(
                  MathStatisticsUtil.lerpFloat(d, d5, d12),
                  MathStatisticsUtil.lerpFloat(d2, d6, d12),
                  MathStatisticsUtil.lerpFloat(d3, d7, d12),
                  MathStatisticsUtil.lerpFloat(d4, d8, d12)
               );
            }

            floatsArr[i] = new float[]{
               GlStateManager.colorState.red, GlStateManager.colorState.green, GlStateManager.colorState.blue, GlStateManager.colorState.alpha
            };
            GL11.glBegin(5);
            GL11.glVertex3d(vec7.xCoord, vec7.yCoord - value3, vec7.zCoord);
            GL11.glVertex3d(vec.xCoord, vec.yCoord, vec.zCoord);
            GL11.glVertex3d(vec15.xCoord, vec15.yCoord - value3, vec15.zCoord);
            GL11.glEnd();
            if (flag) {
               double[] doubles2 = hsvToRgb((Math.atan2(vec16.zCoord, vec16.xCoord) / Math.PI * 180.0 + 180.0 - d14) % 360.0 / 360.0, 1.0, 1.0);
               GlStateManager.color((float)doubles2[0], (float)doubles2[1], (float)doubles2[2], f6);
            } else if (flag2) {
               int[] astolfoRgbArray2 = ColorUtil.getAstolfoRgbArray(MathHelper.wrapAngleTo180_double(Math.atan2(vec16.zCoord, vec16.xCoord) / Math.PI * 180.0 + 180.0 - d14) + 180.0);
               GlStateManager.color((float)astolfoRgbArray2[0] / 255.0F, (float)astolfoRgbArray2[1] / 255.0F, (float)astolfoRgbArray2[2] / 255.0F, f7);
            } else {
               GlStateManager.color(
                  MathStatisticsUtil.lerpFloat(d, d5, d13),
                  MathStatisticsUtil.lerpFloat(d2, d6, d13),
                  MathStatisticsUtil.lerpFloat(d3, d7, d13),
                  MathStatisticsUtil.lerpFloat(d4, d8, d13)
               );
            }

            floatsArr[n * 2 + 1 - i] = new float[]{
               GlStateManager.colorState.red, GlStateManager.colorState.green, GlStateManager.colorState.blue, GlStateManager.colorState.alpha
            };
            GL11.glBegin(5);
            GL11.glVertex3d(vec8.xCoord, vec8.yCoord - value3, vec8.zCoord);
            GL11.glVertex3d(vec.xCoord, vec.yCoord, vec.zCoord);
            GL11.glVertex3d(vec16.xCoord, vec16.yCoord - value3, vec16.zCoord);
            GL11.glEnd();
            vec7 = vec15;
            vec8 = vec16;
         }

         if (this.enabled.getValue()) {
            GlStateManager.depthMask(false);
            GL11.glEnable(2848);
            float f8 = (float)this.redOffset.getValue() / 255.0F;
            float f9 = (float)this.greenOffset.getValue() / 255.0F;
            float f10 = (float)this.blueOffset.getValue() / 255.0F;
            float f11 = MathHelper.clamp_float((float)this.alpha.getValue() / 255.0F, 0.01F, 1.0F);
            GL11.glLineWidth((float)MathHelper.clamp_double(this.lineWidth.getValue(), 0.01, 10.0));
            GL11.glBegin(3);

            for (int j = 0; j < vecs.length; j++) {
               Vec3 vec19 = vecs[j].scale(1.0001);
               float[] floats = floatsArr[j];
               GlStateManager.color(MathHelper.clamp_float(floats[0] + f8, 0.0F, 1.0F), MathHelper.clamp_float(floats[1] + f9, 0.0F, 1.0F), MathHelper.clamp_float(floats[2] + f10, 0.0F, 1.0F), f11);
               GL11.glVertex3d(vec19.xCoord + vec.xCoord, vec19.yCoord + vec.yCoord - value3 + 0.001, vec19.zCoord + vec.zCoord);
            }

            GL11.glEnd();
            GlStateManager.depthMask(true);
         }

         RenderUtil.end3DRender();
         GlStateManager.enableAlpha();
         RenderUtil.setGlColor(-1);
         GlStateManager.popMatrix();
      }
   }

   public static Vec3[] intersectCircleChord(Vec3 vec, Vec3 vec2, Vec3 vec3, Vec3 vec4, double d, int n, int n2) {
      double xCoord = vec.xCoord;
      double zCoord = vec.zCoord;
      double xCoord2 = vec2.xCoord;
      double zCoord2 = vec2.zCoord;
      double d2 = xCoord2 * xCoord2 + zCoord2 * zCoord2;
      double d3 = (xCoord * xCoord2 + zCoord * zCoord2) / d2;
      double d4 = xCoord * xCoord + zCoord * zCoord;
      double d5 = 0.0;
      double d6 = (d - d4) / d2 + d3 * d3;
      if (d6 > 0.0) {
         d5 = Math.sqrt(d6);
      }

      double d7 = d5 - d3;
      double d8 = -d5 - d3;
      Vec3 vec5 = vec.add(vec2.scale(d7));
      Vec3 vec6 = vec.add(vec2.scale(d8));
      if (n == 0) {
         vec5 = vec3;
         vec6 = vec3;
      } else if (n == n2) {
         vec5 = vec4;
         vec6 = vec4;
      }

      return new Vec3[]{vec5, vec6};
   }

   public Vec3 projectOntoAxis(Vec3 vec, Vec3 vec2) {
      Vec3 vec3 = vec.scale(vec2.dotProduct(vec));
      Vec3 vec4 = vec2.add(vec3);
      Vec3 vec5 = vec2.subtract(vec3);
      Vec3 vec6 = vec4;
      if (Math.abs(vec5.dotProduct(vec)) < Math.abs(vec4.dotProduct(vec))) {
         vec6 = vec5;
      }

      return vec6;
   }

   public float interpolateAngle(float f, float f2, float f3) {
      return Math.abs(f2 - f) <= 180.0F ? f + (f2 - f) * f3 : f + (360.0F - f + f2) * f3;
   }

   @Override
   public void onOrientCamera(EventOrientCamera event) {
      if (mc.gameSettings.thirdPersonView == 0) {
         this.cameraOffset = event.getOffset();
         this.cameraEyeHeight = event.getEyeHeight();
      } else {
         this.cameraOffset = new Vec3(0.0, 0.0, 0.0);
         this.cameraEyeHeight = ClientManagers.moduleManager.child.isToggled() && ClientManagers.moduleManager.child.editEyeHeight.getValue() ? 0.85F : mc.thePlayer.getEyeHeight();
      }
   }

   public static double[] hsvToRgb(double d, double d2, double d3) {
      double d4 = 0.0;
      double d5 = 0.0;
      double d6 = 0.0;
      if (d2 == 0.0) {
         d4 = d5 = d6 = (double)((int)(d3 * 255.0 + 0.5));
      } else {
         double d7 = (d - Math.floor(d)) * 6.0;
         double d8 = d7 - Math.floor(d7);
         double d9 = d3 * (1.0 - d2);
         double d10 = d3 * (1.0 - d2 * d8);
         double d11 = d3 * (1.0 - d2 * (1.0 - d8));
         switch ((int)d7) {
            case 0:
               d4 = d3 * 255.0 + 0.5;
               d5 = d11 * 255.0 + 0.5;
               d6 = d9 * 255.0 + 0.5;
               break;
            case 1:
               d4 = d10 * 255.0 + 0.5;
               d5 = d3 * 255.0 + 0.5;
               d6 = d9 * 255.0 + 0.5;
               break;
            case 2:
               d4 = d9 * 255.0 + 0.5;
               d5 = d3 * 255.0 + 0.5;
               d6 = d11 * 255.0 + 0.5;
               break;
            case 3:
               d4 = d9 * 255.0 + 0.5;
               d5 = d10 * 255.0 + 0.5;
               d6 = d3 * 255.0 + 0.5;
               break;
            case 4:
               d4 = d11 * 255.0 + 0.5;
               d5 = d9 * 255.0 + 0.5;
               d6 = d3 * 255.0 + 0.5;
               break;
            case 5:
               d4 = d3 * 255.0 + 0.5;
               d5 = d9 * 255.0 + 0.5;
               d6 = d10 * 255.0 + 0.5;
         }
      }

      return new double[]{d4 / 255.0, d5 / 255.0, d6 / 255.0};
   }

   private boolean isAstolfoAlphaVisible() {
      return this.colorMode.getSelectedValue().equalsIgnoreCase("Astolfo");
   }

   private boolean isRainbowAlphaVisible() {
      return this.colorMode.getSelectedValue().equalsIgnoreCase("Rainbow");
   }

   private boolean isSecondaryColorVisible() {
      return this.colorMode.getSelectedValue().equalsIgnoreCase("fade");
   }

   private boolean isPrimaryColorVisible() {
      return this.colorMode.getSelectedValue().equalsIgnoreCase("fade");
   }

}
