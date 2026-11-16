package client.event;

import com.google.gson.JsonElement;
import java.util.function.Function;

public interface AdapterListener {
   boolean matchesJsonElement(JsonElement jsonElement, boolean flag, Function mapper);

   JsonElement resolveJsonElement(Function mapper);
}
