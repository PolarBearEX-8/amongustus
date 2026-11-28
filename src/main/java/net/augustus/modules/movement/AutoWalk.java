package net.augustus.modules.movement;

import net.augustus.Augustus;
import net.augustus.ClientManagers;
import net.augustus.events.EventLoadWorld;
import net.augustus.events.EventMoveInput;
import net.augustus.events.EventSprint;
import net.augustus.events.listeners.LoadWorldListener;
import net.augustus.events.listeners.MoveInputListener;
import net.augustus.events.listeners.RenderListener;
import net.augustus.events.listeners.SprintListener;
import net.augustus.events.listeners.TickListener;
import net.augustus.settings.MultiOptionSetting;
import net.augustus.settings.RandomizedRangeSettings;
import net.augustus.settings.Setting;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.settings.options.AutoWalkTargetOption;
import net.augustus.utils.pathfinding.AStarPathFinder;
import net.augustus.utils.pathfinding.PathBlockPos;
import net.augustus.utils.pathfinding.PathNode;
import net.augustus.utils.rotation.Rotation;
import net.augustus.utils.rotation.RotationController;
import net.augustus.utils.rotation.RotationUtil;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;
import net.augustus.modules.Categorys;
import net.augustus.modules.RotationModule;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.StringValue;
import net.augustus.settings.DoubleValue;
import net.augustus.utils.TimeHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCarpet;
import net.minecraft.block.BlockLadder;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.potion.Potion;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.util.Vec3i;
import org.lwjgl.opengl.GL11;

public class AutoWalk extends RotationModule implements TickListener, SprintListener, MoveInputListener, RenderListener, LoadWorldListener {
   public final BooleanValue pathFinding;
   public final BooleanValue debugLine;
   public final DoubleValue distance;
   public final DoubleValue maxSearchTime;
   public final DoubleValue updateTicks;
   public final BooleanValue smooth;
   public final BooleanValue keepRotation;
   public final StringValue movementmode;
   public final DoubleValue rotationUpdateTicks;
   private final MultiOptionSetting targets;
   public final BooleanValue onlyWithKillAuraTarget;
   public final DoubleValue noWalkDistanceToTarget;
   public final BooleanValue jump;
   public final BooleanValue stopWhileEating;
   public final BooleanValue stopWhileSlowness;
   public final BooleanValue stopWhileJumpBoost;
   public final BooleanValue adStafeOnHit;
   public final RandomizedRangeSettings adStafeTime;
   public final DoubleValue delayAfterRespawn;
   private final TimeHelper adStrafeTimer;
   private final HashSet pathNodeKeys;
   private final RotationUtil rotationUtil;
   private ArrayList pathNodes;
   private final TimeHelper respawnDelayTimer;
   private int pathStepIndex;
   private boolean strafeRight;
   private String lastPlayerPosKey;
   private int[] silentMoveKeys;
   public float silentMoveYaw;
   public long lastSilentMoveTime;
   public long lastSilentRotationUpdateTime;
   public boolean shouldJumpForWaypoint;
   public boolean facingWaypoint;
   public Rotation silentRotation;
   public long lastPathComputeTime;
   public Rotation keptRotation;
   public long lastRotationApplyTime;

   public AutoWalk() {
       super("AutoWalk", "AutoLaufen", Color.red, Categorys.MOVEMENT);
      this.pathFinding = new BooleanValue(this, "PathFinding", false);
      BooleanValue booleanValue = new BooleanValue(this, "DebugLine", true);
      BooleanValue pathFinding2 = this.pathFinding;
      BooleanValue booleanValue2 = pathFinding2;
      this.debugLine = (BooleanValue)booleanValue.withVisibility(() -> booleanValue2.getValue());
      DoubleValue distanceSetting = new DoubleValue(this, "Distance", 50.0, 3.0, 50.0, 1);
      pathFinding2 = this.pathFinding;
      BooleanValue booleanValue3 = pathFinding2;
      this.distance = distanceSetting.visibleWhen(() -> booleanValue3.getValue());
      DoubleValue maxSearchTimeSetting = new DoubleValue(this, "MaxSearchTime", 50.0, 10.0, 1000.0, 0);
      pathFinding2 = this.pathFinding;
      BooleanValue booleanValue4 = pathFinding2;
      this.maxSearchTime = maxSearchTimeSetting.visibleWhen(() -> booleanValue4.getValue());
      DoubleValue updateTicksSetting = new DoubleValue(this, "UpdateTicks", 1.0, 1.0, 10.0, 0);
      pathFinding2 = this.pathFinding;
      BooleanValue booleanValue5 = pathFinding2;
      this.updateTicks = updateTicksSetting.visibleWhen(() -> booleanValue5.getValue());
      BooleanValue booleanValue6 = new BooleanValue(this, "Smooth", true);
      pathFinding2 = this.pathFinding;
      BooleanValue booleanValue7 = pathFinding2;
      this.smooth = (BooleanValue)booleanValue6.withVisibility(() -> booleanValue7.getValue());
      BooleanValue booleanValue8 = new BooleanValue(this, "KeepRotation", true);
      pathFinding2 = this.pathFinding;
      BooleanValue booleanValue9 = pathFinding2;
      this.keepRotation = (BooleanValue)booleanValue8.withVisibility(() -> booleanValue9.getValue());
      StringValue setting = new StringValue(this, "Movementmode", "Rotation", new String[]{"Rotation", "Silent"});
      pathFinding2 = this.pathFinding;
      BooleanValue booleanValue10 = pathFinding2;
      this.movementmode = (StringValue)setting.withVisibility(() -> booleanValue10.getValue());
      this.rotationUpdateTicks = new DoubleValue(this, "RotationUpdateTicks", 0.0, 0.0, 10.0, 0).visibleWhen(() -> this.isRotationUpdateTicksVisible());
      MultiOptionSetting multiOptionSetting = new MultiOptionSetting(this, "Targets", AutoWalkTargetOption.values());
      pathFinding2 = this.pathFinding;
      BooleanValue booleanValue11 = pathFinding2;
      this.targets = multiOptionSetting.visibleWhen(() -> booleanValue11.getValue());
      this.onlyWithKillAuraTarget = new BooleanValue(this, "OnlyWithKillAuraTarget", true);
      this.noWalkDistanceToTarget = new DoubleValue(this, "NoWalkDistanceToTarget", 1.0, 0.0, 6.0, 2);
      this.jump = new BooleanValue(this, "Jump", true);
      this.stopWhileEating = new BooleanValue(this, "StopWhileEating", true);
      this.stopWhileSlowness = new BooleanValue(this, "StopWhileSlowness", false);
      this.stopWhileJumpBoost = new BooleanValue(this, "StopWhileJumpBoost", false);
      this.adStafeOnHit = (BooleanValue)new BooleanValue(this, "AdStafeOnHit", true).withDisplayName("AdStrafeOnHit");
      RandomizedRangeSettings randomizedRangeSettings2 = (RandomizedRangeSettings)new RandomizedRangeSettings(this, "AdStafeTime", 200.0, 200.0, 0.0, 1000.0, 0).withDisplayNamePrefix("AdStrafeTime");
      pathFinding2 = this.adStafeOnHit;
      BooleanValue booleanValue13 = pathFinding2;
      this.adStafeTime = (RandomizedRangeSettings)randomizedRangeSettings2.withVisibility(() -> booleanValue13.getValue());
      this.delayAfterRespawn = new DoubleValue(this, "DelayAfterRespawn", 0.0, 2000.0, 10000.0, 0);
      this.adStrafeTimer = new TimeHelper();
      this.pathNodeKeys = new HashSet();
      this.rotationUtil = new RotationUtil();
      this.pathNodes = new ArrayList();
      this.respawnDelayTimer = new TimeHelper();
      this.strafeRight = false;
      this.lastPlayerPosKey = "";
      this.rotating = true;
      String str3 = "None";
      this.silentMoveFix.setSelectedValue(str3);
   }

   @Override
   public void onEnable() {
      super.onEnable();
   }

   @Override
   public void onDisable() {
      super.onDisable();
   }

   @Override
   public void updateEventSubscriptions() {
      super.updateEventSubscriptions();
      this.toggleEventSubscription(40, 1);
      this.toggleEventSubscription(36);
      this.toggleEventSubscription(32);
      this.toggleEventSubscription(17);
   }

   @Override
   public void onEarlyTick() {
      this.rotating = true;
      String str = "None";
      this.silentMoveFix.setSelectedValue(str);

      try {
         if (Augustus.getInstance().getModuleManager().freeCam.isToggled() || !this.hasRespawnDelayElapsed()) {
            return;
         }

         if (mc.isNotInGui() && this.pathFinding.getValue()) {
            if (this.keepRotation.getValue() && this.keptRotation != null && System.currentTimeMillis() - this.lastRotationApplyTime < 10000L) {
               ClientManagers.rotationController.requestRotation(this.keptRotation.toArray(), (byte)99, this);
            }

            EntityLivingBase entity = (EntityLivingBase)this.findClosestTarget();
            Vec3 vec = this.rotationUtil.selectEntityAimPoint(entity, ClientManagers.moduleManager.killAura.rotationMode.getSelectedValue(), (int)ClientManagers.moduleManager.killAura.hitBoxPercentageVertical.getValue(), (int)ClientManagers.moduleManager.killAura.hitBoxPercentageHorizontal.getValue(), (int)ClientManagers.moduleManager.killAura.hitBoxPercentageVertical1.getValue(), (int)ClientManagers.moduleManager.killAura.hitBoxPercentageHorizontal1.getValue(), ClientManagers.rotationController.getPreviousRotation());
            if (entity != null
               && (
                  !ClientManagers.moduleManager.killAura.isToggled()
                     || ClientManagers.moduleManager.killAura.target == null
                     || (double)mc.thePlayer.getDistanceToEntityBox(entity) > 3.5
                     || !mc.thePlayer.canEntityBeSeen(vec.xCoord, vec.yCoord, vec.zCoord)
               )) {
               Vec3 interpolatedPos = mc.thePlayer.getInterpolatedPos(1.0F);
               Vec3 interpolatedPos2 = entity.getInterpolatedPos(1.0F);
               PathBlockPos pathBlockPos = new PathBlockPos((int)Math.floor(interpolatedPos.xCoord), (int)Math.floor(interpolatedPos.yCoord), (int)Math.floor(interpolatedPos.zCoord));
               PathBlockPos pathBlockPos2 = new PathBlockPos((int)Math.floor(interpolatedPos2.xCoord), (int)Math.floor(interpolatedPos2.yCoord), (int)Math.floor(interpolatedPos2.zCoord));
               String str3 = pathBlockPos.toKeyString();
               if ((!this.lastPlayerPosKey.equals(str3) || System.currentTimeMillis() - this.lastPathComputeTime < 1000L) && pathBlockPos.distanceTo(pathBlockPos2) < 500.0) {
                  this.lastPlayerPosKey = str3;
                  if (mc.thePlayer.ticksExisted % (int)this.updateTicks.getValue() == 0) {
                     this.computePath(pathBlockPos, pathBlockPos2);
                     this.lastPathComputeTime = System.currentTimeMillis();
                  } else {
                     System.out.println(str3 + " " + this.pathNodeKeys.contains(str3) + " (nodes=" + this.pathNodeKeys.size() + ")");
                     if (this.pathNodeKeys.contains(str3)) {
                        this.pathStepIndex++;
                        if (this.pathNodes.size() <= this.pathStepIndex) {
                           this.pathStepIndex = this.pathNodes.size() - 1;
                        }
                     } else {
                        this.computePath(pathBlockPos, pathBlockPos2);
                        this.lastPathComputeTime = System.currentTimeMillis();
                     }
                  }
               }

               if (this.pathNodes != null && !this.pathNodes.isEmpty() && this.pathNodes.size() > 1) {
                  byte b2 = 1;
                  if (mc.thePlayer.motionY > 0.0) {
                     b2 = 2;
                  }

                  PathNode pathNode = (PathNode)this.pathNodes.get(this.pathNodes.size() - this.pathStepIndex - b2 - 1);
                  if (this.isSlowOrThinBlock(mc.theWorld.getBlockState2(pathNode.getPos().x, pathNode.getPos().y, pathNode.getPos().z).getBlock())) {
                     b2 = 2;
                     pathNode = (PathNode)this.pathNodes.get(this.pathNodes.size() - this.pathStepIndex - b2 - 1);
                  }

                  if ((double)pathNode.getPos().y > mc.thePlayer.posY) {
                     b2 = 3;
                     pathNode = (PathNode)this.pathNodes.get(this.pathNodes.size() - this.pathStepIndex - b2 - 1);
                  }

                  Vec3 vec2 = pathNode.getPos().toVec3();
                  if ((double)pathNode.getPos().y > mc.thePlayer.posY) {
                     int n5 = MathHelper.floor_double(mc.thePlayer.posX);
                     int n6 = MathHelper.floor_double(mc.thePlayer.posY);
                     int n7 = MathHelper.floor_double(mc.thePlayer.posZ);
                     BlockPos pos2 = new BlockPos(n5, n6, n7);
                     IBlockState blockState = mc.theWorld.getBlockState(pos2);
                     if (blockState.getBlock() instanceof BlockLadder) {
                        Vec3i directionVec = ((EnumFacing)blockState.getValue(BlockLadder.FACING)).getOpposite().getDirectionVec();
                        vec2 = new Vec3(pos2.add(directionVec));
                     } else if (this.smooth.getValue()) {
                        for (int i = this.pathNodes.size() - this.pathStepIndex - b2 - 1; i > 0; i--) {
                           Vec3 vec3 = ((PathNode)this.pathNodes.get(i)).getPos().toVec3();
                           Rotation rotation2 = this.calculateRotationToPos(vec3.xCoord + 0.5, vec3.yCoord + (double)mc.thePlayer.getEyeHeight(), vec3.zCoord + 0.5);
                           MovingObjectPosition hit = mc.thePlayer
                              .rayTraceCustom(mc.thePlayer.getDistance(vec3.xCoord + 0.5, interpolatedPos.yCoord, vec3.zCoord + 0.5) + 0.2, 1.0F, rotation2.yaw, rotation2.pitch);
                           MovingObjectPosition hit2 = mc.thePlayer
                              .rayTraceFromPosition(
                                 new Vec3(mc.thePlayer.posX, mc.thePlayer.posY + 0.1, mc.thePlayer.posZ),
                                 mc.thePlayer.getDistance(vec3.xCoord + 0.5, interpolatedPos.yCoord, vec3.zCoord + 0.5) + 0.2,
                                 1.0F,
                                 rotation2.yaw,
                                 rotation2.pitch
                              );
                           if (hit != null && hit.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK || hit2 != null && hit2.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
                              break;
                           }

                           vec2 = vec3;
                        }
                     }
                  } else if (this.smooth.getValue()) {
                     for (int j = this.pathNodes.size() - this.pathStepIndex - b2 - 1; j > 0; j--) {
                        Vec3 vec4 = ((PathNode)this.pathNodes.get(j)).getPos().toVec3();
                        Rotation rotation3 = this.calculateRotationToPos(vec4.xCoord + 0.5, vec4.yCoord + (double)mc.thePlayer.getEyeHeight(), vec4.zCoord + 0.5);
                        MovingObjectPosition hit3 = mc.thePlayer
                           .rayTraceCustom(mc.thePlayer.getDistance(vec4.xCoord + 0.5, interpolatedPos.yCoord, vec4.zCoord + 0.5) + 0.2, 1.0F, rotation3.yaw, rotation3.pitch);
                        MovingObjectPosition hit4 = mc.thePlayer
                           .rayTraceFromPosition(
                              new Vec3(mc.thePlayer.posX, mc.thePlayer.posY + 0.1, mc.thePlayer.posZ),
                              mc.thePlayer.getDistance(vec4.xCoord + 0.5, interpolatedPos.yCoord, vec4.zCoord + 0.5) + 0.2,
                              1.0F,
                              rotation3.yaw,
                              rotation3.pitch
                           );
                        if (hit3 != null && hit3.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK || hit4 != null && hit4.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
                           break;
                        }

                        vec2 = vec4;
                     }
                  }

                  if (this.movementmode.getSelectedValue().equalsIgnoreCase("rotation")) {
                     double d11 = vec2.xCoord + 0.5 - mc.thePlayer.posX;
                     Rotation rotation4 = this.applyRotation((float)(MathHelper.atan2(vec2.zCoord + 0.5 - mc.thePlayer.posZ, d11) * 180.0 / Math.PI - 90.0), (float)ThreadLocalRandom.current().nextDouble(-1.0, 1.0));
                     ClientManagers.rotationController.requestRotation(rotation4.toArray(), (short)-10000, this);
                     this.lastRotationApplyTime = System.currentTimeMillis();
                     this.keptRotation = rotation4;
                  } else {
                     if (this.rotationUpdateTicks.getValue() > 0.0 && (double)mc.thePlayer.ticksExisted % this.rotationUpdateTicks.getValue() == 0.0) {
                        double d13 = vec2.xCoord + 0.5 - mc.thePlayer.posX;
                        this.silentRotation = this.applyRotation((float)(MathHelper.atan2(vec2.zCoord + 0.5 - mc.thePlayer.posZ, d13) * 180.0 / Math.PI - 90.0), (float)ThreadLocalRandom.current().nextDouble(-1.0, 1.0));
                        this.lastSilentRotationUpdateTime = System.currentTimeMillis();
                     }

                     if (this.silentRotation != null
                        && System.currentTimeMillis() - this.lastSilentMoveTime < 100L
                        && (double)(System.currentTimeMillis() - this.lastSilentRotationUpdateTime) < this.rotationUpdateTicks.getValue() * 50.0) {
                        ClientManagers.rotationController.requestRotation(this.silentRotation.toArray(), (short)-10000, this);
                        this.lastRotationApplyTime = System.currentTimeMillis();
                        this.keptRotation = this.silentRotation;
                     }

                     double d15 = vec2.xCoord + 0.5 - mc.thePlayer.posX;
                     float f5 = (float)(MathHelper.atan2(vec2.zCoord + 0.5 - mc.thePlayer.posZ, d15) * 180.0 / Math.PI - 90.0);
                     this.silentMoveKeys = computeMoveKeysForYaw(ClientManagers.rotationController.getCurrentRotation().yaw, f5);
                     this.lastSilentMoveTime = System.currentTimeMillis();
                     this.shouldJumpForWaypoint = vec2.yCoord > mc.thePlayer.posY;
                     this.facingWaypoint = shortestAngleDiff(
                        MathHelper.wrapAngleTo180_float(ClientManagers.rotationController.getCurrentRotation().yaw) + 180.0F, MathHelper.wrapAngleTo180_float(f5) + 180.0F
                     ) <= 45.0F;
                  }
               }
            }
         }
      } catch (Exception e) {
      }
   }

   private boolean isSlowOrThinBlock(Block block) {
      return block == Blocks.web || block instanceof BlockCarpet || block == Blocks.snow_layer;
   }

   public static float shortestAngleDiff(float f, float f2) {
      float f3 = Math.abs(f - f2);
      return Math.min(f3, 360.0F - f3);
   }

   public static int[] computeMoveKeysForYaw(float f, float f2) {
      double[] doubles = calculateMotionVector(f2, 0.0F, 1.0F);
      int[] ints = new int[]{0, 0};
      double d = -1.0;

      for (int i = -1; i <= 1; i++) {
         for (int j = -1; j <= 1; j++) {
            double[] doubles2 = calculateMotionVector(f, (float)i, (float)j);
            double d2 = (doubles2[0] - doubles[0]) * (doubles2[0] - doubles[0]) + (doubles2[1] - doubles[1]) * (doubles2[1] - doubles[1]);
            if (d > d2 || d == -1.0) {
               ints = new int[]{i, j};
               d = d2;
            }
         }
      }

      return ints;
   }

   public static double[] calculateMotionVector(float f, float f2, float f3) {
      float f4 = f2 * f2 + f3 * f3;
      if (f4 >= 1.0E-4F) {
         f4 = MathHelper.sqrt_float(f4);
         if (f4 < 1.0F) {
            f4 = 1.0F;
         }

         f4 = 1.0F / f4;
         f2 *= f4;
         f3 *= f4;
         float f5 = MathHelper.sin(f * (float) Math.PI / 180.0F);
         float f6 = MathHelper.cos(f * (float) Math.PI / 180.0F);
         return new double[]{(double)(f2 * f6 - f3 * f5), (double)(f3 * f6 + f2 * f5)};
      } else {
         return new double[]{0.0, 0.0};
      }
   }

   public void computePath(PathBlockPos pathBlockPos, PathBlockPos pathBlockPos2) {
      boolean isToggled = Augustus.getInstance().getModuleManager().jesus.isToggled();
      boolean flag = isToggled && Augustus.getInstance().getModuleManager().jesus.mode.getSelectedValue().equalsIgnoreCase("dolphin");
      AStarPathFinder aStarPathFinder = new AStarPathFinder(pathBlockPos, pathBlockPos2, isToggled && !flag, flag, false);
      aStarPathFinder.smoothPath = true;
      this.pathNodes = aStarPathFinder.computePath(true, (long)this.maxSearchTime.getValue());
      this.pathNodeKeys.clear();
      if (this.pathNodes != null) {
         for (PathNode pathNode : (Iterable<PathNode>)(this.pathNodes)) {
            this.pathNodeKeys.add(pathNode.getPos().toKeyString());
         }

         this.pathStepIndex = 0;
      }
   }

   @Override
   public void onRender3D(float partialTicks) {
      GL11.glPushMatrix();
      GlStateManager.enableBlend();
      GlStateManager.blendFunc(770, 771);
      GL11.glEnable(2848);
      GlStateManager.disableDepth();
      GlStateManager.disableTexture2D();
      GlStateManager.disableCull();
      GlStateManager.depthMask(false);
      GL11.glTranslated(-mc.getRenderManager().getRenderPosX(), -mc.getRenderManager().getRenderPosY(), -mc.getRenderManager().getRenderPosZ());
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      if (this.debugLine.getValue() && this.pathNodes != null && !this.pathNodes.isEmpty() && this.pathFinding.getValue()) {
         GL11.glBegin(3);
         if (this.pathNodes != null) {
            for (PathNode pathNode : (Iterable<PathNode>)(this.pathNodes)) {
               GL11.glVertex3d((double)pathNode.getPos().x + 0.5, (double)pathNode.getPos().y + 0.01, (double)pathNode.getPos().z + 0.5);
            }
         }

         GL11.glEnd();
      }

      GL11.glPopMatrix();
      GlStateManager.depthMask(true);
      GlStateManager.enableCull();
      GlStateManager.enableTexture2D();
      GlStateManager.enableDepth();
      GlStateManager.disableBlend();
      GL11.glDisable(2848);
   }

   @Override
   public void onSprint(EventSprint event) {
      if (!this.facingWaypoint && this.pathFinding.getValue() && this.movementmode.getSelectedValue().equalsIgnoreCase("silent")) {
         Object[] array = mc.theWorld.loadedEntityList.stream().filter(obj -> this.isValidTarget((Entity)obj)).sorted(Comparator.comparingDouble(obj2 -> (double)mc.thePlayer.getDistanceToEntity((Entity)obj2))).toArray();
         if (array.length > 0 && this.pathNodes != null && !this.pathNodes.isEmpty()) {
            if (((EntityLivingBase)array[0]) != null) {
               event.setForceSprint(false);
            }
         }
      }
   }

   @Override
   public void onPreMoveInput(EventMoveInput event) {
      if (!Augustus.getInstance().getModuleManager().freeCam.isToggled() && this.hasRespawnDelayElapsed()) {
         if (mc.isNotInGui() && this.canWalkWhileEating()) {
            EntityLivingBase entity = null;
            if (this.pathFinding.getValue() && this.pathNodes != null && !this.pathNodes.isEmpty()) {
               entity = (EntityLivingBase)this.findClosestTarget();
            }

            if (this.isBlockedByPotionEffect()) {
               return;
            }

            if ((!ClientManagers.moduleManager.killAura.isToggled() || ClientManagers.moduleManager.killAura.target == null) && entity == null) {
               if (!this.onlyWithKillAuraTarget.getValue()) {
                  event.setMoveForward(1.0F);
               }
            } else {
               EntityLivingBase entity2 = ClientManagers.moduleManager.killAura.isToggled() && ClientManagers.moduleManager.killAura.target != null ? ClientManagers.moduleManager.killAura.target : entity;
               if (entity2 != null) {
                  if (this.movementmode.getSelectedValue().equalsIgnoreCase("rotation") || !this.pathFinding.getValue()) {
                     if ((double)mc.thePlayer.getDistanceToEntityBox(entity2) > this.noWalkDistanceToTarget.getValue()) {
                        event.setMoveForward(1.0F);
                     }
                  } else if (this.silentMoveKeys != null) {
                     event.setMoveStrafe((float)this.silentMoveKeys[0]);
                     event.setMoveForward((float)this.silentMoveKeys[1]);
                     if (this.shouldJumpForWaypoint) {
                        event.setJump(true);
                     }
                  }

                  if (this.jump.getValue()
                     && mc.thePlayer.getDistanceToEntityBox(entity2) > 6.0F
                     && (!this.movementmode.getSelectedValue().equalsIgnoreCase("silent") || this.facingWaypoint || !this.pathFinding.getValue())) {
                     event.setJump(true);
                  }

                  if (this.adStafeOnHit.getValue() && (double)mc.thePlayer.getDistanceToEntityBox(entity2) < 4.5) {
                     if (entity2.hurtTime == 9) {
                        this.adStrafeTimer.reset();
                        this.strafeRight = !this.strafeRight;
                     }

                     if (!this.adStrafeTimer.reached(this.adStafeTime.getLastValue())) {
                        event.setMoveStrafe(this.strafeRight ? 1.0F : -1.0F);
                        this.adStafeTime.nextValue();
                     }
                  }
               }
            }
         }
      }
   }

   @Override
   public void onLoadWorld(EventLoadWorld event) {
      this.respawnDelayTimer.reset();
   }

   private boolean canWalkWhileEating() {
      return !mc.thePlayer.isEating() || !this.stopWhileEating.getValue();
   }

   private boolean isBlockedByPotionEffect() {
      return mc.thePlayer.getActivePotionEffect(Potion.moveSlowdown) != null && this.stopWhileSlowness.getValue()
         || mc.thePlayer.getActivePotionEffect(Potion.jump) != null && this.stopWhileJumpBoost.getValue();
   }

   private boolean hasRespawnDelayElapsed() {
      return this.respawnDelayTimer.reached(this.delayAfterRespawn.getValue());
   }

   private boolean isValidTarget(Entity entity) {
      if (!(entity instanceof EntityPlayerSP)
         && this.hasRespawnDelayElapsed()
         && (
            entity instanceof EntityPlayer && AutoWalkTargetOption.PLAYER.isSelected()
               || (entity instanceof EntityMob || entity instanceof EntityIronGolem || entity instanceof EntitySnowman) && AutoWalkTargetOption.MOB.isSelected()
               || entity instanceof EntityVillager && AutoWalkTargetOption.VILLAGER.isSelected()
               || entity instanceof EntityAnimal && AutoWalkTargetOption.ANIMAL.isSelected()
               || entity instanceof EntityArmorStand && AutoWalkTargetOption.ARMOR_STAND.isSelected()
         )
         && (!entity.isInvisible() || AutoWalkTargetOption.INVISIBLE.isSelected())
         && entity.ticksExisted > 0
         && !entity.isDead
         && !entity.getName().equals("§aShop")
         && !entity.getName().equals("SHOP")
         && !entity.getName().equals("UPGRADES")
         && (!(entity instanceof EntityPlayer) || !ClientManagers.moduleManager.teams.isTeammate(entity))) {
         if (ClientManagers.moduleManager.targetArea.isToggled()) {
            if (!ClientManagers.moduleManager.targetArea.isEntityInArea(entity)) {
               return false;
            }
         }

         if (entity instanceof EntityPlayer) {
            if (ClientManagers.moduleManager.antiBot.isBot(entity)) {
               return false;
            }
         }

         if ((!(entity instanceof EntityPlayer) || !ClientManagers.moduleManager.midClick.isFriend(entity) || ClientManagers.moduleManager.midClick.autoUnfriendTriggered)
            && (double)entity.getDistanceToEntityBox(mc.thePlayer) <= this.distance.getValue()) {
            return true;
         }
      }

      return false;
   }

   public Entity findClosestTarget() {
      Entity entity = null;
      double d = 0.0;

      for (Entity entity2 : (Iterable<Entity>)(mc.theWorld.loadedEntityList)) {
         if (this.isValidTarget(entity2)) {
            double distanceSqToEntityFloat = (double)mc.thePlayer.getDistanceSqToEntityFloat(entity2);
            if (distanceSqToEntityFloat < d || entity == null) {
               entity = entity2;
               d = distanceSqToEntityFloat;
            }
         }
      }

      return entity;
   }

   private boolean isRotationUpdateTicksVisible() {
      return this.movementmode.isVisible() && this.movementmode.getSelectedValue().equalsIgnoreCase("silent");
   }

}
