# LitePalDome
LitePal 2.0  练习dome

#### 序言闲着没事的可以看一下，有事就算了

首先我之前很少做数据库说出来可能丢人，你一个安卓的最起码多少懂点吧，对我就占了那一个点多少就算了。

现在悔不及过往，思量一番还是决定把数据库给学了吧，但是原生太复杂，我可以懂但是真的懒得去写那么多那么繁琐呀，所以我真是一个懒人

然后我就发现了我郭神推荐的一款数据库框架"LitePal",但是我看完以后却发现郭神写的很详细然后使用的方法信心大满在实践的时候发现LitePal已经更新了

对，没错是跟新了，方法大多已经过时，郭神又细心的出了新版，而且附了Kotlin中使用的方法，所以我也心怀忐忑的写一个dome。对，我不止懒还是一个啰嗦的人

### 环境的搭建

基本的配置基本上在网上随便一查就是一大堆就是简单的说明一下，毕竟码字还是蛮累的

1. 基本的添加依赖  implementation 'org.litepal.android:core:2.0.0'

2. 配置litepal.xml，在项目的assets目录下面新建一个litepal.xml文件，并将以下代码拷贝进去

        
        <?xml version="1.0" encoding="utf-8"?>
        <litepal>
            <dbname value="你创建的数据库名字" ></dbname>
            <version value="数据库版本号" ></version>
            <list>
               //你创建的表格表格要详细地址
                <mapping class="dome.test.litepaldome.model.UserSql"></mapping>
            </list>
        </litepal>

3. 配置LitePalApplication

          <manifest>
            <application
                android:name="org.litepal.LitePalApplication"
                ...
            >
            ...
         </application>
 
有可能你的程序中有自己配置的Application 也可以这样配置

        <manifest>
            <application
                android:name="com.example.MyApplication"
                ...
            >
            ...
            </application>
        </manifest>

只需要让你的Application继承LitePalApplication就可以了

        public class MyApplication extends LitePalApplication {
            ...
        }
        
这样基本配置结束了可以配置自己需要的表了，表的创建亦是非常简单（这个bean类是要继承LitePalSupport，这样你才可以对表进行增删改查）


        public class UserSql extends LitePalSupport{
        	
        	private int id;
        	
        	private String account;
        	
        	private String password;
        	
        	private String name;
        	
        	// 自动生成get、set方法
        	...
        }

        
### 数据库查询

查询表内所有数据

    List<UserSql> allSongs = LitePal.findAll(UserSql.class);
    
查询单个数据（值得注意的是，查询全部的时候，如果表没有创建或者表内返回的都是一个长度为零的集合，但是按照id单个查询没有数据返回null所以记得要加判断）

    UserSql song = LitePal.find(UserSql.class, Integer.parseInt(id));
    
另外还可以做一些模糊查询，具体需要什么要按照自己的喜好使用

    List<Song> songs = LitePal.where("name like ? and duration < ?", "song%", "200").order("duration").find(Song.class);
    
    
### 数据添加
    
数据的存储直接调用save()或者saveThrows()，这里看自己的需求，saveThrows()是抛异常的存储如果出错可以自己捕捉异常
save()的返回值是Boolean类型的可以根据返回值判断是否存储成功
         
         UserSql userSql = new UserSql();
         userSql.setAccount(account);
         userSql.setId(Integer.parseInt(id));
         userSql.setPassword(password);
         userSql.setName(name); 
         //抛异常存储
         //userSql.saveThrows();
         if (userSql.save()) {
             toas("添加成功");
         } else {
              toas("添加失败");
         }
         
### 数据修改

根据id修改 
     
    UserSql userSql = new UserSql();
    userSql.setAccount(account);
    userSql.setPassword(password);
    userSql.setName(name);
    userSql.update(Integer.parseInt(id));
    
直接调用save()覆盖修改

         UserSql userSql = new UserSql();
         userSql.setAccount(account);
         userSql.setId(Integer.parseInt(id));
         userSql.setPassword(password);
         userSql.setName(name); 
         if (userSql.save()) {
             toas("修改成功");
         } else {
              toas("修改失败");
         }

或者直接通过条件范围修改

        UserSql userSql = new UserSql();
        userSql.setAccount(account);
        userSql.updateAll("name = ?", "album");
        
        
### 数据的删除

根据id直接删除单条数据

     LitePal.delete(UserSql.class, Integer.parseInt(id));
     
根据条件范围删除 

    LitePal.deleteAll(UserSql.class, "id > ?", "350");
    
    
以上就是怎删改查其实还有好多细枝末节都没有讲到，比如修改表结构后数据库升级，多表关联等等等，这些可以从郭神的博客上找到，我也不一一细讲，将我郭神的博客抛出

郭神博客数据库篇
https://blog.csdn.net/guolin_blog/article/category/2522725