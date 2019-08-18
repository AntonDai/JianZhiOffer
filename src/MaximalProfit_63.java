/**
 * @description: 股票的最大利润
 * @author: Daniel
 * @create: 2019-02-27-12-18
 **/
public class MaximalProfit_63 {

    public static int maxProfit(int[] prices) {
        if(prices == null || prices.length == 0)
            return 0;
        int max = 0, curMax = 0;
        for(int i=1; i<prices.length; i++) {
            if(prices[i] > prices[i-1])
                curMax += prices[i] - prices[i-1];
            else
                curMax = 0;
            max = Math.max(max,curMax);
        }
        return max;
    }

    public static void main(String[] args) {
        int[] prices = {9,11,8,5,7,12,16,14};
        System.out.println(maxProfit(prices));
        int[] prices2 = {5,4};
        System.out.println(maxProfit(prices2));
        int[] prices3 = {5,4,3,2,1};
        System.out.println(maxProfit(prices3));
        int[] prices4 = {1,2,3,4,5};
        System.out.println(maxProfit(prices4));
    }
}
