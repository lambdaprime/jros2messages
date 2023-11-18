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
import id.xfunction.Preconditions;
import id.xfunction.logging.XLogger;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.util.UUID;

/**
 * Kinetic stream implementation for types deserialization in DDS format.
 *
 * @author aeon_flux aeon_flux@eclipso.ch
 */
public class DdsDataInput implements InputKineticStream {

    private static final XLogger LOGGER = XLogger.getLogger(DdsDataInput.class);

    private ByteBuffer in;

    public DdsDataInput(ByteBuffer in) {
        this.in = in.order(MessageConstants.ROS2_BYTE_ORDER);
    }

    @Override
    public int readInt() throws IOException {
        align(Integer.BYTES);
        return in.getInt();
    }

    private void align(int n) throws IOException {
        while (in.position() % n != 0) {
            in.get();
        }
    }

    @Override
    public String readString() throws IOException {
        LOGGER.entering("readString");
        int len = readLen();
        byte[] b = new byte[len];
        in.get(b);
        var value = new String(b, 0, len - 1);
        Preconditions.equals(0, b[len - 1], "Null byte expected");
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
        var value = in.getDouble();
        LOGGER.exiting("readDouble", value);
        return value;
    }

    @Override
    public float readFloat() throws IOException {
        LOGGER.entering("readFloat");
        align(Float.BYTES);
        var value = in.getFloat();
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
        var value = in.get();
        LOGGER.exiting("readByte");
        return value;
    }

    @Override
    public byte[] readByteArray(byte[] array) throws Exception {
        LOGGER.entering("readByteArray");
        array = new byte[readLen()];
        in.get(array);
        LOGGER.exiting("readByteArray");
        return array;
    }

    @Override
    public int[] readIntArray(int[] array) throws Exception {
        LOGGER.entering("readIntArray");
        array = new int[readLen()];
        if (array.length > 0) {
            align(Integer.BYTES);
            var tmpBuf = in.asIntBuffer();
            tmpBuf.get(array);
            in.position(in.position() + array.length * Integer.BYTES);
        }
        LOGGER.exiting("readIntArray");
        return array;
    }

    @Override
    public double[] readDoubleArray(double[] array) throws Exception {
        LOGGER.entering("readDoubleArray");
        array = new double[readLen()];
        if (array.length > 0) {
            align(Double.BYTES);
            var tmpBuf = in.asDoubleBuffer();
            tmpBuf.get(array);
            in.position(in.position() + array.length * Double.BYTES);
        }
        LOGGER.exiting("readDoubleArray");
        return array;
    }

    @Override
    public boolean[] readBooleanArray(boolean[] array) throws Exception {
        LOGGER.entering("readBooleanArray");
        var b = readByteArray(null);
        array = new boolean[b.length];
        for (int i = 0; i < array.length; i++) {
            array[i] = b[i] == 1;
        }
        LOGGER.exiting("readBooleanArray");
        return array;
    }

    @Override
    public long readLong() throws Exception {
        align(Long.BYTES);
        return in.getLong();
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

    public UUID readUUID() throws Exception {
        LOGGER.entering("readUUID");
        var hi = readLong();
        var lo = readLong();
        var uuid = new UUID(lo, hi);
        LOGGER.exiting("readUUID");
        return uuid;
    }

    @Override
    public char readChar() throws Exception {
        throw new UnsupportedOperationException(MessageConstants.CHAR_ERROR);
    }

    @Override
    public char[] readCharArray(char[] arg0) throws Exception {
        throw new UnsupportedOperationException(MessageConstants.CHAR_ARRAY_ERROR);
    }

    @Override
    public float[] readFloatArray(float[] array) throws Exception {
        LOGGER.entering("readFloatArray");
        array = new float[readLen()];
        if (array.length > 0) {
            align(Float.BYTES);
            var tmpBuf = in.asFloatBuffer();
            tmpBuf.get(array);
            in.position(in.position() + array.length * Float.BYTES);
        }
        LOGGER.exiting("readFloatArray");
        return array;
    }
}
