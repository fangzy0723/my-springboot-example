```
/*声明一个需要排序的json数组*/
var array = [
    {name: 'a', phone: 1},
    {name: 'b', phone: 5},
    {name: 'd', phone: 3},
    {name: 'c', phone: 4}
]
```
```
/*排序方法的算法*/
function getSortFun(order, sortBy) {
    var ordAlpah = (order == 'asc') ? '>' : '<';
    var sortFun = new Function('a', 'b', 'return a.' + sortBy + ordAlpah + 'b.' + sortBy + '?1:-1');
    return sortFun;
}
/*方法:将字段sortField按sortType方式排序*/
function sortJson(sortType,sortField){
    return array.sort(getSortFun(sortType, sortField));
}
```
```
/*输出按json中phone字段降序排序之后的json数组*/
alert(JSON.stringify(sortJson("desc","phone")));
```
