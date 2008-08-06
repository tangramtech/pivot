/*
 * Copyright (c) 2008 VMware, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package pivot.wtk.content;

import java.awt.Color;
import java.awt.Font;
import java.net.URL;

import pivot.beans.BeanDictionary;
import pivot.collections.Dictionary;
import pivot.wtk.ApplicationContext;
import pivot.wtk.Component;
import pivot.wtk.Dimensions;
import pivot.wtk.FlowPane;
import pivot.wtk.HorizontalAlignment;
import pivot.wtk.ImageView;
import pivot.wtk.Insets;
import pivot.wtk.Label;
import pivot.wtk.ListView;
import pivot.wtk.VerticalAlignment;
import pivot.wtk.media.Image;

public class ListViewItemRenderer extends FlowPane implements ListView.ItemRenderer {
    protected ImageView imageView = new ImageView();
    protected Label label = new Label();

    public static final String ICON_KEY = "icon";
    public static final String ICON_URL_KEY = "iconURL";
    public static final String LABEL_KEY = "label";

    public static final String ICON_WIDTH_KEY = "iconWidth";
    public static final String ICON_HEIGHT_KEY = "iconHeight";
    public static final String ICON_SIZE_KEY = "iconSize";
    public static final String SHOW_ICON_KEY = "showIcon";

    public static final int DEFAULT_ICON_WIDTH = 16;
    public static final int DEFAULT_ICON_HEIGHT = 16;
    public static boolean DEFAULT_SHOW_ICON = false;

    public ListViewItemRenderer() {
        super();

        getStyles().put("horizontalAlignment", HorizontalAlignment.LEFT);
        getStyles().put("verticalAlignment", VerticalAlignment.CENTER);
        getStyles().put("padding", new Insets(2));

        add(imageView);
        add(label);

        imageView.setPreferredSize(DEFAULT_ICON_WIDTH, DEFAULT_ICON_HEIGHT);
        imageView.setDisplayable(DEFAULT_SHOW_ICON);
    }

    @Override
    public void setSize(int width, int height) {
        super.setSize(width, height);

        // Since this component doesn't have a parent, it won't be validated
        // via layout; ensure that it is valid here
        validate();
    }

    @SuppressWarnings("unchecked")
    public void render(Object item, ListView listView, boolean selected,
        boolean highlighted, boolean disabled) {
        Image icon = null;
        String text = null;

        if (item != null) {
            if (item instanceof Image) {
                icon = (Image)item;
            } else if (item instanceof String) {
                text = (String)item;
            } else {
                Dictionary<String, Object> dictionary = (item instanceof Dictionary<?, ?>) ?
                    (Dictionary<String, Object>)item : new BeanDictionary(item);

                icon = (Image)dictionary.get(ICON_KEY);
                if (icon == null) {
                    URL iconURL = (URL)dictionary.get(ICON_URL_KEY);

                    if (iconURL != null) {
                        ApplicationContext applicationContext = ApplicationContext.getInstance();
                        icon = (Image)applicationContext.getResources().get(iconURL);

                        if (icon == null) {
                            icon = Image.load(iconURL);
                            applicationContext.getResources().put(iconURL, icon);
                        }
                    }
                }

                text = (String)dictionary.get(LABEL_KEY);
            }
        }

        // Update the image view
        imageView.setImage(icon);
        imageView.getStyles().put("opacity", listView.isEnabled() ? 1.0f : 0.5f);

        // Show/hide the label
        label.setText(text);

        if (text != null) {
            // Update the label styles
            Component.StyleDictionary labelStyles = label.getStyles();

            Object labelFont = listView.getStyles().get("font");
            if (labelFont instanceof Font) {
                labelStyles.put("font", labelFont);
            }

            Object color = null;
            if (listView.isEnabled() && !disabled) {
                if (selected) {
                    if (listView.isFocused()) {
                        color = listView.getStyles().get("selectionColor");
                    } else {
                        color = listView.getStyles().get("inactiveSelectionColor");
                    }
                } else {
                    color = listView.getStyles().get("color");
                }
            } else {
                color = listView.getStyles().get("disabledColor");
            }

            if (color instanceof Color) {
                labelStyles.put("color", color);
            }
        }
    }

    public int getIconWidth() {
        return imageView.getPreferredWidth(-1);
    }

    public int getIconHeight() {
        return imageView.getPreferredHeight(-1);
    }

    public Dimensions getIconSize() {
        return new Dimensions(getIconWidth(), getIconHeight());
    }

    public void setIconSize(Dimensions iconSize) {
        setIconSize(iconSize.width, iconSize.height);
    }

    public void setIconSize(Dictionary<String, Object> iconSize) {
        setIconSize(new Dimensions(iconSize));
    }

    public void setIconSize(int width, int height) {
        imageView.setPreferredSize(width, height);
    }

    public boolean getShowIcon() {
        return imageView.isDisplayable();
    }

    public void setShowIcon(boolean showIcon) {
        imageView.setDisplayable(showIcon);
    }

    public void setShowIcon(String showIcon) {
        setShowIcon(Boolean.parseBoolean(showIcon));
    }
}
