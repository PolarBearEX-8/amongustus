package net.augustus.utils;

import net.augustus.Augustus;
import net.augustus.alt.AltUsernameGenerator;
import net.augustus.ui.account.SyllableUsernameGenerator;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;
import net.augustus.utils.interfaces.MC;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.io.IOUtils;

public class ProtectorManager implements MC {
   public final CopyOnWriteArrayList trackedNames;
   private String[] replacementNames;
   private ResourceLocation esoundSkin;
   public final Map nameMappings;
   private int nameIndex;
   private int skinIndex;
   private final ArrayList protectorSkins = new ArrayList();

   public void init() {
      this.protectorSkins.add(new ResourceLocation("augustus", "protector/skin1.png"));
      this.protectorSkins.add(new ResourceLocation("augustus", "protector/skin2.png"));
      this.protectorSkins.add(new ResourceLocation("augustus", "protector/skin3.png"));
      this.protectorSkins.add(new ResourceLocation("augustus", "protector/skin4.png"));
      this.protectorSkins.add(new ResourceLocation("augustus", "protector/skin5.png"));
      this.protectorSkins.add(new ResourceLocation("augustus", "protector/skin6.png"));
      this.esoundSkin = new ResourceLocation("augustus", "protector/esound.png");
      String str = "assets/augustus/protector/names.txt";
      InputStream resourceAsStream = ProtectorManager.class.getClassLoader().getResourceAsStream(str);
      if (resourceAsStream == null) {
         throw new IllegalArgumentException("The embedded resource '" + str + "' cannot be found in client");
      } else {
         StringWriter stringWriter = new StringWriter();

         try {
            IOUtils.copy(resourceAsStream, stringWriter, "UTF-8");
         } catch (IOException e) {
            throw new RuntimeException(e);
         } finally {
            IOUtils.closeQuietly(resourceAsStream);
         }

         this.replacementNames = stringWriter.toString().split(", ");
         List list = Arrays.asList(this.replacementNames);
         Collections.shuffle(list);
         list.toArray(this.replacementNames);
      }
   }

   public String getProtectedName(String str) {
      if (!TextFormatUtil.isCleanPlayerName(str)) {
         return str;
      } else if (this.trackedNames.contains(str)) {
         return (String)this.nameMappings.get(str);
      } else {
         this.trackedNames.add(str);

         while (this.trackedNames.size() >= 251) {
            String str2 = (String)this.trackedNames.get(0);
            this.trackedNames.remove(0);
            this.nameMappings.remove(str2);
         }

         this.nameIndex++;
         if (this.nameIndex >= this.replacementNames.length) {
            this.nameIndex = 0;
         }

         String selectedValue = Augustus.getInstance().getModuleManager().protector.nameGeneration.getSelectedValue();
         String str3;
         switch (selectedValue) {
            case "Generated":
               str3 = ThreadLocalRandom.current().nextBoolean() ? SyllableUsernameGenerator.generateUsername() : AltUsernameGenerator.generateUsername(16);
               break;
            case "Esound":
               str3 = "Esound";
               break;
            default:
               str3 = this.replacementNames[this.nameIndex];
         }

         this.nameMappings.put(str, str3);
         return str3;
      }
   }

   public ResourceLocation getNextProtectorSkin() {
      if (Augustus.getInstance().getModuleManager().protector.nameGeneration.getSelectedValue().equals("Esound") && this.esoundSkin != null) {
         return this.esoundSkin;
      } else {
         this.skinIndex++;
         if (this.skinIndex >= this.protectorSkins.size()) {
            this.skinIndex = 0;
         }

         return (ResourceLocation)this.protectorSkins.get(this.skinIndex);
      }
   }

   public ProtectorManager() {
      this.esoundSkin = null;
      this.skinIndex = 0;
      this.nameIndex = 0;
      this.trackedNames = new CopyOnWriteArrayList();
      this.nameMappings = new HashMap();
   }




}
