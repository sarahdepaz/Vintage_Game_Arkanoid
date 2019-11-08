package other;

import java.awt.Color;

/**
 * Class of an ColorsParser.
 *
 * @author sarah de paz
 */
public class ColorsParser {
    /**
     * function that parse color definition and return the specified color.
     *
     * @param s
     *            the string to parse the color from
     * @return the specified color
     */
    public Color colorFromString(String s) {
        Color c = null;
        try {
            c = (Color) Color.class.getField(s.toLowerCase()).get(null);
        } catch (Exception e) {
            return null;
        }
        return c;
    }

    /**
     * function that create color from RGB.
     *
     * @param red
     *            one of the basic color
     * @param green
     *            one of the basic color
     * @param blue
     *            one of the basic color
     * @return the new color
     */
    public Color colorFromRGB(int red, int green, int blue) {
        float[] hsb = Color.RGBtoHSB(red, green, blue, null);
        return Color.getHSBColor(hsb[0], hsb[1], hsb[2]);
    }
}