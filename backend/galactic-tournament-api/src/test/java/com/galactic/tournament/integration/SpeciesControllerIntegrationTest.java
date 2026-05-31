package com.galactic.tournament.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class SpeciesControllerIntegrationTest {

        @Autowired
        private MockMvc mockMvc;

        @Test
        void shouldCreateSpeciesSuccessfully()
                        throws Exception {

                String request = """
                                {
                                    "name":"Marciano",
                                    "powerLevel":3000,
                                    "specialAbility":"Invisibilidad"
                                }
                                """;

                mockMvc.perform(

                                post("/api/species")
                                                .contentType("application/json")
                                                .content(request)

                )
                                .andExpect(
                                                status().isOk());

        }

        @Test
        void shouldReturnBadRequestWhenNameIsEmpty()
                        throws Exception {

                String request = """
                                {
                                    "name":"",
                                    "powerLevel":3000,
                                    "specialAbility":"Invisibilidad"
                                }
                                """;

                mockMvc.perform(

                                post("/api/species")
                                                .contentType("application/json")
                                                .content(request)

                )
                                .andExpect(
                                                status().isBadRequest());

        }

        @Test
        void shouldReturnSpeciesList()
                        throws Exception {

                mockMvc.perform(

                                get("/api/species")

                )
                                .andExpect(
                                                status().isOk());

        }

        @Test
        void shouldCreateBattleSuccessfully()
                        throws Exception {

                String speciesOne = """
                                {
                                    "name":"Viltrumita",
                                    "powerLevel":8000,
                                    "specialAbility":"Volar"
                                }
                                """;

                String speciesTwo = """
                                {
                                    "name":"Namekusein",
                                    "powerLevel":5000,
                                    "specialAbility":"Regeneracion"
                                }
                                """;

                mockMvc.perform(

                                post("/api/species")
                                                .contentType("application/json")
                                                .content(speciesOne)

                );

                mockMvc.perform(

                                post("/api/species")
                                                .contentType("application/json")
                                                .content(speciesTwo)

                );

                String battleRequest = """
                                {
                                    "speciesOneId":4,
                                    "speciesTwoId":5
                                }
                                """;

                mockMvc.perform(

                                post("/api/battles")
                                                .contentType("application/json")
                                                .content(battleRequest)

                )
                                .andExpect(
                                                status().isOk())
                                .andExpect(
                                                jsonPath("$.winner")
                                                                .value("Zaitama"));

        }
}