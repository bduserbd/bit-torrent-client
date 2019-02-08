import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import Bencode.Bencode;
import Bencode.BencodeBuffer;

public final class BitTorrentFile
{
    private String path;

    BitTorrentFile(String path)
    {
        this.path = path;

        try
        {
            Parse();
        }
        catch (IOException e)
        {
            System.err.println(e.getMessage());
        }
    }

    private byte[] readFileCotent() throws IOException
    {
        Path path = Paths.get(getPath());
        return Files.readAllBytes(path);
    }

    private void Parse() throws IOException
    {
        byte[] binary = readFileCotent();

        System.out.println(String.format("Parsing `%s' (%d bytes)", getPath(), binary.length));

        Bencode.parse(new BencodeBuffer("i35e9:Somethingi1771e0:i348917e4:bbbb".getBytes()));
    }

    public String getPath()
    {
        return this.path;
    }
}
