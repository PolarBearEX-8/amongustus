package client.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public interface GsonCallback {
   Gson gson2 = new GsonBuilder().setPrettyPrinting().create();
   Gson gson = new GsonBuilder().create();
}
