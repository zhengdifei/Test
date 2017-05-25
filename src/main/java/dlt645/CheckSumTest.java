package dlt645;

/**
 * Created by Administrator on 2017/5/8 0008.
 */
public class CheckSumTest {
    public static byte[] checkSum(byte[] msg,int length){
        long mSum = 0;
        byte[] mByte = new byte[length];

        for(byte byteMsg : msg){
            long mNum = ((long)byteMsg >= 0) ? (long)byteMsg : ((long)byteMsg + 256);
            mSum += mNum;
        }

        for (int count = 0;count < length;count++){
            mByte[length - count -1] = (byte)(mSum >> (count * 8) & 0xff);
        }

        return mByte;
    }

    public static void main(String[] args){
        String testStr ="68 03 00 00 12 47 11 68 11 04 33 36 34 35";
        testStr = testStr.replace(" ","");
        System.out.println("原始数据 ：" + testStr );
        String resultStr = Utils.toHexString(checkSum(Utils.hexStringToBytes(testStr),1));
        System.out.println("校验数据 ：" + resultStr );
    }
}
