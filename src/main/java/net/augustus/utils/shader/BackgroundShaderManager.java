package net.augustus.utils.shader;

import net.augustus.Augustus;
import net.augustus.config.ClientSettingsConfigFile;
import net.augustus.render.GpuVendorDetector;
import net.augustus.settings.property.StringProperty;
import net.augustus.utils.file.ClientFileUtil;
import net.augustus.utils.file.ClientPaths;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.stream.Collectors;
import net.minecraft.block.state.pattern.BlockStateHelper;
import org.apache.commons.io.FileUtils;

public class BackgroundShaderManager {
   private final StringProperty selectedShader;
   private final HashMap shadersByName;
   private final List sortedShaders;
   private BackgroundShader currentShader;

   public BackgroundShader getCurrentShader() {
      return this.currentShader;
   }

   private void extractResourceFile(String str, String str2, boolean flag) {
      if (str != null && !str.equals("")) {
         str = str.replace('\\', '/');
         InputStream resourceAsStream = BlockStateHelper.class.getClassLoader().getResourceAsStream(str);
         if (resourceAsStream == null) {
            throw new IllegalArgumentException("The embedded resource '" + str + "' cannot be found in client");
         } else {
            File file = new File(str);
            File file2 = new File(ClientFileUtil.WORKING_DIR + str2 + "/" + file.getName());
            if (!file2.getParentFile().exists()) {
               try {
                  Files.createDirectories(file2.getParentFile().toPath());
               } catch (IOException e) {
                  e.printStackTrace();
               }
            }

            try {
               if (!file2.exists() || flag) {
                  FileUtils.copyInputStreamToFile(resourceAsStream, file2);
                  resourceAsStream.close();
               }
            } catch (IOException e2) {
               System.err.println(Level.SEVERE + "Could not save " + file2.getName() + " to " + file2.getAbsolutePath());
            }
         }
      } else {
         throw new IllegalArgumentException("ResourcePath cannot be null or empty");
      }
   }

   public List getSortedShaders() {
      return this.sortedShaders;
   }

   public String getCurrentShaderName() {
      return this.currentShader.getName().toLowerCase(Locale.ROOT);
   }

   public void selectShader(String str) {
      BackgroundShader backgroundShader = (BackgroundShader)this.shadersByName.get(str.toLowerCase(Locale.ROOT));
      if (backgroundShader != null) {
         this.currentShader = backgroundShader;
      }
   }

   private boolean supportsExtendedShaders() {
      return GpuVendorDetector.isNvidia();
   }

   public BackgroundShaderManager() {
       super();
      this.shadersByName = new HashMap();
      this.selectedShader = (StringProperty)((StringProperty)new StringProperty(ClientSettingsConfigFile.abstractFeature, "SelectedShader", "").withOnValueChanged(obj -> this.selectShader((String)obj))).withValueSupplier(() -> this.getCurrentShaderName());
      String str = Augustus.getInstance().getName().toLowerCase(Locale.ROOT);
      String str2 = str + "/shaders";
      String str3 = str2;
      this.extractResourceFile("assets/augustus/shaders/abraxas.frag", str3, false);
      str2 = str + "/shaders";
      String str5 = str2;
      this.extractResourceFile("assets/augustus/shaders/galaxy.frag", str5, false);
      str2 = str + "/shaders";
      String str7 = str2;
      this.extractResourceFile("assets/augustus/shaders/galaxy2.frag", str7, false);
      str2 = str + "/shaders";
      String str9 = str2;
      this.extractResourceFile("assets/augustus/shaders/purplewater.frag", str9, false);
      String str11 = str + "/shaders";
      String str12 = str11;
      this.extractResourceFile("assets/augustus/shaders/liquidbounce.frag", str12, false);
      str11 = str + "/shaders";
      String str14 = str11;
      this.extractResourceFile("assets/augustus/shaders/redwaterlike.frag", str14, false);
      str11 = str + "/shaders";
      String str16 = str11;
      this.extractResourceFile("assets/augustus/shaders/sexy.frag", str16, false);
      str11 = str + "/shaders";
      String str18 = str11;
      this.extractResourceFile("assets/augustus/shaders/metal.frag", str18, false);
      str11 = str + "/shaders";
      String str20 = str11;
      this.extractResourceFile("assets/augustus/shaders/snowstar.frag", str20, false);
      str11 = str + "/shaders";
      String str22 = str11;
      this.extractResourceFile("assets/augustus/shaders/spider.frag", str22, false);
      str11 = str + "/shaders";
      String str24 = str11;
      this.extractResourceFile("assets/augustus/shaders/trinity.frag", str24, false);
      str11 = str + "/shaders";
      String str26 = str11;
      this.extractResourceFile("assets/augustus/shaders/bluefog.frag", str26, false);
      if (this.supportsExtendedShaders()) {
         str11 = str + "/shaders";
         String str28 = str11;
         this.extractResourceFile("assets/augustus/shaders/cloudtunnel.frag", str28, false);
      }

      str11 = str + "/shaders";
      String str30 = str11;
      this.extractResourceFile("assets/augustus/shaders/geometry.frag", str30, false);
      str11 = str + "/shaders";
      String str32 = str11;
      this.extractResourceFile("assets/augustus/shaders/ukraine.frag", str32, false);
      str11 = str + "/shaders";
      String str34 = str11;
      this.extractResourceFile("assets/augustus/shaders/town.frag", str34, false);
      if (this.supportsExtendedShaders()) {
         str11 = str + "/shaders";
         String str36 = str11;
         this.extractResourceFile("assets/augustus/shaders/labyrinth.frag", str36, false);
      }

      str11 = str + "/shaders";
      String str38 = str11;
      this.extractResourceFile("assets/augustus/shaders/minecraft.frag", str38, false);
      str11 = str + "/shaders";
      String str40 = str11;
      this.extractResourceFile("assets/augustus/shaders/starfog.frag", str40, false);
      str11 = str + "/shaders";
      String str42 = str11;
      this.extractResourceFile("assets/augustus/shaders/polarlights.frag", str42, false);
      if (this.supportsExtendedShaders()) {
         str11 = str + "/shaders";
         String str44 = str11;
         this.extractResourceFile("assets/augustus/shaders/squeezysound.frag", str44, false);
      }

      str11 = str + "/shaders";
      String str46 = str11;
      this.extractResourceFile("assets/augustus/shaders/burningsun.frag", str46, false);
      if (this.supportsExtendedShaders()) {
         str11 = str + "/shaders";
         String str48 = str11;
         this.extractResourceFile("assets/augustus/shaders/mandelbrot.frag", str48, false);
      }

      str11 = str + "/shaders";
      String str50 = str11;
      this.extractResourceFile("assets/augustus/shaders/Augustus1.frag", str50, false);
      if (this.supportsExtendedShaders()) {
         str11 = str + "/shaders";
         String str52 = str11;
         this.extractResourceFile("assets/augustus/shaders/Augustus2.frag", str52, false);
      }

      str11 = str + "/shaders";
      String str54 = str11;
      this.extractResourceFile("assets/augustus/shaders/Clean.frag", str54, false);
      str11 = str + "/shaders";
      String str56 = str11;
      this.extractResourceFile("assets/augustus/shaders/Dynamic.frag", str56, false);
      str11 = str + "/shaders";
      String str58 = str11;
      this.extractResourceFile("assets/augustus/shaders/rise1.frag", str58, false);
      str11 = str + "/shaders";
      String str60 = str11;
      this.extractResourceFile("assets/augustus/shaders/water.frag", str60, false);
      str11 = str + "/shaders";
      String str62 = str11;
      this.extractResourceFile("assets/augustus/shaders/fade.frag", str62, false);
      str11 = str + "/shaders";
      String str64 = str11;
      this.extractResourceFile("assets/augustus/shaders/rise2.frag", str64, false);
      str11 = str + "/shaders";
      String str66 = str11;
      this.extractResourceFile("assets/augustus/shaders/rise3.frag", str66, false);
      str11 = str + "/shaders";
      String str68 = str11;
      this.extractResourceFile("assets/augustus/shaders/purple.frag", str68, false);
      str11 = str + "/shaders";
      String str70 = str11;
      this.extractResourceFile("assets/augustus/shaders/purplefade.frag", str70, false);
      str11 = str + "/shaders";
      String str72 = str11;
      this.extractResourceFile("assets/augustus/shaders/circle.frag", str72, false);
      str11 = str + "/shaders";
      String str74 = str11;
      this.extractResourceFile("assets/augustus/shaders/clean2.frag", str74, false);
      str11 = str + "/shaders";
      String str76 = str11;
      this.extractResourceFile("assets/augustus/shaders/space3.frag", str76, false);
      str11 = str + "/shaders";
      String str78 = str11;
      this.extractResourceFile("assets/augustus/shaders/dynamic2.frag", str78, false);
      str11 = str + "/shaders";
      String str80 = str11;
      this.extractResourceFile("assets/augustus/shaders/dynamic3.frag", str80, false);
      str11 = str + "/shaders";
      String str82 = str11;
      this.extractResourceFile("assets/augustus/shaders/cube2.frag", str82, false);
      str11 = str + "/shaders";
      String str84 = str11;
      this.extractResourceFile("assets/augustus/shaders/core2.frag", str84, false);
      str11 = str + "/shaders";
      String str86 = str11;
      this.extractResourceFile("assets/augustus/shaders/retrowave.frag", str86, false);
      str11 = str + "/shaders";
      String str88 = str11;
      this.extractResourceFile("assets/augustus/shaders/donut.frag", str88, false);
      str11 = str + "/shaders";
      String str90 = str11;
      this.extractResourceFile("assets/augustus/shaders/cube.frag", str90, false);
      str11 = str + "/shaders";
      String str92 = str11;
      this.extractResourceFile("assets/augustus/shaders/bubbles.frag", str92, false);
      str11 = str + "/shaders";
      String str94 = str11;
      this.extractResourceFile("assets/augustus/shaders/core.frag", str94, false);
      str11 = str + "/shaders";
      String str96 = str11;
      this.extractResourceFile("assets/augustus/shaders/heart.frag", str96, false);
      str11 = str + "/shaders";
      String str98 = str11;
      this.extractResourceFile("assets/augustus/shaders/river.frag", str98, false);
      str11 = str + "/shaders";
      String str100 = str11;
      this.extractResourceFile("assets/augustus/shaders/space.frag", str100, false);
      str11 = str + "/shaders";
      String str102 = str11;
      this.extractResourceFile("assets/augustus/shaders/BasicStarfield2.frag", str102, false);
      str11 = str + "/shaders";
      String str104 = str11;
      this.extractResourceFile("assets/augustus/shaders/LilaFrag.frag", str104, false);
      str11 = str + "/shaders";
      String str106 = str11;
      this.extractResourceFile("assets/augustus/shaders/NShader1.frag", str106, false);
      str11 = str + "/shaders";
      String str108 = str11;
      this.extractResourceFile("assets/augustus/shaders/NShader2.frag", str108, false);
      str11 = str + "/shaders";
      String str110 = str11;
      this.extractResourceFile("assets/augustus/shaders/RandomGalaxy2.frag", str110, false);
      str11 = str + "/shaders";
      String str112 = str11;
      this.extractResourceFile("assets/augustus/shaders/RandomGalaxy3.frag", str112, false);
      str11 = str + "/shaders";
      String str114 = str11;
      this.extractResourceFile("assets/augustus/shaders/RedOrangeDiashow.frag", str114, false);
      str11 = str + "/shaders";
      String str116 = str11;
      this.extractResourceFile("assets/augustus/shaders/TheEvolution.frag", str116, false);
      str11 = str + "/shaders";
      String str118 = str11;
      this.extractResourceFile("assets/augustus/shaders/auroras.frag", str118, false);
      str11 = str + "/shaders";
      String str120 = str11;
      this.extractResourceFile("assets/augustus/shaders/bash.frag", str120, false);
      if (this.supportsExtendedShaders()) {
         str11 = str + "/shaders";
         String str122 = str11;
         this.extractResourceFile("assets/augustus/shaders/bash117.frag", str122, false);
         str11 = str + "/shaders";
         String str124 = str11;
         this.extractResourceFile("assets/augustus/shaders/blackhole.frag", str124, false);
         str11 = str + "/shaders";
         String str126 = str11;
         this.extractResourceFile("assets/augustus/shaders/BlueArt.frag", str126, false);
         str11 = str + "/shaders";
         String str128 = str11;
         this.extractResourceFile("assets/augustus/shaders/core3.frag", str128, false);
         str11 = str + "/shaders";
         String str130 = str11;
         this.extractResourceFile("assets/augustus/shaders/core4.frag", str130, false);
         str11 = str + "/shaders";
         String str132 = str11;
         this.extractResourceFile("assets/augustus/shaders/Cubictrip.frag", str132, false);
         str11 = str + "/shaders";
         String str134 = str11;
         this.extractResourceFile("assets/augustus/shaders/dvdlogo.frag", str134, false);
         str11 = str + "/shaders";
         String str136 = str11;
         this.extractResourceFile("assets/augustus/shaders/dynamic5.frag", str136, false);
         str11 = str + "/shaders";
         String str138 = str11;
         this.extractResourceFile("assets/augustus/shaders/FavCurrent.frag", str138, false);
         str11 = str + "/shaders";
         String str140 = str11;
         this.extractResourceFile("assets/augustus/shaders/fireworks.frag", str140, false);
         str11 = str + "/shaders";
         String str142 = str11;
         this.extractResourceFile("assets/augustus/shaders/foggy.frag", str142, false);
         str11 = str + "/shaders";
         String str144 = str11;
         this.extractResourceFile("assets/augustus/shaders/foggy2.frag", str144, false);
         str11 = str + "/shaders";
         String str146 = str11;
         this.extractResourceFile("assets/augustus/shaders/fractal.frag", str146, false);
         str11 = str + "/shaders";
         String str148 = str11;
         this.extractResourceFile("assets/augustus/shaders/galaxystorm.frag", str148, false);
         str11 = str + "/shaders";
         String str150 = str11;
         this.extractResourceFile("assets/augustus/shaders/idk1.frag", str150, false);
         str11 = str + "/shaders";
         String str152 = str11;
         this.extractResourceFile("assets/augustus/shaders/LenniZwei.frag", str152, false);
         str11 = str + "/shaders";
         String str154 = str11;
         this.extractResourceFile("assets/augustus/shaders/pulse.frag", str154, false);
         str11 = str + "/shaders";
         String str156 = str11;
         this.extractResourceFile("assets/augustus/shaders/random_galaxy.frag", str156, false);
         str11 = str + "/shaders";
         String str158 = str11;
         this.extractResourceFile("assets/augustus/shaders/randomboxes.frag", str158, false);
         str11 = str + "/shaders";
         String str160 = str11;
         this.extractResourceFile("assets/augustus/shaders/retrostarnight.frag", str160, false);
         str11 = str + "/shaders";
         String str162 = str11;
         this.extractResourceFile("assets/augustus/shaders/ricky.frag", str162, false);
         str11 = str + "/shaders";
         String str164 = str11;
         this.extractResourceFile("assets/augustus/shaders/rkfadeside.frag", str164, false);
         str11 = str + "/shaders";
         String str166 = str11;
         this.extractResourceFile("assets/augustus/shaders/singularity.frag", str166, false);
         str11 = str + "/shaders";
         String str168 = str11;
         this.extractResourceFile("assets/augustus/shaders/snowflakes.frag", str168, false);
         str11 = str + "/shaders";
         String str170 = str11;
         this.extractResourceFile("assets/augustus/shaders/space2.frag", str170, false);
         str11 = str + "/shaders";
         String str172 = str11;
         this.extractResourceFile("assets/augustus/shaders/star.frag", str172, false);
         str11 = str + "/shaders";
         String str174 = str11;
         this.extractResourceFile("assets/augustus/shaders/theshy.frag", str174, false);
      }

      str11 = str + "/shaders";
      String str176 = str11;
      this.extractResourceFile("assets/augustus/shaders/bloodmaxiter.frag", str176, false);
      str11 = str + "/shaders";
      String str178 = str11;
      this.extractResourceFile("assets/augustus/shaders/bluefade.frag", str178, false);
      str11 = str + "/shaders";
      String str180 = str11;
      this.extractResourceFile("assets/augustus/shaders/bluefade2.frag", str180, false);
      str11 = str + "/shaders";
      String str182 = str11;
      this.extractResourceFile("assets/augustus/shaders/cryptonic_1_12.frag", str182, false);
      str11 = str + "/shaders";
      String str184 = str11;
      this.extractResourceFile("assets/augustus/shaders/deadtome.frag", str184, false);
      str11 = str + "/shaders";
      String str186 = str11;
      this.extractResourceFile("assets/augustus/shaders/debug.frag", str186, false);
      str11 = str + "/shaders";
      String str188 = str11;
      this.extractResourceFile("assets/augustus/shaders/eject.frag", str188, false);
      str11 = str + "/shaders";
      String str190 = str11;
      this.extractResourceFile("assets/augustus/shaders/ejectlila.frag", str190, false);
      str11 = str + "/shaders";
      String str192 = str11;
      this.extractResourceFile("assets/augustus/shaders/fixed_rednight.frag", str192, false);
      str11 = str + "/shaders";
      String str194 = str11;
      this.extractResourceFile("assets/augustus/shaders/full_polarlights.frag", str194, false);
      str11 = str + "/shaders";
      String str196 = str11;
      this.extractResourceFile("assets/augustus/shaders/ghosttravel.frag", str196, false);
      str11 = str + "/shaders";
      String str198 = str11;
      this.extractResourceFile("assets/augustus/shaders/lbday.frag", str198, false);
      str11 = str + "/shaders";
      String str200 = str11;
      this.extractResourceFile("assets/augustus/shaders/lennidark.frag", str200, false);
      str11 = str + "/shaders";
      String str202 = str11;
      this.extractResourceFile("assets/augustus/shaders/master.frag", str202, false);
      str11 = str + "/shaders";
      String str204 = str11;
      this.extractResourceFile("assets/augustus/shaders/maxiter.frag", str204, false);
      str11 = str + "/shaders";
      String str206 = str11;
      this.extractResourceFile("assets/augustus/shaders/neonsunrise.frag", str206, false);
      str11 = str + "/shaders";
      String str208 = str11;
      this.extractResourceFile("assets/augustus/shaders/panorama.frag", str208, false);
      str11 = str + "/shaders";
      String str210 = str11;
      this.extractResourceFile("assets/augustus/shaders/pixelgradient.frag", str210, false);
      str11 = str + "/shaders";
      String str212 = str11;
      this.extractResourceFile("assets/augustus/shaders/prestige.frag", str212, false);
      str11 = str + "/shaders";
      String str214 = str11;
      this.extractResourceFile("assets/augustus/shaders/rednight.frag", str214, false);
      str11 = str + "/shaders";
      String str216 = str11;
      this.extractResourceFile("assets/augustus/shaders/rednightnew.frag", str216, false);
      str11 = str + "/shaders";
      String str218 = str11;
      this.extractResourceFile("assets/augustus/shaders/retrosuntravel.frag", str218, false);
      str11 = str + "/shaders";
      String str220 = str11;
      this.extractResourceFile("assets/augustus/shaders/rickandmortylike.frag", str220, false);
      str11 = str + "/shaders";
      String str222 = str11;
      this.extractResourceFile("assets/augustus/shaders/rings.frag", str222, false);
      str11 = str + "/shaders";
      String str224 = str11;
      this.extractResourceFile("assets/augustus/shaders/rkblue.frag", str224, false);
      str11 = str + "/shaders";
      String str226 = str11;
      this.extractResourceFile("assets/augustus/shaders/rknight.frag", str226, false);
      str11 = str + "/shaders";
      String str228 = str11;
      this.extractResourceFile("assets/augustus/shaders/sandstorm.frag", str228, false);
      str11 = str + "/shaders";
      String str230 = str11;
      this.extractResourceFile("assets/augustus/shaders/snowtravel.frag", str230, false);
      str11 = str + "/shaders";
      String str232 = str11;
      this.extractResourceFile("assets/augustus/shaders/startrip.frag", str232, false);
      str11 = str + "/shaders";
      String str234 = str11;
      this.extractResourceFile("assets/augustus/shaders/tunnel.frag", str234, false);
      str11 = str + "/shaders";
      String str236 = str11;
      this.extractResourceFile("assets/augustus/shaders/unicode.frag", str236, false);
      str11 = str + "/shaders";
      String str238 = str11;
      this.extractResourceFile("assets/augustus/shaders/dynamic4.frag", str238, false);
      str11 = str + "/shaders";
      String str240 = str11;
      this.extractResourceFile("assets/augustus/shaders/Deutschland.frag", str240, false);
      str11 = str + "/shaders";
      String str242 = str11;
      this.extractResourceFile("assets/augustus/shaders/matrix.frag", str242, false);
      str11 = str + "/settings";
      String str244 = str11;
      this.extractResourceFile("assets/augustus/ip_changer_fritzbox.vbs", str244, false);
      str11 = str + "/settings";
      String str246 = str11;
      this.extractResourceFile("assets/augustus/reconnect.sh", str246, false);

      for (File file2 : new File(ClientPaths.CLIENT_DIR + "/shaders").listFiles()) {
         if (file2.getName().contains(".")) {
            String[] parts = file2.getName().split("\\.");
            if (parts.length > 1 && parts[1].equals("frag")) {
               BackgroundShader backgroundShader = new BackgroundShader();
               char[] chars = parts[0].toCharArray();
               chars[0] = Character.toUpperCase(chars[0]);
               backgroundShader.loadFromFile(file2.getAbsolutePath(), new String(chars));
               this.shadersByName.put(parts[0].toLowerCase(Locale.ROOT), backgroundShader);
            }
         }
      }

      BackgroundShader backgroundShader2 = (BackgroundShader)this.shadersByName.get("bluefog");
      if (backgroundShader2 == null) {
         backgroundShader2 = (BackgroundShader)this.shadersByName.values().iterator().next();
      }

      this.currentShader = backgroundShader2;
      this.sortedShaders = (List)this.shadersByName.values().stream().sorted(Comparator.comparing(obj2 -> ((BackgroundShader)obj2).getName())).collect(Collectors.toList());
   }

}
