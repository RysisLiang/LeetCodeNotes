# 二分查找

## 如何识别二分查找？

如前所述，二分查找是一种在每次比较之后将查找空间一分为二的算法。每次需要查找集合中的索引或元素时，都应该考虑二分查找。如果集合是无序的，我们可以总是在应用二分查找之前先对其进行排序。

## 成功的二分查找的 3 个部分
   
二分查找一般由三个主要部分组成：
    
- 预处理 —— 如果集合未排序，则进行排序。
- 二分查找 —— 使用循环或递归在每次比较后将查找空间划分为两半。
- 后处理 —— 在剩余空间中确定可行的候选者。


## 模板

所以有时候为了简化二分法的处理，人们总结出了一些模板

#### 模板1

这是最普通的模板，用户快速定位某个元素在这个有序序列中的位置。

```
int binarySearch(int[] nums, int target){
  if(nums == null || nums.length == 0)
    return -1;

  int left = 0, right = nums.length - 1;
  while(left <= right){
    // Prevent (left + right) overflow
    // jdk: (left + right) >>> 1 这里使用无符号右移操作能相对的增加效率
    int mid = left + (right - left) / 2;
    if(nums[mid] == target){ return mid; }
    else if(nums[mid] < target) { left = mid + 1; }
    else { right = mid - 1; }
  }

  // End Condition: left > right
  return -1;
}
```

关键属性

- 二分查找的最基础和最基本的形式。
- 查找条件可以在不与元素的两侧进行比较的情况下确定（或使用它周围的特定元素）。
- 不需要后处理，因为每一步中，你都在检查是否找到了元素。如果到达末尾，则知道未找到该元素。

区分语法

- 初始条件：left = 0, right = length-1
- 终止：left > right
- 向左查找：right = mid-1
- 向右查找：left = mid+1

#### 模板2

```
int binarySearch(int[] nums, int target){
  if(nums == null || nums.length == 0)
    return -1;

  int left = 0, right = nums.length;
  while(left < right){
    // Prevent (left + right) overflow
    int mid = left + (right - left) / 2;
    if(nums[mid] == target){ return mid; }
    else if(nums[mid] < target) { left = mid + 1; }
    else { right = mid; }
  }

  // Post-processing:
  // End Condition: left == right
  if(left != nums.length && nums[left] == target) return left;
  return -1;
}
```

是二分查找的高级模板。它用于查找需要访问数组中当前索引及其直接右邻居索引的元素或条件。

关键属性

- 一种实现二分查找的高级方法。
- 查找条件需要访问元素的直接右邻居。
- 使用元素的右邻居来确定是否满足条件，并决定是向左还是向右。
- 保证查找空间在每一步中至少有 2 个元素。
- 需要进行后处理。 当你剩下 1 个元素时，循环 / 递归结束。 需要评估剩余元素是否符合条件。


区分语法

- 初始条件：left = 0, right = length
- 终止：left == right
- 向左查找：right = mid
- 向右查找：left = mid+1
