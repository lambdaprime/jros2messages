/*
 * Copyright 2023 jrosclient project
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
package id.jros2messages.impl;

/**
 * @author lambdaprime intid@protonmail.com
 */
public interface MessageConstants {
    String CHAR_ERROR =
            "ROS built-in type char in jrosclient Java Message definitions should be mapped to"
                    + " byte. Please update your Java Message class and use byte instead of char."
                    + " See documentation for details http://portal2.atwebpages.com/jrosclient";
    String CHAR_ARRAY_ERROR =
            "ROS built-in type char[] in jrosclient Java Message definitions should be mapped"
                    + " to byte[]. Please update your Java Message class and use byte[] instead of"
                    + " char[]. See documentation for details"
                    + " http://portal2.atwebpages.com/jrosclient";
}
