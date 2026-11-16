package augustus.modes.movement;

import augustus.modules.movement.LongJump;
import client.ClientGlobals;
import client.event.ClickListener;
import client.event.EventBlockCollision;
import client.event.EventClick;
import client.event.EventMove;
import client.event.EventPacket;
import client.event.EventSendPacket;
import client.event.NetworkPacketListener;
import client.event.PacketListener;
import client.event.RenderListener;
import client.event.TickListener;
import client.render.RenderUtils;
import client.render.WorldRenderer;
import client.rotation.Rotation;
import client.setting.BooleanSetting;
import client.setting.NumberSetting;
import client.setting.SettingGroup;
import client.util.AuxiliaryMoveListenerAdapter;
import client.util.BooleanSupplier;
import client.util.CombatUtil;
import client.util.DefaultVertexFormats;
import client.util.MouseUtils;
import client.util.Packet;
import client.util.RayTraceUtil;
import client.util.SimulatedPlayer;
import client.util.UseEntityAction;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.BiFunction;
import net.minecraft.block.BlockCollisionListener;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityBoat;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.server.S1BPacketEntityAttach;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import org.lwjgl.opengl.GL11;

public class LongJumpIntaveBoatMode extends AuxiliaryMoveListenerAdapter implements RenderListener, PacketListener, BlockCollisionListener, NetworkPacketListener, ClickListener, TickListener {
   public final BooleanSetting settingIntaveLonger;
   public final BooleanSetting settingDisplayDirection;
   public final BooleanSetting settingRotate;
   public final BooleanSetting settingClick;
   public final NumberSetting settingClickRange;
   public final NumberSetting settingIntaveStrength;
   public final BooleanSetting settingHelpMessage;
   public boolean enabled3;
   public int count2;
   public double value;
   public int count3;
   public Entity entity;
   public boolean enabled4;

   @Override
   public void updateState() {
      this.value = -1337.0;
      this.count3 = 0;
      this.entity = null;
      this.enabled4 = false;
      this.enabled3 = false;
      this.count2 = 0;
      if (minecraft.thePlayer != null && this.settingHelpMessage.isEnabled()) {
         RayTraceUtil.applyString("1. Toggle Longjump");
         if (this.settingClick.isEnabled()) {
            RayTraceUtil.applyString("2. Left click (stay from boat as far away as possible)");
         } else {
            RayTraceUtil.applyString("2. Right click boat from far away as possible");
         }

         RayTraceUtil.applyString("3. Enjoy longjumping");
      }
   }

   @Override
   public void onClick(EventClick event) {
      short vecSaltShort = 20072;
      int vecSaltInt = -521758288;
      if (this.entity != null
         && (double)minecraft.thePlayer.getDistanceSqToEntity(this.entity) < this.settingClickRange.getValue()
         && !this.enabled4
         && MouseUtils.checkCondition(0)
         && this.settingClick.isEnabled()) {
         event.setState2(true);
         event.setState3(true);
         this.enabled4 = true;
         Vec3 hitVec = SimulatedPlayer.getVec3(this.entity, vecSaltShort, vecSaltInt);
         Vec3 offsetVec = new Vec3(hitVec.xCoord - this.entity.posX, hitVec.yCoord - this.entity.posY, hitVec.zCoord - this.entity.posZ);
         minecraft.getNetHandlerPlayClient().handlePacket(new C02PacketUseEntity(this.entity, offsetVec));
         minecraft.getNetHandlerPlayClient().handlePacket(new C02PacketUseEntity(this.entity, UseEntityAction.INTERACT));
      }
   }

   private boolean isActive2() {
      return this.booleanValue() && this.settingClick.isEnabled();
   }

   @Override
   public void onBlockCollision(EventBlockCollision event) {
      if (this.settingIntaveLonger.isEnabled()) {
         if (minecraft.thePlayer.motionY < -0.15 && this.value < -1000.0 && this.count3 > 0) {
            this.value = minecraft.thePlayer.posY - 0.5;
         }

         if ((double)event.getBlockPos().getY() < this.value) {
            BlockPos blockPos = event.getBlockPos();
            AxisAlignedBB boundingBox = new AxisAlignedBB(
               (double)blockPos.getX(),
               (double)blockPos.getY(),
               (double)blockPos.getZ(),
               (double)(blockPos.getX() + 1),
               (double)(blockPos.getY() + 1),
               (double)(blockPos.getZ() + 1)
            );
            event.setBoundingBox(boundingBox);
         }
      }
   }

   @Override
   public void onPostNetworkPacket(EventSendPacket event) {
      if (this.enabled3 && this.count2 > 1 && this.count2 != 1001) {
         event.setCancelled(true);
      }
   }

   @Override
   public void onRender2D(float partialTicks, ScaledResolution scaledResolution) {
      if (this.settingDisplayDirection.isEnabled()) {
         boolean outOfRange = true;
         if (this.entity == null
            || !this.settingClick.isEnabled()
            || !((double)minecraft.thePlayer.getDistanceSqToEntity(this.entity) < this.settingClickRange.getValue())) {
            if (!this.settingClick.isEnabled()
               && this.entity != null
               && minecraft.objectMouseOver != null
               && minecraft.objectMouseOver.entityHit instanceof EntityBoat) {
               outOfRange = false;
            }

            if (outOfRange) {
               minecraft.fontRendererObj
                  .computeCount(
                     "Vehicle out of range",
                     (float)scaledResolution.getScaledWidth() / 2.0F - (float)minecraft.fontRendererObj.getIndex("Vehicle out of range") / 2.0F,
                     (float)scaledResolution.getScaledHeight() / 2.0F,
                     Color.red.getRGB(),
                     true,
                     true
                  );
            }
         }

         RenderUtils.processAction();
      }
   }

   private static double computeValueByEntity(Entity entity) {
      return (double)minecraft.thePlayer.getDistanceToEntity(entity);
   }

   @Override
   public void clear() {
   }

   private boolean matchesEntityByEntity(Entity entity) {
      return (entity instanceof EntityBoat || entity instanceof EntityMinecart) && entity.getDistanceToEntity(minecraft.thePlayer) < 8.0F;
   }

   public static BiFunction getBiFunction() {
      return (longJump, group) -> new LongJumpIntaveBoatMode((LongJump)longJump, (SettingGroup)group);
   }

   @Override
   public void onPacketPostSend(EventPacket event) {
      Packet packet = event.getPacket();
      if (packet instanceof S1BPacketEntityAttach) {
         S1BPacketEntityAttach attachPacket = (S1BPacketEntityAttach)packet;
         if (minecraft.thePlayer == null || attachPacket.getCount2() == minecraft.thePlayer.getEntityId()) {
            event.cancel();
            this.enabled3 = true;
         }
      }
   }

   @Override
   public void onMove(EventMove event) {
      if (!minecraft.thePlayer.onGround && this.count3 > 0) {
         this.value = -1337.0;
      }

      if (this.enabled3) {
         this.count3++;
         if (CombatUtil.getCoordOffsetArray(0, minecraft.thePlayer)[9] == 1.0 && !minecraft.thePlayer.onGround && this.count2 > 3) {
            this.count2 = 1000;
         }

         if (this.count2 < 9 && this.count2 > 1 + MathHelper.getInt12((int)this.settingIntaveStrength.getValue(), 1, 3)) {
            event.setState(true);
         }

         if (this.count2 > 1010 && minecraft.thePlayer.onGround) {
            this.enabled3 = false;
            this.count2 = 0;
            this.setEnabled(false);
            return;
         }

         this.count2++;
      }
   }

   @Override
   public void onRender3D(float partialTicks) {
      if (this.entity != null && this.settingDisplayDirection.isEnabled()) {
         RenderUtils.updateState();
         Vec3 startVec = new Vec3(
            minecraft.thePlayer.lastTickPosX + (minecraft.thePlayer.posX - minecraft.thePlayer.lastTickPosX) * (double)partialTicks,
            minecraft.thePlayer.lastTickPosY
               + (minecraft.thePlayer.posY - minecraft.thePlayer.lastTickPosY) * (double)partialTicks
               + (double)minecraft.thePlayer.getHeight2()
               - 0.2,
            minecraft.thePlayer.lastTickPosZ + (minecraft.thePlayer.posZ - minecraft.thePlayer.lastTickPosZ) * (double)partialTicks
         );
         Vec3 directionVec = SimulatedPlayer.getVec3(this.entity, partialTicks).getVec311(startVec).getVec3(-1.0);
         System.out.println(directionVec.toString());
         ArrayList<Vec3> linePoints = new ArrayList<>(2);
         linePoints.add(startVec);
         linePoints.add(startVec.getVec36(directionVec.getVec3(20.0)));
         Rotation targetRotation = SimulatedPlayer.getRotation2(
            directionVec.xCoord, directionVec.yCoord, directionVec.zCoord, ClientGlobals.rotationManager.getRotation3(), 180.0F, 180.0F, false
         );
         targetRotation.yaw -= 180.0F;
         RenderUtils.processAction(Color.green);
         GL11.glLineWidth(4.0F);
         GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
         Tessellator tessellator = Tessellator.getTessellator();
         WorldRenderer worldRenderer = tessellator.getWorldRenderer();
         worldRenderer.updateAuxiliaryLoggerEntry(3, DefaultVertexFormats.auxiliaryLoggerEntry1);

         for (Vec3 point : linePoints) {
            worldRenderer.getWorldRenderer(
                  (double)((float)point.xCoord) - minecraft.getRenderManager().getRenderPosX(),
                  (double)((float)point.yCoord) - minecraft.getRenderManager().getRenderPosY(),
                  (double)((float)point.zCoord) - minecraft.getRenderManager().getRenderPosZ()
               )
               .updateState();
         }

         tessellator.updateState();
         RenderUtils.prepareScissor();
      }
   }

   @Override
   public void updateState2() {
      super.updateState2();
      this.applyIndex(32);
      this.applyIndex(26);
      this.applyIndex(3);
      this.applyIndex(25);
      this.applyIndex(7);
      this.applyIndex(40);
   }

   @Override
   public void onPreTick() {
      short vecSaltShort = 20072;
      int vecSaltInt = -521758288;
      if (this.entity == null) {
         Object[] nearbyVehicles = minecraft.theWorld
            .loadedEntityList
            .stream()
            .filter(item -> this.matchesEntityByEntity((Entity)item))
            .sorted(Comparator.comparingDouble(item -> computeValueByEntity((Entity)item)))
            .toArray();
         if (nearbyVehicles.length > 0) {
            this.entity = (EntityBoat)nearbyVehicles[0];
         }
      } else if (this.settingRotate.isEnabled()) {
         Vec3 targetVec = SimulatedPlayer.getVec3(this.entity, vecSaltShort, vecSaltInt);
         Rotation desiredRotation = SimulatedPlayer.getRotation2(
            targetVec.xCoord, targetVec.yCoord, targetVec.zCoord, ClientGlobals.rotationManager.getRotation3(), 180.0F, 180.0F, false
         );
         Rotation smoothedRotation = SimulatedPlayer.getRotation5(desiredRotation, ClientGlobals.rotationManager.getRotation3());
         ClientGlobals.rotationManager.applyRotation2(smoothedRotation, -10, 0, this.module);
      }
   }

   public LongJumpIntaveBoatMode(LongJump longJump, SettingGroup settingGroup) {
      super(longJump, settingGroup, "IntaveBoat");
      BooleanSupplier visibleSupplier = this::booleanValue;
      BooleanSupplier clickRangeSupplier = this::isActive2;
      this.settingIntaveLonger = (BooleanSetting)new BooleanSetting(this.module, "IntaveLonger", true, this.settingGroup).getSetting(visibleSupplier);
      this.settingDisplayDirection = (BooleanSetting)new BooleanSetting(this.module, "DisplayDirection", true, this.settingGroup).getSetting(visibleSupplier);
      this.settingRotate = (BooleanSetting)new BooleanSetting(this.module, "Rotate", false, this.settingGroup).getSetting(visibleSupplier);
      this.settingClick = (BooleanSetting)new BooleanSetting(this.module, "Click", true, this.settingGroup).getSetting(visibleSupplier);
      this.settingClickRange = new NumberSetting(this.module, "ClickRange", 6.0, 3.0, 6.0, 2, this.settingGroup).getNumberSetting(clickRangeSupplier);
      this.settingIntaveStrength = new NumberSetting(this.module, "IntaveStrength", 3.0, 1.0, 3.0, 0, this.settingGroup).getNumberSetting(visibleSupplier);
      this.settingHelpMessage = (BooleanSetting)new BooleanSetting(this.module, "HelpMessage", true, this.settingGroup).getSetting(visibleSupplier);
      this.enabled3 = false;
      this.count2 = 0;
      this.value = -1337.0;
      this.count3 = 0;
      this.entity = null;
      this.enabled4 = false;
   }
}
