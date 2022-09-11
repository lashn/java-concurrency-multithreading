package ProdConsumer;

public class ProducerConsumer {
    /*bufferoverflow - when producer produces more than queue length - data will be lost
    Average rate of production < Average rate of consumption
    If no queue - Mutex + condition variable to introduce thread safe queue
    Some languages - provide unbounded queue - indefinite length using linkedlist - but still limited by h/w memory
    Rules:
    1.Ensure mutual exclusion of producers and consumers
    2.Prevent producers from trying to add data to a full queue
    3.Prevent consumers from trying to remove data from a empty queue/buffer
     */




}

}