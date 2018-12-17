# -*- coding: UTF-8 -*-

li = ["a", "b", "mpilgrim", "z", "example"]
print li
print li[1]

li.append("new")
print li

li.insert(2, "new")
print li

li.extend(["two", "elements"])
print li

print li.index("example")
print "c" in li
print "example" in li

li.remove("a")
print li

li.remove("new")  # 删除首次出现的一个值
print li
print li.pop()  # pop 会做两件事: 删除 list 的最后一个元素, 然后返回删除元素的值。
print li

li = li + ['example', 'new']
print li
li += ['two']
print li

li = [1, 2] * 3
print li

params = {"server": "mpilgrim", "database": "master", "uid": "sa", "pwd": "secret"}
print ["%s=%s" % (k, v) for k, v in params.items()]

print ";<-->;".join(["%s=%s" % (k, v) for k, v in params.items()])

li = ['server=mpilgrim', 'uid=sa', 'database=master', 'pwd=secret']

print li
s = ";".join(li)

print s

print s.split(";")
print s.split(";", 1)

li = [1, 9, 8, 4]

print [elem * 2 for elem in li]
li = [elem * 2 for elem in li]
print li

params = {"server": "mpilgrim", "database": "master", "uid": "sa", "pwd": "secret"}
print params.keys()
print params.values()
print params.items()
print [k for k, v in params.items()]
print [v for k, v in params.items()]
print ['%s?%s' %(k, v) for k, v in params.items()]
print ["%s=%s" % (k, v) for k, v in params.items()]

# list过滤
li = ["a", "mpilgrim", "foo", "b", "c", "b", "d", "d"]
print li
print [elem for elem in li if len(elem) > 1]
print [elem for elem in li if len(elem) == 1]
print [elem for elem in li if elem != "b"]
print [elem for elem in li if li.count(elem) == 1]
