package com.rysis.queue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Main_341
 * 扁平化嵌套列表迭代器
 *
 * @author kunda-liang
 * @version 1.00
 * @date 2021/3/23 22:44
 */
public class Main_341 {

    // 真正的迭代器。只有next才会推动
    static class NestedIterator implements Iterator<Integer> {
        // 维护嵌套的列表
        private final LinkedList<List<NestedInteger>> itemLt = new LinkedList<>();
        // 维护嵌套时的索引
        private final LinkedList<Integer> indexLt = new LinkedList<>();
        // 记录next的返回值
        private Integer nextValue;

        public NestedIterator(List<NestedInteger> nestedList) {
            itemLt.push(nestedList);
            indexLt.push(0);
        }

        @Override
        public Integer next() {
            moveNextHandler(true);
            return nextValue;
        }

        @Override
        public boolean hasNext() {
            return moveNextHandler(false);
        }


        private boolean moveNextHandler(boolean checkNext) {
            if (indexLt.isEmpty()) {
                nextValue = null;
                return false;
            }
            // 取出当前的遍历的列表和索引
            List<NestedInteger> peekList = itemLt.peek();
            Integer peekIndex = indexLt.peek();
            // 如果当前列表索引，超出列表范围时，当前列表结束遍历，去找父类列表的下一个元素
            if (peekIndex >= peekList.size()) {
                // 当前列表和索引抛出
                itemLt.pop();
                indexLt.pop();
                if (indexLt.isEmpty()) {
                    nextValue = null;
                    return false;
                }
                // 上一层的列表的索引+1
                indexLt.push(indexLt.pop() + 1);
                return moveNextHandler(checkNext);
            }

            // 列表元素
            NestedInteger item = peekList.get(peekIndex);
            if (item.isInteger()) { // 数字。记录数字并结束
                nextValue = item.getInteger();
                indexLt.push(indexLt.pop() + (checkNext ? 1 : 0)); // 当前列表的索引+1
                return true;
            } else { // 列表。上层列表放入队列中
                itemLt.push(item.getList());
                indexLt.push(0);
                return moveNextHandler(checkNext);
            }
        }
    }

    // 直接扁平化数组了
    public class NestedIterator1 implements Iterator<Integer> {
        private final List<Integer> list = new ArrayList<>(); // 用于暂存遍历的结果序列
        private int index; // 指针

        public NestedIterator1(List<NestedInteger> nestedList) {
            init(nestedList);
            index = 0;
        }

        private void init(List<NestedInteger> nestedList) {
            for (NestedInteger nestedInteger : nestedList) {
                if (nestedInteger.isInteger()) {
                    list.add(nestedInteger.getInteger());
                } else {
                    init(nestedInteger.getList());
                }
            }
        }

        @Override
        public Integer next() {
            return list.get(index++);
        }

        @Override
        public boolean hasNext() {
            return list.size() > index;
        }
    }
}


// This is the interface that allows for creating nested lists.
// You should not implement it, or speculate about its implementation
interface NestedInteger {

    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    public boolean isInteger();

    // @return the single integer that this NestedInteger holds, if it holds a single integer
    // Return null if this NestedInteger holds a nested list
    public Integer getInteger();

    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return null if this NestedInteger holds a single integer
    public List<NestedInteger> getList();
}

