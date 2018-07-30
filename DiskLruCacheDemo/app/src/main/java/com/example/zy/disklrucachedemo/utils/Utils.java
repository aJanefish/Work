package com.example.zy.disklrucachedemo.utils;

public class Utils {
	//方法	备注
	//DiskLruCache open(File directory, int appVersion, int valueCount, long maxSize)	打开一个缓存目录，如果没有则首先创建它，directory：指定数据缓存地址 appVersion：APP版本号，当版本号改变时，缓存数据会被清除 valueCount：同一个key可以对应多少文件 maxSize：最大可以缓存的数据量
	//Editor edit(String key)	通过key可以获得一个DiskLruCache.Editor，通过Editor可以得到一个输出流，进而缓存到本地存储上
	//void flush()	强制缓冲文件保存到文件系统
	//Snapshot get(String key)	通过key值来获得一个Snapshot，如果Snapshot存在，则移动到LRU队列的头部来，通过Snapshot可以得到一个输入流InputStream
	//long size()	缓存数据的大小，单位是byte
	//boolean remove(String key)	根据key值来删除对应的数据，如果该数据正在被编辑，则不能删除
	//void delete()	关闭缓存并且删除目录下所有的缓存数据，即使有的数据不是由DiskLruCache 缓存到本目录的
	//void close()	关闭DiskLruCache，缓存数据会保留在外存中
	//boolean isClosed()	判断DiskLruCache是否关闭，返回true表示已关闭
	//File getDirectory()	缓存数据的目录

}
