package Bencode;

import java.util.Arrays;

public class BencodeBuffer
{
    private int index;
    private byte buffer[];

    public BencodeBuffer(byte buffer[])
    {
        this.index = 0;
        this.buffer = buffer;
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

    int getEndIndex()
    {
        int i;

        for (i = getIndex(); buffer[i] != 'e'; i++) ;

        return i;
    }

    byte getCurrentByte()
    {
        return buffer[index];
    }

    int getInteger()
    {
        if (getCurrentByte() != 'i')
            throw new IllegalArgumentException("Invalid Bencode type (expected integer)");

        incIndex();

        int i = getIndex(), e = getEndIndex();

        setIndex(e + 1);

        byte number[] = Arrays.copyOfRange(buffer, i, e);
        return Integer.parseInt(new String(number));
    }
}
