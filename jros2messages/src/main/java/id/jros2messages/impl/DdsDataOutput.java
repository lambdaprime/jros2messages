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

import id.kineticstreamer.KineticStreamController;
import id.kineticstreamer.KineticStreamWriter;
import id.kineticstreamer.OutputKineticStream;
import id.xfunction.logging.XLogger;
import java.io.DataOutput;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.nio.ByteBuffer;
import java.util.UUID;

/**
 * Kinetic stream implementation for types serialization in DDS format.
 *
 * @author aeon_flux aeon_flux@eclipso.ch
 */
public class DdsDataOutput implements OutputKineticStream {

    private static final XLogger LOGGER = XLogger.getLogger(DdsDataOutput.class);
    private DataOutput out;
    private int position;
    private KineticStreamController controller;

    public DdsDataOutput(DataOutput out, KineticStreamController controller) {
        this.out = out;
        this.controller = controller;
    }

    public void writeLen(int len) throws IOException {
        writeInt(len, EMPTY_ANNOTATIONS);
    }

    private void writeArraySize(int len, Annotation[] fieldAnnotations) throws IOException {
        LOGGER.entering("writeArraySize");
        for (int i = 0; i < fieldAnnotations.length; i++) {
            if (fieldAnnotations[i] instanceof id.jrosmessages.Array a) {
                if (a.size() > 0) return;
            }
        }
        writeLen(len);
        LOGGER.exiting("writeArraySize");
    }

    private void align(int n) throws IOException {
        while (position % n != 0) {
            out.writeByte(0);
            position++;
        }
    }

    @Override
    public void writeString(String str, Annotation[] fieldAnnotations) throws IOException {
        var len = str.length();
        len++; // null byte
        writeLen(len);
        out.write(str.getBytes());
        out.write(0);
        position += len;
    }

    @Override
    public void writeInt(Integer i, Annotation[] fieldAnnotations) throws IOException {
        align(Integer.BYTES);
        out.writeInt(Integer.reverseBytes(i));
        position += Integer.BYTES;
    }

    @Override
    public void writeDouble(Double f, Annotation[] fieldAnnotations) throws IOException {
        align(Double.BYTES);
        out.writeDouble(Double.longBitsToDouble(Long.reverseBytes(Double.doubleToRawLongBits(f))));
        position += Double.BYTES;
    }

    @Override
    public void writeFloat(Float f, Annotation[] fieldAnnotations) throws IOException {
        align(Float.BYTES);
        out.writeFloat(Float.intBitsToFloat(Integer.reverseBytes(Float.floatToRawIntBits(f))));
        position += Float.BYTES;
    }

    @Override
    public void writeBoolean(Boolean b, Annotation[] fieldAnnotations) throws IOException {
        writeByte(b ? (byte) 1 : (byte) 0, fieldAnnotations);
    }

    @Override
    public void writeArray(Object[] array, Annotation[] fieldAnnotations) throws Exception {
        writeArraySize(array.length, fieldAnnotations);
        var writer = new KineticStreamWriter(this).withController(controller);
        for (var item : array) {
            writer.write(item);
        }
    }

    @Override
    public void close() throws Exception {
        // nothing to release
    }

    @Override
    public void writeByte(Byte b, Annotation[] fieldAnnotations) throws IOException {
        out.writeByte(b);
        position++;
    }

    @Override
    public void writeIntArray(int[] array, Annotation[] fieldAnnotations) throws Exception {
        writeArraySize(array.length, fieldAnnotations);
        if (array.length > 0) {
            align(Integer.BYTES);
            var buf = new byte[array.length * Integer.BYTES];
            ByteBuffer.wrap(buf)
                    .order(JRos2MessagesConstants.ROS2_BYTE_ORDER)
                    .asIntBuffer()
                    .put(array);
            out.write(buf);
            position += buf.length;
        }
    }

    @Override
    public void writeByteArray(byte[] array, Annotation[] fieldAnnotations) throws Exception {
        writeArraySize(array.length, fieldAnnotations);
        out.write(array);
        position += array.length;
    }

    @Override
    public void writeDoubleArray(double[] array, Annotation[] fieldAnnotations) throws Exception {
        writeArraySize(array.length, fieldAnnotations);
        if (array.length > 0) {
            align(Double.BYTES);
            var buf = new byte[array.length * Double.BYTES];
            ByteBuffer.wrap(buf)
                    .order(JRos2MessagesConstants.ROS2_BYTE_ORDER)
                    .asDoubleBuffer()
                    .put(array);
            out.write(buf);
            position += buf.length;
        }
    }

    @Override
    public void writeBooleanArray(boolean[] array, Annotation[] fieldAnnotations) throws Exception {
        writeArraySize(array.length, fieldAnnotations);
        var buf = new byte[array.length];
        for (int i = 0; i < buf.length; i++) {
            buf[i] = array[i] ? (byte) 1 : (byte) 0;
        }
        writeByteArray(buf, EMPTY_ANNOTATIONS);
    }

    @Override
    public void writeLong(Long l, Annotation[] fieldAnnotations) throws Exception {
        align(Long.BYTES);
        out.writeLong(Long.reverseBytes(l));
        position += Long.BYTES;
    }

    @Override
    public void writeShort(Short arg0, Annotation[] fieldAnnotations) throws Exception {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeShortArray(short[] arg0, Annotation[] fieldAnnotations) throws Exception {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeStringArray(String[] array, Annotation[] fieldAnnotations) throws Exception {
        writeArraySize(array.length, fieldAnnotations);
        for (var item : array) {
            writeString(item, EMPTY_ANNOTATIONS);
        }
    }

    public void writeUUID(UUID uuid) throws Exception {
        LOGGER.entering("writeUUID");
        writeLong(uuid.getLeastSignificantBits(), EMPTY_ANNOTATIONS);
        writeLong(uuid.getMostSignificantBits(), EMPTY_ANNOTATIONS);
        LOGGER.exiting("writeUUID");
    }

    @Override
    public void writeChar(Character ch, Annotation[] fieldAnnotations) throws Exception {
        throw new UnsupportedOperationException(JRos2MessagesConstants.CHAR_ERROR);
    }

    @Override
    public void writeCharArray(char[] array, Annotation[] fieldAnnotations) throws Exception {
        throw new UnsupportedOperationException(JRos2MessagesConstants.CHAR_ARRAY_ERROR);
    }

    @Override
    public void writeFloatArray(float[] array, Annotation[] fieldAnnotations) throws Exception {
        writeLen(array.length);
        if (array.length > 0) {
            align(Float.BYTES);
            var buf = new byte[array.length * Float.BYTES];
            ByteBuffer.wrap(buf)
                    .order(JRos2MessagesConstants.ROS2_BYTE_ORDER)
                    .asFloatBuffer()
                    .put(array);
            out.write(buf);
            position += buf.length;
        }
    }
}
