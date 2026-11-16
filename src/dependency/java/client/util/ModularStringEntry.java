package client.util;

import client.Client;
import client.render.AssetsRendersystemManager;
import client.setting.PassiveOptionType;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import org.apache.commons.io.IOUtils;
import org.lwjgl.input.Mouse;

public class ModularStringEntry implements MinecraftAccessor {
   public boolean enabled;
   public final String labelText2;
   public IntListEntry intListEntry;
   public String labelText;
   public long timestamp;

   public void currentTimeMillis(String text) {
      if (this.intListEntry == null) {
         this.intListEntry = new IntListEntry(this.labelText2, this.resolveText(text));
         this.timestamp = System.currentTimeMillis();
      }
   }

   public void setEnabled(boolean enabled2) {
      this.enabled = enabled2;
   }

   public String resolveText(String text) {
      try {
         return IOUtils.toString(Objects.requireNonNull(AssetsRendersystemManager.class.getResourceAsStream(text)));
      } catch (IOException ioException) {
         return null;
      }
   }

   public void applyText(String text, String text2) {
      if (this.intListEntry == null) {
         try {
            this.intListEntry = new IntListEntry(this.labelText2, this.resolveTextByText(text));
         } catch (RuntimeException exception) {
         }

         this.timestamp = System.currentTimeMillis();
      }

      this.labelText = text2;
   }

   public String resolveTextByText(String text) {

      try {
         StringBuilder builder = new StringBuilder();
         BufferedReader reader = new BufferedReader(new FileReader(text));

         String text2;
         while ((text2 = reader.readLine()) != null) {
            builder.append(text2).append('\n');
         }

         reader.close();
         return builder.toString();
      } catch (IOException ioException) {
         System.err.println("Fragment shader was not load properly");
         return null;
      }
   }

   public void applyFloat(float factor) {
      long value = 0x7fcca8587ac1L;
      if (this.intListEntry != null) {
         this.intListEntry.updateState2();
         float factor2 = (float)(System.currentTimeMillis() - this.timestamp) / factor;
         String text = "time";
         this.intListEntry.applyText(text, factor2);
         String text2 = "timeDelta";
         this.intListEntry.applyText(text2, factor2);
         if (Client.getClient().getModuleManager().clientSettings.settingMouseInputsOnBackgrounds.isEnabled()) {
            IntListEntry intListEntry2 = this.intListEntry;
            float factor3 = (float)(-Mouse.getY());
            float coordX = (float)Mouse.getX();
            String text3 = "mouse";
            intListEntry2.applyText(text3, coordX, factor3);
         }

         float displayWidth = (float)MinecraftAccessor.minecraft.displayWidth;
         float displayHeight = (float)MinecraftAccessor.minecraft.displayHeight;
         float factor4 = displayWidth;
         String text4 = "resolution";
         this.intListEntry.applyText(text4, factor4, displayHeight);
         boolean flag = false;
         PassiveOptionType.ALPHA_TEST.setEnabled(flag);
         displayWidth = (float)MinecraftAccessor.minecraft.displayWidth;
         float displayHeight2 = (float)MinecraftAccessor.minecraft.displayHeight;
         float factor5 = displayWidth;
         float factor6 = 0.0F;
         float factor7 = 0.0F;
         this.intListEntry.applyFloat(factor7, factor6, factor5, displayHeight2);
         PassiveOptionType.ALPHA_TEST.updateState();
         this.intListEntry.updateState();
      }
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   public String getLabelText() {
      return this.labelText;
   }

   public void setLabelText(String labelText3) {
      this.labelText = labelText3;
   }

   public ModularStringEntry() {
      String text = "/assets/augustus/shaders/shader.vert";
      this.labelText2 = this.resolveText(text);
   }
}
