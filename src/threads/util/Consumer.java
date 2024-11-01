package threads.util;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {
    private final BlockingQueue<Message> queue;

    public Consumer(BlockingQueue<Message> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            Message msg;
            //consuming messages until exit message is received
            while (!Objects.equals((msg = queue.take()).getMessage(), "exit")) {
                Thread.sleep(1000);
                System.out.println("Consumed " + msg.getMessage());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
