package augustus.modules.misc;

import augustus.modes.combat.AuraRenderMode;
import client.Client;
import client.ClientGlobals;
import client.event.ClickListener;
import client.event.EventClick;
import client.event.EventMove;
import client.event.MoveListener;
import client.event.TickListener;
import client.module.Category;
import client.module.Module;
import client.rotation.Rotation;
import client.rotation.RotationManager;
import client.setting.SettingContainer;
import client.util.CombatRayTrace;
import client.util.CombatUtil;
import client.util.MovingObjectType;
import client.util.RayTraceHit;
import client.util.RayTraceUtil;
import client.util.SimulatedPlayer;
import client.util.TimeHelper;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;
import net.minecraft.block.BlockCache;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;

public class NewScaffold extends Module implements TickListener, MoveListener, ClickListener {
   public final SimulatedPlayer simulatedPlayer = new SimulatedPlayer();
   public final TimeHelper timer = new TimeHelper();
   public int count6;
   public float factor = 0.0F;
   public float factor2 = 0.0F;
   public boolean enabled3 = false;
   public boolean enabled4 = false;
   public int count7 = 1;
   public MovingObjectPosition movingObjectPosition = null;

   public NewScaffold() {
      super("NewScaffold", "NeuGerüst", new Color(182, 194, 207), Category.WORLD);
   }

   @Override
   public void onPreDisable() {
      this.timer.reset();
      this.count6 = 0;
      this.count7 = 0;
      this.enabled4 = false;
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void onEnable() {
      this.registerEvent(40);
      this.registerEvent(21);
      this.registerEvent(7);
   }

   @Override
   public void onPreTick() {
      BlockPos blockPos = new BlockPos(minecraft.thePlayer.posX, minecraft.thePlayer.posY, minecraft.thePlayer.posZ).down();
      float factor3 = this.getFactor();
      if (this.enabled4) {
         factor3 = ClientGlobals.rotationManager.getRotation3().yaw;
      }

      SimulatedPlayer simulatedPlayer2 = this.simulatedPlayer;
      float factor4 = 24.0F;
      float factor5 = 18.0F;
      float floatItem = SettingContainer.settingProvider.getFloat2(factor5, factor4);
      float yaw = ClientGlobals.rotationManager.getRotation3().yaw;
      float factor6 = floatItem;
      float floatItem2 = simulatedPlayer2.getFloat(factor6, yaw, factor3);
      float pitch = ClientGlobals.rotationManager.getRotation3().pitch;
      if (!this.timer.hasReached(200L)) {
         simulatedPlayer2 = this.simulatedPlayer;
         float factor7 = 24.0F;
         float factor8 = 18.0F;
         floatItem = SettingContainer.settingProvider.getFloat2(factor8, factor7);
         float factor9 = 80.34F;
         float pitch2 = ClientGlobals.rotationManager.getRotation3().pitch;
         float factor10 = floatItem;
         pitch = simulatedPlayer2.getFloat11(factor10, pitch2, factor9);
      }

      boolean flag = !this.enabled4;
      if (flag) {
         float factor11 = 3.0F;
         float factor12 = 1.0F;
         pitch = SimulatedPlayer.getFloat3(pitch + SettingContainer.settingProvider.getFloat2(factor12, factor11), ClientGlobals.rotationManager.getRotation3().pitch);
      }

      System.out.println(minecraft.thePlayer.posX + " " + minecraft.thePlayer.posZ);
      if (flag && !minecraft.theWorld.getBlockState(blockPos).getBlock().isState18()) {
         this.count7 = SettingContainer.settingProvider.getInt2(1, 1);
         int count2 = this.count7 - 1;
         EntityPlayerSP player = minecraft.thePlayer;
         int count3 = count2;
         double[] coordOffsetArray = CombatUtil.getCoordOffsetArray(count3, player, floatItem2);
         System.out.println(coordOffsetArray[0] + "   " + minecraft.thePlayer.posX + "    :   " + coordOffsetArray[2] + "  " + minecraft.thePlayer.posZ);
         Vec3 vec = new Vec3(coordOffsetArray[0], coordOffsetArray[1], coordOffsetArray[2]);
         System.out.println(this.count7);
         ArrayList list = new ArrayList();
         float factor13 = 0.0F;

         for (float factor14 = 15.0F; factor14 < 90.0F; factor14 += 0.05F) {
            float floatItem3 = SimulatedPlayer.getFloat3(factor14, ClientGlobals.rotationManager.getRotation3().pitch);
            if (floatItem3 != factor13) {
               MovingObjectPosition hitResult = CombatRayTrace.resolveMovingObjectPosition(1.0F, vec, floatItem2, floatItem3);
               if (hitResult != null
                  && hitResult.typeOfHit == MovingObjectType.BLOCK
                  && BlockCache.checkBlockPos(hitResult.getBlockPos())
                  && hitResult.sideHit != EnumFacing.DOWN
                  && hitResult.sideHit != EnumFacing.UP
                  && (double)hitResult.getBlockPos().getY() < minecraft.thePlayer.posY) {
                  list.add(new RayTraceHit(hitResult, floatItem3));
               }
            }

            factor13 = floatItem3;
         }

         if (!list.isEmpty()) {
            this.count6 = this.count7;
            list.sort(Comparator.comparingDouble(hit -> this.computeValue(floatItem2, vec, (RayTraceHit)hit)));
            RayTraceHit rayTraceHit = (RayTraceHit)list.get(0);
            float[] factorArray = new float[]{floatItem2, rayTraceHit.scaleFactor};
            ClientGlobals.rotationManager.applyFloatArray(factorArray, -1, this);
            this.enabled4 = true;
            RayTraceUtil.applyString("thiusdlfkjs" + minecraft.thePlayer.ticksExisted);
            return;
         }
      }

      this.enabled4 = this.count6 > 0;
      RotationManager rotationManager = ClientGlobals.rotationManager;
      Rotation rotation = new Rotation(floatItem2, pitch);
      byte byteValue = -1;
      Rotation rotation2 = rotation;
      rotationManager.applyRotation2(rotation2, byteValue, 1898960900, this);
   }

   @Override
   public void onMove(EventMove event) {
      AuraRenderMode auraRenderMode = AuraRenderMode.DEFAULT;
      event.setAuraRenderMode(auraRenderMode);
      if (this.count6 > 0) {
         float factor3 = this.factor;
         event.setScaleFactor2(factor3);
         float factor4 = this.factor2;
         event.setScaleFactor7(factor4);
         boolean enabled = this.enabled3;
         event.setState4(enabled);
      }

      this.count6--;
      this.factor = event.getScaleFactor2();
      this.factor2 = event.getScaleFactor7();
      this.enabled3 = event.isState4();
   }

   @Override
   public void onClick(EventClick event) {
      if (this.count6 <= 0) {
         event.updateInt2(1);
         event.setState2(false);
         event.setState3(true);
      }
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

   private double computeValue(float factor3, Vec3 vec, RayTraceHit rayTraceHit) {
      return this.computeValue(rayTraceHit, factor3, vec);
   }
}
