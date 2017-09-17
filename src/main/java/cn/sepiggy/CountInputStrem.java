package cn.sepiggy;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class CountInputStrem extends FilterInputStream {

    int count = 0;

    public CountInputStrem(InputStream in) {
        super(in);
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        int n = super.read(b, off, len);
        count += n;
        return n;
    }

}
