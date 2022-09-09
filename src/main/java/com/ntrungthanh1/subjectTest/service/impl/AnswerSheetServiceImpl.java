package com.ntrungthanh1.subjectTest.service.impl;

import com.ntrungthanh1.subjectTest.exception.ServiceException;
import com.ntrungthanh1.subjectTest.exception.custom.NotFoundException;
import com.ntrungthanh1.subjectTest.model.entity.AnswerSheet;
import com.ntrungthanh1.subjectTest.model.entity.Option;
import com.ntrungthanh1.subjectTest.model.entity.Question;
import com.ntrungthanh1.subjectTest.model.entity.TestingResult;
import com.ntrungthanh1.subjectTest.model.projection.AnswerProjection;
import com.ntrungthanh1.subjectTest.model.request.AnswerSheetRequest;
import com.ntrungthanh1.subjectTest.model.response.custom.AnswerSheetRespone;
import com.ntrungthanh1.subjectTest.repository.*;
import com.ntrungthanh1.subjectTest.service.AnswerSheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Time;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class AnswerSheetServiceImpl implements AnswerSheetService {
    @Autowired
    AnswerSheetRepository answerSheetRepository;

    @Autowired
    TestingResultRepository testingResultRepository;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    OptionRepository optionRepository;

    @Override
    @Transactional
    public List<AnswerSheet> createAnswerSheet(AnswerSheetRequest answerSheetRequest) {
        TestingResult testingResult = Optional.ofNullable(testingResultRepository.getTestResultById(answerSheetRequest.getIdTestResult()))
                .orElseThrow(() -> new NotFoundException("Not found Test Result by Id"));
        Question question = Optional.ofNullable(questionRepository.getQuestionById(answerSheetRequest.getIdQuestion()))
                .orElseThrow(() -> new NotFoundException("Not found Question by Id"));
        List<Option> listChoiceOption = Optional.ofNullable(optionRepository.findAllById(answerSheetRequest.getIdOption()))
                .orElseThrow(() -> new NotFoundException("Not found Option by Id"));

        Date time = new Date();
        Date startTime = testingResult.getStartTime();

            //check time not between start time -> (start_time + doing_duration)
        if (time.before(startTime) || time.after(endTime(startTime , testingResult.getTest().getDoingDuration()))) {
            throw new ServiceException("Time is over deadline of exam");
        }

        if(question.getQuestionType().getTypeName() == "SINGLE_CHOICE" && listChoiceOption.size() > 1){
            throw new ServiceException("Single choice question can't have list option");
        }

        List<Option> listTrueOption = Optional.ofNullable(optionRepository.findListByQuestion(question))
                .orElseThrow(() -> new NotFoundException("Not found List Correct Option "));

        List<AnswerSheet> answerSheetDB = answerSheetRepository
                .findByTestingResultAndQuestion(answerSheetRequest.getIdTestResult() , answerSheetRequest.getIdQuestion());

        //check list choice option same 100% list correct option
        double grade = 0;
        if(checkEqualsTwoListOption(listChoiceOption , listTrueOption)){
            grade = question.getMark();
        }
        List<AnswerSheet> listResult = new ArrayList<>();

        // Question no exists in AnswerSheet table
        if(answerSheetDB.isEmpty())
        {
            AnswerSheet answerSheet = new AnswerSheet();
            answerSheet.setTestingResult(testingResult);
            answerSheet.setQuestion(question);
            answerSheet.setChosenTime(new Date());
            answerSheet.setGrade(grade);
            //save answer each list answer into db
            for (Option option : listChoiceOption)
            {
                answerSheet.setChosenAnswer(option.getOptionContent());
                answerSheet.setOption(option);
                AnswerSheet result = Optional.ofNullable(answerSheetRepository.save(answerSheet))
                        .orElseThrow(() -> new ServiceException("Could not save answer"));
                listResult.add(result);
            }
            return listResult;
        }
        //question exists in answer sheet table db
        else {
            //Update option of list answer into db
            for (int i = 0 ; i < listChoiceOption.size() ; i++)
            {
                answerSheetDB.get(i).setGrade(grade);
                answerSheetDB.get(i).setChosenAnswer(listChoiceOption.get(i).getOptionContent());
                answerSheetDB.get(i).setOption(listChoiceOption.get(i));
                AnswerSheet result = Optional.ofNullable(answerSheetRepository.save(answerSheetDB.get(i)))
                        .orElseThrow(() -> new ServiceException("Could not save answer"));
                listResult.add(result);
            }
            return listResult;
        }
    }

    public boolean checkEqualsTwoListOption(List<Option> listChoiceOption , List<Option> listTrueOption ){
        for (Option option : listTrueOption){              //check list Choice Option == list True Option
            if (!listChoiceOption.contains(option))
            {
                return false;
            }
        }
        return true;
    }

    public Date endTime(Date date , int minutes){
        Long time = TimeUnit.MINUTES.toMillis(minutes);

        Long timed = date.getTime() + time;
        return new Date(timed);
    }


    @Override
    public List<AnswerProjection> getListAnswer(Long testingResultId){
        if(!testingResultRepository.existsById(testingResultId)){
            throw new NotFoundException("Not found Testing Result");
        }
        List<AnswerProjection> list = answerSheetRepository.findByTestingResult(testingResultId);
        if(list.isEmpty()){
            throw new NotFoundException("Not found answer");
        }
        List<AnswerSheetRespone> result = new ArrayList<>();
        return list;
    }
}
