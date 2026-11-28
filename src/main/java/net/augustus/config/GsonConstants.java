package net.augustus.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public interface GsonConstants {
   Gson GSON = new GsonBuilder().create();
   Gson PRETTY_GSON = new GsonBuilder().setPrettyPrinting().create();
}
