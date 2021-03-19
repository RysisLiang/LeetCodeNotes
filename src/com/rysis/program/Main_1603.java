package com.rysis.program;

import com.rysis.util.ArrayUtil;

/**
 * Main_1603
 * 设计停车系统
 *
 * @author kunda-liang
 * @version 1.00
 * @date 2021/3/19 23:09
 */
public class Main_1603 {

    public static void main(String[] args) {
        String s = "[1,1,0]";
        String s2 = "[[1],[2],[3],[1]]";

        int[] init = ArrayUtil.handleToIntArray(s);
        ParkingSystem parkingSystem = new ParkingSystem(init[0], init[1], init[2]);
        for (int[] i : ArrayUtil.handleToNestedIntArray(s2)) {
            System.out.println(parkingSystem.addCar(i[0]));
        }
    }

    static class ParkingSystem {

        private final int[] park = new int[3];

        public ParkingSystem(int big, int medium, int small) {
            park[0] = big;
            park[1] = medium;
            park[2] = small;
        }

        public boolean addCar(int carType) {
            if (park[carType - 1] > 0) {
                park[carType - 1]--;
                return true;
            } else {
                return false;
            }
        }
    }
}
