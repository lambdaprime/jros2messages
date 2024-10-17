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
package id.jros2messages.diagnostic_msgs;

import id.jros2messages.std_msgs.HeaderMessage;
import id.jrosmessages.Message;
import id.jrosmessages.MessageMetadata;
import id.jrosmessages.diagnostic_msgs.DiagnosticStatusMessage;
import id.xfunction.XJson;
import java.util.Arrays;
import java.util.Objects;

/**
 * Definition for diagnostic_msgs/DiagnosticArray
 *
 * <p>This message is used to send diagnostic information about the state of the robot.
 */
@MessageMetadata(
        name = DiagnosticArrayMessage.NAME,
        fields = {"header", "status"})
public class DiagnosticArrayMessage implements Message {

    static final String NAME = "diagnostic_msgs/DiagnosticArray";

    /** for timestamp */
    public HeaderMessage header = new HeaderMessage();

    /** an array of components being reported on */
    public DiagnosticStatusMessage[] status = new DiagnosticStatusMessage[0];

    public DiagnosticArrayMessage withHeader(HeaderMessage header) {
        this.header = header;
        return this;
    }

    public DiagnosticArrayMessage withStatus(DiagnosticStatusMessage... status) {
        this.status = status;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(header, Arrays.hashCode(status));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DiagnosticArrayMessage other)
            return Objects.equals(header, other.header) && Arrays.equals(status, other.status);
        return false;
    }

    @Override
    public String toString() {
        return XJson.asString(
                "header", header,
                "status", status);
    }
}
