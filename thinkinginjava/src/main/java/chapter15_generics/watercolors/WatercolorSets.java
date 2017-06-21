package chapter15_generics.watercolors;


import java.util.EnumSet;
import java.util.Set;

import static chapter15_generics.datastruct.Sets.*;
import static chapter15_generics.watercolors.Watercolors.*;
import static net.mindview.util.Print.print;


/**
 * Created by xhtc on 2017/5/5.
 */
public class WatercolorSets {

    public static void main(String[] args) {

        Set<Watercolors> set1 = EnumSet.range(YELLOW, PURPLE);

        Set<Watercolors> set2 = EnumSet.range(BLUE, WHITE);

        System.out.println(set1);

        System.out.println(set2);

        print("union(set1, set2): " + union(set1, set2));

        Set<Watercolors> subset = intersection(set1, set2);

        print("intersection(set1, set2): " + subset);

        print("differenct(set1, set2): " + difference(set1, set2));

        print("complement(set1, set2: " + complement(set1, set2));
    }

}
