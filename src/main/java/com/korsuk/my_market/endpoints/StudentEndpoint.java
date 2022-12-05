package com.korsuk.my_market.endpoints;

import com.korsuk.my_market.services.StudentService;
import com.korsuk.my_market.soap.students.GetAllStudentsRequest;
import com.korsuk.my_market.soap.students.GetAllStudentsResponse;
import com.korsuk.my_market.soap.students.GetStudentByNameRequest;
import com.korsuk.my_market.soap.students.GetStudentByNameResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@RequiredArgsConstructor
public class StudentEndpoint {
    private static final String NAMESPACE_URI = "http://www.korsuk.com/spring/market/ws/students";
    private final StudentService studentService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getStudentByNameRequest")
    @ResponsePayload
    public GetStudentByNameResponse getStudentByName(@RequestPayload GetStudentByNameRequest request) {
        GetStudentByNameResponse response = new GetStudentByNameResponse();
        response.setStudent(studentService.getByName(request.getName()));
        return response;
    }

    /*
        Пример запроса: POST http://localhost:8080/ws
        Header -> Content-Type: text/xml

        <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:f="http://www.flamexander.com/spring/ws/students">
            <soapenv:Header/>
            <soapenv:Body>
                <f:getAllStudentsRequest/>
            </soapenv:Body>
        </soapenv:Envelope>
     */

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllStudentsRequest")
    @ResponsePayload
    public GetAllStudentsResponse getAllStudents(@RequestPayload GetAllStudentsRequest request) {
        GetAllStudentsResponse response = new GetAllStudentsResponse();
        studentService.getAllStudents().forEach(response.getStudent()::add);
        return response;
    }
}