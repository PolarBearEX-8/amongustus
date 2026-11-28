package net.augustus.cosmetics;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.awt.LayoutManager;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

public class CosmeticsDebugConsole extends JFrame {
   private static boolean headless;
   private JTextPane logPane;
   private JTextField commandField;
   private static CosmeticsDebugConsole instance = null;
   private JPanel contentPanel;

   public static void openConsole() {
      if (headless) {
         MoreCosmeticsCore.logInfo("Failed to open console! Running headless.");
      } else if (instance != null) {
         if (!instance.isVisible()) {
            EventQueue.invokeLater(() -> {
               instance.setVisible(true);
               instance.toFront();
            });
         }
      } else {
         EventQueue.invokeLater(() -> createAndShowConsole());
      }
   }

   private CosmeticsDebugConsole() {
   }

   public static void appendMessage(String str, ConsoleMessageType consoleMessageType) {
      if (headless && consoleMessageType == ConsoleMessageType.DEBUG) {
         MoreCosmeticsCore.logInfo(consoleMessageType.getPrefix() + " " + str);
      } else if (instance != null) {
         SwingUtilities.invokeLater(() -> {
            StyleContext defaultStyleContext = StyleContext.getDefaultStyleContext();
            AttributeSet attributeSet = defaultStyleContext.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, consoleMessageType.getColor());
            attributeSet = defaultStyleContext.addAttribute(attributeSet, StyleConstants.FontFamily, "Consolas");
            attributeSet = defaultStyleContext.addAttribute(attributeSet, StyleConstants.Alignment, 3);
            int length = instance.logPane.getDocument().getLength();
            instance.logPane.setEditable(true);
            instance.logPane.setCaretPosition(length);
            instance.logPane.setCharacterAttributes(attributeSet, false);
            instance.logPane.replaceSelection(consoleMessageType.getPrefix() + " " + str + "\n");
            instance.logPane.setEditable(false);
         });
      }
   }

   private void initComponents() {
      this.setBounds(100, 100, 1100, 600);
      this.contentPanel = new JPanel();
      this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
      this.contentPanel.setLayout((LayoutManager)null);
      this.contentPanel.setBackground(new Color(25, 26, 31));
      this.logPane = new JTextPane();
      JScrollPane jScrollPane = new JScrollPane(this.logPane);
      jScrollPane.setBounds(10, 10, 1064, 509);
      jScrollPane.setBackground(new Color(19, 18, 23));
      jScrollPane.setBorder((Border)null);
      this.logPane.setBorder((Border)null);
      this.logPane.setEditable(false);
      this.logPane.setBackground(new Color(19, 18, 23));
      this.logPane.setForeground(Color.WHITE);
      this.contentPanel.add(jScrollPane);
      this.commandField = new JTextField();
      this.commandField.setBorder((Border)null);
      this.commandField.setForeground(Color.WHITE);
      this.commandField.setBackground(new Color(19, 18, 23));
      this.commandField.setBounds(10, 530, 1064, 20);
      this.contentPanel.add(this.commandField);
      this.setContentPane(this.contentPanel);
   }

   private static void createAndShowConsole() {

      try {
         UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
         instance = new CosmeticsDebugConsole();
         instance.initComponents();
         instance.setTitle("Debug Console");
         URL resource = Thread.currentThread().getContextClassLoader().getResource("assets/minecraft/morecosmetics/gui/logo/icon.png");
         instance.setIconImage(new ImageIcon(resource).getImage());
         instance.setResizable(false);
         instance.setVisible(true);
         instance.toFront();
      } catch (HeadlessException e) {
         headless = true;
         MoreCosmeticsCore.logInfo("Failed to open console! Running headless.");
      } catch (Exception e2) {
         MoreCosmeticsCore.logError(e2);
      }
   }

}
