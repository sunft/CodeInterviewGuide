package com.sunft.chapter01_stack_queue._004.v1;

import com.sunft.chapter01_stack_queue._004.CatAndDogBase;
import com.sunft.chapter01_stack_queue._004.base.Cat;
import com.sunft.chapter01_stack_queue._004.base.Dog;
import com.sunft.chapter01_stack_queue._004.base.Pet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 使用LinkedList实现：自己的思路
 */
public class CatAndDogQueue01<E extends Pet> implements CatAndDogBase<E> {

    private LinkedList<E> linkedList;

    public CatAndDogQueue01() {
        linkedList = new LinkedList<>();
    }

    @Override
    public void add(E e) {
        linkedList.addLast(e);//注意使用尾插法插入
    }

    /**
     * 直接打印ArrayList即可
     * @return
     */
    @Override
    public List<E> pollAll() {
        //没有数据直接返回空
        if (linkedList.isEmpty()) {
            return Collections.emptyList();
        }

        List<E> list = new ArrayList<>(linkedList);
        linkedList.clear();
        return list;
    }

    /**
     * 返回所有狗的实例
     * @return
     */
    @Override
    public List<E> pollDog() {
        if (linkedList.isEmpty()) {
            return Collections.emptyList();
        }

        List<E> list = new ArrayList<>();
        for (E e : linkedList) {
            if (e instanceof Dog)  {
                list.add(e);
                linkedList.remove(e);
            }
        }

        return list;
    }

    /**
     * 弹出所有猫的实例
     * @return
     */
    @Override
    public List<E> pollCat() {
        if (linkedList.isEmpty()) {
            return Collections.emptyList();
        }

        List<E> list = new ArrayList<>();
        for (E e : linkedList) {
            if (e instanceof Cat)  {
                list.add(e);
                linkedList.remove(e);
            }
        }

        return list;
    }

    /**
     * 判断是否为空
     * @return
     */
    @Override
    public boolean isEmpty() {
        return linkedList.isEmpty();
    }

    /**
     * 判断是否有狗的实例
     * @return
     */
    @Override
    public boolean isDogEmpty() {
        for (E e : linkedList) {
            if (e instanceof Dog) {
                return true;
            }
        }

        return false;
    }

    /**
     * 判断是否有猫的实例
     * @return
     */
    @Override
    public boolean isCatEmpty() {

        for (E e : linkedList) {
            if (e instanceof Cat) {
                return true;
            }
        }

        return false;
    }
}
