package by.it.group410971.maleckij.lesson01;

import java.math.BigInteger;
import java.util.ArrayList;

/*
 * Вам необходимо выполнить способ вычисления чисел Фибоначчи со вспомогательным массивом
 * без ограничений на размер результата (BigInteger)
 */

public class FiboB {

    private long startTime = System.currentTimeMillis();

    public static void main(String[] args) {
        //вычисление чисел простым быстрым методом
        FiboB fibo = new FiboB();
        int n = 55555;
        System.out.printf("fastB(%d)=%d \n\t time=%d \n\n", n, fibo.fastB(n), fibo.time());
    }

    private long time() {
        return System.currentTimeMillis() - startTime;
    }

    BigInteger fastB(Integer n) {
        if (n <= 1) {
            return BigInteger.valueOf(n);
        }
        ArrayList<BigInteger> fib = new ArrayList<>(n + 1);
        fib.add(BigInteger.ZERO);
        fib.add(BigInteger.ONE);
        for (int i = 2; i <= n; i++) {
            fib.add(fib.get(i - 1).add(fib.get(i - 2)));
        }
        return fib.get(n);
    }

}

