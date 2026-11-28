package net.augustus.modules.render;

import java.awt.Color;
import java.util.List;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.modules.misc.MurdererFinder;
import net.augustus.events.listeners.RenderListener;
import net.augustus.modules.render.pathfinder.ScoredTargetEntity;
import net.augustus.events.EventKey;
import net.augustus.ClientManagers;
import net.augustus.settings.KeybindSetting;
import net.augustus.utils.pathfinding.PathNode;
import net.augustus.settings.ColorSetting;
import net.augustus.modules.render.pathfinder.TopKSelector;
import net.augustus.events.listeners.KeyListener;
import net.augustus.utils.pathfinding.TimedAStarPathFinder;
import net.augustus.utils.math.MathStatisticsUtil;
import net.augustus.modules.combat.helper.MurderWeaponSlotUtil;
import net.augustus.utils.combat.TargetFilterPresets;
import net.augustus.utils.rotation.RayTraceUtil;
import net.augustus.modules.render.pathfinder.EntityPathJob;
import net.augustus.render.RenderUtil;
import net.augustus.Augustus;
import net.augustus.events.listeners.TickListener;
import net.augustus.utils.pathfinding.PathBlockPos;
import net.augustus.settings.TargetSelectionSettings;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.SettingGroup;
import net.augustus.settings.DoubleValue;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.util.Vec3i;
import net.optifine.config.Config;
import net.optifine.shaders.Shaders;
import org.lwjgl.opengl.GL11;

public class Pathfinder extends Module implements RenderListener, TickListener, KeyListener {
   private SettingGroup autoTarget;
   private final BooleanValue autoTarget2;
   private final TargetSelectionSettings targetSelectionSettings;
   private final BooleanValue chooseTargetByPathLength;
   private final DoubleValue maxConsideredTargets;
   private final BooleanValue murderMystery;
   private final DoubleValue maxSearchtimePerTick;
   private final DoubleValue maxSteps;
   private final DoubleValue ticksBetweenSearches;
   private final DoubleValue maxFallHeight;
   private final BooleanValue discourageFalldamage;
   private final DoubleValue discourageFalldamageFactor;
   private final KeybindSetting pickTarget;
   private final KeybindSetting resetPickedTarget;
   private final ColorSetting pathColor;
   private final ColorSetting targetColor;
   private Entity autoTargetEntity;
   private java.util.ArrayList currentPath;
   private java.util.ArrayList previousPath;
   private java.util.ArrayList activePathJobs;
   private Entity pickedTargetEntity;
   private PathBlockPos pickedTargetBlock;
   private AxisAlignedBB pickedBlockFaceBox;
   private int searchCooldownTicks;
   private TopKSelector topKSelector;
   private Vec3 searchOriginPos;

   public Pathfinder() {
       super("Pathfinder", "Pfadfinder", Color.RED, Categorys.RENDER);
      this.autoTarget = new SettingGroup(this, "Auto Target");
      this.autoTarget2 = new BooleanValue(this, "Auto Target", true, this.autoTarget);
      TargetSelectionSettings targetSelectionSettings2 = new TargetSelectionSettings(this, TargetFilterPresets.createCombatFilter(), this.autoTarget);
      BooleanValue autoTarget22 = this.autoTarget2;
      BooleanValue booleanValue = autoTarget22;
      this.targetSelectionSettings = targetSelectionSettings2.withVisibility(() -> booleanValue.getValue());
      BooleanValue booleanValue2 = new BooleanValue(this, "Choose Target By Path Length", true, this.autoTarget);
      autoTarget22 = this.autoTarget2;
      BooleanValue booleanValue3 = autoTarget22;
      this.chooseTargetByPathLength = (BooleanValue)booleanValue2.withVisibility(() -> booleanValue3.getValue());
      DoubleValue maxConsideredTargetsSetting = new DoubleValue(this, "Max considered Targets", 2.0, 2.0, 5.0, 0, this.autoTarget);
      SettingVisibilityCondition condition3 = () -> {
         return this.autoTarget2.getValue() && this.chooseTargetByPathLength.getValue();
      };
      this.maxConsideredTargets = maxConsideredTargetsSetting.visibleWhen(condition3);
      BooleanValue booleanValue4 = new BooleanValue(this, "Murder Mystery", false, this.autoTarget);
      SettingVisibilityCondition condition4 = () -> {
         return this.autoTarget2.getValue() && (ClientManagers.moduleManager == null || ClientManagers.moduleManager.murdererFinder == null || ClientManagers.moduleManager.murdererFinder.isToggled());
      };
      this.murderMystery = (BooleanValue)booleanValue4.withVisibility(condition4);
      this.maxSearchtimePerTick = new DoubleValue(this, "Max Searchtime per Tick", 5.0, 2.0, 40.0, 0);
      this.maxSteps = new DoubleValue(this, "Max Steps", 4.0, 1.0, 20.0, 0);
      this.ticksBetweenSearches = new DoubleValue(this, "Ticks between searches", 4.0, 0.0, 20.0, 0);
      this.maxFallHeight = new DoubleValue(this, "Max Fall Height", 255.0, 1.0, 255.0, 0);
      this.discourageFalldamage = new BooleanValue(this, "Discourage Falldamage", false);
      DoubleValue discourageFalldamageFactor2 = new DoubleValue(this, "Discourage Falldamage Factor", 90.0, 1.0, 100.0, 0);
      autoTarget22 = this.discourageFalldamage;
      BooleanValue booleanValue5 = autoTarget22;
      this.discourageFalldamageFactor = discourageFalldamageFactor2.visibleWhen(() -> booleanValue5.getValue());
      this.autoTargetEntity = null;
      this.currentPath = null;
      this.previousPath = null;
      this.activePathJobs = new java.util.ArrayList();
      this.pickedTargetEntity = null;
      this.pickedTargetBlock = null;
      this.pickedBlockFaceBox = null;
      this.searchCooldownTicks = 0;
      this.topKSelector = new TopKSelector();
      this.pickTarget = new KeybindSetting(this, "Pick Target");
      this.resetPickedTarget = new KeybindSetting(this, "Reset picked target");
      this.pathColor = new ColorSetting(this, "Path Color", Color.WHITE);
      this.targetColor = new ColorSetting(this, "Target Color", new Color(255, 255, 255, 100));
      this.pickTarget.setKeyCode((short)10001);
   }

   @Override
   public void onEnable() {
      this.currentPath = null;
      this.autoTargetEntity = null;
   }

   @Override
   public void onDisable() {
      this.currentPath = null;
      this.autoTargetEntity = null;
      this.pickedTargetEntity = null;
      this.pickedTargetBlock = null;
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(32);
      this.toggleEventSubscription(40);
      this.toggleEventSubscription(14);
   }

   @Override
   public void onPostTick() {
      if (mc.thePlayer != null && mc.theWorld != null) {
         this.previousPath = null;
         if (this.currentPath != null) {
            Vec3 vec = (Vec3)this.currentPath.get(0);
            double d = (vec.xCoord + 0.5 - mc.thePlayer.posX) * (vec.xCoord + 0.5 - mc.thePlayer.posX)
               + (vec.yCoord - mc.thePlayer.posY) * (vec.yCoord - mc.thePlayer.posY)
               + (vec.zCoord + 0.5 - mc.thePlayer.posZ) * (vec.zCoord + 0.5 - mc.thePlayer.posZ);
            if (d <= 1.0 && this.pickedTargetBlock != null || this.pickedTargetEntity != null && mc.theWorld.getEntityByID(this.pickedTargetEntity.getEntityId()) == null) {
               this.currentPath = null;
               this.autoTargetEntity = null;
               this.pickedTargetBlock = null;
               this.pickedTargetEntity = null;
               this.resetSearchCooldown();
            }
         }

         if (!this.activePathJobs.isEmpty()) {
            this.stepActivePathJobs();
         } else {
            if (this.searchCooldownTicks < 0) {
               this.searchCooldownTicks = 0;
            }

            if (this.searchCooldownTicks == 0) {
               this.startPathSearch();
            } else {
               this.searchCooldownTicks--;
            }

            if ((double)this.searchCooldownTicks > this.ticksBetweenSearches.getValue()) {
               this.searchCooldownTicks = (int)this.ticksBetweenSearches.getValue();
            }
         }
      } else {
         this.currentPath = null;
         this.autoTargetEntity = null;
         this.pickedTargetBlock = null;
         this.pickedTargetEntity = null;
         this.resetSearchCooldown();
      }
   }

   private void stepActivePathJobs() {
      java.util.ArrayList list = new java.util.ArrayList();
      java.util.ArrayList list2 = new java.util.ArrayList();

      for (int i = 0; i < this.activePathJobs.size(); i++) {
         TimedAStarPathFinder pathFinder = ((EntityPathJob)this.activePathJobs.get(i)).pathFinder;
         java.util.ArrayList list3 = pathFinder.computePathWithTimeout((long)this.maxSearchtimePerTick.getValue());
         if (list3 != null && !list3.isEmpty()) {
            list.add(list3);
            list2.add(((EntityPathJob)this.activePathJobs.get(i)).targetEntity);
            this.activePathJobs.remove(i);
            i--;
         } else if ((double)pathFinder.iterations >= this.maxSteps.getValue() || list3 != null && list3.isEmpty()) {
            this.activePathJobs.remove(i);
            i--;
         }
      }

      if (list.isEmpty() && this.activePathJobs.isEmpty()) {
         this.resetSearchCooldown();
         this.currentPath = null;
         this.autoTargetEntity = null;
      } else {
         if (!list.isEmpty()) {
            java.util.ArrayList list4 = null;
            Entity entity = null;
            double positiveinfinity = Double.POSITIVE_INFINITY;

            for (int j = 0; j < list.size(); j++) {
               java.util.ArrayList list5 = (java.util.ArrayList)list.get(j);
               if (!list5.isEmpty()) {
                  double gCost = ((PathNode)list5.get(0)).getGCost();
                  if (gCost < positiveinfinity) {
                     positiveinfinity = gCost;
                     list4 = list5;
                     entity = (Entity)list2.get(j);
                  }
               }
            }

            this.previousPath = this.currentPath;
            this.currentPath = this.convertPathNodesToVec3(list4);
            this.autoTargetEntity = entity;
            this.resetSearchCooldown();
         }
      }
   }

   private java.util.ArrayList convertPathNodesToVec3(java.util.ArrayList list) {
      java.util.ArrayList list2 = new java.util.ArrayList(list.size());

      for (PathNode pathNode : (Iterable<PathNode>)(list)) {
         list2.add(new Vec3((double)pathNode.getPos().x + 0.5, (double)pathNode.getPos().y, (double)pathNode.getPos().z + 0.5));
      }

      return list2;
   }

   private void resetSearchCooldown() {
      this.searchCooldownTicks = (int)this.ticksBetweenSearches.getValue();
      this.activePathJobs.clear();
   }

   private void startPathSearch() {
      this.activePathJobs.clear();
      Vec3 interpolatedPos = mc.thePlayer.getInterpolatedPos(1.0F);
      PathBlockPos pathBlockPos = new PathBlockPos((int)Math.floor(interpolatedPos.xCoord), (int)Math.floor(interpolatedPos.yCoord), (int)Math.floor(interpolatedPos.zCoord));
      PathBlockPos pathBlockPos2 = null;
      Entity entity = null;
      boolean flag = false;
      if (this.pickedTargetEntity != null) {
         Vec3 interpolatedPos2 = this.pickedTargetEntity.getInterpolatedPos(1.0F);
         pathBlockPos2 = new PathBlockPos((int)Math.floor(interpolatedPos2.xCoord), (int)Math.floor(interpolatedPos2.yCoord), (int)Math.floor(interpolatedPos2.zCoord));
      } else if (this.pickedTargetBlock != null) {
         pathBlockPos2 = this.pickedTargetBlock;
      } else if (this.autoTarget2.getValue()) {
         if (this.chooseTargetByPathLength.getValue()) {
            this.enqueueTopTargetPathJobs();
            if (this.activePathJobs.isEmpty()) {
               this.currentPath = null;
               this.autoTargetEntity = null;
            }

            return;
         }

         entity = this.findNearestTargetEntity();
         if (entity != null) {
            Vec3 interpolatedPos3 = entity.getInterpolatedPos(1.0F);
            pathBlockPos2 = new PathBlockPos((int)Math.floor(interpolatedPos3.xCoord), (int)Math.floor(interpolatedPos3.yCoord), (int)Math.floor(interpolatedPos3.zCoord));
            flag = entity instanceof EntityItem;
         }
      }

      if (pathBlockPos2 != null) {
         this.searchOriginPos = interpolatedPos;
         java.util.ArrayList activePathJobs2 = this.activePathJobs;
         boolean isToggled = Augustus.getInstance().getModuleManager().jesus.isToggled();
         activePathJobs2.add(new EntityPathJob(this, this.createPathFinder(pathBlockPos, pathBlockPos2, isToggled, flag), entity));
      } else {
         this.currentPath = null;
         this.autoTargetEntity = null;
      }
   }

   private void enqueueTopTargetPathJobs() {
      java.util.ArrayList list = new java.util.ArrayList();
      List list2;
      if (this.murderMystery.getValue() && ClientManagers.moduleManager.murdererFinder.isToggled() && MurderWeaponSlotUtil.findMurderWeaponSlot() == -1 && MurderWeaponSlotUtil.findBowSlot() != -1) {
         list2 = mc.theWorld.playerEntities;
      } else {
         list2 = mc.theWorld.loadedEntityList;
      }

      boolean flag = this.murderMystery.getValue() && ClientManagers.moduleManager.murdererFinder.isToggled() && MurderWeaponSlotUtil.findMurderWeaponSlot() == -1;

      for (Entity entity : (Iterable<Entity>)(list2)) {
         if (this.isValidTargetEntity(entity, flag)) {
            list.add(entity);
         }
      }

      java.util.ArrayList list3 = this.selectNearestTargets(list, (int)this.maxConsideredTargets.getValue(true));
      boolean isToggled = Augustus.getInstance().getModuleManager().jesus.isToggled();
      Vec3 vec = this.searchOriginPos = mc.thePlayer.getInterpolatedPos(1.0F);
      PathBlockPos pathBlockPos = new PathBlockPos((int)Math.floor(vec.xCoord), (int)Math.floor(vec.yCoord), (int)Math.floor(vec.zCoord));
      this.activePathJobs.clear();

      for (ScoredTargetEntity scoredTargetEntity : (Iterable<ScoredTargetEntity>)(list3)) {
         PathBlockPos pathBlockPos2 = new PathBlockPos(
            (int)Math.floor(ScoredTargetEntity.getEntity(scoredTargetEntity).posX), (int)Math.floor(ScoredTargetEntity.getEntity(scoredTargetEntity).posY), (int)Math.floor(ScoredTargetEntity.getEntity(scoredTargetEntity).posZ)
         );
         TimedAStarPathFinder timedAStarPathFinder = this.createPathFinder(pathBlockPos, pathBlockPos2, isToggled, ScoredTargetEntity.getEntity(scoredTargetEntity) instanceof EntityItem);
         this.activePathJobs.add(new EntityPathJob(this, timedAStarPathFinder, ScoredTargetEntity.getEntity(scoredTargetEntity)));
      }
   }

   private TimedAStarPathFinder createPathFinder(PathBlockPos pathBlockPos, PathBlockPos pathBlockPos2, boolean flag, boolean flag2) {
      TimedAStarPathFinder timedAStarPathFinder = new TimedAStarPathFinder(pathBlockPos, pathBlockPos2, flag, true, false);
      timedAStarPathFinder.smoothPath = true;
      timedAStarPathFinder.avoidFallDamage = this.discourageFalldamage.getValue();
      timedAStarPathFinder.fallDamagePenalty = 1.001 - this.discourageFalldamageFactor.getValue(true) / 100.0;
      timedAStarPathFinder.maxFallDistance = this.maxFallHeight.getValue(true);
      if (flag2) {
         timedAStarPathFinder.goalTolerance = 1.6;
      }

      return timedAStarPathFinder;
   }

   private java.util.ArrayList interpolatePathNodes(java.util.ArrayList list) {
      java.util.ArrayList list2 = new java.util.ArrayList(list.size());
      Vec3 vec = null;

      for (PathNode pathNode : (Iterable<PathNode>)(list)) {
         Vec3 vec2 = new Vec3((double)pathNode.getPos().x, (double)pathNode.getPos().y, (double)pathNode.getPos().z);
         double d;
         if (vec != null && (d = vec.squareDistanceTo(vec2)) > 2.0) {
            double d2 = Math.sqrt(d);
            double d3 = (vec2.xCoord - vec.xCoord) / d2;
            double d4 = (vec2.yCoord - vec.yCoord) / d2;
            double d5 = (vec2.zCoord - vec.zCoord) / d2;

            for (int i = 0; (double)i < d2 - 1.0; i++) {
               list2.add((vec = new Vec3(vec.xCoord + d3, vec.yCoord + d4, vec.zCoord + d5)).addVector(0.5, 0.0, 0.5));
            }
         }

         list2.add(vec2.addVector(0.5, 0.0, 0.5));
         vec = vec2;
      }

      return list2;
   }

   @Override
   public void onRender3D(float partialTicks) {
      GL11.glPushMatrix();
      GlStateManager.enableBlend();
      GlStateManager.blendFunc(770, 771);
      GL11.glEnable(2848);
      GlStateManager.disableDepth();
      GlStateManager.disableTexture2D();
      GlStateManager.disableAlpha();
      if (Config.isShaderPackLoaded()) {
         Shaders.disableTexture2D();
      }

      GlStateManager.disableCull();
      GlStateManager.depthMask(false);
      GL11.glLineWidth(2.0F);
      GL11.glTranslated(-mc.getRenderManager().getRenderPosX(), -mc.getRenderManager().getRenderPosY(), -mc.getRenderManager().getRenderPosZ());
      this.pathColor.getColorInt((byte)0);
      GlStateManager.color(
         (float)this.pathColor.getRed() / 255.0F,
         (float)this.pathColor.getGreen() / 255.0F,
         (float)this.pathColor.getBlue() / 255.0F,
         (float)this.pathColor.getAlpha() / 255.0F
      );
      if (this.currentPath != null && !this.currentPath.isEmpty()) {
         GL11.glBegin(3);
         if (this.pickedTargetEntity != null) {
            GL11.glVertex3d(
               MathStatisticsUtil.lerp(this.pickedTargetEntity.lastTickPosX, this.pickedTargetEntity.posX, (double)partialTicks),
               MathStatisticsUtil.lerp(this.pickedTargetEntity.lastTickPosY, this.pickedTargetEntity.posY, (double)partialTicks),
               MathStatisticsUtil.lerp(this.pickedTargetEntity.lastTickPosZ, this.pickedTargetEntity.posZ, (double)partialTicks)
            );
         } else if (this.autoTargetEntity != null) {
            GL11.glVertex3d(
               MathStatisticsUtil.lerp(this.autoTargetEntity.lastTickPosX, this.autoTargetEntity.posX, (double)partialTicks),
               MathStatisticsUtil.lerp(this.autoTargetEntity.lastTickPosY, this.autoTargetEntity.posY, (double)partialTicks),
               MathStatisticsUtil.lerp(this.autoTargetEntity.lastTickPosZ, this.autoTargetEntity.posZ, (double)partialTicks)
            );
         }

         double d = MathStatisticsUtil.lerp(mc.thePlayer.lastTickPosX, mc.thePlayer.posX, (double)partialTicks);
         double d2 = MathStatisticsUtil.lerp(mc.thePlayer.lastTickPosY, mc.thePlayer.posY, (double)partialTicks);
         double d3 = MathStatisticsUtil.lerp(mc.thePlayer.lastTickPosZ, mc.thePlayer.posZ, (double)partialTicks);
         if (ClientManagers.moduleManager.freeCam.isToggled()) {
            d = MathStatisticsUtil.lerp(ClientManagers.moduleManager.freeCam.realLastTickPosX, ClientManagers.moduleManager.freeCam.realPosX, (double)partialTicks);
            d2 = MathStatisticsUtil.lerp(ClientManagers.moduleManager.freeCam.realLastTickPosY, ClientManagers.moduleManager.freeCam.realPosY, (double)partialTicks);
            d3 = MathStatisticsUtil.lerp(ClientManagers.moduleManager.freeCam.realLastTickPosZ, ClientManagers.moduleManager.freeCam.realPosZ, (double)partialTicks);
         }

         java.util.ArrayList currentPath2 = this.currentPath;
         if (this.previousPath != null) {
            currentPath2 = new java.util.ArrayList(Math.max(currentPath2.size(), this.previousPath.size()));
            int n = Math.min(this.currentPath.size(), this.previousPath.size());

            for (int i = 0; i < n; i++) {
               currentPath2.add(((Vec3)this.previousPath.get(i)).lerp((Vec3)this.currentPath.get(i), (double)partialTicks));
            }

            if (n < this.currentPath.size()) {
               for (int j = n; j < this.currentPath.size(); j++) {
                  currentPath2.add(this.currentPath.get(j));
               }
            }
         }

         Vec3 vec = null;
         int n2 = -1;
         double positiveinfinity = Double.POSITIVE_INFINITY;

         for (int k = 0; k < currentPath2.size() - 1; k++) {
            Vec3 vec2 = (Vec3)currentPath2.get(k);
            Vec3 vec3 = (Vec3)currentPath2.get(k + 1);
            double xCoord = vec2.xCoord;
            double zCoord = vec2.zCoord;
            Vec3 vec4 = this.closestPointOnSegment(vec3.xCoord - vec2.xCoord, vec3.yCoord - vec2.yCoord, vec3.zCoord - vec2.zCoord, xCoord, vec2.yCoord, zCoord, d, d2, d3);
            double d7 = vec4.squareDistanceTo(d, d2, d3);
            if (d7 < positiveinfinity) {
               positiveinfinity = d7;
               vec = vec4;
               n2 = k;
            }
         }

         for (int m = 0; m < currentPath2.size(); m++) {
            Vec3 vec5 = (Vec3)currentPath2.get(m);
            GL11.glVertex3d(vec5.xCoord, vec5.yCoord + 0.01, vec5.zCoord);
            if (m == n2) {
               GL11.glVertex3d(vec.xCoord, vec.yCoord + 0.01, vec.zCoord);
               break;
            }
         }

         GL11.glVertex3d(d, d2, d3);
         GL11.glEnd();
      }

      this.targetColor.getColorInt((byte)0);
      GlStateManager.color(
         (float)this.targetColor.getRed() / 255.0F,
         (float)this.targetColor.getGreen() / 255.0F,
         (float)this.targetColor.getBlue() / 255.0F,
         (float)this.targetColor.getAlpha() / 255.0F
      );
      if (this.pickedTargetBlock != null) {
         RenderUtil.drawFilledBox(this.pickedBlockFaceBox);
      }

      GL11.glPopMatrix();
      GlStateManager.depthMask(true);
      GlStateManager.enableCull();
      GlStateManager.enableTexture2D();
      if (Config.isShaderPackLoaded()) {
         Shaders.enableTexture2D();
      }

      GlStateManager.enableDepth();
      GlStateManager.disableBlend();
      GlStateManager.blendFunc(770, 771);
      GL11.glDisable(2848);
      GlStateManager.enableAlpha();
   }

   private Vec3 closestPointOnSegment(double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8, double d9) {
      double d11 = MathHelper.clamp_double(((d * d7 + d2 * d8 + d3 * d9) - (d * d4 + d2 * d5 + d3 * d6)) / (d * d + d2 * d2 + d3 * d3), 0.0, 1.0);
      double d12 = d4 + d11 * d;
      double d13 = d5 + d11 * d2;
      double d14 = d6 + d11 * d3;
      return new Vec3(d12, d13, d14);
   }

   private java.util.ArrayList selectNearestTargets(java.util.ArrayList list, int n) {
      java.util.ArrayList list2 = new java.util.ArrayList(list.size());
      EntityPlayerSP thePlayer = mc.thePlayer;

      for (Entity entity : (Iterable<Entity>)(list)) {
         list2.add(new ScoredTargetEntity(this, entity, entity.getDistanceSqToEntity(thePlayer)));
      }

      return this.topKSelector.selectTopK(list2, n, true);
   }

   private Entity findNearestTargetEntity() {
      Entity entity = null;
      double d = 0.0;
      boolean flag = this.murderMystery.getValue() && ClientManagers.moduleManager.murdererFinder.isToggled() && MurderWeaponSlotUtil.findMurderWeaponSlot() == -1;

      for (Entity entity2 : (Iterable<Entity>)(mc.theWorld.loadedEntityList)) {
         if (entity2 != mc.thePlayer && this.isValidTargetEntity(entity2, flag)) {
            double distanceSqToEntityFloat = (double)mc.thePlayer.getDistanceSqToEntityFloat(entity2);
            if (distanceSqToEntityFloat < d || entity == null) {
               entity = entity2;
               d = distanceSqToEntityFloat;
            }
         }
      }

      return entity;
   }

   private boolean isValidTargetEntity(Entity entity, boolean flag) {
      if (entity == mc.thePlayer) {
         return false;
      } else if (!flag) {
         return this.targetSelectionSettings.getTargetFilter().matches(entity);
      } else if (MurderWeaponSlotUtil.findBowSlot() != -1 && !ClientManagers.moduleManager.murdererFinder.detectedMurderers.isEmpty()) {
         return ClientManagers.moduleManager.murdererFinder.isMurderer(entity.getName());
      } else if (!(entity instanceof EntityItem)) {
         return false;
      } else {
         EntityItem entityItem = (EntityItem)entity;
         return entityItem.getEntityItem() != null && entityItem.getEntityItem().getItem() == Items.gold_ingot;
      }
   }

   @Override
   public void onToggleKey(EventKey event) {
      if (event.getKeyCode() == this.pickTarget.getKeyCode()) {
         MovingObjectPosition hit = this.rayTracePickTarget();
         if (hit.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY) {
            if (hit.entityHit != this.pickedTargetEntity) {
               this.pickedTargetEntity = hit.entityHit;
               this.pickedTargetBlock = null;
               this.currentPath = null;
               this.autoTargetEntity = null;
               this.resetSearchCooldown();
               this.startPathSearch();
               this.onLateTick();
            }
         } else if (hit.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
            Vec3i directionVec = hit.sideHit.getDirectionVec();
            BlockPos blockPos = hit.getBlockPos();
            this.pickedTargetBlock = new PathBlockPos(blockPos.getX() + directionVec.getX(), blockPos.getY() + directionVec.getY(), blockPos.getZ() + directionVec.getZ());
            this.pickedBlockFaceBox = BlockOverlay.getFaceBoundingBox(mc.theWorld.getBlockState(blockPos).getBlock().getSelectedBoundingBox(mc.theWorld, blockPos), hit.sideHit);
            this.pickedTargetEntity = null;
            this.currentPath = null;
            this.autoTargetEntity = null;
            this.resetSearchCooldown();
            this.startPathSearch();
            this.onLateTick();
         }
      } else if (event.getKeyCode() == this.resetPickedTarget.getKeyCode() && (this.pickedTargetBlock != null || this.pickedTargetEntity != null)) {
         this.pickedTargetBlock = null;
         this.pickedTargetEntity = null;
         this.currentPath = null;
         this.autoTargetEntity = null;
         this.resetSearchCooldown();
         this.startPathSearch();
      }
   }

   private MovingObjectPosition rayTracePickTarget() {
      double posX = mc.thePlayer.posX;
      double posY = mc.thePlayer.posY;
      double posZ = mc.thePlayer.posZ;
      float rotationYaw = mc.thePlayer.rotationYaw;
      float rotationPitch = mc.thePlayer.rotationPitch;
      boolean flag = false;
      if (ClientManagers.moduleManager.freeCam.isToggled()) {
         flag = true;
         mc.thePlayer.posX = ClientManagers.moduleManager.freeCam.cameraPos.xCoord;
         mc.thePlayer.posY = ClientManagers.moduleManager.freeCam.cameraPos.yCoord;
         mc.thePlayer.posZ = ClientManagers.moduleManager.freeCam.cameraPos.zCoord;
         rotationYaw = ClientManagers.moduleManager.freeCam.cameraYaw;
         rotationPitch = ClientManagers.moduleManager.freeCam.cameraPitch;
      }

      MovingObjectPosition hit = RayTraceUtil.rayTrace(1.0F, rotationYaw, rotationPitch, 300.0, 300.0, 0.0);
      if (flag) {
         mc.thePlayer.posX = posX;
         mc.thePlayer.posY = posY;
         mc.thePlayer.posZ = posZ;
      }

      return hit;
   }

}
