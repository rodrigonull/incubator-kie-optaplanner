/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.optaplanner.persistence.jaxb.api.score.buildin.hardsoft;

import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.junit.jupiter.api.Test;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.persistence.jaxb.api.score.AbstractScoreJaxbAdapterTest;

class HardSoftScoreJaxbAdapterTest extends AbstractScoreJaxbAdapterTest {

    @Test
    void serializeAndDeserialize() {
        assertSerializeAndDeserialize(null, new TestHardSoftScoreWrapper(null));

        HardSoftScore score = HardSoftScore.of(1200, 34);
        assertSerializeAndDeserialize(score, new TestHardSoftScoreWrapper(score));

        score = HardSoftScore.ofUninitialized(-7, 1200, 34);
        assertSerializeAndDeserialize(score, new TestHardSoftScoreWrapper(score));
    }

    @XmlRootElement
    public static class TestHardSoftScoreWrapper extends TestScoreWrapper<HardSoftScore> {

        @XmlJavaTypeAdapter(HardSoftScoreJaxbAdapter.class)
        private HardSoftScore score;

        @SuppressWarnings("unused")
        private TestHardSoftScoreWrapper() {
        }

        public TestHardSoftScoreWrapper(HardSoftScore score) {
            this.score = score;
        }

        @Override
        public HardSoftScore getScore() {
            return score;
        }

    }

}
