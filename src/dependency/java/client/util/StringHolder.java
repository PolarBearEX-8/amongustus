package client.util;

import client.setting.CombatOptionProperty;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import net.minecraft.client.Minecraft;
import org.apache.commons.io.IOUtils;

public class StringHolder {
   public static final String labelText2 = "assets/augustus/ChangeLog.txt";
   public static final String labelText = "\r\n";
   public final List list = new ArrayList();

   public void updateState() {
      String text = this.getText();
      if (text != null && !text.isEmpty()) {
         ArrayList list2 = new ArrayList();
         String text2 = "";

         for (String text3 : text.split("\r\n")) {
            if (text3.endsWith(":")) {
               text2 = text3.replace(":", "");
            }

            if (text3.startsWith("-")) {
               String text4 = text3.substring(1).split(" ")[0];
               CombatOptionProperty combatOptionProperty = CombatOptionProperty.ADDED;
               switch (text4) {
                  case "improved":
                     combatOptionProperty = CombatOptionProperty.IMPROVED;
                     break;
                  case "fixed":
                  case "moved":
                  case "changed":
                  case "reworked":
                     combatOptionProperty = CombatOptionProperty.FIXED;
                     break;
                  case "removed":
                     combatOptionProperty = CombatOptionProperty.REMOVED;
               }

               list2.add(new AdvancedStringArrayEntry(new String[]{text3}, combatOptionProperty));
            }

            if (text3.trim().isEmpty()) {
               this.list.add(new TertiaryListEntry(text2, new ArrayList(list2)));
               text2 = "";
               list2.clear();
            }
         }
      } else {
         throw new CanTReadChangelogFileRuntimeException("empty");
      }
   }

   private String getText() {
      InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("assets/augustus/ChangeLog.txt");

      try {
         return IOUtils.toString(Objects.requireNonNull(resourceAsStream), StandardCharsets.UTF_8);
      } catch (IOException ioException) {
         throw new CanTReadChangelogFileRuntimeException(ioException.getMessage());
      }
   }

   public List resolveList(double offset) {
      ArrayList list2 = new ArrayList(this.list);
      Iterator iterator = list2.iterator();

      while (iterator.hasNext()) {
         for (AdvancedStringArrayEntry advancedStringArrayEntry : (Iterable<AdvancedStringArrayEntry>)(Iterable)(((TertiaryListEntry)iterator.next()).getList())) {
            String text = advancedStringArrayEntry.getStringArray()[0];
            if ((double)Minecraft.getMinecraft().fontRendererObj.getIndex(text) > offset) {
               StringBuilder builder = new StringBuilder();
               String[] textArray = text.split(" ");
               ArrayList list3 = new ArrayList();

               for (Object element : textArray) {
                  if ((double)Minecraft.getMinecraft().fontRendererObj.getIndex(builder.toString() + element + " ") > offset) {
                     list3.add(builder.toString());
                     builder = new StringBuilder();
                  }

                  builder.append((String)element);
                  builder.append(" ");
               }

               list3.add(builder.toString());
               String[] textArray2 = (String[])list3.toArray(new String[0]);
               advancedStringArrayEntry.setStringArray(textArray2);
            }
         }
      }

      return list2;
   }
}
