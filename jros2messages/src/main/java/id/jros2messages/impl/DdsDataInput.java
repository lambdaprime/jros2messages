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

import static id.kineticstreamer.KineticStreamConstants.EMPTY_ANNOTATIONS;

import id.kineticstreamer.InputKineticStream;
import id.kineticstreamer.KineticStreamReader;
import id.xfunction.Preconditions;
import id.xfunction.logging.XLogger;
import java.io.IOException;
import java.lang.annotation.Annotation;
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
        this.in = in.order(JRos2MessagesConstants.ROS2_BYTE_ORDER);
    }

    @Override
    public int readInt(Annotation[] fieldAnnotations) throws IOException {
        LOGGER.entering("readInt");
        align(Integer.BYTES);
        var value = in.getInt();
        LOGGER.exiting("readInt", value);
        return value;
    }

    private void align(int n) throws IOException {
        while (in.position() % n != 0) {
            in.get();
        }
    }

    @Override
    public String readString(Annotation[] fieldAnnotations) throws IOException {
        LOGGER.entering("readString");
        int len = readLen();
        byte[] b = new byte[len];
        in.get(b);
        var value = new String(b, 0, len - 1);
        Preconditions.equals(0, b[len - 1], "Null byte expected");
        LOGGER.exiting("readString", (Object) value);
        return value;
    }

    public int readLen() throws IOException {
        return readInt(EMPTY_ANNOTATIONS);
    }

    @Override
    public double readDouble(Annotation[] fieldAnnotations) throws IOException {
        LOGGER.entering("readDouble");
        align(Double.BYTES);
        var value = in.getDouble();
        LOGGER.exiting("readDouble", value);
        return value;
    }

    @Override
    public float readFloat(Annotation[] fieldAnnotations) throws IOException {
        LOGGER.entering("readFloat");
        align(Float.BYTES);
        var value = in.getFloat();
        LOGGER.exiting("readFloat", value);
        return value;
    }

    @Override
    public boolean readBool(Annotation[] fieldAnnotations) throws IOException {
        LOGGER.entering("readBool");
        var value = readByte(EMPTY_ANNOTATIONS);
        LOGGER.exiting("readBool", value);
        return value == 1;
    }

    @Override
    public Object[] readArray(Object[] a, Class<?> type, Annotation[] fieldAnnotations)
            throws Exception {
        LOGGER.entering("readArray");
        var array = (Object[]) Array.newInstance(type, readArraySize(fieldAnnotations));
        var reader = new KineticStreamReader(this);
        for (int i = 0; i < array.length; i++) {
            array[i] = reader.read(type);
        }
        LOGGER.exiting("readArray");
        return array;
    }

    @Override
    public void close() throws Exception {
        // nothing to release
    }

    @Override
    public byte readByte(Annotation[] fieldAnnotations) throws IOException {
        LOGGER.entering("readByte");
        var value = in.get();
        LOGGER.exiting("readByte");
        return value;
    }

    @Override
    public byte[] readByteArray(byte[] array, Annotation[] fieldAnnotations) throws Exception {
        LOGGER.entering("readByteArray");
        array = new byte[readArraySize(fieldAnnotations)];
        in.get(array);
        LOGGER.exiting("readByteArray");
        return array;
    }

    @Override
    public int[] readIntArray(int[] array, Annotation[] fieldAnnotations) throws Exception {
        LOGGER.entering("readIntArray");
        array = new int[readArraySize(fieldAnnotations)];
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
    public double[] readDoubleArray(double[] array, Annotation[] fieldAnnotations)
            throws Exception {
        LOGGER.entering("readDoubleArray");
        array = new double[readArraySize(fieldAnnotations)];
        if (array.length > 0) {
            align(Double.BYTES);
            var tmpBuf = in.asDoubleBuffer();
            tmpBuf.get(array);
            in.position(in.position() + array.length * Double.BYTES);
        }
        LOGGER.exiting("readDoubleArray");
        return array;
    }

    private int readArraySize(Annotation[] fieldAnnotations) throws IOException {
        LOGGER.entering("readArraySize");
        var s = 0;
        for (int i = 0; i < fieldAnnotations.length; i++) {
            if (fieldAnnotations[i] instanceof id.jrosmessages.Array a) {
                s = a.size();
                break;
            }
        }
        if (s == 0) s = readLen();
        LOGGER.exiting("readArraySize", s);
        return s;
    }

    @Override
    public boolean[] readBooleanArray(boolean[] array, Annotation[] fieldAnnotations)
            throws Exception {
        LOGGER.entering("readBooleanArray");
        var b = readByteArray(null, EMPTY_ANNOTATIONS);
        array = new boolean[b.length];
        for (int i = 0; i < array.length; i++) {
            array[i] = b[i] == 1;
        }
        LOGGER.exiting("readBooleanArray");
        return array;
    }

    @Override
    public long readLong(Annotation[] fieldAnnotations) throws Exception {
        align(Long.BYTES);
        return in.getLong();
    }

    @Override
    public long[] readLongArray(long[] arg0, Annotation[] fieldAnnotations) throws Exception {
        throw new UnsupportedOperationException();
    }

    @Override
    public short readShort(Annotation[] fieldAnnotations) throws Exception {
        throw new UnsupportedOperationException();
    }

    @Override
    public short[] readShortArray(short[] arg0, Annotation[] fieldAnnotations) throws Exception {
        throw new UnsupportedOperationException();
    }

    @Override
    public String[] readStringArray(String[] array, Annotation[] fieldAnnotations)
            throws Exception {
        LOGGER.entering("readStringArray");
        array = new String[readArraySize(fieldAnnotations)];
        for (int i = 0; i < array.length; i++) {
            array[i] = readString(EMPTY_ANNOTATIONS);
        }
        LOGGER.exiting("readStringArray");
        return array;
    }

    public UUID readUUID() throws Exception {
        LOGGER.entering("readUUID");
        var hi = readLong(EMPTY_ANNOTATIONS);
        var lo = readLong(EMPTY_ANNOTATIONS);
        var uuid = new UUID(lo, hi);
        LOGGER.exiting("readUUID");
        return uuid;
    }

    @Override
    public char readChar(Annotation[] fieldAnnotations) throws Exception {
        throw new UnsupportedOperationException(JRos2MessagesConstants.CHAR_ERROR);
    }

    @Override
    public char[] readCharArray(char[] arg0, Annotation[] fieldAnnotations) throws Exception {
        throw new UnsupportedOperationException(JRos2MessagesConstants.CHAR_ARRAY_ERROR);
    }

    @Override
    public float[] readFloatArray(float[] array, Annotation[] fieldAnnotations) throws Exception {
        LOGGER.entering("readFloatArray");
        array = new float[readArraySize(fieldAnnotations)];
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
