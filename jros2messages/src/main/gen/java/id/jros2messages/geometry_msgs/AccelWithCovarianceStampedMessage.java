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
package id.jros2messages.geometry_msgs;

import id.jros2messages.std_msgs.HeaderMessage;
import id.jrosmessages.Message;
import id.jrosmessages.MessageMetadata;
import id.jrosmessages.geometry_msgs.AccelWithCovarianceMessage;
import id.xfunction.XJson;
import java.util.Objects;

/**
 * Definition for geometry_msgs/AccelWithCovarianceStamped This represents an estimated accel with
 * reference coordinate frame and timestamp.
 */
@MessageMetadata(
        type = AccelWithCovarianceStampedMessage.NAME,
        md5sum = "cf7b852cab243d76000e69f42f2700f6")
public class AccelWithCovarianceStampedMessage implements Message {

    static final String NAME = "geometry_msgs/AccelWithCovarianceStamped";

    public HeaderMessage header = new HeaderMessage();

    public AccelWithCovarianceMessage accel = new AccelWithCovarianceMessage();

    public AccelWithCovarianceStampedMessage withHeader(HeaderMessage header) {
        this.header = header;
        return this;
    }

    public AccelWithCovarianceStampedMessage withAccel(AccelWithCovarianceMessage accel) {
        this.accel = accel;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(header, accel);
    }

    @Override
    public boolean equals(Object obj) {
        var other = (AccelWithCovarianceStampedMessage) obj;
        return Objects.equals(header, other.header) && Objects.equals(accel, other.accel);
    }

    @Override
    public String toString() {
        return XJson.asString(
                "header", header,
                "accel", accel);
    }
}
