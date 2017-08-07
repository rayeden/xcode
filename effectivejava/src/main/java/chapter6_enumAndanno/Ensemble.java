package chapter6_enumAndanno;

/**
 * Created by xhtc on 2017/6/22.
 */

/**
 * 31：用实例域代替序数
 * 永远不要根据枚举的序数导出与它相关联的值，而是要将它保存在一个实例域中。
 */
public enum Ensemble {

    SOLO(1), DUET(2), TRIO(3), QUARTET(4), QUINTET(5),
    SEXTET(6), SEPTET(7), OCTET(8), NONET(9), DECTET(10);

    private final int numberOfMusicians;

    Ensemble(int size){
        this.numberOfMusicians = size;
    }
//    SOLO, DUET, TRIO, QUARTET, QUINTET,
//    SEXTET, SEPTET, OCTET, NONET, DECTET;
//
//    public int numberOfMusicians(){
//        return ordinal() + 1;
//    }

    public int numberOfMusicians(){
        return numberOfMusicians;
    }

    public static void main(String[] args) {
        for (Ensemble ensemble : Ensemble.values()) {
            System.out.println(ensemble.numberOfMusicians());
        }
    }
}
