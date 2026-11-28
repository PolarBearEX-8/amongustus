package net.minecraft.util;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.HoverEvent;

public class ChatStyle {
   private ChatStyle parentStyle;
   private EnumChatFormatting color;
   private Boolean bold;
   private Boolean italic;
   private Boolean underlined;
   private Boolean strikethrough;
   private Boolean obfuscated;
   private ClickEvent chatClickEvent;
   private HoverEvent chatHoverEvent;
   private String insertion;
   private static final ChatStyle rootStyle = new ChatStyle.DefaultChatStyle();

   public ChatStyle setUnderlined(Boolean bool) {
      this.underlined = bool;
      return this;
   }

   public boolean isEmpty() {
      return this.bold == null
         && this.italic == null
         && this.strikethrough == null
         && this.underlined == null
         && this.obfuscated == null
         && this.color == null
         && this.chatClickEvent == null
         && this.chatHoverEvent == null;
   }

   public static HoverEvent assignChatHoverEvent(ChatStyle chatStyle, HoverEvent hoverEvent) {
      return chatStyle.chatHoverEvent = hoverEvent;
   }

   public ChatStyle createDeepCopy() {
      ChatStyle chatStyle = new ChatStyle();
      chatStyle.setBold(this.getBold());
      chatStyle.setItalic(this.getItalic());
      chatStyle.setStrikethrough(this.getStrikethrough());
      chatStyle.setUnderlined(this.getUnderlined());
      chatStyle.setObfuscated(this.getObfuscated());
      chatStyle.setColor(this.getColor());
      chatStyle.setChatClickEvent(this.getChatClickEvent());
      chatStyle.setChatHoverEvent(this.getChatHoverEvent());
      chatStyle.setInsertion(this.getInsertion());
      return chatStyle;
   }

   public static EnumChatFormatting assignColor(ChatStyle chatStyle, EnumChatFormatting enumChatFormatting) {
      return chatStyle.color = enumChatFormatting;
   }

   @Override
   public int hashCode() {
      int n = this.color.hashCode();
      n = 31 * n + this.bold.hashCode();
      n = 31 * n + this.italic.hashCode();
      n = 31 * n + this.underlined.hashCode();
      n = 31 * n + this.strikethrough.hashCode();
      n = 31 * n + this.obfuscated.hashCode();
      n = 31 * n + this.chatClickEvent.hashCode();
      n = 31 * n + this.chatHoverEvent.hashCode();
      return 31 * n + this.insertion.hashCode();
   }

   public boolean getStrikethrough() {
      return this.strikethrough == null ? this.getParent().getStrikethrough() : this.strikethrough;
   }

   public static Boolean accessSetObfuscated(ChatStyle chatStyle, Boolean bool) {
      return chatStyle.obfuscated = bool;
   }

   public ChatStyle setItalic(Boolean bool) {
      this.italic = bool;
      return this;
   }

   public static Boolean accessSetItalic(ChatStyle chatStyle, Boolean bool) {
      return chatStyle.italic = bool;
   }

   public ChatStyle setChatHoverEvent(HoverEvent hoverEvent) {
      this.chatHoverEvent = hoverEvent;
      return this;
   }

   public static HoverEvent accessChatHoverEvent(ChatStyle chatStyle) {
      return chatStyle.chatHoverEvent;
   }

   public static String assignInsertion(ChatStyle chatStyle, String str) {
      return chatStyle.insertion = str;
   }

   public String getInsertion() {
      return this.insertion == null ? this.getParent().getInsertion() : this.insertion;
   }

   public static ClickEvent accessChatClickEvent(ChatStyle chatStyle) {
      return chatStyle.chatClickEvent;
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (!(obj instanceof ChatStyle)) {
         return false;
      } else {
         ChatStyle chatStyle = (ChatStyle)obj;
         return this.getBold() == chatStyle.getBold()
            && this.getColor() == chatStyle.getColor()
            && this.getItalic() == chatStyle.getItalic()
            && this.getObfuscated() == chatStyle.getObfuscated()
            && this.getStrikethrough() == chatStyle.getStrikethrough()
            && this.getUnderlined() == chatStyle.getUnderlined()
            && (this.getChatClickEvent() != null ? this.getChatClickEvent().equals(chatStyle.getChatClickEvent()) : chatStyle.getChatClickEvent() == null)
            && (this.getChatHoverEvent() != null ? this.getChatHoverEvent().equals(chatStyle.getChatHoverEvent()) : chatStyle.getChatHoverEvent() == null)
            && (this.getInsertion() != null ? this.getInsertion().equals(chatStyle.getInsertion()) : chatStyle.getInsertion() == null);
      }
   }

   public static EnumChatFormatting accessColor(ChatStyle chatStyle) {
      return chatStyle.color;
   }

   public ClickEvent getChatClickEvent() {
      return this.chatClickEvent == null ? this.getParent().getChatClickEvent() : this.chatClickEvent;
   }

   public ChatStyle setBold(Boolean bool) {
      this.bold = bool;
      return this;
   }

   public String getFormattingCode() {
      if (this.isEmpty()) {
         return this.parentStyle != null ? this.parentStyle.getFormattingCode() : "";
      } else {
         StringBuilder sb = new StringBuilder();
         if (this.getColor() != null) {
            sb.append(this.getColor());
         }

         if (this.getBold()) {
            sb.append(EnumChatFormatting.BOLD);
         }

         if (this.getItalic()) {
            sb.append(EnumChatFormatting.ITALIC);
         }

         if (this.getUnderlined()) {
            sb.append(EnumChatFormatting.UNDERLINE);
         }

         if (this.getObfuscated()) {
            sb.append(EnumChatFormatting.OBFUSCATED);
         }

         if (this.getStrikethrough()) {
            sb.append(EnumChatFormatting.STRIKETHROUGH);
         }

         return sb.toString();
      }
   }

   public ChatStyle setChatClickEvent(ClickEvent clickEvent) {
      this.chatClickEvent = clickEvent;
      return this;
   }

   private ChatStyle getParent() {
      return this.parentStyle == null ? rootStyle : this.parentStyle;
   }

   public static Boolean accessGetItalic(ChatStyle chatStyle) {
      return chatStyle.italic;
   }

   public static Boolean accessGetObfuscated(ChatStyle chatStyle) {
      return chatStyle.obfuscated;
   }

   public static Boolean accessSetBold(ChatStyle chatStyle, Boolean bool) {
      return chatStyle.bold = bool;
   }

   public HoverEvent getChatHoverEvent() {
      return this.chatHoverEvent == null ? this.getParent().getChatHoverEvent() : this.chatHoverEvent;
   }

   public boolean getObfuscated() {
      return this.obfuscated == null ? this.getParent().getObfuscated() : this.obfuscated;
   }

   public boolean getUnderlined() {
      return this.underlined == null ? this.getParent().getUnderlined() : this.underlined;
   }

   public static String accessInsertion(ChatStyle chatStyle) {
      return chatStyle.insertion;
   }

   public static Boolean accessGetStrikethrough(ChatStyle chatStyle) {
      return chatStyle.strikethrough;
   }

   public ChatStyle setInsertion(String str) {
      this.insertion = str;
      return this;
   }

   public boolean getItalic() {
      return this.italic == null ? this.getParent().getItalic() : this.italic;
   }

   @Override
   public String toString() {
      return "Style{hasParent="
         + (this.parentStyle != null)
         + ", color="
         + this.color
         + ", bold="
         + this.bold
         + ", italic="
         + this.italic
         + ", underlined="
         + this.underlined
         + ", obfuscated="
         + this.obfuscated
         + ", clickEvent="
         + this.getChatClickEvent()
         + ", hoverEvent="
         + this.getChatHoverEvent()
         + ", insertion="
         + this.getInsertion()
         + '}';
   }

   public ChatStyle createShallowCopy() {
      ChatStyle chatStyle = new ChatStyle();
      chatStyle.bold = this.bold;
      chatStyle.italic = this.italic;
      chatStyle.strikethrough = this.strikethrough;
      chatStyle.underlined = this.underlined;
      chatStyle.obfuscated = this.obfuscated;
      chatStyle.color = this.color;
      chatStyle.chatClickEvent = this.chatClickEvent;
      chatStyle.chatHoverEvent = this.chatHoverEvent;
      chatStyle.parentStyle = this.parentStyle;
      chatStyle.insertion = this.insertion;
      return chatStyle;
   }

   public ChatStyle setColor(EnumChatFormatting enumChatFormatting) {
      this.color = enumChatFormatting;
      return this;
   }

   public static Boolean accessGetUnderlined(ChatStyle chatStyle) {
      return chatStyle.underlined;
   }

   public EnumChatFormatting getColor() {
      return this.color == null ? this.getParent().getColor() : this.color;
   }

   public boolean getBold() {
      return this.bold == null ? this.getParent().getBold() : this.bold;
   }

   public static Boolean accessGetBold(ChatStyle chatStyle) {
      return chatStyle.bold;
   }

   public ChatStyle setStrikethrough(Boolean bool) {
      this.strikethrough = bool;
      return this;
   }

   public ChatStyle setObfuscated(Boolean bool) {
      this.obfuscated = bool;
      return this;
   }

   public static Boolean accessSetStrikethrough(ChatStyle chatStyle, Boolean bool) {
      return chatStyle.strikethrough = bool;
   }

   public static Boolean accessSetUnderlined(ChatStyle chatStyle, Boolean bool) {
      return chatStyle.underlined = bool;
   }

   public static ClickEvent assignChatClickEvent(ChatStyle chatStyle, ClickEvent clickEvent) {
      return chatStyle.chatClickEvent = clickEvent;
   }

   public ChatStyle setParentStyle(ChatStyle chatStyle) {
      this.parentStyle = chatStyle;
      return this;
   }


   public static final class DefaultChatStyle extends ChatStyle {
      @Override
      public ChatStyle setStrikethrough(Boolean bool) {
         throw new UnsupportedOperationException();
      }

      @Override
      public ChatStyle setItalic(Boolean bool) {
         throw new UnsupportedOperationException();
      }

      @Override
      public HoverEvent getChatHoverEvent() {
         return null;
      }

      @Override
      public ChatStyle createShallowCopy() {
         return this;
      }

      @Override
      public boolean getBold() {
         return false;
      }

      @Override
      public String getFormattingCode() {
         return "";
      }

      @Override
      public boolean getUnderlined() {
         return false;
      }

      @Override
      public boolean getStrikethrough() {
         return false;
      }

      @Override
      public ChatStyle setParentStyle(ChatStyle chatStyle) {
         throw new UnsupportedOperationException();
      }

      @Override
      public boolean getObfuscated() {
         return false;
      }

      @Override
      public ChatStyle setObfuscated(Boolean bool) {
         throw new UnsupportedOperationException();
      }

      @Override
      public String toString() {
         return "Style.ROOT";
      }

      @Override
      public EnumChatFormatting getColor() {
         return null;
      }

      @Override
      public ChatStyle setChatClickEvent(ClickEvent clickEvent) {
         throw new UnsupportedOperationException();
      }

      @Override
      public boolean getItalic() {
         return false;
      }

      @Override
      public ChatStyle setUnderlined(Boolean bool) {
         throw new UnsupportedOperationException();
      }

      @Override
      public ChatStyle createDeepCopy() {
         return this;
      }

      @Override
      public ChatStyle setBold(Boolean bool) {
         throw new UnsupportedOperationException();
      }

      @Override
      public ClickEvent getChatClickEvent() {
         return null;
      }

      @Override
      public ChatStyle setColor(EnumChatFormatting enumChatFormatting) {
         throw new UnsupportedOperationException();
      }

      @Override
      public String getInsertion() {
         return null;
      }

      @Override
      public ChatStyle setChatHoverEvent(HoverEvent hoverEvent) {
         throw new UnsupportedOperationException();
      }
   }

   public static class Serializer implements JsonDeserializer, JsonSerializer {

      public ChatStyle deserializeStyle(JsonElement elem, Type type, JsonDeserializationContext context) throws JsonParseException {
         if (elem.isJsonObject()) {
            ChatStyle chatStyle = new ChatStyle();
            JsonObject asJsonObject = elem.getAsJsonObject();
            if (asJsonObject == null) {
               return null;
            } else {
               if (asJsonObject.has("bold")) {
                  ChatStyle.accessSetBold(chatStyle, asJsonObject.get("bold").getAsBoolean());
               }

               if (asJsonObject.has("italic")) {
                  ChatStyle.accessSetItalic(chatStyle, asJsonObject.get("italic").getAsBoolean());
               }

               if (asJsonObject.has("underlined")) {
                  ChatStyle.accessSetUnderlined(chatStyle, asJsonObject.get("underlined").getAsBoolean());
               }

               if (asJsonObject.has("strikethrough")) {
                  ChatStyle.accessSetStrikethrough(chatStyle, asJsonObject.get("strikethrough").getAsBoolean());
               }

               if (asJsonObject.has("obfuscated")) {
                  ChatStyle.accessSetObfuscated(chatStyle, asJsonObject.get("obfuscated").getAsBoolean());
               }

               if (asJsonObject.has("color")) {
                  ChatStyle.assignColor(chatStyle, (EnumChatFormatting)context.deserialize(asJsonObject.get("color"), EnumChatFormatting.class));
               }

               if (asJsonObject.has("insertion")) {
                  ChatStyle.assignInsertion(chatStyle, asJsonObject.get("insertion").getAsString());
               }

               if (asJsonObject.has("clickEvent")) {
                  JsonObject asJsonObject2 = asJsonObject.getAsJsonObject("clickEvent");
                  if (asJsonObject2 != null) {
                     JsonPrimitive asJsonPrimitive = asJsonObject2.getAsJsonPrimitive("action");
                     ClickEvent.Action action = asJsonPrimitive == null ? null : ClickEvent.Action.getValueByCanonicalName(asJsonPrimitive.getAsString());
                     JsonPrimitive asJsonPrimitive2 = asJsonObject2.getAsJsonPrimitive("value");
                     String str = asJsonPrimitive2 == null ? null : asJsonPrimitive2.getAsString();
                     if (action != null && str != null && action.shouldAllowInChat()) {
                        ChatStyle.assignChatClickEvent(chatStyle, new ClickEvent(action, str));
                     }
                  }
               }

               if (asJsonObject.has("hoverEvent")) {
                  JsonObject asJsonObject3 = asJsonObject.getAsJsonObject("hoverEvent");
                  if (asJsonObject3 != null) {
                     JsonPrimitive asJsonPrimitive3 = asJsonObject3.getAsJsonPrimitive("action");
                     HoverEvent.Action action2 = asJsonPrimitive3 == null ? null : HoverEvent.Action.getValueByCanonicalName(asJsonPrimitive3.getAsString());
                     IChatComponent component = (IChatComponent)context.deserialize(asJsonObject3.get("value"), IChatComponent.class);
                     if (action2 != null && component != null && action2.shouldAllowInChat()) {
                        ChatStyle.assignChatHoverEvent(chatStyle, new HoverEvent(action2, component));
                     }
                  }
               }

               return chatStyle;
            }
         } else {
            return null;
         }
      }

      public JsonElement serialize(Object obj, Type type, JsonSerializationContext jsonSerializationContext) {
         return this.serialize((ChatStyle)obj, type, jsonSerializationContext);
      }

      public Object deserialize(JsonElement elem, Type type, JsonDeserializationContext context) throws JsonParseException {
         return this.deserializeStyle(elem, type, context);
      }

      public JsonElement serialize(ChatStyle chatStyle, Type type, JsonSerializationContext jsonSerializationContext) {
         if (chatStyle.isEmpty()) {
            return null;
         } else {
            JsonObject json = new JsonObject();
            if (ChatStyle.accessGetBold(chatStyle) != null) {
               json.addProperty("bold", ChatStyle.accessGetBold(chatStyle));
            }

            if (ChatStyle.accessGetItalic(chatStyle) != null) {
               json.addProperty("italic", ChatStyle.accessGetItalic(chatStyle));
            }

            if (ChatStyle.accessGetUnderlined(chatStyle) != null) {
               json.addProperty("underlined", ChatStyle.accessGetUnderlined(chatStyle));
            }

            if (ChatStyle.accessGetStrikethrough(chatStyle) != null) {
               json.addProperty("strikethrough", ChatStyle.accessGetStrikethrough(chatStyle));
            }

            if (ChatStyle.accessGetObfuscated(chatStyle) != null) {
               json.addProperty("obfuscated", ChatStyle.accessGetObfuscated(chatStyle));
            }

            if (ChatStyle.accessColor(chatStyle) != null) {
               json.add("color", jsonSerializationContext.serialize(ChatStyle.accessColor(chatStyle)));
            }

            if (ChatStyle.accessInsertion(chatStyle) != null) {
               json.add("insertion", jsonSerializationContext.serialize(ChatStyle.accessInsertion(chatStyle)));
            }

            if (ChatStyle.accessChatClickEvent(chatStyle) != null) {
               JsonObject json2 = new JsonObject();
               json2.addProperty("action", ChatStyle.accessChatClickEvent(chatStyle).getAction().getCanonicalName());
               json2.addProperty("value", ChatStyle.accessChatClickEvent(chatStyle).getValue());
               json.add("clickEvent", json2);
            }

            if (ChatStyle.accessChatHoverEvent(chatStyle) != null) {
               JsonObject json3 = new JsonObject();
               json3.addProperty("action", ChatStyle.accessChatHoverEvent(chatStyle).getAction().getCanonicalName());
               json3.add("value", jsonSerializationContext.serialize(ChatStyle.accessChatHoverEvent(chatStyle).getValue()));
               json.add("hoverEvent", json3);
            }

            return json;
         }
      }
   }
}
