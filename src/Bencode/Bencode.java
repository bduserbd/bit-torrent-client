package Bencode;

import java.util.ArrayList;

public class Bencode
{
    public static BencodeType parseSingleItem(BencodeBuffer buffer)
    {
        char c = (char)(buffer.getCurrentByte() & 0xff);

        if (Character.isDigit(c))
        {
            return BencodeString.Parse(buffer);
        }
        else
        {
            switch (buffer.getCurrentByte())
            {
                case 'i':
                    return BencodeInteger.Parse(buffer);
                case 'l':
                    throw new UnsupportedOperationException("List type parsing not implemented");
                case 'd':
                    throw new UnsupportedOperationException("Dictionary type parsing not implemented");
                default:
                    throw new IllegalArgumentException(String.format("Unknown Bencode type %c", c));
            }
        }
    }

    public static BencodeType[] parse(BencodeBuffer buffer)
    {
        ArrayList<BencodeType> items = new ArrayList<BencodeType>();

        while (!buffer.isEnd())
        {
            items.add(parseSingleItem(buffer));
        }

        BencodeType[] result = new BencodeType[items.size()];
        return items.toArray(result);
    }
}
