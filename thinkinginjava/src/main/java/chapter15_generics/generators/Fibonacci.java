package chapter15_generics.generators;

import net.mindview.util.Generator;

/**
 * Created by xhtc on 2017/5/4.
 */

/**
 * 泛型接口 -- 生成器
 */
public class Fibonacci implements Generator<Integer> {

    private int count = 0;

    @Override
    public Integer next() {
        return fib(count++);
    }

    private int fib(int n){
        if(n < 2)
            return 1;
        else
            return fib(n-1) + fib(n-2);
    }

    public static void main(String[] args) {
        Fibonacci gen = new Fibonacci();
        for (int i = 0; i < 18; ++i) {
            System.out.println(gen.next() + " ");
        }
    }
}
