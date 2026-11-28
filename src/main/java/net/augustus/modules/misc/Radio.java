package net.augustus.modules.misc;

import javazoom.jl.player.Player;
import javazoom.jl.decoder.JavaLayerException;
import java.awt.Color;
import java.io.IOException;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.events.listeners.ClickGuiListener;
import net.augustus.utils.RadioStreamThread;
import net.augustus.events.listeners.TickListener;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.StringValue;
import net.augustus.settings.DoubleValue;

public class Radio extends Module implements ClickGuiListener, TickListener {
   public final DoubleValue volume;
   public final StringValue radioChannels;
   public final BooleanValue onlyIngame;
   private final RadioStreamThread streamThread;
   private String currentChannel;
   private boolean isPlayingIngame;

   public Radio() {
       super("Radio", "Radio", Color.orange, Categorys.MISC);
      this.volume = (DoubleValue)(new DoubleValue(this, "Volume", 50.0, 0.0, 100.0, 0)).markVisualOnly();
      this.radioChannels = (StringValue)(new StringValue(
            this,
            "RadioChannels",
            "I love Radio",
            new String[]{
               "I love Radio",
               "I love 2 Dance",
               "I love ChillHop",
               "I l Deutschrap",
               "I l Greatest Hits",
               "I love Hardstyle",
               "I love Hip Hop",
               "I love Mashup",
               "I love The Club",
               "SWR3",
               "DasDing",
               "BigFM",
               "Bayern3",
               "Sunshine Live",
               "Ö1",
               "Hitradio Ö3",
               "Kronehit",
               "ORF Tirol"
            }
         ))
         .markVisualOnly();
      this.onlyIngame = (BooleanValue)(new BooleanValue(this, "Only Ingame", false)).markVisualOnly();
      this.streamThread = new RadioStreamThread(null);
      this.currentChannel = "";
      this.isPlayingIngame = true;
      this.streamThread.start();
      this.setLoadFromConfig(false);
   }

   public void onEnable() {
      this.currentChannel = "";
      this.streamThread.stopStream();
      if (!this.onlyIngame.getValue() || mc.thePlayer != null) {
         this.switchRadioChannel();
      }

      this.isPlayingIngame = true;
   }

   public void onDisable() {
      this.streamThread.stopStream();
      this.currentChannel = "";
   }

   public void updateEventSubscriptions() {
      this.toggleEventSubscription(6);
      this.toggleEventSubscription(40);
   }

   public void onPreTick() {
      if (this.onlyIngame.getValue()) {
         if (mc.thePlayer == null && this.isPlayingIngame) {
            this.streamThread.stopStream();
            this.isPlayingIngame = false;
         } else if (mc.thePlayer != null && !this.isPlayingIngame) {
            this.currentChannel = "";
            this.streamThread.stopStream();
            this.switchRadioChannel();
            this.isPlayingIngame = true;
         }
      }
   }

   public void onClickGuiUpdate() {
      this.setSuffix(this.radioChannels.getSelectedValue());
      this.switchRadioChannel();
      if (RadioStreamThread.getPlayer(this.streamThread) != null) {
         RadioStreamThread.getPlayer(this.streamThread).setGain((float)(this.volume.getValue(true) * 0.8600000143051147 - 80.0));
      }
   }

   private void switchRadioChannel() {

      try {
         if (!this.currentChannel.equals(this.radioChannels.getSelectedValue())) {
            switch (this.radioChannels.getSelectedValue()) {
               case "I love Radio":
                  this.playStreamUrl("https://streams.ilovemusic.de/iloveradio1.mp3");
                  break;
               case "I love 2 Dance":
                  this.playStreamUrl("https://streams.ilovemusic.de/iloveradio2.mp3");
                  break;
               case "I love ChillHop":
                  this.playStreamUrl("https://streams.ilovemusic.de/iloveradio17.mp3");
                  break;
               case "I l Deutschrap":
                  this.playStreamUrl("https://streams.ilovemusic.de/iloveradio6.mp3");
                  break;
               case "I l Greatest Hits":
                  this.playStreamUrl("https://streams.ilovemusic.de/iloveradio16.mp3");
                  break;
               case "I love Hardstyle":
                  this.playStreamUrl("https://streams.ilovemusic.de/iloveradio21.mp3");
                  break;
               case "I love Hip Hop":
                  this.playStreamUrl("https://streams.ilovemusic.de/iloveradio3.mp3");
                  break;
               case "I love Mashup":
                  this.playStreamUrl("https://streams.ilovemusic.de/iloveradio5.mp3");
                  break;
               case "I love The Club":
                  this.playStreamUrl("https://streams.ilovemusic.de/iloveradio20.mp3");
                  break;
               case "SWR3":
                  this.playStreamUrl("https://liveradio.swr.de/sw282p3/swr3/play.mp3");
                  break;
               case "DasDing":
                  this.playStreamUrl("https://liveradio.swr.de/d9zadj3/dasding/");
                  break;
               case "BigFM":
                  this.playStreamUrl("https://stream.bigfm.de/deutschland/mp3-128/homepage");
                  break;
               case "Bayern3":
                  this.playStreamUrl("https://dispatcher.rndfnk.com/br/br3/live/mp3/mid");
                  break;
               case "Sunshine Live":
                  this.playStreamUrl("https://stream.laut.fm/sunshinelive");
                  break;
               case "Ö1":
                  this.playStreamUrl("https://orf-live.ors-shoutcast.at/oe1-q1a");
                  break;
               case "Hitradio Ö3":
                  this.playStreamUrl("https://orf-live.ors-shoutcast.at/oe3-q1a");
                  break;
               case "Kronehit":
                  this.playStreamUrl("https://secureonair.krone.at/kronehit-hp.mp3");
                  break;
               case "ORF Tirol":
                  this.playStreamUrl("https://orf-live.ors-shoutcast.at/tir-q1a");
            }
         }
      } catch (JavaLayerException | IOException e) {
         e.printStackTrace();
      }

      this.currentChannel = this.radioChannels.getSelectedValue();
   }

   private void playStreamUrl(String str) throws IOException, JavaLayerException {
      this.streamThread.playStream(str);
   }

}
