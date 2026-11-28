package net.minecraft.client.entity;

import baritone.api.BaritoneAPI;
import baritone.api.IBaritone;
import baritone.api.event.events.ChatEvent;
import baritone.api.event.events.PlayerUpdateEvent;
import baritone.api.event.events.SprintStateEvent;
import baritone.api.event.events.type.EventState;
import baritone.behavior.LookBehavior;
import com.viaversion.viaversion.api.protocol.version.ProtocolVersion;
import de.florianmichael.dietrichevents2.StateTypes;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import net.augustus.events.EventChat;
import net.augustus.events.EventLiquidJump;
import net.augustus.events.listeners.SprintListener;
import net.minecraft.util.EnumHand;
import net.augustus.events.EventMove;
import net.augustus.ClientManagers;
import net.augustus.events.listeners.MotionListener;
import net.augustus.utils.math.Vec2d;
import net.augustus.events.listeners.MoveListener;
import net.augustus.events.SprintEventType;
import net.augustus.events.MoveEventType;
import net.augustus.events.listeners.ChatListener;
import net.augustus.events.EventSlowDown;
import net.augustus.utils.simulation.PlayerTickSnapshot;
import net.augustus.events.listeners.MoveEntityListener;
import net.augustus.events.listeners.MoveInputListener;
import net.augustus.events.listeners.AIMoveSpeedListener;
import net.augustus.viaversion.ViaAugustus;
import net.augustus.events.EventSwing;
import net.augustus.events.listeners.SlowDownListener;
import net.augustus.events.listeners.UpdateListener;
import net.augustus.utils.math.StatisticalSampleBuffer;
import net.augustus.events.EventMoveInput;
import net.augustus.utils.BoundedHistoryList;
import net.augustus.events.EventAIMoveSpeed;
import net.augustus.events.MoveInputStage;
import net.augustus.Augustus;
import net.augustus.viaversion.packet.C0APacketSwingHand;
import net.augustus.events.EventUpdate;
import net.augustus.events.EventMoveEntity;
import net.augustus.events.MoveEntityPhase;
import net.augustus.events.EventSprint;
import net.augustus.events.EventMotion;
import net.augustus.events.listeners.SwingListener;
import net.augustus.utils.TimeHelper;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.MovingSoundMinecartRiding;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.GuiCommandBlock;
import net.minecraft.client.gui.GuiEnchantment;
import net.minecraft.client.gui.GuiHopper;
import net.minecraft.client.gui.GuiMerchant;
import net.minecraft.client.gui.GuiRepair;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiScreenBook;
import net.minecraft.client.gui.inventory.GuiBeacon;
import net.minecraft.client.gui.inventory.GuiBrewingStand;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiCrafting;
import net.minecraft.client.gui.inventory.GuiDispenser;
import net.minecraft.client.gui.inventory.GuiEditSign;
import net.minecraft.client.gui.inventory.GuiFurnace;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.gui.inventory.GuiScreenHorseInventory;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.command.server.CommandBlockLogic;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C01PacketChatMessage;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.client.C0APacketAnimation;
import net.minecraft.network.play.client.C0BPacketEntityAction;
import net.minecraft.network.play.client.C0CPacketInput;
import net.minecraft.network.play.client.C0DPacketCloseWindow;
import net.minecraft.network.play.client.C13PacketPlayerAbilities;
import net.minecraft.network.play.client.C16PacketClientStatus;
import net.minecraft.network.play.server.S0BPacketAnimation;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.StatBase;
import net.minecraft.stats.StatFileWriter;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovementInput;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraft.world.IInteractionObject;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class EntityPlayerSP extends AbstractClientPlayer implements Serializable {
   public final NetHandlerPlayClient sendQueue;
   private final StatFileWriter statWriter;
   private GuiScreen spoofedScreen;
   public double lastGroundY;
   public int airTicks;
   public int onGroundTicks;
   public int ticksSinceLastSwing;
   private Vec3 lastGroundPos;
   private int autoJumpTime;
   private double lastReportedPosX;
   private double lastReportedPosY;
   private double lastReportedPosZ;
   public float lastReportedYaw;
   public float prevReportedYaw;
   public float lastReportedPitch;
   public float prevReportedPitch;
   private boolean serverSneakState;
   private boolean serverSprintState;
   private int positionUpdateTicks;
   private boolean hasValidHealth;
   private String clientBrand;
   public MovementInput movementInput;
   public Minecraft mc;
   public int sprintToggleTimer;
   public int sprintingTicksLeft;
   public float renderArmYaw;
   public float renderArmPitch;
   public float prevRenderArmYaw;
   public float prevRenderArmPitch;
   private int horseJumpPowerCounter;
   private float horseJumpPower;
   public float timeInPortal;
   public float prevTimeInPortal;
   private boolean lastReportedOnGround;
   public int rotTickCounter = 0;
   private float lastRotationYaw;
   private float lastRotationPitch;
   private final StatisticalSampleBuffer shortSampleBuffer = new StatisticalSampleBuffer(40);
   private final StatisticalSampleBuffer mediumSampleBuffer = new StatisticalSampleBuffer(60);
   private final StatisticalSampleBuffer longSampleBuffer = new StatisticalSampleBuffer(100);
   private float lastDeltaYaw = 0.0F;
   private int sampleTickCount = 0;
   private Vec3 smoothedServerPos;
   private Vec3 targetServerPos;
   private int serverPosLerpSteps = 3;
   private boolean forceStopSprint = false;
   public boolean sprintResetSilent = false;
   public boolean sprintResetWTap = false;
   public boolean sprintResetSTap = false;
   private boolean autoJumpEnabled;
   private TimeHelper timeHelper = new TimeHelper();
   private float prevDeltaYaw = 0.0F;
   private float prevDeltaPitch = 0.0F;
   private Vec3 serverPos = new Vec3(0.0, 0.0, 0.0);
   private Vec3 prevServerPos = new Vec3(0.0, 0.0, 0.0);
   private Vec3 renderServerPos = new Vec3(0.0, 0.0, 0.0);
   private Vec3 prevRenderServerPos = new Vec3(0.0, 0.0, 0.0);

   public void sendChatMessage(String str) {
      if (Augustus.getInstance().getModuleManager().baritone.isToggled()) {
         ChatEvent chatEvent = new ChatEvent(str);
         IBaritone baritoneForPlayer = BaritoneAPI.getProvider().getBaritoneForPlayer(this);
         if (baritoneForPlayer == null) {
            return;
         }

         baritoneForPlayer.getGameEventHandler().onSendChatMessage(chatEvent);
         if (chatEvent.isCancelled()) {
            return;
         }
      }

      if (!ChatListener.postChat(str).isCancelled()) {
         this.sendQueue.addToSendQueue(new C01PacketChatMessage(str));
      }
   }

   @Override
   public boolean canCommandSenderUseCommand(int n, String str) {
      return n <= 0;
   }

   public void setPlayerSPHealth(float f) {
      if (this.hasValidHealth) {
         float f2 = this.getCurrentHealth() - f;
         if (f2 <= 0.0F) {
            this.setHealth(f);
            if (f2 < 0.0F) {
               this.hurtResistantTime = this.maxHurtResistantTime / 2;
            }
         } else {
            this.lastDamage = f2;
            this.setHealth(this.getCurrentHealth());
            this.hurtResistantTime = this.maxHurtResistantTime;
            this.damageEntity(DamageSource.generic, f2);
            this.hurtTime = this.maxHurtTime = 10;
            this.clientHurtTime = this.maxHurtTime = 10;
            this.unclampedHurtTime = this.maxHurtTime = 10;
         }
      } else {
         this.setHealth(f);
         this.hasValidHealth = true;
      }
   }

   public Vec3 getPrevServerPos() {
      return this.prevServerPos;
   }

   public float getHorseJumpPower() {
      return this.horseJumpPower;
   }

   @Override
   public void displayVillagerTradeGui(IMerchant merchant) {
      this.mc.displayGuiScreen(new GuiMerchant(this.inventory, merchant, this.entityWorld));
   }

   @Override
   public void onLivingUpdate() {
      if (this.sprintingTicksLeft > 0) {
         this.sprintingTicksLeft--;
         if (this.sprintingTicksLeft == 0) {
            this.setSprinting(false);
         }
      }

      if (this.sprintToggleTimer > 0) {
         this.sprintToggleTimer--;
      }

      this.prevTimeInPortal = this.timeInPortal;
      if (this.inPortal) {
         if (this.mc.currentScreen != null && !this.mc.currentScreen.doesGuiPauseGame()) {
            if (ClientManagers.moduleManager.portalScreens.isToggled()) {
               if (!ClientManagers.moduleManager.portalScreens.inventories.getValue() && (this.mc.currentScreen instanceof GuiInventory || this.mc.currentScreen instanceof GuiContainer)
                  )
                {
                  this.mc.displayGuiScreen((GuiScreen)null);
               }
            } else {
               this.mc.displayGuiScreen((GuiScreen)null);
            }
         }

         if (this.timeInPortal == 0.0F) {
            this.mc.getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("portal.trigger"), this.rand.nextFloat() * 0.4F + 0.8F));
         }

         this.timeInPortal += 0.0125F;
         if (this.timeInPortal >= 1.0F) {
            this.timeInPortal = 1.0F;
         }

         this.inPortal = false;
      } else if (this.isPotionActive(Potion.confusion) && this.getActivePotionEffect(Potion.confusion).getDuration() > 60) {
         this.timeInPortal += 0.006666667F;
         if (this.timeInPortal > 1.0F) {
            this.timeInPortal = 1.0F;
         }
      } else {
         if (this.timeInPortal > 0.0F) {
            this.timeInPortal -= 0.05F;
         }

         if (this.timeInPortal < 0.0F) {
            this.timeInPortal = 0.0F;
         }
      }

      if (this.timeUntilPortal > 0) {
         this.timeUntilPortal--;
      }

      boolean jump = this.movementInput.jump;
      boolean sneak = this.movementInput.sneak;
      float f = 0.8F;
      boolean flag = this.movementInput.moveForward >= f;
      this.movementInput.updatePlayerMoveState(this.movementInput.moveForward, this.movementInput.moveStrafe);
      EventSlowDown event = SlowDownListener.onSlowDown(0.2F, 0.2F);
      if (this.isUsingItem() && !this.isRiding()) {
         this.movementInput.moveStrafe = this.movementInput.moveStrafe * event.getStrafeMultiplier();
         this.movementInput.moveForward = this.movementInput.moveForward * event.getForwardMultiplier();
         this.sprintToggleTimer = 0;
      }

      boolean flag2 = false;
      if (this.autoJumpTime > 0 && this.mc.isNotInGui()) {
         this.autoJumpTime--;
         flag2 = true;
         this.movementInput.jump = true;
      }

      this.pushOutOfBlocks(this.posX - (double)this.width * 0.35, this.getEntityBoundingBox().minY + 0.5, this.posZ + (double)this.width * 0.35);
      this.pushOutOfBlocks(this.posX - (double)this.width * 0.35, this.getEntityBoundingBox().minY + 0.5, this.posZ - (double)this.width * 0.35);
      this.pushOutOfBlocks(this.posX + (double)this.width * 0.35, this.getEntityBoundingBox().minY + 0.5, this.posZ - (double)this.width * 0.35);
      this.pushOutOfBlocks(this.posX + (double)this.width * 0.35, this.getEntityBoundingBox().minY + 0.5, this.posZ + (double)this.width * 0.35);
      EventSprint event2 = SprintListener.postSprint(SprintEventType.SPRINT);
      EventMoveInput event3 = MoveInputListener.postMoveInput(
         this.movementInput.moveStrafe, this.movementInput.moveForward, this.movementInput.jump, this.movementInput.sneak, MoveInputStage.POST
      );
      this.movementInput.moveForward = event3.getMoveForward();
      this.movementInput.moveStrafe = event3.getMoveStrafe();
      boolean flag3 = (float)this.getFoodStats().getFoodLevel() > 6.0F || event2.isIgnoreHunger() || this.canAllowFlying(this.capabilities);
      float moveForward2 = this.movementInput.moveForward;
      if (this.sprintResetSilent || this.sprintResetWTap) {
         this.movementInput.moveForward = 0.0F;
      }

      if (this.sprintResetSTap) {
         this.movementInput.moveForward = -1.0F;
      }

      boolean flag4 = event2.isOverrideSprintKey() ? event2.isSprintKeyDown() : this.isSprintKeyDown(this.mc.gameSettings.keyBindSprint);
      if (this.isSprinting() && event2.isOverrideSprintKey() && !event2.isSprintKeyDown() && this.movementInput.moveForward >= f) {
         this.movementInput.moveForward = 0.0F;
      }

      if (this.onGround
         && !sneak
         && !flag
         && this.movementInput.moveForward >= f
         && !this.isSprinting()
         && flag3
         && (!this.isUsingItem() || event.isAllowSprint())
         && !this.isPotionActive(Potion.blindness)) {
         if (this.sprintToggleTimer <= 0 && !flag4) {
            this.sprintToggleTimer = 7;
         } else {
            this.setSprinting(true);
         }
      }

      if (!this.isSprinting()
         && this.movementInput.moveForward >= f
         && flag3
         && (!this.isUsingItem() || event.isAllowSprint())
         && !this.isPotionActive(Potion.blindness)
         && flag4) {
         this.setSprinting(true);
      }

      if (this.isSprinting() && (this.movementInput.moveForward < f || this.isCollidedHorizontally && !event2.isKeepSprintOnCollision() || !flag3)) {
         this.setSprinting(false);
      }

      if (!event.isAllowSprint() && this.isUsingItem() && !this.isRiding()) {
         this.setSprinting(false);
      }

      if (event2.isForceSprint() && !this.sprintResetSilent && !this.sprintResetWTap && !this.sprintResetSTap) {
         this.setSprinting(true);
      }

      if (this.sprintResetSilent) {
         this.movementInput.moveForward = moveForward2;
         this.sprintResetSilent = false;
      }

      if (this.sprintResetWTap) {
         this.sprintResetWTap = false;
      }

      if (this.sprintResetSTap) {
         this.sprintResetSTap = false;
      }

      EventMoveInput event4 = MoveInputListener.postMoveInput(
         this.movementInput.moveStrafe,
         this.movementInput.moveForward,
         this.movementInput.jump,
         this.movementInput.sneak,
         MoveInputStage.POST_JUMP
      );
      this.movementInput.jump = event4.isJump();
      if (this.canAllowFlying(this.capabilities)) {
         if (this.mc.playerController.isSpectatorMode()) {
            if (!this.capabilities.isFlying) {
               this.capabilities.isFlying = true;
               this.sendPlayerAbilities();
            }
         } else if (!jump && this.movementInput.jump && !flag2) {
            if (this.flyToggleTimer == 0) {
               this.flyToggleTimer = 7;
            } else {
               this.capabilities.isFlying = !this.capabilities.isFlying;
               this.sendPlayerAbilities();
               this.flyToggleTimer = 0;
            }
         }
      }

      if (this.movementInput.sneak
         && this.isInWater()
         && !this.capabilities.isFlying
         && ClientManagers.moduleManager.jesus.isToggled()
         && ClientManagers.moduleManager.jesus.mode.getSelectedValue().equals("1.13")) {
         this.motionY += -0.04F;
      }

      if (this.capabilities.isFlying && this.isCurrentViewEntity()) {
         if (this.movementInput.sneak) {
            this.motionY = this.motionY - (double)(this.capabilities.getFlySpeed() * 3.0F);
         }

         if (this.movementInput.jump) {
            this.motionY = this.motionY + (double)(this.capabilities.getFlySpeed() * 3.0F);
         }
      }

      if (this.isRidingHorse()) {
         if (this.horseJumpPowerCounter < 0) {
            this.horseJumpPowerCounter++;
            if (this.horseJumpPowerCounter == 0) {
               this.horseJumpPower = 0.0F;
            }
         }

         if (jump && !this.movementInput.jump) {
            this.horseJumpPowerCounter = -10;
            this.sendHorseJump();
         } else if (!jump && this.movementInput.jump) {
            this.horseJumpPowerCounter = 0;
            this.horseJumpPower = 0.0F;
         } else if (jump) {
            this.horseJumpPowerCounter++;
            if (this.horseJumpPowerCounter < 10) {
               this.horseJumpPower = (float)this.horseJumpPowerCounter * 0.1F;
            } else {
               this.horseJumpPower = 0.8F + 2.0F / (float)(this.horseJumpPowerCounter - 9) * 0.1F;
            }
         }
      } else {
         this.horseJumpPower = 0.0F;
      }

      super.onLivingUpdate();
      if (this.onGround && this.capabilities.isFlying && !this.mc.playerController.isSpectatorMode()) {
         this.capabilities.isFlying = false;
         this.sendPlayerAbilities();
      }

      this.extraSnapshotHistory.add(new PlayerTickSnapshot(this));
   }

   @Override
   public float getAIMoveSpeed() {
      Float attributeValue = (float)this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue();
      if (SprintListener.postSprint(SprintEventType.MOTION_SPRINT).isMotionSprint()) {
         attributeValue = (float)this.getBaseMoveSpeed();
      }

      attributeValue = AIMoveSpeedListener.postAIMoveSpeed(attributeValue).getMoveSpeed();
      return attributeValue;
   }

   @Override
   public void mountEntity(Entity entity) {
      super.mountEntity(entity);
      if (entity instanceof EntityMinecart) {
         this.mc.getSoundHandler().playSound(new MovingSoundMinecartRiding(this, (EntityMinecart)entity));
      }
   }

   public double getLastReportedPosY() {
      return this.lastReportedPosY;
   }

   @Override
   public void onUpdate() {
      if (this.entityWorld.isBlockLoaded(new BlockPos(this.posX, 0.0, this.posZ))) {
         if (this.mc.thePlayer.ticksExisted <= 1) {
            this.lastGroundPos = null;
         }

         if (this.lastGroundPos == null && this.onGround && !this.isRiding()) {
            this.lastGroundPos = new Vec3(this.posX, this.posY, this.posZ);
            this.smoothedServerPos = new Vec3(this.posX, this.posY, this.posZ);
         }

         if (!UpdateListener.postUpdate(StateTypes.PRE).isCancelled()) {
            super.onUpdate();
            if (Augustus.getInstance().getModuleManager().baritone.isToggled()) {
               IBaritone baritoneForPlayer = BaritoneAPI.getProvider().getBaritoneForPlayer(this);
               if (baritoneForPlayer != null) {
                  baritoneForPlayer.getGameEventHandler().onPlayerUpdate(new PlayerUpdateEvent(EventState.PRE));
               }
            }
         }

         if (this.onGround) {
            this.lastGroundY = this.posY;
            this.onGroundTicks++;
            this.airTicks = 0;
         } else {
            this.airTicks++;
            this.onGroundTicks = 0;
         }

         if (UpdateListener.postUpdate(StateTypes.POST).isCancelled()) {
            return;
         }

         if (this.isRiding()) {
            this.sendQueue.addToSendQueue(new C03PacketPlayer.C05PacketPlayerLook(this.rotationYaw, this.rotationPitch, this.onGround));
            this.sendQueue.addToSendQueue(new C0CPacketInput(this.moveStrafing, this.moveForward, this.movementInput.jump, this.movementInput.sneak));
         } else {
            this.onUpdateWalkingPlayer(true);
         }
      }
   }

   public void onUpdateWalkingPlayer(boolean flag) {
      EventMotion event = flag
         ? MotionListener.postPreMotion(this.onGround, this.rotationYaw, this.rotationPitch, this.posX, this.getEntityBoundingBox().minY, this.posZ)
         : new EventMotion(this.onGround, this.rotationYaw, this.rotationPitch, this.posX, this.getEntityBoundingBox().minY, this.posZ, StateTypes.PRE);
      boolean flag2 = this.isSprinting() && !this.forceStopSprint;
      if (flag2 != this.serverSprintState) {
         if (flag2) {
            this.sendQueue.addToSendQueue(new C0BPacketEntityAction(this, C0BPacketEntityAction.Action.START_SPRINTING));
         } else {
            this.sendQueue.addToSendQueue(new C0BPacketEntityAction(this, C0BPacketEntityAction.Action.STOP_SPRINTING));
         }

         this.sprintResetSilent = false;
         this.sprintResetWTap = false;
         this.sprintResetSTap = false;
         this.serverSprintState = flag2;
      }

      this.forceStopSprint = false;
      boolean isSneaking = this.isSneaking();
      if (isSneaking != this.serverSneakState) {
         if (isSneaking) {
            this.sendQueue.addToSendQueue(new C0BPacketEntityAction(this, C0BPacketEntityAction.Action.START_SNEAKING));
         } else {
            this.sendQueue.addToSendQueue(new C0BPacketEntityAction(this, C0BPacketEntityAction.Action.STOP_SNEAKING));
         }

         this.serverSneakState = isSneaking;
      }

      if (this.isCurrentViewEntity()) {
         double d = event.getX() - this.lastReportedPosX;
         double d2 = event.getY() - this.lastReportedPosY;
         double d3 = event.getZ() - this.lastReportedPosZ;
         double d4 = (double)(event.getYaw() - this.lastReportedYaw);
         double d5 = (double)(event.getPitch() - this.lastReportedPitch);
         if (ViaAugustus.targetProtocolVersion.newerThanOrEqualTo(ProtocolVersion.v1_9)) {
            this.positionUpdateTicks++;
         }

         boolean flag3 = d * d + d2 * d2 + d3 * d3 > (ViaAugustus.targetProtocolVersion.newerThan(ProtocolVersion.v1_18) ? 4.0E-8 : 9.0E-4)
            || this.positionUpdateTicks >= 20;
         boolean flag4 = d4 != 0.0 || d5 != 0.0;
         if (this.ridingEntity == null) {
            if (flag3 && flag4) {
               this.sendQueue
                  .addToSendQueue(new C03PacketPlayer.C06PacketPlayerPosLook(event.getX(), event.getY(), event.getZ(), event.getYaw(), event.getPitch(), event.isOnGround()));
            } else if (flag3) {
               this.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(event.getX(), event.getY(), event.getZ(), event.isOnGround()));
            } else if (flag4) {
               this.sendQueue.addToSendQueue(new C03PacketPlayer.C05PacketPlayerLook(event.getYaw(), event.getPitch(), event.isOnGround()));
            } else if (ViaAugustus.targetProtocolVersion.olderThanOrEqualTo(ProtocolVersion.v1_8) || this.lastReportedOnGround != this.mc.thePlayer.onGround) {
               this.sendQueue.addToSendQueue(new C03PacketPlayer(event.isOnGround()));
            }
         } else {
            this.sendQueue.addToSendQueue(new C03PacketPlayer.C06PacketPlayerPosLook(this.motionX, -999.0, this.motionZ, event.getYaw(), event.getPitch(), event.isOnGround()));
            flag3 = false;
         }

         if (ViaAugustus.targetProtocolVersion.olderThanOrEqualTo(ProtocolVersion.v1_8)) {
            this.positionUpdateTicks++;
         }

         if (flag3) {
            this.lastReportedPosX = event.getX();
            this.lastReportedPosY = event.getY();
            this.lastReportedPosZ = event.getZ();
            this.positionUpdateTicks = 0;
         }

         this.prevReportedYaw = this.lastReportedYaw;
         this.prevReportedPitch = this.lastReportedPitch;
         if (flag4) {
            this.lastReportedYaw = event.getYaw();
            this.lastReportedPitch = event.getPitch();
         }
      }

      this.lastReportedOnGround = this.onGround;
      if (this.smoothedServerPos == null) {
         this.smoothedServerPos = new Vec3(event.getX(), event.getY(), event.getZ());
      }

      if (this.targetServerPos == null) {
         this.targetServerPos = new Vec3(event.getX(), event.getY(), event.getZ());
      }

      this.serverPosLerpSteps = this.targetServerPos.xCoord == event.getX() && this.targetServerPos.yCoord == event.getY() && this.targetServerPos.zCoord == event.getZ() ? this.serverPosLerpSteps : 3;
      if (this.serverPosLerpSteps > 0) {
         this.smoothedServerPos = new Vec3(
            this.smoothedServerPos.xCoord + (event.getX() - this.smoothedServerPos.xCoord) / (double)this.serverPosLerpSteps,
            this.smoothedServerPos.yCoord + (event.getY() - this.smoothedServerPos.yCoord) / (double)this.serverPosLerpSteps,
            this.smoothedServerPos.zCoord + (event.getZ() - this.smoothedServerPos.zCoord) / (double)this.serverPosLerpSteps
         );
         this.serverPosLerpSteps--;
      }

      this.targetServerPos = new Vec3(event.getX(), event.getY(), event.getZ());
      if (flag) {
         MotionListener.postPostMotion();
      }

      this.setRenderServerPos(this.getServerPos());
      this.setPrevRenderServerPos(this.getPrevServerPos());
   }

   private Vec3 getLookCustom(float f, float f2, float f3) {
      if (f != 1.0F && f != 2.0F) {
         return this.getVectorForRotation(this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * f, this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * f);
      } else {
         return this.getVectorForRotation(f3, f2);
      }
   }

   public boolean isCurrentViewEntity() {
      return this.mc.getRenderViewEntity() == this;
   }

   @Override
   public BlockPos getPosition() {
      return new BlockPos(this.posX + 0.5, this.posY + 0.5, this.posZ + 0.5);
   }

   @Override
   public void addChatMessage(IChatComponent component) {
      this.mc.ingameGUI.getChatGUI().printChatMessage(component);
   }

   @Override
   public void damageEntity(DamageSource damageSource, float f) {
      if (!this.isEntityInvulnerable(damageSource)) {
         this.setHealth(this.getCurrentHealth() - f);
      }
   }

   @Override
   public EntityItem dropOneItem(boolean flag) {
      C07PacketPlayerDigging.Action action = flag ? C07PacketPlayerDigging.Action.DROP_ALL_ITEMS : C07PacketPlayerDigging.Action.DROP_ITEM;
      this.sendQueue.addToSendQueue(new C07PacketPlayerDigging(action, BlockPos.ORIGIN, EnumFacing.DOWN));
      return null;
   }

   @Override
   public boolean isSneaking() {
      return (this.movementInput != null ? this.movementInput.sneak : false) && !this.sleeping;
   }

   public float getLastReportedYaw() {
      return this.lastReportedYaw;
   }

   private Vec3 getLookInterpolated(float f, float f2, float f3, boolean flag) {
      if (f == 1.0F) {
         return this.getVectorForRotation(f3, f2);
      } else {
         return this.getVectorForRotation(flag ? this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * f : f3, flag ? this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * f : f2);
      }
   }

   public double getLastReportedPosX() {
      return this.lastReportedPosX;
   }

   public void setServerPos(Vec3 vec) {
      this.serverPos = vec;
   }

   @Override
   public void respawnPlayer() {
      this.sendQueue.addToSendQueue(new C16PacketClientStatus(C16PacketClientStatus.EnumState.PERFORM_RESPAWN));
   }

   public Vec3 getRenderServerPos() {
      return this.renderServerPos;
   }

   public Vec3 getPositionEyesCustom(float f, double[] doubles, double[] doubles2) {
      if (f == 1.0F) {
         return new Vec3(doubles[0], doubles[1] + (double)this.getEyeHeight(), doubles[2]);
      } else {
         double d = doubles2[0] + (doubles[0] - doubles2[0]) * (double)f;
         double d2 = doubles2[1] + (doubles[1] - doubles2[1]) * (double)f + (double)this.getEyeHeight();
         double d3 = doubles2[2] + (doubles[2] - doubles2[2]) * (double)f;
         return new Vec3(d, d2, d3);
      }
   }

   public void setPrevServerPos(Vec3 vec) {
      this.prevServerPos = vec;
   }

   private boolean isSprintKeyDown(KeyBinding keyBinding) {
      if (Augustus.getInstance().getModuleManager().baritone.isToggled()) {
         IBaritone baritoneForPlayer = BaritoneAPI.getProvider().getBaritoneForPlayer(this);
         if (baritoneForPlayer == null) {
            return keyBinding.isKeyDown();
         }

         SprintStateEvent sprintStateEvent = new SprintStateEvent();
         baritoneForPlayer.getGameEventHandler().onPlayerSprintState(sprintStateEvent);
         if (sprintStateEvent.getState() != null) {
            return sprintStateEvent.getState();
         }

         if (baritoneForPlayer != BaritoneAPI.getProvider().getPrimaryBaritone()) {
            return false;
         }
      }

      return keyBinding.isKeyDown();
   }

   @Override
   public MovingObjectPosition rayTraceCustom(double d, float f, float f2, float f3) {
      Vec3 positionEyes = this.getPositionEyes(f);
      Vec3 lookCustom = this.getLookCustom(f, f2, f3);
      Vec3 vec = positionEyes.addVector(lookCustom.xCoord * d, lookCustom.yCoord * d, lookCustom.zCoord * d);
      return this.entityWorld.rayTraceBlocks(positionEyes.xCoord, positionEyes.yCoord, positionEyes.zCoord, vec.xCoord, vec.yCoord, vec.zCoord, false, false, true);
   }

   public Vec3 getServerPos() {
      return this.serverPos;
   }

   public void setXPStats(float f, int n, int n2) {
      this.experience = f;
      this.experienceTotal = n;
      this.experienceLevel = n2;
   }

   @Override
   public void heal(float f) {
   }

   public double getBaseMoveSpeed() {
      Collection activePotionEffects = this.getActivePotionEffects();
      double d = (sprintingSpeedBoostModifier.getAmount() + 1.0) * (double)0.1F;
      if (activePotionEffects == null) {
         return d;
      } else {
         int n = -1;
         int n2 = -1;
         Iterator it = activePotionEffects.iterator();

         while (it.hasNext()) {
            PotionEffect potionEffect = (PotionEffect)it.next();
            if (potionEffect.getPotionID() == Potion.moveSpeed.id) {
               n2 = potionEffect.getAmplifier();
            } else if (potionEffect.getPotionID() == Potion.moveSlowdown.id) {
               n = potionEffect.getAmplifier();
            }
         }

         if (n2 >= 0) {
            d *= (double)(n2 + 1) * (double)0.2F + 1.0;
         }

         if (n >= 0) {
            d *= (double)(n + 1) * (double)-0.15F + 1.0;
         }

         return d;
      }
   }

   private Vec3 getLookWithPrev(float f, float f2, float f3, float f4, float f5) {
      if (f == 1.0F) {
         return this.getVectorForRotation(f3, f2);
      } else {
         return this.getVectorForRotation(f5 + (f3 - f5) * f, f4 + (f2 - f4) * f);
      }
   }

   public void closeScreenAndDropStack() {
      this.inventory.setItemStack((ItemStack)null);
      super.closeScreen();
      GuiScreen screen = (GuiScreen)null;
      this.mc.displayGuiScreen(screen);
   }

   public String getClientBrand() {
      return this.clientBrand;
   }

   @Override
   public void joinEntityItemWithWorld(EntityItem entityItem) {
   }

   public float getLastReportedPitch() {
      return this.lastReportedPitch;
   }

   public void setLastReportedPosY(double d) {
      this.lastReportedPosY = d;
   }

   public void sendHorseInventory() {
      this.sendQueue.addToSendQueue(new C0BPacketEntityAction(this, C0BPacketEntityAction.Action.OPEN_INVENTORY));
   }

   public void setLastReportedPosX(double d) {
      this.lastReportedPosX = d;
   }

   public MovingObjectPosition rayTraceFromPosition(Vec3 vec, double d, float f, float f2, float f3) {
      Vec3 lookCustom = this.getLookCustom(f, f2, f3);
      Vec3 vec2 = vec.addVector(lookCustom.xCoord * d, lookCustom.yCoord * d, lookCustom.zCoord * d);
      return this.entityWorld.rayTraceBlocks(vec.xCoord, vec.yCoord, vec.zCoord, vec2.xCoord, vec2.yCoord, vec2.zCoord, false, false, true);
   }

   public boolean isServerSprintState() {
      return this.serverSprintState;
   }

   @Override
   public boolean isServerWorld() {
      return true;
   }

   public double getLastReportedPosZ() {
      return this.lastReportedPosZ;
   }

   @Override
   public void swingItem() {
      this.mc.thePlayer.resetTicksSinceLastSwing();
      EventSwing event = SwingListener.onSwing();
      if (!this.isSwingInProgress || this.swingProgressInt >= this.getArmSwingAnimationEnd() / 2 || this.swingProgressInt < 0) {
         if (!event.cancelClientSwing()) {
            this.swingProgressInt = -1;
            this.isSwingInProgress = true;
         }

         if (this.entityWorld instanceof WorldServer) {
            ((WorldServer)this.entityWorld).getEntityTracker().sendToAllTrackingEntity(this, new S0BPacketAnimation(this, 0));
         }
      }

      if (!event.cancelServerSwing()) {
         if (ViaAugustus.targetProtocolVersion.olderThanOrEqualTo(ProtocolVersion.v1_8)) {
            this.sendQueue.addToSendQueue(new C0APacketAnimation());
         } else {
            this.sendQueue.addToSendQueue(new C0APacketSwingHand(EnumHand.MAIN_HAND));
         }
      }
   }

   public StatFileWriter getStatFileWriter() {
      return this.statWriter;
   }

   @Override
   public void updateRidden() {
      IBaritone baritoneForPlayer = BaritoneAPI.getProvider().getBaritoneForPlayer(this);
      if (baritoneForPlayer != null) {
         ((LookBehavior)baritoneForPlayer.getLookBehavior()).pig();
      }

      super.updateRidden();
   }

   public Vec3 getPrevRenderServerPos() {
      return this.prevRenderServerPos;
   }

   private boolean isOpenBlockSpace(BlockPos pos) {
      return !this.entityWorld.getBlockState(pos).getBlock().isNormalCube() && !this.entityWorld.getBlockState(pos.up()).getBlock().isNormalCube();
   }

   @Override
   public void updateEntityActionState() {
      super.updateEntityActionState();
      if (this.isCurrentViewEntity()) {
         this.moveStrafing = this.movementInput.moveStrafe;
         this.moveForward = this.movementInput.moveForward;
         this.jumping = this.movementInput.jump;
         this.prevRenderArmYaw = this.renderArmYaw;
         this.prevRenderArmPitch = this.renderArmPitch;
         this.renderArmPitch = (float)((double)this.renderArmPitch + (double)(Augustus.getInstance().getYawPitchHelper().realPitch - this.renderArmPitch) * 0.5);
         this.renderArmYaw = (float)((double)this.renderArmYaw + (double)(Augustus.getInstance().getYawPitchHelper().realYaw - this.renderArmYaw) * 0.5);
      }
   }

   @Override
   public void setSprinting(boolean flag) {
      super.setSprinting(flag);
      this.sprintingTicksLeft = flag ? 600 : 0;
   }

   @Override
   public boolean isUser() {
      return true;
   }

   public EntityPlayerSP(Minecraft mc2, World world, NetHandlerPlayClient netHandlerPlayClient, StatFileWriter statFileWriter) {
      super(world, netHandlerPlayClient.getGameProfile());
      this.sendQueue = netHandlerPlayClient;
      this.statWriter = statFileWriter;
      this.mc = mc2;
      this.dimension = 0;
   }

   public void setLastReportedPitch(float f) {
      this.lastReportedPitch = f;
   }

   @Override
   public void sendPlayerAbilities() {
      this.sendQueue.addToSendQueue(new C13PacketPlayerAbilities(this.capabilities));
   }

   @Override
   public Vec3 getLook(float f) {
      float f2 = ClientManagers.moduleManager.fixes.isMouseDelayFixEnabled() ? this.rotationYaw : this.rotationYawHead;
      float f3 = ClientManagers.moduleManager.fixes.isMouseDelayFixEnabled() ? this.prevRotationYaw : this.prevRotationYawHead;
      float rotationPitch2 = this.rotationPitch;
      if (f == 1.0F) {
         if (ClientManagers.moduleManager.fixes.isIntaveFixEnabled()) {
            f2 %= 360.0F;
         }

         return this.getVectorForRotation(rotationPitch2, f2);
      } else {
         return this.getVectorForRotation(this.prevRotationPitch + (rotationPitch2 - this.prevRotationPitch) * f, f3 + (f2 - f3) * f);
      }
   }

   @Override
   public void displayGUIHorse(EntityHorse entityHorse, IInventory inventory2) {
      this.mc.displayGuiScreen(new GuiScreenHorseInventory(this.inventory, inventory2, entityHorse));
   }

   public void setServerSneakState(boolean flag) {
      this.serverSneakState = flag;
   }

   public void setLastReportedPosZ(double d) {
      this.lastReportedPosZ = d;
   }

   public boolean isRidingHorse() {
      return this.ridingEntity != null && this.ridingEntity instanceof EntityHorse && ((EntityHorse)this.ridingEntity).isHorseSaddled();
   }

   @Override
   public boolean pushOutOfBlocks(double d, double d2, double d3) {
      if (this.noClip) {
         return false;
      } else {
         BlockPos pos = new BlockPos(d, d2, d3);
         double d4 = d - (double)pos.getX();
         double d5 = d3 - (double)pos.getZ();
         if (!this.isOpenBlockSpace(pos)) {
            byte b = -1;
            double d6 = 9999.0;
            if (this.isOpenBlockSpace(pos.west()) && d4 < d6) {
               d6 = d4;
               b = 0;
            }

            if (this.isOpenBlockSpace(pos.east()) && 1.0 - d4 < d6) {
               d6 = 1.0 - d4;
               b = 1;
            }

            if (this.isOpenBlockSpace(pos.north()) && d5 < d6) {
               d6 = d5;
               b = 4;
            }

            if (this.isOpenBlockSpace(pos.south()) && 1.0 - d5 < d6) {
               d6 = 1.0 - d5;
               b = 5;
            }

            float f = 0.1F;
            if (b == 0) {
               this.motionX = (double)(-f);
            }

            if (b == 1) {
               this.motionX = (double)f;
            }

            if (b == 4) {
               this.motionZ = (double)(-f);
            }

            if (b == 5) {
               this.motionZ = (double)f;
            }
         }

         return false;
      }
   }

   @Override
   public void playSound(String str, float f, float f2) {
      this.entityWorld.playSound(this.posX, this.posY, this.posZ, str, f, f2, false);
   }

   @Override
   public void handleJumpLava(EventLiquidJump event) {
      this.motionY = this.motionY + event.getLavaMotionY();
   }

   public Vec3 getLastGroundPos() {
      return this.lastGroundPos;
   }

   public void setClientBrand(String str) {
      this.clientBrand = str;
   }

   public void sendHorseJump() {
      this.sendQueue.addToSendQueue(new C0BPacketEntityAction(this, C0BPacketEntityAction.Action.RIDING_JUMP, (int)(this.getHorseJumpPower() * 100.0F)));
   }

   @Override
   public void displayGUIChest(IInventory inventory2) {
      String str = inventory2 instanceof IInteractionObject ? ((IInteractionObject)inventory2).getGuiID() : "minecraft:container";
      if ("minecraft:chest".equals(str)) {
         this.mc.displayGuiScreen(new GuiChest(this.inventory, inventory2));
      } else if ("minecraft:hopper".equals(str)) {
         this.mc.displayGuiScreen(new GuiHopper(this.inventory, inventory2));
      } else if ("minecraft:furnace".equals(str)) {
         this.mc.displayGuiScreen(new GuiFurnace(this.inventory, inventory2));
      } else if ("minecraft:brewing_stand".equals(str)) {
         this.mc.displayGuiScreen(new GuiBrewingStand(this.inventory, inventory2));
      } else if ("minecraft:beacon".equals(str)) {
         this.mc.displayGuiScreen(new GuiBeacon(this.inventory, inventory2));
      } else if (!"minecraft:dispenser".equals(str) && !"minecraft:dropper".equals(str)) {
         this.mc.displayGuiScreen(new GuiChest(this.inventory, inventory2));
      } else {
         this.mc.displayGuiScreen(new GuiDispenser(this.inventory, inventory2));
      }
   }

   private boolean canAllowFlying(PlayerCapabilities playerCapabilities) {
      IBaritone baritoneForPlayer = BaritoneAPI.getProvider().getBaritoneForPlayer(this);
      return baritoneForPlayer == null ? playerCapabilities.allowFlying : !baritoneForPlayer.getPathingBehavior().isPathing() && playerCapabilities.allowFlying;
   }

   public float getDistance2DToEntity(Entity entity) {
      float f = (float)(this.posX - entity.posX);
      float f2 = (float)(this.posZ - entity.posZ);
      return MathHelper.sqrt_float(f * f + f2 * f2);
   }

   public MovingObjectPosition rayTraceFromCoords(double d, float f, float f2, float f3, double[] doubles, double[] doubles2) {
      Vec3 positionEyesCustom = this.getPositionEyesCustom(f, doubles, doubles2);
      Vec3 lookCustom = this.getLookCustom(f, f2, f3);
      Vec3 vec = positionEyesCustom.addVector(lookCustom.xCoord * d, lookCustom.yCoord * d, lookCustom.zCoord * d);
      return this.entityWorld.rayTraceBlocks(positionEyesCustom.xCoord, positionEyesCustom.yCoord, positionEyesCustom.zCoord, vec.xCoord, vec.yCoord, vec.zCoord, false, false, true);
   }

   @Override
   public void onCriticalHit(Entity entity) {
      if (!Augustus.getInstance().getModuleManager().attackEffects.isToggled() && entity.getEntityId() != this.mc.thePlayer.getEntityId()) {
         this.mc.effectRenderer.emitParticleAtEntity(entity, EnumParticleTypes.CRIT);
      }
   }

   @Override
   public void addStat(StatBase statBase, int n) {
      if (statBase != null && statBase.isIndependent) {
         super.addStat(statBase, n);
      }
   }

   public void resetTicksSinceLastSwing() {
      this.ticksSinceLastSwing = 0;
   }

   public void updateAutoJump(float f, float f2) {
      if (this.autoJumpTime <= 0 && this.onGround && !this.isSneaking() && !this.isRiding()) {
         Vec2d vec2d = new Vec2d((double)this.mc.thePlayer.movementInput.moveStrafe, (double)this.mc.thePlayer.movementInput.moveForward);
         if (vec2d.x != 0.0 || vec2d.y != 0.0) {
            Vec3 vec = new Vec3(this.posX, this.getEntityBoundingBox().minY, this.posZ);
            double d = this.posX + (double)f;
            double d2 = this.posZ + (double)f2;
            Vec3 vec2 = new Vec3(d, this.getEntityBoundingBox().minY, d2);
            Vec3 vec3 = new Vec3((double)f, 0.0, (double)f2);
            float aiMoveSpeed2 = this.getAIMoveSpeed();
            float f3 = (float)vec3.lengthSquared();
            if (f3 <= 0.001F) {
               double d3 = (double)aiMoveSpeed2 * vec2d.x;
               double d4 = (double)aiMoveSpeed2 * vec2d.y;
               float f4 = MathHelper.sin(this.rotationYaw * (float) (Math.PI / 180.0));
               float f5 = MathHelper.cos(this.rotationYaw * (float) (Math.PI / 180.0));
               vec3 = new Vec3(d3 * (double)f5 - d4 * (double)f4, vec3.yCoord, d4 * (double)f5 + d3 * (double)f4);
               f3 = (float)vec3.lengthSquared();
               if (f3 <= 0.001F) {
                  return;
               }
            }

            float f6 = (float)MathHelper.fastInverseSqrt((double)f3);
            Vec3 vec4 = vec3.scale((double)f6);
            Vec3 vectorForRotation = this.getVectorForRotation(this.rotationPitch, this.rotationYaw);
            if (((float)(vectorForRotation.xCoord * vec4.xCoord + vectorForRotation.zCoord * vec4.zCoord)) >= -0.15F) {
               BlockPos pos = new BlockPos(this.posX, this.getEntityBoundingBox().maxY, this.posZ);
               IBlockState blockState = this.entityWorld.getBlockState(pos);
               if (blockState.getBlock().getCollisionBoundingBox(this.entityWorld, blockState, pos.getX(), pos.getY(), pos.getZ()) == null) {
                  pos = pos.up();
                  IBlockState blockState2 = this.entityWorld.getBlockState(pos);
                  if (blockState2.getBlock().getCollisionBoundingBox(this.entityWorld, blockState2, pos.getX(), pos.getY(), pos.getZ()) == null) {
                     float f8 = 7.0F;
                     float f9 = 1.2F;
                     if (this.isPotionActive(Potion.jump)) {
                        f9 += (float)(this.getActivePotionEffect(Potion.jump).getAmplifier() + 1) * 0.75F;
                     }

                     Vec3 vec5 = vec2.add(vec4.scale((double)Math.max(aiMoveSpeed2 * 7.0F, 1.0F / f6)));
                     float width2 = this.width;
                     float height2 = this.height;
                     AxisAlignedBB box = new AxisAlignedBB(vec, vec5.addVector(0.0, (double)height2, 0.0)).expand((double)width2, 0.0, (double)width2);
                     Vec3 vec6 = vec.addVector(0.0, 0.51F, 0.0);
                     vec5 = vec5.addVector(0.0, 0.51F, 0.0);
                     Vec3 vec8 = vec4.crossProduct(new Vec3(0.0, 1.0, 0.0)).scale((double)(width2 * 0.5F));
                     Vec3 vec9 = vec6.subtract(vec8);
                     Vec3 vec10 = vec5.subtract(vec8);
                     Vec3 vec11 = vec6.add(vec8);
                     Vec3 vec12 = vec5.add(vec8);
                     List collidingBoundingBoxes = this.entityWorld.getCollidingBoundingBoxes(this, box);
                     float minvalue = Float.MIN_VALUE;

                     for (AxisAlignedBB box2 : (Iterable<AxisAlignedBB>)(collidingBoundingBoxes)) {
                        if (box2.intersectsWith(vec9, vec10) || box2.intersectsWith(vec11, vec12)) {
                           minvalue = (float)box2.maxY;
                           Vec3 center = box2.getCenter();
                           BlockPos pos2 = new BlockPos(center);

                           for (int i = 1; !((float)i >= f9); i++) {
                              BlockPos pos3 = pos2.up(i);
                              IBlockState blockState3 = this.entityWorld.getBlockState(pos3);
                              AxisAlignedBB box3;
                              if ((box3 = blockState3.getBlock().getCollisionBoundingBox(this.entityWorld, blockState3, pos3.getX(), pos3.getY(), pos3.getZ())) != null) {
                                 minvalue = (float)box3.maxY + (float)pos3.getY();
                                 if ((double)minvalue - this.getEntityBoundingBox().minY > (double)f9) {
                                    return;
                                 }
                              }

                              if (i > 1) {
                                 pos = pos.up();
                                 IBlockState blockState4 = this.entityWorld.getBlockState(pos);
                                 if (blockState4.getBlock().getCollisionBoundingBox(this.entityWorld, blockState4, pos3.getX(), pos3.getY(), pos3.getZ()) != null) {
                                    return;
                                 }
                              }
                           }
                           break;
                        }
                     }

                     if (minvalue != Float.MIN_VALUE) {
                        float f11 = (float)((double)minvalue - this.getEntityBoundingBox().minY);
                        if (f11 > 0.5F && f11 <= f9) {
                           this.autoJumpTime = 1;
                        }
                     }
                  }
               }
            }
         }
      }
   }

   public MovingObjectPosition rayTraceWithPrevRotation(double d, float f, float f2, float f3, float f4, float f5) {
      Vec3 positionEyes = this.getPositionEyes(f);
      Vec3 lookWithPrev = this.getLookWithPrev(f, f2, f3, f4, f5);
      Vec3 vec = positionEyes.addVector(lookWithPrev.xCoord * d, lookWithPrev.yCoord * d, lookWithPrev.zCoord * d);
      return this.entityWorld.rayTraceBlocks(positionEyes.xCoord, positionEyes.yCoord, positionEyes.zCoord, vec.xCoord, vec.yCoord, vec.zCoord, false, false, true);
   }

   public void setPrevRenderServerPos(Vec3 vec) {
      this.prevRenderServerPos = vec;
   }

   @Override
   public void addChatComponentMessage(IChatComponent component) {
      this.mc.ingameGUI.getChatGUI().printChatMessage(component);
   }

   @Override
   public void openEditSign(TileEntitySign tileEntitySign) {
      this.mc.displayGuiScreen(new GuiEditSign(tileEntitySign));
   }

   public void setSpoofedScreen(GuiScreen screen) {
      this.spoofedScreen = screen;
   }

   @Override
   public void onEnchantmentCritical(Entity entity) {
      if (!Augustus.getInstance().getModuleManager().attackEffects.isToggled() && entity.getEntityId() != this.mc.thePlayer.getEntityId()) {
         this.mc.effectRenderer.emitParticleAtEntity(entity, EnumParticleTypes.CRIT_MAGIC);
      }
   }

   @Override
   public void moveFlying(float f, float f2, float f3) {
      EventMove event = MoveListener.onMove(this.rotationYaw, f2, f, f3, MoveEventType.MOVE_FLYING);
      if (!event.isCancelled()) {
         f = event.getStrafe();
         f2 = event.getForward();
         f3 = event.getFriction();
         float f4 = f * f + f2 * f2;
         if (f4 >= 1.0E-4F) {
            f4 = MathHelper.sqrt_float(f4);
            if (f4 < 1.0F) {
               f4 = 1.0F;
            }

            f4 = f3 / f4;
            f *= f4;
            f2 *= f4;
            float f5 = MathHelper.sin(event.getYaw() * (float) Math.PI / 180.0F);
            float f6 = MathHelper.cos(event.getYaw() * (float) Math.PI / 180.0F);
            this.motionX += (double)(f * f6 - f2 * f5);
            this.motionZ += (double)(f2 * f6 + f * f5);
         }
      }
   }

   @Override
   public void moveEntity(double d, double d2, double d3) {
      EventMoveEntity event = MoveEntityListener.onMoveEntity(d, d2, d3, MoveEntityPhase.PRE);
      d = event.getX();
      d2 = event.getY();
      d3 = event.getZ();
      if (!event.isCancelled()) {
         super.moveEntity(d, d2, d3);
      }

      MoveEntityListener.onMoveEntity(d, d2, d3, MoveEntityPhase.POST);
   }

   @Override
   public boolean attackEntityFrom(DamageSource damageSource, float f) {
      return false;
   }

   public void setServerSprintState(boolean flag) {
      this.serverSprintState = flag;
   }

   public void setRenderServerPos(Vec3 vec) {
      this.renderServerPos = vec;
   }

   @Override
   public void setPositionAndRotation(double d, double d2, double d3, float f, float f2) {
      double d4 = Math.abs(this.posX - d);
      double d5 = Math.abs(this.posY - d2);
      double d6 = Math.abs(this.posZ - d3);
      if (d4 * d4 + d5 * d5 + d6 * d6 > 900.0) {
         this.lastGroundPos = null;
      }

      super.setPositionAndRotation(d, d2, d3, f, f2);
   }

   public void setForceStopSprint(boolean flag) {
      this.forceStopSprint = flag;
   }

   public Vec3 getSmoothedServerPos() {
      return this.smoothedServerPos;
   }

   @Override
   public void jump() {
      EventMove event = MoveListener.onMove(this.rotationYaw, MoveEventType.JUMP);
      if (!event.isCancelled()) {
         this.motionY = (double)this.getJumpUpwardsMotion();
         if (this.isPotionActive(Potion.jump)) {
            this.motionY = this.motionY + (double)((float)(this.getActivePotionEffect(Potion.jump).getAmplifier() + 1) * 0.1F);
         }

         EventSprint event2 = SprintListener.postSprint(SprintEventType.JUMP_SPRINT);
         if ((this.isSprinting() || event2.isOmniJumpSprint()) && event2.isApplyJumpBoost()) {
            if (event2.isOmniJumpSprint()) {
               if (this.mc.thePlayer.moveForward < 0.0F) {
                  event.setYaw(event.getYaw() - 180.0F);
               }

               if (this.mc.thePlayer.moveStrafing != 0.0F && this.mc.thePlayer.moveForward == 0.0F) {
                  if (this.mc.thePlayer.moveStrafing > 0.0F) {
                     event.setYaw(event.getYaw() - 45.0F);
                  } else if (this.mc.thePlayer.moveStrafing < 0.0F) {
                     event.setYaw(event.getYaw() + 45.0F);
                  }
               }
            }

            float f4 = event.getYaw() * (float) (Math.PI / 180.0);
            this.motionX = this.motionX - (double)(MathHelper.sin(f4) * 0.2F);
            this.motionZ = this.motionZ + (double)(MathHelper.cos(f4) * 0.2F);
         }

         this.isAirBorne = true;
         this.triggerAchievement(StatList.jumpStat);
         if (this.isSprinting()) {
            this.addExhaustion(0.8F);
         } else {
            this.addExhaustion(0.2F);
         }
      }
   }

   @Override
   public void openEditCommandBlock(CommandBlockLogic commandBlockLogic) {
      this.mc.displayGuiScreen(new GuiCommandBlock(commandBlockLogic));
   }

   @Override
   public void closeScreen() {
      this.sendQueue.addToSendQueue(new C0DPacketCloseWindow(this.openContainer.windowId));
      this.closeScreenAndDropStack();
   }

   public boolean isServerSneakState() {
      return this.serverSneakState;
   }

   public void setLastReportedYaw(float f) {
      this.lastReportedYaw = f;
   }

   @Override
   public void handleJumpWater(EventLiquidJump event) {
      this.motionY = this.motionY + event.getWaterMotionY();
   }

   public GuiScreen getSpoofedScreen() {
      return this.spoofedScreen;
   }

   @Override
   public float getEyeHeight() {
      float f = 1.62F;
      if (this.isPlayerSleeping()) {
         f = 0.2F;
      }

      if (this.isSneaking()) {
         f -= 0.08F;
      }

      return f;
   }

   @Override
   public void displayGui(IInteractionObject interactionObject) {
      String guiID = interactionObject.getGuiID();
      if ("minecraft:crafting_table".equals(guiID)) {
         this.mc.displayGuiScreen(new GuiCrafting(this.inventory, this.entityWorld));
      } else if ("minecraft:enchanting_table".equals(guiID)) {
         this.mc.displayGuiScreen(new GuiEnchantment(this.inventory, this.entityWorld, interactionObject));
      } else if ("minecraft:anvil".equals(guiID)) {
         this.mc.displayGuiScreen(new GuiRepair(this.inventory, this.entityWorld));
      }
   }

   @Override
   public void displayGUIBook(ItemStack stack) {
      if (stack.getItem() == Items.writable_book) {
         this.mc.displayGuiScreen(new GuiScreenBook(this, stack, true));
      }
   }
}
