package net.raphimc.minecraftauth.step.msa;

import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebView;
import javax.swing.JFrame;
import lombok.Generated;
import net.lenni0451.commons.httpclient.HttpClient;
import net.lenni0451.commons.httpclient.HttpResponse;
import net.lenni0451.commons.httpclient.utils.URLWrapper;
import net.raphimc.minecraftauth.responsehandler.exception.MsaRequestException;
import net.raphimc.minecraftauth.step.AbstractStep.ApplicationDetails;
import net.raphimc.minecraftauth.step.AbstractStep.InitialInput;
import net.raphimc.minecraftauth.step.msa.MsaCodeStep.MsaCode;
import net.raphimc.minecraftauth.util.logging.ILogger;

public class StepJfxWebViewMsaCode extends MsaCodeStep<StepJfxWebViewMsaCode.JavaFxWebView> {
   private final int timeout;

   public StepJfxWebViewMsaCode(ApplicationDetails applicationDetails, int timeout) {
      super(applicationDetails);
      this.timeout = timeout;
   }

   protected MsaCode execute(ILogger logger, HttpClient httpClient, StepJfxWebViewMsaCode.JavaFxWebView javaFxWebViewCallback) throws Exception {
      try {
         logger.info(this, "Opening JavaFX WebView window for MSA login...");
         URL authenticationUrl = new URLWrapper(this.applicationDetails.getOAuthEnvironment().getAuthorizeUrl())
            .wrapQuery()
            .addQueries(this.applicationDetails.getOAuthParameters())
            .apply()
            .toURL();
         final CompletableFuture<MsaCode> msaCodeFuture = new CompletableFuture<>();
         JFXPanel jfxPanel = new JFXPanel();
         JFrame window = new JFrame("MinecraftAuth - Microsoft Login");
         window.setDefaultCloseOperation(0);
         window.setSize(800, 600);
         window.setLocationRelativeTo(null);
         window.setResizable(false);
         window.setContentPane(jfxPanel);
         window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
               if (!msaCodeFuture.isDone()) {
                  msaCodeFuture.completeExceptionally(new StepJfxWebViewMsaCode.UserClosedWindowException());
               }
            }
         });
         Platform.runLater(() -> {
            WebView webView = new WebView();
            webView.setContextMenuEnabled(false);
            httpClient.getFirstHeader("User-Agent").ifPresent(s -> webView.getEngine().setUserAgent(s));
            webView.getEngine().load(authenticationUrl.toString());
            webView.getEngine().locationProperty().addListener((observable, oldValue, newValue) -> {
               try {
                  if (newValue.startsWith(this.applicationDetails.getRedirectUri())) {
                     Map<String, String> parameters = new URLWrapper(newValue).wrapQuery().getQueries();
                     if (parameters.containsKey("error") && parameters.containsKey("error_description")) {
                        HttpResponse fakeResponse = new HttpResponse(null, 500, new byte[0], Collections.emptyMap());
                        throw new MsaRequestException(fakeResponse, parameters.get("error"), parameters.get("error_description"));
                     }

                     if (!parameters.containsKey("code")) {
                        throw new IllegalStateException("Could not extract MSA Code from response url");
                     }

                     msaCodeFuture.complete(new MsaCode(parameters.get("code")));
                  }
               } catch (Throwable e2) {
                  msaCodeFuture.completeExceptionally(e2);
               }
            });
            jfxPanel.setScene(new Scene(webView, (double)window.getWidth(), (double)window.getHeight()));
            if (javaFxWebViewCallback == null) {
               window.setVisible(true);
            } else {
               javaFxWebViewCallback.openCallback.accept(window);
            }
         });

         MsaCode msaCode2;
         try {
            MsaCode msaCode = msaCodeFuture.get((long)this.timeout, TimeUnit.MILLISECONDS);
            logger.info(this, "Got MSA Code");
            msaCode2 = msaCode;
         } catch (TimeoutException e3) {
            throw new TimeoutException("MSA login timed out");
         } catch (ExecutionException e4) {
            if (e4.getCause() != null) {
               throw e4.getCause();
            }

            throw e4;
         } finally {
            if (javaFxWebViewCallback == null) {
               window.dispose();
            } else {
               javaFxWebViewCallback.closeCallback.accept(window);
            }
         }

         return msaCode2;
      } catch (Throwable e5) {
         throw StepJfxWebViewMsaCode.<RuntimeException>sneakyThrow(e5);
      }
   }

   public static final class JavaFxWebView extends InitialInput {
      private final Consumer<JFrame> openCallback;
      private final Consumer<JFrame> closeCallback;

      public JavaFxWebView() {
         this.openCallback = window -> window.setVisible(true);
         this.closeCallback = Window::dispose;
      }

      @Deprecated
      public JavaFxWebView(BiConsumer<JFrame, WebView> openCallback, Consumer<JFrame> closeCallback) {
         this.openCallback = window -> {
            openCallback.accept(window, (WebView)((JFXPanel)window.getContentPane()).getScene().getRoot());
         };
         this.closeCallback = closeCallback;
      }

      @Generated
      public Consumer<JFrame> getOpenCallback() {
         return this.openCallback;
      }

      @Generated
      public Consumer<JFrame> getCloseCallback() {
         return this.closeCallback;
      }

      @Generated
      @Override
      public String toString() {
         return "StepJfxWebViewMsaCode.JavaFxWebView(openCallback=" + this.getOpenCallback() + ", closeCallback=" + this.getCloseCallback() + ")";
      }

      @Generated
      public JavaFxWebView(Consumer<JFrame> openCallback, Consumer<JFrame> closeCallback) {
         this.openCallback = openCallback;
         this.closeCallback = closeCallback;
      }

      @Generated
      @Override
      public boolean equals(Object o) {
         if (o == this) {
            return true;
         } else if (!(o instanceof StepJfxWebViewMsaCode.JavaFxWebView)) {
            return false;
         } else {
            StepJfxWebViewMsaCode.JavaFxWebView other = (StepJfxWebViewMsaCode.JavaFxWebView)o;
            if (!other.canEqual(this)) {
               return false;
            } else {
               Object this$openCallback = this.getOpenCallback();
               Object other$openCallback = other.getOpenCallback();
               if (this$openCallback == null ? other$openCallback == null : this$openCallback.equals(other$openCallback)) {
                  Object this$closeCallback = this.getCloseCallback();
                  Object other$closeCallback = other.getCloseCallback();
                  return this$closeCallback == null ? other$closeCallback == null : this$closeCallback.equals(other$closeCallback);
               } else {
                  return false;
               }
            }
         }
      }

      @Generated
      protected boolean canEqual(Object other) {
         return other instanceof StepJfxWebViewMsaCode.JavaFxWebView;
      }

      @Generated
      @Override
      public int hashCode() {
         int PRIME = 59;
         int result = 1;
         Object $openCallback = this.getOpenCallback();
         result = result * 59 + ($openCallback == null ? 43 : $openCallback.hashCode());
         Object $closeCallback = this.getCloseCallback();
         return result * 59 + ($closeCallback == null ? 43 : $closeCallback.hashCode());
      }
   }

   public static class UserClosedWindowException extends Exception {
      public UserClosedWindowException() {
         super("User closed login window");
      }
   }

   @SuppressWarnings("unchecked")
   private static <E extends Throwable> RuntimeException sneakyThrow(Throwable t) throws E {
      throw (E)t;
   }
}
