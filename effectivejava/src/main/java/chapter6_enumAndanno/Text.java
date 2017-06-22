package chapter6_enumAndanno;

/**
 * Created by BG289522 on 2017/6/22.
 */

/**
 * 32：用EnumSet代替位域
 */
public class Text {

    public static final int STYLE_BOLD = 1 << 0;
    public static final int STYLE_ITALIC = 1 << 1;
    public static final int STYLE_UNDERLINE = 1 << 2;
    public static final int STYLE_STRIKETHROUGH = 1 << 3;
}
