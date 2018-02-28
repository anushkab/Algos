//TimSort is a hybrid sort combining several sorting techniques like insertion and merge sort. Within, it has a phase known as Galloping Phase where, when initiated while merging two sub arrays, the algorithm starts to move larger chunks of elements per comparison.

proc _TimMergeRuns(Data:[?adom],runs:[?rdom] ?domtype, lo=rdom.low, hi=rdom.high, comparator:?rec=defaultComparator):domtype{
 if(lo==hi){
   return runs[lo];
 }
 var dom1,dom2: domtype;
 var m = (lo+hi)/2;
 cobegin with (ref dom1, ref dom2){
   dom1=_TimMergeRuns(Data,runs,lo,m, comparator=comparator);
   dom2=_TimMergeRuns(Data,runs,m+1,hi, comparator=comparator);
 }
 return _TimSortMerge(Data,dom1,dom2, comparator=comparator);
}
