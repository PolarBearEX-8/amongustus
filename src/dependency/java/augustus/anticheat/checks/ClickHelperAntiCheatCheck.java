package augustus.anticheat.checks;

import client.Client;
import client.util.ClickHelper;
import client.util.DecimalFormatter;
import client.util.MinecraftAccessor;
import client.util.Packet;
import client.util.RayTraceUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerAction;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C0BPacketEntityAction;

public class ClickHelperAntiCheatCheck extends AntiCheatCheck {
   public final ClickHelper clickHelper;
   public final ClickHelper clickHelper2 = new ClickHelper(20);
   public final ClickHelper clickHelper3;
   public int count;
   public final ClickHelper clickHelper4 = new ClickHelper(20);
   public boolean enabled;
   public int count2;
   public final ClickHelper clickHelper5;
   public EntityLivingBase entityLivingBase;
   public int count3;
   public int count4;

   @Override
   public void handlePacket(Packet packet) {
      Client.getClient().getClass();
   }

   public void applyC03PacketPlayer(C03PacketPlayer packet) {
      this.count2++;
      if (this.count4 != -1) {
         this.count4++;
      }

      if (this.isActiveAsFlag()) {
         this.count++;
         if (this.entityLivingBase.hurtTime == 9) {
            this.count = 0;
            if (this.count3 > 0) {
               double count5 = (double)this.count3;
               this.clickHelper5.processDouble(count5);
               if (this.clickHelper5.canExecute()) {
                  String text = "Count AVG: "
                     + DecimalFormatter.formatDecimal(this.clickHelper5.getDouble11(), 3)
                     + " "
                     + DecimalFormatter.formatDecimal(this.clickHelper5.getValueAsDouble(), 3);
                  this.applyText2(text);
               }
            }

            this.count3 = 0;
            double offset = this.enabled ? 1.0 : 0.0;
            this.clickHelper3.processDouble(offset);
            this.enabled = false;
         }
      }
   }

   private boolean isActiveAsFlag() {
      return this.entityLivingBase != null && this.count2 <= 40;
   }

   @Override
   public void updateState2() {
      Client.getClient().getClass();
   }

   public void applyC02PacketUseEntity(C02PacketUseEntity packet) {
      Entity entity = packet.processAction(MinecraftAccessor.minecraft.theWorld);
      if (entity instanceof EntityLivingBase) {
         this.entityLivingBase = (EntityLivingBase)entity;
      }

      this.count2 = 0;
   }

   private void applyText2(String text) {
      RayTraceUtil.applyString(text);
   }

   public void setCount3(C0BPacketEntityAction count5) {
      if (!this.isActiveAsFlag()) {
         this.count4 = -1;
         this.count3 = 0;
      } else {
         EntityPlayerAction entityPlayerAction = count5.getEntityPlayerAction();
         if (entityPlayerAction == EntityPlayerAction.START_SPRINTING) {
            this.count3++;
            double count6 = (double)this.count;
            this.clickHelper2.processDouble(count6);
            if (this.count4 != -1) {
               if (this.count4 == 0) {
                  String text = "IMPOSSIBLE DURATION!";
                  this.applyText2(text);
               }

               double count7 = (double)this.count4;
               this.clickHelper.processDouble(count7);
            }

            this.enabled = true;
            if (this.isActive2()) {
               if (this.clickHelper2.canExecute()) {
                  String text2 = "StartHurtTimes AVG: "
                     + DecimalFormatter.formatDecimal(this.clickHelper2.getDouble11(), 3)
                     + " "
                     + DecimalFormatter.formatDecimal(this.clickHelper2.getValueAsDouble(), 3);
                  this.applyText2(text2);
               }

               if (this.clickHelper4.canExecute()) {
                  String text3 = "EndHurtTimes AVG: "
                     + DecimalFormatter.formatDecimal(this.clickHelper4.getDouble11(), 3)
                     + " "
                     + DecimalFormatter.formatDecimal(this.clickHelper4.getValueAsDouble(), 3);
                  this.applyText2(text3);
               }

               String text4 = "Success AVG: "
                  + DecimalFormatter.formatDecimal(this.clickHelper3.getDouble11(), 3)
                  + " "
                  + DecimalFormatter.formatDecimal(this.clickHelper3.getValueAsDouble(), 3);
               this.applyText2(text4);
               if (this.clickHelper.canExecute()) {
                  String text5 = "Duration AVG: "
                     + DecimalFormatter.formatDecimal(this.clickHelper.getDouble11(), 3)
                     + " "
                     + DecimalFormatter.formatDecimal(this.clickHelper.getValueAsDouble(), 3);
                  this.applyText2(text5);
               }
            }
         } else if (entityPlayerAction == EntityPlayerAction.STOP_SPRINTING) {
            double count8 = (double)this.count;
            this.clickHelper4.processDouble(count8);
            this.count4 = 0;
         }
      }
   }

   private boolean isActive2() {
      return true;
   }

   public ClickHelperAntiCheatCheck(String text, AntiCheatManager antiCheatManager) {
      super(text, antiCheatManager);
      this.clickHelper = new ClickHelper(20);
      this.clickHelper5 = new ClickHelper(40);
      this.clickHelper3 = new ClickHelper(200);
      this.count4 = -1;
      this.count3 = 0;
      this.count = 0;
      this.entityLivingBase = null;
      this.enabled = false;
   }
}
