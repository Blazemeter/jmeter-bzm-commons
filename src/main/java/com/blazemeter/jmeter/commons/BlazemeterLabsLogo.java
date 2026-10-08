package com.blazemeter.jmeter.commons;

import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BlazemeterLabsLogo extends JLabel {

  private static final Logger LOG = LoggerFactory.getLogger(BlazemeterLabsLogo.class);
  private static final String LOGO_RESOURCE = "blazemeter-by-perforce-logo.png";

  private int logoWidth;
  private int logoHeight;

  public BlazemeterLabsLogo(String pageURL) {
    super(ThemedIcon.fromResourceName(LOGO_RESOURCE));
    setBrowseOnClick(pageURL);
  }

  public BlazemeterLabsLogo(String pageURL, int width, int height) {
    super();
    this.logoWidth = width;
    this.logoHeight = height;
    setLogoSize(width, height);
    setBrowseOnClick(pageURL);
  }

  /*
   Called when JMeter's look and feel changes: the logo follows the new theme (its dark or light
   variant), as ThemedIconLabel does.
   */
  @Override
  public void updateUI() {
    super.updateUI();
    if (getIcon() == null) {
      return; // still being constructed
    }
    if (logoWidth > 0 && logoHeight > 0) {
      setLogoSize(logoWidth, logoHeight);
    } else {
      setIcon(ThemedIcon.fromResourceName(LOGO_RESOURCE));
    }
  }

  private void setBrowseOnClick(final String url) {
    this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    this.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent mouseEvent) {
        if (Desktop.isDesktopSupported()) {
          try {
            Desktop.getDesktop().browse(new URI(url));
          } catch (URISyntaxException | IOException exception) {
            BlazemeterLabsLogo.LOG.error("Problem when accessing repository", exception);
          }
        }
      }
    });
  }

  private void setLogoSize(int width, int height) {
    Image scaledImg = ThemedIcon.fromResourceName(LOGO_RESOURCE).getImage()
        .getScaledInstance(width, height, Image.SCALE_SMOOTH);
    setIcon(new ImageIcon(scaledImg));
    setPreferredSize(new Dimension(width, height));
    setMinimumSize(new Dimension(width, height));
    setMaximumSize(new Dimension(width, height));
    setHorizontalAlignment(CENTER);
    setVerticalAlignment(CENTER);
    revalidate();
    repaint();
  }
}
