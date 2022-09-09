package com.ntrungthanh1.subjectTest.service.impl;

import com.ntrungthanh1.subjectTest.dto.OptionDTO;
import com.ntrungthanh1.subjectTest.dto.QuestionDTO;
import com.ntrungthanh1.subjectTest.exception.ServiceException;
import com.ntrungthanh1.subjectTest.exception.custom.NotFoundException;
import com.ntrungthanh1.subjectTest.model.entity.*;
import com.ntrungthanh1.subjectTest.model.projection.AnswerProjection;
import com.ntrungthanh1.subjectTest.model.projection.QuestionProjection;
import com.ntrungthanh1.subjectTest.model.response.custom.QuestionResponse;
import com.ntrungthanh1.subjectTest.repository.*;
import com.ntrungthanh1.subjectTest.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private QuestionTypeRepository questionTypeRepository;

    @Autowired
    private OptionRepository optionRepository;

    @Autowired
    private TestRepository testRepository;

    @Autowired
    private TestingDetailRepository testingDetailRepository;

    @Autowired
    private TestingResultRepository testingResultRepository;

    @Autowired
    private AnswerSheetRepository answerSheetRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public QuestionResponse getListQuestionByTest(Long testId , Long userId , Long testingResultId){
        if(!userRepository.existsById(userId)){
            throw new NotFoundException("Not found User");
        }
        Test test = Optional.ofNullable(testRepository.findTestById(testId))
                .orElseThrow(() ->  new NotFoundException("Not found test"));
        TestingResult testingResult = Optional.ofNullable(testingResultRepository.getTestResultById(testingResultId))
                .orElseThrow(() -> new NotFoundException("Not found testing result"));
        //check user have capable Test?     based one Finish time
        if(testingResult.getFinishTime() != null ){
            throw new ServiceException("You do not Test");
        }
            //get list old answer  from testingResult id
        List<AnswerProjection> listAnswer =  Optional.ofNullable(answerSheetRepository.findByTestingResult(testingResult.getId()))
                .orElseThrow(() -> new NotFoundException("Not found list answer"));

        //get list Question by test id and test code
        QuestionResponse result = getListQuestionResponse(testId , testingResult.getTestCode());
        result.setTestId(test.getId());
        result.setTestCode(testingResult.getTestCode());
        result.setTimer(testingResult.getTimer());
        List<Long> listChoiceAnswer = new ArrayList<>();

        //check Did user do test before?
        if(!listAnswer.isEmpty()){
            for (int i = 0 ; i < listAnswer.size() ; i++){
                listChoiceAnswer.add(listAnswer.get(i).getOption().getId());
            }
            result.setChoosenAnswers(listChoiceAnswer);
        }

        return result;
    }

    public QuestionResponse getListQuestionResponse(Long testId , String testCode){
        QuestionResponse result = new QuestionResponse();
            result.setListQuestion(getListQuestionByTest(testId, testCode));
        return result;
    }
    @Override
    public List<QuestionProjection> getListQuestionByTest(Long testId , String testCode){
        if(!testRepository.existsById(testId)){
            throw new NotFoundException("Not found Test by Id");
        }

        List<QuestionProjection> list =  Optional.ofNullable(questionRepository.findListByTestCode(testCode , testId))
                .orElseThrow(() -> new NotFoundException("Not found list question "));
        if(list.isEmpty()){
            throw  new ServiceException("Get list question is fail");
        }
        return list;
    }

    @Override
    public List<QuestionProjection> getListQuestion() {
        return questionRepository.findAllQuestion();
    }

    @Override
    public boolean createQuestion(QuestionDTO questionDTO) {
        //check questionType , subjectId , question is null
        if (!checkWithTypeQuestion(questionDTO.getQuestionTypeId().intValue(), questionDTO.getOptions()) ||              //check Option for each question type
                !checkNonDuplicateOption(questionDTO.getOptions()))                                                         // check duplicate option
            return false;


        QuestionType questionType = questionTypeRepository.findQuestionTypeById(questionDTO.getQuestionTypeId());
        Subject subject = subjectRepository.findSubjectById(questionDTO.getSubjectId());
        Question questionName = questionRepository.findByQuestion(questionDTO.getQuestion());
        Question questionInsert = new Question();

        //check question type id , subject id , question name in db
        if (questionType == null ||
                subject == null ||
                questionName != null) {
            return false;
        } else {

            questionInsert.setQuestionType(questionType);
            questionInsert.setSubjects(subject);
            questionInsert.setQuestion(questionDTO.getQuestion());
            questionInsert.setMark(questionDTO.getMark());
            questionInsert.setShuffled(questionDTO.isShuffle());

            Question newQuestion = questionRepository.save(questionInsert);
            //set list Option from list OptionDTO
            for (OptionDTO optionDTO : questionDTO.getOptions()) {
                Option option = new Option();
                Random random = new Random();
                long ranNumberId = random.nextLong();

                option.setId(ranNumberId);
                option.setOptionContent(optionDTO.getOptionContent());
                option.setCorrect(optionDTO.isCorrect());
                option.setQuestion(newQuestion);

                //insert option in db
                optionRepository.save(option);
            }
            return true;
        }

    }

    @Override
    public Question getQuestionByQuestion(String question) {
        return questionRepository.findByQuestion(question);
    }

    @Override
    public QuestionProjection getQuestionById(Long id) {
        return questionRepository.findQuestionById(id);
    }

    public boolean checkWithTypeQuestion(int typeId, List<OptionDTO> list) {
        int count = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isCorrect()) count = count + 1;
        }
        switch (typeId) {
            case 1:             //check type is single choice
                if (count != 1) return false;
                break;
            case 2:             //check type is multi choice
                if (count < 2) return false;
                break;
        }
        return true;
    }

    public boolean checkNonDuplicateOption(List<OptionDTO> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(i).getOptionContent().equals(list.get(j).getOptionContent()))
                    return false;
            }
        }
        return true;
    }
}
