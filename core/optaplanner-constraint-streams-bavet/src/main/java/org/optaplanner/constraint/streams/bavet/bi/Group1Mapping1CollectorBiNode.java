/*
 * Copyright 2022 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.optaplanner.constraint.streams.bavet.bi;

import java.util.function.BiFunction;
import java.util.function.Consumer;

import org.optaplanner.constraint.streams.bavet.common.Group;
import org.optaplanner.core.api.score.stream.bi.BiConstraintCollector;

final class Group1Mapping1CollectorBiNode<OldA, OldB, A, B, ResultContainer_>
        extends AbstractGroupBiNode<OldA, OldB, BiTuple<A, B>, A, ResultContainer_, B> {

    private final BiFunction<OldA, OldB, A> groupKeyMapping;
    private final int outputStoreSize;

    public Group1Mapping1CollectorBiNode(BiFunction<OldA, OldB, A> groupKeyMapping, int groupStoreIndex,
            BiConstraintCollector<OldA, OldB, ResultContainer_, B> collector,
            Consumer<BiTuple<A, B>> nextNodesInsert, Consumer<BiTuple<A, B>> nextNodesRetract,
            int outputStoreSize) {
        super(groupStoreIndex, collector, nextNodesInsert, nextNodesRetract);
        this.groupKeyMapping = groupKeyMapping;
        this.outputStoreSize = outputStoreSize;
    }

    @Override
    protected A createGroupKey(BiTuple<OldA, OldB> tuple) {
        return groupKeyMapping.apply(tuple.factA, tuple.factB);
    }

    @Override
    protected BiTuple<A, B> createOutTuple(Group<BiTuple<A, B>, A, ResultContainer_> group) {
        A factA = group.groupKey;
        B factB = finisher.apply(group.resultContainer);
        return new BiTuple<>(factA, factB, outputStoreSize);
    }

    @Override
    public String toString() {
        return "GroupBiNode 1+1";
    }

}