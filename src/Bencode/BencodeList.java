package Bencode;

import java.util.ArrayList;

public class BencodeList extends ArrayList<BencodeType> implements BencodeType
{
    public static BencodeList Parse(BencodeBuffer buffer)
    {
        return buffer.getList();
    }
}
