package net.minecraft.item;

import client.Client;
import client.setting.CannotSupportPropertyPredicate;
import client.util.ConfigManager;
import client.util.Listener60EntryAdapter;
import client.util.ModularStringEntry;
import client.util.OnlineConfigsManager;
import client.util.SmoothPicker;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.logging.Level;
import org.apache.commons.io.FileUtils;
import org.lwjgl.opengl.GL11;

public class GoldenShard {
   public final SmoothPicker smoothPicker;
   public ModularStringEntry modularStringEntry;
   public final HashMap hashMap = new HashMap();
   public List list;

   private boolean isActive() {
      return GL11.glGetString(7937).toLowerCase().contains("amd");
   }

   private void applyText(String text, String text2, boolean flag) {
      if (text == null || text.isEmpty()) {
         throw new IllegalArgumentException("ResourcePath cannot be null or empty");
      }
      text = text.replace('\\', '/');
      InputStream resourceAsStream = CannotSupportPropertyPredicate.class.getClassLoader().getResourceAsStream(text);
      if (resourceAsStream == null) {
         throw new IllegalArgumentException("The embedded resource '" + text + "' cannot be found in client");
      } else {
         File file = new File(text);
         File file2 = new File(ConfigManager.labelText + text2 + "/" + file.getName());
         if (!file2.getParentFile().exists()) {
            file2.getParentFile().mkdirs();
         }

         try {
            if (!file2.exists() || flag) {
               FileUtils.copyInputStreamToFile(resourceAsStream, file2);
               resourceAsStream.close();
            }
         } catch (IOException ioException) {
            System.err.println(Level.SEVERE + "Could not save " + file2.getName() + " to " + file2.getAbsolutePath());
         }
      }
   }

   public void applyText(String text) {
      ModularStringEntry modularStringEntry2 = (ModularStringEntry)this.hashMap.get(text.toLowerCase(Locale.ROOT));
      if (modularStringEntry2 != null) {
         this.modularStringEntry = modularStringEntry2;
      }
   }

   public String getText() {
      return this.modularStringEntry.getLabelText().toLowerCase(Locale.ROOT);
   }

   public ModularStringEntry getModularStringEntry() {
      return this.modularStringEntry;
   }

   public List getList() {
      return this.list;
   }

   public GoldenShard() {
      SmoothPicker smoothPicker2 = new SmoothPicker(Listener60EntryAdapter.listenerTracker, "SelectedShader", "");
      Consumer consumer = (item -> this.applyText((String)item));
      smoothPicker2 = (SmoothPicker)smoothPicker2.withConsumer(consumer);
      Supplier supplier = this::getText;
      this.smoothPicker = (SmoothPicker)smoothPicker2.withSupplier(supplier);
      String text = Client.getClient().getString4().toLowerCase(Locale.ROOT);
      String text2 = text + "/shaders";
      String text3 = "assets/augustus/shaders/abraxas.frag";
      this.applyText(text3, text2, false);
      String text4 = text + "/shaders";
      String text5 = "assets/augustus/shaders/galaxy.frag";
      this.applyText(text5, text4, false);
      String text6 = text + "/shaders";
      String text7 = "assets/augustus/shaders/galaxy2.frag";
      this.applyText(text7, text6, false);
      String text8 = text + "/shaders";
      String text9 = "assets/augustus/shaders/purplewater.frag";
      this.applyText(text9, text8, false);
      String text10 = text + "/shaders";
      String text11 = "assets/augustus/shaders/liquidbounce.frag";
      this.applyText(text11, text10, false);
      String text12 = text + "/shaders";
      String text13 = "assets/augustus/shaders/redwaterlike.frag";
      this.applyText(text13, text12, false);
      String text14 = text + "/shaders";
      String text15 = "assets/augustus/shaders/sexy.frag";
      this.applyText(text15, text14, false);
      String text16 = text + "/shaders";
      String text17 = "assets/augustus/shaders/metal.frag";
      this.applyText(text17, text16, false);
      String text18 = text + "/shaders";
      String text19 = "assets/augustus/shaders/snowstar.frag";
      this.applyText(text19, text18, false);
      String text20 = text + "/shaders";
      String text21 = "assets/augustus/shaders/spider.frag";
      this.applyText(text21, text20, false);
      String text22 = text + "/shaders";
      String text23 = "assets/augustus/shaders/trinity.frag";
      this.applyText(text23, text22, false);
      String text24 = text + "/shaders";
      String text25 = "assets/augustus/shaders/bluefog.frag";
      this.applyText(text25, text24, false);
      if (this.isActive()) {
         String text26 = text + "/shaders";
         String text27 = "assets/augustus/shaders/bash117.frag";
         this.applyText(text27, text26, false);
         String text28 = text + "/shaders";
         String text29 = "assets/augustus/shaders/blackhole.frag";
         this.applyText(text29, text28, false);
         String text30 = text + "/shaders";
         String text31 = "assets/augustus/shaders/BlueArt.frag";
         this.applyText(text31, text30, false);
         String text32 = text + "/shaders";
         String text33 = "assets/augustus/shaders/core3.frag";
         this.applyText(text33, text32, false);
         String text34 = text + "/shaders";
         String text35 = "assets/augustus/shaders/core4.frag";
         this.applyText(text35, text34, false);
         String text36 = text + "/shaders";
         String text37 = "assets/augustus/shaders/Cubictrip.frag";
         this.applyText(text37, text36, false);
         String text38 = text + "/shaders";
         String text39 = "assets/augustus/shaders/dvdlogo.frag";
         this.applyText(text39, text38, false);
         String text40 = text + "/shaders";
         String text41 = "assets/augustus/shaders/dynamic5.frag";
         this.applyText(text41, text40, false);
         String text42 = text + "/shaders";
         String text43 = "assets/augustus/shaders/FavCurrent.frag";
         this.applyText(text43, text42, false);
         String text44 = text + "/shaders";
         String text45 = "assets/augustus/shaders/fireworks.frag";
         this.applyText(text45, text44, false);
         String text46 = text + "/shaders";
         String text47 = "assets/augustus/shaders/foggy.frag";
         this.applyText(text47, text46, false);
         String text48 = text + "/shaders";
         String text49 = "assets/augustus/shaders/foggy2.frag";
         this.applyText(text49, text48, false);
         String text50 = text + "/shaders";
         String text51 = "assets/augustus/shaders/fractal.frag";
         this.applyText(text51, text50, false);
         String text52 = text + "/shaders";
         String text53 = "assets/augustus/shaders/galaxystorm.frag";
         this.applyText(text53, text52, false);
         String text54 = text + "/shaders";
         String text55 = "assets/augustus/shaders/idk1.frag";
         this.applyText(text55, text54, false);
         String text56 = text + "/shaders";
         String text57 = "assets/augustus/shaders/LenniZwei.frag";
         this.applyText(text57, text56, false);
         String text58 = text + "/shaders";
         String text59 = "assets/augustus/shaders/pulse.frag";
         this.applyText(text59, text58, false);
         String text60 = text + "/shaders";
         String text61 = "assets/augustus/shaders/random_galaxy.frag";
         this.applyText(text61, text60, false);
         String text62 = text + "/shaders";
         String text63 = "assets/augustus/shaders/randomboxes.frag";
         this.applyText(text63, text62, false);
         String text64 = text + "/shaders";
         String text65 = "assets/augustus/shaders/retrostarnight.frag";
         this.applyText(text65, text64, false);
         String text66 = text + "/shaders";
         String text67 = "assets/augustus/shaders/ricky.frag";
         this.applyText(text67, text66, false);
         String text68 = text + "/shaders";
         String text69 = "assets/augustus/shaders/rkfadeside.frag";
         this.applyText(text69, text68, false);
         String text70 = text + "/shaders";
         String text71 = "assets/augustus/shaders/singularity.frag";
         this.applyText(text71, text70, false);
         String text72 = text + "/shaders";
         String text73 = "assets/augustus/shaders/snowflakes.frag";
         this.applyText(text73, text72, false);
         String text74 = text + "/shaders";
         String text75 = "assets/augustus/shaders/space2.frag";
         this.applyText(text75, text74, false);
         String text76 = text + "/shaders";
         String text77 = "assets/augustus/shaders/star.frag";
         this.applyText(text77, text76, false);
         String text78 = text + "/shaders";
         String text79 = "assets/augustus/shaders/theshy.frag";
         this.applyText(text79, text78, false);
      }

      String text80 = text + "/shaders";
      String text81 = "assets/augustus/shaders/bloodmaxiter.frag";
      this.applyText(text81, text80, false);
      String text82 = text + "/shaders";
      String text83 = "assets/augustus/shaders/bluefade.frag";
      this.applyText(text83, text82, false);
      String text84 = text + "/shaders";
      String text85 = "assets/augustus/shaders/bluefade2.frag";
      this.applyText(text85, text84, false);
      String text86 = text + "/shaders";
      String text87 = "assets/augustus/shaders/cryptonic_1_12.frag";
      this.applyText(text87, text86, false);
      String text88 = text + "/shaders";
      String text89 = "assets/augustus/shaders/deadtome.frag";
      this.applyText(text89, text88, false);
      String text90 = text + "/shaders";
      String text91 = "assets/augustus/shaders/debug.frag";
      this.applyText(text91, text90, false);
      String text92 = text + "/shaders";
      String text93 = "assets/augustus/shaders/eject.frag";
      this.applyText(text93, text92, false);
      String text94 = text + "/shaders";
      String text95 = "assets/augustus/shaders/ejectlila.frag";
      this.applyText(text95, text94, false);
      String text96 = text + "/shaders";
      String text97 = "assets/augustus/shaders/fixed_rednight.frag";
      this.applyText(text97, text96, false);
      String text98 = text + "/shaders";
      String text99 = "assets/augustus/shaders/full_polarlights.frag";
      this.applyText(text99, text98, false);
      String text100 = text + "/shaders";
      String text101 = "assets/augustus/shaders/ghosttravel.frag";
      this.applyText(text101, text100, false);
      String text102 = text + "/shaders";
      String text103 = "assets/augustus/shaders/lbday.frag";
      this.applyText(text103, text102, false);
      String text104 = text + "/shaders";
      String text105 = "assets/augustus/shaders/lennidark.frag";
      this.applyText(text105, text104, false);
      String text106 = text + "/shaders";
      String text107 = "assets/augustus/shaders/master.frag";
      this.applyText(text107, text106, false);
      String text108 = text + "/shaders";
      String text109 = "assets/augustus/shaders/maxiter.frag";
      this.applyText(text109, text108, false);
      String text110 = text + "/shaders";
      String text111 = "assets/augustus/shaders/neonsunrise.frag";
      this.applyText(text111, text110, false);
      String text112 = text + "/shaders";
      String text113 = "assets/augustus/shaders/panorama.frag";
      this.applyText(text113, text112, false);
      String text114 = text + "/shaders";
      String text115 = "assets/augustus/shaders/pixelgradient.frag";
      this.applyText(text115, text114, false);
      String text116 = text + "/shaders";
      String text117 = "assets/augustus/shaders/prestige.frag";
      this.applyText(text117, text116, false);
      String text118 = text + "/shaders";
      String text119 = "assets/augustus/shaders/rednight.frag";
      this.applyText(text119, text118, false);
      String text120 = text + "/shaders";
      String text121 = "assets/augustus/shaders/rednightnew.frag";
      this.applyText(text121, text120, false);
      String text122 = text + "/shaders";
      String text123 = "assets/augustus/shaders/retrosuntravel.frag";
      this.applyText(text123, text122, false);
      String text124 = text + "/shaders";
      String text125 = "assets/augustus/shaders/rickandmortylike.frag";
      this.applyText(text125, text124, false);
      String text126 = text + "/shaders";
      String text127 = "assets/augustus/shaders/rings.frag";
      this.applyText(text127, text126, false);
      String text128 = text + "/shaders";
      String text129 = "assets/augustus/shaders/rkblue.frag";
      this.applyText(text129, text128, false);
      String text130 = text + "/shaders";
      String text131 = "assets/augustus/shaders/rknight.frag";
      this.applyText(text131, text130, false);
      String text132 = text + "/shaders";
      String text133 = "assets/augustus/shaders/sandstorm.frag";
      this.applyText(text133, text132, false);
      String text134 = text + "/shaders";
      String text135 = "assets/augustus/shaders/snowtravel.frag";
      this.applyText(text135, text134, false);
      String text136 = text + "/shaders";
      String text137 = "assets/augustus/shaders/startrip.frag";
      this.applyText(text137, text136, false);
      String text138 = text + "/shaders";
      String text139 = "assets/augustus/shaders/tunnel.frag";
      this.applyText(text139, text138, false);
      String text140 = text + "/shaders";
      String text141 = "assets/augustus/shaders/unicode.frag";
      this.applyText(text141, text140, false);
      String text142 = text + "/shaders";
      String text143 = "assets/augustus/shaders/dynamic4.frag";
      this.applyText(text143, text142, false);
      String text144 = text + "/shaders";
      String text145 = "assets/augustus/shaders/Deutschland.frag";
      this.applyText(text145, text144, false);
      String text146 = text + "/shaders";
      String text147 = "assets/augustus/shaders/matrix.frag";
      this.applyText(text147, text146, false);
      String text148 = text + "/settings";
      String text149 = "assets/augustus/ip_changer_fritzbox.vbs";
      this.applyText(text149, text148, false);
      String text150 = text + "/settings";
      String text151 = "assets/augustus/reconnect.sh";
      this.applyText(text151, text150, false);
      this.list = new java.util.ArrayList();
      File[] fileArray = new File(OnlineConfigsManager.text + "/shaders").listFiles();
      if (fileArray != null) {
         for (File file : fileArray) {
            if (file.getName().contains(".")) {
               String[] parts = file.getName().split("\\.");
               if (parts.length > 1 && parts[1].equals("frag")) {
                  ModularStringEntry shaderEntry = new ModularStringEntry();
                  char[] chars = parts[0].toCharArray();
                  chars[0] = Character.toUpperCase(chars[0]);
                  String displayName = new String(chars);
                  shaderEntry.applyText(file.getAbsolutePath(), displayName);
                  this.hashMap.put(parts[0].toLowerCase(Locale.ROOT), shaderEntry);
                  this.list.add(displayName);
               }
            }
         }
      }

      this.modularStringEntry = (ModularStringEntry)this.hashMap.get("bluefog");
   }
}
