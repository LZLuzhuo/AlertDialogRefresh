# 简介
> 这是Blog中 "论__AlertDialog自定义布局回调修改的正确方式" 的文章的案例代码.

## 结论
> - 一定要在辅助线程里执行的回调才能修改Dialog的界面; (第1,2的案例)
> - 在ui线程里执行的回调不能修改Dialog的界面.(准确的说:能修改,但是不是你想要的效果) (第3,4的案例)

## 详情
> [请看BLog文章](http://blog.csdn.net/Rozol/article/details/50441057)