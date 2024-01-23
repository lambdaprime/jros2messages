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

import id.jrosmessages.Message;
import id.jrosmessages.MessageMetadata;
import id.jrosmessages.geometry_msgs.PoseWithCovarianceMessage;
import id.xfunction.XJson;
import java.util.Objects;

/**
 * Definition for vision_msgs/ObjectHypothesisWithPose An object hypothesis that contains pose
 * information. # If you would like to define an array of ObjectHypothesisWithPose messages, #
 * please see the Detection2D or Detection3D message types.
 */
@MessageMetadata(name = ObjectHypothesisWithPoseMessage.NAME)
public class ObjectHypothesisWithPoseMessage implements Message {

    static final String NAME = "vision_msgs/ObjectHypothesisWithPose";

    /** The object hypothesis (ID and score). */
    public ObjectHypothesisMessage hypothesis = new ObjectHypothesisMessage();

    /**
     * The 6D pose of the object hypothesis. This pose should be defined as the pose of some fixed
     * reference point on the object, such as the geometric center of the bounding box, the center
     * of mass of the object or the origin of a reference mesh of the object. Note that this pose is
     * not stamped; frame information can be defined by parent messages. Also note that different
     * classes predicted for the same input data may have different predicted 6D poses.
     */
    public PoseWithCovarianceMessage pose = new PoseWithCovarianceMessage();

    public ObjectHypothesisWithPoseMessage withHypothesis(ObjectHypothesisMessage hypothesis) {
        this.hypothesis = hypothesis;
        return this;
    }

    public ObjectHypothesisWithPoseMessage withPose(PoseWithCovarianceMessage pose) {
        this.pose = pose;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(hypothesis, pose);
    }

    @Override
    public boolean equals(Object obj) {
        var other = (ObjectHypothesisWithPoseMessage) obj;
        return Objects.equals(hypothesis, other.hypothesis) && Objects.equals(pose, other.pose);
    }

    @Override
    public String toString() {
        return XJson.asString(
                "hypothesis", hypothesis,
                "pose", pose);
    }
}
