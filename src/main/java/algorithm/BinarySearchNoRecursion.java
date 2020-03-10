package algorithm;
// 二分查找的非递归的算法
public class BinarySearchNoRecursion {
    public static void main(String[] args) {
        int arr[]={1,3,4,67,78,100};
        int index = binarySearch(arr, 4);
        System.out.println("index==="+index);
    }

    public static  int binarySearch(int [] arr,int target){

        int left = 0;
        int right =arr.length-1;
        while (left<=right){
            int mid = (left+right)/2;
            if(arr[mid]==target){
                return mid;
            }else if(arr[mid]<target){
                left=mid+1;
            }else {
                right=mid-1;
            }
        }
        return -1;
    }
}
