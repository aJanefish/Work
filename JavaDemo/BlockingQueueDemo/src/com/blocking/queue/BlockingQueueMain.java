package com.blocking.queue;

import com.blocking.queue.utils.Print;

//阻止队列 demo
/**
 * 阻塞队列与我们平常接触的普通队列(LinkedList或ArrayList等)的最大不同点，在于阻塞队列支出阻塞添加和阻塞删除方法。
 *
 * 阻塞添加
 * 所谓的阻塞添加是指当阻塞队列元素已满时，队列会阻塞加入元素的线程，直队列元素不满时才重新唤醒线程执行元素加入操作。
 *
 * 阻塞删除
 * 阻塞删除是指在队列元素为空时，删除队列元素的线程将被阻塞，直到队列不为空再执行删除操作(一般都会返回被删除的元素)
 *
 * */
public class BlockingQueueMain {

    public static void main(String[] args){
        Print.P("BlockingQueue -- Main");

    }
}
