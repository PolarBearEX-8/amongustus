package augustus.modules.misc;

import client.Client;
import client.ClientGlobals;
import client.event.ClickListener;
import client.event.EventClick;
import client.event.EventMove;
import client.event.EventMoveController;
import client.event.EventSprint;
import client.event.MoveListener;
import client.event.SprintListener;
import client.event.TickListener;
import client.module.Category;
import client.module.Module;
import client.rotation.Rotation;
import client.rotation.RotationManager;
import client.setting.ActionSetting;
import client.setting.ArrowDodgerPhase;
import client.setting.BooleanSetting;
import client.setting.NumberSetting;
import client.setting.SettingContainer;
import client.setting.SettingGroup;
import client.setting.SettingProvider;
import client.util.BooleanSupplier;
import client.util.CombatRayTrace;
import client.util.CombatUtil;
import client.util.MoveUtil;
import client.util.MovingObjectType;
import client.util.PlaceTarget;
import client.util.RayTraceHit;
import client.util.RayTraceUtil;
import client.util.SimulatedPlayer;
import client.util.SmoothDamper;
import client.util.TimeHelper;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import net.minecraft.block.BlockCache;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;

public class FailedScaffold extends Module implements TickListener, MoveListener, ClickListener, SprintListener {
   public final SettingGroup settingRotations;
   public final ActionSetting actionSetting;
   public final ActionSetting actionSetting2;
   public final BooleanSetting settingStrafe;
   public final NumberSetting settingPredictTicks;
   public final SettingGroup settingMovement;
   public final SettingGroup settingSneaking;
   public final BooleanSetting settingSneak;
   public final BooleanSetting settingRemoveSneakStop;
   public final BooleanSetting settingOneStackForward;
   public final ActionSetting actionSetting3;
   public final ActionSetting actionSetting4;
   public final BooleanSetting settingOneStackDiagonal;
   public final ActionSetting actionSetting5;
   public final ActionSetting actionSetting6;
   public final ActionSetting actionSetting7;
   public final ActionSetting actionSetting8;
   public final ActionSetting actionSetting9;
   public final ActionSetting actionSetting10;
   public final NumberSetting settingSneakSlowDown;
   public final BooleanSetting settingSameY;
   public final BooleanSetting settingAutoJump;
   public final BooleanSetting settingTellyBridge;
   public final SimulatedPlayer simulatedPlayer;
   public final TimeHelper timer;
   public int count6;
   public BlockPos blockPos;
   public EventMove eventMove;
   public ArrayList arrayList;
   public float factor;
   public int count7;
   public int count8;
   public int count9;
   public boolean enabled3;
   public float factor2;
   public final SmoothDamper smoothDamper;
   public final SmoothDamper smoothDamper2;

   public FailedScaffold() {
        super("FailedScaffold", "NeuGerüst", new Color(182, 194, 207), Category.WORLD);
      short shortValue = 8329;
      char character = '\ufa0b';
      short shortValue2 = 10427;
      
      this.settingRotations = new SettingGroup(this, "Rotations");
      this.actionSetting = new ActionSetting(this, "YawSpeed", 30.0, 50.0, 0.0, 180.0, 0, this.settingRotations);
      this.actionSetting2 = new ActionSetting(this, "PitchSpeed", 30.0, 50.0, 0.0, 180.0, 0, this.settingRotations);
      this.settingStrafe = new BooleanSetting(this, "Strafe", false, this.settingRotations);
      this.settingPredictTicks = new NumberSetting(this, "PredictTicks", 0.0, 0.0, 2.0, 0, this.settingRotations);
      this.settingMovement = new SettingGroup(this, "Movement");
      this.settingSneaking = new SettingGroup(this, "Sneaking", this.settingMovement);
      this.settingSneak = new BooleanSetting(this, "Sneak", false, this.settingSneaking);
      BooleanSetting setting = new BooleanSetting(this, "RemoveSneakStop", false, this.settingSneaking);
      BooleanSupplier booleanSupplier = this.settingSneak::isEnabled;
      this.settingRemoveSneakStop = (BooleanSetting)setting.getSetting(booleanSupplier);
      setting = new BooleanSetting(this, "OneStackForward", false, this.settingSneaking);
      BooleanSupplier booleanSupplier2 = this.settingSneak::isEnabled;
      this.settingOneStackForward = (BooleanSetting)setting.getSetting(booleanSupplier2);
      ActionSetting setting2 = new ActionSetting(this, "MinOffGroundSneakTickForward", 1.0, 2.0, 0.0, 12.0, 0, this.settingSneaking);
      BooleanSupplier booleanSupplier3 = this::isActive;
      this.actionSetting3 = setting2.getActionSetting(booleanSupplier3);
      ActionSetting setting3 = new ActionSetting(this, "MaxOffGroundSneakTickForward", 10.0, 11.0, 0.0, 12.0, 0, this.settingSneaking);
      BooleanSupplier booleanSupplier4 = this::isActive3;
      this.actionSetting4 = setting3.getActionSetting(booleanSupplier4);
      setting = new BooleanSetting(this, "OneStackDiagonal", false, this.settingSneaking);
      BooleanSupplier booleanSupplier5 = this.settingSneak::isEnabled;
      this.settingOneStackDiagonal = (BooleanSetting)setting.getSetting(booleanSupplier5);
      ActionSetting setting4 = new ActionSetting(this, "MinOffGroundSneakTickDiagonal", 1.0, 2.0, 0.0, 12.0, 0, this.settingSneaking);
      BooleanSupplier booleanSupplier6 = this::isActive2;
      this.actionSetting5 = setting4.getActionSetting(booleanSupplier6);
      ActionSetting setting5 = new ActionSetting(this, "MaxOffGroundSneakTickDiagonal", 10.0, 11.0, 0.0, 12.0, 0, this.settingSneaking);
      BooleanSupplier booleanSupplier7 = this::isActiveAsFlag;
      this.actionSetting6 = setting5.getActionSetting(booleanSupplier7);
      ActionSetting setting6 = new ActionSetting(this, "SneakTicks", 1.0, 2.0, 0.0, 10.0, 0, this.settingSneaking);
      BooleanSupplier booleanSupplier8 = this.settingSneak::isEnabled;
      this.actionSetting7 = setting6.getActionSetting(booleanSupplier8);
      ActionSetting setting7 = new ActionSetting(this, "StartSneakDistToBlock", 0.05, 0.2, -0.2, 0.5, 2, this.settingSneaking);
      BooleanSupplier booleanSupplier9 = this.settingSneak::isEnabled;
      this.actionSetting8 = setting7.getActionSetting(booleanSupplier9);
      ActionSetting setting8 = new ActionSetting(this, "SneakBlocksForward", 1.0, 2.0, 0.0, 15.0, 0, this.settingSneaking);
      BooleanSupplier booleanSupplier10 = this.settingSneak::isEnabled;
      this.actionSetting9 = setting8.getActionSetting(booleanSupplier10);
      ActionSetting setting9 = new ActionSetting(this, "SneakBlocksDiagonal", 1.0, 2.0, 0.0, 15.0, 0, this.settingSneaking);
      BooleanSupplier booleanSupplier11 = this.settingSneak::isEnabled;
      this.actionSetting10 = setting9.getActionSetting(booleanSupplier11);
      NumberSetting setting10 = new NumberSetting(this, "SneakSlowDown", 0.3, 0.3, 1.0, 2, this.settingSneaking);
      BooleanSupplier booleanSupplier12 = this.settingSneak::isEnabled;
      this.settingSneakSlowDown = setting10.getNumberSetting(booleanSupplier12);
      this.settingSameY = new BooleanSetting(this, "SameY", false);
      setting = new BooleanSetting(this, "AutoJump", false);
      BooleanSupplier booleanSupplier13 = this.settingSameY::isEnabled;
      this.settingAutoJump = (BooleanSetting)setting.getSetting(booleanSupplier13);
      setting = new BooleanSetting(this, "TellyBridge", false);
      BooleanSupplier booleanSupplier14 = this.settingSameY::isEnabled;
      this.settingTellyBridge = (BooleanSetting)setting.getSetting(booleanSupplier14);
      this.simulatedPlayer = new SimulatedPlayer();
      this.timer = new TimeHelper();
      this.count6 = 1;
      this.blockPos = null;
      this.eventMove = null;
      this.arrayList = new ArrayList();
      this.factor = 45.0F;
      this.count7 = 0;
      this.count8 = 0;
      this.count9 = 0;
      this.enabled3 = false;
      this.factor2 = 0.0F;
      this.smoothDamper = new SmoothDamper(20, 0.0);
      this.smoothDamper2 = new SmoothDamper(20, 0.0);
   }

   @Override
   public void onPreDisable() {
      this.timer.reset();
      this.arrayList.clear();
      this.count6 = 0;
   }

   @Override
   public void onDisable() {
      this.eventMove = null;
   }

   @Override
   public void onEnable() {
      this.registerEvent(40);
      this.registerEvent(21);
      this.registerEvent(7);
      this.registerEvent(33);
   }

   @Override
   public void onPreTick() {
      if (!this.arrayList.isEmpty()) {
         this.arrayList.remove(0);
      }

      this.count7 = minecraft.thePlayer.onGround ? 0 : this.count7 + 1;
      if (this.eventMove != null) {
         PlaceTarget placeTarget = this.getPlaceTarget();
         this.blockPos = placeTarget == null ? null : placeTarget.getBlockPos();
         float factor3 = this.getFactor2();
         boolean flag = !this.enabled3;
         System.out.println(minecraft.thePlayer.posX + " " + minecraft.thePlayer.posZ);
         if (flag) {
            this.count6 = (int)this.settingPredictTicks.getValue();
            double[] doubleArray = CombatUtil.getDoubleArray();
            ArrayList list = new ArrayList();
            EventMoveController eventMoveController = this.getEventMoveController();
            EventMoveController event = this.onMoveController2(eventMoveController);
            list.add(event);
            double[] offsetArray = new double[]{minecraft.thePlayer.posX, minecraft.thePlayer.posY, minecraft.thePlayer.posZ};

            for (int index = 0; index < this.count6; index++) {
               EntityPlayerSP player = minecraft.thePlayer;
               double posX = minecraft.thePlayer.posX;
               double posY = minecraft.thePlayer.posY;
               double posZ = minecraft.thePlayer.posZ;
               float factor4 = event.getFactor();
               EventMove eventMove2 = event.getEventMove();
               float factor5 = factor4;
               double offset = posZ;
               double offset2 = posY;
               double offset3 = posX;
               offsetArray = CombatUtil.onMove(0, player, offset3, offset2, offset, factor5, eventMove2);
               System.out.println("Last: " + offsetArray[0] + " " + offsetArray[2] + "  " + event.getFactor() + " " + event.getEventMove().isState4());
               CombatUtil.applyDoubleArray(offsetArray);
               if (index < this.count6 - 1) {
                  EventMoveController event2 = (EventMoveController)list.get(list.size() - 1);
                  event = this.onMoveController2(event2);
                  list.add(event);
               }
            }

            CombatUtil.applyDoubleArray(doubleArray);
            float factor6 = event.getFactor();
            Vec3 vec = new Vec3(offsetArray[0], offsetArray[1], offsetArray[2]);
            System.out.println(vec.xCoord + "           " + vec.zCoord);
            ArrayList list2 = new ArrayList();
            float factor7 = 0.0F;

            for (float factor8 = 15.0F; factor8 < 85.0F; factor8 += 0.05F) {
               float floatItem = SimulatedPlayer.getFloat3(factor8, ClientGlobals.rotationManager.getRotation3().pitch);
               if (floatItem != factor7) {
                  MovingObjectPosition hitResult = CombatRayTrace.resolveMovingObjectPosition(1.0F, vec, factor6, floatItem);
                  if (hitResult.typeOfHit == MovingObjectType.BLOCK
                     && BlockCache.checkBlockPos(hitResult.getBlockPos())
                     && hitResult.sideHit != EnumFacing.DOWN
                     && hitResult.sideHit != EnumFacing.UP
                     && (double)hitResult.getBlockPos().getY() < minecraft.thePlayer.posY) {
                     list2.add(new RayTraceHit(hitResult, floatItem));
                  }
               }

               factor7 = floatItem;
            }

            if (!list2.isEmpty()) {
               EventMoveController event3 = (EventMoveController)list.get(0);
               float factor9 = event3.getFactor();
               RayTraceHit rayTraceHit = this.resolveRayTraceHit(list2, factor9, vec);
               RotationManager rotationManager = ClientGlobals.rotationManager;
               float[] factorArray = new float[]{event3.getFactor(), rayTraceHit.scaleFactor};
               rotationManager.applyFloatArray(factorArray, -1, this);
               this.arrayList = list;
               this.count9 = this.count6;
               this.enabled3 = true;
               this.onMoveController(event);
               RayTraceUtil.applyString("thiusdlfkjs" + minecraft.thePlayer.ticksExisted);
               return;
            }
         }

         this.enabled3 = this.count9 > 0;
         this.count9--;
         if (this.arrayList.isEmpty()) {
            EventMoveController eventMoveController2 = this.getEventMoveController();
            EventMoveController event4 = this.onMoveController2(eventMoveController2);
            this.arrayList.add(event4);
            this.onMoveController(event4);
            RotationManager rotationManager2 = ClientGlobals.rotationManager;
            Rotation rotation = new Rotation(event4.getFactor(), factor3);
            byte byteValue = -1;
            Rotation rotation2 = rotation;
            rotationManager2.applyRotation2(rotation2, byteValue, 1898960900, this);
         } else {
            RotationManager rotationManager3 = ClientGlobals.rotationManager;
            Rotation rotation3 = new Rotation(((EventMoveController)this.arrayList.get(0)).getFactor(), factor3);
            byte byteValue2 = -1;
            Rotation rotation4 = rotation3;
            rotationManager3.applyRotation2(rotation4, byteValue2, 1898960900, this);
         }
      }
   }

   @Override
   public void onMove(EventMove event) {
      if (!this.arrayList.isEmpty()) {
         EventMove eventMove2 = ((EventMoveController)this.arrayList.get(0)).getEventMove();
         event.onMove(eventMove2);
      }

      this.eventMove = new EventMove(event);
   }

   @Override
   public void onClick(EventClick event) {
      SmoothDamper smoothDamper3 = this.smoothDamper;
      int intItem = SettingContainer.settingProvider.getInt2(11, 15);
      int intItem2 = smoothDamper3.getInt(intItem);
      if (intItem2 > 0) {
         SmoothDamper smoothDamper4 = this.smoothDamper2;
         int intItem3 = SettingContainer.settingProvider.getInt2(4, 8);
         intItem2 += smoothDamper4.getInt(intItem3);
      }

      if (intItem2 < 1 && minecraft.objectMouseOver != null) {
         ItemStack stack = minecraft.thePlayer.getHeldItem();
         BlockPos blockPos2 = minecraft.objectMouseOver.getBlockPos();
         Vec3 hitVec = minecraft.objectMouseOver.hitVec;
         EnumFacing sideHit = minecraft.objectMouseOver.sideHit;
         BlockPos blockPos3 = blockPos2;
         if (CombatUtil.checkItemStack(stack, blockPos3, sideHit, hitVec)) {
            intItem2 = 1;
         }
      }

      event.updateInt2(intItem2);
      event.setState3(true);
      event.setState1(false);
   }

   @Override
   public void onSprint(EventSprint event) {
      if (this.settingSneak.isEnabled() && this.settingRemoveSneakStop.isEnabled()) {
         event.setActive2(true);
      }
   }

   private EventMoveController getEventMoveController() {
      float factor3 = 0.0F;
      float factor4 = 0.0F;
      if (minecraft.gameSettings.keyBindForward.isKeyDown()) {
         factor3++;
      }

      if (minecraft.gameSettings.keyBindBack.isKeyDown()) {
         factor3--;
      }

      if (minecraft.gameSettings.keyBindLeft.isKeyDown()) {
         factor4++;
      }

      if (minecraft.gameSettings.keyBindRight.isKeyDown()) {
         factor4--;
      }

      boolean keyDown = minecraft.gameSettings.keyBindSneak.isKeyDown();
      boolean keyDown2 = minecraft.gameSettings.keyBindJump.isKeyDown();
      float factor5 = MathHelper.sqrt_float(Client.getClient().getViaProtocolHelper().scaleFactor1 - ClientGlobals.rotationManager.getRotation3().yaw);
      float factor6 = 0.0F;
      float factor7 = 0.0F;
      float factor8;
      float factor9;
      if ((double)factor5 >= 22.5 && (double)factor5 < 67.5) {
         float factor10 = factor7 + factor4;
         float factor11 = factor6 + factor3;
         factor9 = factor10 - factor3;
         factor8 = factor11 + factor4;
      } else if ((double)factor5 >= 67.5 && (double)factor5 < 112.5) {
         factor9 = factor7 - factor3;
         factor8 = factor6 + factor4;
      } else if ((double)factor5 >= 112.5 && (double)factor5 < 157.5) {
         float factor12 = factor7 - factor4;
         float factor13 = factor6 - factor3;
         factor9 = factor12 - factor3;
         factor8 = factor13 + factor4;
      } else if ((double)factor5 >= 157.5 || (double)factor5 <= -157.5) {
         factor9 = factor7 - factor4;
         factor8 = factor6 - factor3;
      } else if ((double)factor5 > -157.5 && (double)factor5 <= -112.5) {
         float factor14 = factor7 - factor4;
         float factor15 = factor6 - factor3;
         factor9 = factor14 + factor3;
         factor8 = factor15 - factor4;
      } else if ((double)factor5 > -112.5 && (double)factor5 <= -67.5) {
         factor9 = factor7 + factor3;
         factor8 = factor6 - factor4;
      } else if ((double)factor5 > -67.5 && (double)factor5 <= -22.5) {
         float factor16 = factor7 + factor4;
         float factor17 = factor6 + factor3;
         factor9 = factor16 + factor3;
         factor8 = factor17 - factor4;
      } else {
         factor9 = factor7 + factor4;
         factor8 = factor6 + factor3;
      }

      float factor18;
      float factor19;
      if (keyDown) {
         factor18 = (float)MathHelper.getDouble7((double)factor8, -0.3, 0.3);
         factor19 = (float)MathHelper.getDouble7((double)factor9, -0.3, 0.3);
      } else {
         factor18 = (float)MathHelper.getDouble7((double)factor8, -1.0, 1.0);
         factor19 = (float)MathHelper.getDouble7((double)factor9, -1.0, 1.0);
      }

      this.factor2 = factor18;
      return new EventMoveController(
         !this.enabled3 ? new EventMove(factor19, factor18, keyDown2, keyDown, ArrowDodgerPhase.PRE) : new EventMove(this.eventMove),
         ClientGlobals.rotationManager.getRotation3().yaw,
         this.factor,
         this.blockPos,
         this.count7
      );
   }

   private void onMoveController(EventMoveController event) {
      this.factor = event.getFactor2();
   }

   private EventMoveController onMoveController2(EventMoveController event) {
      PlaceTarget placeTarget = this.getPlaceTarget();
      BlockPos blockPos2 = placeTarget == null ? null : placeTarget.getBlockPos();
      float factor3 = this.getFactor();
      boolean flag = this.matchesBlockPos(factor3, blockPos2);
      float factor4 = event.getFactor2();
      if (flag) {
         factor4 = this.computeFactor(factor3, factor4, blockPos2);
         factor3 += factor4;
      }

      SimulatedPlayer simulatedPlayer2 = this.simulatedPlayer;
      SettingProvider settingProvider = SettingContainer.settingProvider;
      double doubleItem = this.actionSetting.getDouble3();
      double doubleItem2 = this.actionSetting.getDouble6();
      double offset = doubleItem;
      float floatItem = settingProvider.getFloat3(offset, doubleItem2);
      float factor5 = event.getFactor();
      float factor6 = floatItem;
      float floatItem2 = simulatedPlayer2.getFloat(factor6, factor5, factor3);
      EventMoveController event2 = this.onMoveControllerByEventMoveControl(event);
      return new EventMoveController(event2.getEventMove(), floatItem2, factor4, blockPos2, event2.getCount());
   }

   private EventMoveController onMoveControllerByEventMoveControl(EventMoveController event) {
      EventMove eventMove2 = event.getEventMove();
      if (this.settingSneak.isEnabled()) {
         double value = this.settingSneakSlowDown.getValue();
         eventMove2.setCoordOffset6(value);
         double value2 = this.settingSneakSlowDown.getValue();
         eventMove2.setCoordOffset(value2);
      }

      int count = minecraft.thePlayer.onGround ? 0 : event.getCount() + 1;
      event.setCount(count);
      event.setEventMove(eventMove2);
      return event;
   }

   private float getFactor2() {
      float pitch = ClientGlobals.rotationManager.getRotation3().pitch;
      if (!this.timer.hasReached(200L)) {
         SimulatedPlayer simulatedPlayer2 = this.simulatedPlayer;
         float factor3 = 24.0F;
         float factor4 = 18.0F;
         float floatItem = SettingContainer.settingProvider.getFloat2(factor4, factor3);
         float factor5 = 80.34F;
         float pitch2 = ClientGlobals.rotationManager.getRotation3().pitch;
         float factor6 = floatItem;
         pitch = simulatedPlayer2.getFloat11(factor6, pitch2, factor5);
      }

      return SimulatedPlayer.getFloat3(pitch, ClientGlobals.rotationManager.getRotation3().pitch);
   }

   private RayTraceHit resolveRayTraceHit(List list, float factor3, Vec3 vec) {
      list.sort(Comparator.comparingDouble(hit -> this.computeValue(factor3, vec, (RayTraceHit)hit)));
      return (RayTraceHit)list.get(0);
   }

   private double computeValue(RayTraceHit rayTraceHit) {
      return (double)Math.abs(rayTraceHit.scaleFactor - ClientGlobals.rotationManager.getRotation3().pitch);
   }

   private double computeValue(RayTraceHit rayTraceHit, float factor3, Vec3 vec) {
      MovingObjectPosition hitResult = CombatRayTrace.resolveMovingObjectPosition(1.0F, vec, factor3, rayTraceHit.scaleFactor);
      if (hitResult != null && hitResult.getBlockPos() != null && hitResult.hitVec != null) {
         double offset = hitResult.hitVec.yCoord % 1.0;
         return Math.abs(0.5 - offset);
      } else {
         return 100.0;
      }
   }

   private float getFactor() {
      return Client.getClient().getViaProtocolHelper().scaleFactor1 - 180.0F;
   }

   private float getFactorAsFloat() {
      return !this.settingAutoJump.isEnabled()
            || !this.settingSameY.isEnabled()
            || !this.settingTellyBridge.isEnabled()
            || minecraft.thePlayer.motionY < 0.0 && !minecraft.thePlayer.onGround && MoveUtil.canExecute2()
         ? 0.0F
         : -180.0F;
   }

   private PlaceTarget getPlaceTarget() {
      if (Math.abs(minecraft.thePlayer.motionZ) < 1.0E-6 && Math.abs(minecraft.thePlayer.motionX) < 1.0E-6) {
         for (int index = 1; index < 4; index++) {
            BlockPos blockPos2 = new BlockPos(minecraft.thePlayer.posX, minecraft.thePlayer.posY - (double)index, minecraft.thePlayer.posZ);
            if (BlockCache.checkBlockPos(blockPos2)) {
               return new PlaceTarget(blockPos2, EnumFacing.UP, new Vec3((double)blockPos2.getX() + 0.5, (double)(blockPos2.getY() + 1), (double)blockPos2.getZ() + 0.5));
            }
         }
      }

      float factorAsFloat = this.getFactorAsFloat();
      Vec3 vec3 = minecraft.thePlayer
         .getVec34(Client.getClient().getViaProtocolHelper().scaleFactor2, Client.getClient().getViaProtocolHelper().scaleFactor1 - 180.0F + factorAsFloat);
      double offset = (minecraft.thePlayer.prevPosX - minecraft.thePlayer.posX == 0.0 ? vec3.xCoord : minecraft.thePlayer.prevPosX - minecraft.thePlayer.posX) * 2.0;
      double offset2 = (minecraft.thePlayer.prevPosZ - minecraft.thePlayer.posZ == 0.0 ? vec3.zCoord : minecraft.thePlayer.prevPosZ - minecraft.thePlayer.posZ) * 2.0;
      ArrayList list = new ArrayList();
      ArrayList list2 = new ArrayList();
      float factor3 = this.settingSameY.isEnabled() ? (float)(minecraft.thePlayer.posY - minecraft.thePlayer.coordOffset9) : 0.0F;
      float factor4 = this.settingSameY.isEnabled() ? 4.5F : 1.0F;

      for (float factor5 = 0.5F + factor3; factor5 < 3.0F + factor3; factor5++) {
         double offset3 = minecraft.thePlayer.posX + offset;
         double offset4 = minecraft.thePlayer.posY - (double)factor5;
         double offset5 = minecraft.thePlayer.posZ + offset2;
         double offset6 = offset3 - minecraft.thePlayer.posX;
         double offset7 = offset5 - minecraft.thePlayer.posZ;
         float factor6 = (float)(MathHelper.getDouble6(offset7, offset6) * 180.0 / Math.PI - 90.0);
         float factor7 = (float)(-(MathHelper.getDouble6(0.0, (double)MathHelper.sqrt_double(offset6 * offset6 + offset7 * offset7)) * 180.0 / Math.PI));
         float floatItem = SimulatedPlayer.getFloat9(ClientGlobals.rotationManager.getRotation3().yaw, factor6, 180.0F);
         float floatItem2 = SimulatedPlayer.getFloat9(ClientGlobals.rotationManager.getRotation3().pitch, factor7, 180.0F);
         Vec3 vec32 = minecraft.thePlayer.getVec34(floatItem2, floatItem);
         MovingObjectPosition movingObjectPosition = minecraft.theWorld
            .getMovingObjectPosition4(
               offset3,
               offset4,
               offset5,
               offset3 + vec32.xCoord * (double)factor4,
               offset4 + vec32.yCoord * (double)factor4,
               offset5 + vec32.zCoord * (double)factor4,
               false,
               false,
               true
            );
         if (movingObjectPosition != null
            && movingObjectPosition.typeOfHit == MovingObjectType.BLOCK
            && movingObjectPosition.getBlockPos() != null
            && BlockCache.checkBlockPos(movingObjectPosition.getBlockPos())
            && !list2.contains(movingObjectPosition.getBlockPos())
            && !BlockCache.checkBlockPos2(movingObjectPosition.getBlockPos().up())) {
            list.add(new PlaceTarget(movingObjectPosition.getBlockPos(), movingObjectPosition.sideHit, movingObjectPosition.hitVec));
            list2.add(movingObjectPosition.getBlockPos());
         }
      }

      list.sort(Comparator.comparingDouble(target -> FailedScaffold.computeValue((PlaceTarget)target)));
      if (!list.isEmpty()) {
         return (PlaceTarget)list.get(0);
      } else {
         for (float factor8 = 0.5F + factor3; factor8 < 4.0F + factor3; factor8++) {
            double offset8 = minecraft.thePlayer.posX + offset;
            double offset9 = minecraft.thePlayer.posY - (double)factor8;
            double offset10 = minecraft.thePlayer.posZ + offset2;
            double offset11 = offset8 - minecraft.thePlayer.posX;
            double offset12 = offset10 - minecraft.thePlayer.posZ;
            float factor9 = (float)(MathHelper.getDouble6(offset12, offset11) * 180.0 / Math.PI - 90.0);
            float factor10 = (float)(-(MathHelper.getDouble6(0.0, (double)MathHelper.sqrt_double(offset11 * offset11 + offset12 * offset12)) * 180.0 / Math.PI));
            float floatItem3 = SimulatedPlayer.getFloat9(ClientGlobals.rotationManager.getRotation3().yaw, factor9, 180.0F);
            float floatItem4 = SimulatedPlayer.getFloat9(ClientGlobals.rotationManager.getRotation3().pitch, factor10, 180.0F);

            for (float factor11 = 0.0F; factor11 <= 360.0F; factor11++) {
               Vec3 vec33 = minecraft.thePlayer.getVec34(floatItem4, floatItem3 + factor11);
               MovingObjectPosition movingObjectPosition2 = minecraft.theWorld
                  .getMovingObjectPosition4(
                     offset8, offset9, offset10, offset8 + vec33.xCoord * 4.5, offset9 + vec33.yCoord * 4.5, offset10 + vec33.zCoord * 4.5, false, false, true
                  );
               if (movingObjectPosition2 != null
                  && movingObjectPosition2.typeOfHit == MovingObjectType.BLOCK
                  && movingObjectPosition2.getBlockPos() != null
                  && BlockCache.checkBlockPos(movingObjectPosition2.getBlockPos())
                  && !list2.contains(movingObjectPosition2.getBlockPos())
                  && !BlockCache.checkBlockPos2(movingObjectPosition2.getBlockPos().up())) {
                  list.add(new PlaceTarget(movingObjectPosition2.getBlockPos(), movingObjectPosition2.sideHit, movingObjectPosition2.hitVec));
                  list2.add(movingObjectPosition2.getBlockPos());
               }
            }
         }

         list.sort(Comparator.comparingDouble(target -> FailedScaffold.computeValueByPlaceTarget((PlaceTarget)target)));
         if (!list.isEmpty()) {
            return (PlaceTarget)list.get(0);
         } else {
            for (int index2 = 1; index2 < 4; index2++) {
               BlockPos blockPos3 = new BlockPos(minecraft.thePlayer.posX, minecraft.thePlayer.posY - (double)index2, minecraft.thePlayer.posZ);
               if (BlockCache.checkBlockPos(blockPos3)) {
                  return new PlaceTarget(blockPos3, EnumFacing.UP, new Vec3((double)blockPos3.getX() + 0.5, (double)(blockPos3.getY() + 1), (double)blockPos3.getZ() + 0.5));
               }
            }

            System.out.println("Null");
            return null;
         }
      }
   }

   private boolean matchesBlockPos(float factor3, BlockPos blockPos2) {
      return this.settingStrafe.isEnabled() && SimulatedPlayer.checkCondition(factor3) && blockPos2 != null;
   }

   private float computeFactor(float factor3, float factor4, BlockPos blockPos2) {
      float factor5 = factor4;
      switch (FailedScaffoldEnumFacingSwitchMap.countArray[minecraft.thePlayer.getEnumFacing2(factor3).ordinal()]) {
         case 1:
            if (minecraft.thePlayer.posX > (double)blockPos2.getX() + 0.63) {
               factor5 = -45.0F;
            } else if (minecraft.thePlayer.posX < (double)blockPos2.getX() + 0.38) {
               factor5 = 45.0F;
            }
            break;
         case 2:
            if (minecraft.thePlayer.posX > (double)blockPos2.getX() + 0.63) {
               factor5 = 45.0F;
            } else if (minecraft.thePlayer.posX < (double)blockPos2.getX() + 0.38) {
               factor5 = -45.0F;
            }
            break;
         case 3:
            if (minecraft.thePlayer.posZ > (double)blockPos2.getZ() + 0.63) {
               factor5 = -45.0F;
            } else if (minecraft.thePlayer.posZ < (double)blockPos2.getZ() + 0.38) {
               factor5 = 45.0F;
            }
            break;
         case 4:
            if (minecraft.thePlayer.posZ > (double)blockPos2.getZ() + 0.63) {
               factor5 = 45.0F;
            } else if (minecraft.thePlayer.posZ < (double)blockPos2.getZ() + 0.38) {
               factor5 = -45.0F;
            }
      }

      return factor5;
   }

   private static double computeValueByPlaceTarget(PlaceTarget placeTarget) {
      return minecraft.thePlayer.getDouble4((double)placeTarget.getBlockPos().getX() + 0.5, (double)placeTarget.getBlockPos().getY() + 0.5, (double)placeTarget.getBlockPos().getZ() + 0.5);
   }

   private static double computeValue(PlaceTarget placeTarget) {
      return minecraft.thePlayer.getDouble4((double)placeTarget.getBlockPos().getX() + 0.5, (double)placeTarget.getBlockPos().getY() + 0.5, (double)placeTarget.getBlockPos().getZ() + 0.5);
   }

   private double computeValue(float factor3, Vec3 vec, RayTraceHit rayTraceHit) {
      return this.computeValue(rayTraceHit, factor3, vec);
   }

   private boolean isActiveAsFlag() {
      return this.settingSneak.isEnabled() && this.settingOneStackDiagonal.isEnabled();
   }

   private boolean isActive2() {
      return this.settingSneak.isEnabled() && this.settingOneStackDiagonal.isEnabled();
   }

   private boolean isActive3() {
      return this.settingSneak.isEnabled() && this.settingOneStackForward.isEnabled();
   }

   private boolean isActive() {
      return this.settingSneak.isEnabled() && this.settingOneStackForward.isEnabled();
   }
}
