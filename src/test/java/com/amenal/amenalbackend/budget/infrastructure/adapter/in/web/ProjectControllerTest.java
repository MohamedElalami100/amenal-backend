package com.amenal.amenalbackend.budget.infrastructure.adapter.in.web;

import com.amenal.amenalbackend.budget.core.domain.Project;
import com.amenal.amenalbackend.budget.core.port.in.ProjectUseCase;
import com.amenal.amenalbackend.security.auth.AuthenticationService;
import com.amenal.amenalbackend.security.auth.RegisterRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import static com.amenal.amenalbackend.security.user.Role.ADMIN;
import static org.hamcrest.Matchers.containsString;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class ProjectControllerTest {

        private MockMvc mockMvc;

        private ObjectMapper objectMapper;

        private ProjectUseCase projectDaoAdapter;

        private AuthenticationService authenticationService;

        private String adminToken;

        @Autowired
        public ProjectControllerTest(MockMvc mockMvc, ProjectUseCase projectDaoAdapter,
                                     AuthenticationService authenticationService) {
                this.mockMvc = mockMvc;
                this.projectDaoAdapter = projectDaoAdapter;
                this.authenticationService = authenticationService;
                this.objectMapper = new ObjectMapper();
        }

        @BeforeEach
        public void setUp() throws Exception {
                // Create admin user before each test
                RegisterRequest adminRequest = RegisterRequest.builder()
                        .firstname("Admin")
                        .lastname("Admin")
                        .email("admino@mail.com")
                        .password("password")
                        .role(ADMIN)
                        .build();

                // Register admin user and retrieve JWT token
                adminToken = authenticationService.register(adminRequest).getAccessToken();
        }

        @Test
        public void testFindProjectById() throws Exception {
                Project project = new Project();
                project.setId(1);
                project.setDoc("doc");
                project.setCreatedBy(1);


                Project savedProject = projectDaoAdapter.saveProject(project);

                mockMvc.perform(
                                MockMvcRequestBuilders.get("/projects/{id}", savedProject.getId())
                                        .header("Authorization", "Bearer " + adminToken))
                                .andExpect(
                                                MockMvcResultMatchers.status().isOk())
                                .andExpect(
                                                MockMvcResultMatchers.jsonPath("$.doc").value("doc"));
        }

        @Test
        public void testFindAllProjects() throws Exception {
                Project project = new Project();
                project.setId(1);
                project.setDoc("doc");
                project.setCreatedBy(1);

                projectDaoAdapter.saveProject(project);

                mockMvc.perform(
                                MockMvcRequestBuilders.get("/projects")
                                        .header("Authorization", "Bearer " + adminToken))
                                .andExpect(
                                                MockMvcResultMatchers.status().isOk())
                                .andExpect(
                                                MockMvcResultMatchers.jsonPath("$[0].doc").value("doc"));
        }

        @Test
        public void testSaveProject() throws Exception {
                Project project = new Project();
                project.setDoc("doc");
                project.setCreatedBy(1);

                mockMvc.perform(
                                MockMvcRequestBuilders.post("/projects")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(objectMapper.writeValueAsString(project)))
                                .andExpect(
                                                MockMvcResultMatchers.status().isCreated())
                                .andExpect(
                                                MockMvcResultMatchers.jsonPath("$.doc").value("doc"));
        }

        @Test
        public void testUpdateProject() throws Exception {
                Project project = new Project();
                project.setDoc("doc");
                project.setCreatedBy(1);

                Project savedProject = projectDaoAdapter.saveProject(project);

                mockMvc.perform(
                                MockMvcRequestBuilders.put("/projects")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(objectMapper.writeValueAsString(savedProject)))
                                .andExpect(
                                                MockMvcResultMatchers.status().isOk())
                                .andExpect(
                                                MockMvcResultMatchers.jsonPath("$.doc").value("doc"));
        }

        @Test
        public void testDeleteProject() throws Exception {
                Project project = new Project();
                project.setId(1);
                project.setDoc("doc");
                project.setCreatedBy(1);

                Project savedProject = projectDaoAdapter.saveProject(project);

                mockMvc.perform(
                                MockMvcRequestBuilders.delete("/projects/{id}", savedProject.getId())).andExpect(
                                                MockMvcResultMatchers.status().isNoContent());
        }

        @Test
        public void testFigerProjectWithNoSons() throws Exception {
                Project project = new Project();
                project.setDoc("doc");
                project.setCreatedBy(1);

                Project savedProject = projectDaoAdapter.saveProject(project);

                System.out.println(projectDaoAdapter.figer(savedProject.getId()));
                mockMvc.perform(
                                MockMvcRequestBuilders.patch("/projects/figer/{id}", savedProject.getId())).andExpect(
                                                MockMvcResultMatchers.status().isBadRequest())
                                .andExpect(
                                                MockMvcResultMatchers.content()
                                                                .string(containsString("pas de lots associé")))
                                .andExpect(
                                                MockMvcResultMatchers.content()
                                                                .string(containsString("pas de avenants associé")));
        }

}