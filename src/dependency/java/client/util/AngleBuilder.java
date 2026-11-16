package client.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import net.minecraft.client.Minecraft;
import net.minecraft.item.SpecialItemArrayEntry;
import net.minecraft.util.ResourceLocation;

public class AngleBuilder {
   public String labelText = null;
   public static final String labelText2 = "items";
   public static final String labelText3 = "type";
   public static final String labelText4 = "active";

   public AngleBuilder(String text) {
      this.labelText = text;
   }

   public SpecialItemArrayEntry resolveSpecialItemArrayEntry(JsonElement jsonElement) {
      if (jsonElement == null) {
         throw new JsonParseException("JSON object is null, player: " + this.labelText);
      } else {
         JsonObject jsonObject = (JsonObject)jsonElement;
         SpecialItemArrayEntry specialItemArrayEntry = new SpecialItemArrayEntry();
         JsonArray jsonArray = (JsonArray)jsonObject.get("items");
         if (jsonArray != null) {
            for (int index = 0; index < jsonArray.size(); index++) {
               JsonObject jsonObject2 = (JsonObject)jsonArray.get(index);
               if (WrongArrayHelper.matchesJsonObject(jsonObject2, "active", true)) {
                  String text = WrongArrayHelper.resolveText(jsonObject2, "type");
                  if (text == null) {
                     BuildManager.applyText3("Item type is null, player: " + this.labelText);
                  } else {
                     String text2 = WrongArrayHelper.resolveText(jsonObject2, "model");
                     if (text2 == null) {
                        text2 = "items/" + text + "/model.cfg";
                     }

                     DimensionEntry dimensionEntry = this.resolveDimensionEntry(text2);
                     if (dimensionEntry != null) {
                        if (!dimensionEntry.isEnabled()) {
                           String text3 = WrongArrayHelper.resolveText(jsonObject2, "texture");
                           if (text3 == null) {
                              text3 = "items/" + text + "/users/" + this.labelText + ".png";
                           }

                           BufferedImage bufferedImage = this.resolveBufferedImage(text3);
                           if (bufferedImage == null) {
                              continue;
                           }

                           dimensionEntry.setBufferedImage(bufferedImage);
                           ResourceLocation location = new ResourceLocation("optifine.net", text3);
                           dimensionEntry.setResourceLocation2(location);
                        }

                        specialItemArrayEntry.applyDimensionEntry(dimensionEntry);
                     }
                  }
               }
            }
         }

         return specialItemArrayEntry;
      }
   }

   private BufferedImage resolveBufferedImage(String text) {
      String text2 = PlayerModelsLocalManager.getText() + "/" + text;

      try {
         byte[] byteValueArray = AuxiliaryResponseManager.resolveByteArray(text2, Minecraft.getMinecraft().getProxy());
         return ImageIO.read(new ByteArrayInputStream(byteValueArray));
      } catch (IOException ioException) {
         BuildManager.applyText3("Error loading item texture " + text + ": " + ioException.getClass().getName() + ": " + ioException.getMessage());
         return null;
      }
   }

   private DimensionEntry resolveDimensionEntry(String text) {
      String text2 = PlayerModelsLocalManager.getText() + "/" + text;

      try {
         byte[] byteValueArray = AuxiliaryResponseManager.resolveByteArray(text2, Minecraft.getMinecraft().getProxy());
         String text3 = new String(byteValueArray, "ASCII");
         return TypeModelBoxHelper.resolveDimensionEntry((JsonObject)new JsonParser().parse(text3));
      } catch (Exception exception) {
         BuildManager.applyText3("Error loading item model " + text + ": " + exception.getClass().getName() + ": " + exception.getMessage());
         return null;
      }
   }
}
