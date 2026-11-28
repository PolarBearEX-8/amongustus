package net.augustus.modules.player;

import net.minecraft.util.MovingObjectPosition;
import java.awt.Color;
import java.util.OptionalInt;
import java.util.function.Predicate;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.utils.rotation.RotationEngine;
import net.augustus.events.EventPacket;
import net.augustus.events.listeners.ClickListener;
import net.augustus.events.listeners.BlockBoundingBoxListener;
import net.augustus.ClientManagers;
import net.augustus.events.listeners.SilentSlotListener;
import net.augustus.events.EventClick;
import net.augustus.events.EventPacketDelay;
import net.augustus.events.EventBlockBoundingBox;
import net.augustus.events.listeners.PacketDelayListener;
import net.augustus.events.listeners.MoveEntityListener;
import net.augustus.events.listeners.MoveInputListener;
import net.augustus.events.listeners.UpdateListener;
import net.augustus.utils.rotation.RayTraceUtil;
import net.augustus.events.EventMoveInput;
import net.augustus.utils.player.PlayerUtil;
import net.augustus.events.listeners.TickListener;
import net.augustus.events.EventMoveEntity;
import net.augustus.utils.player.ContainerSlotQuery;
import net.augustus.utils.player.SilentSlotManager;
import net.augustus.events.listeners.PacketListener;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.utils.rotation.Rotation;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.StringValue;
import net.augustus.settings.SettingGroup;
import net.augustus.settings.DoubleValue;
import net.augustus.utils.TimeHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;

public class NoFall extends Module implements ClickListener, TickListener, PacketListener, SilentSlotListener, BlockBoundingBoxListener, MoveEntityListener, UpdateListener, PacketDelayListener, MoveInputListener {
   public StringValue mode;
   private final DoubleValue collisionFallDistance;
   public final DoubleValue minFallDist;
   public final DoubleValue minLegitFallDist;
   private final SettingGroup rotations;
   public final DoubleValue delay;
   private final BooleanValue useWebs;
   private final BooleanValue sneakWhileItemPlace;
   private final BooleanValue silentSlotSwitching;
   private final StringValue blinkMode;
   private final DoubleValue blinkStartFallDistance;
   private RotationEngine rotationEngine;
   private BlockPos lastAimedBlockPos;
   private int ticksSinceLastPlace;
   private double collisionFloorY;
   private int bucketHotbarSlot;
   private BlockPos predictedLandingBlockPos;
   private int vulcanResetStage;
   private final TimeHelper timeHelper;

   public NoFall() {
       super("NoFall", "KeinFallen", new Color(222, 80, 95, 255), Categorys.PLAYER);
      this.mode = new StringValue(
         this, "Mode", "OnGround", new String[]{"OnGround1", "OnGround2", "NoGround", "Stop", "Legit", "Verus", "AAC3", "Collision", "Vulcan"}
      );
      DoubleValue collisionFallDistanceSetting = new DoubleValue(this, "CollisionFallDistance", 2.0, 0.0, 6.0, 2).named("FallDistance");
      SettingVisibilityCondition condition = () -> {
         return this.mode.getSelectedValue().equals("Collision");
      };
      this.collisionFallDistance = collisionFallDistanceSetting.visibleWhen(condition);
      this.minFallDist = new DoubleValue(this, "MinFallDist", 2.0, 1.0, 24.0, 0).visibleWhen(() -> this.isMinFallDistVisible());
      this.minLegitFallDist = new DoubleValue(this, "MinLegitFallDist", 6.0, 2.0, 24.0, 1).named("MinFallDist").visibleWhen(() -> this.isMinLegitFallDistVisible());
      this.rotations = new SettingGroup(this, "Rotations", true).visibleWhen(() -> this.isRotationsVisible());
      DoubleValue delaySetting = new DoubleValue(this, "Delay", 110.0, 0.0, 2000.0, 0);
      SettingVisibilityCondition condition5 = () -> {
         return this.mode.getSelectedValue().equals("Legit");
      };
      this.delay = delaySetting.visibleWhen(condition5);
      this.useWebs = (BooleanValue)new BooleanValue(this, "UseWebs", true).withVisibility(() -> this.isUseWebsVisible());
      this.sneakWhileItemPlace = (BooleanValue)new BooleanValue(this, "SneakWhileItemPlace", true).withVisibility(() -> this.isSneakWhileItemPlaceVisible());
      BooleanValue booleanValue3 = new BooleanValue(this, "SilentSlotSwitching", true);
      SettingVisibilityCondition condition8 = () -> {
         return this.mode.getSelectedValue().equals("Legit");
      };
      this.silentSlotSwitching = (BooleanValue)booleanValue3.withVisibility(condition8);
      this.blinkMode = new StringValue(this, "BlinkMode", "None", new String[]{"None", "OnlyIncoming", "OnlyOutgoing", "Both"});
      DoubleValue blinkStartFallDistance2 = new DoubleValue(this, "BlinkStartFallDistance", 3.0, 1.0, 6.0, 2);
      SettingVisibilityCondition condition9 = () -> {
         return !this.blinkMode.getSelectedValue().equals("None");
      };
      this.blinkStartFallDistance = blinkStartFallDistance2.visibleWhen(condition9);
      this.rotationEngine = new RotationEngine(this, this.rotations);
      this.lastAimedBlockPos = null;
      this.collisionFloorY = 0.0;
      this.bucketHotbarSlot = 0;
      this.vulcanResetStage = 0;
      this.timeHelper = new TimeHelper();
   }

   public static void init() {
   }

   @Override
   public void onEnable() {
      if (mc.thePlayer != null) {
         this.bucketHotbarSlot = mc.thePlayer.inventory.currentItem;
         this.setSuffix(this.mode.getSelectedValue());
      }
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(7);
      this.toggleEventSubscription(40);
      this.toggleEventSubscription(26);
      this.toggleEventSubscription(34);
      this.toggleEventSubscription(3);
      this.toggleEventSubscription(20);
      this.toggleEventSubscription(43);
      this.toggleEventSubscription(25);
      this.toggleEventSubscription(21);
   }

   @Override
   public void onIncomingPacketDelay(EventPacketDelay event) {
      EntityPlayerSP thePlayer = mc.thePlayer;
      if (mc.theWorld != null && thePlayer != null && thePlayer.ticksExisted >= 100) {
         if ((double)thePlayer.clientFallDistance > this.blinkStartFallDistance.getValue() && (this.blinkMode.getSelectedValue().equals("OnlyIncoming") || this.blinkMode.getSelectedValue().equals("Both"))) {
            event.setDelayed(true);
         }
      }
   }

   @Override
   public void onOutgoingPacketDelay(EventPacketDelay event) {
      EntityPlayerSP thePlayer = mc.thePlayer;
      if (mc.theWorld != null && thePlayer != null && thePlayer.ticksExisted >= 100) {
         if ((double)thePlayer.clientFallDistance > this.blinkStartFallDistance.getValue() && (this.blinkMode.getSelectedValue().equals("OnlyOutgoing") || this.blinkMode.getSelectedValue().equals("Both"))) {
            event.setDelayed(true);
         }
      }
   }

   @Override
   public void onPreMoveInput(EventMoveInput event) {
      if (this.mode.getSelectedValue().equals("Legit") && this.sneakWhileItemPlace.getValue() && mc.isNotInGui() && this.bucketHotbarSlot != -1) {
         event.setSneak(true);
      }
   }

   @Override
   public void onPacketSend(EventPacket event) {
      Packet packet = event.getPacket();
      if (mc.thePlayer != null && mc.theWorld != null) {
         switch (this.mode.getSelectedValue()) {
            case "NoGround":
               if (packet instanceof C03PacketPlayer) {
                  ((C03PacketPlayer)packet).setOnGround(false);
                  mc.thePlayer.fallDistance = -1.0F;
               }
               break;
            case "Verus":
               if (packet instanceof C03PacketPlayer && mc.thePlayer.fallDistance > 2.0F) {
                  if (!(packet instanceof C03PacketPlayer.C06PacketPlayerPosLook) && !(packet instanceof C03PacketPlayer.C05PacketPlayerLook) && !(packet instanceof C03PacketPlayer.C04PacketPlayerPosition)) {
                     ((C03PacketPlayer)packet).setOnGround(true);
                     mc.thePlayer.fallDistance = 0.0F;
                  } else {
                     event.cancel();
                     mc.thePlayer.sendQueue.addToSendQueueUnchecked(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY + 1.0, mc.thePlayer.posZ, true));
                     mc.thePlayer.fallDistance = 0.0F;
                  }
               }
               break;
            case "OnGround2":
               if (mc.thePlayer.fallDistance > 3.0F && packet instanceof C03PacketPlayer) {
                  ((C03PacketPlayer)packet).setOnGround(true);
               }
               break;
            case "Vulcan":
               if (packet instanceof C03PacketPlayer) {
                  C03PacketPlayer packet5 = (C03PacketPlayer)packet;
                  if (mc.thePlayer.fallDistance > 3.0F) {
                     packet5.setOnGround(true);
                     mc.thePlayer.fallDistance = 0.0F;
                     this.vulcanResetStage = 1;
                  }

                  if (this.vulcanResetStage == 1) {
                     mc.thePlayer.motionY = -0.011;
                     this.vulcanResetStage = 0;
                  }
               }
         }
      }
   }

   @Override
   public void onBlockBoundingBox(EventBlockBoundingBox event) {
      if (this.mode.getSelectedValue().equals("Collision")
         && mc.thePlayer != null
         && (double)mc.thePlayer.fallDistance > this.collisionFallDistance.getValue()
         && event.getBlock() instanceof BlockAir
         && (double)(event.getBlockPos().getY() + 1) < this.collisionFloorY) {
         BlockPos blockPos = event.getBlockPos();
         AxisAlignedBB box = new AxisAlignedBB(
            (double)blockPos.getX(),
            (double)blockPos.getY(),
            (double)blockPos.getZ(),
            (double)(blockPos.getX() + 1),
            (double)(blockPos.getY() + 1),
            (double)(blockPos.getZ() + 1)
         );
         event.setBoundingBox(box);
      }
   }

   @Override
   public void onPreMoveEntity(EventMoveEntity event) {
      if (this.mode.getSelectedValue().equals("Collision") && (double)mc.thePlayer.fallDistance <= this.collisionFallDistance.getValue()) {
         this.collisionFloorY = mc.thePlayer.posY + event.getY();
      }
   }

   @Override
   public void onTick() {
      if (mc.thePlayer != null) {
         switch (this.mode.getSelectedValue()) {
            case "OnGround1":
               if ((double)mc.thePlayer.fallDistance > this.minFallDist.getValue()) {
                  mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer(true));
               }
               break;
            case "Stop":
               if (mc.thePlayer.fallDistance > 2.0F) {
                  mc.thePlayer.motionY = 0.0;
                  mc.thePlayer.onGround = true;
                  mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer(true));
                  mc.thePlayer.fallDistance = 0.0F;
               }
               break;
            case "AAC3":
               if (!mc.isSingleplayer() && mc.thePlayer.fallDistance > 2.0F) {
                  mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, Double.NaN, mc.thePlayer.posZ, false));
                  mc.thePlayer.fallDistance = -99999.0F;
               }
         }
      }
   }

   @Override
   public void onEarlyTick() {
      this.setSuffix(this.mode.getSelectedValue());
      if (mc.thePlayer != null && this.mode.getSelectedValue().equals("Legit")) {
         boolean flag = false;
         IBlockState blockState = mc.theWorld.getBlockState(new BlockPos(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ));
         if (blockState.getBlock() instanceof BlockLiquid) {
            flag = (Integer)blockState.getValue(BlockLiquid.LEVEL) == 0;
         }

         this.predictedLandingBlockPos = this.predictLandingBlock();
         if (!((double)mc.thePlayer.fallDistance > this.minLegitFallDist.getValue())) {
            if (!mc.thePlayer.isInWater() || !flag) {
               return;
            }

            if (this.timeHelper.reached(7000L)) {
               return;
            }
         }

         if (this.predictedLandingBlockPos != null && this.findSaveItemHotbarSlot() != -1) {
            RotationEngine rotationEngine2 = this.rotationEngine;
            float rotationYaw = mc.thePlayer.rotationYaw;
            Rotation previousRotation = ClientManagers.rotationController.getPreviousRotation();
            Rotation rotation = rotationEngine2.rotateTowards(rotationYaw, 90.0F, previousRotation);
            ClientManagers.rotationController.requestRotation(rotation, (byte)4, this);
         }
      }
   }

   @Override
   public void onPostSilentSlot() {
      this.bucketHotbarSlot = -1;
      if (mc.thePlayer != null && this.mode.getSelectedValue().equals("Legit")) {
         boolean flag = false;
         IBlockState blockState = mc.theWorld.getBlockState(new BlockPos(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ));
         if (blockState.getBlock() instanceof BlockLiquid) {
            flag = (Integer)blockState.getValue(BlockLiquid.LEVEL) == 0;
         }

         boolean shouldUseBucket = (double)mc.thePlayer.fallDistance > this.minLegitFallDist.getValue()
            || (mc.thePlayer.isInWater() && flag && !this.timeHelper.reached(7000L));
         if (shouldUseBucket && this.predictedLandingBlockPos != null && this.findSaveItemHotbarSlot() != -1 && mc.objectMouseOver != null) {
            if (mc.thePlayer.fallDistance == 0.0F && flag && this.lastAimedBlockPos != null && mc.objectMouseOver.getBlockPos() != null && this.lastAimedBlockPos.equalsPos(mc.objectMouseOver.getBlockPos())) {
               this.bucketHotbarSlot = this.findSaveItemHotbarSlot();
            }

            if ((double)mc.thePlayer.fallDistance > this.minLegitFallDist.getValue() && !flag) {
               Rotation currentRotation = ClientManagers.rotationController.getCurrentRotation();
               if (RayTraceUtil.rayTrace(1.0F, currentRotation, 10.0, 10.0, 0.0).typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
                  this.bucketHotbarSlot = this.findSaveItemHotbarSlot();
               }
            }
         }

         if (this.bucketHotbarSlot != -1) {
            SilentSlotManager.requestSlot(this, (byte)1, this.bucketHotbarSlot, this.silentSlotSwitching.getValue());
         }
      }

      this.lastAimedBlockPos = mc.objectMouseOver == null ? null : mc.objectMouseOver.getBlockPos();
   }

   @Override
   public void onClick(EventClick event) {
      this.ticksSinceLastPlace++;
      if (this.mode.getSelectedValue().equals("Legit") && this.predictedLandingBlockPos != null && ClientManagers.rotationController.isOwnedBy(this) && mc.objectMouseOver != null) {
         if (this.bucketHotbarSlot != -1 && this.ticksSinceLastPlace > 2 && mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
            event.addRightClicks((byte)1);
            if (mc.thePlayer.getCurrentEquippedItem() != null && mc.thePlayer.getCurrentEquippedItem().getItem() == Items.water_bucket) {
               this.timeHelper.reset();
            }

            this.ticksSinceLastPlace = 0;
         }
      }
   }

   private BlockPos predictLandingBlock() {
      double posX = mc.thePlayer.posX;
      double posY = mc.thePlayer.posY;
      double posZ = mc.thePlayer.posZ;
      double motionX = mc.thePlayer.motionX;
      double motionY = mc.thePlayer.motionY;
      double motionZ = mc.thePlayer.motionZ;
      boolean jumping = mc.thePlayer.jumping;

      for (int i = 0; i < 200; i++) {
         double[] doubles = PlayerUtil.simulateSingleTickMotion(mc.thePlayer.movementInput.moveForward, mc.thePlayer.movementInput.moveStrafe, motionX, motionY, motionZ, posX, posY, posZ, jumping);
         jumping = false;
         posX = doubles[0];
         posY = doubles[1];
         posZ = doubles[2];
         motionX = doubles[3];
         motionY = doubles[4];
         motionZ = doubles[5];
         BlockPos pos = new BlockPos(posX, posY, posZ);
         if (mc.theWorld.getBlockState(pos).getBlock() instanceof BlockLiquid) {
            return null;
         }

         if (hasSolidCollisionBox(pos)) {
            return pos;
         }
      }

      return null;
   }

   private static boolean hasSolidCollisionBox(BlockPos pos) {
      IBlockState blockState = mc.theWorld.getBlockState(pos);
      return blockState.getBlock().getCollisionBoundingBox(mc.theWorld, blockState, pos.getX(), pos.getY(), pos.getZ()) != null;
   }

   private int findSaveItemHotbarSlot() {
      ContainerSlotQuery containerSlotQuery = ContainerSlotQuery.of(mc.thePlayer.inventoryContainer);
      containerSlotQuery = containerSlotQuery.fromSlot((byte)36);
      Predicate predicate = obj -> {
         return obj != null
            && (
               obj.equals(Item.getItemById(326)) && !mc.thePlayer.isInWater()
                  || obj.equals(Item.getItemById(325)) && mc.thePlayer.fallDistance == 0.0F && mc.thePlayer.isInWater()
            );
      };
      OptionalInt optionalInt = containerSlotQuery.filterItem(predicate).findFirstSlotIndex();
      if (optionalInt.isPresent()) {
         return optionalInt.getAsInt() - 36;
      } else {
         if (this.useWebs.getValue()) {
            containerSlotQuery = ContainerSlotQuery.of(mc.thePlayer.inventoryContainer);
            containerSlotQuery = containerSlotQuery.fromSlot((byte)36);
            Predicate predicate2 = obj2 -> {
               return obj2 != null && obj2.equals(Item.getItemById(30)) && !mc.thePlayer.isInWater();
            };
            OptionalInt optionalInt2 = containerSlotQuery.filterItem(predicate2).findFirstSlotIndex();
            if (optionalInt2.isPresent()) {
               return optionalInt2.getAsInt() - 36;
            }
         }

         return -1;
      }
   }

   private boolean isSneakWhileItemPlaceVisible() {
      return this.mode.getSelectedValue().equals("Legit");
   }

   private boolean isUseWebsVisible() {
      return this.mode.getSelectedValue().equals("Legit");
   }

   private boolean isRotationsVisible() {
      return this.mode.getSelectedValue().equals("Legit");
   }

   private boolean isMinLegitFallDistVisible() {
      return this.mode.getSelectedValue().equals("Legit");
   }

   private boolean isMinFallDistVisible() {
      return this.mode.getSelectedValue().equals("OnGround1");
   }


}
