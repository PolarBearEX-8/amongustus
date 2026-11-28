package net.optifine.player;

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
import net.minecraft.util.ResourceLocation;
import net.optifine.config.Config;
import net.optifine.http.HttpPipeline;
import net.optifine.http.HttpUtils;
import net.optifine.util.Json;

public class PlayerConfigurationParser {
   private String player = null;
   public static final String CONFIG_ITEMS = null;
   public static final String ITEM_TYPE = null;
   public static final String ITEM_ACTIVE = null;

   private PlayerItemModel downloadModel(String str) {
      String str2 = HttpUtils.getPlayerItemsUrl() + "/" + str;

      try {
         byte[] bytes = HttpPipeline.get(str2, Minecraft.getMinecraft().getProxy());
         String str3 = new String(bytes, "ASCII");
         return PlayerItemParser.parseItemModel((JsonObject)new JsonParser().parse(str3));
      } catch (Exception e) {
         Config.warn("Error loading item model " + str + ": " + e.getClass().getName() + ": " + e.getMessage());
         return null;
      }
   }

   public PlayerConfiguration parsePlayerConfiguration(JsonElement elem) {
      if (elem == null) {
         throw new JsonParseException("JSON object is null, player: " + this.player);
      } else {
         JsonObject json = (JsonObject)elem;
         PlayerConfiguration playerConfiguration = new PlayerConfiguration();
         JsonArray array = (JsonArray)json.get("items");
         if (array != null) {
            for (int i = 0; i < array.size(); i++) {
               JsonObject json2 = (JsonObject)array.get(i);
               if (Json.getBoolean(json2, "active", true)) {
                  String string = Json.getString(json2, "type");
                  if (string == null) {
                     Config.warn("Item type is null, player: " + this.player);
                  } else {
                     String string2 = Json.getString(json2, "model");
                     if (string2 == null) {
                        string2 = "items/" + string + "/model.cfg";
                     }

                     PlayerItemModel playerItemModel = this.downloadModel(string2);
                     if (playerItemModel != null) {
                        if (!playerItemModel.isUsePlayerTexture()) {
                           String string3 = Json.getString(json2, "texture");
                           if (string3 == null) {
                              string3 = "items/" + string + "/users/" + this.player + ".png";
                           }

                           BufferedImage image = this.downloadTextureImage(string3);
                           if (image == null) {
                              continue;
                           }

                           playerItemModel.setTextureImage(image);
                           playerItemModel.setTextureLocation(new ResourceLocation("optifine.net", string3));
                        }

                        playerConfiguration.addPlayerItemModel(playerItemModel);
                     }
                  }
               }
            }
         }

         return playerConfiguration;
      }
   }

   public PlayerConfigurationParser(String str) {
      this.player = str;
   }

   private BufferedImage downloadTextureImage(String str) {
      String str2 = HttpUtils.getPlayerItemsUrl() + "/" + str;

      try {
         byte[] bytes = HttpPipeline.get(str2, Minecraft.getMinecraft().getProxy());
         return ImageIO.read(new ByteArrayInputStream(bytes));
      } catch (IOException e) {
         Config.warn("Error loading item texture " + str + ": " + e.getClass().getName() + ": " + e.getMessage());
         return null;
      }
   }
}
