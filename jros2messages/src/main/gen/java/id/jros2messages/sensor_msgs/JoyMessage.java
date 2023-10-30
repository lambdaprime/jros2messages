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
package id.jros2messages.sensor_msgs;

import id.jros2messages.std_msgs.HeaderMessage;
import id.jrosmessages.Message;
import id.jrosmessages.MessageMetadata;
import id.xfunction.XJson;
import java.util.Arrays;
import java.util.Objects;

/** Definition for sensor_msgs/Joy Reports the state of a joystick's axes and buttons. */
@MessageMetadata(name = JoyMessage.NAME)
public class JoyMessage implements Message {

    static final String NAME = "sensor_msgs/Joy";

    /** The timestamp is the time at which data is received from the joystick. */
    public HeaderMessage header = new HeaderMessage();

    /** The axes measurements from a joystick. */
    public float[] axes = new float[0];

    /** The buttons measurements from a joystick. */
    public int[] buttons = new int[0];

    public JoyMessage withHeader(HeaderMessage header) {
        this.header = header;
        return this;
    }

    public JoyMessage withAxes(float... axes) {
        this.axes = axes;
        return this;
    }

    public JoyMessage withButtons(int... buttons) {
        this.buttons = buttons;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(header, Arrays.hashCode(axes), Arrays.hashCode(buttons));
    }

    @Override
    public boolean equals(Object obj) {
        var other = (JoyMessage) obj;
        return Objects.equals(header, other.header)
                && Arrays.equals(axes, other.axes)
                && Arrays.equals(buttons, other.buttons);
    }

    @Override
    public String toString() {
        return XJson.asString(
                "header", header,
                "axes", axes,
                "buttons", buttons);
    }
}
