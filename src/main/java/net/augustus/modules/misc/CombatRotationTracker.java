package net.augustus.modules.misc;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.awt.Color;
import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.events.listeners.RenderListener;
import net.augustus.utils.ChatUtil;
import net.augustus.utils.file.ClientPaths;
import net.augustus.settings.ColorSetting;
import net.augustus.settings.ActionSetting;
import net.augustus.events.listeners.TickListener;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.SettingGroup;
import net.augustus.settings.DoubleValue;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketThreadUtil;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import org.lwjgl.opengl.GL11;

public class CombatRotationTracker extends Module implements TickListener, RenderListener {
   private static final int DATA_FORMAT_VERSION = 2;
   private final DoubleValue radius;
   private final BooleanValue consoleDebug;
   private final BooleanValue chatDebug;
   private final ActionSetting openFolder;
   private final BooleanValue showAimPoints;
   private final SettingGroup visualization;
   private final ColorSetting midpointColor;
   private final ColorSetting bestHitColor;
   private final DoubleValue dotSize;
   private double lastMidpointX;
   private double lastMidpointY;
   private double lastMidpointZ;
   private double lastBestHitX;
   private double lastBestHitY;
   private double lastBestHitZ;
   private boolean sessionActive;
   private int sessionTickCount;
   private EntityPlayer currentTarget;
   private JsonArray recordedTicks;
   private String sessionStartTimestamp;
   private long sessionStartMillis;
   private int totalHits;
   private double sumHorizontalDistance;
   private int prevOwnHurtTime;
   private int prevTargetHurtTime;
   private boolean prevOnGround;
   private double prevMotionY;
   private float prevYaw;
   private float prevPitch;
   private double prevTargetX;
   private double prevTargetY;
   private double prevTargetZ;
   private int ticksSinceLastHit;
   private int ticksSinceTargetHurt;
   private int ticksSinceJumpStart;
   private String jumpPhase;

   public CombatRotationTracker() {
       super("CombatRotationTracker", "KampfRotationsAufzeichner", new Color(80, 160, 220), Categorys.MISC);
      this.radius = new DoubleValue(this, "Radius", 6.0, 3.0, 10.0, 1);
      this.consoleDebug = new BooleanValue(this, "ConsoleDebug", false);
      this.chatDebug = new BooleanValue(this, "ChatDebug", false);
      this.openFolder = new ActionSetting(this, "OpenFolder", () -> {

         try {
            Desktop.getDesktop().open(new File(ClientPaths.CLIENT_DIR + File.separator + "rotationdata"));
         } catch (Exception e) {
            if (this.consoleDebug.getValue()) {
               System.err.println("[CombatRotationTracker] Failed to open folder: " + e.getMessage());
            }

            if (this.chatDebug.getValue()) {
               ChatUtil.sendChat("§cFailed to open folder: " + e.getMessage());
            }
         }
      });
      this.showAimPoints = new BooleanValue(this, "ShowAimPoints", false);
      SettingGroup visualizationGroup = new SettingGroup(this, "Visualization");
      BooleanValue showAimPoints2 = this.showAimPoints;
      this.visualization = visualizationGroup.visibleWhen(() -> showAimPoints2.getValue());
      Color color2 = new Color(0, 255, 0);
      SettingGroup visualization2 = this.visualization;
      Color color3 = color2;
      String str = "MidpointColor";
      this.midpointColor = new ColorSetting(this, str, color3, visualization2);
      color2 = new Color(255, 0, 0);
      visualization2 = this.visualization;
      color3 = color2;
      str = "BestHitColor";
      this.bestHitColor = new ColorSetting(this, str, color3, visualization2);
      this.dotSize = new DoubleValue(this, "DotSize", 6.0, 2.0, 15.0, 1, this.visualization);
      this.markHidden();
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(40);
      this.toggleEventSubscription(32);
   }

   @Override
   public void onEnable() {
      this.resetSessionState();
   }

   @Override
   public void onDisable() {
      if (this.sessionActive) {
         this.endAndSaveSession();
      }
   }

   @Override
   public void onTick() {
      if (mc.thePlayer != null && mc.theWorld != null) {
         EntityPlayer player = this.findNearestPlayer();
         if (player == null) {
            if (this.sessionActive) {
               this.endAndSaveSession();
            }
         } else {
            ArrayList list = new ArrayList();
            if (!this.sessionActive) {
               this.startSession(player);
               list.add("TARGET_SWITCH");
               String str = "new: " + player.getName();
               this.logEvent("TARGET_SWITCH", str);
            } else if (player != this.currentTarget) {
               this.endAndSaveSession();
               this.startSession(player);
               list.add("TARGET_SWITCH");
               String str3 = "new: " + player.getName();
               this.logEvent("TARGET_SWITCH", str3);
            }

            double posX = mc.thePlayer.posX;
            double posY = mc.thePlayer.posY;
            double posZ = mc.thePlayer.posZ;
            double motionX = mc.thePlayer.motionX;
            double motionY = mc.thePlayer.motionY;
            double motionZ = mc.thePlayer.motionZ;
            float rotationYaw = mc.thePlayer.rotationYaw;
            float rotationPitch = mc.thePlayer.rotationPitch;
            float f = MathHelper.wrapAngleTo180_float(rotationYaw - this.prevYaw);
            float f2 = rotationPitch - this.prevPitch;
            int hurtTime = mc.thePlayer.hurtTime;
            boolean onGround = mc.thePlayer.onGround;
            if (onGround && !this.prevOnGround) {
               this.jumpPhase = "ground";
               list.add("LAND");
               this.logEvent("LAND", (String)null);
            } else if (!onGround && this.prevOnGround && motionY > 0.0) {
               this.jumpPhase = "ascending";
               this.ticksSinceJumpStart = 0;
               list.add("JUMP_START");
               this.logEvent("JUMP_START", (String)null);
            } else if (!onGround && motionY > 0.0) {
               short s = 6616;
               this.jumpPhase = "ascending";
            } else if (!onGround && motionY <= 0.0 && motionY > -0.1) {
               short s2 = 23963;
               this.jumpPhase = "peak";
            } else if (!onGround && motionY <= -0.1) {
               short s3 = 32592;
               this.jumpPhase = "descending";
            } else if (onGround) {
               this.jumpPhase = "ground";
            }

            if (!this.jumpPhase.equals("ground")) {
               this.ticksSinceJumpStart++;
            } else {
               this.ticksSinceJumpStart = 0;
            }

            boolean flag = hurtTime == 10 && this.prevOwnHurtTime != 10;
            if (flag) {
               String knockbackCardinalDirection = getKnockbackCardinalDirection(motionX, motionZ);
               list.add("MY_KNOCKBACK");
               String str8 = "source: " + knockbackCardinalDirection;
               this.logEvent("MY_KNOCKBACK", str8);
            }

            double posX2 = player.posX;
            double posY2 = player.posY;
            double d = posY2 + (double)player.getEyeHeight();
            double posZ2 = player.posZ;
            double d2 = player.posX - this.prevTargetX;
            double d3 = player.posY - this.prevTargetY;
            double d4 = player.posZ - this.prevTargetZ;
            int hurtTime2 = player.hurtTime;
            boolean onGround2 = player.onGround;
            this.currentTarget = player;
            boolean flag2 = hurtTime2 > this.prevTargetHurtTime;
            if (flag2) {
               this.ticksSinceLastHit = 0;
               this.ticksSinceTargetHurt = 0;
               this.totalHits++;
               list.add("TARGET_HIT");
               String str10 = "targetHurtTime: 10";
               this.logEvent("TARGET_HIT", str10);
            }

            double d5 = posY + (double)mc.thePlayer.getEyeHeight();
            double d6 = posX2 - posX;
            double d7 = d - d5;
            double d8 = posZ2 - posZ;
            double d9 = Math.sqrt(d6 * d6 + d8 * d8);
            double d10 = Math.sqrt(d6 * d6 + d7 * d7 + d8 * d8);
            this.sumHorizontalDistance += d9;
            Vec3 look = mc.thePlayer.getLook(1.0F);
            double d11;
            if (d10 < 1.0E-6) {
               d11 = 0.0;
            } else {
               double d12 = d6 / d10;
               double d13 = d7 / d10;
               double d14 = d8 / d10;
               double d15 = look.xCoord * d12 + look.yCoord * d13 + look.zCoord * d14;
               if (d15 > 1.0) {
                  d15 = 1.0;
               } else if (d15 < -1.0) {
                  d15 = -1.0;
               }

               d11 = Math.toDegrees(Math.acos(d15));
            }

            boolean isKeyDown = mc.gameSettings.keyBindForward.isKeyDown();
            boolean isKeyDown2 = mc.gameSettings.keyBindLeft.isKeyDown();
            boolean isKeyDown3 = mc.gameSettings.keyBindRight.isKeyDown();
            boolean isKeyDown4 = mc.gameSettings.keyBindBack.isKeyDown();
            boolean isKeyDown5 = mc.gameSettings.keyBindJump.isKeyDown();
            float f3 = calculateYaw(d6, d8);
            float f4 = calculatePitch(d7, d9);
            float f5 = MathHelper.wrapAngleTo180_float(rotationYaw - f3);
            float f6 = rotationPitch - f4;
            AxisAlignedBB box = this.currentTarget.getEntityBoundingBox().expand(0.1, 0.1, 0.1);
            double d16 = clamp(posX, box.minX, box.maxX);
            double d17 = clamp(d5, box.minY, box.maxY);
            double d18 = clamp(posZ, box.minZ, box.maxZ);
            float f7;
            float f8;
            float f9;
            float f10;
            double d19;
            if (d16 == posX && d17 == d5 && d18 == posZ) {
               f7 = rotationYaw;
               f8 = rotationPitch;
               f9 = 0.0F;
               f10 = 0.0F;
               d19 = 0.0;
            } else {
               double d20 = d16 - posX;
               double d21 = d17 - d5;
               double d22 = d18 - posZ;
               double d23 = Math.sqrt(d20 * d20 + d22 * d22);
               if (d23 < 1.0E-6) {
                  f7 = rotationYaw;
                  f8 = calculatePitch(d21, 1.0E-4);
                  f9 = 0.0F;
                  f10 = rotationPitch - f8;
                  d19 = (double)Math.abs(f10);
               } else {
                  f7 = calculateYaw(d20, d22);
                  f8 = calculatePitch(d21, d23);
                  f9 = MathHelper.wrapAngleTo180_float(rotationYaw - f7);
                  f10 = rotationPitch - f8;
                  double d24 = Math.sqrt(d20 * d20 + d21 * d21 + d22 * d22);
                  double d25 = look.xCoord * (d20 / d24) + look.yCoord * (d21 / d24) + look.zCoord * (d22 / d24);
                  d25 = Math.max(-1.0, Math.min(1.0, d25));
                  d19 = Math.toDegrees(Math.acos(d25));
               }
            }

            this.lastMidpointX = posX2;
            this.lastMidpointY = d;
            this.lastMidpointZ = posZ2;
            this.lastBestHitX = d16;
            this.lastBestHitY = d17;
            this.lastBestHitZ = d18;
            if (this.consoleDebug.getValue()) {
               System.out
                  .printf(
                     "T:%d | dY:%.2f dP:%.2f | miderr:%.2f,%.2f | besterr:%.2f,%.2f | hDist:%.2f vDist:%.2f | mVY:%.4f tVY:%.4f | mHurt:%d tHurt:%d | ground:%b | jump:%s%n",
                     this.sessionTickCount,
                     f,
                     f2,
                     f5,
                     f6,
                     f9,
                     f10,
                     d9,
                     d7,
                     motionY,
                     d3,
                     hurtTime,
                     hurtTime2,
                     onGround,
                     this.jumpPhase
                  );
            }

            JsonObject json = new JsonObject();
            json.addProperty("tick", this.sessionTickCount);
            JsonObject json2 = new JsonObject();
            json2.addProperty("posX", roundDouble(posX, 4));
            json2.addProperty("posY", roundDouble(posY, 4));
            json2.addProperty("posZ", roundDouble(posZ, 4));
            json2.addProperty("velX", roundDouble(motionX, 6));
            json2.addProperty("velY", roundDouble(motionY, 6));
            json2.addProperty("velZ", roundDouble(motionZ, 6));
            json2.addProperty("yaw", roundFloat(rotationYaw, 4));
            json2.addProperty("pitch", roundFloat(rotationPitch, 4));
            json2.addProperty("dYaw", roundFloat(f, 4));
            json2.addProperty("dPitch", roundFloat(f2, 4));
            json2.addProperty("hurtTime", hurtTime);
            json2.addProperty("onGround", onGround);
            json2.addProperty("jumpPhase", this.jumpPhase);
            json.add("own", json2);
            JsonObject json3 = new JsonObject();
            json3.addProperty("posX", roundDouble(posX2, 4));
            json3.addProperty("posY", roundDouble(posY2, 4));
            json3.addProperty("posZ", roundDouble(posZ2, 4));
            json3.addProperty("velX", roundDouble(d2, 6));
            json3.addProperty("velY", roundDouble(d3, 6));
            json3.addProperty("velZ", roundDouble(d4, 6));
            json3.addProperty("hurtTime", hurtTime2);
            json3.addProperty("onGround", onGround2);
            json.add("enemy", json3);
            JsonObject json4 = new JsonObject();
            json4.addProperty("relX", roundDouble(d6, 4));
            json4.addProperty("relY", roundDouble(d7, 4));
            json4.addProperty("relZ", roundDouble(d8, 4));
            json4.addProperty("horizontalDist", roundDouble(d9, 4));
            json4.addProperty("totalDist", roundDouble(d10, 4));
            json4.addProperty("fovDelta", roundDouble(d11, 4));
            json.add("relative", json4);
            JsonObject json5 = new JsonObject();
            json5.addProperty("forward", isKeyDown ? 1 : 0);
            json5.addProperty("left", isKeyDown2 ? 1 : 0);
            json5.addProperty("right", isKeyDown3 ? 1 : 0);
            json5.addProperty("back", isKeyDown4 ? 1 : 0);
            json5.addProperty("jump", isKeyDown5 ? 1 : 0);
            json.add("keystrokes", json5);
            JsonObject json6 = new JsonObject();
            json6.addProperty("expectedYaw", roundFloat(f3, 4));
            json6.addProperty("expectedPitch", roundFloat(f4, 4));
            json6.addProperty("yawError", roundFloat(f5, 4));
            json6.addProperty("pitchError", roundFloat(f6, 4));
            json6.addProperty("totalError", roundDouble(d11, 4));
            json.add("aimMid", json6);
            JsonObject json7 = new JsonObject();
            json7.addProperty("expectedYaw", roundFloat(f7, 4));
            json7.addProperty("expectedPitch", roundFloat(f8, 4));
            json7.addProperty("yawError", roundFloat(f9, 4));
            json7.addProperty("pitchError", roundFloat(f10, 4));
            json7.addProperty("totalError", roundDouble(d19, 4));
            json.add("aimBest", json7);
            JsonObject json8 = new JsonObject();
            json8.addProperty("ticksSinceLastHit", this.ticksSinceLastHit);
            json8.addProperty("ticksSinceTargetHurt", this.ticksSinceTargetHurt);
            json8.addProperty("ticksSinceJumpStart", this.ticksSinceJumpStart);
            json8.addProperty("myKnockback", flag);
            json8.addProperty("targetHurt", flag2);
            json.add("context", json8);
            JsonArray array = new JsonArray();

            for (String str12 : (Iterable<String>)(list)) {
               array.add(str12);
            }

            json.add("events", array);
            this.recordedTicks.add(json);
            this.prevOwnHurtTime = hurtTime;
            this.prevTargetHurtTime = hurtTime2;
            this.prevOnGround = onGround;
            this.prevMotionY = motionY;
            this.prevYaw = rotationYaw;
            this.prevPitch = rotationPitch;
            this.prevTargetX = player.posX;
            this.prevTargetY = player.posY;
            this.prevTargetZ = player.posZ;
            this.ticksSinceLastHit++;
            this.ticksSinceTargetHurt++;
            this.sessionTickCount++;
         }
      } else {
         if (this.sessionActive) {
            this.endAndSaveSession();
         }
      }
   }

   @Override
   public void onRender3D(float partialTicks) {
      if (this.showAimPoints.getValue() && this.sessionActive && this.currentTarget != null) {
         double renderPosX = mc.getRenderManager().getRenderPosX();
         double renderPosY = mc.getRenderManager().getRenderPosY();
         double renderPosZ = mc.getRenderManager().getRenderPosZ();
         float value = (float)this.dotSize.getValue();
         int colorInt = this.midpointColor.getColorInt((byte)0);
         int colorInt2 = this.bestHitColor.getColorInt((byte)0);
         GL11.glPushMatrix();
         GL11.glTranslated(-renderPosX, -renderPosY, -renderPosZ);
         GL11.glDisable(3553);
         GL11.glDisable(2929);
         GL11.glEnable(3042);
         GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
         GL11.glPointSize(value);
         GL11.glBegin(0);
         GlStateManager.color((float)(colorInt >> 16 & 0xFF) / 255.0F, (float)(colorInt >> 8 & 0xFF) / 255.0F, (float)(colorInt & 0xFF) / 255.0F, (float)(colorInt >> 24 & 0xFF) / 255.0F);
         GL11.glVertex3d(this.lastMidpointX, this.lastMidpointY, this.lastMidpointZ);
         GlStateManager.color((float)(colorInt2 >> 16 & 0xFF) / 255.0F, (float)(colorInt2 >> 8 & 0xFF) / 255.0F, (float)(colorInt2 & 0xFF) / 255.0F, (float)(colorInt2 >> 24 & 0xFF) / 255.0F);
         GL11.glVertex3d(this.lastBestHitX, this.lastBestHitY, this.lastBestHitZ);
         GL11.glEnd();
         GL11.glEnable(2929);
         GL11.glEnable(3553);
         GL11.glDisable(3042);
         GL11.glPopMatrix();
      }
   }

   private EntityPlayer findNearestPlayer() {
      double value = this.radius.getValue();
      EntityPlayer player = null;
      double maxvalue = Double.MAX_VALUE;

      for (EntityPlayer player2 : (Iterable<EntityPlayer>)(mc.theWorld.playerEntities)) {
         if (player2 != mc.thePlayer) {
            double distanceToEntity = (double)mc.thePlayer.getDistanceToEntity(player2);
            if (distanceToEntity <= value && distanceToEntity < maxvalue) {
               maxvalue = distanceToEntity;
               player = player2;
            }
         }
      }

      return player;
   }

   private void startSession(EntityPlayer player) {
      this.sessionActive = true;
      this.sessionTickCount = 0;
      this.currentTarget = player;
      this.recordedTicks = new JsonArray();
      this.sessionStartTimestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
      this.sessionStartMillis = System.currentTimeMillis();
      this.totalHits = 0;
      this.sumHorizontalDistance = 0.0;
      this.prevOwnHurtTime = mc.thePlayer.hurtTime;
      this.prevTargetHurtTime = 0;
      this.prevOnGround = mc.thePlayer.onGround;
      this.prevMotionY = mc.thePlayer.motionY;
      this.prevYaw = mc.thePlayer.rotationYaw;
      this.prevPitch = mc.thePlayer.rotationPitch;
      this.prevTargetX = player.posX;
      this.prevTargetY = player.posY;
      this.prevTargetZ = player.posZ;
      this.ticksSinceLastHit = 0;
      this.ticksSinceTargetHurt = 0;
      this.ticksSinceJumpStart = 0;
      String str;
      if (mc.thePlayer.onGround) {
         str = "ground";
      } else {
         short s = 3090;
         str = "descending";
      }

      this.jumpPhase = str;
      if (this.consoleDebug.getValue()) {
         System.out.println("[CombatRotationTracker] Session started — target: " + player.getName());
      }

      if (this.chatDebug.getValue()) {
         ChatUtil.sendChat("Session started — target: " + player.getName());
      }
   }

   private void endAndSaveSession() {
      if (this.recordedTicks != null && this.recordedTicks.size() != 0) {
         JsonObject json = new JsonObject();
         json.addProperty("version", 2);
         json.addProperty("mouseSensitivity", roundFloat(mc.gameSettings.mouseSensitivity, 4));
         json.addProperty("sessionStart", this.sessionStartTimestamp);
         json.addProperty("tickCount", this.sessionTickCount);
         json.addProperty("sessionDurationTicks", this.sessionTickCount);
         json.addProperty("totalHits", this.totalHits);
         json.addProperty("avgHorizontalDist", this.sessionTickCount > 0 ? roundDouble(this.sumHorizontalDistance / (double)this.sessionTickCount, 4) : 0.0);
         json.addProperty("player", mc.thePlayer.getName());
         json.addProperty("target", this.currentTarget != null ? this.currentTarget.getName() : "unknown");
         json.add("ticks", this.recordedTicks);
         String str = ClientPaths.CLIENT_DIR + File.separator + "rotationdata";
         new File(str).mkdirs();
         String str2 = "combat_" + this.sessionStartTimestamp + "_" + this.sessionStartMillis + ".json";
         File file = new File(str, str2);
         int sessionTickCount2 = this.sessionTickCount;
         int totalHits2 = this.totalHits;
         boolean value = this.consoleDebug.getValue();
         boolean value2 = this.chatDebug.getValue();
         this.sessionActive = false;
         this.recordedTicks = null;
         this.currentTarget = null;
         CompletableFuture.runAsync(() -> {

            try (FileWriter fileWriter = new FileWriter(file)) {
               new GsonBuilder().setPrettyPrinting().create().toJson(json, fileWriter);
               String str3 = "Session saved: " + str2 + " (" + sessionTickCount2 + " ticks, " + totalHits2 + " hits)";
               if (value) {
                  System.out.println("[CombatRotationTracker] " + str3);
               }

               if (value2) {
                  PacketThreadUtil.runOnMainThreadImmediate(() -> sendInfoChat(str3));
               }
            } catch (Exception e) {
               String str4 = "Failed to save session: " + e.getMessage();
               if (value) {
                  System.err.println("[CombatRotationTracker] " + str4);
                  e.printStackTrace();
               }

               if (value2) {
                  PacketThreadUtil.runOnMainThreadImmediate(() -> sendErrorChat(str4));
               }
            }
         });
      } else {
         this.sessionActive = false;
      }
   }

   private void resetSessionState() {
      this.sessionActive = false;
      this.sessionTickCount = 0;
      this.currentTarget = null;
      this.recordedTicks = null;
      this.totalHits = 0;
      this.sumHorizontalDistance = 0.0;
      this.prevOwnHurtTime = 0;
      this.prevTargetHurtTime = 0;
      this.prevOnGround = true;
      this.prevMotionY = 0.0;
      this.prevYaw = 0.0F;
      this.prevPitch = 0.0F;
      this.prevTargetX = 0.0;
      this.prevTargetY = 0.0;
      this.prevTargetZ = 0.0;
      this.ticksSinceLastHit = 0;
      this.ticksSinceTargetHurt = 0;
      this.ticksSinceJumpStart = 0;
      this.jumpPhase = "ground";
   }

   private static float calculateYaw(double d, double d2) {
      return (float)Math.toDegrees(Math.atan2(-d, d2));
   }

   private static float calculatePitch(double d, double d2) {
      return (float)(-Math.toDegrees(Math.atan2(d, d2)));
   }

   private static String getKnockbackCardinalDirection(double d, double d2) {
      double d3 = Math.toDegrees(Math.atan2(d2, d));
      if (d3 < 0.0) {
         d3 += 360.0;
      }

      return (new String[]{"E", "SE", "S", "SW", "W", "NW", "N", "NE"})[(int)Math.round(d3 / 45.0) % 8];
   }

   private void logEvent(String str, String str2) {
      String str3 = str2 != null ? "[EVENT] " + str + " | T:" + this.sessionTickCount + " | " + str2 : "[EVENT] " + str + " | T:" + this.sessionTickCount;
      if (this.consoleDebug.getValue()) {
         System.out.println(str3);
      }

      if (this.chatDebug.getValue()) {
         ChatUtil.sendChat(str3);
      }
   }

   private static double clamp(double value, double min, double max) {
      return Math.max(min, Math.min(max, value));
   }

   private static double roundDouble(double d, int n) {
      try {
         return new BigDecimal(Double.toString(d)).setScale(n, RoundingMode.HALF_UP).doubleValue();
      } catch (Exception e) {
         return d;
      }
   }

   private static double roundFloat(float f, int n) {
      return roundDouble((double)f, n);
   }

   private static void sendErrorChat(String str) {
      ChatUtil.sendChat("§c" + str);
   }

   private static void sendInfoChat(String str) {
      ChatUtil.sendChat(str);
   }

}
