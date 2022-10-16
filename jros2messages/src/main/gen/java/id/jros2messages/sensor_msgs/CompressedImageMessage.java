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

/** Definition for msg/CompressedImage This message contains a compressed image. */
@MessageMetadata(name = CompressedImageMessage.NAME)
public class CompressedImageMessage implements Message {

    static final String NAME = "sensor_msgs/CompressedImage";

    /**
     * Header timestamp should be acquisition time of image Header frame_id should be optical frame
     * of camera origin of frame should be optical center of cameara +x should point to the right in
     * the image +y should point down in the image +z should point into to plane of the image
     * Specifies the format of the data
     */
    public HeaderMessage header = new HeaderMessage();

    /** Acceptable values: jpeg, png, tiff Compressed image buffer */
    public StringMessage format = new StringMessage();

    /** Compressed image buffer */
    public byte[] data = new byte[0];

    public CompressedImageMessage withHeader(HeaderMessage header) {
        this.header = header;
        return this;
    }

    public CompressedImageMessage withFormat(StringMessage format) {
        this.format = format;
        return this;
    }

    public CompressedImageMessage withData(byte... data) {
        this.data = data;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(header, format, Arrays.hashCode(data));
    }

    @Override
    public boolean equals(Object obj) {
        var other = (CompressedImageMessage) obj;
        return Objects.equals(header, other.header)
                && Objects.equals(format, other.format)
                && Arrays.equals(data, other.data);
    }

    @Override
    public String toString() {
        return XJson.asString(
                "header", header,
                "format", format,
                "data", data);
    }
}
