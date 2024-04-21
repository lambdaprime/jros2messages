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

import id.jrosmessages.impl.RosDataInput;
import id.kineticstreamer.KineticStreamController;
import id.xfunction.Preconditions;
import id.xfunction.logging.TracingToken;
import id.xfunction.logging.XLogger;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.nio.ByteBuffer;
import java.util.UUID;

/**
 * Kinetic stream implementation for types deserialization in DDS format.
 *
 * @author aeon_flux aeon_flux@eclipso.ch
 */
public class DdsDataInput extends RosDataInput {
    private XLogger logger;

    @SuppressWarnings("exports")
    public DdsDataInput(
            @SuppressWarnings("exports") TracingToken tracingToken,
            ByteBuffer buf,
            KineticStreamController controller) {
        super(tracingToken, buf, controller);
        logger = XLogger.getLogger(DdsDataInput.class, tracingToken);
    }

    @Override
    public int readInt(Annotation[] fieldAnnotations) throws IOException {
        align(Integer.BYTES);
        return super.readInt(fieldAnnotations);
    }

    @Override
    public String readString(Annotation[] fieldAnnotations) throws IOException {
        logger.entering("readString");
        int len = readLen();
        byte[] b = new byte[len];
        in.get(b);
        var value = new String(b, 0, len - 1);
        Preconditions.equals(0, b[len - 1], "Null byte expected");
        logger.exiting("readString", (Object) value);
        return value;
    }

    @Override
    public int readLen() throws IOException {
        return readInt(EMPTY_ANNOTATIONS);
    }

    @Override
    public double readDouble(Annotation[] fieldAnnotations) throws IOException {
        align(Double.BYTES);
        return super.readDouble(fieldAnnotations);
    }

    @Override
    public float readFloat(Annotation[] fieldAnnotations) throws IOException {
        align(Float.BYTES);
        return super.readFloat(fieldAnnotations);
    }

    @Override
    public long readLong(Annotation[] fieldAnnotations) throws Exception {
        align(Long.BYTES);
        return super.readLong(fieldAnnotations);
    }

    public UUID readUUID() throws Exception {
        logger.entering("readUUID");
        var hi = readLong(EMPTY_ANNOTATIONS);
        var lo = readLong(EMPTY_ANNOTATIONS);
        var uuid = new UUID(lo, hi);
        logger.exiting("readUUID");
        return uuid;
    }

    @Override
    public int[] readIntArray(int[] array, Annotation[] fieldAnnotations) throws Exception {
        logger.entering("readIntArray");
        array = new int[readArraySize(fieldAnnotations)];
        if (array.length > 0) {
            align(Integer.BYTES);
            var tmpBuf = in.asIntBuffer();
            tmpBuf.get(array);
            in.position(in.position() + array.length * Integer.BYTES);
        }
        logger.exiting("readIntArray");
        return array;
    }

    @Override
    public double[] readDoubleArray(double[] array, Annotation[] fieldAnnotations)
            throws Exception {
        logger.entering("readDoubleArray");
        array = new double[readArraySize(fieldAnnotations)];
        if (array.length > 0) {
            align(Double.BYTES);
            var tmpBuf = in.asDoubleBuffer();
            tmpBuf.get(array);
            in.position(in.position() + array.length * Double.BYTES);
        }
        logger.exiting("readDoubleArray");
        return array;
    }

    @Override
    public float[] readFloatArray(float[] array, Annotation[] fieldAnnotations) throws Exception {
        logger.entering("readFloatArray");
        array = new float[readArraySize(fieldAnnotations)];
        if (array.length > 0) {
            align(Float.BYTES);
            var tmpBuf = in.asFloatBuffer();
            tmpBuf.get(array);
            in.position(in.position() + array.length * Float.BYTES);
        }
        logger.exiting("readFloatArray");
        return array;
    }

    private void align(int n) throws IOException {
        while (in.position() % n != 0) {
            in.get();
        }
    }
}
