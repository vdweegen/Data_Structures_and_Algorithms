package prac2;

public class Tasks {
  /* DATASTRUCTURES AND ALGORITHMS 2014-2015
  * Practical assignment 2
  * Deadline: Friday October 17 23:59
  * 
  * Name(s) :	Cas van der Weegen [2566388]
  * Group : 	None, no access to Blackboard (yet)
  */
	
  // --------------------------------------------------------
  //   GENERIC METHODS BELOW THIS LINE
  // --------------------------------------------------------
  /* Helper function to return a max value */
  private static int max(int a,int b){
	 return (a > b) ? a : b; /* <3 short statements */
  }
  // --------------------------------------------------------
  //   METHODS FOR TASK 1 BELOW THIS LINE
  // --------------------------------------------------------
	/* rodCutting Function Recursive Implementation
	 * 
	 * Time Complexity:
	 * 	T(0) = 1
	 * 	T(n) = 1 + SIGMA((n-1)/j=0)(T(J))
	 * 
	 * 		Which gives: 2^n 
	 */
  private static int rodCuttingRec(int []a, int l){
   if((l - 1) == 0){ /* If l-1 equals ZERO, return rodsize at 0 */
    return a[0];
   }
   if(l == 0){ /* If l equals ZERO, return */
    return 0;
   }
   int b = 0;
   int q = Integer.MIN_VALUE; /* Use Integer.MIN_VALUE for -INFINITY */
   for(int i = 1; i <= l; i++){
    /* Use helperfunction to determine MAX */
    q = max(q, a[i] + rodCuttingRec(a,l - i)); 
   }
   b = max(b,a[l - 1]);
    return b;
   }
	
  public static int task1 (int[] a) {
   return rodCuttingRec(a, a.length-1);
  }
  
  // --------------------------------------------------------
  //   METHODS FOR TASK 2 BELOW THIS LINE
  // --------------------------------------------------------
	/* rodCutting Function Dynamic Implementation
	 * 
	 * Time Complexity:
	 * 	SIGMA(n/j=1)j 
	 *  	Which gives: O(n^2)
	 */
  private static int rodCuttingDP(int[] a, int l) {
   int[] b = new int[l + 1];
   b[0] = 0;
   
   for (int j = 1; j <= l; j++) {
    int q = Integer.MIN_VALUE; /* Use Integer.MIN_VALUE for -INFINITY */
    for (int i = 1; i <= j; i++) {
     q = max(q, a[i] + b[j - i]); /* Use helperfunction to determine MAX */
   	}
   	b[j] = q;
   }
   return b[l];
  }
  
  public static int task2 (int[] a) {
   return rodCuttingDP(a, a.length-1);
  }
  
  // --------------------------------------------------------
  //   METHODS FOR TASK 3 BELOW THIS LINE
  // --------------------------------------------------------
  private static int maxSubArraySum(int[][] m){
   int maxValue, currentMax;
   /* Horizontal Sum */
   int horizontalValue = 0;
   for (int i = 0; i < m.length; i++) {
    currentMax = 0; /* Reset */
    maxValue = 0; /* Reset */
    for(int j = 0; j < m[i].length; j++){
     currentMax = currentMax + m[i][j];
     if(currentMax < 0){ /* Lines that are negative are set to 0,
                          * as per requirements
                          */ 
      currentMax = 0;
     }else if (maxValue < currentMax){
      maxValue = currentMax;
     }
    }
    /* If the current how has a higher sum, it becomes
     * our new horizontalValue
     */
    if(maxValue > horizontalValue){
     horizontalValue = maxValue;
    }
   }
   
   /* Vertical Sum */
   int verticalValue = 0;
   for (int i = 0; i < m.length; i++) {
    currentMax = 0; /* Reset */
    maxValue = 0; /* Reset */
	  for(int j = 0; j < m[i].length; j++){
	   currentMax = currentMax + m[j][i];
     if(currentMax < 0){ /* Lines that are negative are set to 0,
                          * as per requirements
                          */ 
	    currentMax = 0;
	   }else if (maxValue < currentMax){
	    maxValue = currentMax;
	   }
	  }
    /* If the current how has a higher sum, it becomes
     * our new verticalValue
     */
    if(maxValue > verticalValue){
     verticalValue = maxValue;
    }
   }
   /* CounterDiagonal Sum
    * Walk through array and save what we can access
    */
   int counterDiagonalValue = 0;
   /* Start our loop, make the loop run twice as long as the length of the
    * array, since we need to deal with corners
    */
   for(int k = 0; k < m.length * 2; k++){
    currentMax = 0; /* Reset */
	  for( int j = 0 ; j <= k; j++){
     maxValue = 0; /* Reset */
     int i = k - j;
     if(i < m.length && j < m.length){
      currentMax = currentMax + m[j][i];
      if(currentMax < 0){ /* Lines that are negative are set to 0,
                           * as per requirements
                           */ 
       currentMax = 0;
	    }else if (maxValue < currentMax){
	     maxValue = currentMax;
	    }
	   }
     /* If the current how has a higher sum, it becomes
      * our new counterDiagonalValue
      */
	   if(maxValue > counterDiagonalValue){
      counterDiagonalValue = maxValue;
     }
    }
   }
   
	 /* Diagnoal Sum
    *  Basically we reverse the above
	  */
	 int diagonalValue = 0;
   /* Start our loop, make the loop run twice as long as the length of the
    * array, since we need to deal with corners
    */
	 for(int k = 0; k < m.length * 2; k++){
    currentMax = 0; /* Reset */
    for( int j = 0; j <= k; j++){
     maxValue = 0; /* Reset */
     int i = k - j;
     /* Reverse the coordinates to get CounterDiagonal opposed to Diagonal */
     if(((m.length-1)-i) < m.length && ((m.length-1) - j) < m.length &&
         ((m.length-1) - i)>= 0 && ((m.length-1) - j) >= 0 ) {
      currentMax = currentMax + m[i][((m.length-1) - j)];
      if(currentMax < 0){ /* Lines that are negative are set to 0,
                           * as per requirements
                           */ 
       currentMax = 0;
      }else if (maxValue < currentMax){
       maxValue = currentMax;
      }
     }
     /* If the current how has a higher sum, it becomes our new diagonalValue */
     if(maxValue > diagonalValue){
      diagonalValue = maxValue;
     }
    }
   }
   /* Return the SUM of all SUMS */
	 return horizontalValue+verticalValue+diagonalValue+counterDiagonalValue;
  }
  
  public static int task3 (int[][] m) { // m has size n x n (i.e. it is square)
   return maxSubArraySum(m);
  }
}