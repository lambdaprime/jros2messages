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
package id.jros2messages.sensor_msgs;

import id.jros2messages.std_msgs.HeaderMessage;
import id.jrosmessages.Message;
import id.jrosmessages.MessageMetadata;
import id.xfunction.XJson;
import java.util.Arrays;
import java.util.Objects;

/**
 * Definition for sensor_msgs/JointState
 *
 * <p>This is a message that holds data to describe the state of a set of torque controlled joints.
 *
 * <p>This message consists of a multiple arrays, one for each part of the joint state. The goal is
 * to make each of the fields optional. When e.g. your joints have no effort associated with them,
 * you can leave the effort array empty.
 *
 * <p>All arrays in this message should have the same size, or be empty. This is the only way to
 * uniquely associate the joint name with the correct states.
 */
@MessageMetadata(name = JointStateMessage.NAME, md5sum = "3066dcd76a6cfaef579bd0f34173e9fd")
public class JointStateMessage implements Message {

    static final String NAME = "sensor_msgs/JointState";

    /**
     * The header specifies the time at which the joint states were recorded. All the joint states
     * in one message have to be recorded at the same time.
     */
    public HeaderMessage header = new HeaderMessage();

    /** Each joint is uniquely identified by its name */
    public String[] name = new String[0];

    /** The position of the joint (rad or m) */
    public double[] position = new double[0];

    /** The velocity of the joint (rad/s or m/s) */
    public double[] velocity = new double[0];

    /**
     * The effort that is applied in the joint (Nm or N). When e.g. your joints have no effort
     * associated with them, you can leave the effort array empty.
     */
    public double[] effort = new double[0];

    public JointStateMessage() {}

    public JointStateMessage withHeader(HeaderMessage header) {
        this.header = header;
        return this;
    }

    public JointStateMessage withNames(String... name) {
        this.name = name;
        return this;
    }

    public JointStateMessage withPositions(double... position) {
        this.position = position;
        return this;
    }

    public JointStateMessage withVelocities(double... velocity) {
        this.velocity = velocity;
        return this;
    }

    public JointStateMessage withEfforts(double... effort) {
        this.effort = effort;
        return this;
    }

    @Override
    public String toString() {
        return XJson.asString(
                "header", header,
                "name", name,
                "position", position,
                "velocity", velocity,
                "effort", effort);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                header,
                Arrays.hashCode(name),
                Arrays.hashCode(position),
                Arrays.hashCode(velocity),
                Arrays.hashCode(effort));
    }

    @Override
    public boolean equals(Object obj) {
        JointStateMessage other = (JointStateMessage) obj;
        return Objects.equals(header, other.header)
                && Arrays.equals(name, other.name)
                && Arrays.equals(position, other.position)
                && Arrays.equals(velocity, other.velocity)
                && Arrays.equals(effort, other.effort);
    }
}
