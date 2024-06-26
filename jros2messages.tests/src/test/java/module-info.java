/*
 * Copyright 2022 jrosmessages project
 *
 * Website: https://github.com/lambdaprime/jrosmessages
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
/**
 * @author lambdaprime intid@protonmail.com
 */
open module jros2messages.tests {
    requires jros2messages;
    requires id.xfunction;
    requires org.junit.jupiter.api;
    requires org.junit.jupiter.params;
    requires org.junit.platform.commons;
    requires jrosmessages.tests;

    exports id.jros2messages.tests;
}
