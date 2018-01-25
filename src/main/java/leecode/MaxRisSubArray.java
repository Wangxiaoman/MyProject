/**
 * 
 */
package leecode;

/**
 * @author linkedme
 *  最长上升子序列
 */
public class MaxRisSubArray {
    
    
    static void getMaxRiseSubArray(int[] arrays){
        int begin = 0;
        int beginTemp = 0;
        int maxSize = 1;
        int tempSize = 1;
        for(int i=0;i<arrays.length-1;i++){
            if(tempSize > maxSize){
                begin = beginTemp;
                maxSize = tempSize;
            }
            
            if(arrays[i] <= arrays[i+1]){
                tempSize ++;
            } else{
                beginTemp=i;
                tempSize=1;
            }
        }
        System.out.println("begin:"+begin+",maxSize："+maxSize);
    }
    
    public static void main(String[] args) {
        int[] arrays = new int[]{1,2,3,4,1,4,6,12,3,5,7,9,11,1,3,5};
        getMaxRiseSubArray(arrays);
    }
}
