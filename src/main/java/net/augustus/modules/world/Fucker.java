package net.augustus.modules.world;

import net.augustus.ClientManagers;
import net.augustus.events.EventClick;
import net.augustus.events.EventPacket;
import net.augustus.events.EventPacketDelay;
import net.augustus.events.listeners.ClickListener;
import net.augustus.events.listeners.PacketDelayListener;
import net.augustus.events.listeners.PacketListener;
import net.augustus.events.listeners.SilentSlotListener;
import net.augustus.events.listeners.TickListener;
import net.augustus.modules.combat.killaura.HitVecCandidate;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.utils.math.MathStatisticsUtil;
import net.augustus.utils.player.SilentSlotManager;
import net.augustus.utils.rotation.RayCastUtil;
import net.augustus.utils.rotation.RayTraceUtil;
import net.augustus.utils.rotation.Rotation;
import net.augustus.utils.rotation.RotationUtil;
import net.augustus.utils.world.BlockUtil;
import net.augustus.viaversion.ViaAugustus;
import com.viaversion.viaversion.api.protocol.version.ProtocolVersion;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import net.augustus.modules.Categorys;
import net.augustus.modules.RotationModule;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.StringValue;
import net.augustus.settings.DoubleValue;
import net.augustus.utils.TimeHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBed;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;

public class Fucker extends RotationModule implements ClickListener, PacketDelayListener, TickListener, PacketListener, SilentSlotListener {
   public final StringValue block;
   public final StringValue action;
   public final DoubleValue delay;
   public final DoubleValue aimRange;
   public final DoubleValue range;
   public final BooleanValue breakOnlyFreeBeds;
   public final BooleanValue throughWall;
   public final BooleanValue surrounding;
   public final BooleanValue instant;
   public final BooleanValue friendMyBlock;
   public final DoubleValue customID;
   private final BooleanValue silentSlotSwitching;
   public final BooleanValue bedBlink;
   public final DoubleValue bedBlinkTime;
   private final TimeHelper actionDelayTimer;
   private final TimeHelper bedBlinkTimer;
   private final TimeHelper postBreakTimer;
   public BlockPos targetBlockPos;
   private EnumFacing targetFacing;
   public MovingObjectPosition throughWallRayTrace;
   private boolean surroundingCleared;

   public Fucker() {
       super("Fucker", "Ficker", new Color(161, 17, 51), Categorys.WORLD);
      this.block = new StringValue(this, "Block", "Bed", new String[]{"Bed", "Cake", "Custom"});
      this.action = new StringValue(this, "Action", "Break", new String[]{"Break", "Click", "Use"});
      this.delay = new DoubleValue(this, "Delay", 500.0, 0.0, 1000.0, 0);
      this.aimRange = new DoubleValue(this, "AimRange", 5.5, 0.0, 6.0, 1);
      this.range = new DoubleValue(this, "Range", 4.5, 0.0, 6.0, 1);
      this.breakOnlyFreeBeds = new BooleanValue(this, "Break only free beds", false);
      BooleanValue booleanValue = new BooleanValue(this, "ThroughWall", true);
      SettingVisibilityCondition condition = () -> {
         return !this.breakOnlyFreeBeds.getValue();
      };
      this.throughWall = (BooleanValue)booleanValue.withVisibility(condition);
      this.surrounding = (BooleanValue)new BooleanValue(this, "Surrounding", false).withVisibility(() -> this.isSurroundingSettingVisible());
      this.instant = (BooleanValue)new BooleanValue(this, "Instant", true).withVisibility(() -> this.isInstantSettingVisible());
      this.friendMyBlock = new BooleanValue(this, "FriendMyBlock", true);
      this.customID = new DoubleValue(this, "CustomID", 26.0, 0.0, 400.0, 0).visibleWhen(() -> false);
      this.silentSlotSwitching = new BooleanValue(this, "SilentSlotSwitching", true);
      this.bedBlink = new BooleanValue(this, "BedBlink", false);
      DoubleValue bedBlinkTimeSetting = new DoubleValue(this, "BedBlinkTime", 10000.0, 0.0, 20000.0, 0);
      BooleanValue bedBlink2 = this.bedBlink;
      this.bedBlinkTime = bedBlinkTimeSetting.visibleWhen(() -> bedBlink2.getValue());
      this.actionDelayTimer = new TimeHelper();
      this.bedBlinkTimer = new TimeHelper();
      this.postBreakTimer = new TimeHelper();
      this.surroundingCleared = false;
   }

   @Override
   public void onEnable() {
      super.onEnable();
      if (mc.thePlayer != null) {
         this.targetBlockPos = null;
         this.targetFacing = null;
         this.bedBlinkTimer.reset();
         this.surroundingCleared = false;
         this.throughWallRayTrace = null;
      }
   }

   @Override
   public void onDisable() {
      super.onDisable();
   }

   @Override
   public void updateEventSubscriptions() {
      super.updateEventSubscriptions();
      this.toggleEventSubscription(7);
      this.toggleEventSubscription(25);
      this.toggleEventSubscription(40);
      this.toggleEventSubscription(26);
      this.toggleEventSubscription(34);
   }

   @Override
   public void onPacketSend(EventPacket event) {
      Packet packet = event.getPacket();
      if (packet instanceof C07PacketPlayerDigging) {
         C07PacketPlayerDigging packet2 = (C07PacketPlayerDigging)packet;
         if (packet2.getStatus() == C07PacketPlayerDigging.Action.STOP_DESTROY_BLOCK || mc.playerController.isInCreativeMode() && packet2.getStatus() == C07PacketPlayerDigging.Action.START_DESTROY_BLOCK) {
            BlockPos position = packet2.getPosition();
            if (this.surrounding.getValue() && !this.surroundingCleared) {
               this.surroundingCleared = true;
            }

            if (position != null) {
               if (this.isTargetBlockPos(position, this.getTargetBlockId())) {
                  this.postBreakTimer.reset();
               }
            }
         }
      }
   }

   @Override
   public void onIncomingPacketDelay(EventPacketDelay event) {
      EntityPlayerSP thePlayer = mc.thePlayer;
      MovingObjectPosition objectMouseOver = mc.objectMouseOver;
      BlockPos targetBlockPos2 = this.targetBlockPos;
      if (targetBlockPos2 != null
         && thePlayer != null
         && !thePlayer.isDead
         && this.bedBlink.getValue()
         && (
            thePlayer.getLastGroundPos() == null
               || thePlayer.getLastGroundPos()
                     .distanceTo(new Vec3((double)objectMouseOver.getBlockPos().getX(), (double)objectMouseOver.getBlockPos().getY(), (double)objectMouseOver.getBlockPos().getZ()))
                  > 20.0
               || !this.friendMyBlock.getValue()
         )
         && thePlayer.getPositionEyes(1.0F).distanceTo(new Vec3((double)targetBlockPos2.getX() + 0.5, (double)targetBlockPos2.getY() + 0.5, (double)targetBlockPos2.getZ() + 0.5)) < 4.5) {
         if (!this.bedBlinkTimer.reached((long)this.bedBlinkTime.getValue())) {
            if (this.postBreakTimer.reached(500L)) {
               event.setDelayed(true);
               return;
            }
         }
      }

      this.bedBlinkTimer.reset();
      event.setDelayed(false);
   }

   @Override
   public void onEarlyTick() {
      if (this.breakOnlyFreeBeds.getValue()) {
         this.throughWall.setValue(false);
      }

      this.setSuffix(this.block.getSelectedValue());
      Object obj = null;
      if ((!ClientManagers.moduleManager.killAura.isToggled() || ClientManagers.moduleManager.killAura.target == null) && !BlockUtil.isScaffoldActiveAndValid()) {
         obj = this.findNearestTargetBlock();
         this.targetBlockPos = obj == null ? null : new BlockPos((Vec3)obj);
      } else {
         this.targetBlockPos = null;
      }

      if (this.targetBlockPos != null) {
         AxisAlignedBB blockBoundingBox = this.getBlockBoundingBox(this.targetBlockPos);
         Vec3 positionEyes = mc.thePlayer.getPositionEyes(1.0F);
         double d = MathHelper.clamp_double(positionEyes.xCoord, blockBoundingBox.minX + 0.1, blockBoundingBox.maxX - 0.1);
         double d2 = MathHelper.clamp_double(positionEyes.yCoord, blockBoundingBox.minY + 0.1, blockBoundingBox.maxY - 0.1);
         double d3 = MathHelper.clamp_double(positionEyes.zCoord, blockBoundingBox.minZ + 0.1, blockBoundingBox.maxZ - 0.1);
         Rotation rotation = this.calculateRotationToPos(d, d2, d3);
         MovingObjectPosition hit = RayCastUtil.rayCast(1.0F, rotation);
         Rotation rotation2 = rotation.absDelta(ClientManagers.rotationController.getPreviousRotation());
         boolean value = this.breakOnlyFreeBeds.getValue();
         if (this.breakOnlyFreeBeds.getValue()) {
            Rotation previousRotation2 = ClientManagers.rotationController.getPreviousRotation();
            float f = 180.0F;
            Rotation rotationsToPos = RotationUtil.getRotationsToPos(d, d2, d3, previousRotation2, 180.0F, f, false);
            MovingObjectPosition hit2 = RayCastUtil.rayCast(1.0F, rotationsToPos);
            if (hit2 != null && hit2.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
               if (this.isTargetBlockPos(hit2.getBlockPos(), this.getTargetBlockId())) {
                  value = false;
               }
            }
         }

         if (!this.isThroughWallActive() && hit != null && hit.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
            if (!this.isTargetBlockPos(hit.getBlockPos(), this.getTargetBlockId())) {
               HitVecCandidate hitVecCandidate = new HitVecCandidate(
                  (double)this.targetBlockPos.getX() + 0.5 - mc.thePlayer.posX,
                  mc.thePlayer.posY + (double)mc.thePlayer.getEyeHeight(),
                  (double)this.targetBlockPos.getZ() + 0.5 - mc.thePlayer.posZ
               );
               List list = MathStatisticsUtil.sampleBoxSurfacePoints(blockBoundingBox, hitVecCandidate);
               Vec3 vec = this.selectBestVisibleHitVec(list);
               if (this.breakOnlyFreeBeds.getValue()) {
                  value = this.selectDirectVisibleHitVec(list) == null;
               }

               if (vec != null) {
                  rotation = this.calculateRotationToVec(vec);
               }
            }
         }

         if (value) {
            this.targetBlockPos = null;
         } else {
            ClientManagers.rotationController.requestRotation(rotation, (byte)3, this);
         }
      }
   }

   @Override
   public void onClick(EventClick event) {
      this.rotating = this.targetBlockPos != null && ClientManagers.rotationController.isOwnedBy(this);
      if ((!ClientManagers.moduleManager.killAura.isToggled() || ClientManagers.moduleManager.killAura.target == null) && !BlockUtil.isScaffoldActiveAndValid() && this.targetBlockPos != null) {
         event.setIgnoreVanillaClicks(true);
         event.setRightMouseDown(false);
         event.setLeftMouseDown(false);
         if (this.isThroughWallActive()) {
            MovingObjectPosition hit = RayTraceUtil.rayTraceBlock(this.targetBlockPos, ClientManagers.rotationController.getPreviousRotation().yaw, ClientManagers.rotationController.getPreviousRotation().pitch, this.range.getValue());
            if (hit == null) {
               return;
            }

            this.throughWallRayTrace = hit;
            if (hit.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
               switch (this.action.getSelectedValue()) {
                  case "Break":
                     if (this.instant.getValue()) {
                        EnumFacing sideHit = mc.objectMouseOver.sideHit;
                        this.sendInstantBreakPackets(this.targetBlockPos, sideHit);
                     } else {
                        if (!mc.playerController.isHittingBlock) {
                           event.addLeftClicks((byte)1);
                           Runnable runnable = () -> {
                              if (ViaAugustus.targetProtocolVersion.olderThanOrEqualTo(ProtocolVersion.v1_8)) {
                                 mc.thePlayer.swingItem();
                              }

                              mc.playerController.clickBlock(this.targetBlockPos, hit.sideHit);
                              if (ViaAugustus.targetProtocolVersion.newerThanOrEqualTo(ProtocolVersion.v1_9)) {
                                 mc.thePlayer.swingItem();
                              }
                           };
                           event.setLeftClickAction(runnable);
                        }

                        Runnable runnable2 = () -> {
                           boolean flag4 = mc.currentScreen == null && mc.inGameHasFocus;
                           int currentItem = mc.thePlayer.inventory.currentItem;
                           BlockPos targetBlockPos4 = this.targetBlockPos;
                           mc.sendClickBlockToControllerWithSlot(flag4, hit, targetBlockPos4, currentItem);
                        };
                        event.setBlockBreakAction(runnable2);
                        this.targetFacing = hit.sideHit;
                     }
                     break;
                  case "Use":
                     if (this.actionDelayTimer.reached((long)this.delay.getValue()) && mc.theWorld.getBlockState(this.targetBlockPos).getBlock().getMaterial() != Material.air) {
                        event.addRightClicks((byte)1);
                        Runnable runnable3 = () -> {
                           ItemStack heldItem = mc.thePlayer.getHeldItem();
                           int n = heldItem != null ? heldItem.stackSize : 0;
                           if (mc.playerController.onPlayerRightClick(mc.thePlayer, mc.theWorld, heldItem, this.targetBlockPos, hit.sideHit, hit.hitVec)) {
                              mc.thePlayer.swingItem();
                              this.actionDelayTimer.reset();
                           }

                           if (heldItem != null) {
                              if (heldItem.stackSize == 0) {
                                 mc.thePlayer.inventory.mainInventory[mc.thePlayer.inventory.currentItem] = null;
                              } else if (heldItem.stackSize != n || mc.playerController.isInCreativeMode()) {
                                 mc.entityRenderer.itemRenderer.resetEquippedProgress();
                              }
                           }
                        };
                        event.setRightClickAction(runnable3);
                     }
                     break;
                  default:
                     if (this.actionDelayTimer.reached((long)this.delay.getValue())) {
                        event.addLeftClicks((byte)1);
                        Runnable runnable4 = () -> {
                           if (ViaAugustus.targetProtocolVersion.olderThanOrEqualTo(ProtocolVersion.v1_8)) {
                              mc.thePlayer.swingItem();
                           }

                           mc.playerController.clickBlock(this.targetBlockPos, hit.sideHit);
                           if (ViaAugustus.targetProtocolVersion.newerThanOrEqualTo(ProtocolVersion.v1_9)) {
                              mc.thePlayer.swingItem();
                           }
                        };
                        event.setLeftClickAction(runnable4);
                        this.actionDelayTimer.reset();
                     }
               }
            }
         } else if (mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK && !mc.objectMouseOver.getBlockPos().equals(this.targetBlockPos)) {
            if (this.instant.getValue()) {
               EnumFacing sideHit2 = mc.objectMouseOver.sideHit;
               this.sendInstantBreakPackets(mc.objectMouseOver.getBlockPos(), sideHit2);
            } else {
               event.setLeftMouseDown(true);
            }
         } else if (mc.objectMouseOver != null && mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
            switch (this.action.getSelectedValue()) {
               case "Break":
                  if (this.instant.getValue()) {
                     EnumFacing sideHit3 = mc.objectMouseOver.sideHit;
                     this.sendInstantBreakPackets(mc.objectMouseOver.getBlockPos(), sideHit3);
                  } else {
                     this.targetFacing = mc.objectMouseOver.sideHit;
                     event.setLeftMouseDown(true);
                  }
                  break;
               case "Use":
                  if (this.actionDelayTimer.reached((long)this.delay.getValue())) {
                     event.addRightClicks((byte)1);
                     this.actionDelayTimer.reset();
                  }
                  break;
               default:
                  if (this.actionDelayTimer.reached((long)this.delay.getValue())) {
                     event.addLeftClicks((byte)1);
                     this.actionDelayTimer.reset();
                  }
            }
         }
      }
   }

   private AxisAlignedBB getBlockBoundingBox(BlockPos pos) {
      Block block2 = mc.theWorld.getBlockState(pos).getBlock();
      AxisAlignedBB box = new AxisAlignedBB(
         (double)pos.getX() + block2.getBlockBoundsMinX(),
         (double)pos.getY() + block2.getBlockBoundsMinY(),
         (double)pos.getZ() + block2.getBlockBoundsMinZ(),
         (double)pos.getX() + block2.getBlockBoundsMaxX(),
         (double)pos.getY() + block2.getBlockBoundsMaxY(),
         (double)pos.getZ() + block2.getBlockBoundsMaxZ()
      );
      if (block2 instanceof BlockBed) {

         for (EnumFacing facing : new EnumFacing[]{EnumFacing.NORTH, EnumFacing.SOUTH, EnumFacing.WEST, EnumFacing.EAST}) {
            Block block3 = mc.theWorld.getBlockState(pos.offset(facing, 1)).getBlock();
            if (block3 instanceof BlockBed) {
               BlockPos pos2 = pos.offset(facing, 1);
               AxisAlignedBB box2 = new AxisAlignedBB(
                  (double)pos2.getX() + block3.getBlockBoundsMinX(),
                  (double)pos2.getY() + block3.getBlockBoundsMinY(),
                  (double)pos2.getZ() + block3.getBlockBoundsMinZ(),
                  (double)pos2.getX() + block3.getBlockBoundsMaxX(),
                  (double)pos2.getY() + block3.getBlockBoundsMaxY(),
                  (double)pos2.getZ() + block3.getBlockBoundsMaxZ()
               );
               box = box.union(box2);
               break;
            }
         }
      }

      return box;
   }

   private Vec3 findBestAimPoint(BlockPos pos, AxisAlignedBB box) {
      List list = MathStatisticsUtil.sampleBoxSurfacePoints(box, new HitVecCandidate(
         (double)pos.getX() + 0.5 - mc.thePlayer.posX, mc.thePlayer.posY + (double)mc.thePlayer.getEyeHeight(), (double)pos.getZ() + 0.5 - mc.thePlayer.posZ
      ));
      return this.selectBestVisibleHitVec(list);
   }

   private Vec3 selectBestVisibleHitVec(List list) {
      Vec3 vec = null;
      double d = 15.0;

      for (HitVecCandidate hitVecCandidate : (Iterable<HitVecCandidate>)(list)) {
         double d2 = hitVecCandidate.z;
         double d3 = hitVecCandidate.y;
         Rotation rotation = this.calculateRotationToPos(hitVecCandidate.x, d3, d2);
         MovingObjectPosition hit = mc.thePlayer.rayTraceCustom((double)mc.playerController.getBlockReachDistance(), 1.0F, rotation.yaw, rotation.pitch);
         if (hit != null && hit.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
            if (this.isTargetBlockPos(hit.getBlockPos(), this.getTargetBlockId())) {
               double d5 = Math.sqrt(
                  Math.pow(hitVecCandidate.x - mc.thePlayer.posX, 2.0)
                     + Math.pow(hitVecCandidate.y - (mc.thePlayer.posY + (double)mc.thePlayer.getEyeHeight()), 2.0)
                     + Math.pow(hitVecCandidate.z - mc.thePlayer.posZ, 2.0)
               );
               if (d5 <= d) {
                  d = d5;
                  vec = new Vec3(hitVecCandidate.x, hitVecCandidate.y, hitVecCandidate.z);
               }
            }
         }
      }

      return vec;
   }

   private Vec3 findDirectVisibleAimPoint(BlockPos pos, AxisAlignedBB box) {
      List list = MathStatisticsUtil.sampleBoxSurfacePoints(box, new HitVecCandidate(
         (double)pos.getX() + 0.5 - mc.thePlayer.posX, mc.thePlayer.posY + (double)mc.thePlayer.getEyeHeight(), (double)pos.getZ() + 0.5 - mc.thePlayer.posZ
      ));
      return this.selectDirectVisibleHitVec(list);
   }

   private Vec3 selectDirectVisibleHitVec(List list) {
      Vec3 vec = null;
      double d = 15.0;

      for (HitVecCandidate hitVecCandidate : (Iterable<HitVecCandidate>)(list)) {
         double d2 = hitVecCandidate.x;
         double d3 = hitVecCandidate.y;
         double d4 = hitVecCandidate.z;
         Rotation previousRotation = ClientManagers.rotationController.getPreviousRotation();
         float f = 180.0F;
         Rotation rotationsToPos = RotationUtil.getRotationsToPos(d2, d3, d4, previousRotation, 180.0F, f, false);
         MovingObjectPosition hit = mc.thePlayer.rayTraceCustom((double)mc.playerController.getBlockReachDistance(), 1.0F, rotationsToPos.yaw, rotationsToPos.pitch);
         if (hit != null && hit.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
            if (this.isTargetBlockPos(hit.getBlockPos(), this.getTargetBlockId())) {
               double d5 = Math.sqrt(
                  Math.pow(hitVecCandidate.x - mc.thePlayer.posX, 2.0)
                     + Math.pow(hitVecCandidate.y - (mc.thePlayer.posY + (double)mc.thePlayer.getEyeHeight()), 2.0)
                     + Math.pow(hitVecCandidate.z - mc.thePlayer.posZ, 2.0)
               );
               if (d5 <= d) {
                  d = d5;
                  vec = new Vec3(hitVecCandidate.x, hitVecCandidate.y, hitVecCandidate.z);
               }
            }
         }
      }

      return vec;
   }

   private int findBestToolSlot() {
      double d = 1.0;
      int n = -1;
      MovingObjectPosition hit = this.isThroughWallActive() && this.surrounding.getValue() ? this.throughWallRayTrace : mc.objectMouseOver;
      if (hit != null && hit.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
         for (int i = 36; i < mc.thePlayer.inventoryContainer.inventorySlots.size(); i++) {
            ItemStack stack = mc.thePlayer.inventoryContainer.getSlot(i).getStack();
            if (stack != null && (stack.getItem() instanceof ItemTool || stack.getItem() instanceof ItemShears)) {
               if (this.scoreToolForBlock(stack, hit) > d) {
                  d = this.scoreToolForBlock(stack, hit);
                  n = i - 36;
               }
            }
         }
      }

      return n;
   }

   private double scoreToolForBlock(ItemStack stack, MovingObjectPosition hit) {
      double d = 0.0;
      if (stack != null && (stack.getItem() instanceof ItemTool || stack.getItem() instanceof ItemSword || stack.getItem() instanceof ItemShears)) {
         double strVsBlock = (double)stack.getItem().getStrVsBlock(stack, mc.theWorld.getBlockState(hit.getBlockPos()).getBlock());
         Item item = stack.getItem();
         if (!(item instanceof ItemAxe) && !(item instanceof ItemPickaxe) && !(stack.getItem() instanceof ItemSpade)) {
            d = strVsBlock;
         } else {
            d += strVsBlock + (double)(strVsBlock > 1.0 ? EnchantmentHelper.getEnchantmentLevel(Enchantment.efficiency.effectId, stack) : 0);
         }

         d += (double)EnchantmentHelper.getEnchantmentLevel(Enchantment.unbreaking.effectId, stack) / 11.0;
         d += (double)EnchantmentHelper.getEnchantmentLevel(Enchantment.silkTouch.effectId, stack) / 11.0;
         d += (double)EnchantmentHelper.getEnchantmentLevel(Enchantment.sharpness.effectId, stack) / 33.0;
         return d - (double)stack.getItemDamage() / 10000.0;
      } else {
         return 1.0;
      }
   }

   private Vec3 findNearestTargetBlock() {
      BlockPos pos = new BlockPos(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ);
      ArrayList list = new ArrayList();
      int targetBlockId = this.getTargetBlockId();
      int value = (int)this.aimRange.getValue();

      for (int i = pos.getX() - value; i < pos.getX() + value; i++) {
         for (int j = pos.getY() - value; j < pos.getY() + value; j++) {
            for (int k = pos.getZ() - value; k < pos.getZ() + value; k++) {
               if (this.isTargetBlockAt(i, j, k, targetBlockId)) {
                  Vec3 vec = new Vec3((double)i, (double)j, (double)k);
                  if (this.friendMyBlock.getValue() && mc.thePlayer.getLastGroundPos() != null) {
                     if (mc.thePlayer.getLastGroundPos().distanceTo(vec) > 30.0) {
                        list.add(vec);
                     }
                  } else {
                     list.add(vec);
                  }
               }
            }
         }
      }

      list.sort(Comparator.comparingDouble(obj -> mc.thePlayer.getDistance(((Vec3)obj).xCoord, ((Vec3)obj).yCoord, ((Vec3)obj).zCoord)));
      if (list.isEmpty()) {
         this.surroundingCleared = false;
         return null;
      } else {
         Vec3 vec2 = (Vec3)list.get(0);
         if (this.surrounding.getValue() && !this.surroundingCleared && vec2 != null) {
            BlockPos pos2 = new BlockPos(vec2.xCoord, vec2.yCoord, vec2.zCoord);
            if (this.findBestAimPoint(pos2, this.getBlockBoundingBox(pos2)) == null) {
               ArrayList list2 = new ArrayList();

               for (EnumFacing facing : EnumFacing.VALUES) {
                  if (facing != EnumFacing.DOWN) {
                     BlockPos pos3 = pos2.offset(facing);
                     if (!this.isTargetBlockPos(pos3, targetBlockId) && mc.theWorld.getBlockState(pos3).getBlock().getMaterial() != Material.air) {
                        list2.add(new Vec3((double)pos3.getX() + 0.5, (double)pos3.getY() + 0.5, (double)pos3.getZ() + 0.5));
                     }
                  }
               }

               list2.sort(Comparator.comparingDouble(obj2 -> mc.thePlayer.getDistance(((Vec3)obj2).xCoord, ((Vec3)obj2).yCoord, ((Vec3)obj2).zCoord)));
               if (!list2.isEmpty()) {
                  return (Vec3)list2.get(0);
               }
            }
         }

         return vec2;
      }
   }

   private int getTargetBlockId() {
      String selectedValue = this.block.getSelectedValue();
      int n;
      switch (selectedValue) {
         case "Bed":
            n = 26;
            break;
         case "Cake":
            n = 92;
            break;
         default:
            n = (int)this.customID.getValue();
      }

      return n;
   }

   private boolean isTargetBlockAt(int n, int n2, int n3, int n4) {
      return mc.theWorld.getBlockState2(n, n2, n3).getBlock().equals(Block.getBlockById(n4));
   }

   private boolean isTargetBlockPos(BlockPos pos, int n) {
      if (pos != null) {
         int x = pos.getX();
         int z = pos.getZ();
         return this.isTargetBlockAt(x, pos.getY(), z, n);
      } else {
         return false;
      }
   }

   private void sendInstantBreakPackets(BlockPos pos, EnumFacing facing) {
      if (this.actionDelayTimer.reached((long)this.delay.getValue())) {
         mc.thePlayer.swingItem();
         mc.thePlayer.sendQueue.addToSendQueue(new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.START_DESTROY_BLOCK, pos, facing));
         mc.thePlayer.sendQueue.addToSendQueue(new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.STOP_DESTROY_BLOCK, pos, facing));
         this.actionDelayTimer.reset();
      }
   }

   @Override
   public void onPostSilentSlot() {
      if (this.targetBlockPos != null && (!ClientManagers.moduleManager.killAura.isToggled() || ClientManagers.moduleManager.killAura.target == null) && !BlockUtil.isScaffoldActiveAndValid()) {
         int n = this.findBestToolSlot();
         if (n != -1) {
            SilentSlotManager.requestSlot(this, (byte)0, n, this.silentSlotSwitching.getValue());
         }
      }
   }

   public EnumFacing getTargetFacing() {
      return this.targetFacing;
   }

   private boolean isThroughWallActive() {
      return this.throughWall.getValue() && !this.breakOnlyFreeBeds.getValue();
   }

   public MovingObjectPosition overrideRayTraceResult(MovingObjectPosition hit) {
      return this.isToggled() && this.isThroughWallActive() && this.surrounding.getValue() && this.throughWallRayTrace != null ? this.throughWallRayTrace : hit;
   }

   private boolean isInstantSettingVisible() {
      return this.action.getSelectedValue().equals("Break");
   }

   private boolean isSurroundingSettingVisible() {
      return !this.breakOnlyFreeBeds.getValue() && this.throughWall.getValue();
   }


}
