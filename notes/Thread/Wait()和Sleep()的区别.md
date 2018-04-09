# Wait()和Sleep()的区别

### 来源

* wait() 方法来自于Object类
* sleep() 方法来自于Thread类

### 使用

wait() 在停住线程的时候会释放锁，但是sleep() 方法却会一直紧紧抱住锁。