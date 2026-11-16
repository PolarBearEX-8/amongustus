package client.util;

import client.Client;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.io.IOUtils;

public class TertiaryHashMapEntry implements MinecraftAccessor {
   public int count2;
   public final HashMap hashMap;
   public final ArrayList arrayList = new ArrayList();
   public int count;
   public final HashMap hashMap2;
   public final CopyOnWriteArrayList copyOnWriteArrayList;
   public String[] stringArray;
   public ResourceLocation resourceLocation = null;

   public void updateState() {
      this.arrayList.add(new ResourceLocation("augustus", "protector/skin1.png"));
      this.arrayList.add(new ResourceLocation("augustus", "protector/skin2.png"));
      this.arrayList.add(new ResourceLocation("augustus", "protector/skin3.png"));
      this.arrayList.add(new ResourceLocation("augustus", "protector/skin4.png"));
      this.arrayList.add(new ResourceLocation("augustus", "protector/skin5.png"));
      this.arrayList.add(new ResourceLocation("augustus", "protector/skin6.png"));
      this.resourceLocation = new ResourceLocation("augustus", "protector/esound.png");
      String text = "assets/augustus/protector/names.txt";
      InputStream resourceAsStream = TertiaryHashMapEntry.class.getClassLoader().getResourceAsStream(text);
      if (resourceAsStream != null) {
         StringWriter stringWriter = new StringWriter();

         try {
            IOUtils.copy(resourceAsStream, stringWriter, "UTF-8");
         } catch (IOException ioException) {
            throw new RuntimeException(ioException);
         } finally {
            IOUtils.closeQuietly(resourceAsStream);
         }

         String text2 = stringWriter.toString();
         this.stringArray = text2.split(", ");
         List list = Arrays.asList(this.stringArray);
         Collections.shuffle(list);
         list.toArray(this.stringArray);
         return;
      }

      throw new IllegalArgumentException("The embedded resource '" + text + "' cannot be found in client");
   }

   public String resolveText(String text) {
      return text;
   }

   public ResourceLocation resolveResourceLocation(char character, int count3, int count4) {
      if (Client.getClient().getModuleManager().protector.settingNameGeneration.getSelected().equals("Esound") && this.resourceLocation != null) {
         return this.resourceLocation;
      }

      this.count++;
      if (this.count >= this.arrayList.size()) {
         this.count = 0;
      }

      return (ResourceLocation)this.arrayList.get(this.count);
   }

   public TertiaryHashMapEntry() {
      this.count = 0;
      this.count2 = 0;
      this.copyOnWriteArrayList = new CopyOnWriteArrayList();
      this.hashMap2 = new HashMap();
      this.hashMap = new HashMap();
   }
}
