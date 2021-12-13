package com.yahui;

/**
 * @author yanyahui <yanyahui@kuaishou.com>
 * Created on 2021-09-13
 */
public class GaussianDistribution {
    private static final long seed = 1234567L;
    private static final double averageSqrt = Math.sqrt(100);
    private static final int sig = 1;

    public static void main(String[] args) {
//        Random random = new Random(seed);
//        final List<Double> collect = IntStream.range(0, 1000)
//                .mapToObj(i -> random.nextGaussian() * averageSqrt + sig)
//                .collect(Collectors.toList());
//        System.out.println(collect);

        System.out.println(100 % -7);
    }
}
