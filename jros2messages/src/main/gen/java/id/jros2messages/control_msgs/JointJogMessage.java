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
package id.jros2messages.control_msgs;

import id.jros2messages.std_msgs.HeaderMessage;
import id.jrosmessages.Message;
import id.jrosmessages.MessageMetadata;
import id.jrosmessages.std_msgs.StringMessage;
import id.xfunction.XJson;
import java.util.Arrays;
import java.util.Objects;

/** Definition for control_msgs/JointJog */
@MessageMetadata(
        name = JointJogMessage.NAME,
        fields = {"header", "joint_names", "displacements", "velocities", "duration"})
public class JointJogMessage implements Message {

    static final String NAME = "control_msgs/JointJog";

    /** Used in time-stamping the message. */
    public HeaderMessage header = new HeaderMessage();

    /**
     * Name list of the joints. You don't need to specify all joints of the robot. Joint names are
     * case-sensitive.
     */
    public StringMessage[] joint_names = new StringMessage[0];

    /**
     * A position command to the joints listed in joint_names. The order must be identical. Units
     * are meters or radians. If displacements and velocities are filled, a profiled motion is
     * requested. or position_deltas
     */
    public double[] displacements = new double[0];

    /**
     * A velocity command to the joints listed in joint_names. The order must be identical. Units
     * are m/s or rad/s. If displacements and velocities are filled, a profiled motion is requested.
     */
    public double[] velocities = new double[0];

    public double duration;

    public JointJogMessage withHeader(HeaderMessage header) {
        this.header = header;
        return this;
    }

    public JointJogMessage withJointNames(StringMessage... joint_names) {
        this.joint_names = joint_names;
        return this;
    }

    public JointJogMessage withDisplacements(double... displacements) {
        this.displacements = displacements;
        return this;
    }

    public JointJogMessage withVelocities(double... velocities) {
        this.velocities = velocities;
        return this;
    }

    public JointJogMessage withDuration(double duration) {
        this.duration = duration;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                header,
                Arrays.hashCode(joint_names),
                Arrays.hashCode(displacements),
                Arrays.hashCode(velocities),
                duration);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof JointJogMessage other)
            return Objects.equals(header, other.header)
                    && Arrays.equals(joint_names, other.joint_names)
                    && Arrays.equals(displacements, other.displacements)
                    && Arrays.equals(velocities, other.velocities)
                    && duration == other.duration;
        return false;
    }

    @Override
    public String toString() {
        return XJson.asString(
                "header", header,
                "joint_names", joint_names,
                "displacements", displacements,
                "velocities", velocities,
                "duration", duration);
    }
}
