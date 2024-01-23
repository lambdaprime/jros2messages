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
package id.jros2messages.vision_msgs;

import id.jros2messages.std_msgs.HeaderMessage;
import id.jrosmessages.Message;
import id.jrosmessages.MessageMetadata;
import id.jrosmessages.std_msgs.Int32Message;
import id.jrosmessages.std_msgs.StringMessage;
import id.xfunction.XJson;
import java.util.Objects;

/**
 * Definition for vision_msgs/VisionInfo Provides meta-information about a visual pipeline. # # This
 * message serves a similar purpose to sensor_msgs/CameraInfo, but instead # of being tied to
 * hardware, it represents information about a specific # computer vision pipeline. This information
 * stays constant (or relatively # constant) over time, and so it is wasteful to send it with each
 * individual # result. By listening to these messages, subscribers will receive # the context in
 * which published vision messages are to be interpreted. # Each vision pipeline should publish its
 * VisionInfo messages to its own topic, # in a manner similar to CameraInfo.
 */
@MessageMetadata(name = VisionInfoMessage.NAME)
public class VisionInfoMessage implements Message {

    static final String NAME = "vision_msgs/VisionInfo";

    /** Used for sequencing */
    public HeaderMessage header = new HeaderMessage();

    /**
     * Name of the vision pipeline. This should be a value that is meaningful to an outside user.
     */
    public StringMessage method = new StringMessage();

    /**
     * Location where the metadata database is stored. The recommended location is as an XML string
     * on the ROS parameter server, but the exact implementation and information is left up to the
     * user. The database should store information attached to class ids. Each class id should map
     * to an atomic, visually recognizable element. This definition is intentionally vague to allow
     * extreme flexibility. The elements could be classes in a pixel segmentation algorithm, object
     * classes in a detector, different people's faces in a face detection algorithm, etc. Vision
     * pipelines report results in terms of numeric IDs, which map into this database. The
     * information stored in this database is, again, left up to the user. The database could be as
     * simple as a map from ID to class name, or it could include information such as object meshes
     * or colors to use for visualization.
     */
    public StringMessage database_location = new StringMessage();

    /**
     * Metadata database version. This counter is incremented each time the pipeline begins using a
     * new version of the database (useful in the case of online training or user modifications).
     * The counter value can be monitored by listeners to ensure that the pipeline and the listener
     * are using the same metadata.
     */
    public Int32Message database_version = new Int32Message();

    public VisionInfoMessage withHeader(HeaderMessage header) {
        this.header = header;
        return this;
    }

    public VisionInfoMessage withMethod(StringMessage method) {
        this.method = method;
        return this;
    }

    public VisionInfoMessage withDatabaseLocation(StringMessage database_location) {
        this.database_location = database_location;
        return this;
    }

    public VisionInfoMessage withDatabaseVersion(Int32Message database_version) {
        this.database_version = database_version;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(header, method, database_location, database_version);
    }

    @Override
    public boolean equals(Object obj) {
        var other = (VisionInfoMessage) obj;
        return Objects.equals(header, other.header)
                && Objects.equals(method, other.method)
                && Objects.equals(database_location, other.database_location)
                && Objects.equals(database_version, other.database_version);
    }

    @Override
    public String toString() {
        return XJson.asString(
                "header", header,
                "method", method,
                "database_location", database_location,
                "database_version", database_version);
    }
}
