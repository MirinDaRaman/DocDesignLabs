package com.doc.lab2.DTO.impl;

import com.doc.lab2.domain.CommisionPlan;
import com.doc.lab2.exceptions.NoSuchInterviewResultException;
import com.doc.lab2.DTO.DTO;
import org.springframework.hateoas.Link;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

public class CommisionPlanDTO extends DTO<CommisionPlan> {
    public CommisionPlanDTO(CommisionPlan interviewResult, Link link) throws NoSuchInterviewResultException {
        super(interviewResult, link);
//        add(linkTo(methodOn(ArtistController.class).getArtistsByProjectId(getEntity().getId())).withRel("artists"));
    }

    public Long getInterviewResultId() {
        return getEntity().getId();
    }

    public String getName() {
        return getEntity().getCommision_plan();
    }


}
