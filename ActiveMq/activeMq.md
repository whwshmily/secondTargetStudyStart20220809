MQ:异步 解耦 削峰
activeMQ角色: borker(一个服务),producer,consumer
先根据工厂拿到连接--->获取session--->创建queue/topic--->创建producer/consumer--->send/receive
ActiveMQConnectionFactory---->Connection---->Queue---->producer/consumer---->send/receive

queue/topic实现Destination
Destination确定queue或topic 消息的目的地
Queue 支持存在多个消费者，但是对一个消息而言，只会有一个消费者可以消费、其它 的则不能消费此消息了。 当消费者不存在时，消息会一直保存，直到有消费消费
topic:基于订阅/发布的消息模型 会给所有的消费者(基于当前的消费者，发送消费消息后连接的消费者获得不到)

JMS定义的消息类型有TextMessage、MapMessage、BytesMessage、StreamMessage和ObjectMessage。

只有在被确认之后，才认为已经被成功地消费了。

消息的成功消费通常包含三个阶段：客户接收消息、客户处理消息和消息被确认。

- Session.AUTO_ACKNOWLEDGE。当客户成功的从receive方法返回的时候，或者从MessageListener.onMessage方法成功返回的时候，会话自动确认客户收到的消息。
- Session.CLIENT_ACKNOWLEDGE。客户通过消息的acknowledge方法确认消息。需要注意的是，在这种模式中，确认是在会话层上进行：确认一个被消费的消息将自动确认所有已被会话消费的消息。例如，如果一个消息消费者消费了10个消息，然后确认第5个消息，那么所有10个消息都被确认。
- Session.DUPS_ACKNOWLEDGE。该选择只是会话迟钝的确认消息的提交。如果JMS Provider失败，那么可能会导致一些重复的消息。如果是重复的消息，那么JMS Provider必须把消息头的JMSRedelivered字段设置为true。

可以使用消息优先级来指示JMS Provider首先提交紧急的消息。
优先级分10个级别，从0（最低）到9（最高）。如果不指定优先级，默认级别是4。
需要注意的是，JMS Provider并不一定保证按照优先级的顺序提交消息。

可以设置消息在一定时间后过期，默认是永不过期。

KahaDB存储 默认存储
可以更换数据库:换成mysql
activemq.xml中加这些配置
<persistenceAdapter>
           <!-- <kahaDB directory="${activemq.data}/kahadb"/> -->
		<jdbcPersistenceAdapter dataSource="#mysql-ds" createTablesOnStartup="true" />
</persistenceAdapter>
<bean id="mysql-ds" class="org.apache.commons.dbcp.BasicDataSource" destroy-method="close">

<property name="driverClassName" value="com.mysql.jdbc.Driver"/>
<property name="url" value="jdbc:mysql://localhost/activemq?relaxAutoCommit=true"/>
<property name="username" value="activemq"/>
<property name="password" value="activemq"/>
<property name="maxActive" value="200"/>
<property name="poolPreparedStatements" value="true"/>

</bean>

然后会在库中创建三张表
**activemq_acks**：用于存储订阅关系。如果是持久化Topic，订阅者和服务器的订阅关系在这个表保存。
**activemq_lock**：在集群环境中才有用，只有一个Broker可以获得消息，称为Master Broker，
其他的只能作为备份等待Master Broker不可用，才可能成为下一个Master Broker。
这个表用于记录哪个Broker是当前的Master Broker。
**activemq_msgs**：用于存储消息，Queue和Topic都存储在这个表中。

创建工厂时用户和密码需要加这个配置
<plugins>
      <simpleAuthenticationPlugin>
          <users>
              <authenticationUser username="admin" password="admin" groups="admins,publishers,consumers"/>
              <authenticationUser username="publisher" password="publisher"  groups="publishers,consumers"/>
              <authenticationUser username="consumer" password="consumer" groups="consumers"/>
              <authenticationUser username="guest" password="guest"  groups="guests"/>
          </users>
      </simpleAuthenticationPlugin>
 </plugins>

 更改界面密码需要改配置文件jetty-realm.properties

只对于消费者，对于生产者没用
 Session.AUTO_ACKNOWLEDGE
 当客户端从receiver或onMessage成功返回时，Session自动签收客户端的这条消息的收条。

 Session.CLIENT_ACKNOWLEDGE
 客户端通过调用消息(Message)的acknowledge方法签收消息。在这种情况下，
 签收发生在Session层面：签收一个已经消费的消息会自动地签收这个Session所有已消费的收条。
 这个消息在这个session没有销毁时中没有调用acknowledge这个方法当前这个消息不会推给别的消费者
 调用这个方法acknowledge表示已经消费完成，会将持久化的消息删除，不会推给别的消费者
 当你没有调用acknowledge而session又已经销毁时就会推给别的消费者

Session.DUPS_OK_ACKNOWLEDGE
Session不必确保对传送消息的签收，这个模式可能会引起消息的重复，但是降低了Session的开销，
所以只有客户端能容忍重复的消息，才可使用。

使用优先级时增加配置，否则不生效
<policyEntry queue="queue1" prioritizedMessages="true" />

创建session时如果事务选择true，第二个参数是SESSION_TRANSACTED = 0;
 return new ActiveMQSession(this, this.getNextSessionId(), transacted ? 0 : acknowledgeMode, this.isDispatchAsync(), this.isAlwaysSessionAsync());
有事务一定要调用session.commit();否则消息发送不出去

消息可以设置超时时间，过了超时时间会进入死信队列ActiveMQ.DLQ 持久化的消息才会进入
非持久化的消息超时也不会进入
消费消息过程中异常的消息也会进入死信队列(一个消息被消费6次)
消费死信队列的消息报错多次也会从死信队列移除

进入死信队列的消息可以从死信队列ActiveMQ.DLQ 消费

一个队列进入指定的死信队列
<policyEntry queue="f" prioritizedMessages="true" >
    <deadLetterStrategy>
        <individualDeadLetterStrategy   queuePrefix="DLxxQ." useQueueForQueueMessages="true" />
    </deadLetterStrategy>
</policyEntry>

过期消息不进死信队列
<individualDeadLetterStrategy   processExpired="false"  />

非持久化的消息进入死信队列
processNonPersistent="true"

### 独占消费者
Queue queue = session.createQueue("user?consumer.exclusive=true");
还可以设置优先级
Queue queue = session.createQueue("user?consumer.exclusive=true&consumer.priority=10");
priority值越大优先级越高，设置了独占会比其他没有设置的优先级高，会独占
当独占都掉线后其他没设置的会一起消费



消息发送同步还是异步
### 同步与异步

|          | 开启事务 | 关闭事务 |
| -------- | -------- | -------- |
| 持久化   | 异步     | 同步     |
| 非持久化 | 异步     | 异步     |
生产者向broker发送消息，先写内存，后持久化
先写内存后写入文件等待时间(设置)后持久化
在这个等待时间的过程中如果被消费者消费就不用持久化了，直接写入文件。被消费后直接从文件中删除
然后在持久化提高性能

消息堆积，防止消息堆积 超过这个值就不能再次发送消息
producer每发送一个消息，统计一下发送的字节数，当字节数达到ProducerWindowSize值时，需要等待broker的确认，才能继续发送。
brokerUrl中设置: `tcp://localhost:61616?jms.producerWindowSize=1048576`
destinationUri中设置: `myQueue?producer.windowSize=1048576`

延迟消息发送
开启配置:
 <broker xmlns="http://activemq.apache.org/schema/core" brokerName="localhost" dataDirectory="${activemq.data}" schedulerSupport="true">
--schedulerSupport="true"

//延迟10秒 delay
message.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY,10*1000);
带间隔的重复发送
发送消息间隔时间
message.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_PERIOD,2*1000);
发送几次 一定要用setIntProperty 其余不生效
message.setIntProperty(ScheduledMessage.AMQ_SCHEDULED_REPEAT,9);

消息的筛选
MessageConsumer consumer = session.createConsumer(user,"i=5");
筛出消息头中含有I=5的消息
消息分为消息头和消息体
message.setIntProperty("i",i); //在消息头里面放值