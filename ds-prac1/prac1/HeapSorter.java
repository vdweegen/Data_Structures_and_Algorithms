package prac1;

class HeapSorter {
 /* DATASTRUCTURES AND ALGORITHMS 2014-2015
  * Practical assignment 1
  * Deadline: Friday September 19 23:59
  * 
  * Name(s) : Cas van der Weegen [2566388]
  * Group : None, no access to Blackboard (yet)
  *
  */
 
 /* Define Variabeles */
 private static int[] a;
 private static int largest;
 
 /* Heapsort Function
  *   'a' => array to be sorted
  */
 private static int[] heapsort(int[] a){
  int n = a.length - 1; /* We don't want to get out of bounds */
  heapify(a, n); /* Construct the Heap */
  
  /* Do the Actual Sorting
   *  Carried out a.length-1 times,
   *  for index i=0,...,i=a.length-1
   */
  for (int i = n; i > 0; i--){ /* Walk up Through the tree */
   swap(a, 0, i);
   n--; /* Decrement to walk UP */
   siftDown(a, 0, n); /* siftDown current value
                       * since swapping messed up the heap
                       */
  } /* Worst case performance is O(n log(n)) since constructing
    * the heap takes n, and the siftDown takes log(n), hence the
    * accumulative complexity is n log(n) */
  
  return a; /* Carried out Once */
 }
 
 /* Heapsort Function
  *   'a' => array to be sorted
  */
 private static int[] heapsort3(int[] a){
  int n = a.length - 1; /* We don't want to get out of bounds */
  heapify3(a, n); /* Construct the Heap */
  
  /* Do the Actual Sorting
   *  Carried out a.length-1 times,
   *  for index i=0,...,i=a.length-1
   */
  for (int i = n; i > 0; i--){ /* Walk up Through the tree */
   swap(a, 0, i);
   n--; /* Decrement to walk UP */
   siftDown3(a, 0, n); /* siftDown current value
                        * since swapping messed up the heap
                        */
  } /* Worst case performance is O(n log(n)) since constructing
    * the heap takes n, and the siftDown takes log(n), hence the
    * accumulative complexity is n log(n) */
  
  return a; /* Carried out Once */
 } /* Worst case performance is O(n log(n)) since constructing
    * the heap takes n, and the siftDown takes log(n), hence the
    * accumulative complexity is n log(n) */
 
 /* Function that constructs the heap:
  *  'a' => array to be sorted
  *  'n' => last child
  */
 public static void heapify(int[] a, int n) {	
  int lastParent = (n/2); /* Start at the last Parent Node */
  /* This for-loop is run (n/2) times (the amount of parents, where n
   * is the a.length-1
   */
  for(int i = lastParent; i >= 0; i--){ /* Work your way up through the tree */
   siftDown(a, i, n);
  } /* Loop complexity for this function is O(n), where n
     * is the size of the to-be-sorted array
     */
 } /* Constructing the Heap requires n steps, where
     * n is the length of the to-be-sorted array, this cannot
     * be done any faster since you have to at-least copy the entire
     * array once
     */
 
 /* Function that constructs the heap:
  *  'a' => array to be sorted
  *  'n' => last child
  */
 public static void heapify3(int[] a, int n) {	
  int lastParent = ((n-1)/3); /* Start at the last Parent Node */
  /* This for-loop is run (n-1)/3 times (the amount of parents, where n
   * is the a.length-1
   */
  for(int i = lastParent; i >= 0; i--){ /* Work your way up through the tree */
   siftDown3(a, i, n);
  }  /* Loop complexity for this function is 0(n), where n
      * is the size of the to-be-sorted array */
 } /* Constructing the Heap requires n steps, where
     * n is the length of the to-be-sorted array, this cannot
     * be done any faster since you have to at-least copy the entire
     * array once
     */
 
 /* siftDown Function
  *  This function compares the children and parent of the current node
  *  and swaps when/if needed
  *   This function is recursive (it calls itself), so in the worst case the
  *   this function is run 3 times (the amount of children)
  *    'a' => array to be sorted
  *    'i' => current index
  *    'n' => current root
  */
 public static void siftDown(int[] a, int i, int n){		
  int leftChild = 2*i+1; /* 2*i is parent => 2*i+1 is the left child */
  int rightChild = 2*i+2; /* 2*i+2 is the right child */
  /* Check if within bounds */
  if(leftChild <= n){
   /* Check if bigger than parent */
   if(a[leftChild] > a[i]){
    largest = leftChild;
   }
  }else{
   largest = i;
  }
   
  /* Check if within bounds */
  if(rightChild <= n){
   /* Check if bigger than current largest
    * (can be parent or left child)
    */
   if(a[rightChild] > a[largest]){
    largest = rightChild;
   }
  }
   
  /* Compare largest agains parent,
   * if so: swap and siftDown again
   */
  if(a[largest] > a[i]){
   swap(a, i, largest);
   siftDown(a, largest, n);
  }	
 } /* Time complexity for this function is O(log(n)) (worst case),
    * since this function is recursive and needs to run n times */
 
 
 /* siftDown Function [Ternary]
  *  This function compares the children and parent of the current node
  *  and swaps when/if needed
  *   This function is recursive (it calls itself), so in the worst case the
  *   this function is run 3 times (the amount of children)
  *    'a' => array to be sorted
  *    'i' => current index
  *    'n' => current root
  *
  *  This function could potentially be used to use more then 3 children
  *  per node
  */
 public static void siftDown3(int[] a, int i, int n){		
  int child = 3*i+1; /* 3*i is parent => 3*i+1 is the first child */
  /* Check if a child exists
   *  if a child would be bigger than the current root if would
   *  mean the child doens't exist (and we can return)
   */
  if (child > n) {
   return;
  }
  /* The current largest is the first child (obviously) */
  int largest = child;
  /* Run 3 passes (3 Children per node) */
  for (int j = 0; j < 3; j++){
   /* Check if child is within bounds, run for each child,
    *  child + 0 => first child
    *  child + 1 => second child
    *  child + 2 => third child
    */
   if ((child + j) <= n){
    /* Compare child with (current) largest child */
    if(a[largest] < a[child + j]){
     largest = child + j;
    }
   }
  } /* Carried out 3 times, once per child */
   
  /* Compare largest Child again Parent,
   *
   * if child has a bigger value we swap
   * and re-siftDown
   */
  if(a[largest] > a[i]){
   swap(a, i, largest);
   siftDown3(a, largest, n);
  }	
 } /* Time complexity for this function is O(log(n)) (worst case),
    * since this function is recursive and needs to run n times */
 
 /* Moving the swap code to an external function
  * we eliminate a couple of lines (code is used 4 times)
  */
 public static void swap(int[] a, int i, int j){
  int tmp = a[i];
  a[i] = a[j];
  a[j] = tmp;
 }
 
 /* Start function, was to remain untouched */
 public static int[] start (int[] toSort, boolean binaryHeap) {
  if (binaryHeap){ /* Binary Heap */
   heapsort(toSort);
  }else{ /* Ternary Heap */
   heapsort3(toSort);
  }
  /* Return sorted array to framework */
  return toSort;
 }
}
