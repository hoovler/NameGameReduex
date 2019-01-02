list 	= {	01, 02, 03, 04, 05, 06, 07, 08, 09, 10}
idx 	=	00  01  02  03  04  05  06  07  08  10
size	=   10

list2 	= {	00, 01, 02, 03, 04, 05, 06, 07, 08, 09}
idx	=   	01  02  03  04  05  06  07  08  09  10

list3 	= {	aa, bb, cc, dd, ee, zz, yy, qq, kk, pp }
idx	=   	01  02  03  04  05  06  07  08  09 10

list.size() = 10
index = -1
list.get(list.size() - abs(index + 1)) = list.get(10 - 0) = list.get(10) = 10
list2.get(list.size() - abs(index + 1)) = list.get(10 - 0) = list.get(10) = 9

index = -4
list.get(list.size() - abs(index + 1)) = list.get(10 - 3) = list.get(7) = 7
list2.get(list.size() - abs(index + 1)) = list.get(10 - 3) = list.get(7) = 6

index = 3, velocity = -6
subset(list, start = 3, velocity = -6) 
	resultList.size() = abs(6) + 1 = 7
			   -10	-9	-8	-7	-6	-5	-4	-3  -2  -1
	list 	= 	01, 02, 03, 04, 05, 06, 07, 08, 09, 10
	idx 	=	00  01  02  03  04  05  06  07  08  09
						 <-...|			   |...<-
resList.idx =   04, 03, 02, 01, 10, 09, 08
				size = 7
	
ALSO

direction = backwards : velocity < 0
1: reverse list
2: double list
3: reverse the args and remove the sign of velocity
subset(list, 3, -6) 
	= list.reverse.subset(6, 3)
		 0,  1,  2,  3,  4,  5,  6,  7,  8,  9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19
	=>	10, 09, 08, 07, 06, 05, 04, 03, 02, 01, 10, 09, 08, 07, 06, 05, 04, 03, 02, 01
	     0,  1,  2,  3,  4,  5,  6,  7,  8,  9,  0,  1,  2,  3,  4,  5,  6,  7,  8,  9
oldList(3:-6) = 04, 03, 02, 01, 10, 09, 08
newList(?: ?) = 04, 03, 02, 01, 10, 09, 08
	= (6:12)


SIZE = Math.abs(velocity) + 1;

if start=negative: 
	startIndex <= list.size() - start
if start=positive:
	startIndex <= start
	
if velocity=negative:
	originalList = originalList.reverse
	

list = list.addAll(originalList).addAll(originalList)

stopIndex <= start + velocity


	
	
oldList(-8:2); size = abs(velocity) + 1 = 3
	= oldlist(start=-8. stop=-8+2) = (-8: -6) 
	= (list.len - 8: list.len - 8 + 2) = (10 - 8: 10 - 8 + 2) = (2:4)
	=> 03, 04, 05
	AND
	(start=-8. stop=-8+2) = (-8: -6) 
	= (list.len - 8: list.len - 8 + 2) = (10 - 8: 10 - 8 + 2) = (2:4)
	=> 03, 04, 05
	
   -10	-9	-8	-7	-6	-5	-4	-3	-2	-1
 	01, 02, 03, 04, 05, 06, 07, 08, 09, 10
	00  01  02  03  04  05  06  07  08  09 
(-8:-7); size = abs(-7) + 1 = 8	
=> 03, 02, 01, 10, 09, 08, 07, 06
	start = list.size() - start = 10 - abs(-8) = 2
	stop  = start + velocity = 2 + -7 = -5 = 5
	= ^(2:5)
	= 03, 02, 01, 10, 09, 08, 07, 06
	
list.reverse
	 0,  1,  2,  3,  4,  5,  6,  7,  8,  9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19	<- navigatorIndices
	 
	10, 09, 08, 07, 06, 05, 04, 03, 02, 01, 10, 09, 08, 07, 06, 05, 04, 03, 02, 01	<- navigatorList
	 9,  8,  7,  6,  5,  4,  3,  2,  1,  0,  9,  8,  7,  6,  5,  4,  3,  2,  1,  0 	<- indicesList
full=20
half=10
0:10 inclusive * 2
=> 03, 02, 01, 10, 09, 08, 07, 06
	= (start=7:stop=14) = old(2:5)
	= start = list.size() - start = 10 - abs(-8) = 2
	= stop  = start + velocity = 2 + -7 = -5 = 5
	
(-8 : -7)
originalStart = start < 0 ? list.size() - Math.abs(start) : start
	= list.size() - Math.abs(start) 
	= 10 - 8
	= 2
originalStop = abs(originalStart + velocity)
	= abs(2 + -7) = abs(-5)
	= 5

int startIndex = ArrayUtils.indexOf(indices, originalStart);
	= navigatorIndex(atFirstInstanceOf(originalStart) in indicesList)
	= navigatorIndex(atFirstInstanceOf(2) in indicesList)
	= 7
int stopIndex = ArrayUtils.indexOf(indices, originalStop, originalStart);
	= navigatorIndex(atFirstInstanceOf(originalStop).afterIndex(startIndex) in indicesList)
	= navigatorIndex(atFirstInstanceOf(5).afterIndex(7) in indicesList)
	= 
	
startIndex = start < 0 ? list.size() - Math.abs(start) : start
stopIndex = startIndex + velocity

list=
   -10	-9	-8	-7	-6	-5	-4	-3	-2	-1 <- negativeIndex
 	 a,  b,  c,  d,  e,  f,  g,  h,  i,  j <- values
	00  01  02  03  04  05  06  07  08  09 <- index
list.reverse=
	 j,  i,  h,  g,  f,  e,  d,  c,  b,  a <- rIndex
	09	08	07	06	05	04	03	02	01	00 <- old index
	00	01	02	030	04	05	06	07	08	09 <- new index

start = -8
velocity = 3

subset(list, start, velocity) == {[02, 03, 04, 05]} == {c, d, e, f}

startIndex = start < 0 ? list.size() - Math.abs(start) : start = list.size() - Math.abs(start)
	=> list.size()=10, |start|=8 => 10-8
	= 02
stopIndex = startIndex + velocity = 2 + 3
	= 05



start = -8
velocity = -3
subset(list, start, velocity) = {[-08, -09, -10, -11]} 
	= {[02, 01, 00, -01]}
	= {[02, 01, 00, list.size() - abs(-1)=10-1=09]}
	= {[02, 01, 00, 09]}
	= {c, b, a, j}
	= list reverseIterator, loop back to end.
	
subset(list, start, velocity); rList; 
	startIndex = -8 = len-|8| = 10-8 
		= 02
	subset = list{02:-3} = {rList(oldIndex(startIndex) : mag(velocity))} = {rList(oldIndex(02) : mag(velocity))}
		= rList{07:3} = {[07, 08, 09, 00]} = {c, b, a, j}
		
		
		
startIndex = start < 0 ? list.size() - Math.abs(start) : start;