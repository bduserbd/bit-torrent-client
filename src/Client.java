public final class Client
{
    private BitTorrentFile torrent;

    Client(String path)
    {
        torrent = new BitTorrentFile(path);
    }

    public void download()
    {
        System.out.println(String.format("Downloading torrent: `%s'", torrent.getPath()));
    }
}
