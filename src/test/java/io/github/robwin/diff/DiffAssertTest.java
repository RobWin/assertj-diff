/*
 *
 *  Copyright 2016 Robert Winkler
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 *
 */
package io.github.robwin.diff;

import org.assertj.core.api.Assertions;
import org.junit.ComparisonFailure;
import org.junit.Test;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DiffAssertTest {

    @Test
    public void shouldNotFindAnyDifferences() throws URISyntaxException {
        Path actualFile = Paths.get(DiffAssertTest.class.getResource("/actualFile.txt").toURI());
        Path expectedFile = Paths.get(DiffAssertTest.class.getResource("/expectedFile.txt").toURI());
        Path reportPath = Paths.get("build/diff-result.txt");

        DiffAssertions.assertThat(actualFile).isEqualTo(expectedFile, reportPath);
    }

    @Test
    public void shouldNotFail() throws URISyntaxException {
        Path actualFile = Paths.get(DiffAssertTest.class.getResource("/actualFile.txt").toURI());
        Path expectedFile = Paths.get(DiffAssertTest.class.getResource("/wrongExpectedFile.txt").toURI());
        Path reportPath = Paths.get("build/diff-result.txt");
        try{
            DiffAssertions.assertThat(actualFile).isEqualTo(expectedFile, reportPath);
        }catch (ComparisonFailure ex){
            Assertions.assertThat(ex).hasMessageContaining("The content of the following files differ.");
        }


    }
}
