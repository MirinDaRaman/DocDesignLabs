package com.doc.lab2.controller;

import com.doc.lab2.exceptions.*;
import com.doc.lab2.DTO.impl.MessageDTO;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoSuchVacancyException.class)
    ResponseEntity<MessageDTO> handleNoSushCityException(){
        return new ResponseEntity<MessageDTO>(new MessageDTO("Such salary not found"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoSuchInterviewResultException.class)
    ResponseEntity<MessageDTO> handleNoSushPersonException() {
        return new ResponseEntity<MessageDTO>(new MessageDTO("Such person not found"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoSuchCandidateException.class)
    ResponseEntity<MessageDTO> handleNoSushBookException() {
        return new ResponseEntity<MessageDTO>(new MessageDTO("Such book not found"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ExistsCandidatesForVacancyException.class)
    ResponseEntity<MessageDTO> handleExistsPersonsForCityException() {
        return new ResponseEntity<MessageDTO>(new MessageDTO("Delete imposible. There are worker"), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ExistsCandidatesForInterviewResultException.class)
    ResponseEntity<MessageDTO> handleExistsBooksForPersonException() {
        return new ResponseEntity<MessageDTO>(new MessageDTO("Delete imposible. There are books for this person"), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ExistsInterviewResultForCandidatesException.class)
    ResponseEntity<MessageDTO> handleExistsPersonsForBookException() {
        return new ResponseEntity<MessageDTO>(new MessageDTO("Delete imposible. There are persons for this book"), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(AlreadyExistsCandidateInInterviewResultException.class)
    ResponseEntity<MessageDTO> handleAlreadyExistsBookInPersonExceptionException() {
        return new ResponseEntity<MessageDTO>(new MessageDTO("Add imposible. The person already contain this book"), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(CandidateAbsentException.class)
    ResponseEntity<MessageDTO> handleBookAbsentException() {
        return new ResponseEntity<MessageDTO>(new MessageDTO("Now this book is absent"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InterviewResultHasNotCandidateException.class)
    ResponseEntity<MessageDTO> handlePersonHasNotBookException() {
        return new ResponseEntity<MessageDTO>(new MessageDTO("The person hasn't this book"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoSuchLogException.class)
    ResponseEntity<MessageDTO> handleNoSuchLogException() {
        return new ResponseEntity<MessageDTO>(new MessageDTO("Such log not found"), HttpStatus.NOT_FOUND);
    }

}
