package com.mkolongo.judgesystem.contollers;

import com.mkolongo.judgesystem.domain.dtos.*;
import com.mkolongo.judgesystem.services.base.*;
import com.mkolongo.judgesystem.util.base.FileUtil;
import com.mkolongo.judgesystem.util.base.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final SubmissionService submissionService;
    private final CategoryService categoryService;
    private final StrategyService strategyService;
    private final ContestService contestService;
    private final ProblemService problemService;
    private final UserService userService;
    private final FileUtil fileUtil;
    private final Parser parser;

    @Autowired
    public ConsoleRunner(SubmissionService submissionService, CategoryService categoryService, StrategyService strategyService,
                         ContestService contestService, ProblemService problemService, UserService userService,
                         FileUtil fileUtil, Parser parser) {
        this.submissionService = submissionService;
        this.categoryService = categoryService;
        this.strategyService = strategyService;
        this.contestService = contestService;
        this.problemService = problemService;
        this.userService = userService;
        this.fileUtil = fileUtil;
        this.parser = parser;
    }

    @Override
    public void run(String... args) throws Exception {
//        seedCategories();
//        seedStrategies();
//        seedContests();
//        seedUsers();
//        enrollUsersToContests();
//        seedProblems();
        seedSubmissions();
    }

    private void seedSubmissions() throws IOException, JAXBException {
        final String submissionsXmlString = fileUtil.readFile("files/submissions.xml");
        final SubmissionListImportDto submissionListImportDto =
                parser.fromXmlString(submissionsXmlString, SubmissionListImportDto.class);
        submissionService.importSubmissions(submissionListImportDto);
    }

    private void seedProblems() throws IOException, JAXBException {
        final String problemsXmlString = fileUtil.readFile("files/problems.xml");
        final ProblemListImportDto problemListImportDto = parser.fromXmlString(problemsXmlString, ProblemListImportDto.class);
        problemService.importProblems(problemListImportDto);
    }

    private void enrollUsersToContests() throws IOException, JAXBException {
        final String readFile = fileUtil.readFile("files/user_participations.xml");
        final ParticipationListDto participationListDto = parser.fromXmlString(readFile, ParticipationListDto.class);

        userService.enrollUsers(participationListDto);
    }

    private void seedUsers() throws IOException {
        final String userJsonString = fileUtil.readFile("files/users.json");
        final UserImportDto[] userImportDtos = parser.fromJsonString(userJsonString, UserImportDto[].class);
        userService.importUsers(userImportDtos);
    }

    private void seedContests() throws IOException {
        final String contestsJsonString = fileUtil.readFile("files/contests.json");
        final ContestImportDto[] contestImportDtos = parser.fromJsonString(contestsJsonString, ContestImportDto[].class);
        contestService.importContests(contestImportDtos);
    }

    private void seedStrategies() throws IOException {
        final String strategiesJsonString = fileUtil.readFile("files/strategies.json");
        final StrategyImportDto[] strategyImportDtos = parser.fromJsonString(strategiesJsonString, StrategyImportDto[].class);
        strategyService.importStrategies(strategyImportDtos);
    }

    private void seedCategories() throws IOException {
        final String categoriesJsonString = fileUtil.readFile("files/categories.json");
        final CategoryImportDto[] categoryImportDtos = parser.fromJsonString(categoriesJsonString, CategoryImportDto[].class);
        categoryService.importCategories(categoryImportDtos);
    }
}
