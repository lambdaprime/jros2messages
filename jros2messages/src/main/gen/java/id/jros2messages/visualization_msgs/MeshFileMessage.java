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
package id.jros2messages.visualization_msgs;

import id.jrosmessages.Message;
import id.jrosmessages.MessageMetadata;
import id.jrosmessages.std_msgs.StringMessage;
import id.xfunction.XJson;
import java.util.Arrays;
import java.util.Objects;

/** Definition for msg/MeshFile Used to send raw mesh files. */
@MessageMetadata(name = MeshFileMessage.NAME)
public class MeshFileMessage implements Message {

    static final String NAME = "visualization_msgs/MeshFile";

    /**
     * The filename is used for both debug purposes and to provide a file extension for whatever
     * parser is used.
     */
    public StringMessage filename = new StringMessage();

    /** This stores the raw text of the mesh file. */
    public byte[] data = new byte[0];

    public MeshFileMessage withFilename(StringMessage filename) {
        this.filename = filename;
        return this;
    }

    public MeshFileMessage withData(byte... data) {
        this.data = data;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(filename, Arrays.hashCode(data));
    }

    @Override
    public boolean equals(Object obj) {
        var other = (MeshFileMessage) obj;
        return Objects.equals(filename, other.filename) && Arrays.equals(data, other.data);
    }

    @Override
    public String toString() {
        return XJson.asString(
                "filename", filename,
                "data", data);
    }
}
