package com.sunft.chapter01_stack_queue._004.v2;

import com.sunft.chapter01_stack_queue._004.base.Pet;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 课本中的实现方式
 *
 */
public class CatAndDogQueue02 {

    private Queue<PetEnterQueue> dogQ;//保存狗的实例
    private Queue<PetEnterQueue> catQ;//保存猫的实例
    private long count;//用于存储总的数目

    public CatAndDogQueue02() {
        dogQ = new LinkedList<>();
        catQ = new LinkedList<>();
        this.count = 0;
    }

    /**
     * 注意：LinkedList中的add采用的是尾插法
     * @param pet
     */
    public void add(Pet pet) {
        if ("dog".equals(pet.getPetType())) {
            dogQ.add(new PetEnterQueue(pet, this.count ++));
        } else if ("cat".equals(pet.getPetType())) {
            catQ.add(new PetEnterQueue(pet, this.count ++));
        } else {
            throw new RuntimeException("err, not dog or car");
        }
    }

    /**
     * 所有Pet对象出来
     * 注意：LinkedList中的poll会删除第一个元素
     * @return
     */
    public Pet pollAll() {
        if (!dogQ.isEmpty() && !catQ.isEmpty()) {
            if (dogQ.peek().getCount() < catQ.peek().getCount()) {
                return dogQ.poll().getPet();
            } else {
                return catQ.poll().getPet();
            }
        } else if (!dogQ.isEmpty()) {
            return dogQ.poll().getPet();
        } else if ((!catQ.isEmpty())) {
            return catQ.poll().getPet();
        } else {
            throw new RuntimeException("err, the Queue is Empty.");
        }
    }

    /**
     * 狗出队列
     * @return
     */
    public Pet pollDog() {
        if (isDogQueueEmpty()) {
            throw new RuntimeException("err, the Queue is Empty.");
        }
        return dogQ.poll().getPet();
    }

    /**
     * 猫出队列
     * @return
     */
    public Pet pollCat() {
        if (isCatQueueEmpty()) {
            throw new RuntimeException("");
        }
        return catQ.poll().getPet();
    }

    /**
     * 判断队列是否为空
     * @return
     */
    public boolean isEmpty() {
        return dogQ.isEmpty() && catQ.isEmpty();
    }

    /**
     * 判断狗队列是否为空
     * @return
     */
    public boolean isDogQueueEmpty() {
        return dogQ.isEmpty();
    }

    /**
     * 判断猫队列是否为空
     * @return
     */
    public boolean isCatQueueEmpty() {
        return catQ.isEmpty();
    }
}
