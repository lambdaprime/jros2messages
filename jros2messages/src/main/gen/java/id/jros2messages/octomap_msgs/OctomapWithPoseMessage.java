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
import id.jrosmessages.geometry_msgs.PoseMessage;
import id.xfunction.XJson;
import java.util.Objects;

/** Definition for octomap_msgs/OctomapWithPose */
@MessageMetadata(type = OctomapWithPoseMessage.NAME, md5sum = "2be6b076e8442bde71254392e50395ab")
public class OctomapWithPoseMessage implements Message {

    static final String NAME = "octomap_msgs/OctomapWithPose";

    /** A 3D map in binary format, as Octree */
    public HeaderMessage header = new HeaderMessage();

    /** The pose of the octree with respect to the header frame */
    public PoseMessage origin = new PoseMessage();

    /** The actual octree msg */
    public OctomapMessage octomap = new OctomapMessage();

    public OctomapWithPoseMessage withHeader(HeaderMessage header) {
        this.header = header;
        return this;
    }

    public OctomapWithPoseMessage withOrigin(PoseMessage origin) {
        this.origin = origin;
        return this;
    }

    public OctomapWithPoseMessage withOctomap(OctomapMessage octomap) {
        this.octomap = octomap;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(header, origin, octomap);
    }

    @Override
    public boolean equals(Object obj) {
        var other = (OctomapWithPoseMessage) obj;
        return Objects.equals(header, other.header)
                && Objects.equals(origin, other.origin)
                && Objects.equals(octomap, other.octomap);
    }

    @Override
    public String toString() {
        return XJson.asString(
                "header", header,
                "origin", origin,
                "octomap", octomap);
    }
}
