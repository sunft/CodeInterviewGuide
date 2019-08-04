package com.sunft.chapter01_stack_queue._006;

/**
 * 定义枚举类，表示移动的方式
 * NO:不用移动
 * LToM:从左边移到中间
 * MToL:从中间移到左边
 * MToR:从中间移到右边
 * RToM:从右边移到中间
 */
public enum Action {
    NO, LToM, MToL, MToR, RToM
}
