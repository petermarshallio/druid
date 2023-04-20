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

package org.apache.druid.msq.input.inline;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.apache.druid.msq.guice.MSQIndexingModule;
import org.apache.druid.msq.input.InputSpec;
import org.apache.druid.query.InlineDataSource;
import org.apache.druid.segment.TestHelper;
import org.apache.druid.segment.column.ColumnType;
import org.apache.druid.segment.column.RowSignature;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;

public class InlineInputSpecTest
{
  @Test
  public void testSerde() throws Exception
  {
    final ObjectMapper mapper = TestHelper.makeJsonMapper()
                                          .registerModules(new MSQIndexingModule().getJacksonModules());

    final InlineDataSource dataSource = InlineDataSource.fromIterable(
        Collections.singletonList(new Object[]{1}),
        RowSignature.builder().add("x", ColumnType.LONG).build()
    );

    final InlineInputSpec spec = new InlineInputSpec(dataSource);

    Assert.assertEquals(
        spec,
        mapper.readValue(mapper.writeValueAsString(spec), InputSpec.class)
    );
  }

  @Test
  public void testEquals()
  {
    EqualsVerifier.forClass(InlineInputSpec.class).usingGetClass().verify();
  }
}
