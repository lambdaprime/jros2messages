/*
 * Copyright 2022 jrosclient project
 * 
 * Website: https://github.com/lambdaprime/jros2messages
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package id.jros2messages.impl;

import id.kineticstreamer.InputKineticStream;
import id.kineticstreamer.KineticStreamReader;
import id.xfunction.logging.XLogger;
import java.io.DataInput;
import java.io.IOException;
import java.lang.reflect.Array;

public class DdsDataInput implements InputKineticStream {

    private static final XLogger LOGGER = XLogger.getLogger(DdsDataInput.class);

    private DataInput in;
    private int position;

    public DdsDataInput(DataInput in) {
        this.in = in;
    }

    @Override
    public int readInt() throws IOException {
        align(Integer.BYTES);
        position += Integer.BYTES;
        return Integer.reverseBytes(in.readInt());
    }

    private void align(int n) throws IOException {
        while (position % n != 0) {
            in.readByte();
            position++;
        }
    }

    @Override
    public String readString() throws IOException {
        LOGGER.entering("readString");
        int len = readLen();
        byte[] b = new byte[len];
        in.readFully(b);
        var value = new String(b, 0, len - 1);
        position += len;
        LOGGER.exiting("readString", value);
        return value;
    }

    public int readLen() throws IOException {
        return readInt();
    }

    @Override
    public double readDouble() throws IOException {
        LOGGER.entering("readDouble");
        align(Double.BYTES);
        var value =
                Double.longBitsToDouble(
                        Long.reverseBytes(Double.doubleToRawLongBits(in.readDouble())));
        position += Double.BYTES;
        LOGGER.exiting("readDouble", value);
        return value;
    }

    @Override
    public float readFloat() throws IOException {
        LOGGER.entering("readFloat");
        align(Float.BYTES);
        var value =
                Float.intBitsToFloat(Integer.reverseBytes(Float.floatToRawIntBits(in.readFloat())));
        position += Float.BYTES;
        LOGGER.exiting("readFloat", value);
        return value;
    }

    @Override
    public boolean readBool() throws IOException {
        LOGGER.entering("readBool");
        var value = readByte();
        LOGGER.exiting("readBool", value);
        return value == 1;
    }

    @Override
    public Object[] readArray(Object[] a, Class<?> type) throws Exception {
        LOGGER.entering("readArray");
        var array = (Object[]) Array.newInstance(type, readLen());
        for (int i = 0; i < array.length; i++) {
            array[i] = new KineticStreamReader(this).read(type);
        }
        LOGGER.exiting("readArray");
        return array;
    }

    @Override
    public void close() throws Exception {
        // nothing to release
    }

    @Override
    public byte readByte() throws IOException {
        LOGGER.entering("readByte");
        var value = in.readByte();
        position++;
        LOGGER.exiting("readByte");
        return value;
    }

    @Override
    public byte[] readByteArray(byte[] array) throws Exception {
        LOGGER.entering("readByteArray");
        array = new byte[readLen()];
        for (int i = 0; i < array.length; i++) {
            array[i] = readByte();
        }
        LOGGER.exiting("readByteArray");
        return array;
    }

    @Override
    public int[] readIntArray(int[] array) throws Exception {
        throw new UnsupportedOperationException();
    }

    @Override
    public double[] readDoubleArray(double[] array) throws Exception {
        LOGGER.entering("readDoubleArray");
        array = new double[readLen()];
        for (int i = 0; i < array.length; i++) {
            array[i] = readDouble();
        }
        LOGGER.exiting("readDoubleArray");
        return array;
    }

    @Override
    public boolean[] readBooleanArray(boolean[] array) throws Exception {
        LOGGER.entering("readBooleanArray");
        array = new boolean[readLen()];
        for (int i = 0; i < array.length; i++) {
            array[i] = readBool();
        }
        LOGGER.exiting("readBooleanArray");
        return array;
    }

    @Override
    public long readLong() throws Exception {
        throw new UnsupportedOperationException();
    }

    @Override
    public long[] readLongArray(long[] arg0) throws Exception {
        throw new UnsupportedOperationException();
    }

    @Override
    public short readShort() throws Exception {
        throw new UnsupportedOperationException();
    }

    @Override
    public short[] readShortArray(short[] arg0) throws Exception {
        throw new UnsupportedOperationException();
    }

    @Override
    public String[] readStringArray(String[] array) throws Exception {
        LOGGER.entering("readStringArray");
        array = new String[readLen()];
        for (int i = 0; i < array.length; i++) {
            array[i] = readString();
        }
        LOGGER.exiting("readStringArray");
        return array;
    }
}
