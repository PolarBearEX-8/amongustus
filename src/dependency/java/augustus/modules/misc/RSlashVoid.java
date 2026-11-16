package augustus.modules.misc;

import augustus.modules.fun.MLG;
import client.ClientGlobals;
import client.event.ClickListener;
import client.event.EventClick;
import client.event.EventMove;
import client.event.MoveListener;
import client.event.TickListener;
import client.module.Category;
import client.module.Module;
import client.rotation.Rotation;
import client.setting.BooleanSetting;
import client.setting.NumberSetting;
import client.setting.RangeSetting;
import client.setting.SettingContainer;
import client.util.BooleanSupplier;
import client.util.MinecraftAccessor;
import client.util.RayTraceUtil;
import client.util.RecordNode12;
import client.util.SimulatedPlayer;
import client.util.TimeHelper;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

public class RSlashVoid extends Module implements TickListener, ClickListener, MoveListener {
   public final NumberSetting settingNumberOfBlocksToReplace;
   public final RangeSetting settingClickDelay;
   public final BooleanSetting settingTeleport;
   public final RangeSetting settingTPDelay;
   public final RangeSetting settingWalkingPointUpdateDelay;
   public final BooleanSetting settingLeftClick;
   public final BooleanSetting settingRightClick;
   public final BooleanSetting settingLookDown;
   public Vec3 vec3;
   public Vec3 vec2;
   public int count6;
   public List list2;
   public final TimeHelper timer;
   public final TimeHelper timer2;
   public final TimeHelper timer3;

   public RSlashVoid() {
        super("RSlashVoid", "RSlashVoid", Color.black, Category.FUN);
      
      this.settingNumberOfBlocksToReplace = new NumberSetting(this, "Number of blocks to replace", 1000.0, 0.0, 5000.0, 0);
      this.settingClickDelay = (new RangeSetting(this, "ClickDelay", 1000.0, 2000.0, 0.0, 4000.0, 0)).getRangeSetting2();
      this.settingTeleport = new BooleanSetting(this, "Teleport", true);
      RangeSetting rangeSetting = (new RangeSetting(this, "TPDelay", 3000.0, 4000.0, 0.0, 10000.0, 0)).getRangeSetting2();
      BooleanSupplier booleanSupplier = this.settingTeleport::isEnabled;
      this.settingTPDelay = rangeSetting.getRangeSetting(15343, booleanSupplier);
      rangeSetting = (new RangeSetting(this, "WalkingPointUpdateDelay", 3000.0, 4000.0, 0.0, 10000.0, 0)).getRangeSetting2();
      BooleanSupplier booleanSupplier2 = this::isActiveAsFlag;
      this.settingWalkingPointUpdateDelay = rangeSetting.getRangeSetting(15343, booleanSupplier2);
      this.settingLeftClick = new BooleanSetting(this, "LeftClick", true);
      this.settingRightClick = new BooleanSetting(this, "RightClick", true);
      this.settingLookDown = new BooleanSetting(this, "LookDown", true);
      this.vec3 = null;
      this.vec2 = null;
      this.count6 = 0;
      this.list2 = new ArrayList();
      this.timer = new TimeHelper();
      this.timer2 = new TimeHelper();
      this.timer3 = new TimeHelper();
   }

   @Override
   public void onPreDisable() {
      if (MinecraftAccessor.minecraft.theWorld != null && MinecraftAccessor.minecraft.thePlayer != null) {
         RayTraceUtil.applyString("How to grief r/place on gomme:");
         RayTraceUtil.applyString("1. Get a coal block into your hotbar");
         RayTraceUtil.applyString("2. Select the coal block");
         RayTraceUtil.applyString("3. Enable autoplay to tab out and chill");
         this.vec3 = MinecraftAccessor.minecraft.thePlayer.getVec32(1.0F);
         this.list2 = resolveList((int)this.vec3.xCoord, (int)this.vec3.zCoord, (int)this.settingNumberOfBlocksToReplace.getValue());
         this.count6 = 0;
      }

      this.vec2 = null;
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void onEnable() {
      this.registerEvent(40);
      this.registerEvent(7);
      this.registerEvent(21);
   }

   @Override
   public void onPreTick() {
      if (!this.isActive()) {
         if (this.settingLookDown.isEnabled()) {
            float factor = 90.0F;
            ClientGlobals.rotationManager.applyModule(factor, -19, this);
         }

         if (this.count6 == 0 || !this.settingTeleport.isEnabled()) {
            TimeHelper timer4 = this.timer;
            long indexOffset = (long)this.settingTPDelay.getIndexOffset2();
            if (timer4.hasReached(indexOffset) || !this.settingTeleport.isEnabled()) {
               this.settingTPDelay.recalculate();
               this.timer.reset();

               for (RecordNode12 recordNode : (Iterable<RecordNode12>)(Iterable)(this.list2)) {
                  if (!MinecraftAccessor.minecraft
                     .theWorld
                     .getIBlockState2((double)recordNode.count, MinecraftAccessor.minecraft.thePlayer.coordOffset9 - 1.0, (double)recordNode.count2)
                     .getBlock()
                     .equals(Blocks.block9)) {
                     double offset = (double)recordNode.count + 0.5;
                     double offset2 = (double)recordNode.count2 + 0.5;
                     if (this.settingTeleport.isEnabled()) {
                        double doubleItem = MathHelper.getDouble7(MinecraftAccessor.minecraft.thePlayer.posX - offset, -5.0, 5.0);
                        double doubleItem2 = MathHelper.getDouble7(MinecraftAccessor.minecraft.thePlayer.posZ - offset2, -5.0, 5.0);
                        MinecraftAccessor.minecraft
                           .thePlayer
                           .processDouble2(
                              MinecraftAccessor.minecraft.thePlayer.posX - doubleItem, this.vec3.yCoord + 0.2, MinecraftAccessor.minecraft.thePlayer.posZ - doubleItem2
                           );
                     } else {
                        timer4 = this.timer2;
                        long indexOffset2 = (long)this.settingWalkingPointUpdateDelay.getIndexOffset2();
                        if (timer4.hasReached(indexOffset2)) {
                           this.vec2 = new Vec3(offset, MinecraftAccessor.minecraft.thePlayer.posY, offset2);
                           this.timer2.reset();
                           this.settingWalkingPointUpdateDelay.recalculate();
                        }

                        double posX = this.vec2.xCoord;
                        double offset3 = this.vec2.yCoord + (double)MinecraftAccessor.minecraft.thePlayer.getHeight2();
                        double posZ = this.vec2.zCoord;
                        Rotation rotation = ClientGlobals.rotationManager.getRotation3();
                        float factor2 = 30.0F;
                        float factor3 = 20.0F;
                        float floatItem = SettingContainer.settingProvider.getFloat2(factor3, factor2);
                        float factor4 = 15.0F;
                        float factor5 = 10.0F;
                        float floatItem2 = SettingContainer.settingProvider.getFloat2(factor5, factor4);
                        float factor6 = floatItem;
                        Rotation rotation2 = SimulatedPlayer.getRotation2(posX, offset3, posZ, rotation, factor6, floatItem2, false);
                        Rotation rotation3 = new Rotation(
                           rotation2.yaw,
                           this.settingLookDown.isEnabled() && MinecraftAccessor.minecraft.thePlayer.getCoordinate(this.vec2) < 3.0 ? 90.0F : rotation2.pitch
                        );
                        if (Math.abs(rotation3.yaw - ClientGlobals.rotationManager.getRotation3().yaw) > 2.0F
                           || Math.abs(rotation3.pitch - ClientGlobals.rotationManager.getRotation3().pitch) > 2.0F) {
                           float factor7 = 1.0F;
                           float factor8 = -1.0F;
                           rotation3.yaw = rotation3.yaw + SettingContainer.settingProvider.getFloat2(factor8, factor7);
                           float factor9 = 1.0F;
                           float factor10 = -1.0F;
                           rotation3.pitch = rotation3.pitch + SettingContainer.settingProvider.getFloat2(factor10, factor9);
                        }

                        float factor11 = 20.0F;
                        float factor12 = 10.0F;
                        Rotation rotation4 = new Rotation(
                           rotation3.yaw,
                           SimulatedPlayer.getFloat5(
                              ClientGlobals.rotationManager.getRotation3().pitch, rotation3.pitch, SettingContainer.settingProvider.getFloat2(factor12, factor11)
                           )
                        );
                        Rotation rotation5 = ClientGlobals.rotationManager.getRotation3();
                        Rotation rotation6 = SimulatedPlayer.getRotation5(rotation4, rotation5);
                        byte byteValue = -20;
                        ClientGlobals.rotationManager.applyRotation2(rotation6, byteValue, 1898960900, this);
                     }

                     this.count6 = 1;
                     break;
                  }
               }
            }
         }
      }
   }

   @Override
   public void onClick(EventClick event) {
      event.cancel();
      if (!this.isActive()) {
         if (this.count6 == 1 && this.settingTeleport.isEnabled()) {
            this.count6 = 2;
         } else {
            TimeHelper timer4 = this.timer3;
            long indexOffset = (long)this.settingClickDelay.getIndexOffset2();
            if (timer4.hasReached(indexOffset)) {
               BlockPos blockPos = new BlockPos(MinecraftAccessor.minecraft.thePlayer.posX, this.vec3.yCoord - 0.7, MinecraftAccessor.minecraft.thePlayer.posZ);
               if (this.count6 < 2 && this.settingTeleport.isEnabled()
                  || MinecraftAccessor.minecraft.theWorld.getBlockState(blockPos).getBlock().equals(Blocks.block9)
                  || this.settingLookDown.isEnabled() && !(ClientGlobals.rotationManager.getRotation3().pitch > 80.0F)) {
                  this.count6++;
                  if (this.count6 > 4) {
                     this.count6 = 0;
                  }
               } else {
                  this.settingClickDelay.recalculate();
                  this.timer3.reset();
                  if (this.settingLeftClick.isEnabled()) {
                     MinecraftAccessor.minecraft.thePlayer.respawnPlayer();
                     if (MinecraftAccessor.minecraft.theWorld.getBlockState(blockPos).getBlock().getMaterial() != Material.coreItemEntry) {
                        MinecraftAccessor.minecraft.playerController.onPlayerDamageBlock(blockPos, EnumFacing.UP);
                     }
                  }

                  if (this.settingRightClick.isEnabled()) {
                     if (MinecraftAccessor.minecraft.theWorld.getBlockState(blockPos).getBlock().getMaterial() == Material.coreItemEntry) {
                        blockPos = blockPos.down();
                     }

                     if (MinecraftAccessor.minecraft
                        .playerController
                        .onPlayerRightClick(
                           MinecraftAccessor.minecraft.thePlayer,
                           MinecraftAccessor.minecraft.theWorld,
                           MinecraftAccessor.minecraft.thePlayer.getHeldItem(),
                           blockPos,
                           EnumFacing.UP,
                           new Vec3((double)blockPos.getX() + 0.5, (double)blockPos.getY() + 0.5, (double)blockPos.getZ() + 0.5)
                        )) {
                        MinecraftAccessor.minecraft.thePlayer.respawnPlayer();
                     }
                  }

                  this.count6 = 0;
               }
            }
         }
      }
   }

   @Override
   public void onMove(EventMove event) {
      if (!this.isActive() && !this.settingTeleport.isEnabled() && this.vec2 != null) {
         double doubleItem = MinecraftAccessor.minecraft.thePlayer.getDouble4(this.vec2.xCoord, this.vec2.yCoord, this.vec2.zCoord);
         if (doubleItem > 0.2) {
            float factor = 1.0F;
            event.setScaleFactor7(factor);
            if (doubleItem > 6.0) {
               event.setState(true);
            }

            if (doubleItem < 1.5) {
               event.setState4(true);
            }
         } else {
            event.setState4(true);
         }
      }
   }

   private boolean isActive() {
      return this.vec3 == null
         || this.list2 == null
         || MinecraftAccessor.minecraft.currentScreen != null
         || MinecraftAccessor.minecraft.thePlayer.getHeldItem() == null
         || !MinecraftAccessor.minecraft.thePlayer.getHeldItem().getItem().equals(Item.processAction(173));
   }

   public static List resolveList(int count, int count2, int count3) {
      ArrayList list = new ArrayList();
      int count4 = count;
      int count5 = count2;
      int count7 = 1;
      list.add(new RecordNode12(count, count2));

      for (int index = 0; index < count3; index++) {
         for (int index2 = 0; index2 < count7 && index < count3 - 1; index2++) {
            list.add(new RecordNode12(count4, ++count5));
            index++;
         }

         for (int index3 = 0; index3 < count7 && index < count3 - 1; index3++) {
            list.add(new RecordNode12(++count4, count5));
            index++;
         }

         count7++;

         for (int index4 = 0; index4 < count7 && index < count3 - 1; index4++) {
            list.add(new RecordNode12(count4, --count5));
            index++;
         }

         for (int index5 = 0; index5 < count7 && index < count3 - 1; index5++) {
            list.add(new RecordNode12(--count4, count5));
            index++;
         }

         count7++;
      }

      return list;
   }

   private boolean isActiveAsFlag() {
      return !this.settingTeleport.isEnabled();
   }
}
