package com.sunft.chapter01_stack_queue._004.v2;

import com.sunft.chapter01_stack_queue._004.base.Pet;

/**
 * 采用时间戳的方式实现
 */
public class PetEnterQueue {

    private Pet pet;//用户原有的实例
    private long count;//该实例的时间戳

    public PetEnterQueue(Pet pet, long count) {
        this.pet = pet;
        this.count = count;
    }

    public Pet getPet() {
        return this.pet;
    }

    public long getCount() {
        return this.count;
    }

    /**
     * 获取宠物类型
     * @return
     */
    public String getEnterPetType() {
        return this.pet.getPetType();
    }

}
