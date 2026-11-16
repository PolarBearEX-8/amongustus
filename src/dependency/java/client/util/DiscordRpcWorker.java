package client.util;

import client.Client;
import client.module.Module;
import client.network.CosmeticsNetworkHandler;
import net.arikia.dev.drpc.DiscordEventHandlers;
import net.arikia.dev.drpc.DiscordRPC;
import net.arikia.dev.drpc.DiscordUser;
import net.arikia.dev.drpc.DiscordRichPresence.Builder;

public class DiscordRpcWorker {
   public Thread thread;
   public boolean enabled2;
   public boolean enabled;
   public long timestamp;
   public final TimeHelper timer = new TimeHelper();
   public static String labelText = "";

   public void applyString(String text, String text2) {
      Builder builder = null;
      long value = 0x4f8e9984d36a5800L;
      long value2 = 0x233a901efccb8ec9L;

      while (true) {
         int count = (int)(value % 9L);
         if (count != 1) {
            if (count == 8) {
               builder.setDetails(text);
               value = (value ^ -0x588c0705f81f01c6L) + 0x331f6a910ce1a39aL;
               value2 = (value2 ^ 0x6f9db3e6936148c8L) - 0x15a28c55648d4c42L;
               continue;
            }

            if (count == 2) {
               builder = new Builder(text2);
               value = (value ^ -0xa16ef0af8a1bdd1L) + 0x5e5efa95c228f5aeL;
               value2 = (value2 ^ 0x2805edb764bc9101L) - 0x7d64bbae61f6bad5L;
               continue;
            }

            if (count != 4) {
               if (count == 3) {
                  builder.setBigImage("large", "");
                  value = (value ^ 0x2d090cf42b78510eL) - 0x2349d6c24047b700L;
                  value2 = (value2 ^ -0x3a10d731df415420L) + 0x39e9b0cdbb113308L;
                  continue;
               }

               if (count == 0) {
                  builder.setStartTimestamps(this.timestamp);
                  value = (value ^ -0x50eed4fa0640b071L) - 0x435f40c2f9bea893L;
                  value2 = (value2 ^ -0x3d9ada849b678654L) + 0x7a901280d35d5c4bL;
                  continue;
               }

               if (count != 6) {
                  if (Client.getClient().getModuleManager() != null) {
                     value = (value ^ 0x665f495aacf03603L) - 0x225f3de4ece48c42L;
                     value2 = (value2 ^ -0x2ddd84832f004bcaL) + 0x2c75090ead772564L;
                     continue;
                  }
                  break;
               }

               DiscordRPC.discordUpdatePresence(builder.build());
            } else {
               if (((Module)Client.getClient().getModuleManager().noDiscordRPC).isToggled()) {
                  break;
               }

               long value3 = (value ^ 0x16a3c09b7cfde96dL) + 0x4c2a840d2ff56e48L;
               long value4 = (value2 ^ -0x401e90be76605583L) + 0x4c9ecbce8be1f724L;
               if (this.enabled2) {
                  value = (value3 ^ -0x103871bf9ebd55f7L) + 0x5643b0b3ddfdae7dL;
                  value2 = (value4 ^ -0x1ecd32136be802b8L) - 0x76f4b147d917162fL;
                  continue;
               }
            }
         }

         return;
      }
   }

   private void applyText(int count, String text) {
      System.err.println("Failed to load");
      this.enabled2 = false;
   }

   public static boolean matchesDiscordRpcWorker(DiscordRpcWorker discordRpcWorker, boolean flag) {
      return discordRpcWorker.enabled2 = flag;
   }

   public static boolean matchesDiscordRpcWorker(DiscordRpcWorker discordRpcWorker) {
      return discordRpcWorker.enabled2;
   }

   private static void applyDiscordUser(DiscordUser discordUser) {
      if (CosmeticsNetworkHandler.isToggled()
         && CosmeticsNetworkHandler.getSimpleChannelInboundHandlerAdapter().canExecute()
         && discordUser.userId != null
         && !discordUser.userId.isEmpty()) {
         CosmeticsNetworkHandler simpleChannelInboundHandlerAdapter = CosmeticsNetworkHandler.getSimpleChannelInboundHandlerAdapter();
         CommonLongEntry commonLongEntry = new CommonLongEntry(SupplementalLongEntry.byteIndexArray, Long.parseLong(discordUser.userId));
         simpleChannelInboundHandlerAdapter.updatePostMotionProcessor(commonLongEntry);
      }
   }

   public void updateState() {
      long value = 0x74b5f512c41d1981L;

      while (true) {
         if ((int)(value % 3L) == 2) {
            this.enabled2 = false;
            DiscordRPC.discordShutdown();
            break;
         }

         if (!this.enabled2) {
            break;
         }

         value = (value ^ 0xa6dcfaa47d704afL) - 0x1ea5845253973edeL;
      }
   }

   public static TimeHelper resolveTimeHelper(DiscordRpcWorker discordRpcWorker) {
      return discordRpcWorker.timer;
   }

   public void updateState2() {
      DiscordEventHandlers event = null;
      long value = 0x2e3f0e8ab57370c9L;
      long value2 = 0x1ba2dc92e5a12a62L;

      while (true) {
         int count = (int)(value % 11L);
         if (count == 3) {
            this.enabled = true;
            value = (value ^ -0x6aba5aba1cbf030eL) - 0x1adb6491a9797eceL;
            value2 = (value2 ^ -0x35aab9e446b83d9aL) - 0x3ec2f76b8baafef8L;
         } else if (count != 2) {
            if (count == 9) {
               break;
            }

            if (count != 5) {
               if (count != 10) {
                  if (count == 0) {
                     this.thread.start();
                     return;
                  }

                  if (count != 4) {
                     if (count != 6) {
                        if (count != 1) {
                           DiscordRPC.discordInitialize("944603218623733780", event, true);
                           value = (value ^ -0x4e96da248acb5486L) + 0x6ec199615338ebc3L;
                           value2 = (value2 ^ -0x1a5743a8d63e7c45L) + 0xc2f7a1b5248f5b0L;
                        } else {
                           event = new net.arikia.dev.drpc.DiscordEventHandlers.Builder()
                              .setReadyEventHandler(this::applyDiscordUserByDiscordUser)
                              .setErroredEventHandler(this::applyText)
                              .setDisconnectedEventHandler(this::applyTextByText)
                              .build();
                           value = (value ^ 0x4be457eae1f038d3L) - 0x1afea6eb79f46a9aL;
                           value2 = (value2 ^ -0x40d9ef0d79742919L) - 0x4c646d694f6cc66eL;
                        }
                     } else {
                        this.thread = new ThreadAdapter(this, "Discord RPC Callback", event);
                        value = (value ^ -0x2a3eda08e56051bL) - 0x51be087b52350fafL;
                        value2 = (value2 ^ -0x12ec58bea97769e4L) + 0x7b1358d64ff56875L;
                     }
                  } else {
                     this.timer.reset();
                     value = (value ^ -0x7e939ea5a7850f4fL) - 0x77b7c6f4f4623517L;
                     value2 = (value2 ^ 0x6e07279ae7adfcbfL) + 0x4ef6f2d868ed5bd9L;
                  }
               } else {
                  this.thread.interrupt();
                  value = (value ^ -0x8a0af9ed4a23eaeL) + 0x5bcb424f8e71cf5cL;
                  value2 = (value2 ^ -0x716edab0164dee5cL) - 0x697733889b3c9e75L;
               }
            } else {
               Client.getClient().getClass();
               value = (value ^ -0x88a9b79dc67ab56L) + 0x33a2793c5123fcf0L;
               value2 = (value2 ^ 0x11155fde2c8cc260L) + 0x53a4bb8fe3c50d48L;
            }
         } else {
            if (this.enabled2) {
               break;
            }

            long value3 = (value ^ -0x5a9c9344f7cd0718L) + 0x5fe69595ef646793L;
            long value4 = (value2 ^ -0x7010fcc1211f282fL) + 0x466acfd36b424281L;
            if (this.thread == null) {
               value = (value3 ^ 0x14574a15d4d6585bL) - 0x25084859e72fa43aL;
               value2 = (value4 ^ -0x658ff61dba471302L) + 0x779e8be5e564826fL;
            } else {
               value = (value3 ^ 0x55a82c9754c40e6aL) - 0x148b80c5d0ece2f7L;
               value2 = (value4 ^ 0x23dc4cb37baea61aL) + 0x10231f7b34015a5cL;
            }
         }
      }
   }

   private void applyTextByText(int count, String text) {
      for (long index = 0x1d261b03d098b356L; (int)(index % 3L) == 2; index = (index ^ 0x76f7d5d582cb7959L) - 0x4bfd85a03564486bL) {
         System.err.println("Disconnected!");
      }

      this.enabled2 = false;
   }

   private void applyDiscordUserByDiscordUser(DiscordUser discordUser) {
      this.enabled2 = true;
      labelText = discordUser.userId + "###" + discordUser.username;
      SupplementalLongEntry.copyOnWriteArrayList.add((Runnable)(() -> DiscordRpcWorker.applyDiscordUser(discordUser)));
      Client.getClient().getClass();
      ProfileConfigManager profileConfigManager = new ProfileConfigManager();
      String text = Client.getClient().getString7() + "  " + discordUser.username + " likes to go shopping at " + discordUser.userId;
      profileConfigManager.applyText(text, 0x4896959da3e6L);
   }

   public DiscordRpcWorker() {
      this.enabled2 = false;
      this.timestamp = System.currentTimeMillis();
      this.enabled = false;
      this.thread = null;
   }
}
