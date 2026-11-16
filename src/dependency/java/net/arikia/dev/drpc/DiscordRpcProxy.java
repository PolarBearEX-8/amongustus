package net.arikia.dev.drpc;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.UndeclaredThrowableException;
import net.arikia.dev.drpc.DiscordRPC.DLL;

public final class DiscordRpcProxy extends Proxy implements DLL {
   public static Method m1;
   public static Method m4;
   public static Method m9;
   public static Method m11;
   public static Method m7;
   public static Method m2;
   public static Method m3;
   public static Method m6;
   public static Method m8;
   public static Method m5;
   public static Method m0;
   public static Method m10;

   public DiscordRpcProxy(InvocationHandler invocationHandler) {
      super(invocationHandler);
   }

   @Override
   public final boolean equals(Object other) {
      try {
         return (Boolean)super.h.invoke(this, m1, new Object[]{other});
      } catch (RuntimeException | Error exception) {
         throw exception;
      } catch (Throwable throwable) {
         throw new UndeclaredThrowableException(throwable);
      }
   }

   public final void Discord_Respond(String text, int count) {
      try {
         super.h.invoke(this, m4, new Object[]{text, count});
      } catch (RuntimeException | Error exception) {
         throw exception;
      } catch (Throwable throwable) {
         throw new UndeclaredThrowableException(throwable);
      }
   }

   public final void Discord_UpdatePresence(DiscordRichPresence discordRichPresence) {
      try {
         super.h.invoke(this, m9, new Object[]{discordRichPresence});
      } catch (RuntimeException | Error exception) {
         throw exception;
      } catch (Throwable throwable) {
         throw new UndeclaredThrowableException(throwable);
      }
   }

   public final void Discord_ClearPresence() {
      try {
         super.h.invoke(this, m11, null);
      } catch (RuntimeException | Error exception) {
         throw exception;
      } catch (Throwable throwable) {
         throw new UndeclaredThrowableException(throwable);
      }
   }

   public final void Discord_RunCallbacks() {
      try {
         super.h.invoke(this, m7, null);
      } catch (RuntimeException | Error exception) {
         throw exception;
      } catch (Throwable throwable) {
         throw new UndeclaredThrowableException(throwable);
      }
   }

   @Override
   public final String toString() {
      try {
         return (String)super.h.invoke(this, m2, null);
      } catch (RuntimeException | Error exception) {
         throw exception;
      } catch (Throwable throwable) {
         throw new UndeclaredThrowableException(throwable);
      }
   }

   public final void Discord_Register(String text, String text2) {
      try {
         super.h.invoke(this, m3, new Object[]{text, text2});
      } catch (RuntimeException | Error exception) {
         throw exception;
      } catch (Throwable throwable) {
         throw new UndeclaredThrowableException(throwable);
      }
   }

   public final void Discord_Initialize(String text, DiscordEventHandlers event, int count, String text2) {
      try {
         super.h.invoke(this, m6, new Object[]{text, event, count, text2});
      } catch (RuntimeException | Error exception) {
         throw exception;
      } catch (Throwable throwable) {
         throw new UndeclaredThrowableException(throwable);
      }
   }

   public final void Discord_RegisterSteamGame(String text, String text2) {
      try {
         super.h.invoke(this, m8, new Object[]{text, text2});
      } catch (RuntimeException | Error exception) {
         throw exception;
      } catch (Throwable throwable) {
         throw new UndeclaredThrowableException(throwable);
      }
   }

   public final void Discord_Shutdown() {
      try {
         super.h.invoke(this, m5, null);
      } catch (RuntimeException | Error exception) {
         throw exception;
      } catch (Throwable throwable) {
         throw new UndeclaredThrowableException(throwable);
      }
   }

   @Override
   public final int hashCode() {
      try {
         return (Integer)super.h.invoke(this, m0, null);
      } catch (RuntimeException | Error exception) {
         throw exception;
      } catch (Throwable throwable) {
         throw new UndeclaredThrowableException(throwable);
      }
   }

   public final void Discord_UpdateHandlers(DiscordEventHandlers event) {
      try {
         super.h.invoke(this, m10, new Object[]{event});
      } catch (RuntimeException | Error exception) {
         throw exception;
      } catch (Throwable throwable) {
         throw new UndeclaredThrowableException(throwable);
      }
   }

   static {
      try {
         m1 = Class.forName("java.lang.Object").getMethod("equals", Class.forName("java.lang.Object"));
         m4 = Class.forName("net.arikia.dev.drpc.DiscordRPC$DLL").getMethod("Discord_Respond", Class.forName("java.lang.String"), int.class);
         m9 = Class.forName("net.arikia.dev.drpc.DiscordRPC$DLL").getMethod("Discord_UpdatePresence", Class.forName("net.arikia.dev.drpc.DiscordRichPresence"));
         m11 = Class.forName("net.arikia.dev.drpc.DiscordRPC$DLL").getMethod("Discord_ClearPresence");
         m7 = Class.forName("net.arikia.dev.drpc.DiscordRPC$DLL").getMethod("Discord_RunCallbacks");
         m2 = Class.forName("java.lang.Object").getMethod("toString");
         m3 = Class.forName("net.arikia.dev.drpc.DiscordRPC$DLL")
            .getMethod("Discord_Register", Class.forName("java.lang.String"), Class.forName("java.lang.String"));
         m6 = Class.forName("net.arikia.dev.drpc.DiscordRPC$DLL")
            .getMethod(
               "Discord_Initialize",
               Class.forName("java.lang.String"),
               Class.forName("net.arikia.dev.drpc.DiscordEventHandlers"),
               int.class,
               Class.forName("java.lang.String")
            );
         m8 = Class.forName("net.arikia.dev.drpc.DiscordRPC$DLL")
            .getMethod("Discord_RegisterSteamGame", Class.forName("java.lang.String"), Class.forName("java.lang.String"));
         m5 = Class.forName("net.arikia.dev.drpc.DiscordRPC$DLL").getMethod("Discord_Shutdown");
         m0 = Class.forName("java.lang.Object").getMethod("hashCode");
         m10 = Class.forName("net.arikia.dev.drpc.DiscordRPC$DLL")
            .getMethod("Discord_UpdateHandlers", Class.forName("net.arikia.dev.drpc.DiscordEventHandlers"));
      } catch (NoSuchMethodException noSuchMethodException) {
         throw new NoSuchMethodError(noSuchMethodException.getMessage());
      } catch (ClassNotFoundException classNotFoundException) {
         throw new NoClassDefFoundError(classNotFoundException.getMessage());
      }
   }
}
