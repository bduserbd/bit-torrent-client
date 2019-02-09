package Bencode;

import java.util.Arrays;

public class BencodeBuffer
{
    private int index;
    private byte[] buffer;

    public BencodeBuffer(byte[] buffer)
    {
        this.index = 0;
        this.buffer = buffer;
    }

    byte getCurrentByte()
    {
        return buffer[index];
    }

    int getIndex()
    {
        return index;
    }

    void setIndex(int index)
    {
        this.index = index;
    }

    boolean isEnd()
    {
        return index == buffer.length;
    }

    void incIndex()
    {
        index++;
    }

    int getEndIndex(char end)
    {
        int i;

        for (i = getIndex(); buffer[i] != end; i++) ;

        return i;
    }

    private int getIntegerEndMark(char end)
    {
        int i = getIndex(), e = getEndIndex(end);

        setIndex(e + 1);

        byte[] number = Arrays.copyOfRange(buffer, i, e);
        return Integer.parseInt(new String(number));
    }

    int getInteger()
    {
        if (getCurrentByte() != 'i')
            throw new IllegalArgumentException("Invalid Bencode type (expected integer)");

        incIndex();

        return getIntegerEndMark('e');
    }

    String getString()
    {
        int length = getIntegerEndMark(':');
        int start = getIndex();

        setIndex(start + length);

        byte[] string = Arrays.copyOfRange(buffer, start, start + length);
        return new String(string);
    }

    BencodeList getList()
    {
        if (getCurrentByte() != 'l')
            throw new IllegalArgumentException("Invalid Bencode type (expected list)");

        incIndex();

        BencodeList list = new BencodeList();

        while (getCurrentByte() != 'e')
        {
            list.add(Bencode.parseSingleItem(this));
        }

        incIndex();

        return list;
    }
}
