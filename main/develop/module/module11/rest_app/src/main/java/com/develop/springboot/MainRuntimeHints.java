/*
 * Copyright 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.develop.springboot;

import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;


/**
 * MainRuntimeHints class is a runtime hints registrar.
 * It provides methods for registering runtime hints.
 * <p> It implements RuntimeHintsRegistrar interface, which means it will be used for registering runtime hints.
 * <note> Currently this class is not used in the project.
 */
public class MainRuntimeHints implements RuntimeHintsRegistrar {

    @Override
    public void registerHints(RuntimeHints hints, ClassLoader classLoader) {
//		hints.resources().registerPattern("classpath:/views/");
//		hints.resources().registerPattern("data-load/*"); // All CSV files and similar resources
//		hints.resources().registerPattern("spring/*"); // All CSV files and similar resources
//		hints.resources().registerPattern("messages/*"); // For internationalization
        // plus any other necessary resources or configurations
    }

}
