package com.AvA.task.submission.service.service;

import com.AvA.task.submission.service.model.Submission;

import java.util.List;

public interface SubmissionService {

    Submission submitTask(Long taskId, String githubLink, Long userId, String jwt)throws Exception;

    Submission getTaskSubmissionById(Long submissionId)throws Exception;

    List<Submission> getAllTaskSubmissions();
    List<Submission> getTaskSubmissionByTaskId(Long taskId);
    Submission acceptDeclineSubmission(Long id, String status)throws Exception;
}
