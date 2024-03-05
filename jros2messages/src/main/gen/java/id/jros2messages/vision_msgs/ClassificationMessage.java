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
import id.xfunction.XJson;
import java.util.Arrays;
import java.util.Objects;

/**
 * Definition for vision_msgs/Classification
 *
 * <p>Defines a classification result.
 *
 * <p>This result does not contain any position information. It is designed for classifiers, which
 * simply provide class probabilities given an instance of source data (e.g., an image or a point
 * cloud).
 */
@MessageMetadata(
        name = ClassificationMessage.NAME,
        fields = {"header", "results"})
public class ClassificationMessage implements Message {

    static final String NAME = "vision_msgs/Classification";

    public HeaderMessage header = new HeaderMessage();

    /**
     * A list of class probabilities. This list need not provide a probability for every possible
     * class, just ones that are nonzero, or above some user-defined threshold.
     */
    public ObjectHypothesisMessage[] results = new ObjectHypothesisMessage[0];

    public ClassificationMessage withHeader(HeaderMessage header) {
        this.header = header;
        return this;
    }

    public ClassificationMessage withResults(ObjectHypothesisMessage... results) {
        this.results = results;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(header, Arrays.hashCode(results));
    }

    @Override
    public boolean equals(Object obj) {
        var other = (ClassificationMessage) obj;
        return Objects.equals(header, other.header) && Arrays.equals(results, other.results);
    }

    @Override
    public String toString() {
        return XJson.asString(
                "header", header,
                "results", results);
    }
}
