@GenericGenerator(
        name = "ID_GENERATOR",
        strategy = "enhanced-sequence",
        parameters = {
                @Parameter(
                        name = "sequence_name",
                        value = "WORDS_SEQUENCE"
                ),
                @Parameter(
                        name = "initial_value",
                        value = "1000"
                )
        })
package jlearning.words.dao;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;