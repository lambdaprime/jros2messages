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
package id.jros2messages;

import id.jros2messages.geometry_msgs.PoseStampedMessage;
import id.jrosmessages.geometry_msgs.PoseMessage;
import id.jrosmessages.primitives.Time;

/**
 * Message objects transformers.
 *
 * <p>For performance reasons they avoid copying data and copy references instead.
 *
 * @author lambdaprime intid@protonmail.com
 */
public class JRos2MessagesTransformer {

    public PoseStampedMessage toStampedPose(String frameId, PoseMessage poseMessage) {
        var stampedMessage = new PoseStampedMessage();
        // dont set timestamp since it can expire and cause errors
        stampedMessage.header.stamp = new Time();
        stampedMessage.header.frame_id = frameId;
        stampedMessage.pose = poseMessage;
        return stampedMessage;
    }
}
