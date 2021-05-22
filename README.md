# Easy4Form
Easy4Form是smartcmd写的一个十分简单的外部Form库

使用Easy4Form，你可以十分方便的写出Form交互功能

要想使用它，请在你的pom中加入以下配置:
```xml
<repositories>
    <repository>
        <id>ck</id>
        <url>http://finalgame.cn:1000/repository/cookiestudio/</url>
    </repository>
</repositories>

<dependencies>
<dependency>
    <groupId>cn.cookiestudio</groupId>
    <artifactId>Easy4Form</artifactId>
    <version>1.0.0</version>
    <scope>provided</scope>
</dependency>
</dependencies>
```

并保证你的服务器中有加载Easy4Form插件(可以自行构建,github坐标在下面...

1.EZ4F包含的类:
![images/img_1.png](images/img_1.png)
怎么样，是不是很简单?

2.使用
我们以simpleForm作为栗子:
```java
BFormWindowSimple bf = new BFormWindowSimple();
bf.addButton(...)
...
        
...
bf.setResponseAction((e) ->{//e是此Form的ResponseEvent对象(ez4f会保证此event是”这个“form的返回事件)
    ...//这里不需要判空，ez4f会代替你处理这些细节
});
bf.sendToPlayer(player);//注意这里
```

搭配lambda表达式，是不是很方便？
使用ez4f，你可以省去创建监听器，匹配formid等十分麻烦的步骤(特别是匹配formid,这一步非常爽)

顺便补一句，如果你的maven的nk依赖从官方下不下来,我的仓库里面也是有的哦qwq
