# LitePalDome
LitePal 2.0  练习dome

#### 序言闲着没事的可以看一下，有事就算了

首先我之前很少做数据库说出来可能丢人，你一个安卓的最起码多少懂点吧，对我就占了那一个点多少就算了。

现在悔不及过往，思量一番还是决定把数据库给学了吧，但是原生太复杂，我可以懂但是真的懒得去写那么多那么繁琐呀，所以我真是一个懒人

然后我就发现了我郭神推荐的一款数据库框架"LitePal",但是我看完以后却发现郭神写的很详细然后使用的方法信心大满在实践的时候发现LitePal已经更新了

对，没错是跟新了，方法大多已经过时，郭神又细心的出了新版，而且附了Kotlin中使用的方法，所以我也心怀忐忑的写一个dome。对，我不止懒还是一个啰嗦的人



## 数据库查询

查询表内所有数据

    List<UserSql> allSongs = LitePal.findAll(UserSql.class);
    
查询单个数据（值得注意的是，查询全部的时候，如果表没有创建或者表内返回的都是一个长度为零的集合，但是按照id单个查询没有数据返回null所以记得要加判断）

    UserSql song = LitePal.find(UserSql.class, Integer.parseInt(id));
    
另外还可以做一些模糊查询，具体需要什么要按照自己的喜好使用

    List<Song> songs = LitePal.where("name like ? and duration < ?", "song%", "200").order("duration").find(Song.class);