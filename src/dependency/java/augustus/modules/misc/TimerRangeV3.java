package augustus.modules.misc;

import client.ClientGlobals;
import client.event.EventMouseClick;
import client.event.EventSendPacket;
import client.event.MouseClickListener;
import client.event.NetworkPacketListener;
import client.module.Category;
import client.module.Module;
import client.setting.BooleanSetting;
import client.setting.NumberSetting;
import client.util.ChatUtil;
import client.util.CombatUtil;
import client.util.GAMELAGEnum;
import client.util.LvlManager;
import client.util.RayTraceUtil;
import java.awt.Color;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

public class TimerRangeV3 extends Module implements NetworkPacketListener, MouseClickListener {
   public final NumberSetting settingBlinkTicks = new NumberSetting(this, "BlinkTicks", 2.0, 0.0, 10.0, 0);
   public final NumberSetting settingTimerTicks = new NumberSetting(this, "TimerTicks", 5.0, 0.0, 10.0, 0);
   public final NumberSetting settingMaxTargetHurttime = new NumberSetting(this, "MaxTargetHurttime", 3.0, 0.0, 10.0, 0);
   public final BooleanSetting settingCorrect = new BooleanSetting(this, "Correct", false);
   public final BooleanSetting settingDynamic = new BooleanSetting(this, "Dynamic", true);
   public final BooleanSetting settingDebug = new BooleanSetting(this, "Debug", false);
   public GAMELAGEnum gAMELAGEnum;
   public int count6 = 0;
   public int count7 = 0;
   public int count8 = 0;
   public int count9 = 0;

   public TimerRangeV3() {
      super("TimerRangeV3", "ZeitDistanzV3", new Color(23, 233, 123), Category.LATENCY);
   }

   @Override
   public void onPreDisable() {
      this.gAMELAGEnum = GAMELAGEnum.NOTHING;
      this.count6 = 0;
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void onEnable() {
      this.registerEvent(16);
      this.registerEvent(25);
   }

   @Override
   public void onMouseClick(EventMouseClick event) {
      EntityLivingBase livingEntity;
      livingEntity = ChatUtil.getEntityLivingBase();
      boolean enabled = this.settingCorrect.isEnabled();
      event.processBoolean(enabled);
      searchBlock:
      if (this.gAMELAGEnum == GAMELAGEnum.NOTHING && livingEntity != null && event.getIndex() >= 1) {
         if (this.settingBlinkTicks.getValue() > 0.0) {
            this.count9 = 0;

            for (int index = (int)((double)((int)this.settingTimerTicks.getValue()) + this.settingBlinkTicks.getValue()); index > 0; index--) {
               int count = (int)Math.min((double)index, this.settingTimerTicks.getValue());
               int count2 = (int)Math.max((double)index - this.settingTimerTicks.getValue(), 0.0);
               int count3 = count;
               if (this.matchesEntityLivingBase(livingEntity, count3, count2, 0)) {
                  this.gAMELAGEnum = GAMELAGEnum.CONNECTION_LAG;
                  this.count7 = (int)Math.min((double)index, this.settingTimerTicks.getValue());
                  this.count8 = (int)Math.max((double)index - this.settingTimerTicks.getValue(), 0.0);
                  break searchBlock;
               }

               if (!this.settingDynamic.isEnabled()) {
                  break;
               }
            }
         } else {
            for (int index2 = (int)this.settingTimerTicks.getValue(); index2 > 0; index2--) {
               if (this.matchesEntityLivingBase(livingEntity, index2, 0, 0)) {
                  this.gAMELAGEnum = GAMELAGEnum.TIMER;
                  this.count7 = index2;
                  break searchBlock;
               }

               if (!this.settingDynamic.isEnabled()) {
                  break;
               }
            }
         }

         this.gAMELAGEnum = GAMELAGEnum.NOTHING;
      }

      if (this.gAMELAGEnum == GAMELAGEnum.CONNECTION_LAG) {
         if (livingEntity != null) {
            guardBlock:
            if (this.count9 >= this.count8 && event.getIndex() >= 1) {
               this.count9 = 0;

               for (int index3 = (int)this.settingTimerTicks.getValue(); index3 > 0; index3--) {
                  if (this.matchesEntityLivingBase(livingEntity, index3, 0, 0)) {
                     this.gAMELAGEnum = GAMELAGEnum.TIMER;
                     this.count7 = index3;
                     break guardBlock;
                  }

                  if (!this.settingDynamic.isEnabled()) {
                     break;
                  }
               }

               this.gAMELAGEnum = GAMELAGEnum.NOTHING;
               if (this.settingDebug.isEnabled()) {
                  RayTraceUtil.applyString("Abort Timer After Connection Lag");
               }
            } else {
               RayTraceUtil.applyString("LAAG..");
            }

            this.count9 = this.count9 + minecraft.getTimer().indexOffset;
         } else {
            this.gAMELAGEnum = GAMELAGEnum.NOTHING;
            this.count9 = 0;
         }
      }

      if (this.gAMELAGEnum == GAMELAGEnum.TIMER) {
         if (livingEntity != null) {
            int count4 = this.count7;
            event.processAction(count4);
            this.gAMELAGEnum = GAMELAGEnum.GAME_LAG;
            this.count6 = 0;
            if (this.settingDebug.isEnabled()) {
               RayTraceUtil.applyString("TimerSuccessful " + this.count7);
            }
         } else {
            this.gAMELAGEnum = GAMELAGEnum.NOTHING;
         }
      } else if (this.gAMELAGEnum == GAMELAGEnum.GAME_LAG) {
         if (this.count6 <= this.count7) {
            event.processAction(0);
            float factor = 0.0F;
            event.processAction(factor);
            event.setActive(true);
         } else {
            this.gAMELAGEnum = GAMELAGEnum.NOTHING;
            if (this.settingDebug.isEnabled()) {
               RayTraceUtil.applyString("LastTimerStop");
            }
         }
      }

      this.count6 = this.count6 + minecraft.getTimer().indexOffset;
   }

   @Override
   public void onNetworkPacket(EventSendPacket packet) {
   }

   @Override
   public void onPostNetworkPacket(EventSendPacket packet) {
      if (this.gAMELAGEnum == GAMELAGEnum.CONNECTION_LAG) {
         if (packet.getPacket() instanceof C03PacketPlayer) {
            RayTraceUtil.applyString("C03");
         }

         packet.setCancelled(true);
      } else {
         this.count9 = 0;
      }
   }

   private boolean matchesEntityLivingBase(EntityLivingBase livingEntity, int count, int count2, int count3) {
      Vec3 vec;
      Vec3 vec2;
      if (count2 <= 0) {
         vec = new Vec3(livingEntity.coordOffset5, livingEntity.coordOffset20, livingEntity.coordOffset2);
         vec2 = new Vec3(livingEntity.posX, livingEntity.posY, livingEntity.posZ);
      } else {
         double[] coordOffsetArray = CombatUtil.getCoordOffsetArray(count2 - 1, minecraft.thePlayer);
         vec2 = new Vec3(coordOffsetArray[0], coordOffsetArray[1], coordOffsetArray[2]);
         int count4 = count2 - 1;
         double[] coordOffsetArray2 = CombatUtil.getCoordOffsetArray(livingEntity, count4);
         vec = new Vec3(coordOffsetArray2[0], coordOffsetArray2[1], coordOffsetArray2[2]);
      }

      double posX = vec2.xCoord;
      double posY = vec2.yCoord;
      double posZ = vec2.zCoord;
      int indexOffset = livingEntity.indexOffset23;

      for (int index = 0; index < count - 1; index++) {
         posX = vec2.xCoord + (vec.xCoord - vec2.xCoord) / (double)Math.max(1, indexOffset);
         posY = vec2.yCoord + (vec.yCoord - vec2.yCoord) / (double)Math.max(1, indexOffset);
         posZ = vec2.zCoord + (vec.zCoord - vec2.zCoord) / (double)Math.max(1, indexOffset);
         indexOffset--;
      }

      float factor = livingEntity.width / 2.0F;
      float height = livingEntity.getHeight();
      AxisAlignedBB boundingBox = new AxisAlignedBB(
            posX - (double)factor, posY, posZ - (double)factor, posX + (double)factor, posY + (double)livingEntity.height, posZ + (double)factor
         )
         .offset((double)height, (double)height, (double)height);
      Vec3 blockPos = minecraft.thePlayer.getPositionVec8();
      Vec3 blockPos2 = minecraft.thePlayer.getPositionVec12();
      Vec3 vec3 = new Vec3(
         blockPos2.xCoord + (blockPos.xCoord - blockPos2.xCoord) / 2.0,
         blockPos2.yCoord + (blockPos.yCoord - blockPos2.yCoord) / 2.0,
         blockPos2.zCoord + (blockPos.zCoord - blockPos2.zCoord) / 2.0
      );
      if (count2 > 0 || count3 > 0) {
         vec3 = new Vec3(blockPos.xCoord, blockPos.yCoord, blockPos.zCoord);
      }

      double[] coordOffsetArray3 = CombatUtil.getCoordOffsetArray(count - 1, minecraft.thePlayer, livingEntity);
      Vec3 vec4 = new Vec3(coordOffsetArray3[0], coordOffsetArray3[1], coordOffsetArray3[2]);
      Vec3 vec5 = new Vec3(
         vec3.xCoord + (vec4.xCoord - vec3.xCoord) / 3.0,
         vec3.yCoord + (vec4.yCoord - vec3.yCoord) / 3.0,
         vec3.zCoord + (vec4.zCoord - vec3.zCoord) / 3.0
      );
      float factor2 = minecraft.thePlayer.width / 2.0F;
      float height2 = minecraft.thePlayer.getHeight();
      AxisAlignedBB boundingBox2 = new AxisAlignedBB(
            vec5.xCoord - (double)factor2,
            vec5.yCoord,
            vec5.zCoord - (double)factor2,
            vec5.xCoord + (double)factor2,
            vec5.yCoord + (double)minecraft.thePlayer.height,
            vec5.zCoord + (double)factor2
         )
         .offset((double)height2, (double)height2, (double)height2);
      double[] coordOffsetArray4 = CombatUtil.getCoordOffsetArray(livingEntity, count2);
      boolean flag = coordOffsetArray4[9] == 1.0;
      Vec3 vec6 = new Vec3(coordOffsetArray4[0], coordOffsetArray4[1], coordOffsetArray4[2]);
      Vec3 vec7 = new Vec3(coordOffsetArray4[3], coordOffsetArray4[4], coordOffsetArray4[5]);
      float factor3 = 0.91F;
      if (livingEntity.onGround) {
         factor3 = livingEntity.worldObj
               .getBlockState(
                  new BlockPos(
                     MathHelper.ceiling_double_int(vec6.xCoord), MathHelper.ceiling_double_int(vec6.yCoord) - 1, MathHelper.ceiling_double_int(vec6.zCoord)
                  )
               )
               .getBlock()
               .scaleFactor
            * 0.91F;
      }

      double offset = vec6.xCoord - vec7.xCoord;
      double offset2 = vec6.yCoord - vec7.yCoord;
      double offset3 = vec6.zCoord - vec7.zCoord;
      double offset4;
      if (minecraft.theWorld.state
         && (
            !minecraft.theWorld.checkBlockPos12(new BlockPos((int)vec6.xCoord, 0, (int)vec6.zCoord))
               || !minecraft.theWorld.getLoggerEntry(new BlockPos((int)vec6.xCoord, 0, (int)vec6.zCoord)).canExecute2()
         )) {
         if (vec6.yCoord > 0.0) {
            offset4 = -0.1;
         } else {
            offset4 = 0.0;
         }
      } else {
         offset4 = offset2 - 0.08;
      }

      double offset5 = offset4 * 0.98F;
      double offset6 = offset * (double)factor3;
      double offset7 = offset3 * (double)factor3;
      double offset8 = minecraft.thePlayer.posX - livingEntity.posX;

      double offset9;
      for (offset9 = minecraft.thePlayer.posZ - livingEntity.posZ; offset8 * offset8 + offset9 * offset9 < 1.0E-4; offset9 = (Math.random() - Math.random()) * 0.01) {
         offset8 = (Math.random() - Math.random()) * 0.01;
      }

      float factor4 = MathHelper.sqrt_double(offset8 * offset8 + offset9 * offset9);
      float factor5 = 0.4F;
      double offset10 = offset6 / 2.0;
      double offset11 = offset5 / 2.0;
      double offset12 = offset7 / 2.0;
      double offset13 = offset10 - offset8 / (double)factor4 * (double)factor5;
      double offset14 = offset11 + (double)factor5;
      double offset15 = offset12 - offset9 / (double)factor4 * (double)factor5;
      if (offset14 > 0.4F) {
         offset14 = 0.4F;
      }

      Vec3 vec8 = new Vec3(offset13, offset14, offset15);
      Vec3 vec9 = new Vec3(coordOffsetArray3[3], coordOffsetArray3[4] + (double)minecraft.thePlayer.getHeight2(), coordOffsetArray3[5]);
      double doubleItem = MathHelper.getDouble7(vec9.xCoord, boundingBox.minX, boundingBox.maxX);
      double doubleItem2 = MathHelper.getDouble7(vec9.zCoord, boundingBox.minZ, boundingBox.maxZ);
      double offset16 = doubleItem - coordOffsetArray3[3];
      float factor6 = (float)(MathHelper.getDouble6(doubleItem2 - coordOffsetArray3[5], offset16) * 180.0 / Math.PI - 90.0);
      int count5 = 1 + LvlManager.getIndex(minecraft.thePlayer);
      Vec3 vec32 = vec8.getVec312(
         (double)(-MathHelper.abs(factor6 * (float) Math.PI / 180.0F) * (float)count5 * 0.5F),
         0.1,
         (double)(MathHelper.cos(factor6 * (float) Math.PI / 180.0F) * (float)count5 * 0.5F)
      );
      float factor7 = 0.91F;
      if (flag) {
         factor7 = livingEntity.worldObj
               .getBlockState(
                  new BlockPos(
                     MathHelper.ceiling_double_int(vec6.xCoord), MathHelper.ceiling_double_int(vec6.yCoord) - 1, MathHelper.ceiling_double_int(vec6.zCoord)
                  )
               )
               .getBlock()
               .scaleFactor
            * 0.91F;
      }

      float factor8 = 0.16277136F / (factor7 * factor7 * factor7);
      float factor9;
      if (flag) {
         factor9 = livingEntity.getScaleFactor6() * factor8;
      } else {
         factor9 = livingEntity.scaleFactor10;
      }

      float factor10 = 0.0F;
      float factor11 = 0.98F;
      float factor12 = factor10 * factor10 + factor11 * factor11;
      if (factor12 >= 1.0E-4F) {
         float factor13 = MathHelper.sin(factor12);
         if (factor13 < 1.0F) {
            factor13 = 1.0F;
         }

         float factor14 = factor9 / factor13;
         float factor15 = factor10 * factor14;
         float factor16 = factor11 * factor14;
         Vec3 vec10 = new Vec3(vec6.xCoord, vec6.yCoord + (double)livingEntity.getHeight2(), vec6.zCoord);
         new Vec3(
            MathHelper.getDouble7(vec10.xCoord, boundingBox2.minX, boundingBox2.maxX),
            MathHelper.getDouble7(vec10.yCoord, boundingBox2.minY, boundingBox2.maxY),
            MathHelper.getDouble7(vec10.zCoord, boundingBox2.minZ, boundingBox2.maxZ)
         );
         double offset17 = vec32.xCoord * -1.0;
         float factor17 = (float)(MathHelper.getDouble6(vec32.zCoord * -1.0, offset17) * 180.0 / Math.PI - 90.0);
         float factor18 = MathHelper.abs(factor17 * (float) Math.PI / 180.0F);
         float factor19 = MathHelper.cos(factor17 * (float) Math.PI / 180.0F);
         vec32 = vec32.getVec312((double)(factor15 * factor19 - factor16 * factor18), 0.0, (double)(factor16 * factor19 + factor15 * factor18));
      }

      Vec3 vec33 = vec6.getVec36(vec32);
      Vec3 vec11 = new Vec3(vec33.xCoord, vec33.yCoord + (double)livingEntity.getHeight2(), vec33.zCoord);
      Vec3 vec12 = new Vec3(
         MathHelper.getDouble7(vec11.xCoord, boundingBox2.minX, boundingBox2.maxX),
         MathHelper.getDouble7(vec11.yCoord, boundingBox2.minY, boundingBox2.maxY),
         MathHelper.getDouble7(vec11.zCoord, boundingBox2.minZ, boundingBox2.maxZ)
      );
      Vec3 vec13 = new Vec3(coordOffsetArray3[3], coordOffsetArray3[4], coordOffsetArray3[5]);
      Vec3 vec14 = new Vec3(vec13.xCoord, vec13.yCoord + (double)minecraft.thePlayer.getHeight2(), vec13.zCoord);
      Vec3 vec15 = new Vec3(
         MathHelper.getDouble7(vec14.xCoord, boundingBox.minX, boundingBox.maxX),
         MathHelper.getDouble7(vec14.yCoord, boundingBox.minY, boundingBox.maxY),
         MathHelper.getDouble7(vec14.zCoord, boundingBox.minZ, boundingBox.maxZ)
      );
      double offset18 = ((Module)ClientGlobals.moduleManager.killAura).isToggled() && ClientGlobals.moduleManager.killAura.target != null
         ? ClientGlobals.moduleManager.killAura.reachDistance
         : (ClientGlobals.moduleManager.range.checkCondition() ? ClientGlobals.moduleManager.range.getCoordinate() : 3.0);
      if (this.settingDebug.isEnabled()
         && (double)livingEntity.hurtTime <= this.settingMaxTargetHurttime.getValue()
         && vec11.getCoordinate4(vec12) > 3.0
         && vec14.getCoordinate4(vec15) < offset18
         && minecraft.thePlayer.getDistanceToEntity(livingEntity) > 3.0F) {
         RayTraceUtil.applyString(vec11.getCoordinate4(vec12) + "  " + vec14.getCoordinate4(vec15));
      }

      return (double)livingEntity.hurtTime <= this.settingMaxTargetHurttime.getValue()
         && vec11.getCoordinate4(vec12) > 3.0
         && vec14.getCoordinate4(vec15) < offset18
         && minecraft.thePlayer.getDistanceToEntity(livingEntity) > 3.0F;
   }
}
