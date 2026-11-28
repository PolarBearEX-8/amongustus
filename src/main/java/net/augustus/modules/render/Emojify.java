package net.augustus.modules.render;

import java.awt.Color;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.events.EventPacket;
import net.augustus.events.EventRenderEntity;
import net.augustus.events.listeners.RenderEntityListener;
import net.augustus.events.EventAttackEntity;
import net.augustus.events.listeners.AttackEntityListener;
import net.augustus.render.EmojifyRenderer;
import net.augustus.events.listeners.PacketListener;
import net.augustus.settings.StringValue;
import net.augustus.settings.DoubleValue;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S19PacketEntityStatus;

public class Emojify extends Module implements PacketListener, AttackEntityListener, RenderEntityListener {
   public final StringValue modes;
   public final DoubleValue comboHits;
   public final DoubleValue comboTime;
   private final EmojifyRenderer renderer;

   public Emojify() {
       super("Emojify", "Emojify", new Color(66, 135, 245), Categorys.RENDER);
      this.modes = new StringValue(
         this, "Modes", "default1", new String[]{"default1", "default2", "default3", "angry", "pogchamp", "prettygood", "trashmeme"}
      );
      this.comboHits = new DoubleValue(this, "ComboHits", 2.0, 0.0, 5.0, 0);
      this.comboTime = new DoubleValue(this, "ComboTime", 2.0, 0.0, 5.0, 0);
      this.renderer = new EmojifyRenderer();
   }

   public void onEnable() {
      this.renderer.emojiRegistry.registerDefaultSprites();
   }

   public void onDisable() {
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(26);
      this.toggleEventSubscription(1);
      this.toggleEventSubscription(31, 100);
   }

   public void onPacketReceive(EventPacket event) {
      Packet packet = event.getPacket();
      if (packet instanceof S19PacketEntityStatus) {
         S19PacketEntityStatus packet2 = (S19PacketEntityStatus)packet;
         if (packet2.getOpCode() != 2) {
            return;
         }

         if (mc.theWorld == null) {
            return;
         }

         Entity entity = packet2.getEntity(mc.theWorld);
         if (entity == null) {
            return;
         }

         this.renderer.onEntityHurt(entity);
      }
   }

   public void onAttackEntity(EventAttackEntity event) {
      this.renderer.onAttackEntity(event.getTarget());
   }

   public void onRenderEntity(EventRenderEntity event) {
      EntityLivingBase entity = event.getEntity();
      if (entity instanceof EntityPlayer) {
         EmojifyRenderer renderer2 = this.renderer;
         EntityPlayer player = (EntityPlayer)entity;
         double x = event.getX();
         double z = event.getZ();
         renderer2.renderEmoji(player, x, event.getY(), z);
      }
   }

}
