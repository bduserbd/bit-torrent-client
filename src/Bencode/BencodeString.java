package Bencode;

public class BencodeString implements BencodeType
{
    private String string;

    BencodeString(String string)
    {
        this.string = string;
    }

    public static BencodeString Parse(BencodeBuffer buffer)
    {
        return new BencodeString(buffer.getString());
    }
}
