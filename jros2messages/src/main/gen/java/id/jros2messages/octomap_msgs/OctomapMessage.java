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
package id.jros2messages.octomap_msgs;

import id.jros2messages.std_msgs.HeaderMessage;
import id.jrosmessages.Message;
import id.jrosmessages.MessageMetadata;
import id.jrosmessages.std_msgs.StringMessage;
import id.xfunction.XJson;
import java.util.Arrays;
import java.util.Objects;

/** Definition for octomap_msgs/Octomap */
@MessageMetadata(type = OctomapMessage.NAME, md5sum = "32f3044beff16cd691446b68085d739b")
public class OctomapMessage implements Message {

    static final String NAME = "octomap_msgs/Octomap";

    /** A 3D map in binary format, as Octree */
    public HeaderMessage header = new HeaderMessage();

    /** Flag to denote a binary (only free/occupied) or full occupancy octree (.bt/.ot file) */
    public boolean binary;

    /** Class id of the contained octree */
    public StringMessage id = new StringMessage();

    /** Resolution (in m) of the smallest octree nodes */
    public double resolution;

    /** binary serialization of octree, use conversions.h to read and write octrees */
    public byte[] data = new byte[0];

    public OctomapMessage withHeader(HeaderMessage header) {
        this.header = header;
        return this;
    }

    public OctomapMessage withBinary(boolean binary) {
        this.binary = binary;
        return this;
    }

    public OctomapMessage withId(StringMessage id) {
        this.id = id;
        return this;
    }

    public OctomapMessage withResolution(double resolution) {
        this.resolution = resolution;
        return this;
    }

    public OctomapMessage withData(byte... data) {
        this.data = data;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(header, binary, id, resolution, Arrays.hashCode(data));
    }

    @Override
    public boolean equals(Object obj) {
        var other = (OctomapMessage) obj;
        return Objects.equals(header, other.header)
                && binary == other.binary
                && Objects.equals(id, other.id)
                && resolution == other.resolution
                && Arrays.equals(data, other.data);
    }

    @Override
    public String toString() {
        return XJson.asString(
                "header", header,
                "binary", binary,
                "id", id,
                "resolution", resolution,
                "data", data);
    }
}
