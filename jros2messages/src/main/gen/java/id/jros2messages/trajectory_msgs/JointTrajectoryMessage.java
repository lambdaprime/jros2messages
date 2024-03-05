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
package id.jros2messages.trajectory_msgs;

import id.jros2messages.std_msgs.HeaderMessage;
import id.jrosmessages.Message;
import id.jrosmessages.MessageMetadata;
import id.jrosmessages.std_msgs.StringMessage;
import id.jrosmessages.trajectory_msgs.JointTrajectoryPointMessage;
import id.xfunction.XJson;
import java.util.Arrays;
import java.util.Objects;

/** Definition for trajectory_msgs/JointTrajectory */
@MessageMetadata(
        name = JointTrajectoryMessage.NAME,
        fields = {"header", "joint_names", "points"})
public class JointTrajectoryMessage implements Message {

    static final String NAME = "trajectory_msgs/JointTrajectory";

    public HeaderMessage header = new HeaderMessage();

    public StringMessage[] joint_names = new StringMessage[0];

    public JointTrajectoryPointMessage[] points = new JointTrajectoryPointMessage[0];

    public JointTrajectoryMessage withHeader(HeaderMessage header) {
        this.header = header;
        return this;
    }

    public JointTrajectoryMessage withJointNames(StringMessage... joint_names) {
        this.joint_names = joint_names;
        return this;
    }

    public JointTrajectoryMessage withPoints(JointTrajectoryPointMessage... points) {
        this.points = points;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(header, Arrays.hashCode(joint_names), Arrays.hashCode(points));
    }

    @Override
    public boolean equals(Object obj) {
        var other = (JointTrajectoryMessage) obj;
        return Objects.equals(header, other.header)
                && Arrays.equals(joint_names, other.joint_names)
                && Arrays.equals(points, other.points);
    }

    @Override
    public String toString() {
        return XJson.asString(
                "header", header,
                "joint_names", joint_names,
                "points", points);
    }
}
