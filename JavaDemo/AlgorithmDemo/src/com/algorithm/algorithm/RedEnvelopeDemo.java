package com.algorithm.algorithm;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 红包
 * <p>
 * 方法1：二倍均值法
 * 剩余红包金额为M，剩余人数为N，那么有如下公式：
 * 每次抢到的金额 = 随机区间 （0， M / N X 2）
 * 这个公式，保证了每次随机金额的平均值是相等的，不会因为抢红包的先后顺序而造成不公平。
 * 举个栗子：
 * 假设有10个人，红包总额100元。
 * 100/10X2 = 20, 所以第一个人的随机范围是（0，20 )，平均可以抢到10元。
 * 假设第一个人随机到10元，那么剩余金额是100-10 = 90 元。
 * 90/9X2 = 20, 所以第二个人的随机范围同样是（0，20 )，平均可以抢到10元。
 * 假设第二个人随机到10元，那么剩余金额是90-10 = 80 元。
 * 80/8X2 = 20, 所以第三个人的随机范围同样是（0，20 )，平均可以抢到10元。
 * 以此类推，每一次随机范围的均值是相等的。
 */
public class RedEnvelopeDemo {

    //方法1：二倍均值法
    //发红包算法，金额参数以分为单位
    public static List<Integer> divideRedPackage(Integer totalAmount, Integer totalPeopleNum) {
        List<Integer> amountList = new ArrayList<Integer>();
        Integer restAmount = totalAmount;
        Integer restPeopleNum = totalPeopleNum;
        Random random = new Random();

        for (int i = 0; i < totalPeopleNum - 1; i++) {
            //随机范围：[1，剩余人均金额的两倍)，左闭右开
            int amount = random.nextInt(restAmount / restPeopleNum * 2 - 1) + 1;
            restAmount -= amount;
            restPeopleNum--;
            amountList.add(amount);
        }
        amountList.add(restAmount);

        return amountList;
    }

    //方法2：线段切割法
    //如何确定每一条子线段的长度呢？由“切割点”来决定。当N个人一起抢红包的时候，就需要确定N-1个切割点。
    //因此，当N个人一起抢总金额为M的红包时，我们需要做N-1次随机运算，以此确定N-1个切割点。随机的范围区间是（1， M）。
    //当所有切割点确定以后，子线段的长度也随之确定。这样每个人来抢红包的时候，只需要顺次领取与子线段长度等价的红包金额即可。
    //这就是线段切割法的思路。
    // 在这里需要注意以下两点：
    //1.当随机切割点出现重复，如何处理。
    //2.如何尽可能降低时间复杂度和空间复杂度。

    public static void main(String[] args) {
        List<Integer> amountList = divideRedPackage(5000, 30);

        for (Integer amount : amountList) {
            System.out.println("抢到金额：" + amount);
        }
    }
}
