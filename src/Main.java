public class Main
{
    public static void main(String[] args)
    {
        if(args.length < 2)
        {
            System.out.println("Usage: java Main <processes.csv> <time_quantum>");
            return;
        }

        String filename = args[0];
        int timeQuantum = Integer.parseInt(args[1]);

        try{
            Scheduler scheduler = new Scheduler(filename, timeQuantum);
            scheduler.run();
        } catch(Exception e)
        {
            System.err.println("Error: " + e.getMessage());
        }
    }
}