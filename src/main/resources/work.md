## JAVA SE

## 集合框架

* List
    * ArrayList
        * 动态数组
    * LinkedList
        * 双向链表
    * Vector
    * Stack
        * 栈
    * Queue
        * PriorityQueue
            * 二叉堆
        * ArrayDeque
            * 双端队列
* Map
    * HashMap
        * JDK1.8 之前 数组+链表
        * JDK1.8 之后 数组+链表+红黑树
    * TreeMap
        * 红黑树
        * Comparator
    * LinkedHashMap
    * HashTable
    * ConcurrentHashMap
        * JDK1.7 分段锁
        * JDK1.8 CAS + synchronized

## 并发编程

* Thread
    * State
        * NEW
        * RUNNABLE
        * BLOCKED
        * WAITING
            * Object.wait
            * Thread.join
            * LockSupport.park
        * TIMED_WAITING
            * Thread.sleep
            * Object.wait(timeout)
            * Thread.join(timeout)
            * LockSupport.parkNanos
            * LockSupport.parkUntil
        * TERMINATED
    * Interrupt
        * interrupt()
        * isInterrupted()
        * interrupted()

* CAS(Compare And Swap)
    * Unsafe
    * ABA问题
* AQS(AbstractQueuedSynchronizer)
    * State
    * Node

```java
    static final class Node {
    static final Node SHARED = new Node();
    static final Node EXCLUSIVE = null;
    static final int CANCELLED = 1; //  当前节点已取消
    static final int SIGNAL = -1; // 当前线程正在等待锁
    static final int CONDITION = -2; // 条件队列用到

    static final int PROPAGATE = -3; // CountDownLatch 等工具用到

    volatile int waitStatus; // 当前节点状态 默认0


    volatile Node prev; // 前驱节点


    volatile Node next; // 后继节点


    volatile Thread thread; // 当前节点线程

    Node nextWaiter; // 条件队列用到
}
```

* Volatile
    * 内存可见性 JMM

    * 禁止指令重排序 happens-before
* Synchronized
    * 监视器 monitor实现
    * 锁升级
        * 无锁
        * 偏向锁
        * 轻量级锁
        * 重量级锁

| 锁            | 优点             | 缺点            | 场景                |
|:-------------|:---------------|:--------------|:------------------|
| 偏向锁          | 加锁消耗低          | 存在线程竞争会带来额外消耗 | 单线程               |
| 轻量级锁         | 竞争激烈时自旋，提高响应速度 | 线程竞争激烈时自旋消耗大  | 同步代码块执行时间短，追求响应时间 |
| 重量级锁         | 线程竞争不激烈时自旋     | 线程竞争激烈时自旋消耗大  | 同步代码块执行时间长，追求吞吐量  |

* ReentrantLock
* ThreadLocal
    * InheritableThreadLocal
    * ThreadLocalMap
    * WeakReference
    * 内存泄漏
* Executors
    * ThreadPoolExecutor
        * corePoolSize
        * maximumPoolSize
        * keepAliveTime
        * BlockingQueue
            * ArrayBlockingQueue
            * LinkedBlockingQueue
            * SynchronousQueue
            * PriorityBlockingQueue
        * RejectedExecutionHandler
        * ThreadFactory
    * ScheduledThreadPoolExecutor
* ConcurrentHashMap
    * CAS + synchronized
* CountDownLatch
* Atomic

## JVM

* JAVA内存模型
    * JMM
    * 程序计数器
    * 方法区
    * 本地方法栈
    * 虚拟机栈
    * 堆 -Xms 初始化堆内存 -Xmx 最大堆内存 -Xmn 新生代大小 -XX:MaxPermSize 永久代大小
        * 新生代 (Young) -XX:NewRatio:2
            * Eden
            * Survivor -XX:SurvivorRatio:8
                * From
                * To
        * 老年代 (Old)
            - 大对象直接进入老年代 -XX:PretenureSizeThreshold:2M
            - 长期存活对象进入老年代 -XX:MaxTenuringThreshold:15
            - 动态对象年龄判断
        * 元空间 (Metaspace)
* GC
    * 什么是垃圾
        * 引用计数
        * 可达性分析
            * GC Roots
                * 虚拟机栈引用的对象
                * 静态属性引用的对象
                * 方法区常量引用的对象
                * 本地方法栈JNI引用的对象
    * 垃圾回收算法
        * 标记-清除
        * 标记-整理
        * 标记-复制
        * 分代收集
    * 垃圾回收器
        * Serial
        * Serial Old
        * ParNew
        * Parallel Scavenge
        * Parallel Old
        * CMS
            * 初始标记 GC Roots直接可达的对象
            * 并发标记 GC Roots间接可达的对象
            * 重新标记 修正并发标记期间因程序执行导致标记变动的对象 全局停顿
            * 并发清除 与程序并发执行
        * G1
    * Stop The World
        * Minor GC 回收新生代
        * Full GC

## Spring

* IOC

* AOP
    * 动态代理
        * JDK动态代理
        * CGLIB动态代理
    * 切面(Aspect)
    * 通知(Advice)
        * 前置通知 @Before
        * 后置通知 @After
        * 环绕通知 @Around
        * 异常通知 @AfterThrowing
        * 最终通知 @AfterReturning
    * 切点(Pointcut)

## Redis

* 数据结构
    * String
    * List
    * Set
    * Hash
    * ZSet
* 持久化
    * RDB 快照
    * AOF 日志
    * 混合持久化
* 高可用
    * 主从复制
    * 哨兵
    * 集群

* 分布式锁
    * SETNX EXPIRE
    *

## Spring Boot

* 自动配置

## Spring Cloud

## Mybatis

* Configuration
    * Mapper
    * TypeHandler
    * Plugin

## Mysql

* 索引
    * Hash索引
    * B+树索引
    * MYISAM
* 事务
    * 事务隔离级别
* 锁
* 日志
    * redo log 重做日志 用于崩溃恢复
    * undo log 回滚日志 用于事务回滚
    * binlog 二进制日志 用于主从复制
    * slow log 慢查询日志 用于优化查询

## 消息中间件

* RabbitMQ
    * Producer
    * Consumer
    * Queue
* Kafka
    * Producer
    * Consumer
    * Broker
    * Topic
    * Partition
        * Segment
            * Index
            * Log
    * Offset
    * Consumer Group
    * 消息可靠性
        * 生产者
            * acks
            * retries
        * 消费者
            * offset
            * 事务
    * 消息顺序性
        * 生产者
            * key
        * 消费者
            * partition

## 微服务

* Spring Cloud
    * Eureka
    * Ribbon
    * Feign
    * Hystrix
    * Zuul
    * Config
    * Bus
    * Sleuth
    * Zipkin

## 分布式

* CAP(Consistency Availability Partition tolerance)
    * 一致性
    * 可用性
    * 分区容忍性
* BASE理论
    * Basically Available 基本可用
    * Soft state 软状态
    * Eventually consistent 最终一致性
* 分布式事务
    * 两阶段提交2PC
        * 同步阻塞
        * 单点问题
        * 数据不一致
        * 容错性差
    * 三阶段提交
        * 超时机制
    * TCC(Try Confirm Cancel)
    * 本地消息表
    * 最大努力通知
    * 最大努力重试

* 分布式锁
    * mysql 唯一索引
    * redis setnx expire
    * zookeeper 临时最小节点

## 设计模式

* 创建型
    * 单例
    * 工厂
    * 抽象工厂
    * 建造者
    * 原型
* 结构型
    * 适配器
    * 桥接
    * 组合
    * 装饰
    * 外观
    * 享元
    * 代理
* 行为型
    * 模板方法
    * 策略
    * 命令
    * 责任链
    * 状态
    * 观察者

## Linux

## 计算机网络

* 网络分层架构
    * OSI七层模型 应用层 表示层 会话层 传输层 网络层 数据链路层 物理层
    * TCP/IP四层模型 应用层 传输层 网络层 数据链路层
* TCP UDP
    * TCP 三次握手 四次挥手
    * UDP
    * 区别
        * 连接
        * 可靠性
            * 重传
            * 顺序
        * 传输效率
        * 适用场景
        * 首部开销

* HTTP RPC

## Zookeeper

分布式协调服务

单机模式，伪集群模式，集群模式

1. 集群：多个zkServer，每个zkServer称为一个节点，一个leader，多个follower组成一个集群
2. 高可用性：leader挂掉，follower选举新的leader，半数以上节点存活，集群可用
3. 一致性：每个zkServer保存一份数据，数据一致
4. 顺序一致性：来自同一个客户端的请求按照发送顺序依次执行
5. 原子性：请求要么成功要么失败
6. 实时性：客户端能够读取到最新数据

一致性协议：ZAB(Zookeeper Atomic Broadcast)

角色

1. Leader 写请求处理者
2. Follower 写请求转发者，数据同步者，选举权和被选举权
3. Observer 没有投票权，只能同步数据

ZXID 事务ID

全局递增事务ID，64位，高32位是epoch，低32位是xid

1. 每个leader都具有不同的epoch，表示当前leader的任期，每次选举都会增加epoch，epoch越大，leader越新，leader会把自己的epoch广播给follower
2. xid是递增的，每次写操作都会增加xid，xid是全局递增的，每个事务都有唯一的xid

myid文件，每个zkServer都有一个myid文件，里面存放的是当前zkServer的id

历史队列

每个zkServer都有一个历史队列(先进先出 FIFO)，用来存放每次收到的事务请求，保证事务的顺序性

- 可靠提交由ZAB协议保证
- 全局有序由TCP协议保证
- 因果有序由历史队列保证

消息广播模式

1. 消息广播模式
2. 崩溃恢复模式

写流程

1. 客户端向leader发送写请求
2. leader生产事务ID
3. leader将事务ID和写请求广播给follower，将带有zxid作为一个提案(proposal)发送给follower
4. follower收到事务请求写入历史队列,收到提案，写出磁盘成功后，向leader发送ACK
5. leader收到ACK，判断是否超过半数，超过半数则提交事务，并向follower发送commit
6. follower收到commit，判断事务ZXID是否比历史队列的任何ZXID小，如果是则提交事务，否则等待更小的ZXID提交
7. leader将结果返回给客户端

follower接收写请求，不能直接处理，请求转发给leader。

读流程

每个leader, follower, observer都有一个内存数据库，可以直接处理读请求

崩溃恢复

1. 选举：leader挂掉，集群进行选举，选举新的leader
2. 发现：准leader接收所有follower的epoch, 选出最大的epoch，基于此值+1，生成新的epoch，分发给所有follower,
   follower收到新的epoch，更新自己的epoch，返回ack给准leader，带上zxid和历史提议日志，
   leader收到ack，选出最大的zxid，并更新自身历史日志。
3. 同步：准leader将自己的历史日志发送给follower，follower接收到历史日志，超过半数follower同步成功，准leader成为leader，
   follower接收zxid比自己lastzxid大的历史日志
4. 广播: 集群恢复到广播模式

脑裂问题

节点数量 2n+1，过半数节点存活，集群可用，如果过半数节点存活，集群不可用，会出现脑裂问题

选举机制

zkserver状态

1. LOOKING 选举状态
2. FOLLOWING 跟随状态
3. LEADING 领导状态
4. OBSERVING 观察状态

初始化选举

1. 每个zkServer启动时，都会初始化选举
2. 每个zkServer都会向其他zkServer发送选举请求，投票给自己，投票内容 (myid, zxid) zxid=0
3. 每个zkServer都会收到其他zkServer的选举请求，先比较zxid，相同比较myid,投票给myid大的，更改自己的投票内容
4. 投票超过半数，选举成功，成为leader
5. 其他节点自动成为follower

运行时选举

leader崩溃，暂停服务

1. leader挂掉，follower发现leader挂掉，进入LOOKING状态，发起新一轮选举，每个server投票给自己(myid, zxid)
2. 收集各个server的投票
3. 处理投票，处理逻辑，优先比较zxid，再比较myid
4. 统计投票，超过半数，选举成功，成为leader
5. 改变服务器状态 LOOKING -> LEADING -> FOLLOWING
6. 依次进入发现，同步，广播阶段

数据模型

1. 数据节点 ZNode， 唯一路径标识，数据上限1M

    - 持久节点 PERSISTENT 客户端断开连接，节点不会消失
    - 持久化顺序节点 PERSISTENT_SEQUENTIAL 客户端断开连接，节点不会消失，节点名称自动增加
    - 临时节点 EPHEMERAL 客户端断开连接，节点消失
    - 临时顺序节点 EPHEMERAL_SEQUENTIAL 客户端断开连接，节点消失，节点名称自动增加
2. stat 状态信息

    - czxid 创建节点事务ID
    - mzxid 最后修改节点事务ID
    - ctime 创建时间
    - mtime 最后修改时间

监听机制

Watcher 事件监听器, 客户端注册监听器，当符合条件时，触发监听器，发送监听事件通知，客户端收到通知，处理监听事件

1. 事件类型
    - NodeCreated 节点创建
    - NodeDeleted 节点删除
    - NodeDataChanged 节点数据改变
    - NodeChildrenChanged 子节点改变

2. 监听通知机制流程

    - 客户端注册监听器 connect listen
    - zkServer注册监听器
    - zkServer监听到事件，发送通知给客户端
    - 客户端收到通知，处理监听事件

会话机制

ClientCnxn 客户端连接，客户端与zkServer建立连接，发送心跳包，保持连接

SendThread 维护客户端与zkServer的连接，负责将服务端的请求传送给EventThread
EventThread 负责事件处理，触发客户端注册的监听器

应用场景

1. 分布式锁
2. 数据发布/订阅
3. 统一配置管理
4. 集群管理
5. 负载均衡
6. 命名服务 dubbo

## Netty

高性能通信框架

[Netty讲解](https://www.cnblogs.com/mic112/p/15527384.html)

* EventLoopGroup
    * NioEventLoopGroup
        * Boss
        * Worker
* EventLoop
* Selector
* Channel
    * NioSocketChannel
* ChannelPipeline
* ChannelHandler

- 提供了高效的I/O模型、线程模型和时间处理机制
- 提供了非常简单易用的API，相比NIO来说，针对基础的Channel、Selector、Sockets、Buffers等api提供了更高层次的封装，屏蔽了NIO的复杂性
- 对数据协议和序列化提供了很好的支持
- 稳定性，Netty修复了JDK NIO较多的问题，比如select空转导致的cpu消耗100%、TCP断线重连、keep-alive检测等问题。
- 可扩展性在同类型的框架中都是做的非常好的，比如一个是可定制化的线程模型，用户可以在启动参数中选择Reactor模型、 可扩展的事件驱动模型，将业务和框架的关注点分离。
- 性能层面的优化，作为网络通信框架，需要处理大量的网络请求，必然就面临网络对象需要创建和销毁的问题，这种对JVM的GC来说不是很友好，为了降低JVM垃圾回收的压力，引入了两种优化机制
    - 对象池复用
    - 零拷贝技术

## Elasticsearch Solr

* 倒排索引

## Docker

## Nginx

* 负载均衡
    * 轮询
    * 加权轮询
    * IP哈希
    * fair(三方插件)
    * URL哈希(三方插件)
* 反向代理
* 静态资源服务器

## Maven

## Git

## Tomcat Jetty

## Hadoop Spark Flink Storm Kafka Flume Hive HBase MapReduce

## Dubbo

* 服务注册
* 服务发现
* 负载均衡
    * weighted random 加权随机
    * round robin 加权轮询
    * least active 最小活跃数
    * consistent hash 一致性哈希
    * shortest response 最短响应时间
* 泛化调用 GenericService

泛化调用（客户端泛化调用）是指在调用方没有服务方提供的 API（SDK）的情况下，对服务方进行调用，并且可以正常拿到调用结果。

* GenericFilter
* GenericImplFilter
* 反射