package net.augustus.modules.fun;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.modules.fun.mlg.TimedOverlayRenderTask;
import net.augustus.events.listeners.RenderListener;
import net.augustus.events.EventWorldColor;
import net.augustus.events.listeners.WorldColorListener;
import net.augustus.utils.math.RandomNumberGenerator;
import net.augustus.events.listeners.WorldTimeListener;
import net.augustus.utils.math.DefaultRandomGenerator;
import net.augustus.events.listeners.LoadWorldListener;
import net.augustus.events.EventLoadWorld;
import net.augustus.ui.UiIconResources;
import net.augustus.events.EventWorldTime;
import net.augustus.utils.audio.ClientSounds;
import net.augustus.render.RenderUtil;
import net.augustus.utils.render.ColorUtil;
import net.augustus.events.EventAttackEntity;
import net.augustus.events.listeners.TickListener;
import net.augustus.utils.audio.JavaFxAudioPlayer;
import net.augustus.events.listeners.AttackEntityListener;
import net.augustus.modules.fun.mlg.AnimatedGifSequence;
import net.augustus.utils.file.JarResourceListingUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class MLG extends Module implements LoadWorldListener, WorldColorListener, WorldTimeListener, RenderListener, AttackEntityListener, TickListener {
   private long worldTimeCounter;
   private int musicTrackIndex;
   private JavaFxAudioPlayer musicPlayer;
   private int attackHitCounter;
   private EntityLivingBase lastAttackedTarget;
   private long lastAttackTimeMs;
   private final List randomSoundLocations;
   private final ArrayList allGifSequences;
   private final ArrayList unusedGifQueue;
   private final CopyOnWriteArrayList activeOverlayTasks;

   public MLG() {
       super("MLG", "MLG", new Color(50, 168, 82), Categorys.FUN);
      this.musicTrackIndex = 1;
      this.musicPlayer = new JavaFxAudioPlayer();
      this.attackHitCounter = 10;
      this.randomSoundLocations = new ArrayList();
      this.allGifSequences = new ArrayList();
      this.unusedGifQueue = new ArrayList();
      this.activeOverlayTasks = new CopyOnWriteArrayList();

      for (String str : (Iterable<String>)(JarResourceListingUtil.listResourceEntries("sounds/mlg/randomsounds"))) {
         if (str.length() >= 4) {
            ResourceLocation location = new ResourceLocation("augustus", "sounds/mlg/randomsounds/" + str);
            Minecraft.getMinecraft().getSoundHandler().registerAugustusSound(location, "mlg/randomsounds/" + str.substring(0, str.length() - 4));
            this.randomSoundLocations.add(location);
         }
      }


      for (String str2 : (Iterable<String>)(JarResourceListingUtil.listResourceEntries("gifs/mlg/"))) {
         this.allGifSequences.add(new AnimatedGifSequence("gifs/mlg/" + str2, 60));
      }

      this.setLoadFromConfig(false);
   }

   @Override
   public void onEnable() {
      this.playNextMusicTrack();
   }

   @Override
   public void onDisable() {
      this.musicPlayer.stop();
      this.lastAttackedTarget = null;
      this.activeOverlayTasks.clear();
      this.unusedGifQueue.clear();
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(44, 100);
      this.toggleEventSubscription(45, 100);
      this.toggleEventSubscription(32, 100);
      this.toggleEventSubscription(1);
      this.toggleEventSubscription(40);
      this.toggleEventSubscription(17);
   }

   @Override
   public void onWorldColor(EventWorldColor event) {
      int rainbowColor = ColorUtil.getRainbowColor(0, 1000, 1.0F, 1.0F);
      event.setRed((float)ColorUtil.getRed(rainbowColor));
      event.setGreen((float)ColorUtil.getGreen(rainbowColor));
      event.setBlue((float)ColorUtil.getBlue(rainbowColor));
   }

   @Override
   public void onFogColor(EventWorldColor event) {
      if (!this.musicPlayer.isPlaying()) {
         this.playNextMusicTrack();
      }

      int rainbowColor = ColorUtil.getRainbowColor(0, 1000, 1.0F, 1.0F);
      event.setRed((float)ColorUtil.getRed(rainbowColor));
      event.setGreen((float)ColorUtil.getGreen(rainbowColor));
      event.setBlue((float)ColorUtil.getBlue(rainbowColor));
   }

   @Override
   public void onWorldTime(EventWorldTime event) {
      event.setWorldTime(this.worldTimeCounter = this.worldTimeCounter + (long)DefaultRandomGenerator.instance.nextInt((byte)50, (byte)100));
   }

   @Override
   public void onTick() {
      if (this.lastAttackedTarget == null || System.currentTimeMillis() - this.lastAttackTimeMs >= 5000L) {
         this.lastAttackedTarget = null;
      } else if (this.lastAttackedTarget.hurtTime == 10) {
         ClientSounds.playSound(ClientSounds.AUGUSTUS_TEXTURE10);
      }
   }

   private void playNextMusicTrack() {
      if (this.musicTrackIndex > 3) {
         this.musicTrackIndex = 1;
      }

      try {
         String str = "sounds/mlg/music/MLG" + this.musicTrackIndex + ".mp3";
         this.musicPlayer.playResource(str, true);
      } catch (Exception e) {
      }

      this.musicTrackIndex++;
   }

   @Override
   public void onRender2DPost(float partialTicks, ScaledResolution sr) {

      for (TimedOverlayRenderTask timedOverlayRenderTask : (Iterable<TimedOverlayRenderTask>)(this.activeOverlayTasks)) {
         timedOverlayRenderTask.renderOrExpire();
      }

      RenderUtil.resetGlColorState();
   }

   @Override
   public void onAttackEntity(EventAttackEntity event) {
      this.attackHitCounter++;
      this.lastAttackTimeMs = System.currentTimeMillis();
      if (event.getTarget() instanceof EntityLivingBase) {
         this.lastAttackedTarget = (EntityLivingBase)event.getTarget();
      }

      if (this.attackHitCounter > 4) {
         this.playRandomSound();
         this.spawnRandomGifOverlay();
         this.attackHitCounter = 0;
      }
   }

   @Override
   public void onLoadWorld(EventLoadWorld event) {
      this.lastAttackedTarget = null;
   }

   private void playRandomSound() {
      int n2 = DefaultRandomGenerator.instance.nextInt((byte)0, this.randomSoundLocations.size() - 1);
      ClientSounds.playSound((ResourceLocation)this.randomSoundLocations.get(n2));
   }

   private void spawnRandomGifOverlay() {
      if (this.unusedGifQueue.isEmpty()) {
         this.unusedGifQueue.addAll(this.allGifSequences);
      }

      int n = 0;
      ScaledResolution sr = new ScaledResolution(mc);
      double d = (double)(1.0F / (float)sr.getScaleFactor());
      RandomNumberGenerator instance = DefaultRandomGenerator.instance;
      int n3 = instance.nextInt((byte)0, this.unusedGifQueue.size() - 1);
      AnimatedGifSequence animatedGifSequence = (AnimatedGifSequence)this.unusedGifQueue.get(n3);
      this.unusedGifQueue.remove(n3);
      double d2 = 1.2;
      double d3 = 0.7;
      double d4 = DefaultRandomGenerator.instance.nextDouble(d3, d2);
      int n4;
      if (!animatedGifSequence.getResourceFolder().contains("sonic") && !animatedGifSequence.getResourceFolder().contains("yolo")) {
         n4 = DefaultRandomGenerator.instance.nextInt((short)5000, (short)7000);
      } else {
         n4 = 2;
      }

      int n5 = n4;

      while (n < n5) {
         n += animatedGifSequence.getTotalDurationMs();
      }

      int n6 = (int)((double)animatedGifSequence.getHeight() * d4);
      int n7 = (int)((double)animatedGifSequence.getWidth() * d4);
      instance = DefaultRandomGenerator.instance;
      int n9 = instance.nextInt((byte)0, mc.getFramebuffer().framebufferWidth - n7);
      int n10 = (int)DefaultRandomGenerator.instance.nextLong(0L, 120000L) % 2 == 0
         ? (int)((double)mc.getFramebuffer().framebufferHeight - (double)animatedGifSequence.getHeight() * d4)
         : 0;
      if (animatedGifSequence.getResourceFolder().contains("sonic")) {
         this.activeOverlayTasks.add(new TimedOverlayRenderTask(this, System.currentTimeMillis(), n, () -> renderScaledGifFrame(d, animatedGifSequence, n9, n10, n7, n6)));
      } else {
         CopyOnWriteArrayList activeOverlayTasks2 = this.activeOverlayTasks;
         long currentTimeMs = System.currentTimeMillis();
         activeOverlayTasks2.add(new TimedOverlayRenderTask(this, currentTimeMs, n, () -> {
            GL11.glPushMatrix();
            GL11.glScaled(d, d, 1.0);
            UiIconResources.drawIcon(animatedGifSequence.getCurrentFrame(), n9, n10, n7, n6, 1.0);
            GL11.glPopMatrix();
         }));
      }
   }

   private static void renderScaledGifFrame(double d, AnimatedGifSequence animatedGifSequence, int n, int n2, int n3, int n4) {
      GL11.glPushMatrix();
      GL11.glScaled(d, d, 1.0);
      UiIconResources.drawIcon(animatedGifSequence.getCurrentFrame(), n, n2, n3, n4, 1.0);
      GL11.glPopMatrix();
   }

   public static CopyOnWriteArrayList getActiveOverlayTasks(MLG mlg) {
      return mlg.activeOverlayTasks;
   }


}
