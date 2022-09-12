package io.sakila.crud.controller.employee;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.sakila.crud.controller.DepartmentController;
import io.sakila.crud.model.DepartmentDTO;
import io.sakila.crud.model.GlobalResponseDTO;
import io.sakila.crud.model.request.department.DepartmentRequestDTO;
import io.sakila.crud.model.request.department.DepartmentRequestDeletedDTO;
import io.sakila.crud.model.request.department.DepartmentRequestUpdateDTO;
import io.sakila.crud.service.DepartmentService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = DepartmentController.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class DepartmentControllerTest {

    @MockBean
    private DepartmentService departmentService;

    @Autowired
    private MockMvc mockMvc;


    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void findAll() throws Exception {

        List<DepartmentDTO> returnValue = new ArrayList<>();

        Mockito.when(
                departmentService.findAll()
        ).thenReturn(returnValue);
        mockMvc.perform(get("/api/Department/findAll"))
                .andExpect(status().isOk());

    }

    @Test
    void findById() throws Exception {
        String departmentId = "1234";

        DepartmentDTO returnValue = new DepartmentDTO();

        Mockito.when(
                departmentService.findById(Mockito.any())
        ).thenReturn(returnValue);
        mockMvc.perform(get("/api/Department/findById/" + departmentId))
                .andExpect(status().isOk());

    }

    @Test
    void addDepartment() throws Exception {

        final String json = "{\n" +
                "  \"departmentName\": \"string\"\n" +
                "}";

        DepartmentDTO response = new DepartmentDTO();

        DepartmentRequestDTO request = new DepartmentRequestDTO();
        request.setDepartmentName("test");

        Mockito.when(departmentService.addDepartment(Mockito.any())).thenReturn(response);

        mockMvc.perform(post("/api/Department/createDepartment").content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    void updateDepartment() throws Exception {

        final String json = "{\n" +
                "  \"departmentId\": \"1234\",\n" +
                "  \"departmentName\": \"testUpdate\",\n" +
                "  \"updatedBy\": \"Test\"\n" +
                "}";

        DepartmentDTO response = new DepartmentDTO();

        DepartmentRequestUpdateDTO request = new DepartmentRequestUpdateDTO();
        request.setDepartmentId("1234");
        request.setDepartmentName("testUpdate");
        request.setUpdatedBy("Test");

        Mockito.when(departmentService.updateDepartmentById(Mockito.any())).thenReturn(response);

        mockMvc.perform(put("/api/Department/updateDepartment").content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    @Test
    void deleteDepartment() throws Exception {

        final String json = "{\n" +
                "  \"departmentId\": \"1234\",\n" +
                "  \"updatedBy\": \"deletedTest\"\n" +
                "}";

        String response = "1234";

        DepartmentRequestDeletedDTO request = new DepartmentRequestDeletedDTO();
        request.setDepartmentId("1234");
        request.setUpdatedBy("deletedTest");

        Mockito.when(departmentService.deleteDepartmentById(request.getDepartmentId(), request.getUpdatedBy())).thenReturn(response);

        mockMvc.perform(delete("/api/Department/deleteDepartment").content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
