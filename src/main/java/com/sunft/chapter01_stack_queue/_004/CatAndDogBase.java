package com.sunft.chapter01_stack_queue._004;

import com.sunft.chapter01_stack_queue._004.base.Pet;

import java.util.List;

/**
 * 基础接口
 * @param <E>
 */
public interface CatAndDogBase<E extends Pet> {

    /**
     * 将Cat类或dog类的实例放入队列中
     * @param e
     */
    void add(E e);

    /**
     * 将队列中所有的实例按照进队列的先后顺序依次弹出
     * @return
     */
    List<E> pollAll();

    /**
     * 将队列中Dog的实例按照进队列的先后顺序依次弹出
     * @return
     */
    List<E> pollDog();

    /**
     * 将队列中Dog的实例按照进队列的先后顺序依次弹出
     * @return
     */
    List<E> pollCat();

    /**
     * 检查队列中是否还有Dog或Cat类的实例
     * @return
     */
    boolean isEmpty();

    /**
     * 检查队列中是否有dog类的实例
     * @return
     */
    boolean isDogEmpty();

    /**
     * 检查队列中是否有Cat类的实例
     * @return
     */
    boolean isCatEmpty();
}