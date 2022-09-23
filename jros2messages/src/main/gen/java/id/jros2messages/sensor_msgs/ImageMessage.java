/*
 * Copyright 2021 jrosclient project
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
package id.jros2messages.sensor_msgs;

import id.jros2messages.std_msgs.HeaderMessage;
import id.jrosmessages.Message;
import id.jrosmessages.MessageMetadata;
import id.jrosmessages.std_msgs.StringMessage;
import id.xfunction.XJson;
import java.util.Arrays;
import java.util.Objects;

/**
 * Definition for msg/Image This message contains an uncompressed image # (0, 0) is at top-left
 * corner of image
 */
@MessageMetadata(name = ImageMessage.NAME)
public class ImageMessage implements Message {

    static final String NAME = "sensor_msgs/Image";

    /** Header timestamp should be acquisition time of image */
    public HeaderMessage header = new HeaderMessage();

    /**
     * Header frame_id should be optical frame of camera origin of frame should be optical center of
     * cameara +x should point to the right in the image +y should point down in the image +z should
     * point into to plane of the image If the frame_id here and the frame_id of the CameraInfo
     * message associated with the image conflict the behavior is undefined image height, that is,
     * number of rows
     */
    public int height;

    /** image width, that is, number of columns */
    public int width;

    /**
     * The legal values for encoding are in file src/image_encodings.cpp If you want to standardize
     * a new string format, join ros-users@lists.ros.org and send an email proposing a new encoding.
     * Encoding of pixels -- channel meaning, ordering, size
     */
    public StringMessage encoding = new StringMessage();

    /**
     * taken from the list of strings in include/sensor_msgs/image_encodings.hpp is this data
     * bigendian?
     */
    public byte is_bigendian;

    /** Full row length in bytes */
    public int step;

    /** actual matrix data, size is (step * rows) */
    public byte[] data = new byte[0];

    public ImageMessage withHeader(HeaderMessage header) {
        this.header = header;
        return this;
    }

    public ImageMessage withHeight(int height) {
        this.height = height;
        return this;
    }

    public ImageMessage withWidth(int width) {
        this.width = width;
        return this;
    }

    public ImageMessage withEncoding(ImageEncoding encoding) {
        this.encoding = encoding.getName();
        return this;
    }

    public ImageMessage withIsBigendian(byte is_bigendian) {
        this.is_bigendian = is_bigendian;
        return this;
    }

    public ImageMessage withStep(int step) {
        this.step = step;
        return this;
    }

    public ImageMessage withData(byte... data) {
        this.data = data;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                header, height, width, encoding, is_bigendian, step, Arrays.hashCode(data));
    }

    @Override
    public boolean equals(Object obj) {
        var other = (ImageMessage) obj;
        return Objects.equals(header, other.header)
                && height == other.height
                && width == other.width
                && Objects.equals(encoding, other.encoding)
                && is_bigendian == other.is_bigendian
                && step == other.step
                && Arrays.equals(data, other.data);
    }

    @Override
    public String toString() {
        return XJson.asString(
                "header", header,
                "height", height,
                "width", width,
                "encoding", encoding,
                "is_bigendian", is_bigendian,
                "step", step,
                "data.length", data.length);
    }
}
