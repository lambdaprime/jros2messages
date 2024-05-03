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

import id.jrosmessages.impl.JRosMessagesConstants;
import id.jrosmessages.impl.RosDataOutput;
import id.kineticstreamer.KineticStreamController;
import id.xfunction.logging.TracingToken;
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
public class DdsDataOutput extends RosDataOutput {

    private XLogger logger;
    private int position;

    public DdsDataOutput(
            @SuppressWarnings("exports") TracingToken tracingToken,
            DataOutput out,
            KineticStreamController controller) {
        super(tracingToken, out, controller);
        logger = XLogger.getLogger(DdsDataOutput.class, tracingToken);
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
        super.writeInt(i, fieldAnnotations);
        position += Integer.BYTES;
    }

    @Override
    public void writeDouble(Double f, Annotation[] fieldAnnotations) throws IOException {
        align(Double.BYTES);
        super.writeDouble(f, fieldAnnotations);
        position += Double.BYTES;
    }

    @Override
    public void writeFloat(Float f, Annotation[] fieldAnnotations) throws IOException {
        align(Float.BYTES);
        super.writeFloat(f, fieldAnnotations);
        position += Float.BYTES;
    }

    @Override
    public void writeByte(Byte b, Annotation[] fieldAnnotations) throws IOException {
        super.writeByte(b, fieldAnnotations);
        position++;
    }

    @Override
    public void writeLong(Long l, Annotation[] fieldAnnotations) throws Exception {
        align(Long.BYTES);
        super.writeLong(l, fieldAnnotations);
        position += Long.BYTES;
    }

    public void writeUUID(UUID uuid) throws Exception {
        logger.entering("writeUUID");
        writeLong(uuid.getLeastSignificantBits(), EMPTY_ANNOTATIONS);
        writeLong(uuid.getMostSignificantBits(), EMPTY_ANNOTATIONS);
        logger.exiting("writeUUID");
    }

    @Override
    public void writeIntArray(int[] array, Annotation[] fieldAnnotations) throws Exception {
        writeArraySize(array.length, fieldAnnotations);
        if (array.length > 0) {
            align(Integer.BYTES);
            var buf = new byte[array.length * Integer.BYTES];
            wrap(buf).asIntBuffer().put(array);
            out.write(buf);
            position += buf.length;
        }
    }

    @Override
    public void writeByteArray(byte[] array, Annotation[] fieldAnnotations) throws Exception {
        super.writeByteArray(array, fieldAnnotations);
        position += array.length;
    }

    @Override
    public void writeDoubleArray(double[] array, Annotation[] fieldAnnotations) throws Exception {
        writeArraySize(array.length, fieldAnnotations);
        if (array.length > 0) {
            align(Double.BYTES);
            var buf = new byte[array.length * Double.BYTES];
            wrap(buf).asDoubleBuffer().put(array);
            out.write(buf);
            position += buf.length;
        }
    }

    @Override
    public void writeFloatArray(float[] array, Annotation[] fieldAnnotations) throws Exception {
        writeArraySize(array.length, fieldAnnotations);
        if (array.length > 0) {
            align(Float.BYTES);
            var buf = new byte[array.length * Float.BYTES];
            wrap(buf).asFloatBuffer().put(array);
            out.write(buf);
            position += buf.length;
        }
    }

    private ByteBuffer wrap(byte[] array) {
        return ByteBuffer.wrap(array).order(JRosMessagesConstants.ROS_BYTE_ORDER);
    }
}
