package Bencode;

public class BencodeInteger implements BencodeType
{
    private int integer;

    BencodeInteger(int integer)
    {
        this.integer = integer;
    }

    public static BencodeInteger Parse(BencodeBuffer buffer)
    {
        return new BencodeInteger(buffer.getInteger());
    }
}
