import java.util.LinkedList;
import java.util.Queue;

public class ReadyQueue
{
    private Queue<Process> queue = new LinkedList<>();

    public void enqueue(Process p)
    {
        queue.add(p);
    }

    public Process dequeue()
    {
        return queue.poll();
    }

    public boolean isEmpty()
    {
        return queue.isEmpty();
    }

    public int size()
    {
        return queue.size();
    }

    public Queue<Process> getQueue()
    {
        return queue;
    }
}