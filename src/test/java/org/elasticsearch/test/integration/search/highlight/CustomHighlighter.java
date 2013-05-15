/*
 * Licensed to ElasticSearch and Shay Banon under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. ElasticSearch licenses this
 * file to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.elasticsearch.test.integration.search.highlight;

import com.google.common.collect.Lists;
import org.elasticsearch.common.text.StringText;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.search.highlight.HighlightField;
import org.elasticsearch.search.highlight.Highlighter;
import org.elasticsearch.search.highlight.HighlighterContext;
import org.elasticsearch.search.highlight.SearchContextHighlight;

import java.util.List;
import java.util.Map;

/**
 * total dumb highlighter used to test the pluggable highlighting functionality
 */
public class CustomHighlighter implements Highlighter {

    @Override
    public String[] names() {
        return new String[] { "test-custom" };
    }

    @Override
    public HighlightField highlight(HighlighterContext highlighterContext) {
        SearchContextHighlight.Field field = highlighterContext.field;

        List<Text> responses = Lists.newArrayList();
        responses.add(new StringText("standard response"));

        if (field.options() != null) {
            for (Map.Entry<String, Object> entry : field.options().entrySet()) {
                responses.add(new StringText("field:" + entry.getKey() + ":" + entry.getValue()));
            }
        }

        return new HighlightField(highlighterContext.fieldName, responses.toArray(new Text[]{}));
    }
}
