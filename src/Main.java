public class Main
{
    public static void main(String[] args)
    {
        if (args.length != 1)
        {
            System.err.println("usage: Java [BitTorrent file path]");
            System.exit(1);
        }

        Client client = new Client(args[0]);
        client.download();
    }
}
