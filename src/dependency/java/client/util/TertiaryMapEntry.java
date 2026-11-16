package client.util;

import client.gui.DropdownMenuWidget;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.mojang.authlib.minecraft.MinecraftProfileTexture.Type;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.io.IOUtils;

public class TertiaryMapEntry {
   public static final Map map2 = new HashMap();
   public static final Map map = new HashMap();
   public static final Map map3 = new HashMap();

   public static ResourceLocation get(String text) {
      ResourceLocation location = (ResourceLocation)map3.get(text);
      if (location != null) {
         return location;
      } else {
         try {
            HttpURLConnection httpURLConnection = (HttpURLConnection)new URL("https://api.mojang.com/users/profiles/minecraft/" + text + "").openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(20000);
            httpURLConnection.connect();
            BufferedReader reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            StringBuilder builder = new StringBuilder();

            String text2;
            while ((text2 = reader.readLine()) != null) {
               builder.append(text2);
            }

            IOUtils.closeQuietly((Reader)reader);
            String[] textArray = builder.toString().split("\"");
            if (textArray.length >= 8) {
               int count = 1;

               for (String text3 : textArray) {
                  count++;
                  if (text3.equals("id")) {
                     break;
                  }
               }

               HttpURLConnection httpURLConnection2 = (HttpURLConnection)new URL("https://sessionserver.mojang.com/session/minecraft/profile/" + textArray[count] + "")
                  .openConnection();
               httpURLConnection2.setRequestMethod("GET");
               httpURLConnection2.setConnectTimeout(20000);
               httpURLConnection2.connect();
               BufferedReader reader2 = new BufferedReader(new InputStreamReader(httpURLConnection2.getInputStream()));
               StringBuilder builder2 = new StringBuilder();

               String text4;
               while ((text4 = reader2.readLine()) != null) {
                  builder2.append(text4);
               }

               httpURLConnection2.disconnect();
               IOUtils.closeQuietly((Reader)reader2);
               String[] textArray2 = builder2.toString().split("\"");
               if (textArray2.length >= 18) {
                  byte[] byteValueArray = Base64.getDecoder().decode(textArray2[17]);
                  String[] textArray3 = new String(byteValueArray).split("\"");
                  if (textArray3.length >= 18) {
                     String text5 = textArray3[17];
                     ResourceLocation location2 = Minecraft.getMinecraft()
                        .getSkinsManager()
                        .resolveResourceLocationByMinecraftProfile(new MinecraftProfileTexture(text5, null), Type.SKIN);
                     map3.put(text, location2);
                     return location2;
                  }
               }
            }
         } catch (Exception exception) {
         }

         map3.put(text, null);
         return null;
      }
   }

   public static ResourceLocation get(String text, int count) {
      ResourceLocation location = (ResourceLocation)map.get(text);
      if (location != null) {
         return location;
      } else {
         String text2 = "https://minotar.net/avatar/" + text + "/" + count + ".png";
         ResourceLocation location2 = new ResourceLocation("skins/" + text);
         LoggerEntryHandler loggerEntryHandler = new LoggerEntryHandler(null, text2, null, null);
         Minecraft.getMinecraft().getTextureManager().matchesResourceLocation(location2, loggerEntryHandler);
         map.put(text, location2);
         AbstractClientPlayer.resolveLoggerEntryHandler(location2, text);
         return location2;
      }
   }

   public static void updateState() {

      for (AccountProfile accountProfile : (Iterable<AccountProfile>)(Iterable)(DropdownMenuWidget.copyOnWriteArrayList)) {
         ScaledResolution scaledResolution = new ScaledResolution(Minecraft.getMinecraft());

         try {
            int count = accountProfile.getCount3() * scaledResolution.getScaleFactor();
            get(accountProfile.getLabelText(), count);
         } catch (Exception exception) {
            System.err.println("Failed to load Skins!");
         }
      }
   }

   public static ResourceLocation getByText(String text) {
      if (map2.containsKey(text)) {
         return (ResourceLocation)map2.get(text);
      } else {
         try {
            HttpURLConnection httpURLConnection = (HttpURLConnection)new URL("https://api.mojang.com/users/profiles/minecraft/" + text + "").openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(20000);
            httpURLConnection.connect();
            BufferedReader reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            StringBuilder builder = new StringBuilder();

            String text2;
            while ((text2 = reader.readLine()) != null) {
               builder.append(text2);
            }

            httpURLConnection.disconnect();
            IOUtils.closeQuietly((Reader)reader);
            String[] textArray = builder.toString().split("\"");
            int count = 1;

            for (String text3 : textArray) {
               count++;
               if (text3.equals("id")) {
                  break;
               }
            }

            if (textArray.length >= 8) {
               HttpURLConnection httpURLConnection2 = (HttpURLConnection)new URL("https://sessionserver.mojang.com/session/minecraft/profile/" + textArray[count] + "")
                  .openConnection();
               httpURLConnection2.setRequestMethod("GET");
               httpURLConnection2.setConnectTimeout(20000);
               httpURLConnection2.connect();
               BufferedReader reader2 = new BufferedReader(new InputStreamReader(httpURLConnection2.getInputStream()));
               StringBuilder builder2 = new StringBuilder();

               String text4;
               while ((text4 = reader2.readLine()) != null) {
                  builder2.append(text4);
               }

               httpURLConnection2.disconnect();
               IOUtils.closeQuietly((Reader)reader2);
               String[] textArray2 = builder2.toString().split("\"");
               if (textArray2.length >= 18) {
                  byte[] byteValueArray = Base64.getDecoder().decode(textArray2[17]);
                  String[] textArray3 = new String(byteValueArray).split("\"");
                  if (textArray3.length >= 24) {
                     String text5 = textArray3[23];
                     ResourceLocation location = Minecraft.getMinecraft()
                        .getSkinsManager()
                        .resolveResourceLocationByMinecraftProfile(new MinecraftProfileTexture(text5, null), Type.CAPE);
                     map2.put(text, location);
                     return location;
                  }
               }
            }
         } catch (Exception exception) {
         }

         map2.put(text, null);
         return null;
      }
   }
}
