package ru.mongo.test.mongodbtest.sandbox;

public class MainClass {
    public static void main(String[] args) {

        var i = 10;

        var nums = new int[12];
        nums[3] = i;

        System.out.println(i);
        System.out.println(nums.length);
        System.out.println(nums[2] + " " + nums[3]);
    }
}
