package com;

import com.utils.Print;
import sun.dc.pr.PRError;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.Arrays;

public class AllocateMemoryDemo {
    private static int[] ints = {1,2,3,4,5,6,7,8,9,0};
    private static String[] strings = {"aA","bB","cC","dD"};
    public  static  void main(String[] args) throws NoSuchFieldException, IllegalAccessException, InstantiationException {
        // 通过反射得到theUnsafe对应的Field对象
        Field field = Unsafe.class.getDeclaredField("theUnsafe");
        Print.P(field);
        // 设置该Field为可访问
        field.setAccessible(true);

        // 通过Field得到该Field对应的具体对象，传入null是因为该Field为static的
        Unsafe unsafe = (Unsafe) field.get(null);
        Print.P(unsafe);

        long data = 1000;
        byte size = 1;//单位字节

        //调用allocateMemory分配内存,并获取内存地址memoryAddress
        long memoryAddress = unsafe.allocateMemory(size);
        //直接往内存写入数据
        unsafe.putAddress(memoryAddress, data);
        //获取指定内存地址的数据
        long addrData=unsafe.getAddress(memoryAddress);
        System.out.println(memoryAddress+"--addrData:"+addrData);
        for(int i= 0;i <10;i ++){
            long newaddress = memoryAddress+i*16;
            addrData=unsafe.getAddress(newaddress);
            System.out.println(newaddress+"--addrData:"+addrData);

            Print.P(unsafe.allocateMemory(size));
        }

        //获取本机内存的页数，这个值永远都是2的幂次方
        Print.P("unsafe.pageSize():"+unsafe.pageSize());

        //数组操作
        ////获取数组第一个元素的偏移地址
        //public native int arrayBaseOffset(Class arrayClass);
        ////数组中一个元素占据的内存空间,arrayBaseOffset与arrayIndexScale配合使用，可定位数组中每个元素在内存中的位置
        //public native int arrayIndexScale(Class arrayClass);

        Print.P(ints+" , "+ints.getClass() + " , "+ints.getClass().hashCode());
        Print.P(unsafe.arrayBaseOffset(ints.getClass()) + " , "+unsafe.arrayIndexScale(ints.getClass()));

        long valueOffset = unsafe.staticFieldOffset(AllocateMemoryDemo.class.getDeclaredField("ints"));

        Print.P("valueOffset:"+valueOffset);
        int[] ints1= (int[]) unsafe.getObject(AllocateMemoryDemo.class,valueOffset);
        Print.P(ints1);
        Print.P(Arrays.toString(ints1));

        Print.P(strings+" , "+strings.getClass() + " , "+strings.getClass().hashCode());
        Print.P(unsafe.arrayBaseOffset(strings.getClass()) + " , "+unsafe.arrayIndexScale(strings.getClass()));
        valueOffset = unsafe.staticFieldOffset(AllocateMemoryDemo.class.getDeclaredField("strings"));

        Print.P("valueOffset:"+valueOffset);
        String[] strings1 = (String[]) unsafe.getObject(AllocateMemoryDemo.class,valueOffset);
        Print.P(strings1);
        Print.P(Arrays.toString(strings1));

        Print.P(unsafe.getObject(AllocateMemoryDemo.class,valueOffset+36));


        int scale = unsafe.arrayIndexScale(int[].class);
        Print.P(scale);
        int shift = 31 - Integer.numberOfLeadingZeros(scale);
        Print.P(Integer.numberOfLeadingZeros(scale));
        Print.P(shift);
        //Integer.numberOfLeadingZeros(scale)  //用来计算0的个数
        //scale=4，转成二进制为
        //00000000 00000000 00000000 00000100
        //即前导零数为29，也就是shift=2，然后利用shift来定位数组中的内存位置
        Print.P(Integer.numberOfLeadingZeros(0));
        Print.P(Integer.numberOfLeadingZeros(1));
        Print.P(Integer.numberOfLeadingZeros(2));
        Print.P(Integer.numberOfLeadingZeros(4));
        Print.P(Long.numberOfLeadingZeros(4));
        Print.P(int[].class);
        Print.P(Long[].class);
        Print.P(Double[].class);
        Print.P(int.class);
        Print.P(Long.class);
        Print.P(Double.class);
    }
}
