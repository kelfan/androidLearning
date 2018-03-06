# general ways
- activity --> fragment:
    - 在activity中创建bundle数据包，并调用fragment的setArguments（bundle bundle）方法
- fragment --> activity：
    - 需要在fragment中定义一个内部回调接口，再让包含该fragment的activity实现该回调接口。fragment调用方法传递给activity

# 方法
1. getActivity 获取所在activity
1. FragmentManager的findFragmentById 或 findFragmentByTag 获取Fragment