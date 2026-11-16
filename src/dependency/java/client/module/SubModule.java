package client.module;

import augustus.modes.combat.AuraRenderMode;
import augustus.modules.legit.Range;
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
import client.setting.RangeSetting;
import client.setting.Setting;
import client.setting.SettingContainer;
import client.setting.SettingProvider;
import client.util.BooleanSupplier;
import client.util.SimulatedPlayer;
import java.awt.Color;
import java.util.concurrent.ThreadLocalRandom;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

public abstract class SubModule extends Module implements RotationListener, MoveListener {
   public final ActionSetting actionSetting;
   public final ActionSetting actionSetting2;
   public final ModeSetting settingRotationMode;
   public final NumberSetting settingHitBoxPercentageVertical;
   public final NumberSetting settingHitBoxPercentageHorizontal;
   public final NumberSetting settingHitBoxPercentageVertical2;
   public final NumberSetting settingHitBoxPercentageHorizontal2;
   public final BooleanSetting settingAdvancedRots;
   public final BooleanSetting settingInterpolation;
   public final ModeSetting settingRandomType;
   public final ModeSetting settingRandomize;
   public final BooleanSetting settingReducedRotate;
   public final NumberSetting settingReduceRotationPercentage;
   public final BooleanSetting settingSmartRotate;
   public final BooleanSetting settingPitchOutliers;
   public final RangeSetting settingPitchOutliersAdd;
   public final RangeSetting settingPitchOutliersDelay;
   public final ActionSetting actionSetting5;
   public final NumberSetting settingBestHitXMinDist;
   public final NumberSetting settingBestHitYMInDist;
   public final NumberSetting settingMinAbsDist;
   public final NumberSetting settingMaxXAbsDist;
   public final NumberSetting settingMaxYAbsDist;
   public final ActionSetting actionSetting3;
   public final ActionSetting actionSetting4;
   public final NumberSetting settingMaxRotationAngle;
   public final NumberSetting settingRandomStrength;
   public final BooleanSetting settingDrops2;
   public final ModeSetting modeSetting4;
   public final BooleanSetting settingSilentMoveFixIntave;
   public boolean expanded2;
   public boolean enabled2;
   public final SimulatedPlayer simulatedPlayer;

   public Rotation resolveRotation(Vec3 vec) {
      double posZ = vec.zCoord;
      double posY = vec.yCoord;
      double posX = vec.xCoord;
      return this.resolveRotation(posX, posY, posZ);
   }

   public Rotation getRotation(Entity entity) {
      Vec3 vec = this.resolveVecByEntity((char)0, entity, 73745997, 46414);
      if (vec != null) {
         double posZ = vec.zCoord;
         double posY = vec.yCoord;
         double posX = vec.xCoord;
         return this.resolveRotation(posX, posY, posZ);
      } else {
         return null;
      }
   }

   public Rotation resolveRotation(EntityLivingBase livingEntity, float factor, float factor2, Rotation rotation) {
      SettingProvider settingProvider = SettingContainer.settingProvider;
      double doubleItem = this.actionSetting.getDouble6();
      double doubleItem2 = this.actionSetting.getDouble3();
      float floatItem = settingProvider.getFloat(doubleItem2, doubleItem);
      settingProvider = SettingContainer.settingProvider;
      double doubleItem3 = this.actionSetting2.getDouble6();
      double doubleItem4 = this.actionSetting2.getDouble3();
      float floatItem2 = settingProvider.getFloat(doubleItem4, doubleItem3);
      return this.resolveRotation(livingEntity, (char)0, factor, factor2, 1662972407, 58882, rotation, floatItem, floatItem2);
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

   public Rotation resolveRotation(double posX, double posY, double posZ) {
      double offset = posX - minecraft.thePlayer.posX;
      double offset2 = posY - (minecraft.thePlayer.posY + (double)minecraft.thePlayer.getHeight2());
      double offset3 = posZ - minecraft.thePlayer.posZ;
      float factor = (float)(MathHelper.getDouble6(offset3, offset) * 180.0 / Math.PI - 90.0);
      float factor2 = (float)(-(MathHelper.getDouble6(offset2, (double)MathHelper.sqrt_double(offset * offset + offset3 * offset3)) * 180.0 / Math.PI));
      return this.resolveRotation(factor, factor2);
   }

   @Override
   public void onRotation(EventRotation event) {
      Setting[] settingArray = ModuleManager.getSettingArray();
      boolean expanded = this.expanded2;
      if (settingArray == null) {
         if (!this.expanded2) {
            return;
         }

         expanded = this.settingDrops2.isEnabled();
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

         expanded = this.settingDrops2.isEnabled();
      }

      if (!expanded) {
         float scaleFactor = Client.getClient().getViaProtocolHelper().scaleFactor1;
         event.setScaleFactor(scaleFactor);
      }
   }

   public Rotation resolveRotation(Entity entity, char character, float factor2, float factor3, int count, int count2, Rotation rotation, float factor4, float factor5) {
        float factor = 0.0F;
        java.lang.Object entry = null;
      Setting[] settingArray;
      float factor6;
      float factor7;
      checkBlock: {
         byte byteValue = 0;
         int count3 = 1662972407;
         char character2 = '\ue602';
         settingArray = ModuleManager.getSettingArray();
         if (this.settingInterpolation.isEnabled()) {
            factor6 = SimulatedPlayer.getFloat7(rotation.yaw, factor2, factor4 / 180.0F);
            factor7 = SimulatedPlayer.getFloat7(rotation.pitch, factor3, factor5 / 180.0F);
            if (settingArray == null) {
               break checkBlock;
            }
         }

         factor6 = SimulatedPlayer.getFloat9(rotation.yaw, factor2, factor4);
         factor7 = SimulatedPlayer.getFloat9(rotation.pitch, factor3, factor5);
      }

      int count4;
      String selected = this.settingRandomize.getSelected();
      byte byteValue2 = -1;
      count4 = selected.hashCode();
      searchBlock:
      if (settingArray == null) {
         switch (count4) {
            case 63955982:
               count4 = (selected.equals("Basic") ? 1 : 0);
               if (settingArray != null) {
                  break searchBlock;
               }

               if (count4 == 0) {
                  break;
               }

               byteValue2 = 0;
               if (settingArray == null) {
                  break;
               }
            case 2128509802:
               count4 = (selected.equals("OnlyRotation") ? 1 : 0);
               if (settingArray != null) {
                  break searchBlock;
               }

               if (count4 == 0) {
                  break;
               }

               byteValue2 = 1;
               if (settingArray == null) {
                  break;
               }
            case -785344877:
               count4 = (selected.equals("Doubled") ? 1 : 0);
               if (settingArray != null) {
                  break searchBlock;
               }

               if (count4 == 0) {
                  break;
               }

               byteValue2 = 2;
               if (settingArray == null) {
                  break;
               }
            case -654193598:
               count4 = (selected.equals("Advanced") ? 1 : 0);
               if (settingArray != null) {
                  break searchBlock;
               }

               if (count4 != 0) {
                  byteValue2 = 3;
               }
         }

         count4 = byteValue2;
      }

      tryBlock:
      if (settingArray == null) {
         branchBlock: {
            switch (count4) {
               case 0:
                  factor6 += this.computeFactor(factor7);
                  factor7 += this.computeFactor(factor6);
                  if (settingArray == null) {
                     break branchBlock;
                  }
               case 1:
                  double offset = (double)MathHelper.sqrt_float(factor2 - rotation.yaw);
                  double offset2 = (double)MathHelper.sqrt_float(factor3 - rotation.pitch);
                  factor = -factor4;
                  scanBlock:
                  if (settingArray == null) {
                     evalBlock:
                     if ((double)factor <= offset) {
                        count4 = Double.compare(offset, (double)factor4);
                        if (settingArray == null) {
                           if (count4 > 0) {
                              break evalBlock;
                           }

                           factor = -factor5;
                           if (settingArray != null) {
                              break scanBlock;
                           }

                           count4 = Double.compare((double)factor, offset2);
                        }

                        if (count4 <= 0) {
                           count4 = Double.compare(offset2, (double)factor5);
                           if (settingArray != null) {
                              break tryBlock;
                           }

                           if (count4 <= 0) {
                              break branchBlock;
                           }
                        }
                     }

                     factor6 += this.computeFactor(factor7);
                     factor = factor7 + this.computeFactor(factor6);
                  }

                  factor7 = factor;
                  if (settingArray == null) {
                     break branchBlock;
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
                  if (settingArray == null) {
                     break branchBlock;
                  }
               case 3:
                  break;
               default:
                  break branchBlock;
            }

            count4 = ((entity instanceof EntityLivingBase) ? 1 : 0);
            if (settingArray != null) {
               break tryBlock;
            }

            if (count4 != 0) {
               boolean flag;
               double offset11;
               EntityLivingBase livingEntity;
               float factor18;
               float factor19;
               String text;
               double offset12;
               boolean flag2;
               boolean flag3;
               RangeSetting setting;
               RangeSetting setting2;
               guardBlock: {
                  entry = this.simulatedPlayer;
                  flag = this.enabled2;
                  livingEntity = (EntityLivingBase)entity;
                  factor18 = rotation.yaw;
                  factor19 = rotation.pitch;
                  text = this.settingRandomType.getSelected();
                  offset12 = this.settingReduceRotationPercentage.getValue();
                  flag2 = this.settingReducedRotate.isEnabled();
                  flag3 = this.settingPitchOutliers.isEnabled();
                  setting = this.settingPitchOutliersAdd;
                  setting2 = this.settingPitchOutliersDelay;
                  Range range = ClientGlobals.moduleManager.range;
                  if (settingArray == null) {
                     if (!((Module)ClientGlobals.moduleManager.range).isToggled()) {
                        offset11 = 3.0;
                        break guardBlock;
                     }

                     range = ClientGlobals.moduleManager.range;
                  }

                  offset11 = range.getCoordinate();
               }

               boolean enabled = this.settingSmartRotate.isEnabled();
               float value = (float)this.settingMaxXAbsDist.getValue();
               float value2 = (float)this.settingMaxYAbsDist.getValue();
               float value3 = (float)this.settingMinAbsDist.getValue();
               float doubleItem = (float)this.actionSetting3.getDouble3();
               float doubleItem2 = (float)this.actionSetting4.getDouble3();
               float doubleItem3 = (float)this.actionSetting3.getDouble6();
               float doubleItem4 = (float)this.actionSetting4.getDouble6();
               float value4 = (float)this.settingMaxRotationAngle.getValue();
               float doubleItem5 = (float)this.actionSetting5.getDouble3();
               float value5 = (float)this.settingBestHitXMinDist.getValue();
               float value6 = (float)this.settingBestHitYMInDist.getValue();
               float factor20 = value5;
               float factor21 = doubleItem5;
               float factor22 = value4;
               float factor23 = doubleItem4;
               float factor24 = doubleItem3;
               float factor25 = doubleItem2;
               float factor26 = doubleItem;
               float factor27 = value3;
               float factor28 = value2;
               float factor29 = value;
               boolean flag4 = enabled;
               double offset13 = offset11;
               RangeSetting setting3 = setting2;
               RangeSetting setting4 = setting;
               boolean flag5 = flag3;
               boolean flag6 = flag2;
               double offset14 = offset12;
               String text2 = text;
               float factor30 = factor19;
               float factor31 = factor18;
               EntityLivingBase livingEntity2 = livingEntity;
               boolean flag7 = flag;
               Rotation rotation2 = ((SimulatedPlayer)entry).getRotation(
                  flag7,
                  livingEntity2,
                  factor6,
                  factor7,
                  factor31,
                  factor30,
                  text2,
                  factor2,
                  factor3,
                  offset14,
                  flag6,
                  flag5,
                  setting4,
                  setting3,
                  offset13,
                  flag4,
                  factor29,
                  factor28,
                  factor27,
                  factor26,
                  factor25,
                  factor24,
                  factor23,
                  factor22,
                  factor21,
                  factor20,
                  value6
               );
               this.enabled2 = false;
               factor6 = rotation2.yaw;
               factor7 = rotation2.pitch;
            }
         }

         count4 = ((this.settingAdvancedRots.isEnabled()) ? 1 : 0);
      }

      if (count4 != 0) {
         factor7 = (float)((double)factor7 + Math.sin(0.06981317007977318 * (double)(SimulatedPlayer.getFloat9(rotation.yaw, factor2, 180.0F) - factor6)) * 8.0);
      }

      return SimulatedPlayer.getRotation8(factor6, factor7, rotation.yaw, rotation.pitch);
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
      return this.resolveRotation(factor2, factor3);
   }

   public Rotation resolveRotation(int count, Entity entity, char character, float factor, short shortValue) {
      Rotation rotation = ClientGlobals.rotationManager.getRotation3();
      Rotation rotation2 = rotation;
      Vec3 vec = this.resolveVec3(entity, (char)0, factor, 1620775850, (short)26298, rotation2);
      if (vec != null) {
         double posZ = vec.zCoord;
         double posY = vec.yCoord;
         double posX = vec.xCoord;
         return this.resolveRotation(posX, posY, posZ, factor);
      } else {
         return null;
      }
   }

   private Vec3 resolveVecByEntity(char character, Entity entity, int count, int count2) {
      Vec3 vec;
      int count3;
      byte byteValue = 0;
      int count4 = 73745997;
      char character2 = '\ub54e';
      vec = null;
      Setting[] settingArray = ModuleManager.getSettingArray();
      String selected = this.settingRotationMode.getSelected();
      byte byteValue2 = -1;
      Setting[] settingArray2 = settingArray;
      count3 = selected.hashCode();
      searchBlock:
      if (settingArray2 == null) {
         switch (count3) {
            case 2245120:
               count3 = (selected.equals("Head") ? 1 : 0);
               if (settingArray2 != null) {
                  break searchBlock;
               }

               if (count3 == 0) {
                  break;
               }

               byteValue2 = 0;
               if (settingArray2 == null) {
                  break;
               }
            case -1656516059:
               count3 = (selected.equals("BestHitVec") ? 1 : 0);
               if (settingArray2 != null) {
                  break searchBlock;
               }

               if (count3 == 0) {
                  break;
               }

               byteValue2 = 1;
               if (settingArray2 == null) {
                  break;
               }
            case -1967997729:
               count3 = (selected.equals("NearestHitVec") ? 1 : 0);
               if (settingArray2 != null) {
                  break searchBlock;
               }

               if (count3 != 0) {
                  byteValue2 = 2;
               }
         }

         count3 = byteValue2;
      }

      switch (count3) {
         case 0:
            vec = new Vec3(entity.posX, entity.posY + (double)entity.getHeight2(), entity.posZ);
            break;
         case 1:
            int value = (int)this.settingHitBoxPercentageVertical.getValue();
            int value2 = (int)this.settingHitBoxPercentageHorizontal.getValue();
            int count5 = value;
            vec = SimulatedPlayer.getVec3(entity, count5, value2);
            break;
         case 2:
            int value3 = (int)this.settingHitBoxPercentageVertical2.getValue();
            int value4 = (int)this.settingHitBoxPercentageHorizontal2.getValue();
            Rotation rotation = ClientGlobals.rotationManager.getRotation3();
            int count6 = value4;
            int count7 = value3;
            vec = SimulatedPlayer.getVec3(entity, count7, count6, rotation);
      }

      return vec;
   }

   private boolean isActive16() {
      Setting[] settingArray = ModuleManager.getSettingArray();
      boolean flag = this.settingRandomize.getSelected().equals("Advanced");
      if (settingArray == null) {
         if (!flag) {
            return false;
         }

         flag = this.settingReducedRotate.isEnabled();
      }

      return settingArray != null ? flag : flag;
   }

   private boolean isActive4() {
      return this.settingRandomize.getSelected().equals("Advanced");
   }

   public Rotation resolveRotation(float[] factorArray) {
      float factor = factorArray[0];
      float factor2 = factorArray[1];
      float factor3 = factor;
      return this.resolveRotation(factor3, factor2);
   }

   private boolean isActive21() {
      return this.settingRandomize.getSelected().equals("Advanced");
   }

   public Rotation resolveRotation(float factor, float factor2) {
      Rotation rotation = ClientGlobals.rotationManager.getRotation3();
      Object entry = null;
      return this.resolveRotation((EntityLivingBase)entry, factor, factor2, rotation);
   }

   private Vec3 resolveVec3(Entity entity, char character, float factor, int count, short shortValue, Rotation rotation) {
      Vec3 vec;
      int count2;
      byte byteValue = 0;
      int count3 = 1620775850;
      short shortValue2 = 26298;
      vec = null;
      Setting[] settingArray = ModuleManager.getSettingArray();
      String selected = this.settingRotationMode.getSelected();
      byte byteValue2 = -1;
      Setting[] settingArray2 = settingArray;
      count2 = selected.hashCode();
      searchBlock:
      if (settingArray2 == null) {
         switch (count2) {
            case 2245120:
               count2 = (selected.equals("Head") ? 1 : 0);
               if (settingArray2 != null) {
                  break searchBlock;
               }

               if (count2 == 0) {
                  break;
               }

               byteValue2 = 0;
               if (settingArray2 == null) {
                  break;
               }
            case -1656516059:
               count2 = (selected.equals("BestHitVec") ? 1 : 0);
               if (settingArray2 != null) {
                  break searchBlock;
               }

               if (count2 == 0) {
                  break;
               }

               byteValue2 = 1;
               if (settingArray2 == null) {
                  break;
               }
            case -1967997729:
               count2 = (selected.equals("NearestHitVec") ? 1 : 0);
               if (settingArray2 != null) {
                  break searchBlock;
               }

               if (count2 != 0) {
                  byteValue2 = 2;
               }
         }

         count2 = byteValue2;
      }

      switch (count2) {
         case 0:
            vec = new Vec3(
               entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * (double)minecraft.getTimer().scaleFactor6,
               entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * (double)minecraft.getTimer().scaleFactor6 + (double)entity.getHeight2(),
               entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * (double)minecraft.getTimer().scaleFactor6
            );
            break;
         case 1:
            int value = (int)this.settingHitBoxPercentageVertical.getValue();
            int value2 = (int)this.settingHitBoxPercentageHorizontal.getValue();
            int count4 = value;
            vec = SimulatedPlayer.getVec3(entity, factor, count4, value2);
            break;
         case 2:
            int value3 = (int)this.settingHitBoxPercentageVertical2.getValue();
            int value4 = (int)this.settingHitBoxPercentageHorizontal2.getValue();
            int count5 = value3;
            vec = SimulatedPlayer.getVec3(entity, factor, count5, value4, rotation);
      }

      return vec;
   }

   public Rotation resolveRotation(Rotation rotation) {
      float pitch = rotation.pitch;
      float yaw = rotation.yaw;
      return this.resolveRotation(yaw, pitch);
   }

   private boolean isActive20() {
      return this.settingRandomize.getSelected().equals("Advanced");
   }

   public void updateState2() {
      byte byteValue = 0;
      int count = 5434762;
      int count2 = 14981586;
      NumberSetting settingHitBoxPercentageHorizontal3 = this.settingHitBoxPercentageHorizontal;
      BooleanSupplier booleanSupplier = this::isActive11;
      settingHitBoxPercentageHorizontal3.getNumberSetting(booleanSupplier);
      settingHitBoxPercentageHorizontal3 = this.settingHitBoxPercentageVertical;
      BooleanSupplier booleanSupplier2 = this::isActive7;
      settingHitBoxPercentageHorizontal3.getNumberSetting(booleanSupplier2);
      settingHitBoxPercentageHorizontal3 = this.settingHitBoxPercentageHorizontal2;
      BooleanSupplier booleanSupplier3 = this::isActive13;
      settingHitBoxPercentageHorizontal3.getNumberSetting(booleanSupplier3);
      settingHitBoxPercentageHorizontal3 = this.settingHitBoxPercentageVertical2;
      BooleanSupplier booleanSupplier4 = this::isActive18;
      settingHitBoxPercentageHorizontal3.getNumberSetting(booleanSupplier4);
      ModeSetting modeSetting = this.modeSetting4;
      BooleanSupplier booleanSupplier5 = this::isActive10;
      modeSetting.getModeSetting(booleanSupplier5);
      BooleanSetting settingSilentMoveFixIntave2 = this.settingSilentMoveFixIntave;
      BooleanSupplier booleanSupplier6 = this::isActive;
      settingSilentMoveFixIntave2.getSetting(booleanSupplier6);
   }

   @Override
   public void onMove(EventMove event) {
      Setting[] settingArray = ModuleManager.getSettingArray();
      boolean expanded = this.expanded2;
      if (settingArray == null) {
         if (!this.expanded2) {
            return;
         }

         expanded = this.settingDrops2.isEnabled();
      }

      if (settingArray == null) {
         if (!expanded) {
            return;
         }

         expanded = this.modeSetting4.getSelected().equals("None");
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

         expanded = this.modeSetting4.getSelected().equals("Advanced");
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

   public Rotation resolveRotationByEntity(Entity entity, double posX, double posY, double posZ, Rotation rotation, float posX2, float posY2, float posZ2) {
      double offset = minecraft.thePlayer.lastTickPosX + (minecraft.thePlayer.posX - minecraft.thePlayer.lastTickPosX) * (double)posZ2;
      double offset2 = minecraft.thePlayer.lastTickPosY + (minecraft.thePlayer.posY - minecraft.thePlayer.lastTickPosY) * (double)posZ2;
      double offset3 = minecraft.thePlayer.lastTickPosZ + (minecraft.thePlayer.posZ - minecraft.thePlayer.lastTickPosZ) * (double)posZ2;
      double offset4 = posX - offset;
      double offset5 = posY - (offset2 + (double)minecraft.thePlayer.getHeight2());
      double offset6 = posZ - offset3;
      float factor = (float)(MathHelper.getDouble6(offset6, offset4) * 180.0 / Math.PI - 90.0);
      float factor2 = (float)(-(MathHelper.getDouble6(offset5, (double)MathHelper.sqrt_double(offset4 * offset4 + offset6 * offset6)) * 180.0 / Math.PI));
      return this.resolveRotation(entity, (char)0, factor, factor2, 1662972407, 58882, rotation, posX2, posY2);
   }

   public Rotation getRotation(Entity entity, Rotation rotation, float posX, float posY, float posZ) {
      Vec3 vec = this.resolveVec3(entity, (char)0, posX, 1620775850, (short)26298, rotation);
      if (vec != null) {
         double posZ2 = vec.zCoord;
         double posY2 = vec.yCoord;
         double posX2 = vec.xCoord;
         return this.resolveRotationByEntity(entity, posX2, posY2, posZ2, rotation, posY, posZ, posX);
      } else {
         return null;
      }
   }

   private boolean isActive14() {
      return this.settingRandomize.getSelected().equals("Advanced");
   }

   private boolean isActive19() {
      return this.settingRandomize.getSelected().equals("Advanced");
   }

   private boolean isActive3() {
      return this.settingRandomize.getSelected().equals("Advanced");
   }

   private boolean isActive18() {
      return this.settingRotationMode.getSelected().equalsIgnoreCase("NearestHitVec");
   }

   private boolean isActive10() {
      Setting[] settingArray = ModuleManager.getSettingArray();
      boolean enabled = this.settingDrops2.isEnabled();
      if (settingArray == null) {
         if (!enabled) {
            return false;
         }

         enabled = this.settingDrops2.isVisible();
      }

      return settingArray != null ? enabled : enabled;
   }

   private boolean isActive15() {
      return this.settingRandomize.getSelected().equals("Advanced");
   }

   private boolean isActive17() {
      return this.settingRandomize.getSelected().equals("Advanced");
   }

   private boolean isActive7() {
      return this.settingRotationMode.getSelected().equalsIgnoreCase("BestHitVec");
   }

   private boolean isActive6() {
      return this.settingRandomize.getSelected().equals("Advanced");
   }

   private boolean isActive2() {
      return this.settingRandomize.getSelected().equals("Advanced");
   }

   private boolean isActive() {
      Setting[] settingArray = ModuleManager.getSettingArray();
      boolean visible = this.modeSetting4.isVisible();
      if (settingArray == null) {
         if (!visible) {
            return false;
         }

         visible = this.modeSetting4.getSelected().equals("None");
      }

      return settingArray != null ? visible : !visible;
   }

   private boolean isActiveAsFlag() {
      return this.settingRandomize.getSelected().equals("Advanced");
   }

   private boolean isActive8() {
      return this.settingRandomize.getSelected().equals("Advanced");
   }

   private boolean isActive9() {
      Setting[] settingArray = ModuleManager.getSettingArray();
      boolean flag = this.settingRandomize.getSelected().equals("Advanced");
      if (settingArray == null) {
         if (!flag) {
            return false;
         }

         flag = this.settingPitchOutliers.isEnabled();
      }

      return settingArray != null ? flag : flag;
   }

   @Override
   public void onEnable() {
      this.registerEvent(28);
      this.registerEvent(21);
   }

   private boolean isActive13() {
      return this.settingRotationMode.getSelected().equalsIgnoreCase("NearestHitVec");
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void onPreDisable() {
      byte byteValue = 0;
      int count = 306858775;
      short shortValue = 9708;
      SimulatedPlayer simulatedPlayer2 = this.simulatedPlayer;
      float doubleItem = (float)this.actionSetting5.getDouble6();
      simulatedPlayer2.processAction(doubleItem, 1000);
   }

   private boolean isActive5() {
      Setting[] settingArray = ModuleManager.getSettingArray();
      boolean flag = this.settingRandomize.getSelected().equals("Advanced");
      if (settingArray == null) {
         if (flag) {
            return false;
         }

         flag = this.settingRandomize.getSelected().equals("None");
      }

      return settingArray != null ? flag : !flag;
   }

   private boolean isActive12() {
      Setting[] settingArray = ModuleManager.getSettingArray();
      boolean flag = this.settingRandomize.getSelected().equals("Advanced");
      if (settingArray == null) {
         if (!flag) {
            return false;
         }

         flag = this.settingPitchOutliers.isEnabled();
      }

      return settingArray != null ? flag : flag;
   }

   private boolean isActive11() {
      return this.settingRotationMode.getSelected().equalsIgnoreCase("BestHitVec");
   }

   public SubModule(String text, String text2, Color color, Category category) {
        super(text, text2, color, category);
      
      ActionSetting setting = new ActionSetting(this, "YawSpeedInRotationModule", 30.0, 50.0, 0.0, 180.0, 0);
      String text3 = "MaxYawSpeed";
      String text4 = "MinYawSpeed";
      this.actionSetting = setting.getActionSetting(text4, text3);
      setting = new ActionSetting(this, "PitchSpeedInRotationModule", 30.0, 50.0, 0.0, 180.0, 0);
      String text5 = "MaxPitchSpeed";
      String text6 = "MinPitchSpeed";
      this.actionSetting2 = setting.getActionSetting(text6, text5);
      ModeSetting setting2 = new ModeSetting(this, "RotationModeInEntityRotationModule", "BestHitVec", new String[]{"Head", "BestHitVec", "NearestHitVec"});
      String text7 = "RotationMode";
      this.settingRotationMode = setting2.getModeSetting(text7);
      NumberSetting setting3 = new NumberSetting(this, "HitBoxPercentageVerticalInEntityRotationModule", 100.0, 1.0, 100.0, 0);
      String text8 = "HitBoxPercentageVertical";
      this.settingHitBoxPercentageVertical = setting3.getNumberSetting(text8);
      NumberSetting setting4 = new NumberSetting(this, "HitBoxPercentageHorizontalInEntityRotationModule", 100.0, 1.0, 100.0, 0);
      String text9 = "HitBoxPercentageHorizontal";
      this.settingHitBoxPercentageHorizontal = setting4.getNumberSetting(text9);
      NumberSetting setting5 = new NumberSetting(this, "HitBoxPercentageVerticalInEntityRotationModule1", 100.0, 1.0, 100.0, 0);
      String text10 = "HitBoxPercentageVertical";
      this.settingHitBoxPercentageVertical2 = setting5.getNumberSetting(text10);
      NumberSetting setting6 = new NumberSetting(this, "HitBoxPercentageHorizontalInEntityRotationModule1", 100.0, 1.0, 100.0, 0);
      String text11 = "HitBoxPercentageHorizontal";
      this.settingHitBoxPercentageHorizontal2 = setting6.getNumberSetting(text11);
      BooleanSetting setting7 = new BooleanSetting(this, "AdvancedRotsInRotationModule", true);
      String text12 = "AdvancedRots";
      this.settingAdvancedRots = (BooleanSetting)setting7.getSetting(text12);
      BooleanSetting setting8 = new BooleanSetting(this, "InterpolationInRotationModule", false);
      String text13 = "Interpolation";
      this.settingInterpolation = (BooleanSetting)setting8.getSetting(text13);
      ModeSetting setting9 = new ModeSetting(this, "RandomTypeInRotationModule", "SecureRandom", new String[]{"Random", "SecureRandom", "Gaussian", "Intave"});
      String text14 = "RandomType";
      this.settingRandomType = setting9.getModeSetting(text14);
      ModeSetting setting10 = new ModeSetting(
         this, "RandomizeInRotationModule", "OnlyRotation", new String[]{"None", "Basic", "Doubled", "OnlyRotation", "Advanced"}
      );
      String text15 = "Randomize";
      this.settingRandomize = setting10.getModeSetting(text15);
      BooleanSetting setting11 = new BooleanSetting(this, "ReducedRotateInRotationModule", false);
      String text16 = "ReducedRotate";
      BooleanSetting setting12 = (BooleanSetting)setting11.getSetting(text16);
      BooleanSupplier booleanSupplier = this::isActive4;
      this.settingReducedRotate = (BooleanSetting)setting12.getSetting(booleanSupplier);
      NumberSetting setting13 = new NumberSetting(this, "ReduceRotationPercentageInRotationModule", 100.0, 0.0, 100.0, 0);
      String text17 = "ReduceRotationPercentage";
      NumberSetting numberSetting = setting13.getNumberSetting(text17);
      BooleanSupplier booleanSupplier2 = this::isActive16;
      this.settingReduceRotationPercentage = numberSetting.getNumberSetting(booleanSupplier2);
      BooleanSetting setting14 = new BooleanSetting(this, "SmartRotateInRotationModule", false);
      String text18 = "SmartRotate";
      BooleanSetting setting15 = (BooleanSetting)setting14.getSetting(text18);
      BooleanSupplier booleanSupplier3 = this::isActive8;
      this.settingSmartRotate = (BooleanSetting)setting15.getSetting(booleanSupplier3);
      BooleanSetting setting16 = new BooleanSetting(this, "PitchOutliersRotationModule", false);
      String text19 = "PitchOutliers";
      BooleanSetting setting17 = (BooleanSetting)setting16.getSetting(text19);
      BooleanSupplier booleanSupplier4 = this::isActive17;
      this.settingPitchOutliers = (BooleanSetting)setting17.getSetting(booleanSupplier4);
      RangeSetting setting18 = new RangeSetting(this, "PitchOutliersAddRotationModule", 4.0, 10.0, 0.0, 20.0, 2);
      String text20 = "PitchOutliersAdd";
      RangeSetting rangeSetting = setting18.getRangeSetting(text20);
      BooleanSupplier booleanSupplier5 = this::isActive12;
      this.settingPitchOutliersAdd = rangeSetting.getRangeSetting(15343, booleanSupplier5);
      RangeSetting rangeSetting2 = (new RangeSetting(this, "PitchOutliersDelayRotationModule", 15.0, 20.0, 1.0, 40.0, 0)).getRangeSetting2();
      String text21 = "PitchOutliersDelay";
      RangeSetting rangeSetting3 = rangeSetting2.getRangeSetting(text21);
      BooleanSupplier booleanSupplier6 = this::isActive9;
      this.settingPitchOutliersDelay = rangeSetting3.getRangeSetting(15343, booleanSupplier6);
      setting = new ActionSetting(this, "InnerRadiusInRotationModule", "RadiusInRotationModule", 4.0, 9.0, 0.0, 15.0, 2);
      String text22 = "Radius";
      String text23 = "InnerRadius";
      setting = setting.getActionSetting(text23, text22);
      BooleanSupplier booleanSupplier7 = this::isActive3;
      this.actionSetting5 = setting.getActionSetting(booleanSupplier7);
      NumberSetting setting19 = new NumberSetting(this, "BestHitXMinDistInRotationModule", 2.0, 0.0, 10.0, 2);
      String text24 = "BestHitXMinDist";
      NumberSetting numberSetting2 = setting19.getNumberSetting(text24);
      BooleanSupplier booleanSupplier8 = this::isActive20;
      this.settingBestHitXMinDist = numberSetting2.getNumberSetting(booleanSupplier8);
      NumberSetting setting20 = new NumberSetting(this, "BestHitYMInDistInRotationModule", 2.0, 0.0, 10.0, 2);
      String text25 = "BestHitYMInDist";
      NumberSetting numberSetting3 = setting20.getNumberSetting(text25);
      BooleanSupplier booleanSupplier9 = this::isActiveAsFlag;
      this.settingBestHitYMInDist = numberSetting3.getNumberSetting(booleanSupplier9);
      NumberSetting setting21 = new NumberSetting(this, "MinAbsDistInRotationModule", 0.5, 0.0, 10.0, 2);
      String text26 = "MinAbsDist";
      NumberSetting numberSetting4 = setting21.getNumberSetting(text26);
      BooleanSupplier booleanSupplier10 = this::isActive2;
      this.settingMinAbsDist = numberSetting4.getNumberSetting(booleanSupplier10);
      NumberSetting setting22 = new NumberSetting(this, "MaxXAbsDistInRotationModule", 5.0, 0.0, 20.0, 2);
      String text27 = "MaxXAbsDist";
      NumberSetting numberSetting5 = setting22.getNumberSetting(text27);
      BooleanSupplier booleanSupplier11 = this::isActive19;
      this.settingMaxXAbsDist = numberSetting5.getNumberSetting(booleanSupplier11);
      NumberSetting setting23 = new NumberSetting(this, "MaxYAbsDistInRotationModule", 20.0, 0.0, 20.0, 2);
      String text28 = "MaxYAbsDist";
      NumberSetting numberSetting6 = setting23.getNumberSetting(text28);
      BooleanSupplier booleanSupplier12 = this::isActive21;
      this.settingMaxYAbsDist = numberSetting6.getNumberSetting(booleanSupplier12);
      setting = new ActionSetting(this, "XRelDistInRotationModule", 0.5, 10.0, 0.0, 20.0, 2);
      String text29 = "MaxXRelDist";
      String text30 = "MinXRelDist";
      setting = setting.getActionSetting(text30, text29);
      BooleanSupplier booleanSupplier13 = this::isActive15;
      this.actionSetting3 = setting.getActionSetting(booleanSupplier13);
      setting = new ActionSetting(this, "YRelDistInRotationModule", 0.5, 10.0, 0.0, 20.0, 2);
      String text31 = "MaxYRelDist";
      String text32 = "MinYRelDist";
      setting = setting.getActionSetting(text32, text31);
      BooleanSupplier booleanSupplier14 = this::isActive6;
      this.actionSetting4 = setting.getActionSetting(booleanSupplier14);
      NumberSetting setting24 = new NumberSetting(this, "MaxRotationAngleInRotationModule", 90.0, 0.0, 180.0, 0);
      String text33 = "MaxRotationAngle";
      NumberSetting numberSetting7 = setting24.getNumberSetting(text33);
      BooleanSupplier booleanSupplier15 = this::isActive14;
      this.settingMaxRotationAngle = numberSetting7.getNumberSetting(booleanSupplier15);
      NumberSetting setting25 = new NumberSetting(this, "RandomStrengthInRotationModule", 1.25, 0.01, 5.0, 2);
      String text34 = "RandomStrength";
      NumberSetting numberSetting8 = setting25.getNumberSetting(text34);
      BooleanSupplier booleanSupplier16 = this::isActive5;
      this.settingRandomStrength = numberSetting8.getNumberSetting(booleanSupplier16);
      BooleanSetting setting26 = new BooleanSetting(this, "MoveFixInRotationModule", true);
      String text35 = "MoveFix";
      this.settingDrops2 = (BooleanSetting)setting26.getSetting(text35);
      ModeSetting setting27 = new ModeSetting(this, "SilentMoveFix", "Default", new String[]{"None", "Default", "Advanced"});
      String text36 = "SilentMoveFix";
      this.modeSetting4 = setting27.getModeSetting(text36);
      BooleanSetting setting28 = new BooleanSetting(this, "SilentMoveFixIntaveInRotationModule", true);
      String text37 = "SilentMoveFixIntave";
      this.settingSilentMoveFixIntave = (BooleanSetting)setting28.getSetting(text37);
      this.expanded2 = false;
      this.enabled2 = true;
      this.simulatedPlayer = new SimulatedPlayer();
   }

   public SubModule(String text, String text2, boolean flag, Color color, Category category) {
        super(text, text2, flag, color, category);
      
      ActionSetting setting = new ActionSetting(this, "YawSpeedInRotationModule", 30.0, 50.0, 0.0, 180.0, 0);
      String text3 = "MaxYawSpeed";
      String text4 = "MinYawSpeed";
      this.actionSetting = setting.getActionSetting(text4, text3);
      setting = new ActionSetting(this, "PitchSpeedInRotationModule", 30.0, 50.0, 0.0, 180.0, 0);
      String text5 = "MaxPitchSpeed";
      String text6 = "MinPitchSpeed";
      this.actionSetting2 = setting.getActionSetting(text6, text5);
      ModeSetting setting2 = new ModeSetting(this, "RotationModeInEntityRotationModule", "BestHitVec", new String[]{"Head", "BestHitVec", "NearestHitVec"});
      String text7 = "RotationMode";
      this.settingRotationMode = setting2.getModeSetting(text7);
      NumberSetting setting3 = new NumberSetting(this, "HitBoxPercentageVerticalInEntityRotationModule", 100.0, 1.0, 100.0, 0);
      String text8 = "HitBoxPercentageVertical";
      this.settingHitBoxPercentageVertical = setting3.getNumberSetting(text8);
      NumberSetting setting4 = new NumberSetting(this, "HitBoxPercentageHorizontalInEntityRotationModule", 100.0, 1.0, 100.0, 0);
      String text9 = "HitBoxPercentageHorizontal";
      this.settingHitBoxPercentageHorizontal = setting4.getNumberSetting(text9);
      NumberSetting setting5 = new NumberSetting(this, "HitBoxPercentageVerticalInEntityRotationModule1", 100.0, 1.0, 100.0, 0);
      String text10 = "HitBoxPercentageVertical";
      this.settingHitBoxPercentageVertical2 = setting5.getNumberSetting(text10);
      NumberSetting setting6 = new NumberSetting(this, "HitBoxPercentageHorizontalInEntityRotationModule1", 100.0, 1.0, 100.0, 0);
      String text11 = "HitBoxPercentageHorizontal";
      this.settingHitBoxPercentageHorizontal2 = setting6.getNumberSetting(text11);
      BooleanSetting setting7 = new BooleanSetting(this, "AdvancedRotsInRotationModule", true);
      String text12 = "AdvancedRots";
      this.settingAdvancedRots = (BooleanSetting)setting7.getSetting(text12);
      BooleanSetting setting8 = new BooleanSetting(this, "InterpolationInRotationModule", false);
      String text13 = "Interpolation";
      this.settingInterpolation = (BooleanSetting)setting8.getSetting(text13);
      ModeSetting setting9 = new ModeSetting(this, "RandomTypeInRotationModule", "SecureRandom", new String[]{"Random", "SecureRandom", "Gaussian", "Intave"});
      String text14 = "RandomType";
      this.settingRandomType = setting9.getModeSetting(text14);
      ModeSetting setting10 = new ModeSetting(
         this, "RandomizeInRotationModule", "OnlyRotation", new String[]{"None", "Basic", "Doubled", "OnlyRotation", "Advanced"}
      );
      String text15 = "Randomize";
      this.settingRandomize = setting10.getModeSetting(text15);
      BooleanSetting setting11 = new BooleanSetting(this, "ReducedRotateInRotationModule", false);
      String text16 = "ReducedRotate";
      BooleanSetting setting12 = (BooleanSetting)setting11.getSetting(text16);
      BooleanSupplier booleanSupplier = this::isActive4;
      this.settingReducedRotate = (BooleanSetting)setting12.getSetting(booleanSupplier);
      NumberSetting setting13 = new NumberSetting(this, "ReduceRotationPercentageInRotationModule", 100.0, 0.0, 100.0, 0);
      String text17 = "ReduceRotationPercentage";
      NumberSetting numberSetting = setting13.getNumberSetting(text17);
      BooleanSupplier booleanSupplier2 = this::isActive16;
      this.settingReduceRotationPercentage = numberSetting.getNumberSetting(booleanSupplier2);
      BooleanSetting setting14 = new BooleanSetting(this, "SmartRotateInRotationModule", false);
      String text18 = "SmartRotate";
      BooleanSetting setting15 = (BooleanSetting)setting14.getSetting(text18);
      BooleanSupplier booleanSupplier3 = this::isActive8;
      this.settingSmartRotate = (BooleanSetting)setting15.getSetting(booleanSupplier3);
      BooleanSetting setting16 = new BooleanSetting(this, "PitchOutliersRotationModule", false);
      String text19 = "PitchOutliers";
      BooleanSetting setting17 = (BooleanSetting)setting16.getSetting(text19);
      BooleanSupplier booleanSupplier4 = this::isActive17;
      this.settingPitchOutliers = (BooleanSetting)setting17.getSetting(booleanSupplier4);
      RangeSetting setting18 = new RangeSetting(this, "PitchOutliersAddRotationModule", 4.0, 10.0, 0.0, 20.0, 2);
      String text20 = "PitchOutliersAdd";
      RangeSetting rangeSetting = setting18.getRangeSetting(text20);
      BooleanSupplier booleanSupplier5 = this::isActive12;
      this.settingPitchOutliersAdd = rangeSetting.getRangeSetting(15343, booleanSupplier5);
      RangeSetting rangeSetting2 = (new RangeSetting(this, "PitchOutliersDelayRotationModule", 15.0, 20.0, 1.0, 40.0, 0)).getRangeSetting2();
      String text21 = "PitchOutliersDelay";
      RangeSetting rangeSetting3 = rangeSetting2.getRangeSetting(text21);
      BooleanSupplier booleanSupplier6 = this::isActive9;
      this.settingPitchOutliersDelay = rangeSetting3.getRangeSetting(15343, booleanSupplier6);
      setting = new ActionSetting(this, "InnerRadiusInRotationModule", "RadiusInRotationModule", 4.0, 9.0, 0.0, 15.0, 2);
      String text22 = "Radius";
      String text23 = "InnerRadius";
      setting = setting.getActionSetting(text23, text22);
      BooleanSupplier booleanSupplier7 = this::isActive3;
      this.actionSetting5 = setting.getActionSetting(booleanSupplier7);
      NumberSetting setting19 = new NumberSetting(this, "BestHitXMinDistInRotationModule", 2.0, 0.0, 10.0, 2);
      String text24 = "BestHitXMinDist";
      NumberSetting numberSetting2 = setting19.getNumberSetting(text24);
      BooleanSupplier booleanSupplier8 = this::isActive20;
      this.settingBestHitXMinDist = numberSetting2.getNumberSetting(booleanSupplier8);
      NumberSetting setting20 = new NumberSetting(this, "BestHitYMInDistInRotationModule", 2.0, 0.0, 10.0, 2);
      String text25 = "BestHitYMInDist";
      NumberSetting numberSetting3 = setting20.getNumberSetting(text25);
      BooleanSupplier booleanSupplier9 = this::isActiveAsFlag;
      this.settingBestHitYMInDist = numberSetting3.getNumberSetting(booleanSupplier9);
      NumberSetting setting21 = new NumberSetting(this, "MinAbsDistInRotationModule", 0.5, 0.0, 10.0, 2);
      String text26 = "MinAbsDist";
      NumberSetting numberSetting4 = setting21.getNumberSetting(text26);
      BooleanSupplier booleanSupplier10 = this::isActive2;
      this.settingMinAbsDist = numberSetting4.getNumberSetting(booleanSupplier10);
      NumberSetting setting22 = new NumberSetting(this, "MaxXAbsDistInRotationModule", 5.0, 0.0, 20.0, 2);
      String text27 = "MaxXAbsDist";
      NumberSetting numberSetting5 = setting22.getNumberSetting(text27);
      BooleanSupplier booleanSupplier11 = this::isActive19;
      this.settingMaxXAbsDist = numberSetting5.getNumberSetting(booleanSupplier11);
      ModuleManager.getSettingArray();
      NumberSetting setting23 = new NumberSetting(this, "MaxYAbsDistInRotationModule", 20.0, 0.0, 20.0, 2);
      String text28 = "MaxYAbsDist";
      setting23 = setting23.getNumberSetting(text28);
      BooleanSupplier booleanSupplier12 = this::isActive21;
      this.settingMaxYAbsDist = setting23.getNumberSetting(booleanSupplier12);
      ActionSetting setting24 = new ActionSetting(this, "XRelDistInRotationModule", 0.5, 10.0, 0.0, 20.0, 2);
      String text29 = "MaxXRelDist";
      String text30 = "MinXRelDist";
      ActionSetting actionSetting6 = setting24.getActionSetting(text30, text29);
      BooleanSupplier booleanSupplier13 = this::isActive15;
      this.actionSetting3 = actionSetting6.getActionSetting(booleanSupplier13);
      ActionSetting setting25 = new ActionSetting(this, "YRelDistInRotationModule", 0.5, 10.0, 0.0, 20.0, 2);
      String text31 = "MaxYRelDist";
      String text32 = "MinYRelDist";
      ActionSetting actionSetting7 = setting25.getActionSetting(text32, text31);
      BooleanSupplier booleanSupplier14 = this::isActive6;
      this.actionSetting4 = actionSetting7.getActionSetting(booleanSupplier14);
      setting23 = new NumberSetting(this, "MaxRotationAngleInRotationModule", 90.0, 0.0, 180.0, 0);
      String text33 = "MaxRotationAngle";
      setting23 = setting23.getNumberSetting(text33);
      BooleanSupplier booleanSupplier15 = this::isActive14;
      this.settingMaxRotationAngle = setting23.getNumberSetting(booleanSupplier15);
      setting23 = new NumberSetting(this, "RandomStrengthInRotationModule", 1.25, 0.01, 5.0, 2);
      String text34 = "RandomStrength";
      setting23 = setting23.getNumberSetting(text34);
      BooleanSupplier booleanSupplier16 = this::isActive5;
      this.settingRandomStrength = setting23.getNumberSetting(booleanSupplier16);
      BooleanSetting setting26 = new BooleanSetting(this, "MoveFixInRotationModule", true);
      String text35 = "MoveFix";
      this.settingDrops2 = (BooleanSetting)setting26.getSetting(text35);
      ModeSetting setting27 = new ModeSetting(this, "SilentMoveFix", "Default", new String[]{"None", "Default", "Advanced"});
      String text36 = "SilentMoveFix";
      this.modeSetting4 = setting27.getModeSetting(text36);
      BooleanSetting setting28 = new BooleanSetting(this, "SilentMoveFixIntaveInRotationModule", true);
      String text37 = "SilentMoveFixIntave";
      this.settingSilentMoveFixIntave = (BooleanSetting)setting28.getSetting(text37);
      this.expanded2 = false;
      this.enabled2 = true;
      this.simulatedPlayer = new SimulatedPlayer();
   }
}
