package client.module;

import augustus.modes.combat.AuraRenderMode;
import client.Client;
import client.ClientGlobals;
import client.event.EventMove;
import client.event.EventRotation;
import client.event.MoveListener;
import client.event.RotationListener;
import client.rotation.Rotation;
import client.setting.ActionSetting;
import client.setting.BooleanSetting;
import client.setting.ModeSetting;
import client.setting.NumberSetting;
import client.setting.Setting;
import client.setting.SettingContainer;
import client.setting.SettingProvider;
import client.util.BooleanSupplier;
import client.util.SimulatedPlayer;
import java.awt.Color;
import java.util.concurrent.ThreadLocalRandom;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

public abstract class ActionModule extends Module implements RotationListener, MoveListener {
   public final ActionSetting actionSetting2;
   public final ActionSetting actionSetting;
   public final ModeSetting settingRandomType;
   public final ModeSetting settingRandomize;
   public final NumberSetting settingRandomStrength;
   public final BooleanSetting settingAdvancedRots;
   public final BooleanSetting settingInterpolation;
   public final BooleanSetting settingMoveFix;
   public final ModeSetting modeSetting3;
   public final BooleanSetting settingSilentMoveFixIntave;
   public boolean expanded2;

   public Rotation getRotation(float factor, float factor2) {
      Rotation rotation = ClientGlobals.rotationManager.getRotation3();
      return this.resolveRotation(factor, factor2, rotation);
   }

   public Rotation resolveRotation(float factor, float factor2, Rotation rotation) {
      SettingProvider settingProvider = SettingContainer.settingProvider;
      double doubleItem = this.actionSetting2.getDouble6();
      double doubleItem2 = this.actionSetting2.getDouble3();
      float floatItem = settingProvider.getFloat(doubleItem2, doubleItem);
      settingProvider = SettingContainer.settingProvider;
      double doubleItem3 = this.actionSetting.getDouble6();
      double doubleItem4 = this.actionSetting.getDouble3();
      float floatItem2 = settingProvider.getFloat(doubleItem4, doubleItem3);
      return this.resolveRotation(factor, factor2, rotation, floatItem, floatItem2);
   }

   public Rotation resolveRotation(double posX, double posY, double posZ, float factor, float factor2) {
      double offset = posX - minecraft.thePlayer.posX;
      double offset2 = posY - (minecraft.thePlayer.posY + (double)minecraft.thePlayer.getHeight2());
      double offset3 = posZ - minecraft.thePlayer.posZ;
      float factor3 = (float)(MathHelper.getDouble6(offset3, offset) * 180.0 / Math.PI - 90.0);
      float factor4 = (float)(-(MathHelper.getDouble6(offset2, (double)MathHelper.sqrt_double(offset * offset + offset3 * offset3)) * 180.0 / Math.PI));
      Rotation rotation = ClientGlobals.rotationManager.getRotation3();
      return this.resolveRotation(factor3, factor4, rotation, factor, factor2);
   }

   public void updateState() {
      ModeSetting modeSetting = this.modeSetting3;
      BooleanSupplier booleanSupplier = this::isActive;
      modeSetting.getModeSetting(booleanSupplier);
      BooleanSetting settingSilentMoveFixIntave2 = this.settingSilentMoveFixIntave;
      BooleanSupplier booleanSupplier2 = this::isActiveAsFlag;
      settingSilentMoveFixIntave2.getSetting(booleanSupplier2);
   }

   public Rotation getRotation4(double posX, double posY, double posZ) {
      double offset = posX - minecraft.thePlayer.posX;
      double offset2 = posY - (minecraft.thePlayer.posY + (double)minecraft.thePlayer.getHeight2());
      double offset3 = posZ - minecraft.thePlayer.posZ;
      float factor = (float)(MathHelper.getDouble6(offset3, offset) * 180.0 / Math.PI - 90.0);
      float factor2 = (float)(-(MathHelper.getDouble6(offset2, (double)MathHelper.sqrt_double(offset * offset + offset3 * offset3)) * 180.0 / Math.PI));
      return this.getRotation(factor, factor2);
   }

   public Rotation resolveRotation(Rotation rotation) {
      float pitch = rotation.pitch;
      float yaw = rotation.yaw;
      return this.getRotation(yaw, pitch);
   }

   public Rotation getRotation(Vec3 vec) {
      double posZ = vec.zCoord;
      double posY = vec.yCoord;
      double posX = vec.xCoord;
      return this.getRotation4(posX, posY, posZ);
   }

   @Override
   public void onRotation(EventRotation event) {
      Setting[] settingArray = ModuleManager.getSettingArray();
      boolean expanded = this.expanded2;
      if (settingArray == null) {
         if (!this.expanded2) {
            return;
         }

         expanded = this.settingMoveFix.isEnabled();
      }

      if (!expanded) {
         float scaleFactor = Client.getClient().getViaProtocolHelper().scaleFactor1;
         event.setScaleFactor(scaleFactor);
      }
   }

   @Override
   public void onPostRotation(EventRotation event) {
      Setting[] settingArray = ModuleManager.getSettingArray();
      boolean expanded = this.expanded2;
      if (settingArray == null) {
         if (!this.expanded2) {
            return;
         }

         expanded = this.settingMoveFix.isEnabled();
      }

      if (!expanded) {
         float scaleFactor = Client.getClient().getViaProtocolHelper().scaleFactor1;
         event.setScaleFactor(scaleFactor);
      }
   }

   private float computeFactor(float factor) {
      int count;
      double offset;
      int count2;
      count = 0;
      offset = this.settingRandomStrength.getValue();
      Setting[] settingArray = ModuleManager.getSettingArray();
      String selected = this.settingRandomType.getSelected();
      Setting[] settingArray2 = settingArray;
      byte byteValue = -1;
      count2 = selected.hashCode();
      searchBlock:
      if (settingArray2 == null) {
         switch (count2) {
            case -1854418717:
               count2 = (selected.equals("Random") ? 1 : 0);
               if (settingArray2 != null) {
                  break searchBlock;
               }

               if (count2 == 0) {
                  break;
               }

               byteValue = 0;
               if (settingArray2 == null) {
                  break;
               }
            case -1461628389:
               count2 = (selected.equals("Gaussian") ? 1 : 0);
               if (settingArray2 != null) {
                  break searchBlock;
               }

               if (count2 == 0) {
                  break;
               }

               byteValue = 1;
               if (settingArray2 == null) {
                  break;
               }
            case 722717498:
               count2 = (selected.equals("SecureRandom") ? 1 : 0);
               if (settingArray2 != null) {
                  break searchBlock;
               }

               if (count2 == 0) {
                  break;
               }

               byteValue = 2;
               if (settingArray2 == null) {
                  break;
               }
            case -2099899231:
               count2 = (selected.equals("Intave") ? 1 : 0);
               if (settingArray2 != null) {
                  break searchBlock;
               }

               if (count2 != 0) {
                  byteValue = 3;
               }
         }

         count2 = byteValue;
      }

      switch (count2) {
         case 0:
            double offset2 = -offset;
            double offset3 = offset2;
            return SettingContainer.settingProvider.getFloat3(offset3, offset);
         case 1:
            return (float)(ThreadLocalRandom.current().nextGaussian() * offset);
         case 2:
            double offset4 = -offset;
            return SettingContainer.settingProvider.getFloat(offset4, offset);
         case 3:
            double offset5 = 2.0;
            double offset6 = 1.0;
            return (float)((double)((float)((double)SettingContainer.settingProvider.getFloat(offset6, offset5) * offset)) * Math.sin((double)factor * Math.PI));
         default:
            return 1.0F;
      }
   }

   public Rotation resolveRotation(float[] factorArray) {
      float factor = factorArray[0];
      float factor2 = factorArray[1];
      float factor3 = factor;
      return this.getRotation(factor3, factor2);
   }

   public Rotation resolveRotation(double posX, double posY, double posZ, Rotation rotation, float posX2, float posY2, float posZ2) {
      double offset = minecraft.thePlayer.lastTickPosX + (minecraft.thePlayer.posX - minecraft.thePlayer.lastTickPosX) * (double)posZ2;
      double offset2 = minecraft.thePlayer.lastTickPosY + (minecraft.thePlayer.posY - minecraft.thePlayer.lastTickPosY) * (double)posZ2;
      double offset3 = minecraft.thePlayer.lastTickPosZ + (minecraft.thePlayer.posZ - minecraft.thePlayer.lastTickPosZ) * (double)posZ2;
      double offset4 = posX - offset;
      double offset5 = posY - (offset2 + (double)minecraft.thePlayer.getHeight2());
      double offset6 = posZ - offset3;
      float factor = (float)(MathHelper.getDouble6(offset6, offset4) * 180.0 / Math.PI - 90.0);
      float factor2 = (float)(-(MathHelper.getDouble6(offset5, (double)MathHelper.sqrt_double(offset4 * offset4 + offset6 * offset6)) * 180.0 / Math.PI));
      return this.resolveRotation(factor, factor2, rotation, posX2, posY2);
   }

   private boolean isActiveAsFlag() {
      Setting[] settingArray = ModuleManager.getSettingArray();
      boolean visible = this.modeSetting3.isVisible();
      if (settingArray == null) {
         if (!visible) {
            return false;
         }

         visible = this.modeSetting3.getSelected().equals("None");
      }

      return settingArray != null ? visible : !visible;
   }

   public Rotation resolveRotation(float factor2, float factor3, Rotation rotation, float factor4, float factor5) {
        float factor = 0.0F;
      Setting[] settingArray;
      float factor6;
      float factor7;
      searchBlock: {
         settingArray = ModuleManager.getSettingArray();
         if (this.settingInterpolation.isEnabled()) {
            factor6 = SimulatedPlayer.getFloat7(rotation.yaw, factor2, factor4 / 180.0F);
            factor7 = SimulatedPlayer.getFloat7(rotation.pitch, factor3, factor5 / 180.0F);
            if (settingArray == null) {
               break searchBlock;
            }
         }

         factor6 = SimulatedPlayer.getFloat9(rotation.yaw, factor2, factor4);
         factor7 = SimulatedPlayer.getFloat9(rotation.pitch, factor3, factor5);
      }

      int count;
      String selected = this.settingRandomize.getSelected();
      byte byteValue = -1;
      count = selected.hashCode();
      guardBlock:
      if (settingArray == null) {
         switch (count) {
            case 63955982:
               count = (selected.equals("Basic") ? 1 : 0);
               if (settingArray != null) {
                  break guardBlock;
               }

               if (count == 0) {
                  break;
               }

               byteValue = 0;
               if (settingArray == null) {
                  break;
               }
            case 2128509802:
               count = (selected.equals("OnlyRotation") ? 1 : 0);
               if (settingArray != null) {
                  break guardBlock;
               }

               if (count == 0) {
                  break;
               }

               byteValue = 1;
               if (settingArray == null) {
                  break;
               }
            case -785344877:
               count = (selected.equals("Doubled") ? 1 : 0);
               if (settingArray != null) {
                  break guardBlock;
               }

               if (count != 0) {
                  byteValue = 2;
               }
         }

         count = byteValue;
      }

      scanBlock:
      if (settingArray == null) {
         switch (count) {
            case 0:
               factor6 += this.computeFactor(factor7);
               factor7 += this.computeFactor(factor6);
               if (settingArray == null) {
                  break;
               }
            case 1:
               double offset = (double)MathHelper.sqrt_float(factor2 - rotation.yaw);
               double offset2 = (double)MathHelper.sqrt_float(factor3 - rotation.pitch);
               factor = -factor4;
               evalBlock:
               if (settingArray == null) {
                  checkBlock:
                  if ((double)factor <= offset) {
                     count = Double.compare(offset, (double)factor4);
                     if (settingArray == null) {
                        if (count > 0) {
                           break checkBlock;
                        }

                        factor = -factor5;
                        if (settingArray != null) {
                           break evalBlock;
                        }

                        count = Double.compare((double)factor, offset2);
                     }

                     if (count <= 0) {
                        count = Double.compare(offset2, (double)factor5);
                        if (settingArray != null) {
                           break scanBlock;
                        }

                        if (count <= 0) {
                           break;
                        }
                     }
                  }

                  factor6 += this.computeFactor(factor7);
                  factor = factor7 + this.computeFactor(factor6);
               }

               factor7 = factor;
               if (settingArray == null) {
                  break;
               }
            case 2:
               float factor8 = this.computeFactor(factor7);
               float factor9 = this.computeFactor(factor7);
               float factor10 = this.computeFactor(factor7);
               float factor11 = this.computeFactor(factor7);
               float factor12 = this.computeFactor(factor6);
               float factor13 = this.computeFactor(factor6);
               float factor14 = this.computeFactor(factor6);
               float factor15 = this.computeFactor(factor6);
               SettingProvider settingProvider = SettingContainer.settingProvider;
               double offset3 = (double)Math.max(factor8, factor9);
               double offset4 = (double)Math.min(factor8, factor9);
               float factor16 = factor6 + settingProvider.getFloat(offset4, offset3);
               settingProvider = SettingContainer.settingProvider;
               double offset5 = (double)Math.max(factor10, factor11);
               double offset6 = (double)Math.min(factor10, factor11);
               factor6 = factor16 + settingProvider.getFloat(offset6, offset5);
               settingProvider = SettingContainer.settingProvider;
               double offset7 = (double)Math.max(factor12, factor13);
               double offset8 = (double)Math.min(factor12, factor13);
               float factor17 = factor7 + settingProvider.getFloat(offset8, offset7);
               settingProvider = SettingContainer.settingProvider;
               double offset9 = (double)Math.max(factor14, factor15);
               double offset10 = (double)Math.min(factor14, factor15);
               factor7 = factor17 + settingProvider.getFloat(offset10, offset9);
         }

         count = ((this.settingAdvancedRots.isEnabled()) ? 1 : 0);
      }

      if (count != 0) {
         factor7 = (float)((double)factor7 + Math.sin(0.06981317007977318 * (double)(SimulatedPlayer.getFloat9(rotation.yaw, factor2, 180.0F) - factor6)) * 8.0);
      }

      return SimulatedPlayer.getRotation8(factor6, factor7, rotation.yaw, rotation.pitch);
   }

   private boolean isActive() {
      Setting[] settingArray = ModuleManager.getSettingArray();
      boolean enabled = this.settingMoveFix.isEnabled();
      if (settingArray == null) {
         if (!enabled) {
            return false;
         }

         enabled = this.settingMoveFix.isVisible();
      }

      return settingArray != null ? enabled : enabled;
   }

   public Rotation resolveRotation(double posX, double posY, double posZ, Rotation rotation, float factor) {
      double offset = minecraft.thePlayer.lastTickPosX + (minecraft.thePlayer.posX - minecraft.thePlayer.lastTickPosX) * (double)factor;
      double offset2 = minecraft.thePlayer.lastTickPosY + (minecraft.thePlayer.posY - minecraft.thePlayer.lastTickPosY) * (double)factor;
      double offset3 = minecraft.thePlayer.lastTickPosZ + (minecraft.thePlayer.posZ - minecraft.thePlayer.lastTickPosZ) * (double)factor;
      double offset4 = posX - offset;
      double offset5 = posY - (offset2 + (double)minecraft.thePlayer.getHeight2());
      double offset6 = posZ - offset3;
      float factor2 = (float)(MathHelper.getDouble6(offset6, offset4) * 180.0 / Math.PI - 90.0);
      float factor3 = (float)(-(MathHelper.getDouble6(offset5, (double)MathHelper.sqrt_double(offset4 * offset4 + offset6 * offset6)) * 180.0 / Math.PI));
      return this.resolveRotation(factor2, factor3, rotation);
   }

   @Override
   public void onMove(EventMove event) {
      Setting[] settingArray = ModuleManager.getSettingArray();
      boolean expanded = this.expanded2;
      if (settingArray == null) {
         if (!this.expanded2) {
            return;
         }

         expanded = this.settingMoveFix.isEnabled();
      }

      if (settingArray == null) {
         if (!expanded) {
            return;
         }

         expanded = this.modeSetting3.getSelected().equals("None");
      }

      if (settingArray == null) {
         if (expanded) {
            return;
         }

         expanded = ClientGlobals.rotationManager.checkModule(this);
      }

      if (settingArray == null) {
         if (!expanded) {
            return;
         }

         expanded = this.modeSetting3.getSelected().equals("Advanced");
      }

      searchBlock: {
         if (expanded) {
            AuraRenderMode auraRenderMode = AuraRenderMode.ADVANCED;
            event.setAuraRenderMode(auraRenderMode);
            if (settingArray == null) {
               break searchBlock;
            }
         }

         AuraRenderMode auraRenderMode2 = AuraRenderMode.DEFAULT;
         event.setAuraRenderMode(auraRenderMode2);
      }

      if (this.settingSilentMoveFixIntave.isEnabled()) {
         event.setState1(true);
      }
   }

   public Rotation resolveRotation(double posX, double posY, double posZ, float factor) {
      double offset = minecraft.thePlayer.lastTickPosX + (minecraft.thePlayer.posX - minecraft.thePlayer.lastTickPosX) * (double)factor;
      double offset2 = minecraft.thePlayer.lastTickPosY + (minecraft.thePlayer.posY - minecraft.thePlayer.lastTickPosY) * (double)factor;
      double offset3 = minecraft.thePlayer.lastTickPosZ + (minecraft.thePlayer.posZ - minecraft.thePlayer.lastTickPosZ) * (double)factor;
      double offset4 = posX - offset;
      double offset5 = posY - (offset2 + (double)minecraft.thePlayer.getHeight2());
      double offset6 = posZ - offset3;
      float factor2 = (float)(MathHelper.getDouble6(offset6, offset4) * 180.0 / Math.PI - 90.0);
      float factor3 = (float)(-(MathHelper.getDouble6(offset5, (double)MathHelper.sqrt_double(offset4 * offset4 + offset6 * offset6)) * 180.0 / Math.PI));
      return this.getRotation(factor2, factor3);
   }

   @Override
   public void onEnable() {
      this.registerEvent(28);
      this.registerEvent(21);
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void onPreDisable() {
   }

   public ActionModule(String text, String text2, Color color, Category category) {
      super(text, text2, color, category);
      ActionSetting setting = new ActionSetting(this, "YawSpeedInRotationModule", 30.0, 50.0, 0.0, 180.0, 0);
      String text3 = "MaxYawSpeed";
      String text4 = "MinYawSpeed";
      this.actionSetting2 = setting.getActionSetting(text4, text3);
      setting = new ActionSetting(this, "PitchSpeedInRotationModule", 30.0, 50.0, 0.0, 180.0, 0);
      String text5 = "MaxPitchSpeed";
      String text6 = "MinPitchSpeed";
      this.actionSetting = setting.getActionSetting(text6, text5);
      ModeSetting setting2 = new ModeSetting(this, "RandomTypeInRotationModule", "SecureRandom", new String[]{"Random", "SecureRandom", "Gaussian", "Intave"});
      String text7 = "RandomType";
      this.settingRandomType = setting2.getModeSetting(text7);
      ModeSetting setting3 = new ModeSetting(this, "RandomizeInRotationModule", "OnlyRotation", new String[]{"None", "Basic", "Doubled", "OnlyRotation"});
      String text8 = "Randomize";
      this.settingRandomize = setting3.getModeSetting(text8);
      NumberSetting setting4 = new NumberSetting(this, "RandomStrengthInRotationModule", 1.25, 0.01, 5.0, 2);
      String text9 = "RandomStrength";
      this.settingRandomStrength = setting4.getNumberSetting(text9);
      BooleanSetting setting5 = new BooleanSetting(this, "AdvancedRotsInRotationModule", true);
      String text10 = "AdvancedRots";
      this.settingAdvancedRots = (BooleanSetting)setting5.getSetting(text10);
      BooleanSetting setting6 = new BooleanSetting(this, "InterpolationInRotationModule", false);
      String text11 = "Interpolation";
      this.settingInterpolation = (BooleanSetting)setting6.getSetting(text11);
      BooleanSetting setting7 = new BooleanSetting(this, "MoveFixInRotationModule", true);
      String text12 = "MoveFix";
      this.settingMoveFix = (BooleanSetting)setting7.getSetting(text12);
      ModeSetting setting8 = new ModeSetting(this, "SilentMoveFix", "Default", new String[]{"None", "Default", "Advanced"});
      String text13 = "SilentMoveFix";
      this.modeSetting3 = setting8.getModeSetting(text13);
      BooleanSetting setting9 = new BooleanSetting(this, "SilentMoveFixIntaveInRotationModule", true);
      String text14 = "SilentMoveFixIntave";
      this.settingSilentMoveFixIntave = (BooleanSetting)setting9.getSetting(text14);
      this.expanded2 = false;
   }

   public ActionModule(String text, String text2, boolean flag, Color color, Category category) {
      super(text, text2, flag, color, category);
      ActionSetting setting = new ActionSetting(this, "YawSpeedInRotationModule", 30.0, 50.0, 0.0, 180.0, 0);
      String text3 = "MaxYawSpeed";
      String text4 = "MinYawSpeed";
      this.actionSetting2 = setting.getActionSetting(text4, text3);
      setting = new ActionSetting(this, "PitchSpeedInRotationModule", 30.0, 50.0, 0.0, 180.0, 0);
      String text5 = "MaxPitchSpeed";
      String text6 = "MinPitchSpeed";
      this.actionSetting = setting.getActionSetting(text6, text5);
      ModeSetting setting2 = new ModeSetting(this, "RandomTypeInRotationModule", "SecureRandom", new String[]{"Random", "SecureRandom", "Gaussian", "Intave"});
      String text7 = "RandomType";
      this.settingRandomType = setting2.getModeSetting(text7);
      ModeSetting setting3 = new ModeSetting(this, "RandomizeInRotationModule", "OnlyRotation", new String[]{"None", "Basic", "Doubled", "OnlyRotation"});
      String text8 = "Randomize";
      this.settingRandomize = setting3.getModeSetting(text8);
      NumberSetting setting4 = new NumberSetting(this, "RandomStrengthInRotationModule", 1.25, 0.01, 5.0, 2);
      String text9 = "RandomStrength";
      this.settingRandomStrength = setting4.getNumberSetting(text9);
      BooleanSetting setting5 = new BooleanSetting(this, "AdvancedRotsInRotationModule", true);
      String text10 = "AdvancedRots";
      this.settingAdvancedRots = (BooleanSetting)setting5.getSetting(text10);
      BooleanSetting setting6 = new BooleanSetting(this, "InterpolationInRotationModule", false);
      String text11 = "Interpolation";
      this.settingInterpolation = (BooleanSetting)setting6.getSetting(text11);
      BooleanSetting setting7 = new BooleanSetting(this, "MoveFixInRotationModule", true);
      String text12 = "MoveFix";
      this.settingMoveFix = (BooleanSetting)setting7.getSetting(text12);
      ModeSetting setting8 = new ModeSetting(this, "SilentMoveFix", "Default", new String[]{"None", "Default", "Advanced"});
      String text13 = "SilentMoveFix";
      this.modeSetting3 = setting8.getModeSetting(text13);
      BooleanSetting setting9 = new BooleanSetting(this, "SilentMoveFixIntaveInRotationModule", true);
      String text14 = "SilentMoveFixIntave";
      this.settingSilentMoveFixIntave = (BooleanSetting)setting9.getSetting(text14);
      this.expanded2 = false;
   }
}
