package client.util;

import com.google.gson.JsonDeserializer;

public interface JsonDeserializerExtension extends JsonDeserializer {
   String getText();
}
