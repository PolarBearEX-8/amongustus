package client.util;

import client.event.PreUpdateConsumer;
import client.setting.CombatProperty;
import com.google.common.base.Charsets;
import com.mojang.authlib.GameProfile;
import io.netty.util.concurrent.GenericFutureListener;
import java.security.PrivateKey;
import java.util.Arrays;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import javax.crypto.SecretKey;
import net.minecraft.entity.player.EntityPlayerAdapter;
import net.minecraft.network.NetworkManager;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ListenerAdapter3 implements PreUpdateConsumer, InternalINetHandlerExtension {
   public static final AtomicInteger atomicInteger = new AtomicInteger(0);
   public static final Logger logger = LogManager.getLogger();
   public static final Random random = new Random();
   public final byte[] byteFlagArray = new byte[4];
   public final VanillaRunnable vanillaRunnable;
   public final NetworkManager networkManager;
   public CombatProperty combatProperty = CombatProperty.HELLO;
   public int count;
   public GameProfile gameProfile;
   public String labelText = "";
   public SecretKey secretKey;
   public EntityPlayerAdapter entityPlayerAdapter;

   public static GameProfile resolveGameProfile(ListenerAdapter3 listenerAdapter, GameProfile gameProfile2) {
      return listenerAdapter.gameProfile = gameProfile2;
   }

   public void applyText(String text) {
      try {
         logger.info("Disconnecting " + this.getText() + ": " + text);
         ChatComponentText chatComponentText = new ChatComponentText(text);
         this.networkManager.handlePacket6(new SpecialPacketAdapter(chatComponentText));
         this.networkManager.applyIChatComponent(chatComponentText);
      } catch (Exception exception) {
         logger.error("Error whilst disconnecting player", exception);
      }
   }

   public static VanillaRunnable resolveVanillaRunnable(ListenerAdapter3 listenerAdapter) {
      return listenerAdapter.vanillaRunnable;
   }

   public ListenerAdapter3(VanillaRunnable vanillaRunnable2, NetworkManager networkManager2) {
      this.vanillaRunnable = vanillaRunnable2;
      this.networkManager = networkManager2;
      random.nextBytes(this.byteFlagArray);
   }

   @Override
   public void applyCustomPacketAdapter(CustomPacketAdapter packet) {
      Validate.validState(this.combatProperty == CombatProperty.KEY, "Unexpected key packet", new Object[0]);
      PrivateKey privateItem = this.vanillaRunnable.getKeyPair().getPrivate();
      if (!Arrays.equals(this.byteFlagArray, packet.resolveByteArray(privateItem))) {
         throw new IllegalStateException("Invalid nonce!");
      } else {
         this.secretKey = packet.resolveSecretKey(privateItem);
         this.combatProperty = CombatProperty.AUTHENTICATING;
         this.networkManager.applySecretKey(this.secretKey);
         new UUIDPlayerThread(this, "User Authenticator #" + atomicInteger.incrementAndGet()).start();
      }
   }

   public static SecretKey resolveSecretKey(ListenerAdapter3 listenerAdapter) {
      return listenerAdapter.secretKey;
   }

   public String getText() {
      return this.gameProfile != null
         ? this.gameProfile + " (" + this.networkManager.getSocketAddress().toString() + ")"
         : String.valueOf(this.networkManager.getSocketAddress());
   }

   @Override
   public void applyIChatComponent(IChatComponent iChatComponent) {
      logger.info(this.getText() + " lost connection: " + iChatComponent.getString());
   }

   public static CombatProperty resolveCombatProperty(ListenerAdapter3 listenerAdapter, CombatProperty combatProperty2) {
      return listenerAdapter.combatProperty = combatProperty2;
   }

   public static Logger getLogger() {
      return logger;
   }

   public static GameProfile resolveGameProfile(ListenerAdapter3 listenerAdapter) {
      return listenerAdapter.gameProfile;
   }

   public GameProfile resolveGameProfile(GameProfile gameProfile2) {
      UUID uuid = UUID.nameUUIDFromBytes(("OfflinePlayer:" + gameProfile2.getName()).getBytes(Charsets.UTF_8));
      return new GameProfile(uuid, gameProfile2.getName());
   }

   public static String resolveText(ListenerAdapter3 listenerAdapter) {
      return listenerAdapter.labelText;
   }

   public void updateState2() {
      if (!this.gameProfile.isComplete()) {
         this.gameProfile = this.resolveGameProfile(this.gameProfile);
      }

      String text = this.vanillaRunnable.getMovingManager().resolveText(this.networkManager.getSocketAddress(), this.gameProfile);
      if (text != null) {
         this.applyText(text);
      } else {
         this.combatProperty = CombatProperty.ACCEPTED;
         if (this.vanillaRunnable.getCount3() >= 0 && !this.networkManager.isActive()) {
            this.networkManager
               .applyPacket(
                  new StandardPacketAdapter(this.vanillaRunnable.getCount3()), new ListenerAdapter3ChannelFutureListener(this), new GenericFutureListener[0]
               );
         }

         this.networkManager.handlePacket6(new SecondaryPacketAdapter(this.gameProfile));
         if (this.vanillaRunnable.getMovingManager().get(this.gameProfile.getId()) != null) {
            this.combatProperty = CombatProperty.DELAY_ACCEPT;
            this.entityPlayerAdapter = this.vanillaRunnable.getMovingManager().resolveEntityPlayerAdapter(this.gameProfile);
         } else {
            this.vanillaRunnable
               .getMovingManager()
               .applyNetworkManager(this.networkManager, this.vanillaRunnable.getMovingManager().resolveEntityPlayerAdapter(this.gameProfile));
         }
      }
   }

   @Override
   public void applyExtraPacketHandler(ExtraPacketHandler packet) {
      Validate.validState(this.combatProperty == CombatProperty.HELLO, "Unexpected hello packet", new Object[0]);
      this.gameProfile = packet.getGameProfile();
      if (this.vanillaRunnable.isEnabled2() && !this.networkManager.isActive()) {
         this.combatProperty = CombatProperty.KEY;
         this.networkManager.handlePacket6(new TertiaryPacketAdapter(this.labelText, this.vanillaRunnable.getKeyPair().getPublic(), this.byteFlagArray));
      } else {
         this.combatProperty = CombatProperty.READY_TO_ACCEPT;
      }
   }

   @Override
   public void updateState() {
      if (this.combatProperty == CombatProperty.READY_TO_ACCEPT) {
         this.updateState2();
      } else if (this.combatProperty == CombatProperty.DELAY_ACCEPT && this.vanillaRunnable.getMovingManager().get(this.gameProfile.getId()) == null) {
         this.combatProperty = CombatProperty.READY_TO_ACCEPT;
         this.vanillaRunnable.getMovingManager().applyNetworkManager(this.networkManager, this.entityPlayerAdapter);
         this.entityPlayerAdapter = null;
      }

      if (this.count++ == 600) {
         this.applyText("Took too long to log in");
      }
   }
}
